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
public class Instance extends Action {

	/**
	 * @generated NOT
	 * @param process Process.
	 */
	public Instance(Process process, String... args) {
		super();
		
		// Set the process.
		setProcess(process);
		
		// Set the arguments.
		if (args!=null) {
			for (String arg : args) getArguments().add(arg);
		}
	}
	
	/**
	 * @generated NOT
	 * @param process Process.
	 */
	public Instance(Process process) {
		this(process, (String[]) null);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (process==null || process.getName()==null) return "null";
		StringBuffer result = new StringBuffer(process.getName());
		if (!getArguments().isEmpty()) {
			result.append("(");
			for (int i=0; i<getArguments().size(); i++) {
				result.append(getArguments().get(i));
				if (i<getArguments().size()-1) result.append(",");
			}
			result.append(")");
		}		
		return  result.toString();
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

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
	 * The cached value of the '{@link #getProcess() <em>Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcess()
	 * @generated
	 * @ordered
	 */
	protected org.ect.reo.mcrl2.Process process;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Instance() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.INSTANCE;
	}

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' attribute list.
	 * @see org.ect.reo.mcrl2.MCRL2Package#getInstance_Arguments()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getArguments() {
		if (arguments == null) {
			arguments = new EDataTypeEList<String>(String.class, this, MCRL2Package.INSTANCE__ARGUMENTS);
		}
		return arguments;
	}

	/**
	 * Returns the value of the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' reference.
	 * @see #setProcess(org.ect.reo.mcrl2.Process)
	 * @see org.ect.reo.mcrl2.MCRL2Package#getInstance_Process()
	 * @model
	 * @generated
	 */
	public org.ect.reo.mcrl2.Process getProcess() {
		if (process != null && process.eIsProxy()) {
			InternalEObject oldProcess = (InternalEObject)process;
			process = (org.ect.reo.mcrl2.Process)eResolveProxy(oldProcess);
			if (process != oldProcess) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MCRL2Package.INSTANCE__PROCESS, oldProcess, process));
			}
		}
		return process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.ect.reo.mcrl2.Process basicGetProcess() {
		return process;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.mcrl2.Instance#getProcess <em>Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' reference.
	 * @see #getProcess()
	 * @generated
	 */
	public void setProcess(org.ect.reo.mcrl2.Process newProcess) {
		org.ect.reo.mcrl2.Process oldProcess = process;
		process = newProcess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MCRL2Package.INSTANCE__PROCESS, oldProcess, process));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MCRL2Package.INSTANCE__ARGUMENTS:
				return getArguments();
			case MCRL2Package.INSTANCE__PROCESS:
				if (resolve) return getProcess();
				return basicGetProcess();
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
			case MCRL2Package.INSTANCE__ARGUMENTS:
				getArguments().clear();
				getArguments().addAll((Collection<? extends String>)newValue);
				return;
			case MCRL2Package.INSTANCE__PROCESS:
				setProcess((org.ect.reo.mcrl2.Process)newValue);
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
			case MCRL2Package.INSTANCE__ARGUMENTS:
				getArguments().clear();
				return;
			case MCRL2Package.INSTANCE__PROCESS:
				setProcess((org.ect.reo.mcrl2.Process)null);
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
			case MCRL2Package.INSTANCE__ARGUMENTS:
				return arguments != null && !arguments.isEmpty();
			case MCRL2Package.INSTANCE__PROCESS:
				return process != null;
		}
		return super.eIsSet(featureID);
	}

} // Instance
