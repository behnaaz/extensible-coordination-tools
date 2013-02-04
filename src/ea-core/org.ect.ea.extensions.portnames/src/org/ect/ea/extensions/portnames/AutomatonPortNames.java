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
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.providers.AutomatonPortNamesProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/**
 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getPortNames()
 * @model kind="class"
 * @generated
 */
public class AutomatonPortNames extends StringListExtension {
	
	/**
	 * @generated NOT
	 */
	public AutomatonPortNames() {
		super();
		setId(AutomatonPortNamesProvider.EXTENSION_ID);
	}

	/**
	 * Creates a new port names extension that contains all names 
	 * of the parameter. Duplicate entries will be added only once. 
	 * This also sets the inports and outports.
	 * @generated NOT
	 */
	public static AutomatonPortNames join(AutomatonPortNames n1, AutomatonPortNames n2) {
		
		AutomatonPortNames result = new AutomatonPortNames();
		result.append(n1, true);
		result.append(n2, true);
		
		for (String name : result.getValues()) {
			if (n1.isInPort(name) && n2.isInPort(name)) result.getInPorts().add(name);
			if (n1.isOutPort(name) && n2.isOutPort(name)) result.getOutPorts().add(name);
		}
		
		return result;
		
	}

	
	/**
	 * Parse a comma-separated list of port names.
	 * @generated NOT
	 */
	public static AutomatonPortNames parse(String value) throws ParseException {
		
		StringListExtension list = StringListExtension.parse(value);
		AutomatonPortNames portNames = new AutomatonPortNames();
		portNames.getValues().addAll(list.getValues());
		
		return portNames;
	}
	
	
	/**
	 * Validate this port names extension. This checks for duplicate port names.
	 */
	public IValidationResult validate() {
		
		EList<String> duplicates = getDuplicateEntries();
		
		// Check for duplicate port names.
		if (!duplicates.isEmpty()) {
			String list = new StringListExtension(duplicates).toString();
			String message = "Duplicate port names: " + list;
			return ValidationResult.newErrorResult(message);
		}
		
		// Validation successful.
		return ValidationResult.newOKResult();
	}
	
	
	/**
	 * @genereated NOT
	 */
	public void trimAll() {
		super.trimAll();
		for (int i=0; i<getInPorts().size(); i++) {
			String value = getInPorts().get(i);
			getInPorts().set(i, value.trim());
		}
		for (int i=0; i<getOutPorts().size(); i++) {
			String value = getOutPorts().get(i);
			getOutPorts().set(i, value.trim());
		}
	}
	
	
	/**
	 * @generated NOT
	 */
	public boolean isInPort(String name) {
		for (String inPort : getInPorts()) {
			if (name.equals(inPort)) return true;
		}
		return false;
	}

	
	/**
	 * @generated NOT
	 */
	public boolean isOutPort(String name) {
		for (String outPort : getOutPorts()) {
			if (name.equals(outPort)) return true;
		}
		return false;
	}

	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	
	public boolean setInPort(String name) {
		getOutPorts().remove(name);
		return getInPorts().add(name);
	}
	
	public boolean setOutPort(String name) {
		getInPorts().remove(name);
		return getOutPorts().add(name);
	}

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	
	/**
	 * @see #getInPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<String> inPorts;

	/**
	 * @see #getOutPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<String> outPorts;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortNamesPackage.Literals.AUTOMATON_PORT_NAMES;
	}

	/**
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getPortNames_InPorts()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getInPorts() {
		if (inPorts == null) {
			inPorts = new EDataTypeUniqueEList<String>(String.class, this, PortNamesPackage.AUTOMATON_PORT_NAMES__IN_PORTS);
		}
		return inPorts;
	}

	/**
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getPortNames_OutPorts()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getOutPorts() {
		if (outPorts == null) {
			outPorts = new EDataTypeUniqueEList<String>(String.class, this, PortNamesPackage.AUTOMATON_PORT_NAMES__OUT_PORTS);
		}
		return outPorts;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PortNamesPackage.AUTOMATON_PORT_NAMES__IN_PORTS:
				return getInPorts();
			case PortNamesPackage.AUTOMATON_PORT_NAMES__OUT_PORTS:
				return getOutPorts();
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
			case PortNamesPackage.AUTOMATON_PORT_NAMES__IN_PORTS:
				getInPorts().clear();
				getInPorts().addAll((Collection<? extends String>)newValue);
				return;
			case PortNamesPackage.AUTOMATON_PORT_NAMES__OUT_PORTS:
				getOutPorts().clear();
				getOutPorts().addAll((Collection<? extends String>)newValue);
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
			case PortNamesPackage.AUTOMATON_PORT_NAMES__IN_PORTS:
				getInPorts().clear();
				return;
			case PortNamesPackage.AUTOMATON_PORT_NAMES__OUT_PORTS:
				getOutPorts().clear();
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
			case PortNamesPackage.AUTOMATON_PORT_NAMES__IN_PORTS:
				return inPorts != null && !inPorts.isEmpty();
			case PortNamesPackage.AUTOMATON_PORT_NAMES__OUT_PORTS:
				return outPorts != null && !outPorts.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
//	public static void main(String[] args) {
//		AutomatonPortNames apn = AutomatonPortNames.parse("A,B,C");
//		apn.setInPort("A");
//		apn.setOutPort("B");
//		apn.setOutPort("A");
//		System.out.println("In:"+apn.getInPorts());
//		System.out.println("Out:"+apn.getOutPorts());
//		System.out.println("All:"+apn.getValues());
//	}
} // PortNames
