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
import org.eclipse.gmf.runtime.notation.Node;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.figures.shapes.TriangleShape;
import org.ect.reo.channels.Transform;

import com.anotherbigidea.flash.structs.AlphaColor;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class TransformFigure extends ChannelFigure {

	public TransformFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}

	@Override
	protected void initSymbols() {
		addChannelLine(false);
		addTargetArrow();
		addTriangle();
    	addNoFlowArrows();
    	
		// Add the expression label.
    	Transform transform = (Transform) getChannel();
		String expression = transform.getExpression();
		
		if (expression!=null && !getView().getChildren().isEmpty()) {
			Node label = (Node) getView().getChildren().get(0);
			addChannelLabel(expression, label);
		}

	}
	

	protected void addTriangle() {
		
		double height = AnimationConstants.SIZE_FIFO_BUFFER;
		
		TriangleShape shape = new TriangleShape((int) height, (int) height);
		shape.defineLineStyle(AnimationConstants.WIDTH_CHANNEL, getForegroundColor());
		shape.setLineStyle(1);		
		shape.defineFillStyle(AnimationConstants.COLOR_FIFO_BUFFER);
		shape.setRightFillStyle(1);
		shape.draw();
				
		// Add the shape.
		addSymbol(shape, getMidLocation(), getMidDirection(), false);
		
		// Check if there is data flow.
		boolean isFlow = getAnimation().isFlow(getChannel().getChannelEndOne()) ||
						 getAnimation().isFlow(getChannel().getChannelEndTwo());
		
		if (isFlow) {
			
			TriangleShape flow = new TriangleShape((int) height, (int) height);
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
