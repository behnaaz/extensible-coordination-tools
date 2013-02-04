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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.ect.reo.Component;
import org.ect.reo.Composite;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.ReconfAction;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;



/**
 * @author Christian Krause
 * @generated NOT
 */
public class ReconfElements {
	
	// 'Active' actions.
	private static final ReconfType[] ACTIVE;	
	static {
		ACTIVE = new ReconfType[ReconfType.values().length-1];
		for (int i=0; i<ACTIVE.length; i++) ACTIVE[i] = ReconfType.values()[i+1];
	}

	
	/**
	 * Collect all reconfigurable elements in a composite.
	 * @param composite Composite.
	 * @return List of elements.
	 */
	public static Reconfigurable[] collectAllElements(Composite composite) {
		return collectRuleElements(null, composite, ReconfType.values());
	}
	
	/**
	 * Collect all elements that belong to a rule, i.e. that have an action
	 * that is not Reconf.NONE associated to the argument rule.
	 * @param rule The rule
	 * @param composite Composite.
	 * @return List of elements.
	 */
	public static Reconfigurable[] collectActiveRuleElements(ReconfRule rule, Composite composite) {
		return collectRuleElements(rule, composite, ACTIVE);
	}
	
	/**
	 * Collect all elements with a given action type.
	 */
	public static Reconfigurable[] collectRuleElements(ReconfRule rule, Composite composite, ReconfType type) {
		return collectRuleElements(rule, composite, new ReconfType[] { type } );
	}

	
	/**
	 * Collect all elements with a given action type.
	 */
	public static Reconfigurable[] collectRuleElements(ReconfRule rule, Composite composite, ReconfType[] types) {
		List<Reconfigurable> result = new ArrayList<Reconfigurable>();
		for (Connector connector : composite.getConnectors()) collectRuleElements(rule, connector, types, result);
		for (Component component : composite.getComponents()) collectRuleElements(rule, component, types, result);
		Reconfigurable[] array = new Reconfigurable[result.size()];
		return result.toArray(array);
	}
	
	
	/*
	 * Collect reconfigurable elements.
	 */
	private static void collectRuleElements(ReconfRule rule, EObject object, ReconfType[] types, Collection<Reconfigurable> collected) {
		
		// Add the object itself.
		if (object instanceof Reconfigurable && matchesAction((Reconfigurable) object, rule, types)) {
			collected.add((Reconfigurable) object);
		}
		
		// Collect all children with the given action.
		TreeIterator<EObject> elements = object.eAllContents();	
		while (elements.hasNext()) {			
			EObject current = elements.next();
			if (current instanceof Reconfigurable && matchesAction((Reconfigurable) current, rule, types)) {
				collected.add((Reconfigurable) current);
			}
		}
	}
	
	
	/*
	 * Check whether an element has a particular action.
	 */
	private static boolean matchesAction(Reconfigurable element, ReconfRule rule, ReconfType[] types) {
		if (rule==null) return true;
		ReconfAction action = ReconfActions.internalGet(element, rule);
		ReconfType type = (action==null) ? ReconfType.NONE : action.getType();
		for (int i=0; i<types.length; i++) {
			if (types[i]==type) return true;
		}
		return false;
	}
	
	
	
	/**
	 * Create a rule network. A rule network consists of all connected parts of a rule.
	 * @param rule The rule.
	 * @param module Module that contains the rule.
	 * @return The created network.
	 */
	public static Network createRuleNetwork(ReconfRule rule, Module module) {
		Network network = new Network();
		Reconfigurable[] elements = collectActiveRuleElements(rule, module);
		for (Reconfigurable element : elements) {
			
			if (element instanceof Connector) {
				network.getConnectors().add((Connector) element);
			}
			else if (element instanceof Component) {
				network.getComponents().add((Component) element);
			}
			else if (element instanceof Node) {
				network.getConnectors().add(((Node) element).getConnector());
			}
			else if (element instanceof Primitive) {
				EObject container = element.eContainer();
				if (container instanceof Connector) {
					network.getConnectors().add((Connector) container);
				}
			}
		}
		network.update();
		return network;
	}
	
	
	/**
	 * Compute the elements that are not reached by a match.
	 * @param targets Codomain of the match.
	 * @param match The match.
	 * @return Elements that are not reached.
	 */
	public static Reconfigurable[] notMatched(Reconfigurable[] targets, ReconfMatch match) {
		
		Set<Reconfigurable> notMatched = new HashSet<Reconfigurable>();
		notMatched.addAll(Arrays.asList(targets));
		notMatched.removeAll(match.keySet());
		
		Reconfigurable[] notMatched_ = new Reconfigurable[notMatched.size()];
		notMatched_ = notMatched.toArray(notMatched_);
		return notMatched_;
		
	}

	
}
