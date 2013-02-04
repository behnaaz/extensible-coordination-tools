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
package org.ect.ea.extensions.constraints;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.IntentionalPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.ea.extensions.portnames.providers.IntensionalPortNamesProvider;
import org.ect.ea.extensions.portnames.providers.TransitionPortNamesProvider;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;

/**
 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getElement()
 * @model kind="class"
 * @generated
 */
public class Element extends EObjectImpl implements Parameter {

	/**
	 * @generated NOT
	 */
	public Element() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public Element(String value, ElementType type) {
		super();
		setValue(value);
		setType(type);
	}
	

	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public boolean isIdentifier() {
		return getType().getValue()==ElementType.IDENTIFIER_VALUE;
	}

	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public boolean isStringValue() {
		return getType().getValue()==ElementType.STRING_VALUE;
	}

	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public boolean isIntegerValue() {
		return getType().getValue()==ElementType.INTEGER_VALUE;
	}

	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public boolean isPrimitiveValue() {
		return isIntegerValue() || isStringValue();
	}
	
	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public boolean isSourceMemory() {
		return getType().getValue()==ElementType.SOURCE_MEMORY_VALUE;
	}

	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public boolean isTargetMemory() {
		return getType().getValue()==ElementType.TARGET_MEMORY_VALUE;
	}


	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public boolean isMemory() {
		return isSourceMemory() || isTargetMemory();
	}

	
	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public Equation getEquation() {
		if (eContainer() instanceof Equation) return (Equation) eContainer();
		return null;
	}
	
	
	/**
	 * @generated NOT
	 */
	public EList<Element> getAllElements() {
		EList<Element> elements = new BasicEList<Element>();
		elements.add(this);
		return elements;
	}
	
	
	/**
	 * @generated NOT
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof Element) {
			Element element = (Element) object;
			return element.getType()==getType() &&
				   (""+element.getValue()).equals(getValue());
		}
		return false;
	}
	
	
	/**
	 * @generated NOT
	 */
	@Override
	public int hashCode() {
		int valueHash = getValue()==null ? 0 : getValue().hashCode();
		int typeHash = getType()==null ? 0 : getType().getValue() + 1;
		return (valueHash+2) * (typeHash+3);
	}
	
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		switch (getType().getValue()) {
			case ElementType.IDENTIFIER_VALUE		: return getValue();
			case ElementType.INTEGER_VALUE			: return getValue();
			case ElementType.STRING_VALUE			: return "\"" + getValue() + "\"";
			case ElementType.SOURCE_MEMORY_VALUE	: return "$s." + getValue();
			case ElementType.TARGET_MEMORY_VALUE	: return "$t." + getValue();
		}
		return getValue();
	}

	
	/**
	 * Validate this element.
	 */
	public IValidationResult validate() {
		
		// Get the transition to which this constraint belongs to.
		IExtendible owner = getEquation().getOwner();
		if (!(owner instanceof Transition)) {
			return ValidationResult.newOKResult();
		}
		
		Transition transition = (Transition) owner;
		StringListExtension memory;
		
		switch (getType().getValue()) {
			
			case ElementType.IDENTIFIER_VALUE:
				
				// Check whether the identifier is a legal port name.
				TransitionPortNames ports = (TransitionPortNames) owner.findExtension(TransitionPortNamesProvider.EXTENSION_ID);		
				IntentionalPortNames iports = (IntentionalPortNames) owner.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				
				if ((ports==null || !ports.getValues().contains(getValue())) &&
					(iports==null || (!iports.getRequests().contains(getValue()) && !iports.getFirings().contains(getValue())))) {
					return ValidationResult.newErrorResult("Unknown port name: " + getValue());
				}
				
				break;

			case ElementType.SOURCE_MEMORY_VALUE:
				
				// Check whether the "$s.*" refers to an existing source memory cell.
				memory = CA.getMemoryCells(((Transition) owner).getSource());		
				if (memory==null || !memory.getValues().contains(getValue())) {
					return ValidationResult.newErrorResult("Unknown source memory cell: " + getValue());
				}
				
				// Check whether it has been assigned as a target.
				for (Transition t : transition.getSource().getIncoming()) {
					Constraint constraint = CA.getConstraint(t);
					boolean found = false;
					for (Element element : constraint.getAllElements()) {
						if (element.getType().getValue()==ElementType.TARGET_MEMORY_VALUE && element.getValue().equals(getValue())) {
							found = true; break;
						}
					}
					if (!found) return ValidationResult.newErrorResult("Uninitialized source memory cell: " + getValue());
				}

				break;

			case ElementType.TARGET_MEMORY_VALUE:
				
				// Check whether the "$t.*" refers to an existing target memory cell.				
				memory = CA.getMemoryCells(((Transition) owner).getTarget());		
				if (memory==null || !memory.getValues().contains(getValue())) {
					return ValidationResult.newErrorResult("Unknown target memory cell: " + getValue());
				}
				
				break;
		}

		return ValidationResult.newOKResult();
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
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ElementType TYPE_EDEFAULT = ElementType.IDENTIFIER;

	/**
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ElementType type = TYPE_EDEFAULT;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.ELEMENT;
	}

	/**
	 * @see #setValue(String)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getElement_Value()
	 * @model
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @see #getValue()
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.ELEMENT__VALUE, oldValue, value));
	}

	/**
	 * @see org.ect.ea.extensions.constraints.ElementType
	 * @see #setType(ElementType)
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getElement_Type()
	 * @model
	 * @generated
	 */
	public ElementType getType() {
		return type;
	}

	/**
	 * @see org.ect.ea.extensions.constraints.ElementType
	 * @see #getType()
	 * @generated
	 */
	public void setType(ElementType newType) {
		ElementType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.ELEMENT__TYPE, oldType, type));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.ELEMENT__VALUE:
				return getValue();
			case ConstraintsPackage.ELEMENT__TYPE:
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
			case ConstraintsPackage.ELEMENT__VALUE:
				setValue((String)newValue);
				return;
			case ConstraintsPackage.ELEMENT__TYPE:
				setType((ElementType)newValue);
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
			case ConstraintsPackage.ELEMENT__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case ConstraintsPackage.ELEMENT__TYPE:
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
			case ConstraintsPackage.ELEMENT__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case ConstraintsPackage.ELEMENT__TYPE:
				return type != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} // Element
