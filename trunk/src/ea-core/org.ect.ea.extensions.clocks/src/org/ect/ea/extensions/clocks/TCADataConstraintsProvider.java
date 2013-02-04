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
import org.ect.ea.extensions.clocks.parsers.TCADataParser;
import org.ect.ea.extensions.portnames.providers.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.providers.TransitionPortNamesProvider;
import org.ect.ea.extensions.statememory.StateMemoryExtensionProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/** Data constraints in TCA allow more than constraints in CA. */

public class TCADataConstraintsProvider implements ITextualExtensionProvider {

	/** The ID of the extension. */
	public static final String EXTENSION_ID = "cwi.ea.extensions.clocks.tcaConstraints";
	
	/* References to other EXTENSION_IDs */
	private static final String STATE_MEMORY_ID = StateMemoryExtensionProvider.EXTENSION_ID;
	private static final String PORT_NAMES_ID = AutomatonPortNamesProvider.EXTENSION_ID;
	private static final String TRANS_PORT_NAMES_ID = TransitionPortNamesProvider.EXTENSION_ID;
	private static final String CLOCKS_ID = AutomatonClocksProvider.EXTENSION_ID;
	
	/** Create a default TCAConstraint extension. */
	public IExtension createDefaultExtension(IExtendible owner) {
		return new StringExtension("true");
	}

	/** Product of two TCAConstraints is their conjunction. */
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

	/** Print the constraint. */
	public String printExtension(IExtension extension) {
		return editExtension(extension);
	}

	/** Edit the constraint. */
	public String editExtension(IExtension extension) {
		StringExtension cons = (StringExtension) extension;
		return cons.toString();
	}

	/** Validate that the data constraint obeys the allowed format.
	 * Check that: 
	 * - All IDs without $s. or $t. prefix are ports known to the automaton     				OK
	 * - All IDs with $s. prefix are memory cells of the source location 						OK
	 * - All IDs with $t. prefix are memory cells of the target location 						OK
	 * - All ports used in the data constraint are contained in the port set.					OK  
	 * - No ID is equal to a clock name, to a port name. Names are checked       				OK
	 * 	 against keywords 'true' and 'false' by the parser already, if other 
	 * 	 keywords need to be checked as well, this has to be added here. 
	 * */
	public IValidationResult validateExtension(IExtension x) {
		
		try {
			TCADataParser parser = new TCADataParser(x.toString());
			EList<String> pNames = parser.get_port_names();   //port names in x
			EList<String> apNames = (StringListExtension.parse((
					(StringListExtension) ((Automaton) ((Transition) 
							x.getOwner()).getAutomaton()).findExtension(PORT_NAMES_ID)).toString())).getValues();  //port names in automaton
			EList<String> sNames = new BasicEList<String>();   //source memory cell names in x
			EList<String> smNames = new BasicEList<String>();  //source location memory cell names
			EList<String> tNames = new BasicEList<String>();   //target memory cell names in x
			EList<String> tmNames = new BasicEList<String>();  //target location memory cell names
			EList<String> temp = new BasicEList<String>(); 	   //for local computations 
			EList<String> pSet = (StringListExtension.parse((
					(StringListExtension) ((Transition) 
							x.getOwner()).findExtension(TRANS_PORT_NAMES_ID)).toString())).getValues(); //port set of the transition
			EList<String> clocks = (StringListExtension.parse((
					(StringExtension) ((Automaton) ((Transition) 
							x.getOwner()).getAutomaton()).findExtension(CLOCKS_ID)).toString())).getValues();  //clocks of the automaton
			EList<String> states = new BasicEList<String>();
			for (State s : ((Automaton) ((Transition) x.getOwner()).getAutomaton()).getStates()) {
				states.add(s.getName());
			}
			
			
			//check that port names are unique
			temp = new BasicEList<String>(clocks); 
			temp.addAll(apNames);
			StringListExtension sle = new StringListExtension(temp);
			temp = sle.getDuplicateEntries();
			if (!temp.isEmpty()) {
				return ValidationResult.newErrorResult("Port names must not equal clock names: " + temp.toString().replace("[", "").replace("]", ""));
			}
			temp = new BasicEList<String>(apNames); 
			temp.addAll(states);
			sle = new StringListExtension(temp);
			temp = sle.getDuplicateEntries();
			if (!temp.isEmpty()) {
				return ValidationResult.newErrorResult("Port names must not equal state names: " + temp.toString().replace("[", "").replace("]", ""));
			}
			
			
			//check that ports in data constraint are all contained in ports of automaton
			if (!apNames.containsAll(pNames)) {
				temp = new BasicEList<String>(pNames);
				temp.removeAll(apNames);
				return ValidationResult.newErrorResult("Ports must be declared on automaton level, declare " + temp.toString().replace("[", "").replace("]", ""));
			}
			
			//check that ports in data constraint are contained in the port set of the transition
			if (!pSet.containsAll(pNames)) {
				temp = new BasicEList<String>(pNames);
				temp.removeAll(pSet);
				return ValidationResult.newErrorResult("Ports used in the data constraint must be contained in the port set, add " + temp.toString().replace("[", "").replace("]", ""));
			}

			//memory cell name constraints must be checked only if the corresponding extension is used
			if (((Automaton) ((Transition) x.getOwner()).getAutomaton()).getUsedExtensionIds().contains(STATE_MEMORY_ID)) {
				//source memory cells in data constraint are contained in source location memory cells
				smNames = (StringListExtension.parse((
						(StringListExtension) ((State) ((Transition) 
								x.getOwner()).getSource()).findExtension(STATE_MEMORY_ID)).toString())).getValues();
				parser = new TCADataParser(x.toString());
				sNames = parser.get_sourceloc_memcell_names();
				if (!smNames.containsAll(sNames)) {
					temp = new BasicEList<String>(sNames);
					temp.removeAll(smNames);
					return ValidationResult.newErrorResult("No memory cell(s) named '" + temp.toString().replace("[", "").replace("]", "") + "' known in source location.");
				}
				
				
				//target memory cells in data constraint are contained in target location memory cells
				tmNames = (StringListExtension.parse((
						(StringListExtension) ((State) ((Transition) 
								x.getOwner()).getTarget()).findExtension(STATE_MEMORY_ID)).toString())).getValues();

				parser = new TCADataParser(x.toString()); 
				tNames = parser.get_targetloc_memcell_names();
				if (!tmNames.containsAll(tNames)) {
					temp = new BasicEList<String>(tNames);
					temp.removeAll(tmNames);
					return ValidationResult.newErrorResult("No memory cell(s) named '" + temp.toString().replace("[", "").replace("]", "") + "' known in target location.");
				}
			}
		
		} catch (ParseException pe) {
			
		} catch (RecognitionException re) {
			
		}
		return ValidationResult.newOKResult();
	}
	
	/** Parse the TCAConstraint. */
	public IExtension parseExtension(String value, IExtendible owner)
			throws ParseException {
		String ret = "";
		if (value.equals("")) {
			ret = "true";
		} else {
			try {
				TCADataParser parser = new TCADataParser(value);
				ret = parser.data_constraint();
				if (ret == "") {
					ret = "true";
				}
			} catch (Exception re) {
			}
		}
		return new StringExtension(ret);
	}

	/** Default font colour. */
	public Color getFontColor(IExtension extension) {
		return ColorConstants.darkBlue;
	}

	/** Whether the constraint is read-only. */
	public boolean isReadOnly(IExtension extension) {
		return false;
	}

	/** There must not be silent extension! */
	public IExtension createSilentExtension(Transition owner) {
		return null;
	}

	/** There must not be silent extensions! */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}


	
}
