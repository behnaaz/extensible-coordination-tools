/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.util;

import java.util.Vector;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class CompoundValidationResult extends Vector<IValidationResult> implements IValidationResult {
		
	/**
	 * Machine generated serial id.
	 */
	private static final long serialVersionUID = -1009436973614821289L;

	/**
	 * Default constructor.
	 */
	public CompoundValidationResult() {
		super();
	}
	
	
	public String getMessage() {
		for (IValidationResult result : this) {
			if (result.getMessage()!=null) return result.getMessage();
		}		
		return null;
	}
	
	
	public ValidationResultType getType() {
		ValidationResultType type = ValidationResultType.OK;
		for (IValidationResult result : this) {
			if (result.getType().ordinal() > type.ordinal()) type = result.getType();
		}
		return type;
	}
	
}
