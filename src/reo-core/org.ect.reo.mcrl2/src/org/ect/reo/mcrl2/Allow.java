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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * @generated
 */
public class Allow extends Action {
	
	/**
	 * @generated NOT
	 */
	public String toString() {
		StringBuffer result = new StringBuffer("allow({");
		for (int i=0; i<getAtoms().size(); i++) {
			result.append(getAtoms().get(i).getName());
			if (i<getAtoms().size()-1) result.append(", ");
		}
		result.append("}");
		return result.toString() + ",\n" + getChild() + ")";
	}

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The cached value of the '{@link #getChild() <em>Child</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChild()
	 * @generated
	 * @ordered
	 */
	protected Action child;

	/**
	 * The cached value of the '{@link #getAtoms() <em>Atoms</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtoms()
	 * @generated
	 * @ordered
	 */
	protected EList<Atom> atoms;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Allow() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.ALLOW;
	}

	/**
	 * Returns the value of the '<em><b>Child</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child</em>' containment reference.
	 * @see #setChild(Action)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAllow_Child()
	 * @model containment="true"
	 * @generated
	 */
	public Action getChild() {
		return child;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChild(Action newChild, NotificationChain msgs) {
		Action oldChild = child;
		child = newChild;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MCRL2Package.ALLOW__CHILD, oldChild, newChild);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Allow#getChild <em>Child</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Child</em>' containment reference.
	 * @see #getChild()
	 * @generated
	 */
	public void setChild(Action newChild) {
		if (newChild != child) {
			NotificationChain msgs = null;
			if (child != null)
				msgs = ((InternalEObject)child).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.ALLOW__CHILD, null, msgs);
			if (newChild != null)
				msgs = ((InternalEObject)newChild).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.ALLOW__CHILD, null, msgs);
			msgs = basicSetChild(newChild, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.ALLOW__CHILD, newChild, newChild));
	}

	/**
	 * Returns the value of the '<em><b>Atoms</b></em>' reference list.
	 * The list contents are of type {@link org.ect.reo.mcrl2.Atom}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Atoms</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Atoms</em>' reference list.
	 * @see org.ect.reo.mcrl2.MCRL2Package#getAllow_Atoms()
	 * @model
	 * @generated
	 */
	public EList<Atom> getAtoms() {
		if (atoms == null) {
			atoms = new EObjectResolvingEList<Atom>(Atom.class, this, MCRL2Package.ALLOW__ATOMS);
		}
		return atoms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MCRL2Package.ALLOW__CHILD:
				return basicSetChild(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.ALLOW__CHILD:
				return getChild();
			case MCRL2Package.ALLOW__ATOMS:
				return getAtoms();
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
			case MCRL2Package.ALLOW__CHILD:
				setChild((Action)newValue);
				return;
			case MCRL2Package.ALLOW__ATOMS:
				getAtoms().clear();
				getAtoms().addAll((Collection<? extends Atom>)newValue);
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
			case MCRL2Package.ALLOW__CHILD:
				setChild((Action)null);
				return;
			case MCRL2Package.ALLOW__ATOMS:
				getAtoms().clear();
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
			case MCRL2Package.ALLOW__CHILD:
				return child != null;
			case MCRL2Package.ALLOW__ATOMS:
				return atoms != null && !atoms.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // Allow
