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

import org.ect.reo.colouring.Colourable;

/**
 * @see org.ect.reo.animation.AnimationPackage#getAnimatable()
 * @model kind="class" interface="true" abstract="true"
 * @generated
 */
public interface Animatable extends Colourable {

	/**
	 * Get the animation table of this Animatable instance.
	 * @see org.ect.reo.animation.AnimationPackage#getAnimatable_AnimationTable()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	AnimationTable getAnimationTable();
	
}
