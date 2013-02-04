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

import org.ect.reo.Reconfigurable;

/**
 * Interface for reconfiguration listeners.
 * @generated NOT
 * @author Christian Krause
 */
public interface ReconfListener {
	
	/**
	 * Notify the listener that a new element has bee created.
	 * @param element Newly created element.
	 * @param template Template that the element is created from.
	 */
	void elementCreated(Reconfigurable element, Reconfigurable template);
	
	/**
	 * Notify the listener that an element has been deleted.
	 * @param element Deleted element.
	 * @param template Template that caused the deletion.
	 */
	void elementDeleted(Reconfigurable element, Reconfigurable template);
	
}
