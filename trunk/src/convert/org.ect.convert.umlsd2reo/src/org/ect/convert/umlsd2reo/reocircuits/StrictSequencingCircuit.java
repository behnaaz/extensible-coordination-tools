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

import org.ect.convert.umlsd2reo.sdmodel.SeqDiagram;
import org.ect.reo.Node;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Sync;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * Provides the structure for an Strict Sequencing Execution Circuit.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class StrictSequencingCircuit extends ActivableCircuit {
	
	BaseCircuit member1;
	BaseCircuit member2;
	
	StrictSequencingCircuit (SeqDiagram firstMember, SeqDiagram secondMember) {
		super("Strict Sequencing");
		
		member1=new BaseCircuit(firstMember);
		member2=new BaseCircuit(secondMember);
		
		includeSuperConnector(member1);
		includeSuperConnector(member2);
		
		//now create the structure of this connector
		Node temp=new Node();
		addNode(temp);
		
		FIFO fifo=new FIFO(activationInput,temp);
		addPrimitive(fifo);
		
		Sync sync=new Sync(temp,member1.getActivationInput());		
		addPrimitive(sync);
		
		sync=new Sync(member1.getActivationOutput(),member2.getActivationInput());
		addPrimitive(sync);
		
		temp=new Node();
		addNode(temp);
		
		sync=new Sync(member2.getActivationOutput(),temp);
		addPrimitive(sync);
		
		fifo=new FIFO(temp,activationOutput);
		addPrimitive(fifo);
	}
}
