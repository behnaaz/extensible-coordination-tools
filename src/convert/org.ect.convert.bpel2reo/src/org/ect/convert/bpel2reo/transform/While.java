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

import org.ect.convert.bpel2reo.util.Pair;
import org.ect.convert.bpel2reo.util.Triple;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;

public class While extends ControlFlow implements Translateable {
	
	Triple<Node, Node, String> data;

	public void AddPart(Node start, Node end, String condition) {
		data = new Triple<Node, Node, String>(start, end, condition);
	}

	@Override
	public String getName() {
		return "while";
	}

	@Override
	public Pair<Node, Node> transform(Connector connector) {
		sNode = new Node("start:while");
		connector.getNodes().add(sNode);
		eNode = new Node("end:while");
		connector.getNodes().add(eNode);
		Node bNode = new Node("b");
		connector.getNodes().add(bNode);
		Node tNode = new Node("t");
		connector.getNodes().add(tNode);
		Node ceNode = new Node("while:cond eval");
		connector.getNodes().add(ceNode);
		Node fNode = new Node("f");
		connector.getNodes().add(fNode);

		Channel s2t = new Sync(sNode, tNode);
		connector.getPrimitives().add(s2t);
		Channel t2b = new FIFO(tNode, bNode);
		connector.getPrimitives().add(t2b);
		Channel b2f = new SyncDrain(bNode, fNode);
		connector.getPrimitives().add(b2f);
		Channel f2e = new Sync(fNode, eNode);
		connector.getPrimitives().add(f2e);
		Channel ce2f = new Sync(ceNode, fNode);
		connector.getPrimitives().add(ce2f);
		Channel ce2sd = new Sync(ceNode, data.getFirst());
		connector.getPrimitives().add(ce2sd);
		Channel ed2ce = new Sync(data.getSecond(), ceNode);
		connector.getPrimitives().add(ed2ce);

		return new Pair<Node, Node>(sNode, eNode);
	}

}
