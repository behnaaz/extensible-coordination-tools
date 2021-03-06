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
package org.ect.ea;

import java.text.ParseException;

import org.eclipse.swt.graphics.Color;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;

/**
 * Textual extensions occur as strings in an automaton diagram.
 * They appear as labels at the automaton itself, the states or
 * at the transitions of the automaton. This is determined in the 
 * extension point <code>org.ect.ea.extensions.XXXExtension</code>.
 * <p>
 * Besides the methods in {@link IExtensionProvider}, implementations
 * of this interface also must provide methods for parsing and
 * pretty-printing extensions.
 * </p>
 * 
 * @generated NOT
 */
public interface ITextualExtensionProvider extends IExtensionProvider {

	/**
	 * Pretty print an extension for displaying 
	 * it in the diagram.
	 */
	public String printExtension(IExtension extension);

	/**
	 * Pretty print an extension for editing it 
	 * in a text box in the diagram.
	 */
	public String editExtension(IExtension extension);
	
	/**
	 * Parse an string value and update the 
	 * associated extension with the new data.
	 */
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException;

	/**
	 * Get the font color of the extension labels.
	 * If this returns <code>null</code>, black is used.
	 */
	public Color getFontColor(IExtension extension);
	
	/**
	 * Ceck whether the label of a textual extension
	 * should be readonly.
	 */
	public boolean isReadOnly(IExtension extension);

}

