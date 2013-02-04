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
package org.ect.ea.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.ect.ea.EA;
import org.ect.ea.IAutomatonType;
import org.ect.ea.IAutomatonTypeRegistry;
import org.ect.ea.automata.Automaton;

/**
 * Default implementation of {@link org.ect.ea.IAutomatonTypeRegistry}.
 * @author Christian Krause
 * @generated NOT
 */
public class AutomatonTypeRegistry implements IAutomatonTypeRegistry {
	
	// Hashmap for registered automata types.
	private HashMap<HashSet<String>,IAutomatonType> types;
	
	/**
	 * Default constructor.
	 */
	public AutomatonTypeRegistry() {
		types = new LinkedHashMap<HashSet<String>, IAutomatonType>();		
	}
	
	/**
	 * Load automata types. Initializes the registry with all automata types
	 * that are given through entries in the plugin.xml of active plugins.
	 */
	public void load(IExtensionPoint extensionPoint) {
		
		List<IAutomatonType> types = new ArrayList<IAutomatonType>(); 
		
		// Load the type definitions.
		for (IConfigurationElement element : extensionPoint.getConfigurationElements()) {
			if (element.getName().equals("automatonType")) {	
				types.add(new AutomatonType(element));
			}
		}
		
		// Sort the list of types.
		Collections.sort(types, new Comparator<IAutomatonType>() {

			public int compare(IAutomatonType o1, IAutomatonType o2) {
				String s1 = o1.getName()!=null ? o1.getName() : ""; 
				String s2 = o2.getName()!=null ? o2.getName() : ""; 
				return s1.compareToIgnoreCase(s2);
			}
			
		});
		
		// Update the hashmap.
		for (IAutomatonType current : types) {
			HashSet<String> ids = new HashSet<String>();
			for (String id : current.getExtensionIds()) ids.add(id);
			for (String id : current.getResolvedDependencies()) ids.add(id);
			for (String id : current.getUnresolvedDependencies()) ids.add(id);
			this.types.put(ids,current);
		}
		
		// Done.
	}
	
	
	/* (non-Javadoc)
	 * @see org.ect.ea.IAutomatonTypeRegistry#getAutomatonType(org.ect.ea.automata.Automaton)
	 */
	public IAutomatonType getAutomatonType(Automaton automaton) {
		HashSet<String> ids = new HashSet<String>( automaton.getUsedExtensionIds() );
		return types.get(ids);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IAutomatonTypeRegistry#setAutomatonType(org.ect.ea.automata.Automaton, org.ect.ea.IAutomatonType)
	 */
	public void setAutomatonType(Automaton automaton, IAutomatonType type) {
		String[] unresolved = type.getUnresolvedDependencies();
		for (String id : unresolved) {
			EA.logError("Extension type '" + id + "' could not be resolved.");
		}
		if (unresolved.length==0) {
			automaton.getUsedExtensionIds().clear();
			for (String id : type.getExtensionIds()) {
				automaton.getUsedExtensionIds().add(id);
			}
			for (String id : type.getResolvedDependencies()) {
				automaton.getUsedExtensionIds().add(id);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IAutomatonTypeRegistry#getAutomatonTypes()
	 */
	public IAutomatonType[] getAutomatonTypes() {
		IAutomatonType[] result = new IAutomatonType[ types.values().size() ];
		types.values().toArray(result);
		return result;
	}
		
}

