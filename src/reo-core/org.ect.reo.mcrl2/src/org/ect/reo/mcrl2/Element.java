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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * @see org.ect.reo.mcrl2.MCRL2Package#getElement()
 * @model kind="class"
 * @generated
 */
public class Element extends Nameable {

	/**
	 * @generated NOT
	 */
	public Element(String name, Atom... parameters) {
		super();
		setName(name);
		for (Atom param : parameters) {
			getParameters().add(param);
		}
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(getName()==null ? "null" : getName());
		if (!getParameters().isEmpty()) {
			result.append("(");
			for (int i=0; i<getParameters().size(); i++) {
				result.append(getParameters().get(i));
				if (i<getParameters().size()-1) result.append(", ");
			}
			result.append(")");
		}
		if (getDiscriminatorName()!=null) {
			result.append("?" + getDiscriminatorName());
		}
		return result.toString();
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	
	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Atom> parameters;
	
	/**
	 * The default value of the '{@link #getDiscriminatorName() <em>Discriminator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiscriminatorName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISCRIMINATOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDiscriminatorName() <em>Discriminator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiscriminatorName()
	 * @generated
	 * @ordered
	 */
	protected String discriminatorName = DISCRIMINATOR_NAME_EDEFAULT;

	/**
	 * @generated
	 */
	public Element() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.ELEMENT;
	}

	/**
	 * Returns the value of the '<em><b>Declarations</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.mcrl2.Declaration}.
	 * @return the value of the '<em>Declarations</em>' containment reference list.
	 * @see org.ect.reo.mcrl2.MCRL2Package#getElement_Declarations()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Atom> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Atom>(Atom.class, this, MCRL2Package.ELEMENT__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * Returns the value of the '<em><b>Discriminator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selector Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Discriminator Name</em>' attribute.
	 * @see #setDiscriminatorName(String)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getElement_DiscriminatorName()
	 * @model
	 * @generated
	 */
	public String getDiscriminatorName() {
		return discriminatorName;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Element#getDiscriminatorName <em>Discriminator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Discriminator Name</em>' attribute.
	 * @see #getDiscriminatorName()
	 * @generated
	 */
	public void setDiscriminatorName(String newDiscriminatorName) {
		String oldDiscriminatorName = discriminatorName;
		discriminatorName = newDiscriminatorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.ELEMENT__DISCRIMINATOR_NAME, oldDiscriminatorName, discriminatorName));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MCRL2Package.ELEMENT__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.ELEMENT__PARAMETERS:
				return getParameters();
			case MCRL2Package.ELEMENT__DISCRIMINATOR_NAME:
				return getDiscriminatorName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MCRL2Package.ELEMENT__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Atom>)newValue);
				return;
			case MCRL2Package.ELEMENT__DISCRIMINATOR_NAME:
				setDiscriminatorName((String)newValue);
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
			case MCRL2Package.ELEMENT__PARAMETERS:
				getParameters().clear();
				return;
			case MCRL2Package.ELEMENT__DISCRIMINATOR_NAME:
				setDiscriminatorName(DISCRIMINATOR_NAME_EDEFAULT);
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
			case MCRL2Package.ELEMENT__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case MCRL2Package.ELEMENT__DISCRIMINATOR_NAME:
				return DISCRIMINATOR_NAME_EDEFAULT == null ? discriminatorName != null : !DISCRIMINATOR_NAME_EDEFAULT.equals(discriminatorName);
		}
		return super.eIsSet(featureID);
	}

}
