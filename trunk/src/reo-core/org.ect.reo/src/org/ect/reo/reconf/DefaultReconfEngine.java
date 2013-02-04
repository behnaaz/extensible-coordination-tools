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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Nameable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.Property;
import org.ect.reo.PropertyHolder;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;
import org.ect.reo.Reo;
import org.ect.reo.ReoFactory;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.ChannelsFactory;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.Transform;
import org.ect.reo.components.ComponentsFactory;


/**
 * Reo reconfiguration engine.
 * @generated NOT
 * @author Christian Krause
 *
 */
public class DefaultReconfEngine implements ReconfEngine {
	
	// Set of listeners.
	protected Set<ReconfListener> listeners = new HashSet<ReconfListener>();
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.reconf.ReconfEngine#applyRule(org.ect.reo.ReconfRule, org.ect.reo.reconf.ReconfMatch, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void applyRule(ReconfRule rule, ReconfMatch match, IProgressMonitor monitor) throws ReconfException {
		
		// Check if the rule exists.
		monitor.beginTask(rule.getName(), 2);
		
		// Delete elements.
		monitor.subTask("Deleting elements");
		List<Reconfigurable> removing = new ArrayList<Reconfigurable>();
		for (Reconfigurable element : match.keySet()) {
			if (ReconfType.get(element, rule)==ReconfType.DELETE) {
				removing.add(element);
			}
		}
		// Sort the list first.
		Collections.sort(removing, new ReconfComparator(ReconfType.DELETE));
		
		// Remove the elements.
		for (Reconfigurable element : removing) {
			delete(match.get(element));
			// Notify listeners.
			for (ReconfListener listener : listeners) {
				listener.elementDeleted(match.get(element), element);
			}
		}
		monitor.worked(1);
		
		
		// Create elements.
		monitor.subTask("Creating new elements");
		Reconfigurable[] create = ReconfElements.collectRuleElements(rule, match.getSource(), ReconfType.CREATE);
		
		// Sort the elements first.
		Arrays.sort(create, new ReconfComparator(ReconfType.CREATE));
		
		// Create the elements.
		createAll(create, match);
		monitor.worked(1);
		
		monitor.done();
		
	}
	

	/**
	 * Remove an element from its container.
	 * @param element Element to be removed.
	 */
	private void delete(Reconfigurable element) {
		
		// Is it a node?
		if (element instanceof Node) {
			Node node = (Node) element;
			node.getConnector().removeNode(node);
		}
		
		// Or a primitive?
		else if (element instanceof Primitive) {
			Primitive primitive = (Primitive) element;
			if (primitive.eContainer() instanceof Connector) {
				((Connector) primitive.eContainer()).removePrimitive(primitive);
			}
			else if (primitive.eContainer() instanceof Module) {
				primitive.disconnectEnds();
				((Module) primitive.eContainer()).getComponents().remove(primitive);
			}
		}
		
		// Or a connector?
		else if (element instanceof Connector) {
			Connector connector = (Connector) element;
			
			// Remove all nodes, primitives and sub-connectors.
			while (!connector.getPrimitives().isEmpty()) {
				connector.removePrimitive(connector.getPrimitives().get(0));
			}
			while (!connector.getNodes().isEmpty()) {
				connector.removeNode(connector.getNodes().get(0));
			}
			while (!connector.getSubConnectors().isEmpty()) {
				delete(connector.getSubConnectors().get(0));
			}
		}
		
	}
	
	
	/**
	 * Create elements and wire them up.
	 * @param templates Templates.
	 * @param match Match.
	 */
	private void createAll(Reconfigurable[] templates, ReconfMatch match) {
		
		// First create all elements.
		for (Reconfigurable elem : templates) {
			match.put(elem, create(elem));
		}
		
		// Wire primitives.
		for (Reconfigurable elem : templates) {
			if (elem instanceof Primitive) {
				
				Primitive e = (Primitive) elem;
				Primitive c = (Primitive) match.get(elem);
				
				// Source ends.
				for (int j=0; j<e.getSourceEnds().size(); j++) {
					Node node = e.getSourceEnds().get(j).getNode();
					if (node!=null && match.containsKey(node)) node = (Node) match.get(node);
					c.getSourceEnds().get(j).setNode(node);
				}
				
				// Sink ends.
				for (int j=0; j<e.getSinkEnds().size(); j++) {
					Node node = e.getSinkEnds().get(j).getNode();
					if (node!=null && match.containsKey(node)) node = (Node) match.get(node);
					c.getSinkEnds().get(j).setNode(node);
				}
			}
		}
		
		// Add elements to their containers.
		for (Reconfigurable temp : templates) {
			
			Reconfigurable elem = match.get(temp);
			EObject container = temp.eContainer();
			
			// Determine the target container.
			EObject target;
			if (container==match.getSource()) {
				target = match.getTarget();
			} 
			else if (match.containsKey(container)) {
				 target = match.get(container);
			} 
			else {
				target = container;
			}
			
			if (target instanceof Connector) {
				Connector connector = (Connector) target;
				if (elem instanceof Node) connector.getNodes().add((Node) elem);
				if (elem instanceof Primitive) connector.getPrimitives().add((Primitive) elem);
				if (elem instanceof Connector) connector.getSubConnectors().add((Connector) elem);
			}
			else if (target instanceof Module) {
				Module module = (Module) target;
				if (elem instanceof Component) module.getComponents().add((Component) elem);
				if (elem instanceof Connector) module.getConnectors().add((Connector) elem);
			}
			
			// Notify listeners.
			for (ReconfListener listener : listeners) {
				listener.elementCreated(elem, temp);
			}
		}

		
	}

	/**
	 * Create a single element.
	 * @param template Template.
	 * @return The element.
	 */
	private Reconfigurable create(Reconfigurable template) {
		
		EClass type = template.eClass();
		Reconfigurable result = null;
		
		if (template instanceof Channel) {
			result = (Reconfigurable) ChannelsFactory.eINSTANCE.create(type);
		}
		else if (template instanceof Component && template.getClass()!=Component.class) {
			result = (Reconfigurable) ComponentsFactory.eINSTANCE.create(type);
		}
		else {
			result = (Reconfigurable) ReoFactory.eINSTANCE.create(type);
		}
		
		// Set the name.
		if (result instanceof Nameable) {
			((Nameable) result).setName( ((Nameable) template).getName() );
		}
		
		// Copy properties.
		if (template instanceof PropertyHolder) {
			for (Property property : ((PropertyHolder) template).getProperties()) {
				Property copy = (Property) EcoreUtil.copy(property);
				((PropertyHolder) result).getProperties().add(copy);
			}
		}
		
		// Create primitive ends.
		if (result instanceof Primitive) {
			for (SourceEnd end : ((Primitive) template).getSourceEnds()) {
				SourceEnd copy = new SourceEnd(end.getName());
				((Primitive) result).getSourceEnds().add(copy);
			}
			for (SinkEnd end : ((Primitive) template).getSinkEnds()) {
				SinkEnd copy = new SinkEnd(end.getName());
				((Primitive) result).getSinkEnds().add(copy);
			}
		}
		
		// Some special attributes.
		if (result instanceof Node) {
			((Node) result).setType( ((Node) template).getType() );
		}
		if (result instanceof FIFO) {
			((FIFO) result).setFull( ((FIFO) template).isFull() );
		}
		if (result instanceof Filter) {
			((Filter) result).setExpression( ((Filter) template).getExpression() );
		}
		if (result instanceof Transform) {
			((Transform) result).setExpression( ((Transform) template).getExpression() );
		}
		
		if (result==null) {
			Reo.logError("Error cloning element " + template);
		}
		
		return result;
		
	}
	
	
	// ------ Reconfiguration listeners ------- //
	
	/* (non-Javadoc)
	 * @see org.ect.reo.reconf.ReconfEngine#addReconfListener(org.ect.reo.reconf.ReconfListener)
	 */
	public void addReconfListener(ReconfListener listener) {
		listeners.add(listener);
	}
	
	/* (non-Javadoc)
	 * @see org.ect.reo.reconf.ReconfEngine#removeReconfListener(org.ect.reo.reconf.ReconfListener)
	 */
	public void removeReconfListener(ReconfListener listener) {
		listeners.remove(listener);
	}

}
