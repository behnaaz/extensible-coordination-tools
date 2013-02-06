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


public class BasicWriter<I,O> implements ReoComponent<I,O> {
	protected static Random rand = new Random();

	protected Sink<O> outPort;
	protected String name = "Writer";
	protected int start = 0;
	protected int end = 100;
	protected int sleepTime = 100;
	protected String message="";
	
	public BasicWriter<I,O> withRange(int start, int end) {
		this.start = start;
		this.end = end;
		return this;
	}

	public void run() {
		Thread.currentThread().setName(name);
		System.out.println(name + " is ready");
		
		int i=0;
		for (i=start; i<end; i++) 
			try {
				Thread.sleep(rand.nextInt(sleepTime));
//				synchronized (outPort) {
					write((O) (message+" #"+i));
					System.out.println(name + " wrote message#"+ i);
					System.out.flush();					
//				}

				if (Thread.interrupted())
					break;
			} catch (InterruptedException e) {
				break;
			} catch (TimeoutException e) {
				System.out.println("timeout in "+ name);
			}

			System.out.println("*** "+ name + " wrote total of "+ (i-start) + " items ***");
	}

	protected void write(O val) throws TimeoutException, InterruptedException {
		outPort.write(val);
	}
	
	public BasicWriter<I,O> withName(String name) {
		this.name = name;
		return this;
	}
	
	public BasicWriter<I,O> withCount(int n) {
		end = n;
		return this;
	}
	
	public BasicWriter<I,O> withMessage(String message) {
		this.message = message;
		return this;
	}

	public BasicWriter<I,O> withMaxSleep(int millis) {
		sleepTime = millis;
		return this;
	}

	public BasicWriter<I,O> withSourcePorts(Source<I>... sources) {
		throw new IllegalAccessError();
	}

	public BasicWriter<I,O> withSinkPorts(Sink<O>... sinks) {
		outPort = sinks[0];
		return this;
	}
}
