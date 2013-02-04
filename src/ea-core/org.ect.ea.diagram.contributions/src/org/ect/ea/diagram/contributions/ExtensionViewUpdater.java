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
package org.ect.ea.diagram.contributions;

import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.EA;
import org.ect.ea.ICustomExtensionProvider;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IExtensionViewUpdater;

/**
 * Default implementation of {@link IExtensionViewUpdater}.
 * @generated NOT
 * @author Christian Krause
 */
public class ExtensionViewUpdater implements IExtensionViewUpdater {
	
	// Extension registry to be used.
	private IExtensionRegistry registry;
	
	/**
	 * Default constructor.
	 */
	public ExtensionViewUpdater() {
		this.registry = EA.getExtensionRegistry();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.util.IExtensionViewUpdater#updateExtensionView(org.ect.ea.extensions.IExtension)
	 */
	public void updateExtensionView(IExtension extension, View view) {
		
		// Get the extension definition.
		IExtensionDefinition definition = registry.findExtensionDefinition(extension.getId());
		
		if (definition.getProvider() instanceof ITextualExtensionProvider) {
			TextualExtensionsUpdater.updateTextualExtension(extension, view);
		}
		if (definition.getProvider() instanceof ICustomExtensionProvider) {
			ICustomExtensionProvider provider = (ICustomExtensionProvider) definition.getProvider();
			provider.updateExtensionView(extension, view);
		}

	}
	
	
	/**
	 * Update the views of all extensions of an {@link IExtendible}.
	 * @param owner The owner of the extensions.
	 * @param view The view of the owner.
	 */
	public void updateExtensionViews(IExtendible owner, View view) {
		for (IExtension extension : owner.getExtensions()) {
			updateExtensionView(extension, view);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.util.IExtensionViewUpdater#prepareExtensionRemoval(org.ect.ea.extensions.IExtension)
	 */
	public void prepareExtensionRemoval(IExtension extension, View view) {

		// Get the extension definition.
		IExtensionDefinition definition = registry.findExtensionDefinition(extension.getId());

		// Prepare the removal.
		if (definition.getProvider() instanceof ITextualExtensionProvider) {
			TextualExtensionsUpdater.removeTextualExtension(extension, view);				
		}				
		if (definition.getProvider() instanceof ICustomExtensionProvider) {
			ICustomExtensionProvider provider = (ICustomExtensionProvider) definition.getProvider();
			provider.prepareExtensionRemoval(extension, view);
		}
	}
}
