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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.PrimitiveEnd;


/**
 * This is an implementation of the hiding-operation
 * for the colouring tables.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public class ColouringHiding {
	
	/**
	 * Hide all occurrences of the argument ends in the argument table.
	 * This involves a hiding of no-flow steps.
	 * @param table Colouring table.
	 * @param ends Ends to be hidden.
	 */
	public static void hideEnds(ColouringTable table, List<PrimitiveEnd> ends) {
		// Remove ends from all colourings.
		for (ColouringTable current : table.getAllTables()) {
			for (Colouring colouring : current.getColourings()) {
				for (PrimitiveEnd end : ends) {
					colouring.getColours().removeKey(end);
				}
			}
		}
		ColouringFilter.removeDuplicates(table);
	}
	
	/**
	 * Hide no-flow steps that involve a state change.
	 * If a colouring has no-flow and its next-table
	 * is different, the colouring is deleted and the
	 * next-table is copied into the current table.
	 * If there is only one of these entries in a table,
	 * the table is removed.
	 */
	public static void hideNoFlow(ColouringTable table) {
		
		for (ColouringTable current : table.getAllTables()) {
			for (int i=0; i<current.getColourings().size(); i++) {
				
				// We always assume that the next table is not null.
				Colouring colouring = current.getColourings().get(i);
				ColouringTable next = colouring.getNextColouringTable();
				if (next==null) continue;
				
				// Check if it is no-flow and the state is changing. 
				if (!colouring.hasFlow() && next!=current) {
					
					// Check if this table can be removed.
					if (current!=table && canRemove(current, new HashSet<ColouringTable>())) {
						
						// Remove the table completely.
						ColouringRefactoring.replaceNextRefs(table, current, next);
						
					} else {
						// Copy all colourings from the next table into this table.
						for (Colouring n : next.getColourings()) {
							if (n.hasFlow()) current.getColourings().add(n.getCopy());							
						}
						
						// Remove the colouring from the table.
						current.getColourings().remove(i--);
					}
				}
			}
		}
	}
	
	
	/**
	 * Check whether a whole table can be removed.
	 * @param table Table to check.
	 * @return True if it can be removed.
	 */
	private static boolean canRemove(ColouringTable table, Set<ColouringTable> visited) {
		
		if (visited.contains(table)) return true;
		else visited.add(table);
		
		// Empty table can always be removed.
		if (table.getColourings().isEmpty()) return true;
		ColouringTable next = table.getColourings().get(0).getNextColouringTable();
		
		for (Colouring colouring : table.getColourings()) {
			// Must be no-flow.
			if (colouring.hasFlow()) return false;
			// Next table must always be the same, or null, or removable.
			if (colouring.getNextColouringTable()!=null &&
				colouring.getNextColouringTable()!=next &&
				!canRemove(table, visited)) return false;
		}
		// Otherwise it is ok to remove the table.
		return true;
	}
	
	
	/**
	 * Hide internal no-flow. 
	 * @param table Colouring table.
	 * @param closedEnds Internal ends.
	 * @return Number of removed colourings.
	 */
	public static int hideInternalNoFlow(ColouringTable table, 
			Collection<PrimitiveEnd> closedEnds, IProgressMonitor monitor) {
		
		// There must be at least one closed end.
		if (closedEnds.isEmpty()) return 0;
		
		// Remember how many colourings were removed.
		int removed = 0;
		
		// Do this for all tables.
		for (ColouringTable current : table.getAllTables()) {
			
			// Check all colourings.
			for (int i=0; i<current.getColourings().size(); i++) {
				Colouring colouring = current.getColourings().get(i);
				if (monitor.isCanceled()) return removed;
				
				// Get a copy of the colouring that does not include the no-flow ends.
				Colouring reduced = colouring.getCopy();
				for (PrimitiveEnd end : closedEnds) {
					if (!colouring.isFlow(end)) reduced.getColours().removeKey(end);
				}
				
				for (int j=i+1; j<current.getColourings().size(); j++) {
					Colouring c = current.getColourings().get(j);
					
					// Check whether the reduced colouring is part of the current colouring.
					if (!c.contains(reduced)) continue;
					
					// All removed closed ends must have no-flow in that colouring!
					boolean compatible = true;
					for (PrimitiveEnd end : closedEnds) {
						if (!reduced.getColours().containsKey(end) && c.isFlow(end)) {
							compatible = false;
							break;
						}
					}
					
					// Remove the colouring:
					if (compatible) {
						//Reo.debug("removing " + c);
						current.getColourings().remove(j--);
						removed++;
					}
					
				}
			}
		}
		return removed;
	}
	
}
