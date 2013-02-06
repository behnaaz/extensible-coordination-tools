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
package org.ect.ea.extensions.costs;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.costs.CostsObject;
import org.ect.ea.extensions.ExtensionElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.costs.CostsObjectExtension#getCostsObjects <em>Costs Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsObjectExtension()
 * @model kind="class"
 * @generated
 */
public class CostsObjectExtension extends ExtensionElement {
	
	/**
	 * The cached value of the '{@link #getCostsObjects() <em>Costs Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostsObjects()
	 * @generated
	 * @ordered
	 */
	protected EList<CostsObject> costsObjects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public CostsObjectExtension() {
		super();
		setId(CostValuesProvider.EXTENSION_ID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CostsPackage.Literals.COSTS_OBJECT_EXTENSION;
	}

	/**
	 * Returns the value of the '<em><b>Costs Objects</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.ea.costs.CostsObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Costs Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Costs Objects</em>' containment reference list.
	 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsObjectExtension_CostsObjects()
	 * @model containment="true"
	 * @generated
	 */
	public EList<CostsObject> getCostsObjects() {
		if (costsObjects == null) {
			costsObjects = new EObjectContainmentEList<CostsObject>(CostsObject.class, this, CostsPackage.COSTS_OBJECT_EXTENSION__COSTS_OBJECTS);
		}
		return costsObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CostsPackage.COSTS_OBJECT_EXTENSION__COSTS_OBJECTS:
				return ((InternalEList<?>)getCostsObjects()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CostsPackage.COSTS_OBJECT_EXTENSION__COSTS_OBJECTS:
				return getCostsObjects();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CostsPackage.COSTS_OBJECT_EXTENSION__COSTS_OBJECTS:
				getCostsObjects().clear();
				getCostsObjects().addAll((Collection<? extends CostsObject>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CostsPackage.COSTS_OBJECT_EXTENSION__COSTS_OBJECTS:
				getCostsObjects().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CostsPackage.COSTS_OBJECT_EXTENSION__COSTS_OBJECTS:
				return costsObjects != null && !costsObjects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // CostsObjectExtension
