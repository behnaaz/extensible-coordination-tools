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
public class Pair<T1, T2> {
	private T1 t1;
	private T2 t2;

	public Pair(T1 t1val, T2 t2val) {
		t1 = t1val;
		t2 = t2val;
	}

	public T1 getFirst() {
		return t1;
	}

	public T2 getSecond() {
		return t2;
	}
}
