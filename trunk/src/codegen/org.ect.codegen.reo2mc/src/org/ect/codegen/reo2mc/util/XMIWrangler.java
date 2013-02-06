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
package org.ect.codegen.reo2mc.util;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.extensions.constraints.ConstraintsPackage;
import org.ect.ea.extensions.portnames.PortNamesPackage;
import org.ect.reo.ReoPackage;

public class XMIWrangler {
	
	static {
		AutomataPackage.init();
		PortNamesPackage.init();
		ConstraintsPackage.init();
		ReoPackage.init();
		NotationPackage.eINSTANCE.getDiagram();		

		Resource.Factory.Registry.INSTANCE.
		getExtensionToFactoryMap().put("sm", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.
		getExtensionToFactoryMap().put("csv", new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.
		getExtensionToFactoryMap().put("reo", new XMIResourceFactoryImpl());
	}
	
	public static org.ect.reo.Module loadReo(String path) {
		// Get the URI of the model file.
		URI fileURI = URI.createFileURI(path);
//		URI fileURI = URI.createPlatformResourceURI(path,false);
		System.err.println("Loading connectors from " + fileURI);

		// Demand load the resource for this file.
		Resource resource = new ResourceSetImpl().getResource(fileURI, true);
		
		List<EObject> circuits =  resource.getContents();
		return (org.ect.reo.Module) circuits.get(0);
	}
	
}
