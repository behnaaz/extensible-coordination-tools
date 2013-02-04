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
import java.util.List;

import org.ect.reo.Connectable;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.util.ReoTraversal.TraversalType;


/**
 * Abstract super-class for coloring engines.
 * @generated NOT
 * @author Christian Krause
 *
 */
public abstract class AbstractColouringEngine implements ColouringEngine {
		
	// Maximal number of steps.
	protected int maxSteps = -1;
	
	// Number of colours.
	protected int colours = 3;
	
	// Current colouring.
	protected Colouring current;
	
	// Ignore no-flow colourings?
	protected boolean ignoreNoFlow;
	
	// Traversal type.
	protected TraversalType traversalType;
	
	// Colouring cache.
	protected ColouringCache cache;
	
	// List of elements to be used.
	protected List<Colourable> elements = new ArrayList<Colourable>();
	
	// List of border elements.
	protected List<Connectable> border = new ArrayList<Connectable>();
	

	/**
	 * Find the next colouring table of a primitive, a node or the
	 * connector itself. This is according to {@link #current}.
	 */
	protected ColouringTable findNextColouringTable(ColouringTable table) {
		
		// Initial state or empty table?
		if (current==null || table.getColourings().isEmpty()) {
			return table;
		}

		// Check for equality
		for (ColouringTable t : table.getAllTables()) {
			for (Colouring colouring : t.getColourings()) {
				for (Colouring part : current.getParts()) {
					if (colouring.equals(part)) {
						return part.getNextColouringTable(); 
					}
				}
			}
		}

		// Check for match up to equality of the colouring entries.
		for (ColouringTable t : table.getAllTables()) {
			for (Colouring colouring : t.getColourings()) {
				boolean contains = true;
				for (PrimitiveEnd key : colouring.getColours().keySet()) {
					if (current.getColour(key)==colouring.getColour(key)) {
						contains = false;
						break;
					}
				}
				if (contains) return colouring.getNextColouringTable();
			}
		}
		
		// Next coloring table not found.
		return null;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.ColouringEngine#setElements(java.util.List)
	 */
	public void setElements(List<Colourable> elements) {
		this.elements = new ArrayList<Colourable>(elements);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.IColouringEngine#setIgnoreNoFlow(boolean)
	 */
	public void setIgnoreNoFlow(boolean ignoreNoFlow) {
		this.ignoreNoFlow = ignoreNoFlow;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.IColouringEngine#setMaxSteps(int)
	 */
	public void setMaxSteps(int maxSteps) {
		this.maxSteps = maxSteps; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.IColouringEngine#setColours(int)
	 */
	public void setColours(int colours) {
		this.colours = colours;		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.IColouringEngine#setCurrentColouring(org.ect.reo.colouring.Colouring)
	 */
	public void setCurrent(Colouring current) {
		this.current = current;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.colouring.ColouringEngine#setCache(org.ect.reo.colouring.ColouringCache)
	 */
	public void setCache(ColouringCache cache) {
		this.cache = cache;
	}
	
	public void setBorderElements(List<Connectable> border) {
		this.border = new ArrayList<Connectable>(border);
	}
	
	public void setTraversalType(TraversalType type) {
		this.traversalType = type;
	}
	
}