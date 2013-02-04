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
package org.ect.ea.costs;

public class UnsupportedCostsTypeException extends Exception  {
	
	private static final long serialVersionUID = -6266302609637811615L;

	private CostsAlgebra costsAlgebra;
	private Class unsupportedType;
	
	public UnsupportedCostsTypeException(CostsAlgebra costsAlgebra, Class unsupportedType) {
		super("CostsAlgebra " + costsAlgebra + " does not support the type " + unsupportedType.getName());
		this.costsAlgebra = costsAlgebra;
		this.unsupportedType = unsupportedType;
	}

	public CostsAlgebra getCostsAlgebra() {
		return costsAlgebra;
	}

	public Class getUnsupportedType() {
		return unsupportedType;
	}

}
