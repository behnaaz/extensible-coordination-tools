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
package org.ect.ea.extensions.clocks;

import java.text.ParseException;

import org.antlr.runtime.RecognitionException;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.clocks.parsers.TCAClocksParser;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

public class AutomatonClocksProvider implements ITextualExtensionProvider {

	/** The ID of the extension. */
	public static final String EXTENSION_ID = "cwi.ea.extensions.clocks.automatonClocks";

	/**
	 * Create a default AutomatonClocks extension. 
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new StringExtension("{}");
	}

	/**
	 * Edit this extension. 
	 */
	public String editExtension(IExtension extension) {
		StringExtension clockNames = (StringExtension) extension;
		return clockNames.toString().replace("{", "").replace("}", "");
	}
	
	/**
	 * Print this extension.
	 */
	public String printExtension(IExtension extension) {
		return "{" + editExtension(extension) + "}";
	}
	
	/**
	 * Parse a clock extension (syntax checking). A valid clock name consists of letters and digits, starting with a letter.
	 */
	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		
		String ret = "";
		try {
			TCAClocksParser parser = new TCAClocksParser(value.replace("{", "").replace("}", ""));
			ret = parser.automaton_clocks();
		} catch (RecognitionException re) {
			//do nothing
		}
		return new StringExtension(ret);
	}

	/**
	 * Compute the product of two clocks extensions. The product of two clock 
	 * sets is the union of the sets.
	 * @note This method does not check for duplicates in the clock sets, though
	 * the original definition allows to compute the product only in case the 
	 * clock sets of the involved automata are disjoint. 
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		BasicEList<IExtension> ret = new BasicEList<IExtension>();
		
		try {
			StringListExtension s1 = StringListExtension.parse(x1.toString());
			StringListExtension s2 = StringListExtension.parse(x2.toString());
			s1.append(s2, true);
		    ret.add(new StringExtension(s1.toString()));
		} catch (ParseException e) {
			System.err.println("Parse error during clocks product computation.");
		}
		
		return ret;		
	}
	
	/**
	 *  There must not be silent clock extensions! 
	 */
	public IExtension createSilentExtension(Transition owner) {
		return null;
	}
		
	/**
	 *  There must not be silent clock extensions! 
	 */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}

	/**
	 * Validate a clock extension (semantics checking). A list of clocks must not contain duplicates, keywords may not be
	 * used as clock names, and clock names must not equal port names or state names.
	 * @see org.ect.ea.IExtensionProvider#validateExtension(org.ect.ea.extensions.IExtension) 
	 */
	public IValidationResult validateExtension(IExtension extension) {
		
		StringExtension ext = (StringExtension) extension;
		
		StringListExtension extensionSLE;
		try {
			extensionSLE = StringListExtension.parse(ext.toString());
		} catch (ParseException e) {
			return ValidationResult.newErrorResult(e.getMessage());
		}
		
		EList<String> extensionList = extensionSLE.getValues();
		EList<String> checkList = new BasicEList<String>();
		boolean contains;
	
		// Check for duplicate clock names.
		checkList = extensionSLE.getDuplicateEntries();
		if (!(checkList.isEmpty())) {
			String list = new StringListExtension(checkList).toString();
			String message = "Duplicate clock names lead to strange, unexpected behaviour! Duplicate names: " + list;
			return ValidationResult.newErrorResult(message);
		}
		
		//Check for keywords
		checkList = extensionSLE.getValues();
		String name = "";
		if (checkList.contains ("true")) {
			name = "true";
		}
		if (checkList.contains("false")) {
			name = "false";
		}
		if (name != "") {
			String message = name + " is not a valid clock name";
			return ValidationResult.newErrorResult(message);
		}
		
		//Check that clock names are not used in any other extension.
		//Check state names
		Automaton automaton = (Automaton) extension.getOwner();
		contains = false;
		for (State st: automaton.getStates()) {
			String s = st.getName();			
			if (s != null) {
				contains = extensionList.contains(s);
			}
		}
		if (contains) {
			return ValidationResult.newErrorResult("clock names must not equal state names");
		}
		
		//Check port names
		contains = false;
		IExtension iExtension = automaton.findExtension("org.ect.ea.extensions.automatonPortNames");
		if (iExtension != null) {
			
			try {
				checkList = (StringListExtension.parse(iExtension.toString())).getValues();
			} catch (ParseException e) {
				return ValidationResult.newErrorResult(e.getMessage());
			} //gets all port names in an EList
			
			for (String s: checkList) {
				if (extensionList.contains(s)) {
					contains = true;
				}
			}
		}
		if (contains) {
			return ValidationResult.newErrorResult("clock names must not equal port names");
		}
		
		// Validation successful.
		return ValidationResult.newOKResult();
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