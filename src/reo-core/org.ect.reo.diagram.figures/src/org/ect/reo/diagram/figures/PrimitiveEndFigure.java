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
package org.ect.reo.diagram.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.RectangleFigure;

/**
 * @generated NOT
 */
public class PrimitiveEndFigure extends RectangleFigure {
	
	// Use local coordinates?
	private boolean localCoordinates = false;

	/**
	 * Constructor.
	 */
	public PrimitiveEndFigure() {
		setLineWidth(1);
		setBackgroundColor(ColorConstants.white);
	}
	
	protected boolean useLocalCoordinates() {
		return localCoordinates;
	}

	protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
		localCoordinates = useLocalCoordinates;
	}

}
