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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.channels.FIFO;
import org.ect.reo.provider.ReoPropertyCategories;
import org.ect.reo.provider.util.DelayableItemPropertyDescriptor;
import org.ect.reo.provider.util.DelayableItemPropertyDescriptor.DelayType;


/**
 * This is the item provider adapter for a {@link org.ect.reo.channels.FIFO} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FIFOItemProvider
	extends DirectedChannelItemProvider
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
	public FIFOItemProvider(AdapterFactory adapterFactory) {
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

			addFullPropertyDescriptor(object);
			addCellNamePropertyDescriptor(object);
			addInitialValuePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}
	
	/**
	 * This adds a property descriptor for the Processing Delay feature.
	 * @generated NOT
	 */
	@Override
	protected void addProcessingDelayPropertyDescriptor(Object object) {
		
		// We add two processing delay properties:
		ComposeableAdapterFactory factory = ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory();
		itemPropertyDescriptors.add(
				new DelayableItemPropertyDescriptor("Processing delay (from source to buffer)", 0, DelayType.PROCESSING_DELAY, factory, getResourceLocator()));
		itemPropertyDescriptors.add(
				new DelayableItemPropertyDescriptor("Processing delay (from buffer to sink)", 1, DelayType.PROCESSING_DELAY, factory, getResourceLocator()));
		
	}
	
	
	/**
	 * This adds a property descriptor for the Full feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addFullPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FIFO_full_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FIFO_full_feature", "_UI_FIFO_type"),
				 ChannelsPackage.Literals.FIFO__FULL,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 ReoPropertyCategories.BASIC,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cell Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addCellNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FIFO_cellName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FIFO_cellName_feature", "_UI_FIFO_type"),
				 ChannelsPackage.Literals.FIFO__CELL_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 ReoPropertyCategories.BASIC,
				 null));
	}

	/**
	 * This adds a property descriptor for the Initial Value feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addInitialValuePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FIFO_initialValue_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FIFO_initialValue_feature", "_UI_FIFO_type"),
				 ChannelsPackage.Literals.FIFO__INITIAL_VALUE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 ReoPropertyCategories.BASIC,
				 null));
	}

	/**
	 * This returns FIFO.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FIFO"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FIFO)object).getCellName();
		return label == null || label.length() == 0 ?
			getString("_UI_FIFO_type") :
			getString("_UI_FIFO_type") + " " + label;
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

		switch (notification.getFeatureID(FIFO.class)) {
			case ChannelsPackage.FIFO__FULL:
			case ChannelsPackage.FIFO__CELL_NAME:
			case ChannelsPackage.FIFO__INITIAL_VALUE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}
