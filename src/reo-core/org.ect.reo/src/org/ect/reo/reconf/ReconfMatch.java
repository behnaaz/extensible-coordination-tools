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
package org.ect.reo.reconf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.ect.reo.Component;
import org.ect.reo.Module;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.Reconfigurable;
import org.ect.reo.channels.FIFO;


/**
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class ReconfMatch extends HashMap<Reconfigurable,Reconfigurable> {
	
	private static final long serialVersionUID = 1L;
	
	private Module source, target;
	
	/**
	 * Constructor.
	 */
	public ReconfMatch(Module source, Module target) {
		super();
		this.source = source;
		this.target = target;
	}
	
	/**
	 * Create a copy of an existing match.
	 * @param match Existing match.
	 */
	public ReconfMatch(ReconfMatch match) {
		this(match.getSource(), match.getTarget());
		for (Reconfigurable key : match.keySet()) {
			put(key, match.get(key));
		}
	}
	
	/**
	 * Validate this reconfiguration match.
	 * @throws ReconfException On validation errors.
	 */
	public void validate() throws ReconfException {
		
		// Check types.
		for (Reconfigurable elem : keySet()) {
			if (!canMatch(elem, get(elem))) {
				throw new ReconfException("Incompatible types: " + elem + " cannot be mapped to " + get(elem));
			}
		}
		
		// Check graph homomorphism condition.
		for (Reconfigurable elem : keySet()) {
			
			// Check the container.
			EObject container = elem.eContainer();
			if (containsKey(container) && get(container)!= get(elem).eContainer()) {
				throw new ReconfException(get(elem) + " is in the wrong container");
			}
			
			
			if (!(elem instanceof Primitive)) continue;
			Primitive p1 = (Primitive) elem;
			Primitive p2 = (Primitive) get(elem);
			
			// Check the number of ends first.
			if (p1.getSourceEnds().size()!=p2.getSourceEnds().size() ||
				p1.getSinkEnds().size()!=p2.getSinkEnds().size()) {
				throw new ReconfException(p2 + " has wrong number of ends");
			}
			
			// Source ends.
			for (int i=0; i<p1.getSourceEnds().size(); i++) {
				Node origin = p1.getSourceEnds().get(i).getNode();
				if (origin==null) continue;
				Node is = (Node) (containsKey(origin) ? get(origin) : origin);
				Node should = p2.getSourceEnds().get(i).getNode();
				if (is!=should) {
					throw new ReconfException("Graph morphism condition violated for " + p1 + " at source end " + i);
				}
			}
			// Sink ends.
			for (int i=0; i<p1.getSinkEnds().size(); i++) {
				Node origin = p1.getSinkEnds().get(i).getNode();
				if (origin==null) continue;
				Node is = (Node) (containsKey(origin) ? get(origin) : origin);
				Node should = p2.getSinkEnds().get(i).getNode();
				if (is!=should) {
					throw new ReconfException("Graph morphism condition violated for " + p1 + " at sink end " + i);
				}
			}
		}

	}
	
	/**
	 * Check whether source can be mapped to target. This checks only
	 * whether the type of source and target are compatible.
	 * @param source Source element.
	 * @param target Target element.
	 * @return <code>true</code> if the match is ok.
	 */
	public static boolean canMatch(Reconfigurable source, Reconfigurable target) {
		
		// Check the types.
		if (!source.getClass().isInstance(target)) return false;
		
		// Some special cases.
		if (source instanceof Node) {
			if (((Node) source).getType() != ((Node) target).getType()) return false;
		}
		if (source instanceof Component) {
			if (((Component) source).getSourceEnds().size()!=((Component) target).getSourceEnds().size()) return false;
			if (((Component) source).getSinkEnds().size()!=((Component) target).getSinkEnds().size()) return false;
		}
		if (source instanceof FIFO) {
			if (((FIFO) source).isFull()!=((FIFO) target).isFull()) return false;
		}
		
		return true;
	}
	
	
	/**
	 * Compute the identity match for a given set of elements.
	 * @param elements Reconfigurable elements.
	 * @return Identity reconfiguration match.
	 */
	public static ReconfMatch identity(Module module, Reconfigurable[] elements) {
		ReconfMatch match = new ReconfMatch(module, module);
		for (Reconfigurable element : elements) {
			match.put(element, element);
		}
		return match;
	}
	
	/**
	 * Check if the argument match overlaps in the values of the argument elements.
	 * @param match Reconfiguration match.
	 * @param elements Keys.
	 * @return <code>true</code> if the matches overlap in their values.
	 */
	public boolean valuesOverlap(ReconfMatch match, Reconfigurable[] elements) {
		Set<Reconfigurable> range1 = new HashSet<Reconfigurable>();
		Set<Reconfigurable> range2 = new HashSet<Reconfigurable>();
		for (int i=0; i<elements.length; i++) {
			if (containsKey(elements[i])) range1.add(get(elements[i]));
			if (match.containsKey(elements[i])) range2.add(match.get(elements[i]));
		}
		range1.retainAll(range2);
		return !range1.isEmpty();
	}
	
	public Module getSource() {
		return source;
	}
	
	public Module getTarget() {
		return target;
	}

}
