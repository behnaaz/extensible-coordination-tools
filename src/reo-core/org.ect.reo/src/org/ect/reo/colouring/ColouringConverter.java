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
import java.util.Set;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.AnimationTable;



/**
 * @generated NOT
 * @author Christian Krause
 */
public class ColouringConverter {
	
	/**
	 * Convert a colouring table to a table with only flow
	 * and no-flow colours (2 colouring scheme). Duplicate
	 * colouring are removed. The original table is changed.
	 */
	public static void to2Colours(ColouringTable table) {
		
		// Already 2 colours?
		if (table.getColours()==2) return;
		
		// Convert all colourings.
		for (ColouringTable t : table.getAllTables()) {
			for (Colouring colouring : t.getColourings()) {
				for (PrimitiveEnd key : colouring.getColours().keySet()) {
					if (colouring.getColour(key)!=FlowColour.FLOW_LITERAL) {
						colouring.setColour(key, FlowColour.NO_FLOW_LITERAL);
					}
				}
			}			
			// Update colours attribute.
			t.setColours(2);
		}
		
		// Clean up.
		ColouringFilter.removeDuplicates(table);
		
	}

	
	public static int applyFlipRule(ColouringTable table) {
		if (table.getColourings().isEmpty()) return 0;
		Set<PrimitiveEnd> ends = table.getColourings().get(0).getColours().keySet();
		return applyFlipRule(table, ends);
	}

	
	/**
	 * Remove unnecessary colourings by applying the flip rule.
	 * @generated NOT
	 * @result Number of removed colourings.
	 */
	public static int applyFlipRule(ColouringTable table, Collection<PrimitiveEnd> interfaceEnds) {
		
		int removed = 0;
		for (ColouringTable current : table.getAllTables()) {
			for (int i=0; i<current.getColourings().size(); i++) {				
				for (int j=0; j<current.getColourings().size(); j++) {
					
					if (i==j) continue;
					Colouring c1 = current.getColourings().get(i);
					Colouring c2 = current.getColourings().get(j);
					
					Set<PrimitiveEnd> allEnds = c1.getColours().keySet();
					
					// Next-tables must be the same.
					if (c1.getNextColouringTable()!=c2.getNextColouringTable()) continue;
					
					boolean canRemove = true;
					for (PrimitiveEnd end : allEnds) {
						int f1 = c1.getColours().get(end).getValue();
						int f2 = c2.getColours().get(end).getValue();
						if (interfaceEnds.contains(end)) {
							canRemove = canRemove && ((f1==f2) || (f1==FlowColour.NO_FLOW_GIVE_REASON && 
													  f2==FlowColour.NO_FLOW_REQUIRE_REASON));
						} else {
							canRemove = canRemove && (f1==f2);
						}
						if (!canRemove) break;
					}
					
					// If that is so, we can remove the colouring.
					if (canRemove) {
						if (j<i) i--;
						// System.out.println("Remove " + c2);
						current.getColourings().remove(j--);
						removed++;
					}
					
				}
			}
		}
		return removed;
	}
	
	
	/**
	 * Add colourings implied by the flip rule.
	 * @generated NOT
	 */
	public static void reverseFlipRule(ColouringTable table) {
		
		// Must be non-empty.
		if (table.getColourings().isEmpty()) return;
		Set<PrimitiveEnd> ends = table.getColourings().get(0).getColours().keySet();
		
		// Do this for all tables.
		for (ColouringTable current : table.getAllTables()) {
		
			// Add the implicit colourings.
			for (PrimitiveEnd end : ends) {
				int colouringCount = current.getColourings().size();
				for (int k=0; k<colouringCount; k++) {
					Colouring colouring = (Colouring) current.getColourings().get(k);
					if (colouring.isNoFlowGiveReason(end)) {
						// Create a copy and change the color of the current end.
						Colouring changedCopy = colouring.getCopy();
						changedCopy.setColour(end, FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
						current.getColourings().add(changedCopy);
					}
				}
			}
		}
		
	}


	/**
	 * Flip no-flow colours in all colourings, i.e. 
	 * replace ->- by -<- and vice versa. 
	 * @param start
	 */
	public static void flipNoFlowColours(AnimationTable start) {
	
		// Update all colourings.
		for (ColouringTable table : start.getAllTables()) {	
			for (Colouring colouring : table.getColourings()) {
				for (PrimitiveEnd end : colouring.getColours().keySet()) {
					if (colouring.getColour(end).equals(FlowColour.NO_FLOW_GIVE_REASON_LITERAL)) {
						colouring.setColour(end, FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);
					}
					// 'Else' is important here!!!
					else if (colouring.getColour(end).equals(FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL)) {
						colouring.setColour(end, FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
					}
				}
			}
		}
	}
	
	
}
