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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * Source end of primitives. Being the counterpart of
 * sink ends, source ends are used to write data into
 * a primitive (a channel for example).
 * 
 * @see org.ect.reo.ReoFactory#createSourceEnd()
 * @see org.ect.reo.PrimitiveEnd
 * @model kind="class"
 * @generated
 */
public class SourceEnd extends PrimitiveEnd {

	/**
	 * @generated NOT
	 */
	public SourceEnd(String name) {
		super();
		setName(name);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return (name!=null) ? "Source " + name :
		"Source" + Integer.toHexString(hashCode());
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected Node node;
	
	/**
	 * @generated
	 */
	public SourceEnd() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.SOURCE_END;
	}

	/**
	 * Getter method for the node.
	 * @return The node that this end is attached to.
	 * @see #setNode(Node)
	 * @see org.ect.reo.Node#getSourceEnds
	 * @model opposite="sourceEnds"
	 * @generated
	 */
	public Node getNode() {
		if (node != null && node.eIsProxy()) {
			InternalEObject oldNode = (InternalEObject)node;
			node = (Node)eResolveProxy(oldNode);
			if (node != oldNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReoPackage.SOURCE_END__NODE, oldNode, node));
			}
		}
		return node;
	}

	/**
	 * @generated
	 */
	public Node basicGetNode() {
		return node;
	}

	/**
	 * Setter method for the node.
	 * @param newNode The new node.
	 * @see #getNode()
	 * @generated
	 */
	public void setNode(Node newNode) {
		if (newNode != node) {
			NotificationChain msgs = null;
			if (node != null)
				msgs = ((InternalEObject)node).eInverseRemove(this, ReoPackage.NODE__SOURCE_ENDS, Node.class, msgs);
			if (newNode != null)
				msgs = ((InternalEObject)newNode).eInverseAdd(this, ReoPackage.NODE__SOURCE_ENDS, Node.class, msgs);
			msgs = basicSetNode(newNode, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.SOURCE_END__NODE, newNode, newNode));
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetNode(Node newNode, NotificationChain msgs) {
		Node oldNode = node;
		node = newNode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReoPackage.SOURCE_END__NODE, oldNode, newNode);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Getter method for the primitive.
	 * @return The primitive this end belongs to.
	 * @see #setPrimitive(Primitive)
	 * @see org.ect.reo.Primitive#getSourceEnds
	 * @model opposite="sourceEnds"
	 * @generated
	 */
	public Primitive getPrimitive() {
		if (eContainerFeatureID() != ReoPackage.SOURCE_END__PRIMITIVE) return null;
		return (Primitive)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetPrimitive(Primitive newPrimitive, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPrimitive, ReoPackage.SOURCE_END__PRIMITIVE, msgs);
		return msgs;
	}

	/**
	 * Setter for the primitive.
	 * @param newPrimitive The new primitive.
	 * @see #getPrimitive()
	 * @generated
	 */
	public void setPrimitive(Primitive newPrimitive) {
		if (newPrimitive != eInternalContainer() || (eContainerFeatureID() != ReoPackage.SOURCE_END__PRIMITIVE && newPrimitive != null)) {
			if (EcoreUtil.isAncestor(this, newPrimitive))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPrimitive != null)
				msgs = ((InternalEObject)newPrimitive).eInverseAdd(this, ReoPackage.PRIMITIVE__SOURCE_ENDS, Primitive.class, msgs);
			msgs = basicSetPrimitive(newPrimitive, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.SOURCE_END__PRIMITIVE, newPrimitive, newPrimitive));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.SOURCE_END__NODE:
				if (node != null)
					msgs = ((InternalEObject)node).eInverseRemove(this, ReoPackage.NODE__SOURCE_ENDS, Node.class, msgs);
				return basicSetNode((Node)otherEnd, msgs);
			case ReoPackage.SOURCE_END__PRIMITIVE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPrimitive((Primitive)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.SOURCE_END__NODE:
				return basicSetNode(null, msgs);
			case ReoPackage.SOURCE_END__PRIMITIVE:
				return basicSetPrimitive(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ReoPackage.SOURCE_END__PRIMITIVE:
				return eInternalContainer().eInverseRemove(this, ReoPackage.PRIMITIVE__SOURCE_ENDS, Primitive.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.SOURCE_END__NODE:
				if (resolve) return getNode();
				return basicGetNode();
			case ReoPackage.SOURCE_END__PRIMITIVE:
				return getPrimitive();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReoPackage.SOURCE_END__NODE:
				setNode((Node)newValue);
				return;
			case ReoPackage.SOURCE_END__PRIMITIVE:
				setPrimitive((Primitive)newValue);
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
			case ReoPackage.SOURCE_END__NODE:
				setNode((Node)null);
				return;
			case ReoPackage.SOURCE_END__PRIMITIVE:
				setPrimitive((Primitive)null);
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
			case ReoPackage.SOURCE_END__NODE:
				return node != null;
			case ReoPackage.SOURCE_END__PRIMITIVE:
				return getPrimitive() != null;
		}
		return super.eIsSet(featureID);
	}

}
