/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.automata;

import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.CompoundValidationResult;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/**
 * @generated NOT
 * @author koehler
 *
 */
public class AutomataValidator {

	
	/**
	 * Validate all extension used in an automaton.
	 * This uses the extension providers to validate
	 * all extensions in an automaton.
	 */
	public static IValidationResult validate(Automaton automaton) {
		
		CompoundValidationResult result = new CompoundValidationResult();

		// Validate automaton extensions.
		for (IExtension extension : automaton.getExtensions()) {
			result.add( validate(extension) );
		}

		// Validate state extensions.
		for (State state : automaton.getStates()) {
			for (IExtension extension : state.getExtensions()) {
				result.add( validate(extension) );
			}			
		}

		// Validate transition extensions.
		for (Transition transition : automaton.getTransitions()) {
			for (IExtension extension : transition.getExtensions()) {
				result.add( validate(extension) );
			}			
		}

		return result;
		
	}
	
	
	/**
	 * Validate a single extension.
	 */
	public static IValidationResult validate(IExtension extension) {
		
		IExtensionRegistry registry = EA.getExtensionRegistry();
		IExtensionDefinition definition = registry.findExtensionDefinition(extension.getId());
		IExtensionProvider provider = definition.getProvider();
		
		IValidationResult result = provider.validateExtension(extension);
		if (result==null) {
			result = ValidationResult.newErrorResult("Extension provider " + definition.getId() + " returned null as validation result.");
		}
		
		return result;
		
	}
		
}
