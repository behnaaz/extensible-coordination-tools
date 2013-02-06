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
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delay Information</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.portnames.DelayInformation#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayInformation()
 * @model kind="class"
 * @generated
 */
public class DelayInformation extends ExtensionElement {
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public DelayInformation() {
		super();
		setId(DelayInfoProvider.EXTENSION_ID);
	}
	
	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<DelayElement> elements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortNamesPackage.Literals.DELAY_INFORMATION;
	}

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.ea.extensions.portnames.DelayElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayInformation_Elements()
	 * @model containment="true"
	 * @generated
	 */
	public EList<DelayElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentEList<DelayElement>(DelayElement.class, this, PortNamesPackage.DELAY_INFORMATION__ELEMENTS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortNamesPackage.DELAY_INFORMATION__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PortNamesPackage.DELAY_INFORMATION__ELEMENTS:
				return getElements();
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
			case PortNamesPackage.DELAY_INFORMATION__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends DelayElement>)newValue);
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
			case PortNamesPackage.DELAY_INFORMATION__ELEMENTS:
				getElements().clear();
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
			case PortNamesPackage.DELAY_INFORMATION__ELEMENTS:
				return elements != null && !elements.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	/**
	 * To validate the format of rewards and input and output port names.
	 */
	public IValidationResult validate()
	{	
		IntensionalPortNames portNames = (IntensionalPortNames)this.getOwner().findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		if (portNames==null) return ValidationResult.newOKResult();
		
		StringListExtension ports = new StringListExtension(portNames.getRequests());
		ports.append(new StringListExtension(portNames.getFirings()), true);
		EList<String> compare = ports.getValues(); 
		EList<String> missingPorts = ports.getValues(); 
		List<String> compensation = new Vector<String>();
		
		EList<DelayElement> elements = this.getElements(); 
		List<String> unknown = new Vector<String>();
		List<String> duplicates = new Vector<String>();
		
		int i=0;
		while(i<elements.size()){
			DelayElement temp = elements.get(i++);
			EList<String> input = temp.getInput();
			EList<String> output = temp.getOutput();
			List<String> names = new Vector<String>();
			for(String a : input){
				StringTokenizer Itoken = new StringTokenizer(a,",");
				while(Itoken.hasMoreTokens()){
					String Iport = Itoken.nextToken();
					if(names.contains(Iport))	duplicates.add(Iport);
					else names.add(Iport);
				}
			}
			for(String b : output){
				StringTokenizer Otoken = new StringTokenizer(b,",");
				while(Otoken.hasMoreTokens()){
					String Oport = Otoken.nextToken();
					if(names.contains(Oport))	duplicates.add(Oport);
					else names.add(Oport);
				}
				
			}
			
			for(int j=0;j<names.size();j++){
				String Name = names.get(j);
				if(!compare.contains(Name) && !compensation.contains(Name)){
					unknown.add(Name);
				}
				if(missingPorts.indexOf(Name)>=0){
					missingPorts.remove(missingPorts.indexOf(Name));
					compensation.add(Name);
				}
			}
		}
		
		if(!duplicates.isEmpty()){
			String list1 = new StringListExtension(duplicates).toString();
			String message = "Duplicate port names: " + list1;
			return ValidationResult.newErrorResult(message);
		}
		if(!missingPorts.isEmpty()){
			String list2 = new StringListExtension(missingPorts).toString();
			String message = "Missing port names: " + list2;
			return ValidationResult.newErrorResult(message);
		}
		else if(!unknown.isEmpty()) {
			String list3 = new StringListExtension(unknown).toString();
			String message = "Unknown port names: "+ list3;
			return ValidationResult.newErrorResult(message);
		}
		else 	return ValidationResult.newOKResult();
				
	}
	
	public static DelayInformation join(DelayInformation d1,DelayInformation d2)
	{
		DelayInformation result = new DelayInformation();
		
		Collection<DelayElement> temp1 = EcoreUtil.copyAll(d1.getElements());
		Collection<DelayElement> temp2 = EcoreUtil.copyAll(d2.getElements());

		result.getElements().addAll(temp1);
		result.getElements().addAll(temp2);

		List<DelayElement> remove = new Vector<DelayElement>();
		
		if(!temp1.isEmpty() && !temp2.isEmpty()) {
			for(int i=0;i<result.getElements().size();i++){
				DelayElement a = result.getElements().get(i);
				for(int j=0;j<result.getElements().size();j++){
					if(i!=j){
						DelayElement b = result.getElements().get(j);
						if(a.isEqual(b))	remove.add(a);
					}
				}
			}
		}
		List<DelayElement> attach = new Vector<DelayElement>();
		boolean exists = false;
		for(DelayElement c : remove){
			for(DelayElement d: attach){
				if(c.isEqual(d))	exists = true;
			}
			if(!exists)	attach.add(c);
			exists = false;
		}
		
		result.getElements().removeAll(remove);
		result.getElements().addAll(attach);
		return result; 
	}	

} // DelayInformation
