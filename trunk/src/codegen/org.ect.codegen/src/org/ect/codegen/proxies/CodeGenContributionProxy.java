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
import org.ect.codegen.extensions.CodeGenTargetExtension;
import org.ect.codegen.extensions.CodeGeneratorExtension;

public class CodeGenContributionProxy extends AbstractProxy {

	// Code generator to be used.
	private CodeGeneratorExtension generator;

	// Target class for the object contribution.
	private CodeGenTargetExtension target;

	// Code generation action.
	private CodeGenActionProxy actionProxy;

	// Code generation menu.
	private CodeGenMenuProxy menuProxy;

	
	/**
	 * Default constructor.
	 * @param generator Code generator to be used.
	 */
	public CodeGenContributionProxy(CodeGeneratorExtension generator, CodeGenTargetExtension target) {
		super(generator.getElement());
		this.generator = generator;
		this.target = target;
		this.actionProxy = new CodeGenActionProxy(generator);
		this.menuProxy = new CodeGenMenuProxy(generator);
	}

	
	public String getName() throws InvalidRegistryObjectException {
		return IWorkbenchRegistryConstants.TAG_OBJECT_CONTRIBUTION;
	}

	
	public String getAttribute(String name) throws InvalidRegistryObjectException {
		
		// 'id' property.
		if (IWorkbenchRegistryConstants.ATT_ID.equalsIgnoreCase(name)) {
			return generator.getId();
		}
		// 'objectClass' property.
		if (IWorkbenchRegistryConstants.ATT_OBJECTCLASS.equalsIgnoreCase(name)) {
			return target.getTargetClassName();
		}
		// 'adaptable' property.
		if (IWorkbenchRegistryConstants.ATT_ADAPTABLE.equalsIgnoreCase(name)) {
			return "false";
		}
		// 'nameFilter' property.
		if (IWorkbenchRegistryConstants.ATT_NAME_FILTER.equalsIgnoreCase(name)) {
			return generator.getNameFilter();
		}	
		
		return null;
	
	}

	
	public String[] getAttributeNames() throws InvalidRegistryObjectException {
		return new String[] {
				IWorkbenchRegistryConstants.ATT_ID, IWorkbenchRegistryConstants.ATT_OBJECTCLASS,
				IWorkbenchRegistryConstants.ATT_ADAPTABLE, IWorkbenchRegistryConstants.ATT_NAME_FILTER
		};
	}

	
	public IConfigurationElement[] getChildren() throws InvalidRegistryObjectException {
		return new IConfigurationElement[] { menuProxy, actionProxy };
	}

	
	public IConfigurationElement[] getChildren(String name) throws InvalidRegistryObjectException {
		if (IWorkbenchRegistryConstants.TAG_ACTION.equalsIgnoreCase(name)) {
			return new IConfigurationElement[] { actionProxy };
		} else {
			return new IConfigurationElement[] { };
		}
	}

	
	public boolean isValid() {
		return generator.isValid();
	}
	
	
	public String getTargetClassName() {
		return target.getTargetClassName();
	}
	
}
