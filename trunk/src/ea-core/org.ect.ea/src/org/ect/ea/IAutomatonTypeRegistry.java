/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
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