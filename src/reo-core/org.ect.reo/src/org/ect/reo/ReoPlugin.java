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
package org.ect.reo;

import org.eclipse.core.runtime.Plugin;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.osgi.framework.BundleContext;



/**
 * Reo plug-in activator.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public class ReoPlugin extends Plugin {
	
	// Plug-in ID.
	public static final String ID = "org.ect.reo";
	
	// Instance of the ReoPlugin.
	private static ReoPlugin instance;
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
		// Load textual semantics providers.
		try {
			ReoTextualSemantics.REGISTRY.loadProviders();
		} catch (Throwable t) {
			Reo.logError("Error loading textual semantics registry.", t);
		}
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}
	
	public static ReoPlugin getInstance() {
		return instance;
	}
	
}
