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

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class DataAwareChannelLine extends ChannelLine {
	
	// Label for the expression.
	private WrappingLabel expressionLabel;
	
	/**
	 * Constructor.
	 */
	public DataAwareChannelLine() {
		super();
		createLabel();
	}

	private void createLabel() {
		expressionLabel = new WrappingLabel();
		expressionLabel.setText("");
		add(expressionLabel);
	}
	
	public WrappingLabel getExpressionLabel() {
		return expressionLabel;
	}

}
