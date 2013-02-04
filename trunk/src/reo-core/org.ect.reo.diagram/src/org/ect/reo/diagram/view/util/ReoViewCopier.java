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
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.channels.Channel;


/**
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class ReoViewCopier {
	
	@SuppressWarnings("unchecked")
	public static Copier copyViews(Copier copier, Diagram target) {
		
		Copier viewCopier = new Copier();
		
		// Collect a list of all top-level views.
		Collection<View> views = new ArrayList<View>();
		for (EObject item : copier.keySet()) {
			
			View view = null;
			
			// Only top-level elements and channels/links!
			if ((item instanceof Connector) && ((Connector) item).getParent()==null) {
				view = ReoViewFinder.findConnectorView((Connector) item, null);
				if (view==null) Reo.logError("Cannot find view for " + item + ". No copying performed.");
			}
			else if (item instanceof Component) {
				view = ReoViewFinder.findComponentView((Component) item, null);
				if (view==null) Reo.logError("Cannot find view for " + item + ". No copying performed.");
			}
			else if (item instanceof Channel) {
				view = ReoViewFinder.findChannelView((Channel) item, null);
				if (view==null) Reo.logError("Cannot find view for " + item + ". No copying performed.");
			}
			else if ((item instanceof PrimitiveEnd) && ((PrimitiveEnd) item).isComponentEnd()) {
				view = ReoViewFinder.findComponentEndEdge((PrimitiveEnd) item, null);
				if (view==null) Reo.logError("Cannot find view for " + item + ". No copying performed.");
			}
			
			// Add the view.
			if (view!=null) {
				views.add(view);
			}

			
		}
		
		// Copy the views.
		viewCopier.copyAll(views);
		viewCopier.copyReferences();
		
		// Update the element references.
		for (EObject key : viewCopier.keySet()) {			
			if (key instanceof View) {
				View view = (View) key;
				View copy = (View) viewCopier.get(view);
				EObject element = copier.get(view.getElement());
				copy.setElement(element);
			}
		}
		
		// Add the top-level views to the diagram.
		for (View view : views) {			
			View copy = (View) viewCopier.get(view);
			
			// Add the new view to the target diagram.
			if (copy instanceof Node) {
				target.getPersistedChildren().add(copy);
			} else if (copy instanceof Edge) {
				target.getDiagram().getPersistedEdges().add(copy);
			}
		}
		
		return viewCopier;
	}

	
	
	/**
	 * Copy a layout constraint (position / size) of a connector.
	 * Network view is optional.
	 * @param source Source connector.
	 * @param target Target connector.
	 */
	public static void copyConnectorLayout(Connector source, Connector target, NetworkView networkView) {
		
		Node sourceView = ReoViewFinder.findConnectorView(source, null);
		Node targetView = ReoViewFinder.findConnectorView(target, null);
		
		if (sourceView!=null && targetView!=null) {
			GenericViewUtil.copyLayoutConstraint(sourceView, targetView);
			if (networkView!=null) {
				Location location = (Location) ((Node) targetView).getLayoutConstraint();
				location.setX(location.getX() + networkView.getBounds().getX());
				location.setX(location.getY() + networkView.getBounds().getY());
			}
		}
		
	}

	
	
	/**
	 * Copy a layout constraint (position / size) of a Reo node.
	 * @param source Source node.
	 * @param target Target node.
	 */
	public static void copyNodeLayout(org.ect.reo.Node source, org.ect.reo.Node target) {
		
		Node sourceView = ReoViewFinder.findNodeView(source, null);
		Node targetView = ReoViewFinder.findNodeView(target, null);
		
		if (sourceView!=null && targetView!=null) {
			GenericViewUtil.copyLayoutConstraint(sourceView, targetView);
		}
		
	}

	
	
	/**
	 * Copy a layout constraint of a Reo primitive. This can be positions / sizes
	 * of components or bendpoints of the edges. The network view is optional. 
	 * @param source Source primitive.
	 * @param target Target primitive.
	 */
	public static void copyPrimiveLayout(Primitive source, Primitive target, NetworkView networkView) {
		
		View sourceView = ReoViewFinder.findPrimitiveView(source, null);
		View targetView = ReoViewFinder.findPrimitiveView(target, null);
		
		if (sourceView!=null && targetView!=null) {
			GenericViewUtil.copyLayout(sourceView, targetView);
			if (networkView!=null && (sourceView instanceof Node)  && (targetView instanceof Node)) {
				Location location = (Location) ((Node) targetView).getLayoutConstraint();
				location.setX(location.getX() + networkView.getBounds().getX());
				location.setX(location.getY() + networkView.getBounds().getY());
			}
		}
		
		// Copy possible layouts of the source ends.
		for (int i=0; i<source.getSourceEnds().size(); i++) {
			if (i>=target.getSourceEnds().size()) break;
			
			// End nodes.
			sourceView = ReoViewFinder.findComponentEndNode(source.getSourceEnds().get(i), null);
			targetView = ReoViewFinder.findComponentEndNode(target.getSourceEnds().get(i), null);			
			if (sourceView!=null && targetView!=null) {
				GenericViewUtil.copyLayout(sourceView, targetView);
			}
			
			// Links.
			sourceView = ReoViewFinder.findComponentEndEdge(source.getSourceEnds().get(i), null);
			targetView = ReoViewFinder.findComponentEndEdge(target.getSourceEnds().get(i), null);			
			if (sourceView!=null && targetView!=null) {
				GenericViewUtil.copyLayout(sourceView, targetView);
			}

		}
		
		// Copy possible layouts of the sink ends.
		for (int i=0; i<source.getSinkEnds().size(); i++) {
			if (i>=target.getSinkEnds().size()) break;
			
			// End nodes.
			sourceView = ReoViewFinder.findComponentEndNode(source.getSinkEnds().get(i), null);
			targetView = ReoViewFinder.findComponentEndNode(target.getSinkEnds().get(i), null);
			if (sourceView!=null && targetView!=null) {
				GenericViewUtil.copyLayout(sourceView, targetView);
			}
			
			// Links.
			sourceView = ReoViewFinder.findComponentEndEdge(source.getSinkEnds().get(i), null);
			targetView = ReoViewFinder.findComponentEndEdge(target.getSinkEnds().get(i), null);
			if (sourceView!=null && targetView!=null) {
				GenericViewUtil.copyLayout(sourceView, targetView);
			}
		}
	
	}
}

