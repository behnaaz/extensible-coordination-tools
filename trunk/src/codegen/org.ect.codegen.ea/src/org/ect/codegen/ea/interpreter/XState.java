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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Logger;

import org.ect.codegen.ea.runtime.TransactionalIO;
import org.ect.codegen.ea.runtime.primitives.Cell;

public class XState implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String name ;
	private Collection<Cell> memory = new HashSet<Cell>();;
	private Collection<XTransition>  transitions = new ArrayList<XTransition>();
	private boolean startState;

	public XState(String stateName) {
		this(stateName, false);
	}
	
	public XState(String stateName, boolean isStart) {
		name = stateName;
		startState = isStart;
	}
	
	public void setStart(boolean isStart) {
		startState = isStart;
	}
	
	public void addCell(Cell mem) {
		memory.add(mem);
	}
	
	public void addOutgoing(XTransition trans) {
		transitions.add(trans);
	}

	public boolean isStart() {
		return startState;
	}

	public Collection<XTransition> getTransitions() {
		return transitions;
	}

	public Collection<Cell> getMemory() {
		return memory;
	}
	
	XState tryStep(TransactionalIO notifier) {
		for (XTransition t: transitions)
			if (t.execute(notifier)) {
				logger.fine("Executed " + this + t);
				return t.getTarget();
			}
		//else
		return null;
	}

	@Override
	public String toString() {
		return new StringBuilder(name)
		.append(isStart() ? "*":"").append(memory)
		.toString();		
	}
}
