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
package org.ect.codegen.ea.generator;

import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.HASDATA;
import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.WANTSDATA;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.ect.codegen.ea.runtime.TransactionalIO;
import org.ect.codegen.ea.runtime.TransactionalIO.IOState;

public abstract class Coordinator implements Observer, Runnable {
//	protected EngineIO notifier;
	protected Semaphore events = new Semaphore(0);

	public void update(Observable o, Object ioState) {
		assert o instanceof TransactionalIO && ioState instanceof Set;
			
		if (((Set)ioState).isEmpty()) //Port notifying us of a timeout
			return;
			
//		notifier = (EngineIO) o;
		events.release();
	}
	
	protected boolean txnStart(TransactionalIO... ports) {
		for (TransactionalIO p: ports) {
			Set<IOState> ioState = p.getState();
			if ( !(ioState.contains(HASDATA) || ioState.contains(WANTSDATA)) )
				return false;						
		}		
		
		for (TransactionalIO p: ports) 
			p.beginTxn();
		
		return true;
	}
	
	protected void txnFinish(TransactionalIO... ports) {
		for (TransactionalIO p: ports) 
			p.endTxn();		
	}
}
