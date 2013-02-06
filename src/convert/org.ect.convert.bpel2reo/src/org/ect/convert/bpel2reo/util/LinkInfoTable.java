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
package org.ect.convert.bpel2reo.util;

import org.ect.reo.Node;

public class LinkInfoTable {
	
	String linkName, type, transCondition;
	Node startNode, endNode;
	
	public LinkInfoTable(String linkName, String type, String transCondition,Node startNode, Node endNode) {
		this.linkName = linkName; 
		this.type = type; 
		this.transCondition = transCondition;
		this.startNode = startNode;
		this.endNode = endNode;
	}

	public String getLinkName()	{return linkName;}
	public String getType()	{return type;}
	public String getTransitionCondition()	{return transCondition;}
	public Node getStartNode()	{return startNode;}
	public Node getEndNode()	{return endNode;}
}
