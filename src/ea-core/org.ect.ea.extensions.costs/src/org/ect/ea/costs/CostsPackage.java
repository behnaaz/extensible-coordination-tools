/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs;



import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.ea.costs.algebras.AlgebrasPackage;
import org.ect.ea.costs.types.TypesPackage;

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
 * @see org.ect.ea.costs.CostsFactory
 * @model kind="package"
 * @generated
 */
public class CostsPackage extends EPackageImpl {
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
	public static final String eNAME = "costs";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/costs";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "org.ect.ea.costs";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final CostsPackage eINSTANCE = org.ect.ea.costs.CostsPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.CostsObject <em>Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.CostsObject
	 * @see org.ect.ea.costs.CostsPackage#getCostsObject()
	 * @generated
	 */
	public static final int COSTS_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_OBJECT__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_OBJECT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_OBJECT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.CostsAlgebra <em>Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.CostsAlgebra
	 * @see org.ect.ea.costs.CostsPackage#getCostsAlgebra()
	 * @generated
	 */
	public static final int COSTS_ALGEBRA = 1;

	/**
	 * The number of structural features of the '<em>Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COSTS_ALGEBRA_FEATURE_COUNT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass costsObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass costsAlgebraEClass = null;

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
	 * @see org.ect.ea.costs.CostsPackage#eNS_URI
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
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		TypesPackage theTypesPackage = (TypesPackage)(EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) instanceof TypesPackage ? EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) : TypesPackage.eINSTANCE);
		AlgebrasPackage theAlgebrasPackage = (AlgebrasPackage)(EPackage.Registry.INSTANCE.getEPackage(AlgebrasPackage.eNS_URI) instanceof AlgebrasPackage ? EPackage.Registry.INSTANCE.getEPackage(AlgebrasPackage.eNS_URI) : AlgebrasPackage.eINSTANCE);

		// Create package meta-data objects
		theCostsPackage.createPackageContents();
		theTypesPackage.createPackageContents();
		theAlgebrasPackage.createPackageContents();

		// Initialize created meta-data
		theCostsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();
		theAlgebrasPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCostsPackage.freeze();

		return theCostsPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.CostsObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object</em>'.
	 * @see org.ect.ea.costs.CostsObject
	 * @generated
	 */
	public EClass getCostsObject() {
		return costsObjectEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.costs.CostsObject#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ect.ea.costs.CostsObject#getType()
	 * @see #getCostsObject()
	 * @generated
	 */
	public EAttribute getCostsObject_Type() {
		return (EAttribute)costsObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.ea.costs.CostsObject#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.ea.costs.CostsObject#getValue()
	 * @see #getCostsObject()
	 * @generated
	 */
	public EAttribute getCostsObject_Value() {
		return (EAttribute)costsObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.CostsAlgebra <em>Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Algebra</em>'.
	 * @see org.ect.ea.costs.CostsAlgebra
	 * @generated
	 */
	public EClass getCostsAlgebra() {
		return costsAlgebraEClass;
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
		costsObjectEClass = createEClass(COSTS_OBJECT);
		createEAttribute(costsObjectEClass, COSTS_OBJECT__TYPE);
		createEAttribute(costsObjectEClass, COSTS_OBJECT__VALUE);

		costsAlgebraEClass = createEClass(COSTS_ALGEBRA);
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
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);
		AlgebrasPackage theAlgebrasPackage = (AlgebrasPackage)EPackage.Registry.INSTANCE.getEPackage(AlgebrasPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theTypesPackage);
		getESubpackages().add(theAlgebrasPackage);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(costsObjectEClass, CostsObject.class, "CostsObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCostsObject_Type(), ecorePackage.getEJavaClass(), "type", null, 0, 1, CostsObject.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCostsObject_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, CostsObject.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(costsAlgebraEClass, CostsAlgebra.class, "CostsAlgebra", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = addEOperation(costsAlgebraEClass, this.getCostsObject(), "choose", 0, 1);
		addEParameter(op, this.getCostsObject(), "c1", 0, 1);
		addEParameter(op, this.getCostsObject(), "c2", 0, 1);

		op = addEOperation(costsAlgebraEClass, this.getCostsObject(), "combineSequentially", 0, 1);
		addEParameter(op, this.getCostsObject(), "c1", 0, 1);
		addEParameter(op, this.getCostsObject(), "c2", 0, 1);

		op = addEOperation(costsAlgebraEClass, this.getCostsObject(), "combineParallel", 0, 1);
		addEParameter(op, this.getCostsObject(), "c1", 0, 1);
		addEParameter(op, this.getCostsObject(), "c2", 0, 1);

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
		 * The meta object literal for the '{@link org.ect.ea.costs.CostsObject <em>Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.CostsObject
		 * @see org.ect.ea.costs.CostsPackage#getCostsObject()
		 * @generated
		 */
		public static final EClass COSTS_OBJECT = eINSTANCE.getCostsObject();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute COSTS_OBJECT__TYPE = eINSTANCE.getCostsObject_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute COSTS_OBJECT__VALUE = eINSTANCE.getCostsObject_Value();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.CostsAlgebra <em>Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.CostsAlgebra
		 * @see org.ect.ea.costs.CostsPackage#getCostsAlgebra()
		 * @generated
		 */
		public static final EClass COSTS_ALGEBRA = eINSTANCE.getCostsAlgebra();

}

} //CostsPackage
