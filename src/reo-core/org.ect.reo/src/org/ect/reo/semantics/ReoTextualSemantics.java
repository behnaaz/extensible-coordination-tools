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
package org.ect.reo.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.Component;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.util.PrimitiveEndNames;
import org.ect.reo.util.PropertyHelper;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ReoTextualSemantics {
	
	/**
	 * Registry for semantics providers.
	 */
	public static final TextualSemanticsRegistry REGISTRY = new TextualSemanticsRegistry();
	
	
	/**
	 * Get the textual semantics for a connectable element.
	 * 
	 * @param element Element.
	 * @param key Key of the semantics.
	 * @return Textual representation of the semantics. 
	 */
	public static String getElementSemantics(Connectable element, String key) {
		
		// Check if it is a user-defined component.
		if ((element instanceof CustomPrimitive) &&
					(!(element instanceof Component) ||
	 				   element.getClass()==Component.class)) {
			String value = PropertyHelper.getFirstValue((CustomPrimitive) element, key);
			return value;
		
		} else {

			// Call the provider.
			TextualSemanticsProvider provider = getProvider(key);
			return provider.getElementSemantics(element, getEndNames(element));

		}
				
	}
	
	
	/**
	 * Compute the textual semantics for a connector.
	 * 
	 * @see TextualSemanticsProvider#computeConnectorSemantics(Connector, Map, org.eclipse.core.runtime.IProgressMonitor)
	 * @param connector Connector.
	 * @param key Key of the semantics.
	 * @return Textual representation of the semantics. 
	 */
	public static String computeConnectorSemantics(Connector connector, String key, IProgressMonitor monitor) {
		
		// Get the provider.
		TextualSemanticsProvider provider = getProvider(key);
		PrimitiveEndNames names = new PrimitiveEndNames();
		
		// Initialize the external end names.
		for (Primitive primitive : connector.getForeignPrimitives()) {
			names.generate(primitive);
		}
		
		// Initialize the internal end names.
		for (Primitive primitive : connector.getPrimitives()) {
			names.generate(primitive);
		}
		
		// Make the call.
		return provider.computeConnectorSemantics(connector, names, monitor);
		
	}
	
	
	/**
	 * Compute the textual semantics for a network.
	 * @param network Network.
	 * @param key Key of the semantics provider.
	 * @param monitor Progress monitor.
	 * @return The textual semantics.
	 */
	public static String computeNetworkSemantics(Network network, String key, IProgressMonitor monitor) {

		// Get the provider.
		TextualSemanticsProvider provider = getProvider(key);		
		PrimitiveEndNames names = new PrimitiveEndNames();
		
		// Initialize the end names for the components first.
		for (Component component : network.getComponents()) {
			names.generate(component);
		}
		
		// Initialize the internal end names.
		for (Primitive primitive : network.getAllPrimitives()) {
			if (!network.getComponents().contains(primitive)) {
				names.generate(primitive);
			}
		}
		
		// Make the call.
		return provider.computeNetworkSemantics(network, names, monitor);
	}
		
	
	/*
	 * Get a provider for a given key. Throws a runtime exception if the provider was not found.
	 */
	private static TextualSemanticsProvider getProvider(String key) {
		TextualSemanticsProvider provider = REGISTRY.get(key);
		if (provider==null) throw new RuntimeException("No semantics provider for key '" + key + "' found.");
		return provider;
	}
	
	
	// ------- Helper methods for resolving end names ------ //
	
	/**
	 * Get the end names for an element.
	 * @param element The element.
	 * @return End names map.
	 */
	public static PrimitiveEndNames getEndNames(Connectable element) {
		PrimitiveEndNames names = new PrimitiveEndNames();
		names.generate(element);
		return names;
	}
	
	/**
	 * Get the ends of an element indexed by their names.
	 * @param element The element.
	 * @return Names to end map.
	 */
	public static Map<String,PrimitiveEnd> getEnds(Connectable element) {
		PrimitiveEndNames names = getEndNames(element);
		Map<String,PrimitiveEnd> result = new HashMap<String,PrimitiveEnd>();
		for (PrimitiveEnd end : element.getAllEnds()) {
			result.put(names.get(end), end);
		}
		// Also add the end names to it (node names are usually prefered):
		for (PrimitiveEnd end : element.getAllEnds()) {
			if (end.getName()!=null && !result.containsKey(end.getName())) {
				result.put(end.getName(), end);
			}
		}
		return result;
	}

	
	/**
	 * Create a normalized copy of the argument connector. This disconnects all
	 * attached components and attaches readers and writers instead. The original
	 * connector is not modified. A copy is created instead.
	 * @param connector Connector.
	 * @param End names, can be <code>null</code>.
	 * @return A normalized copy of the connector.
	 */
	public static Connector createNormalizedConnector(Connector connector, PrimitiveEndNames names) {
		
		// Make a copy of the connector.
		EcoreUtil.Copier copier = new EcoreUtil.Copier();
		Connector copy = (Connector) copier.copy(connector);
	    copier.copyReferences();
	    
	    // Use the same colouring engine.
		//copy.setColouringEngine(connector.getColouringEngine());
		
		// Update the primitive end names:
		if (names!=null) {
			for (PrimitiveEnd end : new ArrayList<PrimitiveEnd>(names.keySet())) {
				if (copier.containsKey(end)) {
					names.put((PrimitiveEnd) copier.get(end), names.get(end));
				}
			}
		}
		
		// Attach writers to the source nodes.
		for (Node node : connector.getSourceNodes()) {
			Node newNode = (Node) copier.get(node);
			String name = getEndName(node.getSinkEnds(), names);
			if (name==null) name = node.getName();
			newNode.getSinkEnds().clear();
			Writer writer = new Writer(newNode);
			writer.setRequests(-1);
			writer.getEnd().setName(name);
			if (names!=null && name!=null) names.put(writer.getEnd(), name);
		}
		
		// Attach readers to the sink nodes.
		for (Node node : connector.getSinkNodes()) {
			Node newNode = (Node) copier.get(node);
			String name = getEndName(node.getSourceEnds(), names);
			if (name==null) name = node.getName();
			newNode.getSourceEnds().clear();
			Reader reader = new Reader(newNode);
			reader.setRequests(-1);
			reader.getEnd().setName(name);
			if (names!=null && name!=null) names.put(reader.getEnd(), name);
		}

		return copy;
	}
	
	/*
	 * Private helper for determining the name of an end if there are more than one.
	 */
	private static String getEndName(List<? extends PrimitiveEnd> ends, PrimitiveEndNames names) {
		if (names==null) return null;
		for (PrimitiveEnd end : ends) {
			if (names.containsKey(end)) return names.get(end);
		}
		return null;
	}
	
}
