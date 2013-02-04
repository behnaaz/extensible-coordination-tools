/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.extensions;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * @see org.ect.ea.extensions.ExtensionsPackage#getBooleanExtension()
 * @model kind="class"
 * @generated
 */
public class BooleanExtension extends ExtensionElement {
		
	/**
	 * @generated NOT
	 */
	public BooleanExtension() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public BooleanExtension(boolean value) {
		super();
		setValue(value);
	}

	/**
	 * @generated NOT
	 */
	public boolean getValue() {
		return value;
	}
	
	/**
	 * @generated NOT
	 */
	public void flipValue() {
		setValue(!getValue());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return String.valueOf(getValue());
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
	protected static final boolean VALUE_EDEFAULT = false;

	/**
	 * @generated
	 * @ordered
	 */
	protected boolean value = VALUE_EDEFAULT;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtensionsPackage.Literals.BOOLEAN_EXTENSION;
	}

	/**
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(boolean)
	 * @see org.ect.ea.extensions.ExtensionsPackage#getBooleanExtension_Value()
	 * @model
	 * @generated
	 */
	public boolean isValue() {
		return value;
	}

	/**
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #isValue()
	 * @generated
	 */
	public void setValue(boolean newValue) {
		boolean oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtensionsPackage.BOOLEAN_EXTENSION__VALUE, oldValue, value));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtensionsPackage.BOOLEAN_EXTENSION__VALUE:
				return isValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExtensionsPackage.BOOLEAN_EXTENSION__VALUE:
				setValue((Boolean)newValue);
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
			case ExtensionsPackage.BOOLEAN_EXTENSION__VALUE:
				setValue(VALUE_EDEFAULT);
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
			case ExtensionsPackage.BOOLEAN_EXTENSION__VALUE:
				return value != VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} // BooleanExtension
