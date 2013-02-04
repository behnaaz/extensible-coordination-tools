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

import org.eclipse.draw2d.Graphics;
import org.ect.reo.diagram.figures.decorations.ArrowDecoration;


/**
 * @generated NOT
 */
public class LossySyncLine extends ChannelLine {
	
	/**
	 * Constructor.
	 */
	public LossySyncLine() {
		super();
		setLineStyle(Graphics.LINE_DASH);
		setTargetDecoration(new ArrowDecoration());
	}

}
