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
public class TriangleShape extends PredefinedShape {
	
	int width, height;
	
	public TriangleShape(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void draw() {
		int x = width / 2;
		int y = height / 2;
		move(-x, -y);
		line(x, 0);
		line(-x, y);
		line(-x, -y);
	}
	
	@Override
	public void setDefaultStyles() {
		defineLineStyle(1.0, new Color(0,0,0));
		defineFillStyle(new Color(255,255,255));	
		setRightFillStyle(1);
		setLineStyle(1);
	}
	
}
