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
import org.ect.reo.Primitive;
import org.ect.reo.ReoFactory;
import org.ect.reo.ReoPackage;


/**
 * This is the item provider adapter for a {@link org.ect.reo.Primitive} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PrimitiveItemProvider
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
	public PrimitiveItemProvider(AdapterFactory adapterFactory) {
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

			addArrivalRatePropertyDescriptor(object);
			addProcessingDelayPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Arrival Rate feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addArrivalRatePropertyDescriptor(Object object) {
		// No arrival rates for primitives
//		itemPropertyDescriptors.add
//			(createItemPropertyDescriptor
//				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
//				 getResourceLocator(),
//				 getString("_UI_Delayable_arrivalRate_feature"),
//				 getString("_UI_PropertyDescriptor_description", "_UI_Delayable_arrivalRate_feature", "_UI_Delayable_type"),
//				 ReoPackage.Literals.DELAYABLE__ARRIVAL_RATE,
//				 true,
//				 false,
//				 false,
//				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
//				 ReoPropertyCategories.PERFORMANCE,
//				 null));
	}

	/**
	 * This adds a property descriptor for the Processing Delay feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addProcessingDelayPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delayable_processingDelay_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delayable_processingDelay_feature", "_UI_Delayable_type"),
				 ReoPackage.Literals.DELAYABLE__PROCESSING_DELAY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 ReoPropertyCategories.PERFORMANCE,
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
			childrenFeatures.add(ReoPackage.Literals.PRIMITIVE__SOURCE_ENDS);
			childrenFeatures.add(ReoPackage.Literals.PRIMITIVE__SINK_ENDS);
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
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_Primitive_type");
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

		switch (notification.getFeatureID(Primitive.class)) {
			case ReoPackage.PRIMITIVE__ARRIVAL_RATE:
			case ReoPackage.PRIMITIVE__PROCESSING_DELAY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ReoPackage.PRIMITIVE__RECONF_ACTIONS:
			case ReoPackage.PRIMITIVE__PROPERTIES:
			case ReoPackage.PRIMITIVE__SOURCE_ENDS:
			case ReoPackage.PRIMITIVE__SINK_ENDS:
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
				(ReoPackage.Literals.PRIMITIVE__SOURCE_ENDS,
				 ReoFactory.eINSTANCE.createSourceEnd()));

		newChildDescriptors.add
			(createChildParameter
				(ReoPackage.Literals.PRIMITIVE__SINK_ENDS,
				 ReoFactory.eINSTANCE.createSinkEnd()));
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
