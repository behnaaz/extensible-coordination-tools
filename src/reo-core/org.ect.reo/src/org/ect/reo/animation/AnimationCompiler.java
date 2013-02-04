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
package org.ect.reo.animation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Reo;
import org.ect.reo.colouring.AbstractColouringEngine;
import org.ect.reo.colouring.Colourable;
import org.ect.reo.colouring.ColouringCache;
import org.ect.reo.colouring.ColouringEngine;
import org.ect.reo.colouring.ColouringTable;
import org.ect.reo.colouring.StepwiseColouringEngine;



/**
 * Helper methods for computing the animation tables 
 * of a network or a connector.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public class AnimationCompiler {
	
	/**
	 * Compute an animation table.
	 * @param elements Elements to be used.
	 * @param border Border elements (not to be used).
	 * @param engine Engine to be used (can be null).
	 * @param monitor Monitor.
	 * @return The compiled table.
	 */
	public static AnimationTable computeAnimations(
			List<Colourable> elements, List<Connectable> border, 
			ColouringEngine engine, ColouringCache cache,
			IProgressMonitor monitor) {
		
		monitor.beginTask("Compute animations", 5);
		if (engine==null) {
			engine = new StepwiseColouringEngine();
		}
		engine.setElements(elements);
		
		// Try to set the border elements.
		if (border==null) border = new ArrayList<Connectable>();
		if (engine instanceof AbstractColouringEngine) {
			((AbstractColouringEngine) engine).setBorderElements(border);
		} else if (!border.isEmpty()) {
			Reo.logWarning(engine + " does not support border elements!");
		}
		
		if (cache==null) {
			cache = new ColouringCache();
		}
		engine.setCache(cache);
		
		// Compute the colouring table.
		ColouringTable table = engine.computeColouringTable(new SubProgressMonitor(monitor,4));			
		
		if (monitor.isCanceled()) return new AnimationTable();
		
		// Compile the animation table.
		CompiledAnimationTable compiled = new CompiledAnimationTable();
		compiled.setColouringTable(table);
		compiled.setColouringCache(cache);
		compiled.setBorderElements(border);
		compiled.compile();
		
		monitor.worked(1);
		monitor.done();
		
		return compiled;
		
	}
	
	/**
	 * Compute the animation table for a connector.
	 * @param connector Connector.
	 * @param engine Colouring engine.
	 * @param cache Colouring cache.
	 * @param monitor Monitor.
	 * @return The compiled animation table.
	 */
	public static AnimationTable computeAnimations(
			Connector connector, ColouringEngine engine, ColouringCache cache, IProgressMonitor monitor) {
		
		List<Colourable> elements = new ArrayList<Colourable>();
		elements.addAll(connector.getPrimitives());
		elements.addAll(connector.getNodes());
		
		List<Connectable> border = new ArrayList<Connectable>();
		border.addAll(connector.getForeignPrimitives());
		border.addAll(connector.getForeignNodes());
		
		return computeAnimations(elements, border, engine, cache, monitor);
	}
	
	
	/**
	 * Compute the animation table for a network.
	 * @param network Network.
	 * @param engine Colouring engine.
	 * @param cache Colouring cache.
	 * @param monitor Monitor.
	 * @return The compiled animation table.
	 */
	public static AnimationTable computeAnimations(
			Network network, ColouringEngine engine, ColouringCache cache, IProgressMonitor monitor) {
		
		List<Colourable> elements = new ArrayList<Colourable>();
		elements.addAll(network.getAllPrimitives());
		elements.addAll(network.getAllNodes());
		
		return computeAnimations(elements, null, engine, cache, monitor);
		
	}

}
