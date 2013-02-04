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
package org.ect.ea.extensions;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @see org.ect.ea.extensions.ExtensionsPackage#getExtensionElement()
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class ExtensionElement extends MinimalEObjectImpl implements IExtension {

	/**
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
	protected ExtensionElement() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtensionsPackage.Literals.EXTENSION_ELEMENT;
	}

	/**
	 * @see #setOwner(IExtendible)
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtension_Owner()
	 * @see org.ect.ea.extensions.IExtendible#getExtensions
	 * @model opposite="extensions"
	 * @generated
	 */
	public IExtendible getOwner() {
		if (eContainerFeatureID() != ExtensionsPackage.EXTENSION_ELEMENT__OWNER) return null;
		return (IExtendible)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetOwner(IExtendible newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, ExtensionsPackage.EXTENSION_ELEMENT__OWNER, msgs);
		return msgs;
	}

	/**
	 * @see #getOwner()
	 * @generated
	 */
	public void setOwner(IExtendible newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != ExtensionsPackage.EXTENSION_ELEMENT__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, ExtensionsPackage.IEXTENDIBLE__EXTENSIONS, IExtendible.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtensionsPackage.EXTENSION_ELEMENT__OWNER, newOwner, newOwner));
	}

	/**
	 * @see #setId(String)
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtension_Id()
	 * @model
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * @see #getId()
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExtensionsPackage.EXTENSION_ELEMENT__ID, oldId, id));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExtensionsPackage.EXTENSION_ELEMENT__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((IExtendible)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExtensionsPackage.EXTENSION_ELEMENT__OWNER:
				return basicSetOwner(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ExtensionsPackage.EXTENSION_ELEMENT__OWNER:
				return eInternalContainer().eInverseRemove(this, ExtensionsPackage.IEXTENDIBLE__EXTENSIONS, IExtendible.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtensionsPackage.EXTENSION_ELEMENT__OWNER:
				return getOwner();
			case ExtensionsPackage.EXTENSION_ELEMENT__ID:
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
			case ExtensionsPackage.EXTENSION_ELEMENT__OWNER:
				setOwner((IExtendible)newValue);
				return;
			case ExtensionsPackage.EXTENSION_ELEMENT__ID:
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
			case ExtensionsPackage.EXTENSION_ELEMENT__OWNER:
				setOwner((IExtendible)null);
				return;
			case ExtensionsPackage.EXTENSION_ELEMENT__ID:
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
			case ExtensionsPackage.EXTENSION_ELEMENT__OWNER:
				return getOwner() != null;
			case ExtensionsPackage.EXTENSION_ELEMENT__ID:
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
