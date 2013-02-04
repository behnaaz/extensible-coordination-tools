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
public class CircleShape extends PredefinedShape {
	
	public static final double SIN_PI_DIV_4 = Math.cos(Math.PI / 4.0);
	public static final double COS_PI_DIV_4 = Math.cos(Math.PI / 4.0);
	public static final double TAN_PI_DIV_8 = Math.tan(Math.PI / 8.0);
	
	// Radius.
	int r;
	
	public CircleShape(int r) {
		this.r = r;
	}
	
	@Override
	public void draw() {
		
		move(r, 0);
		
		curve(COS_PI_DIV_4*r, SIN_PI_DIV_4*r, r, TAN_PI_DIV_8*r);
		curve(0, r, TAN_PI_DIV_8*r, r);

		curve(-COS_PI_DIV_4*r, SIN_PI_DIV_4*r, -TAN_PI_DIV_8*r, r);		
		curve(-r, 0, -r, TAN_PI_DIV_8*r);

		curve(-COS_PI_DIV_4*r, -SIN_PI_DIV_4*r, -r, -TAN_PI_DIV_8*r);		
		curve(0, -r, -TAN_PI_DIV_8*r, -r);

		curve(COS_PI_DIV_4*r, -SIN_PI_DIV_4*r, TAN_PI_DIV_8*r, -r);		
		curve(r, 0, r, -TAN_PI_DIV_8*r);
	}


	@Override
	public void setDefaultStyles() {
		defineLineStyle(1.0, new Color(0,0,0));
		defineFillStyle(new Color(255,255,255));
		
		setRightFillStyle(1);
		setLineStyle(1);	
	}
	
}
