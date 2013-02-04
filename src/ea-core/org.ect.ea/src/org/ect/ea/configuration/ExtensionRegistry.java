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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;

/**
 * Default implementation of {@link IExtensionRegistry}.
 * @author Christian Krause
 * @generated NOT
 */
public class ExtensionRegistry implements IExtensionRegistry {
	
	// The list of registered extension definitions.
	private List<IExtensionDefinition> definitions; 
	
	// Hashmaps of resolved, unresolved and reverse dependencies.
	private HashMap<IExtensionDefinition,String[]> unresolvedDependencies;
	private HashMap<IExtensionDefinition,IExtensionDefinition[]> resolvedDependencies;
	private HashMap<IExtensionDefinition,IExtensionDefinition[]> reverseDependencies;

	// Hashmaps of local and global mutual exclusions.
	private HashMap<IExtensionDefinition,IExtensionDefinition[]> localExclusions;
	private HashMap<IExtensionDefinition,IExtensionDefinition[]> globalExclusions;
	
	// Flags indicating whether the dependencies and exclusions must be recomputed.
	private boolean dependenciesChanged, exclusionsChanged;
	
	/**
	 * Default constructor. 
	 */
	public ExtensionRegistry() {
		
		// Initialize definitions.
		definitions = new Vector<IExtensionDefinition>();

		// Initialize hashmaps.
		resolvedDependencies = new HashMap<IExtensionDefinition, IExtensionDefinition[]>();
		unresolvedDependencies = new HashMap<IExtensionDefinition, String[]>();
		reverseDependencies = new HashMap<IExtensionDefinition, IExtensionDefinition[]>();
		localExclusions = new HashMap<IExtensionDefinition, IExtensionDefinition[]>();
		globalExclusions = new HashMap<IExtensionDefinition, IExtensionDefinition[]>();
				
	}

	
	/**
	 * Load extension definitions. Initializes the registry with all definitions
	 * that are given through entries in the plugin.xml of active plug-ins.
	 */
	public void load(IExtensionPoint extensionPoint) {
		
		IConfigurationElement[] genElements = extensionPoint.getConfigurationElements();
				
		// Register definitions.
		for (int i=0;i<genElements.length; i++) {
			if (genElements[i].getName().equals("extensionDefinition")) {
				IExtensionDefinition definition = new ExtensionDefinition(genElements[i]);
				registerExtensionDefinition(definition);
			}
		}
		
		// Compute dependencies and exclusions.
		recomputeDependencies();
		recomputeMutualExclusions();
		
	}
	
	
	
	/* ----- Finding extension definitions. ------ */


	public IExtensionDefinition findExtensionDefinition(String typeId) {
		// Iterate all extension definitions.
		for (IExtensionDefinition definition : definitions) {
			if (definition.getId().equals(typeId)) return definition;
		}
		// Not found.
		return null;
	}
	

	public IExtensionDefinition[] getExtensionDefinitions() {	
		// Copy to an array.
		IExtensionDefinition[] result = new IExtensionDefinition[definitions.size()];
		return definitions.toArray(result);
	}

	
	
	/* ----- Registering extension definitions. ------ */

	
	public void registerExtensionDefinition(IExtensionDefinition definition) {
		
		if (definition==null) return;
		
		// Check if the extension is valid.
		if (!definition.isValid()) {
			EA.logError("Automata extension definition " + definition.getId() + " is invalid."); 
			return;
		}
		// Unregister old extensions definition, if required.
		if (findExtensionDefinition(definition.getId())!=null) {
			unregisterExtensionDefinition(definition);
		}
		// Register it.
		definitions.add(definition);
		dependenciesChanged = true;
		exclusionsChanged = true;
	}
	
	
	public void unregisterExtensionDefinition(IExtensionDefinition definition) {
		definitions.remove(definition);
		dependenciesChanged = true;
		exclusionsChanged = true;
	}

	
	
	/* ----- Dependencies of extension definitions. ------ */


	public IExtensionDefinition[] getResolvedDependencies(IExtensionDefinition definition) {
		// Recompute if required.
		if (dependenciesChanged) recomputeDependencies();
		return resolvedDependencies.get(definition);
	}


	public String[] getUnresolvedDependencies(IExtensionDefinition definition) {
		// Recompute if required.
		if (dependenciesChanged) recomputeDependencies();
		return unresolvedDependencies.get(definition);
	}

	
	public IExtensionDefinition[] getReverseDependencies(IExtensionDefinition definition) {
		// Recompute if required.
		if (dependenciesChanged) recomputeDependencies();
		return reverseDependencies.get(definition);
	}

	
	/*
	 * Recompute all dependencies.
	 */
	protected void recomputeDependencies() {
		
		resolvedDependencies.clear();
		unresolvedDependencies.clear();
		
		HashSet<IExtensionDefinition> resolved = new HashSet<IExtensionDefinition>(); 
		HashSet<IExtensionDefinition> reverse = new HashSet<IExtensionDefinition>(); 
		HashSet<String> unresolved = new HashSet<String>();
		
		// Check all registered definitions.
		for (IExtensionDefinition definition : definitions) {
			
			resolved.clear();
			unresolved.clear();
			
			// Compute dependencies.
			computeDependencies(definition, resolved, unresolved);
			
			resolvedDependencies.put(definition, new IExtensionDefinition[ resolved.size() ]);
			resolved.toArray( resolvedDependencies.get(definition) );

			unresolvedDependencies.put(definition, new String[ unresolved.size() ]);
			unresolved.toArray( unresolvedDependencies.get(definition) );
		}
		
		// Compute reverse dependencies.
		for (IExtensionDefinition definition : definitions) {
			reverse.clear();
			for (IExtensionDefinition current : definitions) {
				if (definition==reverse) continue;
				for (IExtensionDefinition dependency : resolvedDependencies.get(current)) {
					if (dependency==definition) reverse.add(current);
				}
			}
			reverseDependencies.put(definition, new IExtensionDefinition[ reverse.size() ]);
			reverse.toArray( reverseDependencies.get(definition) );
		}
		
		dependenciesChanged = false;
		
	}
	
	
	/*
	 * Compute the resolved and unresolved dependencies of an extension definition.
	 */
	protected void computeDependencies(IExtensionDefinition definition, HashSet<IExtensionDefinition> resolved, HashSet<String> unresolved) {
		
		for (String id : definition.getDependencies()) {
			
			// No self dependencies.
			if (definition.getId().equals(id)) continue;
			IExtensionDefinition dependency = findExtensionDefinition(id);
			
			// Resolved.
			if (dependency!=null && !resolved.contains(dependency)) {
				resolved.add(dependency);
				computeDependencies(dependency, resolved, unresolved);
			}
			
			// Unresolved.
			if (dependency==null) {
				unresolved.add(id);
			}
			
		}
	}
	
		
	
	/* ----- Mutual exclusions. ------ */
	
	
	public IExtensionDefinition[] getMutualExclusions(IExtensionDefinition definition, boolean global) {
		if (exclusionsChanged) recomputeMutualExclusions();
		if (global) return globalExclusions.get(definition);
		else return localExclusions.get(definition);
	}

	
	/*
	 * Recompute the hashmap of mutual exclusions.
	 */
	protected void recomputeMutualExclusions() {
		
		HashSet<IExtensionDefinition> exclusions = new HashSet<IExtensionDefinition>();
		
		for (IExtensionDefinition definition : definitions) {
			
			// Global exclusions.
			exclusions.clear();
			computeMutualExclusions(definition, true, exclusions);
			globalExclusions.put(definition, new IExtensionDefinition[ exclusions.size() ]);
			exclusions.toArray( globalExclusions.get(definition) );

			// Local exclusions.
			exclusions.clear();
			computeMutualExclusions(definition, false, exclusions);
			localExclusions.put(definition, new IExtensionDefinition[ exclusions.size() ]);
			exclusions.toArray( localExclusions.get(definition) );

		}
		exclusionsChanged = false;
		
	}
	

	/*
	 * Compute the list of mutual exclusions of an extension definition.
	 */
	protected void computeMutualExclusions(IExtensionDefinition definition, boolean global, HashSet<IExtensionDefinition> exclusions) {
		
		// Add direct exclusions.
		for (String id : definition.getMutualExclusions(global)) {
			IExtensionDefinition excluded = findExtensionDefinition(id);
			if (excluded!=null && excluded!=definition) exclusions.add(excluded);
		}
		
		// Indirect exclusions.
		for (IExtensionDefinition current : definitions) {
			for (String id : current.getMutualExclusions(global)) {
				if (definition.getId().equals(id)) {
					IExtensionDefinition excluded = findExtensionDefinition(id);
					if (excluded!=null && excluded!=definition) exclusions.add(excluded);					
				}
			}
		}
	}
	
}


