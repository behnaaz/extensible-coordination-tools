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
package org.ect.codegen.wizards;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.ect.codegen.GenericGenModel;
import org.ect.codegen.IGenModel;
import org.ect.codegen.extensions.CodeGeneratorExtension;
import org.ect.codegen.parts.CodeGenPlugin;

public class GenerateCodeAction implements IObjectActionDelegate {

	private IWorkbenchPart workbenchPart;
	private Object target;
	
	private CodeGeneratorExtension generatorExtension;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		
		IGenModel genModel = new GenericGenModel(target);
		
		// Inititialize genModel.
		try {
			generatorExtension.getCodeGenerator().initGenModel(target, genModel);
			if (genModel.getTarget()==null) genModel.setTarget(target);
		}
		catch (Exception e) {
			MessageDialog.openError(workbenchPart.getSite().getShell(), 
					"Error in code generator", 
					"An error occured during the initialization of the code generator: " + e);
			CodeGenPlugin.getInstance().logError("An error occured during the initialization of the code generator.", e);
			return;
		}
		
		GenerateCodeWizard wizard = new GenerateCodeWizard(generatorExtension, genModel);
		
		WizardDialog dialog = new WizardDialog(workbenchPart.getSite().getShell(), wizard);
		wizard.setDialog(dialog);
		
		dialog.create();
		dialog.getShell().setSize(550, 525);
		dialog.open();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {

		generatorExtension = null;
		target = null;
		
		// Find code generator extension.
		CodeGeneratorExtension[] extensions = CodeGenPlugin.getInstance().getExtensionRegistry().getCodeGenerators();
		for (int i=0; i<extensions.length; i++) {
			if (!extensions[i].getId().equals( action.getId() )) continue;
			Object[] current = extensions[i].extractTargets(selection);
			if (current.length!=0) {
				target = current[0];
				generatorExtension = extensions[i];
				break;
			}
		}
		
		action.setEnabled(target!=null);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
		this.workbenchPart = workbenchPart;
		action.setEnabled(true);
	}

}
