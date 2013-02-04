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
package org.ect.reo;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;


/**
 * Properties can be added to components. They are identified using a 
 * key and further have a value and an optional type. The class does not 
 * implement java.util.Map$Entry.
 * 
 * @see org.ect.reo.ReoPackage#getProperty()
 * @model features="key value" 
 * @generated
 */
public class Property extends MinimalEObjectImpl implements EObject {
	
	/**
	 * @generated NOT
	 */
	public Property(String key, String value) {
		super();
		setKey(key);
		setValue(value);
	}
	
	/*
	 * Check if two properties are equal. Not that we do NOT
	 * override java.lang.Object#hashCode() since this messes
	 * up the editing in the Reo editor.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof Property) {
			Property property = (Property) object;
			return ((key==null && property.getKey()==null) || (key!=null && key.equals(property.getKey()))) &&
				   ((type==null && property.getType()==null) || (type!=null && type.equals(property.getType()))) &&
				   ((value==null && property.getValue()==null) || (value!=null && value.equals(property.getValue())));
		}
		return false;
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

	/**
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDDEN_EDEFAULT = false;

	/**
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected boolean hidden = HIDDEN_EDEFAULT;

	/**
	 * @generated
	 */
	public Property() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.PROPERTY;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.PROPERTY__KEY:
				return getKey();
			case ReoPackage.PROPERTY__VALUE:
				return getValue();
			case ReoPackage.PROPERTY__TYPE:
				return getType();
			case ReoPackage.PROPERTY__HIDDEN:
				return isHidden();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReoPackage.PROPERTY__KEY:
				setKey((String)newValue);
				return;
			case ReoPackage.PROPERTY__VALUE:
				setValue((String)newValue);
				return;
			case ReoPackage.PROPERTY__TYPE:
				setType((String)newValue);
				return;
			case ReoPackage.PROPERTY__HIDDEN:
				setHidden((Boolean)newValue);
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
			case ReoPackage.PROPERTY__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case ReoPackage.PROPERTY__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case ReoPackage.PROPERTY__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ReoPackage.PROPERTY__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
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
			case ReoPackage.PROPERTY__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case ReoPackage.PROPERTY__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case ReoPackage.PROPERTY__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ReoPackage.PROPERTY__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (key: ");
		result.append(key);
		result.append(", value: ");
		result.append(value);
		result.append(", type: ");
		result.append(type);
		result.append(", hidden: ");
		result.append(hidden);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @generated
	 */
	public void setKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.PROPERTY__KEY, oldKey, key));
	}

	/**
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.PROPERTY__VALUE, oldValue, value));
	}

	/**
	 * @see #setType(String)
	 * @see org.ect.reo.ReoPackage#getProperty_Type()
	 * @model
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * @see #getType()
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.PROPERTY__TYPE, oldType, type));
	}

	/**
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #setHidden(boolean)
	 * @see org.ect.reo.ReoPackage#getProperty_Hidden()
	 * @model default="false"
	 * @generated
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #isHidden()
	 * @generated
	 */
	public void setHidden(boolean newHidden) {
		boolean oldHidden = hidden;
		hidden = newHidden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.PROPERTY__HIDDEN, oldHidden, hidden));
	}

}
