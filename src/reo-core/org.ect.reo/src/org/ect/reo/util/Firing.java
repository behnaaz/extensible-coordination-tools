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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Nameable;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.AnimationCompiler;
import org.ect.reo.animation.AnimationExpander;
import org.ect.reo.animation.AnimationList;
import org.ect.reo.animation.AnimationMatrix;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.colouring.Colourable;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.ColouringCache;
import org.ect.reo.colouring.ColouringTable;
import org.ect.reo.colouring.StepwiseColouringEngine;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class Firing implements Iterator<Firing> {
	
	// Actions for this firing.
	private Map<PrimitiveEnd,Boolean> actions = new HashMap<PrimitiveEnd,Boolean>();	
	
	// Set of elements (shared).
	protected Set<Connectable> elements = new LinkedHashSet<Connectable>();
	
	// Colouring cache (shared).
	protected ColouringCache cache = new ColouringCache();
	
	// Next firing.
	protected Firing next;
	
	/**
	 * Create an empty firing.
	 */
	public Firing() {		
	}
	
	/**
	 * Create an initialized firing.
	 * @param colouring Colouring or animation.
	 */
	public Firing(Colouring colouring) {
		
		// Find all used elements.
		Set<Connectable> elements = new HashSet<Connectable>();
		for (PrimitiveEnd end : colouring.getColours().keySet()) {
			if (end.getPrimitive()!=null) elements.add(end.getPrimitive());
			if (end.getNode()!=null) elements.add(end.getNode());
		}
		
		// Add entries for all elements.
		for (Connectable element : elements) {
			boolean[] sources = new boolean[element.getSourceEnds().size()];
			boolean[] sinks = new boolean[element.getSinkEnds().size()];
			for (int i=0; i<element.getSourceEnds().size(); i++) {
				sources[i] = colouring.isFlow(element.getSourceEnds().get(i));
			}
			for (int i=0; i<element.getSinkEnds().size(); i++) {
				sinks[i] = colouring.isFlow(element.getSinkEnds().get(i));
			}
			set(element, sources, sinks);
		}
		
	}
	
	/**
	 * Set the actions of the argument element.
	 * @param element Element.
	 * @param sources Actions at the source ends.
	 * @param sinks Actions at the sink ends.
	 */
	public void set(Connectable element, boolean[] sources, boolean[] sinks) {
		
		// Check if the number of actions is correct.
		if (sources==null || sources.length!=element.getSourceEnds().size() ||
			sinks==null || sinks.length!=element.getSinkEnds().size()) {
			throw new RuntimeException("Number of actions does not match number of ends for " + element);
		}
		
		// Save the data.
		for (int i=0; i<sources.length; i++) {
			internalSet(element.getSourceEnds().get(i), sources[i]);
		}
		for (int i=0; i<sinks.length; i++) {
			internalSet(element.getSinkEnds().get(i), sinks[i]);
		}
		elements.add(element);
		
	}

	/**
	 * Get the action of the argument end.
	 * @param end PrimitiveEnd
	 */
	public boolean getAction(PrimitiveEnd end) {
		if (!actions.containsKey(end)) {
			throw new RuntimeException("Firing does not include " + end + " of " + end.getPrimitive());
		}

		return actions.get(end).booleanValue();
	}

	/*
	 * Internal set method for end actions.
	 */
	private void internalSet(PrimitiveEnd end, boolean value) {
		if (actions.containsKey(end)) {
			boolean set = actions.get(end);
			if (set!=value) {
				throw new RuntimeException("Action for " + end + " of " + end.getPrimitive() + " is already set to " + set + "!");
			}
		} else {
			actions.put(end, value);
		}
	}
	
	
	/**
	 * Get the next firing.
	 * @return Next firing.
	 */
	public Firing next() {
		return next;
	}
	
	/**
	 * Set the next firing.
	 * @param next Next firing.
	 */
	public void setNext(Firing next) {
		this.next = next;
		updateSharedData();
	}
	
	/**
	 * Set the colouring cache to be used.
	 * @param cache Colouring cache.
	 */
	public void setColouringCache(ColouringCache cache) {
		this.cache = cache;
		updateSharedData();
	}
	
	/*
	 * Update the shared data of all referenced firing instances.
	 */
	private void updateSharedData() {
		validate();
		Firing current = this;
		while (current!=null) {
			current.elements = this.elements;
			current.cache = this.cache;
			current = current.next;
		}
	}
	
	/**
	 * Derive the animation table for this trace of firings.
	 * @param monitor Progress monitor.
	 * @return Animations.
	 */
	public AnimationTable computeAnimations(IProgressMonitor monitor) {
		
		validate();
		System.out.println(this);
		
		// Clear the cache first.
		cache.clear();
		List<Colourable> colourables = new ArrayList<Colourable>();
		
		for (Connectable element : elements) {
			
			// Get a fresh colouring table of the current element.
			if (!(element instanceof Colourable)) throw new RuntimeException(element + " is not colourable");
			colourables.add((Colourable) element);
			
			// Restrict the table and all next tables.
			try {
				ColouringTable table = cache.get(element);
				table = restrict(table, element.getAllEnds(), 1);
				cache.put((Colourable) element, table);
				
				//System.out.println("Restricted table of " + element + ":");
				//System.out.println(table);

			} catch (RuntimeException e) {
				System.out.println("ERROR restricting colouring table of " + element + ":");
				System.out.println("  --> " + e.getMessage());
				System.out.println(printTrace(element));
				throw e;
			}
						
		}
		// Compute the animations.
		StepwiseColouringEngine engine = new StepwiseColouringEngine();
		engine.setColours(2);
		return AnimationCompiler.computeAnimations(colourables, null, engine, cache, monitor);
		
	}
	
	/**
	 * Compute the animations and order them in an AnimationList.
	 * @param monitor Progress monitor.
	 * @param showNoFlow show no-flow steps
	 * @return Generated animation list.
	 */
	public AnimationList computeAnimationList(IProgressMonitor monitor, boolean showNoFlow) {
		AnimationTable table = computeAnimations(monitor);
		AnimationExpander expander = new AnimationExpander();
		expander.setRemoveNoFlow(!showNoFlow);
		AnimationMatrix matrix = expander.expand(table, new NullProgressMonitor());
		return matrix.get(0);
	}
	
	
	/*
	 * Restrict a colouring table to the actions defined in this colouring.
	 */
	protected ColouringTable restrict(ColouringTable table, List<PrimitiveEnd> ends, int step) {
		
		// Make a copy of the table.
		ColouringTable result = table.getCopy();
		
		// Iterate over all colourings.
		for (int i=0; i<result.getColourings().size(); i++) {
			
			Colouring current = result.getColourings().get(i);
			boolean compatible = true;
			
			// Iterate over all ends and find out whether the colouring is ok.
			for (PrimitiveEnd end : ends) {
				if (!actions.containsKey(end)) {
					throw new RuntimeException("Firing does not include " + end + " of " + end.getPrimitive());
				}
				compatible = compatible && (actions.get(end)==current.isFlow(end));
			}
			
			// If not compatible, remove the colouring.
			if (!compatible) {
				result.getColourings().remove(i--);
			}
		}
		
		// Now all colourings should have the same next table.
		if (!result.getColourings().isEmpty()) {
			
			// Check for nondeterminism.
			ColouringTable next = result.getColourings().get(0).getNextColouringTable();
			for (Colouring current : result.getColourings()) {
				if (current.getNextColouringTable()!=next) {
					throw new RuntimeException("Nondeterminism in colouring table");
				}
			}
			
			// Restrict the table for the next firing.
			if (next()!=null) {
				next = next().restrict(next, ends, step+1);
			} else {
				next = new ColouringTable();
			}
			
			// Set the next table.
			for (Colouring current : result.getColourings()) {
				current.setNextColouringTable(next);
			}

		} else {
			for (PrimitiveEnd end : ends) {	
				System.out.println("Required color for " + end + ": " + actions.get(end));
			}
			throw new RuntimeException("No compatible colouring found for firing in step " + step);
		}
		
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext() {
		return next!=null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return printTrace();
	}
	
	/**
	 * Compute the length of this trace.
	 * @return Length.
	 */
	public int length() {
		validate();
		if (next==null) return 1;
		else return 1 + next.length();
	}
	
	/**
	 * Check if there are cycles in this trace.
	 */
	public void validate() {
		Set<Firing> firings = new HashSet<Firing>();
		Firing current = this;
		while (current!=null) {
			if (firings.contains(current)) throw new RuntimeException("Loop in trace detected");
			firings.add(current);
			current = current.next;
		}
	}
	
	/**
	 * Print the firing of a particular element.
	 * @param element The element.
	 * @return String representation.
	 */
	protected String printActions(Connectable element) {
		if (elements.contains(element)) {
			String result = new String("[");
			for (int i=0; i<element.getSourceEnds().size(); i++) {
				result = result + actions.get(element.getSourceEnds().get(i));
				if (i<element.getSourceEnds().size()-1) result = result + ",";
			}
			result = result + "],[";
			for (int i=0; i<element.getSinkEnds().size(); i++) {
				result = result + actions.get(element.getSinkEnds().get(i));
				if (i<element.getSinkEnds().size()-1) result = result + ",";
			}
			return result + "]";
		} else {
			return element + " is not included in this firing!";
		}
	}
	
	/**
	 * Print the trace for a particular element.
	 * @param element Element.
	 * @return String representation.
	 */
	public String printTrace(Connectable element) {
		validate();
		StringBuffer result = new StringBuffer("=== Trace for " + element + " ===\n");
		Firing current = this;
		int step = 1;
		while (current!=null) {
			result.append("Step " + (step++) + ": " + current.printActions(element) + "\n");
			current = current.next;
		}
		return result.toString();
	}
	
	
	/**
	 * Print the trace for all elements.
	 * @return String representation.
	 */
	public String printTrace() {		
		// Make sure the trace is valid.
		validate();
		StringBuffer result = new StringBuffer("=== Trace for " + elements.size() + " elements ===\n");
		Firing current = this;
		int step = 1;
		while (current!=null) {
			result.append("Step " + (step++) + ": ");
			for (Connectable element : elements) {
				result.append(element.getClass().getSimpleName());
				if (element instanceof Nameable) {
					String name = ((Nameable) element).getName();
					if (name!=null) result.append(" " + name);
				}
				result.append(": " + current.printActions(element) + " ");
			}
			result.append("\n");
			current = current.next;
		}
		return result.toString();
	}
	
}
