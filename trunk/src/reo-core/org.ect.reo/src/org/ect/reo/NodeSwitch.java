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
package org.ect.reo;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class NodeSwitch<T> extends ReoSwitch<T> {
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ReoSwitch#caseNode(org.ect.reo.Node)
	 */
	@Override
	public T caseNode(Node node) {
		switch (node.getType().getValue()) {
			case NodeType.REPLICATE_VALUE : return caseReplicateNode(node);
			case NodeType.ROUTE_VALUE : return caseRouteNode(node);
			case NodeType.JOIN_VALUE : return caseJoinNode(node);
			default : return defaultCase(node);
		}
	}
	
	/*
	 * Case for replicate nodes.
	 */
	protected T caseReplicateNode(Node node) {
		return null;
	}
	
	/*
	 * Case for route nodes.
	 */
	protected T caseRouteNode(Node node) {
		return null;
	}
	
	/*
	 * Case for join nodes.
	 */
	protected T caseJoinNode(Node node) {
		return null;
	}

}
