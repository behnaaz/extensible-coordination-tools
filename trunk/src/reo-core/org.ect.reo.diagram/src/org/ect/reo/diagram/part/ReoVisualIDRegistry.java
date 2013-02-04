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

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Component;
import org.ect.reo.Module;
import org.ect.reo.ReoPackage;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.channels.FIFO;
import org.ect.reo.components.ComponentsPackage;
import org.ect.reo.diagram.edit.parts.AsyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.AsyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSinkSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSourceSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSyncEditPart;
import org.ect.reo.diagram.edit.parts.ComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ComponentNameEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorNameEditPart;
import org.ect.reo.diagram.edit.parts.CustomDirectedChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomDrainChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomSpoutChannelEditPart;
import org.ect.reo.diagram.edit.parts.FIFOEditPart;
import org.ect.reo.diagram.edit.parts.FilterEditPart;
import org.ect.reo.diagram.edit.parts.FilterExpressionEditPart;
import org.ect.reo.diagram.edit.parts.FullFIFOEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentNameEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderNameEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterNameEditPart;
import org.ect.reo.diagram.edit.parts.LossySyncEditPart;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.NodeNameEditPart;
import org.ect.reo.diagram.edit.parts.PrioritySyncEditPart;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;
import org.ect.reo.diagram.edit.parts.ReaderCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.ReaderEditPart;
import org.ect.reo.diagram.edit.parts.ReaderNameEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNameEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndNameEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorNameEditPart;
import org.ect.reo.diagram.edit.parts.SyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.SyncDrainExpressionEditPart;
import org.ect.reo.diagram.edit.parts.SyncEditPart;
import org.ect.reo.diagram.edit.parts.SyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.TimerEditPart;
import org.ect.reo.diagram.edit.parts.TimerTimeoutEditPart;
import org.ect.reo.diagram.edit.parts.TransformEditPart;
import org.ect.reo.diagram.edit.parts.TransformExpressionEditPart;
import org.ect.reo.diagram.edit.parts.WriterCompartmentEditPart;
import org.ect.reo.diagram.edit.parts.WriterEditPart;
import org.ect.reo.diagram.edit.parts.WriterNameEditPart;


/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented 
 * by a domain model object.
 *
 * @generated
 */
public class ReoVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.ect.reo.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (ModuleEditPart.MODEL_ID.equals(view.getType())) {
				return ModuleEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.ect.reo.diagram.part.ReoVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				ReoDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ReoPackage.eINSTANCE.getModule().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Module) domainElement)) {
			return ModuleEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.ect.reo.diagram.part.ReoVisualIDRegistry
				.getModelID(containerView);
		if (!ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.ect.reo.diagram.part.ReoVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModuleEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case ModuleEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getConnector().isSuperTypeOf(
					domainElement.eClass())) {
				return ConnectorEditPart.VISUAL_ID;
			}
			if (ComponentsPackage.eINSTANCE.getReader().isSuperTypeOf(
					domainElement.eClass())) {
				return ReaderEditPart.VISUAL_ID;
			}
			if (ComponentsPackage.eINSTANCE.getWriter().isSuperTypeOf(
					domainElement.eClass())) {
				return WriterEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getComponent().isSuperTypeOf(
					domainElement.eClass())) {
				return ComponentEditPart.VISUAL_ID;
			}
			break;
		case ReaderEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getSourceEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSourceEnd_2006((SourceEnd) domainElement)) {
				return SourceEndEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getSinkEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSinkEnd_2007((SinkEnd) domainElement)) {
				return SinkEndEditPart.VISUAL_ID;
			}
			break;
		case WriterEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getSourceEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSourceEnd_2006((SourceEnd) domainElement)) {
				return SourceEndEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getSinkEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSinkEnd_2007((SinkEnd) domainElement)) {
				return SinkEndEditPart.VISUAL_ID;
			}
			break;
		case ComponentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getSourceEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSourceEnd_2006((SourceEnd) domainElement)) {
				return SourceEndEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getSinkEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSinkEnd_2007((SinkEnd) domainElement)) {
				return SinkEndEditPart.VISUAL_ID;
			}
			break;
		case InternalReaderEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getSourceEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSourceEnd_2006((SourceEnd) domainElement)) {
				return SourceEndEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getSinkEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSinkEnd_2007((SinkEnd) domainElement)) {
				return SinkEndEditPart.VISUAL_ID;
			}
			break;
		case InternalWriterEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getSourceEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSourceEnd_2006((SourceEnd) domainElement)) {
				return SourceEndEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getSinkEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSinkEnd_2007((SinkEnd) domainElement)) {
				return SinkEndEditPart.VISUAL_ID;
			}
			break;
		case InternalComponentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getSourceEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSourceEnd_2006((SourceEnd) domainElement)) {
				return SourceEndEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getSinkEnd().isSuperTypeOf(
					domainElement.eClass())
					&& isSinkEnd_2007((SinkEnd) domainElement)) {
				return SinkEndEditPart.VISUAL_ID;
			}
			break;
		case ConnectorCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())) {
				return NodeEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getConnector().isSuperTypeOf(
					domainElement.eClass())) {
				return SubConnectorEditPart.VISUAL_ID;
			}
			if (ComponentsPackage.eINSTANCE.getReader().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalReaderEditPart.VISUAL_ID;
			}
			if (ComponentsPackage.eINSTANCE.getWriter().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalWriterEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getComponent().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalComponentEditPart.VISUAL_ID;
			}
			break;
		case SubConnectorCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getNode().isSuperTypeOf(
					domainElement.eClass())) {
				return NodeEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getConnector().isSuperTypeOf(
					domainElement.eClass())) {
				return SubConnectorEditPart.VISUAL_ID;
			}
			if (ComponentsPackage.eINSTANCE.getReader().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalReaderEditPart.VISUAL_ID;
			}
			if (ComponentsPackage.eINSTANCE.getWriter().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalWriterEditPart.VISUAL_ID;
			}
			if (ReoPackage.eINSTANCE.getComponent().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalComponentEditPart.VISUAL_ID;
			}
			break;
		case InternalReaderCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getProperty().isSuperTypeOf(
					domainElement.eClass())) {
				return PropertyEditPart.VISUAL_ID;
			}
			break;
		case InternalWriterCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getProperty().isSuperTypeOf(
					domainElement.eClass())) {
				return PropertyEditPart.VISUAL_ID;
			}
			break;
		case InternalComponentCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getProperty().isSuperTypeOf(
					domainElement.eClass())) {
				return PropertyEditPart.VISUAL_ID;
			}
			break;
		case ReaderCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getProperty().isSuperTypeOf(
					domainElement.eClass())) {
				return PropertyEditPart.VISUAL_ID;
			}
			break;
		case WriterCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getProperty().isSuperTypeOf(
					domainElement.eClass())) {
				return PropertyEditPart.VISUAL_ID;
			}
			break;
		case ComponentCompartmentEditPart.VISUAL_ID:
			if (ReoPackage.eINSTANCE.getProperty().isSuperTypeOf(
					domainElement.eClass())) {
				return PropertyEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.ect.reo.diagram.part.ReoVisualIDRegistry
				.getModelID(containerView);
		if (!ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.ect.reo.diagram.part.ReoVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModuleEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ModuleEditPart.VISUAL_ID:
			if (ConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (WriterEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComponentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ConnectorEditPart.VISUAL_ID:
			if (ConnectorNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ConnectorCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ReaderEditPart.VISUAL_ID:
			if (ReaderNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ReaderCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SourceEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SinkEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case WriterEditPart.VISUAL_ID:
			if (WriterNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (WriterCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SourceEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SinkEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComponentEditPart.VISUAL_ID:
			if (ComponentNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ComponentCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SourceEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SinkEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NodeEditPart.VISUAL_ID:
			if (NodeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubConnectorEditPart.VISUAL_ID:
			if (SubConnectorNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubConnectorCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalReaderEditPart.VISUAL_ID:
			if (InternalReaderNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalReaderCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SourceEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SinkEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SourceEndEditPart.VISUAL_ID:
			if (SourceEndNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SinkEndEditPart.VISUAL_ID:
			if (SinkEndNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalWriterEditPart.VISUAL_ID:
			if (InternalWriterNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalWriterCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SourceEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SinkEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalComponentEditPart.VISUAL_ID:
			if (InternalComponentNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalComponentCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SourceEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SinkEndEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ConnectorCompartmentEditPart.VISUAL_ID:
			if (NodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalWriterEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalComponentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubConnectorCompartmentEditPart.VISUAL_ID:
			if (NodeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalReaderEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalWriterEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalComponentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalReaderCompartmentEditPart.VISUAL_ID:
			if (PropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalWriterCompartmentEditPart.VISUAL_ID:
			if (PropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalComponentCompartmentEditPart.VISUAL_ID:
			if (PropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ReaderCompartmentEditPart.VISUAL_ID:
			if (PropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case WriterCompartmentEditPart.VISUAL_ID:
			if (PropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ComponentCompartmentEditPart.VISUAL_ID:
			if (PropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SyncDrainEditPart.VISUAL_ID:
			if (SyncDrainExpressionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FilterEditPart.VISUAL_ID:
			if (FilterExpressionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransformEditPart.VISUAL_ID:
			if (TransformExpressionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TimerEditPart.VISUAL_ID:
			if (TimerTimeoutEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ChannelsPackage.eINSTANCE.getSync().isSuperTypeOf(
				domainElement.eClass())) {
			return SyncEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getLossySync().isSuperTypeOf(
				domainElement.eClass())) {
			return LossySyncEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getFIFO().isSuperTypeOf(
				domainElement.eClass())) {
			return FIFOEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getSyncDrain().isSuperTypeOf(
				domainElement.eClass())) {
			return SyncDrainEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getSyncSpout().isSuperTypeOf(
				domainElement.eClass())) {
			return SyncSpoutEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getAsyncDrain().isSuperTypeOf(
				domainElement.eClass())) {
			return AsyncDrainEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getAsyncSpout().isSuperTypeOf(
				domainElement.eClass())) {
			return AsyncSpoutEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getFilter().isSuperTypeOf(
				domainElement.eClass())) {
			return FilterEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getTransform().isSuperTypeOf(
				domainElement.eClass())) {
			return TransformEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getTimer().isSuperTypeOf(
				domainElement.eClass())) {
			return TimerEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getCustomDirectedChannel().isSuperTypeOf(
				domainElement.eClass())) {
			return CustomDirectedChannelEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getCustomDrainChannel().isSuperTypeOf(
				domainElement.eClass())) {
			return CustomDrainChannelEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getCustomSpoutChannel().isSuperTypeOf(
				domainElement.eClass())) {
			return CustomSpoutChannelEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getPrioritySync().isSuperTypeOf(
				domainElement.eClass())) {
			return PrioritySyncEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getBlockingSync().isSuperTypeOf(
				domainElement.eClass())) {
			return BlockingSyncEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getBlockingSinkSync().isSuperTypeOf(
				domainElement.eClass())) {
			return BlockingSinkSyncEditPart.VISUAL_ID;
		}
		if (ChannelsPackage.eINSTANCE.getBlockingSourceSync().isSuperTypeOf(
				domainElement.eClass())) {
			return BlockingSourceSyncEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Module element) {
		return true;
	}

	/**
	 * @generated NOT
	 */
	private static boolean isSourceEnd_2006(SourceEnd end) {
		return (end.getPrimitive() instanceof Component);
	}

	/**
	 * @generated NOT
	 */
	private static boolean isSinkEnd_2007(SinkEnd end) {
		return (end.getPrimitive() instanceof Component);
	}

}
