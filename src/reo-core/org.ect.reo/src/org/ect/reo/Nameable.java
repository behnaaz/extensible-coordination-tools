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

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for nameable elements. This interface defines attributes
 * that all nameable elements must have.
 *
 * @see org.ect.reo.ReoPackage#getNamedContainer()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Nameable extends EObject {

	/**
	 * Get the name of the container.
	 * @see #setName(String)
	 * @see org.ect.reo.ReoPackage#getNamedContainer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Set the name of the container.
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

}
