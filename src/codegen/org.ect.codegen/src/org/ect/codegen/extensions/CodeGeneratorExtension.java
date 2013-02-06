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

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.ect.codegen.ICodeGenerator;
import org.ect.codegen.parts.CodeGenPlugin;
import org.ect.codegen.proxies.CodeGenContributionProxy;

public class CodeGeneratorExtension {
	
	// Extension configuration.
	private IConfigurationElement element;
	
	// The main code generator instance.
	private ICodeGenerator codeGenerator;
	
	// Additional complex properties.
	private CodeGenPropertyExtension[] properties;
	private CodeGenTargetExtension[] targets;
	private CodeGenWizardPageExtension[] wizardPages;
	
	// Proxy object contributions.
	private CodeGenContributionProxy[] objectContributions;
   
	
	/**
	 * Default constructor.
	 * @param element Configuration element with type "org.ect.codegen.codeGenerators".
	 */
	public CodeGeneratorExtension(IConfigurationElement element) {  
		this.element = element;
	}
   
	
	public String getName() {
		return element.getAttribute("name");
	}

	public String getId() {
		return element.getAttribute("id");
	}
	
	public String getNameFilter() {
		return element.getAttribute("nameFilter");
	}

	public String getIcon() {
		return element.getAttribute("icon");
	}

   
	public CodeGenPropertyExtension[] getProperties() {
	   
		if (properties!=null) {
			return properties;
		}
	   
		IConfigurationElement[] children = element.getChildren("property");
		properties = new CodeGenPropertyExtension[children.length+2];
	   
		properties[0] = CodeGenPropertyExtension.PROJECT_NAME_PROPERTY;
		for (int i=0; i<children.length; i++) {
			properties[i+1] = new CodeGenPropertyExtension(children[i]);
		}
		properties[properties.length-1] = CodeGenPropertyExtension.PROJECT_LOCATION_PROPERTY;
	   
		return properties;
   
	}

   
	public CodeGenTargetExtension[] getTargets() {

		if (targets != null) {
			return targets;
		}
		IConfigurationElement[] children = element.getChildren("target");
		targets = new CodeGenTargetExtension[children.length];

		for (int i = 0; i < children.length; i++) {
			targets[i] = new CodeGenTargetExtension(children[i]);
		}

		return targets;

	}

   
	public CodeGenWizardPageExtension[] getWizardPages() {

		if (wizardPages != null) {
			return wizardPages;
		}
		IConfigurationElement[] children = element.getChildren("wizardPage");
		wizardPages = new CodeGenWizardPageExtension[children.length];

		for (int i = 0; i < children.length; i++) {
			wizardPages[i] = new CodeGenWizardPageExtension(children[i]);
		}

		return wizardPages;

	}

   
	public ICodeGenerator getCodeGenerator() {

		if (codeGenerator != null) {
			return codeGenerator;
		}
		try {
			codeGenerator = (ICodeGenerator) element.createExecutableExtension("generatorClass");
		} catch (Exception e) {
			CodeGenPlugin.getInstance().logError(
					"Error loading code generator: " + element.getAttribute("generatorClass"), e);
		}

		return codeGenerator;

	}

	
	public Object[] extractTargets(ISelection selection) {

		Object[] empty = new Object[0];
		List<Object> targets = new Vector<Object>(3);

		if (!(selection instanceof IStructuredSelection)) {
			return empty;
		}
		
		IStructuredSelection structured = (IStructuredSelection) selection;
		
		if (structured.isEmpty()) {
			return empty;
		}

		Iterator<?> it = structured.iterator();
		while (it.hasNext()) {
			Object target = it.next();
			for (int i = 0; i < getTargets().length; i++) {
				if (getTargets()[i].getTargetClass() == null)
					continue;
				if (getTargets()[i].getTargetClass().isInstance(target)) {
					targets.add(target);
				}
			}
		}

		return targets.toArray();

	}

	
	public boolean isValidTarget(ISelection selection) {
		return extractTargets(selection).length != 0;
	}

	
	public boolean isValid() {
		return getCodeGenerator() != null;
	}

	
	public CodeGenContributionProxy[] getContributionProxies() {
		
		if (objectContributions != null) {
			return objectContributions;
		}
		
		// Create an object contribution for each target.
		objectContributions = new CodeGenContributionProxy[getTargets().length];
		for (int i=0; i<getTargets().length; i++) {
			objectContributions[i] = new CodeGenContributionProxy(this, getTargets()[i]);
		}
		
		return objectContributions;
	}

	
	public IConfigurationElement getElement() {
		return element;
	}


}
