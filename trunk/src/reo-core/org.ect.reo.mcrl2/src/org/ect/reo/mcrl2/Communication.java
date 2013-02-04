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
 * @generated
 */
public class Communication extends Action {
	
	/**
	 * @generated NOT
	 */
	public String toString() {
		StringBuffer result = new StringBuffer("comm({");
		for (int i=0; i<getImplications().size(); i++) {
			result.append(getImplications().get(i));
			if (i<getImplications().size()-1) result.append(", ");
		}
		result.append("}");
		return result.toString() + ",\n" + getParallelism() + ")";	
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	
	/**
	 * The cached value of the '{@link #getImplications() <em>Implications</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplications()
	 * @generated
	 * @ordered
	 */
	protected EList<Implication> implications;

	/**
	 * The cached value of the '{@link #getParallelism() <em>Parallelism</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallelism()
	 * @generated
	 * @ordered
	 */
	protected Parallelism parallelism;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Communication() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.COMMUNICATION;
	}

	/**
	 * Returns the value of the '<em><b>Implications</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.mcrl2.Implication}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implications</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implications</em>' containment reference list.
	 * @see org.ect.reo.mcrl2.MCRL2Package#getCommunication_Implications()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Implication> getImplications() {
		if (implications == null) {
			implications = new EObjectContainmentEList<Implication>(Implication.class, this, MCRL2Package.COMMUNICATION__IMPLICATIONS);
		}
		return implications;
	}

	/**
	 * Returns the value of the '<em><b>Parallelism</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parallelism</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parallelism</em>' containment reference.
	 * @see #setParallelism(Parallelism)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getCommunication_Parallelism()
	 * @model containment="true"
	 * @generated
	 */
	public Parallelism getParallelism() {
		return parallelism;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParallelism(Parallelism newParallelism, NotificationChain msgs) {
		Parallelism oldParallelism = parallelism;
		parallelism = newParallelism;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MCRL2Package.COMMUNICATION__PARALLELISM, oldParallelism, newParallelism);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Communication#getParallelism <em>Parallelism</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallelism</em>' containment reference.
	 * @see #getParallelism()
	 * @generated
	 */
	public void setParallelism(Parallelism newParallelism) {
		if (newParallelism != parallelism) {
			NotificationChain msgs = null;
			if (parallelism != null)
				msgs = ((InternalEObject)parallelism).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.COMMUNICATION__PARALLELISM, null, msgs);
			if (newParallelism != null)
				msgs = ((InternalEObject)newParallelism).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.COMMUNICATION__PARALLELISM, null, msgs);
			msgs = basicSetParallelism(newParallelism, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.COMMUNICATION__PARALLELISM, newParallelism, newParallelism));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MCRL2Package.COMMUNICATION__IMPLICATIONS:
				return ((InternalEList<?>)getImplications()).basicRemove(otherEnd, msgs);
			case MCRL2Package.COMMUNICATION__PARALLELISM:
				return basicSetParallelism(null, msgs);
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
			case MCRL2Package.COMMUNICATION__IMPLICATIONS:
				return getImplications();
			case MCRL2Package.COMMUNICATION__PARALLELISM:
				return getParallelism();
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
			case MCRL2Package.COMMUNICATION__IMPLICATIONS:
				getImplications().clear();
				getImplications().addAll((Collection<? extends Implication>)newValue);
				return;
			case MCRL2Package.COMMUNICATION__PARALLELISM:
				setParallelism((Parallelism)newValue);
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
			case MCRL2Package.COMMUNICATION__IMPLICATIONS:
				getImplications().clear();
				return;
			case MCRL2Package.COMMUNICATION__PARALLELISM:
				setParallelism((Parallelism)null);
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
			case MCRL2Package.COMMUNICATION__IMPLICATIONS:
				return implications != null && !implications.isEmpty();
			case MCRL2Package.COMMUNICATION__PARALLELISM:
				return parallelism != null;
		}
		return super.eIsSet(featureID);
	}

} // Communication
