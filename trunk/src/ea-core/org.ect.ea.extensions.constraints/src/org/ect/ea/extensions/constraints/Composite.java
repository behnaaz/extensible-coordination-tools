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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.constraints.Composite#getMembers <em>Members</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getComposite()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Composite extends Constraint {
	
	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.ea.extensions.constraints.Constraint}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getComposite_Members()
	 * @model containment="true"
	 * @generated
	 */
	EList<Constraint> getMembers();

} // Composite
