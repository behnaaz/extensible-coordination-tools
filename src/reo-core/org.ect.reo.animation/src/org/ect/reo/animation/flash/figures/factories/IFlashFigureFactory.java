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
package org.ect.reo.animation.flash.figures.factories;

import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.figures.IFlashFigure;
import org.ect.reo.diagram.view.util.NetworkView;


/**
 * @author Christian Krause
 * @generated NOT
 */
public interface IFlashFigureFactory {
	
	/**
	 * Create a new Flash figure for a view.
	 */
	public IFlashFigure createFlashFigure(View view);
	
	/**
	 * Set the current animation.
	 */
	public void setAnimation(Animation animation);
	
	/**
	 * Set the NetworkView to be used.
	 */
	public void setNetworkView(NetworkView networkView);
	
}