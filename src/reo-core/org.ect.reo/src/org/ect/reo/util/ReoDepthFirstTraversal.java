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

import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;

/**
 * Depth-first traversal for Reo connectors.
 * @author Christian Krause
 * @generated NOT
 */
public class ReoDepthFirstTraversal extends ReoTraversal {
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.ReoTraversal#startTraversal(org.ect.reo.Connectable, org.ect.reo.ReoTraversalWorker)
	 */
	@Override
	protected void startTraversal(Connectable element, ReoTraversalWorker worker) {
		
		if (element instanceof Primitive) {
			depthFirstPrimitive((Primitive) element, worker);
		} else if (element instanceof Node) { 
			depthFirstNode((Node) element, worker);
		} else {
			Reo.logError("Unknown element type: " + element.getClass());
		}
	
	}
	
	
	protected void depthFirstPrimitive(Primitive primitive, ReoTraversalWorker worker) {
		
		// Try to visit the primitive.
		if (!visit(primitive, worker)) return;
		
		// Do the actual traversal.
		for (PrimitiveEnd end : getSortedEnds(worker, primitive)) {
			Node node = end.getNode();
			if (node!=null && !shouldStop(node, worker)) {
				depthFirstNode(node, worker);
			}
			if (isCanceled()) return;
		}
		
	}
	
	
	protected void depthFirstNode(Node node, ReoTraversalWorker worker) {
		
		// Try to visit the node.
		if (!visit(node, worker)) return;
		
		// Do the actual traversal
		for (PrimitiveEnd end : getSortedEnds(worker, node)) {
			Primitive primitive = end.getPrimitive();
			if (primitive!=null && !shouldStop(primitive, worker)) {
				depthFirstPrimitive(primitive, worker);
			}
			if (isCanceled()) return;
		}
		
	}
	
}

