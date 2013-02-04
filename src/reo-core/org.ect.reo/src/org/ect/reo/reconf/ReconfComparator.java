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

import java.util.Comparator;

import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.ReconfType;
import org.ect.reo.Reconfigurable;


/**
 * Comparator for sorting reconfigurable elements.
 * @generated NOT
 * @author Christian Krause
 *
 */
class ReconfComparator implements Comparator<Reconfigurable> {

	// Mode
	private ReconfType mode;
	
	/**
	 * Default constructor.
	 * @param mode comparison mode.
	 */
	ReconfComparator(ReconfType mode) {
		this.mode = mode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Reconfigurable e1, Reconfigurable e2) {
		
		int result = priority(e2) - priority(e1);
		
		// Special case: two connectors.
		if (e1 instanceof Connector && e2 instanceof Connector) {
			Connector c1 = (Connector) e1;
			Connector c2 = (Connector) e2;
			result = c2.getLevel() - c1.getLevel();
		}
		
		return (mode==ReconfType.DELETE) ? -result : result;
	}
	
	private int priority(Reconfigurable element) {
		if (element instanceof Primitive) return 1;
		if (element instanceof Node) return 2;
		if (element instanceof Connector) return 3;
		return 0;
	}
}
