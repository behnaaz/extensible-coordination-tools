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
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;

import com.anotherbigidea.flash.movie.Shape;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class AsyncDrainFigure extends ChannelFigure {

	public AsyncDrainFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}

	@Override
	protected void initSymbols() {
		
		addChannelLine(false);
		addSourceArrow();
		addTargetArrow();
    	
		PrimitiveEnd end = getChannel().getChannelEndOne();
		PointList path = getFlowPath(end);
		Point position  = path.getLast().translate(getPosition(end));
		Point direction = path.get( path.size()-2 ).translate(getPosition(end));
		
		Shape decoration = createDecoration(16, 10);
        addSymbol(decoration, position, direction, false);
        
		addNoFlowArrows();
    	
	}
	
	
	protected Shape createDecoration(int height, int distance) {
		
		Shape decoration = new Shape();

		decoration.setLineStyle(1);
		decoration.setRightFillStyle(1);
		
		decoration.defineLineStyle( AnimationConstants.WIDTH_CHANNEL, getForegroundColor());
		
		decoration.move(-distance/2, -height/2);
		decoration.line(-distance/2, height/2);

		decoration.move(distance/2, -height/2);
		decoration.line(distance/2, height/2);
		
		return decoration;

	}
	
	
	
}
