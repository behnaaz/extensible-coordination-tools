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
package org.ect.codegen.reo2ea.qia.plugin;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.ect.codegen.reo2ea.qia.transform.channels.ChannelTransform;
import org.ect.codegen.reo2ea.util.XMIWrangler;
import org.osgi.framework.BundleContext;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;

/**
 * The activator class controls the plug-in life cycle
 */
public class Reo2QIAPlugin extends AbstractUIPlugin {
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.ect.codegen.reo2ea.qia";
	
	// The shared instance
	private static Reo2QIAPlugin plugin;
	
	//Mappings for Reo primitives 
	private static final String PRIMITIVES_PATH="/primitives";

	/**
	 * The constructor
	 */
	public Reo2QIAPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		URL url = getDefault().getBundle().getEntry("/primitives");
		URL root = FileLocator.toFileURL(url);

		Collection<Automaton> automata = new ArrayList<Automaton>();
		for( Module m: XMIWrangler.loadAllCAs(root.getPath()))
			automata.addAll(m.getAutomata());
//		XXX
		ChannelTransform.setTemplates(automata);
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
	public static Reo2QIAPlugin getDefault() {
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
