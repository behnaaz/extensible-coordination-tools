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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.animation.CustomAnimationResolver;
import org.ect.reo.libraries.ReoLibraryUtil;
import org.ect.reo.util.PropertyHelper;
import org.ect.reo.util.ReoNames;


/**
 * Components are primitives that reside outside of connectors.
 * 
 * @see org.ect.reo.ReoPackage#getComponent()
 * @model kind="class"
 * @generated
 */
public class Component extends Primitive implements Nameable, CustomPrimitive {
	
	/**
	 * Convenience constructor.
	 * @generated NOT
	 */
	public Component(String name) {
		this();
		setName(name);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public void initializeEnds() {
		// no default number of ends.
	}
		
	/**
	 * Check if this component is internal, i.e. if it is
	 * contained in a connector, and not a module.
	 * @generated NOT
	 */
	public boolean isInternal() {
		return getConnector()!=null;
	}
	
	/**
	 * Get the animation table.
	 * @generated NOT
	 */
	public AnimationTable getAnimationTable() {
		return CustomAnimationResolver.getAnimationTable(this);
	}	
	
	/**
	 * Returns the value of the '<em><b>Type URI</b></em>' attribute.
	 * @generated NOT
	 */
	public URI getTypeURI() {
		return ReoLibraryUtil.getTypeURI(this);
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.Component#getTypeURI <em>Type URI</em>}' attribute.
	 * @generated NOT
	 */
	public void setTypeURI(URI newTypeURI) {
		ReoLibraryUtil.setTypeURI(this, newTypeURI);		
	}
	
	/**
	 * Set the resolved component.
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

	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		String name = ReoNames.getName(this);
		return name + "@" + Integer.toHexString(hashCode()); 
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
	 * The default value of the '{@link #getTypeURI() <em>Type URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeURI()
	 * @generated
	 * @ordered
	 */
	protected static final URI TYPE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResolved() <em>Resolved</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	public Component() {
		super();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReoPackage.Literals.COMPONENT;
	}

	/**
	 * @see #setName(String)
	 * @see org.ect.reo.ReoPackage#getComponent_Name()
	 * @model derived="true"
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
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.COMPONENT__NAME, oldName, name));
	}

	/**
	 * Returns the value of the '<em><b>Synchronous</b></em>' attribute.
	 * @return the value of the '<em>Synchronous</em>' attribute.
	 * @see #setSynchronous(boolean)
	 * @see org.ect.reo.ReoPackage#getCustomPrimitive_Synchronous()
	 * @model
	 * @generated
	 */
	public boolean isSynchronous() {
		return synchronous;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.Component#isSynchronous <em>Synchronous</em>}' attribute.
	 * @param value the new value of the '<em>Synchronous</em>' attribute.
	 * @see #isSynchronous()
	 * @generated
	 */
	public void setSynchronous(boolean newSynchronous) {
		boolean oldSynchronous = synchronous;
		synchronous = newSynchronous;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.COMPONENT__SYNCHRONOUS, oldSynchronous, synchronous));
	}

	/**
	 * @see #setModule(Module)
	 * @see org.ect.reo.ReoPackage#getComponent_Module()
	 * @see org.ect.reo.Module#getComponents
	 * @model opposite="components" transient="false"
	 * @generated
	 */
	public Module getModule() {
		if (eContainerFeatureID() != ReoPackage.COMPONENT__MODULE) return null;
		return (Module)eContainer();
	}


	/**
	 * @generated
	 */
	public NotificationChain basicSetModule(Module newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, ReoPackage.COMPONENT__MODULE, msgs);
		return msgs;
	}


	/**
	 * @see #getModule()
	 * @generated
	 */
	public void setModule(Module newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != ReoPackage.COMPONENT__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, ReoPackage.MODULE__COMPONENTS, Module.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReoPackage.COMPONENT__MODULE, newModule, newModule));
	}


	/**
	 * Returns the value of the '<em><b>Resolved</b></em>' containment reference.
	 * @return the value of the '<em>Resolved</em>' containment reference.
	 * @see #setResolved(Component)
	 * @see org.ect.reo.ReoPackage#getComponent_Resolved()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	public CustomPrimitive getResolved() {
		return resolved;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResolved(CustomPrimitive newResolved, NotificationChain msgs) {
		CustomPrimitive oldResolved = resolved;
		resolved = newResolved;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReoPackage.COMPONENT__RESOLVED, oldResolved, newResolved);
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
			case ReoPackage.COMPONENT__RESOLVED:
				return basicSetResolved(null, msgs);
			case ReoPackage.COMPONENT__MODULE:
				return basicSetModule(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ReoPackage.COMPONENT__MODULE:
				return eInternalContainer().eInverseRemove(this, ReoPackage.MODULE__COMPONENTS, Module.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReoPackage.COMPONENT__NAME:
				return getName();
			case ReoPackage.COMPONENT__TYPE_URI:
				return getTypeURI();
			case ReoPackage.COMPONENT__RESOLVED:
				return getResolved();
			case ReoPackage.COMPONENT__FOREGROUND_COLOR:
				return getForegroundColor();
			case ReoPackage.COMPONENT__SYNCHRONOUS:
				return isSynchronous();
			case ReoPackage.COMPONENT__MODULE:
				return getModule();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReoPackage.COMPONENT__NAME:
				setName((String)newValue);
				return;
			case ReoPackage.COMPONENT__TYPE_URI:
				setTypeURI((URI)newValue);
				return;
			case ReoPackage.COMPONENT__RESOLVED:
				setResolved((CustomPrimitive)newValue);
				return;
			case ReoPackage.COMPONENT__FOREGROUND_COLOR:
				setForegroundColor((String)newValue);
				return;
			case ReoPackage.COMPONENT__SYNCHRONOUS:
				setSynchronous((Boolean)newValue);
				return;
			case ReoPackage.COMPONENT__MODULE:
				setModule((Module)newValue);
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
			case ReoPackage.COMPONENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ReoPackage.COMPONENT__TYPE_URI:
				setTypeURI(TYPE_URI_EDEFAULT);
				return;
			case ReoPackage.COMPONENT__RESOLVED:
				setResolved((CustomPrimitive)null);
				return;
			case ReoPackage.COMPONENT__FOREGROUND_COLOR:
				setForegroundColor(FOREGROUND_COLOR_EDEFAULT);
				return;
			case ReoPackage.COMPONENT__SYNCHRONOUS:
				setSynchronous(SYNCHRONOUS_EDEFAULT);
				return;
			case ReoPackage.COMPONENT__MODULE:
				setModule((Module)null);
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
			case ReoPackage.COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ReoPackage.COMPONENT__TYPE_URI:
				return TYPE_URI_EDEFAULT == null ? getTypeURI() != null : !TYPE_URI_EDEFAULT.equals(getTypeURI());
			case ReoPackage.COMPONENT__RESOLVED:
				return resolved != null;
			case ReoPackage.COMPONENT__FOREGROUND_COLOR:
				return FOREGROUND_COLOR_EDEFAULT == null ? getForegroundColor() != null : !FOREGROUND_COLOR_EDEFAULT.equals(getForegroundColor());
			case ReoPackage.COMPONENT__SYNCHRONOUS:
				return synchronous != SYNCHRONOUS_EDEFAULT;
			case ReoPackage.COMPONENT__MODULE:
				return getModule() != null;
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
				case ReoPackage.COMPONENT__NAME: return ReoPackage.NAMEABLE__NAME;
				default: return -1;
			}
		}
		if (baseClass == CustomPrimitive.class) {
			switch (derivedFeatureID) {
				case ReoPackage.COMPONENT__TYPE_URI: return ReoPackage.CUSTOM_PRIMITIVE__TYPE_URI;
				case ReoPackage.COMPONENT__RESOLVED: return ReoPackage.CUSTOM_PRIMITIVE__RESOLVED;
				case ReoPackage.COMPONENT__FOREGROUND_COLOR: return ReoPackage.CUSTOM_PRIMITIVE__FOREGROUND_COLOR;
				case ReoPackage.COMPONENT__SYNCHRONOUS: return ReoPackage.CUSTOM_PRIMITIVE__SYNCHRONOUS;
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
				case ReoPackage.NAMEABLE__NAME: return ReoPackage.COMPONENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == CustomPrimitive.class) {
			switch (baseFeatureID) {
				case ReoPackage.CUSTOM_PRIMITIVE__TYPE_URI: return ReoPackage.COMPONENT__TYPE_URI;
				case ReoPackage.CUSTOM_PRIMITIVE__RESOLVED: return ReoPackage.COMPONENT__RESOLVED;
				case ReoPackage.CUSTOM_PRIMITIVE__FOREGROUND_COLOR: return ReoPackage.COMPONENT__FOREGROUND_COLOR;
				case ReoPackage.CUSTOM_PRIMITIVE__SYNCHRONOUS: return ReoPackage.COMPONENT__SYNCHRONOUS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
	
	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReoPackage.COMPONENT__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((Module)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

}
