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
package org.ect.ea.costs.types;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.ect.ea.costs.CostsObject;

/**
 * @see org.ect.ea.costs.types.TypesPackage#getIntegerCosts()
 * @model kind="class"
 * @generated
 */
public class IntegerCosts extends CostsObject {

	/**
	 * @generated NOT
	 */
	public IntegerCosts() {
		super();
	}


	/**
	 * @generated NOT
	 */
	public IntegerCosts(int value) {
		super();
		setIntegerValue(value);
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.costs.CostsObject#getType()
 	 * @generated NOT
	 */
	public Class getType() {
		return Integer.class;
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.costs.CostsObject#getValue()
	 * @generated NOT
	 */
	public Object getValue() {
		return getIntegerValue();
	}

	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	/**
	 * @see #getIntegerValue()
	 * @generated
	 * @ordered
	 */
	protected static final int INTEGER_VALUE_EDEFAULT = 0;

	/**
	 * @see #getIntegerValue()
	 * @generated
	 * @ordered
	 */
	protected int integerValue = INTEGER_VALUE_EDEFAULT;

	/**
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TypesPackage.Literals.INTEGER_COSTS;
	}

	/**
	 * @return The value of the '<em>Integer Value</em>' attribute.
	 * @see #setIntegerValue(int)
	 * @see org.ect.ea.costs.types.TypesPackage#getIntegerCosts_IntegerValue()
	 * @model
	 * @generated
	 */
	public int getIntegerValue() {
		return integerValue;
	}

	/**
	 * @param value The new value of the '<em>Integer Value</em>' attribute.
	 * @see #getIntegerValue()
	 * @generated
	 */
	public void setIntegerValue(int newIntegerValue) {
		int oldIntegerValue = integerValue;
		integerValue = newIntegerValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.INTEGER_COSTS__INTEGER_VALUE, oldIntegerValue, integerValue));
	}

	/**
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.INTEGER_COSTS__INTEGER_VALUE:
				return new Integer(getIntegerValue());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypesPackage.INTEGER_COSTS__INTEGER_VALUE:
				setIntegerValue(((Integer)newValue).intValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case TypesPackage.INTEGER_COSTS__INTEGER_VALUE:
				setIntegerValue(INTEGER_VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TypesPackage.INTEGER_COSTS__INTEGER_VALUE:
				return integerValue != INTEGER_VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (integerValue: ");
		result.append(integerValue);
		result.append(')');
		return result.toString();
	}

}
