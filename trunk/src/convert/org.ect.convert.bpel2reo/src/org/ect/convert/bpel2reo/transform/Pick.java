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

import java.util.ArrayList;
import java.util.List;

import org.ect.convert.bpel2reo.util.Pair;
import org.ect.reo.*;
import org.ect.reo.channels.*;

public class Pick extends ControlFlow implements Translateable {
	
	private List<Pair<Node, Node>> list = new ArrayList<Pair<Node, Node>>();

	public void AddPart(Node start, Node end) {
		list.add(new Pair<Node, Node>(start, end));
	}

	@Override
	public String getName() {
		return "pick";
	}

	@Override
	public Pair<Node, Node> transform(Connector connector) {
		Node xorNode = new Node("XOR");
		xorNode.setType(NodeType.ROUTE);
		Node bNode = new Node("b:pick");
		sNode = new Node("start:seq");
		eNode = new Node("end:seq");
		connector.getNodes().add(sNode);
		connector.getNodes().add(eNode);
		connector.getNodes().add(bNode);
		Channel s2b = new FIFO(sNode, bNode);
		connector.getPrimitives().add(s2b);

		Channel b2e = new SyncDrain(bNode, eNode);
		connector.getPrimitives().add(b2e);
		Channel s2xor = new Sync(sNode, xorNode);
		connector.getPrimitives().add(s2xor);

		for (Pair<Node, Node> pair : list) {
			Channel chl = new Sync(xorNode, pair.getFirst());
			connector.getPrimitives().add(chl);
			Channel chnl = new Sync(pair.getSecond(), eNode);
			connector.getPrimitives().add(chnl);
		}

		return new Pair<Node, Node>(sNode, eNode);
	}

}
