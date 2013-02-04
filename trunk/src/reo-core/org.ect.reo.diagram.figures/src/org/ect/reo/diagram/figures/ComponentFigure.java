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

import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Color;
import org.ect.reo.Component;
import org.ect.reo.diagram.figures.util.ReoFigureColors;


/**
 * @generated NOT
 */
public class ComponentFigure extends RoundedRectangle {
	
	// Label for the component name.
	private WrappingLabel nameLabel;
	
	// Use local coordinates?
	private boolean localCoordinates = false;
	
	// The component.
	private Component component;

	/**
	 * Constructor.
	 */
	public ComponentFigure(Component component) {
		this.component = component;
		setCornerDimensions(new Dimension(8, 8));
		setLineWidth(1);
		setForegroundColor(ReoFigureColors.FG_COMPONENT);
		update();
		createContents();
	}
	
	private void createContents() {
		nameLabel = new WrappingLabel();
		nameLabel.setText("");
		nameLabel.setMaximumSize(new Dimension(110, 16));
		add(nameLabel);
	}
	
	protected boolean useLocalCoordinates() {
		return localCoordinates;
	}
	
	protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
		localCoordinates = useLocalCoordinates;
	}
	
	public WrappingLabel getFigureComponentNameLabel() {
		return nameLabel;
	}
	
	/**
	 * We always use our own color.
	 */
	@Override
	public void setBackgroundColor(Color color) {
		super.setBackgroundColor(ReoFigureColors.getComponentColor(component));
	}
	
	public void update() {
		setBackgroundColor(null);
	}
	
}
