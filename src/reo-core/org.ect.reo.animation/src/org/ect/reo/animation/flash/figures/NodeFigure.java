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

import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.ect.reo.NodeType;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.figures.shapes.CircleShape;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.util.ReoFigureColors;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.util.ReoNames;

import com.anotherbigidea.flash.movie.Shape;
import com.anotherbigidea.flash.movie.Transform;
import com.anotherbigidea.flash.structs.AlphaColor;
import com.anotherbigidea.flash.structs.Color;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class NodeFigure extends HighlightableFigure implements NetworkAwareFigure {
	
	// Reo node to be animated.
	private org.ect.reo.Node reoNode;
	
	// Animation model.
	private Animation animation;

	// Network view to be used.
	private NetworkView networkView;
	
	/**
	 * Default constructor.
	 * @param visualNode Visual representation of a Reo node.
	 * @param animation ANimation to be used.
	 */
	public NodeFigure(Node visualNode, Animation animation) {
		super(visualNode);
		this.reoNode = (org.ect.reo.Node) visualNode.getElement();
		this.animation = animation;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.NetworkAwareFigure#setNetworkView(org.ect.reo.diagram.view.util.NetworkView)
	 */
	public void setNetworkView(NetworkView networkView) {
		this.networkView = networkView;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.AbstractFigure#initSymbols()
	 */
	protected void initSymbols() {

		boolean flow = false;
		
		// Find out whether there is any flow here.
		for (PrimitiveEnd end : reoNode.getAllEnds()) {
			if (animation.isFlow(end)) {
				flow = true;
				break;
			}
		}
		
		// Radius of the node.
		int r = AnimationConstants.SIZE_NODE / 2;
		
		// Create separate shapes for background, outline and decoration.
		Shape background = createBackground(r, reoNode);
		Shape outline = createOutline(r);
		Shape decoration = createDecoration(r);
		
		// Find out the absolute position of the node.
		Point position = (networkView==null) ? Point.getLocation((Node) getView()) :
						Point.fromLocation(networkView.getNodeLocation((Node) getView()));
		
		// Add the shapes to the figure.
		addSymbol(background, position);
		addSymbol(outline, position);
		if (decoration!=null) addSymbol(decoration, position);
		
		// Add highlighting if there is flow.
		if (flow) {
			Shape highlighting = createHighlighting(r);
			addSymbol(highlighting, position);
			setHighlighting(highlighting, AnimationConstants.FADE_IN, AnimationConstants.TRANSPARENCY_FLOW);
		}
		
		// Try to add the name label.
		String name = ReoNames.getName(reoNode);
		if (name!=null && !name.equals("") && !getView().getChildren().isEmpty()) {
			
			Node label = (Node) getView().getChildren().get(0);
			Location location = (Location) label.getLayoutConstraint();
			double x,y;
			
			// Check if it is to close.
			if (Math.abs(location.getX())<8 && Math.abs(location.getY())<8) {
				x = -30;
				y = 20;
			} else {
				// Horizontal shifting.
				x = location.getX() - (AnimationConstants.SIZE_NODE / 2);
				y = location.getY() + (AnimationConstants.FONT_SIZE / 3);
			}
			
			//Text text = 
			addText(name, new Point(x, y).translate(position), AnimationConstants.FONT_SIZE, getForegroundColor());
			//if (flow) setHighlighting(text, AnimationConstants.FADE_FLOW);			
		}
		
		
	}
	
	
	private Shape createDecoration(int r) {
		
		Shape decoration = new Shape();
		
		decoration.defineLineStyle(AnimationConstants.WIDTH_CHANNEL, getForegroundColor());
		decoration.defineFillStyle(AnimationConstants.COLOR_FLOW);
		decoration.setLineStyle(1);
		//cross.setRightFillStyle(1);
		//cross.setLeftFillStyle(1);

		if (reoNode.getType()==NodeType.ROUTE) {
			
			decoration.move(CircleShape.COS_PI_DIV_4*r, CircleShape.SIN_PI_DIV_4*r);
			decoration.line(-CircleShape.COS_PI_DIV_4*r, -CircleShape.SIN_PI_DIV_4*r);

			decoration.move(-CircleShape.COS_PI_DIV_4*r, CircleShape.SIN_PI_DIV_4*r);
			decoration.line(CircleShape.COS_PI_DIV_4*r, -CircleShape.SIN_PI_DIV_4*r);

			return decoration;

		} else if (reoNode.getType()==NodeType.JOIN) {
			
			decoration.move(-r, 0);
			decoration.line(r, 0);
			
			decoration.move(0, -r);
			decoration.line(0, r);

			return decoration;

		}
		
		return null;
	}
	
	
	private Shape createBackground(int r, final org.ect.reo.Node reoNode) {
		
		Color border = getForegroundColor();
		Color center = new Color(
				Math.min(border.getRed()+127,255), Math.min(border.getGreen()+127,255), Math.min(border.getBlue()+127,255));
		
		CircleShape shape = new CircleShape(r);
		shape.defineLineStyle(AnimationConstants.WIDTH_CHANNEL, border);
		
		if (reoNode.isMixedNode() && reoNode.getType()==NodeType.REPLICATE) {
			shape.defineFillStyle( new Color[] { center, border }, new int[] { 0, 5}, new Transform(), true );
		} else {
			shape.defineFillStyle( AnimationConstants.COLOR_WHITE );
		}
		shape.setRightFillStyle(1);
		shape.draw();
		return shape;
		
	}
	
	
	private Shape createOutline(int r) {
		CircleShape shape = new CircleShape(r);
		shape.defineLineStyle(AnimationConstants.WIDTH_CHANNEL, getForegroundColor());
		shape.setLineStyle(1);
		shape.draw();
		return shape;
	}
	
	
	private Shape createHighlighting(int r) {
		CircleShape shape = new CircleShape(r);
		Color flow = new AlphaColor(AnimationConstants.COLOR_FLOW, 0);
		shape.defineLineStyle(0, flow);
		shape.defineFillStyle(flow);
		shape.setRightFillStyle(1);
		shape.draw();
		return shape;
	}

	/**
	 * Get the foreground color for this figure.
	 * @return The foreground color.
	 */
	protected Color getForegroundColor() {
		return AnimationConstants.convertColor(ReoFigureColors.getReconfColor(reoNode));
	}

}
