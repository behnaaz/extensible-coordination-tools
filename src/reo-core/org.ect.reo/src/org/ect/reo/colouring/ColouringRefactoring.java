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

import org.ect.reo.PrimitiveEnd;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class ColouringRefactoring {
	
	/**
	 * Replace all occurrences of 'oldEnd' by 'newEnd' in all
	 * colouring tables reachable from 'start'.
	 * @param start Colouring table.
	 * @param oldEnd Old end.
	 * @param newEnd New end.
	 */
	public static void replaceEnd(ColouringTable start, PrimitiveEnd oldEnd, PrimitiveEnd newEnd) {
		
		for (ColouringTable table : start.getAllTables()) {	
			for (Colouring colouring : table.getColourings()) {
				if (colouring.getColours().containsKey(oldEnd)) {
					
					// Get the colour of the old end.
					FlowColour colour = colouring.getColour(oldEnd);
					
					// Add the new end.
					colouring.setColour(newEnd,colour);
					
					// Remove the old end.
					colouring.getColours().removeKey(oldEnd);
				}
			}
		}
		
	}
	
	/**
	 * Update the next-references in colouring tables.
	 * Replaces 'oldtable' by 'newtable' in all
	 * tables reachable from 'start'.
	 */
	public static int replaceNextRefs(ColouringTable start, 
										ColouringTable oldtable, 
										ColouringTable newtable) {
		int replaced = 0;
		// Update all colourings.
		for (ColouringTable table : start.getAllTables()) {	
			for (Colouring colouring : table.getColourings()) {
				if (colouring.getNextColouringTable()==oldtable) {
					colouring.setNextColouringTable(newtable);
					replaced++;
				}
			}
		}
		return replaced;
	}

	/**
	 * Reset table names.
	 */
	public static void resetTableNames(ColouringTable table) {
		// Reset names of the resulting tables.
		int counter = 1;
		for (ColouringTable current : table.getAllTables()) {
			current.setName("t" + counter++);
		}
	}
	
}
