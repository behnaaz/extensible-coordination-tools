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

/**
 * @generated NOT
 * @author Christian Krause
 */
public class ColouringFilter {
	
	/**
	 * Remove all empty colourings in a table and all its next tables.
	 */
	public static int removeEmptyColourings(ColouringTable table) {
		int removed = 0;
		for (ColouringTable current : table.getAllTables()) {
			for (int i=0; i<current.getColourings().size(); i++) {
				Colouring colouring = current.getColourings().get(i);
				if (colouring.getColours().isEmpty()) {
					current.getColourings().remove(i--);
					removed++;
				}
			}
		}
		return removed;
	}
	
	
	/**
	 * Remove all duplicate colourings in a table.
	 */
	public static int removeDuplicates(ColouringTable table) {
		int removed = 0;
		for (ColouringTable current : table.getAllTables()) {
			for (int i=0; i<current.getColourings().size(); i++) 
				for (int j=i+1; j<current.getColourings().size(); j++) {
					if (current.getColourings().get(i).equals( current.getColourings().get(j)) ) {
						current.getColourings().remove(j--);
						removed++;
					}
				}
		}
		return removed;
	}
	
	
	/**
	 * Remove all no-flow colourings in a table and all its next tables.
	 */
	public static int removeNoFlowColourings(ColouringTable table) {
		int removed = 0;		
		for (ColouringTable current : table.getAllTables()) {
			for (int i=0; i<current.getColourings().size(); i++) {
				Colouring colouring = current.getColourings().get(i);
				if (!colouring.hasFlow()) {
					current.getColourings().remove(i--);
				}
			}
		}
		return removed;
	}
	
	
	/**
	 * Remove all duplicate flow colourings in a table and all its next tables.
	 */
	public static int removeDuplicateFlows(ColouringTable table) {
		int removed = 0;
		for (ColouringTable current : table.getAllTables()) {
			for (int i=0; i<current.getColourings().size(); i++) {
				Colouring c1 = current.getColourings().get(i);
			
				for (int j=i+1; j<current.getColourings().size(); j++) {
					Colouring c2 = current.getColourings().get(j);
					
					// We ignore the next tables.
					/*
					boolean nextEqual = (c1.getNextColouringTable()==null && c2.getNextColouringTable()==null) || 
										(c1.getNextColouringTable()!=null && c1.getNextColouringTable().equals(c2.getNextColouringTable()));
					*/
					if (c1.hasEqualFlow(c2) /*&& nextEqual*/) {
						current.getColourings().remove(j--);
						removed++;
					}
				}
			}
		}
		return removed;
	}

}
