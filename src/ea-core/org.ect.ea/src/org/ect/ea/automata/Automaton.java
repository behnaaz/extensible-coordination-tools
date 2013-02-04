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
package org.ect.ea.automata;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.extensions.ExtendibleElement;

/**
 * @see org.ect.ea.automata.AutomataPackage#getAutomaton()
 * @model kind="class"
 * @generated
 */
public class Automaton extends ExtendibleElement {

	/**
	 * @generated NOT
	 */
	public Automaton() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public Automaton(String name) {
		super();
		setName(name);
	}

	/**
	 * @see org.ect.ea.automata.AutomataPackage#getAutomaton_Id()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated NOT
	 */
	public String getId() {
		if (getModule()==null) return "a?";
		else return "a" + getModule().getAutomata().indexOf(this);
	}
	
	
	/**
	 * This method adds the parameter extension id to the
	 * {@link #usedExtensionIds} field, if it is not already 
	 * there (duplicate entries are avoided).
	 * @generated NOT
	 */
	public void useExtensionType(String extensionId) {
		if (!getUsedExtensionIds().contains(extensionId)) {
			getUsedExtensionIds().add(extensionId);
		}
	}
	
	
	/**
	 * @generated NOT
	 */
	public boolean isActiveExtension(String id) {
		return getUsedExtensionIds().contains(id);
	}

	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> states;

	/**
	 * @see #getTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> transitions;

	/**
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getUsedExtensionIds() <em>Used Extension Ids</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedExtensionIds()
	 * @generated
	 * @ordered
	 */
	protected EList<String> usedExtensionIds;

	/**
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AutomataPackage.Literals.AUTOMATON;
	}

	/**
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see org.ect.ea.automata.AutomataPackage#getAutomaton_States()
	 * @see org.ect.ea.automata.State#getAutomaton
	 * @model type="org.ect.ea.automata.State" opposite="automaton" containment="true"
	 * @generated
	 */
	public EList<State> getStates() {
		if (states == null) {
			states = new EObjectContainmentWithInverseEList<State>(State.class, this, AutomataPackage.AUTOMATON__STATES, AutomataPackage.STATE__AUTOMATON);
		}
		return states;
	}

	/**
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see org.ect.ea.automata.AutomataPackage#getAutomaton_Transitions()
	 * @see org.ect.ea.automata.Transition#getAutomaton
	 * @model type="org.ect.ea.automata.Transition" opposite="automaton" containment="true"
	 * @generated
	 */
	public EList<Transition> getTransitions() {
		if (transitions == null) {
			transitions = new EObjectContainmentWithInverseEList<Transition>(Transition.class, this, AutomataPackage.AUTOMATON__TRANSITIONS, AutomataPackage.TRANSITION__AUTOMATON);
		}
		return transitions;
	}

	/**
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.ect.ea.automata.AutomataPackage#getAutomaton_Name()
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.AUTOMATON__NAME, oldName, name));
	}

	/**
	 * @return the value of the '<em>All Extension Ids</em>' attribute list.
	 * @see org.ect.ea.automata.AutomataPackage#getAutomaton_AllExtensionIds()
	 * @model
	 * @generated
	 */
	public EList<String> getUsedExtensionIds() {
		if (usedExtensionIds == null) {
			usedExtensionIds = new EDataTypeUniqueEList<String>(String.class, this, AutomataPackage.AUTOMATON__USED_EXTENSION_IDS);
		}
		return usedExtensionIds;
	}

	/**
	 * @return the value of the '<em>Module</em>' container reference.
	 * @see #setModule(Module)
	 * @see org.ect.ea.automata.AutomataPackage#getAutomaton_Module()
	 * @see org.ect.ea.automata.Module#getAutomata
	 * @model opposite="automata"
	 * @generated
	 */
	public Module getModule() {
		if (eContainerFeatureID() != AutomataPackage.AUTOMATON__MODULE) return null;
		return (Module)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetModule(Module newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, AutomataPackage.AUTOMATON__MODULE, msgs);
		return msgs;
	}

	/**
	 * @param value the new value of the '<em>Module</em>' container reference.
	 * @see #getModule()
	 * @generated
	 */
	public void setModule(Module newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != AutomataPackage.AUTOMATON__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, AutomataPackage.MODULE__AUTOMATA, Module.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.AUTOMATON__MODULE, newModule, newModule));
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.AUTOMATON__STATES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStates()).basicAdd(otherEnd, msgs);
			case AutomataPackage.AUTOMATON__TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTransitions()).basicAdd(otherEnd, msgs);
			case AutomataPackage.AUTOMATON__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((Module)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.AUTOMATON__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
			case AutomataPackage.AUTOMATON__TRANSITIONS:
				return ((InternalEList<?>)getTransitions()).basicRemove(otherEnd, msgs);
			case AutomataPackage.AUTOMATON__MODULE:
				return basicSetModule(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case AutomataPackage.AUTOMATON__MODULE:
				return eInternalContainer().eInverseRemove(this, AutomataPackage.MODULE__AUTOMATA, Module.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AutomataPackage.AUTOMATON__STATES:
				return getStates();
			case AutomataPackage.AUTOMATON__TRANSITIONS:
				return getTransitions();
			case AutomataPackage.AUTOMATON__NAME:
				return getName();
			case AutomataPackage.AUTOMATON__USED_EXTENSION_IDS:
				return getUsedExtensionIds();
			case AutomataPackage.AUTOMATON__ID:
				return getId();
			case AutomataPackage.AUTOMATON__MODULE:
				return getModule();
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
			case AutomataPackage.AUTOMATON__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends State>)newValue);
				return;
			case AutomataPackage.AUTOMATON__TRANSITIONS:
				getTransitions().clear();
				getTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case AutomataPackage.AUTOMATON__NAME:
				setName((String)newValue);
				return;
			case AutomataPackage.AUTOMATON__USED_EXTENSION_IDS:
				getUsedExtensionIds().clear();
				getUsedExtensionIds().addAll((Collection<? extends String>)newValue);
				return;
			case AutomataPackage.AUTOMATON__MODULE:
				setModule((Module)newValue);
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
			case AutomataPackage.AUTOMATON__STATES:
				getStates().clear();
				return;
			case AutomataPackage.AUTOMATON__TRANSITIONS:
				getTransitions().clear();
				return;
			case AutomataPackage.AUTOMATON__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AutomataPackage.AUTOMATON__USED_EXTENSION_IDS:
				getUsedExtensionIds().clear();
				return;
			case AutomataPackage.AUTOMATON__MODULE:
				setModule((Module)null);
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
			case AutomataPackage.AUTOMATON__STATES:
				return states != null && !states.isEmpty();
			case AutomataPackage.AUTOMATON__TRANSITIONS:
				return transitions != null && !transitions.isEmpty();
			case AutomataPackage.AUTOMATON__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AutomataPackage.AUTOMATON__USED_EXTENSION_IDS:
				return usedExtensionIds != null && !usedExtensionIds.isEmpty();
			case AutomataPackage.AUTOMATON__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case AutomataPackage.AUTOMATON__MODULE:
				return getModule() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", usedExtensionIds: ");
		result.append(usedExtensionIds);
		result.append(')');
		return result.toString();
	}

}

