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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.Reo;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.ReoPreferences;
import org.ect.reo.diagram.figures.geometry.Point;
import org.ect.reo.diagram.figures.geometry.PointList;

import com.anotherbigidea.flash.movie.Font;
import com.anotherbigidea.flash.movie.Frame;
import com.anotherbigidea.flash.movie.Instance;
import com.anotherbigidea.flash.movie.Shape;
import com.anotherbigidea.flash.movie.Symbol;
import com.anotherbigidea.flash.movie.Text;
import com.anotherbigidea.flash.movie.Transform;
import com.anotherbigidea.flash.structs.Color;



/**
 * Common superclass for all Reo-Flash figures. Subclasses must implement 
 * {@link #initSymbols()} to create the shapes that this figure consists of. 
 * The {@link #draw(Frame)}-method can then be used by external callers to
 * draw the shapes to a SWF frame.
 * @generated NOT
 */
public abstract class AbstractFigure implements IFlashFigure {
	
	// Hashmap assigning to each symbol its position. Order is important here.
	private LinkedHashMap<Symbol,Point> positions = new LinkedHashMap<Symbol,Point>();
	
	// Hashmap for transformations (mainly rotations).
	private HashMap<Symbol,Transform> transformations = new HashMap<Symbol,Transform>();

	// Hashmap assigning to each symbol its last instance.
	private HashMap<Symbol,Instance> instances = new HashMap<Symbol,Instance>();

	// The view associated to this figure.
	private View view;
	
	
	/**
	 * Default constructor. Subclasses must call this constructor.
	 * @param view The view associated with this figure.
	 */
	AbstractFigure(View view) {
		this.view = view;
	}
	
	/**
	 * Create all symbols of the figure and add them
	 * using one of the addShape() methods. This is
	 * a call-back function that must be implemented
	 * by all subclasses.
	 */
	protected abstract void initSymbols();
	
	
	/**
	 * Add a symbol to a specified position.
	 * @param symbol Symbol / shape to be added.
	 * @param position The position, where the symbol should be placed.
	 */
	protected final void addSymbol(Symbol symbol, Point position) {
		// Register the symbol and its position.
		positions.put(symbol, position);
	}
	
	
	/**
	 * Same as {@link #addSymbol(Symbol, Point)} but with an additional
	 * rotation argument, given in radians.
	 */
	protected final void addSymbol(Symbol symbol, Point position, double radian) {
		addSymbol(symbol, new Point(0,0));
		Transform matrix = new Transform(radian, position.x, position.y);
		transformations.put(symbol, matrix);		
	}
	
	
	/**
	 * Convenience method for {@link #addSymbol(Symbol, Point, double)}.
	 */
	protected final void addSymbol(Symbol symbol, Point position, Point direction, boolean flip) {
		double radians = Math.atan2(direction.y - position.y, direction.x - position.x);
		if (flip) radians = radians + Math.PI;
        addSymbol(symbol, position, radians);
	}
	
	
	
	/**
	 * Add text to the figure.
	 * @param text The string to be printed.
	 * @param position The texts position.
	 */
	protected final Text addText(String text, Point position, double fontSize, Color color) {
		
		// Make sure there are no weird characters 
		// and that it is not to long.
		if (text.indexOf('\n')>=0) text = text.substring(0, text.indexOf('\n')-1) + "...";
		if (text.indexOf('\t')>=0) text = text.substring(0, text.indexOf('\t')-1) + "...";
		
		// Draw the label.
        Text label = new Text(null);
        Font font = new Font(ReoPreferences.getFontDefinition());
		try {
	        label.row(font.chars(text, fontSize), color, 0, 0, true, true);
		} catch (Exception e) {
			Reo.logError("Unable to draw label", e);
		}
		addSymbol(label, position);
		return label;
	}
	
	/**
	 * Add text using the default font size and color.
	 */
	protected final Text addText(String text, Point position) {
		return addText(text, position, AnimationConstants.FONT_SIZE, AnimationConstants.COLOR_FONT);
	}
	
	
	/**
	 * Add a solid line to the figure.
	 * @param points List of relative bend-points (including start and end).
	 * @param position The position of the source node. 
	 * @param color The color of the line.
	 * @param width The width of the line.
	 */
	protected final Shape addLine(PointList points, Point position, Color color, double width) {
    	
    	Shape lineShape = new Shape();

		lineShape.setLineStyle(1);
		lineShape.defineLineStyle( width, color);

        Point loc = points.getFirst();
        lineShape.move(loc.x, loc.y);
        
        for (int i=1; i<points.size(); i++) {
        	loc = points.get(i);
            lineShape.line(loc.x, loc.y);
        }

        addSymbol(lineShape, position);
        
        return lineShape;
        
	}

	
	
	/**
	 * Add a dashed line to the figure.
	 * @param points List of relative bendpoints (including start and end).
	 * @param position The position of the source node. 
	 * @param color The color of the line.
	 * @param width The width of the line.
	 * @param space The space between the dashes. 
	 */
	protected final Shape addDashedLine(PointList points, Point position, Color color, double width, double space, double offset) {
    	
    	Shape lineShape = new Shape();

		lineShape.setLineStyle(1);
		lineShape.defineLineStyle( width, color);
		
        for (int i=1; i<points.size(); i++) {
        	offset = dashedLine(lineShape, points.get(i-1), points.get(i), space, offset);
        }

        // Remember the last offset.
        lastOffset = offset;
        
        addSymbol(lineShape, position);
        
        return lineShape;
        
	}

	
	private double lastOffset = 0.0;
	
	protected final Shape addDashedLine(PointList points, Point position, Color color, double width, double space) {
		return addDashedLine(points, position, color, width, space, lastOffset);
	}

	

	/**
	 * Private helper method for drawing a dashed line.
	 */
	private double dashedLine(Shape shape, Point start, Point end, double space, double offset) {

        double alpha = Math.atan2(end.y-start.y, end.x-start.x);
        
        double offsetX = offset * Math.cos(alpha);
        double offsetY = offset * Math.sin(alpha);
        
        Point p = new Point(start.x + offsetX, start.y + offsetY);
        shape.move(p.x, p.y);

        double length = p.getDistance(end);
        
        int pieces = (int) Math.floor(length / space);
        
        double dx = space * Math.cos(alpha);
        double dy = space * Math.sin(alpha);
        
        for (int i=0; i<pieces; i++) {
        	p = p.translate(dx, dy);
        	if (i % 2 == 0) shape.line(p.x, p.y); 
        	else shape.move(p.x, p.y);
        }
        
        return space - p.getDistance(end);

	}
	
	
	
	/**
	 * Draws all shapes / symbols of this figue to a frame.
	 * @param frame Frame where the shapes will be drawn.
	 */
	public void draw(Frame frame) {
		
		initSymbols();

		for (Symbol symbol : positions.keySet()) {
			
			Point position = positions.get(symbol);
			if (position==null) continue;
			
			// Place the symbol and remember the instance.
			Instance instance = frame.placeSymbol(symbol, (int) position.x, (int) position.y);
			instances.put(symbol, instance);
			
			// Check if there is a transformation assigned to that symbol.
			if (transformations.containsKey(symbol)) {
				frame.alter(instance, transformations.get(symbol), null);
			}

		}
		
	}

	
	/**
	 * Remove all shapes / symbols of this figure frome a frame.
	 * @param frame Frame from which the shapes will be removed.
	 */
	public void remove(Frame frame) {
		
		Iterator<Symbol> it = positions.keySet().iterator();

		while (it.hasNext()) {
			Symbol symbol = it.next();
			frame.remove(instances.get(symbol));
		}
		
		instances.clear();
	}
	
	
	public void redraw(Frame frame) {
		remove(frame);
		draw(frame);
	}
	
	
	// ----- Getters and setters ------ //

	/**
	 * Get the position of a symbol of this figure.
	 * @param symbol A symbol/shape of this figure.
	 * @return Its position.
	 */
	protected Point getPosition(Symbol symbol) {
		return positions.get(symbol);
	}

	/**
	 * Get the last instance of a symbol, which was drawn to a 
	 * frame using the {@link #draw(Frame)}-method.
	 * @param symbol A symbol/shape of this figure. 
	 * @return The last known instance of it.
	 */
	protected Instance getInstance(Symbol symbol) {
		return instances.get(symbol);
	}

	
	/**
	 * Getter for the symbols/shapes of this figure. 
	 */
	public List<Symbol> getSymbols() {
		return new Vector<Symbol>(positions.keySet());
	}

	/**
	 * Getter for the view.
	 */
	public View getView() {
		return view;
	}
	
	/**
	 * Setter for the view.
	 */
	protected void setView(View view) {
		this.view = view;
	}
	
}
