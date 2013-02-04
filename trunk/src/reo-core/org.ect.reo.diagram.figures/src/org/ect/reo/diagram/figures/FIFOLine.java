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
import org.ect.reo.diagram.figures.decorations.FIFOBufferDecoration;
import org.ect.reo.diagram.figures.decorations.FIFOTokenDecoration;
import org.ect.reo.diagram.figures.util.MiddleLocator;

/**
 * @generated NOT
 */
public class FIFOLine extends ChannelLine {
	
	// Token decoration.
	private FIFOTokenDecoration token;
	
	/**
	 * Constructor.
	 */
	public FIFOLine() {
		super();
		
		// Put the buffer rectangle in the middle of the line. 
		add(new FIFOBufferDecoration(), new MiddleLocator(this));
		
		// Arrow as usual to the target end.
		setTargetDecoration(new ArrowDecoration(false));
	}
	
	/**
	 * Add or remove the token decoration.
	 * @param full Whether the FIFO is full or not.
	 */
	public void setFull(boolean full) {
		if (full && token==null) {
			add(token = new FIFOTokenDecoration(), new MiddleLocator(this));
		}
		else if(!full && token!=null) {
			remove(token);
			token = null;
		}
	}
	
}
