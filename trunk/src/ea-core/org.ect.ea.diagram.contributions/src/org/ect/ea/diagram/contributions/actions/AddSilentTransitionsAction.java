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

import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.commands.AddSilentTransitionsCommand;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;

public class AddSilentTransitionsAction extends Action {

	public static String ADD_SILENT_ACTION_ID = "addSilentTransitions";
	public static String REMOVE_SILENT_ACTION_ID = "removeSilentTransitions";
	
	private IWorkbenchPage workbenchPage;
	private boolean add;

	/**
	 * Default constructor.
	 */
	public AddSilentTransitionsAction(IWorkbenchPage workbenchPage, boolean add) {
		this.workbenchPage = workbenchPage;
		setText((add ? "Add" : "Remove") + " Silent Transitions");
		this.add = add;
	}
	
	
	@Override
	public void run() {
		
		IEditorPart part = workbenchPage.getActiveEditor();
		if (!(part instanceof IDiagramWorkbenchPart)) return;
		
		IDiagramWorkbenchPart editor = (IDiagramWorkbenchPart) part;
		
		// Get the selection.
		IStructuredSelection selection = StructuredSelection.EMPTY;
		if (editor.getDiagramGraphicalViewer().getSelection() instanceof IStructuredSelection) {
			selection = (IStructuredSelection) editor.getDiagramGraphicalViewer().getSelection();
		}
		
		List<Automaton> automata = new Vector<Automaton>();
		
		// Extract the automata.
		for (Object selected : selection.toList()) {
			if (selected instanceof AutomatonEditPart) {
				AutomatonEditPart editpart = (AutomatonEditPart) selected;
				automata.add( (Automaton) editpart.getNotationView().getElement() );
			}
		}
		
		// Create the command.
		ModuleEditPart editpart = (ModuleEditPart) editor.getDiagramEditPart();		
		AddSilentTransitionsCommand addCommand = new AddSilentTransitionsCommand(add, editpart, automata);
		
		// Execute it.
		CommandStack stack = editpart.getViewer().getEditDomain().getCommandStack();
		stack.execute(new ICommandProxy(addCommand));

		if (addCommand.getSilentTransitions().isEmpty()) {
			Shell shell = editor.getSite().getShell();
			MessageDialog.openWarning(shell, "Silent Transitions", "No silent transitions " + (add ? "added" : "removed") + ".");
		}
		
	}

}