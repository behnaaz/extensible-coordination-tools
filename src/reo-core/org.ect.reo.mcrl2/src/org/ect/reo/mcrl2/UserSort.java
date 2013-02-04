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
package org.ect.reo.mcrl2;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * @see org.ect.reo.mcrl2.MCRL2Package#getUserSort()
 * @model kind="class"
 * @generated
 */
public class UserSort extends Nameable implements Sort {
	
	/**
	 * @generated NOT
	 * @param name Name.
	 * @param definition Definition.
 	 */
	public UserSort(String name, String definition) {
		super();
		setName(name);
		setDefinition(definition);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return getName() + " = " + getDefinition();
	}

	
	/**
	 * @generated
	 */
	public UserSort() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.USER_SORT;
	}
	
	/**
	 * The default value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_EDEFAULT = null;
	
	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected String definition = DEFINITION_EDEFAULT;

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' attribute.
	 * @return the value of the '<em>Definition</em>' attribute.
	 * @see #setDefinition(String)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getUserSort_Definition()
	 * @model
	 * @generated
	 */
	public String getDefinition() {
		return definition;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.UserSort#getDefinition <em>Definition</em>}' attribute.
	 * @param value the new value of the '<em>Definition</em>' attribute.
	 * @see #getDefinition()
	 * @generated
	 */
	public void setDefinition(String newDefinition) {
		String oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.USER_SORT__DEFINITION, oldDefinition, definition));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.USER_SORT__DEFINITION:
				return getDefinition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MCRL2Package.USER_SORT__DEFINITION:
				setDefinition((String)newValue);
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
			case MCRL2Package.USER_SORT__DEFINITION:
				setDefinition(DEFINITION_EDEFAULT);
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
			case MCRL2Package.USER_SORT__DEFINITION:
				return DEFINITION_EDEFAULT == null ? definition != null : !DEFINITION_EDEFAULT.equals(definition);
		}
		return super.eIsSet(featureID);
	}

}
