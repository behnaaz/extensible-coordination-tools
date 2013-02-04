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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.ColouringTable;
import org.ect.reo.colouring.FlowColour;



/**
 * @see org.ect.reo.animation.AnimationPackage#getAnimationTable()
 * @model kind="class"
 * @generated
 */
public class AnimationTable extends ColouringTable {
	
	/**
	 * @generated NOT
	 */
	public AnimationTable() {
		super();
	}


	/**
	 * @generated NOT
	 */
	public AnimationTable(String name) {
		super(name);
	}


	/**
	 * Get the size of the table.
	 * @generated NOT
	 */
	public int size() {
		return getColourings().size();
	}

	/**
	 * Get the animation at a given index. 
	 * @generated NOT
	 */
	public Animation get(int index) {
		return (Animation) getColourings().get(index);
	}

	
	/**
	 * Add an animation to the table.
	 * @param animation Animation to be added.
	 * @generated NOT
	 */
	public void add(Animation animation) {
		getColourings().add(animation);
	}

	/**
	 * Add an animation to the table.
	 * @param animation Animation to be added.
	 * @generated NOT
	 */
	public void add(int index, Animation animation) {
		getColourings().add(index, animation);
	}

	/**
	 * Add a number animations to the table.
	 * @generated NOT
	 */
	public void addAll(Collection<Animation> animations) {
		getColourings().addAll(animations);
	}

	/**
	 * Remove an animation from the table.
	 * @param animation Animation to be removed.
	 * @generated NOT
	 */
	public void remove(Animation animation) {
		getColourings().remove(animation);
	}
	
	
	/**
	 * Find an animation that has the same colouring as the argument
	 * colouring function. Animations are usually identified by there
	 * colouring, so that there should be a unique animation fulfilling
	 * these constraints (if there is such an animation in this list).
	 *  
	 * @param colouring Colouring that the animation should be compatible with.
	 * @return The compatible animation if such exists.
	 * @generated NOT
	 */
	public Animation findAnimation(Colouring colouring) {
		
		Animation animation = findOriginalAnimation(colouring);
		
		if (animation==null) {
			//Reo.debug("Animation not found for: " + colouring);
			animation = findCompatibleAnimation(colouring, true);
		}
		
		if (animation==null) {
			Reo.debug("No exact match found for: " + colouring);
			animation = findCompatibleAnimation(colouring, false);
		}
		
		if (animation==null) {
			throw new RuntimeException("Fatal error: cannot find animation for " + colouring + " in table " + this);
		}
		
		return animation; 
	}
	
	
	/*
	 * Private helper method for finding the original animations.
	 */
	private Animation findOriginalAnimation(Colouring colouring) {	
		
		// Parts must be specified.
		if (colouring.getParts().isEmpty()) return null;
		
		// Check all tables.
		for (AnimationTable table : getAllTables()) {
			// Check the parts of the colouring.
			for (int i=0; i<table.size(); i++) {
				if (colouring.getParts().contains(table.get(i))) return table.get(i);
			}
		}
		return null;
	}
	
	
	
	/*
	 * Private helper method for finding compatible animations.
	 */
	private Animation findCompatibleAnimation(Colouring colouring, boolean exactMatch) {
				
		// Check all tables.
		for (AnimationTable table : getAllTables()) {
		
			// Otherwise compare the colourings manually.
			for (int i=0; i<table.size(); i++) {
			
				Animation animation = table.get(i);
				boolean compatible = true;

				for (PrimitiveEnd end : animation.getColours().keySet()) {
				
					FlowColour c1 = colouring.getColour(end);
					FlowColour c2 = animation.getColour(end);
					
					// If the colouring does not define a color for the end it's fine.
					if (c1==null) continue;
					
					if (!exactMatch && c1.getValue()!=FlowColour.FLOW && c2.getValue()!=FlowColour.FLOW) continue;
				
					// Otherwise, the colours must be the same.
					if (c1.getValue()!=c2.getValue()) {
						compatible = false;
						break;
					}
				}
				
				if (compatible) return animation;
			}
		}
		return null;
		
	}
	
	
	/**
	 * Create a copy of this animation table.
	 * @generated NOT
	 */
	@Override
	public AnimationTable getCopy() {
		AnimationTable table = new AnimationTable();
		table.setColours(colours);
		for (int i=0; i<size(); i++) {
			table.add(get(i).getCopy());
		}
		return table;
	}

	
	/**
	 * Returns a list of all referenced animation tables.
	 * @generated NOT
	 */
	@Override
	public Set<AnimationTable> getAllTables() {
		Set<AnimationTable> tables = new LinkedHashSet<AnimationTable>();
		for (ColouringTable table : super.getAllTables()) {
			if (table instanceof AnimationTable) {
				tables.add((AnimationTable) table);
			}
		}
		return tables;
	}

	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		AnimationPrinter printer = new AnimationPrinter();
		return printer.printAllTables(this);
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnimationPackage.Literals.ANIMATION_TABLE;
	}

}
