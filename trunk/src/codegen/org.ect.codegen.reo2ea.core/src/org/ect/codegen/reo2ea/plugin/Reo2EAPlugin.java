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
package org.ect.codegen.reo2ea.plugin;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Reo2EAPlugin extends AbstractUIPlugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.ect.codegen.reo2ea.core";
	
	// The shared instance
	public static Reo2EAPlugin plugin;
	
	public URL root;

	/**
	 * The constructor
	 */
	public Reo2EAPlugin() {
		plugin = this;
	}


	public void start(BundleContext context) throws Exception {
		super.start(context);

		URL url = getDefault().getBundle().getEntry("/");
		root = FileLocator.toFileURL(url);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * @return the shared instance
	 */
	public static Reo2EAPlugin getDefault() {
		return plugin;
	}
	
	//===============	logging	=====================
	public static void log(String msg) {
		log(msg, null);
	}
	
	public static void log(String msg, Throwable e) {
		if (plugin!=null)
			plugin.getLog().log(new Status( Status.WARNING, PLUGIN_ID, msg, e));
		else
			System.err.println(msg + e);
	}
	
	public static String handleError(Exception e) {
		Throwable realException = e;
		StringBuffer message = new StringBuffer(); 
		
		while (realException.getCause()!=null) {
			if (realException.getMessage()!=null)
				message.append(realException.getMessage()).append("\n\t");
			
			realException = realException.getCause();
		}
		Reo2EAPlugin.log("Reo2ea Error", realException);

		return 	message.length()==0 ? 
			"Unknown error in "+realException.getStackTrace()[0].toString() :
			message.toString();
	}
}
