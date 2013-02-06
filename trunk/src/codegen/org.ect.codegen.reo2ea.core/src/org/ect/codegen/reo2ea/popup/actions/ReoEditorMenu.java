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

import org.ect.codegen.reo2ea.wizards.ExtensibleWizard;
import org.ect.reo.diagram.edit.parts.ConnectorEditPart;

public class ReoEditorMenu implements IObjectActionDelegate {
	Shell myShell;
	ConnectorEditPart connectorPart;
	private IStructuredSelection selection;

	public ReoEditorMenu() {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myShell = targetPart.getSite().getShell();
	}

	public void run(IAction action) {
		INewWizard wizard = new ExtensibleWizard();
		wizard.init(PlatformUI.getWorkbench(), selection);
		new WizardDialog(myShell, wizard).open();
/*
		Connector connector = (Connector) connectorPart.getNotationView().getElement();
        for (Node n: connector.getNodes()) {
        	if (n.getName()==null) 
        		if (MessageDialog.openConfirm(myShell, "Autolabel nodes", 
        				"The current Reo connector cannot be converted because it contains unlabelled nodes.\n" +
        				"Shall I autolabel nodes?"))
					try {
						labelNodes(connector).execute(new NullProgressMonitor(),
        						null);
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else
        			return;
        }
		INewWizard wizard = new Reo2EaWizard(connector);
*/
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
/*	
	private AbstractTransactionalCommand labelNodes(final Connector connector) {
		return new AbstractTransactionalCommand(
				connectorPart.getEditingDomain(),
				"Node label Operation",
				Collections.EMPTY_LIST) {
			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				char name = 'A';
				for (Node n: connector.getNodes()) {
					n.setName(Character.toString(name));
					name++;
				}
				return null;
			}
		};
	}
*/
}
