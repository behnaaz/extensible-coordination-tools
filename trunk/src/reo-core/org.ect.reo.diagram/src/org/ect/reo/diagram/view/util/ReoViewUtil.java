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

import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Primitive;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.CustomDirectedChannel;
import org.ect.reo.channels.CustomDrainChannel;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.channels.Timer;
import org.ect.reo.channels.Transform;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.diagram.edit.parts.AsyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.AsyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.CustomDrainChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomSpoutChannelEditPart;
import org.ect.reo.diagram.edit.parts.FIFOEditPart;
import org.ect.reo.diagram.edit.parts.FilterEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterEditPart;
import org.ect.reo.diagram.edit.parts.LossySyncEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.ReaderEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorEditPart;
import org.ect.reo.diagram.edit.parts.SyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.SyncEditPart;
import org.ect.reo.diagram.edit.parts.SyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.TimerEditPart;
import org.ect.reo.diagram.edit.parts.TransformEditPart;
import org.ect.reo.diagram.edit.parts.WriterEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;



/**
 * Utilities for handling Reo diagrams. 
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class ReoViewUtil {
	
	// Preference hint for the view creation.
	public static final PreferencesHint preferencesHint;
	
	// Init preference hint.
	static {
		PreferencesHint hint = null;
		try {
			hint = ReoDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		} catch (Throwable t) {
			hint = new PreferencesHint("org.ect.reo.diagram");
		}
		preferencesHint = hint;
	}
	
	/*
	 * Get the semantic hint for a visual id.
	 */
	protected static String getSemanticHint(int visualID) {
		return String.valueOf(visualID);
		//return ReoVisualIDRegistry.getType(visualID);
	}
	
	
	/* ------ Check view type. ------ */
	
	/**
	 * Check if a view is a connector view.
	 */
	public static boolean isConnectorView(View view) {
		return (view instanceof Node) && 
				(getSemanticHint(ConnectorEditPart.VISUAL_ID).equals(view.getType()) ||
				 getSemanticHint(SubConnectorEditPart.VISUAL_ID).equals(view.getType()));
	}
	
	/**
	 * Check if a view is a connector compartment view.
	 */
	public static boolean isConnectorCompartmentView(View view) {
		return (view instanceof Node) && 
				(getSemanticHint(ConnectorCompartmentEditPart.VISUAL_ID).equals(view.getType()) ||
				 getSemanticHint(SubConnectorCompartmentEditPart.VISUAL_ID).equals(view.getType()));
	}
	
	/**
	 * Check if a view is a component view.
	 */
	public static boolean isComponentView(View view) {
		return (view instanceof Node) && 
				(getSemanticHint(ComponentEditPart.VISUAL_ID).equals(view.getType()) ||
				 getSemanticHint(InternalComponentEditPart.VISUAL_ID).equals(view.getType()));
	}
	
	/**
	 * Check if a view is a reader view.
	 */
	public static boolean isReaderView(View view) {
		return (view instanceof Node) && 
				(getSemanticHint(ReaderEditPart.VISUAL_ID).equals(view.getType()) ||
				 getSemanticHint(InternalReaderEditPart.VISUAL_ID).equals(view.getType()));
	}
	
	/**
	 * Check if a view is a writer view.
	 */
	public static boolean isWriterView(View view) {
		return (view instanceof Node) && 
				(getSemanticHint(WriterEditPart.VISUAL_ID).equals(view.getType()) || 
				getSemanticHint(InternalWriterEditPart.VISUAL_ID).equals(view.getType()));
	}
	
	/**
	 * Check if a view is a node view.
	 */
	public static boolean isNodeView(View view) {
		return (view instanceof Node) && getSemanticHint(NodeEditPart.VISUAL_ID).equals(view.getType());
	}
	
	/**
	 * Check if a view is a component end node.
	 */
	public static boolean isComponentEndNode(View view) {
		return (view instanceof Node) && 
			(getSemanticHint(SourceEndEditPart.VISUAL_ID).equals(view.getType()) ||
	 		 getSemanticHint(SinkEndEditPart.VISUAL_ID).equals(view.getType()) );
	}
	
	/**
	 * Get the visual ID for a channel.
	 */
	public static int getChannelVisualID(Channel channel) {
		Class<? extends Channel> type = channel.getClass();
		if (type.equals(Sync.class)) return SyncEditPart.VISUAL_ID;
		if (type.equals(LossySync.class)) return LossySyncEditPart.VISUAL_ID;
		if (type.equals(SyncDrain.class)) return SyncDrainEditPart.VISUAL_ID;
		if (type.equals(SyncSpout.class)) return SyncSpoutEditPart.VISUAL_ID;
		if (type.equals(AsyncDrain.class)) return AsyncDrainEditPart.VISUAL_ID;
		if (type.equals(AsyncSpout.class)) return AsyncSpoutEditPart.VISUAL_ID;
		if (type.equals(Filter.class)) return FilterEditPart.VISUAL_ID;
		if (type.equals(Transform.class)) return TransformEditPart.VISUAL_ID;
		if (type.equals(Timer.class)) return TimerEditPart.VISUAL_ID;
		if (type.equals(FIFO.class)) return FIFOEditPart.VISUAL_ID;		
		if (type.equals(CustomDirectedChannel.class)) return CustomDrainChannelEditPart.VISUAL_ID;		
		if (type.equals(CustomDrainChannel.class)) return CustomDrainChannelEditPart.VISUAL_ID;		
		if (type.equals(CustomSpoutChannel.class)) return CustomSpoutChannelEditPart.VISUAL_ID;		
		return -1;
	}
	
	
	/**
	 * Get the visual ID for a primitive.
	 */
	public static int getPrimitiveVisualID(Primitive primitive) {
		if (primitive instanceof Channel) {
			return getChannelVisualID((Channel) primitive);
		}
		boolean internal = (primitive.eContainer() instanceof Connector);
		if (primitive instanceof Reader) {
			return internal ? InternalReaderEditPart.VISUAL_ID : ReaderEditPart.VISUAL_ID;
		}
		if (primitive instanceof Writer) {
			return internal ? InternalWriterEditPart.VISUAL_ID : WriterEditPart.VISUAL_ID;
		}
		if (primitive instanceof Component) {
			return internal ? InternalComponentEditPart.VISUAL_ID : ComponentEditPart.VISUAL_ID;
		}
		return -1;
	}
	
	
	/**
	 * Create all views for the contents of a module.
	 */
	public static void updateViews(Module module) {
		
		// Create a diagram if necessary.
		Diagram diagram = GenericViewUtil.findDiagram(module);
		if (diagram==null) {
			diagram = ReoViewCreator.createModuleView(module);
		}
				
		// Create views for all of the connectors and nodes.
		for (Connector connector : module.getAllConnectors()) {
			if (ReoViewFinder.findConnectorView(connector, diagram)==null) {
				ReoViewCreator.createConnectorView(connector, diagram);
			}
			// Nodes.
			for (org.ect.reo.Node node : connector.getNodes()) {
				if (ReoViewFinder.findNodeView(node, diagram)==null) {
					ReoViewCreator.createNodeView(node, diagram);
				}
			}
		}
				
		// Create views for all primitives.
		for (Primitive primitive : module.getAllPrimitives()) {
			if (ReoViewFinder.findPrimitiveView(primitive, diagram)==null) {
				ReoViewCreator.createPrimitiveView(primitive, diagram);
			}
		}

	}
	
}


