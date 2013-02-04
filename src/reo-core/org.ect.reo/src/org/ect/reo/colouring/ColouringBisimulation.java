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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * Bisimulation model and algorithms for colouring tables.
 * @author Christian Krause
 * @generated NOT
 */
public class ColouringBisimulation {
	
	// A map is used to encode the bisimulation relation.
	protected Map<ColouringTable,Set<ColouringTable>> map;
	
	/**
	 * Constructor for an (empty) bisimulation.
	 */
	public ColouringBisimulation() {
		map = new HashMap<ColouringTable,Set<ColouringTable>>();
	}
	
	/**
	 * Try to add an entry to the bisimulation. If this is not allowed
	 * by the bisimulation condition, the data is not changed and 
	 * <code>false</code> is returned.
	 * @param t1 First colouring table.
	 * @param t2 Second colouring table.
	 * @return True is the entry could be added.
	 */
	public boolean add(ColouringTable t1, ColouringTable t2) {
		ColouringBisimulation copy = copy();
		boolean success = copy.internalAdd(t1,t2);
		if (success) {
			copy.copyInto(this);
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Internal method for adding an entry, and all induced entries
	 * to this table. Returns <code>true</code> if the bisimulation
	 * condition is not violated at any point. If it is violated,
	 * <code>false</code> is returned and the bisimulation is not
	 * valid anymore.
	 * @param t1 First table.
	 * @param t2 Second table.
	 * @return True if the bisimulation is still valid.
	 */
	protected boolean internalAdd(ColouringTable t1, ColouringTable t2) {
		
		// Must be not null.
		if (t1==null || t2==null) return false;
		
		// Check if it is there already.
		if (contains(t1,t2)) return true;
		
		// Assume they are actually bisimilar.
		internalAddEntry(t1,t2);
		
		// The tables must have the same size to be bisimilar.
		if (t1.getColourings().size()!=t2.getColourings().size()) {
			return false;
		}
		
		// Check if both tables contains the same colourings and
		// add the entries for the next-tables to this bisimulation.
		for (Colouring c1 : t1.getColourings()) {
			boolean added = false;
			for (Colouring c2 : t2.getColourings()) {
				if (c1.equals(c2)) { // This does not compare the next-tables!!!
					// Recursively add the next entries.
					added = internalAdd(c1.getNextColouringTable(), c2.getNextColouringTable());
					break;
				}
			}
			// If there was a problem, we stop here already.
			if (!added) return false;
		}
		
		// Everything went well.
		return true;
	}
	
	/**
	 * Internal helper method for adding an entry to this bisimulation.
	 * @param t1 First table.
	 * @param t2 Second table.
	 */
	protected void internalAddEntry(ColouringTable t1, ColouringTable t2) {
		if (!map.containsKey(t1)) map.put(t1,new HashSet<ColouringTable>());
		map.get(t1).add(t2);
	}
	
	/**
	 * Check if this bisimulation contains the entry (t1,t2). 
	 * @param t1 First colouring table.
	 * @param t2 Second colouring table.
	 * @return True if the entry exists.
	 */
	public boolean contains(ColouringTable t1, ColouringTable t2) {
		return map.containsKey(t1) && map.get(t1).contains(t2);
	}
	
	/**
	 * Get the keys of this bisimulation. These are all tables
	 * that occur on the left side of the relation. If {@link #symmetricClosure()}
	 * was invoked before, it will contain all tables that are related
	 * by this bisimulation.
	 * @return Set of colouring tables.
	 */
	public Collection<ColouringTable> keys() {
		return map.keySet();
	}
	
	/**
	 * Get the set of tables that is bisimilar to the argument
	 * according to this bisimulation.
	 * @param table Colouring table.
	 * @return Bisimilar colouring tables.
	 */
	public Collection<ColouringTable> bisimilarTables(ColouringTable table) {
		if (map.containsKey(table)) {
			return map.get(table);
		} else {
			return new ArrayList<ColouringTable>();
		}
	}	
	
	/**
	 * Copy a bisimulation.
	 */
	public ColouringBisimulation copy() {
		ColouringBisimulation copy = new ColouringBisimulation();
		copyInto(copy);
		return copy;
	}
	
	/**
	 * Copy a bisimulation into a target bisimulation.
	 * @param target Target bisimulation.
	 */
	protected void copyInto(ColouringBisimulation target) {
		for (ColouringTable key : keys()) {
			for (ColouringTable value : map.get(key)) {
				target.internalAddEntry(key,value);
			}
		}
	}
	
	/**
	 * Make the symmetric, reflexive, transitive 
	 * closure of the relation.
	 */
	public void makeClosure() {
		symmetricClosure();
		reflexiveClosure();
		transitiveClosure();
	}
	
	/**
	 * Make the reflexive closure of the relation.
	 */
	public void reflexiveClosure() {
		for (ColouringTable key : copy().keys()) {
			internalAddEntry(key,key);
		}
	}
	
	/**
	 * Make the symmetric closure of the relation.
	 */
	public void symmetricClosure() {
		for (ColouringTable key : copy().keys()) {
			for (ColouringTable value : map.get(key)) {
				internalAddEntry(value,key);
			}
		}
	}
	
	/**
	 * Make the transitive closure of the relation.
	 */
	public void transitiveClosure() {
		Iterable<ColouringTable> keys = copy().keys();
		boolean changed = true;
		while (changed) {
			changed = false;
			for (ColouringTable key1 : keys) 
				for (ColouringTable key2 : map.get(key1)) 
					for (ColouringTable value : map.get(key2)) 
						if (!contains(key1,value)) {
							internalAddEntry(key1,value);
							changed = true;
						}
		}
	}
	
	
	// ------ Static helper methods ------ //
	
	/**
	 * Check if two colouring tables are bisimilar. This just creates
	 * a bisimulation object and tries to add the pair to it.
	 * @param t1 First colouring table.
	 * @param t2 Second colouring table.
	 * @return True if the two tables are bisimilar.
	 */
	public static boolean bisimilar(ColouringTable t1, ColouringTable t2) {
		ColouringBisimulation bisimulation = new ColouringBisimulation();
		return bisimulation.add(t1, t2);
	}

	/**
	 * Compute the largest bisimulation for a set of colouring
	 * transition systems.
	 * @param start Start colouring tables.
	 * @param monitor Progress monitor.
	 * @return Largest bisimulation.
	 */
	public static ColouringBisimulation computeLargest(Set<ColouringTable> start, IProgressMonitor monitor) {
		
		monitor.beginTask("Compute bisimulation", 100);
		ColouringBisimulation bisimulation = new ColouringBisimulation();
		if (start==null || start.isEmpty()) {
			return bisimulation;
		}
		
		// Get a list of all tables.
		List<ColouringTable> tables = new ArrayList<ColouringTable>(start);
		for (ColouringTable table : start) {
			tables.addAll(table.getAllTables());
		}
		monitor.worked(5); // 5%
		
		// Compute the bisimulation.
		IProgressMonitor subMonitor = new SubProgressMonitor(monitor, 90); // 95% 
		subMonitor.beginTask("Compute bisimulation", (tables.size() * (tables.size()-1)) / 2);
		for (int i=0; i<tables.size(); i++) {
			for (int j=i+1; j<tables.size(); j++) {
				// Try to add an entry.
				bisimulation.add(tables.get(i), tables.get(j));
				subMonitor.worked(1);
			}
		}
		subMonitor.done();
		
		// Compute symmetric, reflexive, transitive closure.
		bisimulation.makeClosure();
		monitor.worked(5); // 100%
		
		monitor.done();
		return bisimulation;
	
	}
	
	/**
	 * Compute the largest bisimulation for a colouring transition systems.
	 * @param start Start colouring table.
	 * @param monitor Progress monitor.
	 * @return Largest bisimulation.
	 */
	public static ColouringBisimulation computeLargest(ColouringTable start, IProgressMonitor monitor) {
		Set<ColouringTable> tables = new HashSet<ColouringTable>();
		tables.add(start);
		return computeLargest(tables, monitor);
	}
		
	/**
	 * Minimize a colouring transition system up to bisimilarity.
	 * @param start Start colouring table.
	 * @return Number of deleted tables.
	 */
	public static int minimize(ColouringTable start, IProgressMonitor monitor) {
		
		monitor.beginTask("Minimize colouring system", 10);
		
		// Compute the largest bisimulation.
		ColouringBisimulation bisimulation = computeLargest(start, new SubProgressMonitor(monitor,8)); // 80%
		
		// Remove bisimilar tables.
		IProgressMonitor subMonitor = new SubProgressMonitor(monitor,2); // 100%
		subMonitor.beginTask("Remove bisimilar tables", bisimulation.keys().size());
		Set<ColouringTable> removed = new HashSet<ColouringTable>();
		
		for (ColouringTable table : bisimulation.keys()) {
			for (ColouringTable bisimilar : bisimulation.bisimilarTables(table)) {
				
				// Identities are not interesting.
				if (table==bisimilar) continue;
				
				// Special case: start state
				if (bisimilar==start) {
					// Replace all references to 'table' by 'bisimilar' (equals 'start' here)
					if (!removed.contains(bisimilar)) {
						ColouringRefactoring.replaceNextRefs(start, table, bisimilar);
						removed.add(table);
					}
				} else {
					// Replace all references to 'bisimilar' by 'table'.
					if (!removed.contains(table)) {
						ColouringRefactoring.replaceNextRefs(start, bisimilar, table);
						removed.add(bisimilar);
					}
				}
			}
			subMonitor.worked(1);
		}
		
		// Done.
		subMonitor.done();
		monitor.done();
		return removed.size();
	}
	
}
