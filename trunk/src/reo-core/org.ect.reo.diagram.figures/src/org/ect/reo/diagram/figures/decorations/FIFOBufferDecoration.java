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
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class FIFOBufferDecoration extends PolygonDecoration {
	
	public FIFOBufferDecoration() {
		setFill(true);
		setLineWidth(1);
		setBackgroundColor(ColorConstants.white);
		setTemplate(getTemplate());
		setScale(7, 3);
	}
	
	protected PointList getTemplate() {
		PointList pl = new PointList();
		pl.addPoint(-3, -2);
		pl.addPoint(3, -2);
		pl.addPoint(3, 2);
		pl.addPoint(-3, 2);
		pl.addPoint(-3, -2);
		return pl;
	}
}
