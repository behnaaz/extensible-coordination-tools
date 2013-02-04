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

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class ArrowDecoration extends PolygonDecoration {
	
	public ArrowDecoration() {
		this(false);
	}
	
	public ArrowDecoration(boolean inverse) {
		setFill(true);
		setLineWidth(1);
		setTemplate(inverse ? getInverseTemplate() : getDefaultTemplate());
		setScale(3, 3);	
	}
	
	protected PointList getDefaultTemplate() {
		PointList pl = new PointList();
		pl.addPoint(-2, 1);
		pl.addPoint(0, 0);
		pl.addPoint(-2, -1);
		pl.addPoint(-1, 0);
		return pl;
	}
	
	protected PointList getInverseTemplate() {
		PointList pl = new PointList();
		pl.addPoint(-1, 1);
		pl.addPoint(-3, 0);
		pl.addPoint(-1, -1);
		pl.addPoint(-2, 0);
		return pl;
	}

}
