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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.extensions.ExtendibleElement;

/**
 * @see org.ect.ea.automata.AutomataPackage#getState()
 * @model kind="class"
 * @generated
 */
public class State extends ExtendibleElement {

	/**
	 * @generated NOT
	 */
	public State() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public State(String name) {
		super();
		setName(name);
	}

	/**
	 * @see org.ect.ea.automata.AutomataPackage#getState_Id()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated NOT
	 */
	public String getId() {
		if (getAutomaton()==null) return "s?";
		else return "s" + getAutomaton().getStates().indexOf(this);
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #getIncoming()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> incoming;

	/**
	 * @see #getOutgoing()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> outgoing;

	/**
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

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
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AutomataPackage.Literals.STATE;
	}

	/**
	 * @return the value of the '<em>Automaton</em>' container reference.
	 * @see #setAutomaton(Automaton)
	 * @see org.ect.ea.automata.AutomataPackage#getState_Automaton()
	 * @see org.ect.ea.automata.Automaton#getStates
	 * @model opposite="states"
	 * @generated
	 */
	public Automaton getAutomaton() {
		if (eContainerFeatureID() != AutomataPackage.STATE__AUTOMATON) return null;
		return (Automaton)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetAutomaton(Automaton newAutomaton, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAutomaton, AutomataPackage.STATE__AUTOMATON, msgs);
		return msgs;
	}

	/**
	 * @param value the new value of the '<em>Automaton</em>' container reference.
	 * @see #getAutomaton()
	 * @generated
	 */
	public void setAutomaton(Automaton newAutomaton) {
		if (newAutomaton != eInternalContainer() || (eContainerFeatureID() != AutomataPackage.STATE__AUTOMATON && newAutomaton != null)) {
			if (EcoreUtil.isAncestor(this, newAutomaton))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAutomaton != null)
				msgs = ((InternalEObject)newAutomaton).eInverseAdd(this, AutomataPackage.AUTOMATON__STATES, Automaton.class, msgs);
			msgs = basicSetAutomaton(newAutomaton, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.STATE__AUTOMATON, newAutomaton, newAutomaton));
	}

	/**
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see org.ect.ea.automata.AutomataPackage#getState_Incoming()
	 * @see org.ect.ea.automata.Transition#getTarget
	 * @model type="org.ect.ea.automata.Transition" opposite="target"
	 * @generated
	 */
	public EList<Transition> getIncoming() {
		if (incoming == null) {
			incoming = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, AutomataPackage.STATE__INCOMING, AutomataPackage.TRANSITION__TARGET);
		}
		return incoming;
	}

	/**
	 * @return the value of the '<em>Outgoing</em>' reference list.
	 * @see org.ect.ea.automata.AutomataPackage#getState_Outgoing()
	 * @see org.ect.ea.automata.Transition#getSource
	 * @model type="org.ect.ea.automata.Transition" opposite="source"
	 * @generated
	 */
	public EList<Transition> getOutgoing() {
		if (outgoing == null) {
			outgoing = new EObjectWithInverseResolvingEList<Transition>(Transition.class, this, AutomataPackage.STATE__OUTGOING, AutomataPackage.TRANSITION__SOURCE);
		}
		return outgoing;
	}


	/**
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.ect.ea.automata.AutomataPackage#getState_Name()
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
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.STATE__NAME, oldName, name));
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.STATE__AUTOMATON:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAutomaton((Automaton)otherEnd, msgs);
			case AutomataPackage.STATE__INCOMING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncoming()).basicAdd(otherEnd, msgs);
			case AutomataPackage.STATE__OUTGOING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.STATE__AUTOMATON:
				return basicSetAutomaton(null, msgs);
			case AutomataPackage.STATE__INCOMING:
				return ((InternalEList<?>)getIncoming()).basicRemove(otherEnd, msgs);
			case AutomataPackage.STATE__OUTGOING:
				return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case AutomataPackage.STATE__AUTOMATON:
				return eInternalContainer().eInverseRemove(this, AutomataPackage.AUTOMATON__STATES, Automaton.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AutomataPackage.STATE__AUTOMATON:
				return getAutomaton();
			case AutomataPackage.STATE__INCOMING:
				return getIncoming();
			case AutomataPackage.STATE__OUTGOING:
				return getOutgoing();
			case AutomataPackage.STATE__ID:
				return getId();
			case AutomataPackage.STATE__NAME:
				return getName();
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
			case AutomataPackage.STATE__AUTOMATON:
				setAutomaton((Automaton)newValue);
				return;
			case AutomataPackage.STATE__INCOMING:
				getIncoming().clear();
				getIncoming().addAll((Collection<? extends Transition>)newValue);
				return;
			case AutomataPackage.STATE__OUTGOING:
				getOutgoing().clear();
				getOutgoing().addAll((Collection<? extends Transition>)newValue);
				return;
			case AutomataPackage.STATE__NAME:
				setName((String)newValue);
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
			case AutomataPackage.STATE__AUTOMATON:
				setAutomaton((Automaton)null);
				return;
			case AutomataPackage.STATE__INCOMING:
				getIncoming().clear();
				return;
			case AutomataPackage.STATE__OUTGOING:
				getOutgoing().clear();
				return;
			case AutomataPackage.STATE__NAME:
				setName(NAME_EDEFAULT);
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
			case AutomataPackage.STATE__AUTOMATON:
				return getAutomaton() != null;
			case AutomataPackage.STATE__INCOMING:
				return incoming != null && !incoming.isEmpty();
			case AutomataPackage.STATE__OUTGOING:
				return outgoing != null && !outgoing.isEmpty();
			case AutomataPackage.STATE__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case AutomataPackage.STATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(')');
		return result.toString();
	}

} // State