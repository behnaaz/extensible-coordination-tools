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
import org.ect.reo.diagram.figures.decorations.PriorityDotDecoration;
import org.ect.reo.diagram.figures.decorations.PriorityLineDecoration;
import org.ect.reo.diagram.figures.util.MiddleLocator;

/**
 * @generated NOT
 */
public class PrioritySyncLine extends ChannelLine {
	// Token decoration.
		private PriorityLineDecoration tokenline;
		private PriorityDotDecoration tokendot;
	/**
	 * Constructor.
	 */
	public PrioritySyncLine() {
		super();
		setTargetDecoration(new ArrowDecoration());
	
		// Put the buffer rectangle in the middle of the line. 
		add(new PriorityDotDecoration(), new MiddleLocator(this));
		add(new PriorityLineDecoration(), new MiddleLocator(this));
		
				// Arrow as usual to the target end.
	  setTargetDecoration(new ArrowDecoration(false));
				
		// We use thicker lines:
		setLineWidth(1);
	}
	
	/**
	 * Add or remove the token decoration.
	 * @param full Whether the FIFO is full or not.
	 */
	public void setFull(boolean full) {
		if (full && tokendot==null) {
			add(tokendot = new PriorityDotDecoration(), new MiddleLocator(this));
			add(tokenline = new PriorityLineDecoration(), new MiddleLocator(this));			
		}
		else if(!full && tokendot!=null) {
			remove(tokendot);
			remove(tokenline);
			tokendot = null;
			tokenline = null;
		}
	}
}