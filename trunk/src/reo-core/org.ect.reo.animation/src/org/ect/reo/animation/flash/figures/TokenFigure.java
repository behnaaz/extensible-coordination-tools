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

import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.figures.shapes.PentagonShape;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;

import com.anotherbigidea.flash.structs.AlphaColor;
import com.anotherbigidea.flash.structs.Color;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class TokenFigure extends MovableFigure {

	private static int colorIndex = 0; 
	private Color color;
	private PointList path;
	private Point position;
	
	
	public TokenFigure(Point position, PointList path, Color color) {
		// Tokens have no associated GMF view.
		super(null);
		
		this.color = color;
		this.position = position;
		this.path = path;
	}

	
	public TokenFigure(Point position, PointList path) {
		// Tokens have no associated GMF view.
		super(null);
		
		this.color = nextColor();
		this.position = position;
		this.path = path;		
	}

	
	public TokenFigure(Point position) {
		// no path
		this(position, null);
	}


	
	protected void initSymbols() {

		PentagonShape token = new PentagonShape(AnimationConstants.SIZE_TOKEN / 2);
		token.defineLineStyle(0.0, new AlphaColor(AnimationConstants.COLOR_BLACK, 0));
		token.defineFillStyle(color);
		token.setLineStyle(1);
		token.setRightFillStyle(1);
		token.draw();
		
		// Point position = Point.getLocation((Node) (((Edge) getView()).getSource()));
		
		addSymbol(token, position);
		setHighlighting(token, AnimationConstants.FADE_IN);
		if (path!=null) setPath(path);
	}

	
	public Color getColor() {
		return color;
	}
	
	
	public static void resetColor() {
		colorIndex = 0;
	}
	
	public static Color nextColor() {
		Color color = currentColor();
		colorIndex = (colorIndex + 1) % AnimationConstants.COLOR_TOKEN.length;
		return color;
	}

	public static Color currentColor() {
		return new AlphaColor(AnimationConstants.COLOR_TOKEN[colorIndex], 0);		
	}

}
