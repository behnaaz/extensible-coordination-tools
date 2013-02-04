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

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * This is an abstract interpreter class for Reo animations.
 * @generated NOT
 * @author Christian Krause
 */
public abstract class AbstractAnimationInterpreter {
	
	// Show no-flow animations?
	private boolean showNoFlow = true;
	
	// Number of animations.
	private int animationCount = 0;
	
	/**
	 * Start a movie. A movie is a sequence of animations.
	 */
	protected abstract void startMovie(int index, AnimationList animations);
	
	/**
	 * Stop a movie.
	 */
	protected abstract void stopMovie(int index);
	
	
	
	/**
	 * Start an animation. An animation consists of a number of animation steps.
	 */
	protected abstract void startAnimation(int index, Animation animation);
	
	/**
	 * Stop an animation.
	 */
	protected abstract void stopAnimation(int index);
	
	
	
	/**
	 * Perform a CreateStep.
	 */
	protected abstract void doCreateStep(CreateStep step, int index);
	
	/**
	 * Perform a ReplicateStep.
	 */
	protected abstract void doReplicateStep(ReplicateStep step, int index);
	
	/**
	 * Perform a ReceiveStep.
	 */
	protected abstract void doReceiveStep(ReceiveStep step, int index);
	
	/**
	 * Perform a SendStep.
	 */
	protected abstract void doSendStep(SendStep step, int index);
	
	/**
	 * Perform a DestroyStep.
	 */
	protected abstract void doDestroyStep(DestroyStep step, int index);
	
	/**
	 * Perform a ReceiveStep followed by a DestroyStep.
	 */
	protected void doReceiveAndDestroyStep(ReceiveStep receive, DestroyStep destroy, int index) {
		// Default is to perform the steps separately.
		doReceiveStep(receive, index);
		doDestroyStep(destroy, index);
	}
	
	
	
	/**
	 * Perform the actual interpretation of the animations.
	 * @param animations Animation to be interpreted.
	 * @param monitor Progress monitor to be used.
	 */
	public void animate(AnimationMatrix animations, IProgressMonitor monitor) {
		
		// Compute the number of animations.
		int animationCount = 0;
		for (int i=0; i<animations.size(); i++) {
			animationCount = animationCount + animations.get(i).size();
		}
		
		// Start.
		monitor.beginTask("Generating animations", animationCount);
		
		for (int i=0; i<animations.size(); i++) {
			
			// Start the movie.
			startMovie(i, animations.get(i));
			
			// Append all animations.
			for (int j=0; j<animations.get(i).size(); j++) {
				appendAnimation(animations.get(i).get(j), i);
				monitor.worked(1);
				if (monitor.isCanceled() && i<animations.size()/2) return;
			}
			
			// And stop the movie again.
			stopMovie(i);
			if (monitor.isCanceled()) return;
		
		}
		
		// Done.
		monitor.done();
		
	}
	
	
	/*
	 * Append an animation.
	 */
	protected void appendAnimation(Animation animation, int id) {
		if (!showNoFlow && animation.getSteps().isEmpty()) return;
    	startAnimation(id, animation);
    	int size = animation.getSteps().size();
    	int j=0;
		while (j<size) {
			AnimationStep next = (j<size-1) ? animation.getSteps().get(j+1) : null;
			j = j + doAnimationStep(animation.getSteps().get(j), next, id);
		}
		stopAnimation(id);
	}
	
	
	/*
	 * Helper method for animation step execution.
	 */
	protected int doAnimationStep(AnimationStep step, AnimationStep next, int id) {
		if (step instanceof CreateStep) doCreateStep((CreateStep) step, id); else
		if (step instanceof ReplicateStep) doReplicateStep((ReplicateStep) step, id); else
		if (step instanceof SendStep) doSendStep((SendStep) step, id); else
		if (step instanceof ReceiveStep && next instanceof DestroyStep) {
			doReceiveAndDestroyStep((ReceiveStep) step, (DestroyStep) next, id);
			return 2;
		} else
		if (step instanceof DestroyStep) doDestroyStep((DestroyStep) step, id); else
		if (step instanceof ReceiveStep) doReceiveStep((ReceiveStep) step, id);
		return 1;
	}

	/**
	 * Get the number of animations.
	 */
	protected int getAnimationCount() {
		return animationCount;
	}
	
	/**
	 * Determines whether to show no-flow animations.
	 */
	public boolean getShowNoFlow() {
		return showNoFlow;
	}
	
	/**
	 * Setter for the show-no-flow flag.
	 */
	public void setShowNoFlow(boolean showNoFlow) {
		this.showNoFlow = showNoFlow;
	}
	
}
