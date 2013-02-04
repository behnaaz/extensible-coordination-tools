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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.providers.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.providers.IntensionalPortNamesProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intensional Port Names</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.portNames.IntensionalPortNames#getRequests <em>Requests</em>}</li>
 *   <li>{@link org.ect.ea.extensions.portNames.IntensionalPortNames#getFirings <em>Firings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getIntensionalPortNames()
 * @model kind="class"
 * @generated
 */
public class IntentionalPortNames extends ExtensionElement {
	
	/**
	 * @generated NOT
	 */
	public IntentionalPortNames() {
		super();
		setId(IntensionalPortNamesProvider.EXTENSION_ID);
	}
	
	/**
	 * The cached value of the '{@link #getRequests() <em>Requests</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequests()
	 * @generated
	 * @ordered
	 */
	protected EList<String> requests;

	/**
	 * The cached value of the '{@link #getFirings() <em>Firings</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirings()
	 * @generated
	 * @ordered
	 */
	protected EList<String> firings;

	/**
	 * Non-real port names but used to collect mixed nodes on one transition.
	 */
	protected boolean Mcollections = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortNamesPackage.Literals.INTENSIONAL_PORT_NAMES;
	}

	public boolean getMcollections(){
		return Mcollections;
	}
	
	public void setMcollections(boolean value){
		Mcollections = value;
	}
	
	/**
	 * Returns the value of the '<em><b>Requests</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requests</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requests</em>' attribute list.
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getIntensionalPortNames_Requests()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getRequests() {
		if (requests == null) {
			requests = new EDataTypeEList<String>(String.class, this, PortNamesPackage.INTENSIONAL_PORT_NAMES__REQUESTS);
		}
		return requests;
	}

	/**
	 * Returns the value of the '<em><b>Firings</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Firings</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Firings</em>' attribute list.
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getIntensionalPortNames_Firings()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getFirings() {
		if (firings == null) {
			firings = new EDataTypeEList<String>(String.class, this, PortNamesPackage.INTENSIONAL_PORT_NAMES__FIRINGS);
		}
		return firings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__REQUESTS:
				return getRequests();
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__FIRINGS:
				return getFirings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__REQUESTS:
				getRequests().clear();
				getRequests().addAll((Collection<? extends String>)newValue);
				return;
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__FIRINGS:
				getFirings().clear();
				getFirings().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__REQUESTS:
				getRequests().clear();
				return;
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__FIRINGS:
				getFirings().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__REQUESTS:
				return requests != null && !requests.isEmpty();
			case PortNamesPackage.INTENSIONAL_PORT_NAMES__FIRINGS:
				return firings != null && !firings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (requests: ");
		result.append(requests);
		result.append(", firings: ");
		result.append(firings);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * Validate these port names extension. This method checks
	 * for duplicate and unknown port names.
	 */
	public IValidationResult validate() {
		
		StringListExtension RequestTemp = new StringListExtension(this.getRequests());
		StringListExtension FiringTemp = new StringListExtension(this.getFirings());
		
		EList<String> RequestDuplicates = RequestTemp.getDuplicateEntries();
		EList<String> FiringDuplicates = FiringTemp.getDuplicateEntries();
		
 		// Check duplicate port names
		if(!RequestDuplicates.isEmpty() || !FiringDuplicates.isEmpty()){
			String message = "Duplicate port names: ";
			boolean rDuplicates = false;
			if(!RequestDuplicates.isEmpty()){
				rDuplicates = true;
				String list1 = new StringListExtension(RequestDuplicates).toString();
				message = message + list1;
			}
			if(!FiringDuplicates.isEmpty()){
				String list2 = new StringListExtension(FiringDuplicates).toString();
				if(rDuplicates)	message = message + "," + list2;
				else	message = message + list2;
			}
			return ValidationResult.newErrorResult(message);
		}
		
		// Check unknown port names.
		Automaton automaton = ((Transition) getOwner()).getAutomaton();
		AutomatonPortNames portNames = (AutomatonPortNames) automaton.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		
		if (portNames==null) {
			String message = "Missing port names extension for automaton '" + automaton.getName() + "'";
			return ValidationResult.newErrorResult(message);
		}
			
		List<String> RequestUnknown = new Vector<String>();
		for (String name : RequestTemp.getValues()) {
			if (!portNames.getValues().contains(name)) RequestUnknown.add(name);
		}
		
		List<String> FiringUnknown = new Vector<String>();
		for (String name : FiringTemp.getValues()) {
			if (!portNames.getValues().contains(name)) FiringUnknown.add(name);
		}
		
		if(!RequestUnknown.isEmpty() || !FiringUnknown.isEmpty()){
			String message = "Unknown port names: ";
			boolean rUnknown = false;
			if(!RequestUnknown.isEmpty()){
				rUnknown = true;
				String list1 = new StringListExtension(RequestUnknown).toString();
				message = message + list1;
			}
			if(!FiringUnknown.isEmpty()){
				String list2 = new StringListExtension(FiringUnknown).toString();
				if(rUnknown)	message = message + "," + list2;
				else message = message + list2;
			}
			return ValidationResult.newErrorResult(message);
		}
			
		// Validation successful.
		return ValidationResult.newOKResult();
	
	}	
	
	public static IntentionalPortNames join(IntentionalPortNames n1, IntentionalPortNames n2) {
		
		IntentionalPortNames result = new IntentionalPortNames();
		// Refine the result i.e. to remove duplicate ports names from firing and request sets
		EObject Iport1 = EcoreUtil.copy(n1);
		EObject Iport2 = EcoreUtil.copy(n2);
			
		HashSet<String> requesttemp1 = new HashSet<String>(((IntentionalPortNames)Iport1).getRequests());
		HashSet<String> requesttemp2 = new HashSet<String>(((IntentionalPortNames)Iport2).getRequests());
		HashSet<String> firingtemp1 = new HashSet<String>(((IntentionalPortNames)Iport1).getFirings());
		HashSet<String> firingtemp2 = new HashSet<String>(((IntentionalPortNames)Iport2).getFirings());
		
		requesttemp1.retainAll(requesttemp2);
		firingtemp1.retainAll(firingtemp2);
			
		result.getRequests().addAll(((IntentionalPortNames)Iport1).getRequests());
		result.getRequests().addAll(((IntentionalPortNames)Iport2).getRequests());
		result.getFirings().addAll(((IntentionalPortNames)Iport1).getFirings());
		result.getFirings().addAll(((IntentionalPortNames)Iport2).getFirings());
			
		if(!requesttemp1.isEmpty()){
			for(String a : requesttemp1){
				result.getRequests().remove(a);
			}
		}
		if(!firingtemp1.isEmpty()){
			for(String b : firingtemp1){
				result.getFirings().remove(b);
			}
		}
		return result;
		
	}
	
} // IntensionalPortNames
