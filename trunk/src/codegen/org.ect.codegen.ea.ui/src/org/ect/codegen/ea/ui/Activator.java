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
package org.ect.codegen.ea.ui;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplateGroup;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class Activator extends AbstractUIPlugin {
	public static Activator instance;
//	private static String[] templates = {"main"};
	
	String templateRoot;

	private Map<String, StringTemplateGroup> templateGroups 
	= new HashMap<String, StringTemplateGroup>();
	
	@Override
	public void start(BundleContext context) throws Exception {
		instance = this;
		super.start(context);
		URL url = getBundle().getEntry("/templates");
		templateRoot = FileLocator.toFileURL(url).getPath();

	}
	
	public StringTemplateGroup getTemplateGroup(String name) {
		return templateGroups .get(name);
	}
}
