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

import java.util.ArrayList;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Graphics;
import org.ect.reo.diagram.figures.decorations.ArrowDecoration;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;


/**
 * @generated NOT
 */
public class FilterLine extends DataAwareChannelLine {
	
	/**
	 * Constructor.
	 */
	public FilterLine() {
		super();
		setLineStyle(Graphics.LINE_SOLID);
		setTargetDecoration(new ArrowDecoration(false));
		setConnectionRouter(WIGGLY_ROUTER);
	}

	@Override
	public void setConnectionRouter(ConnectionRouter router) {
		// Always use the wiggly router.
		super.setConnectionRouter(WIGGLY_ROUTER);
	}
	
	
	/* ---------- Wiggly lines ---------- */
	
	public static final int WIGGLE_COUNT = 5;
	public static final int WIGGLE_HEIGHT = 5;
	public static final int WIGGLE_WIDTH = 2;

	/**
	 * Compute a wiggly line as a point list.
	 */
	public static PointList computeWigglyLine(Point start, Point end) {
		PointList line = new PointList();
		
		double length = start.getDistance(end);
		double startWiggly = (length - (double) ((WIGGLE_COUNT)*(WIGGLE_WIDTH*4))) / 2;
		
		line.add(new Point(0,0));
		line.add(new Point(startWiggly,0));
		
		double position = startWiggly - WIGGLE_WIDTH;
		for (int i=0; i<WIGGLE_COUNT; i++) {
			line.add(new Point(position += (2*WIGGLE_WIDTH), -WIGGLE_HEIGHT));
			line.add(new Point(position += (2*WIGGLE_WIDTH), WIGGLE_HEIGHT));
		}
		line.add(new Point(position += WIGGLE_WIDTH, 0));		
		line.add(new Point(length,0));
		
		// Rotate the line.
		double angle = Math.atan2(end.x - start.x, end.y - start.y) - (Math.PI/2);
		line = line.rotate(new Point(0,0), angle);
		
		// Shift the line.
		line = line.translate(start);
		
		// Make sure the start and end are exactly as required.
		line.set(0, start);
		line.set(line.size()-1, end);
		
		// Done.
		return line;
	}
	
	
	/**
	 * A connection router that produces wiggly lines.
	 */
	public static ConnectionRouter WIGGLY_ROUTER = new AbstractRouter() {
		
		/**
		 * Routes the given Connection using a wiggly line 
		 * between the source and target anchors.
		 * @param conn the connection to be routed
		 */
		public void route(Connection conn) {
			
			// Reset points.
			org.eclipse.draw2d.geometry.PointList points = conn.getPoints();
			points.removeAllPoints();
			
			// Get the start and the end of the connection.
			org.eclipse.draw2d.geometry.Point start, end;
			conn.translateToRelative(start = getStartPoint(conn));
			conn.translateToRelative(end = getEndPoint(conn));
			
			// Compute the line using relative coordinates.
			PointList line = computeWigglyLine(new Point(start.x,start.y), new Point(end.x,end.y));
			for (Point point : line) {
				points.addPoint((int) point.x, (int) point.y);
			}
			conn.setPoints(points);
		}
		
		@Override
		public Object getConstraint(Connection connection) {
			return new ArrayList<Bendpoint>();
		}
		
	};

}
