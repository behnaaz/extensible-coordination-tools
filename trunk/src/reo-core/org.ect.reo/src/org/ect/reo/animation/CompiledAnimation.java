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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.ColouringCache;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class CompiledAnimation extends Animation {
	
	// Colouring for which we want to compile the animation for.
	private Colouring colouring;
	
	// A colouring cache.
	private ColouringCache cache;
	
	// List of border elements.
	private List<Connectable> border;
	
	/**
	 * Set the colouring to be used.
	 * @param colouring The colouring.
	 */
	public void setColouring(Colouring colouring) {
		this.colouring = colouring;
	}
		
	/**
	 * Compile the animation.
	 */
	public void compile() {
		
		// Make sure the border list is not null.
		if (border==null) border = new ArrayList<Connectable>();
		
		copyColouring(colouring);
		getParts().addAll(colouring.getParts());
		
		// We keep a list of the current primitives, the visited primitives and visited nodes.
		List<Connectable> visited = new ArrayList<Connectable>();
		List<Connectable> delayed = new ArrayList<Connectable>();
		for (Connectable element : getElements()) {
			if (colouring.hasInitialFlow(element,border)) delayed.add(element);
		}
		
		// Breadth first search.
		while (!delayed.isEmpty()) {
			
			// Termination check.
			Set<Connectable> newDelayed = new HashSet<Connectable>();
			
			// Merge the animations of the next elements.
			Animation animation = new Animation();
			for (int j=0; j<delayed.size(); j++) {
				
				Connectable element = delayed.get(j);
				
				// Make sure that there are tokens available.
				if (!isReady(element,visited)) continue;
				
				// Do the merge.
				animation.merge(findAnimation((Animatable) element, colouring));
				
				delayed.remove(j--);
				newDelayed.addAll(getNextElements(element));
				
				visited.add(element);
			}
			
			// Append the animation.
			append(animation);
			
			// Add (only!) the new delayed elements.
			for (Connectable element : newDelayed) {
				if (!delayed.contains(element) && !visited.contains(element)) {
					delayed.add(element);
				}
			}
			
		}
		
		// Add remaining parts.
		for (PrimitiveEnd end : colouring.getColours().keySet()) {
			if (getColours().get(end)!=null) continue;
			if (!border.contains(end.getPrimitive())) {
				Animation noFlow = findAnimation(end.getPrimitive(), colouring);
				if (noFlow!=null) append(noFlow);
			}
			if (!border.contains(end.getNode())) {
				Animation noFlow = findAnimation(end.getNode(), colouring);
				if (noFlow!=null) append(noFlow);
			}			
		}
		
	}
	
	
	/*
	 * Find the original animation for a given colouring.
	 * Make sure the cache is always set correctly. Otherwise
	 * you might get some unexpected results, e.g. for the full FIFO.
	 */
	private Animation findAnimation(Animatable a, Colouring c) {
		AnimationTable t = (cache!=null) ? (AnimationTable) cache.get(a) : a.getAnimationTable();
		return t.findAnimation(c);
	}
	
	
	/*
	 * Compute the set of elements that are involved in the animation.
	 */
	private Set<Connectable> getElements() {
		Set<Connectable> elements = new HashSet<Connectable>();		
		for (PrimitiveEnd end : colouring.getColours().keySet()) {
			Primitive primitive = end.getPrimitive();
			Node node = end.getNode();
			// Use only non-border primitives and nodes!
			if (primitive!=null && !border.contains(primitive)) elements.add(primitive);
			if (node!=null && !border.contains(node)) elements.add(node);
		}
		return elements;
	}
	
	
	/*
	 * Makes sure that there is a token at all source ends of a primitive / all sink ends of a node.
	 */
	private boolean isReady(Connectable element, Collection<Connectable> visited) {
		if (element instanceof Primitive) { 
			for (PrimitiveEnd end : element.getSourceEnds()) {
				Node node = end.getNode();
				if (colouring.isFlow(end) && node!=null && 
					!visited.contains(node) && !border.contains(node)) {
					return false;
				}
			}
		} else {
			for (PrimitiveEnd end : element.getSinkEnds()) {
				Primitive primitive = end.getPrimitive();
				if (colouring.isFlow(end) && primitive!=null && 
					!visited.contains(primitive) && !border.contains(primitive)) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	private Set<Connectable> getNextElements(Connectable element) {
		Set<Connectable> next = new HashSet<Connectable>();
		if (element instanceof Primitive) {
			for (SinkEnd sinkEnd : element.getSinkEnds()) {
				Node node = sinkEnd.getNode();
				// Must not be a border node!
				if (node!=null && !border.contains(node) && colouring.isFlow(sinkEnd)) {
					next.add(node);
				}
			}
		}
		if (element instanceof Node) {
			for (SourceEnd sourceEnd : element.getSourceEnds()) {
				Primitive primitive = sourceEnd.getPrimitive();
				// Must not be a border primitive!
				if (primitive!=null && !border.contains(primitive) && colouring.isFlow(sourceEnd)) {
					next.add(primitive);
				}
			}
		}
		return next;
	}
	
	/**
	 * Set the colouring cache. This should contain the colouring tables
	 * of the used primitives.
	 * @param cache The colouring cache.
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
}
