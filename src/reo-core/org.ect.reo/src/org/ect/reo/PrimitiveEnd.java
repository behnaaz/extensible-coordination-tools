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

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * Abstract super class for source and sink ends.
 * A primitive end always belongs to exactly one primitive and
 * can be attached to exactly one node.
 * 
 * @see org.ect.reo.SourceEnd
 * @see org.ect.reo.SinkEnd
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public abstract class PrimitiveEnd extends MinimalEObjectImpl implements Nameable, PropertyHolder {
	
	/**
	 * Getter method for the node.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public abstract Node getNode();
	
	/**
	 * Setter method for the node.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public abstract void setNode(Node node);

	/**
	 * Getter method for the primitive.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public abstract Primitive getPrimitive();

	/**
	 * Setter method for the primitive.
	 * @model kind="operation"
	 * @generated NOT
	 */
	public abstract void setPrimitive(Primitive primitive);
	
	/**
	 * Returns <code>true</true> if this end belongs to a {@link Component}.
	 * @generated NOT
	 */
	public boolean isComponentEnd() {
		return getPrimitive() instanceof Component;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return (name!=null) ? "PrimitiveEnd " + name :
		"PrimitiveEnd@" + Integer.toHexString(hashCode());
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
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
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> properties;

	/**
	 * @generated
	 */
	public PrimitiveEnd() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.PRIMITIVE_END;
	}

	/**
	 * @see #setName(String)
	 * @see org.ect.reo.ReoPackage#getPrimitiveEnd_Name()
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.PRIMITIVE_END__NAME, oldName, name));
	}

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.ect.reo.Property}.
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see org.ect.reo.ReoPackage#getPropertyHolder_Properties()
	 * @model containment="true" keys="key"
	 * @generated
	 */
	public EList<Property> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<Property>(Property.class, this, ReoPackage.PRIMITIVE_END__PROPERTIES);
		}
		return properties;
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.PRIMITIVE_END__NAME:
				return getName();
			case ReoPackage.PRIMITIVE_END__PROPERTIES:
				return getProperties();
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
			case ReoPackage.PRIMITIVE_END__NAME:
				setName((String)newValue);
				return;
			case ReoPackage.PRIMITIVE_END__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends Property>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.PRIMITIVE_END__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ReoPackage.PRIMITIVE_END__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ReoPackage.PRIMITIVE_END__PROPERTIES:
				getProperties().clear();
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
			case ReoPackage.PRIMITIVE_END__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ReoPackage.PRIMITIVE_END__PROPERTIES:
				return properties != null && !properties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyHolder.class) {
			switch (derivedFeatureID) {
				case ReoPackage.PRIMITIVE_END__PROPERTIES: return ReoPackage.PROPERTY_HOLDER__PROPERTIES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyHolder.class) {
			switch (baseFeatureID) {
				case ReoPackage.PROPERTY_HOLDER__PROPERTIES: return ReoPackage.PRIMITIVE_END__PROPERTIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
	
}
