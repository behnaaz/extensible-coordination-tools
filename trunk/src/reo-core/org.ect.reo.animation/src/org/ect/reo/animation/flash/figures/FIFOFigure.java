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
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.figures.shapes.RectangleShape;

import com.anotherbigidea.flash.structs.AlphaColor;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class FIFOFigure extends ChannelFigure {

	public FIFOFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}
	
	@Override
	protected void initSymbols() {
		addChannelLine(false);
		addBufferShape();
    	addNoFlowArrows();
	}
		
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.ChannelFigure#addChannelDecorations()
	 */
	@Override
	protected void addChannelDecorations() {
		addTargetArrow();
	}
	
	
	protected void addBufferShape() {
		
		double ratio = 2.5;
		double height = AnimationConstants.SIZE_FIFO_BUFFER;
		double width = height * ratio;
		
		RectangleShape buffer = new RectangleShape((int) width, (int) height);
		buffer.defineLineStyle(AnimationConstants.WIDTH_CHANNEL, getForegroundColor());
		buffer.setLineStyle(1);		
		buffer.defineFillStyle(AnimationConstants.COLOR_FIFO_BUFFER);
		buffer.setRightFillStyle(1);
		buffer.draw();
				
		// Add the shape.
		addSymbol(buffer, getMidLocation(), getMidDirection(), false);
		
		// Check if there is data flow.
		boolean isFlow = getAnimation().isFlow(getChannel().getChannelEndOne()) ||
						 getAnimation().isFlow(getChannel().getChannelEndTwo());
		
		if (isFlow) {
			
			RectangleShape flow = new RectangleShape((int) width, (int) height);
			flow.defineLineStyle(0, getForegroundColor());
			flow.setLineStyle(1);
			flow.defineFillStyle(new AlphaColor(AnimationConstants.COLOR_FLOW, 0));
			flow.setRightFillStyle(1);
			flow.draw();
			
			addSymbol(flow, getMidLocation(), getMidDirection(), false);	
			setHighlighting(flow, AnimationConstants.FADE_IN, AnimationConstants.TRANSPARENCY_FLOW);
		}
		
	}
	
	
}
