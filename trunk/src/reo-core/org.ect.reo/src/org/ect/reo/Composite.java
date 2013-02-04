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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.BasicEList.UnmodifiableEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * Composites consist of a set of connectors and a set of
 * components. This is an abstract superclass for {@link Module} 
 * and {@link Network}.
 *
 * @see org.ect.reo.ReoPackage#getComposite()
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class Composite extends MinimalEObjectImpl implements Nameable {
	
	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public abstract EList<Connector> getConnectors();
	
	/**
	 * @model kind="operation"
	 * @generated NOT
	 */
	public abstract EList<Component> getComponents();
	
	/**
	 * Get a list of all connectors in this network. This includes 
	 * all sub-connectors. The list is not modifiable.
	 * @return List of all connectors.
	 */
	public EList<Connector> getAllConnectors() {		
		List<Connector> connectors = new ArrayList<Connector>();
		for (Connector current : getConnectors()) {
			connectors.add(current);
			connectors.addAll(current.getAllSubConnectors());
		}
		return new UnmodifiableEList<Connector>(connectors.size(), connectors.toArray());
	}
	
	/**
	 * Get a list of all components in this network. This includes 
	 * all external and internal components. The list is not modifiable.
	 * @return List of all components.
	 */
	public EList<Component> getAllComponents() {		
		List<Component> components = new ArrayList<Component>();
		for (Primitive primitive : getAllPrimitives()) {
			if (primitive instanceof Component) {
				components.add((Component) primitive);
			}
		}
		return new UnmodifiableEList<Component>(components.size(), components.toArray());
	}
	
	/**
	 * Get a list of all primitives in this network. This includes 
	 * all components and primitives in connectors and sub-connectors. 
	 * The list is not modifiable.
	 * @return List of primitives.
	 */
	public EList<Primitive> getAllPrimitives() {
		List<Primitive> primitives = new ArrayList<Primitive>();
		primitives.addAll(getComponents());
		for (Connector connector : getAllConnectors()) {
			primitives.addAll(connector.getPrimitives());
		}
		return new UnmodifiableEList<Primitive>(primitives.size(), primitives.toArray());
	}
	
	
	/**
	 * Get a list of all nodes in this network. This includes 
	 * the nodes in all sub-connectors.
	 * The list is not modifiable.
	 * @return List of nodes.
	 */
	public EList<Node> getAllNodes() {
		List<Node> nodes = new ArrayList<Node>();
		for (Connector connector : getAllConnectors()) {
			nodes.addAll(connector.getNodes());
		}
		return new UnmodifiableEList<Node>(nodes.size(), nodes.toArray());
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * @generated
	 */
	public Composite() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.COMPOSITE;
	}

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.ect.reo.ReoPackage#getNameable_Name()
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.Composite#getName <em>Name</em>}' attribute.
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.COMPOSITE__NAME, oldName, name));
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.COMPOSITE__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReoPackage.COMPOSITE__NAME:
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
			case ReoPackage.COMPOSITE__NAME:
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
			case ReoPackage.COMPOSITE__NAME:
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

}
