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
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.SilentTransitions;
import org.ect.ea.automata.Transition;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;

public class AddSilentTransitionsCommand extends ChangeExtensionsCommand {

	private List<Automaton> automata;
	private List<Transition> transitions;
	private boolean add;
	
	
	public AddSilentTransitionsCommand(boolean add, ModuleEditPart editpart, List<Automaton> automata) {
		super("Silent Transitions", editpart);
		this.automata = automata;
		this.add = add;
	}
	
	
	@Override
	protected void performExtensionsChange(IProgressMonitor monitor) {
		
		monitor.beginTask("Silent Transitions", automata.size()+1);
		
		transitions = new Vector<Transition>();
		for (Automaton automaton : automata) {
			
			// Add or remove the silent transitions.
			if (add) transitions.addAll( SilentTransitions.add(automaton) );
			else transitions.addAll( SilentTransitions.remove(automaton) );
			monitor.worked(1);
			
		}
		
		// Add / remove the edges.
		updateNormalViews();
		monitor.worked(1);
		monitor.done();
		
	}
	
	
	public List<Transition> getSilentTransitions() {
		return transitions;
	}
	
}

