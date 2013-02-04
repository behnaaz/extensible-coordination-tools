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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;

/**
 * Point list class.
 * @author Christian Krause
 * @generated NOT
 */
public class PointList extends ArrayList<Point> {
	
	// Default serial ID.
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PointList() {
		super();
	}

	/**
	 * Creates a PointList from the bendpoints of an edge.
	 */	
	@SuppressWarnings("unchecked")
	public PointList(Point source, Point target, RelativeBendpoints relativeBendPoints) {

		List<RelativeBendpoint> bendpoints = relativeBendPoints.getPoints();
		if (bendpoints.isEmpty()) {
			// There should be at least two bendpoints.
			bendpoints = new ArrayList<RelativeBendpoint>();
			bendpoints.add(new RelativeBendpoint());
			bendpoints.add(new RelativeBendpoint());
		}
		
		// copy points
		for (RelativeBendpoint bendpoint : bendpoints) {
            add(new Point( bendpoint.getSourceX(), bendpoint.getSourceY()));
    	}
		
		// stretch so that they fit in between
		double oldX = source.x + getLast().x;
		double oldY = source.y + getLast().y;

		double dx = ((double) (target.x - source.x)) / ((double) (oldX - source.x));  
		double dy = ((double) (target.y - source.y)) / ((double) (oldY - source.y));  
		if (dx==0 || Double.isInfinite(dx) || Double.isNaN(dx)) dx = 1;
		if (dy==0 || Double.isInfinite(dy) || Double.isNaN(dy)) dy = 1;
		
		for (int i=0; i<size(); i++) {
			set(i, get(i).scale(dx,dy));
		}
		
		// set the start / end point exactly to the source / target location
		set(0, new Point(0,0));
		set(size()-1, new Point(target.x-source.x, target.y-source.y));
		
	}
	
	public Point getFirst() {
		if (isEmpty()) return null;
		return get(0);
	}

	public Point getMiddle() {
		if (isEmpty()) return null;
		if (size() % 2 == 1) {
			return get(size() / 2 + 1);
		} else {
			Point p1 = get(size() / 2 - 1);
			Point p2 = get(size() / 2);
			return new Point((p2.x + p1.x) / 2, (p2.y + p1.y) / 2);
		}
	}

	public Point getLast() {
		if (isEmpty()) return null;
		return get(size()-1);
	}	
	
	
	/**
	 * Compute the exact length of the path that this PointList defines.
	 * @return Double value being the length of the path.
	 */
	public double getLength() {
		if (size()==0) return 0;
		double length = 0;
		for (int i=1; i<size(); i++) {
			length = length + get(i-1).getDistance(get(i));
		}
		return length;
	}
	
	/**
	 * Compute the length of a fragment of the path. Counting the fragments
	 * starts with zero. The index of the last fragment is {@link #size()}-2.
	 * @param fragment Index of the fragment.
	 * @return Length of that fragment.
	 */
	public double getFragmentLength(int fragment) {
		if (fragment>=size()-1) return 0.0;
		return get(fragment).getDistance(get(fragment+1));
	}
	
	
	/**
	 * Split this PointList into to a number of parts, each having the
	 * same length. The original PointList is not changed by that.
	 * @param parts Number of part. Should be greater or equal than 2.
	 * @return A list of PointLists, being the result of the splitting.
	 */
	public List<PointList> split(int parts) {

		List<PointList> result = new Vector<PointList>();

		if (size()<2 || parts<1) {
			result.add(this);
			return result;
		}
		
		// The partLength must be rounded up!!!
		int totalLength = (int) getLength();
		int partLength = (int) Math.floor(((double) totalLength) / ((double) parts)) + 1;
		int currentLength = 0;
		
		//System.out.println("\nTotal length: " + totalLength);
		
		int nextCut = partLength;
		
		PointList currentPart = new PointList();
		result.add(currentPart);
		currentPart.add(get(0));

		//System.out.println("Start point: " + points.getPoint(0));
		//System.out.println("First cut at: " + nextCut);
		
		for (int i=1; i<size(); i++) {
			
			currentLength =  currentLength + ((int) getFragmentLength(i-1));	// Fragement before the point.
			
			//System.out.println("Fragment length-" + i +" : " + fragmentLength(points, i-1));
			//System.out.println("Current length: " + currentLength);
			
			if (currentLength > nextCut) {

				Point cutPoint = cutPoint(get(i-1), get(i), 0.5);
				currentPart.add(cutPoint);
				
				currentPart = new PointList();
				result.add(currentPart);
				currentPart.add(cutPoint);
				
				nextCut = nextCut + partLength;
				
				//System.out.println("Cutting at: " + cutPoint);
				//System.out.println("Next cut at: " + nextCut);
				
			}
			
			//System.out.println("Adding point: " + points.getPoint(i));
			currentPart.add(get(i));
		
		}
		
		return result;
	}
	
	
	private static Point cutPoint(Point p1, Point p2, double ratio) {
		return new Point(ratio*(p1.x + p2.x), ratio*(p1.y + p2.y) );
	}
	
	public PointList revert() {
		PointList reverted = new PointList();
		for (int i=size()-1; i>=0; i--) {
			reverted.add(get(i));
		}
		return reverted;
	}
	
	public PointList translate(Point point) {
		PointList translated = new PointList();
		for (int i=0; i<size(); i++) {
			translated.add(get(i).translate(point));
		}
		return translated;
	}

	public PointList rotate(Point origin, double theta) {
		PointList rotated = new PointList();
		for (int i=0; i<size(); i++) {
			rotated.add(get(i).rotate(origin,theta));
		}
		return rotated;		
	}
		
}
