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
package org.ect.reo.colouring;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.reo.ReoPackage;
import org.ect.reo.animation.AnimationPackage;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.components.ComponentsPackage;


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
 * @see org.ect.reo.colouring.ColouringFactory
 * @model kind="package"
 * @generated
 */
public class ColouringPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "colouring";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/reo/colouring";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "colouring";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ColouringPackage eINSTANCE = org.ect.reo.colouring.ColouringPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.reo.colouring.Colourable <em>Colourable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.colouring.Colourable
	 * @see org.ect.reo.colouring.ColouringPackage#getColourable()
	 * @generated
	 */
	public static final int COLOURABLE = 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.colouring.ColouringTable <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.colouring.ColouringTable
	 * @see org.ect.reo.colouring.ColouringPackage#getColouringTable()
	 * @generated
	 */
	public static final int COLOURING_TABLE = 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.colouring.Colouring <em>Colouring</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.colouring.Colouring
	 * @see org.ect.reo.colouring.ColouringPackage#getColouring()
	 * @generated
	 */
	public static final int COLOURING = 0;

	/**
	 * The feature id for the '<em><b>Colours</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING__COLOURS = 0;

	/**
	 * The feature id for the '<em><b>Next Colouring Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING__NEXT_COLOURING_TABLE = 1;

	/**
	 * The feature id for the '<em><b>Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING__PARTS = 2;

	/**
	 * The number of structural features of the '<em>Colouring</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_TABLE__NAME = ReoPackage.NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Colourings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_TABLE__COLOURINGS = ReoPackage.NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Colours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_TABLE__COLOURS = ReoPackage.NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_TABLE_FEATURE_COUNT = ReoPackage.NAMEABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Colourable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURABLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.colouring.ColouringEntry <em>Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.colouring.ColouringEntry
	 * @see org.ect.reo.colouring.ColouringPackage#getColouringEntry()
	 * @generated
	 */
	public static final int COLOURING_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLOURING_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.colouring.FlowColour <em>Flow Colour</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.colouring.FlowColour
	 * @see org.ect.reo.colouring.ColouringPackage#getFlowColour()
	 * @generated
	 */
	public static final int FLOW_COLOUR = 4;

	/**
	 * The meta object id for the '<em>Engine</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.colouring.ColouringEngine
	 * @see org.ect.reo.colouring.ColouringPackage#getColouringEngine()
	 * @generated
	 */
	public static final int COLOURING_ENGINE = 5;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colourableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colouringEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colouringTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass colouringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum flowColourEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType colouringEngineEDataType = null;

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
	 * @see org.ect.reo.colouring.ColouringPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ColouringPackage() {
		super(eNS_URI, ColouringFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ColouringPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ColouringPackage init() {
		if (isInited) return (ColouringPackage)EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI);

		// Obtain or create and register package
		ColouringPackage theColouringPackage = (ColouringPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ColouringPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ColouringPackage());

		isInited = true;

		// Obtain or create and register interdependencies
		ReoPackage theReoPackage = (ReoPackage)(EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) instanceof ReoPackage ? EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) : ReoPackage.eINSTANCE);
		ChannelsPackage theChannelsPackage = (ChannelsPackage)(EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) instanceof ChannelsPackage ? EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) : ChannelsPackage.eINSTANCE);
		AnimationPackage theAnimationPackage = (AnimationPackage)(EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) instanceof AnimationPackage ? EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) : AnimationPackage.eINSTANCE);
		ComponentsPackage theComponentsPackage = (ComponentsPackage)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackage ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);

		// Create package meta-data objects
		theColouringPackage.createPackageContents();
		theReoPackage.createPackageContents();
		theChannelsPackage.createPackageContents();
		theAnimationPackage.createPackageContents();
		theComponentsPackage.createPackageContents();

		// Initialize created meta-data
		theColouringPackage.initializePackageContents();
		theReoPackage.initializePackageContents();
		theChannelsPackage.initializePackageContents();
		theAnimationPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theColouringPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ColouringPackage.eNS_URI, theColouringPackage);
		return theColouringPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.colouring.Colourable <em>Colourable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Colourable</em>'.
	 * @see org.ect.reo.colouring.Colourable
	 * @generated
	 */
	public EClass getColourable() {
		return colourableEClass;
	}

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.ect.reo.PrimitiveEnd"
	 *        valueDataType="org.ect.reo.colouring.FlowColour"
	 * @generated
	 */
	public EClass getColouringEntry() {
		return colouringEntryEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getColouringEntry()
	 * @generated
	 */
	public EReference getColouringEntry_Key() {
		return (EReference)colouringEntryEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getColouringEntry()
	 * @generated
	 */
	public EAttribute getColouringEntry_Value() {
		return (EAttribute)colouringEntryEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.colouring.ColouringTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see org.ect.reo.colouring.ColouringTable
	 * @generated
	 */
	public EClass getColouringTable() {
		return colouringTableEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.colouring.ColouringTable#getColourings <em>Colourings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Colourings</em>'.
	 * @see org.ect.reo.colouring.ColouringTable#getColourings()
	 * @see #getColouringTable()
	 * @generated
	 */
	public EReference getColouringTable_Colourings() {
		return (EReference)colouringTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.colouring.ColouringTable#getColours <em>Colours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Colours</em>'.
	 * @see org.ect.reo.colouring.ColouringTable#getColours()
	 * @see #getColouringTable()
	 * @generated
	 */
	public EAttribute getColouringTable_Colours() {
		return (EAttribute)colouringTableEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.colouring.Colouring <em>Colouring</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Colouring</em>'.
	 * @see org.ect.reo.colouring.Colouring
	 * @generated
	 */
	public EClass getColouring() {
		return colouringEClass;
	}

	/**
	 * Returns the meta object for the map '{@link org.ect.reo.colouring.Colouring#getColours <em>Colours</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Colours</em>'.
	 * @see org.ect.reo.colouring.Colouring#getColours()
	 * @see #getColouring()
	 * @generated
	 */
	public EReference getColouring_Colours() {
		return (EReference)colouringEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.colouring.Colouring#getNextColouringTable <em>Next Colouring Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next Colouring Table</em>'.
	 * @see org.ect.reo.colouring.Colouring#getNextColouringTable()
	 * @see #getColouring()
	 * @generated
	 */
	public EReference getColouring_NextColouringTable() {
		return (EReference)colouringEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.colouring.Colouring#getParts <em>Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parts</em>'.
	 * @see org.ect.reo.colouring.Colouring#getParts()
	 * @see #getColouring()
	 * @generated
	 */
	public EReference getColouring_Parts() {
		return (EReference)colouringEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for enum '{@link org.ect.reo.colouring.FlowColour <em>Flow Colour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Flow Colour</em>'.
	 * @see org.ect.reo.colouring.FlowColour
	 * @generated
	 */
	public EEnum getFlowColour() {
		return flowColourEEnum;
	}

	/**
	 * Returns the meta object for data type '{@link org.ect.reo.colouring.ColouringEngine <em>Engine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Engine</em>'.
	 * @see org.ect.reo.colouring.ColouringEngine
	 * @model instanceClass="org.ect.reo.colouring.ColouringEngine"
	 * @generated
	 */
	public EDataType getColouringEngine() {
		return colouringEngineEDataType;
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ColouringFactory getColouringFactory() {
		return (ColouringFactory)getEFactoryInstance();
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
		colouringEClass = createEClass(COLOURING);
		createEReference(colouringEClass, COLOURING__COLOURS);
		createEReference(colouringEClass, COLOURING__NEXT_COLOURING_TABLE);
		createEReference(colouringEClass, COLOURING__PARTS);

		colouringTableEClass = createEClass(COLOURING_TABLE);
		createEReference(colouringTableEClass, COLOURING_TABLE__COLOURINGS);
		createEAttribute(colouringTableEClass, COLOURING_TABLE__COLOURS);

		colourableEClass = createEClass(COLOURABLE);

		colouringEntryEClass = createEClass(COLOURING_ENTRY);
		createEReference(colouringEntryEClass, COLOURING_ENTRY__KEY);
		createEAttribute(colouringEntryEClass, COLOURING_ENTRY__VALUE);

		// Create enums
		flowColourEEnum = createEEnum(FLOW_COLOUR);

		// Create data types
		colouringEngineEDataType = createEDataType(COLOURING_ENGINE);
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
		ReoPackage theReoPackage = (ReoPackage)EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		colouringTableEClass.getESuperTypes().add(theReoPackage.getNameable());

		// Initialize classes and features; add operations and parameters
		initEClass(colouringEClass, Colouring.class, "Colouring", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getColouring_Colours(), this.getColouringEntry(), null, "colours", null, 0, -1, Colouring.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getColouring_NextColouringTable(), this.getColouringTable(), null, "nextColouringTable", null, 0, 1, Colouring.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getColouring_Parts(), this.getColouring(), null, "parts", null, 0, -1, Colouring.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		EOperation op = addEOperation(colouringEClass, this.getColouring(), "join", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getColouring(), "function", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(colouringTableEClass, ColouringTable.class, "ColouringTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getColouringTable_Colourings(), this.getColouring(), null, "colourings", null, 0, -1, ColouringTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColouringTable_Colours(), ecorePackage.getEInt(), "colours", "3", 0, 1, ColouringTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(colouringTableEClass, null, "join", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getColouringTable(), "table", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(colourableEClass, Colourable.class, "Colourable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(colourableEClass, this.getColouringTable(), "getColouringTable", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(colourableEClass, ecorePackage.getEBoolean(), "usesFlipRule", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(colouringEntryEClass, Map.Entry.class, "ColouringEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getColouringEntry_Key(), theReoPackage.getPrimitiveEnd(), null, "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColouringEntry_Value(), this.getFlowColour(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(flowColourEEnum, FlowColour.class, "FlowColour");
		addEEnumLiteral(flowColourEEnum, FlowColour.FLOW_LITERAL);
		addEEnumLiteral(flowColourEEnum, FlowColour.NO_FLOW_LITERAL);
		addEEnumLiteral(flowColourEEnum, FlowColour.NO_FLOW_GIVE_REASON_LITERAL);
		addEEnumLiteral(flowColourEEnum, FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL);

		// Initialize data types
		initEDataType(colouringEngineEDataType, ColouringEngine.class, "ColouringEngine", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
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
		 * The meta object literal for the '{@link org.ect.reo.colouring.Colourable <em>Colourable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.colouring.Colourable
		 * @see org.ect.reo.colouring.ColouringPackage#getColourable()
		 * @generated
		 */
		public static final EClass COLOURABLE = eINSTANCE.getColourable();

		/**
		 * The meta object literal for the '{@link org.ect.reo.colouring.ColouringEntry <em>Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.colouring.ColouringEntry
		 * @see org.ect.reo.colouring.ColouringPackage#getColouringEntry()
		 * @generated
		 */
		public static final EClass COLOURING_ENTRY = eINSTANCE.getColouringEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLOURING_ENTRY__KEY = eINSTANCE.getColouringEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute COLOURING_ENTRY__VALUE = eINSTANCE.getColouringEntry_Value();

		/**
		 * The meta object literal for the '{@link org.ect.reo.colouring.ColouringTable <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.colouring.ColouringTable
		 * @see org.ect.reo.colouring.ColouringPackage#getColouringTable()
		 * @generated
		 */
		public static final EClass COLOURING_TABLE = eINSTANCE.getColouringTable();

		/**
		 * The meta object literal for the '<em><b>Colourings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLOURING_TABLE__COLOURINGS = eINSTANCE.getColouringTable_Colourings();

		/**
		 * The meta object literal for the '<em><b>Colours</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute COLOURING_TABLE__COLOURS = eINSTANCE.getColouringTable_Colours();

		/**
		 * The meta object literal for the '{@link org.ect.reo.colouring.Colouring <em>Colouring</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.colouring.Colouring
		 * @see org.ect.reo.colouring.ColouringPackage#getColouring()
		 * @generated
		 */
		public static final EClass COLOURING = eINSTANCE.getColouring();

		/**
		 * The meta object literal for the '<em><b>Colours</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLOURING__COLOURS = eINSTANCE.getColouring_Colours();

		/**
		 * The meta object literal for the '<em><b>Next Colouring Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLOURING__NEXT_COLOURING_TABLE = eINSTANCE.getColouring_NextColouringTable();

		/**
		 * The meta object literal for the '<em><b>Parts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLOURING__PARTS = eINSTANCE.getColouring_Parts();

		/**
		 * The meta object literal for the '{@link org.ect.reo.colouring.FlowColour <em>Flow Colour</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.colouring.FlowColour
		 * @see org.ect.reo.colouring.ColouringPackage#getFlowColour()
		 * @generated
		 */
		public static final EEnum FLOW_COLOUR = eINSTANCE.getFlowColour();

		/**
		 * The meta object literal for the '<em>Engine</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.colouring.ColouringEngine
		 * @see org.ect.reo.colouring.ColouringPackage#getColouringEngine()
		 * @generated
		 */
		public static final EDataType COLOURING_ENGINE = eINSTANCE.getColouringEngine();

	}

} //ColouringPackage
