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
package org.ect.ea.diagram.contributions.actions;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.EditDomain;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.ect.ea.AutomataPlugin;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;

public abstract class ChangeExtensionsAction extends Action {
		
	/**
	 * Get the ChangeExtensionsCommand for this action.
	 * Subclasses must implement this method. The action
	 * can only succeed if the command returned by this
	 * method is not null and canExecute() returns true.
	 */
	protected abstract ChangeExtensionsCommand getCommand();
	
	
	/**
	 * Perform the action.
	 */
	public void run() {
		
		ChangeExtensionsCommand command = getCommand();
		if (command==null || !command.canExecute()) return;
				
		try {
			// Try to use the editors command stack.
			EditDomain domain = command.getEditDomain();
			if (domain!=null) {
				domain.getCommandStack().execute(new ICommandProxy(command));
			} else {
				// Fallback: just execute the command (no undo).
				command.execute(new NullProgressMonitor(), null);
			}
		}
		catch (ExecutionException e) {
			AutomataPlugin.getInstance().logError("Error in ChangeExtensionsCommand.", e);
		}
		
	}
	
	
	/**
	 * Check if there are only states selected.
	 * @param selection Selection to be checked.
	 * @return True, if there are only {@link StateEditPart}s.
	 */
	protected boolean isStateSelection(IGraphicalEditPart[] selection) {
		if (selection==null || selection.length==0) return false;
		for (IGraphicalEditPart editpart : selection) {
			if (!(editpart instanceof StateEditPart)) return false;
		}
		return true;
	}

	
	/**
	 * Check if there are only automata selected.
	 * @param selection Selection to be checked.
	 * @return True, if there are only {@link AutomatonEditPart}s.
	 */
	protected boolean isAutomataSelection(IGraphicalEditPart[] selection) {
		if (selection==null || selection.length==0) return false;
		for (IGraphicalEditPart editpart : selection) {
			if (!(editpart instanceof AutomatonEditPart)) return false;
		}
		return true;
	}


	/**
	 * Check if there are only transition selected.
	 * @param selection Selection to be checked.
	 * @return True, if there are only {@link AutomatonEditPart}s.
	 */
	protected boolean isTransitionSelection(IGraphicalEditPart[] selection) {
		if (selection==null || selection.length==0) return false;
		for (IGraphicalEditPart editpart : selection) {
			if (!(editpart instanceof TransitionEditPart)) return false;
		}
		return true;
	}


	/**
	 * Check if the selection contains exaclty one element.
	 * @param selection Selection to be checked.
	 */
	protected boolean isSingletonSelection(IGraphicalEditPart[] selection) {
		if (selection==null) return false;
		return selection.length==1;
	}
	
}
