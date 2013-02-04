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
import org.ect.reo.CustomPrimitive;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.channels.DirectedChannel;
import org.ect.reo.diagram.figures.util.ReoFigureColors;

import com.anotherbigidea.flash.structs.Color;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class CustomChannelFigure extends ChannelFigure {
	
	public CustomChannelFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.AbstractFigure#initSymbols()
	 */
	@Override
	protected void initSymbols() {
		
		addChannelLine(false);
		
		if (getChannel() instanceof DirectedChannel) {
			addTargetArrow();
		} else {
			addSourceArrow();
			addTargetArrow();			
		}
		
    	addNoFlowArrows();
    	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.PrimitiveFigure#getForegroundColor()
	 */
	@Override
	protected Color getForegroundColor() {
		return AnimationConstants.convertColor(ReoFigureColors.getCustomPrimitiveColor((CustomPrimitive) getPrimitive()));
	}

}
