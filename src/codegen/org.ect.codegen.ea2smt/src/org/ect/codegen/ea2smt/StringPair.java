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

/** A pair of strings. */

class StringPair {

	private String first;
	private String second;
	
	public StringPair(String f, String s) {
		this.first = f;
		this.second = s;
	}
	
	public String getFirst() {
		return this.first;
	}
	
	public String getSecond() {
		return this.second;
	}
	
	public String toString() {
		return "(" + this.first + ", " + this.second + ")";
	}
	
	public void setFirst(String f) {
		this.first = f;
	}
	
	public void setSecond(String s) {
		this.second = s;
	}
	
}
