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
package org.ect.reo.semantics;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.ect.reo.Reo;


/**
 * Textual semantics registry for Reo.
 * @generated NOT
 * @author Christian Krause
 *
 */
public final class TextualSemanticsRegistry extends ArrayList<TextualSemanticsProvider> {

	// The extension point id of this configuration type.
	public static final String EXTENSION_POINT_ID = "org.ect.reo.textualSemantics";
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Load the semantics providers from the Eclipse extension registry.
	 */
	public void loadProviders() {
		
		// Get registered automata extension definitions.
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(EXTENSION_POINT_ID);
		IConfigurationElement[] genElements = extensionPoint.getConfigurationElements();
		
		// Register the providers.
		for (int i=0;i<genElements.length; i++) {
			try {
				TextualSemanticsProvider provider = (TextualSemanticsProvider) genElements[i].createExecutableExtension("class");
				add(provider);
			}
			catch (Exception e) {
				Reo.logError("Cannot instantiate textual semantics provider.", e);
			}
		}
	}
	
	/**
	 * Get the semantics provider for a given key.
	 * @param key Key of the semantics.
	 * @return The provider if it was found.
	 */
	public TextualSemanticsProvider get(String key) {
		for (TextualSemanticsProvider provider : this) {
			if (key.equals(provider.getSemanticsKey())) return provider;
		}
		return null;
	}
	
}
