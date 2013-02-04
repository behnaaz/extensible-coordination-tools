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
package org.ect.reo.libraries;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.Nameable;
import org.ect.reo.Reo;
import org.ect.reo.util.ComponentCreator;


/**
 * Library provider for Reo files.
 * @author Christian Krause
 * @generated NOT
 */
public class ReoLibraryProvider implements LibraryProvider {
		
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#createConnector(org.ect.reo.CustomPrimitive, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Connector createConnector(CustomPrimitive stub, IProgressMonitor monitor) {
		
		// Load and check connectors.
		monitor.beginTask("Load connectors", 1);
		List<Nameable> elements = loadElements(stub);
		
		for (Nameable element : elements) {
			if (element instanceof Connector) {
				
				Connector connector = (Connector) element;
				
				// Interfaces must match.
				if (interfacesMatch(stub, connector)) {
					monitor.worked(1);
					monitor.done();
					return connector;
				}
			}	
		}
		
		// Nothing found.
		monitor.worked(1);
		monitor.done();
		return null;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#createPrimitive(org.ect.reo.CustomPrimitive, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public CustomPrimitive createPrimitive(CustomPrimitive custom, IProgressMonitor monitor) {
		
		// Load and check components.
		monitor.beginTask("Load components", 1);
		List<Nameable> elements = loadElements(custom);
		
		for (Nameable element : elements) {
			if (element.getClass()==custom.getClass()) {
				CustomPrimitive current = (CustomPrimitive) element;
				
				// Must not be the same component!
				if (custom!=current && interfacesMatch(custom, current)) {
					
					// Copy and disconnect the component.
					current = (Component) EcoreUtil.copy(current);
					
					monitor.worked(1);
					monitor.done();
					return current;
					
				}
			}	
		}
		
		// If there a connector we can use?
		Connector connector = createConnector(custom, new NullProgressMonitor());
		if (connector!=null) {			
			Component component = ComponentCreator.fromConnector(connector, monitor);
			return component;
		}
		
		// Nothing found.
		monitor.worked(1);
		monitor.done();
		return null;
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#canCreateConnector(org.ect.reo.CustomPrimitive)
	 */
	public boolean canCreateConnector(CustomPrimitive stub) {
		return createConnector(stub, new NullProgressMonitor())!=null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#canCreatePrimitive(org.ect.reo.CustomPrimitive)
	 */
	public boolean canCreatePrimitive(CustomPrimitive stub) {
		// First try to create a connector!
		return (canCreateConnector(stub) || createPrimitive(stub, new NullProgressMonitor())!=null);
	}
	
	
	
	// **********************
	// * Helper methods     *
	// **********************	
	
	/*
	 * Load matched elements for a primitive stub.
	 */
	private static List<Nameable> loadElements(CustomPrimitive stub) {
		URI uri = ReoLibraryUtil.getAbsoluteTypeURI(stub);
		if (uri==null || !Reo.FILE_EXT.equals(uri.fileExtension())) {
			return new ArrayList<Nameable>();
		}
		ResourceSet resourceSet = stub.eResource().getResourceSet();
		return Reo.loadNameables(uri, resourceSet);
	}
		
	/*
	 * Check if the interfaces of two components match.
	 */
	private static boolean interfacesMatch(CustomPrimitive c1, CustomPrimitive c2) {
		return c1.getSourceEnds().size()==c2.getSourceEnds().size() &&
				c1.getSinkEnds().size()==c2.getSinkEnds().size();
	}
	
	/*
	 * Check if the interface of a component and a connector match.
	 */
	private static boolean interfacesMatch(CustomPrimitive custom, Connector connector) {
		return custom.getSourceEnds().size()==connector.getSourceNodes().size() &&
				custom.getSinkEnds().size()==connector.getSinkNodes().size();
	}

}
