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
 * Interface for network elements that may have delay information attached.
 * Delay information are used to do performance analysis.
 *
 * This should (at some point) be done using properties. 
 * See the PropertyHolder interface and e.g. the deployment properties. 
 * @see org.ect.reo.ReoPackage#getDelayable()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Delayable extends EObject {

	/**
	 * @see org.ect.reo.ReoPackage#getDelayable_ArrivalRate()
	 * @model
	 * @generated
	 */
	EList<Double> getArrivalRate();

	/**
	 * @see org.ect.reo.ReoPackage#getDelayable_ProcessingDelay()
	 * @model
	 * @generated
	 */
	EList<Double> getProcessingDelay();

}
