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
package org.ect.reo.animation.flash.figures;

import java.util.List;

import org.eclipse.gmf.runtime.notation.View;

import com.anotherbigidea.flash.movie.Frame;
import com.anotherbigidea.flash.movie.Symbol;

/**
 * @author Christian Krause
 * @generated NOT
 */
public interface IFlashFigure {

	/**
	 * Get the view that is associated with this figure.
	 * @return The view of this figure.
	 */
	public View getView();

	/**
	 * Get all symbols of this figure.
	 * @return List of all symbols.
	 */
	public List<Symbol> getSymbols();

	/**
	 * Draw all symbols of the figure to a frame.
	 * @param frame Frame where the symbols should be drawn to.
	 */
	public void draw(Frame frame);
	
	/**
	 * Remove all shapes / symbols of this figure frome a frame.
	 * @param frame Frame from which the shapes will be removed.
	 */
	public void remove(Frame frame);
	
}
