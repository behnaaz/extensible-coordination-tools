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

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * @generated
 */
public class Atom extends Nameable {
	
	/**
	 * Tau atom.
	 * @generated NOT
	 */
	public static final Atom TAU = new Atom("tau");
	
	/**
	 * @generated NOT
	 */
	public Atom(String name) {
		super();
		setName(name);
	}	
	
	/**
	 * @generated NOT
	 * @param name Name of the variable.
	 * @param type Its type.
	 */
	public Atom(String name, Sort type) {
		this(name);
		setType(type);
	}

	/**
	 * @generated NOT
	 * @param name Name of the variable.
	 * @param type Its type.
	 * @param initial Intial value.
	 */
	public Atom(String name, Sort type, String initial) {
		this(name, type);
		setInitial(initial);
	}
		
	/**
	 * @generated NOT
	 */
	public static void removeUnnamed(List<? extends Atom> actions) {
		for (int i=0; i<actions.size(); i++) {
			if (actions.get(i).getName()==null) actions.remove(i--);
		}
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return getType()!=null ? getName() + " : " + getType().getName() : getName();
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Sort type;
	/**
	 * The default value of the '{@link #getInitial() <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitial()
	 * @generated
	 * @ordered
	 */
	protected static final String INITIAL_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getInitial() <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitial()
	 * @generated
	 * @ordered
	 */
	protected String initial = INITIAL_EDEFAULT;

	/**
	 * @generated
	 */
	public Atom() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.ATOM;
	}

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Sort)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAtom_Type()
	 * @model
	 * @generated
	 */
	public Sort getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Sort)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MCRL2Package.ATOM__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sort basicGetType() {
		return type;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Atom#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	public void setType(Sort newType) {
		Sort oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.ATOM__TYPE, oldType, type));
	}

	/**
	 * Returns the value of the '<em><b>Initial</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial</em>' attribute.
	 * @see #setInitial(String)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAtom_Initial()
	 * @model
	 * @generated
	 */
	public String getInitial() {
		return initial;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Atom#getInitial <em>Initial</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial</em>' attribute.
	 * @see #getInitial()
	 * @generated
	 */
	public void setInitial(String newInitial) {
		String oldInitial = initial;
		initial = newInitial;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.ATOM__INITIAL, oldInitial, initial));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.ATOM__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case MCRL2Package.ATOM__INITIAL:
				return getInitial();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MCRL2Package.ATOM__TYPE:
				setType((Sort)newValue);
				return;
			case MCRL2Package.ATOM__INITIAL:
				setInitial((String)newValue);
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
			case MCRL2Package.ATOM__TYPE:
				setType((Sort)null);
				return;
			case MCRL2Package.ATOM__INITIAL:
				setInitial(INITIAL_EDEFAULT);
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
			case MCRL2Package.ATOM__TYPE:
				return type != null;
			case MCRL2Package.ATOM__INITIAL:
				return INITIAL_EDEFAULT == null ? initial != null : !INITIAL_EDEFAULT.equals(initial);
		}
		return super.eIsSet(featureID);
	}

}
