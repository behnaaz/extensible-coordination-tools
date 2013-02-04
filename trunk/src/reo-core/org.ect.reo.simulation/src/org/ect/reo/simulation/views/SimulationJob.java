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
package org.ect.reo.simulation.views;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.ect.reo.Connector;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.ColouringFilter;
import org.ect.reo.prefs.ReoPreferences;
import org.ect.reo.simulation.StochasticReoPlugin;
import org.ect.reo.simulation.simulator.ReoSimulator;
import org.ect.reo.simulation.simulator.StatisticCategory;


/**
 * Job for the running the simulation
 */
public class SimulationJob extends Job {
	
	private int seed, batches, livelockColourings;
	
	private double warmUp, simTime, confidence;
	
	private boolean longTerm, batchesEvents, detectDeadlock, detectLivelock;
	
	private String[] stopStates, specialStates;
	
	private Connector connector;
	
	private AnimationTable table;

	private ReoSimulator simulator;
	
	private HashMap<String, StatisticCategory> statisticCategories;
	
	public SimulationJob(Connector connector, boolean longTerm, boolean batchesEvents, double warmUp, double simTime, int batches, double confidence, 
						 boolean detectDeadlock, boolean detectLivelock, int livelockColourings, int seed, String[] stopStates, String[] specialStates,
						 HashMap<String, StatisticCategory> statisticCategories) {
		super("Running simulation");
		this.connector = connector;
		this.longTerm = longTerm;
		this.batchesEvents = batchesEvents;
		this.warmUp = warmUp;
		this.simTime = simTime;
		this.batches = batches;
		this.confidence = confidence;
		this.detectDeadlock = detectDeadlock;
		this.detectLivelock = detectLivelock;
		this.livelockColourings = livelockColourings;
		this.seed = seed;
		this.stopStates = stopStates;
		this.specialStates = specialStates;
		this.statisticCategories =  statisticCategories;
		
		setUser(true);
	}
	
    public IStatus run(IProgressMonitor monitor) {
    	
    	monitor.beginTask("Calculating Animation Tables", batches + 1);
    	
    	// Set the correct colouring engine.
    	connector.setColouringEngine(ReoPreferences.getColouringEngine());
    	
    	try {
    		table = connector.getAnimationTable(new SubProgressMonitor(monitor,1));  		
    	} catch (Throwable e) {
    		return new Status(IStatus.ERROR, StochasticReoPlugin.PLUGIN_ID, IStatus.ERROR, "Computing animations failed: " + e, e);
    	}
    	
    	// Remove the no flow colorings because these are not relevant for the simulation
		ColouringFilter.removeNoFlowColourings(table);
		System.out.println(table);
		
		// Run the simulation
        simulator = new ReoSimulator(connector, //connector 
        										  table, //colouring table
        										  warmUp, //warm up period
        										  simTime, //max simulation time
        										  batches, //number of batches or runs
        										  batchesEvents, //base batches on events? true = based on events, false = based on time
        										  longTerm, //use long term simulation?
        										  seed, //seed
        										  confidence, //confidence level
        										  detectDeadlock, //detect deadlocks
        										  detectLivelock, //detect livelocks
        										  livelockColourings, //number of times a colouring without any involved ports has been chosen without any action on the ports for a livelock
        										  stopStates,
        										  specialStates,
        										  statisticCategories);
        simulator.runSimulation(new SubProgressMonitor(monitor,batches));
    	
        monitor.done();
	    return Status.OK_STATUS;
    }
    
    public AnimationTable getAnimationTable() {
    	return table;
    }

	/**
	 * @return the simulator
	 */
	public ReoSimulator getSimulator() {
		return simulator;
	}
    
};