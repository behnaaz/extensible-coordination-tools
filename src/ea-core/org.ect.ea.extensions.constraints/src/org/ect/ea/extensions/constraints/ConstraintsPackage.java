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
package org.ect.ea.extensions.constraints;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
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
 * @see org.ect.ea.extensions.constraints.ConstraintsFactory
 * @model kind="package"
 * @generated
 */
public class ConstraintsPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "constraints";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/constraints";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "constraints";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ConstraintsPackage eINSTANCE = org.ect.ea.extensions.constraints.ConstraintsPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Constraint <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Constraint
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getConstraint()
	 * @generated
	 */
	public static final int CONSTRAINT = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONSTRAINT__OWNER = ExtensionsPackage.IEXTENSION__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONSTRAINT__ID = ExtensionsPackage.IEXTENSION__ID;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONSTRAINT_FEATURE_COUNT = ExtensionsPackage.IEXTENSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Conjunction <em>Conjunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Conjunction
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getConjunction()
	 * @generated
	 */
	public static final int CONJUNCTION = 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Disjunction <em>Disjunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Disjunction
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getDisjunction()
	 * @generated
	 */
	public static final int DISJUNCTION = 2;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Equation <em>Equation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Equation
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getEquation()
	 * @generated
	 */
	public static final int EQUATION = 3;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Literal <em>Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Literal
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getLiteral()
	 * @generated
	 */
	public static final int LITERAL = 4;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Parameter <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Parameter
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getParameter()
	 * @generated
	 */
	public static final int PARAMETER = 6;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Element <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Element
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getElement()
	 * @generated
	 */
	public static final int ELEMENT = 5;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Function <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Function
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getFunction()
	 * @generated
	 */
	public static final int FUNCTION = 7;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Composite <em>Composite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Composite
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getComposite()
	 * @generated
	 */
	public static final int COMPOSITE = 8;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONJUNCTION__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONJUNCTION__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONJUNCTION__MEMBERS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Conjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONJUNCTION_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DISJUNCTION__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DISJUNCTION__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DISJUNCTION__MEMBERS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Disjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DISJUNCTION_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EQUATION__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EQUATION__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EQUATION__LEFT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EQUATION__RIGHT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Equation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EQUATION_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL__VALUE = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PARAMETER_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT__VALUE = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT__TYPE = PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FUNCTION__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FUNCTION__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FUNCTION__VALUE = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FUNCTION__CLASS_NAME = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FUNCTION__PARAMETERS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FUNCTION_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE__OWNER = CONSTRAINT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE__ID = CONSTRAINT__ID;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE__MEMBERS = CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.Predicate <em>Predicate</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.Predicate
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getPredicate()
	 * @generated
	 */
	public static final int PREDICATE = 9;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PREDICATE__OWNER = EQUATION__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PREDICATE__ID = EQUATION__ID;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PREDICATE__LEFT = EQUATION__LEFT;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PREDICATE__RIGHT = EQUATION__RIGHT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PREDICATE__TYPE = EQUATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Predicate</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PREDICATE_FEATURE_COUNT = EQUATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.ElementType <em>Element Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.ElementType
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getElementType()
	 * @generated
	 */
	public static final int ELEMENT_TYPE = 10;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.constraints.PredicateType <em>Predicate Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.constraints.PredicateType
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getPredicateType()
	 * @generated
	 */
	public static final int PREDICATE_TYPE = 11;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conjunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass disjunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass equationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass predicateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum elementTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum predicateTypeEEnum = null;

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
	 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ConstraintsPackage() {
		super(eNS_URI, ConstraintsFactory.eINSTANCE);
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
	public static ConstraintsPackage init() {
		if (isInited) return (ConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(ConstraintsPackage.eNS_URI);

		// Obtain or create and register package
		ConstraintsPackage theConstraintsPackage = (ConstraintsPackage)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ConstraintsPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ConstraintsPackage());

		isInited = true;

		// Initialize simple dependencies
		AutomataPackage.eINSTANCE.eClass();
		ExtensionsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theConstraintsPackage.createPackageContents();

		// Initialize created meta-data
		theConstraintsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theConstraintsPackage.freeze();

		return theConstraintsPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.ect.ea.extensions.constraints.Constraint
	 * @generated
	 */
	public EClass getConstraint() {
		return constraintEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Conjunction <em>Conjunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conjunction</em>'.
	 * @see org.ect.ea.extensions.constraints.Conjunction
	 * @generated
	 */
	public EClass getConjunction() {
		return conjunctionEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Disjunction <em>Disjunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Disjunction</em>'.
	 * @see org.ect.ea.extensions.constraints.Disjunction
	 * @generated
	 */
	public EClass getDisjunction() {
		return disjunctionEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Equation <em>Equation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Equation</em>'.
	 * @see org.ect.ea.extensions.constraints.Equation
	 * @generated
	 */
	public EClass getEquation() {
		return equationEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.ect.ea.extensions.constraints.Equation#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see org.ect.ea.extensions.constraints.Equation#getLeft()
	 * @see #getEquation()
	 * @generated
	 */
	public EReference getEquation_Left() {
		return (EReference)equationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.ect.ea.extensions.constraints.Equation#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see org.ect.ea.extensions.constraints.Equation#getRight()
	 * @see #getEquation()
	 * @generated
	 */
	public EReference getEquation_Right() {
		return (EReference)equationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Literal <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal</em>'.
	 * @see org.ect.ea.extensions.constraints.Literal
	 * @generated
	 */
	public EClass getLiteral() {
		return literalEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.constraints.Literal#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.ea.extensions.constraints.Literal#isValue()
	 * @see #getLiteral()
	 * @generated
	 */
	public EAttribute getLiteral_Value() {
		return (EAttribute)literalEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.ect.ea.extensions.constraints.Element
	 * @generated
	 */
	public EClass getElement() {
		return elementEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.constraints.Element#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.ea.extensions.constraints.Element#getValue()
	 * @see #getElement()
	 * @generated
	 */
	public EAttribute getElement_Value() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.constraints.Element#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ect.ea.extensions.constraints.Element#getType()
	 * @see #getElement()
	 * @generated
	 */
	public EAttribute getElement_Type() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.ect.ea.extensions.constraints.Parameter
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see org.ect.ea.extensions.constraints.Function
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.constraints.Function#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.ea.extensions.constraints.Function#getValue()
	 * @see #getFunction()
	 * @generated
	 */
	public EAttribute getFunction_Value() {
		return (EAttribute)functionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.constraints.Function#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see org.ect.ea.extensions.constraints.Function#getClassName()
	 * @see #getFunction()
	 * @generated
	 */
	public EAttribute getFunction_ClassName() {
		return (EAttribute)functionEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.extensions.constraints.Function#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.ect.ea.extensions.constraints.Function#getParameters()
	 * @see #getFunction()
	 * @generated
	 */
	public EReference getFunction_Parameters() {
		return (EReference)functionEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Composite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite</em>'.
	 * @see org.ect.ea.extensions.constraints.Composite
	 * @generated
	 */
	public EClass getComposite() {
		return compositeEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.extensions.constraints.Composite#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Members</em>'.
	 * @see org.ect.ea.extensions.constraints.Composite#getMembers()
	 * @see #getComposite()
	 * @generated
	 */
	public EReference getComposite_Members() {
		return (EReference)compositeEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.constraints.Predicate <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Predicate</em>'.
	 * @see org.ect.ea.extensions.constraints.Predicate
	 * @generated
	 */
	public EClass getPredicate() {
		return predicateEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.constraints.Predicate#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ect.ea.extensions.constraints.Predicate#getType()
	 * @see #getPredicate()
	 * @generated
	 */
	public EAttribute getPredicate_Type() {
		return (EAttribute)predicateEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for enum '{@link org.ect.ea.extensions.constraints.ElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Element Type</em>'.
	 * @see org.ect.ea.extensions.constraints.ElementType
	 * @generated
	 */
	public EEnum getElementType() {
		return elementTypeEEnum;
	}


	/**
	 * Returns the meta object for enum '{@link org.ect.ea.extensions.constraints.PredicateType <em>Predicate Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Predicate Type</em>'.
	 * @see org.ect.ea.extensions.constraints.PredicateType
	 * @generated
	 */
	public EEnum getPredicateType() {
		return predicateTypeEEnum;
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ConstraintsFactory getConstraintsFactory() {
		return (ConstraintsFactory)getEFactoryInstance();
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
		constraintEClass = createEClass(CONSTRAINT);

		conjunctionEClass = createEClass(CONJUNCTION);

		disjunctionEClass = createEClass(DISJUNCTION);

		equationEClass = createEClass(EQUATION);
		createEReference(equationEClass, EQUATION__LEFT);
		createEReference(equationEClass, EQUATION__RIGHT);

		literalEClass = createEClass(LITERAL);
		createEAttribute(literalEClass, LITERAL__VALUE);

		elementEClass = createEClass(ELEMENT);
		createEAttribute(elementEClass, ELEMENT__VALUE);
		createEAttribute(elementEClass, ELEMENT__TYPE);

		parameterEClass = createEClass(PARAMETER);

		functionEClass = createEClass(FUNCTION);
		createEAttribute(functionEClass, FUNCTION__VALUE);
		createEAttribute(functionEClass, FUNCTION__CLASS_NAME);
		createEReference(functionEClass, FUNCTION__PARAMETERS);

		compositeEClass = createEClass(COMPOSITE);
		createEReference(compositeEClass, COMPOSITE__MEMBERS);

		predicateEClass = createEClass(PREDICATE);
		createEAttribute(predicateEClass, PREDICATE__TYPE);

		// Create enums
		elementTypeEEnum = createEEnum(ELEMENT_TYPE);
		predicateTypeEEnum = createEEnum(PREDICATE_TYPE);
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
		constraintEClass.getESuperTypes().add(theExtensionsPackage.getIExtension());
		conjunctionEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());
		conjunctionEClass.getESuperTypes().add(this.getComposite());
		disjunctionEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());
		disjunctionEClass.getESuperTypes().add(this.getComposite());
		equationEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());
		equationEClass.getESuperTypes().add(this.getConstraint());
		literalEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());
		literalEClass.getESuperTypes().add(this.getConstraint());
		literalEClass.getESuperTypes().add(this.getParameter());
		elementEClass.getESuperTypes().add(this.getParameter());
		functionEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());
		functionEClass.getESuperTypes().add(this.getParameter());
		compositeEClass.getESuperTypes().add(this.getConstraint());
		predicateEClass.getESuperTypes().add(this.getEquation());

		// Initialize classes and features; add operations and parameters
		initEClass(constraintEClass, Constraint.class, "Constraint", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conjunctionEClass, Conjunction.class, "Conjunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(disjunctionEClass, Disjunction.class, "Disjunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(equationEClass, Equation.class, "Equation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEquation_Left(), this.getParameter(), null, "left", null, 0, 1, Equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEquation_Right(), this.getParameter(), null, "right", null, 0, 1, Equation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(literalEClass, Literal.class, "Literal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLiteral_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, Literal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementEClass, Element.class, "Element", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getElement_Value(), ecorePackage.getEString(), "value", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElement_Type(), this.getElementType(), "type", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(elementEClass, ecorePackage.getEBoolean(), "isIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementEClass, ecorePackage.getEBoolean(), "isStringValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementEClass, ecorePackage.getEBoolean(), "isIntegerValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementEClass, ecorePackage.getEBoolean(), "isSourceMemory", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementEClass, ecorePackage.getEBoolean(), "isTargetMemory", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(elementEClass, ecorePackage.getEBoolean(), "isMemory", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(parameterEClass, ecorePackage.getEString(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(parameterEClass, null, "setValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(parameterEClass, this.getEquation(), "getEquation", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunction_Value(), ecorePackage.getEString(), "value", null, 0, 1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFunction_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFunction_Parameters(), this.getParameter(), null, "parameters", null, 0, -1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeEClass, Composite.class, "Composite", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComposite_Members(), this.getConstraint(), null, "members", null, 0, -1, Composite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(predicateEClass, Predicate.class, "Predicate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPredicate_Type(), this.getPredicateType(), "type", null, 0, 1, Predicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(predicateEClass, null, "negate", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(predicateEClass, null, "flip", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(elementTypeEEnum, ElementType.class, "ElementType");
		addEEnumLiteral(elementTypeEEnum, ElementType.IDENTIFIER);
		addEEnumLiteral(elementTypeEEnum, ElementType.STRING);
		addEEnumLiteral(elementTypeEEnum, ElementType.INTEGER);
		addEEnumLiteral(elementTypeEEnum, ElementType.SOURCE_MEMORY);
		addEEnumLiteral(elementTypeEEnum, ElementType.TARGET_MEMORY);
		addEEnumLiteral(elementTypeEEnum, ElementType.ARBITRARY);

		initEEnum(predicateTypeEEnum, PredicateType.class, "PredicateType");
		addEEnumLiteral(predicateTypeEEnum, PredicateType.EQUAL);
		addEEnumLiteral(predicateTypeEEnum, PredicateType.NOT_EQUAL);
		addEEnumLiteral(predicateTypeEEnum, PredicateType.GREATER);
		addEEnumLiteral(predicateTypeEEnum, PredicateType.GREATER_EQUAL);
		addEEnumLiteral(predicateTypeEEnum, PredicateType.LESS);
		addEEnumLiteral(predicateTypeEEnum, PredicateType.LESS_EQUAL);

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
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Constraint <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Constraint
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getConstraint()
		 * @generated
		 */
		public static final EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Conjunction <em>Conjunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Conjunction
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getConjunction()
		 * @generated
		 */
		public static final EClass CONJUNCTION = eINSTANCE.getConjunction();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Disjunction <em>Disjunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Disjunction
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getDisjunction()
		 * @generated
		 */
		public static final EClass DISJUNCTION = eINSTANCE.getDisjunction();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Equation <em>Equation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Equation
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getEquation()
		 * @generated
		 */
		public static final EClass EQUATION = eINSTANCE.getEquation();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference EQUATION__LEFT = eINSTANCE.getEquation_Left();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference EQUATION__RIGHT = eINSTANCE.getEquation_Right();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Literal <em>Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Literal
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getLiteral()
		 * @generated
		 */
		public static final EClass LITERAL = eINSTANCE.getLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute LITERAL__VALUE = eINSTANCE.getLiteral_Value();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Element <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Element
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getElement()
		 * @generated
		 */
		public static final EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ELEMENT__VALUE = eINSTANCE.getElement_Value();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute ELEMENT__TYPE = eINSTANCE.getElement_Type();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Parameter <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Parameter
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getParameter()
		 * @generated
		 */
		public static final EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Function <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Function
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getFunction()
		 * @generated
		 */
		public static final EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute FUNCTION__VALUE = eINSTANCE.getFunction_Value();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute FUNCTION__CLASS_NAME = eINSTANCE.getFunction_ClassName();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference FUNCTION__PARAMETERS = eINSTANCE.getFunction_Parameters();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Composite <em>Composite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Composite
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getComposite()
		 * @generated
		 */
		public static final EClass COMPOSITE = eINSTANCE.getComposite();

		/**
		 * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COMPOSITE__MEMBERS = eINSTANCE.getComposite_Members();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.Predicate <em>Predicate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.Predicate
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getPredicate()
		 * @generated
		 */
		public static final EClass PREDICATE = eINSTANCE.getPredicate();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PREDICATE__TYPE = eINSTANCE.getPredicate_Type();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.ElementType <em>Element Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.ElementType
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getElementType()
		 * @generated
		 */
		public static final EEnum ELEMENT_TYPE = eINSTANCE.getElementType();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.constraints.PredicateType <em>Predicate Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.constraints.PredicateType
		 * @see org.ect.ea.extensions.constraints.ConstraintsPackage#getPredicateType()
		 * @generated
		 */
		public static final EEnum PREDICATE_TYPE = eINSTANCE.getPredicateType();

	}

} //ConstraintsPackage
