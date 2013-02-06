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
package org.ect.codegen.reo2ea.core;

public class TransformationException extends Exception {
	private static final long serialVersionUID = 1L;

	public TransformationException(Exception e) {
		super(e);
	}
	public TransformationException(String string) {
		super(string);
	}
	public TransformationException(String string, Exception e) {
		super(string, e);
	}
}
