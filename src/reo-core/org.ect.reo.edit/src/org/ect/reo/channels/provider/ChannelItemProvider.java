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

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.provider.PrimitiveItemProvider;
import org.ect.reo.provider.ReoEditPlugin;
import org.ect.reo.provider.ReoPropertyCategories;
import org.ect.reo.provider.util.DelayableItemPropertyDescriptor;
import org.ect.reo.provider.util.DelayableItemPropertyDescriptor.DelayType;

/**
 * This is the item provider adapter for a {@link org.ect.reo.channels.Channel} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ChannelItemProvider
	extends PrimitiveItemProvider
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
	public ChannelItemProvider(AdapterFactory adapterFactory) {
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

			//addChannelEndOnePropertyDescriptor(object);
			//addChannelEndTwoPropertyDescriptor(object);
			//addNodeOnePropertyDescriptor(object);
			//addNodeTwoPropertyDescriptor(object);
			addChannelEndNameOnePropertyDescriptor(object);
			addChannelEndNameTwoPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Channel End One feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addChannelEndOnePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Channel_channelEndOne_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Channel_channelEndOne_feature", "_UI_Channel_type"),
				 ChannelsPackage.Literals.CHANNEL__CHANNEL_END_ONE,
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Channel End Two feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addChannelEndTwoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Channel_channelEndTwo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Channel_channelEndTwo_feature", "_UI_Channel_type"),
				 ChannelsPackage.Literals.CHANNEL__CHANNEL_END_TWO,
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Node One feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNodeOnePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Channel_nodeOne_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Channel_nodeOne_feature", "_UI_Channel_type"),
				 ChannelsPackage.Literals.CHANNEL__NODE_ONE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Node Two feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNodeTwoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Channel_nodeTwo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Channel_nodeTwo_feature", "_UI_Channel_type"),
				 ChannelsPackage.Literals.CHANNEL__NODE_TWO,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Channel End Name One feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addChannelEndNameOnePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Channel_channelEndNameOne_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Channel_channelEndNameOne_feature", "_UI_Channel_type"),
				 ChannelsPackage.Literals.CHANNEL__CHANNEL_END_NAME_ONE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 ReoPropertyCategories.ENDS,
				 null));
	}

	/**
	 * This adds a property descriptor for the Channel End Name Two feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addChannelEndNameTwoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Channel_channelEndNameTwo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Channel_channelEndNameTwo_feature", "_UI_Channel_type"),
				 ChannelsPackage.Literals.CHANNEL__CHANNEL_END_NAME_TWO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 ReoPropertyCategories.ENDS,
				 null));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Channel)object).getChannelEndNameOne();
		return label == null || label.length() == 0 ?
			getString("_UI_Channel_type") :
			getString("_UI_Channel_type") + " " + label;
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
				new DelayableItemPropertyDescriptor("Processing delay (data flow)", 0, DelayType.PROCESSING_DELAY, factory, getResourceLocator()));	
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

		switch (notification.getFeatureID(Channel.class)) {
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_ONE:
			case ChannelsPackage.CHANNEL__CHANNEL_END_NAME_TWO:
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
