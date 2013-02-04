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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;


/**
 * Generic utilities for handling GMF diagrams.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class GenericViewUtil {

	/**
	 * Find the view associated to a model element.
	 * @param element Model element. Can be null.
	 * @param root Root view.
	 * @param semanticHint Semantic hint of the view. Can be null.
	 * @return The associated view.
	 */
	public static View findView(EObject element, String semanticHint, View root) {
		// Check if the root view is null.
		if (root==null) {
			root = findDiagram(element);
		}
		// Check the root view.
		if ((element==null || element==root.getElement()) && (semanticHint==null || semanticHint.equals(root.getType()))) {
			return root;
		}
		// Check all children (depth-first).
		for (Object child : root.getChildren()) {
			if (child instanceof View) {
				View view = findView(element, semanticHint, (View) child);
				if (view!=null) return view;
			}
		}
		// Not found.
		return null;
	}
	
	
	/**
	 * Find the diagram of an element. This searches in the resource of the element.
	 * @param element Model element.
	 * @return The diagram.
	 */
	public static Diagram findDiagram(EObject element) {
		// Search the diagram in the resource.
		Resource resource = element.eResource();
		if (resource==null) return null;
		for (Object object : resource.getContents()) {
			if (object instanceof Diagram) {
				return (Diagram) object;
			}
		}
		// Not found.
		return null;
	}
	
	
	/**
	 * Find an edge associated to an element.
	 * @param element Model element.
	 * @param semanticHint Semantic hint.
	 * @return The edge if found.
	 */
	public static Edge findEdge(EObject element, String semanticHint, Diagram diagram) {
		if (diagram==null) {
			diagram = findDiagram(element);
		}
		if (diagram==null) return null;
		for (int i=0; i<diagram.getEdges().size(); i++) {
			Edge edge = (Edge) diagram.getEdges().get(i);
			if (edge.getElement()==element &&
				(semanticHint==null || semanticHint.equals(edge.getType()))) {
				return edge;
			}
		}
		// Not found.
		return null;
	}

	
	/**
	 * Find an edge that has no element associated with it.
	 * @param source Element of the source node of the edge.
	 * @param target Element of the target node of the edge.
	 * @param semanticHint Semantic hint.
	 * @return The edge if found.
	 */
	public static Edge findEdge(EObject source, EObject target, String semanticHint, Diagram diagram) {
		if (diagram==null) {
			diagram = findDiagram(source);
		}
		if (diagram==null) return null;
		for (int i=0; i<diagram.getEdges().size(); i++) {
			Edge edge = (Edge) diagram.getEdges().get(i);
			if (edge.getSource().getElement()==source && edge.getTarget().getElement()==target &&
				(semanticHint==null || semanticHint.equals(edge.getType()))) {
				return edge;
			}
		}
		// Not found.
		return null;
	}

	
	/**
	 * Remove a view from its diagram.
	 * @param view View to be removed.
	 */
	@SuppressWarnings("unchecked")
	public static void removeView(View view) {
		
		// Remove all edges.
		Set<Edge> edges = new HashSet<Edge>(); 
		edges.addAll(view.getSourceEdges());
		edges.addAll(view.getTargetEdges());
		for (Edge edge : edges) {
			removeView(edge);
		}
		
		// Remove all children.
		for (int i=0; i<view.getChildren().size(); i++) {
			removeView((View) view.getChildren().get(i));
		}
		
		// Now we can remove the view.
		if (view instanceof Edge) {
			Edge edge = (Edge) view;
			edge.setSource(null);
			edge.setTarget(null);
			edge.getDiagram().getTransientEdges().remove(edge);
			edge.getDiagram().getPersistedEdges().remove(edge);
		}
		else if (view instanceof Node) {
			View container = (View) ((view.eContainer() instanceof View) ? view.eContainer() : null);
			if (container!=null) {
				container.getTransientChildren().remove(view);
				container.getPersistedChildren().remove(view);
			}			
		}
	}
	
	/**
	 * Copy a layout constraint (position / size) from one node to another.
	 * @param source Source node.
	 * @param target Target node.
	 */
	public static void copyLayoutConstraint(Node source, Node target) {
		LayoutConstraint constraint = source.getLayoutConstraint();
		if (constraint!=null) {
			constraint = (LayoutConstraint) EcoreUtil.copy(constraint);
			target.setLayoutConstraint(constraint);
		}
	}
	
	
	/**
	 * Copy the bend points from one edge to another.
	 * @param source Source edge.
	 * @param target Target edge.
	 */
	public static void copyBendpoints(Edge source, Edge target) {
		Bendpoints bendpoints = source.getBendpoints();
		if (bendpoints!=null) {
			bendpoints = (Bendpoints) EcoreUtil.copy(bendpoints);
			target.setBendpoints(bendpoints);
		}
	}
	
	
	/**
	 * Copy the layout information from one view to another.
	 * @param source Source view.
	 * @param target Target view.
	 */
	public static void copyLayout(View source, View target) {
		if (source instanceof Edge && target instanceof Edge) {
			GenericViewUtil.copyBendpoints((Edge) source, (Edge) target);
		}
		else if (source instanceof Node && target instanceof Node) {
			GenericViewUtil.copyLayoutConstraint((Node) source, (Node) target);
		}
	}
	
	
	/**
	 * Helper method for creating a Size object.
	 * @param width Width.
	 * @param height Height.
	 * @return The Size object.
	 */
	public static Size createSize(int width, int height) {
		Size size = NotationFactory.eINSTANCE.createSize();
		size.setWidth(width);
		size.setHeight(height);
		return size;
	}
	
	/**
	 * Create a new random location.
	 */
	public static Bounds createRandomLocation(Size size) {
		Bounds location = NotationFactory.eINSTANCE.createBounds();
		location.setX((int) (Math.random() * size.getWidth()));
		location.setY((int) (Math.random() * size.getHeight()));
		location.setWidth(-1);
		location.setHeight(-1);
		return location;
	}
	
	/**
	 * Move a graphical node using relative coordination.
	 * @param node Node to be moved
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public static void move(Node node, int x, int y) {
		Location location = (Location) node.getLayoutConstraint();
		location.setX(location.getX() + x);
		location.setY(location.getY() + y);		
	}

	/**
	 * Move a graphical node to an absolute coordination.
	 * @param node Node to be moved
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public static void moveTo(Node node, int x, int y) {
		Location location = (Location) node.getLayoutConstraint();
		location.setX(x);
		location.setY(y);		
	}
	
	/**
	 * 'Free' a node.
	 * @param node Node
	 * @param shiftX horizontal shift.
	 * @param shiftY vertical shift.
	 */
	public static void freeNode(Node node, int shiftX, int shiftY) {
		
		View container = (View) node.eContainer();
		if (container==null) return;
		if (!(node.getLayoutConstraint() instanceof Location)) return;
		
		Location location = (Location) node.getLayoutConstraint();
		EList<?> children = container.getChildren();
		for (int i=0; i<children.size(); i++) {
			if (children.get(i)!=node && children.get(i) instanceof Node) {
				Node child = (Node) children.get(i);
				if (child.getLayoutConstraint() instanceof Location) {
					Location loc = (Location) child.getLayoutConstraint();
					if (loc.getX()==location.getX() && loc.getY()==location.getY()) {
						location.setX(location.getX() + shiftX);
						location.setY(location.getY() + shiftY);
						i=-1; continue;
					}
				}
			}
		}
	}

}
