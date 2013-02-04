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
import org.ect.reo.channels.Filter;
import org.ect.reo.diagram.figures.FilterLine;
import org.ect.reo.diagram.figures.geometry.Point;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class FilterFigure extends ChannelFigure {

	public FilterFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}
	
	@Override
	protected void computeLine() {
		// Compute the wiggly line.
		Point end = getSourcePosition().negate().translate(getTargetPosition());
		line = FilterLine.computeWigglyLine(new Point(0,0), end);
	}
	
	@Override
	protected void initSymbols() {
		
		addChannelLine(false);
		addTargetArrow();
    	addNoFlowArrows();
    	
    	// Add the expression label.
    	Filter filter = (Filter) getChannel();
		String expression = filter.getExpression();
		
		if (expression!=null && !getView().getChildren().isEmpty()) {
			Node label = (Node) getView().getChildren().get(0);
			addChannelLabel(expression, label);
		}
    	
	}
	
}
