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
import org.eclipse.core.runtime.Platform;
import org.ect.codegen.parts.CodeGenPlugin;
import org.osgi.framework.Bundle;



public class CodeGenTargetExtension {

	private IConfigurationElement element;
	private Class<?> targetClass;
   
	public CodeGenTargetExtension(IConfigurationElement element) {  
	   this.element = element;
	}
	
	public String getTargetClassName() {
		return element.getAttribute("targetClass");
	}

	public String getTargetPluginID() {
		return element.getAttribute("targetPlugin");
	}
	
	/*
	 * Warning: should not be used during startup.
	 */
	public Class<?> getTargetClass() {
		
		if (targetClass!=null) {
			return targetClass;
		}

		try {
			Bundle bundle = Platform.getBundle(getTargetPluginID());
			targetClass = bundle.loadClass(getTargetClassName());

		} catch (Throwable e) {
			CodeGenPlugin.getInstance().logError("Error loading class: " + getTargetClassName(), e);
		}

		return targetClass;
	   
	}
	
}
