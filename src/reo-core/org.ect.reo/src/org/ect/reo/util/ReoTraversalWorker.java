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
package org.ect.reo.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Connectable;


/**
 * Worker interface for Reo traversals. The methods {@link #getStart()},
 * {@link #compare(Connectable, Connectable, Primitive)} and {@link #getShift()} steer 
 * the traversal. The method {@link #visit(Connectable, IProgressMonitor)}
 * are the actual worker methods.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public interface ReoTraversalWorker {
	
	/**
	 * Get the start element for the traversal.
	 */
	public Connectable getStart();
	
	/**
	 * Visit an element.
	 */
	public boolean visit(Connectable element, IProgressMonitor monitor);
	
	/**
	 * Compare two elements. Determines which element should be visited first.
	 */
	public int compare(Connectable e1, Connectable e2, Connectable current);
	
	/**
	 * Get the shift value for this worker. Default is zero. This is usually needed 
	 * only when using multiple workers. To avoid that the traversals of the different 
	 * workers overlap too much, every worker should use a different shift value.
	 */
	public int getShift();
	
	/**
	 * Get the priority of this worker. Default is {@value Thread#NORM_PRIORITY}. The
	 * value must be between {@value Thread#MIN_PRIORITY} and {@value Thread#MAX_PRIORITY}.
	 * This is only important when using multiple workers in a multi-threaded traversal.
	 */
	public int getPriority();

}
