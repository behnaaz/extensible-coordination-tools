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

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.figures.IFlashFigure;
import org.ect.reo.diagram.view.util.NetworkView;



/**
 * Flash figure factory that acts as a wrapper on a set of
 * figure factories.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class CompoundFigureFactory implements IFlashFigureFactory {
	
	// Factories.
	protected Set<IFlashFigureFactory> factories;
	
	/**
	 * Default constructor.
	 */
	public CompoundFigureFactory() {
		// Linked set because we want to maintain the order.
		this.factories = new LinkedHashSet<IFlashFigureFactory>();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.IFlashFigureFactory#createFlashFigure(org.eclipse.gmf.runtime.notation.View)
	 */
	public IFlashFigure createFlashFigure(View view) {
		for (IFlashFigureFactory factory : factories) {
			IFlashFigure figure = factory.createFlashFigure(view);
			if (figure!=null) return figure;
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.IFlashFigureFactory#setAnimation(org.ect.reo.animation.Animation)
	 */
	public void setAnimation(Animation animation) {
		for (IFlashFigureFactory factory : factories) {
			factory.setAnimation(animation);
		}
	}
		
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.factories.IFlashFigureFactory#setNetworkView(org.ect.reo.diagram.view.util.NetworkView)
	 */
	public void setNetworkView(NetworkView networkView) {
		for (IFlashFigureFactory factory : factories) {
			factory.setNetworkView(networkView);
		}
	}
	
	/**
	 * Get the set of registered factories.
	 */
	public Set<IFlashFigureFactory> getFactories() {
		return factories;
	}

}