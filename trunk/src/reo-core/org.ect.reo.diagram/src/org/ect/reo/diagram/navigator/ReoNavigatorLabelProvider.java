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
package org.ect.reo.diagram.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.ect.reo.Module;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.BlockingSinkSync;
import org.ect.reo.channels.BlockingSourceSync;
import org.ect.reo.channels.BlockingSync;
import org.ect.reo.channels.CustomDirectedChannel;
import org.ect.reo.channels.CustomDrainChannel;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.diagram.edit.parts.AsyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.AsyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSinkSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSourceSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSyncEditPart;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ComponentNameEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorNameEditPart;
import org.ect.reo.diagram.edit.parts.CustomDirectedChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomDrainChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomSpoutChannelEditPart;
import org.ect.reo.diagram.edit.parts.FIFOEditPart;
import org.ect.reo.diagram.edit.parts.FilterEditPart;
import org.ect.reo.diagram.edit.parts.FilterExpressionEditPart;
import org.ect.reo.diagram.edit.parts.FullFIFOEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentNameEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderNameEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterNameEditPart;
import org.ect.reo.diagram.edit.parts.LossySyncEditPart;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.NodeNameEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.PrioritySyncEditPart;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;
import org.ect.reo.diagram.edit.parts.ReaderEditPart;
import org.ect.reo.diagram.edit.parts.ReaderNameEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNameEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndNameEditPart;
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
import org.ect.reo.diagram.edit.parts.WriterEditPart;
import org.ect.reo.diagram.edit.parts.WriterNameEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;
import org.ect.reo.diagram.providers.ReoParserProvider;


/**
 * @generated
 */
public class ReoNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		ReoDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		ReoDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof ReoNavigatorItem
				&& !isOwnView(((ReoNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof ReoNavigatorGroup) {
			ReoNavigatorGroup group = (ReoNavigatorGroup) element;
			return ReoDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof ReoNavigatorItem) {
			ReoNavigatorItem navigatorItem = (ReoNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (ReoVisualIDRegistry.getVisualID(view)) {
		case LossySyncEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?LossySync", ReoElementTypes.LossySync_3002); //$NON-NLS-1$
		case SyncSpoutEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?SyncSpout", ReoElementTypes.SyncSpout_3006); //$NON-NLS-1$
		case TransformEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?Transform", ReoElementTypes.Transform_3012); //$NON-NLS-1$
		case ReaderEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.cwi.nl/reo/components?Reader", ReoElementTypes.Reader_1002); //$NON-NLS-1$
		case InternalReaderEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo/components?Reader", ReoElementTypes.Reader_2008); //$NON-NLS-1$
		case PropertyEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo?Property", ReoElementTypes.Property_2004); //$NON-NLS-1$
		case NodeSourceEndsEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo?Node?sourceEnds", ReoElementTypes.NodeSourceEnds_3013); //$NON-NLS-1$
		case CustomDrainChannelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?CustomDrainChannel", ReoElementTypes.CustomDrainChannel_3017); //$NON-NLS-1$
		case AsyncDrainEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?AsyncDrain", ReoElementTypes.AsyncDrain_3007); //$NON-NLS-1$
		case ModuleEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://www.cwi.nl/reo?Module", ReoElementTypes.Module_1000); //$NON-NLS-1$
		case ComponentEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.cwi.nl/reo?Component", ReoElementTypes.Component_1004); //$NON-NLS-1$
		case SinkEndNodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo?SinkEnd?node", ReoElementTypes.SinkEndNode_3014); //$NON-NLS-1$
		case CustomSpoutChannelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?CustomSpoutChannel", ReoElementTypes.CustomSpoutChannel_3018); //$NON-NLS-1$
		case PrioritySyncEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?PrioritySync", ReoElementTypes.PrioritySync_3019); //$NON-NLS-1$
		case TimerEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?Timer", ReoElementTypes.Timer_3015); //$NON-NLS-1$
		case NodeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo?Node", ReoElementTypes.Node_2001); //$NON-NLS-1$
		case InternalComponentEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo?Component", ReoElementTypes.Component_2010); //$NON-NLS-1$
		case CustomDirectedChannelEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?CustomDirectedChannel", ReoElementTypes.CustomDirectedChannel_3016); //$NON-NLS-1$
		case SinkEndEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo?SinkEnd", ReoElementTypes.SinkEnd_2007); //$NON-NLS-1$
		case ConnectorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.cwi.nl/reo?Connector", ReoElementTypes.Connector_1001); //$NON-NLS-1$
		case InternalWriterEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo/components?Writer", ReoElementTypes.Writer_2009); //$NON-NLS-1$
		case FilterEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?Filter", ReoElementTypes.Filter_3011); //$NON-NLS-1$
		case SyncDrainEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?SyncDrain", ReoElementTypes.SyncDrain_3005); //$NON-NLS-1$
		case SubConnectorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo?Connector", ReoElementTypes.Connector_2005); //$NON-NLS-1$
		case WriterEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.cwi.nl/reo/components?Writer", ReoElementTypes.Writer_1003); //$NON-NLS-1$
		case FIFOEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?FIFO", ReoElementTypes.FIFO_3003); //$NON-NLS-1$
		case BlockingSyncEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?BlockingSync", ReoElementTypes.BlockingSync_3020); //$NON-NLS-1$
		case BlockingSinkSyncEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?BlockingSinkSync", ReoElementTypes.BlockingSinkSync_3021); //$NON-NLS-1$
		case BlockingSourceSyncEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?BlockingSourceSync", ReoElementTypes.BlockingSourceSync_3022); //$NON-NLS-1$

		case AsyncSpoutEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?AsyncSpout", ReoElementTypes.AsyncSpout_3008); //$NON-NLS-1$
		case SyncEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://www.cwi.nl/reo/channels?Sync", ReoElementTypes.Sync_3001); //$NON-NLS-1$
		case SourceEndEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.cwi.nl/reo?SourceEnd", ReoElementTypes.SourceEnd_2006); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = ReoDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& ReoElementTypes.isKnownElementType(elementType)) {
			image = ReoElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof ReoNavigatorGroup) {
			ReoNavigatorGroup group = (ReoNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof ReoNavigatorItem) {
			ReoNavigatorItem navigatorItem = (ReoNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (ReoVisualIDRegistry.getVisualID(view)) {
		case LossySyncEditPart.VISUAL_ID:
			return getLossySync_3002Text(view);
		case SyncSpoutEditPart.VISUAL_ID:
			return getSyncSpout_3006Text(view);
		case TransformEditPart.VISUAL_ID:
			return getTransform_3012Text(view);
		case ReaderEditPart.VISUAL_ID:
			return getReader_1002Text(view);
		case InternalReaderEditPart.VISUAL_ID:
			return getReader_2008Text(view);
		case PropertyEditPart.VISUAL_ID:
			return getProperty_2004Text(view);
		case NodeSourceEndsEditPart.VISUAL_ID:
			return getNodeSourceEnds_3013Text(view);
		case CustomDrainChannelEditPart.VISUAL_ID:
			return getCustomDrainChannel_3017Text(view);
		case AsyncDrainEditPart.VISUAL_ID:
			return getAsyncDrain_3007Text(view);
		case ModuleEditPart.VISUAL_ID:
			return getModule_1000Text(view);
		case ComponentEditPart.VISUAL_ID:
			return getComponent_1004Text(view);
		case SinkEndNodeEditPart.VISUAL_ID:
			return getSinkEndNode_3014Text(view);
		case CustomSpoutChannelEditPart.VISUAL_ID:
			return getCustomSpoutChannel_3018Text(view);
		case PrioritySyncEditPart.VISUAL_ID:
			return getPrioritySync_3019Text(view);
		case TimerEditPart.VISUAL_ID:
			return getTimer_3015Text(view);
		case NodeEditPart.VISUAL_ID:
			return getNode_2001Text(view);
		case InternalComponentEditPart.VISUAL_ID:
			return getComponent_2010Text(view);
		case CustomDirectedChannelEditPart.VISUAL_ID:
			return getCustomDirectedChannel_3016Text(view);
		case SinkEndEditPart.VISUAL_ID:
			return getSinkEnd_2007Text(view);
		case ConnectorEditPart.VISUAL_ID:
			return getConnector_1001Text(view);
		case InternalWriterEditPart.VISUAL_ID:
			return getWriter_2009Text(view);
		case FilterEditPart.VISUAL_ID:
			return getFilter_3011Text(view);
		case SyncDrainEditPart.VISUAL_ID:
			return getSyncDrain_3005Text(view);
		case SubConnectorEditPart.VISUAL_ID:
			return getConnector_2005Text(view);
		case WriterEditPart.VISUAL_ID:
			return getWriter_1003Text(view);
		case FIFOEditPart.VISUAL_ID:
			return getFIFO_3003Text(view);
		case BlockingSyncEditPart.VISUAL_ID: 
			return getBlockingSync_3020Text(view);
		case BlockingSinkSyncEditPart.VISUAL_ID:
			return getBlockingSinkSync_3021Text(view);
		case BlockingSourceSyncEditPart.VISUAL_ID:
			return getBlockingSourceSync_3022Text(view);
		case AsyncSpoutEditPart.VISUAL_ID:
			return getAsyncSpout_3008Text(view);
		case SyncEditPart.VISUAL_ID:
			return getSync_3001Text(view);
		case SourceEndEditPart.VISUAL_ID:
			return getSourceEnd_2006Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getModule_1000Text(View view) {
		Module domainModelElement = (Module) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getPrioritySync_3019Text(View view) {
		PrioritySync domainModelElement = (PrioritySync) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3019); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getConnector_1001Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Connector_1001,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(ConnectorNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getReader_1002Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Reader_1002,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(ReaderNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getWriter_1003Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Writer_1003,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(WriterNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getComponent_1004Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Component_1004,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(ComponentNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNode_2001Text(View view) {
		IParser parser = ReoParserProvider.getParser(ReoElementTypes.Node_2001,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(NodeNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getConnector_2005Text(View view) {
		IParser parser = ReoParserProvider
				.getParser(ReoElementTypes.Connector_2005,
						view.getElement() != null ? view.getElement() : view,
						ReoVisualIDRegistry
								.getType(SubConnectorNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4012); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getReader_2008Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Reader_2008,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry
						.getType(InternalReaderNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4016); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getProperty_2004Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Property_2004,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(PropertyEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 2004); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSourceEnd_2006Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.SourceEnd_2006,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(SourceEndNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSinkEnd_2007Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.SinkEnd_2007,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(SinkEndNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getWriter_2009Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Writer_2009,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry
						.getType(InternalWriterNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getComponent_2010Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Component_2010,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry
						.getType(InternalComponentNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4018); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSync_3001Text(View view) {
		Sync domainModelElement = (Sync) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getLossySync_3002Text(View view) {
		LossySync domainModelElement = (LossySync) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getFIFO_3003Text(View view) {
		org.ect.reo.channels.FIFO domainModelElement = (org.ect.reo.channels.FIFO) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getCellName();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSyncDrain_3005Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.SyncDrain_3005,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry
						.getType(SyncDrainExpressionEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4019); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getSyncSpout_3006Text(View view) {
		SyncSpout domainModelElement = (SyncSpout) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAsyncDrain_3007Text(View view) {
		AsyncDrain domainModelElement = (AsyncDrain) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAsyncSpout_3008Text(View view) {
		AsyncSpout domainModelElement = (AsyncSpout) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getBlockingSync_3020Text(View view) {
		BlockingSync domainModelElement = (BlockingSync) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3020); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getBlockingSinkSync_3021Text(View view) {
		BlockingSinkSync domainModelElement = (BlockingSinkSync) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3020); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	
	/**
	 * @generated
	 */
	private String getBlockingSourceSync_3022Text(View view) {
		BlockingSourceSync domainModelElement = (BlockingSourceSync) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getChannelEndNameOne();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3020); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * @generated
	 */
	private String getCustomDirectedChannel_3016Text(View view) {
		CustomDirectedChannel domainModelElement = (CustomDirectedChannel) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3016); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getFilter_3011Text(View view) {
		IParser parser = ReoParserProvider
				.getParser(ReoElementTypes.Filter_3011,
						view.getElement() != null ? view.getElement() : view,
						ReoVisualIDRegistry
								.getType(FilterExpressionEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getCustomDrainChannel_3017Text(View view) {
		CustomDrainChannel domainModelElement = (CustomDrainChannel) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTransform_3012Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Transform_3012,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry
						.getType(TransformExpressionEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTimer_3015Text(View view) {
		IParser parser = ReoParserProvider.getParser(
				ReoElementTypes.Timer_3015,
				view.getElement() != null ? view.getElement() : view,
				ReoVisualIDRegistry.getType(TimerTimeoutEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 4015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getCustomSpoutChannel_3018Text(View view) {
		CustomSpoutChannel domainModelElement = (CustomSpoutChannel) view
				.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			ReoDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 3018); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNodeSourceEnds_3013Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getSinkEndNode_3014Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return ModuleEditPart.MODEL_ID.equals(ReoVisualIDRegistry
				.getModelID(view));
	}

}
