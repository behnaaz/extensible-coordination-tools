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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.IValidationResult.ValidationResultType;

public class ExtensionLabelHelper {

	// The extension and its definition + provider.
	private IExtension extension;

	// Extension registry.
	private IExtensionRegistry registry;
	
	// The label icon.
	private Image icon;
	private ImageDescriptor iconDescriptor;
	
	
	/**
	 * Default constructor.
	 */
	public ExtensionLabelHelper(IGraphicalEditPart editpart) {		
		extension = (IExtension) editpart.getNotationView().getElement();
		registry = EA.getExtensionRegistry();
	}
	
	
	public Image getLabelIcon() {
		
		// These things cannot be cached.
		IExtensionDefinition definition = getExtensionDefinition();
		ITextualExtensionProvider provider = getExtensionProvider();

		if (provider!=null) {
			
			// Validate the extension.
			IValidationResult result = provider.validateExtension(getExtension());
			
			// Validation error.
			if (result.getType()==ValidationResultType.WARNING) {
				return ContributionsPlugin.getInstance().getBundledImage(ContributionsPlugin.ICON_WARNING);
			}	
			// Validation warning.
			if (result.getType()==ValidationResultType.ERROR) {
				return ContributionsPlugin.getInstance().getBundledImage(ContributionsPlugin.ICON_ERROR);
			}
			
			// No icon.
			if (definition.getIcon()==null) {
				return null;
			}
			
			// Default extension icon.
			if (definition.getIcon()!=iconDescriptor) {
				iconDescriptor = definition.getIcon();
				icon = iconDescriptor.createImage();
			}
			
			return icon;

		}
		
		// No icon.
		return null;

	}

	
	/**
	 * Get the tool tip for the label.
	 */
	public String getToolTip() {
		
		ITextualExtensionProvider provider = getExtensionProvider();
		
		if (provider!=null) {
			
			// Validate the extension.
			IValidationResult result = provider.validateExtension(getExtension());

			if (result.getType()==ValidationResultType.WARNING ||
				result.getType()==ValidationResultType.ERROR) {
				return result.getMessage();
			}
		}
		
		return getExtensionDefinition().getName();
	
	}
    
	
	/**
	 * Get the font color of the extension label.
	 */
	public Color getFontColor() {
		ITextualExtensionProvider provider = getExtensionProvider();
		if (provider.getFontColor(extension)!=null) {
			return provider.getFontColor(extension);
		} else {
			return ColorConstants.black;
		}	
	}
	
	
	/**
	 * Asks the provider to check whether the extension
	 * is editable or not.
	 */
	public boolean isEditable() {
		ITextualExtensionProvider provider = getExtensionProvider();
		return !provider.isReadOnly(extension);
	}
	
	
	/**
	 * Get the extension that is displayed in this label.
	 */
	public IExtension getExtension() {
		return extension;
	}
	
	/**
	 * Get the extension definition of the used extension.
	 */
	public IExtensionDefinition getExtensionDefinition() {
		return registry.findExtensionDefinition(extension.getId());
	}

	/**
	 * Get the textual extension provider.
	 */
	public ITextualExtensionProvider getExtensionProvider() {
		IExtensionProvider provider = getExtensionDefinition().getProvider();
		if (provider instanceof ITextualExtensionProvider) {
			return (ITextualExtensionProvider) provider;
		}
		return null;
	}

}
