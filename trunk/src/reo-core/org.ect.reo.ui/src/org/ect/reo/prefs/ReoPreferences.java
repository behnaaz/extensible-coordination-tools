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

import org.ect.reo.Reo;
import org.ect.reo.colouring.AbstractColouringEngine;
import org.ect.reo.colouring.ColouringEngine;
import org.ect.reo.colouring.ColouringEngineDescription;
import org.ect.reo.colouring.ColouringEngines;
import org.ect.reo.prefs.ReoPreferenceConstants.MCRL2Mode;
import org.ect.reo.util.ReoTraversal.TraversalType;



public class ReoPreferences {
	
	// ------- External programs ------ //
	public static String getLTSA() {
		return ReoPreferenceStore.getString(ReoPreferenceConstants.LTSA_HOME);		
	}
	
	public static void setLTSA(String ltsa) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.LTSA_HOME, ltsa);
	}
	
	public static String getXPrism() {
		return ReoPreferenceStore.getString(ReoPreferenceConstants.XPRISM);		
	}
	
	public static void setXPrism(String xprism) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.XPRISM, xprism);
	}
	
	
	public static String getMCRL2Home() {
		return ReoPreferenceStore.getString(ReoPreferenceConstants.MCRL2_HOME);		
	}
	
	public static void setMCRLHome(String mcrl2Home) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.MCRL2_HOME, mcrl2Home);
	}
	
	
	public static MCRL2Mode getMCRLMode() {
		String type = ReoPreferenceStore.getString(ReoPreferenceConstants.MCRL2_MODE);
		try {
			return MCRL2Mode.valueOf(type);
		} catch (Throwable t) {
			return MCRL2Mode.valueOf((String) ReoPreferenceConstants.DEFAULTS.get(
					ReoPreferenceConstants.MCRL2_MODE));
		}
	}
	
	public static void setMCRL2Mode(MCRL2Mode mode) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.MCRL2_MODE, mode.toString());
	}

	
	// -------- Animation preferences -------- //
	
	public static boolean showNoFlowArrows() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.SHOW_NO_FLOW_ARROW);
	}
	
	public static void setShowNoFlowArrows(boolean show) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.SHOW_NO_FLOW_ARROW, show);
	}

	
	public static boolean showNoFlowAnims() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.SHOW_NO_FLOW_ANIMS);
	}
	
	public static void setShowNoFlowAnims(boolean show) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.SHOW_NO_FLOW_ANIMS, show);
	}

	
	public static boolean loopAnimations() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.LOOP_ANIMATIONS);
	}
	
	public static void setLoopAnimations(boolean loop) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.LOOP_ANIMATIONS, loop);
	}

	
	public static boolean drawConnectorContainers() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.DRAW_CONNECTOR_CONTAINERS);
	}
	
	public static void setDrawConnectorContainers(boolean draw) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.DRAW_CONNECTOR_CONTAINERS, draw);
	}

	
	public static boolean drawNodes() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.DRAW_NODES);
	}
	
	public static void setDrawNodes(boolean draw) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.DRAW_NODES, draw);
	}

	
	public static boolean useSameTokenColor() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.USE_SAME_TOKEN_COLOR);
	}
	
	public static void setUseSameTokenColor(boolean same) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.USE_SAME_TOKEN_COLOR, same);
	}

	
	public static boolean useModuleScope() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.USE_MODULE_SCOPE);
	}

	public static void setUseModuleScope(boolean moduleScope) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.USE_MODULE_SCOPE, moduleScope);
	}

	
	public static boolean enableMultiCoreSupport() {
		return ReoPreferenceStore.getBoolean(ReoPreferenceConstants.MULTI_CORE_SUPPORT);
	}
	
	public static void setEnableMultiCoreSupport(boolean enable) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.MULTI_CORE_SUPPORT, enable);
	}

	
	public static int getMaxAnimationLength() {
		int length = ReoPreferenceStore.getInt(ReoPreferenceConstants.MAX_ANIMATION_LENGTH);
		return length >= 0 ? length : -1;
	}
	
	public static void setMaxAnimationLength(int length) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.MAX_ANIMATION_LENGTH, length);
	}
	
	
	public static int getMaxAnimationCount() {
		int count = ReoPreferenceStore.getInt(ReoPreferenceConstants.MAX_ANIMATION_COUNT);
		return count >= 0 ? count : 25;
	}
	
	public static void setMaxAnimationCount(int count) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.MAX_ANIMATION_COUNT, count);
	}

	
	public static int getAnimationSpeed() {
		int speed = ReoPreferenceStore.getInt(ReoPreferenceConstants.ANIMATION_SPEED);
		if (speed<1) return 1;
		if (speed>20) return 20;
		return speed;
	}
	
	public static void setAnimationSpeed(int speed) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.ANIMATION_SPEED, speed);
	}
	
	
	public static int getColours() {
		int colours = ReoPreferenceStore.getInt(ReoPreferenceConstants.COLOURS);
		return colours < 2 || colours > 4 ? 3 : colours;
	}
	
	public static void setColours(int colours) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.COLOURS, colours);
	}
	
	
	public static double getScaleFactor() {
		double scale = ReoPreferenceStore.getDouble(ReoPreferenceConstants.SCALE_FACTOR);
		if (scale==0.0) return (Double) ReoPreferenceConstants.DEFAULTS.get(ReoPreferenceConstants.SCALE_FACTOR);
		if (scale<0.1) return 0.1;
		if (scale>5.0) return 5.0;
		return scale;
	}
	
	public static void setScaleFactor(double scale) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.SCALE_FACTOR, scale);
	}

	public static String getAnimationFont() {
		return ReoPreferenceStore.getString(ReoPreferenceConstants.ANIMATION_FONT);
	}
	
	public static void setAnimationFont(String font) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.ANIMATION_FONT, font);
	}
	
	/**
	 * Get the current coloring engine implementation.
	 */
	public static ColouringEngine getColouringEngine() {
		
		ColouringEngine engine = null;
		String className = ReoPreferenceStore.getString(ReoPreferenceConstants.COLOURING_ENGINE);
		
		ColouringEngineDescription description = null;
		for (ColouringEngineDescription current : ColouringEngines.getDescriptions()) {
			if (current.getClassName().equals(className)) { description = current; break; }
		}
		
		// Otherwise use the default engine.
		if (description==null) description = ColouringEngines.DEFAULT;
		
		try {
			engine = description.loadEngine();
		} catch (Throwable e) {
			Reo.logError("Cannot load colouring engine: " + className, e);
		}
		
		if (engine==null) {
			// Second try.
			description = ColouringEngines.DEFAULT;
			try {
				engine = description.loadEngine();
			} catch (Throwable e) {
				Reo.logError("Cannot load default colouring engine.", e);
			}
		}
		
		// Initialize the engine.
		if (engine!=null) {
			engine.setColours(getColours());
			engine.setMaxSteps(getMaxAnimationLength());
		}
		
		// Set the traversal type.
		if (engine instanceof AbstractColouringEngine) {
			((AbstractColouringEngine) engine).setTraversalType(getTraversalType());
		}
		
		return engine;
		
	}
	

	public static TraversalType getTraversalType() {
		String type = ReoPreferenceStore.getString(ReoPreferenceConstants.TRAVERSAL_TYPE);
		try {
			return TraversalType.valueOf(type);
		} catch (Throwable t) {
			return TraversalType.valueOf((String) ReoPreferenceConstants.DEFAULTS.get(
					ReoPreferenceConstants.TRAVERSAL_TYPE));
		}
	}
	
	public static void setTraversalType(TraversalType type) {
		ReoPreferenceStore.setValue(ReoPreferenceConstants.TRAVERSAL_TYPE, type.toString());
	}
	
}
