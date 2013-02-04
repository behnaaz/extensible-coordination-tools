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
package org.ect.reo.animation.animators;

import org.eclipse.gmf.runtime.notation.View;

/**
 * Interface for animation of diagram elements.
 * @author Christian Krause
 */
public interface IViewAnimator {
	
	/**
	 * Update the animation of a view.
	 * @param view View to be animated.
	 */
	public void update();
	
	/**
	 * Get the currently active view.
	 * @return Active view.
	 */
	public View getView();
	
	/**
	 * Set the currently active view.
	 * @param view View.
	 */
	public void setView(View view);
	
	/**
	 * Cancel the current animation.
	 */
	public void cancel();

	/**
	 * Add an animation listener.
	 * @param listener Listener.
	 */
	public void addAnimationListener(IViewAnimatorListener listener);
	
	/**
	 * Remove an animation listener.
	 * @param listener Listener.
	 */
	public void removeAnimationListener(IViewAnimatorListener listener);
	
	/**
	 * Change enabled-state of the animator.
	 * @param enabled Enabled-flag.
	 */
	public void setEnabled(boolean enabled);
	
	/**
	 * Dispose the animator.
	 */
	public void dispose();
	
}
