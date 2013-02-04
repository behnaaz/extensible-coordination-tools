/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.extensions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.ea.automata.AutomataPackage;


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
 * @see org.ect.ea.extensions.ExtensionsFactory
 * @model kind="package"
 * @generated
 */
public class ExtensionsPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "extensions";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/extensions";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "org.ect.ea.extensions";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ExtensionsPackage eINSTANCE = org.ect.ea.extensions.ExtensionsPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.IExtension <em>IExtension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.IExtension
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtension()
	 * @generated
	 */
	public static final int IEXTENSION = 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.IExtendible <em>IExtendible</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.IExtendible
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtendible()
	 * @generated
	 */
	public static final int IEXTENDIBLE = 0;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IEXTENDIBLE__EXTENSIONS = 0;

	/**
	 * The number of structural features of the '<em>IExtendible</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IEXTENDIBLE_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IEXTENSION__OWNER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IEXTENSION__ID = 1;

	/**
	 * The number of structural features of the '<em>IExtension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IEXTENSION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.ExtendibleElement <em>Extendible Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.ExtendibleElement
	 * @see org.ect.ea.extensions.ExtensionsPackage#getExtendibleElement()
	 * @generated
	 */
	public static final int EXTENDIBLE_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXTENDIBLE_ELEMENT__EXTENSIONS = IEXTENDIBLE__EXTENSIONS;

	/**
	 * The number of structural features of the '<em>Extendible Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXTENDIBLE_ELEMENT_FEATURE_COUNT = IEXTENDIBLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.ExtensionElement <em>Extension Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.ExtensionElement
	 * @see org.ect.ea.extensions.ExtensionsPackage#getExtensionElement()
	 * @generated
	 */
	public static final int EXTENSION_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXTENSION_ELEMENT__OWNER = IEXTENSION__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXTENSION_ELEMENT__ID = IEXTENSION__ID;

	/**
	 * The number of structural features of the '<em>Extension Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXTENSION_ELEMENT_FEATURE_COUNT = IEXTENSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.IntegerExtension <em>Integer Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.IntegerExtension
	 * @see org.ect.ea.extensions.ExtensionsPackage#getIntegerExtension()
	 * @generated
	 */
	public static final int INTEGER_EXTENSION = 4;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_EXTENSION__OWNER = EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_EXTENSION__ID = EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_EXTENSION__VALUE = EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_EXTENSION_FEATURE_COUNT = EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.StringExtension <em>String Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.StringExtension
	 * @see org.ect.ea.extensions.ExtensionsPackage#getStringExtension()
	 * @generated
	 */
	public static final int STRING_EXTENSION = 5;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_EXTENSION__OWNER = EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_EXTENSION__ID = EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_EXTENSION__VALUE = EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_EXTENSION_FEATURE_COUNT = EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.StringListExtension <em>String List Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.StringListExtension
	 * @see org.ect.ea.extensions.ExtensionsPackage#getStringListExtension()
	 * @generated
	 */
	public static final int STRING_LIST_EXTENSION = 6;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LIST_EXTENSION__OWNER = EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LIST_EXTENSION__ID = EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LIST_EXTENSION__VALUES = EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String List Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LIST_EXTENSION_FEATURE_COUNT = EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.BooleanExtension <em>Boolean Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.BooleanExtension
	 * @see org.ect.ea.extensions.ExtensionsPackage#getBooleanExtension()
	 * @generated
	 */
	public static final int BOOLEAN_EXTENSION = 7;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_EXTENSION__OWNER = EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_EXTENSION__ID = EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_EXTENSION__VALUE = EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_EXTENSION_FEATURE_COUNT = EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iExtendibleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendibleElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extensionElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringListExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanExtensionEClass = null;

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
	 * @see org.ect.ea.extensions.ExtensionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExtensionsPackage() {
		super(eNS_URI, ExtensionsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ExtensionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExtensionsPackage init() {
		if (isInited) return (ExtensionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExtensionsPackage.eNS_URI);

		// Obtain or create and register package
		ExtensionsPackage theExtensionsPackage = (ExtensionsPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExtensionsPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExtensionsPackage());

		isInited = true;

		// Obtain or create and register interdependencies
		AutomataPackage theAutomataPackage = (AutomataPackage)(EPackage.Registry.INSTANCE.getEPackage(AutomataPackage.eNS_URI) instanceof AutomataPackage ? EPackage.Registry.INSTANCE.getEPackage(AutomataPackage.eNS_URI) : AutomataPackage.eINSTANCE);

		// Create package meta-data objects
		theExtensionsPackage.createPackageContents();
		theAutomataPackage.createPackageContents();

		// Initialize created meta-data
		theExtensionsPackage.initializePackageContents();
		theAutomataPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExtensionsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExtensionsPackage.eNS_URI, theExtensionsPackage);
		return theExtensionsPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.IExtendible <em>IExtendible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IExtendible</em>'.
	 * @see org.ect.ea.extensions.IExtendible
	 * @generated
	 */
	public EClass getIExtendible() {
		return iExtendibleEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.extensions.IExtendible#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extensions</em>'.
	 * @see org.ect.ea.extensions.IExtendible#getExtensions()
	 * @see #getIExtendible()
	 * @generated
	 */
	public EReference getIExtendible_Extensions() {
		return (EReference)iExtendibleEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.IExtension <em>IExtension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IExtension</em>'.
	 * @see org.ect.ea.extensions.IExtension
	 * @generated
	 */
	public EClass getIExtension() {
		return iExtensionEClass;
	}

	/**
	 * Returns the meta object for the container reference '{@link org.ect.ea.extensions.IExtension#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owner</em>'.
	 * @see org.ect.ea.extensions.IExtension#getOwner()
	 * @see #getIExtension()
	 * @generated
	 */
	public EReference getIExtension_Owner() {
		return (EReference)iExtensionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.IExtension#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.ect.ea.extensions.IExtension#getId()
	 * @see #getIExtension()
	 * @generated
	 */
	public EAttribute getIExtension_Id() {
		return (EAttribute)iExtensionEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.ExtendibleElement <em>Extendible Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extendible Element</em>'.
	 * @see org.ect.ea.extensions.ExtendibleElement
	 * @generated
	 */
	public EClass getExtendibleElement() {
		return extendibleElementEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.ExtensionElement <em>Extension Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension Element</em>'.
	 * @see org.ect.ea.extensions.ExtensionElement
	 * @generated
	 */
	public EClass getExtensionElement() {
		return extensionElementEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.IntegerExtension <em>Integer Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Extension</em>'.
	 * @see org.ect.ea.extensions.IntegerExtension
	 * @generated
	 */
	public EClass getIntegerExtension() {
		return integerExtensionEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.IntegerExtension#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.ea.extensions.IntegerExtension#getValue()
	 * @see #getIntegerExtension()
	 * @generated
	 */
	public EAttribute getIntegerExtension_Value() {
		return (EAttribute)integerExtensionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.StringExtension <em>String Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Extension</em>'.
	 * @see org.ect.ea.extensions.StringExtension
	 * @generated
	 */
	public EClass getStringExtension() {
		return stringExtensionEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.StringExtension#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.ea.extensions.StringExtension#getValue()
	 * @see #getStringExtension()
	 * @generated
	 */
	public EAttribute getStringExtension_Value() {
		return (EAttribute)stringExtensionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.StringListExtension <em>String List Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String List Extension</em>'.
	 * @see org.ect.ea.extensions.StringListExtension
	 * @generated
	 */
	public EClass getStringListExtension() {
		return stringListExtensionEClass;
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.ea.extensions.StringListExtension#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see org.ect.ea.extensions.StringListExtension#getValues()
	 * @see #getStringListExtension()
	 * @generated
	 */
	public EAttribute getStringListExtension_Values() {
		return (EAttribute)stringListExtensionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.BooleanExtension <em>Boolean Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Extension</em>'.
	 * @see org.ect.ea.extensions.BooleanExtension
	 * @generated
	 */
	public EClass getBooleanExtension() {
		return booleanExtensionEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.extensions.BooleanExtension#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.ea.extensions.BooleanExtension#isValue()
	 * @see #getBooleanExtension()
	 * @generated
	 */
	public EAttribute getBooleanExtension_Value() {
		return (EAttribute)booleanExtensionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ExtensionsFactory getExtensionsFactory() {
		return (ExtensionsFactory)getEFactoryInstance();
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
		iExtendibleEClass = createEClass(IEXTENDIBLE);
		createEReference(iExtendibleEClass, IEXTENDIBLE__EXTENSIONS);

		iExtensionEClass = createEClass(IEXTENSION);
		createEReference(iExtensionEClass, IEXTENSION__OWNER);
		createEAttribute(iExtensionEClass, IEXTENSION__ID);

		extendibleElementEClass = createEClass(EXTENDIBLE_ELEMENT);

		extensionElementEClass = createEClass(EXTENSION_ELEMENT);

		integerExtensionEClass = createEClass(INTEGER_EXTENSION);
		createEAttribute(integerExtensionEClass, INTEGER_EXTENSION__VALUE);

		stringExtensionEClass = createEClass(STRING_EXTENSION);
		createEAttribute(stringExtensionEClass, STRING_EXTENSION__VALUE);

		stringListExtensionEClass = createEClass(STRING_LIST_EXTENSION);
		createEAttribute(stringListExtensionEClass, STRING_LIST_EXTENSION__VALUES);

		booleanExtensionEClass = createEClass(BOOLEAN_EXTENSION);
		createEAttribute(booleanExtensionEClass, BOOLEAN_EXTENSION__VALUE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		extendibleElementEClass.getESuperTypes().add(this.getIExtendible());
		extensionElementEClass.getESuperTypes().add(this.getIExtension());
		integerExtensionEClass.getESuperTypes().add(this.getExtensionElement());
		stringExtensionEClass.getESuperTypes().add(this.getExtensionElement());
		stringListExtensionEClass.getESuperTypes().add(this.getExtensionElement());
		booleanExtensionEClass.getESuperTypes().add(this.getExtensionElement());

		// Initialize classes and features; add operations and parameters
		initEClass(iExtendibleEClass, IExtendible.class, "IExtendible", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIExtendible_Extensions(), this.getIExtension(), this.getIExtension_Owner(), "extensions", null, 0, -1, IExtendible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(iExtendibleEClass, this.getIExtension(), "findExtension", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iExtendibleEClass, null, "updateExtension", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIExtension(), "extension", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iExtensionEClass, IExtension.class, "IExtension", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIExtension_Owner(), this.getIExtendible(), this.getIExtendible_Extensions(), "owner", null, 0, 1, IExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIExtension_Id(), ecorePackage.getEString(), "id", null, 0, 1, IExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(extendibleElementEClass, ExtendibleElement.class, "ExtendibleElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(extensionElementEClass, ExtensionElement.class, "ExtensionElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(integerExtensionEClass, IntegerExtension.class, "IntegerExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerExtension_Value(), ecorePackage.getEInt(), "value", null, 0, 1, IntegerExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringExtensionEClass, StringExtension.class, "StringExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringExtension_Value(), ecorePackage.getEString(), "value", null, 0, 1, StringExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringListExtensionEClass, StringListExtension.class, "StringListExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringListExtension_Values(), ecorePackage.getEString(), "values", null, 0, -1, StringListExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanExtensionEClass, BooleanExtension.class, "BooleanExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanExtension_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, BooleanExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		 * The meta object literal for the '{@link org.ect.ea.extensions.IExtendible <em>IExtendible</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.IExtendible
		 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtendible()
		 * @generated
		 */
		public static final EClass IEXTENDIBLE = eINSTANCE.getIExtendible();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference IEXTENDIBLE__EXTENSIONS = eINSTANCE.getIExtendible_Extensions();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.IExtension <em>IExtension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.IExtension
		 * @see org.ect.ea.extensions.ExtensionsPackage#getIExtension()
		 * @generated
		 */
		public static final EClass IEXTENSION = eINSTANCE.getIExtension();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference IEXTENSION__OWNER = eINSTANCE.getIExtension_Owner();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute IEXTENSION__ID = eINSTANCE.getIExtension_Id();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.ExtendibleElement <em>Extendible Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.ExtendibleElement
		 * @see org.ect.ea.extensions.ExtensionsPackage#getExtendibleElement()
		 * @generated
		 */
		public static final EClass EXTENDIBLE_ELEMENT = eINSTANCE.getExtendibleElement();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.ExtensionElement <em>Extension Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.ExtensionElement
		 * @see org.ect.ea.extensions.ExtensionsPackage#getExtensionElement()
		 * @generated
		 */
		public static final EClass EXTENSION_ELEMENT = eINSTANCE.getExtensionElement();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.IntegerExtension <em>Integer Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.IntegerExtension
		 * @see org.ect.ea.extensions.ExtensionsPackage#getIntegerExtension()
		 * @generated
		 */
		public static final EClass INTEGER_EXTENSION = eINSTANCE.getIntegerExtension();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute INTEGER_EXTENSION__VALUE = eINSTANCE.getIntegerExtension_Value();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.StringExtension <em>String Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.StringExtension
		 * @see org.ect.ea.extensions.ExtensionsPackage#getStringExtension()
		 * @generated
		 */
		public static final EClass STRING_EXTENSION = eINSTANCE.getStringExtension();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_EXTENSION__VALUE = eINSTANCE.getStringExtension_Value();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.StringListExtension <em>String List Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.StringListExtension
		 * @see org.ect.ea.extensions.ExtensionsPackage#getStringListExtension()
		 * @generated
		 */
		public static final EClass STRING_LIST_EXTENSION = eINSTANCE.getStringListExtension();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_LIST_EXTENSION__VALUES = eINSTANCE.getStringListExtension_Values();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.BooleanExtension <em>Boolean Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.BooleanExtension
		 * @see org.ect.ea.extensions.ExtensionsPackage#getBooleanExtension()
		 * @generated
		 */
		public static final EClass BOOLEAN_EXTENSION = eINSTANCE.getBooleanExtension();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute BOOLEAN_EXTENSION__VALUE = eINSTANCE.getBooleanExtension_Value();

	}

} //ExtensionsPackage
