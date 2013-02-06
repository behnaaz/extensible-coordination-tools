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
package org.ect.codegen.ea2smt;

/** Thrown to indicate that an exception has occurred which was not expected 
 * at that point. */

public class FormulaGenerationException extends RuntimeException {

	/**
	 *  Machine-generated object id, for serialisation. 
	 */
	private static final long serialVersionUID = 6543387831731323626L;


	public FormulaGenerationException() {
	}

	public FormulaGenerationException(String message) {
		super(message);
	}

	public FormulaGenerationException(Throwable cause) {
		super(cause);
	}

	public FormulaGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

}
