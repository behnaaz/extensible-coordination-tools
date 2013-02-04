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
package org.ect.reo.animation;

import java.util.HashMap;
import java.util.List;

import org.ect.reo.Connectable;
import org.ect.reo.Reo;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.ColouringCache;
import org.ect.reo.colouring.ColouringTable;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class CompiledAnimationTable extends AnimationTable {

	// Colouring table for which we want to compile the animation table for.
	private ColouringTable table;
	
	// Cached colourings tables of the primitives.
	private ColouringCache cache;
	
	// List of border elements.
	private List<Connectable> border;
	
	/**
	 * Set the colouring table to be used.
	 * @param table The colouring table.
	 */
	public void setColouringTable(ColouringTable table) {
		this.table = table;
	}

	/**
	 * Set the colourings cache to be used.
	 * @param table The colouring cache.
	 */
	public void setColouringCache(ColouringCache cache) {
		this.cache = cache;
	}
	
	/**
	 * Set the border elements (should not be considered in the animation).
	 * @param border List of border elements.
	 */
	public void setBorderElements(List<Connectable> border) {
		this.border = border;
	}

	/**
	 * Compile this animation table.
	 */
	public void compile() {
		compile(new HashMap<ColouringTable,AnimationTable>());
	}
	
	/*
	 * Internal compilation method. 
	 */
	private void compile(HashMap<ColouringTable,AnimationTable> cached) {
		
		// Cache the animation table.
		cached.put(table, this);
		
		// Copy the name.
		setName(table.getName());
		
		for (final Colouring colouring : table.getColourings()) {
			
			CompiledAnimation animation = new CompiledAnimation();
			animation.setColouring(colouring);
			animation.setColouringCache(cache);
			animation.setBorderElements(border);
			
			try {
				animation.compile();
			} catch (Exception e) {
				Reo.logError("Error compiling animation.", e);
				continue;
			}
			
			// Clean up.
			animation.setColouring(null);
			
			ColouringTable nextTable = colouring.getNextColouringTable();
			if (nextTable==null) nextTable = table;
			
			if (cached.get(nextTable)==null) {
				CompiledAnimationTable nextAnimations = new CompiledAnimationTable();
				nextAnimations.setColouringTable(nextTable);
				nextAnimations.setColouringCache(cache);
				nextAnimations.setBorderElements(border);
				nextAnimations.compile(cached);
				//cached.put(nextTable, nextAnimations);
				
				// Clean up.
				nextAnimations.setColouringTable(null);
				nextAnimations.setColouringCache(null);
				nextAnimations.setBorderElements(null);
			}
			
			animation.setNextAnimationTable(cached.get(nextTable));
			add(animation);
			
		}
				
	}

}

