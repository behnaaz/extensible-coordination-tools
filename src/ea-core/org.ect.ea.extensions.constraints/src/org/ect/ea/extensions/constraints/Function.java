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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;



/**
 * Function model for constraints. Functions have a name (see {@link #value}),
 * a class name (see {@link #className}) and a number of parameters (see {@link #parameters}).
 * Functions may be used as parameters in equations or as stand-alone constraints (predicates).
 * 
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getFunction()
 * @model kind="class"
 * @generated
 */
public class Function extends ExtensionElement implements Parameter {
	
	/**
	 * @generated NOT
	 */
	public Function() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public Function(String name) {
		super();
		this.value = name;
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
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer(getValue() + "(");
		for (int i=0; i<getParameters().size(); i++) {
			result.append( getParameters().get(i) );
			if (i!=getParameters().size()-1) result.append(",");
		}
		
		return result.toString() + ")";
	}

	/**
	 * @generated NOT
	 */
	public EList<Element> getAllElements() {
		EList<Element> elements = new BasicEList<Element>();
		for (Parameter param : getParameters()) {
			elements.addAll(param.getAllElements());
		}
		return elements;
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	
	/**
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameters;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.FUNCTION;
	}

	/**
	 * @see #setClassName(String)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getFunction_ClassName()
	 * @model
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @see #getClassName()
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FUNCTION__CLASS_NAME, oldClassName, className));
	}

	/**
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getFunction_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	public EList<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, ConstraintsPackage.FUNCTION__PARAMETERS);
		}
		return parameters;
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
	 * @see #setValue(String)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getFunction_Value()
	 * @model
	 * @generated
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FUNCTION__VALUE, oldValue, value));
	}


	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintsPackage.FUNCTION__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.FUNCTION__VALUE:
				return getValue();
			case ConstraintsPackage.FUNCTION__CLASS_NAME:
				return getClassName();
			case ConstraintsPackage.FUNCTION__PARAMETERS:
				return getParameters();
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
			case ConstraintsPackage.FUNCTION__VALUE:
				setValue((String)newValue);
				return;
			case ConstraintsPackage.FUNCTION__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case ConstraintsPackage.FUNCTION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Parameter>)newValue);
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
			case ConstraintsPackage.FUNCTION__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case ConstraintsPackage.FUNCTION__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case ConstraintsPackage.FUNCTION__PARAMETERS:
				getParameters().clear();
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
			case ConstraintsPackage.FUNCTION__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case ConstraintsPackage.FUNCTION__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case ConstraintsPackage.FUNCTION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // Function
