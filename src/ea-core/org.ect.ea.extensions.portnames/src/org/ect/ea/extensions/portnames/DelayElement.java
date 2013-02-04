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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Delay Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.portnames.DelayElement#getInput <em>Input</em>}</li>
 *   <li>{@link org.ect.ea.extensions.portnames.DelayElement#getOutput <em>Output</em>}</li>
 *   <li>{@link org.ect.ea.extensions.portnames.DelayElement#getDelay <em>Delay</em>}</li>
 *   <li>{@link org.ect.ea.extensions.portnames.DelayElement#getRewards <em>Rewards</em>}</li>
 *   <li>{@link org.ect.ea.extensions.portnames.DelayElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement()
 * @model kind="class"
 * @generated
 */
public class DelayElement extends MinimalEObjectImpl.Container implements EObject {
	/**
	 * The cached value of the '{@link #getInput() <em>Input</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInput()
	 * @generated
	 * @ordered
	 */
	protected EList<String> input;

	/**
	 * The cached value of the '{@link #getOutput() <em>Output</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutput()
	 * @generated
	 * @ordered
	 */
	protected EList<String> output;

	/**
	 * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected static final double DELAY_EDEFAULT = 0.0;
	
	
	/**
	 * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelay()
	 * @generated
	 * @ordered
	 */
	protected double delay = DELAY_EDEFAULT;

	/**
	 * The reward (or cost) of data-arrivals or data-flows  
	 * @see #getReward()
	 */
	protected EList<String> rewards;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DelayElement() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortNamesPackage.Literals.DELAY_ELEMENT;
	}

	/**
	 * Returns the value of the '<em><b>Input</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input</em>' attribute list.
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement_Input()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getInput() {
		if (input == null) {
			input = new EDataTypeEList<String>(String.class, this, PortNamesPackage.DELAY_ELEMENT__INPUT);
		}
		return input;
	}

	/**
	 * Returns the value of the '<em><b>Output</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output</em>' attribute list.
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement_Output()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getOutput() {
		if (output == null) {
			output = new EDataTypeEList<String>(String.class, this, PortNamesPackage.DELAY_ELEMENT__OUTPUT);
		}
		return output;
	}

	/**
	 * Returns the value of the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delay</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delay</em>' attribute.
	 * @see #setDelay(double)
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement_Delay()
	 * @model
	 * @generated
	 */
	public double getDelay() {
		return delay;
	}
	
	public boolean isEqual(DelayElement a){
		if(this.getInput().equals(a.getInput()) && 
				this.getOutput().equals(a.getOutput()) && 
				this.getDelay()== a.getDelay()){
			for(int i=0;i<this.getRewards().size();i++){
				String b = this.getRewards().get(i);
				String c = a.getRewards().get(i);
				if(!b.equals(c)){
					return false;
				}
			}
			return true;
		}
		else	return false;
		
	}
	
	public boolean isUseful(){
		if(this.getDelay()==0.0)	return false;
		else return true;
	}
	
	/**
	 * Sets the value of the '{@link org.ect.ea.extensions.portnames.DelayElement#getDelay <em>Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delay</em>' attribute.
	 * @see #getDelay()
	 * @generated
	 */
	public void setDelay(double newDelay) {
		double oldDelay = delay;
		delay = newDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortNamesPackage.DELAY_ELEMENT__DELAY, oldDelay, delay));
	}

	/**
	 * Returns the value of the '<em><b>Rewards</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rewards</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rewards</em>' attribute list.
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement_Rewards()
	 * @model unique="false"
	 * @generated
	 */
	public EList<String> getRewards() {
		if (rewards == null) {
			rewards = new EDataTypeEList<String>(String.class, this, PortNamesPackage.DELAY_ELEMENT__REWARDS);
		}
		return rewards;
	}

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement_Name()
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the '{@link org.ect.ea.extensions.portnames.DelayElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortNamesPackage.DELAY_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PortNamesPackage.DELAY_ELEMENT__INPUT:
				return getInput();
			case PortNamesPackage.DELAY_ELEMENT__OUTPUT:
				return getOutput();
			case PortNamesPackage.DELAY_ELEMENT__DELAY:
				return getDelay();
			case PortNamesPackage.DELAY_ELEMENT__REWARDS:
				return getRewards();
			case PortNamesPackage.DELAY_ELEMENT__NAME:
				return getName();
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
			case PortNamesPackage.DELAY_ELEMENT__INPUT:
				getInput().clear();
				getInput().addAll((Collection<? extends String>)newValue);
				return;
			case PortNamesPackage.DELAY_ELEMENT__OUTPUT:
				getOutput().clear();
				getOutput().addAll((Collection<? extends String>)newValue);
				return;
			case PortNamesPackage.DELAY_ELEMENT__DELAY:
				setDelay((Double)newValue);
				return;
			case PortNamesPackage.DELAY_ELEMENT__REWARDS:
				getRewards().clear();
				getRewards().addAll((Collection<? extends String>)newValue);
				return;
			case PortNamesPackage.DELAY_ELEMENT__NAME:
				setName((String)newValue);
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
			case PortNamesPackage.DELAY_ELEMENT__INPUT:
				getInput().clear();
				return;
			case PortNamesPackage.DELAY_ELEMENT__OUTPUT:
				getOutput().clear();
				return;
			case PortNamesPackage.DELAY_ELEMENT__DELAY:
				setDelay(DELAY_EDEFAULT);
				return;
			case PortNamesPackage.DELAY_ELEMENT__REWARDS:
				getRewards().clear();
				return;
			case PortNamesPackage.DELAY_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
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
			case PortNamesPackage.DELAY_ELEMENT__INPUT:
				return input != null && !input.isEmpty();
			case PortNamesPackage.DELAY_ELEMENT__OUTPUT:
				return output != null && !output.isEmpty();
			case PortNamesPackage.DELAY_ELEMENT__DELAY:
				return delay != DELAY_EDEFAULT;
			case PortNamesPackage.DELAY_ELEMENT__REWARDS:
				return rewards != null && !rewards.isEmpty();
			case PortNamesPackage.DELAY_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (input: ");
		result.append(input);
		result.append(", output: ");
		result.append(output);
		result.append(", delay: ");
		result.append(delay);
		result.append(", rewards: ");
		result.append(rewards);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} // DelayElement
