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
package org.ect.codegen.reo2ea.qia.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import org.ect.codegen.reo2ea.qia.wizards.HideWizard;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;

public class HideMenu implements IObjectActionDelegate{
	Shell myShell;
	AutomatonEditPart automatonPart;
	
	public HideMenu(){
		super();
	}
	
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myShell = targetPart.getSite().getShell();		
	}

	public void run(IAction action) {
		Dialog dialog = new WizardDialog(myShell, new HideWizard(automatonPart));
		dialog.open();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection)selection).getFirstElement();
			if (o instanceof AutomatonEditPart) 
				automatonPart = (AutomatonEditPart)o;			
		}
	}
	
}
