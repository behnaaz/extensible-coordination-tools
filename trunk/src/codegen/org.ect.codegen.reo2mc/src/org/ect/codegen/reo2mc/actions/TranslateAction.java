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
package org.ect.codegen.reo2mc.actions;

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

import org.ect.codegen.reo2mc.wizards.Reo2MCWizard;
import org.ect.reo.Connector;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;

public class TranslateAction implements IObjectActionDelegate {
	
	Shell shell;
	ConnectorEditPart connectorPart;
	//IPath path;
	
	public TranslateAction(){
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();		
	}

	public void run(IAction action) {
		Connector connector = (Connector) connectorPart.getNotationView().getElement();
		
		INewWizard wizard = new Reo2MCWizard(connector);
		wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection)selection).getFirstElement();
			if (o instanceof ConnectorEditPart) 
				connectorPart = (ConnectorEditPart)o;			
		}
		
	}
}
