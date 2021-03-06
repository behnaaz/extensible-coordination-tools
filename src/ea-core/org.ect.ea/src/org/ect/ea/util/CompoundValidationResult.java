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
