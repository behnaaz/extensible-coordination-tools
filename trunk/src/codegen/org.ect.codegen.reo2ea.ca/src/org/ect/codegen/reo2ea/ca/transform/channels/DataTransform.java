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
package org.ect.codegen.reo2ea.ca.transform.channels;

import org.ect.codegen.reo2ea.core.TransformationException;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.constraints.CA;
import org.ect.reo.channels.Channel;

public class DataTransform extends DataAwareHelper {
	@Override
	public Automaton transform(Channel channel) throws TransformationException {
		Automaton automaton = super.transform(channel);
		assert automaton.getTransitions().size()==1;
		Transition t = automaton.getTransitions().get(0);
		CA.setConstraint(t, mapNames(channel));
		
		return automaton;
	}
}
