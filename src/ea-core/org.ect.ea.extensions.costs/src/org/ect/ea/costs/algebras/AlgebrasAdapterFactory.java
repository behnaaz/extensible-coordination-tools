/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs.algebras;



import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.CostsObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.ea.costs.algebras.AlgebrasPackage
 * @generated
 */
public class AlgebrasAdapterFactory extends AdapterFactoryImpl {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AlgebrasPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebrasAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AlgebrasPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AlgebrasSwitch modelSwitch =
		new AlgebrasSwitch() {
			public Object caseMemoryAlgebra(MemoryAlgebra object) {
				return createMemoryAlgebraAdapter();
			}
			public Object caseCPUAlgebra(CPUAlgebra object) {
				return createCPUAlgebraAdapter();
			}
			public Object caseBandwidthAlgebra(BandwidthAlgebra object) {
				return createBandwidthAlgebraAdapter();
			}
			public Object caseDelayAlgebra(DelayAlgebra object) {
				return createDelayAlgebraAdapter();
			}
			public Object caseReliabilityAlgebra(ReliabilityAlgebra object) {
				return createReliabilityAlgebraAdapter();
			}
			public Object caseSecurityAlgebra(SecurityAlgebra object) {
				return createSecurityAlgebraAdapter();
			}
			public Object caseExponentialDelayAlgebra(ExponentialDelayAlgebra object) {
				return createExponentialDelayAlgebraAdapter();
			}
			public Object caseCostsAlgebra(CostsAlgebra object) {
				return createCostsAlgebraAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.algebras.MemoryAlgebra <em>Memory Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.algebras.MemoryAlgebra
	 * @generated
	 */
	public Adapter createMemoryAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.algebras.CPUAlgebra <em>CPU Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.algebras.CPUAlgebra
	 * @generated
	 */
	public Adapter createCPUAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.algebras.BandwidthAlgebra <em>Bandwidth Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.algebras.BandwidthAlgebra
	 * @generated
	 */
	public Adapter createBandwidthAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.algebras.DelayAlgebra <em>Delay Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.algebras.DelayAlgebra
	 * @generated
	 */
	public Adapter createDelayAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.algebras.ReliabilityAlgebra <em>Reliability Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.algebras.ReliabilityAlgebra
	 * @generated
	 */
	public Adapter createReliabilityAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.algebras.SecurityAlgebra <em>Security Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.algebras.SecurityAlgebra
	 * @generated
	 */
	public Adapter createSecurityAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.algebras.ExponentialDelayAlgebra <em>Exponential Delay Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.algebras.ExponentialDelayAlgebra
	 * @generated
	 */
	public Adapter createExponentialDelayAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.costs.CostsAlgebra <em>Algebra</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.costs.CostsAlgebra
	 * @generated
	 */
	public Adapter createCostsAlgebraAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AlgebrasAdapterFactory
