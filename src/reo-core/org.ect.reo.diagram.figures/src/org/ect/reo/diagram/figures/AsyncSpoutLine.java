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
import org.ect.reo.diagram.figures.decorations.AsyncDecoration;
import org.ect.reo.diagram.figures.util.MiddleLocator;

/**
 * @generated NOT
 */
public class AsyncSpoutLine extends ChannelLine {

	/**
	 * Constructor.
	 */
	public AsyncSpoutLine() {
		super();

		// Put the two lines in the middle of the channel. 
		add(new AsyncDecoration(), new MiddleLocator(this));

		// Arrow as usual to the source/target end.
		setSourceDecoration(new ArrowDecoration(false));
		setTargetDecoration(new ArrowDecoration(false));
	}

}
