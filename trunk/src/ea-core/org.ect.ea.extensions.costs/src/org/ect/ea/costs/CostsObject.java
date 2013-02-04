/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;


import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * @see org.ect.ea.costs.CostsPackage#getCostsObject()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public abstract class CostsObject extends EObjectImpl implements EObject {

	/**
	 * @generated NOT
	 */
	public CostsObject() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public abstract Object getValue();

	
	/**
	 * @generated NOT
	 */
	public abstract Class getType();

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
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final Class TYPE_EDEFAULT = null;

	/**
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final Object VALUE_EDEFAULT = null;

	
	/**
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CostsPackage.Literals.COSTS_OBJECT;
	}

	/**
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CostsPackage.COSTS_OBJECT__TYPE:
				return getType();
			case CostsPackage.COSTS_OBJECT__VALUE:
				return getValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CostsPackage.COSTS_OBJECT__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case CostsPackage.COSTS_OBJECT__VALUE:
				return VALUE_EDEFAULT == null ? getValue() != null : !VALUE_EDEFAULT.equals(getValue());
		}
		return super.eIsSet(featureID);
	}

}
