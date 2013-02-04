/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs.types;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.ect.ea.costs.CostsObject;

/**
 * @see org.ect.ea.costs.types.TypesPackage#getFloatCosts()
 * @model kind="class"
 * @generated
 */
public class FloatCosts extends CostsObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated Not
	 */
	public FloatCosts() {
		super();
	}

	public FloatCosts(float value) {
		super();
		setFloatValue(value);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.costs.CostsObject#getType()
 	 * @generated NOT
	 */
	public Class getType() {
		return Float.class;
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.costs.CostsObject#getValue()
	 * @generated NOT
	 */
	public Object getValue() {
		return getFloatValue();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	/**
	 * The default value of the '{@link #getFloatValue() <em>Float Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatValue()
	 * @generated
	 * @ordered
	 */
	protected static final float FLOAT_VALUE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getFloatValue() <em>Float Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFloatValue()
	 * @generated
	 * @ordered
	 */
	protected float floatValue = FLOAT_VALUE_EDEFAULT;
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TypesPackage.Literals.FLOAT_COSTS;
	}

	/**
	 * Returns the value of the '<em><b>Float Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Float Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Float Value</em>' attribute.
	 * @see #setFloatValue(float)
	 * @see org.ect.ea.costs.types.TypesPackage#getFloatCosts_FloatValue()
	 * @model
	 * @generated
	 */
	public float getFloatValue() {
		return floatValue;
	}

	/**
	 * Sets the value of the '{@link org.ect.ea.costs.types.FloatCosts#getFloatValue <em>Float Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Float Value</em>' attribute.
	 * @see #getFloatValue()
	 * @generated
	 */
	public void setFloatValue(float newFloatValue) {
		float oldFloatValue = floatValue;
		floatValue = newFloatValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.FLOAT_COSTS__FLOAT_VALUE, oldFloatValue, floatValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.FLOAT_COSTS__FLOAT_VALUE:
				return new Float(getFloatValue());
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypesPackage.FLOAT_COSTS__FLOAT_VALUE:
				setFloatValue(((Float)newValue).floatValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case TypesPackage.FLOAT_COSTS__FLOAT_VALUE:
				setFloatValue(FLOAT_VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TypesPackage.FLOAT_COSTS__FLOAT_VALUE:
				return floatValue != FLOAT_VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (floatValue: ");
		result.append(floatValue);
		result.append(')');
		return result.toString();
	}

} // FloatCosts