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

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * An interface for automaton types.
 * @author Christian Krause
 * @generated NOT
 */
public interface IAutomatonType {
	
	/**
	 * Get the name of this automaton type.
	 */
	public String getName();

	/**
	 * Get an icon for this automaton type.
	 */
	public ImageDescriptor getIcon();

	/**
	 * Get a list of extension ids that all automata
	 * of this type must have. Note also that the
	 * extension ids must match exactly. If an automaton
	 * uses more extension types than in assumed for this
	 * type, it will not be of this type anymore. 
	 */
	public String[] getExtensionIds();
	
	/**
	 * Get resolved dependencies for this type.
	 */
	public String[] getResolvedDependencies();
	
	/**
	 * Get dependencies for this type that 
	 * could not be resolved.
	 */
	public String[] getUnresolvedDependencies();

}
