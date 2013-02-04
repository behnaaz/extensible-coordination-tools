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
package org.ect.reo.animation.flash;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Reo;
import org.ect.reo.channels.FIFO;
import org.ect.reo.diagram.view.util.ReoViewUtil;


public class ReoAnimationTest {
	
	
	public static Network createNetwork() throws IOException {
		
		Connector connector = new Connector("Test");
		
		Node A = new Node("A");
		Node B = new Node("B");
		connector.getNodes().add(A);
		connector.getNodes().add(B);
		
		FIFO fifo = new FIFO(A,B);
		connector.getPrimitives().add(fifo);
		
//		Writer w = new Writer(A); 
//		Reader r = new Reader(B);
				
		Network network = new Network(connector);
		
		// Create the reo file, add the contents and create the views.
		Module module = Reo.createModule(URI.createFileURI("test.reo"));
		module.addNetwork(network);				
		ReoViewUtil.updateViews(module);
		
		
		// Save everything.
		Reo.saveModule(module);
		
		return network;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
			
//			int id = FullFIFOEditPart.VISUAL_ID;
			
//			Network network = createNetwork();
			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}
