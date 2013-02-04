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
package org.ect.ea.diagram.contributions.commands;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.ect.ea.EA;
import org.ect.ea.IAutomatonType;
import org.ect.ea.IAutomatonTypeRegistry;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.edit.AutomatonProxyEditPart;

public class ActivateTypeCommand extends ChangeExtensionsCommand {
	
	// The currently selected automata.
	private List<AutomatonProxyEditPart> editparts;
	
	// Automaton type to be activated.
	private IAutomatonType type;

	
	/**
	 * Default constructor.
	 */
	public ActivateTypeCommand(IGraphicalEditPart container) {
		super("activate automaton type", container);
	}
	
	/**
	 * Perform the activation command.
	 */
	protected void performExtensionsChange(IProgressMonitor monitor) {
		
		monitor.beginTask("activate automaton type", editparts.size());
		IAutomatonTypeRegistry registry = EA.getAutomatonTypeRegistry();
		
		// Set the automaton type.
		for (AutomatonProxyEditPart editpart : editparts) {
			Automaton automaton = editpart.getAutomaton();
			registry.setAutomatonType(automaton, type);
			monitor.worked(1);
		}
		
		monitor.done();
		
	}
	
	
	public void setAutomatonType(IAutomatonType type) {
		this.type = type;
	}
		
	public void setEditparts(List<AutomatonProxyEditPart> editparts) {
		this.editparts = editparts;
	}	
	
}
