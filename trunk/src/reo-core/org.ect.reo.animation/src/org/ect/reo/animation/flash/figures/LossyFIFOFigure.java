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
package org.ect.reo.animation.flash.figures;

import org.eclipse.gmf.runtime.notation.Edge;
import org.ect.reo.animation.Animation;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class LossyFIFOFigure extends FIFOFigure {

	public LossyFIFOFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}
	
	@Override
	protected void initSymbols() {
		// In contrast to a normal FIFOFigure, we make
		// the line dashed here. The rest is the same.
		addChannelLine(true);		
		addTargetArrow();
		addBufferShape();
    	addNoFlowArrows();
	}
	
}

