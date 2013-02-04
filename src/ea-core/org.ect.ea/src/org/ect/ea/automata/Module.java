/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.automata;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * @see org.ect.ea.automata.AutomataPackage#getModule()
 * @model kind="class"
 * @generated
 */
public class Module extends MinimalEObjectImpl implements EObject {

	/**
	 * @generated NOT
	 */
	public Module() {
		super();
	}

	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #getAutomata()
	 * @generated
	 * @ordered
	 */
	protected EList<Automaton> automata;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AutomataPackage.Literals.MODULE;
	}

	/**
	 * @return the value of the '<em>Automata</em>' containment reference list.
	 * @see org.ect.ea.automata.AutomataPackage#getModule_Automata()
	 * @see org.ect.ea.automata.Automaton#getModule
	 * @model opposite="module" containment="true"
	 * @generated
	 */
	public EList<Automaton> getAutomata() {
		if (automata == null) {
			automata = new EObjectContainmentWithInverseEList<Automaton>(Automaton.class, this, AutomataPackage.MODULE__AUTOMATA, AutomataPackage.AUTOMATON__MODULE);
		}
		return automata;
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.MODULE__AUTOMATA:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAutomata()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.MODULE__AUTOMATA:
				return ((InternalEList<?>)getAutomata()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AutomataPackage.MODULE__AUTOMATA:
				return getAutomata();
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
			case AutomataPackage.MODULE__AUTOMATA:
				getAutomata().clear();
				getAutomata().addAll((Collection<? extends Automaton>)newValue);
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
			case AutomataPackage.MODULE__AUTOMATA:
				getAutomata().clear();
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
			case AutomataPackage.MODULE__AUTOMATA:
				return automata != null && !automata.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // Module