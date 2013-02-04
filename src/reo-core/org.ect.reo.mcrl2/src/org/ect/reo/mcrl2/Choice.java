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
public class Choice extends CompositeAction {
	
	/**
	 * Alternative constructor.
	 * @generated NOT
	 * @param actions Actions.
	 */
	public Choice(Action... actions) {
		super(actions);
	}

	/**
	 * @generated NOT
	 */
	public static final String OPERATOR = " + ";

	/**
	 * @generated NOT
	 */
	@Override
	protected String operator() {
		return OPERATOR;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	protected boolean breakLines() {
		return (eContainer() instanceof Process) && getActions().size()>2;
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
	public Choice() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.CHOICE;
	}

} // AltAction
