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
package org.ect.ea.extensions.statememory;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.extensions.ExtensionsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.ect.ea.extensions.statememory.StateMemoryFactory
 * @model kind="package"
 * @generated
 */
public class StateMemoryPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "stateMemory";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/stateMemory";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "stateMemory";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final StateMemoryPackage eINSTANCE = org.ect.ea.extensions.statememory.StateMemoryPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.statememory.StateMemory <em>State Memory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.statememory.StateMemory
	 * @see org.ect.ea.extensions.statememory.StateMemoryPackage#getStateMemory()
	 * @generated
	 */
	public static final int STATE_MEMORY = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_MEMORY__OWNER = ExtensionsPackage.STRING_LIST_EXTENSION__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_MEMORY__ID = ExtensionsPackage.STRING_LIST_EXTENSION__ID;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_MEMORY__VALUES = ExtensionsPackage.STRING_LIST_EXTENSION__VALUES;

	/**
	 * The feature id for the '<em><b>Initializations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_MEMORY__INITIALIZATIONS = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>State Memory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_MEMORY_FEATURE_COUNT = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.statememory.StringMapEntry <em>String Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.statememory.StringMapEntry
	 * @see org.ect.ea.extensions.statememory.StateMemoryPackage#getStringMapEntry()
	 * @generated
	 */
	public static final int STRING_MAP_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateMemoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringMapEntryEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.ect.ea.extensions.statememory.StateMemoryPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StateMemoryPackage() {
		super(eNS_URI, StateMemoryFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StateMemoryPackage init() {
		if (isInited) return (StateMemoryPackage)EPackage.Registry.INSTANCE.getEPackage(StateMemoryPackage.eNS_URI);

		// Obtain or create and register package
		StateMemoryPackage theStateMemoryPackage = (StateMemoryPackage)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof StateMemoryPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new StateMemoryPackage());

		isInited = true;

		// Initialize simple dependencies
		AutomataPackage.eINSTANCE.eClass();
		ExtensionsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theStateMemoryPackage.createPackageContents();

		// Initialize created meta-data
		theStateMemoryPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStateMemoryPackage.freeze();

		return theStateMemoryPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.statememory.StateMemory <em>State Memory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Memory</em>'.
	 * @see org.ect.ea.extensions.statememory.StateMemory
	 * @generated
	 */
	public EClass getStateMemory() {
		return stateMemoryEClass;
	}

	/**
	 * Returns the meta object for the map '{@link org.ect.ea.extensions.statememory.StateMemory#getInitializations <em>Initializations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Initializations</em>'.
	 * @see org.ect.ea.extensions.statememory.StateMemory#getInitializations()
	 * @see #getStateMemory()
	 * @generated
	 */
	public EReference getStateMemory_Initializations() {
		return (EReference)stateMemoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	public EClass getStringMapEntry() {
		return stringMapEntryEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringMapEntry()
	 * @generated
	 */
	public EAttribute getStringMapEntry_Key() {
		return (EAttribute)stringMapEntryEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringMapEntry()
	 * @generated
	 */
	public EAttribute getStringMapEntry_Value() {
		return (EAttribute)stringMapEntryEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public StateMemoryFactory getStateMemoryFactory() {
		return (StateMemoryFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		stateMemoryEClass = createEClass(STATE_MEMORY);
		createEReference(stateMemoryEClass, STATE_MEMORY__INITIALIZATIONS);

		stringMapEntryEClass = createEClass(STRING_MAP_ENTRY);
		createEAttribute(stringMapEntryEClass, STRING_MAP_ENTRY__KEY);
		createEAttribute(stringMapEntryEClass, STRING_MAP_ENTRY__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ExtensionsPackage theExtensionsPackage = (ExtensionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		stateMemoryEClass.getESuperTypes().add(theExtensionsPackage.getStringListExtension());

		// Initialize classes and features; add operations and parameters
		initEClass(stateMemoryEClass, StateMemory.class, "StateMemory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStateMemory_Initializations(), this.getStringMapEntry(), null, "initializations", null, 0, -1, StateMemory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringMapEntryEClass, Map.Entry.class, "StringMapEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringMapEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public interface Literals {
		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.statememory.StateMemory <em>State Memory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.statememory.StateMemory
		 * @see org.ect.ea.extensions.statememory.StateMemoryPackage#getStateMemory()
		 * @generated
		 */
		public static final EClass STATE_MEMORY = eINSTANCE.getStateMemory();

		/**
		 * The meta object literal for the '<em><b>Initializations</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference STATE_MEMORY__INITIALIZATIONS = eINSTANCE.getStateMemory_Initializations();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.statememory.StringMapEntry <em>String Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.statememory.StringMapEntry
		 * @see org.ect.ea.extensions.statememory.StateMemoryPackage#getStringMapEntry()
		 * @generated
		 */
		public static final EClass STRING_MAP_ENTRY = eINSTANCE.getStringMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_MAP_ENTRY__KEY = eINSTANCE.getStringMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_MAP_ENTRY__VALUE = eINSTANCE.getStringMapEntry_Value();

	}

} //StateMemoryPackage
