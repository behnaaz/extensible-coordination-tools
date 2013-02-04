/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.automata;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;
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
 * @see org.ect.ea.automata.AutomataFactory
 * @model kind="package"
 * @generated
 */
public class AutomataPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "automata";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/automata";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "org.ect.ea.automata";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AutomataPackage eINSTANCE = org.ect.ea.automata.AutomataPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.automata.Automaton <em>Automaton</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.automata.Automaton
	 * @see org.ect.ea.automata.AutomataPackage#getAutomaton()
	 * @generated
	 */
	public static final int AUTOMATON = 0;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON__EXTENSIONS = ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON__STATES = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON__TRANSITIONS = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON__NAME = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Used Extension Ids</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON__USED_EXTENSION_IDS = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON__ID = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON__MODULE = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Automaton</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON_FEATURE_COUNT = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.ect.ea.automata.State <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.automata.State
	 * @see org.ect.ea.automata.AutomataPackage#getState()
	 * @generated
	 */
	public static final int STATE = 1;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE__EXTENSIONS = ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Automaton</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE__AUTOMATON = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE__INCOMING = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE__OUTGOING = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE__ID = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE__NAME = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STATE_FEATURE_COUNT = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.ect.ea.automata.Transition <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.automata.Transition
	 * @see org.ect.ea.automata.AutomataPackage#getTransition()
	 * @generated
	 */
	public static final int TRANSITION = 2;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION__EXTENSIONS = ExtensionsPackage.EXTENDIBLE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Automaton</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION__AUTOMATON = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION__SOURCE = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION__TARGET = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION__ID = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION_FEATURE_COUNT = ExtensionsPackage.EXTENDIBLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.ect.ea.automata.Module <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.automata.Module
	 * @see org.ect.ea.automata.AutomataPackage#getModule()
	 * @generated
	 */
	public static final int MODULE = 3;

	/**
	 * The feature id for the '<em><b>Automata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE__AUTOMATA = 0;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE_FEATURE_COUNT = 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass automatonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleEClass = null;

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
	 * @see org.ect.ea.automata.AutomataPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AutomataPackage() {
		super(eNS_URI, AutomataFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AutomataPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AutomataPackage init() {
		if (isInited) return (AutomataPackage)EPackage.Registry.INSTANCE.getEPackage(AutomataPackage.eNS_URI);

		// Obtain or create and register package
		AutomataPackage theAutomataPackage = (AutomataPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AutomataPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AutomataPackage());

		isInited = true;

		// Obtain or create and register interdependencies
		ExtensionsPackage theExtensionsPackage = (ExtensionsPackage)(EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI) instanceof ExtensionsPackage ? EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI) : ExtensionsPackage.eINSTANCE);

		// Create package meta-data objects
		theAutomataPackage.createPackageContents();
		theExtensionsPackage.createPackageContents();

		// Initialize created meta-data
		theAutomataPackage.initializePackageContents();
		theExtensionsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAutomataPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AutomataPackage.eNS_URI, theAutomataPackage);
		return theAutomataPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.automata.Automaton <em>Automaton</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Automaton</em>'.
	 * @see org.ect.ea.automata.Automaton
	 * @generated
	 */
	public EClass getAutomaton() {
		return automatonEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.automata.Automaton#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see org.ect.ea.automata.Automaton#getStates()
	 * @see #getAutomaton()
	 * @generated
	 */
	public EReference getAutomaton_States() {
		return (EReference)automatonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.automata.Automaton#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitions</em>'.
	 * @see org.ect.ea.automata.Automaton#getTransitions()
	 * @see #getAutomaton()
	 * @generated
	 */
	public EReference getAutomaton_Transitions() {
		return (EReference)automatonEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.automata.Automaton#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ect.ea.automata.Automaton#getName()
	 * @see #getAutomaton()
	 * @generated
	 */
	public EAttribute getAutomaton_Name() {
		return (EAttribute)automatonEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.automata.Automaton#getUsedExtensionIds <em>Used Extension Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Used Extension Ids</em>'.
	 * @see org.ect.ea.automata.Automaton#getUsedExtensionIds()
	 * @see #getAutomaton()
	 * @generated
	 */
	public EAttribute getAutomaton_UsedExtensionIds() {
		return (EAttribute)automatonEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.automata.Automaton#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ect.ea.automata.Automaton#getId()
	 * @see #getAutomaton()
	 * @generated
	 */
	public EAttribute getAutomaton_Id() {
		return (EAttribute)automatonEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * Returns the meta object for the container reference '{@link org.ect.ea.automata.Automaton#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see org.ect.ea.automata.Automaton#getModule()
	 * @see #getAutomaton()
	 * @generated
	 */
	public EReference getAutomaton_Module() {
		return (EReference)automatonEClass.getEStructuralFeatures().get(5);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.automata.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.ect.ea.automata.State
	 * @generated
	 */
	public EClass getState() {
		return stateEClass;
	}

	/**
	 * Returns the meta object for the container reference '{@link org.ect.ea.automata.State#getAutomaton <em>Automaton</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Automaton</em>'.
	 * @see org.ect.ea.automata.State#getAutomaton()
	 * @see #getState()
	 * @generated
	 */
	public EReference getState_Automaton() {
		return (EReference)stateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference list '{@link org.ect.ea.automata.State#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming</em>'.
	 * @see org.ect.ea.automata.State#getIncoming()
	 * @see #getState()
	 * @generated
	 */
	public EReference getState_Incoming() {
		return (EReference)stateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the reference list '{@link org.ect.ea.automata.State#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing</em>'.
	 * @see org.ect.ea.automata.State#getOutgoing()
	 * @see #getState()
	 * @generated
	 */
	public EReference getState_Outgoing() {
		return (EReference)stateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.automata.State#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ect.ea.automata.State#getId()
	 * @see #getState()
	 * @generated
	 */
	public EAttribute getState_Id() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.automata.State#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ect.ea.automata.State#getName()
	 * @see #getState()
	 * @generated
	 */
	public EAttribute getState_Name() {
		return (EAttribute)stateEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.automata.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see org.ect.ea.automata.Transition
	 * @generated
	 */
	public EClass getTransition() {
		return transitionEClass;
	}

	/**
	 * Returns the meta object for the container reference '{@link org.ect.ea.automata.Transition#getAutomaton <em>Automaton</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Automaton</em>'.
	 * @see org.ect.ea.automata.Transition#getAutomaton()
	 * @see #getTransition()
	 * @generated
	 */
	public EReference getTransition_Automaton() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference '{@link org.ect.ea.automata.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.ect.ea.automata.Transition#getSource()
	 * @see #getTransition()
	 * @generated
	 */
	public EReference getTransition_Source() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the reference '{@link org.ect.ea.automata.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.ect.ea.automata.Transition#getTarget()
	 * @see #getTransition()
	 * @generated
	 */
	public EReference getTransition_Target() {
		return (EReference)transitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.automata.Transition#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ect.ea.automata.Transition#getId()
	 * @see #getTransition()
	 * @generated
	 */
	public EAttribute getTransition_Id() {
		return (EAttribute)transitionEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.automata.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see org.ect.ea.automata.Module
	 * @generated
	 */
	public EClass getModule() {
		return moduleEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.automata.Module#getAutomata <em>Automata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Automata</em>'.
	 * @see org.ect.ea.automata.Module#getAutomata()
	 * @see #getModule()
	 * @generated
	 */
	public EReference getModule_Automata() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public AutomataFactory getAutomataFactory() {
		return (AutomataFactory)getEFactoryInstance();
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
		automatonEClass = createEClass(AUTOMATON);
		createEReference(automatonEClass, AUTOMATON__STATES);
		createEReference(automatonEClass, AUTOMATON__TRANSITIONS);
		createEAttribute(automatonEClass, AUTOMATON__NAME);
		createEAttribute(automatonEClass, AUTOMATON__USED_EXTENSION_IDS);
		createEAttribute(automatonEClass, AUTOMATON__ID);
		createEReference(automatonEClass, AUTOMATON__MODULE);

		stateEClass = createEClass(STATE);
		createEReference(stateEClass, STATE__AUTOMATON);
		createEReference(stateEClass, STATE__INCOMING);
		createEReference(stateEClass, STATE__OUTGOING);
		createEAttribute(stateEClass, STATE__ID);
		createEAttribute(stateEClass, STATE__NAME);

		transitionEClass = createEClass(TRANSITION);
		createEReference(transitionEClass, TRANSITION__AUTOMATON);
		createEReference(transitionEClass, TRANSITION__SOURCE);
		createEReference(transitionEClass, TRANSITION__TARGET);
		createEAttribute(transitionEClass, TRANSITION__ID);

		moduleEClass = createEClass(MODULE);
		createEReference(moduleEClass, MODULE__AUTOMATA);
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
		automatonEClass.getESuperTypes().add(theExtensionsPackage.getExtendibleElement());
		stateEClass.getESuperTypes().add(theExtensionsPackage.getExtendibleElement());
		transitionEClass.getESuperTypes().add(theExtensionsPackage.getExtendibleElement());

		// Initialize classes and features; add operations and parameters
		initEClass(automatonEClass, Automaton.class, "Automaton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAutomaton_States(), this.getState(), this.getState_Automaton(), "states", null, 0, -1, Automaton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAutomaton_Transitions(), this.getTransition(), this.getTransition_Automaton(), "transitions", null, 0, -1, Automaton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAutomaton_Name(), ecorePackage.getEString(), "name", null, 0, 1, Automaton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAutomaton_UsedExtensionIds(), ecorePackage.getEString(), "usedExtensionIds", null, 0, -1, Automaton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAutomaton_Id(), ecorePackage.getEString(), "id", null, 0, 1, Automaton.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAutomaton_Module(), this.getModule(), this.getModule_Automata(), "module", null, 0, 1, Automaton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getState_Automaton(), this.getAutomaton(), this.getAutomaton_States(), "automaton", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_Incoming(), this.getTransition(), this.getTransition_Target(), "incoming", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getState_Outgoing(), this.getTransition(), this.getTransition_Source(), "outgoing", null, 0, -1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Id(), ecorePackage.getEString(), "id", null, 0, 1, State.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getState_Name(), ecorePackage.getEString(), "name", null, 0, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionEClass, Transition.class, "Transition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransition_Automaton(), this.getAutomaton(), this.getAutomaton_Transitions(), "automaton", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Source(), this.getState(), this.getState_Outgoing(), "source", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransition_Target(), this.getState(), this.getState_Incoming(), "target", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransition_Id(), ecorePackage.getEString(), "id", null, 0, 1, Transition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModule_Automata(), this.getAutomaton(), this.getAutomaton_Module(), "automata", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		 * The meta object literal for the '{@link org.ect.ea.automata.Automaton <em>Automaton</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.automata.Automaton
		 * @see org.ect.ea.automata.AutomataPackage#getAutomaton()
		 * @generated
		 */
		public static final EClass AUTOMATON = eINSTANCE.getAutomaton();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference AUTOMATON__STATES = eINSTANCE.getAutomaton_States();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference AUTOMATON__TRANSITIONS = eINSTANCE.getAutomaton_Transitions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute AUTOMATON__NAME = eINSTANCE.getAutomaton_Name();

		/**
		 * The meta object literal for the '<em><b>Used Extension Ids</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute AUTOMATON__USED_EXTENSION_IDS = eINSTANCE.getAutomaton_UsedExtensionIds();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute AUTOMATON__ID = eINSTANCE.getAutomaton_Id();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference AUTOMATON__MODULE = eINSTANCE.getAutomaton_Module();

		/**
		 * The meta object literal for the '{@link org.ect.ea.automata.State <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.automata.State
		 * @see org.ect.ea.automata.AutomataPackage#getState()
		 * @generated
		 */
		public static final EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Automaton</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference STATE__AUTOMATON = eINSTANCE.getState_Automaton();

		/**
		 * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference STATE__INCOMING = eINSTANCE.getState_Incoming();

		/**
		 * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference STATE__OUTGOING = eINSTANCE.getState_Outgoing();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STATE__ID = eINSTANCE.getState_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STATE__NAME = eINSTANCE.getState_Name();

		/**
		 * The meta object literal for the '{@link org.ect.ea.automata.Transition <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.automata.Transition
		 * @see org.ect.ea.automata.AutomataPackage#getTransition()
		 * @generated
		 */
		public static final EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Automaton</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TRANSITION__AUTOMATON = eINSTANCE.getTransition_Automaton();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TRANSITION__SOURCE = eINSTANCE.getTransition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TRANSITION__TARGET = eINSTANCE.getTransition_Target();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute TRANSITION__ID = eINSTANCE.getTransition_Id();

		/**
		 * The meta object literal for the '{@link org.ect.ea.automata.Module <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.automata.Module
		 * @see org.ect.ea.automata.AutomataPackage#getModule()
		 * @generated
		 */
		public static final EClass MODULE = eINSTANCE.getModule();

		/**
		 * The meta object literal for the '<em><b>Automata</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference MODULE__AUTOMATA = eINSTANCE.getModule_Automata();

	}

} //AutomataPackage
