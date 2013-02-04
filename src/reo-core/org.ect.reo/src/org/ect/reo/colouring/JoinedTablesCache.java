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
 * Cache for joining colouring tables.
 * 
 * @generated NOT
 * @author Christian Krause
 *
 */
class JoinedTablesCache {
	
	// The hash map.
	private HashMap<ColouringTable, HashMap<ColouringTable,ColouringTable>> tables;
	
	/**
	 * Default constructor.
	 */
	public JoinedTablesCache() {
		// Initialize the hash map.
		tables = new HashMap<ColouringTable, HashMap<ColouringTable,ColouringTable>>();
	}
	
	/**
	 * Find a joined colouring table.
	 */
	public ColouringTable getJoined(ColouringTable t1, ColouringTable t2) {
		if (tables.containsKey(t1) && tables.get(t1).containsKey(t2)) return tables.get(t1).get(t2);
		if (tables.containsKey(t2) && tables.get(t2).containsKey(t1)) return tables.get(t2).get(t1);
		return null;
	}
	
	/**
	 * Add a joined colouring table to the cache.
	 */
	public void putJoined(ColouringTable t1, ColouringTable t2, ColouringTable joined) {
		if (!tables.containsKey(t1)) tables.put(t1, new HashMap<ColouringTable,ColouringTable>());
		tables.get(t1).put(t2, joined);
	}
	
}