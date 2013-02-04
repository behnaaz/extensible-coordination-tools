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

import org.ect.reo.diagram.figures.decorations.ArrowDecoration;

/**
 * @generated NOT
 */
public class SyncDrainLine extends DataAwareChannelLine {

	/**
	 * Constructor.
	 */
	public SyncDrainLine() {
		super();
		setSourceDecoration(new ArrowDecoration(true));
		setTargetDecoration(new ArrowDecoration(true));
	}

}
