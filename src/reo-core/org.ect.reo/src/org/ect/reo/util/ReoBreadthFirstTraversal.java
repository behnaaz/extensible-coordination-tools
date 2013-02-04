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
package org.ect.reo.util;

import java.util.List;

import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;


/**
 * Breadth-first traversal for Reo connectors.
 * @author Christian Krause
 * @generated NOT
 */
public class ReoBreadthFirstTraversal extends ReoTraversal {
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ReoTraversal#startTraversal(org.ect.reo.Connectable, org.ect.reo.ReoTraversalWorker)
	 */
	@Override
	protected void startTraversal(Connectable element, ReoTraversalWorker worker) {
		
		// Visit the first element.
		visit(element, worker);
		
		if (element instanceof Primitive) {
			breadthFirstPrimitive((Primitive) element, worker);
		} else if (element instanceof Node) { 
			breadthFirstNode((Node) element, worker);			
		} else {
			Reo.logError("Unknown element type: " + element.getClass());
		}

	}
	
	
	protected void breadthFirstPrimitive(Primitive primitive, ReoTraversalWorker worker) {
		
		//  Get all ends of the primitive.
		List<PrimitiveEnd> ends = getSortedEnds(worker, primitive);
		
		// Visit the connected nodes.
		boolean oneVisited = false;
		for (PrimitiveEnd end : ends) {
			Node node = end.getNode();			
			if (node!=null && !shouldStop(node, worker)) {
				boolean success = visit(node, worker);
				oneVisited = oneVisited || success;
			}
			if (isCanceled()) return;
		}
		
		// If no new nodes where visited we stop at this point.
		if (!oneVisited) return;
		
		// Recursion.
		for (PrimitiveEnd end : ends) {
			Node node = end.getNode();			
			if (node!=null) {
				breadthFirstNode(node, worker);
			}
			if (isCanceled()) return;
		}
		
	}
	
	
	protected void breadthFirstNode(Node node, ReoTraversalWorker worker) {
		
		//  Get all ends of the node.
		List<PrimitiveEnd> ends = getSortedEnds(worker, node);
		
		// Visit the connected primitives.
		boolean oneVisited = false;
		for (PrimitiveEnd end : ends) {
			Primitive primitive = end.getPrimitive();			
			if (primitive!=null && !shouldStop(primitive, worker)) {
				boolean success = visit(primitive, worker);
				oneVisited = oneVisited || success;
			}
			if (isCanceled()) return;
		}
		
		// If no new primitives where visited we stop at this point.
		if (!oneVisited) return;
		
		// Recursion.
		for (PrimitiveEnd end : ends) {
			Primitive primitive = end.getPrimitive();			
			if (primitive!=null) {
				breadthFirstPrimitive(primitive, worker);
			}
			if (isCanceled()) return;
		}
		
	}
	
}
