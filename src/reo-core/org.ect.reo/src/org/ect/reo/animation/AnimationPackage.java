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
package org.ect.reo.animation;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.ect.reo.ReoPackage;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.colouring.ColouringPackage;
import org.ect.reo.components.ComponentsPackage;



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
 * @see org.ect.reo.animation.AnimationFactory
 * @model kind="package"
 * @generated
 */
public class AnimationPackage extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "animation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.cwi.nl/reo/animation";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "animation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AnimationPackage eINSTANCE = org.ect.reo.animation.AnimationPackage.init();

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.Animatable <em>Animatable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.Animatable
	 * @see org.ect.reo.animation.AnimationPackage#getAnimatable()
	 * @generated
	 */
	public static final int ANIMATABLE = 0;

	/**
	 * The number of structural features of the '<em>Animatable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATABLE_FEATURE_COUNT = ColouringPackage.COLOURABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.Animation <em>Animation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.Animation
	 * @see org.ect.reo.animation.AnimationPackage#getAnimation()
	 * @generated
	 */
	public static final int ANIMATION = 1;

	/**
	 * The feature id for the '<em><b>Colours</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION__COLOURS = ColouringPackage.COLOURING__COLOURS;

	/**
	 * The feature id for the '<em><b>Next Colouring Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION__NEXT_COLOURING_TABLE = ColouringPackage.COLOURING__NEXT_COLOURING_TABLE;

	/**
	 * The feature id for the '<em><b>Parts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION__PARTS = ColouringPackage.COLOURING__PARTS;

	/**
	 * The feature id for the '<em><b>Steps</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION__STEPS = ColouringPackage.COLOURING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Animation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_FEATURE_COUNT = ColouringPackage.COLOURING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.AnimationStep <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.AnimationStep
	 * @see org.ect.reo.animation.AnimationPackage#getAnimationStep()
	 * @generated
	 */
	public static final int ANIMATION_STEP = 3;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.AnimationTable <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.AnimationTable
	 * @see org.ect.reo.animation.AnimationPackage#getAnimationTable()
	 * @generated
	 */
	public static final int ANIMATION_TABLE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_TABLE__NAME = ColouringPackage.COLOURING_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Colourings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_TABLE__COLOURINGS = ColouringPackage.COLOURING_TABLE__COLOURINGS;

	/**
	 * The feature id for the '<em><b>Colours</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_TABLE__COLOURS = ColouringPackage.COLOURING_TABLE__COLOURS;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_TABLE_FEATURE_COUNT = ColouringPackage.COLOURING_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Active Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_STEP__ACTIVE_ENDS = 0;

	/**
	 * The feature id for the '<em><b>Animation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_STEP__ANIMATION = 1;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANIMATION_STEP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.CreateStep <em>Create Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.CreateStep
	 * @see org.ect.reo.animation.AnimationPackage#getCreateStep()
	 * @generated
	 */
	public static final int CREATE_STEP = 4;

	/**
	 * The feature id for the '<em><b>Active Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_STEP__ACTIVE_ENDS = ANIMATION_STEP__ACTIVE_ENDS;

	/**
	 * The feature id for the '<em><b>Animation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_STEP__ANIMATION = ANIMATION_STEP__ANIMATION;

	/**
	 * The number of structural features of the '<em>Create Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_STEP_FEATURE_COUNT = ANIMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.SendStep <em>Send Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.SendStep
	 * @see org.ect.reo.animation.AnimationPackage#getSendStep()
	 * @generated
	 */
	public static final int SEND_STEP = 5;

	/**
	 * The feature id for the '<em><b>Active Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEND_STEP__ACTIVE_ENDS = ANIMATION_STEP__ACTIVE_ENDS;

	/**
	 * The feature id for the '<em><b>Animation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEND_STEP__ANIMATION = ANIMATION_STEP__ANIMATION;

	/**
	 * The number of structural features of the '<em>Send Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEND_STEP_FEATURE_COUNT = ANIMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.ReceiveStep <em>Receive Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.ReceiveStep
	 * @see org.ect.reo.animation.AnimationPackage#getReceiveStep()
	 * @generated
	 */
	public static final int RECEIVE_STEP = 6;

	/**
	 * The feature id for the '<em><b>Active Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECEIVE_STEP__ACTIVE_ENDS = ANIMATION_STEP__ACTIVE_ENDS;

	/**
	 * The feature id for the '<em><b>Animation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECEIVE_STEP__ANIMATION = ANIMATION_STEP__ANIMATION;

	/**
	 * The number of structural features of the '<em>Receive Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int RECEIVE_STEP_FEATURE_COUNT = ANIMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.ReplicateStep <em>Replicate Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.ReplicateStep
	 * @see org.ect.reo.animation.AnimationPackage#getReplicateStep()
	 * @generated
	 */
	public static final int REPLICATE_STEP = 7;

	/**
	 * The feature id for the '<em><b>Active Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REPLICATE_STEP__ACTIVE_ENDS = ANIMATION_STEP__ACTIVE_ENDS;

	/**
	 * The feature id for the '<em><b>Animation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REPLICATE_STEP__ANIMATION = ANIMATION_STEP__ANIMATION;

	/**
	 * The number of structural features of the '<em>Replicate Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REPLICATE_STEP_FEATURE_COUNT = ANIMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.ect.reo.animation.DestroyStep <em>Destroy Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.ect.reo.animation.DestroyStep
	 * @see org.ect.reo.animation.AnimationPackage#getDestroyStep()
	 * @generated
	 */
	public static final int DESTROY_STEP = 8;

	/**
	 * The feature id for the '<em><b>Active Ends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DESTROY_STEP__ACTIVE_ENDS = ANIMATION_STEP__ACTIVE_ENDS;

	/**
	 * The feature id for the '<em><b>Animation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DESTROY_STEP__ANIMATION = ANIMATION_STEP__ANIMATION;

	/**
	 * The number of structural features of the '<em>Destroy Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int DESTROY_STEP_FEATURE_COUNT = ANIMATION_STEP_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass animatableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass animationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass animationStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass createStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sendStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass receiveStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass replicateStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass destroyStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass animationTableEClass = null;

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
	 * @see org.ect.reo.animation.AnimationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AnimationPackage() {
		super(eNS_URI, AnimationFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AnimationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AnimationPackage init() {
		if (isInited) return (AnimationPackage)EPackage.Registry.INSTANCE.getEPackage(AnimationPackage.eNS_URI);

		// Obtain or create and register package
		AnimationPackage theAnimationPackage = (AnimationPackage)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AnimationPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AnimationPackage());

		isInited = true;

		// Obtain or create and register interdependencies
		ReoPackage theReoPackage = (ReoPackage)(EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) instanceof ReoPackage ? EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI) : ReoPackage.eINSTANCE);
		ChannelsPackage theChannelsPackage = (ChannelsPackage)(EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) instanceof ChannelsPackage ? EPackage.Registry.INSTANCE.getEPackage(ChannelsPackage.eNS_URI) : ChannelsPackage.eINSTANCE);
		ColouringPackage theColouringPackage = (ColouringPackage)(EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) instanceof ColouringPackage ? EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI) : ColouringPackage.eINSTANCE);
		ComponentsPackage theComponentsPackage = (ComponentsPackage)(EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) instanceof ComponentsPackage ? EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI) : ComponentsPackage.eINSTANCE);

		// Create package meta-data objects
		theAnimationPackage.createPackageContents();
		theReoPackage.createPackageContents();
		theChannelsPackage.createPackageContents();
		theColouringPackage.createPackageContents();
		theComponentsPackage.createPackageContents();

		// Initialize created meta-data
		theAnimationPackage.initializePackageContents();
		theReoPackage.initializePackageContents();
		theChannelsPackage.initializePackageContents();
		theColouringPackage.initializePackageContents();
		theComponentsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAnimationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AnimationPackage.eNS_URI, theAnimationPackage);
		return theAnimationPackage;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.Animatable <em>Animatable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Animatable</em>'.
	 * @see org.ect.reo.animation.Animatable
	 * @generated
	 */
	public EClass getAnimatable() {
		return animatableEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.Animation <em>Animation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Animation</em>'.
	 * @see org.ect.reo.animation.Animation
	 * @generated
	 */
	public EClass getAnimation() {
		return animationEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.ect.reo.animation.Animation#getSteps <em>Steps</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Steps</em>'.
	 * @see org.ect.reo.animation.Animation#getSteps()
	 * @see #getAnimation()
	 * @generated
	 */
	public EReference getAnimation_Steps() {
		return (EReference)animationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.AnimationStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see org.ect.reo.animation.AnimationStep
	 * @generated
	 */
	public EClass getAnimationStep() {
		return animationStepEClass;
	}

	/**
	 * Returns the meta object for the reference list '{@link org.ect.reo.animation.AnimationStep#getActiveEnds <em>Active Ends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Active Ends</em>'.
	 * @see org.ect.reo.animation.AnimationStep#getActiveEnds()
	 * @see #getAnimationStep()
	 * @generated
	 */
	public EReference getAnimationStep_ActiveEnds() {
		return (EReference)animationStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the container reference '{@link org.ect.reo.animation.AnimationStep#getAnimation <em>Animation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Animation</em>'.
	 * @see org.ect.reo.animation.AnimationStep#getAnimation()
	 * @see #getAnimationStep()
	 * @generated
	 */
	public EReference getAnimationStep_Animation() {
		return (EReference)animationStepEClass.getEStructuralFeatures().get(1);
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.CreateStep <em>Create Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Create Step</em>'.
	 * @see org.ect.reo.animation.CreateStep
	 * @generated
	 */
	public EClass getCreateStep() {
		return createStepEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.SendStep <em>Send Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Send Step</em>'.
	 * @see org.ect.reo.animation.SendStep
	 * @generated
	 */
	public EClass getSendStep() {
		return sendStepEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.ReceiveStep <em>Receive Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Receive Step</em>'.
	 * @see org.ect.reo.animation.ReceiveStep
	 * @generated
	 */
	public EClass getReceiveStep() {
		return receiveStepEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.ReplicateStep <em>Replicate Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Replicate Step</em>'.
	 * @see org.ect.reo.animation.ReplicateStep
	 * @generated
	 */
	public EClass getReplicateStep() {
		return replicateStepEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.DestroyStep <em>Destroy Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Destroy Step</em>'.
	 * @see org.ect.reo.animation.DestroyStep
	 * @generated
	 */
	public EClass getDestroyStep() {
		return destroyStepEClass;
	}


	/**
	 * Returns the meta object for class '{@link org.ect.reo.animation.AnimationTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see org.ect.reo.animation.AnimationTable
	 * @generated
	 */
	public EClass getAnimationTable() {
		return animationTableEClass;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public AnimationFactory getAnimationFactory() {
		return (AnimationFactory)getEFactoryInstance();
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
		animatableEClass = createEClass(ANIMATABLE);

		animationEClass = createEClass(ANIMATION);
		createEReference(animationEClass, ANIMATION__STEPS);

		animationTableEClass = createEClass(ANIMATION_TABLE);

		animationStepEClass = createEClass(ANIMATION_STEP);
		createEReference(animationStepEClass, ANIMATION_STEP__ACTIVE_ENDS);
		createEReference(animationStepEClass, ANIMATION_STEP__ANIMATION);

		createStepEClass = createEClass(CREATE_STEP);

		sendStepEClass = createEClass(SEND_STEP);

		receiveStepEClass = createEClass(RECEIVE_STEP);

		replicateStepEClass = createEClass(REPLICATE_STEP);

		destroyStepEClass = createEClass(DESTROY_STEP);
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
		ColouringPackage theColouringPackage = (ColouringPackage)EPackage.Registry.INSTANCE.getEPackage(ColouringPackage.eNS_URI);
		ReoPackage theReoPackage = (ReoPackage)EPackage.Registry.INSTANCE.getEPackage(ReoPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		animatableEClass.getESuperTypes().add(theColouringPackage.getColourable());
		animationEClass.getESuperTypes().add(theColouringPackage.getColouring());
		animationTableEClass.getESuperTypes().add(theColouringPackage.getColouringTable());
		createStepEClass.getESuperTypes().add(this.getAnimationStep());
		sendStepEClass.getESuperTypes().add(this.getAnimationStep());
		receiveStepEClass.getESuperTypes().add(this.getAnimationStep());
		replicateStepEClass.getESuperTypes().add(this.getAnimationStep());
		destroyStepEClass.getESuperTypes().add(this.getAnimationStep());

		// Initialize classes and features; add operations and parameters
		initEClass(animatableEClass, Animatable.class, "Animatable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(animatableEClass, this.getAnimationTable(), "getAnimationTable", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(animationEClass, Animation.class, "Animation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnimation_Steps(), this.getAnimationStep(), this.getAnimationStep_Animation(), "steps", null, 0, -1, Animation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(animationEClass, this.getAnimation(), "merge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAnimation(), "animation", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(animationEClass, this.getAnimation(), "append", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAnimation(), "animation", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(animationTableEClass, AnimationTable.class, "AnimationTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(animationStepEClass, AnimationStep.class, "AnimationStep", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnimationStep_ActiveEnds(), theReoPackage.getPrimitiveEnd(), null, "activeEnds", null, 0, -1, AnimationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnimationStep_Animation(), this.getAnimation(), this.getAnimation_Steps(), "animation", null, 0, 1, AnimationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(createStepEClass, CreateStep.class, "CreateStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sendStepEClass, SendStep.class, "SendStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(receiveStepEClass, ReceiveStep.class, "ReceiveStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(replicateStepEClass, ReplicateStep.class, "ReplicateStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(destroyStepEClass, DestroyStep.class, "DestroyStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
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
		 * The meta object literal for the '{@link org.ect.reo.animation.Animatable <em>Animatable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.Animatable
		 * @see org.ect.reo.animation.AnimationPackage#getAnimatable()
		 * @generated
		 */
		public static final EClass ANIMATABLE = eINSTANCE.getAnimatable();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.Animation <em>Animation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.Animation
		 * @see org.ect.reo.animation.AnimationPackage#getAnimation()
		 * @generated
		 */
		public static final EClass ANIMATION = eINSTANCE.getAnimation();

		/**
		 * The meta object literal for the '<em><b>Steps</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ANIMATION__STEPS = eINSTANCE.getAnimation_Steps();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.AnimationStep <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.AnimationStep
		 * @see org.ect.reo.animation.AnimationPackage#getAnimationStep()
		 * @generated
		 */
		public static final EClass ANIMATION_STEP = eINSTANCE.getAnimationStep();

		/**
		 * The meta object literal for the '<em><b>Active Ends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ANIMATION_STEP__ACTIVE_ENDS = eINSTANCE.getAnimationStep_ActiveEnds();

		/**
		 * The meta object literal for the '<em><b>Animation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ANIMATION_STEP__ANIMATION = eINSTANCE.getAnimationStep_Animation();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.CreateStep <em>Create Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.CreateStep
		 * @see org.ect.reo.animation.AnimationPackage#getCreateStep()
		 * @generated
		 */
		public static final EClass CREATE_STEP = eINSTANCE.getCreateStep();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.SendStep <em>Send Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.SendStep
		 * @see org.ect.reo.animation.AnimationPackage#getSendStep()
		 * @generated
		 */
		public static final EClass SEND_STEP = eINSTANCE.getSendStep();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.ReceiveStep <em>Receive Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.ReceiveStep
		 * @see org.ect.reo.animation.AnimationPackage#getReceiveStep()
		 * @generated
		 */
		public static final EClass RECEIVE_STEP = eINSTANCE.getReceiveStep();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.ReplicateStep <em>Replicate Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.ReplicateStep
		 * @see org.ect.reo.animation.AnimationPackage#getReplicateStep()
		 * @generated
		 */
		public static final EClass REPLICATE_STEP = eINSTANCE.getReplicateStep();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.DestroyStep <em>Destroy Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.DestroyStep
		 * @see org.ect.reo.animation.AnimationPackage#getDestroyStep()
		 * @generated
		 */
		public static final EClass DESTROY_STEP = eINSTANCE.getDestroyStep();

		/**
		 * The meta object literal for the '{@link org.ect.reo.animation.AnimationTable <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.ect.reo.animation.AnimationTable
		 * @see org.ect.reo.animation.AnimationPackage#getAnimationTable()
		 * @generated
		 */
		public static final EClass ANIMATION_TABLE = eINSTANCE.getAnimationTable();

	}

} //AnimationPackage
