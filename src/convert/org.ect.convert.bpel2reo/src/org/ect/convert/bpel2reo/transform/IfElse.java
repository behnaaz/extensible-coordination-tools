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
import org.ect.convert.bpel2reo.util.Triple;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;

public class IfElse extends ControlFlow implements Translateable {
	
	String name;
	private List<Triple<Node, Node, String>> list = new ArrayList<Triple<Node, Node, String>>();

	public IfElse(String name) {
		this.name = name;
	}

	public void AddPart(Node start, Node end, String condition) {
		list.add(new Triple<Node, Node, String>(start, end, condition));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Pair<Node, Node> transform(Connector connector) {
		sNode = new Node("start:" + name);
		connector.getNodes().add(sNode);
		eNode = new Node("end:" + name);
		connector.getNodes().add(eNode);
		Node bNode = new Node("b");
		connector.getNodes().add(bNode);
		Channel s2b = new FIFO(sNode, bNode);
		connector.getPrimitives().add(s2b);
		Channel b2e = new SyncDrain(bNode, eNode);
		connector.getPrimitives().add(b2e);

		Node pre = null;
		for (Triple<Node, Node, String> curr : list) {
			Node mNode = new Node();
			connector.getNodes().add(mNode);

			Channel m2bd = new Filter();// how to add conditions???????????????
			m2bd.setNodeOne(mNode);
			m2bd.setNodeTwo(curr.getFirst());
			connector.getPrimitives().add(m2bd);

			Channel bd2e = new Sync(curr.getSecond(), eNode);
			connector.getPrimitives().add(bd2e);

			if (pre == null) {
				Channel s2m = new Sync(sNode, mNode);
				connector.getPrimitives().add(s2m);
			} else {
				Channel pre2bdelse = new Filter();// how to add
													// conditions???????????????
				pre2bdelse.setNodeOne(mNode);
				pre2bdelse.setNodeTwo(curr.getFirst());
				connector.getPrimitives().add(pre2bdelse);
			}
			pre = mNode;
		}
		return new Pair<Node, Node>(sNode, eNode);
	}

}
