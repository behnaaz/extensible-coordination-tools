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
package org.ect.reo.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.diagram.figures.SyncSpoutLine;


/**
 * @generated NOT
 */
public class CustomSpoutChannelEditPart extends CustomChannelEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3018;

	/**
	 * @generated
	 */
	public CustomSpoutChannelEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Connection createConnectionFigure() {
		return new SyncSpoutLine();
	}

}
