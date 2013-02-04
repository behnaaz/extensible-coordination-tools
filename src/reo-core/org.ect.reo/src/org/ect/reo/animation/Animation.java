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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.ect.reo.colouring.Colouring;



/**
 * @see org.ect.reo.animation.AnimationPackage#getAnimation()
 * @model kind="class"
 * @generated
 */
public class Animation extends Colouring {
	
	/**
	 * Default constructor.
	 * @generated NOT
	 */
	public Animation() {
		super();
	}
	
	/**
	 * Merge the argument animation into this animation.
	 * 
	 * Merging animations two animations results in animation with
	 * a size between max(size1,size2) and size1+size2,  depending
	 * on the contained animation steps.
	 * 
	 * Two animation steps will be merged into one, if there are of
	 * the same type (parallel step). If not, they both will be 
	 * copied into the result animation (sequential). 
	 * 
	 * @model
	 * @generated NOT
	 */
	public void merge(Animation animation) {
		
		//System.out.println("Merging " + animation);
		
		// Add coloring.
		copyColouring(animation);
		
		// Merge the animation steps.
		int i1 = 0, i2 = 0;
		
		while (i1<getSteps().size() || i2<animation.getSteps().size()) {
			
			// This animation is finished.
			if (i1>=getSteps().size()) {
				// Append the rest of the argument animation.
				AnimationStep step = animation.getSteps().get(i2);
				getSteps().add(step.getCopy());
				i1++; i2++;
				continue;
			}
			
			// Argument animation finished.
			if (i2>=animation.getSteps().size()) {
				// Done.
				return;
			}
			
			AnimationStep step1 = getSteps().get(i1);
			AnimationStep step2 = animation.getSteps().get(i2);
			
			if (stepsCompatible(step1, step2)) {
				// Merge the two steps.
				step1.copyActiveEnds(step2);
				i1++;
				i2++;
				continue;
			}
			
			// Incompatible steps. Look ahead and decide which to append first.
			int n1 = findNextCompatibleAnimation(this, i1, step2.getClass());
			int n2 = findNextCompatibleAnimation(animation, i2, step1.getClass());
			
			// Incompatible steps. Look ahead and decide how to append.
			if (n1<=n2) {
				// First this step.
				i1++;
				continue;
			} else {
				// First the other step.
				getSteps().add(i1, step2.getCopy());
				i1++; i2++;
				continue;				
			}
		}
		
	}
	
	
	/*
	 * Helper method for finding the next compatible animation step.
	 */
	private int findNextCompatibleAnimation(Animation animation, int start, Class<?> stepKind) {
		int index = start;
		while (animation.getSteps().size()>index) {
			AnimationStep step = animation.getSteps().get(index);
			if (stepKind.isInstance(step)) return index;
			index++;
		}
		return index;
	}
	
	
	/**
	 * Append an animation at the end of this animation. 
	 * The coloring will be copied, without any checks. 
	 * 
	 * @model
	 * @generated NOT
	 */
	public void append(Animation animation) {

		// Join colorings.
		copyColouring(animation);
		
		for (AnimationStep step : animation.getSteps()) {
			getSteps().add(step.getCopy());
		}
		
	}
	
	// TODO Make that nicer.
	private boolean stepsCompatible(AnimationStep step1, AnimationStep step2) {
		return ((step1 instanceof ReceiveStep) && (step2 instanceof ReceiveStep)) ||
		   ((step1 instanceof SendStep) && (step2 instanceof SendStep)) ||
		   ((step1 instanceof ReplicateStep) && (step2 instanceof ReplicateStep)) ||
		   ((step1 instanceof CreateStep) && (step2 instanceof CreateStep)) ||
		   ((step1 instanceof DestroyStep) && (step2 instanceof DestroyStep));
	}
		
	/**
	 * @generated NOT
	 */
	public AnimationTable getNextAnimationTable() {
		return (AnimationTable) getNextColouringTable();
	}
	
	/**
	 * @generated NOT
	 */
	public void setNextAnimationTable(AnimationTable nextAnimations) {
		setNextColouringTable(nextAnimations);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public Animation getCopy() {
		Animation animation = new Animation();
		animation.copyColouring(this);
		animation.setNextColouringTable(nextColouringTable);
		animation.getParts().addAll(getParts());
		for (AnimationStep step : getSteps()) {
			animation.getSteps().add(step.getCopy());
		}
		return animation;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public int hashCode() {
		int hashCode = super.hashCode() + getSteps().hashCode();
		return hashCode;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public String toString() {
		AnimationPrinter printer = new AnimationPrinter();
		return printer.printAnimation(this);
	}
		
	
	
	

	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */
	
	/**
	 * @see #getSteps()
	 * @generated
	 * @ordered
	 */
	protected EList<AnimationStep> steps;

	/**
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AnimationPackage.Literals.ANIMATION;
	}

	/**
	 * @return the value of the '<em>Steps</em>' containment reference list.
	 * @see org.ect.reo.animation.AnimationPackage#getAnimation_Steps()
	 * @model type="org.ect.reo.animation.AnimationStep" containment="true"
	 * @generated
	 */
	public EList<AnimationStep> getSteps() {
		if (steps == null) {
			steps = new EObjectContainmentWithInverseEList<AnimationStep>(AnimationStep.class, this, AnimationPackage.ANIMATION__STEPS, AnimationPackage.ANIMATION_STEP__ANIMATION);
		}
		return steps;
	}
	
	/**
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnimationPackage.ANIMATION__STEPS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSteps()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}
	
	/**
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AnimationPackage.ANIMATION__STEPS:
				return ((InternalEList<?>)getSteps()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AnimationPackage.ANIMATION__STEPS:
				return getSteps();
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
			case AnimationPackage.ANIMATION__STEPS:
				getSteps().clear();
				getSteps().addAll((Collection<? extends AnimationStep>)newValue);
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
			case AnimationPackage.ANIMATION__STEPS:
				getSteps().clear();
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
			case AnimationPackage.ANIMATION__STEPS:
				return steps != null && !steps.isEmpty();
		}
		return super.eIsSet(featureID);
	}

}
