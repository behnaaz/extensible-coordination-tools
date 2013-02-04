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
package org.ect.ea.extensions.startstates.actions;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;

public class RemoveUnreachableStatesCommand extends ChangeExtensionsCommand {

	private Automaton automaton;

	public RemoveUnreachableStatesCommand(Automaton automaton, IGraphicalEditPart editpart) {
		super("Remove unreachable states", editpart);
		this.automaton = automaton;
	}
	
	
	@Override
	protected void performExtensionsChange(IProgressMonitor monitor) {
		
		monitor.beginTask("Remove unreachable states", 4);
		System.out.println("remove unreachable states...");
		
		EList<State> states = automaton.getStates();
		Stack<State> temp = new Stack<State>();
		List<State> reachable = new Vector<State>();
		List<Transition> removeT = new Vector<Transition>();
		boolean removable = false;
		
		for(State a : states){
			BooleanExtension start = (BooleanExtension) a.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			
			if (start!=null && start.getValue()==true) {
				removable = true;
				temp.push(a);
				reachable.add(a);
			}			
		}
		
		monitor.worked(1);
		
		while(removable && !temp.isEmpty()){
			State tempState = temp.pop(); 
			EList<Transition> outgoings = tempState.getOutgoing();
			for(Transition b : outgoings){ 
				State next = b.getTarget();
				if(!reachable.contains(next)){
					temp.push(next);
					reachable.add(next); 
				}	
			}
		}
		
		monitor.worked(1);

		List<State> unreachable = new Vector<State>();
		for(State b : states){
			if(!reachable.contains(b))	unreachable.add(b);
		}
		for(State c :  unreachable){
			if(c.getOutgoing()!=null)	removeT.addAll(c.getOutgoing());
		}
		
		//Just for debugging 
		//for(Transition test1 : removeT)	System.out.println(test1.getSource().getName()+" to "+test1.getTarget().getName());
		//for(State test2 : unreachable) System.out.println(test2.getName());
		
		automaton.getTransitions().removeAll(removeT);
		automaton.getStates().removeAll(unreachable);
		
		monitor.worked(1);

		// Add / remove the edges.
		updateNormalViews();
		
		monitor.worked(1);
		monitor.done();

	}

}
