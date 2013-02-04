/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs.algebras;



import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.CostsObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.ect.ea.costs.algebras.AlgebrasPackage
 * @generated
 */
public class AlgebrasSwitch {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AlgebrasPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlgebrasSwitch() {
		if (modelPackage == null) {
			modelPackage = AlgebrasPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AlgebrasPackage.MEMORY_ALGEBRA: {
				MemoryAlgebra memoryAlgebra = (MemoryAlgebra)theEObject;
				Object result = caseMemoryAlgebra(memoryAlgebra);
				if (result == null) result = caseCostsAlgebra(memoryAlgebra);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgebrasPackage.CPU_ALGEBRA: {
				CPUAlgebra cpuAlgebra = (CPUAlgebra)theEObject;
				Object result = caseCPUAlgebra(cpuAlgebra);
				if (result == null) result = caseCostsAlgebra(cpuAlgebra);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgebrasPackage.BANDWIDTH_ALGEBRA: {
				BandwidthAlgebra bandwidthAlgebra = (BandwidthAlgebra)theEObject;
				Object result = caseBandwidthAlgebra(bandwidthAlgebra);
				if (result == null) result = caseCostsAlgebra(bandwidthAlgebra);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgebrasPackage.DELAY_ALGEBRA: {
				DelayAlgebra delayAlgebra = (DelayAlgebra)theEObject;
				Object result = caseDelayAlgebra(delayAlgebra);
				if (result == null) result = caseCostsAlgebra(delayAlgebra);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgebrasPackage.RELIABILITY_ALGEBRA: {
				ReliabilityAlgebra reliabilityAlgebra = (ReliabilityAlgebra)theEObject;
				Object result = caseReliabilityAlgebra(reliabilityAlgebra);
				if (result == null) result = caseCostsAlgebra(reliabilityAlgebra);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgebrasPackage.SECURITY_ALGEBRA: {
				SecurityAlgebra securityAlgebra = (SecurityAlgebra)theEObject;
				Object result = caseSecurityAlgebra(securityAlgebra);
				if (result == null) result = caseCostsAlgebra(securityAlgebra);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AlgebrasPackage.EXPONENTIAL_DELAY_ALGEBRA: {
				ExponentialDelayAlgebra exponentialDelayAlgebra = (ExponentialDelayAlgebra)theEObject;
				Object result = caseExponentialDelayAlgebra(exponentialDelayAlgebra);
				if (result == null) result = caseCostsAlgebra(exponentialDelayAlgebra);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Memory Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Memory Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMemoryAlgebra(MemoryAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>CPU Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>CPU Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCPUAlgebra(CPUAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Bandwidth Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Bandwidth Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBandwidthAlgebra(BandwidthAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Delay Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Delay Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDelayAlgebra(DelayAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Reliability Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Reliability Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseReliabilityAlgebra(ReliabilityAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Security Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Security Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSecurityAlgebra(SecurityAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Exponential Delay Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Exponential Delay Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseExponentialDelayAlgebra(ExponentialDelayAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Algebra</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Algebra</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCostsAlgebra(CostsAlgebra object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //AlgebrasSwitch
