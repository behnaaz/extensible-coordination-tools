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
package org.ect.reo.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.reo.ReoPackage;
import org.ect.reo.animation.AnimationPackage;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.colouring.ColouringPackage;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.ect.reo.components.ComponentsFactory
 * @model kind="package"
 * @generated
 */
public class ComponentsPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "components";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/reo/components";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "components";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ComponentsPackage eINSTANCE = org.ect.reo.components.ComponentsPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.reo.components.SingleEndComponent <em>Single End Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.components.SingleEndComponent
	 * @see org.ect.reo.components.ComponentsPackage#getSingleEndComponent()
	 * @generated
	 */
	public static final int SINGLE_END_COMPONENT = 2;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__ARRIVAL_RATE = ReoPackage.COMPONENT__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__PROCESSING_DELAY = ReoPackage.COMPONENT__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__RECONF_ACTIONS = ReoPackage.COMPONENT__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__PROPERTIES = ReoPackage.COMPONENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__SOURCE_ENDS = ReoPackage.COMPONENT__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__SINK_ENDS = ReoPackage.COMPONENT__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__NAME = ReoPackage.COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__TYPE_URI = ReoPackage.COMPONENT__TYPE_URI;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__RESOLVED = ReoPackage.COMPONENT__RESOLVED;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__FOREGROUND_COLOR = ReoPackage.COMPONENT__FOREGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__SYNCHRONOUS = ReoPackage.COMPONENT__SYNCHRONOUS;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__MODULE = ReoPackage.COMPONENT__MODULE;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__EXPRESSION = ReoPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__END = ReoPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Requests</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT__REQUESTS = ReoPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Single End Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SINGLE_END_COMPONENT_FEATURE_COUNT = ReoPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.ect.reo.components.Reader <em>Reader</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.components.Reader
	 * @see org.ect.reo.components.ComponentsPackage#getReader()
	 * @generated
	 */
	public static final int READER = 0;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__ARRIVAL_RATE = SINGLE_END_COMPONENT__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__PROCESSING_DELAY = SINGLE_END_COMPONENT__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__RECONF_ACTIONS = SINGLE_END_COMPONENT__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__PROPERTIES = SINGLE_END_COMPONENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__SOURCE_ENDS = SINGLE_END_COMPONENT__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__SINK_ENDS = SINGLE_END_COMPONENT__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__NAME = SINGLE_END_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__TYPE_URI = SINGLE_END_COMPONENT__TYPE_URI;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__RESOLVED = SINGLE_END_COMPONENT__RESOLVED;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__FOREGROUND_COLOR = SINGLE_END_COMPONENT__FOREGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__SYNCHRONOUS = SINGLE_END_COMPONENT__SYNCHRONOUS;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__MODULE = SINGLE_END_COMPONENT__MODULE;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__EXPRESSION = SINGLE_END_COMPONENT__EXPRESSION;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__END = SINGLE_END_COMPONENT__END;

	/**
	 * The feature id for the '<em><b>Requests</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__REQUESTS = SINGLE_END_COMPONENT__REQUESTS;

	/**
	 * The feature id for the '<em><b>Source End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER__SOURCE_END = SINGLE_END_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reader</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int READER_FEATURE_COUNT = SINGLE_END_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.components.Writer <em>Writer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.components.Writer
	 * @see org.ect.reo.components.ComponentsPackage#getWriter()
	 * @generated
	 */
	public static final int WRITER = 1;

	/**
	 * The feature id for the '<em><b>Arrival Rate</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__ARRIVAL_RATE = SINGLE_END_COMPONENT__ARRIVAL_RATE;

	/**
	 * The feature id for the '<em><b>Processing Delay</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__PROCESSING_DELAY = SINGLE_END_COMPONENT__PROCESSING_DELAY;

	/**
	 * The feature id for the '<em><b>Reconf Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__RECONF_ACTIONS = SINGLE_END_COMPONENT__RECONF_ACTIONS;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__PROPERTIES = SINGLE_END_COMPONENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Source Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__SOURCE_ENDS = SINGLE_END_COMPONENT__SOURCE_ENDS;

	/**
	 * The feature id for the '<em><b>Sink Ends</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__SINK_ENDS = SINGLE_END_COMPONENT__SINK_ENDS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__NAME = SINGLE_END_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Type URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__TYPE_URI = SINGLE_END_COMPONENT__TYPE_URI;

	/**
	 * The feature id for the '<em><b>Resolved</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__RESOLVED = SINGLE_END_COMPONENT__RESOLVED;

	/**
	 * The feature id for the '<em><b>Foreground Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__FOREGROUND_COLOR = SINGLE_END_COMPONENT__FOREGROUND_COLOR;

	/**
	 * The feature id for the '<em><b>Synchronous</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__SYNCHRONOUS = SINGLE_END_COMPONENT__SYNCHRONOUS;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__MODULE = SINGLE_END_COMPONENT__MODULE;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__EXPRESSION = SINGLE_END_COMPONENT__EXPRESSION;

	/**
	 * The feature id for the '<em><b>End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__END = SINGLE_END_COMPONENT__END;

	/**
	 * The feature id for the '<em><b>Requests</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__REQUESTS = SINGLE_END_COMPONENT__REQUESTS;

	/**
	 * The feature id for the '<em><b>Sink End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER__SINK_END = SINGLE_END_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Writer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRITER_FEATURE_COUNT = SINGLE_END_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass readerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass writerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleEndComponentEClass = null;

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
	 * @see org.ect.reo.components.ComponentsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ComponentsPackage() {
		super(eNS_URI, ComponentsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ComponentsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ComponentsPackage init() {
		if (isInited) return (ComponentsPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI);

		// Obtain or create and register package
		ComponentsPackage theComponentsPackage = (ComponentsPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ComponentsPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ComponentsPackage());

		isInited = true;

		// Obtain or create and register interdependencies
		ReoPackage theReoPackage = (ReoPackage)(EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) instanceof ReoPackage ? EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) : ReoPackage.eINSTANCE);
		ChannelsPackage theChannelsPackage = (ChannelsPackage)(EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) instanceof ChannelsPackage ? EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) : ChannelsPackage.eINSTANCE);
		ColouringPackage theColouringPackage = (ColouringPackage)(EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) instanceof ColouringPackage ? EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) : ColouringPackage.eINSTANCE);
		AnimationPackage theAnimationPackage = (AnimationPackage)(EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) instanceof AnimationPackage ? EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI) : AnimationPackage.eINSTANCE);

		// Create package meta-data objects
		theComponentsPackage.createPackageContents();
		theReoPackage.createPackageContents();
		theChannelsPackage.createPackageContents();
		theColouringPackage.createPackageContents();
		theAnimationPackage.createPackageContents();

		// Initialize created meta-data
		theComponentsPackage.initializePackageContents();
		theReoPackage.initializePackageContents();
		theChannelsPackage.initializePackageContents();
		theColouringPackage.initializePackageContents();
		theAnimationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theComponentsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ComponentsPackage.eNS_URI, theComponentsPackage);
		return theComponentsPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.components.Reader <em>Reader</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reader</em>'.
	 * @see org.ect.reo.components.Reader
	 * @generated
	 */
	public EClass getReader() {
		return readerEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.components.Reader#getSourceEnd <em>Source End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source End</em>'.
	 * @see org.ect.reo.components.Reader#getSourceEnd()
	 * @see #getReader()
	 * @generated
	 */
	public EReference getReader_SourceEnd() {
		return (EReference)readerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.components.Writer <em>Writer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Writer</em>'.
	 * @see org.ect.reo.components.Writer
	 * @generated
	 */
	public EClass getWriter() {
		return writerEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.components.Writer#getSinkEnd <em>Sink End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sink End</em>'.
	 * @see org.ect.reo.components.Writer#getSinkEnd()
	 * @see #getWriter()
	 * @generated
	 */
	public EReference getWriter_SinkEnd() {
		return (EReference)writerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.components.SingleEndComponent <em>Single End Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single End Component</em>'.
	 * @see org.ect.reo.components.SingleEndComponent
	 * @generated
	 */
	public EClass getSingleEndComponent() {
		return singleEndComponentEClass;
	}


	/**
	 * Returns the meta object for the reference '{@link org.ect.reo.components.SingleEndComponent#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End</em>'.
	 * @see org.ect.reo.components.SingleEndComponent#getEnd()
	 * @see #getSingleEndComponent()
	 * @generated
	 */
	public EReference getSingleEndComponent_End() {
		return (EReference)singleEndComponentEClass.getEStructuralFeatures().get(0);
	}


	/**
	 * Returns the meta object for the attribute '{@link org.ect.reo.components.SingleEndComponent#getRequests <em>Requests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requests</em>'.
	 * @see org.ect.reo.components.SingleEndComponent#getRequests()
	 * @see #getSingleEndComponent()
	 * @generated
	 */
	public EAttribute getSingleEndComponent_Requests() {
		return (EAttribute)singleEndComponentEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ComponentsFactory getComponentsFactory() {
		return (ComponentsFactory)getEFactoryInstance();
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
		readerEClass = createEClass(READER);
		createEReference(readerEClass, READER__SOURCE_END);

		writerEClass = createEClass(WRITER);
		createEReference(writerEClass, WRITER__SINK_END);

		singleEndComponentEClass = createEClass(SINGLE_END_COMPONENT);
		createEReference(singleEndComponentEClass, SINGLE_END_COMPONENT__END);
		createEAttribute(singleEndComponentEClass, SINGLE_END_COMPONENT__REQUESTS);
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
		ReoPackage theReoPackage = (ReoPackage)EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		readerEClass.getESuperTypes().add(this.getSingleEndComponent());
		writerEClass.getESuperTypes().add(this.getSingleEndComponent());
		singleEndComponentEClass.getESuperTypes().add(theReoPackage.getComponent());
		singleEndComponentEClass.getESuperTypes().add(theReoPackage.getDataAware());

		// Initialize classes and features; add operations and parameters
		initEClass(readerEClass, Reader.class, "Reader", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReader_SourceEnd(), theReoPackage.getSourceEnd(), null, "sourceEnd", null, 0, 1, Reader.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(writerEClass, Writer.class, "Writer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWriter_SinkEnd(), theReoPackage.getSinkEnd(), null, "sinkEnd", null, 0, 1, Writer.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(singleEndComponentEClass, SingleEndComponent.class, "SingleEndComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleEndComponent_End(), theReoPackage.getPrimitiveEnd(), null, "end", null, 0, 1, SingleEndComponent.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSingleEndComponent_Requests(), ecorePackage.getEInt(), "requests", "1", 0, 1, SingleEndComponent.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
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
		 * The meta object literal for the '{@link org.ect.reo.components.Reader <em>Reader</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.components.Reader
		 * @see org.ect.reo.components.ComponentsPackage#getReader()
		 * @generated
		 */
		public static final EClass READER = eINSTANCE.getReader();

		/**
		 * The meta object literal for the '<em><b>Source End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference READER__SOURCE_END = eINSTANCE.getReader_SourceEnd();

		/**
		 * The meta object literal for the '{@link org.ect.reo.components.Writer <em>Writer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.components.Writer
		 * @see org.ect.reo.components.ComponentsPackage#getWriter()
		 * @generated
		 */
		public static final EClass WRITER = eINSTANCE.getWriter();

		/**
		 * The meta object literal for the '<em><b>Sink End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference WRITER__SINK_END = eINSTANCE.getWriter_SinkEnd();

		/**
		 * The meta object literal for the '{@link org.ect.reo.components.SingleEndComponent <em>Single End Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.components.SingleEndComponent
		 * @see org.ect.reo.components.ComponentsPackage#getSingleEndComponent()
		 * @generated
		 */
		public static final EClass SINGLE_END_COMPONENT = eINSTANCE.getSingleEndComponent();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SINGLE_END_COMPONENT__END = eINSTANCE.getSingleEndComponent_End();

		/**
		 * The meta object literal for the '<em><b>Requests</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute SINGLE_END_COMPONENT__REQUESTS = eINSTANCE.getSingleEndComponent_Requests();

	}

} //ComponentsPackage
