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
package org.ect.codegen;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;



/**
 * This is an example wizard page for code generation wizard.
 * This is not meant to be used in applications nor to be
 * subclassed. Its just here to illustrated how to use the
 * wizardPage attribute in the org.ect.codegen.codeGenerators
 * extension point.
 * 
 * Important: don't forget to add your additional pages
 * to your codeGenerators extension.
 *
 */
final public class ExampleCodeGenWizardPage extends WizardPage implements ICodeGenWizardPage {

	private Text text;
	
	private static int counter = 1;

	
	public ExampleCodeGenWizardPage() {
		super("Page #" + counter);
		setTitle("Page #" + counter);
		setDescription("This is a dummy page. Not meant to be used in real code generators..");
	}

	
	public void createControl(Composite parent) {
	
		Composite body = new Composite(parent, SWT.FILL);
		body.setLayout(new FillLayout());	
		
		text = new Text(body, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		
		setControl(body);
	
	}
	

	public void updateGenModel(IGenModel genModel) {
		
		// This shouldn't happen, but we check anyway.
		if (text==null || text.isDisposed()) return;
		
		// Update the project name property in the genModel.
		genModel.setProperty(IGenModel.PROJECT_NAME, text.getText());
		
	}

	
	public void updatePage(IGenModel genModel) {

		if (text==null || text.isDisposed()) return;
		
		// Update the text widget.
		text.setText(genModel.getProperty(IGenModel.PROJECT_NAME));
		
	}
	
	
}
