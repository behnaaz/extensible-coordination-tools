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
import org.ect.reo.channels.*;

public class Copy extends ControlFlow implements Translateable {
	
	String fromVar, toVar;
	Component copyCircuit;
	
	public Copy(String fromVariable, String toVariable) {
		fromVar = fromVariable;
		toVar = toVariable;
		copyCircuit = ComponentLoader.LoadComponent(ComponentName.Copy);
		copyCircuit.setName("Copy "+fromVariable+" to "+toVariable);
	}

	public Component getCircuit() {
		return copyCircuit;
	}

	public SourceEnd getFrom() {
		return (SourceEnd) PrimitivesManager.getEnd(copyCircuit, "from");
	}

	public SinkEnd getTo() {
		return (SinkEnd) PrimitivesManager.getEnd(copyCircuit, "to");
	}

	
	public Pair<Node, Node> transform(Connector connector) {
		Variable varInput = new Variable(fromVar);
		Variable varOutput = new Variable(toVar);
		Node readNode, writeNode, fromNode, toNode;

		Channel filterFrom = new Filter();
		writeNode = new Node("write:"+varInput.getName());
		varInput.getWrite().setNode(writeNode);
		fromNode = new Node("from");
		filterFrom.setNodeOne(writeNode);
		filterFrom.setNodeTwo(fromNode);

		Channel filterTo = new Filter();
		readNode = new Node("read:"+varOutput.getName());
		varOutput.getRead().setNode(readNode);
		toNode = new Node("to");		
		filterTo.setNodeOne(toNode);
		filterTo.setNodeTwo(readNode);
		
		getFrom().setNode(fromNode);
		getTo().setNode(toNode);
		
		sNode = new Node("s "+getName());
		PrimitivesManager.getEnd(copyCircuit, "s").setNode(sNode);
		eNode = new Node("e "+getName());
		PrimitivesManager.getEnd(copyCircuit, "e").setNode(eNode);

		connector.getPrimitives().add(varInput.getComponent());
		connector.getPrimitives().add(varOutput.getComponent());
		connector.getPrimitives().add(copyCircuit);
		connector.getNodes().add(readNode);
		connector.getNodes().add(writeNode);
		connector.getNodes().add(fromNode);
		connector.getNodes().add(toNode);
		connector.getNodes().add(sNode);
		connector.getNodes().add(eNode);
		connector.getPrimitives().add(filterFrom);
		connector.getPrimitives().add(filterTo);		
		return new Pair<Node, Node>(sNode, eNode);
	}

	@Override
	public String getName() {
		return copyCircuit.getName();
	}
}