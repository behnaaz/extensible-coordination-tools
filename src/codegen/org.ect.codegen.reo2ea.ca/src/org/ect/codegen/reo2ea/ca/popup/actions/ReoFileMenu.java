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
package org.ect.codegen.reo2ea.ca.popup.actions;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.ect.codegen.reo2ea.ca.wizards.BatchEAWizard;
import org.ect.codegen.reo2ea.plugin.Reo2EAPlugin;
import org.ect.codegen.reo2ea.util.XMIWrangler;


public class ReoFileMenu implements IObjectActionDelegate {
	IPath path;
	Resource diagram;
	Shell myShell;
	
	public ReoFileMenu() {
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		myShell = targetPart.getSite().getShell();
	}

	public void run(IAction action) {
		try {
			org.ect.reo.Module circuit = XMIWrangler.loadReo(path.toString());
			
			BatchEAWizard wizard = new BatchEAWizard(circuit);
			wizard.init(PlatformUI.getWorkbench(), StructuredSelection.EMPTY);
			WizardDialog dialog = new WizardDialog(myShell, wizard);
			dialog.open();
		} catch (Exception e) {
			Reo2EAPlugin.getDefault().log("ERROR", e);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		Object file = ((IStructuredSelection)selection).getFirstElement();
		
		if (file instanceof IFile) {
			path = ((IFile) file).getLocation();
		}
	}
}
