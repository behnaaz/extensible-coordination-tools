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
package org.ect.reo.reconf;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.ReconfRule;


/**
 * Interface for reconfiguration engines.
 * @generated NOT
 * @author Christian Krause
 */
public interface ReconfEngine {
	
	/**
	 * Apply a reconfiguration rule.
	 * @param rule Reconfiguration rule to be applied.
	 * @param match Match to be used.
	 * @param monitor Progress monitor.
	 */
	void applyRule(ReconfRule rule, ReconfMatch match, IProgressMonitor monitor) throws ReconfException;
	
	/**
	 * Add a reconfiguration listener.
	 * @param listener Reconfiguration listener.
	 */
	void addReconfListener(ReconfListener listener);

	/**
	 * Remove a reconfiguration listener.
	 * @param listener Reconfiguration listener.
	 */
	void removeReconfListener(ReconfListener listener);

}
