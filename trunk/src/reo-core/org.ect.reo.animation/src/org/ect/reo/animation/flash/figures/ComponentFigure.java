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

import java.util.HashMap;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Size;
import org.ect.reo.Component;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Property;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.figures.shapes.RectangleShape;
import org.ect.reo.diagram.edit.parts.SinkEndNameEditPart;
import org.ect.reo.diagram.edit.parts.SourceEndNameEditPart;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;
import org.ect.reo.diagram.figures.util.ReoFigureColors;
import org.ect.reo.diagram.view.util.GenericViewUtil;
import org.ect.reo.diagram.view.util.ReoViewFinder;
import org.ect.reo.util.ReoNames;

import com.anotherbigidea.flash.movie.Shape;
import com.anotherbigidea.flash.structs.AlphaColor;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ComponentFigure extends NamedContainerFigure {
	
	protected HashMap<PrimitiveEnd,PointList> flowPaths;
	protected HashMap<PrimitiveEnd,Point> pathPositions;
	protected HashMap<PrimitiveEnd,Point> endPositions;
	protected HashMap<PrimitiveEnd,Point> labelPositions;
	
	protected Point position;
	protected Size size;
	
	public ComponentFigure(Node node, Animation animation) {
		super(node, animation);
		flowPaths = new HashMap<PrimitiveEnd,PointList>();
		pathPositions = new HashMap<PrimitiveEnd,Point>();
		endPositions = new HashMap<PrimitiveEnd,Point>();
		labelPositions = new HashMap<PrimitiveEnd,Point>();
	}
	
	
	private void computeCoordinates() {
		
		// Position and size of the component.
		Component component = (Component) getView().getElement();
		
		size = getNodeSize((Node) getView());
		position = getNodePosition((Node) getView());
		
		// Coordinates of the primitive end and their edges.
		for (PrimitiveEnd end : component.getAllEnds()) {
			
			// Node
			Node node = ReoViewFinder.findComponentEndNode(end, getView().getDiagram());
			if (node==null) continue;
			endPositions.put(end, getNodePosition(node));
			
			// Edge.
			Edge edge = ReoViewFinder.findComponentEndEdge(end, getView().getDiagram());
			if (edge==null) continue;
			PointList points = new PointList(	getNodePosition((Node) edge.getSource()), 
												getNodePosition((Node) edge.getTarget()), 
												(RelativeBendpoints) edge.getBendpoints());
			flowPaths.put(end, points);
			pathPositions.put(end, getNodePosition((Node) edge.getSource()));
			
			// Label
			int visualId = (end instanceof SinkEnd) ? SinkEndNameEditPart.VISUAL_ID : SourceEndNameEditPart.VISUAL_ID;
			Node label = (Node) GenericViewUtil.findView(end, String.valueOf(visualId), node);
			if (label!=null) {
				labelPositions.put(end, Point.getLocation(label));
			}
			
		}
		
	}
	
	@Override
	protected void initSymbols() {
		
		Component component = (Component) getView().getElement();
		computeCoordinates();

		// Draw the named container.
		backgroundColor = AnimationConstants.getComponentColor(component);
		lineColor = AnimationConstants.convertColor(ReoFigureColors.getReconfColor(component));
		lineWidth = AnimationConstants.WIDTH_BORDER;
		fontSize = AnimationConstants.FONT_SIZE;
		fontColor = AnimationConstants.COLOR_FONT;
		if (ReoFigureColors.getReconfColor(component)!=ReoFigureColors.FG_COMPONENT) {
			fontColor = lineColor;
		}
		
		String name = ReoNames.getName(component);
		createContainer(name, position, size);

		// Draw the property labels.
		Point propPosition = position.translate(
									-size.getWidth()/2 + 10, 
									-size.getHeight()/2 + fontSize + 10);
		
		for (Property property : component.getProperties()) {
			if (property.isHidden()) continue;
			propPosition = propPosition.translate(0, fontSize + 2);
			String text = property.getKey() + "=" + property.getValue();
			boolean truncated = false;
			while (text.length() > (3 * size.getWidth()) / (2 * fontSize)) {
				text = text.substring(0, text.length()-1);
				truncated = true;
			}
			if (truncated) text = text + "...";
			addText(text, propPosition, fontSize, fontColor);
		}

		// Draw the lines.
		for (PrimitiveEnd end : flowPaths.keySet()) {
			
			Shape line = addLine(flowPaths.get(end), pathPositions.get(end), lineColor, AnimationConstants.WIDTH_LINK);
			registerPrimitiveEndSymbol(end, line);
			
			// Add No-Flow arrow.
			if (!getAnimation().isFlow(end)) {
				
				PointList partOne = flowPaths.get(end).split(2).get(0);
				Point midPoint = partOne.getLast().translate(pathPositions.get(end));
				Point directionPoint = partOne.get( partOne.size()-2 ).translate(pathPositions.get(end));
				
				if (getAnimation().isNoFlowGiveReason(end) || getAnimation().isNoFlowRequireReason(end)) {
					boolean flip = getAnimation().isNoFlowGiveReason(end) ^ (end instanceof SourceEnd);
					addNoFlowArrow( midPoint, directionPoint, flip);
				}
				
			}

			// Set highlighting if there is flow.
			else if (getAnimation().isFlow(end)) {
				Shape flow = addLine(flowPaths.get(end), pathPositions.get(end), new AlphaColor(AnimationConstants.COLOR_FLOW, 0), AnimationConstants.WIDTH_FLOW );			
				setHighlighting(flow, AnimationConstants.FADE_IN, AnimationConstants.TRANSPARENCY_FLOW);
			}

		}
		
		// Draw the end nodes.
		int size = AnimationConstants.SIZE_PRIMITIVE_END;
		
		for (PrimitiveEnd end : endPositions.keySet()) {
			
			RectangleShape buffer = new RectangleShape(size, size);
			buffer.defineLineStyle(AnimationConstants.WIDTH_BORDER, lineColor);
			buffer.setLineStyle(1);
			buffer.defineFillStyle(AnimationConstants.COLOR_PRIMITIVE_END);
			buffer.setRightFillStyle(1);
			buffer.draw();
			
			// Add the shape.
			addSymbol(buffer, endPositions.get(end));
			
			// Add flow highlighting.
			if (getAnimation().isFlow(end)) {
				
				RectangleShape flow = new RectangleShape(size, size);
				flow.defineLineStyle(0, lineColor);
				flow.setLineStyle(1);
				flow.defineFillStyle(new AlphaColor(AnimationConstants.COLOR_FLOW, 0));
				flow.setRightFillStyle(1);
				flow.draw();
				
				addSymbol(flow, endPositions.get(end));
				setHighlighting(flow, AnimationConstants.FADE_IN, AnimationConstants.TRANSPARENCY_FLOW);			}
			
		}
		
		// Draw the end labels.
		for (PrimitiveEnd end : labelPositions.keySet()) {
		
			name = ReoNames.getName(end);
			if (name==null || name.equals("")) continue;
			Point location = labelPositions.get(end);
			
			// Check if it is to close.
			if (Math.abs(location.x)<8 && Math.abs(location.y)<8) {
				location.x = -30;
				location.y = 30;
			} else {
				// Vertical shifting.
				location.x = location.x - AnimationConstants.SIZE_PRIMITIVE_END / 2 - 2;
				location.y = location.y + AnimationConstants.SIZE_PRIMITIVE_END / 2 - 2;
			}
			addText(name, location.translate(endPositions.get(end)), 
					fontSize, fontColor);
		}
	}
	
	
	@Override
	public PointList getFlowPath(PrimitiveEnd end) {
		return flowPaths.get(end);
	}
	
}
