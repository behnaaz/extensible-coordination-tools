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
import org.ect.reo.channels.SyncDrain;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class SyncDrainFigure extends ChannelFigure {

	public SyncDrainFigure(Edge edge, Animation animation) {
		super(edge, animation);
	}

	@Override
	protected void initSymbols() {
		addChannelLine(false);
		addSourceArrow();
		addTargetArrow();
    	addNoFlowArrows();
    	
    	// Add the expression label.
    	SyncDrain syncdrain = (SyncDrain) getChannel();
		String expression = syncdrain.getExpression();
		
		if (expression!=null && !getView().getChildren().isEmpty()) {
			Node label = (Node) getView().getChildren().get(0);
			addChannelLabel(expression, label);
		}
	}
	
}
