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
package org.ect.convert.umlsd2reo.reocircuits;

import org.ect.reo.Node;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This Abstract Connector provide the basic structure for an activable
 * circuit. An activable circuit doesn't work until an input is received
 * in his activationInput Node. At the end of the execution, it trows
 * an output from its activationOutput Node.
 * 
 * @author Eccher Alessandro
 *
 */

public abstract class ActivableCircuit extends SuperConnector {
	
	Node activationInput=null;
	Node activationOutput=null;
	
	ActivableCircuit(String name) {
		super(name);
		
		activationInput=new Node("control line input");
		this.addNode(activationInput);
		activationOutput=new Node("control line output");
		this.addNode(activationOutput);
	}

	public Node getActivationInput() {
		return activationInput;
	}

	public Node getActivationOutput() {
		return activationOutput;
	}
	
	
}
