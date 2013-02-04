/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.extensions.statememory;

import java.util.Map;

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
 * @see org.ect.ea.extensions.statememory.StateMemoryPackage
 * @generated
 */
public class StateMemoryFactory extends EFactoryImpl {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final StateMemoryFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StateMemoryFactory init() {
		try {
			StateMemoryFactory theStateMemoryFactory = (StateMemoryFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/ea/stateMemory"); 
			if (theStateMemoryFactory != null) {
				return theStateMemoryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StateMemoryFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMemoryFactory() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StateMemoryPackage.STATE_MEMORY: return createStateMemory();
			case StateMemoryPackage.STRING_MAP_ENTRY: return (EObject)createStringMapEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMemory createStateMemory() {
		StateMemory stateMemory = new StateMemory();
		return stateMemory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createStringMapEntry() {
		StringMapEntry stringMapEntry = new StringMapEntry();
		return stringMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMemoryPackage getStateMemoryPackage() {
		return (StateMemoryPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StateMemoryPackage getPackage() {
		return StateMemoryPackage.eINSTANCE;
	}

} //StateMemoryFactory
