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

package org.ect.reo.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.Property;
import org.ect.reo.ReoPackage;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.BlockingSinkSync;
import org.ect.reo.channels.BlockingSourceSync;
import org.ect.reo.channels.BlockingSync;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.channels.CustomDirectedChannel;
import org.ect.reo.channels.CustomDrainChannel;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.channels.Timer;
import org.ect.reo.channels.Transform;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.diagram.edit.parts.AsyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.AsyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSinkSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSourceSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSyncEditPart;
import org.ect.reo.diagram.edit.parts.ComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.CustomDirectedChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomDrainChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomSpoutChannelEditPart;
import org.ect.reo.diagram.edit.parts.FIFOEditPart;
import org.ect.reo.diagram.edit.parts.FilterEditPart;
import org.ect.reo.diagram.edit.parts.FullFIFOEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterEditPart;
import org.ect.reo.diagram.edit.parts.LossySyncEditPart;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.PrioritySyncEditPart;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;
import org.ect.reo.diagram.edit.parts.ReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ReaderEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorEditPart;
import org.ect.reo.diagram.edit.parts.SyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.SyncEditPart;
import org.ect.reo.diagram.edit.parts.SyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.TimerEditPart;
import org.ect.reo.diagram.edit.parts.TransformEditPart;
import org.ect.reo.diagram.edit.parts.WriterCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.WriterEditPart;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class ReoDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getSemanticChildren(View view) {
		switch (ReoVisualIDRegistry.getVisualID(view)) {
		case ModuleEditPart.VISUAL_ID:
			return getModule_1000SemanticChildren(view);
		case ReaderEditPart.VISUAL_ID:
			return getReader_1002SemanticChildren(view);
		case WriterEditPart.VISUAL_ID:
			return getWriter_1003SemanticChildren(view);
		case ComponentEditPart.VISUAL_ID:
			return getComponent_1004SemanticChildren(view);
		case InternalReaderEditPart.VISUAL_ID:
			return getReader_2008SemanticChildren(view);
		case InternalWriterEditPart.VISUAL_ID:
			return getWriter_2009SemanticChildren(view);
		case InternalComponentEditPart.VISUAL_ID:
			return getComponent_2010SemanticChildren(view);
		case ConnectorCompartmentEditPart.VISUAL_ID:
			return getConnectorConnectorCompartment_5001SemanticChildren(view);
		case SubConnectorCompartmentEditPart.VISUAL_ID:
			return getConnectorConnectorCompartment_5005SemanticChildren(view);
		case InternalReaderCompartmentEditPart.VISUAL_ID:
			return getReaderComponentCompartment_5006SemanticChildren(view);
		case InternalWriterCompartmentEditPart.VISUAL_ID:
			return getWriterComponentCompartment_5007SemanticChildren(view);
		case InternalComponentCompartmentEditPart.VISUAL_ID:
			return getComponentComponentCompartment_5008SemanticChildren(view);
		case ReaderCompartmentEditPart.VISUAL_ID:
			return getReaderComponentCompartment_5002SemanticChildren(view);
		case WriterCompartmentEditPart.VISUAL_ID:
			return getWriterComponentCompartment_5003SemanticChildren(view);
		case ComponentCompartmentEditPart.VISUAL_ID:
			return getComponentComponentCompartment_5004SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getReader_1002SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Reader modelElement = (Reader) view.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getSourceEnds().iterator(); it
				.hasNext();) {
			SourceEnd childElement = (SourceEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SourceEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSinkEnds().iterator(); it
				.hasNext();) {
			SinkEnd childElement = (SinkEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SinkEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getWriter_1003SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Writer modelElement = (Writer) view.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getSourceEnds().iterator(); it
				.hasNext();) {
			SourceEnd childElement = (SourceEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SourceEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSinkEnds().iterator(); it
				.hasNext();) {
			SinkEnd childElement = (SinkEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SinkEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getComponent_1004SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Component modelElement = (Component) view.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getSourceEnds().iterator(); it
				.hasNext();) {
			SourceEnd childElement = (SourceEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SourceEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSinkEnds().iterator(); it
				.hasNext();) {
			SinkEnd childElement = (SinkEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SinkEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getReader_2008SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Reader modelElement = (Reader) view.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getSourceEnds().iterator(); it
				.hasNext();) {
			SourceEnd childElement = (SourceEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SourceEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSinkEnds().iterator(); it
				.hasNext();) {
			SinkEnd childElement = (SinkEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SinkEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getWriter_2009SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Writer modelElement = (Writer) view.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getSourceEnds().iterator(); it
				.hasNext();) {
			SourceEnd childElement = (SourceEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SourceEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSinkEnds().iterator(); it
				.hasNext();) {
			SinkEnd childElement = (SinkEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SinkEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getComponent_2010SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Component modelElement = (Component) view.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getSourceEnds().iterator(); it
				.hasNext();) {
			SourceEnd childElement = (SourceEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SourceEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSinkEnds().iterator(); it
				.hasNext();) {
			SinkEnd childElement = (SinkEnd) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SinkEndEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getConnectorConnectorCompartment_5001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Connector modelElement = (Connector) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNodes().iterator(); it.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NodeEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSubConnectors().iterator(); it
				.hasNext();) {
			Connector childElement = (Connector) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubConnectorEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getPrimitives().iterator(); it
				.hasNext();) {
			Primitive childElement = (Primitive) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == InternalReaderEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InternalWriterEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InternalComponentEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getConnectorConnectorCompartment_5005SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Connector modelElement = (Connector) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNodes().iterator(); it.hasNext();) {
			Node childElement = (Node) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NodeEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getSubConnectors().iterator(); it
				.hasNext();) {
			Connector childElement = (Connector) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == SubConnectorEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getPrimitives().iterator(); it
				.hasNext();) {
			Primitive childElement = (Primitive) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == InternalReaderEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InternalWriterEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InternalComponentEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getReaderComponentCompartment_5006SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Reader modelElement = (Reader) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProperties().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PropertyEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getWriterComponentCompartment_5007SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Writer modelElement = (Writer) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProperties().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PropertyEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getComponentComponentCompartment_5008SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Component modelElement = (Component) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProperties().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PropertyEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getReaderComponentCompartment_5002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Reader modelElement = (Reader) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProperties().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PropertyEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getWriterComponentCompartment_5003SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Writer modelElement = (Writer) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProperties().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PropertyEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getComponentComponentCompartment_5004SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Component modelElement = (Component) containerView.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProperties().iterator(); it
				.hasNext();) {
			Property childElement = (Property) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PropertyEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoNodeDescriptor> getModule_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Module modelElement = (Module) view.getElement();
		LinkedList<ReoNodeDescriptor> result = new LinkedList<ReoNodeDescriptor>();
		for (Iterator<?> it = modelElement.getConnectors().iterator(); it
				.hasNext();) {
			Connector childElement = (Connector) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ConnectorEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getComponents().iterator(); it
				.hasNext();) {
			Component childElement = (Component) it.next();
			int visualID = ReoVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ReaderEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == WriterEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ComponentEditPart.VISUAL_ID) {
				result.add(new ReoNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getContainedLinks(View view) {
		switch (ReoVisualIDRegistry.getVisualID(view)) {
		case ModuleEditPart.VISUAL_ID:
			return getModule_1000ContainedLinks(view);
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_1001ContainedLinks(view);
		case ReaderEditPart.VISUAL_ID:
			return getReader_1002ContainedLinks(view);
		case WriterEditPart.VISUAL_ID:
			return getWriter_1003ContainedLinks(view);
		case ComponentEditPart.VISUAL_ID:
			return getComponent_1004ContainedLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001ContainedLinks(view);
		case SubConnectorEditPart.VISUAL_ID:
			return getConnector_2005ContainedLinks(view);
		case InternalReaderEditPart.VISUAL_ID:
			return getReader_2008ContainedLinks(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2004ContainedLinks(view);
		case SourceEndEditPart.VISUAL_ID:
			return getSourceEnd_2006ContainedLinks(view);
		case SinkEndEditPart.VISUAL_ID:
			return getSinkEnd_2007ContainedLinks(view);
		case InternalWriterEditPart.VISUAL_ID:
			return getWriter_2009ContainedLinks(view);
		case InternalComponentEditPart.VISUAL_ID:
			return getComponent_2010ContainedLinks(view);
		case SyncEditPart.VISUAL_ID:
			return getSync_3001ContainedLinks(view);
		case LossySyncEditPart.VISUAL_ID:
			return getLossySync_3002ContainedLinks(view);
		case FIFOEditPart.VISUAL_ID:
			return getFIFO_3003ContainedLinks(view);
		case SyncDrainEditPart.VISUAL_ID:
			return getSyncDrain_3005ContainedLinks(view);
		case SyncSpoutEditPart.VISUAL_ID:
			return getSyncSpout_3006ContainedLinks(view);
		case AsyncDrainEditPart.VISUAL_ID:
			return getAsyncDrain_3007ContainedLinks(view);
		case AsyncSpoutEditPart.VISUAL_ID:
			return getAsyncSpout_3008ContainedLinks(view);
		case FilterEditPart.VISUAL_ID:
			return getFilter_3011ContainedLinks(view);
		case TransformEditPart.VISUAL_ID:
			return getTransform_3012ContainedLinks(view);
		case TimerEditPart.VISUAL_ID:
			return getTimer_3015ContainedLinks(view);
		case CustomDirectedChannelEditPart.VISUAL_ID:
			return getCustomDirectedChannel_3016ContainedLinks(view);
		case CustomDrainChannelEditPart.VISUAL_ID:
			return getCustomDrainChannel_3017ContainedLinks(view);
		case CustomSpoutChannelEditPart.VISUAL_ID:
			return getCustomSpoutChannel_3018ContainedLinks(view);
		case PrioritySyncEditPart.VISUAL_ID:
			return getPrioritySync_3019ContainedLinks(view);
		case BlockingSyncEditPart.VISUAL_ID:
			return getBlockingSync_3020ContainedLinks(view);
		case BlockingSinkSyncEditPart.VISUAL_ID:
			return getBlockingSinkSync_3021ContainedLinks(view);
		case BlockingSourceSyncEditPart.VISUAL_ID:
			return getBlockingSourceSync_3022ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getIncomingLinks(View view) {
		switch (ReoVisualIDRegistry.getVisualID(view)) {
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_1001IncomingLinks(view);
		case ReaderEditPart.VISUAL_ID:
			return getReader_1002IncomingLinks(view);
		case WriterEditPart.VISUAL_ID:
			return getWriter_1003IncomingLinks(view);
		case ComponentEditPart.VISUAL_ID:
			return getComponent_1004IncomingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001IncomingLinks(view);
		case SubConnectorEditPart.VISUAL_ID:
			return getConnector_2005IncomingLinks(view);
		case InternalReaderEditPart.VISUAL_ID:
			return getReader_2008IncomingLinks(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2004IncomingLinks(view);
		case SourceEndEditPart.VISUAL_ID:
			return getSourceEnd_2006IncomingLinks(view);
		case SinkEndEditPart.VISUAL_ID:
			return getSinkEnd_2007IncomingLinks(view);
		case InternalWriterEditPart.VISUAL_ID:
			return getWriter_2009IncomingLinks(view);
		case InternalComponentEditPart.VISUAL_ID:
			return getComponent_2010IncomingLinks(view);
		case SyncEditPart.VISUAL_ID:
			return getSync_3001IncomingLinks(view);
		case LossySyncEditPart.VISUAL_ID:
			return getLossySync_3002IncomingLinks(view);
		case FIFOEditPart.VISUAL_ID:
			return getFIFO_3003IncomingLinks(view);
		case SyncDrainEditPart.VISUAL_ID:
			return getSyncDrain_3005IncomingLinks(view);
		case SyncSpoutEditPart.VISUAL_ID:
			return getSyncSpout_3006IncomingLinks(view);
		case AsyncDrainEditPart.VISUAL_ID:
			return getAsyncDrain_3007IncomingLinks(view);
		case AsyncSpoutEditPart.VISUAL_ID:
			return getAsyncSpout_3008IncomingLinks(view);
		case FilterEditPart.VISUAL_ID:
			return getFilter_3011IncomingLinks(view);
		case TransformEditPart.VISUAL_ID:
			return getTransform_3012IncomingLinks(view);
		case TimerEditPart.VISUAL_ID:
			return getTimer_3015IncomingLinks(view);
		case CustomDirectedChannelEditPart.VISUAL_ID:
			return getCustomDirectedChannel_3016IncomingLinks(view);
		case CustomDrainChannelEditPart.VISUAL_ID:
			return getCustomDrainChannel_3017IncomingLinks(view);
		case CustomSpoutChannelEditPart.VISUAL_ID:
			return getCustomSpoutChannel_3018IncomingLinks(view);
		case PrioritySyncEditPart.VISUAL_ID:
			return getPrioritySync_3019IncomingLinks(view);
		case BlockingSyncEditPart.VISUAL_ID:
			return getBlockingSync_3020IncomingLinks(view);
		case BlockingSinkSyncEditPart.VISUAL_ID:
			return getBlockingSinkSync_3021IncomingLinks(view);
		case BlockingSourceSyncEditPart.VISUAL_ID:
			return getBlockingSourceSync_3022IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getOutgoingLinks(View view) {
		switch (ReoVisualIDRegistry.getVisualID(view)) {
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_1001OutgoingLinks(view);
		case ReaderEditPart.VISUAL_ID:
			return getReader_1002OutgoingLinks(view);
		case WriterEditPart.VISUAL_ID:
			return getWriter_1003OutgoingLinks(view);
		case ComponentEditPart.VISUAL_ID:
			return getComponent_1004OutgoingLinks(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001OutgoingLinks(view);
		case SubConnectorEditPart.VISUAL_ID:
			return getConnector_2005OutgoingLinks(view);
		case InternalReaderEditPart.VISUAL_ID:
			return getReader_2008OutgoingLinks(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2004OutgoingLinks(view);
		case SourceEndEditPart.VISUAL_ID:
			return getSourceEnd_2006OutgoingLinks(view);
		case SinkEndEditPart.VISUAL_ID:
			return getSinkEnd_2007OutgoingLinks(view);
		case InternalWriterEditPart.VISUAL_ID:
			return getWriter_2009OutgoingLinks(view);
		case InternalComponentEditPart.VISUAL_ID:
			return getComponent_2010OutgoingLinks(view);
		case SyncEditPart.VISUAL_ID:
			return getSync_3001OutgoingLinks(view);
		case LossySyncEditPart.VISUAL_ID:
			return getLossySync_3002OutgoingLinks(view);
		case FIFOEditPart.VISUAL_ID:
			return getFIFO_3003OutgoingLinks(view);
		case SyncDrainEditPart.VISUAL_ID:
			return getSyncDrain_3005OutgoingLinks(view);
		case SyncSpoutEditPart.VISUAL_ID:
			return getSyncSpout_3006OutgoingLinks(view);
		case AsyncDrainEditPart.VISUAL_ID:
			return getAsyncDrain_3007OutgoingLinks(view);
		case AsyncSpoutEditPart.VISUAL_ID:
			return getAsyncSpout_3008OutgoingLinks(view);
		case FilterEditPart.VISUAL_ID:
			return getFilter_3011OutgoingLinks(view);
		case TransformEditPart.VISUAL_ID:
			return getTransform_3012OutgoingLinks(view);
		case TimerEditPart.VISUAL_ID:
			return getTimer_3015OutgoingLinks(view);
		case CustomDirectedChannelEditPart.VISUAL_ID:
			return getCustomDirectedChannel_3016OutgoingLinks(view);
		case CustomDrainChannelEditPart.VISUAL_ID:
			return getCustomDrainChannel_3017OutgoingLinks(view);
		case CustomSpoutChannelEditPart.VISUAL_ID:
			return getCustomSpoutChannel_3018OutgoingLinks(view);
		case PrioritySyncEditPart.VISUAL_ID:
			return getPrioritySync_3019OutgoingLinks(view);
		case BlockingSyncEditPart.VISUAL_ID:
			return getBlockingSync_3020OutgoingLinks(view);
		case BlockingSinkSyncEditPart.VISUAL_ID:
			return getBlockingSinkSync_3021OutgoingLinks(view);
		case BlockingSourceSyncEditPart.VISUAL_ID:
			return getBlockingSourceSync_3022OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getModule_1000ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getConnector_1001ContainedLinks(
			View view) {
		Connector modelElement = (Connector) view.getElement();
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Sync_3001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_LossySync_3002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_FIFO_3003(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_SyncDrain_3005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_SyncSpout_3006(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_AsyncDrain_3007(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_AsyncSpout_3008(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Filter_3011(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Transform_3012(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Timer_3015(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomDirectedChannel_3016(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomDrainChannel_3017(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomSpoutChannel_3018(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_PrioritySync_3019(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_BlockingSyncs_3020(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_BlockingSinkSync_3021(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_BlockingSourceSync_3022(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getReader_1002ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getWriter_1003ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getComponent_1004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getNode_2001ContainedLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_SourceEnds_3013(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getConnector_2005ContainedLinks(
			View view) {
		Connector modelElement = (Connector) view.getElement();
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Sync_3001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_LossySync_3002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_FIFO_3003(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_SyncDrain_3005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_SyncSpout_3006(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_AsyncDrain_3007(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_AsyncSpout_3008(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Filter_3011(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Transform_3012(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Timer_3015(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomDirectedChannel_3016(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomDrainChannel_3017(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CustomSpoutChannel_3018(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_PrioritySync_3019(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_BlockingSyncs_3020(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_BlockingSinkSync_3021(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_BlockingSourceSync_3022(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getReader_2008ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getProperty_2004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSourceEnd_2006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSinkEnd_2007ContainedLinks(
			View view) {
		SinkEnd modelElement = (SinkEnd) view.getElement();
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_SinkEnd_Node_3014(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getWriter_2009ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getComponent_2010ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSync_3001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getLossySync_3002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getFIFO_3003ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSyncDrain_3005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSyncSpout_3006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getAsyncDrain_3007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getAsyncSpout_3008ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getFilter_3011ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getTransform_3012ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getTimer_3015ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomDirectedChannel_3016ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomDrainChannel_3017ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomSpoutChannel_3018ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getPrioritySync_3019ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSync_3020ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSinkSync_3021ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSourceSync_3022ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	
	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getConnector_1001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getReader_1002IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getWriter_1003IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getComponent_1004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getNode_2001IncomingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Sync_3001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_LossySync_3002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_FIFO_3003(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_SyncDrain_3005(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_SyncSpout_3006(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_AsyncDrain_3007(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_AsyncSpout_3008(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Filter_3011(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Transform_3012(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Timer_3015(modelElement,
				crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_SinkEnd_Node_3014(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CustomDirectedChannel_3016(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CustomDrainChannel_3017(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CustomSpoutChannel_3018(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_PrioritySync_3019(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_BlockingSync_3020(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_BlockingSinkSync_3021(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_BlockingSinkSync_3021(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getConnector_2005IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getReader_2008IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getProperty_2004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSourceEnd_2006IncomingLinks(
			View view) {
		SourceEnd modelElement = (SourceEnd) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Node_SourceEnds_3013(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSinkEnd_2007IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getWriter_2009IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getComponent_2010IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSync_3001IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getLossySync_3002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getFIFO_3003IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSyncDrain_3005IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSyncSpout_3006IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getAsyncDrain_3007IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getAsyncSpout_3008IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getFilter_3011IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getTransform_3012IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getTimer_3015IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomDirectedChannel_3016IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomDrainChannel_3017IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomSpoutChannel_3018IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getPrioritySync_3019IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSync_3020IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSinkSync_3021IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSourceSync_3022IncomingLinks(
			View view) {
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getConnector_1001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getReader_1002OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getWriter_1003OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getComponent_1004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getNode_2001OutgoingLinks(View view) {
		Node modelElement = (Node) view.getElement();
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Sync_3001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_LossySync_3002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_FIFO_3003(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_SyncDrain_3005(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_SyncSpout_3006(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_AsyncDrain_3007(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_AsyncSpout_3008(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Filter_3011(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Transform_3012(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Timer_3015(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Node_SourceEnds_3013(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_CustomDirectedChannel_3016(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_CustomDrainChannel_3017(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_CustomSpoutChannel_3018(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_PrioritySync_3019(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_BlockingSync_3020(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_BlockingSinkSync_3021(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_BlockingSourceSync_3022(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getConnector_2005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getReader_2008OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getProperty_2004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSourceEnd_2006OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSinkEnd_2007OutgoingLinks(View view) {
		SinkEnd modelElement = (SinkEnd) view.getElement();
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_SinkEnd_Node_3014(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getWriter_2009OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getComponent_2010OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSync_3001OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getLossySync_3002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getFIFO_3003OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSyncDrain_3005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getSyncSpout_3006OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getAsyncDrain_3007OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getAsyncSpout_3008OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getFilter_3011OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getTransform_3012OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getTimer_3015OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomDirectedChannel_3016OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomDrainChannel_3017OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getCustomSpoutChannel_3018OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getPrioritySync_3019OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSync_3020OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSinkSync_3021OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ReoLinkDescriptor> getBlockingSourceSync_3022OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}


	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_Sync_3001(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Sync) {
				continue;
			}
			Sync link = (Sync) linkObject;
			if (SyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Sync_3001, SyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_LossySync_3002(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof LossySync) {
				continue;
			}
			LossySync link = (LossySync) linkObject;
			if (LossySyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.LossySync_3002, LossySyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_FIFO_3003(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof org.ect.reo.channels.FIFO) {
				continue;
			}
			org.ect.reo.channels.FIFO link = (org.ect.reo.channels.FIFO) linkObject;
			if (FIFOEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.FIFO_3003, FIFOEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_SyncDrain_3005(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SyncDrain) {
				continue;
			}
			SyncDrain link = (SyncDrain) linkObject;
			if (SyncDrainEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.SyncDrain_3005, SyncDrainEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_SyncSpout_3006(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SyncSpout) {
				continue;
			}
			SyncSpout link = (SyncSpout) linkObject;
			if (SyncSpoutEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.SyncSpout_3006, SyncSpoutEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_AsyncDrain_3007(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof AsyncDrain) {
				continue;
			}
			AsyncDrain link = (AsyncDrain) linkObject;
			if (AsyncDrainEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.AsyncDrain_3007,
					AsyncDrainEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_AsyncSpout_3008(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof AsyncSpout) {
				continue;
			}
			AsyncSpout link = (AsyncSpout) linkObject;
			if (AsyncSpoutEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.AsyncSpout_3008,
					AsyncSpoutEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_Filter_3011(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Filter) {
				continue;
			}
			Filter link = (Filter) linkObject;
			if (FilterEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Filter_3011, FilterEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_Transform_3012(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transform) {
				continue;
			}
			Transform link = (Transform) linkObject;
			if (TransformEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Transform_3012, TransformEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_Timer_3015(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Timer) {
				continue;
			}
			Timer link = (Timer) linkObject;
			if (TimerEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Timer_3015, TimerEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_CustomDirectedChannel_3016(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CustomDirectedChannel) {
				continue;
			}
			CustomDirectedChannel link = (CustomDirectedChannel) linkObject;
			if (CustomDirectedChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.CustomDirectedChannel_3016,
					CustomDirectedChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_CustomDrainChannel_3017(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CustomDrainChannel) {
				continue;
			}
			CustomDrainChannel link = (CustomDrainChannel) linkObject;
			if (CustomDrainChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.CustomDrainChannel_3017,
					CustomDrainChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_CustomSpoutChannel_3018(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CustomSpoutChannel) {
				continue;
			}
			CustomSpoutChannel link = (CustomSpoutChannel) linkObject;
			if (CustomSpoutChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.CustomSpoutChannel_3018,
					CustomSpoutChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_PrioritySync_3019(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PrioritySync) {
				continue;
			}
			PrioritySync link = (PrioritySync) linkObject;
			if (PrioritySyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.PrioritySync_3019,
					PrioritySyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_BlockingSyncs_3020(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BlockingSync) {
				continue;
			}
			BlockingSync link = (BlockingSync) linkObject;
			if (BlockingSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.BlockingSync_3020,
					BlockingSyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_BlockingSinkSync_3021(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BlockingSinkSync) {
				continue;
			}
			BlockingSinkSync link = (BlockingSinkSync) linkObject;
			if (BlockingSinkSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.BlockingSinkSync_3021,
					BlockingSinkSyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getContainedTypeModelFacetLinks_BlockingSourceSync_3022(
			Connector container) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BlockingSourceSync) {
				continue;
			}
			BlockingSourceSync link = (BlockingSourceSync) linkObject;
			if (BlockingSourceSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.BlockingSourceSync_3022,
					BlockingSourceSyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_Sync_3001(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof Sync) {
				continue;
			}
			Sync link = (Sync) setting.getEObject();
			if (SyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.Sync_3001, SyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_LossySync_3002(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof LossySync) {
				continue;
			}
			LossySync link = (LossySync) setting.getEObject();
			if (LossySyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.LossySync_3002, LossySyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_FIFO_3003(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof org.ect.reo.channels.FIFO) {
				continue;
			}
			org.ect.reo.channels.FIFO link = (org.ect.reo.channels.FIFO) setting
					.getEObject();
			if (FIFOEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.FIFO_3003, FIFOEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_SyncDrain_3005(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof SyncDrain) {
				continue;
			}
			SyncDrain link = (SyncDrain) setting.getEObject();
			if (SyncDrainEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.SyncDrain_3005, SyncDrainEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_SyncSpout_3006(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof SyncSpout) {
				continue;
			}
			SyncSpout link = (SyncSpout) setting.getEObject();
			if (SyncSpoutEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.SyncSpout_3006, SyncSpoutEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_AsyncDrain_3007(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof AsyncDrain) {
				continue;
			}
			AsyncDrain link = (AsyncDrain) setting.getEObject();
			if (AsyncDrainEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.AsyncDrain_3007,
					AsyncDrainEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_AsyncSpout_3008(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof AsyncSpout) {
				continue;
			}
			AsyncSpout link = (AsyncSpout) setting.getEObject();
			if (AsyncSpoutEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.AsyncSpout_3008,
					AsyncSpoutEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_Filter_3011(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof Filter) {
				continue;
			}
			Filter link = (Filter) setting.getEObject();
			if (FilterEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.Filter_3011, FilterEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_Transform_3012(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof Transform) {
				continue;
			}
			Transform link = (Transform) setting.getEObject();
			if (TransformEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.Transform_3012, TransformEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_Timer_3015(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof Timer) {
				continue;
			}
			Timer link = (Timer) setting.getEObject();
			if (TimerEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.Timer_3015, TimerEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingFeatureModelFacetLinks_Node_SourceEnds_3013(
			SourceEnd target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == ReoPackage.eINSTANCE
					.getNode_SourceEnds()) {
				result.add(new ReoLinkDescriptor(setting.getEObject(), target,
						ReoElementTypes.NodeSourceEnds_3013,
						NodeSourceEndsEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingFeatureModelFacetLinks_SinkEnd_Node_3014(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == ReoPackage.eINSTANCE
					.getSinkEnd_Node()) {
				result.add(new ReoLinkDescriptor(setting.getEObject(), target,
						ReoElementTypes.SinkEndNode_3014,
						SinkEndNodeEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_CustomDirectedChannel_3016(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof CustomDirectedChannel) {
				continue;
			}
			CustomDirectedChannel link = (CustomDirectedChannel) setting
					.getEObject();
			if (CustomDirectedChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.CustomDirectedChannel_3016,
					CustomDirectedChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_CustomDrainChannel_3017(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof CustomDrainChannel) {
				continue;
			}
			CustomDrainChannel link = (CustomDrainChannel) setting.getEObject();
			if (CustomDrainChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.CustomDrainChannel_3017,
					CustomDrainChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_CustomSpoutChannel_3018(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof CustomSpoutChannel) {
				continue;
			}
			CustomSpoutChannel link = (CustomSpoutChannel) setting.getEObject();
			if (CustomSpoutChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.CustomSpoutChannel_3018,
					CustomSpoutChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_PrioritySync_3019(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof PrioritySync) {
				continue;
			}
			PrioritySync link = (PrioritySync) setting.getEObject();
			if (PrioritySyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.PrioritySync_3019,
					PrioritySyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_BlockingSync_3020(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof BlockingSync) {
				continue;
			}
			BlockingSync link = (BlockingSync) setting.getEObject();
			if (BlockingSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.BlockingSync_3020,
					BlockingSyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_BlockingSinkSync_3021(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof BlockingSinkSync) {
				continue;
			}
			BlockingSinkSync link = (BlockingSinkSync) setting.getEObject();
			if (BlockingSinkSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.BlockingSinkSync_3021,
					BlockingSinkSyncEditPart.VISUAL_ID));
		}
		return result;
	}
	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getIncomingTypeModelFacetLinks_BlockingSourceSync_3022(
			Node target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ChannelsPackage.eINSTANCE
					.getChannel_NodeTwo()
					|| false == setting.getEObject() instanceof BlockingSourceSync) {
				continue;
			}
			BlockingSourceSync link = (BlockingSourceSync) setting.getEObject();
			if (BlockingSourceSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node src = link.getNodeOne();
			result.add(new ReoLinkDescriptor(src, target, link,
					ReoElementTypes.BlockingSourceSync_3022,
					BlockingSourceSyncEditPart.VISUAL_ID));
		}
		return result;
	}
	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_Sync_3001(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Sync) {
				continue;
			}
			Sync link = (Sync) linkObject;
			if (SyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Sync_3001, SyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_LossySync_3002(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof LossySync) {
				continue;
			}
			LossySync link = (LossySync) linkObject;
			if (LossySyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.LossySync_3002, LossySyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_FIFO_3003(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof org.ect.reo.channels.FIFO) {
				continue;
			}
			org.ect.reo.channels.FIFO link = (org.ect.reo.channels.FIFO) linkObject;
			if (FIFOEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.FIFO_3003, FIFOEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_SyncDrain_3005(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SyncDrain) {
				continue;
			}
			SyncDrain link = (SyncDrain) linkObject;
			if (SyncDrainEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.SyncDrain_3005, SyncDrainEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_SyncSpout_3006(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SyncSpout) {
				continue;
			}
			SyncSpout link = (SyncSpout) linkObject;
			if (SyncSpoutEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.SyncSpout_3006, SyncSpoutEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_AsyncDrain_3007(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof AsyncDrain) {
				continue;
			}
			AsyncDrain link = (AsyncDrain) linkObject;
			if (AsyncDrainEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.AsyncDrain_3007,
					AsyncDrainEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_AsyncSpout_3008(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof AsyncSpout) {
				continue;
			}
			AsyncSpout link = (AsyncSpout) linkObject;
			if (AsyncSpoutEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.AsyncSpout_3008,
					AsyncSpoutEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_Filter_3011(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Filter) {
				continue;
			}
			Filter link = (Filter) linkObject;
			if (FilterEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Filter_3011, FilterEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_Transform_3012(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transform) {
				continue;
			}
			Transform link = (Transform) linkObject;
			if (TransformEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Transform_3012, TransformEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_Timer_3015(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Timer) {
				continue;
			}
			Timer link = (Timer) linkObject;
			if (TimerEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.Timer_3015, TimerEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingFeatureModelFacetLinks_Node_SourceEnds_3013(
			Node source) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> destinations = source.getSourceEnds().iterator(); destinations
				.hasNext();) {
			SourceEnd destination = (SourceEnd) destinations.next();
			result.add(new ReoLinkDescriptor(source, destination,
					ReoElementTypes.NodeSourceEnds_3013,
					NodeSourceEndsEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingFeatureModelFacetLinks_SinkEnd_Node_3014(
			SinkEnd source) {
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		Node destination = source.getNode();
		if (destination == null) {
			return result;
		}
		result.add(new ReoLinkDescriptor(source, destination,
				ReoElementTypes.SinkEndNode_3014, SinkEndNodeEditPart.VISUAL_ID));
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_CustomDirectedChannel_3016(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CustomDirectedChannel) {
				continue;
			}
			CustomDirectedChannel link = (CustomDirectedChannel) linkObject;
			if (CustomDirectedChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.CustomDirectedChannel_3016,
					CustomDirectedChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_CustomDrainChannel_3017(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CustomDrainChannel) {
				continue;
			}
			CustomDrainChannel link = (CustomDrainChannel) linkObject;
			if (CustomDrainChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.CustomDrainChannel_3017,
					CustomDrainChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_CustomSpoutChannel_3018(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CustomSpoutChannel) {
				continue;
			}
			CustomSpoutChannel link = (CustomSpoutChannel) linkObject;
			if (CustomSpoutChannelEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.CustomSpoutChannel_3018,
					CustomSpoutChannelEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_PrioritySync_3019(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PrioritySync) {
				continue;
			}
			PrioritySync link = (PrioritySync) linkObject;
			if (PrioritySyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.PrioritySync_3019,
					PrioritySyncEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_BlockingSourceSync_3022(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BlockingSourceSync) {
				continue;
			}
			BlockingSourceSync link = (BlockingSourceSync) linkObject;
			if (BlockingSourceSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.BlockingSourceSync_3022,
					BlockingSourceSyncEditPart.VISUAL_ID));
		}
		return result;
	}


	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_BlockingSinkSync_3021(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BlockingSinkSync) {
				continue;
			}
			BlockingSinkSync link = (BlockingSinkSync) linkObject;
			if (BlockingSinkSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.BlockingSinkSync_3021,
					BlockingSinkSyncEditPart.VISUAL_ID));
		}
		return result;
	}


	/**
	 * @generated
	 */
	private static Collection<ReoLinkDescriptor> getOutgoingTypeModelFacetLinks_BlockingSync_3020(
			Node source) {
		Connector container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				container = (Connector) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ReoLinkDescriptor> result = new LinkedList<ReoLinkDescriptor>();
		for (Iterator<?> links = container.getPrimitives().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof BlockingSync) {
				continue;
			}
			BlockingSync link = (BlockingSync) linkObject;
			if (BlockingSyncEditPart.VISUAL_ID != ReoVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Node dst = link.getNodeTwo();
			Node src = link.getNodeOne();
			if (src != source) {
				continue;
			}
			result.add(new ReoLinkDescriptor(src, dst, link,
					ReoElementTypes.BlockingSync_3020,
					BlockingSyncEditPart.VISUAL_ID));
		}
		return result;
	}

}
