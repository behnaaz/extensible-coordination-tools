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
 * @see org.ect.ea.extensions.ExtensionsPackage#getStringExtension()
 * @model kind="class"
 * @generated
 */
public class StringExtension extends ExtensionElement {

	/**
	 * @generated NOT
	 */
	public StringExtension() {
		super();
	}

	/**
	 * @generated NOT
	 */
	public StringExtension(String value) {
		super();
		setValue(value);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return getValue();
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtensionsPackage.Literals.STRING_EXTENSION;
	}

	/**
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.ect.ea.extensions.ExtensionsPackage#getStringExtension_Value()
	 * @model
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtensionsPackage.STRING_EXTENSION__VALUE, oldValue, value));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtensionsPackage.STRING_EXTENSION__VALUE:
				return getValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExtensionsPackage.STRING_EXTENSION__VALUE:
				setValue((String)newValue);
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
			case ExtensionsPackage.STRING_EXTENSION__VALUE:
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
			case ExtensionsPackage.STRING_EXTENSION__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
		}
		return super.eIsSet(featureID);
	}

} // StringExtension
