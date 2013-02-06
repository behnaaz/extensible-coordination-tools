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

import org.ect.reo.*;
import org.ect.reo.channels.*;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class provides the structure for an Exclusive Router circuit. An
 * Exclusive Router route the input it receiver to one and only one of his
 * possible exits.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class ExclusiveRouter extends SuperConnector {

	int cardinality;
	Node input;
	Node[] output;
	
	public ExclusiveRouter(int cardinality) {
		super("Exclusive Router");
		
		input=new Node();
		input.setName("Entry");
		addNode(input);
		this.cardinality=cardinality;
		
		//building core structure
		Node one=new Node();
		addNode(one);
		Node two=new Node();
		addNode(two);
		
		addPrimitive(new Sync(input,one));
		addPrimitive(new SyncDrain(two,one));
		
		Node temp;
		
		//generate output nodes array and structure
		output=new Node[cardinality];		
		for (int i=0;i<cardinality;i++) {
			output[i]=new Node();
			output[i].setName("Exit "+(i+1));
			addNode(output[i]);
			
			temp=new Node();
			addNode(temp);
			addPrimitive(new LossySync(one,temp));
			addPrimitive(new Sync(temp,two));
			addPrimitive(new Sync(temp,output[i]));
		}		
	}

	public Node getInput() {
		return input;
	}

	public Node getOutput(int index) {
		return output[index-1];
	}

	public int getCardinality() {
		return cardinality;
	}
	
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Reo.initStandalone();
		
		ExclusiveRouter seq=new ExclusiveRouter(4);
		try {
			Module mod=Reo.createModule("exclusiveRouter.reo");
			mod.getConnectors().add(seq.representedConnector);
			Reo.saveModule(mod);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Zaza blabla");
		}		
	}*/
}
