/**
 * Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.
 *
 * $Id$
 */
package org.ect.ea.extensions;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.ea.extensions.ExtensionsPackage
 * @generated
 */
public class ExtensionsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ExtensionsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensionsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ExtensionsPackage.eINSTANCE;
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
	@Override
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
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensionsSwitch<Adapter> modelSwitch =
		new ExtensionsSwitch<Adapter>() {
			@Override
			public Adapter caseIExtendible(IExtendible object) {
				return createIExtendibleAdapter();
			}
			@Override
			public Adapter caseIExtension(IExtension object) {
				return createIExtensionAdapter();
			}
			@Override
			public Adapter caseExtendibleElement(ExtendibleElement object) {
				return createExtendibleElementAdapter();
			}
			@Override
			public Adapter caseExtensionElement(ExtensionElement object) {
				return createExtensionElementAdapter();
			}
			@Override
			public Adapter caseIntegerExtension(IntegerExtension object) {
				return createIntegerExtensionAdapter();
			}
			@Override
			public Adapter caseStringExtension(StringExtension object) {
				return createStringExtensionAdapter();
			}
			@Override
			public Adapter caseStringListExtension(StringListExtension object) {
				return createStringListExtensionAdapter();
			}
			@Override
			public Adapter caseBooleanExtension(BooleanExtension object) {
				return createBooleanExtensionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
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
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.IExtendible <em>IExtendible</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.IExtendible
	 * @generated
	 */
	public Adapter createIExtendibleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.IExtension <em>IExtension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.IExtension
	 * @generated
	 */
	public Adapter createIExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.ExtendibleElement <em>Extendible Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.ExtendibleElement
	 * @generated
	 */
	public Adapter createExtendibleElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.ExtensionElement <em>Extension Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.ExtensionElement
	 * @generated
	 */
	public Adapter createExtensionElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.IntegerExtension <em>Integer Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.IntegerExtension
	 * @generated
	 */
	public Adapter createIntegerExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.StringExtension <em>String Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.StringExtension
	 * @generated
	 */
	public Adapter createStringExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.StringListExtension <em>String List Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.StringListExtension
	 * @generated
	 */
	public Adapter createStringListExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.BooleanExtension <em>Boolean Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.BooleanExtension
	 * @generated
	 */
	public Adapter createBooleanExtensionAdapter() {
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

} //ExtensionsAdapterFactory
