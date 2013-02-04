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
 * Interface for connectable elements. This is the (most important) common 
 * interface of the classes {@link org.ect.reo.Node} and {@link org.ect.reo.Primitive}.
 * 
 * @see org.ect.reo.ReoPackage#getConnectable()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Connectable extends EObject {

	/**
	 * Get the list of source ends of this connectable element.
	 * This list can be modified.
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	EList<SourceEnd> getSourceEnds();

	/**
 	 * Get the list of sink ends of this connectable element.
	 * This list can be modified.
 	 * 
	 * @model kind="operation"
	 * @generated
	 */
	EList<SinkEnd> getSinkEnds();

	/**
	 * Get the list of all primitive ends of this connectable element.
	 * This list is derived: it is contains all elements from {@link #getSourceEnds()}
	 * and {@link #getSinkEnds()}. The list cannot be modified.
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	EList<PrimitiveEnd> getAllEnds();

}
