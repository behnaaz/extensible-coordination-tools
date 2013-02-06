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

import java.util.logging.Level;

import org.ect.convert.bpel2reo.Activator;
import org.ect.convert.bpel2reo.util.Pair;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.SyncDrain;

public class Link extends ControlFlow implements Translateable {
	
	//Node source, target;
	//Component linkComponent;
	String linkName;

	public Link(String name, Pair<Node, Node> pair)
	{
	/*    source = pair.getFirst();
		target = pair.getSecond();
		linkComponent = ComponentLoader.LoadComponent(ComponentName.Link);
		linkComponent.setName(name);*/
		sNode =  pair.getFirst();
		eNode = pair.getSecond(); 
		linkName = name;
		Activator.logger.log(Level.INFO, "Parsing: A Link instance named '"+ linkName + "' generated.");
	}
	
	@Override
	public String getName() {
		return linkName;//linkComponent.getName();
	}

	@Override
	public Pair<Node, Node> transform(Connector connector) {
//		sNode = new Node("s");
//		connector.getNodes().add(sNode);
//		PrimitivesManager.getEnd(linkComponent, "s").setNode(sNode);
//		eNode = new Node("e");
//		connector.getNodes().add(eNode);
//		PrimitivesManager.getEnd(linkComponent, "e").setNode(eNode);
//
//		if (target.getSourceEnds().size() == 0)
//		{System.out.print("Nonwellformed link structure");
//		/************	Node mNode = new Node();
//			connector.getNodes().add(mNode);
//			Channel msyn = new Sync(mN);***********/
//		}
//		else if (target.getSourceEnds().size() == 1)
//		{
//			for (Primitive ch : connector.getPrimitives())
//				if (ch instanceof Sync && ch.getSinkEnds().get(0).getNode() == target)
//				{
//					Channel syn = new Sync(ch.getSourceEnds().get(0).getNode(), eNode);
//					connector.getPrimitives().add(syn);
//				}
//		}	
//		else
//			System.out.print("Nonwellformed link structure");
//		
//		
//		
//		if (source.getSourceEnds().size() == 1)
//		{
//			for (Primitive ch : connector.getPrimitives())
//				if (ch instanceof Sync && ch.getSinkEnds().get(0).getNode() == source)
//				{
//					Channel syn = new Sync(ch.getSourceEnds().get(0).getNode(), sNode);
//					connector.getPrimitives().add(syn);
//				}
//		}	
//		else
//			System.out.print("Nonwellformed link structure");
//		
		if (sNode == null)
			{
			Activator.logger.log(Level.SEVERE, " while generating link: '"+ linkName + "' link source is null!");
			return null;
			}
		if (eNode == null)
		{
			Activator.logger.log(Level.SEVERE, " while generating link: '"+ linkName + "' link end is null!");
		return null;
		}

		Node mNode = new Node();
		mNode.setName("Link " + linkName);
		connector.getNodes().add(mNode);
				
		Channel fifo = new FIFO();
		fifo.setNodeOne(sNode);
		fifo.setNodeTwo(mNode);
		connector.getPrimitives().add(fifo);

		Channel syncdrain = new SyncDrain();
		syncdrain.setNodeOne(mNode);
		syncdrain.setNodeTwo(eNode);
		connector.getPrimitives().add(syncdrain);
		
		Activator.logger.log(Level.INFO, "Transforming: Link instance named '"+ linkName + "' transformed ("+ sNode.getName() + " => " + eNode.getName() + ")");
 	    return new Pair<Node, Node>(sNode, eNode);
	}
}