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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.ReconfAction;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;


/**
 * Methods for handling reconfiguration actions.
 * @author Christian Krause
 * @generated NOT
 */
public class ReconfActions {
		
	/*
	 * Get the reconfiguration action for a single rule.
	 */
	static ReconfAction internalGet(Reconfigurable element, ReconfRule rule) {
		for (ReconfAction action : element.getReconfActions()) {
			if (rule==action.getRule()) return action;
		}
		return null;
	}

	public static void set(Reconfigurable element, ReconfAction action) {
		
		// Set the action.
		if (internalSet(element,action)==false) return;
		
		// Check dangling edges.
		if (element instanceof Node) {
			for (PrimitiveEnd end : ((Node) element).getAllEnds()) {
				internalSet(end.getPrimitive(), action);
			}
		}
		else if (element instanceof Primitive) {
			Primitive primitive = (Primitive) element;
			for (PrimitiveEnd end : primitive.getAllEnds()) {
				if (end.getNode()==null) continue;
				ReconfType a = ReconfType.get(end.getNode(), action.getRule());
				if (a!=action.getType()) {
					internalSet(end.getNode(), new ReconfAction(action.getRule(),ReconfType.NONE));
				}
			}
		}
		// Take care of connector contents.
		else if (element instanceof Connector) {
			Connector connector = (Connector) element;
			for (Node node : connector.getNodes()) internalSet(node, action);
			for (Primitive primitive : connector.getPrimitives()) internalSet(primitive, action);
			for (Connector sub : connector.getSubConnectors()) set(sub, action);
		}
		
	}
	
	/*
	 * Internal helper method.
	 */
	private static boolean internalSet(Reconfigurable element, ReconfAction action) {
		action = (ReconfAction) EcoreUtil.copy(action);
		ReconfAction existing = internalGet(element,action.getRule());
		if (existing!=null) {
			if (existing.getType()==action.getType()) return false;
			int index = element.getReconfActions().indexOf(existing);
			element.getReconfActions().set(index, action);
		} else {
			element.getReconfActions().add(action);
		}
		return true;
	}

}
