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
package org.ect.ea.extensions.guards;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/**
 * Guard extension for transitions.
 *
 * @see org.ect.ea.extensions.guards.GuardsPackage#getGuard()
 * @model kind="class" abstract="true"
 * @generated
 */
public class Guard extends StringListExtension {
	
	/**
	 * @generated NOT
	 */
	public static final char NEGATION = '!';

	/**
	 * @generated NOT
	 */
	public Guard() {
		super();
		setId(GuardsProvider.EXTENSION_ID);
	}

	/**
	 * Parse a guard from a string representation. This returns either an instance of
	 * {@link Guard} or of {@link BooleanExtension}.
	 * @generated NOT
	 */
	public static IExtension parseGuard(String string) throws ParseException {
		
		// Check if it is 'true' or 'false'.
		if (string.trim().equals(String.valueOf(true))) return new BooleanExtension(true);
		if (string.trim().equals(String.valueOf(false))) return new BooleanExtension(false);
		
		// Otherwise try to parse it as a list.
		StringListExtension list = StringListExtension.parse(string);
		Guard guard = new Guard();
		for (String value : list.getValues()) {
			value = value.trim();
			// Check the format.
			if (value.indexOf(' ')>=0) continue;
			if (value.equals("")) continue;
			if (value.lastIndexOf(NEGATION)>0) continue;
			
			String cleaned = value.replaceAll(String.valueOf(NEGATION), "");
			if (guard.getValues().contains(cleaned)) continue;
			
			// Add it to the list.
			guard.getValues().add(cleaned);
			if (value.indexOf(NEGATION)>=0) {
				guard.getNegated().add(cleaned);
			}
		}
		
		// If the list is empty, we return 'true'.
		if (guard.getValues().isEmpty()) {
			return new BooleanExtension(true);
		} else {
			return guard;
		}
	}
	
	
	/**
	 * Convert the guard back to a string representation.
	 * @generated NOT
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i=0; i<getValues().size(); i++) {
			String value = getValues().get(i);
			if (getNegated().contains(value)) value = NEGATION + value;
			result.append(value);
			if (i<getValues().size()-1) result.append(SEPARATOR);
		}
		return result.toString();
	}
	
	
	/**
	 * Validate this guard. This method checks unknown port names.
	 */
	public IValidationResult validate() {
		
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
			String message = "Unknown port names in guard: " + list;
			return ValidationResult.newErrorResult(message);				
		}
			
		// Validation successful.
		return ValidationResult.newOKResult();
	
	}

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @see #getNegated()
	 * @generated
	 * @ordered
	 */
	protected EList<String> negated;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GuardsPackage.Literals.GUARD;
	}

	/**
	 * Returns the value of the '<em><b>Negated</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * @return the value of the '<em>Negated</em>' attribute list.
	 * @see org.ect.ea.extensions.guards.GuardsPackage#getGuard_Negated()
	 * @model
	 * @generated
	 */
	public EList<String> getNegated() {
		if (negated == null) {
			negated = new EDataTypeUniqueEList<String>(String.class, this, GuardsPackage.GUARD__NEGATED);
		}
		return negated;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GuardsPackage.GUARD__NEGATED:
				return getNegated();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GuardsPackage.GUARD__NEGATED:
				getNegated().clear();
				getNegated().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GuardsPackage.GUARD__NEGATED:
				getNegated().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GuardsPackage.GUARD__NEGATED:
				return negated != null && !negated.isEmpty();
		}
		return super.eIsSet(featureID);
	}

}
