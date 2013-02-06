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

import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.ect.codegen.ea.runtime.ReoComponent;
import org.ect.codegen.ea.runtime.Sink;
import org.ect.codegen.ea.runtime.Source;

public class BasicReader<I,O> implements ReoComponent<I,O> {
	protected static Random rand = new Random();
	protected Source<I> inPort;
	protected String name = "Reader";
	protected int count = 100;
	protected int sleepTime = 100;
	
	public void run() {
		Thread.currentThread().setName(name);
		System.out.println(name + " is ready");
		
		int i=0;
		for (i=0; i<count; i++) 
			try {
				Thread.sleep(rand.nextInt(sleepTime));
//				synchronized (inPort) {
					I val = take();
					System.out.println(name + " read " + val);
					System.out.flush();					
//				}
	
				if (Thread.interrupted())
					break;			 
			} catch (InterruptedException e) {  
				break;
			} catch (TimeoutException e) {
				System.out.println("timeout in "+ name);
			}
	
			System.out.println("*** "+ name + " read total of "+ i + " items ***");
	}

	protected I take() throws TimeoutException, InterruptedException {
		return inPort.take();
	}
	
	public BasicReader<I,O> withName(String name) {
		this.name = name;
		return this;
	}
	
	public BasicReader<I,O> withCount(int n) {
		count = n;
		return this;
	}
	
	public BasicReader<I,O> withMaxSleep(int millis) {
		sleepTime = millis;
		return this;
	}

	public BasicReader<I,O> withSourcePorts(Source<I>... sources) {
		inPort = sources[0];
		return this;
	}

	public BasicReader<I,O> withSinkPorts(Sink<O>... sinks) {
		throw new IllegalAccessError();
	}
}