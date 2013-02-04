/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs.algebras;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.ea.costs.algebras.AlgebrasPackage
 * @generated
 */
public class AlgebrasFactory extends EFactoryImpl {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AlgebrasFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AlgebrasFactory init() {
		try {
			AlgebrasFactory theAlgebrasFactory = (AlgebrasFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/ea/costs/algebras"); 
			if (theAlgebrasFactory != null) {
				return theAlgebrasFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AlgebrasFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebrasFactory() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AlgebrasPackage.MEMORY_ALGEBRA: return createMemoryAlgebra();
			case AlgebrasPackage.CPU_ALGEBRA: return createCPUAlgebra();
			case AlgebrasPackage.BANDWIDTH_ALGEBRA: return createBandwidthAlgebra();
			case AlgebrasPackage.DELAY_ALGEBRA: return createDelayAlgebra();
			case AlgebrasPackage.RELIABILITY_ALGEBRA: return createReliabilityAlgebra();
			case AlgebrasPackage.SECURITY_ALGEBRA: return createSecurityAlgebra();
			case AlgebrasPackage.EXPONENTIAL_DELAY_ALGEBRA: return createExponentialDelayAlgebra();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemoryAlgebra createMemoryAlgebra() {
		MemoryAlgebra memoryAlgebra = new MemoryAlgebra();
		return memoryAlgebra;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CPUAlgebra createCPUAlgebra() {
		CPUAlgebra cpuAlgebra = new CPUAlgebra();
		return cpuAlgebra;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BandwidthAlgebra createBandwidthAlgebra() {
		BandwidthAlgebra bandwidthAlgebra = new BandwidthAlgebra();
		return bandwidthAlgebra;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DelayAlgebra createDelayAlgebra() {
		DelayAlgebra delayAlgebra = new DelayAlgebra();
		return delayAlgebra;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReliabilityAlgebra createReliabilityAlgebra() {
		ReliabilityAlgebra reliabilityAlgebra = new ReliabilityAlgebra();
		return reliabilityAlgebra;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityAlgebra createSecurityAlgebra() {
		SecurityAlgebra securityAlgebra = new SecurityAlgebra();
		return securityAlgebra;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExponentialDelayAlgebra createExponentialDelayAlgebra() {
		ExponentialDelayAlgebra exponentialDelayAlgebra = new ExponentialDelayAlgebra();
		return exponentialDelayAlgebra;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebrasPackage getAlgebrasPackage() {
		return (AlgebrasPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static AlgebrasPackage getPackage() {
		return AlgebrasPackage.eINSTANCE;
	}

} //AlgebrasFactory
