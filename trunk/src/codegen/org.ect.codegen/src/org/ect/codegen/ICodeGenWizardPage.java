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

import org.eclipse.jface.wizard.IWizardPage;

/**
 * Wizard page for the code generation wizard.
 * To add additional pages to the code generation
 * wizard, this interface has to be implemented
 * and it must be added to the codeGeneration extension
 * description in the plugin.xml file. 
 * 
 * @see org.ect.codegen.IGenModel
 * @author Christian Koehler
 *
 */
public interface ICodeGenWizardPage extends IWizardPage {

	/**
	 * Read all required information from the genModel
	 * and update the widgets of the page with this
	 * content. This method is called after the page
	 * has been created.
	 * 
	 * @param genModel The genModel of the code generation.
	 */
	public void updatePage(IGenModel genModel);

	
	/**
	 * Update the genModel with the content of the
	 * wizard page. This method is called when the uses
	 * flips to the next page or when the wizard is 
	 * finished.
	 * 
	 * @param genModel The genModel of the codeGeneration.
	 */
	public void updateGenModel(IGenModel genModel);
	
}
