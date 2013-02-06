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
import org.ect.reo.*;
import org.ect.reo.channels.*;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * Provides the structure for an Alternative Execution Circuit.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class AlternativeCircuit extends ActivableCircuit {
	
	BaseCircuit member1;
	BaseCircuit member2;
	
	
	AlternativeCircuit (SeqDiagram firstMember, SeqDiagram secondMember,
			String condExpression1, String condExpression2) {		
		super("Alternative");
		
		ChannelsFactory fact=new ChannelsFactory();
		
		member1=new BaseCircuit(firstMember);
		member2=new BaseCircuit(secondMember);
		
		includeSuperConnector(member1);
		includeSuperConnector(member2);
		
		//now create the structure of this connector
		Node temp=new Node();
		addNode(temp);
		
		FIFO fifo=new FIFO(activationInput,temp);
		addPrimitive(fifo);
		
		Node temp2=member1.getActivationInput();
		Filter filt=fact.createFilter();
		filt.setNodeOne(temp);
		filt.setNodeTwo(temp2);
		filt.setExpression(condExpression1);		
		addPrimitive(filt);
		
		temp2=member2.getActivationInput();
		filt=fact.createFilter();
		filt.setNodeOne(temp);
		filt.setNodeTwo(temp2);
		filt.setExpression(condExpression2);		
		addPrimitive(filt);
		
		temp=new Node();
		addNode(temp);
		
		temp2=member1.getActivationOutput();
		Sync sync=new Sync(temp2,temp);		
		addPrimitive(sync);
		
		temp2=member2.getActivationOutput();
		sync=new Sync(temp2,temp);		
		addPrimitive(sync);
		
		fifo=new FIFO(temp,activationOutput);
		addPrimitive(fifo);
	}
}
