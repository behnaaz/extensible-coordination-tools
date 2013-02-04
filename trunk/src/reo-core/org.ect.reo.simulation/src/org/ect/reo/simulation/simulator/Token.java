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
package org.ect.reo.simulation.simulator;

import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;

/** 
 * A token indicates a flow from a startingPoint to a certain endingPoint. It is used for the end to end delay statistics.
 * The token will know it's current location, however it will not keep track of all it's intermediate locations. 
 */
public class Token {
	private double beginTime, endTime;
	private Connectable startingPoint, endingPoint, location;
	
	
	public Token(Connectable start, double time) {
		startingPoint = start;
		location = start;
		beginTime = time;
		endingPoint = null;
		endTime = -1;
	}

	
	/**
	 * Return the path of the token, this will be a string representing the starting and ending location of the token. 
	 * It will not return it's full path with all intermediate points.
	 * @return The path
	 */
	public String getPath() {
		String startString, endString;
		if (startingPoint instanceof Node) {
			startString = ReoSimulator.getNodeString((Node) startingPoint);
		} else {
			startString = ReoSimulator.getPrimitiveString((Primitive) startingPoint);
		}
		if (endingPoint instanceof Node) {
			endString =  ReoSimulator.getNodeString((Node) endingPoint);
		} else {
			endString = ReoSimulator.getPrimitiveString((Primitive) endingPoint);
		}
		return startString + " - " + endString;
	}
	
	
	/**
	 * @return The time it took from startingPoint to endingPoint
	 */
	public double getDuration() {
		return endTime - beginTime;
	}
	
	
	/**
	 * Return a copy of the token. It will only copy the begin time and starting point.
	 * @return A new token with the same startingPoint and beginTime as this Token
	 */
	public Token getCopy() {
		return new Token(startingPoint, beginTime);
	}
	
	
	/**
	 * @param end the ending point
	 * @param endTime the ending time
	 */
	public void setEndingPointAndTime(Connectable end, double endTime) {
		setEndingPoint(end);
		setEndTime(endTime);
	}


	/**
	 * @return the beginTime
	 */
	public double getBeginTime() {
		return beginTime;
	}


	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(double beginTime) {
		this.beginTime = beginTime;
	}


	/**
	 * @return the endTime
	 */
	public double getEndTime() {
		return endTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}


	/**
	 * @return the startingPoint
	 */
	public Connectable getStartingPoint() {
		return startingPoint;
	}


	/**
	 * @param startingPoint the startingPoint to set
	 */
	public void setStartingPoint(Connectable startingPoint) {
		this.startingPoint = startingPoint;
	}


	/**
	 * @return the endingPoint
	 */
	public Connectable getEndingPoint() {
		return endingPoint;
	}


	/**
	 * @param endingPoint the endingPoint to set
	 */
	public void setEndingPoint(Connectable endingPoint) {
		this.endingPoint = endingPoint;
	}


	/**
	 * @return the location
	 */
	public Connectable getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(Connectable location) {
		this.location = location;
	}
}
