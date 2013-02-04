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

import org.eclipse.emf.ecore.EClass;

/**
 * @generated
 */
public class Synchronization extends CompositeAction {
	
	/**
	 * Alternative constructor.
	 * @generated NOT
	 * @param actions Actions.
	 */
	public Synchronization(Action... actions) {
		super(actions);
	}

	/**
	 * @generated NOT
	 */
	public static final String OPERATOR = " | ";
	
	/**
	 * @generated NOT
	 */
	@Override
	protected String operator() {
		return OPERATOR;
	}

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Synchronization() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.SYNCHRONIZATION;
	}

} // Synchronization
