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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.codegen.reo2ea.core.TransformationException;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Predicate;
import org.ect.reo.DataAware;
import org.ect.reo.channels.Channel;

public class FilterTransform extends DataAwareHelper {

	@Override
	public Automaton transform(Channel channel) throws TransformationException {
		assert channel instanceof DataAware;	
		Automaton automaton = super.transform(channel);

		Predicate pred = (Predicate) mapNames(channel);
		Predicate negated = (Predicate) EcoreUtil.copy(pred);
		negated.negate();

		for (Transition t : automaton.getTransitions()) 
			if (CA.getPortNames(t).getValues().size()==1)
				CA.setConstraint(t, negated);
			else if (CA.getPortNames(t).getValues().size()==2) {
				Conjunction con = new Conjunction();
				con.getMembers().add(pred);
				con.getMembers().add(CA.getConstraint(t));
				CA.setConstraint(t, con);				
			} 
			
		return automaton;		
	}
}
