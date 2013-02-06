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
package org.ect.codegen.extensions;

import org.eclipse.core.runtime.IConfigurationElement;
import org.ect.codegen.ICodeGenWizardPage;
import org.ect.codegen.parts.CodeGenPlugin;



public class CodeGenWizardPageExtension {

	private IConfigurationElement element;
	private ICodeGenWizardPage wizardPage;
   
	public CodeGenWizardPageExtension(IConfigurationElement element) {  
	   this.element = element;
	}
	
	
	public ICodeGenWizardPage getWizardPage() {
		   
		if (wizardPage!=null) {
			return wizardPage;
		}
		   
		try {
			wizardPage = (ICodeGenWizardPage) element.createExecutableExtension("pageClass");
		} catch (Exception e) {
			CodeGenPlugin.getInstance().logError("Error instantiating wizard page: " + element.getAttribute("pageClass"), e);
		}
		   
		return wizardPage;
	}
	
}
