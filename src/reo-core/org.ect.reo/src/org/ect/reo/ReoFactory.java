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
package org.ect.reo;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 * The <b>Factory</b> for the Reo model.
 * It provides a create method for each non-abstract class of the model.
 * 
 * @see org.ect.reo.ReoPackage
 * @generated
 */
public class ReoFactory extends EFactoryImpl {
	
	/**
	 * Convert a string to a URI.
	 * @generated NOT
	 */
	public URI createURIFromString(EDataType eDataType, String value) {
		return (value!=null) ? URI.createURI(value) : null;
	}
	
	/**
	 * Convert a URI to a string.
	 * @generated NOT
	 */
	public String convertURIToString(EDataType eDataType, Object value) {
		return (value instanceof URI) ? ((URI) value).toString() : null;
	}
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * The singleton instance of the factory.
	 * @generated
	 */
	public static final ReoFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * @generated
	 */
	public static ReoFactory init() {
		try {
			ReoFactory theReoFactory = (ReoFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/reo"); 
			if (theReoFactory != null) {
				return theReoFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ReoFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * @generated
	 */
	public ReoFactory() {
		super();
	}

	/**
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ReoPackage.MODULE: return createModule();
			case ReoPackage.NETWORK: return createNetwork();
			case ReoPackage.CONNECTOR: return createConnector();
			case ReoPackage.COMPONENT: return createComponent();
			case ReoPackage.NODE: return createNode();
			case ReoPackage.SOURCE_END: return createSourceEnd();
			case ReoPackage.SINK_END: return createSinkEnd();
			case ReoPackage.PROPERTY: return createProperty();
			case ReoPackage.RECONF_RULE: return createReconfRule();
			case ReoPackage.RECONF_ACTION: return createReconfAction();
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
			case ReoPackage.NODE_TYPE:
				return createNodeTypeFromString(eDataType, initialValue);
			case ReoPackage.RECONF_TYPE:
				return createReconfTypeFromString(eDataType, initialValue);
			case ReoPackage.URI:
				return createURIFromString(eDataType, initialValue);
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
			case ReoPackage.NODE_TYPE:
				return convertNodeTypeToString(eDataType, instanceValue);
			case ReoPackage.RECONF_TYPE:
				return convertReconfTypeToString(eDataType, instanceValue);
			case ReoPackage.URI:
				return convertURIToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * @generated
	 */
	public Connector createConnector() {
		Connector connector = new Connector();
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component createComponent() {
		Component component = new Component();
		return component;
	}

	/**
	 * @generated
	 */
	public Node createNode() {
		Node node = new Node();
		return node;
	}

	/**
	 * @generated
	 */
	public SourceEnd createSourceEnd() {
		SourceEnd sourceEnd = new SourceEnd();
		return sourceEnd;
	}

	/**
	 * @generated
	 */
	public SinkEnd createSinkEnd() {
		SinkEnd sinkEnd = new SinkEnd();
		return sinkEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createProperty() {
		Property property = new Property();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfRule createReconfRule() {
		ReconfRule reconfRule = new ReconfRule();
		return reconfRule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfAction createReconfAction() {
		ReconfAction reconfAction = new ReconfAction();
		return reconfAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Network createNetwork() {
		Network network = new Network();
		return network;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeType createNodeTypeFromString(EDataType eDataType, String initialValue) {
		NodeType result = NodeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNodeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfType createReconfTypeFromString(EDataType eDataType, String initialValue) {
		ReconfType result = ReconfType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReconfTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * @generated
	 */
	public Module createModule() {
		Module module = new Module();
		return module;
	}

	/**
	 * @generated
	 */
	public ReoPackage getReoPackage() {
		return (ReoPackage)getEPackage();
	}

	/**
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ReoPackage getPackage() {
		return ReoPackage.eINSTANCE;
	}

}
