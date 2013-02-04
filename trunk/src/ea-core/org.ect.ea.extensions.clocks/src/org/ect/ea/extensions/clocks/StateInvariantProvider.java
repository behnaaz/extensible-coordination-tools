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

import org.antlr.runtime.RecognitionException;
//import antlr.TokenStreamException;


public class StateInvariantProvider implements ITextualExtensionProvider {

	/** The ID of the extension. */
	public static final String EXTENSION_ID = "cwi.ea.extensions.clocks.stateInvariants";
	
	public IExtension createDefaultExtension(IExtendible owner) {
		return new StringExtension("true");
	}

	public String editExtension(IExtension extension) {
		StringExtension inv = (StringExtension) extension;
		return inv.toString(); 
	}

	public String printExtension(IExtension extension) {
		return editExtension(extension);
	}

	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		String ret = "";
		if (value.equals("")) {
			ret = "true";
		} else {
			try {
				value = ClockUtils.simplifyCC(value);
				//ClocksLexer lexer = new ClocksLexer(new StringReader(value));
				TCAClocksParser parser = new TCAClocksParser(value);
				ret = parser.clock_constraint();
			} catch (RecognitionException re) {
				//do nothing
			//} catch (TokenStreamException tse) {
			//	//do nothing
			}
		}
		return new StringExtension(ret);
	}

	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		
		BasicEList<IExtension> result = new BasicEList<IExtension>();
		StringExtension product = new StringExtension();
		
		//minor simplification for default extension
		if (x1.toString().equals("true")) {
			product.setValue(x2.toString());
		} else if (x2.toString().equals("true")) {
			product.setValue(x1.toString());
		} else {
			product.setValue("(" + x1.toString() + ") & (" + x2.toString() + ")");
		}
		result.add(product);
		
		return result;
	}

	public IValidationResult validateExtension(IExtension x) {
		
		StringExtension inv = (StringExtension) x;
		boolean result = false;
		TCAClocksParser parser;
		EList<String> aNames = null;
		EList<String> gNames = null;
		
		try {
			/*Check that all clocks used in the invariant are known to the automaton*/		
			//get list of clocks from the automaton
			aNames = (StringListExtension.parse((
					(StringExtension) ((Automaton) ((State) 
							x.getOwner()).getAutomaton()).findExtension("org.ect.ea.extensions.clocks.automatonClocks")).toString())).getValues();

			//get list of clocks from the invariant
			//lexer = new ClocksLexer(new StringReader(inv.getValue()));
			parser = new TCAClocksParser(inv.getValue());
			gNames = parser.clock_names();
		} catch (RecognitionException re) {
			//do nothing
		//} catch (TokenStreamException tse) {
		//	//do nothing
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//compare
		result = aNames.containsAll(gNames);
		if (result == false) {
			gNames.removeAll(aNames);
			return ValidationResult.newErrorResult("clocks used in invariants must be declared on automaton level, declare " + gNames);
		}
		return ValidationResult.newOKResult();
	}

	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkBlue;
	}

	public boolean isReadOnly(IExtension extension) {
		return false;
	}

	/** There must not be silent extensions. */
	public IExtension createSilentExtension(Transition owner) {
		return null;
	}

	/** There must not be silent extensions. */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}

}
