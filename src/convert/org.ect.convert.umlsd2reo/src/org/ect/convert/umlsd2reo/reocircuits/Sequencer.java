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
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Sync;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class provides the structure for a Sequencer circuit. A sequencer circuit
 * iteratively emits an output from his several exits but always in the same,
 * specific order (from the first exit, to the last one). 
 * 
 * @author Eccher Alessandro
 *
 */ 
public class Sequencer extends SuperConnector {

	int cardinality;
	Node pins[];
	
	public Sequencer(int cardinality) {
		super("Sequencer");
		this.cardinality=cardinality;
		
		//instantiate output nodes array
		pins=new Node[cardinality];		
		for (int i=0;i<cardinality;i++) {
			pins[i]=new Node();
			pins[i].setName("Exit "+(i+1));
			addNode(pins[i]);
		}
		
		//create internal structure
		Node zip=new Node();
		addNode(zip);
		Node tempNode=new Node();
		addNode(tempNode);
		
		Channel tempChannel=new FIFO(zip,tempNode); 
		((FIFO)tempChannel).setFull(true);
		addPrimitive(tempChannel);
		
		Node prevNode=null;
		
		for (int i=0;i<cardinality-1;i++) {
			tempChannel=new Sync(tempNode,pins[i]);
			addPrimitive(tempChannel);
			
			prevNode=tempNode;
			tempNode=new Node();
			addNode(tempNode);
			
			tempChannel=new FIFO(prevNode,tempNode);
			addPrimitive(tempChannel);
		}
		
		tempChannel=new Sync(tempNode,pins[cardinality-1]);
		addPrimitive(tempChannel);
		tempChannel=new Sync(tempNode,zip);
		addPrimitive(tempChannel);		
	}

	
	public int getCardinality() {
		return cardinality;
	}

	
	public Node getPin(int index) {
		return pins[index-1];
	}
	
	
	
	
	
	
	
	
	
	/**
	 * @param args
	 /
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Reo.initStandalone();

		
		Sequencer seq=new Sequencer(5);
		try {
			Module mod=Reo.createModule("mio_Modulo.reo");
			mod.getConnectors().add(seq.representedConnector);
			Reo.saveModule(mod);
			
		}
		catch (Exception e) {
			System.err.println("Zaza blabla");
		}
		
		
	}
*/
}
