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
package org.ect.reo.colouring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Reo;



/**
 * A colouring engine that acts as a wrapper for other
 * colouring engines and which uses a step-wise mode
 * for computing colouring tables.
 * 
 * @generated NOT
 * @author Christian Krause
 *
 */
public class StepwiseColouringEngine extends AbstractColouringEngine {
	
	// Default colouring engine:
	protected ColouringEngine engine = new DefaultColouringEngine();
	
	private Map<Set<ColouringTable>,ColouringTable> joint;
	
	private int index = 0;
	private int states = 0;
	private int estimated = 0;

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.ColouringEngine#computeColouringTable(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public ColouringTable computeColouringTable(IProgressMonitor monitor) {
		
		// Check steps count.
		if (maxSteps==0) return new ColouringTable();
		
		// Make sure the cache is valid.
		if (cache==null) cache = new ColouringCache();
		
		// Initialize underlying engine.
		engine.setColours(colours);
		engine.setCurrent(current);
		engine.setIgnoreNoFlow(ignoreNoFlow);
		engine.setElements(elements);
		engine.setCache(cache);
		
		if (engine instanceof AbstractColouringEngine) {
			((AbstractColouringEngine) engine).setTraversalType(traversalType);
			((AbstractColouringEngine) engine).setBorderElements(border);
		}
		
		// Stepwise mode.
		engine.setMaxSteps(1);
		
		// Turn off verbose.
		if (engine instanceof DefaultColouringEngine) {
			((DefaultColouringEngine) engine).setVerbose(false);
		}
		
		// Initialize joint-tables map.
		Colouring oldCurrent = current;
		joint = new HashMap<Set<ColouringTable>, ColouringTable>();
		index = 0;
		states = 0;
		
		// Estimate the work.
		estimated = 1;
		for (Colourable element : elements) {
			estimated = estimated * element.getColouringTable().getAllTables().size();
		}
		estimated = 1+(estimated / (elements.size()+1)); // Could be zero elements!!!
		//Reo.debug("Estimated " + estimated + " states...");
		long time = System.currentTimeMillis();
		
		// Compute the colouring table.
		monitor.beginTask("Compute colouring tables", estimated);
		ColouringTable table = computeColouringTable(maxSteps, monitor);
		monitor.done();
		
		// Print some stats.
		time = System.currentTimeMillis() - time;
		int seconds = (int) (time / 1000);
		int fract = (int) (time - seconds*1000) / 10;
		Reo.debug("\nComputing colouring tables took " + seconds + "." + fract + " seconds.");
		
		// Clean up.
		joint = null;
		index = 0;
		states = 0;
		estimated = 0;
		current = oldCurrent;
		
		return table;
		
	}
	
	
	private ColouringTable computeColouringTable(int steps, IProgressMonitor monitor) {
		
		// Nothing more to do?
		if (steps==0) return new ColouringTable();
		
		// Stepwise mode.
		engine.setMaxSteps(1);
		
		// Set the current colouring.
		engine.setCurrent(current);
		
		// Check the progress monitor.
		IProgressMonitor subMonitor;
		if ((double) states > ((double) estimated) * 0.8 && states%10!=0) {
			subMonitor = new NullProgressMonitor();
		} else {
			subMonitor = new SubProgressMonitor(monitor, 1);
		}
		
		// Compute the current colouring table.
		ColouringTable table = engine.computeColouringTable(subMonitor);
		table.setName("t" + (++index));
		
		// Statistics.
		if (states>0) {
			Reo.debug(states + " states computed...");
			monitor.subTask(states + " states computed...");
		}
		states++;
		
		// Cache the current table.
		joint.put(getElemTables(current), table);
		
		// Compute the next tables.
		for (Colouring colouring : table.getColourings()) {
			
			current = colouring;
			engine.setCurrent(colouring);
			
			// Get the element next-tables.
			Set<ColouringTable> nextElemTables = getElemTables(colouring);
			
			// Check if the next-table is not computed yet.
			if (!joint.containsKey(nextElemTables)) {
				
				// Make sure there is a next table.
				ColouringTable next = new ColouringTable();
				colouring.setNextColouringTable(next);
				
				// Compute the next table.
				joint.put(nextElemTables, next);
				ColouringTable computed = computeColouringTable(steps-1, monitor);
				joint.put(nextElemTables, computed);
				
			}
			colouring.setNextColouringTable(joint.get(nextElemTables));
		}
		
		return table;
	}
	
	
	private Set<ColouringTable> getElemTables(Colouring colouring) {
		Set<ColouringTable> elemTables = new HashSet<ColouringTable>();
		for (Colourable element : elements) {
			ColouringTable next = findNextColouringTable(cache.get(element));
			elemTables.add(next);
		}
		return elemTables;
	}
	
	public ColouringEngine getUnderlyingEngine() {
		return engine;
	}
	
	public void setUnderlyingEngine(ColouringEngine engine) {
		if (engine!=null) {
			this.engine = engine;
		}
	}

}
