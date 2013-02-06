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
import org.ect.reo.components.Writer;

public class Wait extends ControlFlow implements Translateable {
	
	Component wtCircuit;
	public Wait()
	{
		wtCircuit = ComponentLoader.LoadComponent(ComponentName.Wait);
		wtCircuit.setName("wait");
	}
	
	//@Override
	public Pair<Node, Node> transform(Connector connector) {
		Node cNode = new Node("clock");
		PrimitivesManager.getEnd(wtCircuit, "c").setNode(cNode);
		Writer clock = new Writer(cNode);
		
		sNode = new Node("s "+getName());
		PrimitivesManager.getEnd(wtCircuit, "s").setNode(sNode);
		eNode = new Node("e "+getName());
		PrimitivesManager.getEnd(wtCircuit, "e").setNode(eNode);
		
		connector.getNodes().add(cNode);
		connector.getNodes().add(sNode);
		connector.getNodes().add(eNode);		
		connector.getPrimitives().add(clock);
		return new Pair<Node, Node>(sNode, eNode);
	}

	@Override
	public String getName() {
		return wtCircuit.getName();
	}

}
