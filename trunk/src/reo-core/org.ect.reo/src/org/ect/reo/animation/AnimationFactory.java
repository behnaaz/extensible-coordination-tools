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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.ect.reo.animation.AnimationPackage
 * @generated
 */
public class AnimationFactory extends EFactoryImpl {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AnimationFactory eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AnimationFactory init() {
		try {
			AnimationFactory theAnimationFactory = (AnimationFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.cwi.nl/reo/animation"); 
			if (theAnimationFactory != null) {
				return theAnimationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AnimationFactory();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnimationFactory() {
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
			case AnimationPackage.ANIMATION: return createAnimation();
			case AnimationPackage.ANIMATION_TABLE: return createAnimationTable();
			case AnimationPackage.CREATE_STEP: return createCreateStep();
			case AnimationPackage.SEND_STEP: return createSendStep();
			case AnimationPackage.RECEIVE_STEP: return createReceiveStep();
			case AnimationPackage.REPLICATE_STEP: return createReplicateStep();
			case AnimationPackage.DESTROY_STEP: return createDestroyStep();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Animation createAnimation() {
		Animation animation = new Animation();
		return animation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnimationTable createAnimationTable() {
		AnimationTable animationTable = new AnimationTable();
		return animationTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CreateStep createCreateStep() {
		CreateStep createStep = new CreateStep();
		return createStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SendStep createSendStep() {
		SendStep sendStep = new SendStep();
		return sendStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReceiveStep createReceiveStep() {
		ReceiveStep receiveStep = new ReceiveStep();
		return receiveStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReplicateStep createReplicateStep() {
		ReplicateStep replicateStep = new ReplicateStep();
		return replicateStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DestroyStep createDestroyStep() {
		DestroyStep destroyStep = new DestroyStep();
		return destroyStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnimationPackage getAnimationPackage() {
		return (AnimationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AnimationPackage getPackage() {
		return AnimationPackage.eINSTANCE;
	}

} //AnimationFactory
