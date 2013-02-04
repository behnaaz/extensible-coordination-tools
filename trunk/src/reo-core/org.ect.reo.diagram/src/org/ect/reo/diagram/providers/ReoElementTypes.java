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

package org.ect.reo.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.ect.reo.ReoPackage;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.components.ComponentsPackage;
import org.ect.reo.diagram.edit.parts.AsyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.AsyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSinkSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSourceSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSyncEditPart;
import org.ect.reo.diagram.edit.parts.ComponentEditPart;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;
import org.ect.reo.diagram.edit.parts.CustomDirectedChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomDrainChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomSpoutChannelEditPart;
import org.ect.reo.diagram.edit.parts.FIFOEditPart;
import org.ect.reo.diagram.edit.parts.FilterEditPart;
import org.ect.reo.diagram.edit.parts.InternalComponentEditPart;
import org.ect.reo.diagram.edit.parts.InternalReaderEditPart;
import org.ect.reo.diagram.edit.parts.InternalWriterEditPart;
import org.ect.reo.diagram.edit.parts.LossySyncEditPart;
import org.ect.reo.diagram.edit.parts.ModuleEditPart;
import org.ect.reo.diagram.edit.parts.NodeEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.PrioritySyncEditPart;
import org.ect.reo.diagram.edit.parts.PropertyEditPart;
import org.ect.reo.diagram.edit.parts.ReaderEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndEditPart;
import org.ect.reo.diagram.edit.parts.SubConnectorEditPart;
import org.ect.reo.diagram.edit.parts.SyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.SyncEditPart;
import org.ect.reo.diagram.edit.parts.SyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.TimerEditPart;
import org.ect.reo.diagram.edit.parts.TransformEditPart;
import org.ect.reo.diagram.edit.parts.WriterEditPart;
import org.ect.reo.diagram.part.ReoDiagramEditorPlugin;


/**
 * @generated
 */
public class ReoElementTypes {

	/**
	 * @generated
	 */
	private ReoElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return ReoDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(Module_1000, ReoPackage.eINSTANCE.getModule());

			elements.put(Connector_1001, ReoPackage.eINSTANCE.getConnector());

			elements.put(Reader_1002, ComponentsPackage.eINSTANCE.getReader());

			elements.put(Writer_1003, ComponentsPackage.eINSTANCE.getWriter());

			elements.put(Component_1004, ReoPackage.eINSTANCE.getComponent());

			elements.put(Node_2001, ReoPackage.eINSTANCE.getNode());

			elements.put(Connector_2005, ReoPackage.eINSTANCE.getConnector());

			elements.put(Reader_2008, ComponentsPackage.eINSTANCE.getReader());

			elements.put(Property_2004, ReoPackage.eINSTANCE.getProperty());

			elements.put(SourceEnd_2006, ReoPackage.eINSTANCE.getSourceEnd());

			elements.put(SinkEnd_2007, ReoPackage.eINSTANCE.getSinkEnd());

			elements.put(Writer_2009, ComponentsPackage.eINSTANCE.getWriter());

			elements.put(Component_2010, ReoPackage.eINSTANCE.getComponent());

			elements.put(Sync_3001, ChannelsPackage.eINSTANCE.getSync());

			elements.put(LossySync_3002,
					ChannelsPackage.eINSTANCE.getLossySync());

			elements.put(FIFO_3003, ChannelsPackage.eINSTANCE.getFIFO());

			elements.put(SyncDrain_3005,
					ChannelsPackage.eINSTANCE.getSyncDrain());

			elements.put(SyncSpout_3006,
					ChannelsPackage.eINSTANCE.getSyncSpout());

			elements.put(AsyncDrain_3007,
					ChannelsPackage.eINSTANCE.getAsyncDrain());

			elements.put(AsyncSpout_3008,
					ChannelsPackage.eINSTANCE.getAsyncSpout());

			elements.put(Filter_3011, ChannelsPackage.eINSTANCE.getFilter());

			elements.put(Transform_3012,
					ChannelsPackage.eINSTANCE.getTransform());

			elements.put(Timer_3015, ChannelsPackage.eINSTANCE.getTimer());

			elements.put(NodeSourceEnds_3013,
					ReoPackage.eINSTANCE.getNode_SourceEnds());

			elements.put(SinkEndNode_3014,
					ReoPackage.eINSTANCE.getSinkEnd_Node());

			elements.put(CustomDirectedChannel_3016,
					ChannelsPackage.eINSTANCE.getCustomDirectedChannel());

			elements.put(CustomDrainChannel_3017,
					ChannelsPackage.eINSTANCE.getCustomDrainChannel());

			elements.put(CustomSpoutChannel_3018,
					ChannelsPackage.eINSTANCE.getCustomSpoutChannel());

			elements.put(PrioritySync_3019,
					ChannelsPackage.eINSTANCE.getPrioritySync());

			elements.put(BlockingSync_3020,
					ChannelsPackage.eINSTANCE.getBlockingSync());
			
			elements.put(BlockingSinkSync_3021,
					ChannelsPackage.eINSTANCE.getBlockingSinkSync());
			
			elements.put(BlockingSourceSync_3022,
					ChannelsPackage.eINSTANCE.getBlockingSourceSync());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	public static final IElementType Node_2001 = getElementType("org.ect.reo.diagram.Node_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Connector_2005 = getElementType("org.ect.reo.diagram.Connector_2005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Reader_2008 = getElementType("org.ect.reo.diagram.Reader_2008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Property_2004 = getElementType("org.ect.reo.diagram.Property_2004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SourceEnd_2006 = getElementType("org.ect.reo.diagram.SourceEnd_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SinkEnd_2007 = getElementType("org.ect.reo.diagram.SinkEnd_2007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Writer_2009 = getElementType("org.ect.reo.diagram.Writer_2009"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Component_2010 = getElementType("org.ect.reo.diagram.Component_2010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Connector_1001 = getElementType("org.ect.reo.diagram.Connector_1001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Reader_1002 = getElementType("org.ect.reo.diagram.Reader_1002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Writer_1003 = getElementType("org.ect.reo.diagram.Writer_1003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Component_1004 = getElementType("org.ect.reo.diagram.Component_1004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Sync_3001 = getElementType("org.ect.reo.diagram.Sync_3001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType LossySync_3002 = getElementType("org.ect.reo.diagram.LossySync_3002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType FIFO_3003 = getElementType("org.ect.reo.diagram.FIFO_3003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SyncDrain_3005 = getElementType("org.ect.reo.diagram.SyncDrain_3005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SyncSpout_3006 = getElementType("org.ect.reo.diagram.SyncSpout_3006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType AsyncDrain_3007 = getElementType("org.ect.reo.diagram.AsyncDrain_3007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType AsyncSpout_3008 = getElementType("org.ect.reo.diagram.AsyncSpout_3008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Filter_3011 = getElementType("org.ect.reo.diagram.Filter_3011"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Transform_3012 = getElementType("org.ect.reo.diagram.Transform_3012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Timer_3015 = getElementType("org.ect.reo.diagram.Timer_3015"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType NodeSourceEnds_3013 = getElementType("org.ect.reo.diagram.NodeSourceEnds_3013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType SinkEndNode_3014 = getElementType("org.ect.reo.diagram.SinkEndNode_3014"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType CustomDirectedChannel_3016 = getElementType("org.ect.reo.diagram.CustomDirectedChannel_3016"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType CustomDrainChannel_3017 = getElementType("org.ect.reo.diagram.CustomDrainChannel_3017"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType CustomSpoutChannel_3018 = getElementType("org.ect.reo.diagram.CustomSpoutChannel_3018"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType PrioritySync_3019 = getElementType("org.ect.reo.diagram.PrioritySync_3019"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType BlockingSync_3020 = getElementType("org.ect.reo.diagram.BlockingSync_3020"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType BlockingSinkSync_3021 = getElementType("org.ect.reo.diagram.BlockingSinkSync_3021"); //$NON-NLS-1$

	
	/**
	 * @generated
	 */
	public static final IElementType BlockingSourceSync_3022 = getElementType("org.ect.reo.diagram.BlockingSourceSync_3022"); //$NON-NLS-1$

	
	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Module_1000 = getElementType("org.ect.reo.diagram.Module_1000"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Module_1000);
			KNOWN_ELEMENT_TYPES.add(Connector_1001);
			KNOWN_ELEMENT_TYPES.add(Reader_1002);
			KNOWN_ELEMENT_TYPES.add(Writer_1003);
			KNOWN_ELEMENT_TYPES.add(Component_1004);
			KNOWN_ELEMENT_TYPES.add(Node_2001);
			KNOWN_ELEMENT_TYPES.add(Connector_2005);
			KNOWN_ELEMENT_TYPES.add(Reader_2008);
			KNOWN_ELEMENT_TYPES.add(Property_2004);
			KNOWN_ELEMENT_TYPES.add(SourceEnd_2006);
			KNOWN_ELEMENT_TYPES.add(SinkEnd_2007);
			KNOWN_ELEMENT_TYPES.add(Writer_2009);
			KNOWN_ELEMENT_TYPES.add(Component_2010);
			KNOWN_ELEMENT_TYPES.add(Sync_3001);
			KNOWN_ELEMENT_TYPES.add(LossySync_3002);
			KNOWN_ELEMENT_TYPES.add(FIFO_3003);
			KNOWN_ELEMENT_TYPES.add(SyncDrain_3005);
			KNOWN_ELEMENT_TYPES.add(SyncSpout_3006);
			KNOWN_ELEMENT_TYPES.add(AsyncDrain_3007);
			KNOWN_ELEMENT_TYPES.add(AsyncSpout_3008);
			KNOWN_ELEMENT_TYPES.add(Filter_3011);
			KNOWN_ELEMENT_TYPES.add(Transform_3012);
			KNOWN_ELEMENT_TYPES.add(Timer_3015);
			KNOWN_ELEMENT_TYPES.add(NodeSourceEnds_3013);
			KNOWN_ELEMENT_TYPES.add(SinkEndNode_3014);
			KNOWN_ELEMENT_TYPES.add(CustomDirectedChannel_3016);
			KNOWN_ELEMENT_TYPES.add(CustomDrainChannel_3017);
			KNOWN_ELEMENT_TYPES.add(CustomSpoutChannel_3018);
			KNOWN_ELEMENT_TYPES.add(PrioritySync_3019);
			KNOWN_ELEMENT_TYPES.add(BlockingSync_3020);
			KNOWN_ELEMENT_TYPES.add(BlockingSinkSync_3021);
			KNOWN_ELEMENT_TYPES.add(BlockingSourceSync_3022);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case ModuleEditPart.VISUAL_ID:
			return Module_1000;
		case ConnectorEditPart.VISUAL_ID:
			return Connector_1001;
		case ReaderEditPart.VISUAL_ID:
			return Reader_1002;
		case WriterEditPart.VISUAL_ID:
			return Writer_1003;
		case ComponentEditPart.VISUAL_ID:
			return Component_1004;
		case NodeEditPart.VISUAL_ID:
			return Node_2001;
		case SubConnectorEditPart.VISUAL_ID:
			return Connector_2005;
		case InternalReaderEditPart.VISUAL_ID:
			return Reader_2008;
		case PropertyEditPart.VISUAL_ID:
			return Property_2004;
		case SourceEndEditPart.VISUAL_ID:
			return SourceEnd_2006;
		case SinkEndEditPart.VISUAL_ID:
			return SinkEnd_2007;
		case InternalWriterEditPart.VISUAL_ID:
			return Writer_2009;
		case InternalComponentEditPart.VISUAL_ID:
			return Component_2010;
		case SyncEditPart.VISUAL_ID:
			return Sync_3001;
		case LossySyncEditPart.VISUAL_ID:
			return LossySync_3002;
		case FIFOEditPart.VISUAL_ID:
			return FIFO_3003;
		case SyncDrainEditPart.VISUAL_ID:
			return SyncDrain_3005;
		case SyncSpoutEditPart.VISUAL_ID:
			return SyncSpout_3006;
		case AsyncDrainEditPart.VISUAL_ID:
			return AsyncDrain_3007;
		case AsyncSpoutEditPart.VISUAL_ID:
			return AsyncSpout_3008;
		case FilterEditPart.VISUAL_ID:
			return Filter_3011;
		case TransformEditPart.VISUAL_ID:
			return Transform_3012;
		case TimerEditPart.VISUAL_ID:
			return Timer_3015;
		case NodeSourceEndsEditPart.VISUAL_ID:
			return NodeSourceEnds_3013;
		case SinkEndNodeEditPart.VISUAL_ID:
			return SinkEndNode_3014;
		case CustomDirectedChannelEditPart.VISUAL_ID:
			return CustomDirectedChannel_3016;
		case CustomDrainChannelEditPart.VISUAL_ID:
			return CustomDrainChannel_3017;
		case CustomSpoutChannelEditPart.VISUAL_ID:
			return CustomSpoutChannel_3018;
		case PrioritySyncEditPart.VISUAL_ID:
			return PrioritySync_3019;
		case BlockingSyncEditPart.VISUAL_ID:
			return BlockingSync_3020;
		case BlockingSinkSyncEditPart.VISUAL_ID:
			return BlockingSinkSync_3021;
		case BlockingSourceSyncEditPart.VISUAL_ID:
			return BlockingSourceSync_3022;
		}
		return null;
	}
}
