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

import java.util.HashMap;

import org.eclipse.gmf.runtime.notation.View;

import com.anotherbigidea.flash.movie.Frame;
import com.anotherbigidea.flash.movie.Symbol;
import com.anotherbigidea.flash.structs.AlphaTransform;

/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class HighlightableFigure extends AbstractFigure {
	
	// Shapes can have a color change assigned to.
	private HashMap<Symbol,AlphaTransform> colorChanges = new HashMap<Symbol,AlphaTransform>();
	
	// Color change factors.
	private HashMap<Symbol,Double> changeFactors = new HashMap<Symbol,Double>();
	
	private double intensity = 0.0;
	
	
	HighlightableFigure(View view) {
		super(view);
	}
	
	
	protected void setHighlighting(Symbol symbol, AlphaTransform change, double factor) {
		colorChanges.put(symbol, change);
		changeFactors.put(symbol, factor);
	}
	
	protected void setHighlighting(Symbol symbol, AlphaTransform change) {
		setHighlighting(symbol, change, 1.0);
	}

	
	public void highlight(Frame frame, double intensity) {
		
		if (intensity<0.0) intensity = 0.0;
		if (intensity>1.0) intensity = 1.0;
		
		this.intensity = intensity;
		
		for (Symbol symbol : getSymbols()) {
			
			if (getInstance(symbol)==null || !colorChanges.containsKey(symbol)) continue;
			double factor = changeFactors.get(symbol);
			
			AlphaTransform transform = absoluteChange( colorChanges.get(symbol), factor * intensity * 255.0);
			frame.alter(getInstance(symbol), null, transform);
		
		}
		
	}
	

	private AlphaTransform absoluteChange(AlphaTransform a, double intensity) {
		if (intensity>255) intensity = 255;
		AlphaTransform b = new AlphaTransform();
		b.setAddRed(	(int) (a.getAddRed() * intensity) );
		b.setAddGreen(	(int) (a.getAddGreen() * intensity) );
		b.setAddBlue(	(int) (a.getAddBlue() * intensity) 	);
		b.setAddAlpha(	(int) (a.getAddAlpha() * intensity) );
		return b;
	}
	
	
	public void redraw(Frame frame) {
		super.redraw(frame);
		highlight(frame, intensity);
	}

	
	public double getIntensity() {
		return intensity;
	}
	
}

