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
package org.ect.reo.colouring;

import org.eclipse.emf.ecore.EObject;

/**
 * @see org.ect.reo.colouring.ColouringPackage#getColourable()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Colourable extends EObject {
	
	/**
	 * Get the colouring table of this Colourable.
	 * @see org.ect.reo.colouring.ColouringPackage#getColourable_ColouringTable()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	ColouringTable getColouringTable();
	
	/**
	 * Determines whether the colouring table of this Colourable
	 * is reduced up to the flip rule or not. If this flag is set 
	 * to <code>true</code> then colouring engines have to add 
	 * the extra colourings implied by the flip rule using
	 * {@link ColouringConverter#reverseFlipRule(ColouringTable)}.
	 * @model
	 * @generated
	 */
	boolean usesFlipRule();
	
}
