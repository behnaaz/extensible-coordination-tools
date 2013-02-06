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
package org.ect.convert.bpel2reo.transform;

import org.ect.convert.bpel2reo.transform.manager.ComponentLoader;
import org.ect.convert.bpel2reo.util.Pair;
import org.ect.convert.bpel2reo.util.PrimitivesManager;
import org.ect.convert.bpel2reo.util.PrimitivesManager.ComponentName;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.Sync;
import org.ect.reo.components.Reader;

public class Invoke extends ControlFlow implements Translateable {
	
	String processPartner;
	String inputVar;
	String correlationSet;
	Component invCircuit;
	
	public Invoke(String processpartner, String inputVariable, String correlationset) {
		processPartner = processpartner;
		inputVar = inputVariable;
		correlationSet = correlationset;
		invCircuit = ComponentLoader.LoadComponent(ComponentName.Invoke);
		invCircuit.setName("invoke");
	}
	
	//@Override
	public Pair<Node, Node> transform(Connector connector) {
		Node vNode = new Node("invoke "+inputVar);
		Node csNode = new Node(correlationSet);
		Node ppNode = new Node(processPartner);
		
		PrimitivesManager.getEnd(invCircuit, "v").setNode(vNode);
		PrimitivesManager.getEnd(invCircuit, "cs").setNode(csNode);
		PrimitivesManager.getEnd(invCircuit, "pp").setNode(ppNode);
		
		Reader writer = new Reader(ppNode);
		Reader reader = new Reader(csNode);
		Node readNode = new Node(); 
		
		Variable varInput = new Variable(inputVar);
		varInput.getRead().setNode(readNode);
		Channel syncVar = new Sync(readNode, vNode);
				
		sNode = new Node("s "+getName());
		PrimitivesManager.getEnd(invCircuit, "s").setNode(sNode);
		eNode = new Node("e "+getName());
		PrimitivesManager.getEnd(invCircuit, "e").setNode(eNode);

		connector.getPrimitives().add(varInput.getComponent());
		connector.getPrimitives().add(invCircuit);
		connector.getNodes().add(readNode);
		connector.getNodes().add(vNode);
		connector.getNodes().add(csNode);
		connector.getNodes().add(ppNode);
		connector.getNodes().add(sNode);
		connector.getNodes().add(eNode);
		connector.getPrimitives().add(syncVar);
		connector.getPrimitives().add(writer);
		connector.getPrimitives().add(reader);
		return new Pair<Node, Node>(sNode, eNode);
	}

	@Override
	public String getName() {
		return invCircuit.getName();
	}

}
