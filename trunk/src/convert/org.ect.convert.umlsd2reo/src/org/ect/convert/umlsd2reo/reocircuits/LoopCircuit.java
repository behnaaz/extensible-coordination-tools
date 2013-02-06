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

import org.ect.convert.umlsd2reo.sdmodel.*;
import org.ect.reo.*;
import org.ect.reo.channels.*;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * Provides the structure for a Looping Execution Circuit.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class LoopCircuit extends ActivableCircuit {

	BaseCircuit member;
	
	LoopCircuit(SeqDiagram memberDiagram, String guardCondition){
		super("Loop");
		
		this.member=new BaseCircuit(memberDiagram);
		includeSuperConnector(member);
		
		//now we create the circuit structure
		Node temp=new Node();
		addNode(temp);
		
		FIFO fifo=new FIFO(activationInput,temp);
		addPrimitive(fifo);
		
		Sync sync=new Sync(temp,member.getActivationInput());
		addPrimitive(sync);
		
		GuardedExclusiveRouter gExcl=new GuardedExclusiveRouter(guardCondition);
		includeSuperConnector(gExcl);
		
		sync=new Sync(member.activationOutput,gExcl.getInput());
		addPrimitive(sync);
		
		sync=new Sync(gExcl.getGuardMatchedOutput(),temp);
		addPrimitive(sync);
		
		temp=new Node();
		addNode(temp);
		
		sync=new Sync(gExcl.getGuardMismatchedOutput(),temp);
		addPrimitive(sync);
		
		fifo=new FIFO(temp,activationOutput);
		addPrimitive(fifo);
	}
}
