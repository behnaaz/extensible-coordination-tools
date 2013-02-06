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
import org.ect.reo.channels.*;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class provides the structure for a Guarded Exclusive Router circuit.
 * Depending from what message it contains, the Guarded Exclusive Router 
 * route the input it receives to one or the other of his 2 exits.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class GuardedExclusiveRouter extends SuperConnector {
	
	Node input;
	Node guardedOutput;
	Node unGuardedOutput;
	
	public GuardedExclusiveRouter(String guardExpression) {
		super("Guarded Exclusive Router");
		
		ChannelsFactory channelsFactory=new ChannelsFactory();
		
		input=new Node();
		input.setName("Entry");
		addNode(input);
		
		//building core structure
		Node one=new Node();
		addNode(one);
		Node two=new Node();
		addNode(two);
		
		addPrimitive(new Sync(input,one));
		addPrimitive(new SyncDrain(two,one));
		
		Node temp;
		
		guardedOutput=new Node();
		guardedOutput.setName("Guard Verified Exit");
		addNode(guardedOutput);
		temp=new Node();
		addNode(temp);
		
		Filter filt=channelsFactory.createFilter();
		filt.setExpression("\""+guardExpression+"\"");
		filt.setNodeOne(one);
		filt.setNodeTwo(temp);
		addPrimitive(filt);
		addPrimitive(new Sync(temp,two));
		addPrimitive(new Sync(temp,guardedOutput));
		
		unGuardedOutput=new Node();
		unGuardedOutput.setName("Guard Mismatched Exit");
		addNode(unGuardedOutput);
		temp=new Node();
		addNode(temp);
		
		filt=channelsFactory.createFilter();
		filt.setExpression("NOT \""+guardExpression+"\"");
		filt.setNodeOne(one);
		filt.setNodeTwo(temp);
		addPrimitive(filt);
		addPrimitive(new Sync(temp,two));
		addPrimitive(new Sync(temp,unGuardedOutput));		
		
	}

	public Node getInput() {
		return input;
	}

	public Node getGuardMatchedOutput() {
		return guardedOutput;
	}
	
	public Node getGuardMismatchedOutput() {
		return unGuardedOutput;
	}
}
