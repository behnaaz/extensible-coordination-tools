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
public class SyncFigure extends ChannelFigure {

	public SyncFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}
	
	@Override
	protected void initSymbols() {	
		addChannelLine(false);
		addTargetArrow();
    	addNoFlowArrows();
	}
	
	
}
