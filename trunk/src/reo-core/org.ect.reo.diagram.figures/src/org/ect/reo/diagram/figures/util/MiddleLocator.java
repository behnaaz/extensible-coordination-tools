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
package org.ect.reo.diagram.figures.util;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;

/**
 *	@generated NOT 
 */
public class MiddleLocator extends ConnectionLocator {

	public MiddleLocator(Connection arg0) {
		super(arg0);
	}

	/**
	 * Relocates the passed in figure (which must be a {@link RotatableDecoration}) at either
	 * the start or end of the connection.
	 * @param target The RotatableDecoration to relocate
	 */
	@Override
	public void relocate(IFigure target) {
		PointList points = getConnection().getPoints();
		RotatableDecoration box = (RotatableDecoration) target;
		box.setLocation(getLocation(points));
		int midPoint = points.size() / 2;
		box.setReferencePoint(points.getPoint(midPoint - 1));
	}

}