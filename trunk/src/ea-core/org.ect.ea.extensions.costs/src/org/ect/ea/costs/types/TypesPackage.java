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
package org.ect.ea.costs.types;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.ea.costs.CostsPackage;
import org.ect.ea.costs.algebras.AlgebrasPackage;

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
 * @see org.ect.ea.costs.types.TypesFactory
 * @model kind="package"
 * @generated
 */
public class TypesPackage extends EPackageImpl {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "types";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/costs/types";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "org.ect.ea.costs.types";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final TypesPackage eINSTANCE = org.ect.ea.costs.types.TypesPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.types.IntegerCosts <em>Integer Costs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.types.IntegerCosts
	 * @see org.ect.ea.costs.types.TypesPackage#getIntegerCosts()
	 * @generated
	 */
	public static final int INTEGER_COSTS = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_COSTS__TYPE = CostsPackage.COSTS_OBJECT__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_COSTS__VALUE = CostsPackage.COSTS_OBJECT__VALUE;

	/**
	 * The feature id for the '<em><b>Integer Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_COSTS__INTEGER_VALUE = CostsPackage.COSTS_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Costs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_COSTS_FEATURE_COUNT = CostsPackage.COSTS_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.types.StringCosts <em>String Costs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.types.StringCosts
	 * @see org.ect.ea.costs.types.TypesPackage#getStringCosts()
	 * @generated
	 */
	public static final int STRING_COSTS = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_COSTS__TYPE = CostsPackage.COSTS_OBJECT__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_COSTS__VALUE = CostsPackage.COSTS_OBJECT__VALUE;

	/**
	 * The feature id for the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_COSTS__STRING_VALUE = CostsPackage.COSTS_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Costs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_COSTS_FEATURE_COUNT = CostsPackage.COSTS_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.types.FloatCosts <em>Float Costs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.types.FloatCosts
	 * @see org.ect.ea.costs.types.TypesPackage#getFloatCosts()
	 * @generated
	 */
	public static final int FLOAT_COSTS = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_COSTS__TYPE = CostsPackage.COSTS_OBJECT__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_COSTS__VALUE = CostsPackage.COSTS_OBJECT__VALUE;

	/**
	 * The feature id for the '<em><b>Float Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_COSTS__FLOAT_VALUE = CostsPackage.COSTS_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Costs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FLOAT_COSTS_FEATURE_COUNT = CostsPackage.COSTS_OBJECT_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerCostsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringCostsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatCostsEClass = null;

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
	 * @see org.ect.ea.costs.types.TypesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypesPackage() {
		super(eNS_URI, TypesFactory.eINSTANCE);
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
	public static TypesPackage init() {
		if (isInited) return (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Obtain or create and register package
		TypesPackage theTypesPackage = (TypesPackage)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof TypesPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new TypesPackage());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CostsPackage theCostsPackage = (CostsPackage)(EPackage.Registry.INSTANCE.getEPackage(CostsPackage.eNS_URI) instanceof CostsPackage ? EPackage.Registry.INSTANCE.getEPackage(CostsPackage.eNS_URI) : CostsPackage.eINSTANCE);
		AlgebrasPackage theAlgebrasPackage = (AlgebrasPackage)(EPackage.Registry.INSTANCE.getEPackage(AlgebrasPackage.eNS_URI) instanceof AlgebrasPackage ? EPackage.Registry.INSTANCE.getEPackage(AlgebrasPackage.eNS_URI) : AlgebrasPackage.eINSTANCE);

		// Create package meta-data objects
		theTypesPackage.createPackageContents();
		theCostsPackage.createPackageContents();
		theAlgebrasPackage.createPackageContents();

		// Initialize created meta-data
		theTypesPackage.initializePackageContents();
		theCostsPackage.initializePackageContents();
		theAlgebrasPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypesPackage.freeze();

		return theTypesPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.types.IntegerCosts <em>Integer Costs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Costs</em>'.
	 * @see org.ect.ea.costs.types.IntegerCosts
	 * @generated
	 */
	public EClass getIntegerCosts() {
		return integerCostsEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.costs.types.IntegerCosts#getIntegerValue <em>Integer Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Value</em>'.
	 * @see org.ect.ea.costs.types.IntegerCosts#getIntegerValue()
	 * @see #getIntegerCosts()
	 * @generated
	 */
	public EAttribute getIntegerCosts_IntegerValue() {
		return (EAttribute)integerCostsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.types.StringCosts <em>String Costs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Costs</em>'.
	 * @see org.ect.ea.costs.types.StringCosts
	 * @generated
	 */
	public EClass getStringCosts() {
		return stringCostsEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.costs.types.StringCosts#getStringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Value</em>'.
	 * @see org.ect.ea.costs.types.StringCosts#getStringValue()
	 * @see #getStringCosts()
	 * @generated
	 */
	public EAttribute getStringCosts_StringValue() {
		return (EAttribute)stringCostsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.types.FloatCosts <em>Float Costs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Costs</em>'.
	 * @see org.ect.ea.costs.types.FloatCosts
	 * @generated
	 */
	public EClass getFloatCosts() {
		return floatCostsEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.costs.types.FloatCosts#getFloatValue <em>Float Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Float Value</em>'.
	 * @see org.ect.ea.costs.types.FloatCosts#getFloatValue()
	 * @see #getFloatCosts()
	 * @generated
	 */
	public EAttribute getFloatCosts_FloatValue() {
		return (EAttribute)floatCostsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public TypesFactory getTypesFactory() {
		return (TypesFactory)getEFactoryInstance();
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
		integerCostsEClass = createEClass(INTEGER_COSTS);
		createEAttribute(integerCostsEClass, INTEGER_COSTS__INTEGER_VALUE);

		stringCostsEClass = createEClass(STRING_COSTS);
		createEAttribute(stringCostsEClass, STRING_COSTS__STRING_VALUE);

		floatCostsEClass = createEClass(FLOAT_COSTS);
		createEAttribute(floatCostsEClass, FLOAT_COSTS__FLOAT_VALUE);
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
		CostsPackage theCostsPackage = (CostsPackage)EPackage.Registry.INSTANCE.getEPackage(CostsPackage.eNS_URI);

		// Add supertypes to classes
		integerCostsEClass.getESuperTypes().add(theCostsPackage.getCostsObject());
		stringCostsEClass.getESuperTypes().add(theCostsPackage.getCostsObject());
		floatCostsEClass.getESuperTypes().add(theCostsPackage.getCostsObject());

		// Initialize classes and features; add operations and parameters
		initEClass(integerCostsEClass, IntegerCosts.class, "IntegerCosts", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerCosts_IntegerValue(), ecorePackage.getEInt(), "integerValue", null, 0, 1, IntegerCosts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringCostsEClass, StringCosts.class, "StringCosts", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringCosts_StringValue(), ecorePackage.getEString(), "stringValue", null, 0, 1, StringCosts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatCostsEClass, FloatCosts.class, "FloatCosts", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatCosts_FloatValue(), ecorePackage.getEFloat(), "floatValue", null, 0, 1, FloatCosts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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
		 * The meta object literal for the '{@link org.ect.ea.costs.types.IntegerCosts <em>Integer Costs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.types.IntegerCosts
		 * @see org.ect.ea.costs.types.TypesPackage#getIntegerCosts()
		 * @generated
		 */
		public static final EClass INTEGER_COSTS = eINSTANCE.getIntegerCosts();

		/**
		 * The meta object literal for the '<em><b>Integer Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute INTEGER_COSTS__INTEGER_VALUE = eINSTANCE.getIntegerCosts_IntegerValue();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.types.StringCosts <em>String Costs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.types.StringCosts
		 * @see org.ect.ea.costs.types.TypesPackage#getStringCosts()
		 * @generated
		 */
		public static final EClass STRING_COSTS = eINSTANCE.getStringCosts();

		/**
		 * The meta object literal for the '<em><b>String Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_COSTS__STRING_VALUE = eINSTANCE.getStringCosts_StringValue();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.types.FloatCosts <em>Float Costs</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.types.FloatCosts
		 * @see org.ect.ea.costs.types.TypesPackage#getFloatCosts()
		 * @generated
		 */
		public static final EClass FLOAT_COSTS = eINSTANCE.getFloatCosts();

		/**
		 * The meta object literal for the '<em><b>Float Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute FLOAT_COSTS__FLOAT_VALUE = eINSTANCE.getFloatCosts_FloatValue();

	}

} //TypesPackage
