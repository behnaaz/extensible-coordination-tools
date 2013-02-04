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
package org.ect.reo.channels;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.Nameable;
import org.ect.reo.ReoPackage;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.CustomAnimationResolver;
import org.ect.reo.libraries.ReoLibraryUtil;
import org.ect.reo.util.PropertyHelper;
import org.ect.reo.util.ReoNames;


/**
 * @author Behrooz Nobakht, Christian Krause
 * @see org.ect.reo.channels.ChannelsPackage#getCustomSpoutChannel()
 * @model kind="class"
 * @generated
 */
public class CustomSpoutChannel extends SpoutChannel implements Nameable, CustomPrimitive {

	/**
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {
		return CustomAnimationResolver.getAnimationTable(this);
	}

	/**
	 * @generated NOT
	 */
	public URI getTypeURI() {
		return ReoLibraryUtil.getTypeURI(this);
	}

	/**
	 * @generated NOT
	 */
	public void setTypeURI(URI newTypeURI) {
		ReoLibraryUtil.setTypeURI(this, newTypeURI);
	}

	/**
	 * @generated NOT
	 */
	public void setResolved(CustomPrimitive newResolved) {
		// No notification!
		this.resolved = newResolved;
	}
	
	/**
	 * @model transient="true" volatile="true"
	 * @generated NOT
	 */
	public String getForegroundColor() {
		return PropertyHelper.getFirstValue(this, FOREGROUND_COLOR_KEY);
	}

	/**
	 * @see #getForegroundColor()
	 * @generated NOT
	 */
	public void setForegroundColor(String foregroundColor) {
		PropertyHelper.setOrRemoveHidden(this, FOREGROUND_COLOR_KEY, foregroundColor);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.Primitive#toString()
	 */
	@Override
	public String toString() {
		String myName = ReoNames.getName(this);
		return myName + "@" + Integer.toHexString(hashCode());
	}

	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTypeURI() <em>Type URI</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypeURI()
	 * @generated
	 * @ordered
	 */
	protected static final URI TYPE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolved() <em>Resolved</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResolved()
	 * @generated
	 * @ordered
	 */
	protected CustomPrimitive resolved;

	/**
	 * The default value of the '{@link #getForegroundColor() <em>Foreground Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForegroundColor()
	 * @generated
	 * @ordered
	 */
	protected static final String FOREGROUND_COLOR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isSynchronous() <em>Synchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronous()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNCHRONOUS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSynchronous() <em>Synchronous</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronous()
	 * @generated
	 * @ordered
	 */
	protected boolean synchronous = SYNCHRONOUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CustomSpoutChannel() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ChannelsPackage.Literals.CUSTOM_SPOUT_CHANNEL;
	}

	/**
	 * Returns the value of the '<em><b>Synchronous</b></em>' attribute.
	 * @return the value of the '<em>Synchronous</em>' attribute.
	 * @see #setSynchronous(boolean)
	 * @see org.ect.reo.channels.ChannelsPackage#getCustomPrimitive_Synchronous()
	 * @model
	 * @generated
	 */
	public boolean isSynchronous() {
		return synchronous;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.channels.CustomSpoutChannel#isSynchronous <em>Synchronous</em>}' attribute.
	 * @param value the new value of the '<em>Synchronous</em>' attribute.
	 * @see #isSynchronous()
	 * @generated
	 */
	public void setSynchronous(boolean newSynchronous) {
		boolean oldSynchronous = synchronous;
		synchronous = newSynchronous;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS, oldSynchronous, synchronous));
	}

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.ect.reo.channels.ChannelsPackage#getNameable_Name()
	 * @model
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.channels.CustomSpoutChannel#getName <em>Name</em>}' attribute.
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME, oldName, name));
	}

	/**
	 * Returns the value of the '<em><b>Type URI</b></em>' attribute. 
	 * @return the value of the '<em>Type URI</em>' attribute.
	 * @see #setTypeURI(URI)
	 * @see org.ect.reo.channels.ChannelsPackage#getCustomPrimitive_TypeURI()
	 * @model dataType="org.ect.reo.URI" transient="true" volatile="true"
	 * @generated
	 */
	/**
	 * Returns the value of the '<em><b>Resolved</b></em>' containment reference.
	 * @return the value of the '<em>Resolved</em>' containment reference.
	 * @see #setResolved(CustomPrimitive)
	 * @see org.ect.reo.channels.ChannelsPackage#getCustomPrimitive_Resolved()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	public CustomPrimitive getResolved() {
		return resolved;
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetResolved(CustomPrimitive newResolved, NotificationChain msgs) {
		CustomPrimitive oldResolved = resolved;
		resolved = newResolved;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED, oldResolved, newResolved);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED:
				return basicSetResolved(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME:
				return getName();
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__TYPE_URI:
				return getTypeURI();
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED:
				return getResolved();
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR:
				return getForegroundColor();
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS:
				return isSynchronous();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME:
				setName((String)newValue);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__TYPE_URI:
				setTypeURI((URI)newValue);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED:
				setResolved((CustomPrimitive)newValue);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR:
				setForegroundColor((String)newValue);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS:
				setSynchronous((Boolean)newValue);
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
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__TYPE_URI:
				setTypeURI(TYPE_URI_EDEFAULT);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED:
				setResolved((CustomPrimitive)null);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR:
				setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
				return;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS:
				setSynchronous(SYNCHRONOUS_EDEFAULT);
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
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__TYPE_URI:
				return TYPE_URI_EDEFAULT == null ? getTypeURI() != null : !TYPE_URI_EDEFAULT.equals(getTypeURI());
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED:
				return resolved != null;
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR:
				return FOREGROUND_COLOR_EDEFAULT == null ? getForegroundColor() != null : !FOREGROUND_COLOR_EDEFAULT.equals(getForegroundColor());
			case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS:
				return synchronous != SYNCHRONOUS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Nameable.class) {
			switch (derivedFeatureID) {
				case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME: return ReoPackage.NAMEABLE__NAME;
				default: return -1;
			}
		}
		if (baseClass == CustomPrimitive.class) {
			switch (derivedFeatureID) {
				case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__TYPE_URI: return ReoPackage.CUSTOM_PRIMITIVE__TYPE_URI;
				case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED: return ReoPackage.CUSTOM_PRIMITIVE__RESOLVED;
				case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR: return ReoPackage.CUSTOM_PRIMITIVE__FOREGROUND_COLOR;
				case ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS: return ReoPackage.CUSTOM_PRIMITIVE__SYNCHRONOUS;
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
		if (baseClass == Nameable.class) {
			switch (baseFeatureID) {
				case ReoPackage.NAMEABLE__NAME: return ChannelsPackage.CUSTOM_SPOUT_CHANNEL__NAME;
				default: return -1;
			}
		}
		if (baseClass == CustomPrimitive.class) {
			switch (baseFeatureID) {
				case ReoPackage.CUSTOM_PRIMITIVE__TYPE_URI: return ChannelsPackage.CUSTOM_SPOUT_CHANNEL__TYPE_URI;
				case ReoPackage.CUSTOM_PRIMITIVE__RESOLVED: return ChannelsPackage.CUSTOM_SPOUT_CHANNEL__RESOLVED;
				case ReoPackage.CUSTOM_PRIMITIVE__FOREGROUND_COLOR: return ChannelsPackage.CUSTOM_SPOUT_CHANNEL__FOREGROUND_COLOR;
				case ReoPackage.CUSTOM_PRIMITIVE__SYNCHRONOUS: return ChannelsPackage.CUSTOM_SPOUT_CHANNEL__SYNCHRONOUS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} // CustomSpoutChannel
