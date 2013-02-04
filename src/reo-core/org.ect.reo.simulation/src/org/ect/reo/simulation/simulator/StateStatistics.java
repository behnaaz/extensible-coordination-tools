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

import java.util.HashMap;

import org.ect.reo.colouring.Colouring;


/**
 * Class to keep track of an object which changes it's state all the time. It will keep a map with all observed states, and for every state
 * it will have a Statistic object to keep track of the duration and count. 
 */
public class StateStatistics implements Comparable<StateStatistics> {
	private HashMap<Object, Statistic> stateMap;
	private boolean countOnly;
	private boolean splitInterval;
	private boolean useChart;
	private boolean utilizationStat;
	private double lastChange;
	private int lastChangeEvent, maxChartPoints;
	private Object lastState;
	private String description;
	private ReoSimulator simulator;
	
	
	public StateStatistics(Object state, double time, int event, ReoSimulator sim, String description, boolean split, boolean utilizationStat, boolean countOnly) {
		
		this.countOnly = countOnly;
		useChart = false;
		this.utilizationStat = utilizationStat;
		stateMap = new HashMap<Object, Statistic>();
		lastChange = time;
		lastChangeEvent = event;
		lastState = state;
		simulator = sim;
		splitInterval = split;
		maxChartPoints = 100;
		this.description = description;
	}
	
	
	public StateStatistics(Object state, double time, int event, ReoSimulator sim, String description, boolean split, boolean utilizationStat) {
		
		// call other constructor with countOnly set to false
		this(state, time, event, sim, description, split, utilizationStat, false);
	}
	
	
	public Object getState() {
		return lastState;
	}
	
	
	public void setStateAndTime(Object state, double time, int event) {
		
		// set the state and the time, without updating any statistic
		lastState = state;
		lastChange = time;
		lastChangeEvent = event;
	}
	
	
	public void changeState(Object state, double time, int event) {
		
		// if we only want to count, update the statistic every time, else only update the state if it has changed,
		// also only update the statistic for the last state when the time has also changed (so the last state has been used for more than 0 seconds)
		if ((state != lastState) || countOnly) {
			if (time >= lastChange) {	
				if ((!utilizationStat) || (lastState != ReoSimulator.STATE_EMPTY)) {
					// check if the last used state already occurs in the stateMap. If so use this statistic, else make a new statistic
					Statistic statistic = stateMap.get(lastState);
					if (statistic == null) {
					
						// build the description for the statistic
						String statDescription = description;									
						if (lastState == null) {
							statDescription = (description == "") ? "null" : (description + " - null");
						} else if (lastState instanceof Colouring) {
							String value = stateMap.containsKey(null) ? Integer.toString(stateMap.size()) : Integer.toString(stateMap.size() + 1); 
							statDescription = (description == "") ? value : (description + " - " + value); 
						} else if (!utilizationStat) {
							statDescription = (description == "") ? lastState.toString() : (description + " - " + lastState.toString());
						}	
						
						// create the statistic
						statistic = new Statistic(simulator, statDescription, splitInterval, 1, lastState);
						statistic.setUseChart(useChart);
						statistic.setMaxChartPoints(maxChartPoints);
					}
					
					// add the time the last state has been used
					statistic.addTime(lastChange, time, lastChangeEvent, event);
					
					// add it to the map (again) causing that the map has the most actual statistic
					stateMap.put(lastState, statistic);
				}
				lastChangeEvent = event;
				lastChange = time;
			}
			lastState = state;
		}
	}
	
	
	/**
	 * Print all the statisticString of all statistic in the stateMap to the Console
	 */
	public void printStates() {
		System.out.println(description);
		for (Object key : stateMap.keySet()) {    
			System.out.println(key + ": " + stateMap.get(key).getStatisticString());
		}
	}
	
	
	public Statistic getStatistic(Object key) {
		return stateMap.get(key);
	}


	/**
	 * @return the stateMap
	 */
	public HashMap<Object, Statistic> getStateMap() {
		return stateMap;
	}


	/**
	 * @param useChart the useChart to set
	 */
	public void setUseChart(boolean useChart) {
		this.useChart = useChart;
	}


	/**
	 * @param chartPointsToKeep the chartPointsToKeep to set
	 */
	public void setMaxChartPoints(int maxChartPoints) {
		this.maxChartPoints = maxChartPoints;
	}


	/**
	 * Compare the description of the state statistics
	 */
	public int compareTo(StateStatistics o) {
		return this.description.compareTo(o.description);
	}
}
