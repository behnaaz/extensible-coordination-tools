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
package org.ect.codegen.ea2mc.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import org.ect.codegen.ea2mc.genMCfileWizard.GenFileWizard;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;

public class GenMCFileAction implements IObjectActionDelegate {
	private Shell shell;
	private AutomatonEditPart editpart;
	private Automaton automaton;
	
	public GenMCFileAction(){
		super();
	}
		
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	public void run(IAction action) {
		INewWizard wizard = new GenFileWizard(automaton);
		wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) return;
		IStructuredSelection structured = (IStructuredSelection) selection;
		
		Object selected = structured.getFirstElement();
		if (selected instanceof AutomatonEditPart) {
			this.editpart = (AutomatonEditPart) selected;
			this.automaton = (Automaton) editpart.getNotationView().getElement();
		}		
	}
}
