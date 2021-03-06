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

import org.ect.ea.automata.Automaton;

/**
 * Interface for automaton type registries.
 * @generated NOT
 */
public interface IAutomatonTypeRegistry {
	
	/**
	 * Get all registered automaton types.
	 */
	public IAutomatonType[] getAutomatonTypes();

	/**
	 * Get the type of an automaton. If no matching type
	 * was found it returns <code>null</code>.
	 */
	public IAutomatonType getAutomatonType(Automaton automaton);

	/**
	 * Set the type of an automaton. 
	 */
	public void setAutomatonType(Automaton automaton, IAutomatonType type);
	
}