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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * @see org.ect.reo.mcrl2.MCRL2Package#getSummation()
 * @model kind="class"
 * @generated
 */
public class Summation extends Action {
	
	/**
	 * Alternative constructor.
	 * @param parameters Declarations.
	 */
	public Summation(Atom... parameters) {
		super();
		for (Atom atom : parameters) {
			getParameters().add(atom);
		}
	}
		
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		String result = "sum "; 
		for (int i=0; i<getParameters().size(); i++) {
			result = result + getParameters().get(i);
			if (i<getParameters().size()-1) result = result + ", ";
		}
		return result;
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
	 * @generated
	 */
	public Summation() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.SUMMATION;
	}

	/**
	 * @model containment="true"
	 * @generated
	 */
	public EList<Atom> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Atom>(Atom.class, this, MCRL2Package.SUMMATION__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MCRL2Package.SUMMATION__PARAMETERS:
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
			case MCRL2Package.SUMMATION__PARAMETERS:
				return getParameters();
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
			case MCRL2Package.SUMMATION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Atom>)newValue);
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
			case MCRL2Package.SUMMATION__PARAMETERS:
				getParameters().clear();
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
			case MCRL2Package.SUMMATION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

}
