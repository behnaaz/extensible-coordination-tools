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
package org.ect.ea.extensions.startstates;

import java.util.List;
import java.util.Vector;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.ect.ea.extensions.startstates.edit.FakeTransitionEditPart;
import org.ect.ea.extensions.startstates.edit.InvisibleNodeEditPart;

public class FakeTransitionViewFactory {
	
	public static Edge findFakeTransition(Node stateNode) {

		// Iterate all edges.
		for (int i=0; i<stateNode.getTargetEdges().size(); i++) {
			Edge current = (Edge) stateNode.getTargetEdges().get(i);
			if (current.getType().equals(FakeTransitionEditPart.VISUAL_ID)) {
				return current;
			}
		}

		// Not found.
		return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static Edge createFakeTransition(Node source, Node target) {
		
		// Create the edge.
		Edge edge = NotationFactory.eINSTANCE.createEdge();
		edge.setType(FakeTransitionEditPart.VISUAL_ID);
		edge.setSource(source);
		edge.setTarget(target);

		// Set smoothness value.
		RoutingStyle routingStyle = NotationFactory.eINSTANCE.createRoutingStyle();
		routingStyle.setSmoothness(Smoothness.NORMAL_LITERAL);
		edge.getStyles().add(routingStyle);
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());

		// Add bendpoints.
		List<RelativeBendpoint> points = new Vector<RelativeBendpoint>(3);
		points.add(new RelativeBendpoint(1, 3, -33, -31));
		points.add(new RelativeBendpoint(7, 20, -27, -14));
		points.add(new RelativeBendpoint(27, 30, -7, -4));
		
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE.createRelativeBendpoints();
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		
		IdentityAnchor anchor = NotationFactory.eINSTANCE.createIdentityAnchor();
		anchor.setId("(0.175,0.55)");
		edge.setTargetAnchor(anchor);
		
		return edge;
		
	}
	
	
	
	public static Node createInvisibleNode(Node stateNode) {
		
		// Create the new node.
		Node node = NotationFactory.eINSTANCE.createNode();
		Location location = NotationFactory.eINSTANCE.createBounds();
		Location stateLoc = (Location) stateNode.getLayoutConstraint();
		
		// Set the location.
		location.setX(stateLoc.getX() - 30);
		location.setY(stateLoc.getY() - 15);
		
		node.setLayoutConstraint(location);
		node.setType(InvisibleNodeEditPart.VISUAL_ID);
		
		return node;
		
	}


}
