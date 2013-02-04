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
import org.ect.reo.diagram.figures.SyncLine;


/**
 * @generated NOT
 * @author Behrooz Nobakht, Christian Krause
 */
public class CustomDirectedChannelEditPart extends CustomChannelEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3016;

	/**
	 * @generated
	 */
	public CustomDirectedChannelEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Connection createConnectionFigure() {
		return new SyncLine();
	}

}
