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

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Property;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.Channel;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.figures.util.ReoFigureSizes;
import org.ect.reo.diagram.providers.ReoViewProvider;



/**
 * This class contains methods for creating views for Reo model elements.
 * All methods for creating views automatically add the views to the resource 
 * of the connector. If no such resource exists, the views
 * cannot be created and the methods will return <code>null</code>.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class ReoViewCreator {
	
	// The view provider.
	private static final ReoViewProvider provider = new ReoViewProvider();

	// Size of the diagram area.
	private final static Size DIAGRAM_AREA = GenericViewUtil.createSize(400, 200);
	

	/**
	 * Create and persist the view for a Reo channel.
	 */
	public static Edge createChannelView(Channel channel, Diagram diagram) {
		
		// Find the diagram first.
		if (diagram==null) {
			diagram = GenericViewUtil.findDiagram(channel);
		}
		if (diagram==null) return null;
		
		EObjectAdapter adapter = new EObjectAdapter(channel, ReoViewUtil.getPrimitiveVisualID(channel));
		int visualId = ReoViewUtil.getPrimitiveVisualID(channel); 
		String semanticHint = ReoViewUtil.getSemanticHint(visualId);
		Edge edge = provider.createEdge(adapter, diagram, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);
		
		// Set the source and target.
		if (channel.getNodeOne()!=null) {
			View source = ReoViewFinder.findNodeView(channel.getNodeOne(), diagram);
			edge.setSource(source);
		}
		if (channel.getNodeTwo()!=null) {
			View target = ReoViewFinder.findNodeView(channel.getNodeTwo(), diagram);
			edge.setTarget(target);			
		}
		
		return edge;
	
	}

	
	/**
	 * Create and persist the node for a component end.
	 */
	public static Node createComponentEndNode(PrimitiveEnd end, Diagram diagram) {
		
		if (!end.isComponentEnd()) return null;
		
		View container = ReoViewFinder.findComponentView((Component) end.getPrimitive(), diagram);
		if (container==null) return null;
		String semanticHint;
		EObjectAdapter adapter;
		
		if (end instanceof SourceEnd) {
			semanticHint = ReoViewUtil.getSemanticHint(SourceEndEditPart.VISUAL_ID);
			adapter = new EObjectAdapter(end, SourceEndEditPart.VISUAL_ID);
		} else {
			semanticHint = ReoViewUtil.getSemanticHint(SinkEndEditPart.VISUAL_ID);
			adapter = new EObjectAdapter(end, SinkEndEditPart.VISUAL_ID);
		}
		return provider.createNode(adapter, container, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);

	}
	
	
	/**
	 * Create and persist the edge for a component end.
	 */
	public static Edge createComponentEndEdge(PrimitiveEnd end, Diagram diagram) {
		
		if (!end.isComponentEnd() || end.getNode()==null) return null;
		
		// Find the diagram first.
		if (diagram==null) {
			diagram = GenericViewUtil.findDiagram(end);
		}
		if (diagram==null) return null;

		Edge edge = null;
		
		if (end instanceof SourceEnd) {
			
			Node source = ReoViewFinder.findNodeView(end.getNode(), diagram);
			Node target = ReoViewFinder.findComponentEndNode(end, diagram);
			if (source==null || target==null) return null;
			
			String semanticHint = ReoViewUtil.getSemanticHint(NodeSourceEndsEditPart.VISUAL_ID);
			EObjectAdapter adapter = new EObjectAdapter(end, NodeSourceEndsEditPart.VISUAL_ID);

			edge = provider.createEdge(adapter, diagram, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);
			edge.setSource(source);
			edge.setTarget(target);
			
		} else if (end instanceof SinkEnd) {
			
			Node source = ReoViewFinder.findComponentEndNode(end, diagram);
			Node target = ReoViewFinder.findNodeView(end.getNode(), diagram);
			if (source==null || target==null) return null;

			String semanticHint = ReoViewUtil.getSemanticHint(SinkEndNodeEditPart.VISUAL_ID);
			EObjectAdapter adapter = new EObjectAdapter(end, SinkEndNodeEditPart.VISUAL_ID);
			
			edge = provider.createEdge(adapter, diagram, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);
			edge.setSource(source);
			edge.setTarget(target);
		}
		
		return edge;
	}

	
	
	/**
	 * Create and persist the view for a component.
	 */
	public static Node createComponentView(Component component, Diagram diagram) {
		
		// Find the container view:
		View container;
		if (component.isInternal()) {
			Connector connector = (Connector) component.eContainer();
			container = ReoViewFinder.findConnectorCompartmentView(connector, diagram);
		} else {
			container = diagram;
		}
		if (container==null) {
			return null;
		}
		
		int visualID = ReoViewUtil.getPrimitiveVisualID(component);
		String semanticHint = ReoViewUtil.getSemanticHint(visualID);
		EObjectAdapter adapter = new EObjectAdapter(component, visualID);
		
		Node result = provider.createNode(adapter, container, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);
		
		for (PrimitiveEnd end : component.getAllEnds()) {
			createComponentEndNode(end, diagram);
			if (end.getNode()!=null) createComponentEndEdge(end, diagram);
		}
		if (result!=null) {
			result.setLayoutConstraint(GenericViewUtil.createRandomLocation(DIAGRAM_AREA));
		}
		return result;
		
	}

	
	/**
	 * Create and persist the view for a connector.
	 */
	public static Node createConnectorView(Connector connector, Diagram diagram) {
		
		View container = GenericViewUtil.findView(connector.eContainer(), null, diagram);
		if (container==null) return null;
		
		String semanticHint = ReoViewUtil.getSemanticHint(ConnectorEditPart.VISUAL_ID);
		EObjectAdapter adapter = new EObjectAdapter(connector, ConnectorEditPart.VISUAL_ID);
		
		Node result = provider.createNode(adapter, container, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);		
		if (result!=null) {
			result.setLayoutConstraint(GenericViewUtil.createRandomLocation(DIAGRAM_AREA));
		}
		return result;

	}

	
	/**
	 * Create and persist the view for a module. This is a diagram instance.
	 */
	public static Diagram createModuleView(Module module) {
		
		if (module.eResource()==null) return null;
		EObjectAdapter adapter = new EObjectAdapter(module, ModuleEditPart.VISUAL_ID);
		
		Diagram diagram = provider.createDiagram(adapter, "Reo", ReoViewUtil.preferencesHint);
		module.eResource().getContents().add(diagram);
		return diagram;
		
	}

	
	/**
	 * Create and persist the view for a Reo node.
	 */
	public static Node createNodeView(org.ect.reo.Node node, Diagram diagram) {
		
		View container = ReoViewFinder.findConnectorCompartmentView(node.getConnector(), diagram);
		if (container==null) return null;
		
		String semanticHint = ReoViewUtil.getSemanticHint(NodeEditPart.VISUAL_ID);
		EObjectAdapter adapter = new EObjectAdapter(node, NodeEditPart.VISUAL_ID);
		
		Node result = provider.createNode(adapter, container, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);
		if (result!=null) {
			result.setLayoutConstraint(GenericViewUtil.createRandomLocation(ReoFigureSizes.CONNECTOR));
		}
		return result;
		
	}
	

	/**
	 * Create view for a primitive.
	 */
	public static View createPrimitiveView(Primitive primitive, Diagram diagram) {
		if (primitive instanceof Channel) {
			return createChannelView((Channel) primitive, diagram);
		} else if (primitive instanceof Component) {
			return createComponentView((Component) primitive, diagram);
		}
		return null;
	}

	/**
	 * Create a view for a property.
	 * @param property Property.
	 * @param diagram Diagram.
	 */
	public static Node createPropertyView(Property property, Diagram diagram) {		
		if (property.eContainer() instanceof Component) {
			View container = ReoViewFinder.findComponentCompartmentView((Component) property.eContainer(), diagram);
			if (container==null) return null;
			
			String semanticHint = ReoViewUtil.getSemanticHint(PropertyEditPart.VISUAL_ID);
			EObjectAdapter adapter = new EObjectAdapter(property, PropertyEditPart.VISUAL_ID);
		
			return provider.createNode(adapter, container, semanticHint, ViewUtil.APPEND, true, ReoViewUtil.preferencesHint);
		}
		return null;
	}
	
}
