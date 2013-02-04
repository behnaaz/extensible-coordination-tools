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
package org.ect.reo.diagram.figures.geometry;

import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;


/**
 * Version of Draw2d points, that can deal with floating point coordinates.
 * Represents a point (x, y) in 2-dimensional space. This class provides various
 * methods for manipulating this Point or creating new derived geometric
 * Objects.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class Point {
	
	// Coordinates
	public double x, y;
	
	/**
	 * Constructor.
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor.
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor.
	 * @param point Point.
	 */
	public Point(Point point) {
		x = point.x;
		y = point.y;
	}
	
	/**
	 * Derive a point from a location object.
	 * @param location Location.
	 * @return Point.
	 */
	public static Point fromLocation(Location location) {
	    return new Point(location.getX(), location.getY());
	}
	
	/**
	 * Get the location of a node as a point.
	 * @param node Node.
	 * @return Point.
	 */
	public static Point getLocation(Node node) {
		return fromLocation((Location) node.getLayoutConstraint());
	}
	
	/**
	 * Get the distance to the argument point.
	 * @param p point.
	 * @return Distance.
	 */
	public double getDistance(Point p) {
		double dx = p.x - x;
		double dy = p.y - y;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	// -------------------------------- //
	
	/**
	 * Get a copy of this point.
	 * @return Copy.
	 */
	public Point copy() {
		return new Point(x, y);
	}
	
	/**
	 * Get a copy of this point that is scaled by the given factor.
	 * @param factor Factor.
	 * @return Scaled point.
	 */
	public Point scale(double fx, double fy) {
		return new Point(fx*x, fy*y);
	}
	
	/**
	 * Get a shifted copy of this point.
	 * @param x X-shift.
	 * @param y Y-shift.
	 * @return Shifted point.
	 */
	public Point translate(double dx, double dy) {
		return new Point(x+dx, y+dy);
	}
	
	/**
	 * Get a shifted copy of this point.
	 * @param pt Shift.
	 * @return Shifted copy.
	 */
	public Point translate(Point d) {
		return new Point(x+d.x, y+d.y);
	}
	
	/**
	 * Get a negated copy of this point.
	 * @return Negated point.
	 */
	public Point negate() {
		return new Point(-x, -y);
	}	
	
	/**
	 * Rotate the point.
	 * @param origin Origin.
	 * @param theta Angle.
	 * @return Rotated point.
	 */
	public Point rotate(Point origin, double theta) {
		Point shifted = translate(origin.negate());
		double x = shifted.x * Math.cos(theta) + shifted.y * Math.sin(theta);
		double y = -shifted.x * Math.sin(theta) + shifted.y * Math.cos(theta);
		return new Point(x,y).translate(origin);
	}
	
	// ------------------------------------------------------ //
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point p = (Point) o;
			return p.x == x && p.y == y;
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) (x * y) ^ (int)(x + y);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
