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
package org.ect.ea.extensions.statememory;

import java.text.ParseException;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.extensions.StringListExtension;

/**
 * @see org.ect.ea.extensions.statememory.StateMemoryPackage#getStateMemory()
 * @model kind="class"
 * @generated
 */
public class StateMemory extends StringListExtension {
	
	/**
	 * @generated NOT
	 */
	public StateMemory() {
		super();
		setId(StateMemoryExtensionProvider.EXTENSION_ID);
	}
	
	/**
	 * Parse a state memory definition.
	 * @generated NOT
	 */
	public static StateMemory parse(String value) throws ParseException {
		
		StringListExtension list = StringListExtension.parse(value);
		list.trimAll();
		StateMemory memory = new StateMemory();
		
		for (String def : list.getValues()) {
			
			int occ = occurences(def,'=');
			
			switch (occ) {
			
				case 0: 	memory.getValues().add(def); 
							break;
			
				case 1: 	String name = def.substring(0,occ).trim();
							String init = def.substring(occ+1).trim();
							memory.getValues().add(name);
							memory.getInitializations().put(name, init);
							break;
							
				default:	throw new ParseException("Multiple uses of '='.", 0);
			
			}
			
		}	
		return memory;
	}
	
	
	/**
	 * @generated NOT
	 */
	private static int occurences(String string, char c) {
		int occ = 0;
		for (int i=0; i<string.length(); i++) {
			if (string.charAt(i)==c) occ++;
		}
		return occ;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i=0; i<getValues().size(); i++) {
			String name = getValues().get(i);
			result.append(name);
			if (getInitializations().containsKey(name)) {
				result.append("=" + getInitializations().get(name));
			}
			if (i<getValues().size()-1) {
				result.append(SEPARATOR);
			}
		}
		return result.toString();
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @see #getInitializations()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> initializations;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StateMemoryPackage.Literals.STATE_MEMORY;
	}

	/**
	 * @see org.ect.ea.extensions.statememory.StateMemoryPackage#getStateMemory_Initializations()
	 * @model mapType="org.ect.ea.extensions.stateMemory.StringToEObjectEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject>"
	 * @generated
	 */
	public EMap<String, String> getInitializations() {
		if (initializations == null) {
			initializations = new EcoreEMap<String,String>(StateMemoryPackage.Literals.STRING_MAP_ENTRY, StringMapEntry.class, this, StateMemoryPackage.STATE_MEMORY__INITIALIZATIONS);
		}
		return initializations;
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StateMemoryPackage.STATE_MEMORY__INITIALIZATIONS:
				return ((InternalEList<?>)getInitializations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StateMemoryPackage.STATE_MEMORY__INITIALIZATIONS:
				if (coreType) return getInitializations();
				else return getInitializations().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StateMemoryPackage.STATE_MEMORY__INITIALIZATIONS:
				((EStructuralFeature.Setting)getInitializations()).set(newValue);
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
			case StateMemoryPackage.STATE_MEMORY__INITIALIZATIONS:
				getInitializations().clear();
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
			case StateMemoryPackage.STATE_MEMORY__INITIALIZATIONS:
				return initializations != null && !initializations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // StateMemory
