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
/**
 * 
 */
package org.ect.codegen.reo2ea.transform;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.ect.reo.PrimitiveEnd;

public abstract class EndNamingPolicy {
	protected Map<PrimitiveEnd, String> cache = new HashMap<PrimitiveEnd, String>();
	
	public String getName(PrimitiveEnd key) {
		if (!(cache.containsKey(key)))				
				setName(key);
		
		 return cache.get(key);
	}

	protected abstract void setName(PrimitiveEnd end);

//	public abstract Collection<String> getSynthNames();
	
	public Map<PrimitiveEnd, String> getAllNames() {
		return Collections.unmodifiableMap(cache);
	}
}
