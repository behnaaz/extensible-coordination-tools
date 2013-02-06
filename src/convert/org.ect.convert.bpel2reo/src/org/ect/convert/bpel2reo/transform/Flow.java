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
import java.util.logging.Level;

import org.ect.convert.bpel2reo.Activator;
import org.ect.convert.bpel2reo.util.Pair;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;

public class Flow extends ControlFlow implements Translateable {
	private List<Pair<Node, Node>> list = new ArrayList<Pair<Node, Node>>();
	private String flowName;
	
	public Flow(String name) {
		flowName = name;
		Activator.logger.log(Level.INFO, "Parsing: A Flow instance named '"+ flowName + "' generated.");
	}
	
	public void AddPart(Pair<Node, Node> startend) {
		list.add(startend);
		Activator.logger.log(Level.INFO, "Parsing: A pair added to Flow named '"+ flowName + "' < " + startend.getFirst().getName() + ", " + startend.getSecond().getName() + ">");
	}
	
	@Override
	public String getName() {
		return flowName;
	}

	@Override
	public Pair<Node, Node> transform(Connector connector) {
		Node bNode = new Node("b " + flowName);
		sNode = new Node("start " + flowName);
		eNode = new Node("end " + flowName);
		Node mNode = new Node("mid " + flowName);
		connector.getNodes().add(sNode);
		connector.getNodes().add(eNode);
		connector.getNodes().add(bNode);
		connector.getNodes().add(mNode);
		
		Channel s2b = new FIFO(sNode, bNode);
		connector.getPrimitives().add(s2b);
		
		Channel b2m = new SyncDrain(bNode, mNode);
		connector.getPrimitives().add(b2m);
		
		Channel m2e = new Sync(eNode, mNode);
		connector.getPrimitives().add(m2e);
		
		Node lst = null;
		for (Pair<Node, Node> pair : list) {
			Channel s2smid = new FIFO(sNode, pair.getFirst());
			connector.getPrimitives().add(s2smid);
			Node xNode = new Node("x");
			connector.getNodes().add(xNode);
			Channel emid2x = new FIFO(pair.getSecond(), xNode);
			connector.getPrimitives().add(emid2x);
			//Channel x2e = new Sync(xNode, eNode);
			//connector.getPrimitives().add(x2e);
			if (lst != null) {
				Channel chl = new SyncDrain(lst, xNode);
				connector.getPrimitives().add(chl);
			} 
			else {
		/////////////////////?????????????		Channel x2m = new Sync(xNode, mNode);
				///////////connector.getPrimitives().add(x2m);
			}				
		    lst = xNode;
		}
		Channel lst2e = new Sync(lst, eNode);
		connector.getPrimitives().add(lst2e);
			
		Activator.logger.log(Level.INFO, "Transforming: Flow instance named '"+ flowName + "' transformed.");
		return new Pair<Node, Node>(sNode, eNode);
	}
}
