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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.util.AbstractTraversalWorker;


/**
 * Traversal worker for computing colouring tables.
 * @author Christian Krause
 * @generated NOT
 */
public class ColouringTraversalWorker extends AbstractTraversalWorker {
	
	public interface NextTableFinder {
		ColouringTable findNextColouringTable(ColouringTable table);
	}
	
	// Start of the traversal.
	private Connectable start;
	
	// The colouring table.
	private ColouringTable table;
	
	// Number of colours to be used.
	private int colours = 3;
	
	// Maximal number of steps.
	private int maxSteps = -1;
	
	// Next table finder.
	private NextTableFinder tableFinder;
	
	// CPU (thread) executing this traversal.
	private int cpu;
	
	// Priority of this worker
	private int priority = Thread.NORM_PRIORITY;

	// Cache for the colouring tables of the primitives.
	private ColouringCache cache;
	
	// Set of closed ends.
	private Set<PrimitiveEnd> closedEnds;
	
	// Verbose flag.
	protected boolean verbose = true;
	

	/**
	 * Default constructor.
	 * @param start Start element.
	 */
	public ColouringTraversalWorker(Connectable start, NextTableFinder tableFinder, ColouringCache cache, int cpu) {
		
		this.start = start;
		this.tableFinder = tableFinder;
		this.cpu = cpu;
		this.cache = cache;
		
		table = null;
		closedEnds = new HashSet<PrimitiveEnd>();
	}
	
	/**
	 * Convenience constructor without a cache parameter.
	 */
	public ColouringTraversalWorker(Connectable start, NextTableFinder tableFinder, int cpu) {
		this(start, tableFinder, new ColouringCache(), cpu);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.AbstractTraversalWorker#visitElement(org.ect.reo.Connectable, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public boolean visitElement(Connectable element, IProgressMonitor monitor) {
		if (element instanceof Colourable) {
			joinTable((Colourable) element, element.toString(), monitor);
			return true;
		} else {
			Reo.logError(element + " is not colourable!");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.ReoTraversalWorker#compare(org.ect.reo.Connectable, org.ect.reo.Connectable, org.ect.reo.Connectable)
	 */
	public int compare(Connectable n1, Connectable n2, Connectable current) {
		
		EList<PrimitiveEnd> a1 = n1.getAllEnds();
		EList<PrimitiveEnd> a2 = n2.getAllEnds();
		EList<PrimitiveEnd> c = current.getAllEnds();
		
		int dis1 = getDistance(a1, c, (current instanceof Node));
		int dis2 = getDistance(a2, c, (current instanceof Node));
		
		int new1 = newEnds(a1);
		int new2 = newEnds(a2);
		
		return (dis1-dis2) + (new1-new2);
	}
	
	/*
	 * Get the number of 'new' ends.
	 */
	private int newEnds(EList<PrimitiveEnd> ends) {
		int newEnds = 0;
		for (PrimitiveEnd end : ends) {
			if (!getVisitedEnds().contains(end)) newEnds++;
		}
		return newEnds;
	}
	
	/**
	 * Join the colouring table with the argument's.
	 * @param element Something that produces a colouring table.
	 * @param name Name of the element, for debugging only.
	 * @param monitor Progress monitor.
	 */
	public void joinTable(Colourable element, String name, IProgressMonitor monitor) {
		
		monitor.beginTask("Join tables", 5);
		long msecs = System.currentTimeMillis();
		
		// Get the current colouring table.
		ColouringTable t = cache.get(element);
		
		// Convert the colouring table if necessary.
		if (t.getColours()!=colours) {
			if (colours==2) ColouringConverter.to2Colours(t);
		}
		
		// Remove duplicates colourings.
		ColouringFilter.removeDuplicates(t);
		
		// Get the next table.
		t = tableFinder.findNextColouringTable(t);
		
		// Join tables and hide equivalent no-flow.
		table = (table==null) ? t.getCopy() : t.join(table, maxSteps, new SubProgressMonitor(monitor,3));
		if (monitor.isCanceled()) return;
		
		// Do some optimizations.
		if (colours==3 && table!=null && !table.getColourings().isEmpty()) {
			
			// Mark the current element as visited.
			getVisitedElements().add((Connectable) element);
			
			// Update the list of closed ends.
			updateClosedEnds((Connectable) element);
			
			// Hide internal no-flow at mixed node.
			ColouringHiding.hideInternalNoFlow(table, closedEnds, new SubProgressMonitor(monitor,1));
			if (monitor.isCanceled()) return;
			
			// Remove causality-loops.
			ColouringCausalityLoops.removeLoops(table, new SubProgressMonitor(monitor,1));
			
		}
		
		// Update stats.
		if (verbose) {
			msecs = System.currentTimeMillis() - msecs;				
			Reo.debug("  " + 	table.getColourings().size() + "\t| " + 
								table.totalColourings() + " \t| " + 
								getVisitedEnds().size() + " \t| " + 
								(msecs/1000) + "s \t| " + cpu + " \t| " + priority + " \t| " +
								name + " with " + t.getColourings().size() + "/" +
								t.totalColourings() + " entries");
		}
	}
	
	
	private void updateClosedEnds(Connectable element) {
		for (PrimitiveEnd end : element.getAllEnds()) {
			if (closedEnds.contains(end)) continue;
			if (end.getPrimitive()==null || end.getNode()==null || end.getPrimitive().getConnector()==null) continue;
			if (end.getPrimitive().getConnector().getForeignPrimitives().contains(end.getPrimitive())) continue;
			if (!getVisitedElements().contains(end.getPrimitive())) continue;
			if (!getVisitedElements().contains(end.getNode())) continue;
			closedEnds.add(end);
		}
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.ReoTraversalWorker#getStart()
	 */
	public Connectable getStart() {
		return start;
	}
	
	public void setMaxSteps(int maxSteps) {
		this.maxSteps = maxSteps;
	}
	
	public void setColours(int colours) {
		this.colours = colours;
	}
	
	public ColouringTable getColouringTable() {
		return table;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public int getShift() {
		// We assume the CPU indices start at 1.
		return cpu-1;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}
	
	public void setColouringTable(ColouringTable table) {
		if (table!=null) this.table = table;
	}
	
	@Override
	public String toString() {
		return "ColouringTraveralWorker #" + cpu;
	}
	
}
