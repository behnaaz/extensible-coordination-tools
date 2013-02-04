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
package org.ect.reo.prefs;

import java.util.HashMap;

import org.ect.reo.colouring.ColouringEngines;
import org.ect.reo.util.ReoTraversal.TraversalType;


/**
 * Constant definitions for Reo plug-in preferences.
 */
public class ReoPreferenceConstants {
	
	// Animation preferences.
	public static final String COLOURING_ENGINE = "colouringEngine";
	public static final String SHOW_NO_FLOW_ARROW = "noflowArrows";
	public static final String SHOW_NO_FLOW_ANIMS = "noflowAnims";
	public static final String DRAW_CONNECTOR_CONTAINERS = "connectorContainers";
	public static final String DRAW_NODES = "drawNodes";
	public static final String USE_SAME_TOKEN_COLOR = "sameTokenColor";
	public static final String MAX_ANIMATION_LENGTH = "maxAnimationLength";
	public static final String MAX_ANIMATION_COUNT = "maxAnimationCount";
	public static final String COLOURS = "colours";
	public static final String SCALE_FACTOR = "scaleFactor";
	public static final String MULTI_CORE_SUPPORT = "multiCoreSupport";
	public static final String LOOP_ANIMATIONS = "loopAnimations";
	public static final String ANIMATION_SPEED = "animationSpeed";
	public static final String TRAVERSAL_TYPE = "traversalType";
	public static final String ANIMATION_FONT = "animationFont";
	public static final String USE_MODULE_SCOPE = "useModuleScope";
	
	// External programs.
	public static final String XPRISM = "xprism";
	public static final String MCRL2_HOME = "mcrl2Home";
	public static final String MCRL2_MODE = "mcrl2Mode";
	public static final String LTSA_HOME = "ltsaHome";
	
	public enum MCRL2Mode {
		CA, IA
	}
	
	// Default values.
	public static HashMap<String,Object> DEFAULTS;
	
	static {
		DEFAULTS = new HashMap<String, Object>();
		
		DEFAULTS.put(COLOURING_ENGINE, ColouringEngines.DEFAULT.getClassName());
		DEFAULTS.put(SHOW_NO_FLOW_ARROW, true);
		DEFAULTS.put(SHOW_NO_FLOW_ANIMS, false);
		DEFAULTS.put(DRAW_CONNECTOR_CONTAINERS, true);
		DEFAULTS.put(DRAW_NODES, true);
		DEFAULTS.put(USE_SAME_TOKEN_COLOR, false);
		DEFAULTS.put(MAX_ANIMATION_LENGTH, new Integer(-1));
		DEFAULTS.put(MAX_ANIMATION_COUNT, new Integer(25));
		DEFAULTS.put(COLOURS, new Integer(3));
		DEFAULTS.put(SCALE_FACTOR, new Double(1.0));
		DEFAULTS.put(MULTI_CORE_SUPPORT, false); //Runtime.getRuntime().availableProcessors()>1);
		DEFAULTS.put(LOOP_ANIMATIONS, false);
		DEFAULTS.put(ANIMATION_SPEED, new Integer(10));
		DEFAULTS.put(TRAVERSAL_TYPE, TraversalType.DEPTH_FIRST.toString());
		
		DEFAULTS.put(XPRISM, "/usr/local/bin/xprism");
		DEFAULTS.put(MCRL2_HOME, "/usr/local");
		DEFAULTS.put(MCRL2_MODE, MCRL2Mode.CA.toString());
		
	}
		
}
