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
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;


/**
 * A compound library provider class.
 * @author Christian Krause
 * @generated NOT
 */
public class CompoundLibraryProvider implements LibraryProvider {
	
	// The (internal) library providers.
	private List<LibraryProvider> providers;
	
	/**
	 * Default constructor.
	 */
	public CompoundLibraryProvider() {
		this.providers = new ArrayList<LibraryProvider>();
	}
	
	/**
	 * Get the list of registered library providers.
	 * @return List of providers.
	 */
	public List<LibraryProvider> getProviders() {
		return providers;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#canCreatePrimitive(org.ect.reo.CustomPrimitive)
	 */
	public boolean canCreatePrimitive(CustomPrimitive stub) {
		for (LibraryProvider provider : providers) {
			if (provider.canCreatePrimitive(stub)) return true;
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#canCreateConnector(org.ect.reo.CustomPrimitive)
	 */
	public boolean canCreateConnector(CustomPrimitive stub) {
		for (LibraryProvider provider : providers) {
			if (provider.canCreateConnector(stub)) return true;
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#createPrimitive(org.ect.reo.CustomPrimitive, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public CustomPrimitive createPrimitive(CustomPrimitive stub, IProgressMonitor monitor) {
		for (LibraryProvider provider : providers) {
			if (provider.canCreatePrimitive(stub)) {
				return provider.createPrimitive(stub, monitor);
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.libraries.LibraryProvider#createConnector(org.ect.reo.CustomPrimitive, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Connector createConnector(CustomPrimitive stub, IProgressMonitor monitor) {
		for (LibraryProvider provider : providers) {
			if (provider.canCreateConnector(stub)) {
				return provider.createConnector(stub, monitor);
			}
		}
		return null;
	}
	
}
