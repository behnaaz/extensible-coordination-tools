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
package org.ect.codegen.ea2mc;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class EATranslation extends AbstractUIPlugin {

	private static final String PRIMITIVES_PATH = "/templates";
	public static final String PLUGIN_ID = "org.ect.codegen.ea2mc"; 
	public static EATranslation INSTANCE;
	public TemplateLoader templates;
	public EATranslation() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
		URL url = getBundle().getEntry(PRIMITIVES_PATH);
		//URL local = FileLocator.toFileURL(url);
		templates = new TemplateLoader(FileLocator.toFileURL(url).getPath());
	}
	
}
