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
package org.ect.ea.extensions.constraints;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.ect.ea.util.IValidationResult;

/**
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getParameter()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Parameter extends EObject {
	
	/**
	 * Get the value (name) of this parameter.
	 * @model kind="operation"
	 * @generated
	 */
	String getValue();

	/**
	 * Set the value (name) of this parameter.
	 * @model
	 * @generated
	 */
	void setValue(String value);


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Equation getEquation();

	/**
	 * Validate this parameter.
	 * @generated NOT
	 */
	public IValidationResult validate();
	
	
	/**
	 * Get all elements used in this parameter.
	 * @generated NOT
	 */
	public EList<Element> getAllElements();
	
} // Parameter
