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
package org.ect.ea;

/**
 * A registry interface for automata extension definitions.
 * External providers can use such a registry to add their
 * own automata extensions.
 * 
 * WARNING: This has nothing to do with {@link org.eclipse.core.runtime.IExtensionRegistry}.
 * 
 * @author Christian Koehler
 * @generated NOT
 */
public interface IExtensionRegistry {
	
	
	/* ----- Finding extension definitions. ------ */
	
	/**
	 * Get a list of registered extension definitions.
	 * This should not return <code>null</code>.
	 * 
	 * @return List of registered extension definitions.
	 */
	public IExtensionDefinition[] getExtensionDefinitions();

	/**
	 * Find a registered extension definition by its type id.
	 * This iterates all registered definitions and returns
	 * the first one that has the given typeId, or null if
	 * no definition with this type was found.
	 * 
	 * @param id The type id of the extension.
	 * @return The registered extension definition.
	 */
	public IExtensionDefinition findExtensionDefinition(String id);


	/* ----- Registering extension definitions. ------ */

	/**
	 * Register an extension definition. If the definition is already
	 * registered, nothing happens. if there is already another
	 * definition with the same type id, the old one will be overridden 
	 * by the new one.
	 * 
	 * @param definition Extension definition to be registered.
	 */
	public void registerExtensionDefinition(IExtensionDefinition definition);

	/**
	 * Unregister an extension definition. If the definition is not
	 * registered, nothing happens.
	 * 
	 * @param definition Extension definition to be unregistered.
	 */
	public void unregisterExtensionDefinition(IExtensionDefinition definition);
		
	
	/* ----- Dependencies of extension definitions. ------ */
	
	/**
	 * Get a list of all resolved dependencies of an extension definition.
	 * When using a specific extension type it should be always ensured
	 * that all dependencies are activated too and that there are no
	 * unresolved dependencies.
	 * 
	 * @see #getUnresolvedDependencies(IExtensionDefinition)
	 * @param definition The extension definition to be checked.
	 * @return A list of resolved dependencies.
	 */
	public IExtensionDefinition[] getResolvedDependencies(IExtensionDefinition definition);

	/**
	 * Get a list of unresolved dependencies of an extension definition.
	 * If this list is not empty, the parameter extension definition
	 * cannot be used. Since the dependencies cannot be resolved, the
	 * method returns a list of extension ids instead of the extension
	 * definitions. 
	 * 
	 * @param definition The extension definition to be checked.
	 * @return A list of unresolved extension ids.
	 */
	public String[] getUnresolvedDependencies(IExtensionDefinition definition);

	/**
	 * Get a list of extension definitions that depend on the parameter
	 * definition (reverse dependencies).  
	 * @param definition The extension definition to be checked.
	 * @return List of reverse dependencies.
	 */
	public IExtensionDefinition[] getReverseDependencies(IExtensionDefinition definition);

	
	/* ----- Mutual exclusions. ------ */
	
	/**
	 * Get the list of extension definitions that cannot be used together
	 * with the parameter definition. The parameter 'global' determines
	 * whether the mutual exclusions are defined globally for the whole
	 * automaton, or locally for a concrete transition, state etc.
	 * 
	 * @param definition The extension definition to be checked.
	 * @return A list of extension definitions.
	 */
	public IExtensionDefinition[] getMutualExclusions(IExtensionDefinition definition, boolean global);

}


