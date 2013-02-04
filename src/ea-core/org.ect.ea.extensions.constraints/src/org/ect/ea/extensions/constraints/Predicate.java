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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.ect.ea.extensions.constraints.providers.ConstraintExtensionProvider;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Predicate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ect.ea.extensions.constraints.Predicate#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getPredicate()
 * @model kind="class"
 * @generated
 */
public class Predicate extends Equation {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final PredicateType TYPE_EDEFAULT = PredicateType.EQUAL;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected PredicateType type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Predicate() {
		super();
		setId(ConstraintExtensionProvider.EXTENSION_ID);
	}

	/**
	 * @generated NOT
	 */
	public Predicate(Parameter left, Parameter right, PredicateType type) {
		super(left, right);
		this.type = type;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.PREDICATE;
	}

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.ect.ea.extensions.constraints.PredicateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.ect.ea.extensions.constraints.PredicateType
	 * @see #setType(PredicateType)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getPredicate_Type()
	 * @model
	 * @generated
	 */
	public PredicateType getType() {
		return type;
	}

	/**
	 * Sets the value of the '{@link org.ect.ea.extensions.constraints.Predicate#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.ect.ea.extensions.constraints.PredicateType
	 * @see #getType()
	 * @generated
	 */
	public void setType(PredicateType newType) {
		PredicateType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.PREDICATE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated NOT
	 */
	public void negate() {
		switch (type) {
		case EQUAL:
			type = PredicateType.NOT_EQUAL;
			break;
		case NOT_EQUAL:
			type = PredicateType.EQUAL;
			break;
		case GREATER:
			type = PredicateType.LESS_EQUAL;
			break;
		case GREATER_EQUAL:
			type = PredicateType.LESS;
			break;
		case LESS:
			type = PredicateType.GREATER_EQUAL;
			break;
		case LESS_EQUAL:
			type = PredicateType.GREATER;
			break;	
		default:
			throw new IllegalStateException();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * Flips the operands and operator around.
	 * Result is equivalent to original predicate.
	 * <!-- end-user-doc -->
	 * @model
	 * @generated NOT
	 */
	public void flip() {
		Parameter temp = left; left = right; right = temp;
		
		switch (type) {
		case GREATER:
			type = PredicateType.LESS;
			break;
		case GREATER_EQUAL:
			type = PredicateType.LESS_EQUAL;
			break;
		case LESS:
			type = PredicateType.GREATER;
			break;
		case LESS_EQUAL:
			type = PredicateType.GREATER_EQUAL;
			break;	
		case EQUAL:
		case NOT_EQUAL:
			break;
		default:
			throw new IllegalStateException();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.PREDICATE__TYPE:
				return getType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintsPackage.PREDICATE__TYPE:
				setType((PredicateType)newValue);
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
			case ConstraintsPackage.PREDICATE__TYPE:
				setType(TYPE_EDEFAULT);
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
			case ConstraintsPackage.PREDICATE__TYPE:
				return type != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		return getLeft() + type.getLiteral() + getRight();
	}

} // Predicate
