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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * @generated
 */
public class Implication extends Action {
	
	/**
	 * @generated NOT
	 */
	public Implication(String condition, Action action) {
		super();
		setCondition(condition);
		setAction(action);
	}
	
	/**
	 * @generated NOT
	 */
	public Implication(String condition, Action action, Action elseAction) {
		this(condition, action);
		setElseAction(elseAction);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		
		String result;
		if ((condition==null || condition.equals("true")) && elseAction==null) {
			
			// Simplified case:
			result = action.toString();
			
		} else {
			
			// Default case:
			result = condition + " -> " + action;
			if (elseAction!=null) {
				result = result + " <> " + elseAction;
			}
			
		}
		
		// Determine whether parenthesis are required:
		EObject cont = eContainer();
		return (cont==null || cont instanceof Process || 
				cont instanceof Communication) ? 
				result : "(" + result + ")";
	}

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected String condition = CONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected Action action;

	/**
	 * The cached value of the '{@link #getElseAction() <em>Else Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseAction()
	 * @generated
	 * @ordered
	 */
	protected Action elseAction;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Implication() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.IMPLICATION;
	}

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getImplication_Condition()
	 * @model
	 * @generated
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Implication#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	public void setCondition(String newCondition) {
		String oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.IMPLICATION__CONDITION, oldCondition, condition));
	}

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(Action)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getImplication_Action()
	 * @model containment="true"
	 * @generated
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAction(Action newAction, NotificationChain msgs) {
		Action oldAction = action;
		action = newAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MCRL2Package.IMPLICATION__ACTION, oldAction, newAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Implication#getAction <em>Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	public void setAction(Action newAction) {
		if (newAction != action) {
			NotificationChain msgs = null;
			if (action != null)
				msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.IMPLICATION__ACTION, null, msgs);
			if (newAction != null)
				msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.IMPLICATION__ACTION, null, msgs);
			msgs = basicSetAction(newAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.IMPLICATION__ACTION, newAction, newAction));
	}

	/**
	 * Returns the value of the '<em><b>Else Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Action</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Action</em>' containment reference.
	 * @see #setElseAction(Action)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getImplication_ElseAction()
	 * @model containment="true"
	 * @generated
	 */
	public Action getElseAction() {
		return elseAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseAction(Action newElseAction, NotificationChain msgs) {
		Action oldElseAction = elseAction;
		elseAction = newElseAction;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MCRL2Package.IMPLICATION__ELSE_ACTION, oldElseAction, newElseAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Implication#getElseAction <em>Else Action</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else Action</em>' containment reference.
	 * @see #getElseAction()
	 * @generated
	 */
	public void setElseAction(Action newElseAction) {
		if (newElseAction != elseAction) {
			NotificationChain msgs = null;
			if (elseAction != null)
				msgs = ((InternalEObject)elseAction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.IMPLICATION__ELSE_ACTION, null, msgs);
			if (newElseAction != null)
				msgs = ((InternalEObject)newElseAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.IMPLICATION__ELSE_ACTION, null, msgs);
			msgs = basicSetElseAction(newElseAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.IMPLICATION__ELSE_ACTION, newElseAction, newElseAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MCRL2Package.IMPLICATION__ACTION:
				return basicSetAction(null, msgs);
			case MCRL2Package.IMPLICATION__ELSE_ACTION:
				return basicSetElseAction(null, msgs);
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
			case MCRL2Package.IMPLICATION__CONDITION:
				return getCondition();
			case MCRL2Package.IMPLICATION__ACTION:
				return getAction();
			case MCRL2Package.IMPLICATION__ELSE_ACTION:
				return getElseAction();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MCRL2Package.IMPLICATION__CONDITION:
				setCondition((String)newValue);
				return;
			case MCRL2Package.IMPLICATION__ACTION:
				setAction((Action)newValue);
				return;
			case MCRL2Package.IMPLICATION__ELSE_ACTION:
				setElseAction((Action)newValue);
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
			case MCRL2Package.IMPLICATION__CONDITION:
				setCondition(CONDITION_EDEFAULT);
				return;
			case MCRL2Package.IMPLICATION__ACTION:
				setAction((Action)null);
				return;
			case MCRL2Package.IMPLICATION__ELSE_ACTION:
				setElseAction((Action)null);
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
			case MCRL2Package.IMPLICATION__CONDITION:
				return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
			case MCRL2Package.IMPLICATION__ACTION:
				return action != null;
			case MCRL2Package.IMPLICATION__ELSE_ACTION:
				return elseAction != null;
		}
		return super.eIsSet(featureID);
	}

} // Implication
