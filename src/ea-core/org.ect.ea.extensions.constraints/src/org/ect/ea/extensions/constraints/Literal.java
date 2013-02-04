/**
 *  * Constraint Extension, Copyright (C) 2007 SEN3 group at CWI, Amsterdam.
 *  * 
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version
 *  * 2 of the License, or (at your option) any later version.
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 *  * 02110-1301, USA.
 * 
 *
 * $Id$
 */
package org.ect.ea.extensions.constraints;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/**
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getLiteral()
 * @model kind="class"
 * @generated
 */
public class Literal extends ExtensionElement implements Constraint, Parameter {

	/**
	 * @generated NOT
	 */
	public Literal() {
		super();
		setId(ConstraintExtensionProvider.EXTENSION_ID);
	}

	/**
	 * @generated NOT
	 */
	public Literal(boolean value) {
		this();
		setValue(value);
	}

	
	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public String getValue() {
		return String.valueOf(value);
	}

	
	/**
	 * @model
	 * @generated NOT
	 */
	public void setValue(String value) {
		setValue(Boolean.getBoolean(value));
	}
	
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	public Equation getEquation() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated NOT
	 */
	public boolean booleanValue() {
		return value;
	}

	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return String.valueOf(getValue());
	}

	
	/**
	 * @generated NOT
	 */
	@Override
	public IExtendible getOwner() {
		if (eContainer() instanceof IExtendible) return (IExtendible) eContainer();
		if (eContainer() instanceof IExtension) return ((IExtension) eContainer()).getOwner();
		return null;
	}

	
	/**
	 * @generated NOT
	 */
	public IValidationResult validate() {
		return ValidationResult.newOKResult();
	}
	
	
	/**
	 * @generated NOT
	 */
	public EList<Element> getAllElements() {
		return new BasicEList<Element>();
	}

	
	/**
	 * @generated NOT
	 */
	public Disjunction toDNF() {
		Disjunction disjunction = new Disjunction();
		Conjunction conjunction = new Conjunction();
		conjunction.getMembers().add(new Literal(value));
		disjunction.getMembers().add(conjunction);
		return disjunction;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @generated NOT
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Literal)) return false;	
		return getValue()==((Literal) obj).getValue();
	}
	
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALUE_EDEFAULT = false;

	/**
	 * @generated
	 * @ordered
	 */
	protected boolean value = VALUE_EDEFAULT;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.LITERAL;
	}
	
	/**
	 * @see #setValue(boolean)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getLiteral_Value()
	 * @model
	 * @generated NOT
	 */
	private boolean isValue() {
		return value;
	}
	
	/**
	 * @generated
	 */
	public void setValue(boolean newValue) {
		boolean oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.LITERAL__VALUE, oldValue, value));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.LITERAL__VALUE:
				return isValue() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintsPackage.LITERAL__VALUE:
				setValue(((Boolean)newValue).booleanValue());
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
			case ConstraintsPackage.LITERAL__VALUE:
				setValue(VALUE_EDEFAULT);
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
			case ConstraintsPackage.LITERAL__VALUE:
				return value != VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} // Literal
