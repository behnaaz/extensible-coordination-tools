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
package org.ect.codegen.ea.interpreter;

import org.ect.codegen.ea.runtime.Sink;

@SuppressWarnings("unchecked")
public class Gate implements Runnable {
	private static final int sleepTime = 500;
	private static final int TIMES = 10; 
	
	private Sink open, close;
	private boolean isOpen = true;
	private int nTimes;
	public Gate(Sink j, Sink k) {
		this(j,k, TIMES);
	}

	public Gate(Sink j, Sink k, int n) {
		open = k;
		close = j;
		nTimes = n;
		Thread.currentThread().setName("gate");
	}

	public void run() {
		int i=0;
		while(!isOpen || ++i<nTimes) 
		try {
			Thread.sleep(sleepTime);
			if(isOpen) {
				System.out.println("===closing valve===");
				close.write(true);
			} else {
				System.out.println("===opening valve===");
				open.write(true);
			}
			isOpen=!isOpen;
			System.out.println("===valve is " + (isOpen? "open":"shut")+"===");
			System.out.flush();
		} catch (InterruptedException e) {
			break;
		}
	}
}
