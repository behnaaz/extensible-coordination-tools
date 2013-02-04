/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.extensions.costs;



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
 * @see org.ect.ea.extensions.costs.CostsFactory
 * @model kind="package"
 * @generated
 */
public class CostsPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "costs";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/costExtensions";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "costs";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final CostsPackage eINSTANCE = org.ect.ea.extensions.costs.CostsPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.costs.CostsObjectExtension <em>Object Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.costs.CostsObjectExtension
	 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsObjectExtension()
	 * @generated
	 */
	public static final int COSTS_OBJECT_EXTENSION = 0;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_OBJECT_EXTENSION__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_OBJECT_EXTENSION__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Costs Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_OBJECT_EXTENSION__COSTS_OBJECTS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Object Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_OBJECT_EXTENSION_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.ea.extensions.costs.CostsAlgebraExtension <em>Algebra Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.extensions.costs.CostsAlgebraExtension
	 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsAlgebraExtension()
	 * @generated
	 */
	public static final int COSTS_ALGEBRA_EXTENSION = 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_ALGEBRA_EXTENSION__OWNER = ExtensionsPackage.EXTENSION_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_ALGEBRA_EXTENSION__ID = ExtensionsPackage.EXTENSION_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Costs Algebras</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Algebra Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_ALGEBRA_EXTENSION_FEATURE_COUNT = ExtensionsPackage.EXTENSION_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass costsObjectExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass costsAlgebraExtensionEClass = null;

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
	 * @see org.ect.ea.extensions.costs.CostsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CostsPackage() {
		super(eNS_URI, CostsFactory.eINSTANCE);
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
	public static CostsPackage init() {
		if (isInited) return (CostsPackage)EPackage.Registry.INSTANCE.getEPackage(CostsPackage.eNS_URI);

		// Obtain or create and register package
		CostsPackage theCostsPackage = (CostsPackage)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof CostsPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new CostsPackage());

		isInited = true;

		// Initialize simple dependencies
		AutomataPackage.eINSTANCE.eClass();
		ExtensionsPackage.eINSTANCE.eClass();
		org.ect.ea.costs.CostsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCostsPackage.createPackageContents();

		// Initialize created meta-data
		theCostsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCostsPackage.freeze();

		return theCostsPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.costs.CostsObjectExtension <em>Object Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Extension</em>'.
	 * @see org.ect.ea.extensions.costs.CostsObjectExtension
	 * @generated
	 */
	public EClass getCostsObjectExtension() {
		return costsObjectExtensionEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.extensions.costs.CostsObjectExtension#getCostsObjects <em>Costs Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Costs Objects</em>'.
	 * @see org.ect.ea.extensions.costs.CostsObjectExtension#getCostsObjects()
	 * @see #getCostsObjectExtension()
	 * @generated
	 */
	public EReference getCostsObjectExtension_CostsObjects() {
		return (EReference)costsObjectExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.extensions.costs.CostsAlgebraExtension <em>Algebra Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Algebra Extension</em>'.
	 * @see org.ect.ea.extensions.costs.CostsAlgebraExtension
	 * @generated
	 */
	public EClass getCostsAlgebraExtension() {
		return costsAlgebraExtensionEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.ea.extensions.costs.CostsAlgebraExtension#getCostsAlgebras <em>Costs Algebras</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Costs Algebras</em>'.
	 * @see org.ect.ea.extensions.costs.CostsAlgebraExtension#getCostsAlgebras()
	 * @see #getCostsAlgebraExtension()
	 * @generated
	 */
	public EReference getCostsAlgebraExtension_CostsAlgebras() {
		return (EReference)costsAlgebraExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public CostsFactory getCostsFactory() {
		return (CostsFactory)getEFactoryInstance();
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
		costsObjectExtensionEClass = createEClass(COSTS_OBJECT_EXTENSION);
		createEReference(costsObjectExtensionEClass, COSTS_OBJECT_EXTENSION__COSTS_OBJECTS);

		costsAlgebraExtensionEClass = createEClass(COSTS_ALGEBRA_EXTENSION);
		createEReference(costsAlgebraExtensionEClass, COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS);
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
		org.ect.ea.costs.CostsPackage theCostsPackage_1 = (org.ect.ea.costs.CostsPackage)EPackage.Registry.INSTANCE.getEPackage(org.ect.ea.costs.CostsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		costsObjectExtensionEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());
		costsAlgebraExtensionEClass.getESuperTypes().add(theExtensionsPackage.getExtensionElement());

		// Initialize classes and features; add operations and parameters
		initEClass(costsObjectExtensionEClass, CostsObjectExtension.class, "CostsObjectExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCostsObjectExtension_CostsObjects(), theCostsPackage_1.getCostsObject(), null, "costsObjects", null, 0, -1, CostsObjectExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(costsAlgebraExtensionEClass, CostsAlgebraExtension.class, "CostsAlgebraExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCostsAlgebraExtension_CostsAlgebras(), theCostsPackage_1.getCostsAlgebra(), null, "costsAlgebras", null, 0, -1, CostsAlgebraExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		 * The meta object literal for the '{@link org.ect.ea.extensions.costs.CostsObjectExtension <em>Object Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.costs.CostsObjectExtension
		 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsObjectExtension()
		 * @generated
		 */
		public static final EClass COSTS_OBJECT_EXTENSION = eINSTANCE.getCostsObjectExtension();

		/**
		 * The meta object literal for the '<em><b>Costs Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COSTS_OBJECT_EXTENSION__COSTS_OBJECTS = eINSTANCE.getCostsObjectExtension_CostsObjects();

		/**
		 * The meta object literal for the '{@link org.ect.ea.extensions.costs.CostsAlgebraExtension <em>Algebra Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.extensions.costs.CostsAlgebraExtension
		 * @see org.ect.ea.extensions.costs.CostsPackage#getCostsAlgebraExtension()
		 * @generated
		 */
		public static final EClass COSTS_ALGEBRA_EXTENSION = eINSTANCE.getCostsAlgebraExtension();

		/**
		 * The meta object literal for the '<em><b>Costs Algebras</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COSTS_ALGEBRA_EXTENSION__COSTS_ALGEBRAS = eINSTANCE.getCostsAlgebraExtension_CostsAlgebras();

	}

} //CostsPackage
