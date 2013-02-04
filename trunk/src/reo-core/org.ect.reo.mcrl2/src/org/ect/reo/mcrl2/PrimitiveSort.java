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
 * @see org.ect.reo.mcrl2.MCRL2Package#getPrimitiveSort()
 * @model kind="class"
 * @generated
 */
public class PrimitiveSort extends Nameable implements Sort {
	
	/**
	 * @generated NOT
	 */
	public static final PrimitiveSort BOOL = new PrimitiveSort("Bool");	
	
	/**
	 * @generated NOT
	 */
	public static final PrimitiveSort INT = new PrimitiveSort("Int");
	
	/**
	 * @generated NOT
	 */
	public static final PrimitiveSort NAT = new PrimitiveSort("Nat");
	
	
	/**
	 * @generated NOT
	 */
	public PrimitiveSort(String name) {
		super();
		setName(name);
	}

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	public PrimitiveSort() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MCRL2Package.Literals.PRIMITIVE_SORT;
	}

}
