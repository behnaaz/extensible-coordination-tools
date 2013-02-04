/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
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
