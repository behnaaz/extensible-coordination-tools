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
package org.ect.convert.umlsd2reo.sdmodel;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class represent an Loop Operator in a Sequence Diagram.
 * 
 * @author Eccher Alessandro
 *
 */
public class SDInterOperatorLoop extends SDInteractionOperator {
	
	SeqDiagram argument;
	String guard;
	
	protected SDInterOperatorLoop(SeqDiagram argument, String guard){
		this.argument=argument;
		this.guard=guard;
	}
	
	public boolean isRelatedTo(SDParticipant participant) {
		
	return argument.getParticipants().contains(argument);
	}

	public SeqDiagram getArgument() {
		return argument;
	}

	public String getGuard() {
		return guard;
	}
}