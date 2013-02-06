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
package org.ect.convert.bpel2reo.util;

public class Triple<T1, T2, T3> {
	private T1 t1;
	private T2 t2;
	private T3 t3;

	public Triple(T1 t1val, T2 t2val, T3 t3val) {
		t1 = t1val;
		t2 = t2val;
		t3 = t3val;
	}

	public T1 getFirst() {
		return t1;
	}

	public T2 getSecond() {
		return t2;
	}
	
	public T3 getThird() {
		return t3;
	}
}
