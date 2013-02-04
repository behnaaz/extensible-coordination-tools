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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import java.util.LinkedList;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.ect.reo.channels.BlockingSync;
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
import org.ect.reo.diagram.part.Messages;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;


/**
 * @generated
 */
public class ReoNavigatorContentProvider implements ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public ReoNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
				new WorkspaceSynchronizer.Delegate() {
					public void dispose() {
					}

					public boolean handleResourceChanged(final Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceDeleted(Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceMoved(Resource resource,
							final URI newURI) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}
				});
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		myViewer = null;
		unloadAllResources();
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	 * @generated
	 */
	void unloadAllResources() {
		for (Resource nextResource : myEditingDomain.getResourceSet()
				.getResources()) {
			nextResource.unload();
		}
	}

	/**
	 * @generated
	 */
	void asyncRefresh() {
		if (myViewer != null && !myViewer.getControl().isDisposed()) {
			myViewer.getControl().getDisplay()
					.asyncExec(myViewerRefreshRunnable);
		}
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
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
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(
					fileURI, true);
			ArrayList<ReoNavigatorItem> result = new ArrayList<ReoNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource
					.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			result.addAll(createNavigatorItems(
					selectViewsByType(topViews, ModuleEditPart.MODEL_ID), file,
					false));
			return result.toArray();
		}

		if (parentElement instanceof ReoNavigatorGroup) {
			ReoNavigatorGroup group = (ReoNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof ReoNavigatorItem) {
			ReoNavigatorItem navigatorItem = (ReoNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (ReoVisualIDRegistry.getVisualID(view)) {

		case LossySyncEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_LossySync_3002_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_LossySync_3002_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case SyncSpoutEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SyncSpout_3006_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SyncSpout_3006_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case TransformEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Transform_3012_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Transform_3012_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case ReaderEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(ReaderCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(PropertyEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SourceEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case InternalReaderEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(InternalReaderCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(PropertyEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SourceEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case NodeSourceEndsEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_NodeSourceEnds_3013_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_NodeSourceEnds_3013_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SourceEndEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case CustomDrainChannelEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_CustomDrainChannel_3017_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_CustomDrainChannel_3017_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case AsyncDrainEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_AsyncDrain_3007_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_AsyncDrain_3007_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case ModuleEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			ReoNavigatorGroup links = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Module_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(ConnectorEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(ReaderEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(WriterEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(ComponentEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(LossySyncEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(FIFOEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncDrainEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncSpoutEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(AsyncDrainEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(AsyncSpoutEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(FilterEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(TransformEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(TimerEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(NodeSourceEndsEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndNodeEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomDirectedChannelEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomDrainChannelEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomSpoutChannelEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(PrioritySyncEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSyncEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSinkSyncEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSourceSyncEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case ComponentEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(ComponentCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(PropertyEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SourceEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case SinkEndNodeEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SinkEndNode_3014_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SinkEndNode_3014_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case CustomSpoutChannelEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_CustomSpoutChannel_3018_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_CustomSpoutChannel_3018_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case PrioritySyncEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_PrioritySync_3019_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_PrioritySync_3019_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case TimerEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Timer_3015_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Timer_3015_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case NodeEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			ReoNavigatorGroup incominglinks = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Node_2001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup outgoinglinks = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Node_2001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(LossySyncEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(LossySyncEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(FIFOEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(FIFOEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncDrainEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncDrainEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncSpoutEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SyncSpoutEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(AsyncDrainEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(AsyncDrainEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(AsyncSpoutEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(AsyncSpoutEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(FilterEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(FilterEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(TransformEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(TransformEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(TimerEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(TimerEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(NodeSourceEndsEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndNodeEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomDirectedChannelEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomDirectedChannelEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomDrainChannelEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomDrainChannelEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomSpoutChannelEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(CustomSpoutChannelEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(PrioritySyncEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(PrioritySyncEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSyncEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSyncEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSinkSyncEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSinkSyncEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSourceSyncEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(BlockingSourceSyncEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case InternalComponentEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(InternalComponentCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(PropertyEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SourceEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case CustomDirectedChannelEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_CustomDirectedChannel_3016_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_CustomDirectedChannel_3016_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case SinkEndEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			ReoNavigatorGroup outgoinglinks = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SinkEnd_2007_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndNodeEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ConnectorEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(ConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(ConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(SubConnectorEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(ConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry
							.getType(InternalReaderEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(ConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry
							.getType(InternalWriterEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(ConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry
							.getType(InternalComponentEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case InternalWriterEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(InternalWriterCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(PropertyEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SourceEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case FilterEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Filter_3011_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Filter_3011_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case SyncDrainEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SyncDrain_3005_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SyncDrain_3005_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case SubConnectorEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(SubConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(SubConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(SubConnectorEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(SubConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry
							.getType(InternalReaderEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(SubConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry
							.getType(InternalWriterEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(SubConnectorCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry
							.getType(InternalComponentEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case WriterEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(WriterCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ReoVisualIDRegistry.getType(PropertyEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SourceEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(SinkEndEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case FIFOEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_FIFO_3003_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_FIFO_3003_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case BlockingSyncEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_BlockingSync_3020_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_BlockingSync_3020_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case BlockingSinkSyncEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_BlockingSinkSync_3021_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_BlockingSinkSync_3021_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}
		
		case BlockingSourceSyncEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_BlockingSourceSync_3022_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_BlockingSourceSync_3022_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}
		
		case AsyncSpoutEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_AsyncSpout_3008_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_AsyncSpout_3008_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case SyncEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ReoNavigatorGroup target = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Sync_3001_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ReoNavigatorGroup source = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_Sync_3001_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ReoVisualIDRegistry.getType(NodeEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case SourceEndEditPart.VISUAL_ID: {
			LinkedList<ReoAbstractNavigatorItem> result = new LinkedList<ReoAbstractNavigatorItem>();
			Node sv = (Node) view;
			ReoNavigatorGroup incominglinks = new ReoNavigatorGroup(
					Messages.NavigatorGroupName_SourceEnd_2006_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ReoVisualIDRegistry
							.getType(NodeSourceEndsEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksSourceByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType())
					&& isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksTargetByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType())
					&& isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getOutgoingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getIncomingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getChildrenByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getDiagramLinksByType(
			Collection<Diagram> diagrams, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (Diagram nextDiagram : diagrams) {
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> selectViewsByType(Collection<View> views,
			String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (View nextView : views) {
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return ModuleEditPart.MODEL_ID.equals(ReoVisualIDRegistry
				.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection<ReoNavigatorItem> createNavigatorItems(
			Collection<View> views, Object parent, boolean isLeafs) {
		ArrayList<ReoNavigatorItem> result = new ArrayList<ReoNavigatorItem>(
				views.size());
		for (View nextView : views) {
			result.add(new ReoNavigatorItem(nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof ReoAbstractNavigatorItem) {
			ReoAbstractNavigatorItem abstractNavigatorItem = (ReoAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
