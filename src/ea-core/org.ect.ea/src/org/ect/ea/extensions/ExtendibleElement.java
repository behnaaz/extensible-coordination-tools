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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * @see org.ect.ea.extensions.ExtensionsPackage#getExtendibleElement()
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class ExtendibleElement extends MinimalEObjectImpl implements IExtendible {

	/**
	 * @generated NOT
	 */
	public ExtendibleElement() {
		super();
	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.extensions.IExtendible#findExtension(java.lang.String)
	 */
	public IExtension findExtension(String id) {
		for (IExtension extension : getExtensions()) {
			if (id.equals(extension.getId())) {
				return extension;
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.extensions.IExtendible#updateExtension(org.ect.ea.extensions.IExtension)
	 */
	public void updateExtension(IExtension extension) {
		// Check whether there is already an extension with this id.
		IExtension oldExtension = findExtension(extension.getId());
		if (oldExtension!=null) {
			// Replace old extension.
			getExtensions().set( getExtensions().indexOf(oldExtension), extension );
		} else {
			// Add the extension.
			getExtensions().add(extension);
		}
	}

	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtensions()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}


	/**
	 * @see #getIExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<IExtension> extensions;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExtensionsPackage.Literals.EXTENDIBLE_ELEMENT;
	}

	/**
	 * @return the value of the '<em>Extensions</em>' map.
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtendible_Extensions()
	 * @see org.ect.ea.extensions.IExtension#getOwner
	 * @model mapType="org.ect.ea.extensions.StringToExtensionMapEntry" keyType="java.lang.String" valueType="org.ect.ea.extensions.IExtension"
	 * @generated
	 */
	public EList<IExtension> getExtensions() {
		if (extensions == null) {
			extensions = new EObjectContainmentWithInverseEList<IExtension>(IExtension.class, this, ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS, ExtensionsPackage.IEXTENSION__OWNER);
		}
		return extensions;
	}


	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS:
				return getExtensions();
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
			case ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS:
				getExtensions().clear();
				getExtensions().addAll((Collection<? extends IExtension>)newValue);
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
			case ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS:
				getExtensions().clear();
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
			case ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS:
				return extensions != null && !extensions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

}
