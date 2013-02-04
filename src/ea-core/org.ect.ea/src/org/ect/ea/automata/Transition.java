/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.automata;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.ExtendibleElement;



/**
 * @see org.ect.ea.automata.AutomataPackage#getTransition()
 * @model kind="class"
 * @generated
 */
public class Transition extends ExtendibleElement {

	/**
	 * @generated NOT
	 */
	public Transition() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public Transition(State source, State target) {
		super();
		setSource(source);
		setTarget(target);
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected State source;

	/**
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected State target;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AutomataPackage.Literals.TRANSITION;
	}

	/**
	 * @return the value of the '<em>Automaton</em>' container reference.
	 * @see #setAutomaton(Automaton)
	 * @see org.ect.ea.automata.AutomataPackage#getTransition_Automaton()
	 * @see org.ect.ea.automata.Automaton#getTransitions
	 * @model opposite="transitions"
	 * @generated
	 */
	public Automaton getAutomaton() {
		if (eContainerFeatureID() != AutomataPackage.TRANSITION__AUTOMATON) return null;
		return (Automaton)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetAutomaton(Automaton newAutomaton, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAutomaton, AutomataPackage.TRANSITION__AUTOMATON, msgs);
		return msgs;
	}

	/**
	 * @param value the new value of the '<em>Automaton</em>' container reference.
	 * @see #getAutomaton()
	 * @generated
	 */
	public void setAutomaton(Automaton newAutomaton) {
		if (newAutomaton != eInternalContainer() || (eContainerFeatureID() != AutomataPackage.TRANSITION__AUTOMATON && newAutomaton != null)) {
			if (EcoreUtil.isAncestor(this, newAutomaton))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAutomaton != null)
				msgs = ((InternalEObject)newAutomaton).eInverseAdd(this, AutomataPackage.AUTOMATON__TRANSITIONS, Automaton.class, msgs);
			msgs = basicSetAutomaton(newAutomaton, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TRANSITION__AUTOMATON, newAutomaton, newAutomaton));
	}

	/**
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(State)
	 * @see org.ect.ea.automata.AutomataPackage#getTransition_Source()
	 * @see org.ect.ea.automata.State#getOutgoing
	 * @model opposite="outgoing"
	 * @generated
	 */
	public State getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (State)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AutomataPackage.TRANSITION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * @generated
	 */
	public State basicGetSource() {
		return source;
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetSource(State newSource, NotificationChain msgs) {
		State oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AutomataPackage.TRANSITION__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	public void setSource(State newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, AutomataPackage.STATE__OUTGOING, State.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, AutomataPackage.STATE__OUTGOING, State.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TRANSITION__SOURCE, newSource, newSource));
	}

	/**
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(State)
	 * @see org.ect.ea.automata.AutomataPackage#getTransition_Target()
	 * @see org.ect.ea.automata.State#getIncoming
	 * @model opposite="incoming"
	 * @generated
	 */
	public State getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (State)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AutomataPackage.TRANSITION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * @generated
	 */
	public State basicGetTarget() {
		return target;
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetTarget(State newTarget, NotificationChain msgs) {
		State oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AutomataPackage.TRANSITION__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	public void setTarget(State newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, AutomataPackage.STATE__INCOMING, State.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, AutomataPackage.STATE__INCOMING, State.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TRANSITION__TARGET, newTarget, newTarget));
	}

	/**
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.ect.ea.automata.AutomataPackage#getTransition_Id()
	 * @model
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AutomataPackage.TRANSITION__ID, oldId, id));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.TRANSITION__AUTOMATON:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAutomaton((Automaton)otherEnd, msgs);
			case AutomataPackage.TRANSITION__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, AutomataPackage.STATE__OUTGOING, State.class, msgs);
				return basicSetSource((State)otherEnd, msgs);
			case AutomataPackage.TRANSITION__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, AutomataPackage.STATE__INCOMING, State.class, msgs);
				return basicSetTarget((State)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AutomataPackage.TRANSITION__AUTOMATON:
				return basicSetAutomaton(null, msgs);
			case AutomataPackage.TRANSITION__SOURCE:
				return basicSetSource(null, msgs);
			case AutomataPackage.TRANSITION__TARGET:
				return basicSetTarget(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case AutomataPackage.TRANSITION__AUTOMATON:
				return eInternalContainer().eInverseRemove(this, AutomataPackage.AUTOMATON__TRANSITIONS, Automaton.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AutomataPackage.TRANSITION__AUTOMATON:
				return getAutomaton();
			case AutomataPackage.TRANSITION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case AutomataPackage.TRANSITION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case AutomataPackage.TRANSITION__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AutomataPackage.TRANSITION__AUTOMATON:
				setAutomaton((Automaton)newValue);
				return;
			case AutomataPackage.TRANSITION__SOURCE:
				setSource((State)newValue);
				return;
			case AutomataPackage.TRANSITION__TARGET:
				setTarget((State)newValue);
				return;
			case AutomataPackage.TRANSITION__ID:
				setId((String)newValue);
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
			case AutomataPackage.TRANSITION__AUTOMATON:
				setAutomaton((Automaton)null);
				return;
			case AutomataPackage.TRANSITION__SOURCE:
				setSource((State)null);
				return;
			case AutomataPackage.TRANSITION__TARGET:
				setTarget((State)null);
				return;
			case AutomataPackage.TRANSITION__ID:
				setId(ID_EDEFAULT);
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
			case AutomataPackage.TRANSITION__AUTOMATON:
				return getAutomaton() != null;
			case AutomataPackage.TRANSITION__SOURCE:
				return source != null;
			case AutomataPackage.TRANSITION__TARGET:
				return target != null;
			case AutomataPackage.TRANSITION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

}
