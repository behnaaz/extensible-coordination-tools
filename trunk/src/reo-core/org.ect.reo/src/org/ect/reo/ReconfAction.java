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
package org.ect.reo;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * @see org.ect.reo.ReoPackage#getReconfAction()
 * @model kind="class"
 * @generated
 */
public class ReconfAction extends MinimalEObjectImpl implements EObject {
	
	/**
	 * @generated NOT
	 */
	public ReconfAction(ReconfRule rule, ReconfType type) {
		super();
		setRule(rule);
		setType(type);
	}

	
	/**
	 * The cached value of the '{@link #getRule() <em>Rule</em>}' reference.
	 * @see #getRule()
	 * @generated
	 * @ordered
	 */
	protected ReconfRule rule;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ReconfType TYPE_EDEFAULT = ReconfType.NONE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ReconfType type = TYPE_EDEFAULT;

	/**
	 * @generated
	 */
	public ReconfAction() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.RECONF_ACTION;
	}

	/**
	 * Returns the value of the '<em><b>Rule</b></em>' reference.
	 * @return the value of the '<em>Rule</em>' reference.
	 * @see #setRule(ReconfRule)
	 * @see org.ect.reo.ReoPackage#getReconfAction_Rule()
	 * @model
	 * @generated
	 */
	public ReconfRule getRule() {
		if (rule != null && rule.eIsProxy()) {
			InternalEObject oldRule = (InternalEObject)rule;
			rule = (ReconfRule)eResolveProxy(oldRule);
			if (rule != oldRule) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReoPackage.RECONF_ACTION__RULE, oldRule, rule));
			}
		}
		return rule;
	}

	/**
	 * @generated
	 */
	public ReconfRule basicGetRule() {
		return rule;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.ReconfAction#getRule <em>Rule</em>}' reference.
	 * @param value the new value of the '<em>Rule</em>' reference.
	 * @see #getRule()
	 * @generated
	 */
	public void setRule(ReconfRule newRule) {
		ReconfRule oldRule = rule;
		rule = newRule;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.RECONF_ACTION__RULE, oldRule, rule));
	}

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.ect.reo.ReconfType}.
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.ect.reo.ReconfType
	 * @see #setType(ReconfType)
	 * @see org.ect.reo.ReoPackage#getReconfAction_Type()
	 * @model
	 * @generated
	 */
	public ReconfType getType() {
		return type;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.ReconfAction#getType <em>Type</em>}' attribute.
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.ect.reo.ReconfType
	 * @see #getType()
	 * @generated
	 */
	public void setType(ReconfType newType) {
		ReconfType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.RECONF_ACTION__TYPE, oldType, type));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.RECONF_ACTION__RULE:
				if (resolve) return getRule();
				return basicGetRule();
			case ReoPackage.RECONF_ACTION__TYPE:
				return getType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReoPackage.RECONF_ACTION__RULE:
				setRule((ReconfRule)newValue);
				return;
			case ReoPackage.RECONF_ACTION__TYPE:
				setType((ReconfType)newValue);
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
			case ReoPackage.RECONF_ACTION__RULE:
				setRule((ReconfRule)null);
				return;
			case ReoPackage.RECONF_ACTION__TYPE:
				setType(TYPE_EDEFAULT);
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
			case ReoPackage.RECONF_ACTION__RULE:
				return rule != null;
			case ReoPackage.RECONF_ACTION__TYPE:
				return type != TYPE_EDEFAULT;
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

}
