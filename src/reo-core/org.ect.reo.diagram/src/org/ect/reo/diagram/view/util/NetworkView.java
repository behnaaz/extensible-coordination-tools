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

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.diagram.figures.util.ReoFigureSizes;



/**
 * A view model for networks. This class must be instantiated 
 * for a specific network and manually updated when the network 
 * has changed. This relies on the fact that there are views
 * associated to the network constituents. Otherwise it will not
 * work (and throw exceptions). This class does not inherit from
 * the GMF view class: {@link View}.
 * 
 * @generated NOT
 * @author Christian Krause
 * 
 */
public class NetworkView {
	
	public static final int NETWORK_MARGIN = 8;
	public static final int CONNECTOR_CONTENT_SHIFT_X = 0;
	public static final int CONNECTOR_CONTENT_SHIFT_Y = 24;
	
	// The network to be displayed.
	private Network network;
	
	// Virtual bounds of the network.
	private Bounds bounds;
	
	// Diagram belonging to the network.
	private Set<Diagram> diagrams;
	
	/**
	 * Default constructor.
	 * @param network The network.
	 */
	public NetworkView(Network network) {
		this.network = network;
		this.diagrams = new LinkedHashSet<Diagram>();
		update();
	}
	
	/**
	 * Update the properties of the network.
	 * This does not update the network.
	 */
	public void update() {
		
		// Initialize the bounds.
		if (!network.getConnectors().isEmpty()) {
			Node node = ReoViewFinder.findConnectorView(network.getConnectors().get(0), null);
			bounds = (Bounds) EcoreUtil.copy(node.getLayoutConstraint());
		} else if (!network.getComponents().isEmpty()) {
			Node node = ReoViewFinder.findComponentView(network.getComponents().get(0), null);
			bounds = (Bounds) EcoreUtil.copy(node.getLayoutConstraint());
		} else {
			bounds = NotationFactory.eINSTANCE.createBounds();
		}
		
		// Refine the boundaries.
		for (Connector connector : network.getConnectors()) {
			Node node = ReoViewFinder.findConnectorView(connector, null);
			if (node!=null) updateBounds(node);
		}
		for (Component component : network.getComponents()) {
			Node node = ReoViewFinder.findComponentView(component, null);
			if (node!=null) updateBounds(node);
		}
		
		// Add margin;
		bounds.setWidth(bounds.getWidth()+2*NETWORK_MARGIN);
		bounds.setHeight(bounds.getHeight()+2*NETWORK_MARGIN);		
		
		// Collect diagrams.
		diagrams.clear();
		for (Connector connector : network.getConnectors()) {
			Diagram diagram = GenericViewUtil.findDiagram(connector);
			if (diagram!=null) diagrams.add(diagram);
		}
		for (Component component : network.getComponents()) {
			Diagram diagram = GenericViewUtil.findDiagram(component);
			if (diagram!=null) diagrams.add(diagram);
		}
		
	}
	
	
	/*
	 * Update the upper right and lower left corner of the boundaries.
	 */
	private void updateBounds(Node node) {
		
		// Get the current bounds.
		Bounds current = (Bounds) node.getLayoutConstraint();
		Size size = getNodeSize(node);
		
		// Upper left corner.
		if (current.getX() < bounds.getX()) {
			bounds.setWidth(bounds.getWidth() + bounds.getX() - current.getX());
			bounds.setX(current.getX());
		}
		if (current.getY() < bounds.getY()) {
			bounds.setHeight(bounds.getHeight() + bounds.getY() - current.getY());
			bounds.setY(current.getY());
		}
		
		// Lower right corner.
		if (current.getX()+size.getWidth() > bounds.getX()+bounds.getWidth()) 
			bounds.setWidth(current.getX() - bounds.getX() + size.getWidth());
		if (current.getY()+size.getHeight() > bounds.getY()+bounds.getHeight()) 
			bounds.setHeight(current.getY() - bounds.getY() + size.getHeight());
		
	}
	
	
	/**
	 * Get the size of a node in the network.
	 */
	public Size getNodeSize(Node node) {
		
		// Get the width and height?
		int width = ((Size) node.getLayoutConstraint()).getWidth();
		int height = ((Size) node.getLayoutConstraint()).getHeight();
		
		// Use default width?
		if (width<=0) {
			if (ReoViewUtil.isConnectorView(node)) {
				Connector connector = (Connector) node.getElement();
				width = (connector.getParent()==null) ? ReoFigureSizes.CONNECTOR.getWidth() : ReoFigureSizes.SUBCONNECTOR.getWidth();
			} else if (ReoViewUtil.isReaderView(node)) {
				width = ReoFigureSizes.READER.getWidth();
			} else if (ReoViewUtil.isWriterView(node)) {
				width = ReoFigureSizes.WRITER.getWidth();
			} else if (ReoViewUtil.isComponentView(node)) {
				width = ReoFigureSizes.COMPONENT.getWidth();
			} else if (ReoViewUtil.isNodeView(node)) {
				width = ReoFigureSizes.NODE.getWidth();
			} else if (ReoViewUtil.isComponentEndNode(node)) {
				width = ReoFigureSizes.SOURCE_END.getWidth();
			}
		}

		// Use default height?
		if (height<=0) {
			if (ReoViewUtil.isConnectorView(node)) {
				Connector connector = (Connector) node.getElement();
				height = (connector.getParent()==null) ? ReoFigureSizes.CONNECTOR.getHeight() : ReoFigureSizes.SUBCONNECTOR.getHeight();
			} else if (ReoViewUtil.isReaderView(node)) {
				height = ReoFigureSizes.READER.getHeight();
			} else if (ReoViewUtil.isWriterView(node)) {
				height = ReoFigureSizes.WRITER.getHeight();
			} else if (ReoViewUtil.isComponentView(node)) {
				height = ReoFigureSizes.COMPONENT.getHeight();
			} else if (ReoViewUtil.isNodeView(node)) {
				height = ReoFigureSizes.NODE.getHeight();
			} else if (ReoViewUtil.isComponentEndNode(node)) {
				height = ReoFigureSizes.SOURCE_END.getHeight();
			}
		}

		// Create new size object
		return GenericViewUtil.createSize(width, height);
	}
	
	/**
	 * Get the absolute location of a node in the network view.
	 * This returns a fresh Location instance.
	 */
	public Location getNodeLocation(Node node) {
		
		Location result = (Location) EcoreUtil.copy(node.getLayoutConstraint());
		
		// Check if it is a connector view.
		Connector connector = null;
		Component component = null;
		if (node.getElement() instanceof Connector) {
			connector = (Connector) node.getElement();
		}
		else if (node.getElement() instanceof Component) {
			component = (Component) node.getElement();
		}
		
		// Nested elements?
		boolean isSubconnector = connector!=null && connector.isSubConnector() && ReoViewUtil.isConnectorView(node);
		boolean isInternalComponent = component!=null && component.isInternal() &&
					(ReoViewUtil.isComponentView(node) || ReoViewUtil.isReaderView(node) || ReoViewUtil.isWriterView(node));
		
		// Top-level elements.
		if ((connector!=null && !isSubconnector) || (component!=null && !isInternalComponent)) {
			
			Size size = getNodeSize(node);
			result.setX(result.getX() - bounds.getX() + size.getWidth()/2 + NETWORK_MARGIN);
			result.setY(result.getY() - bounds.getY() + size.getHeight()/2  + NETWORK_MARGIN);
		}
		
		// Nested connector elements.
		else if (isSubconnector || isInternalComponent || ReoViewUtil.isNodeView(node)) {
			
			Node container = (Node) node.eContainer().eContainer();
			Size containerSize = getNodeSize(container);
			Location containerLocation = getNodeLocation(container);
			
			Size size = getNodeSize(node);
			
			int shiftX = containerLocation.getX() - containerSize.getWidth()/2 + size.getWidth()/2 + CONNECTOR_CONTENT_SHIFT_X;
			int shiftY = containerLocation.getY() - containerSize.getHeight()/2 + size.getHeight()/2 + CONNECTOR_CONTENT_SHIFT_Y;
			
			result.setX(result.getX() + shiftX + NETWORK_MARGIN);
			result.setY(result.getY() + shiftY + NETWORK_MARGIN);
		}
		
		// Component end views.
		else if (ReoViewUtil.isComponentEndNode(node)) {
			
			// Check if it is (0,0).
			if (result.getX()==0 && result.getY()==0) {
				// Use default values.
				PrimitiveEnd end = (PrimitiveEnd) node.getElement();
				int index = 0;
				if (end instanceof SourceEnd) {
					index = end.getPrimitive().getSourceEnds().indexOf(end);
					result.setX(-ReoFigureSizes.SOURCE_END.getWidth()/2);
				} else if (end instanceof SinkEnd) {
					index = end.getPrimitive().getSinkEnds().indexOf(end);
					Size size = getNodeSize((Node) node.eContainer());
					result.setX(size.getWidth() -ReoFigureSizes.SOURCE_END.getWidth()/2);					
				}
				result.setY(index * (ReoFigureSizes.SOURCE_END.getHeight()+8) + 26);					
			}
			
			Node container = (Node) node.eContainer();
			Size containerSize = getNodeSize(container);
			Location containerLocation = getNodeLocation(container);
			
			Size size = getNodeSize(node);
			
			int shiftX = containerLocation.getX() - containerSize.getWidth()/2 + size.getWidth()/2;
			int shiftY = containerLocation.getY() - containerSize.getHeight()/2 + size.getHeight()/2;
			
			result.setX(result.getX() + shiftX);
			result.setY(result.getY() + shiftY);

		}
		
		return result;
	}
	
	
	/**
	 * Get the network of this view.
	 */
	public Network getNetwork() {
		return network;
	}
	
	/**
	 * Get the diagrams belonging to this NetworkView.
	 */
	public Set<Diagram> getDiagrams() {
		return diagrams;
	}
	
	/**
	 * Get the bounds (location and size) of this network.
	 */
	public Bounds getBounds() {
		return bounds;
	}
	
	
	/**
	 * Move this network view.
	 */
	public void move(int x, int y) {
		
		// Move connectors and components.
		for (Connector connector : network.getConnectors()) {
			Node node = ReoViewFinder.findConnectorView(connector, null);
			GenericViewUtil.move(node, x, y);
		}
		for (Component component : network.getComponents()) {
			Node node = ReoViewFinder.findComponentView(component, null);
			GenericViewUtil.move(node, x, y);
		}
		
		// Update bounds.
		bounds.setX(bounds.getX() + x);
		bounds.setY(bounds.getY() + y);		
		
	}
		
}
