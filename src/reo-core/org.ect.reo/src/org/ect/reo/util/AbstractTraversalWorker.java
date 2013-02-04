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
package org.ect.reo.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.PrimitiveEnd;


/**
 * Abstract super class for traversal workers.
 * @author Christian Krause
 * @generated NOT
 */
public abstract class AbstractTraversalWorker implements ReoTraversalWorker {
	
	// Visited elements:
	private Set<Connectable> visited = new HashSet<Connectable>();	
	
	// Visited ends:
	private Set<PrimitiveEnd> visitedEnds = new HashSet<PrimitiveEnd>();
	
	// Last visited element:
	private Connectable last;
	
	/**
	 * Visitor method. To be implemented by subclasses.
	 * @param element Current element.
	 * @param monitor Monitor.
	 * @return <code>true</code> if the traversal should continue.
	 */
	protected abstract boolean visitElement(Connectable element, IProgressMonitor monitor);
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.ReoTraversalWorker#visit(org.ect.reo.Connectable, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public final boolean visit(Connectable element, IProgressMonitor monitor) {
		boolean result = visitElement(element, monitor);
		last = element;
		if (result) {
			visited.add(element);
			visitedEnds.addAll(element.getAllEnds());
		}
		return result;
	}
	
	protected Connectable getLastElement() {
		return last;
	}
	
	protected Set<Connectable> getVisitedElements() {
		return visited;
	}
	
	protected Set<PrimitiveEnd> getVisitedEnds() {
		return visitedEnds;
	}
	
	protected Set<PrimitiveEnd> getOpenPrimitiveEnds() {
		Set<PrimitiveEnd> open = new HashSet<PrimitiveEnd>();
		for (PrimitiveEnd end : visitedEnds) {
			if (end.getNode()==null || end.getPrimitive()==null) continue;
			if (visited.contains(end.getPrimitive()) && !visited.contains(end.getNode())) {
				open.add(end);
			}
		}
		return open;
	}
	
	
	protected Set<PrimitiveEnd> getOpenNodeEnds() {
		Set<PrimitiveEnd> open = new HashSet<PrimitiveEnd>();
		for (PrimitiveEnd end : visitedEnds) {
			if (end.getNode()==null || end.getPrimitive()==null) continue;
			if (!visited.contains(end.getPrimitive()) && visited.contains(end.getNode())) {
				open.add(end);
			}
		}
		return open;
	}
	
	
	protected int getDistance(Collection<PrimitiveEnd> ends, 
							Collection<PrimitiveEnd> current, 
							boolean currentIsNode) {
		
		// Make copies and remove current ends.
		ends = new HashSet<PrimitiveEnd>(ends);
		ends.removeAll(current);
		
		int distance = 0;
		boolean done = false;
		int size = ends.size();
		
		while (!done) {
			
			distance++;
						
			for (PrimitiveEnd end : new HashSet<PrimitiveEnd>(ends)) {
				if (currentIsNode) {
					if (end.getNode()!=null) {
						ends.addAll(end.getNode().getAllEnds());
					}
				} else {
					if (end.getPrimitive()!=null) {
						ends.addAll(end.getPrimitive().getAllEnds());
					}
				}
			}
			ends.removeAll(current);
			
			// No change?
			if (size==ends.size()) {
				return Integer.MAX_VALUE;
			}
			size = ends.size();
			
			// Check if we are done.
			for (PrimitiveEnd end : ends) {
				if ((end.getPrimitive()!=null && visited.contains(end.getPrimitive())) || 
					(end.getNode()!=null && visited.contains(end.getNode()))) {
					done = true;
				}
			}
			currentIsNode = !currentIsNode;
		}
		
		return distance;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.ReoTraversalWorker#compare(org.ect.reo.Connectable, org.ect.reo.Connectable, org.ect.reo.Connectable)
	 */
	public int compare(Connectable e1, Connectable e2, Connectable current) {
		return 0;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.ReoTraversalWorker#getPriority()
	 */
	public int getPriority() {
		return Thread.NORM_PRIORITY;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.util.ReoTraversalWorker#getShift()
	 */
	public int getShift() {
		return 0;
	}

}
