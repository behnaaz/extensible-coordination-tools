/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.reo.simulation.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.channels.*;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.simulation.StochasticReoProperties;
import org.ect.reo.simulation.distributions.ConstantDistribution;
import org.ect.reo.simulation.distributions.IfNeededDistribution;
import org.ect.reo.simulation.distributions.ReoDistribution;
import org.ect.reo.simulation.views.SimulationView;
import org.ect.reo.simulation.views.SimulationViewResults;
import org.ect.reo.simulation.views.SimulationViewStoppedResults;


import JSci.maths.statistics.*;

/**
 * Main simulator class for the simulation. An instance of this class has to be created with all parameters for the simulation. <br>
 * After that, the simulation can be run by calling the runSimulation method with a progress monitor. <br>
 * Finally outputResults has to be called to output the results to the simulation view.
 */
public class ReoSimulator {
	public static String STATE_EMPTY = "e";
	public static String STATE_WAITING = "w";
	public static String STATE_BUSY = "b";
	public static String STATE_FULL = "f";
	private static String[] POS_STOPPED_REASONS = {"Cancelled", "Empty eventlist", "Max simulation time", "Max events", "Livelock", "Deadlock", "Observed state"}; 
	private boolean useMaxEvents, stateChanged, useLongTermSimulation, stopRun;
	private boolean detectDeadlock, detectLivelock;
	private int activeColourings, allPortsWaitingSteps, numOfBatches, runCount, maxLivelockSteps; 
	private int warmUpEvents, maxEvents, batchLengthEvents, currentBatch;
	private long startTime;
	private double time, maxSimTime, warmUpInterval, batchLengthTime, confidence;
	private int[] eventCount;
	private double[] batchTimes, batchEndTimes;
	private String[] stopStates, specialStates;
	private String[] stoppedReason;
	private Connector connector;
	private AnimationTable table;
	private AnimationTable originalTable;
	private List<Node> boundaryNodes;
	private List<PrimitiveEnd> boundaryPrimitiveEnds;
	private List<FIFO> buffers;
	private List<Connectable> border;
	private StatisticCategory requestStatCategory, bufferStatCategory, nodeStatCategory, channelStatCategory, endEndStatCategory;	
	private HashMap<PrimitiveEnd, ReoDistribution> distributionMap;
	private HashMap<Node, ReoDistribution> arrivalDistributionMap;
	private HashMap<Connectable, Token> activeTokenMap;
	private StateStatistics colouringStatistics, systemStatistics, specialStateStatistics;
	private Random random;
    private TreeSet<ReoEvent> eventList;
    private static HashMap<String, StatisticCategory> statisticCategories;
    
    // Comparator to insert a event at the right place in the eventlist. 
	private Comparator<ReoEvent> eventComparator = new Comparator<ReoEvent>() {
    	public int compare(ReoEvent event1, ReoEvent event2) {
    		// earlier events get higher priority
    		if (event1.getTime() < event2.getTime()) {
    			return -1;
    		} else if (event1.getTime() > event2.getTime()) {
    			return 1;
    		
    		// if events are at the same time, give a ColouringEvent priority over RequestEvents
    		} else if (event1 instanceof ColouringEvent && event2 instanceof RequestEvent) {
    			return 1;
    		} else if (event2 instanceof ColouringEvent && event1 instanceof RequestEvent) {
    			return -1;
    		} else if (event1 instanceof RequestEvent && event2 instanceof RequestEvent) {
    			// If both events are request events, compare the names of the corresponding nodes
    			return getNodeString(((RequestEvent) event1).getPort()).compareTo(getNodeString(((RequestEvent) event2).getPort()));
    		} else {
    			// Time and event type are the same: compare hashcode
    			return ((Integer) event1.hashCode()).compareTo((Integer) event2.hashCode());
    		}
    	} 
    };
    
	
	public ReoSimulator(Connector connector, AnimationTable table, double warmUp, double max, int batches, boolean useEvents, 
						boolean longTerm, int seed, double confidenceLevel, boolean detectDeadlock,
						boolean detectLivelock, int livelockSteps, String[] stopStates, String[] specialStates,
						HashMap<String, StatisticCategory> statisticCategories) {
		
		super();
		startTime = System.currentTimeMillis();
		ReoSimulator.statisticCategories =  statisticCategories;
		this.connector = connector;
		this.table = table;
		originalTable = table;
		useLongTermSimulation = longTerm;
		useMaxEvents = useEvents;
		numOfBatches = batches;
		maxSimTime = max;
		warmUpInterval = warmUp;
		warmUpEvents = (int) warmUp;
		maxEvents = (int) max;	
		random = (seed == Integer.MIN_VALUE) ? new Random() : new Random(seed);
		stopRun = false;
		eventCount = new int[numOfBatches];
		stoppedReason = new String[numOfBatches];
		maxLivelockSteps = livelockSteps;
		this.detectDeadlock = detectDeadlock;
		this.detectLivelock = detectLivelock;
		this.stopStates = stopStates;
		this.specialStates = specialStates;
				
		time = 0;		
		runCount = 0;
		allPortsWaitingSteps = 0;
		activeColourings = 0;
		currentBatch = -1;

		if (useLongTermSimulation) {
			batchLengthTime = ((maxSimTime - warmUpInterval) / numOfBatches);
			batchLengthEvents = (int) Math.ceil((maxEvents - warmUpEvents) / numOfBatches);
		} else {
			batchLengthTime = maxSimTime - warmUpInterval;
			batchLengthEvents = maxEvents - warmUpEvents;
		}
		
		batchTimes = new double[numOfBatches + 2];
		batchEndTimes = new double[numOfBatches + 2];
	
		eventList = new TreeSet<ReoEvent>(eventComparator);
		bufferStatCategory = statisticCategories.get(SimulationView.BUFFER_UT_STATKEY);
		nodeStatCategory = statisticCategories.get(SimulationView.NODE_STATE_STATKEY);
		requestStatCategory = statisticCategories.get(SimulationView.REQ_OBS_STATKEY);
		channelStatCategory = statisticCategories.get(SimulationView.CH_UT_STATKEY);
		endEndStatCategory = statisticCategories.get(SimulationView.END_END_STATKEY);
		distributionMap = new HashMap<PrimitiveEnd, ReoDistribution>();
		arrivalDistributionMap = new HashMap<Node, ReoDistribution>();
		colouringStatistics = new StateStatistics(null, time, eventCount[runCount], this, "", true, false);
		statisticCategories.get(SimulationView.COL_STATKEY).setStateStatistics(colouringStatistics);
		systemStatistics = new StateStatistics(null, time, eventCount[runCount], this, "", true, false);
		statisticCategories.get(SimulationView.SYS_STATE_STATKEY).setStateStatistics(systemStatistics);
		specialStateStatistics = new StateStatistics(STATE_EMPTY, time, eventCount[runCount], this, arrayToString(specialStates, ", "), true, true);
		statisticCategories.get(SimulationView.SPECIALSTATE_STATKEY).setStateStatistics(specialStateStatistics);
		activeTokenMap = new HashMap<Connectable, Token>();
		border = new ArrayList<Connectable>();
		confidence = 1 - ((1 - confidenceLevel) / 2);
	}
	
	
	private void resetShortTermSimulation() {
		
		// reset counters and clear the eventList and activeTokenMap
		time = 0;	
		allPortsWaitingSteps = 0;
		activeColourings = 0;
		eventList.clear();
		activeTokenMap.clear();
		table = originalTable;
		for (ReoDistribution distribution : arrivalDistributionMap.values()) {
			distribution.resetAlternation();
		}
		
		// for the statistics we have to initialize the states again
		resetStates();
	}
	
	
	private void resetStates() {
		
		// initialize all the states of the statistics to the starting value
		for (Node boundary : boundaryNodes) {
			nodeStatCategory.getStateStatistics(boundary).setStateAndTime(STATE_EMPTY, 0, 0);
			requestStatCategory.getStateStatistics(boundary).setStateAndTime(STATE_EMPTY, 0, 0);
			
			// schedule the first request arrivals on the boundary nodes
			eventList.add(new RequestEvent((StochasticReoProperties.getRequestStart(boundary) ? 0 : arrivalDistributionMap.get(boundary).inverse(random.nextDouble())), boundary));
		}
		for (StateStatistics stateStat : channelStatCategory.getStateStatMap().values()) {
			stateStat.setStateAndTime(STATE_EMPTY, 0, 0);
		}
		for (FIFO fifo : buffers) {
			bufferStatCategory.getStateStatistics(fifo).setStateAndTime(fifo.isFull() ? STATE_FULL : STATE_EMPTY, 0, 0);
		}
		colouringStatistics.setStateAndTime(null, 0, 0);
		systemStatistics.setStateAndTime(null, 0, 0);
		specialStateStatistics.setStateAndTime(STATE_EMPTY, 0, 0);
	}


	/**
	 * Get the start time of batch i
	 */
	public double getBatchStart(int i) {
		
		// get the time at which a batch starts, when the batches are based on the number of events we have to get the starting time of the batch
		// from the array with starting times, else we can just calculate it
		if (useLongTermSimulation) {
			if (useMaxEvents) {	
				return batchTimes[i];
			} else {
				return warmUpInterval + (i * batchLengthTime);
			}
		} else {
			return useMaxEvents ? batchTimes[i] : warmUpInterval;
		}
	}
	
	
	/**
	 * Get the end time of batch i. If the end time is not defined yet (when using events), this function will return Double.MAX_VALUE 
	 */
	public double getBatchEnd(int i) {
		
		// get the time at which a batch ends, when the batches are based on the number of events we have to get the starting time of the batch
		// from the array with starting times. Because it can be that we don't know the ending time of the batch (yet), we will return the max value if it is not set. 
		// if the batches are based on time we can just calculate the ending time
		if (useLongTermSimulation) {
			if (useMaxEvents) {
				return (batchTimes[i + 1] == 0) ? Double.MAX_VALUE : batchTimes[i + 1];
			} else {
				return warmUpInterval + ((i + 1) * batchLengthTime);
			}
		} else {
			return batchEndTimes[i];
		}
	}
	
	
	/**
	 * Get the length (time) of batch i
	 */
	public double getBatchLength(int i) {
		return getBatchEnd(i) - getBatchStart(i); 
	}
	

	private int getBatchNr(double time) {
		
		// return the batch number for this time, it will return -1 if the batch number is not valid (less than 0 or greater than numOfBatches - 1)
		int result = (int) Math.floor(((time - warmUpInterval) / batchLengthTime)); 
		return ((result >= 0) && (result < numOfBatches)) ? result : -1;
	}
	
	
	/**
	 * Get the warm up period (time) of the simulation 
	 */
	public double getWarmUpPeriod() {
		if (useLongTermSimulation) {
			return useMaxEvents ? getBatchStart(0) : warmUpInterval;
		} else {
			return getBatchStart(runCount);
		}
	}

	
	/**
	 * Main method of the simulation. Does the actual simulation.
	 * @param monitor Progress monitor which has to updated during the simulation
	 */
	public void runSimulation(IProgressMonitor monitor) {
		
		try {
			initialize();
			monitor.beginTask("Running simulation " + (useLongTermSimulation ? "batch " : "run ") + "1 of " + numOfBatches, numOfBatches);
			if (useLongTermSimulation) {
				handleEvents(new SubProgressMonitor(monitor, numOfBatches));	
				batchEndTimes[0] = time;
				monitor.setTaskName("Generating output");
			} else {
				// When using short term simulation, make a new sub progress monitor for every run.
				for (int i = 0; i < numOfBatches; i++) {
					handleEvents(new SubProgressMonitor(monitor, 1));
					batchEndTimes[runCount] = time;	
					resetShortTermSimulation();
					stopRun = false;
					runCount++;
				}
				monitor.setTaskName("Generating output");
			}			
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			monitor.done();
		}
	}
	
	
	private void initialize() {
		
		// initialise all variables which are related to the boundaries
		calculateBoundaries();
		
		for (Node boundary : boundaryNodes) {
			
			// set the arrival distribution for the boundary node and add this to the map
			ReoDistribution dist = StochasticReoProperties.getArrivalRate(boundary);
			arrivalDistributionMap.put(boundary, dist);
			
			// check if the node starts with a request, if so add the first request at time zero, else draw the first arrival from the distribution of the node
			eventList.add(new RequestEvent((StochasticReoProperties.getRequestStart(boundary) ? 0 : dist.inverse(random.nextDouble())), boundary));

			// add the boundary node to the nodeStatMap for the state statistics of the node
			nodeStatCategory.addStateStatistics(boundary, new StateStatistics(STATE_EMPTY, time, eventCount[runCount], this, getNodeString(boundary), true, false));
			
			// add the boundary node to the requestStatMap for the observations of the arriving requests
			requestStatCategory.addStateStatistics(boundary, new StateStatistics(STATE_EMPTY, time, eventCount[runCount], this, getNodeString(boundary), false, false, true));
		}
		
		// initialise the state of the system
		systemStatistics.changeState(getSystemState(), time, eventCount[runCount]);
	}
	
	
	private void calculateBoundaries() {
		
		// initialise the boundaryNodes, the boundaryPrimitiveEnd and the involved FIFO buffers
		List<Node> nodes = connector.getBoundaryNodes();
		boundaryNodes = new ArrayList<Node>();
		boundaryNodes.addAll(nodes);
		Collections.sort(boundaryNodes, new Comparator<Node>() {
			public int compare(Node node1, Node node2) {
				return getNodeString(node1).compareTo(getNodeString(node2));
			}
		});
		border.addAll(connector.getForeignNodes());
		border.addAll(connector.getForeignPrimitives());
		boundaryPrimitiveEnds = new ArrayList<PrimitiveEnd>();
		buffers = new ArrayList<FIFO>();
		
		for (Node node : connector.getSourceNodes()) {
			for (PrimitiveEnd end : node.getSinkEnds()) {
				boundaryPrimitiveEnds.add(end);
			}
		}
		
		for (Node node : connector.getSinkNodes()) {
			for (PrimitiveEnd end : node.getSourceEnds()) {
				boundaryPrimitiveEnds.add(end);
			}
		}
		
		
		for (Primitive primitive : connector.getPrimitives()) {
			
			// if the primitive is of type FIFO add it to the buffer list and add it to the fifo statistics map
			if (primitive instanceof FIFO) {
				FIFO fifo = (FIFO) primitive;
				buffers.add(fifo);
				bufferStatCategory.addStateStatistics(fifo, new StateStatistics(fifo.isFull() ? STATE_FULL : STATE_EMPTY, time, eventCount[runCount], this, getPrimitiveString(fifo), true, true));
				channelStatCategory.addStateStatistics(fifo.getSourceEnd(0), new StateStatistics(STATE_EMPTY, time, eventCount[runCount], this, getEndString(fifo.getSourceEnd(0)), false, true));
				channelStatCategory.addStateStatistics(fifo.getSinkEnd(0), new StateStatistics(STATE_EMPTY, time, eventCount[runCount], this, getEndString(fifo.getSinkEnd(0)), false, true));
			} else if ((primitive instanceof LossySync) || (primitive instanceof AsyncDrain) || (primitive instanceof AsyncSpout)) {
				for (PrimitiveEnd end : primitive.getAllEnds()){
					channelStatCategory.addStateStatistics(end, new StateStatistics(STATE_EMPTY, time, eventCount[runCount], this, getEndString(end, true), false, true));	
				}
			} else {
				//add the primitive to the channelStatMap
				channelStatCategory.addStateStatistics(primitive, new StateStatistics(STATE_EMPTY, time, eventCount[runCount], this, getPrimitiveString(primitive), false, true));	
			}
			
			// set the distributions for this primitive
			initDistribution(primitive);
		}
		Collections.sort(buffers, new Comparator<FIFO>() {
			public int compare(FIFO fifo1, FIFO fifo2) {
				return getPrimitiveString(fifo1).compareTo(getPrimitiveString(fifo2));
			}
		});
	}
	
	
	private void initDistribution(Primitive primitive) {
		
		// initialize the distributionMap. The map contains a distribution for every primitiveEnd
		if (primitive instanceof Sync) {
			
			// for the Sync channel the delay will be set to the source end. The delay at the sink end will be set to zero
			distributionMap.put(primitive.getSourceEnd(0), StochasticReoProperties.getProcessingDelay1(primitive));
			distributionMap.put(primitive.getSinkEnd(0), new ReoDistribution(new ConstantDistribution(0), false, false));
			
		} else if (primitive instanceof LossySync) {
			
			// for the lossy sync, the delay when losing the data (delay2) will be set to the source end and the delay
			// when behaving as a sync channel (delay1) will be set to the sink end. When drawing from this channel
			// we will check if the channel loses it's data. If so we will use the distribution on the source end, if not we will use 0 on that end.
			distributionMap.put(primitive.getSourceEnd(0), StochasticReoProperties.getProcessingDelay2(primitive));
			distributionMap.put(primitive.getSinkEnd(0), StochasticReoProperties.getProcessingDelay1(primitive));
			
		} else if (primitive instanceof FIFO) {
			
			// for the FIFO buffer the first delay is on the source end and the second delay is on the sink end 
			distributionMap.put(primitive.getSourceEnd(0), StochasticReoProperties.getProcessingDelay1(primitive));
			distributionMap.put(primitive.getSinkEnd(0), StochasticReoProperties.getProcessingDelay2(primitive));
			
		} else if (primitive instanceof AsyncDrain) {
			
			// for the AsyncDrain both source ends get one type of delay
			distributionMap.put(primitive.getSourceEnd(0), StochasticReoProperties.getProcessingDelay1(primitive));
			distributionMap.put(primitive.getSourceEnd(1), StochasticReoProperties.getProcessingDelay2(primitive));	
			
		} else if (primitive instanceof AsyncSpout) {
			
			// for the AsyncSpout both sink ends get one type of delay
			distributionMap.put(primitive.getSinkEnd(0), StochasticReoProperties.getProcessingDelay1(primitive));
			distributionMap.put(primitive.getSinkEnd(1), StochasticReoProperties.getProcessingDelay2(primitive));	
			
		} else {
			
			// if the primitive is not of one of the types above, just set the delay to every end
			for (PrimitiveEnd end : primitive.getAllEnds()) { 
				distributionMap.put(end, StochasticReoProperties.getProcessingDelay1(primitive));
			}
		}
	}
	
	
	private void handleEvents(IProgressMonitor monitor) {
		// Update the progress monitor and set the right name
		monitor.beginTask("Running simulation", numOfBatches);
		if (useLongTermSimulation) {
			monitor.setTaskName("Running simulation warm up period");
		} else {
			if (runCount > 0) {
				monitor.setTaskName("Running simulation run " + (runCount + 1) + " of " + numOfBatches + " | " + getTimeLeftString());
			} else {
				monitor.setTaskName("Running simulation run 1 of " + numOfBatches);
			}
		}
		
		while(true) {
			// check if the simulation or the run has to be ended
			if (stopRun) {
				monitor.done();
				return;
			} else if (monitor.isCanceled()) {
				stoppedReason[runCount] = "Cancelled";
				monitor.done();
				return;
			} else if (eventList.isEmpty()) {
				stoppedReason[runCount] = "Empty eventlist";
				monitor.done();
				return;
			} else {
				if (useMaxEvents) {
					if (eventCount[runCount] >= maxEvents) {
						stoppedReason[runCount] = "Max events";
						monitor.done();
						return;
					}
				} else if (time >= maxSimTime) {
					stoppedReason[runCount] = "Max simulation time";
					monitor.done();
					return;
				}
			}			
			
			// get the first event from the list and remove it
			ReoEvent event = eventList.first();
		    eventList.remove(eventList.first());
		    
		    if (time != event.getTime()) {
		    	// if the time of the event is different, change the current simulation time and set the system to notChanged
		    	time = event.getTime();
		    	stateChanged = false;
		    	if (useLongTermSimulation && (!useMaxEvents)) {
		    		int batch = getBatchNr(time);
		    		if (batch > currentBatch) {
		    			// We are in a new batch, update the task name and indicate that we have done one batch
		    			currentBatch = batch;
		    			monitor.worked(1);
		    			monitor.setTaskName("Running simulation batch " + (currentBatch + 1) + " of " + numOfBatches + " | " + getTimeLeftString());
		    		}
		    	}
		    }
		    
		    // handle the event
			handleEvent(event);
			
			eventCount[runCount]++;
			
			// if we are using events for splitting the batches, we have to increment the event count
			if (useMaxEvents) {
				if (eventCount[runCount] == warmUpEvents) {
					batchTimes[useLongTermSimulation ? 0 : runCount] = time;
				} else if (useLongTermSimulation && (eventCount[runCount] > warmUpEvents) && (((eventCount[runCount] - warmUpEvents) % batchLengthEvents) == 0)) {
					// if we are at the last event of the batch, remember the time for this event
					batchTimes[((eventCount[runCount] - warmUpEvents) / batchLengthEvents)] = time;
					monitor.worked(1);
					monitor.setTaskName("Running simulation batch " + ((eventCount[runCount] - warmUpEvents) / batchLengthEvents) + " of " + numOfBatches + " | " + getTimeLeftString());
				}
			}
		}
	}
	
	
	private String getTimeLeftString() {
		// Will return an estimation of the time left for the simulation. The string will have this format: "Estimated time left: hh:mm:ss"		
		double timeBusy = (double) System.currentTimeMillis() - startTime;
		double timeLeft;
		if (useLongTermSimulation) {
			if (useMaxEvents) {
				timeLeft = ((timeBusy / eventCount[runCount]) * maxEvents) - timeBusy; 
			} else {
				timeLeft = (timeBusy / time * maxSimTime) - timeBusy;
			}
		} else {
			timeLeft = (timeBusy / runCount) * (numOfBatches - runCount);
		}
		
		int hours = (int) (timeLeft / 3600000);
		int minutes = (int) (timeLeft - 3600000 * hours) / 60000;
		int seconds = (int) (timeLeft - 3600000 * hours - 60000 * minutes) / 1000;
		String result = "Estimated time left: ";
		result = result + ((hours < 10) ? "0" + hours : hours) + ":";
		result = result + ((minutes < 10) ? "0" + minutes : minutes) + ":";
		result = result + ((seconds < 10) ? "0" + seconds : seconds);
		return result;
	}

	
	private void handleEvent(ReoEvent event) {
		
		// change the availability of the involved ports
		updatePorts(event);
		
		if (event instanceof RequestEvent) {
			scheduleNextRequest((RequestEvent) event);
		} else {
			// event is a ColouringEvent
			
			// decrease the number of active colourings
			activeColourings--;
			
			// Important!! mark system as changed, if you have a colouring without involved ports, you still have to know the system is changed
			stateChanged = true;
			
			// update colouring statistics
			colouringStatistics.changeState(null, time, eventCount[runCount]);
		}
		
		// go to the next event in one of these cases:
		//		1. the system hasn't changed
		//		2. there is an active colouring
		//		3. the next event is at the same time as the current event
		if (!stateChanged || (activeColourings > 0) || (!eventList.isEmpty() && (eventList.first().getTime() == time))) {
			return;
		}
		
		// check if we can have a new colouring
		changeColouring();		
	}
	
	
	private void updatePorts(ReoEvent event) {
		
		if (event instanceof RequestEvent) {			
			Node port = ((RequestEvent) event).getPort();
			
			// update the statistics for the observations of the request, the nodeStatMap contains the current state of the system because 
			// that map is updated in the changeNodeState. The availability of the port can only change from empty to waiting by a requestEvent
			requestStatCategory.getStateStatistics(port).changeState(nodeStatCategory.getStateStatMap().get(port).getState(), time, eventCount[runCount]);
			if (nodeStatCategory.getStateStatisticsState(port) == STATE_EMPTY) {
				changeNodeState(port, STATE_WAITING);
			}
			
		} else {			
			// event is a ColouringEvent
			ColouringEvent colEvent = (ColouringEvent) event;
			
			// change the availability of all involved ports to empty
			for (Node port : colEvent.getPorts()) {
				changeNodeState(port, STATE_EMPTY);
				
				// if the distribution of the port is AlwaysNeeded, immediately change the state to waiting
				if (arrivalDistributionMap.get(port).isDrawWhenEmpty()) {
					eventList.add(new RequestEvent(time + arrivalDistributionMap.get(port).inverse(random.nextDouble()), port));
				}
				
				allPortsWaitingSteps = 0;
			}
			
			// change the state of the filled fifo buffers to full
			for (FIFO fifo : colEvent.getFilledBuffers()) {
				bufferStatCategory.getStateStatistics(fifo).changeState(STATE_FULL, time, eventCount[runCount]);
			}
			
			// change the state of the emptied fifo buffers to empty
			for (FIFO fifo : colEvent.getEmptiedBuffers()) {
				bufferStatCategory.getStateStatistics(fifo).changeState(STATE_EMPTY, time, eventCount[runCount]);
			}
		}
	}
	
	
	private void changeNodeState(Node port, String state) {
		// if state is different than the current state of the port, change the state of the port and mark the system as changed
		if (nodeStatCategory.getStateStatisticsState(port) != state) {
			nodeStatCategory.getStateStatistics(port).changeState(state, time, eventCount[runCount]);
			stateChanged = true;
			systemStatistics.changeState(getSystemState(), time, eventCount[runCount]);
		}
	}
	
	
	private void scheduleNextRequest(RequestEvent event) {
		
		// Schedule when the next request should arrive
		Node port = event.getPort();
		ProbabilityDistribution dist = arrivalDistributionMap.get(port).getDistribution();
		
		// when the distribution of the port is of type IfNeeded or AlwaysAvailable, we won't schedule the next arrival
		if (!((dist instanceof IfNeededDistribution) || (arrivalDistributionMap.get(port).isDrawWhenEmpty()))) {
			double draw = dist.inverse(random.nextDouble());
			if ((draw > 0) && (draw != Double.NaN)) {
				eventList.add(new RequestEvent(time + draw, port));
			}
		}		
	}
	
	
	private void changeColouring() {  
		List<Colouring> compatibleColourings = new ArrayList<Colouring>();

		// loop over all possible next colourings
		for (Colouring colouring : table.getColourings()) {
			
			// colouring is not compatible if it has no flow at all
            boolean compatible = colouring.hasFlow();
           
            // check if the colouring is compatible
            if (compatible) {
                for (PrimitiveEnd end : boundaryPrimitiveEnds) {
                	// if an end has flow, there should be a waiting request at the node of the end, else the colouring is not compatible
                	if (((colouring.isFlow(end) && !hasRequest(end)) || (colouring.isNoFlowGiveReason(end) && hasRequest(end))) && !(arrivalDistributionMap.get(end.getNode()).getDistribution() instanceof IfNeededDistribution)) {
                    	compatible = false;
                        break;
                    }
                }
            }
            
            // if the colouring is still compatible, add it to the list
            if (compatible) {
                compatibleColourings.add(colouring);               
            }           
        }
		
		// if there are compatible colourings, choose one of these at random and get the next animation table from this colouring
		if (!compatibleColourings.isEmpty()) {
			int randomIndex = randomIndex(compatibleColourings.size());
			Colouring colouring = compatibleColourings.get(randomIndex);
			activateColouring(colouring);
			table = (AnimationTable) colouring.getNextColouringTable();
			
			// if we want to detect a deadlock, check if we are in one (when the animation table is empty)
			if ((detectDeadlock) && (table.size() == 0)) {
				stoppedReason[runCount] = "Deadlock";
				stopRun = true;			
			}
		}
		
		// to detect livelocks, we will count the number of times a colouring is chosen without using any port while all ports are waiting
		if ((detectLivelock) && (allPortsWaiting())) {
			allPortsWaitingSteps++;
			if (allPortsWaitingSteps == maxLivelockSteps) {
				stoppedReason[runCount] = "Livelock";
				stopRun = true;
			}
		}
	}
	
	
	public boolean inWarmUpPeriod(double time, int event) {
		return (((useMaxEvents) && (event <= warmUpEvents)) || ((!useMaxEvents) && (time <= warmUpInterval))); 
	}
	
	private boolean allPortsWaiting() {
		
		// check if all ports are in the waiting state
		for (Node boundary : boundaryNodes) {
			if (nodeStatCategory.getStateStatisticsState(boundary) != STATE_WAITING) {
				return false;
			}
		}
		return true;
	}
	
	
	private void activateColouring(Colouring colouring) {
		// change the active colouring
		colouringStatistics.changeState(colouring, time, eventCount[runCount]);
        
		// generate the end of colouring event, this method will also update all relevant statistics (end to end delay, channel utilization)
		ColouringEvent event = DepthFirst.getColouringEvent(colouring, this);
        
		// set the involved ports to busy
		activatePorts(event.getPorts());
        
		// finally add the event to the eventlist
		eventList.add(event);
		activeColourings++;
	}
	
		
	private void activatePorts(List<Node> nodes) {
		
		// set all the boundary nodes which will be used by a colouring to busy 
		for (Node node : nodes) {
			changeNodeState(node, STATE_BUSY);
			allPortsWaitingSteps = 0;
		}
	}

	
	private int randomIndex(int number) {
		
		// return a random number between 0 (inclusive) and number (exclusive)
		return random.nextInt(number);
	}
	
	private boolean hasRequest(PrimitiveEnd end) {
		
		// check if the node of the primitiveEnd has a waiting request
		return hasRequest(end.getNode());
	}
	
	
	private boolean hasRequest(Node node) {
		
		// check if the node has a waiting request
		return nodeStatCategory.getStateStatisticsState(node) == STATE_WAITING;
	}
	
	
	private String getSystemState() {
		
		// build up a string which represents the state of the system. 
		// it's composed of the state of the boundary nodes and the state of the fifo buffers
	    StringBuffer result = new StringBuffer();

		for (Node boundary : boundaryNodes) {
			result.append(nodeStatCategory.getStateStatisticsState(boundary).toString());
		}
		 
		for (FIFO fifo : buffers) {
			result.append(bufferStatCategory.getStateStatisticsState(fifo).toString());
		}
		
		if (!inWarmUpPeriod(time, eventCount[runCount])) {
			if (stateInStateArray(result.toString(), stopStates)) {
				stopRun = true;
				stoppedReason[runCount] = "Observed state: " + result.toString();
			}
			if (stateInStateArray(result.toString(), specialStates)) {
				specialStateStatistics.changeState("sp", time, eventCount[runCount]);
			} else {
				specialStateStatistics.changeState(STATE_EMPTY, time, eventCount[runCount]);
			}
		}
		
		return result.toString();
	}
	
	
	private boolean stateInStateArray(String systemState, String[] array) {
		for (String state : array) {
			if (systemState.length() == state.length()) {
				boolean stop = true;
				for (int i = 0; i < state.length(); i++) {
					if ((state.charAt(i) != '?') && (state.charAt(i) != systemState.charAt(i))) {
						stop = false;
						break;
					}
				}
				if (stop) {
					return true;
				}				
			}
		}
		return false;
	}
	
	
	private String arrayToString(String[] a, String separator) {
	    StringBuffer result = new StringBuffer();
	    if (a.length > 0) {
	        result.append(a[0]);
	        for (int i=1; i<a.length; i++) {
	            result.append(separator);
	            result.append(a[i]);
	        }
	    }
	    return result.toString();
	}
	
	
	/**
	 * Output method of the simulation, this method will output all primary results to the Simulation view. It will also calculate and ouput
	 * all derived statistics. It will only output a result is has been checked in the result preferences.
	 */
	public void outputResults() {
		SimulationOutput.outputResults(this, colouringStatistics, systemStatistics, channelStatCategory, requestStatCategory, bufferStatCategory, nodeStatCategory, endEndStatCategory);	
		if (useLongTermSimulation) {
			SimulationViewStoppedResults.addStoppedStatistics((new double[] {batchEndTimes[0]}), eventCount, (new String[] {stoppedReason[0]}), POS_STOPPED_REASONS);
		} else {
			SimulationViewStoppedResults.addStoppedStatistics(batchEndTimes, eventCount, stoppedReason, POS_STOPPED_REASONS);
		}
		SimulationViewResults.addChartCategories();
	}
	
	
	public static String getPrimitiveString(Primitive prim) {
		// return a nice (instead of a hashcode) string for the primitive, it consists of the name of the primitive and the name of the nodes it connects. For example: Sync(A-B)
		if (prim instanceof Channel) {
			Node node1 = ((Channel) prim).getNodeOne();
			Node node2 = ((Channel) prim).getNodeTwo();
			return prim.getName() + "(" + getNodeString(node1) + "-" + getNodeString(node2) + ")";
		} else {
			return prim.toString();
		}
	}
	
	
	public static String getNodeString(Node node) {
		if ((node.getName() == null) || (node.getName().equals(""))) {
			return "Node@" + Integer.toHexString(node.hashCode());
		} else {
			return node.getName();
		}
	}
	
	
	public static String getEndString(PrimitiveEnd end) {
		return getEndString(end, false);
	}
	
	
	public static String getEndString(PrimitiveEnd end, boolean checkLossy) {
		// Return a nice (instead of a hashcode) string for the primitive end. It consists of the nice name of the primitive and the name of the node of the end.
		// For example: Sync(A-B):B. If checkLossy is set to true the ends of a lossy will be translated into Loss or Sync. So for example:
		// LossySync(A-B):A will be LossySync(A-B):Loss and LossySync(A-B):B will be LossySync(A-B):Sync
		if (checkLossy && (end.getPrimitive() instanceof LossySync)) {
			if (end instanceof SourceEnd) {
				return getPrimitiveString(end.getPrimitive()) + ":Loss";
			} else {
				return getPrimitiveString(end.getPrimitive()) + ":Sync";
			}
		} else {
			return getPrimitiveString(end.getPrimitive()) + ":" + getNodeString(end.getNode());	
		}
	}

	
	public int getEventCount() {
		return eventCount[runCount];
	}


	/**
	 * @return the useLongTermSimulation
	 */
	public boolean isUseLongTermSimulation() {
		return useLongTermSimulation;
	}


	/**
	 * @return the runCount
	 */
	public int getRunCount() {
		return runCount;
	}


	/**
	 * @return the useMaxEvents
	 */
	public boolean isUseMaxEvents() {
		return useMaxEvents;
	}


	/**
	 * @return the warmUpEvents
	 */
	public int getWarmUpEvents() {
		return warmUpEvents;
	}


	/**
	 * @return the batchLengthEvents
	 */
	public int getBatchLengthEvents() {
		return batchLengthEvents;
	}


	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}


	/**
	 * @return the connector
	 */
	public Connector getConnector() {
		return connector;
	}


	/**
	 * @return the boundaryNodes
	 */
	public List<Node> getBoundaryNodes() {
		return boundaryNodes;
	}


	/**
	 * @return the statisticCategories
	 */
	public static HashMap<String, StatisticCategory> getStatisticCategories() {
		return statisticCategories;
	}


	/**
	 * @return the boundaryPrimitiveEnds
	 */
	public List<PrimitiveEnd> getBoundaryPrimitiveEnds() {
		return boundaryPrimitiveEnds;
	}


	/**
	 * @return the border
	 */
	public List<Connectable> getBorder() {
		return border;
	}


	/**
	 * @return the buffers
	 */
	public List<FIFO> getBuffers() {
		return buffers;
	}


	/**
	 * @return the numOfBatches
	 */
	public int getNumOfBatches() {
		return numOfBatches;
	}


	/**
	 * @return the warmUpInterval
	 */
	public double getWarmUpInterval() {
		return warmUpInterval;
	}

	
	/**
	 * @return the batchLength
	 */
	public double getBatchLengthTime() {
		return batchLengthTime;
	}


	/**
	 * @return the distributionMap
	 */
	public HashMap<PrimitiveEnd, ReoDistribution> getDistributionMap() {
		return distributionMap;
	}


	/**
	 * @return the activeTokenMap
	 */
	public HashMap<Connectable, Token> getActiveTokenMap() {
		return activeTokenMap;
	}


	/**
	 * @return the random
	 */
	public Random getRandom() {
		return random;
	}
	
	
	/**
	 * @return the confidence
	 */
	public double getConfidence() {
		return confidence;
	}
	
	
	public StatisticCategory getEndToEndCategory() {
		return endEndStatCategory;
	}
	
	
	public StatisticCategory getChannelStatCategory() {
		return channelStatCategory;
	}
}