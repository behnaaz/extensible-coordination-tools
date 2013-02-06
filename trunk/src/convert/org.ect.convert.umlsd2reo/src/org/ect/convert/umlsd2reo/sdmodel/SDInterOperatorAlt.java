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
 * This class represent an Alternative Operator in a Sequence Diagram.
 * 
 * @author Eccher Alessandro
 *
 */
public class SDInterOperatorAlt extends SDInteractionOperator {

	SeqDiagram first;
	SeqDiagram second;
	String guard1;
	String guard2;
	
	protected SDInterOperatorAlt(SeqDiagram first, SeqDiagram second,
			String guard1, String guard2) {
		this.first=first;
		this.second=second;
		this.guard1=guard1;
		this.guard2=guard2;		
	}
	
	
	public boolean isRelatedTo(SDParticipant participant) {
		
		if (first.participants.contains(participant) ||
				second.participants.contains(participant))
			return true;
		return false;
	}


	public SeqDiagram getFirst() {
		return first;
	}


	public SeqDiagram getSecond() {
		return second;
	}


	public String getGuard1() {
		return guard1;
	}


	public String getGuard2() {
		return guard2;
	}
}
