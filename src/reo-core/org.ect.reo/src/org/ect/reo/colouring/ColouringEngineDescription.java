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
package org.ect.reo.colouring;


/**
 * Description of a colouring engine.
 * @generated NOT
 * @author Christian Krause
 */
public class ColouringEngineDescription {
	
	// Properties of a colouring engine.
	protected String name, pluginId, className;
	
	/**
	 * Default constructor.
	 */
	public ColouringEngineDescription(String name, String pluginId, String className) {
		this.name = name;
		this.pluginId = pluginId;
		this.className = className;
	}
	
	/**
	 * Load the engine.
	 */
	public ColouringEngine loadEngine() throws Exception {
		Class<?> platform = Class.forName("org.eclipse.core.runtime.Platform");
		Object bundle = platform.getMethod("getBundle", String.class).invoke(null, pluginId);
		Class<?> clazz = (Class<?>) bundle.getClass().getMethod("loadClass", String.class).invoke(bundle, className); 
		return (ColouringEngine) clazz.newInstance();
	}
	
	/**
	 * Get the name of the engine.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the plug-in id of the colouring engine. 
	 */
	public String getPluginId() {
		return pluginId;
	}
	
	/**
	 * Get the class name of the engine.
	 */
	public String getClassName() {
		return className;
	}
	
}
