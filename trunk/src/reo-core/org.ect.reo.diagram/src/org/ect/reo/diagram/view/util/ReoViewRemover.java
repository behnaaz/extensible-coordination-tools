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
package org.ect.reo.diagram.view.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.channels.Channel;


/**
 * Tools for removing views in Reo diagrams.
 * @author Christian Krause
 * @generated NOT
 */
public class ReoViewRemover {
	
	/**
	 * Remove a connector view from its diagram. Removes channel views as well.
	 * @param connector Connector.
	 */
	@SuppressWarnings("unchecked")
	public static boolean removeConnectorView(Connector connector, Diagram diagram) {
		
		Node view = ReoViewFinder.findConnectorView(connector, diagram);
		if (view==null) return false;
		
		// Remove the view (and all of its children).
		if (diagram==null) diagram = view.getDiagram();
		View container = (View) view.eContainer();
		container.getPersistedChildren().remove(view);
		
		// Check the edges.
		List<Edge> removing = new ArrayList<Edge>();
		for (int i=0; i<diagram.getEdges().size(); i++) {
			Edge edge = (Edge) diagram.getEdges().get(i);
			if (edge.getElement() instanceof Primitive) {
				Primitive p = (Primitive) edge.getElement();
				if (connector.getPrimitives().contains(p)) {
					removing.add(edge);
				}
				for (PrimitiveEnd end : p.getAllEnds()) {
					if (connector.getNodes().contains(end.getNode())) {
						removing.add(edge);
						break;
					}
				}
			}
		}
		
		// Remove the edges.
		diagram.getEdges().removeAll(removing);
		return true;
		
	}
	
	/**
	 * Remove a primitive view.
	 * @param primitive Primitive.
	 */
	public static boolean removePrimitiveView(Primitive primitive, Diagram diagram) {
		if (primitive instanceof Channel) {
			Edge edge = ReoViewFinder.findChannelView((Channel) primitive, diagram);
			if (edge==null) return false;
			GenericViewUtil.removeView(edge);
		}
		else if (primitive instanceof Component) {
			View view = ReoViewFinder.findComponentView((Component) primitive, diagram);
			if (view==null) return false; 
			GenericViewUtil.removeView(view);
		}
		return true;
	}
	
	/**
	 * Remove a node view.
	 * @param node Reo node.
	 */
	public static boolean removeNodeView(org.ect.reo.Node node, Diagram diagram) {
		View view = ReoViewFinder.findNodeView(node, diagram);
		if (view==null) return false;
		GenericViewUtil.removeView(view);
		return true;
	}

}
