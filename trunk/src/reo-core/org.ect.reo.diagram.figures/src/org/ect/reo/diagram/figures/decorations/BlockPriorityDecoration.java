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
package org.ect.reo.diagram.figures.decorations;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;
/**@author Behnaz Changizi
 * @generated NOT
 */
public class BlockPriorityDecoration extends PolygonDecoration {
	public static int DIR_BOTH = 1;
	public static int DIR_SINK2SOURCE = 2;
	public static int DIR_SOURCE2SINK = 3;
	

	public BlockPriorityDecoration(int dir) {
		setFill(true);
		setLineWidth(1);
		setTemplate((dir==DIR_BOTH) ? getBothTemplate() : ((dir==DIR_SINK2SOURCE) )?getSource2SinkTemplate():getSink2SourceTemplate());
		setScale(3, 3);	
	//	setForegroundColor(ColorConstants.red);				
	}
	
	protected PointList getBothTemplate() {
		PointList pl = new PointList();
		pl.addPoint(-1, 1);
		pl.addPoint(1, 1);
		pl.addPoint(0, 1);
		pl.addPoint(0, -1);
		pl.addPoint(1, -1);
		pl.addPoint(-1, -1);
		pl.addPoint(0, -1);
		pl.addPoint(0, 1);
		return pl;
	}
	
	
	protected PointList getSource2SinkTemplate() {
		PointList pl = new PointList();
		pl.addPoint(-1, 1);
		pl.addPoint(0, 1);
		pl.addPoint(0, -1);
		pl.addPoint(-1, -1);
		pl.addPoint(0, -1);
		pl.addPoint(0, 1);
		return pl;
	}
	
	protected PointList getSink2SourceTemplate() {
		PointList pl = new PointList();
		pl.addPoint(1, 1);
		pl.addPoint(0, 1);
		pl.addPoint(0, -1);
		pl.addPoint(1, -1);
		pl.addPoint(0, -1);
		pl.addPoint(0, 1);
		return pl;
	}
}