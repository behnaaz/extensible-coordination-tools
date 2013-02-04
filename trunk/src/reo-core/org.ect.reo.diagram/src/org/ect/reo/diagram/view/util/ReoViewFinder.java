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

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Property;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.Channel;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.diagram.edit.parts.ComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;
import org.ect.reo.diagram.edit.parts.ReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ReaderEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorEditPart;
import org.ect.reo.diagram.edit.parts.WriterCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.WriterEditPart;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ReoViewFinder {
		
	/**
	 * Find the associated edge of a component end.
	 */
	public static Edge findComponentEndEdge(PrimitiveEnd end, Diagram diagram) {
		if (end instanceof SourceEnd && end.getNode()!=null) {
			return GenericViewUtil.findEdge(end.getNode(), end, 
					ReoViewUtil.getSemanticHint(NodeSourceEndsEditPart.VISUAL_ID), diagram);			
		}
		if (end instanceof SinkEnd && end.getNode()!=null) {
			return GenericViewUtil.findEdge(end, end.getNode(), 
					ReoViewUtil.getSemanticHint(SinkEndNodeEditPart.VISUAL_ID), diagram);
		}
		return null;
	}

	/**
	 * Find the associated visual node of a component end.
	 */
	public static Node findComponentEndNode(PrimitiveEnd end, Diagram diagram) {
		View container = findComponentView((Component) end.getPrimitive(), diagram);
		if (container==null) return null;
		if (end instanceof SourceEnd) {
			return (Node) GenericViewUtil.findView(end, ReoViewUtil.getSemanticHint(SourceEndEditPart.VISUAL_ID), container);
		}
		if (end instanceof SinkEnd) {
			return (Node) GenericViewUtil.findView(end, ReoViewUtil.getSemanticHint(SinkEndEditPart.VISUAL_ID), container);
		}
		return null;
	}
	

	/**
	 * Find the associated view of a component (not the compartment).
	 */
	public static Node findComponentView(Component component, Diagram diagram) {
		View view = null;
		
		// Reader?
		if (component instanceof Reader) {
			view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(InternalReaderEditPart.VISUAL_ID), diagram);
			if (view==null) view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(ReaderEditPart.VISUAL_ID), diagram);
		}
		
		// Writer?
		else if (component instanceof Writer) {
			view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(InternalWriterEditPart.VISUAL_ID), diagram);
			if (view==null) view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(WriterEditPart.VISUAL_ID), diagram);
		}
		
		// Generic component?
		else {
			view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(InternalComponentEditPart.VISUAL_ID), diagram);
			if (view==null) view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(ComponentEditPart.VISUAL_ID), diagram);			
		}
		return (Node) view;
	}
	
	
	/**
	 * Find the associated compartment view of a component.
	 */
	public static Node findComponentCompartmentView(Component component, Diagram diagram) {
		View view = null;
		
		// Reader?
		if (component instanceof Reader) {
			view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(InternalReaderCompartmentEditPart.VISUAL_ID), diagram);
			if (view==null) view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(ReaderCompartmentEditPart.VISUAL_ID), diagram);
		}
		
		// Writer?
		else if (component instanceof Writer) {
			view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(InternalWriterCompartmentEditPart.VISUAL_ID), diagram);
			if (view==null) view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(WriterCompartmentEditPart.VISUAL_ID), diagram);
		}
		
		// Generic component?
		else {
			view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(InternalComponentCompartmentEditPart.VISUAL_ID), diagram);
			if (view==null) view = GenericViewUtil.findView(component, ReoViewUtil.getSemanticHint(ComponentCompartmentEditPart.VISUAL_ID), diagram);			
		}
		return (Node) view;
	}

	
	/**
	 * Find the associated view of a connector (not the compartment).
	 * The visual ID of the view is either {@value org.ect.reo.diagram.edit.parts.ConnectorEditPart#VISUAL_ID}
	 * or {@value org.ect.reo.diagram.edit.parts.SubConnectorEditPart#VISUAL_ID}
	 */
	public static Node findConnectorView(Connector connector, Diagram diagram) {
		
		// Try to find a top-level view.
		Node node = (Node) GenericViewUtil.findView(connector, 
				ReoViewUtil.getSemanticHint(ConnectorEditPart.VISUAL_ID), diagram);
		
		// Otherwise check for nested view. This could be even the case for top-level connectors!!!
		if (node==null) node = (Node) GenericViewUtil.findView(connector, 
				ReoViewUtil.getSemanticHint(SubConnectorEditPart.VISUAL_ID), diagram);
		
		return node;
	}
	
	
	/**
	 * Find the associated compartment view of a connector. The visual ID of 
	 * the view is either {@value org.ect.reo.diagram.edit.parts.ConnectorCompartmentEditPart#VISUAL_ID}
	 * or {@value org.ect.reo.diagram.edit.parts.SubConnectorCompartmentEditPart#VISUAL_ID}
	 */
	public static Node findConnectorCompartmentView(Connector connector, Diagram diagram) {
		
		// Try to find a top-level view.
		Node node = (Node) GenericViewUtil.findView(connector, 
				ReoViewUtil.getSemanticHint(ConnectorCompartmentEditPart.VISUAL_ID), diagram);
		
		// Otherwise check for nested view. This could be even the case for top-level connectors!!!
		if (node==null) node = (Node) GenericViewUtil.findView(connector, 
				ReoViewUtil.getSemanticHint(SubConnectorCompartmentEditPart.VISUAL_ID), diagram);
		
		return node;
	}
	


	/**
	 * Find the associated view of a Reo node.
	 */
	public static Node findNodeView(org.ect.reo.Node node, Diagram diagram) {
		return (Node) GenericViewUtil.findView(node, 
				ReoViewUtil.getSemanticHint(NodeEditPart.VISUAL_ID), diagram);
	}
	
	/**
	 * Find the edge for a channel.
	 */
	public static Edge findChannelView(Channel channel, Diagram diagram) {
		return GenericViewUtil.findEdge(channel, 
				ReoViewUtil.getSemanticHint(ReoViewUtil.getPrimitiveVisualID(channel)), diagram);
	}
	
	/**
	 * Find the the view for a primitive.
	 */
	public static View findPrimitiveView(Primitive primitive, Diagram diagram) {
		String hint = ReoViewUtil.getSemanticHint(ReoViewUtil.getPrimitiveVisualID(primitive));
		if (primitive instanceof Channel) return GenericViewUtil.findEdge(primitive, hint, diagram);
		if (primitive instanceof Component) return findComponentView((Component) primitive, diagram);
		else return GenericViewUtil.findView(primitive, hint, diagram);
	}
	
	/**
	 * Find a property view.
	 * @param property Property.
	 * @return View.
	 */
	public static Node findPropertyView(Property property, Diagram diagram) {
		return (Node) GenericViewUtil.findView(property, 
				ReoViewUtil.getSemanticHint(PropertyEditPart.VISUAL_ID), diagram);
	}

}
