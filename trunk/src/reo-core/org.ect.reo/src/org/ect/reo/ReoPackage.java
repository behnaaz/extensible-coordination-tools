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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.reo.animation.AnimationPackage;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.colouring.ColouringPackage;
import org.ect.reo.components.ComponentsPackage;



/**
 * The <b>Package</b> for the Reo model.
 * 
 * @see org.ect.reo.ReoFactory
 * @model kind="package"
 * @generated
 */
public class ReoPackage extends EPackageImpl {
	
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "reo";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/reo";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "reo";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ReoPackage eINSTANCE = org.ect.reo.ReoPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.reo.Nameable <em>Nameable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Nameable
	 * @see org.ect.reo.ReoPackage#getNameable()
	 * @generated
	 */
	public static final int NAMEABLE = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NAMEABLE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Nameable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NAMEABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.Composite <em>Composite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Composite
	 * @see org.ect.reo.ReoPackage#getComposite()
	 * @generated
	 */
	public static final int COMPOSITE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE__NAME = NAMEABLE__NAME;

	/**
	 * The number of structural features of the '<em>Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPOSITE_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.Connector <em>Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Connector
	 * @see org.ect.reo.ReoPackage#getConnector()
	 * @generated
	 */
	public static final int CONNECTOR = 3;

	/**
	 * The meta object id for the '{@link org.ect.reo.Node <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Node
	 * @see org.ect.reo.ReoPackage#getNode()
	 * @generated
	 */
	public static final int NODE = 5;

	/**
	 * The meta object id for the '{@link org.ect.reo.SourceEnd <em>Source End</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.SourceEnd
	 * @see org.ect.reo.ReoPackage#getSourceEnd()
	 * @generated
	 */
	public static final int SOURCE_END = 8;

	/**
	 * The meta object id for the '{@link org.ect.reo.SinkEnd <em>Sink End</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.SinkEnd
	 * @see org.ect.reo.ReoPackage#getSinkEnd()
	 * @generated
	 */
	public static final int SINK_END = 9;

	/**
	 * The meta object id for the '{@link org.ect.reo.Module <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Module
	 * @see org.ect.reo.ReoPackage#getModule()
	 * @generated
	 */
	public static final int MODULE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE__NAME = COMPOSITE__NAME;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE__PROPERTIES = COMPOSITE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connectors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE__CONNECTORS = COMPOSITE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE__COMPONENTS = COMPOSITE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Reconf Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE__RECONF_RULES = COMPOSITE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Active Reconf Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE__ACTIVE_RECONF_RULE = COMPOSITE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MODULE_FEATURE_COUNT = COMPOSITE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.ect.reo.Primitive <em>Primitive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Primitive
	 * @see org.ect.reo.ReoPackage#getPrimitive()
	 * @generated
	 */
	public static final int PRIMITIVE = 6;

	/**
	 * The meta object id for the '{@link org.ect.reo.Component <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Component
	 * @see org.ect.reo.ReoPackage#getComponent()
	 * @generated
	 */
	public static final int COMPONENT = 4;

	/**
	 * The meta object id for the '{@link org.ect.reo.Connectable <em>Connectable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Connectable
	 * @see org.ect.reo.ReoPackage#getConnectable()
	 * @generated
	 */
	public static final int CONNECTABLE = 11;

	/**
	 * The meta object id for the '{@link org.ect.reo.PrimitiveEnd <em>Primitive End</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.PrimitiveEnd
	 * @see org.ect.reo.ReoPackage#getPrimitiveEnd()
	 * @generated
	 */
	public static final int PRIMITIVE_END = 7;

	/**
	 * The meta object id for the '{@link org.ect.reo.Property <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Property
	 * @see org.ect.reo.ReoPackage#getProperty()
	 * @generated
	 */
	public static final int PROPERTY = 10;

	/**
	 * The meta object id for the '{@link org.ect.reo.Delayable <em>Delayable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Delayable
	 * @see org.ect.reo.ReoPackage#getDelayable()
	 * @generated
	 */
	public static final int DELAYABLE = 12;

	/**
	 * The meta object id for the '{@link org.ect.reo.Network <em>Network</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Network
	 * @see org.ect.reo.ReoPackage#getNetwork()
	 * @generated
	 */
	public static final int NETWORK = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NETWORK__NAME = COMPOSITE__NAME;

	/**
	 * The feature id for the '<em><b>Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NETWORK__COMPONENTS = COMPOSITE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connectors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NETWORK__CONNECTORS = COMPOSITE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Colouring Engine</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NETWORK__COLOURING_ENGINE = COMPOSITE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NETWORK_FEATURE_COUNT = COMPOSITE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__RECONF_ACTIONS = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__PROPERTIES = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__NODES = NAMEABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Primitives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__PRIMITIVES = NAMEABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sub Connectors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__SUB_CONNECTORS = NAMEABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__MODULE = NAMEABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__PARENT = NAMEABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Colouring Engine</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR__COLOURING_ENGINE = NAMEABLE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Connectable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTABLE_FEATURE_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE__ARRIVAL_RATE = CONNECTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE__PROCESSING_DELAY = CONNECTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE__RECONF_ACTIONS = CONNECTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE__PROPERTIES = CONNECTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE__SOURCE_ENDS = CONNECTABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE__SINK_ENDS = CONNECTABLE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Primitive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_FEATURE_COUNT = CONNECTABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__ARRIVAL_RATE = PRIMITIVE__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__PROCESSING_DELAY = PRIMITIVE__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__RECONF_ACTIONS = PRIMITIVE__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__PROPERTIES = PRIMITIVE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__SOURCE_ENDS = PRIMITIVE__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__SINK_ENDS = PRIMITIVE__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__NAME = PRIMITIVE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__TYPE_URI = PRIMITIVE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__RESOLVED = PRIMITIVE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__FOREGROUND_COLOR = PRIMITIVE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__SYNCHRONOUS = PRIMITIVE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT__MODULE = PRIMITIVE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT_FEATURE_COUNT = PRIMITIVE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__NAME = CONNECTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__ARRIVAL_RATE = CONNECTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__PROCESSING_DELAY = CONNECTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__RECONF_ACTIONS = CONNECTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__PROPERTIES = CONNECTABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__CONNECTOR = CONNECTABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__SOURCE_ENDS = CONNECTABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__SINK_ENDS = CONNECTABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__TYPE = CONNECTABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Hide</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE__HIDE = CONNECTABLE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NODE_FEATURE_COUNT = CONNECTABLE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_END__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_END__PROPERTIES = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_END_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE_END__NAME = PRIMITIVE_END__NAME;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE_END__PROPERTIES = PRIMITIVE_END__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE_END__NODE = PRIMITIVE_END_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Primitive</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE_END__PRIMITIVE = PRIMITIVE_END_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Source End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SOURCE_END_FEATURE_COUNT = PRIMITIVE_END_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINK_END__NAME = PRIMITIVE_END__NAME;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINK_END__PROPERTIES = PRIMITIVE_END__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINK_END__NODE = PRIMITIVE_END_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Primitive</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINK_END__PRIMITIVE = PRIMITIVE_END_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Sink End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINK_END_FEATURE_COUNT = PRIMITIVE_END_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY__HIDDEN = 3;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_FEATURE_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAYABLE__ARRIVAL_RATE = 0;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAYABLE__PROCESSING_DELAY = 1;

	/**
	 * The number of structural features of the '<em>Delayable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DELAYABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.DataAware <em>Data Aware</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.DataAware
	 * @see org.ect.reo.ReoPackage#getDataAware()
	 * @generated
	 */
	public static final int DATA_AWARE = 14;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DATA_AWARE__EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Data Aware</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DATA_AWARE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.PropertyHolder <em>Property Holder</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.PropertyHolder
	 * @see org.ect.reo.ReoPackage#getPropertyHolder()
	 * @generated
	 */
	public static final int PROPERTY_HOLDER = 15;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_HOLDER__PROPERTIES = 0;

	/**
	 * The number of structural features of the '<em>Property Holder</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_HOLDER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.CustomPrimitive <em>Custom Primitive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.CustomPrimitive
	 * @see org.ect.reo.ReoPackage#getCustomPrimitive()
	 * @generated
	 */
	public static final int CUSTOM_PRIMITIVE = 16;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PRIMITIVE__PROPERTIES = CONNECTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PRIMITIVE__TYPE_URI = CONNECTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PRIMITIVE__RESOLVED = CONNECTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PRIMITIVE__FOREGROUND_COLOR = CONNECTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PRIMITIVE__SYNCHRONOUS = CONNECTABLE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Custom Primitive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_PRIMITIVE_FEATURE_COUNT = CONNECTABLE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.ect.reo.Reconfigurable <em>Reconfigurable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.Reconfigurable
	 * @see org.ect.reo.ReoPackage#getReconfigurable()
	 * @generated
	 */
	public static final int RECONFIGURABLE = 17;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONFIGURABLE__RECONF_ACTIONS = 0;

	/**
	 * The number of structural features of the '<em>Reconfigurable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONFIGURABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.ReconfRule <em>Reconf Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.ReconfRule
	 * @see org.ect.reo.ReoPackage#getReconfRule()
	 * @generated
	 */
	public static final int RECONF_RULE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONF_RULE__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Exported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONF_RULE__EXPORTED = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reconf Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONF_RULE_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.ReconfAction <em>Reconf Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.ReconfAction
	 * @see org.ect.reo.ReoPackage#getReconfAction()
	 * @generated
	 */
	public static final int RECONF_ACTION = 19;

	/**
	 * The feature id for the '<em><b>Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONF_ACTION__RULE = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONF_ACTION__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Reconf Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECONF_ACTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.NodeType <em>Node Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.NodeType
	 * @see org.ect.reo.ReoPackage#getNodeType()
	 * @generated
	 */
	public static final int NODE_TYPE = 20;

	/**
	 * The meta object id for the '{@link org.ect.reo.ReconfType <em>Reconf Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.ReconfType
	 * @see org.ect.reo.ReoPackage#getReconfType()
	 * @generated
	 */
	public static final int RECONF_TYPE = 21;

	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.URI
	 * @see org.ect.reo.ReoPackage#getURI()
	 * @generated
	 */
	public static final int URI = 22;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sourceEndEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sinkEndEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass delayableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nameableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataAwareEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyHolderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customPrimitiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reconfigurableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reconfRuleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reconfActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass networkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum nodeTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum reconfTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveEndEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

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
	 * @see org.ect.reo.ReoPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ReoPackage() {
		super(eNS_URI, ReoFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ReoPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ReoPackage init() {
		if (isInited) return (ReoPackage)EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI);

		// Obtain or create and register package
		ReoPackage theReoPackage = (ReoPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ReoPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ReoPackage());

		isInited = true;

		// Obtain or create and register interdependencies
		ChannelsPackage theChannelsPackage = (ChannelsPackage)(EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) instanceof ChannelsPackage ? EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) : ChannelsPackage.eINSTANCE);
		ColouringPackage theColouringPackage = (ColouringPackage)(EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) instanceof ColouringPackage ? EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) : ColouringPackage.eINSTANCE);
		AnimationPackage theAnimationPackage = (AnimationPackage)(EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) instanceof AnimationPackage ? EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) : AnimationPackage.eINSTANCE);
		ComponentsPackage theComponentsPackage = (ComponentsPackage)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackage ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);

		// Create package meta-data objects
		theReoPackage.createPackageContents();
		theChannelsPackage.createPackageContents();
		theColouringPackage.createPackageContents();
		theAnimationPackage.createPackageContents();
		theComponentsPackage.createPackageContents();

		// Initialize created meta-data
		theReoPackage.initializePackageContents();
		theChannelsPackage.initializePackageContents();
		theColouringPackage.initializePackageContents();
		theAnimationPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theReoPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ReoPackage.eNS_URI, theReoPackage);
		return theReoPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Composite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite</em>'.
	 * @see org.ect.reo.Composite
	 * @generated
	 */
	public EClass getComposite() {
		return compositeEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see org.ect.reo.Connector
	 * @generated
	 */
	public EClass getConnector() {
		return connectorEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Connector#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.ect.reo.Connector#getNodes()
	 * @see #getConnector()
	 * @generated
	 */
	public EReference getConnector_Nodes() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Connector#getPrimitives <em>Primitives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Primitives</em>'.
	 * @see org.ect.reo.Connector#getPrimitives()
	 * @see #getConnector()
	 * @generated
	 */
	public EReference getConnector_Primitives() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Connector#getSubConnectors <em>Sub Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Connectors</em>'.
	 * @see org.ect.reo.Connector#getSubConnectors()
	 * @see #getConnector()
	 * @generated
	 */
	public EReference getConnector_SubConnectors() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for the container reference '{@link org.ect.reo.Connector#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see org.ect.reo.Connector#getModule()
	 * @see #getConnector()
	 * @generated
	 */
	public EReference getConnector_Module() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for the container reference '{@link org.ect.reo.Connector#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see org.ect.reo.Connector#getParent()
	 * @see #getConnector()
	 * @generated
	 */
	public EReference getConnector_Parent() {
		return (EReference)connectorEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Connector#getColouringEngine <em>Colouring Engine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Colouring Engine</em>'.
	 * @see org.ect.reo.Connector#getColouringEngine()
	 * @see #getConnector()
	 * @generated
	 */
	public EAttribute getConnector_ColouringEngine() {
		return (EAttribute)connectorEClass.getEStructuralFeatures().get(5);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.ect.reo.Component
	 * @generated
	 */
	public EClass getComponent() {
		return componentEClass;
	}


	/**
	 * Returns the meta object for the container reference '{@link org.ect.reo.Component#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see org.ect.reo.Component#getModule()
	 * @see #getComponent()
	 * @generated
	 */
	public EReference getComponent_Module() {
		return (EReference)componentEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.SourceEnd <em>Source End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source End</em>'.
	 * @see org.ect.reo.SourceEnd
	 * @generated
	 */
	public EClass getSourceEnd() {
		return sourceEndEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.SourceEnd#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node</em>'.
	 * @see org.ect.reo.SourceEnd#getNode()
	 * @see #getSourceEnd()
	 * @generated
	 */
	public EReference getSourceEnd_Node() {
		return (EReference)sourceEndEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the container reference '{@link org.ect.reo.SourceEnd#getPrimitive <em>Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Primitive</em>'.
	 * @see org.ect.reo.SourceEnd#getPrimitive()
	 * @see #getSourceEnd()
	 * @generated
	 */
	public EReference getSourceEnd_Primitive() {
		return (EReference)sourceEndEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.SinkEnd <em>Sink End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sink End</em>'.
	 * @see org.ect.reo.SinkEnd
	 * @generated
	 */
	public EClass getSinkEnd() {
		return sinkEndEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.SinkEnd#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Node</em>'.
	 * @see org.ect.reo.SinkEnd#getNode()
	 * @see #getSinkEnd()
	 * @generated
	 */
	public EReference getSinkEnd_Node() {
		return (EReference)sinkEndEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the container reference '{@link org.ect.reo.SinkEnd#getPrimitive <em>Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Primitive</em>'.
	 * @see org.ect.reo.SinkEnd#getPrimitive()
	 * @see #getSinkEnd()
	 * @generated
	 */
	public EReference getSinkEnd_Primitive() {
		return (EReference)sinkEndEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.ect.reo.Property
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Property#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see org.ect.reo.Property#getKey()
	 * @see #getProperty()
	 * @generated
	 */
	public EAttribute getProperty_Key() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.ect.reo.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Property#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ect.reo.Property#getType()
	 * @see #getProperty()
	 * @generated
	 */
	public EAttribute getProperty_Type() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Property#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.ect.reo.Property#isHidden()
	 * @see #getProperty()
	 * @generated
	 */
	public EAttribute getProperty_Hidden() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Connectable <em>Connectable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connectable</em>'.
	 * @see org.ect.reo.Connectable
	 * @generated
	 */
	public EClass getConnectable() {
		return connectableEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Delayable <em>Delayable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delayable</em>'.
	 * @see org.ect.reo.Delayable
	 * @generated
	 */
	public EClass getDelayable() {
		return delayableEClass;
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.reo.Delayable#getArrivalRate <em>Arrival Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Arrival Rate</em>'.
	 * @see org.ect.reo.Delayable#getArrivalRate()
	 * @see #getDelayable()
	 * @generated
	 */
	public EAttribute getDelayable_ArrivalRate() {
		return (EAttribute)delayableEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute list '{@link org.ect.reo.Delayable#getProcessingDelay <em>Processing Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Processing Delay</em>'.
	 * @see org.ect.reo.Delayable#getProcessingDelay()
	 * @see #getDelayable()
	 * @generated
	 */
	public EAttribute getDelayable_ProcessingDelay() {
		return (EAttribute)delayableEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nameable</em>'.
	 * @see org.ect.reo.Nameable
	 * @generated
	 */
	public EClass getNameable() {
		return nameableEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Nameable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.ect.reo.Nameable#getName()
	 * @see #getNameable()
	 * @generated
	 */
	public EAttribute getNameable_Name() {
		return (EAttribute)nameableEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.DataAware <em>Data Aware</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Aware</em>'.
	 * @see org.ect.reo.DataAware
	 * @generated
	 */
	public EClass getDataAware() {
		return dataAwareEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.DataAware#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.ect.reo.DataAware#getExpression()
	 * @see #getDataAware()
	 * @generated
	 */
	public EAttribute getDataAware_Expression() {
		return (EAttribute)dataAwareEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.PropertyHolder <em>Property Holder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Holder</em>'.
	 * @see org.ect.reo.PropertyHolder
	 * @generated
	 */
	public EClass getPropertyHolder() {
		return propertyHolderEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.PropertyHolder#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.ect.reo.PropertyHolder#getProperties()
	 * @see #getPropertyHolder()
	 * @generated
	 */
	public EReference getPropertyHolder_Properties() {
		return (EReference)propertyHolderEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.CustomPrimitive <em>Custom Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Primitive</em>'.
	 * @see org.ect.reo.CustomPrimitive
	 * @generated
	 */
	public EClass getCustomPrimitive() {
		return customPrimitiveEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.CustomPrimitive#getTypeURI <em>Type URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type URI</em>'.
	 * @see org.ect.reo.CustomPrimitive#getTypeURI()
	 * @see #getCustomPrimitive()
	 * @generated
	 */
	public EAttribute getCustomPrimitive_TypeURI() {
		return (EAttribute)customPrimitiveEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the containment reference '{@link org.ect.reo.CustomPrimitive#getResolved <em>Resolved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resolved</em>'.
	 * @see org.ect.reo.CustomPrimitive#getResolved()
	 * @see #getCustomPrimitive()
	 * @generated
	 */
	public EReference getCustomPrimitive_Resolved() {
		return (EReference)customPrimitiveEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.CustomPrimitive#getForegroundColor <em>Foreground Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Foreground Color</em>'.
	 * @see org.ect.reo.CustomPrimitive#getForegroundColor()
	 * @see #getCustomPrimitive()
	 * @generated
	 */
	public EAttribute getCustomPrimitive_ForegroundColor() {
		return (EAttribute)customPrimitiveEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.CustomPrimitive#isSynchronous <em>Synchronous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchronous</em>'.
	 * @see org.ect.reo.CustomPrimitive#isSynchronous()
	 * @see #getCustomPrimitive()
	 * @generated
	 */
	public EAttribute getCustomPrimitive_Synchronous() {
		return (EAttribute)customPrimitiveEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Reconfigurable <em>Reconfigurable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconfigurable</em>'.
	 * @see org.ect.reo.Reconfigurable
	 * @generated
	 */
	public EClass getReconfigurable() {
		return reconfigurableEClass;
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Reconfigurable#getReconfActions <em>Reconf Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reconf Actions</em>'.
	 * @see org.ect.reo.Reconfigurable#getReconfActions()
	 * @see #getReconfigurable()
	 * @generated
	 */
	public EReference getReconfigurable_ReconfActions() {
		return (EReference)reconfigurableEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.ReconfRule <em>Reconf Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconf Rule</em>'.
	 * @see org.ect.reo.ReconfRule
	 * @generated
	 */
	public EClass getReconfRule() {
		return reconfRuleEClass;
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.ReconfRule#isExported <em>Exported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exported</em>'.
	 * @see org.ect.reo.ReconfRule#isExported()
	 * @see #getReconfRule()
	 * @generated
	 */
	public EAttribute getReconfRule_Exported() {
		return (EAttribute)reconfRuleEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.ReconfAction <em>Reconf Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconf Action</em>'.
	 * @see org.ect.reo.ReconfAction
	 * @generated
	 */
	public EClass getReconfAction() {
		return reconfActionEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.ReconfAction#getRule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rule</em>'.
	 * @see org.ect.reo.ReconfAction#getRule()
	 * @see #getReconfAction()
	 * @generated
	 */
	public EReference getReconfAction_Rule() {
		return (EReference)reconfActionEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.ReconfAction#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ect.reo.ReconfAction#getType()
	 * @see #getReconfAction()
	 * @generated
	 */
	public EAttribute getReconfAction_Type() {
		return (EAttribute)reconfActionEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Network <em>Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Network</em>'.
	 * @see org.ect.reo.Network
	 * @generated
	 */
	public EClass getNetwork() {
		return networkEClass;
	}


	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.Network#getConnectors <em>Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connectors</em>'.
	 * @see org.ect.reo.Network#getConnectors()
	 * @see #getNetwork()
	 * @generated
	 */
	public EReference getNetwork_Connectors() {
		return (EReference)networkEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Network#getColouringEngine <em>Colouring Engine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Colouring Engine</em>'.
	 * @see org.ect.reo.Network#getColouringEngine()
	 * @see #getNetwork()
	 * @generated
	 */
	public EAttribute getNetwork_ColouringEngine() {
		return (EAttribute)networkEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.Network#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Components</em>'.
	 * @see org.ect.reo.Network#getComponents()
	 * @see #getNetwork()
	 * @generated
	 */
	public EReference getNetwork_Components() {
		return (EReference)networkEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for enum '{@link org.ect.reo.NodeType <em>Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Node Type</em>'.
	 * @see org.ect.reo.NodeType
	 * @generated
	 */
	public EEnum getNodeType() {
		return nodeTypeEEnum;
	}


	/**
	 * Returns the meta object for enum '{@link org.ect.reo.ReconfType <em>Reconf Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Reconf Type</em>'.
	 * @see org.ect.reo.ReconfType
	 * @generated
	 */
	public EEnum getReconfType() {
		return reconfTypeEEnum;
	}


	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	public EDataType getURI() {
		return uriEDataType;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Primitive <em>Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive</em>'.
	 * @see org.ect.reo.Primitive
	 * @generated
	 */
	public EClass getPrimitive() {
		return primitiveEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Primitive#getSourceEnds <em>Source Ends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Source Ends</em>'.
	 * @see org.ect.reo.Primitive#getSourceEnds()
	 * @see #getPrimitive()
	 * @generated
	 */
	public EReference getPrimitive_SourceEnds() {
		return (EReference)primitiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Primitive#getSinkEnds <em>Sink Ends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sink Ends</em>'.
	 * @see org.ect.reo.Primitive#getSinkEnds()
	 * @see #getPrimitive()
	 * @generated
	 */
	public EReference getPrimitive_SinkEnds() {
		return (EReference)primitiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.PrimitiveEnd <em>Primitive End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive End</em>'.
	 * @see org.ect.reo.PrimitiveEnd
	 * @generated
	 */
	public EClass getPrimitiveEnd() {
		return primitiveEndEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see org.ect.reo.Module
	 * @generated
	 */
	public EClass getModule() {
		return moduleEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Module#getConnectors <em>Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connectors</em>'.
	 * @see org.ect.reo.Module#getConnectors()
	 * @see #getModule()
	 * @generated
	 */
	public EReference getModule_Connectors() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Module#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see org.ect.reo.Module#getComponents()
	 * @see #getModule()
	 * @generated
	 */
	public EReference getModule_Components() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.Module#getReconfRules <em>Reconf Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reconf Rules</em>'.
	 * @see org.ect.reo.Module#getReconfRules()
	 * @see #getModule()
	 * @generated
	 */
	public EReference getModule_ReconfRules() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(2);
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.Module#getActiveReconfRule <em>Active Reconf Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Active Reconf Rule</em>'.
	 * @see org.ect.reo.Module#getActiveReconfRule()
	 * @see #getModule()
	 * @generated
	 */
	public EReference getModule_ActiveReconfRule() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.ect.reo.Node
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * Returns the meta object for the container reference '{@link org.ect.reo.Node#getConnector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Connector</em>'.
	 * @see org.ect.reo.Node#getConnector()
	 * @see #getNode()
	 * @generated
	 */
	public EReference getNode_Connector() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.Node#getSourceEnds <em>Source Ends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Source Ends</em>'.
	 * @see org.ect.reo.Node#getSourceEnds()
	 * @see #getNode()
	 * @generated
	 */
	public EReference getNode_SourceEnds() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.Node#getSinkEnds <em>Sink Ends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sink Ends</em>'.
	 * @see org.ect.reo.Node#getSinkEnds()
	 * @see #getNode()
	 * @generated
	 */
	public EReference getNode_SinkEnds() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Node#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.ect.reo.Node#getType()
	 * @see #getNode()
	 * @generated
	 */
	public EAttribute getNode_Type() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(3);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.Node#isHide <em>Hide</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hide</em>'.
	 * @see org.ect.reo.Node#isHide()
	 * @see #getNode()
	 * @generated
	 */
	public EAttribute getNode_Hide() {
		return (EAttribute)nodeEClass.getEStructuralFeatures().get(4);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ReoFactory getReoFactory() {
		return (ReoFactory)getEFactoryInstance();
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
		compositeEClass = createEClass(COMPOSITE);

		moduleEClass = createEClass(MODULE);
		createEReference(moduleEClass, MODULE__CONNECTORS);
		createEReference(moduleEClass, MODULE__COMPONENTS);
		createEReference(moduleEClass, MODULE__RECONF_RULES);
		createEReference(moduleEClass, MODULE__ACTIVE_RECONF_RULE);

		networkEClass = createEClass(NETWORK);
		createEReference(networkEClass, NETWORK__COMPONENTS);
		createEReference(networkEClass, NETWORK__CONNECTORS);
		createEAttribute(networkEClass, NETWORK__COLOURING_ENGINE);

		connectorEClass = createEClass(CONNECTOR);
		createEReference(connectorEClass, CONNECTOR__NODES);
		createEReference(connectorEClass, CONNECTOR__PRIMITIVES);
		createEReference(connectorEClass, CONNECTOR__SUB_CONNECTORS);
		createEReference(connectorEClass, CONNECTOR__MODULE);
		createEReference(connectorEClass, CONNECTOR__PARENT);
		createEAttribute(connectorEClass, CONNECTOR__COLOURING_ENGINE);

		componentEClass = createEClass(COMPONENT);
		createEReference(componentEClass, COMPONENT__MODULE);

		nodeEClass = createEClass(NODE);
		createEReference(nodeEClass, NODE__CONNECTOR);
		createEReference(nodeEClass, NODE__SOURCE_ENDS);
		createEReference(nodeEClass, NODE__SINK_ENDS);
		createEAttribute(nodeEClass, NODE__TYPE);
		createEAttribute(nodeEClass, NODE__HIDE);

		primitiveEClass = createEClass(PRIMITIVE);
		createEReference(primitiveEClass, PRIMITIVE__SOURCE_ENDS);
		createEReference(primitiveEClass, PRIMITIVE__SINK_ENDS);

		primitiveEndEClass = createEClass(PRIMITIVE_END);

		sourceEndEClass = createEClass(SOURCE_END);
		createEReference(sourceEndEClass, SOURCE_END__NODE);
		createEReference(sourceEndEClass, SOURCE_END__PRIMITIVE);

		sinkEndEClass = createEClass(SINK_END);
		createEReference(sinkEndEClass, SINK_END__NODE);
		createEReference(sinkEndEClass, SINK_END__PRIMITIVE);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEAttribute(propertyEClass, PROPERTY__VALUE);
		createEAttribute(propertyEClass, PROPERTY__TYPE);
		createEAttribute(propertyEClass, PROPERTY__HIDDEN);

		connectableEClass = createEClass(CONNECTABLE);

		delayableEClass = createEClass(DELAYABLE);
		createEAttribute(delayableEClass, DELAYABLE__ARRIVAL_RATE);
		createEAttribute(delayableEClass, DELAYABLE__PROCESSING_DELAY);

		nameableEClass = createEClass(NAMEABLE);
		createEAttribute(nameableEClass, NAMEABLE__NAME);

		dataAwareEClass = createEClass(DATA_AWARE);
		createEAttribute(dataAwareEClass, DATA_AWARE__EXPRESSION);

		propertyHolderEClass = createEClass(PROPERTY_HOLDER);
		createEReference(propertyHolderEClass, PROPERTY_HOLDER__PROPERTIES);

		customPrimitiveEClass = createEClass(CUSTOM_PRIMITIVE);
		createEAttribute(customPrimitiveEClass, CUSTOM_PRIMITIVE__TYPE_URI);
		createEReference(customPrimitiveEClass, CUSTOM_PRIMITIVE__RESOLVED);
		createEAttribute(customPrimitiveEClass, CUSTOM_PRIMITIVE__FOREGROUND_COLOR);
		createEAttribute(customPrimitiveEClass, CUSTOM_PRIMITIVE__SYNCHRONOUS);

		reconfigurableEClass = createEClass(RECONFIGURABLE);
		createEReference(reconfigurableEClass, RECONFIGURABLE__RECONF_ACTIONS);

		reconfRuleEClass = createEClass(RECONF_RULE);
		createEAttribute(reconfRuleEClass, RECONF_RULE__EXPORTED);

		reconfActionEClass = createEClass(RECONF_ACTION);
		createEReference(reconfActionEClass, RECONF_ACTION__RULE);
		createEAttribute(reconfActionEClass, RECONF_ACTION__TYPE);

		// Create enums
		nodeTypeEEnum = createEEnum(NODE_TYPE);
		reconfTypeEEnum = createEEnum(RECONF_TYPE);

		// Create data types
		uriEDataType = createEDataType(URI);
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
		ChannelsPackage theChannelsPackage = (ChannelsPackage)EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI);
		ColouringPackage theColouringPackage = (ColouringPackage)EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI);
		AnimationPackage theAnimationPackage = (AnimationPackage)EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI);
		ComponentsPackage theComponentsPackage = (ComponentsPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theChannelsPackage);
		getESubpackages().add(theColouringPackage);
		getESubpackages().add(theAnimationPackage);
		getESubpackages().add(theComponentsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		compositeEClass.getESuperTypes().add(this.getNameable());
		moduleEClass.getESuperTypes().add(this.getComposite());
		moduleEClass.getESuperTypes().add(this.getPropertyHolder());
		networkEClass.getESuperTypes().add(this.getComposite());
		networkEClass.getESuperTypes().add(theAnimationPackage.getAnimatable());
		connectorEClass.getESuperTypes().add(this.getNameable());
		connectorEClass.getESuperTypes().add(theAnimationPackage.getAnimatable());
		connectorEClass.getESuperTypes().add(this.getReconfigurable());
		connectorEClass.getESuperTypes().add(this.getPropertyHolder());
		componentEClass.getESuperTypes().add(this.getPrimitive());
		componentEClass.getESuperTypes().add(this.getNameable());
		componentEClass.getESuperTypes().add(this.getCustomPrimitive());
		nodeEClass.getESuperTypes().add(this.getConnectable());
		nodeEClass.getESuperTypes().add(this.getNameable());
		nodeEClass.getESuperTypes().add(theAnimationPackage.getAnimatable());
		nodeEClass.getESuperTypes().add(this.getDelayable());
		nodeEClass.getESuperTypes().add(this.getReconfigurable());
		nodeEClass.getESuperTypes().add(this.getPropertyHolder());
		primitiveEClass.getESuperTypes().add(this.getConnectable());
		primitiveEClass.getESuperTypes().add(theAnimationPackage.getAnimatable());
		primitiveEClass.getESuperTypes().add(this.getDelayable());
		primitiveEClass.getESuperTypes().add(this.getReconfigurable());
		primitiveEClass.getESuperTypes().add(this.getPropertyHolder());
		primitiveEndEClass.getESuperTypes().add(this.getNameable());
		primitiveEndEClass.getESuperTypes().add(this.getPropertyHolder());
		sourceEndEClass.getESuperTypes().add(this.getPrimitiveEnd());
		sinkEndEClass.getESuperTypes().add(this.getPrimitiveEnd());
		customPrimitiveEClass.getESuperTypes().add(this.getConnectable());
		customPrimitiveEClass.getESuperTypes().add(this.getPropertyHolder());
		reconfRuleEClass.getESuperTypes().add(this.getNameable());

		// Initialize classes and features; add operations and parameters
		initEClass(compositeEClass, Composite.class, "Composite", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(compositeEClass, this.getConnector(), "getConnectors", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(compositeEClass, this.getComponent(), "getComponents", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModule_Connectors(), this.getConnector(), this.getConnector_Module(), "connectors", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_Components(), this.getComponent(), this.getComponent_Module(), "components", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_ReconfRules(), this.getReconfRule(), null, "reconfRules", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_ActiveReconfRule(), this.getReconfRule(), null, "activeReconfRule", null, 0, 1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(networkEClass, Network.class, "Network", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNetwork_Components(), this.getComponent(), null, "components", null, 0, -1, Network.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNetwork_Connectors(), this.getConnector(), null, "connectors", null, 0, -1, Network.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNetwork_ColouringEngine(), theColouringPackage.getColouringEngine(), "colouringEngine", "", 0, 1, Network.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(networkEClass, null, "update", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(connectorEClass, Connector.class, "Connector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnector_Nodes(), this.getNode(), this.getNode_Connector(), "nodes", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnector_Primitives(), this.getPrimitive(), null, "primitives", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnector_SubConnectors(), this.getConnector(), this.getConnector_Parent(), "subConnectors", null, 0, -1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnector_Module(), this.getModule(), this.getModule_Connectors(), "module", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnector_Parent(), this.getConnector(), this.getConnector_SubConnectors(), "parent", null, 0, 1, Connector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnector_ColouringEngine(), theColouringPackage.getColouringEngine(), "colouringEngine", "", 0, 1, Connector.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponent_Module(), this.getModule(), this.getModule_Components(), "module", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNode_Connector(), this.getConnector(), this.getConnector_Nodes(), "connector", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_SourceEnds(), this.getSourceEnd(), this.getSourceEnd_Node(), "sourceEnds", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_SinkEnds(), this.getSinkEnd(), this.getSinkEnd_Node(), "sinkEnds", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNode_Type(), this.getNodeType(), "type", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNode_Hide(), ecorePackage.getEBoolean(), "hide", null, 0, 1, Node.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(nodeEClass, ecorePackage.getEBoolean(), "isSourceNode", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(nodeEClass, ecorePackage.getEBoolean(), "isSinkNode", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(nodeEClass, ecorePackage.getEBoolean(), "isMixedNode", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(primitiveEClass, Primitive.class, "Primitive", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPrimitive_SourceEnds(), this.getSourceEnd(), this.getSourceEnd_Primitive(), "sourceEnds", null, 0, -1, Primitive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPrimitive_SinkEnds(), this.getSinkEnd(), this.getSinkEnd_Primitive(), "sinkEnds", null, 0, -1, Primitive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(primitiveEClass, null, "initializeEnds", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(primitiveEClass, null, "disconnectEnds", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(primitiveEClass, ecorePackage.getEBoolean(), "isConnected", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(primitiveEClass, ecorePackage.getEString(), "getName", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(primitiveEClass, ecorePackage.getEBoolean(), "isSynchronous", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(primitiveEndEClass, PrimitiveEnd.class, "PrimitiveEnd", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(primitiveEndEClass, this.getNode(), "getNode", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(primitiveEndEClass, null, "setNode", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "node", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(primitiveEndEClass, this.getPrimitive(), "getPrimitive", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(primitiveEndEClass, null, "setPrimitive", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getPrimitive(), "primitive", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(sourceEndEClass, SourceEnd.class, "SourceEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSourceEnd_Node(), this.getNode(), this.getNode_SourceEnds(), "node", null, 0, 1, SourceEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSourceEnd_Primitive(), this.getPrimitive(), this.getPrimitive_SourceEnds(), "primitive", null, 0, 1, SourceEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sinkEndEClass, SinkEnd.class, "SinkEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSinkEnd_Node(), this.getNode(), this.getNode_SinkEnds(), "node", null, 0, 1, SinkEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSinkEnd_Primitive(), this.getPrimitive(), this.getPrimitive_SinkEnds(), "primitive", null, 0, 1, SinkEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Key(), ecorePackage.getEString(), "key", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Type(), ecorePackage.getEString(), "type", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Hidden(), ecorePackage.getEBoolean(), "hidden", "false", 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectableEClass, Connectable.class, "Connectable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(connectableEClass, this.getSourceEnd(), "getSourceEnds", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(connectableEClass, this.getSinkEnd(), "getSinkEnds", 0, -1, IS_UNIQUE, IS_ORDERED);

		addEOperation(connectableEClass, this.getPrimitiveEnd(), "getAllEnds", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(delayableEClass, Delayable.class, "Delayable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDelayable_ArrivalRate(), ecorePackage.getEDouble(), "arrivalRate", null, 0, -1, Delayable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelayable_ProcessingDelay(), ecorePackage.getEDouble(), "processingDelay", null, 0, -1, Delayable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nameableEClass, Nameable.class, "Nameable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNameable_Name(), ecorePackage.getEString(), "name", null, 0, 1, Nameable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataAwareEClass, DataAware.class, "DataAware", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataAware_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, DataAware.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyHolderEClass, PropertyHolder.class, "PropertyHolder", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyHolder_Properties(), this.getProperty(), null, "properties", null, 0, -1, PropertyHolder.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getPropertyHolder_Properties().getEKeys().add(this.getProperty_Key());

		initEClass(customPrimitiveEClass, CustomPrimitive.class, "CustomPrimitive", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomPrimitive_TypeURI(), this.getURI(), "typeURI", null, 0, 1, CustomPrimitive.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCustomPrimitive_Resolved(), this.getCustomPrimitive(), null, "resolved", null, 0, 1, CustomPrimitive.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomPrimitive_ForegroundColor(), ecorePackage.getEString(), "foregroundColor", null, 0, 1, CustomPrimitive.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomPrimitive_Synchronous(), ecorePackage.getEBoolean(), "synchronous", null, 0, 1, CustomPrimitive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reconfigurableEClass, Reconfigurable.class, "Reconfigurable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReconfigurable_ReconfActions(), this.getReconfAction(), null, "reconfActions", null, 0, -1, Reconfigurable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reconfRuleEClass, ReconfRule.class, "ReconfRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReconfRule_Exported(), ecorePackage.getEBoolean(), "exported", null, 0, 1, ReconfRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reconfActionEClass, ReconfAction.class, "ReconfAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReconfAction_Rule(), this.getReconfRule(), null, "rule", null, 0, 1, ReconfAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReconfAction_Type(), this.getReconfType(), "type", null, 0, 1, ReconfAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(nodeTypeEEnum, NodeType.class, "NodeType");
		addEEnumLiteral(nodeTypeEEnum, NodeType.REPLICATE);
		addEEnumLiteral(nodeTypeEEnum, NodeType.ROUTE);
		addEEnumLiteral(nodeTypeEEnum, NodeType.JOIN);

		initEEnum(reconfTypeEEnum, ReconfType.class, "ReconfType");
		addEEnumLiteral(reconfTypeEEnum, ReconfType.NONE);
		addEEnumLiteral(reconfTypeEEnum, ReconfType.CREATE);
		addEEnumLiteral(reconfTypeEEnum, ReconfType.DELETE);
		addEEnumLiteral(reconfTypeEEnum, ReconfType.FORBID);

		// Initialize data types
		initEDataType(uriEDataType, org.eclipse.emf.common.util.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
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
		 * The meta object literal for the '{@link org.ect.reo.Composite <em>Composite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Composite
		 * @see org.ect.reo.ReoPackage#getComposite()
		 * @generated
		 */
		public static final EClass COMPOSITE = eINSTANCE.getComposite();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Connector <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Connector
		 * @see org.ect.reo.ReoPackage#getConnector()
		 * @generated
		 */
		public static final EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CONNECTOR__NODES = eINSTANCE.getConnector_Nodes();

		/**
		 * The meta object literal for the '<em><b>Primitives</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CONNECTOR__PRIMITIVES = eINSTANCE.getConnector_Primitives();

		/**
		 * The meta object literal for the '<em><b>Sub Connectors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CONNECTOR__SUB_CONNECTORS = eINSTANCE.getConnector_SubConnectors();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CONNECTOR__MODULE = eINSTANCE.getConnector_Module();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CONNECTOR__PARENT = eINSTANCE.getConnector_Parent();

		/**
		 * The meta object literal for the '<em><b>Colouring Engine</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CONNECTOR__COLOURING_ENGINE = eINSTANCE.getConnector_ColouringEngine();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Component <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Component
		 * @see org.ect.reo.ReoPackage#getComponent()
		 * @generated
		 */
		public static final EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COMPONENT__MODULE = eINSTANCE.getComponent_Module();

		/**
		 * The meta object literal for the '{@link org.ect.reo.SourceEnd <em>Source End</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.SourceEnd
		 * @see org.ect.reo.ReoPackage#getSourceEnd()
		 * @generated
		 */
		public static final EClass SOURCE_END = eINSTANCE.getSourceEnd();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SOURCE_END__NODE = eINSTANCE.getSourceEnd_Node();

		/**
		 * The meta object literal for the '<em><b>Primitive</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SOURCE_END__PRIMITIVE = eINSTANCE.getSourceEnd_Primitive();

		/**
		 * The meta object literal for the '{@link org.ect.reo.SinkEnd <em>Sink End</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.SinkEnd
		 * @see org.ect.reo.ReoPackage#getSinkEnd()
		 * @generated
		 */
		public static final EClass SINK_END = eINSTANCE.getSinkEnd();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SINK_END__NODE = eINSTANCE.getSinkEnd_Node();

		/**
		 * The meta object literal for the '<em><b>Primitive</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SINK_END__PRIMITIVE = eINSTANCE.getSinkEnd_Primitive();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Property <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Property
		 * @see org.ect.reo.ReoPackage#getProperty()
		 * @generated
		 */
		public static final EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PROPERTY__KEY = eINSTANCE.getProperty_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PROPERTY__TYPE = eINSTANCE.getProperty_Type();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute PROPERTY__HIDDEN = eINSTANCE.getProperty_Hidden();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Connectable <em>Connectable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Connectable
		 * @see org.ect.reo.ReoPackage#getConnectable()
		 * @generated
		 */
		public static final EClass CONNECTABLE = eINSTANCE.getConnectable();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Delayable <em>Delayable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Delayable
		 * @see org.ect.reo.ReoPackage#getDelayable()
		 * @generated
		 */
		public static final EClass DELAYABLE = eINSTANCE.getDelayable();

		/**
		 * The meta object literal for the '<em><b>Arrival Rate</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DELAYABLE__ARRIVAL_RATE = eINSTANCE.getDelayable_ArrivalRate();

		/**
		 * The meta object literal for the '<em><b>Processing Delay</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DELAYABLE__PROCESSING_DELAY = eINSTANCE.getDelayable_ProcessingDelay();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Nameable <em>Nameable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Nameable
		 * @see org.ect.reo.ReoPackage#getNameable()
		 * @generated
		 */
		public static final EClass NAMEABLE = eINSTANCE.getNameable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute NAMEABLE__NAME = eINSTANCE.getNameable_Name();

		/**
		 * The meta object literal for the '{@link org.ect.reo.DataAware <em>Data Aware</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.DataAware
		 * @see org.ect.reo.ReoPackage#getDataAware()
		 * @generated
		 */
		public static final EClass DATA_AWARE = eINSTANCE.getDataAware();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute DATA_AWARE__EXPRESSION = eINSTANCE.getDataAware_Expression();

		/**
		 * The meta object literal for the '{@link org.ect.reo.PropertyHolder <em>Property Holder</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.PropertyHolder
		 * @see org.ect.reo.ReoPackage#getPropertyHolder()
		 * @generated
		 */
		public static final EClass PROPERTY_HOLDER = eINSTANCE.getPropertyHolder();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PROPERTY_HOLDER__PROPERTIES = eINSTANCE.getPropertyHolder_Properties();

		/**
		 * The meta object literal for the '{@link org.ect.reo.CustomPrimitive <em>Custom Primitive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.CustomPrimitive
		 * @see org.ect.reo.ReoPackage#getCustomPrimitive()
		 * @generated
		 */
		public static final EClass CUSTOM_PRIMITIVE = eINSTANCE.getCustomPrimitive();

		/**
		 * The meta object literal for the '<em><b>Type URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CUSTOM_PRIMITIVE__TYPE_URI = eINSTANCE.getCustomPrimitive_TypeURI();

		/**
		 * The meta object literal for the '<em><b>Resolved</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CUSTOM_PRIMITIVE__RESOLVED = eINSTANCE.getCustomPrimitive_Resolved();

		/**
		 * The meta object literal for the '<em><b>Foreground Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CUSTOM_PRIMITIVE__FOREGROUND_COLOR = eINSTANCE.getCustomPrimitive_ForegroundColor();

		/**
		 * The meta object literal for the '<em><b>Synchronous</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CUSTOM_PRIMITIVE__SYNCHRONOUS = eINSTANCE.getCustomPrimitive_Synchronous();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Reconfigurable <em>Reconfigurable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Reconfigurable
		 * @see org.ect.reo.ReoPackage#getReconfigurable()
		 * @generated
		 */
		public static final EClass RECONFIGURABLE = eINSTANCE.getReconfigurable();

		/**
		 * The meta object literal for the '<em><b>Reconf Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RECONFIGURABLE__RECONF_ACTIONS = eINSTANCE.getReconfigurable_ReconfActions();

		/**
		 * The meta object literal for the '{@link org.ect.reo.ReconfRule <em>Reconf Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.ReconfRule
		 * @see org.ect.reo.ReoPackage#getReconfRule()
		 * @generated
		 */
		public static final EClass RECONF_RULE = eINSTANCE.getReconfRule();

		/**
		 * The meta object literal for the '<em><b>Exported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RECONF_RULE__EXPORTED = eINSTANCE.getReconfRule_Exported();

		/**
		 * The meta object literal for the '{@link org.ect.reo.ReconfAction <em>Reconf Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.ReconfAction
		 * @see org.ect.reo.ReoPackage#getReconfAction()
		 * @generated
		 */
		public static final EClass RECONF_ACTION = eINSTANCE.getReconfAction();

		/**
		 * The meta object literal for the '<em><b>Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference RECONF_ACTION__RULE = eINSTANCE.getReconfAction_Rule();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute RECONF_ACTION__TYPE = eINSTANCE.getReconfAction_Type();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Network <em>Network</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Network
		 * @see org.ect.reo.ReoPackage#getNetwork()
		 * @generated
		 */
		public static final EClass NETWORK = eINSTANCE.getNetwork();

		/**
		 * The meta object literal for the '<em><b>Connectors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference NETWORK__CONNECTORS = eINSTANCE.getNetwork_Connectors();

		/**
		 * The meta object literal for the '<em><b>Colouring Engine</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute NETWORK__COLOURING_ENGINE = eINSTANCE.getNetwork_ColouringEngine();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference NETWORK__COMPONENTS = eINSTANCE.getNetwork_Components();

		/**
		 * The meta object literal for the '{@link org.ect.reo.NodeType <em>Node Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.NodeType
		 * @see org.ect.reo.ReoPackage#getNodeType()
		 * @generated
		 */
		public static final EEnum NODE_TYPE = eINSTANCE.getNodeType();

		/**
		 * The meta object literal for the '{@link org.ect.reo.ReconfType <em>Reconf Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.ReconfType
		 * @see org.ect.reo.ReoPackage#getReconfType()
		 * @generated
		 */
		public static final EEnum RECONF_TYPE = eINSTANCE.getReconfType();

		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.URI
		 * @see org.ect.reo.ReoPackage#getURI()
		 * @generated
		 */
		public static final EDataType URI = eINSTANCE.getURI();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Primitive <em>Primitive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Primitive
		 * @see org.ect.reo.ReoPackage#getPrimitive()
		 * @generated
		 */
		public static final EClass PRIMITIVE = eINSTANCE.getPrimitive();

		/**
		 * The meta object literal for the '<em><b>Source Ends</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PRIMITIVE__SOURCE_ENDS = eINSTANCE.getPrimitive_SourceEnds();

		/**
		 * The meta object literal for the '<em><b>Sink Ends</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PRIMITIVE__SINK_ENDS = eINSTANCE.getPrimitive_SinkEnds();

		/**
		 * The meta object literal for the '{@link org.ect.reo.PrimitiveEnd <em>Primitive End</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.PrimitiveEnd
		 * @see org.ect.reo.ReoPackage#getPrimitiveEnd()
		 * @generated
		 */
		public static final EClass PRIMITIVE_END = eINSTANCE.getPrimitiveEnd();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Module <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Module
		 * @see org.ect.reo.ReoPackage#getModule()
		 * @generated
		 */
		public static final EClass MODULE = eINSTANCE.getModule();

		/**
		 * The meta object literal for the '<em><b>Connectors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference MODULE__CONNECTORS = eINSTANCE.getModule_Connectors();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference MODULE__COMPONENTS = eINSTANCE.getModule_Components();

		/**
		 * The meta object literal for the '<em><b>Reconf Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference MODULE__RECONF_RULES = eINSTANCE.getModule_ReconfRules();

		/**
		 * The meta object literal for the '<em><b>Active Reconf Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference MODULE__ACTIVE_RECONF_RULE = eINSTANCE.getModule_ActiveReconfRule();

		/**
		 * The meta object literal for the '{@link org.ect.reo.Node <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.Node
		 * @see org.ect.reo.ReoPackage#getNode()
		 * @generated
		 */
		public static final EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference NODE__CONNECTOR = eINSTANCE.getNode_Connector();

		/**
		 * The meta object literal for the '<em><b>Source Ends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference NODE__SOURCE_ENDS = eINSTANCE.getNode_SourceEnds();

		/**
		 * The meta object literal for the '<em><b>Sink Ends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference NODE__SINK_ENDS = eINSTANCE.getNode_SinkEnds();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute NODE__TYPE = eINSTANCE.getNode_Type();

		/**
		 * The meta object literal for the '<em><b>Hide</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute NODE__HIDE = eINSTANCE.getNode_Hide();

}

} //ReoPackage
