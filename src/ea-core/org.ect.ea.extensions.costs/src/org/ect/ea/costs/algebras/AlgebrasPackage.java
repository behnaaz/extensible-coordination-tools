/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs.algebras;



import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.ea.costs.CostsPackage;
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
 * @see org.ect.ea.costs.algebras.AlgebrasFactory
 * @model kind="package"
 * @generated
 */
public class AlgebrasPackage extends EPackageImpl {
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
	public static final String eNAME = "algebras";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/ea/costs/algebras";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "org.ect.ea.costs.algebras";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AlgebrasPackage eINSTANCE = org.ect.ea.costs.algebras.AlgebrasPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.algebras.MemoryAlgebra <em>Memory Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.algebras.MemoryAlgebra
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getMemoryAlgebra()
	 * @generated
	 */
	public static final int MEMORY_ALGEBRA = 0;

	/**
	 * The number of structural features of the '<em>Memory Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MEMORY_ALGEBRA_FEATURE_COUNT = CostsPackage.COSTS_ALGEBRA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.algebras.CPUAlgebra <em>CPU Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.algebras.CPUAlgebra
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getCPUAlgebra()
	 * @generated
	 */
	public static final int CPU_ALGEBRA = 1;

	/**
	 * The number of structural features of the '<em>CPU Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CPU_ALGEBRA_FEATURE_COUNT = CostsPackage.COSTS_ALGEBRA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.algebras.BandwidthAlgebra <em>Bandwidth Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.algebras.BandwidthAlgebra
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getBandwidthAlgebra()
	 * @generated
	 */
	public static final int BANDWIDTH_ALGEBRA = 2;

	/**
	 * The number of structural features of the '<em>Bandwidth Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BANDWIDTH_ALGEBRA_FEATURE_COUNT = CostsPackage.COSTS_ALGEBRA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.algebras.DelayAlgebra <em>Delay Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.algebras.DelayAlgebra
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getDelayAlgebra()
	 * @generated
	 */
	public static final int DELAY_ALGEBRA = 3;

	/**
	 * The number of structural features of the '<em>Delay Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAY_ALGEBRA_FEATURE_COUNT = CostsPackage.COSTS_ALGEBRA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.algebras.ReliabilityAlgebra <em>Reliability Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.algebras.ReliabilityAlgebra
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getReliabilityAlgebra()
	 * @generated
	 */
	public static final int RELIABILITY_ALGEBRA = 4;

	/**
	 * The number of structural features of the '<em>Reliability Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RELIABILITY_ALGEBRA_FEATURE_COUNT = CostsPackage.COSTS_ALGEBRA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.algebras.SecurityAlgebra <em>Security Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.algebras.SecurityAlgebra
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getSecurityAlgebra()
	 * @generated
	 */
	public static final int SECURITY_ALGEBRA = 5;

	/**
	 * The number of structural features of the '<em>Security Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SECURITY_ALGEBRA_FEATURE_COUNT = CostsPackage.COSTS_ALGEBRA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.ea.costs.algebras.ExponentialDelayAlgebra <em>Exponential Delay Algebra</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.ea.costs.algebras.ExponentialDelayAlgebra
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getExponentialDelayAlgebra()
	 * @generated
	 */
	public static final int EXPONENTIAL_DELAY_ALGEBRA = 6;

	/**
	 * The number of structural features of the '<em>Exponential Delay Algebra</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPONENTIAL_DELAY_ALGEBRA_FEATURE_COUNT = CostsPackage.COSTS_ALGEBRA_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memoryAlgebraEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cpuAlgebraEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bandwidthAlgebraEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass delayAlgebraEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reliabilityAlgebraEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass securityAlgebraEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentialDelayAlgebraEClass = null;

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
	 * @see org.ect.ea.costs.algebras.AlgebrasPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AlgebrasPackage() {
		super(eNS_URI, AlgebrasFactory.eINSTANCE);
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
	public static AlgebrasPackage init() {
		if (isInited) return (AlgebrasPackage)EPackage.Registry.INSTANCE.getEPackage(AlgebrasPackage.eNS_URI);

		// Obtain or create and register package
		AlgebrasPackage theAlgebrasPackage = (AlgebrasPackage)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof AlgebrasPackage ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new AlgebrasPackage());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CostsPackage theCostsPackage = (CostsPackage)(EPackage.Registry.INSTANCE.getEPackage(CostsPackage.eNS_URI) instanceof CostsPackage ? EPackage.Registry.INSTANCE.getEPackage(CostsPackage.eNS_URI) : CostsPackage.eINSTANCE);
		TypesPackage theTypesPackage = (TypesPackage)(EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) instanceof TypesPackage ? EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) : TypesPackage.eINSTANCE);

		// Create package meta-data objects
		theAlgebrasPackage.createPackageContents();
		theCostsPackage.createPackageContents();
		theTypesPackage.createPackageContents();

		// Initialize created meta-data
		theAlgebrasPackage.initializePackageContents();
		theCostsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAlgebrasPackage.freeze();

		return theAlgebrasPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.algebras.MemoryAlgebra <em>Memory Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Memory Algebra</em>'.
	 * @see org.ect.ea.costs.algebras.MemoryAlgebra
	 * @generated
	 */
	public EClass getMemoryAlgebra() {
		return memoryAlgebraEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.algebras.CPUAlgebra <em>CPU Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CPU Algebra</em>'.
	 * @see org.ect.ea.costs.algebras.CPUAlgebra
	 * @generated
	 */
	public EClass getCPUAlgebra() {
		return cpuAlgebraEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.algebras.BandwidthAlgebra <em>Bandwidth Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bandwidth Algebra</em>'.
	 * @see org.ect.ea.costs.algebras.BandwidthAlgebra
	 * @generated
	 */
	public EClass getBandwidthAlgebra() {
		return bandwidthAlgebraEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.algebras.DelayAlgebra <em>Delay Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delay Algebra</em>'.
	 * @see org.ect.ea.costs.algebras.DelayAlgebra
	 * @generated
	 */
	public EClass getDelayAlgebra() {
		return delayAlgebraEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.algebras.ReliabilityAlgebra <em>Reliability Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reliability Algebra</em>'.
	 * @see org.ect.ea.costs.algebras.ReliabilityAlgebra
	 * @generated
	 */
	public EClass getReliabilityAlgebra() {
		return reliabilityAlgebraEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.algebras.SecurityAlgebra <em>Security Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Algebra</em>'.
	 * @see org.ect.ea.costs.algebras.SecurityAlgebra
	 * @generated
	 */
	public EClass getSecurityAlgebra() {
		return securityAlgebraEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.ea.costs.algebras.ExponentialDelayAlgebra <em>Exponential Delay Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Delay Algebra</em>'.
	 * @see org.ect.ea.costs.algebras.ExponentialDelayAlgebra
	 * @generated
	 */
	public EClass getExponentialDelayAlgebra() {
		return exponentialDelayAlgebraEClass;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public AlgebrasFactory getAlgebrasFactory() {
		return (AlgebrasFactory)getEFactoryInstance();
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
		memoryAlgebraEClass = createEClass(MEMORY_ALGEBRA);

		cpuAlgebraEClass = createEClass(CPU_ALGEBRA);

		bandwidthAlgebraEClass = createEClass(BANDWIDTH_ALGEBRA);

		delayAlgebraEClass = createEClass(DELAY_ALGEBRA);

		reliabilityAlgebraEClass = createEClass(RELIABILITY_ALGEBRA);

		securityAlgebraEClass = createEClass(SECURITY_ALGEBRA);

		exponentialDelayAlgebraEClass = createEClass(EXPONENTIAL_DELAY_ALGEBRA);
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
		memoryAlgebraEClass.getESuperTypes().add(theCostsPackage.getCostsAlgebra());
		cpuAlgebraEClass.getESuperTypes().add(theCostsPackage.getCostsAlgebra());
		bandwidthAlgebraEClass.getESuperTypes().add(theCostsPackage.getCostsAlgebra());
		delayAlgebraEClass.getESuperTypes().add(theCostsPackage.getCostsAlgebra());
		reliabilityAlgebraEClass.getESuperTypes().add(theCostsPackage.getCostsAlgebra());
		securityAlgebraEClass.getESuperTypes().add(theCostsPackage.getCostsAlgebra());
		exponentialDelayAlgebraEClass.getESuperTypes().add(theCostsPackage.getCostsAlgebra());

		// Initialize classes and features; add operations and parameters
		initEClass(memoryAlgebraEClass, MemoryAlgebra.class, "MemoryAlgebra", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cpuAlgebraEClass, CPUAlgebra.class, "CPUAlgebra", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(bandwidthAlgebraEClass, BandwidthAlgebra.class, "BandwidthAlgebra", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(delayAlgebraEClass, DelayAlgebra.class, "DelayAlgebra", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(reliabilityAlgebraEClass, ReliabilityAlgebra.class, "ReliabilityAlgebra", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(securityAlgebraEClass, SecurityAlgebra.class, "SecurityAlgebra", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exponentialDelayAlgebraEClass, ExponentialDelayAlgebra.class, "ExponentialDelayAlgebra", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
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
		 * The meta object literal for the '{@link org.ect.ea.costs.algebras.MemoryAlgebra <em>Memory Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.algebras.MemoryAlgebra
		 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getMemoryAlgebra()
		 * @generated
		 */
		public static final EClass MEMORY_ALGEBRA = eINSTANCE.getMemoryAlgebra();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.algebras.CPUAlgebra <em>CPU Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.algebras.CPUAlgebra
		 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getCPUAlgebra()
		 * @generated
		 */
		public static final EClass CPU_ALGEBRA = eINSTANCE.getCPUAlgebra();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.algebras.BandwidthAlgebra <em>Bandwidth Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.algebras.BandwidthAlgebra
		 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getBandwidthAlgebra()
		 * @generated
		 */
		public static final EClass BANDWIDTH_ALGEBRA = eINSTANCE.getBandwidthAlgebra();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.algebras.DelayAlgebra <em>Delay Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.algebras.DelayAlgebra
		 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getDelayAlgebra()
		 * @generated
		 */
		public static final EClass DELAY_ALGEBRA = eINSTANCE.getDelayAlgebra();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.algebras.ReliabilityAlgebra <em>Reliability Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.algebras.ReliabilityAlgebra
		 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getReliabilityAlgebra()
		 * @generated
		 */
		public static final EClass RELIABILITY_ALGEBRA = eINSTANCE.getReliabilityAlgebra();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.algebras.SecurityAlgebra <em>Security Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.algebras.SecurityAlgebra
		 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getSecurityAlgebra()
		 * @generated
		 */
		public static final EClass SECURITY_ALGEBRA = eINSTANCE.getSecurityAlgebra();

		/**
		 * The meta object literal for the '{@link org.ect.ea.costs.algebras.ExponentialDelayAlgebra <em>Exponential Delay Algebra</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.ea.costs.algebras.ExponentialDelayAlgebra
		 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getExponentialDelayAlgebra()
		 * @generated
		 */
		public static final EClass EXPONENTIAL_DELAY_ALGEBRA = eINSTANCE.getExponentialDelayAlgebra();

	}

} //AlgebrasPackage
