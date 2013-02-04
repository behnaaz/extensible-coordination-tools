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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * @generated
 */
public class AtomicAction extends Action {
	
	/**
	 * @generated NOT
	 */
	public AtomicAction(Atom atom, String... arguments) {
		super();
		setAtom(atom);
		for (String arg : arguments) {
			getArguments().add(arg);
		}
	}
		
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		String result = getAtom()==null ? "null" : getAtom().getName();
		if (!getArguments().isEmpty()) {
			result = result + "(";
			for (int i=0; i<getArguments().size(); i++) {
				result = result + getArguments().get(i);
				if (i<getArguments().size()-1) result = result + ", ";
			}
			result = result + ")";
		}
		if (getTime()!=null) {
			result = result + "@" + getTime();
		}
		return result;
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The cached value of the '{@link #getAtom() <em>Atom</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtom()
	 * @generated
	 * @ordered
	 */
	protected Atom atom;

	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<String> arguments;

	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected String time = TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AtomicAction() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.ATOMIC_ACTION;
	}

	/**
	 * Returns the value of the '<em><b>Atom</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.ect.reo.mcrl2.Atom#getReferences <em>References</em>}'.
	 * @return the value of the '<em>Atom</em>' reference.
	 * @see #setAtom(Atom)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAtomicAction_Atom()
	 * @see org.ect.reo.mcrl2.Atom#getReferences
	 * @model opposite="references"
	 * @generated
	 */
	public Atom getAtom() {
		if (atom != null && atom.eIsProxy()) {
			InternalEObject oldAtom = (InternalEObject)atom;
			atom = (Atom)eResolveProxy(oldAtom);
			if (atom != oldAtom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MCRL2Package.ATOMIC_ACTION__ATOM, oldAtom, atom));
			}
		}
		return atom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Atom basicGetAtom() {
		return atom;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.AtomicAction#getAtom <em>Atom</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Atom</em>' reference.
	 * @see #getAtom()
	 * @generated
	 */
	public void setAtom(Atom newAtom) {
		Atom oldAtom = atom;
		atom = newAtom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.ATOMIC_ACTION__ATOM, oldAtom, atom));
	}

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * @return the value of the '<em>Arguments</em>' attribute list.
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAtomicAction_Arguments()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getArguments() {
		if (arguments == null) {
			arguments = new EDataTypeEList<String>(String.class, this, MCRL2Package.ATOMIC_ACTION__ARGUMENTS);
		}
		return arguments;
	}

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(String)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAtomicAction_Time()
	 * @model
	 * @generated
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.AtomicAction#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	public void setTime(String newTime) {
		String oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.ATOMIC_ACTION__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.ATOMIC_ACTION__ATOM:
				if (resolve) return getAtom();
				return basicGetAtom();
			case MCRL2Package.ATOMIC_ACTION__ARGUMENTS:
				return getArguments();
			case MCRL2Package.ATOMIC_ACTION__TIME:
				return getTime();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MCRL2Package.ATOMIC_ACTION__ATOM:
				setAtom((Atom)newValue);
				return;
			case MCRL2Package.ATOMIC_ACTION__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends String>)newValue);
				return;
			case MCRL2Package.ATOMIC_ACTION__TIME:
				setTime((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MCRL2Package.ATOMIC_ACTION__ATOM:
				setAtom((Atom)null);
				return;
			case MCRL2Package.ATOMIC_ACTION__ARGUMENTS:
				getArguments().clear();
				return;
			case MCRL2Package.ATOMIC_ACTION__TIME:
				setTime(TIME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MCRL2Package.ATOMIC_ACTION__ATOM:
				return atom != null;
			case MCRL2Package.ATOMIC_ACTION__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
			case MCRL2Package.ATOMIC_ACTION__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
		}
		return super.eIsSet(featureID);
	}

} // AtomicAction
