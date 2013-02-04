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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColouringReachabilityMap extends HashMap<ColouringTable,Set<ColouringTable>> {

	private static final long serialVersionUID = 1L;
	private ColouringTable initial;
	
	public ColouringReachabilityMap(ColouringTable initial) {
		this.initial = initial;
		compute(initial);
	}
	
	private void compute(ColouringTable current) {
		
		// Check if there is already an entry:
		if (containsKey(current)) {
			return;
		} else {
			put(current, new HashSet<ColouringTable>());
		}
		
		// The current target states:
		Set<ColouringTable> targets = get(current);
		
		// Put a self-loop:
		targets.add(current);
		
		// Update the reachability map:
		for (Colouring coloring : current.getColourings()) {
			ColouringTable next = coloring.getNextColouringTable();
			if (next!=null) {
				targets.add(next);
			}
		}

		// Continue recursively:
		for (Colouring coloring : current.getColourings()) {
			ColouringTable next = coloring.getNextColouringTable();
			if (next!=null) {
				compute(next);
			}
		}

	}
	
	public List<Colouring> findPath(ColouringTable start, ColouringTable end) {
		if (start==null || end==null || !containsKey(start) || !get(start).contains(end)) {
			return null;
		} else {
			List<Colouring> path = new ArrayList<Colouring>();
			Set<ColouringTable> visited = new HashSet<ColouringTable>();
			if (findPath(start, end, path, visited)) {
				return path;
			} else {
				return null;
			}
		}
	}

	private boolean findPath(ColouringTable start, ColouringTable end, 
			List<Colouring> path, Set<ColouringTable> visited) {
		
		// Finished already?
		if (start==end) {
			return true;
		}
		
		// Mark start state as visited:
		visited.add(start);
		
		for (Colouring colouring : start.getColourings()) {
			
			// Check whether the next state was visited already:
			ColouringTable next = colouring.getNextColouringTable();
			if (next==null || visited.contains(next)) {
				continue;
			}
			
			// Otherwise try to follow the path:
			path.add(colouring);
			if (findPath(next, end, path, visited)) {
				return true;
			}
			path.remove(path.size()-1);
		}
		
		// None found:
		return false;
		
	}


	public ColouringTable getInitial() {
		return initial;
	}
	
	public boolean canReachInitial(ColouringTable table) {
		return (table!=null && containsKey(table) && get(table).contains(initial));
	}
}
