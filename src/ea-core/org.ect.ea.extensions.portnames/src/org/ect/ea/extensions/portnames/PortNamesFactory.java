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
 * @see org.ect.ea.extensions.portnames.PortNamesPackage
 * @generated
 */
public class PortNamesFactory extends EFactoryImpl {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final PortNamesFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PortNamesFactory init() {
		try {
			PortNamesFactory thePortNamesFactory = (PortNamesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/ea/portNames"); 
			if (thePortNamesFactory != null) {
				return thePortNamesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PortNamesFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortNamesFactory() {
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
			case PortNamesPackage.AUTOMATON_PORT_NAMES: return createAutomatonPortNames();
			case PortNamesPackage.TRANSITION_PORT_NAMES: return createTransitionPortNames();
			case PortNamesPackage.INTENSIONAL_PORT_NAMES: return createIntensionalPortNames();
			case PortNamesPackage.DELAY_ELEMENT: return createDelayElement();
			case PortNamesPackage.DELAY_INFORMATION: return createDelayInformation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AutomatonPortNames createAutomatonPortNames() {
		AutomatonPortNames automatonPortNames = new AutomatonPortNames();
		return automatonPortNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionPortNames createTransitionPortNames() {
		TransitionPortNames transitionPortNames = new TransitionPortNames();
		return transitionPortNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntensionalPortNames createIntensionalPortNames() {
		IntensionalPortNames intensionalPortNames = new IntensionalPortNames();
		return intensionalPortNames;
	}

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DelayElement createDelayElement() {
		DelayElement delayElement = new DelayElement();
		return delayElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DelayInformation createDelayInformation() {
		DelayInformation delayInformation = new DelayInformation();
		return delayInformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortNamesPackage getPortNamesPackage() {
		return (PortNamesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PortNamesPackage getPackage() {
		return PortNamesPackage.eINSTANCE;
	}

} //PortNamesFactory
