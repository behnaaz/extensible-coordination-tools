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
import org.ect.reo.*;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.Sync;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;

public class Receive extends ControlFlow implements Translateable {
	
	String processPartner;
	String outputVar;
	String correlationSet;
	Component recCircuit;
	Node vNode, csNode, ppNode;
	
	public Receive(String processpartner, String outputVariable, String correlationset) {
		processPartner = processpartner;
		outputVar = outputVariable;
		correlationSet = correlationset;
		recCircuit = ComponentLoader.LoadComponent(ComponentName.Receive);
		recCircuit.setName("receive");
	}

	public Pair<Node, Node> transform(Connector connector) {
		vNode = new Node("receive "+outputVar);
		csNode = new Node(correlationSet);
		ppNode = new Node(processPartner);
		
		PrimitivesManager.getEnd(recCircuit, "v").setNode(vNode);
		PrimitivesManager.getEnd(recCircuit, "cs").setNode(csNode);
		PrimitivesManager.getEnd(recCircuit, "pp").setNode(ppNode);
		
		Writer writer = new Writer(ppNode);
		Reader reader = new Reader(csNode);
		Node readNode = new Node(); 
		
		Variable varOutput = new Variable(outputVar);
		varOutput.getRead().setNode(readNode);
		Channel syncVar = new Sync(readNode, vNode);
				
		sNode = new Node("s "+getName());
		PrimitivesManager.getEnd(recCircuit, "s").setNode(sNode);
		eNode = new Node("e "+getName());
		PrimitivesManager.getEnd(recCircuit, "e").setNode(eNode);

		connector.getPrimitives().add(varOutput.getComponent());
		connector.getPrimitives().add(recCircuit);
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
		return recCircuit.getName();
	}

}
