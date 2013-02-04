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

import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.ect.reo.Connector;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;
import org.ect.reo.util.ReoNames;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ConnectorFigure extends NamedContainerFigure {
	
	protected Point position;
	protected Size size;
	
	public ConnectorFigure(Node node, Animation animation) {
		super(node, animation);
	}
	
	private void computeCoordinates() {
		// Position and size of the connector.
		size = getNodeSize((Node) getView());
		position = getNodePosition((Node) getView());
	}
	
	@Override
	protected void initSymbols() {
		
		Connector connector = (Connector) getView().getElement();
		computeCoordinates();
		
		// Draw the named container.
		backgroundColor = AnimationConstants.getConnectorColor(connector);
		lineColor = AnimationConstants.COLOR_BORDER;
		fontSize = AnimationConstants.FONT_SIZE;
		lineWidth = AnimationConstants.WIDTH_BORDER;
		
		String name = ReoNames.getName(connector);
		createContainer(name, position, size);
		
	}
	
	
	@Override
	public PointList getFlowPath(PrimitiveEnd end) {
		// Not used.
		return null;
	}
	
}
