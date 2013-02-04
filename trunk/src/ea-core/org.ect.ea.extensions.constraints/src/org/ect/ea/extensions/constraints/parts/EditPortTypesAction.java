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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;

/*
 * TODO: MOVE ALL PORT RELATED STUFF TO org.ect.ea.extensions.portnames !
 */
public class EditPortTypesAction implements IObjectActionDelegate  {

	private IWorkbenchPart part;
	private AutomatonEditPart editpart;	
	
	
	public void run(IAction action) {
		
		EditPortTypesWizard wizard = new EditPortTypesWizard();
		wizard.setAutomaton((Automaton) editpart.getNotationView().getElement());
		
		Shell shell = part.getSite().getShell();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		
		dialog.open();
		
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection) selection;
			editpart = (AutomatonEditPart) structured.getFirstElement();
		} else {
			editpart = null;
		}
	}
	
	public void setActivePart(IAction action, IWorkbenchPart part) {
		this.part = part;
		action.setEnabled(true);
	}

}
