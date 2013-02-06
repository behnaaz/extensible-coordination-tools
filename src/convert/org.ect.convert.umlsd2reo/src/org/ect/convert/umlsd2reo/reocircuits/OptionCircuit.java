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
 * Provides the structure for an Optional Execution Circuit.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class OptionCircuit extends ActivableCircuit {

	BaseCircuit member=null;
	
	OptionCircuit (SeqDiagram memberDiagram) {
		super("Option");
	
		member=new BaseCircuit(memberDiagram);
		
		includeSuperConnector(member);
		
		//now create the structure of this connector
		Node temp=new Node();
		addNode(temp);
		
		FIFO fifo=new FIFO(activationInput,temp);
		addPrimitive(fifo);
		
		ExclusiveRouter router=new ExclusiveRouter(2);
		includeSuperConnector(router);
		
		Sync sync=new Sync(temp,router.getInput());
		addPrimitive(sync);
		
		sync=new Sync(router.getOutput(1),member.getActivationInput());
		addPrimitive(sync);
		
		temp=new Node();
		addNode(temp);
		sync=new Sync(member.getActivationOutput(),temp);
		addPrimitive(sync);
		
		fifo=new FIFO(router.getOutput(2),temp);
		addPrimitive(fifo);
		
		fifo=new FIFO(temp,activationOutput);
		addPrimitive(fifo);
	}
}
