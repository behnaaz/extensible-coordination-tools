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
package org.ect.reo.animation.flash.figures.shapes;

import com.anotherbigidea.flash.structs.Color;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class RoundedRectangleShape extends PredefinedShape {
	
	private static final double SIN = CircleShape.SIN_PI_DIV_4;
	private static final double COS = CircleShape.COS_PI_DIV_4;
	private static final double TAN = CircleShape.TAN_PI_DIV_8;

	
	int width, height, r;
	
	public RoundedRectangleShape(int width, int height, int cornerRadius) {
		this.width = width;
		this.height = height;
		this.r = cornerRadius;
	}
	
	@Override
	public void draw() {
		int x = width / 2;
		int y = height / 2;
		
		// Start at upper right corner.
		move(x, -y+r);
		
		// Line down.
		line(x, y-r);
		
		// Curve to the right. X=x-r; Y=y-r.
		curve(COS*r + x-r, SIN*r + y-r, x, TAN*r + y-r);		
		curve(x-r, y, TAN*r + x-r, y);
		
		// Line to the left.
		line(-x+r, y);
		
		// Curve up. X=-x+r; Y=y-r;
		curve(-COS*r -x+r, SIN*r +y-r, -TAN*r -x+r, y);		
		curve(-x, y-r, -x, TAN*r+y-r);
		
		// Line up.
		line(-x, -y+r);
		
		// Curve to the right. X=-x+r; Y=-y+r;
		curve(-COS*r -x+r, -SIN*r-y+r, -x, -TAN*r-y+r);		
		curve(-x+r, -y, -TAN*r-x+r, -y);
		
		// Line to the right.
		line(x-r, -y);
		
		// Curve down. X=x-r; Y=-y+r;
		curve(COS*r +x-r, -SIN*r-y+r, TAN*r +x-r, -y);		
		curve(x, -y+r, x, -TAN*r-y+r);
		
	}
	
	@Override
	public void setDefaultStyles() {
		defineLineStyle(1.0, new Color(0,0,0));
		defineFillStyle(new Color(255,255,255));	
		setRightFillStyle(1);
		setLineStyle(1);
	}
	
}
