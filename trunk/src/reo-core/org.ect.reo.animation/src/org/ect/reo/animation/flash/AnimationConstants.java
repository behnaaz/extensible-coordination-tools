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
package org.ect.reo.animation.flash;

import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.ReconfRule;
import org.ect.reo.Reconfigurable;
import org.ect.reo.diagram.figures.util.ReoFigureColors;
import org.ect.reo.diagram.figures.util.ReoFigureSizes;

import com.anotherbigidea.flash.structs.AlphaTransform;
import com.anotherbigidea.flash.structs.Color;



public class AnimationConstants {

	// General animation properties.
	public static final double FONT_SIZE = 14.0;	
	public static final int FRAME_RATE = 40;
	
	
	// Ideally, the speed values should all be dividable by SPEED_REFERENCE_VALUE.
	// The speed values will be multiplied with the animation speed preference!
	public static final int SPEED_TOKEN_FADE = 6;
	public static final int SPEED_TOKEN_MOVE = 4;
	public static final int SPEED_COLOURING_FADE = 4;
	public static final int SPEED_REFERENCE_VALUE = 1000;
	
	
	// Line widths.
	public static final double WIDTH_CHANNEL = 2.5;
	public static final double WIDTH_LINK = 2.5;
	public static final double WIDTH_NO_FLOW_ARROW = 3.0;
	public static final double WIDTH_FLOW = 12;
	public static final double WIDTH_BORDER = 2.0;
	
	// Shape sizes (this is usually the height).
	public static final int SIZE_NODE = ReoFigureSizes.NODE.getWidth();
	public static final int SIZE_PRIMITIVE_END = ReoFigureSizes.SOURCE_END.getWidth();
	public static final int SIZE_FIFO_BUFFER = 15;
	public static final int SIZE_CHANNEL_ARROW = 10;
	public static final int SIZE_NO_FLOW_ARROW = 10;
	public static final int SIZE_TOKEN = 14;

	// Space in dashed lines.
	public static final double DASH_SPACE = 8.0; 
	
	// Color constants.
	public static final Color COLOR_WHITE = new Color(255, 255, 255);
	public static final Color COLOR_BLACK = new Color(0, 0, 0);
	public static final Color COLOR_GREY = new Color(130, 130, 130);
	public static final Color COLOR_RED = new Color(255, 0, 0);
	public static final Color COLOR_GREEN = new Color(0, 255, 0);
	public static final Color COLOR_BLUE = new Color(0, 0, 255);
	public static final Color COLOR_YELLOW = new Color(255, 255, 0);
	public static final Color COLOR_VIOLETT = new Color(255, 0, 255);
	public static final Color COLOR_MAGENTA = new Color(0, 255, 255);
	public static final Color COLOR_ORANGE = new Color(255, 128, 0);
	
	// Colors of elements.
	
	// General border and background color.
	public static final Color COLOR_BORDER = convertColor(ReoFigureColors.FG_COMPONENT);
	public static final Color COLOR_FONT = COLOR_BLACK;
	
	// Connector and component colors.	
	public static final Color getConnectorColor(Connector connector) {
		return convertColor(ReoFigureColors.getConnectorColor(connector));
	}
	public static final Color getComponentColor(Component component) {
		return convertColor(ReoFigureColors.getComponentColor(component));
	}

	// Channel colors.
	//public static final Color COLOR_CHANNEL = convertColor(ReoFigureColors.FG_CHANNEL);
	public static final Color COLOR_LINK = convertColor(ReoFigureColors.FG_LINK);
	public static final Color COLOR_PRIMITIVE_END = COLOR_WHITE;
	public static final Color COLOR_FIFO_BUFFER = COLOR_WHITE;
	
	// Color indicating (no-)flow.
	public static final Color COLOR_FLOW = COLOR_BLUE;
	public static final Color COLOR_NO_FLOW = COLOR_RED;
	public static final double TRANSPARENCY_FLOW = 0.35;
	public static final double TRANSPARENCY_NO_FLOW = 0.5;
	
	// Token colors.
	public static final Color[] COLOR_TOKEN = new Color[] { COLOR_GREEN, COLOR_BLUE, COLOR_YELLOW, 
															COLOR_MAGENTA, COLOR_VIOLETT, COLOR_ORANGE, 
															COLOR_GREY, COLOR_RED, COLOR_WHITE, COLOR_BLACK };
	
	// Reconfiguration colors.
	public static final Color getReconfColor(Reconfigurable element, ReconfRule rule) {
		return convertColor(ReoFigureColors.getReconfColor(element, rule));
	}
	
	// Fading constants.
	public static final AlphaTransform FADE_TO_RED = new AlphaTransform(1,0,0,0);
	public static final AlphaTransform FADE_TO_GREEN = new AlphaTransform(0,1,0,0);
	public static final AlphaTransform FADE_TO_BLUE = new AlphaTransform(0,0,1,0);
	public static final AlphaTransform FADE_IN = new AlphaTransform(0,0,0,1);
	public static final AlphaTransform FADE_OUT = new AlphaTransform(0,0,0,-1); 
	
	// Color indicating flow.
	@Deprecated
	public static final AlphaTransform FADE_FLOW = FADE_TO_BLUE;
	
	/**
	 * Convert a SWT color into a Flash color.
	 */
	public static Color convertColor(org.eclipse.swt.graphics.Color color) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue());
	}
	
}
