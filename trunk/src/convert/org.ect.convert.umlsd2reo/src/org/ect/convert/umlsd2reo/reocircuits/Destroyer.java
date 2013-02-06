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
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;

/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This class provides the structure for a destryer circuit. A destroyer circuit
 * absorbs (without storing) every input it receives.
 * 
 * @author Eccher Alessandro
 *
 */ 
public class Destroyer extends SuperConnector {
	
	Node sink;
	
	public Destroyer() {
		super("Destroyer");
		sink=new Node();
		sink.setName("Entrance");
		addNode(sink);
		
		Node inner=new Node();
		addNode(inner);
		
		addPrimitive(new Sync(sink,inner));
		addPrimitive(new SyncDrain(inner,inner));
	}

	public Node getSinkNode() {
		return sink;
	}
	
	/**
	 * @param args
	 /
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Reo.initStandalone();

		
		Destroyer seq=new Destroyer();
		try {
			Module mod=Reo.createModule("destroyer.reo");
			mod.getConnectors().add(seq.representedConnector);
			Reo.saveModule(mod);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Zaza blabla");
		}		
	}*/
}
