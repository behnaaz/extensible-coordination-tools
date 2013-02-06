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
package org.ect.codegen.reo2mc.plugin;

import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.ect.codegen.reo2mc.plugin.Reo2mcPlugin;


public class Reo2mcPlugin extends AbstractUIPlugin{
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.ect.codegen.reo2mc";
	
	// The shared instance
	private static Reo2mcPlugin plugin;
	
	public Reo2mcPlugin() {
	}
	
	public static Reo2mcPlugin getDefault(){
		return plugin; 
	}
	
	//===============	logging	=====================
	public void log(String msg) {
		log(msg, null);
	}
	
	public void log(String msg, Exception e) {
		getLog().log(new Status(Status.INFO, PLUGIN_ID, Status.OK, msg, e));
	}
}
