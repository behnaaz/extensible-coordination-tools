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
import org.ect.reo.diagram.figures.decorations.TimerDecoration;
import org.ect.reo.diagram.figures.util.MiddleLocator;

/**
 * @author Christian Krause
 * @generated NOT
 */
public class TimerLine extends DataAwareChannelLine {
	
	/**
	 * Constructor.
	 */
	public TimerLine() {
		super();
		setTargetDecoration(new ArrowDecoration());
		add(new TimerDecoration(), new MiddleLocator(this));
	}

}
