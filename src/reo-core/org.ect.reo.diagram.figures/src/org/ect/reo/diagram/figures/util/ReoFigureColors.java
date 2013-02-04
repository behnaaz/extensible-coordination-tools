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
package org.ect.reo.diagram.figures.util;

import java.lang.reflect.Field;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Color;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.Module;
import org.ect.reo.ReconfRule;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class ReoFigureColors {
	
	private static final Color[] CONNECTOR = new Color[] {
		new Color(null, 222, 232, 242), 
		new Color(null, 242, 252, 255)
	};
	
	private static final Color EXTERNAL_COMPONENT = new Color(null, 255, 255, 215);
	private static final Color INTERNAL_COMPONENT = new Color(null, 225, 225, 225);
	
	public static final Color MIXED_NODE = new Color(null, 128, 128, 128);	
	public static final Color BOUNDARY_NODE = new Color(null, 255, 255, 255);
	
	public static final Color FG_CHANNEL = new Color(null, 0, 0, 0);
	public static final Color FG_COMPONENT = new Color(null, 142, 142, 142);
	public static final Color FG_LINK = MIXED_NODE;
	
	// Color of custom primitives. Use getCustomPrimitiveColor(). 
	private static final Color FG_CUSTOM = new Color(null, 0, 0, 240);
	
	// Reconfiguration action colors. Use getReconfColor().
	private static final Color RECONF_CREATE = new Color(null, 0, 220, 0);
	private static final Color RECONF_DELETE = new Color(null, 220, 0, 0);
	private static final Color RECONF_FORBID = new Color(null, 220, 0, 220);
	
	
	/**
	 * Get the foreground color of an element that can be reconfigured.
	 * @param holder Element.
	 * @param rule Rule to be used.
	 * @return The color.
	 */
	public static final Color getReconfColor(Reconfigurable element, ReconfRule rule) {
		if (rule==null || ReconfType.get(element, rule)==ReconfType.NONE) {
			if (element instanceof Component || element instanceof Connector) return FG_COMPONENT;
			else return FG_CHANNEL;
		}
		ReconfType type = ReconfType.get(element, rule);
		switch (type) {
			case CREATE : return RECONF_CREATE; 
			case DELETE : return RECONF_DELETE; 
			case FORBID : return RECONF_FORBID;
		default:
			break; 
		}
		return FG_CHANNEL;
	}
	
	/**
	 * Get the foreground color of a reconfigurable element, based on the
	 * currently active reconfiguration rule for its container module.
	 * @param element Reconfigurable element.
	 * @return Color.
	 */
	public static final Color getReconfColor(Reconfigurable element) {
		
		// Find the container module:
		EObject module = element.eContainer();
		while (module!=null && !(module instanceof Module)) {
			module = module.eContainer();
		}
		
		// Get the default reconfiguration rule:
		ReconfRule rule = (module!=null) ? ((Module) module).getActiveReconfRule() : null;
		return getReconfColor(element, rule);
		
	}

	/**
	 * Compute the default color for a connector. Subconnectors have
	 * slightly different colors then there parents, so that they can 
	 * be distinguished better.
	 * 
	 * @param connector The connector.
	 * @return Color that the connector should be displayed in.
	 */
	public static final Color getConnectorColor(Connector connector) {
		return CONNECTOR[connector.getLevel() % CONNECTOR.length];
	}
	
	public static final Color getComponentColor(Component component) {
		return (component.isInternal()) ? INTERNAL_COMPONENT : EXTERNAL_COMPONENT;
	}
	
	public static final Color getCustomPrimitiveColor(CustomPrimitive custom) {
		String value = custom.getForegroundColor();
		if (value==null) return FG_CUSTOM;
		value = value.trim();
		try{
			if (value.startsWith("#")) {
				int red = Integer.parseInt(value.substring(1,3), 16);	// hex format
				int green = Integer.parseInt(value.substring(3,5), 16);
				int blue = Integer.parseInt(value.substring(5,7), 16);
				return new Color(null, red, green, blue);
			} else {
				for (Field field : ColorConstants.class.getFields()) {
					if (value.equalsIgnoreCase(field.getName())) {
						return (Color) field.get(null);
					}
				}
			}
		} catch (Throwable t) {}
		return FG_CUSTOM;	// fall back
	}
	
}
