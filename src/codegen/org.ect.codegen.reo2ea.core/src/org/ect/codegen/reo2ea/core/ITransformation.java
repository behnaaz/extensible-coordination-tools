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
package org.ect.codegen.reo2ea.core;

import org.ect.codegen.reo2ea.transform.EndNamingPolicy;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.reo.Connectable;

public interface ITransformation<T extends Connectable> {
	
	final String EXTENSION_ID = "org.ect.codegen.reo2ea.core.transformation";
	
	final Automaton IDENITIY = new Automaton() {
		{
			getStates().add(new State());
		}
	};

	/**
	 * 
	 * @param endNames a mapping from primitive ends to Strings 
	 */
	void setEndNamingPolicy(EndNamingPolicy endNamingPolicy);
	/**
	 * Transform primitive to automaton
	 * @param connectable Reo node or primitive 
	 * @return
	 */
	Automaton transform(T connectable) throws TransformationException;
}