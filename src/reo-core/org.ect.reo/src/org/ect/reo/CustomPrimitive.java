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
package org.ect.reo;

import org.eclipse.emf.common.util.URI;

/**
 * @see org.ect.reo.ReoPackage#getCustomPrimitive()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface CustomPrimitive extends Connectable, PropertyHolder {
	
	/**
	 * @generated NOT
	 */
	final String FOREGROUND_COLOR_KEY = "foreGround";
	
	/**
	 * Get the type URI of this custom primitive.
	 * @generated
	 */
	URI getTypeURI();
	
	/**
	 * Set the type URI of this custom primitive.
	 * @generated
	 */
	void setTypeURI(URI value);

	/**
	 * Get the resolved custom primitive.
	 * @generated
	 */
	CustomPrimitive getResolved();

	/**
	 * Set the resolved custom primitive.
	 * @generated
	 */
	void setResolved(CustomPrimitive value);

	/**
	 * Returns the value of the '<em><b>Foreground Color</b></em>' attribute.
	 * @return the value of the '<em>Foreground Color</em>' attribute.
	 * @see #setForegroundColor(String)
	 * @see org.ect.reo.ReoPackage#getCustomPrimitive_ForegroundColor()
	 * @model transient="true" volatile="true"
	 * @generated
	 */
	String getForegroundColor();

	/**
	 * Sets the value of the '{@link org.ect.reo.CustomPrimitive#getForegroundColor <em>Foreground Color</em>}' attribute.
	 * @param value the new value of the '<em>Foreground Color</em>' attribute.
	 * @see #getForegroundColor()
	 * @generated
	 */
	void setForegroundColor(String value);

	/**
	 * Returns the value of the '<em><b>Synchronous</b></em>' attribute.
	 * @return the value of the '<em>Synchronous</em>' attribute.
	 * @see #setSynchronous(boolean)
	 * @see org.ect.reo.ReoPackage#getCustomPrimitive_Synchronous()
	 * @model
	 * @generated
	 */
	boolean isSynchronous();

	/**
	 * Sets the value of the '{@link org.ect.reo.CustomPrimitive#isSynchronous <em>Synchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronous</em>' attribute.
	 * @see #isSynchronous()
	 * @generated
	 */
	void setSynchronous(boolean value);
	
}
