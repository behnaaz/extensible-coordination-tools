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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ColouringCausalityLoops {
	
	/**
	 * Remove all colourings that have causality loops (flow and no-flow).
	 * This is done on all reachable colouring tables.
	 * @param table Colouring table.
	 */
	public static void removeLoops(ColouringTable table, IProgressMonitor monitor) {
		for (ColouringTable current : table.getAllTables()) {
			for (int i=0; i<current.getColourings().size(); i++) {
				if (monitor.isCanceled()) {
					return;
				}
				if (hasLoop(current.getColourings().get(i))) {
					//Colouring colouring = 
					current.getColourings().remove(i--);
					//Reo.debug("Removed causality loop: " + colouring);
				}
			}
		}
	}
	
	/**
	 * Check if a colouring contains a causality loop (flow or no-no-flow).
	 * @param colouring Colouring.
	 * @return <code>true</code> is a causality loop was found.
	 */
	public static boolean hasLoop(Colouring colouring) {
		
		// Empty colouring?
		if (colouring.getColours().isEmpty()) return false;
		
		// Checked ends.
		Set<PrimitiveEnd> checked = new HashSet<PrimitiveEnd>();
				
		while (true) {
			
			// Find the next end to be checked:
			PrimitiveEnd next = null;
			for (PrimitiveEnd end : colouring.getColours().keySet()) {
				if (!checked.contains(end)) {
					next = end; break;
				}
			}
			
			// Are we done already?
			if (next==null) return false;
			
			// Compute the transitive closure and check for loops:
			Set<PrimitiveEnd> visited = new HashSet<PrimitiveEnd>();
			
			Set<PrimitiveEnd> checking = new HashSet<PrimitiveEnd>();
			checking.add(next);
			
			while (!checking.isEmpty()) {
				
				List<PrimitiveEnd> iteration = new ArrayList<PrimitiveEnd>(checking);
				visited.addAll(checking);
				checking.clear();
				
				for (PrimitiveEnd end : iteration) {
					
					// No-flow at nodes:
					if (colouring.isNoFlowGiveReason(end) && end.getNode()!=null) {
						
						// All other ends must be no-flow require reason:
						boolean anotherReason = false;
						for (PrimitiveEnd current : end.getNode().getAllEnds()) {
							anotherReason = anotherReason || (current!=end && colouring.isNoFlowGiveReason(current));
						}
						
						if (!anotherReason) {						
							for (PrimitiveEnd current : end.getNode().getAllEnds()) {
								if (colouring.isNoFlowRequireReason(current)) {
									if (visited.contains(current)) return true;
									else checking.add(current);
								}
							}
						}
						
					}
					
					// No-flow at primitives:
					if (colouring.isNoFlowRequireReason(end) && end.getPrimitive()!=null) {
						for (PrimitiveEnd current : end.getPrimitive().getAllEnds()) {
							if (colouring.isNoFlowGiveReason(current)) {
								if (visited.contains(current)) return true;
								else checking.add(current);
							}
						}
					}
					
					// Flow:
					if (colouring.isFlow(end)) {
						
						// Source ends:
						if (end instanceof SourceEnd && end.getPrimitive()!=null) {
							for (SinkEnd current : end.getPrimitive().getSinkEnds()) {
								if (colouring.isFlow(current)) {
									if (visited.contains(current)) return true;
									else checking.add(current);
								}
							}
						}
						
						// Sink ends:
						if (end instanceof SinkEnd && end.getNode()!=null) {
							for (SourceEnd current : end.getNode().getSourceEnds()) {
								if (colouring.isFlow(current)) {
									if (visited.contains(current)) return true;
									else checking.add(current);
								}
							}
						}
						
					}
					
				}
			}
			
			// Mark the the ends as checked.
			checked.addAll(visited);
			
		}
		
	}
	
}
