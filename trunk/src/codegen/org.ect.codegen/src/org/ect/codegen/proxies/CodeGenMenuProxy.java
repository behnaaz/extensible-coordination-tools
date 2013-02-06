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
package org.ect.codegen.proxies;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.ect.codegen.extensions.CodeGeneratorExtension;
import org.ect.codegen.parts.CodeGenPlugin;

public class CodeGenMenuProxy extends AbstractProxy {

	private CodeGenSeparatorProxy separatorProxy;
	
	public CodeGenMenuProxy(CodeGeneratorExtension generator) {
		super(generator.getElement());
		this.separatorProxy = new CodeGenSeparatorProxy(generator);
	}

	public String getName() throws InvalidRegistryObjectException {
		return IWorkbenchRegistryConstants.TAG_MENU;
	}	
	
	public String getAttribute(String name) throws InvalidRegistryObjectException {
		
		// 'id' property.
		if (IWorkbenchRegistryConstants.ATT_ID.equalsIgnoreCase(name)) {
			return CodeGenPlugin.CODEGEN_MENU_ID;
		}
		// 'label' property.
		if (IWorkbenchRegistryConstants.ATT_LABEL.equalsIgnoreCase(name)) {
			return CodeGenPlugin.CODEGEN_MENU_LABEL;
		}
		// 'path' property.
		if (IWorkbenchRegistryConstants.ATT_PATH.equalsIgnoreCase(name)) {
			return CodeGenPlugin.CODEGEN_MENU_PATH;
		}
		
		return null;
	
	}


	public String[] getAttributeNames() throws InvalidRegistryObjectException {
		return new String[] {
				IWorkbenchRegistryConstants.ATT_ID, 
				IWorkbenchRegistryConstants.ATT_LABEL,
				IWorkbenchRegistryConstants.ATT_PATH 
		};
	}

	
	public IConfigurationElement[] getChildren() throws InvalidRegistryObjectException {
		return new IConfigurationElement[] { separatorProxy };
	}

	
	public IConfigurationElement[] getChildren(String name) throws InvalidRegistryObjectException {
		return getChildren();
	}

	public boolean isValid() {
		return true;
	}
		
}
