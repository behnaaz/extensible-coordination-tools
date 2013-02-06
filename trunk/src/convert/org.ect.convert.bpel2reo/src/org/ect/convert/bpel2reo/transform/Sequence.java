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
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;

public class Sequence extends ControlFlow implements Translateable {
	
	private List<Pair<Node, Node>> list = new ArrayList<Pair<Node, Node>>();

	public void AddPart(Node start, Node end) {
		list.add(new Pair<Node, Node>(start, end));
	}

	// @Override
	public Pair<Node, Node> transform(Connector connector) {
		Pair<Node, Node> pre = null;
		Node bNode = new Node("b:seq");
		for (Pair<Node, Node> pair : list) {
			if (pre == null) {
				sNode = new Node("start:seq");
				eNode = new Node("end:seq");
				connector.getNodes().add(sNode);
				connector.getNodes().add(eNode);
				connector.getNodes().add(bNode);
				Channel s2b = new FIFO(sNode, bNode);
				connector.getPrimitives().add(s2b);

				Channel s2s = new Sync(sNode, pair.getFirst());
				connector.getPrimitives().add(s2s);
			} else {
				Channel chl = new Sync(pre.getSecond(), pair.getFirst());
				connector.getPrimitives().add(chl);
			}
			pre = pair;
		}
		if (pre != null) {
			Channel b2e = new SyncDrain(bNode, eNode);
			connector.getPrimitives().add(b2e);
			Channel e2e = new Sync(pre.getSecond(), eNode);
			connector.getPrimitives().add(e2e);
		}
		return new Pair<Node, Node>(sNode, eNode);
	}

	@Override
	public String getName() {
		return "seq";
	}

}
