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
package org.ect.codegen.ea.components;

import java.util.concurrent.TimeoutException;

import org.ect.codegen.ea.runtime.ReoComponent;

@SuppressWarnings("rawtypes")
public class TimeoutReader<I,O> extends BasicReader<I,O> {
	long timeout = 100 * 1000000;

	public ReoComponent withTimeout(int millis) {
		timeout = millis * 1000000;
		return this;
	}

	@Override
	protected I take() throws TimeoutException, InterruptedException {
		return (I) inPort.take(timeout);
	}
}
