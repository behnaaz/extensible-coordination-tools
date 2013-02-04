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
package org.ect.reo.diagram.figures.activator;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * @generated NOT
 */
public class ReoDiagramFiguresPlugin extends Plugin {

	// Plug-In ID.
	public static final String ID = "org.ect.reo.diagram.figures";
	
	// Instance.
	private static ReoDiagramFiguresPlugin instance;
	
	/**
	 * Constructor.
	 */
	public ReoDiagramFiguresPlugin() {
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	/**
	 * Get the instance.
	 */
	public static ReoDiagramFiguresPlugin getInstance() {
		return instance;
	}
}
