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

import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.flash.AnimationConstants;
import org.ect.reo.animation.flash.figures.shapes.RoundedRectangleShape;
import org.ect.reo.diagram.figures.geometry.Point;

import com.anotherbigidea.flash.movie.Shape;
import com.anotherbigidea.flash.structs.Color;


/**
 * Figure for named containers, e.g. connectors or components.
 * @author Christian Krause
 * @generated NOT
 */
public abstract class NamedContainerFigure extends PrimitiveFigure {
	
	/**
	 * Interface for customizing names of container figures.
	 * This has the advantage that that the model doesn't need 
	 * to be changed. 
	 * @author Christian Krause
	 */
	public static interface IContainerNameCustomizer {
		public String getCustomizedName(View view, String name);
	}
	
	// Name customizer.
	private static IContainerNameCustomizer nameCustomizer = null;
	
	// Flag indicating whether for this figure customizers are allowed.
	private boolean useCustomizer = true;	
	
	// Default properties of the container figure.
	protected Color backgroundColor, lineColor, fontColor;
	protected double lineWidth = 1.0;
	protected double fontSize = 14.0;
	protected int cornerRadius = 8;
	
	/**
	 * Default constructor.
	 * @param view View.
	 * @param animation Animation.
	 */
	public NamedContainerFigure(View view, Animation animation) {
		super(view, animation);
	}

	
	/**
	 * Create a named container in this figure.
	 */
	protected void createContainer(String name, Point position, Size size) {
		
		// Default colors.
		if (backgroundColor==null) backgroundColor = new Color(240,240,240);
		if (lineColor==null) lineColor = new Color(0,0,0);
		if (fontColor==null) fontColor = new Color(0,0,0);
		
		// Draw the rounded rectangle.
		RoundedRectangleShape shape = new RoundedRectangleShape(size.getWidth(), size.getHeight(), cornerRadius);
		shape.defineLineStyle(lineWidth, lineColor);
		shape.defineFillStyle(backgroundColor);
		shape.setRightFillStyle(1);
		shape.setLineStyle(1);
		shape.draw();
		
		addSymbol(shape, position);
		
		int gap = 2;
		
		// Draw the name label.
		if (name!=null) {
			
			// Customize the label.
			if (useCustomizer && nameCustomizer!=null) {
				String newName = nameCustomizer.getCustomizedName(getView(), name);
				if (newName!=null) name = newName;
			}
			
			Point offset = new Point(-name.length()*3-4, -size.getHeight() / 2 + fontSize + gap);
			addText(name, position.translate(offset), AnimationConstants.FONT_SIZE, fontColor);
		}
		
		// Draw an extra line.
		Shape line = new Shape();
		line.defineLineStyle(lineWidth, lineColor);
		line.setLineStyle(1);
		line.move(-size.getWidth() / 2 , 0);
		line.line(size.getWidth() / 2 , 0);
		Point offset = new Point(0, -size.getHeight() / 2 + fontSize + 4*gap);
		addSymbol(line, position.translate(offset));
		
	}
	
	protected void setUseNameCustomizer(boolean useCustomizer) {
		this.useCustomizer = useCustomizer;
	}
	
	public static void setNameCustomizer(IContainerNameCustomizer customizer) {
		nameCustomizer = customizer;
	}
}
