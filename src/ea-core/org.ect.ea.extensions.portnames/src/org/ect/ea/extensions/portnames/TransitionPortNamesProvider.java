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
package org.ect.ea.extensions.portnames;

import java.text.ParseException;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IValidationResult;

public class TransitionPortNamesProvider implements ITextualExtensionProvider {

	// The ID of the extensions.
	public static final String EXTENSION_ID = "cwi.ea.extensions.transitionPortNames";
		
	// The name of the extensions.
	public static final String EXTENSION_NAME = "Port Names (Transtions)";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.TRANSITIONS, new TransitionPortNamesProvider());

	
	/**
	 * Create a default TransitionPortNames extension.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		// Create an empty list of port names.
		return new TransitionPortNames();
	}
	
	/**
	 * Create a silent extension. This is the same as the default one.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return createDefaultExtension(transition);
	}

	/**
	 * Check whether a port names extension is silent, i.e. empty.
	 */
	public boolean isSilentExtension(IExtension extension) {
		TransitionPortNames names = (TransitionPortNames) extension;
		return names.getValues().isEmpty();
	}


	
	public String editExtension(IExtension extension) {
		TransitionPortNames portNames = (TransitionPortNames) extension;
		return portNames.toString();
	}
	
	public String printExtension(IExtension extension) {
		return "{" + editExtension(extension) + "}";
	}
	
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		TransitionPortNames portNames = TransitionPortNames.parse(value);
		portNames.trimAll();
		return portNames;
	}

	
	/**
	 * Compute the product of two port name extensions.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		EList<IExtension> result = new BasicEList<IExtension>();
		TransitionPortNames p1 = (TransitionPortNames) x1;
		TransitionPortNames p2 = (TransitionPortNames) x2;
				
		Transition t1 = (Transition) x1.getOwner();
		Transition t2 = (Transition) x2.getOwner();
			
		if (PortNamesProductConditions.canJoin(t1, t2)) {
			TransitionPortNames joined = TransitionPortNames.join(p1, p2);
			result.add(joined);
		}
				
		return result;
		
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IExtensionProvider#validateExtension(org.ect.ea.extensions.IExtension)
	 */
	public IValidationResult validateExtension(IExtension extension) {
		return ((TransitionPortNames) extension).validate();
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#getLabelColor()
	 */
	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkBlue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.ITextualExtensionProvider#isReadOnly(org.ect.ea.extensions.IExtension)
	 */
	public boolean isReadOnly(IExtension extension) {
		return false;
	}

}
