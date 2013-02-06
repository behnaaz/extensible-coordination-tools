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

public class CodeGenActionProxy extends AbstractProxy {

	private CodeGeneratorExtension generator;

	public CodeGenActionProxy(CodeGeneratorExtension generator) {
		super(generator.getElement());
		this.generator = generator;
	}

	
	public String getName() throws InvalidRegistryObjectException {
		return IWorkbenchRegistryConstants.TAG_ACTION;
	}
	
	
	public String getAttribute(String name) throws InvalidRegistryObjectException {
		
		// 'id' property.
		if (IWorkbenchRegistryConstants.ATT_ID.equalsIgnoreCase(name)) {
			return generator.getId();
		}
		// 'label' property.
		if (IWorkbenchRegistryConstants.ATT_LABEL.equalsIgnoreCase(name)) {
			return generator.getName();// CodeGenPlugin.CODEGEN_ACTION_LABEL;
		}
		// 'class' property.
		if (IWorkbenchRegistryConstants.ATT_CLASS.equalsIgnoreCase(name)) {
			return CodeGenPlugin.CODEGEN_ACTION_CLASS;
		}
		// 'definitionId' property.
		if (IWorkbenchRegistryConstants.ATT_DEFINITION_ID.equalsIgnoreCase(name)) {
			return null;
		}
		// 'menubarPath' property.
		if (IWorkbenchRegistryConstants.ATT_MENUBAR_PATH.equalsIgnoreCase(name)) {
			return CodeGenPlugin.CODEGEN_ACTION_MENUBAR_PATH;
		}
		// 'icon' property.
		if (IWorkbenchRegistryConstants.ATT_ICON.equalsIgnoreCase(name)) {
			return generator.getIcon();
		}
		// 'helpContextId' property.
		if (IWorkbenchRegistryConstants.ATT_HELP_CONTEXT_ID.equalsIgnoreCase(name)) {
			return null;
		}
		// 'style' property.
		if (IWorkbenchRegistryConstants.ATT_STYLE.equalsIgnoreCase(name)) {
			return "push";
		}
		// 'state' property.
		if (IWorkbenchRegistryConstants.ATT_STATE.equalsIgnoreCase(name)) {
			return null;
		}
		// 'enablesFor' property.
		if (IWorkbenchRegistryConstants.ATT_ENABLES_FOR.equalsIgnoreCase(name)) {
			return "1";
		}
		// 'overrideActionId' property.
		if ("overrideActionId".equalsIgnoreCase(name)) {
			return null;
		}
		// 'tooltip' property.
		if (IWorkbenchRegistryConstants.ATT_TOOLTIP.equalsIgnoreCase(name)) {
			return null;
		}
		
		return null; //super.getAttribute(name);
	
	}


	public String[] getAttributeNames() throws InvalidRegistryObjectException {
		return new String[] {
				IWorkbenchRegistryConstants.ATT_ID, IWorkbenchRegistryConstants.ATT_LABEL,
				IWorkbenchRegistryConstants.ATT_CLASS, IWorkbenchRegistryConstants.ATT_DEFINITION_ID,
				IWorkbenchRegistryConstants.ATT_MENUBAR_PATH, IWorkbenchRegistryConstants.ATT_ICON,
				IWorkbenchRegistryConstants.ATT_HELP_CONTEXT_ID, IWorkbenchRegistryConstants.ATT_STYLE,
				IWorkbenchRegistryConstants.ATT_STATE, IWorkbenchRegistryConstants.ATT_ENABLES_FOR,
				"overrideActionId", IWorkbenchRegistryConstants.ATT_TOOLTIP
		};
	}

	
	public IConfigurationElement[] getChildren() throws InvalidRegistryObjectException {
		return new IConfigurationElement[] { };
	}

	
	public IConfigurationElement[] getChildren(String name) throws InvalidRegistryObjectException {
		return getChildren();
	}
	
	
	public boolean isValid() {
		return generator.isValid();
	}
	
}
