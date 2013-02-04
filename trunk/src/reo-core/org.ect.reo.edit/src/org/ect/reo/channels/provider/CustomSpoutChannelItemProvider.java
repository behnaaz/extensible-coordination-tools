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
package org.ect.reo.channels.provider;





import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.ect.reo.ReoFactory;
import org.ect.reo.ReoPackage;
import org.ect.reo.channels.ChannelsFactory;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.components.ComponentsFactory;
import org.ect.reo.provider.ReoPropertyCategories;

/**
 * This is the item provider adapter for a {@link org.ect.reo.channels.CustomSpoutChannel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CustomSpoutChannelItemProvider
	extends SpoutChannelItemProvider
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
	public CustomSpoutChannelItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addTypeURIPropertyDescriptor(object);
			addForegroundColorPropertyDescriptor(object);
			addSynchronousPropertyDescriptor(object);
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
	 * This adds a property descriptor for the Type URI feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addTypeURIPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CustomPrimitive_typeURI_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CustomPrimitive_typeURI_feature", "_UI_CustomPrimitive_type"),
				 ReoPackage.Literals.CUSTOM_PRIMITIVE__TYPE_URI,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 ReoPropertyCategories.BASIC,
				 null));
	}

	/**
	 * This adds a property descriptor for the Foreground Color feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addForegroundColorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CustomPrimitive_foregroundColor_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CustomPrimitive_foregroundColor_feature", "_UI_CustomPrimitive_type"),
				 ReoPackage.Literals.CUSTOM_PRIMITIVE__FOREGROUND_COLOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 ReoPropertyCategories.BASIC,
				 null));
	}

	/**
	 * This adds a property descriptor for the Synchronous feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addSynchronousPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CustomPrimitive_synchronous_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CustomPrimitive_synchronous_feature", "_UI_CustomPrimitive_type"),
				 ReoPackage.Literals.CUSTOM_PRIMITIVE__SYNCHRONOUS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 ReoPropertyCategories.BASIC,
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
			childrenFeatures.add(ReoPackage.Literals.CUSTOM_PRIMITIVE__RESOLVED);
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
	 * This returns CustomSpoutChannel.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CustomSpoutChannel"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CustomSpoutChannel)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_CustomSpoutChannel_type") :
			getString("_UI_CustomSpoutChannel_type") + " " + label;
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

		switch (notification.getFeatureID(CustomSpoutChannel.class)) {
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME:
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__TYPE_URI:
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR:
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED:
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
				(ReoPackage.Literals.CUSTOM_PRIMITIVE__RESOLVED,
				 ChannelsFactory.eINSTANCE.createCustomDirectedChannel()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CUSTOM_PRIMITIVE__RESOLVED,
				 ChannelsFactory.eINSTANCE.createCustomDrainChannel()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CUSTOM_PRIMITIVE__RESOLVED,
				 ChannelsFactory.eINSTANCE.createCustomSpoutChannel()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CUSTOM_PRIMITIVE__RESOLVED,
				 ReoFactory.eINSTANCE.createComponent()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CUSTOM_PRIMITIVE__RESOLVED,
				 ComponentsFactory.eINSTANCE.createReader()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.CUSTOM_PRIMITIVE__RESOLVED,
				 ComponentsFactory.eINSTANCE.createWriter()));
	}

}
