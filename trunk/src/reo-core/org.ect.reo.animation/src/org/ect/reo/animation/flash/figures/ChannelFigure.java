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
package org.ect.reo.animation.flash.figures;

import java.util.List;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.channels.Channel;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;
import org.ect.reo.prefs.ReoPreferences;

import com.anotherbigidea.flash.movie.Shape;
import com.anotherbigidea.flash.movie.Text;
import com.anotherbigidea.flash.structs.AlphaColor;
import com.anotherbigidea.flash.structs.Color;


/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class ChannelFigure extends PrimitiveFigure {

	protected Channel channel;
	protected PointList line, linePartOne, linePartTwo;
	protected Point position, positionOne, positionTwo;
	
	// Line width.
	protected double channelLineWidth = AnimationConstants.WIDTH_CHANNEL;
	
	/**
	 * Default constructor.
	 */
	ChannelFigure(Edge edge, Animation animation) {
		super(edge, animation);
		channel = (Channel) edge.getElement();
	}
	
	
	protected void computeLine() {
		// Compute the line based on its bendpoints.
		RelativeBendpoints bendpoints = (RelativeBendpoints) ((Edge) getView()).getBendpoints();
		line = new PointList(getSourcePosition(), getTargetPosition(), bendpoints);	
	}
	
	
	protected void splitLine() {
		    	
		// Split the line.
		List<PointList> parts = line.split(2);
    	
    	// The lines are drawn in the direction of the data flow.
		linePartOne = isFlipped(channel.getChannelEndOne()) ? parts.get(0).revert() : parts.get(0);
		linePartTwo = isFlipped(channel.getChannelEndTwo()) ? parts.get(1) : parts.get(1).revert();
		
		// The positions are the start of the data flow.
		Point shift = linePartOne.getFirst();
		linePartOne = linePartOne.translate(shift.negate());
		positionOne = getSourcePosition().translate(shift);
		
		shift = linePartTwo.getFirst();
		linePartTwo = linePartTwo.translate(shift.negate());
		positionTwo = getSourcePosition().translate(shift);
		
		shift = line.getFirst();
		line = line.translate(shift.negate());
		position = getSourcePosition().translate(shift);

	}
	
	@Override
	public Point getPosition(PrimitiveEnd end) {
		if (end==channel.getChannelEndOne()) return positionOne;
		if (end==channel.getChannelEndTwo()) return positionTwo;
		return super.getPosition(end);
	}

	protected void addChannelLine(boolean dashed) {
		
		// Foreground color:
		Color color = getForegroundColor();
		
		double dash = AnimationConstants.DASH_SPACE;
		
		PrimitiveEnd endOne = channel.getChannelEndOne();
		PrimitiveEnd endTwo = channel.getChannelEndTwo();
		
		// Compute the paths.
		computeLine();
		splitLine();
		
		if (dashed) {
			double offset = AnimationConstants.SIZE_NODE / 2 - 2;
			addDashedLine( line, positionOne, color, channelLineWidth, dash, offset);
		} else {
			addLine( line, positionOne, color, channelLineWidth);
		}
		
		// Add optional decorations.
		addChannelDecorations();
		Shape flow = null;
		
		// Only flow at the first end.
		if (getAnimation().isFlow(endOne) && !getAnimation().isFlow(endTwo)) {
			flow = addLine( linePartOne, positionOne, new AlphaColor(AnimationConstants.COLOR_FLOW,0), AnimationConstants.WIDTH_FLOW);
		}
		
		// Only flow at the second end.
		if (!getAnimation().isFlow(endOne) && getAnimation().isFlow(endTwo)) {
			flow = addLine( linePartTwo, positionTwo, new AlphaColor(AnimationConstants.COLOR_FLOW,0), AnimationConstants.WIDTH_FLOW);
		}
		
		// Flow on both ends.
		if (getAnimation().isFlow(endOne) && getAnimation().isFlow(endTwo)) {
			flow = addLine(this.line, position, new AlphaColor(AnimationConstants.COLOR_FLOW,0), AnimationConstants.WIDTH_FLOW);
		}
		
		if (flow!=null) {
			setHighlighting(flow, AnimationConstants.FADE_IN, AnimationConstants.TRANSPARENCY_FLOW);
		}
		
	}
	
	/**
	 * Add decorations to the channel line. This is called by {@link #addChannelLine(boolean)}
	 * <i>before</i> adding the flow colorings. Subclasses may override this to add decorations
	 * to the channel, like arrows etc.
	 */
	protected void addChannelDecorations() {
		// by default we add nothing.
	}
	
	protected void addSourceArrow() {
		addChannelArrow(channel.getChannelEndOne());
	}


	protected void addTargetArrow() {
		addChannelArrow(channel.getChannelEndTwo());
	}
	
	
	protected void addChannelArrow(PrimitiveEnd end) {
		
		PointList points = getFlowPath(end);
		boolean flipped = isFlipped(end);
		
		Point position  = !flipped ? points.get(0) : points.getLast();
		Point direction = !flipped ? points.get(1) : points.get(points.size()-2);
		
		position = position.translate( getPosition(end) );
		direction = direction.translate( getPosition(end) );
		
		// Detwrmine the distance of the arrow:
		int arrowDistance = AnimationConstants.SIZE_CHANNEL_ARROW;
		if (ReoPreferences.drawNodes()) {
			arrowDistance += AnimationConstants.SIZE_NODE / 2;
		}
		if (!flipped || !ReoPreferences.drawNodes()) {
			arrowDistance = arrowDistance - (AnimationConstants.SIZE_CHANNEL_ARROW / 5);
		}
		
		//Shape arrow = 
		addPrimitiveArrow(position, direction, arrowDistance, flipped);
		//if (getAnimation().isFlow(end)) {
		//	setHighlighting(arrow, AnimationConstants.FADE_FLOW);
		//}
	}
	
	
	protected void addNoFlowArrows() {
		addNoFlowArrow(channel.getChannelEndOne());
		addNoFlowArrow(channel.getChannelEndTwo());
	}
	
	
	
	protected void addNoFlowArrow(PrimitiveEnd end) {
		if (!getAnimation().isFlow(end)) {
			
			PointList startPoints;
			Point startMidPoint, startDirectionPoint;
			
			if (!isFlipped(end)) {
				startPoints = getFlowPath(end).split(2).get(0);
				startMidPoint = startPoints.getLast();
				startDirectionPoint = startPoints.get( startPoints.size()-2 );
			} else {
				startPoints = getFlowPath(end).split(2).get(1);				
				startMidPoint = startPoints.getFirst();
				startDirectionPoint = startPoints.get(1);
			}
			
			Point mid = startMidPoint.translate(getPosition(end));
			Point dir = startDirectionPoint.translate(getPosition(end));
			
			if (getAnimation().isNoFlowGiveReason(end) || getAnimation().isNoFlowRequireReason(end)) {
				boolean flip = getAnimation().isNoFlowRequireReason(end);
				addNoFlowArrow(mid, dir, flip);
			}
		}
	}
	
	/**
	 * Add a label to the channel figure. The label is positioned relatively to 
	 * the middle of the channel line.
	 * @param labelText Label text.
	 * @param labelView Label view.
	 * @return The created text.
	 */
	public Text addChannelLabel(String labelText, Node labelView) {
				
		Location location = (Location) labelView.getLayoutConstraint();		
		Point loc = (location==null) ? new Point(0,0) : new Point(location.getX(), location.getY());
		
		Point position = loc.translate(line.getMiddle());
		position.x = position.x - (labelText.length()*4);
		return addText(labelText, position.translate(getSourcePosition()));
		
	}
	
	
	public PointList getFlowPath(PrimitiveEnd end) {
		// Already calculated?
		if (linePartOne==null || linePartTwo==null) {
			computeLine();
			splitLine();
		}
		if (channel.getChannelEndOne()==end) return linePartOne;
		if (channel.getChannelEndTwo()==end) return linePartTwo;
		return null;
	}
	
	
	protected boolean canJoinNoFlowArrows() {
		return true;
	}
	
	
	protected boolean isFlipped(PrimitiveEnd end) {
		return (end instanceof SinkEnd);
	}
	
	
	public Channel getChannel() {
		return channel;
	}
	
	
	public Point getMidLocation() {
		PrimitiveEnd end = channel.getChannelEndOne();
		PointList points = getFlowPath(end);
		Point mid = !isFlipped(end) ? points.getLast() : points.getFirst();
		return mid.translate(getPosition(end));
	}

	
	public Point getMidDirection() {
		PrimitiveEnd end = channel.getChannelEndTwo();
		PointList points = getFlowPath(end);
		Point mid = !isFlipped(end) ? points.get( points.size()-2 ) : points.get(1);
		return mid.translate(getPosition(end));
	}
	
	
	protected Point getSourcePosition() {
		return getNodePosition((Node) ((Edge) getView()).getSource());
	}
	
	protected Point getTargetPosition() {
		return getNodePosition((Node) ((Edge) getView()).getTarget());
	}

}

