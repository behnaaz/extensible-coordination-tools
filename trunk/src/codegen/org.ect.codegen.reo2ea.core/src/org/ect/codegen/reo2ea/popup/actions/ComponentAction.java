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
package org.ect.codegen.reo2ea.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import org.ect.codegen.reo2ea.wizards.ComponentWizard;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;

public class ComponentAction implements IObjectActionDelegate {
	Shell myShell;
	ConnectorEditPart connectorPart;
	private IStructuredSelection selection;

	public ComponentAction() {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myShell = targetPart.getSite().getShell();
	}

	public void run(IAction action) {
		INewWizard wizard = new ComponentWizard();
		wizard.init(PlatformUI.getWorkbench(), selection);
		new WizardDialog(
				myShell, wizard
				).open();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) 
			this.selection =(IStructuredSelection)selection;
		{
			Object o = ((IStructuredSelection)selection).getFirstElement();
			if (o instanceof ConnectorEditPart) 
				connectorPart = (ConnectorEditPart)o;			
		}
//		System.out.println("selectionChanged:"+((IStructuredSelection)selection).getFirstElement());	
	}
}
