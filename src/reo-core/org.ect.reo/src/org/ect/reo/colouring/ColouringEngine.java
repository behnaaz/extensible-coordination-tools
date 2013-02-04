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
package org.ect.reo.colouring;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * An interface for colouring engines.
 * @generated NOT
 * @author Christian Krause
 */
public interface ColouringEngine {
	
	/**
	 * Compute the colouring table for the current elements.
	 * @param monitor Progress monitor to be used.
	 * @return The resulting colouring table.
	 */
	public ColouringTable computeColouringTable(IProgressMonitor monitor);
	
	/**
	 * Set the current network elements to be used.
	 * @param elements Colourable elements.
	 */
	public void setElements(List<Colourable> elements);
	
	/**
	 * Set the number of colours that should be used. This should 
	 * be a value between <code>2</code> and <code>3</code>. Engines
	 * should use {@link org.ect.reo.colouring.ColouringConverter} to
	 * convert the colouring tables of the primitives and the nodes.
	 */
	public void setColours(int colours);
	
	/**
	 * Setting this flag to <code>true</code> tells this colouring engine 
	 * that no-flow colourings will be filtered out later and that there 
	 * is no need to compute these colourings. A no-flow colouring is a
	 * colouring that has no flow at any primitive of the connector.
	 */
	public void setIgnoreNoFlow(boolean ignoreNoFlow);
	
	/**
	 * Set the maximum number of steps to be computed.
	 * A negative value means that all steps shall be
	 * computed. The default is -1.
	 */
	public void setMaxSteps(int maxSteps);
	
	/**
	 * Set the current state of the network or of one of its primitives.
	 * The engine should when it retrieves the colouring tables of each of
	 * the primitives check whether this colouring refers to one of its tables
	 * and use this one instead.
	 */
	public void setCurrent(Colouring current);
	
	/**
	 * Set the colouring cache to be used.
	 * @param cache Colouring cache.
	 */
	public void setCache(ColouringCache cache);
}
