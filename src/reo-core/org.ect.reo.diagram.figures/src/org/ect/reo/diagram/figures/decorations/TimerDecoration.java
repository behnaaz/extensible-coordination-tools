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
package org.ect.reo.diagram.figures.decorations;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class TimerDecoration extends Ellipse implements RotatableDecoration {
	
	// Size of the token.
	public final static int SIZE = 12;
	
	/**
	 * Constructor.
	 */
	public TimerDecoration() {
		setSize(SIZE, SIZE);
		setBackgroundColor(ColorConstants.white);
	}
	
	public void setReferencePoint(Point p) {
		Rectangle rect = getBounds();
		setBounds(new Rectangle(
					rect.x - (SIZE / 2), 
					rect.y - (SIZE / 2),
					SIZE, SIZE)
				);
	}
	
}
