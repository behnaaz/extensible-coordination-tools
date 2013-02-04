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
package org.ect.reo.colouring;


import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.ect.reo.PrimitiveEnd;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.reo.colouring.ColouringPackage
 * @generated
 */
public class ColouringFactory extends EFactoryImpl {
	
	/**
	 * <!-- begin-user-doc -->
	 * Create a colouring engine.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ColouringEngine createColouringEngineFromString(EDataType eDataType, String value) {
		
		// Find the engine description.
		ColouringEngineDescription description = ColouringEngines.DEFAULT;
		for (ColouringEngineDescription desc : ColouringEngines.getDescriptions()) {
			if (value!=null && value.equals(desc.getClassName())) {
				description = desc; break;
			}
		}
		
		// Load the engine.
		try {
			return description.loadEngine();
		} catch (Throwable t) {
			return new StepwiseColouringEngine();
		}
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Convert a colouring engine to a string.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertColouringEngineToString(EDataType eDataType, Object engine) {
		ColouringEngine e = (ColouringEngine) engine;
		return e.getClass().getName();
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ColouringFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ColouringFactory init() {
		try {
			ColouringFactory theColouringFactory = (ColouringFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/reo/colouring"); 
			if (theColouringFactory != null) {
				return theColouringFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ColouringFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColouringFactory() {
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
			case ColouringPackage.COLOURING: return createColouring();
			case ColouringPackage.COLOURING_TABLE: return createColouringTable();
			case ColouringPackage.COLOURING_ENTRY: return (EObject)createColouringEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ColouringPackage.FLOW_COLOUR:
				return createFlowColourFromString(eDataType, initialValue);
			case ColouringPackage.COLOURING_ENGINE:
				return createColouringEngineFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ColouringPackage.FLOW_COLOUR:
				return convertFlowColourToString(eDataType, instanceValue);
			case ColouringPackage.COLOURING_ENGINE:
				return convertColouringEngineToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColouringTable createColouringTable() {
		ColouringTable colouringTable = new ColouringTable();
		return colouringTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<PrimitiveEnd, FlowColour> createColouringEntry() {
		ColouringEntry colouringEntry = new ColouringEntry();
		return colouringEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Colouring createColouring() {
		Colouring colouring = new Colouring();
		return colouring;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowColour createFlowColourFromString(EDataType eDataType, String initialValue) {
		FlowColour result = FlowColour.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFlowColourToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColouringPackage getColouringPackage() {
		return (ColouringPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ColouringPackage getPackage() {
		return ColouringPackage.eINSTANCE;
	}

} //ColouringFactory
