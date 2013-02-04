/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.extensions.portnames;

import java.text.ParseException;
import java.util.List;
import java.util.Vector;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.providers.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.providers.TransitionPortNamesProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;


/**
 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getTransitionPortNames()
 * @model kind="class"
 * @generated
 */
public class TransitionPortNames extends StringListExtension {

	/**
	 * @generated NOT
	 */
	public TransitionPortNames() {
		super();
		setId(TransitionPortNamesProvider.EXTENSION_ID);
	}
	
	
	/**
	 * Creates a new TransitionPortNames extension that contains all 
	 * names of the parameter port names. Duplicate entries will be added only once. 
	 * @generated NOT
	 */
	public static TransitionPortNames join(TransitionPortNames n1, TransitionPortNames n2) {
		
		TransitionPortNames result = new TransitionPortNames();
		result.append(n1, true);
		result.append(n2, true);
		
		return result;
		
	}

	
	/**
	 * Parse a comma-separated list of port names.
	 * @generated NOT
	 */
	public static TransitionPortNames parse(String value) throws ParseException {
		
		StringListExtension list = StringListExtension.parse(value);
		TransitionPortNames portNames = new TransitionPortNames();
		portNames.getValues().addAll(list.getValues());
		
		return portNames;
	}

	
	
	/**
	 * Validate this port names extension. This method checks
	 * for duplicate and unknown port names.
	 */
	public IValidationResult validate() {
		
		EList<String> duplicates = getDuplicateEntries();
		
		// Check for duplicate port names.
		if (!duplicates.isEmpty()) {
			String list = new StringListExtension(duplicates).toString();
			String message = "Duplicate port names: " + list;
			return ValidationResult.newErrorResult(message);
		}

		// Check for unknown port names.
			
		Automaton automaton = ((Transition) getOwner()).getAutomaton();
		AutomatonPortNames portNames = (AutomatonPortNames) automaton.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		
		if (portNames==null) {
			String message = "Missing port names extension for automaton '" + automaton.getName() + "'";
			return ValidationResult.newErrorResult(message);
		}
			
		List<String> unknown = new Vector<String>();
		for (String name : getValues()) {
			if (!portNames.getValues().contains(name)) unknown.add(name);
		}
			
		if (!unknown.isEmpty()) {
			String list = new StringListExtension(unknown).toString();
			String message = "Unknown port names: " + list;
			return ValidationResult.newErrorResult(message);				
		}
			
		// Validation successful.
		return ValidationResult.newOKResult();
	
	}

	
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortNamesPackage.Literals.TRANSITION_PORT_NAMES;
	}

}
