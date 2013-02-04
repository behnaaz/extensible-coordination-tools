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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class PrimitiveEndNames extends HashMap<PrimitiveEnd,String> {
	
	// Default serial ID.
	private static final long serialVersionUID = 1L;
	
	// Collection of reserved names
	private Set<String> reservedNames = new HashSet<String>();
	
	/**
	 * Generate a name for an end.
	 * @param end End.
	 * @return Generated end name.
	 */
	public String generate(PrimitiveEnd end) {
		
		// The name of the end.
		String name = null;
		
		// Try to use a name of the node or the end.
		if (end.getNode()!=null) {
			name = end.getNode().getName();
		}
		if (isEmpty(name) || values().contains(name)) {
			name = end.getName();
		}
		
		// Otherwise use a simple letter.
		if (isEmpty(name) || isChar(name)) {
			
			char start = 'M', stop = 'Z';
			char c = isChar(name) ? name.charAt(0) : start;
			int i = 0;
			
			name = String.valueOf(c);
			while (values().contains(name)) {
				if (c==stop)	{ c = start; i++;	}					
				else { c++; }
				name = (i>0) ? String.valueOf(c) + i : String.valueOf(c);
			}
		}
		
		name = normalize(name);
		
		// Make sure the name does not exist already.
		if (values().contains(name)) {
			
			// Truncate trailing numbers:
			String baseName = name;
			while (Character.isDigit(baseName.charAt(baseName.length()-1))) {
				baseName = baseName.substring(0, name.length()-1);
			}
			
			// Add an index in the end if required:
			int index=0;
			while (values().contains(name)) {
				name = baseName + (index++);
			}
			
		}
		
		// Ok.
		put(end, name);
		return name;
	}
	
	/*
	 *  Remove non-word characters
	 * */
	private String normalize(String name){		
		return name.replaceAll("\\W", "");		
	}
	
	
	/*
	 * Check if a string is empty (null or just whitespaces)
	 */
	private boolean isEmpty(String string) {
		return (string==null || string.trim().equals(""));
	}
	
	/*
	 * Check if a string consists of a single upper case character.
	 */
	private boolean isChar(String string) {
		return (string!=null && string.length()==1 && Character.isUpperCase(string.charAt(0)));
	}
	

	/**
	 * Generate names for the ends of an element.
	 * @param element Element.
	 */
	public void generate(Connectable element) {
		for (PrimitiveEnd end : element.getAllEnds()) {
			generate(end);
		}
	}

	/**
	 * Generate names for the all ends in a connector.
	 * @param connector Connector.
	 */
	public void generate(Connector connector) {
		reserveNodeNames(connector.getNodes());
		for (Primitive primitive : connector.getPrimitives()) {
			generate(primitive);
		}
	}
	
	/**
	 * Generate names for the all ends in a network.
	 * @param network Network.
	 */
	public void generate(Network network) {
		reserveNodeNames(network.getAllNodes());
		for (Primitive primitive : network.getAllPrimitives()) {
			generate(primitive);
		}
	}
	
	private void reserveNodeNames(List<Node> nodes) {
		for (Node node : nodes) {
			if (node.getName()!=null) reservedNames.add(node.getName());
		}
	}
	
	public Collection<String> getReservedNames() {
		return reservedNames;
	}
	
}
