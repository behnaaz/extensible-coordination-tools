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
package org.ect.ea.util;

/**
 * @generated NOT
 * @author Christian Krause
 */
public interface IValidationResult {
	
	public enum ValidationResultType {
		OK, WARNING, ERROR
	}

	/**
	 * Get the validation result type.
	 */
	public ValidationResultType getType();
	
	/**
	 * Get the validation message.
	 */
	public String getMessage();
	
}
