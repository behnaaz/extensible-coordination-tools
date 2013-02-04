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
package org.ect.reo.colouring;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.AnimationPrinter;


/**
 * @see org.ect.reo.colouring.ColouringPackage#getColouringEntry()
 * @model keyType="org.ect.reo.PrimitiveEnd"
 *        valueDataType="org.ect.reo.colouring.FlowColour"
 * @generated
 */
public class ColouringEntry extends MinimalEObjectImpl implements BasicEMap.Entry<PrimitiveEnd,FlowColour>, EObject {
	
	/**
	 * The cached value of the '{@link #getTypedKey() <em>Key</em>}' reference.
	 * @see #getTypedKey()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveEnd key;

	/**
	 * The default value of the '{@link #getTypedValue() <em>Value</em>}' attribute.
	 * @see #getTypedValue()
	 * @generated
	 * @ordered
	 */
	protected static final FlowColour VALUE_EDEFAULT = FlowColour.FLOW_LITERAL;

	/**
	 * The cached value of the '{@link #getTypedValue() <em>Value</em>}' attribute.
	 * @see #getTypedValue()
	 * @generated
	 * @ordered
	 */
	protected FlowColour value = VALUE_EDEFAULT;

	/**
	 * @generated
	 */
	public ColouringEntry() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ColouringPackage.Literals.COLOURING_ENTRY;
	}

	/**
	 * Returns the value of the '<em><b>Key</b></em>' reference.
	 * @return the value of the '<em>Key</em>' reference.
	 * @see #setTypedKey(PrimitiveEnd)
	 * @see org.ect.reo.colouring.ColouringPackage#getColouringEntry_Key()
	 * @model
	 * @generated
	 */
	public PrimitiveEnd getTypedKey() {
		if (key != null && key.eIsProxy()) {
			InternalEObject oldKey = (InternalEObject)key;
			key = (PrimitiveEnd)eResolveProxy(oldKey);
			if (key != oldKey) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ColouringPackage.COLOURING_ENTRY__KEY, oldKey, key));
			}
		}
		return key;
	}

	/**
	 * @generated
	 */
	public PrimitiveEnd basicGetTypedKey() {
		return key;
	}

	/**
	 * Sets the value of the '{@link java.util.Map.Entry#getTypedKey <em>Key</em>}' reference.
	 * @param value the new value of the '<em>Key</em>' reference.
	 * @see #getTypedKey()
	 * @generated
	 */
	public void setTypedKey(PrimitiveEnd newKey) {
		PrimitiveEnd oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ColouringPackage.COLOURING_ENTRY__KEY, oldKey, key));
	}

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The literals are from the enumeration {@link org.ect.reo.colouring.FlowColour}.
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see org.ect.reo.colouring.FlowColour
	 * @see #setTypedValue(FlowColour)
	 * @see org.ect.reo.colouring.ColouringPackage#getColouringEntry_Value()
	 * @model
	 * @generated
	 */
	public FlowColour getTypedValue() {
		return value;
	}

	/**
	 * Sets the value of the '{@link java.util.Map.Entry#getTypedValue <em>Value</em>}' attribute.
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see org.ect.reo.colouring.FlowColour
	 * @see #getTypedValue()
	 * @generated
	 */
	public void setTypedValue(FlowColour newValue) {
		FlowColour oldValue = value;
		value = newValue == null ? VALUE_EDEFAULT : newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ColouringPackage.COLOURING_ENTRY__VALUE, oldValue, value));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ColouringPackage.COLOURING_ENTRY__KEY:
				if (resolve) return getTypedKey();
				return basicGetTypedKey();
			case ColouringPackage.COLOURING_ENTRY__VALUE:
				return getTypedValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ColouringPackage.COLOURING_ENTRY__KEY:
				setTypedKey((PrimitiveEnd)newValue);
				return;
			case ColouringPackage.COLOURING_ENTRY__VALUE:
				setTypedValue((FlowColour)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ColouringPackage.COLOURING_ENTRY__KEY:
				setTypedKey((PrimitiveEnd)null);
				return;
			case ColouringPackage.COLOURING_ENTRY__VALUE:
				setTypedValue(VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ColouringPackage.COLOURING_ENTRY__KEY:
				return key != null;
			case ColouringPackage.COLOURING_ENTRY__VALUE:
				return value != VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		AnimationPrinter printer = new AnimationPrinter();
		return printer.printColouringEntry(getKey(), getValue());
	}

	/**
	 * @generated
	 */
	protected int hash = -1;

	/**
	 * @generated
	 */
	public int getHash() {
		if (hash == -1) {
			Object theKey = getKey();
			hash = (theKey == null ? 0 : theKey.hashCode());
		}
		return hash;
	}

	/**
	 * @generated
	 */
	public void setHash(int hash) {
		this.hash = hash;
	}

	/**
	 * @generated
	 */
	public PrimitiveEnd getKey() {
		return getTypedKey();
	}

	/**
	 * @generated
	 */
	public void setKey(PrimitiveEnd key) {
		setTypedKey(key);
	}

	/**
	 * @generated
	 */
	public FlowColour getValue() {
		return getTypedValue();
	}

	/**
	 * @generated
	 */
	public FlowColour setValue(FlowColour value) {
		FlowColour oldValue = getValue();
		setTypedValue(value);
		return oldValue;
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EMap<PrimitiveEnd, FlowColour> getEMap() {
		EObject container = eContainer();
		return container == null ? null : (EMap<PrimitiveEnd, FlowColour>)container.eGet(eContainmentFeature());
	}

}
