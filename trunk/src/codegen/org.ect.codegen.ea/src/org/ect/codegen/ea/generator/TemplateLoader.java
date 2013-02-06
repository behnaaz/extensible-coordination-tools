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
package org.ect.codegen.ea.generator;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.antlr.stringtemplate.StringTemplateGroup;

public class TemplateLoader {
	public static final String EXTENSION = ".stg";
	private Map<String,StringTemplateGroup> templates = new HashMap<String,StringTemplateGroup>();

	public TemplateLoader(String path) throws IOException {
		FileFilter ff = new FileFilter() {
			public boolean accept(File f) {
				return f.getName().endsWith(EXTENSION);
			}
		};
		
		for (File f: new File(path).listFiles(ff)) {
			FileReader groupFile = new FileReader(f);
			StringTemplateGroup stg = new StringTemplateGroup(groupFile);
			String name = f.getName().split(EXTENSION)[0];
			templates .put(name.toUpperCase(), stg); 
			groupFile.close();
		}

	}
	
	public StringTemplateGroup getGroup(String name) {
		return templates.get(name.toUpperCase());
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(
				new TemplateLoader("templates").templates);
	
	}
}
