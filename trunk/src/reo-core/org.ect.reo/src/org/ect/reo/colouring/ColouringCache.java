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

import java.util.HashMap;

/**
 * Cache for colouring tables.
 * @author Christian Krause
 * @generated NOT
 */
@SuppressWarnings("serial")
public class ColouringCache extends HashMap<Colourable,ColouringTable> {
	
	@Override
	public ColouringTable get(Object o) {
		
		// Must be a colourable.
		Colourable p = (Colourable) o;
		
		// Check if cached.
		if (containsKey(p)) return super.get(p);
		
		// Compute the colouring table.
		ColouringTable t = p.getColouringTable();
		
		// Apply the flip rule is necessary.
		if (t.getColours()==3 && p.usesFlipRule()) {
			ColouringConverter.reverseFlipRule(t);
		}
		
		// Cache and return it.
		put(p,t);
		return t;
		
	}
		
}