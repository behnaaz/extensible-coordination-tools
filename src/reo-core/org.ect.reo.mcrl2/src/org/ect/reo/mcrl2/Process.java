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
public class Process extends Nameable {
	
	/**
	 * @generated NOT
	 */
	public Process(String name) {
		super();
		setName(name);
	}
	
	/**
	 * @generated NOT
	 */
	public Instance newInstance() {
		Instance instance = new Instance(this);
		for (Atom param : getParameters()) {
			instance.getArguments().add(param.getInitial());
		}
		return instance;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		result.append(name);
		
		// Check parameters.
		if (!getParameters().isEmpty()) {
			result.append("(");
			for (int i=0; i<getParameters().size(); i++) {
				result.append(getParameters().get(i));
				if (i<getParameters().size()-1) result.append(",");
			}
			result.append(")");
		}
		
		result.append(" = ");
		
		// Append the definition.
		String def = (getAction()!=null) ? getAction().toString().trim() : "";
		if (!def.endsWith(";")) def = def + ";";
		result.append(def);
		
		return  result.toString();
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Atom> parameters;

	/**
	 * The default value of the '{@link #isInitial() <em>Initial</em>}' attribute.
	 * @see #isInitial()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INITIAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInitial() <em>Initial</em>}' attribute.
	 * @see #isInitial()
	 * @generated
	 * @ordered
	 */
	protected boolean initial = INITIAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference.
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected Action action;

	/**
	 * @generated
	 */
	public Process() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.PROCESS;
	}

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.mcrl2.Declaration}.
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.ect.reo.mcrl2.MCRL2Package#getProcess_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Atom> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Atom>(Atom.class, this, MCRL2Package.PROCESS__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * Returns the value of the '<em><b>Initial</b></em>' attribute.
	 * @return the value of the '<em>Initial</em>' attribute.
	 * @see #setInitial(boolean)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getProcess_Initial()
	 * @model
	 * @generated
	 */
	public boolean isInitial() {
		return initial;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Process#isInitial <em>Initial</em>}' attribute.
	 * @param value the new value of the '<em>Initial</em>' attribute.
	 * @see #isInitial()
	 * @generated
	 */
	public void setInitial(boolean newInitial) {
		boolean oldInitial = initial;
		initial = newInitial;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.PROCESS__INITIAL, oldInitial, initial));
	}

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference.
	 * @return the value of the '<em>Action</em>' containment reference.
	 * @see #setAction(Action)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getProcess_Action()
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MCRL2Package.PROCESS__ACTION, oldAction, newAction);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Process#getAction <em>Action</em>}' containment reference.
	 * @param value the new value of the '<em>Action</em>' containment reference.
	 * @see #getAction()
	 * @generated
	 */
	public void setAction(Action newAction) {
		if (newAction != action) {
			NotificationChain msgs = null;
			if (action != null)
				msgs = ((InternalEObject)action).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.PROCESS__ACTION, null, msgs);
			if (newAction != null)
				msgs = ((InternalEObject)newAction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MCRL2Package.PROCESS__ACTION, null, msgs);
			msgs = basicSetAction(newAction, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.PROCESS__ACTION, newAction, newAction));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MCRL2Package.PROCESS__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case MCRL2Package.PROCESS__ACTION:
				return basicSetAction(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.PROCESS__PARAMETERS:
				return getParameters();
			case MCRL2Package.PROCESS__INITIAL:
				return isInitial();
			case MCRL2Package.PROCESS__ACTION:
				return getAction();
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
			case MCRL2Package.PROCESS__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Atom>)newValue);
				return;
			case MCRL2Package.PROCESS__INITIAL:
				setInitial((Boolean)newValue);
				return;
			case MCRL2Package.PROCESS__ACTION:
				setAction((Action)newValue);
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
			case MCRL2Package.PROCESS__PARAMETERS:
				getParameters().clear();
				return;
			case MCRL2Package.PROCESS__INITIAL:
				setInitial(INITIAL_EDEFAULT);
				return;
			case MCRL2Package.PROCESS__ACTION:
				setAction((Action)null);
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
			case MCRL2Package.PROCESS__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case MCRL2Package.PROCESS__INITIAL:
				return initial != INITIAL_EDEFAULT;
			case MCRL2Package.PROCESS__ACTION:
				return action != null;
		}
		return super.eIsSet(featureID);
	}

}
