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

/** A triple of strings. */

class StringTriple {

	private String first;
	private String second;
	private String third;
	
	public StringTriple(String f, String s, String t) {
		this.first = f;
		this.second = s;
		this.third = t;
	}
	
	public String getFirst() {
		return this.first;
	}
	public String getSecond() {
		return this.second;
	}
	public String getThird() {
		return this.third;
	}
	
	public String toString() {
		return "(" + this.first + ", " + this.second + ", " + this.third + ")";
	}
	
	public void setFirst(String f) {
		this.first = f;
	}
	public void setSecond(String s) {
		this.second = s;
	}
	public void setTrhid(String t) {
		this.third = t;
	}

}
