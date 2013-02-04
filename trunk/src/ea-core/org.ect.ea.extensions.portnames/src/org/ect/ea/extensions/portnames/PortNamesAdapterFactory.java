/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
package org.ect.ea.extensions.portnames;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;
import org.ect.ea.extensions.ExtensionElement;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringListExtension;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.ea.extensions.portnames.PortNamesPackage
 * @generated
 */
public class PortNamesAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PortNamesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortNamesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PortNamesPackage.eINSTANCE;
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
	protected PortNamesSwitch<Adapter> modelSwitch =
		new PortNamesSwitch<Adapter>() {
			@Override
			public Adapter caseAutomatonPortNames(AutomatonPortNames object) {
				return createAutomatonPortNamesAdapter();
			}
			@Override
			public Adapter caseTransitionPortNames(TransitionPortNames object) {
				return createTransitionPortNamesAdapter();
			}
			@Override
			public Adapter caseIntensionalPortNames(IntentionalPortNames object) {
				return createIntensionalPortNamesAdapter();
			}
			@Override
			public Adapter caseDelayElement(DelayElement object) {
				return createDelayElementAdapter();
			}
			@Override
			public Adapter caseDelayInformation(DelayInformation object) {
				return createDelayInformationAdapter();
			}
			@Override
			public Adapter caseIExtension(IExtension object) {
				return createIExtensionAdapter();
			}
			@Override
			public Adapter caseExtensionElement(ExtensionElement object) {
				return createExtensionElementAdapter();
			}
			@Override
			public Adapter caseStringListExtension(StringListExtension object) {
				return createStringListExtensionAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.portnames.AutomatonPortNames <em>Automaton Port Names</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.portnames.AutomatonPortNames
	 * @generated
	 */
	public Adapter createAutomatonPortNamesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.portnames.TransitionPortNames <em>Transition Port Names</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.portnames.TransitionPortNames
	 * @generated
	 */
	public Adapter createTransitionPortNamesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.portnames.IntentionalPortNames <em>Intensional Port Names</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.portnames.IntentionalPortNames
	 * @generated
	 */
	public Adapter createIntensionalPortNamesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.portnames.DelayElement <em>Delay Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.portnames.DelayElement
	 * @generated
	 */
	public Adapter createDelayElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.ect.ea.extensions.portnames.DelayInformation <em>Delay Information</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.ect.ea.extensions.portnames.DelayInformation
	 * @generated
	 */
	public Adapter createDelayInformationAdapter() {
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

} //PortNamesAdapterFactory
