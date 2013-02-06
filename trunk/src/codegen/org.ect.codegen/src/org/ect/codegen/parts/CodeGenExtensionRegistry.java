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
package org.ect.codegen.parts;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.internal.IObjectActionContributor;
import org.eclipse.ui.internal.ObjectActionContributor;
import org.eclipse.ui.internal.ObjectActionContributorManager;
import org.ect.codegen.extensions.CodeGeneratorExtension;
import org.ect.codegen.proxies.CodeGenContributionProxy;

public class CodeGenExtensionRegistry {

	private static CodeGeneratorExtension[] codeGenerators;

	/**
	 * Default constructor.
	 */
	public CodeGenExtensionRegistry() {
	}
	
	
	public void init() {

		// System.out.println("Contributing code generator extensions...");

		// Object action manager for dynamically registring objectContributions. 
		ObjectActionContributorManager actionManager = ObjectActionContributorManager.getManager();
		
		// Code generators.
		IConfigurationElement[] genElements = getConfigurationElements("org.ect.codegen.codeGenerators");
		codeGenerators = new CodeGeneratorExtension[genElements.length];
		
		for (int i=0;i<genElements.length; i++) {

			// Create a new CodeGeneratorExtension.
			codeGenerators[i] = new CodeGeneratorExtension(genElements[i]);
			if (!codeGenerators[i].isValid()) continue;
			
			// Register objectContributions for the generator. 
			CodeGenContributionProxy[] proxies = codeGenerators[i].getContributionProxies();
			
			for (int j=0; j<proxies.length; j++) {
				// System.out.println("Registering " + codeGenerators[i].getName() + " for type " + proxies[j].getTargetClassName());
				IObjectActionContributor contributor = new ObjectActionContributor(proxies[j]);
		        actionManager.registerContributor(contributor, proxies[j].getTargetClassName());
			}
	        
		}
		// System.out.println("done.");
		
	}
	
	
	public void dispose() {	
	}
	
	
	public CodeGeneratorExtension[] getCodeGenerators() {		
		if (codeGenerators==null) init();
		return codeGenerators;
	}
	
	
	private IConfigurationElement[] getConfigurationElements(String extensionPointId) {
		
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = registry.getExtensionPoint(extensionPointId);
	
		return extensionPoint.getConfigurationElements();
	
	}
	
}
