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

import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;
/**
 * Figure for a token for priority.
 * 
 * @generated NOT
 * @author Behnaz Changizi
 */
public class PriorityLineDecoration extends PolylineDecoration{
	/**
	 * Constructor.
	 */
	public PriorityLineDecoration() {
		//setLineWidth(1);
		setTemplate(getTemplate());
		setScale(7, 3);
	//	setForegroundColor(ColorConstants.red);	
	}
	protected PointList getTemplate() {
		PointList pl = new PointList();
		pl.addPoint(0, -4);									
		pl.addPoint(0, -2);
		return pl;
	}
}
