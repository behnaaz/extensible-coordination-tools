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
public class PentagonShape extends PredefinedShape {
	
	// Radius.
	int r;
	
	public PentagonShape(int r) {
		this.r = r;		
	}
	
	@Override
	public void draw() {
		move(0, -r);
		for (int i=2; i<7; i++) {
			double angle = Math.toRadians(18 + (i*72)); 
			line(Math.cos(angle) * r, -Math.sin(angle) * r);
		}		
	}

	@Override
	public void setDefaultStyles() {
		defineLineStyle(1.0, new Color(0,0,0));
		defineFillStyle(new Color(255,255,255));
	
		setRightFillStyle(1);
		setLineStyle(1);
	}
	
}
