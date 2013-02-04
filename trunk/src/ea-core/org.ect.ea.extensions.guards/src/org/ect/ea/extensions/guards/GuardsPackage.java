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
package org.ect.ea.extensions.guards;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
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
 * @see org.ect.ea.extensions.guards.GuardsFactory
 * @model kind="package"
 * @generated
 */
public class GuardsPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "guards";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/guards";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "guards";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final GuardsPackage eINSTANCE = org.ect.ea.extensions.guards.GuardsPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.guards.Guard <em>Guard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.guards.Guard
	 * @see org.ect.ea.extensions.guards.GuardsPackage#getGuard()
	 * @generated
	 */
	public static final int GUARD = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int GUARD__OWNER = ExtensionsPackage.STRING_LIST_EXTENSION__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int GUARD__ID = ExtensionsPackage.STRING_LIST_EXTENSION__ID;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int GUARD__VALUES = ExtensionsPackage.STRING_LIST_EXTENSION__VALUES;

	/**
	 * The feature id for the '<em><b>Negated</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int GUARD__NEGATED = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Guard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int GUARD_FEATURE_COUNT = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guardEClass = null;

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
	 * @see org.ect.ea.extensions.guards.GuardsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GuardsPackage() {
		super(eNS_URI, GuardsFactory.eINSTANCE);
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
	public static GuardsPackage init() {
		if (isInited) return (GuardsPackage)EPackage.Registry.INSTANCE.getEPackage(GuardsPackage.eNS_URI);

		// Obtain or create and register package
		GuardsPackage theGuardsPackage = (GuardsPackage)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof GuardsPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new GuardsPackage());

		isInited = true;

		// Initialize simple dependencies
		AutomataPackage.eINSTANCE.eClass();
		ExtensionsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theGuardsPackage.createPackageContents();

		// Initialize created meta-data
		theGuardsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGuardsPackage.freeze();

		return theGuardsPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.guards.Guard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guard</em>'.
	 * @see org.ect.ea.extensions.guards.Guard
	 * @generated
	 */
	public EClass getGuard() {
		return guardEClass;
	}

	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.guards.Guard#getNegated <em>Negated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Negated</em>'.
	 * @see org.ect.ea.extensions.guards.Guard#getNegated()
	 * @see #getGuard()
	 * @generated
	 */
	public EAttribute getGuard_Negated() {
		return (EAttribute)guardEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public GuardsFactory getGuardsFactory() {
		return (GuardsFactory)getEFactoryInstance();
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
		guardEClass = createEClass(GUARD);
		createEAttribute(guardEClass, GUARD__NEGATED);
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
		guardEClass.getESuperTypes().add(theExtensionsPackage.getStringListExtension());

		// Initialize classes and features; add operations and parameters
		initEClass(guardEClass, Guard.class, "Guard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGuard_Negated(), ecorePackage.getEString(), "negated", null, 0, -1, Guard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		 * The meta object literal for the '{@link org.ect.ea.extensions.guards.Guard <em>Guard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.guards.Guard
		 * @see org.ect.ea.extensions.guards.GuardsPackage#getGuard()
		 * @generated
		 */
		public static final EClass GUARD = eINSTANCE.getGuard();

		/**
		 * The meta object literal for the '<em><b>Negated</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute GUARD__NEGATED = eINSTANCE.getGuard_Negated();

	}

} //GuardsPackage
