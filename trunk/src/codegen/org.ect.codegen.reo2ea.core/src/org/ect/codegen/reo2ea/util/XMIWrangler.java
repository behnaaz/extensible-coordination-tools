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
package org.ect.codegen.reo2ea.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class XMIWrangler {
/*	
	static {
		AutomataPackage.init();
		PortNamesPackage.init();
		ConstraintsPackage.init();
		StateMemoryPackage.init();
		ReoPackage.init();
		NotationPackage.eINSTANCE.getDiagram();		

		Resource.Factory.Registry.INSTANCE.
		getExtensionToFactoryMap().put("ea", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.
		getExtensionToFactoryMap().put("reo", new XMIResourceFactoryImpl());
	}
*/	
	public static org.ect.reo.Module loadReo(String path) throws IOException {
		// Get the URI of the model file.
		URI fileURI = URI.createFileURI(path);
//		URI fileURI = URI.createPlatformResourceURI(path,false);
		System.err.println("Loading connectors from " + fileURI);

		// Demand load the resource for this file.
		Resource resource = new ResourceSetImpl().getResource(fileURI, true);
		
		List<EObject> circuits =  resource.getContents();
		return (org.ect.reo.Module) circuits.get(0);
	}
	
	public static org.ect.ea.automata.Module loadAutomata(String path) throws IOException {
		// Get the URI of the model file.
		URI fileURI = URI.createFileURI(path);
		System.err.println("Loading automata from " + fileURI);
		
		return loadAutomata(fileURI);
	}
	
	public static org.ect.ea.automata.Module loadAutomata(URI uri) throws IOException {
		try {
			// Demand load the resource for this file.
			Resource resource = new ResourceSetImpl().getResource(uri, true);
			List<EObject> circuits =  resource.getContents();
			return (org.ect.ea.automata.Module) circuits.get(0);
		} catch (WrappedException e) {
			Throwable cause = e.getCause();
			throw cause instanceof IOException ? 
					(IOException)cause : new IOException("error loading "+uri);
		}
	}

	public static List<org.ect.ea.automata.Module> loadAllCAs(String dir) throws IOException {
		List<org.ect.ea.automata.Module> cas = new ArrayList<org.ect.ea.automata.Module>();
		
		FileFilter ff = new FileFilter() {
			public boolean accept(File f) {
				return f.getName().endsWith(".ea");
			}
		};
		
		for (File f: new File(dir).listFiles(ff)) {
			org.ect.ea.automata.Module mod = loadAutomata(f.getAbsolutePath());
			cas.add(mod);
		}
		
		return cas;
	}
	
	public static void saveAutomata(org.ect.ea.automata.Module module, String path) throws IOException {
		System.err.println("Saving automaton to " + path);
		saveAutomata(module, URI.createFileURI(path));		
	}

		
	public static void saveAutomata(org.ect.ea.automata.Module module, URI uri) throws IOException {
		try {
			// Create a resource for this file.
			Resource resource = new ResourceSetImpl().createResource(uri);
			resource.getContents().add(module);
			resource.save(Collections.EMPTY_MAP);
		} catch (WrappedException e) {
			Throwable cause = e.getCause();
			throw cause instanceof IOException ? 
					(IOException)cause : new IOException("error loading "+uri);
		}
	}
}
