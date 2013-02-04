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
import org.ect.reo.diagram.figures.decorations.BlockPriorityDecoration;
import org.ect.reo.diagram.figures.util.MiddleLocator;

/**
 * @generated NOT
 */
public class BlockingSourceSyncLine extends ChannelLine {

	/**
	 * Constructor.
	 */
	public BlockingSourceSyncLine() {
		super();
		
		// Put the two lines in the middle of the channel. 
				add(new BlockPriorityDecoration(BlockPriorityDecoration.DIR_SOURCE2SINK), new MiddleLocator(this));
		
		// Arrow as usual to the target end.
  setTargetDecoration(new ArrowDecoration(false));
			
		// We use thicker lines:
		setLineWidth(1);
	}
	
}
