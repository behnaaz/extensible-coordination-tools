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
package org.ect.ea.extensions.constraints.parts;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.CA.PortType;

public class ChangePortTypesCommand  extends AbstractTransactionalCommand {

	private Automaton automaton;
	private String[] nodeNames;
	private PortType[] nodeTypes;
	
	
	public ChangePortTypesCommand(TransactionalEditingDomain editingDomain, Automaton automaton, String[] nodeNames, PortType[] nodeTypes) {
		super(editingDomain, "Change port types", null);
		this.automaton = automaton;
		this.nodeNames = nodeNames;
		this.nodeTypes = nodeTypes;
	}

		
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable adapter) throws ExecutionException {
		for (int i=0; i<nodeNames.length; i++) {
			CA.setPortType(nodeNames[i], nodeTypes[i], automaton);
		}
		return CommandResult.newOKCommandResult();
	}	
	
}

