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
 * An interface for reconfigurable elements.
 *
 * @see org.ect.reo.ReoPackage#getReconfigurable()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Reconfigurable extends EObject {
	
	/**
	 * Returns the value of the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.ReconfAction}.
	 * @return the value of the '<em>Reconf Actions</em>' containment reference list.
	 * @see org.ect.reo.ReoPackage#getReconfigurable_ReconfActions()
	 * @model containment="true"
	 * @generated
	 */
	EList<ReconfAction> getReconfActions();
	
}
