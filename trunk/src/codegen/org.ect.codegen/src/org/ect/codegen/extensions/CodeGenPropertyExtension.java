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
import org.ect.codegen.IGenModel;



public class CodeGenPropertyExtension {

	public static final CodeGenPropertyExtension PROJECT_NAME_PROPERTY =
					new CodeGenPropertyExtension(IGenModel.PROJECT_NAME, "Project name", false);

	public static final CodeGenPropertyExtension PROJECT_LOCATION_PROPERTY =
					new CodeGenPropertyExtension(IGenModel.PROJECT_LOCATION, "Location", false);

	private String key;
	private String name;
	private boolean isBoolean;


	public CodeGenPropertyExtension(IConfigurationElement element) {	   
		 this.key = element.getAttribute("key");
		 this.name = element.getAttribute("name");
		 this.isBoolean = "true".equalsIgnoreCase(element.getAttribute("boolean"));
	}

	 public CodeGenPropertyExtension(String key, String name, boolean isBoolean) {	   
		 this.key = key;
		 this.name = name;
		 this.isBoolean = isBoolean;
	 }

	 public String getName() {
		 return name;
	 }

	 public String getKey() {
		 return key;
	 }

	 public boolean isBoolean() {
		 return isBoolean;
	 }
	 	 
}
