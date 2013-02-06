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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.ect.codegen.ICodeGenWizardPage;
import org.ect.codegen.ICodeGenerator;
import org.ect.codegen.IGenModel;
import org.ect.codegen.extensions.CodeGeneratorExtension;
import org.ect.codegen.parts.CodeGenPlugin;



public class GenerateCodeWizard extends Wizard implements IPageChangedListener {
	
	private CodeGeneratorExtension generatorExtension;
	private IGenModel genModel;
	private ICodeGenWizardPage[] wizardPages;
	private ICodeGenWizardPage currentPage;
	private Object dialog;
	
	
	/**
	 * Default constructor. Important: the correct functioning of this wizard
	 * relies on the fact that the wizard dialog has been set before running
	 * the wizard. This is done by {@link #setDialog(WizardDialog)}.
	 * @param generatorExtension
	 * @param genModel
	 */
	public GenerateCodeWizard(CodeGeneratorExtension generatorExtension, IGenModel genModel) {
		
		super();
		
		this.generatorExtension = generatorExtension;
		this.genModel = genModel;
		this.currentPage = null;
		this.wizardPages = new ICodeGenWizardPage[ generatorExtension.getWizardPages().length + 1];

		wizardPages[0] = new GenerateCodeWizardPage(generatorExtension, genModel);
		
		for (int i=0; i<generatorExtension.getWizardPages().length; i++) {
			wizardPages[i+1] = generatorExtension.getWizardPages()[i].getWizardPage();
		}
		
		setDefaultPageImageDescriptor(CodeGenPlugin.getBundledImageDescriptor(CodeGenPlugin.CODEGEN_WIZARD_IMAGE));
		setWindowTitle(CodeGenPlugin.CODEGEN_MENU_LABEL);
		setNeedsProgressMonitor(true);
		setHelpAvailable(false);

	}
	
	
	/**
	 * Add wizard pages.
	 * @see Wizard#addPages()
	 */
	public void addPages() {
		if (getPageCount()>0) return;
		for (int i=0; i<wizardPages.length; i++) {
			addPage(wizardPages[i]);
		}		
	}

	
	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		
		// Update the genModel.
		if (currentPage!=null) {
			currentPage.updateGenModel(genModel);
		}

		// The code generator.
		final ICodeGenerator codeGenerator = generatorExtension.getCodeGenerator();
		
		// Try to set the shell.
		try {
			Method setter = codeGenerator.getClass().getMethod("setShell", new Class[] { Shell.class } );
			if (setter!=null) { 
				setter.invoke(codeGenerator, new Object[] { getShell() } );
			}
		}
		catch (Throwable t) {}
		
		
		// Instanciate a new Code generation operation.
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			
			public void execute(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					codeGenerator.generateCode(genModel, monitor);
				}
				catch (Exception e) { throw new InvocationTargetException(e); } 
				finally { monitor.done(); }
			}
			
		};
		
		// Run the operation.
		try {
			getContainer().run(true, false, operation);
		}
		catch (InterruptedException e) {
			return false;
		}
		catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Code Generation Error", realException.toString());
			CodeGenPlugin.getInstance().logError("Code Generation Error.", e);
			return false;
		}

		// Everything was ok.
		return true;
	
	}

	
	/**
	 * Set the dialog where the wizard is displayed in.
	 * @param dialog WizardDialog.
	 */
	public void setDialog(WizardDialog dialog) {
		
		if (this.dialog!=null) {
			dialog.removePageChangedListener(this);
		}
		if (dialog!=null) {
			dialog.addPageChangedListener(this);
		}
		
		this.dialog = dialog;
	
	}
	
	
	/**
	 * Listen to page chenges and update the genModel accordingly.
	 */
	public void pageChanged(PageChangedEvent event) {
		
		if (currentPage!=null) {
			currentPage.updateGenModel(genModel);
		}
		currentPage = (ICodeGenWizardPage) event.getSelectedPage();
		currentPage.updatePage(genModel);
		
	}
	
}
