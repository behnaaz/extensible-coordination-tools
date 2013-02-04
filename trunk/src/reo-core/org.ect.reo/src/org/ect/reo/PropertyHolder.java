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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * Common interface for property holders.
 *
 * @see org.ect.reo.ReoPackage#getPropertyHolder()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface PropertyHolder extends EObject {
	
	/**
	 * Returns the list of properties of this object.
	 * The list contents are of type {@link org.ect.reo.Property}.
	 * 
	 * @see org.ect.reo.ReoPackage#getPropertyHolder_Properties()
	 * @model containment="true" keys="key"
	 * @generated
	 */
	EList<Property> getProperties();

}
