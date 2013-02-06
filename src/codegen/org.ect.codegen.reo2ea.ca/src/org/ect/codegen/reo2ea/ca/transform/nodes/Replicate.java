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
package org.ect.codegen.reo2ea.ca.transform.nodes;

import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.extensions.constraints.Conjunction;
import org.ect.ea.extensions.constraints.Element;
import org.ect.ea.extensions.constraints.ElementType;
import org.ect.ea.extensions.constraints.Equation;
import org.ect.ea.util.ca.CaHelper;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;

public class Replicate implements ITransformation<Node> {
	private EndNamingPolicy endNames;

	public Automaton transform(Node node) {
		if (!(node.getSinkEnds().size()>1))
			return IDENITIY;
		
		CaHelper helper = new CaHelper("MRG "+ node2PortName(node));
		State ss = helper.createState("mrg", true, null);
		
		for (PrimitiveEnd sinkEnd: node.getSinkEnds()) {
			Conjunction con = new Conjunction();
			for (PrimitiveEnd sourceEnd: node.getSourceEnds()) {
				String sinkName = endNames.getName(sinkEnd);
				String sourceName = endNames.getName(sourceEnd);
				con.getMembers().add(new Equation(
						new Element(sourceName, ElementType.IDENTIFIER),
						new Element(sinkName, ElementType.IDENTIFIER)));
				
			}
			helper.createTransition(ss, ss, con);
		}
		
		helper.validatePorts();
		return helper.getAutomaton();
	}

	public void setEndNamingPolicy(EndNamingPolicy endNames) {
		this.endNames = endNames;		
	}
}
