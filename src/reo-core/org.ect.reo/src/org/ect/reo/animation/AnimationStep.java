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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.reo.PrimitiveEnd;


/**
 * @see org.ect.reo.animation.AnimationPackage#getAnimationStep()
 * @model kind="class" abstract="true"
 * @generated
 */
public abstract class AnimationStep extends MinimalEObjectImpl implements EObject {

	/**
	 * @see #getActiveEnds()
	 * @generated
	 * @ordered
	 */
	protected EList<PrimitiveEnd> activeEnds;

	/**
	 * @generated
	 */
	public AnimationStep() {
		super();
	}

	/**
	 * @generated NOT
	 */
	protected AnimationStep(PrimitiveEnd end) {
		super();
		getActiveEnds().add(end);
	}
	
	/**
	 * @generated NOT
	 */
	protected abstract AnimationStep getCopy();
	
	/**
	 * @generated NOT
	 */
	protected void copyActiveEnds(AnimationStep step) {
		for (int i=0; i<step.getActiveEnds().size(); i++) {
			getActiveEnds().add(step.getActiveEnds().get(i));
		}
	}

	/**
	 * @generated NOT
	 */
	public void replaceActiveEnd(PrimitiveEnd end1, PrimitiveEnd end2) {
		if (getActiveEnds().contains(end1)) {
			int index = getActiveEnds().indexOf(end1);
			getActiveEnds().set(index, end2);
		}
	}
	
	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnimationPackage.Literals.ANIMATION_STEP;
	}

	/**
	 * @return the value of the '<em>Active Ends</em>' reference list.
	 * @see org.ect.reo.animation.AnimationPackage#getAnimationStep_ActiveEnds()
	 * @model
	 * @generated
	 */
	public EList<PrimitiveEnd> getActiveEnds() {
		if (activeEnds == null) {
			activeEnds = new EObjectResolvingEList<PrimitiveEnd>(PrimitiveEnd.class, this, AnimationPackage.ANIMATION_STEP__ACTIVE_ENDS);
		}
		return activeEnds;
	}

	/**
	 * Returns the value of the '<em><b>Animation</b></em>' container reference.
	 * @return the value of the '<em>Animation</em>' container reference.
	 * @see #setAnimation(Animation)
	 * @see org.ect.reo.animation.AnimationPackage#getAnimationStep_Animation()
	 * @see org.ect.reo.animation.Animation#getSteps
	 * @model opposite="steps" transient="false"
	 * @generated
	 */
	public Animation getAnimation() {
		if (eContainerFeatureID() != AnimationPackage.ANIMATION_STEP__ANIMATION) return null;
		return (Animation)eContainer();
	}

	/**
	 * @generated
	 */
	public NotificationChain basicSetAnimation(Animation newAnimation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAnimation, AnimationPackage.ANIMATION_STEP__ANIMATION, msgs);
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.ect.reo.animation.AnimationStep#getAnimation <em>Animation</em>}' container reference.
	 * @param value the new value of the '<em>Animation</em>' container reference.
	 * @see #getAnimation()
	 * @generated
	 */
	public void setAnimation(Animation newAnimation) {
		if (newAnimation != eInternalContainer() || (eContainerFeatureID() != AnimationPackage.ANIMATION_STEP__ANIMATION && newAnimation != null)) {
			if (EcoreUtil.isAncestor(this, newAnimation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAnimation != null)
				msgs = ((InternalEObject)newAnimation).eInverseAdd(this, AnimationPackage.ANIMATION__STEPS, Animation.class, msgs);
			msgs = basicSetAnimation(newAnimation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AnimationPackage.ANIMATION_STEP__ANIMATION, newAnimation, newAnimation));
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnimationPackage.ANIMATION_STEP__ANIMATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAnimation((Animation)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnimationPackage.ANIMATION_STEP__ANIMATION:
				return basicSetAnimation(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case AnimationPackage.ANIMATION_STEP__ANIMATION:
				return eInternalContainer().eInverseRemove(this, AnimationPackage.ANIMATION__STEPS, Animation.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AnimationPackage.ANIMATION_STEP__ACTIVE_ENDS:
				return getActiveEnds();
			case AnimationPackage.ANIMATION_STEP__ANIMATION:
				return getAnimation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AnimationPackage.ANIMATION_STEP__ACTIVE_ENDS:
				getActiveEnds().clear();
				getActiveEnds().addAll((Collection<? extends PrimitiveEnd>)newValue);
				return;
			case AnimationPackage.ANIMATION_STEP__ANIMATION:
				setAnimation((Animation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AnimationPackage.ANIMATION_STEP__ACTIVE_ENDS:
				getActiveEnds().clear();
				return;
			case AnimationPackage.ANIMATION_STEP__ANIMATION:
				setAnimation((Animation)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AnimationPackage.ANIMATION_STEP__ACTIVE_ENDS:
				return activeEnds != null && !activeEnds.isEmpty();
			case AnimationPackage.ANIMATION_STEP__ANIMATION:
				return getAnimation() != null;
		}
		return super.eIsSet(featureID);
	}

}

