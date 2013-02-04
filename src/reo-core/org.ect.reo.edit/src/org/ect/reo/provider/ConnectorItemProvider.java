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
package org.ect.reo.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.ect.reo.Connector;
import org.ect.reo.ReoFactory;
import org.ect.reo.ReoPackage;
import org.ect.reo.channels.ChannelsFactory;
import org.ect.reo.components.ComponentsFactory;


/**
 * This is the item provider adapter for a {@link org.ect.reo.Connector} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorItemProvider
	extends ItemProviderAdapter
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			// SKIP THE ENGINE
			//addColouringEnginePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Nameable_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Nameable_name_feature", "_UI_Nameable_type"),
				 ReoPackage.Literals.NAMEABLE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 ReoPropertyCategories.BASIC,
				 null));
	}

	/**
	 * This adds a property descriptor for the Colouring Engine feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addColouringEnginePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Connector_colouringEngine_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Connector_colouringEngine_feature", "_UI_Connector_type"),
				 ReoPackage.Literals.CONNECTOR__COLOURING_ENGINE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ReoPackage.Literals.RECONFIGURABLE__RECONF_ACTIONS);
			childrenFeatures.add(ReoPackage.Literals.PROPERTY_HOLDER__PROPERTIES);
			childrenFeatures.add(ReoPackage.Literals.CONNECTOR__NODES);
			childrenFeatures.add(ReoPackage.Literals.CONNECTOR__PRIMITIVES);
			childrenFeatures.add(ReoPackage.Literals.CONNECTOR__SUB_CONNECTORS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Connector.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Connector"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Connector)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Connector_type") :
			getString("_UI_Connector_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Connector.class)) {
			case ReoPackage.CONNECTOR__NAME:
			case ReoPackage.CONNECTOR__COLOURING_ENGINE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ReoPackage.CONNECTOR__RECONF_ACTIONS:
			case ReoPackage.CONNECTOR__PROPERTIES:
			case ReoPackage.CONNECTOR__NODES:
			case ReoPackage.CONNECTOR__PRIMITIVES:
			case ReoPackage.CONNECTOR__SUB_CONNECTORS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.RECONFIGURABLE__RECONF_ACTIONS,
				 ReoFactory.eINSTANCE.createReconfAction()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.PROPERTY_HOLDER__PROPERTIES,
				 ReoFactory.eINSTANCE.createProperty()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__NODES,
				 ReoFactory.eINSTANCE.createNode()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ReoFactory.eINSTANCE.createComponent()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createSync()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createLossySync()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createFIFO()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createLossyFIFO()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createSyncDrain()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createAsyncDrain()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createSyncSpout()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createAsyncSpout()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createFilter()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createTransform()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createTimer()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createCustomDirectedChannel()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createCustomDrainChannel()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createCustomSpoutChannel()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createPrioritySync()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createBlockingSourceSync()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createBlockingSinkSync()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ChannelsFactory.eINSTANCE.createBlockingSync()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ComponentsFactory.eINSTANCE.createReader()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__PRIMITIVES,
				 ComponentsFactory.eINSTANCE.createWriter()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CONNECTOR__SUB_CONNECTORS,
				 ReoFactory.eINSTANCE.createConnector()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ReoEditPlugin.INSTANCE;
	}

}
