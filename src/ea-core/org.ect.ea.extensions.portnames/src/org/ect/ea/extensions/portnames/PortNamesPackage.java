/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.extensions.portnames;

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
 * @see org.ect.ea.extensions.portnames.PortNamesFactory
 * @model kind="package"
 * @generated
 */
public class PortNamesPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "portNames";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/portNames";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "portNames";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final PortNamesPackage eINSTANCE = org.ect.ea.extensions.portnames.PortNamesPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.portnames.AutomatonPortNames <em>Automaton Port Names</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.portnames.AutomatonPortNames
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getAutomatonPortNames()
	 * @generated
	 */
	public static final int AUTOMATON_PORT_NAMES = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON_PORT_NAMES__OWNER = ExtensionsPackage.STRING_LIST_EXTENSION__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON_PORT_NAMES__ID = ExtensionsPackage.STRING_LIST_EXTENSION__ID;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON_PORT_NAMES__VALUES = ExtensionsPackage.STRING_LIST_EXTENSION__VALUES;

	/**
	 * The feature id for the '<em><b>In Ports</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON_PORT_NAMES__IN_PORTS = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out Ports</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON_PORT_NAMES__OUT_PORTS = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Automaton Port Names</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AUTOMATON_PORT_NAMES_FEATURE_COUNT = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.portnames.TransitionPortNames <em>Transition Port Names</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.portnames.TransitionPortNames
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getTransitionPortNames()
	 * @generated
	 */
	public static final int TRANSITION_PORT_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION_PORT_NAMES__OWNER = ExtensionsPackage.STRING_LIST_EXTENSION__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION_PORT_NAMES__ID = ExtensionsPackage.STRING_LIST_EXTENSION__ID;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION_PORT_NAMES__VALUES = ExtensionsPackage.STRING_LIST_EXTENSION__VALUES;

	/**
	 * The number of structural features of the '<em>Transition Port Names</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TRANSITION_PORT_NAMES_FEATURE_COUNT = ExtensionsPackage.STRING_LIST_EXTENSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.portnames.IntentionalPortNames <em>Intensional Port Names</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.portnames.IntentionalPortNames
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getIntensionalPortNames()
	 * @generated
	 */
	public static final int INTENSIONAL_PORT_NAMES = 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTENSIONAL_PORT_NAMES__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTENSIONAL_PORT_NAMES__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Requests</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTENSIONAL_PORT_NAMES__REQUESTS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Firings</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTENSIONAL_PORT_NAMES__FIRINGS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Intensional Port Names</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTENSIONAL_PORT_NAMES_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.portnames.DelayElement <em>Delay Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.portnames.DelayElement
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement()
	 * @generated
	 */
	public static final int DELAY_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Input</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_ELEMENT__INPUT = 0;

	/**
	 * The feature id for the '<em><b>Output</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_ELEMENT__OUTPUT = 1;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_ELEMENT__DELAY = 2;

	/**
	 * The feature id for the '<em><b>Rewards</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_ELEMENT__REWARDS = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_ELEMENT__NAME = 4;

	/**
	 * The number of structural features of the '<em>Delay Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_ELEMENT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.portnames.DelayInformation <em>Delay Information</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.portnames.DelayInformation
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayInformation()
	 * @generated
	 */
	public static final int DELAY_INFORMATION = 4;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_INFORMATION__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_INFORMATION__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_INFORMATION__ELEMENTS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Delay Information</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_INFORMATION_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass automatonPortNamesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionPortNamesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intensionalPortNamesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass delayElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass delayInformationEClass = null;

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
	 * @see org.ect.ea.extensions.portnames.PortNamesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PortNamesPackage() {
		super(eNS_URI, PortNamesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PortNamesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PortNamesPackage init() {
		if (isInited) return (PortNamesPackage)EPackage.Registry.INSTANCE.getEPackage(PortNamesPackage.eNS_URI);

		// Obtain or create and register package
		PortNamesPackage thePortNamesPackage = (PortNamesPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PortNamesPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PortNamesPackage());

		isInited = true;

		// Initialize simple dependencies
		AutomataPackage.eINSTANCE.eClass();
		ExtensionsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePortNamesPackage.createPackageContents();

		// Initialize created meta-data
		thePortNamesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePortNamesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PortNamesPackage.eNS_URI, thePortNamesPackage);
		return thePortNamesPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.portnames.AutomatonPortNames <em>Automaton Port Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Automaton Port Names</em>'.
	 * @see org.ect.ea.extensions.portnames.AutomatonPortNames
	 * @generated
	 */
	public EClass getAutomatonPortNames() {
		return automatonPortNamesEClass;
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.portnames.AutomatonPortNames#getInPorts <em>In Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>In Ports</em>'.
	 * @see org.ect.ea.extensions.portnames.AutomatonPortNames#getInPorts()
	 * @see #getAutomatonPortNames()
	 * @generated
	 */
	public EAttribute getAutomatonPortNames_InPorts() {
		return (EAttribute)automatonPortNamesEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.portnames.AutomatonPortNames#getOutPorts <em>Out Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Out Ports</em>'.
	 * @see org.ect.ea.extensions.portnames.AutomatonPortNames#getOutPorts()
	 * @see #getAutomatonPortNames()
	 * @generated
	 */
	public EAttribute getAutomatonPortNames_OutPorts() {
		return (EAttribute)automatonPortNamesEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.portnames.TransitionPortNames <em>Transition Port Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Port Names</em>'.
	 * @see org.ect.ea.extensions.portnames.TransitionPortNames
	 * @generated
	 */
	public EClass getTransitionPortNames() {
		return transitionPortNamesEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.portnames.IntentionalPortNames <em>Intensional Port Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intensional Port Names</em>'.
	 * @see org.ect.ea.extensions.portnames.IntentionalPortNames
	 * @generated
	 */
	public EClass getIntensionalPortNames() {
		return intensionalPortNamesEClass;
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.portnames.IntentionalPortNames#getRequests <em>Requests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Requests</em>'.
	 * @see org.ect.ea.extensions.portnames.IntentionalPortNames#getRequests()
	 * @see #getIntensionalPortNames()
	 * @generated
	 */
	public EAttribute getIntensionalPortNames_Requests() {
		return (EAttribute)intensionalPortNamesEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.portnames.IntentionalPortNames#getFirings <em>Firings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Firings</em>'.
	 * @see org.ect.ea.extensions.portnames.IntentionalPortNames#getFirings()
	 * @see #getIntensionalPortNames()
	 * @generated
	 */
	public EAttribute getIntensionalPortNames_Firings() {
		return (EAttribute)intensionalPortNamesEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.portnames.DelayElement <em>Delay Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delay Element</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayElement
	 * @generated
	 */
	public EClass getDelayElement() {
		return delayElementEClass;
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.portnames.DelayElement#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Input</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayElement#getInput()
	 * @see #getDelayElement()
	 * @generated
	 */
	public EAttribute getDelayElement_Input() {
		return (EAttribute)delayElementEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.portnames.DelayElement#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Output</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayElement#getOutput()
	 * @see #getDelayElement()
	 * @generated
	 */
	public EAttribute getDelayElement_Output() {
		return (EAttribute)delayElementEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.portnames.DelayElement#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayElement#getDelay()
	 * @see #getDelayElement()
	 * @generated
	 */
	public EAttribute getDelayElement_Delay() {
		return (EAttribute)delayElementEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.portnames.DelayElement#getRewards <em>Rewards</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Rewards</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayElement#getRewards()
	 * @see #getDelayElement()
	 * @generated
	 */
	public EAttribute getDelayElement_Rewards() {
		return (EAttribute)delayElementEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.portnames.DelayElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayElement#getName()
	 * @see #getDelayElement()
	 * @generated
	 */
	public EAttribute getDelayElement_Name() {
		return (EAttribute)delayElementEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.portnames.DelayInformation <em>Delay Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delay Information</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayInformation
	 * @generated
	 */
	public EClass getDelayInformation() {
		return delayInformationEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.extensions.portnames.DelayInformation#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.ect.ea.extensions.portnames.DelayInformation#getElements()
	 * @see #getDelayInformation()
	 * @generated
	 */
	public EReference getDelayInformation_Elements() {
		return (EReference)delayInformationEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public PortNamesFactory getPortNamesFactory() {
		return (PortNamesFactory)getEFactoryInstance();
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
		automatonPortNamesEClass = createEClass(AUTOMATON_PORT_NAMES);
		createEAttribute(automatonPortNamesEClass, AUTOMATON_PORT_NAMES__IN_PORTS);
		createEAttribute(automatonPortNamesEClass, AUTOMATON_PORT_NAMES__OUT_PORTS);

		transitionPortNamesEClass = createEClass(TRANSITION_PORT_NAMES);

		intensionalPortNamesEClass = createEClass(INTENSIONAL_PORT_NAMES);
		createEAttribute(intensionalPortNamesEClass, INTENSIONAL_PORT_NAMES__REQUESTS);
		createEAttribute(intensionalPortNamesEClass, INTENSIONAL_PORT_NAMES__FIRINGS);

		delayElementEClass = createEClass(DELAY_ELEMENT);
		createEAttribute(delayElementEClass, DELAY_ELEMENT__INPUT);
		createEAttribute(delayElementEClass, DELAY_ELEMENT__OUTPUT);
		createEAttribute(delayElementEClass, DELAY_ELEMENT__DELAY);
		createEAttribute(delayElementEClass, DELAY_ELEMENT__REWARDS);
		createEAttribute(delayElementEClass, DELAY_ELEMENT__NAME);

		delayInformationEClass = createEClass(DELAY_INFORMATION);
		createEReference(delayInformationEClass, DELAY_INFORMATION__ELEMENTS);
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
		automatonPortNamesEClass.getESuperTypes().add(theExtensionsPackage.getStringListExtension());
		transitionPortNamesEClass.getESuperTypes().add(theExtensionsPackage.getStringListExtension());
		intensionalPortNamesEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());
		delayInformationEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());

		// Initialize classes and features; add operations and parameters
		initEClass(automatonPortNamesEClass, AutomatonPortNames.class, "AutomatonPortNames", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAutomatonPortNames_InPorts(), ecorePackage.getEString(), "inPorts", null, 0, -1, AutomatonPortNames.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAutomatonPortNames_OutPorts(), ecorePackage.getEString(), "outPorts", null, 0, -1, AutomatonPortNames.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transitionPortNamesEClass, TransitionPortNames.class, "TransitionPortNames", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(intensionalPortNamesEClass, IntentionalPortNames.class, "IntensionalPortNames", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntensionalPortNames_Requests(), ecorePackage.getEString(), "requests", null, 0, -1, IntentionalPortNames.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntensionalPortNames_Firings(), ecorePackage.getEString(), "firings", null, 0, -1, IntentionalPortNames.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(delayElementEClass, DelayElement.class, "DelayElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDelayElement_Input(), ecorePackage.getEString(), "input", null, 0, -1, DelayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelayElement_Output(), ecorePackage.getEString(), "output", null, 0, -1, DelayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelayElement_Delay(), ecorePackage.getEDouble(), "delay", null, 0, 1, DelayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelayElement_Rewards(), ecorePackage.getEString(), "rewards", null, 0, -1, DelayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelayElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, DelayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(delayInformationEClass, DelayInformation.class, "DelayInformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDelayInformation_Elements(), this.getDelayElement(), null, "elements", null, 0, -1, DelayInformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		 * The meta object literal for the '{@link org.ect.ea.extensions.portnames.AutomatonPortNames <em>Automaton Port Names</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.portnames.AutomatonPortNames
		 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getAutomatonPortNames()
		 * @generated
		 */
		public static final EClass AUTOMATON_PORT_NAMES = eINSTANCE.getAutomatonPortNames();

		/**
		 * The meta object literal for the '<em><b>In Ports</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute AUTOMATON_PORT_NAMES__IN_PORTS = eINSTANCE.getAutomatonPortNames_InPorts();

		/**
		 * The meta object literal for the '<em><b>Out Ports</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute AUTOMATON_PORT_NAMES__OUT_PORTS = eINSTANCE.getAutomatonPortNames_OutPorts();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.portnames.TransitionPortNames <em>Transition Port Names</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.portnames.TransitionPortNames
		 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getTransitionPortNames()
		 * @generated
		 */
		public static final EClass TRANSITION_PORT_NAMES = eINSTANCE.getTransitionPortNames();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.portnames.IntentionalPortNames <em>Intensional Port Names</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.portnames.IntentionalPortNames
		 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getIntensionalPortNames()
		 * @generated
		 */
		public static final EClass INTENSIONAL_PORT_NAMES = eINSTANCE.getIntensionalPortNames();

		/**
		 * The meta object literal for the '<em><b>Requests</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute INTENSIONAL_PORT_NAMES__REQUESTS = eINSTANCE.getIntensionalPortNames_Requests();

		/**
		 * The meta object literal for the '<em><b>Firings</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute INTENSIONAL_PORT_NAMES__FIRINGS = eINSTANCE.getIntensionalPortNames_Firings();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.portnames.DelayElement <em>Delay Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.portnames.DelayElement
		 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayElement()
		 * @generated
		 */
		public static final EClass DELAY_ELEMENT = eINSTANCE.getDelayElement();

		/**
		 * The meta object literal for the '<em><b>Input</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DELAY_ELEMENT__INPUT = eINSTANCE.getDelayElement_Input();

		/**
		 * The meta object literal for the '<em><b>Output</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DELAY_ELEMENT__OUTPUT = eINSTANCE.getDelayElement_Output();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DELAY_ELEMENT__DELAY = eINSTANCE.getDelayElement_Delay();

		/**
		 * The meta object literal for the '<em><b>Rewards</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DELAY_ELEMENT__REWARDS = eINSTANCE.getDelayElement_Rewards();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DELAY_ELEMENT__NAME = eINSTANCE.getDelayElement_Name();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.portnames.DelayInformation <em>Delay Information</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.portnames.DelayInformation
		 * @see org.ect.ea.extensions.portnames.PortNamesPackage#getDelayInformation()
		 * @generated
		 */
		public static final EClass DELAY_INFORMATION = eINSTANCE.getDelayInformation();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference DELAY_INFORMATION__ELEMENTS = eINSTANCE.getDelayInformation_Elements();

	}

} //PortNamesPackage
