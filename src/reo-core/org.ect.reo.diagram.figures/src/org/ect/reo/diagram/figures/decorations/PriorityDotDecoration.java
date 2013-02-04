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

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
/**
 * Figure for a token for priority.
 * 
 * @generated NOT
 * @author Behnaz Changizi
 */
public class PriorityDotDecoration  extends Ellipse implements RotatableDecoration {	
	/**
	 * Constructor.
	 */

	// Size of the token.
	public final int SIZE = 4;
	
	public PriorityDotDecoration() {
		//setLineWidth(1);
		setFill(true);
		setSize(SIZE, SIZE);
	//	setBackgroundColor(ColorConstants.red);
	}
	
	protected PointList getTemplate() {
		return null;
	}

	@Override
	public void setReferencePoint(Point arg0) {
			Rectangle rect = getBounds();
			setBounds(new Rectangle(
						rect.x - (SIZE / 2), 
						rect.y - (SIZE / 2),
						SIZE, SIZE)
					);
	}
}
