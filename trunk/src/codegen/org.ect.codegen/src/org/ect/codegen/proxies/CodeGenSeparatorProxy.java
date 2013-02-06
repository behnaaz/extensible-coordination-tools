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

public class CodeGenSeparatorProxy extends AbstractProxy {

	public CodeGenSeparatorProxy(CodeGeneratorExtension generator) {
		super(generator.getElement());
	}

	public String getName() throws InvalidRegistryObjectException {
		return IWorkbenchRegistryConstants.TAG_SEPARATOR;
	}	
	
	public String getAttribute(String name) throws InvalidRegistryObjectException {
		
		// 'path' property.
		if (IWorkbenchRegistryConstants.ATT_NAME.equalsIgnoreCase(name)) {
			return CodeGenPlugin.CODEGEN_MENU_GROUP;
		}
		return null;
	}


	public String[] getAttributeNames() throws InvalidRegistryObjectException {
		return new String[] { IWorkbenchRegistryConstants.ATT_NAME };
	}

	
	public IConfigurationElement[] getChildren() throws InvalidRegistryObjectException {
		return new IConfigurationElement[] { };
	}

	
	public IConfigurationElement[] getChildren(String name) throws InvalidRegistryObjectException {
		return getChildren();
	}

	public boolean isValid() {
		return true;
	}
	
}
