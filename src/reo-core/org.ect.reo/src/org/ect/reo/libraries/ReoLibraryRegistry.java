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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.ect.reo.Reo;


/**
 * Registry for Reo libraries.
 * @author Christian Krause
 * @generated NOT
 */
public class ReoLibraryRegistry extends CompoundLibraryProvider {
	
	// Static instance.
	public static final ReoLibraryRegistry INSTANCE = new ReoLibraryRegistry();
	
	// The extension point id of this configuration type.
	public static final String EXTENSION_POINT_ID = "org.ect.reo.libraryProviders";
	
	/*
	 * Private constructor.
	 */
	private ReoLibraryRegistry() {
				
		// Get registered automata extension definitions.
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(EXTENSION_POINT_ID);
		IConfigurationElement[] genElements = extensionPoint.getConfigurationElements();
		
		// Register the providers.
		for (int i=0;i<genElements.length; i++) {
			try {
				LibraryProvider provider = (LibraryProvider) genElements[i].createExecutableExtension("class");
				getProviders().add(provider);
			}
			catch (Exception e) {
				Reo.logError("Cannot instantiate library provider.", e);
			}
		}
		
	}
	
}
