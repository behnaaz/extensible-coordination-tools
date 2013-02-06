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
import org.ect.reo.channels.ChannelsFactory;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class provides the structure for a Producer circuit. A producer circuit
 * iteratively emits the same message.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class Producer extends SuperConnector {
	
	Node source;
	
	public Producer(String msgProduced) {
		super("Producer");
		source=new Node();
		source.setName("Exit");
		addNode(source);
		
		Node one=new Node();
		addNode(one);
		Node two=new Node();
		addNode(two);
		
		Filter filt=(new ChannelsFactory()).createFilter();
		filt.setExpression(msgProduced);
		filt.setNodeOne(two);
		filt.setNodeTwo(source);
		
		addPrimitive(filt);
		addPrimitive(new FIFO(two,one));
		FIFO c=new FIFO(one,two);
		c.setFull(true);
		addPrimitive(c);
	}

	public Node getSourceNode() {
		return source;
	}
	
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Reo.initStandalone();

		
		Producer seq=new Producer("messaggio");
		try {
			Module mod=Reo.createModule("producer.reo");
			mod.getConnectors().add(seq.representedConnector);
			Reo.saveModule(mod);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Zaza blabla");
		}		
	}*/
}
