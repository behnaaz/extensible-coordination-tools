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

/**
 * Interface for listening to animations of diagram elements.
 * @author Christian Krause
 */
public interface IViewAnimatorListener {
	
	/**
	 * Notify about an animation update.
	 */
	public void animationUpdated();
	
	/**
	 * Notify about a clearing of an animation.
	 */
	public void animationCleared();
	
}
