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

import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.HASDATA;
import static org.ect.codegen.ea.runtime.TransactionalIO.IOState.WANTSDATA;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.ect.codegen.ea.runtime.TransCon;
import org.ect.codegen.ea.runtime.TransactionalIO;
import org.ect.codegen.ea.runtime.TransactionalIO.IOState;

public class XTransition implements Serializable {
	private static final String SPACER = "::";
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Collection<TransactionalIO> ports = new HashSet<TransactionalIO>();
	private TransCon constraint;
	private XState target;
	
	public void addPort(TransactionalIO p) {
		ports.add(p);
	}
	
	public void setConstraint(TransCon constraint) {
		this.constraint = constraint;
	}
	
	public void setTarget(XState target) {
		this.target = target;
	}
	
	public TransCon getConstraint() {
		return constraint;
	}
	
	public XState getTarget() {
		return target;
	}
	
	public Collection<TransactionalIO> getPorts() {
		return ports;
	}
	
	boolean execute(TransactionalIO notifier) {
//		if (!ports.isEmpty() && !ports.contains(notifier))
//			return false;		
		
		for (TransactionalIO p: ports) {
			Set<IOState> ioState = p.getState();
			if ( !(ioState.contains(HASDATA) || ioState.contains(WANTSDATA)) )
				return false;						
		}
		
		for (TransactionalIO p: ports) 
			p.beginTxn();
		
		boolean ret = true;		
		if (constraint!=null)
			ret = constraint.execute();
		
		for (TransactionalIO p: ports) 
			p.endTxn();		
		
		return ret;
	}
	
	@Override
	public String toString() {
		return  new StringBuilder()
		.append(ports).append(SPACER)
		.append(constraint).append(SPACER)
		.append(target).append("\n")
		.toString();
	}
}
