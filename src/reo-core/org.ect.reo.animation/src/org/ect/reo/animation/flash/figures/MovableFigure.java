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

import java.util.Iterator;

import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;

import com.anotherbigidea.flash.movie.Frame;
import com.anotherbigidea.flash.movie.Symbol;
import com.anotherbigidea.flash.movie.Transform;


/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class MovableFigure extends HighlightableFigure {

	private PointList path;
	private double progress = 0.0;
	
	public MovableFigure(View view) {
		super(view);
	}
	
	
	public void move(Frame frame, double progress) {
		
		if (path==null || path.size()<2) return;
		
		if (progress<0.0) progress = 0.0;
		if (progress>1.0) progress = 1.0;
		
		this.progress = progress;
		
		Point relativePosition = getRelativePosition(progress);
		if (relativePosition==null) return;
		
		Iterator<Symbol> it = getSymbols().iterator();
				
		while (it.hasNext()) {
			
			Symbol symbol = it.next();
			if (getInstance(symbol)==null) continue;
			
			Point position = relativePosition.translate( getPosition(symbol) );
			
			Transform transform = new Transform(position.x, position.y);
			frame.alter(getInstance(symbol), transform, null);
		
		}
		
	}
	
	
	private Point getRelativePosition(double progress) {

		double position = path.getLength() * progress;
		double length = 0;
		
		for (int i=0; i<path.size()-1; i++) {
			
			double fragment = path.getFragmentLength(i);
			
			if (length + fragment >= position) {
				Point p1 = new Point(path.get(i));
				Point p2 = new Point(path.get(i+1));
				double ratio = (position - length) / fragment;
				return new Point( p1.x + ratio*(p2.x-p1.x), p1.y + ratio*(p2.y-p1.y) );				
			}
			
			length = length + fragment; 

		}
		
		return null;

	}
	
	
	public void redraw(Frame frame) {
		super.redraw(frame);
		move(frame, progress);
	}

	
	protected void setPath(PointList path) {
		this.path = path;
	}

	
	public double getProgress() {
		return progress;
	}
	
}
