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

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.ea.automata.Automaton;


/**
 * Interface for automata product definitions.
 * 
 * @generated NOT
 */
public interface IProductProvider {
	
	/**
	 * Compute the product of two automata.
	 * @param a1 First automaton.
	 * @param a2 Second automaton.
	 * @param monitor Progress monitor.
	 * @return The product of the two automata.
	 */
	public Automaton computeProduct(Automaton a1, Automaton a2, IProgressMonitor monitor);
	
	/**
	 * Do optional post processing of the product automaton.
	 * @param automaton Product automaton.
	 * @param monitor Progress monitor.
	 */
	public void doPostProcessing(Automaton automaton, IProgressMonitor monitor);

}
