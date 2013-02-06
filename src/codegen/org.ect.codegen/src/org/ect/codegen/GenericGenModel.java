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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;


public class GenericGenModel implements IGenModel {

	private Object target;
	private HashMap<String,String> properties;
	
	public GenericGenModel(Object target) {
		this.target = target;
		this.properties = new HashMap<String,String>();
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getProperty(String key) {
		return properties.get(key);
	}

	public void setProperty(String key, String value) {
		properties.put(key,value);
	}

	public String getTargetId() {
		// TODO Auto-generated method stub
		return null;
	}

	public IFile getResource() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

}
