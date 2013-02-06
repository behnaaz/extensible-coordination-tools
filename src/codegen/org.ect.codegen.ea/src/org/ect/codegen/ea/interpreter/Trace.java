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

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

import org.ect.codegen.ea.runtime.TransactionalIO;

/**
 * 
 */
class Trace implements Observer, Runnable {
	static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private XState initial;
	private Semaphore events = new Semaphore(0);
	private volatile TransactionalIO notifier;
	
	Trace(XState start, Collection<Observable> ports) {
		initial = start;
		for (Observable port: ports)
			port.addObserver(this);
	}

	public void run() {
		XState next = initial;
		while (!Thread.interrupted()) 
			try {
				XState result = next.tryStep(notifier);
				if (result==null)
					events.acquire(); //wait for read/write on a port
				else
					next = result;	//and try another step
			} catch (InterruptedException e) {
				return;
			}
	}

	public void update(Observable o, Object ioState) {
		assert o instanceof TransactionalIO;
		
		if (ioState==null || ((Set)ioState).isEmpty())
			return;
		
		logger.fine(o +" is ready");
		notifier = (TransactionalIO) o;
		events.release();
	}
}