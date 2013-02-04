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

import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.ReoPreferences;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;
import org.ect.reo.diagram.figures.util.ReoFigureColors;
import org.ect.reo.diagram.view.util.NetworkView;

import com.anotherbigidea.flash.movie.Shape;
import com.anotherbigidea.flash.movie.Symbol;
import com.anotherbigidea.flash.structs.AlphaColor;
import com.anotherbigidea.flash.structs.Color;


/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class PrimitiveFigure extends HighlightableFigure implements NetworkAwareFigure {
	
	// Animation model.
	private Animation animation;
	
	// Symbols for primitive ends.
	private HashMap<PrimitiveEnd, Symbol> primitiveEndSymbols;
	
	// NetworkView to be used.
	private NetworkView networkView;
	
	/**
	 * Default constructor.
	 */
	PrimitiveFigure(View view, Animation animation) {
		super(view);
		this.animation = animation;
		this.primitiveEndSymbols = new HashMap<PrimitiveEnd, Symbol>();
	}
	
	
	public abstract PointList getFlowPath(PrimitiveEnd end);
	
	
	public Point getPosition(PrimitiveEnd end) {
		Symbol symbol = getPrimitiveEndSymbol(end);
		return (symbol!=null) ? super.getPosition(symbol) : null;
	}

	
	public final Symbol getPrimitiveEndSymbol(PrimitiveEnd end) {
		return primitiveEndSymbols.get(end);
	}

	
	protected final void registerPrimitiveEndSymbol(PrimitiveEnd end, Symbol symbol) {
		primitiveEndSymbols.put(end, symbol);
	}
	
	protected Primitive getPrimitive() {
		return (Primitive) getView().getElement();
	}
	
	/**
	 * Get the foreground color for this figure.
	 * @return The foreground color.
	 */
	protected Color getForegroundColor() {
		return AnimationConstants.convertColor(ReoFigureColors.getReconfColor(getPrimitive()));
	}
	
	protected Shape addPrimitiveArrow(Point position, Point direction, int distance, boolean flip) {
		
    	Shape arrow = new Shape();

		arrow.setLineStyle(1);
		arrow.setRightFillStyle(1);

		arrow.defineLineStyle(1.0, getForegroundColor());
		arrow.defineFillStyle(getForegroundColor());

		int x = AnimationConstants.SIZE_CHANNEL_ARROW;
		int y = x / 2;
		
		// Negate distance if the arrow is flipped.
		if (flip) distance = -distance;
		
		arrow.move(distance, y);
		arrow.line(distance+x, 0);
		arrow.line(distance, -y);
		arrow.line(distance+(x/2), 0);
		arrow.line(distance, y);
		
        addSymbol(arrow, position, direction, flip);
        
        return arrow;
		
	}

	
	protected Shape addNoFlowArrow(Point position, Point direction, boolean flip) {
		
		if (!ReoPreferences.showNoFlowArrows()) return null;
		
    	Shape arrow = new Shape();
    	Color color = new AlphaColor(AnimationConstants.COLOR_NO_FLOW, 0);
    	
		arrow.defineLineStyle(AnimationConstants.WIDTH_NO_FLOW_ARROW, color);
		arrow.setLineStyle(1);
//		arrow.defineFillStyle(color);
//		arrow.setRightFillStyle(1);
		
		int size = AnimationConstants.SIZE_NO_FLOW_ARROW / 2;
		
		arrow.move(-size, size);
		arrow.line(size, 0);
		arrow.line(-size, -size);
		arrow.line(-size, size);
		
        addSymbol(arrow, position, direction, flip);
		
        setHighlighting(arrow, AnimationConstants.FADE_IN, AnimationConstants.TRANSPARENCY_NO_FLOW);
        
        return arrow;
        
	}
	
	/**
	 * Get the absolute position of a node. If the NetworkView of this figure
	 * is not set, it returns the normal position. Otherwise the absolute position
	 * of the node in the network is calculated.
	 */
	protected Point getNodePosition(Node node) {
		return (networkView==null) ? Point.getLocation(node) :
			Point.fromLocation(networkView.getNodeLocation(node));
	}
	
	/**
	 * Get the size of a node. Uses the NetworkView if possible.
	 */
	protected Size getNodeSize(Node node) {
		return (networkView==null) ? (Size) node.getLayoutConstraint() :
				networkView.getNodeSize(node);
	}

	
	/**
	 * Get the animation model.
	 */
	protected Animation getAnimation() {
		return animation;
	}
	
	/**
	 * Get the network view to be used.
	 */
	protected NetworkView getNetworkView() {
		return networkView;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.animation.flash.figures.NetworkAwareFigure#setNetworkView(org.ect.reo.diagram.view.util.NetworkView)
	 */
	public void setNetworkView(NetworkView networkView) {
		this.networkView = networkView;
	}
	
}
