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
public class ValidationResult implements IValidationResult {

	// Validation Message.
	private String message;
	
	// Validation type.
	private ValidationResultType type;
	
	/**
	 * Default constructor.
	 * @param type Validation result type.
	 * @param message Validation message.
	 */
	public ValidationResult(ValidationResultType type, String message) {
		this.type = type;
		this.message = message;
	}
	
	/**
	 * Create a new OK validation result.
	 */
	public static ValidationResult newOKResult() {
		return new ValidationResult(ValidationResultType.OK, null);
	}

	/**
	 * Create a new error validation result.
	 */
	public static ValidationResult newErrorResult(String message) {
		return new ValidationResult(ValidationResultType.ERROR, message);
	}

	/**
	 * Create a new warning validation result.
	 */
	public static ValidationResult newWarningResult(String message) {
		return new ValidationResult(ValidationResultType.WARNING, message);
	}
	
	
	/**
	 * Get the validation message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Get the validation result type.
	 */
	public ValidationResultType getType() {
		return type;
	}

}
