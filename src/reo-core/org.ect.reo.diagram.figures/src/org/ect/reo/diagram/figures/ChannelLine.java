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

import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.swt.graphics.Color;
import org.ect.reo.diagram.figures.util.ReoFigureColors;


/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class ChannelLine extends PolylineConnectionEx {
	
	public ChannelLine() {
		super.setForegroundColor(ReoFigureColors.FG_CHANNEL);
		setLineWidth(1);
	}
	
	@Override
	public void setForegroundColor(Color color) {
		// Ignore change requests.
	}
	
	public void setCustomColor(Color color) {
		super.setForegroundColor(color);		
	}
	
}
