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
package org.ect.reo.animation;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Reo;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.ColouringReachabilityMap;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class AnimationExpander {
	
	@SuppressWarnings("serial")
	public class TableCache extends HashMap<AnimationTable,AnimationMatrix> {};
	
	private TableCache tableCache;
	
	private boolean removeDuplicates = true;
	private boolean removeDuplicateFlow = true;
	private boolean removeEmpty = true;
	private boolean removeNoFlow = true;
	private boolean removePartialFlow = true;
	
	private int maxlength = -1;
	private int maxcount = -1;
	
	public AnimationExpander() {
		tableCache = new TableCache();
	}
	
	
	/**
	 * Expand an animation table to an animation matrix.
	 */
	public AnimationMatrix expand(AnimationTable table, IProgressMonitor monitor) {
		
		// Check if the matrix has been computed already.
		if (tableCache.containsKey(table)) return tableCache.get(table);
		
		// Create a new matrix and a new cache for the animations.
		AnimationMatrix matrix = new AnimationMatrix();
		AnimationCache cache = new AnimationCache();
		
		// Compute the reachability map:
		ColouringReachabilityMap reachibilityMap = new ColouringReachabilityMap(table);
		
		monitor.beginTask("Expanding animations", table.size() * 2);
		Reo.debug("Expanding animations...");
		
		// Expand every animation.
		for (int i=0; i<table.size(); i++) {			
			Animation animation = table.get(i);
			
			// Cache has to be cleared!
			cache.clear();
			
			// Expand the animation and add it to the matrix.
			AnimationMatrix newMatrix = expand(animation, cache, reachibilityMap, 
					maxlength, maxcount, new SubProgressMonitor(monitor, 1));
			matrix.addAll(newMatrix);
			
			// Filter the animations.
			filterAnimations(matrix, new SubProgressMonitor(monitor, 1));
			if (maxcount>0 && matrix.size()>maxcount) {
				while (matrix.size()>maxcount) {
					matrix.remove(matrix.size()-1);
				}
				break;
			}
			
			if (monitor.isCanceled()) break;
		}
		
		// Update the cache.
		if (!monitor.isCanceled()) {
			tableCache.put(table, matrix);
		}
		monitor.done();
		
		return matrix;
	}
	
	
	/**
	 * Private helper method for expanding animations.
	 * @param animation Animation to be expanded.
	 * @param cache A hash map from animations to matrixes.
	 */
	private AnimationMatrix expand(Animation animation, 
			AnimationCache cache, ColouringReachabilityMap reachabilityMap,
			int maxlength, int maxcount, IProgressMonitor monitor) {
		
		// Get the next animation table.
		AnimationTable table = animation.getNextAnimationTable();
		monitor.beginTask("Expanding animations", table.size() * 2);
		
		// Create a new animation matrix and add it to the cache.
		AnimationMatrix matrix = new AnimationMatrix();
		cache.put(animation, matrix);
				
		// If the next table is empty, add a list with the animation to the matrix.
		if (table.size()==0) {
			AnimationList list = new AnimationList();
			list.add(animation);
			matrix.add(list);
		}
		
		// Otherwise iterate over the table content.
		else for (int i=0; i<table.size(); i++) {
			
			Animation nextAnimation = table.get(i);
			
			// Already visited?
			if (cache.containsEquivalent(nextAnimation)) {
				AnimationList list = new AnimationList();
				list.add(animation);
				List<Colouring> path = reachabilityMap.findPath(table, reachabilityMap.getInitial());
				if (path!=null) {
					for (Colouring colouring : path) {
						list.add((Animation) colouring);
					}
				}
				matrix.add(list);
				filterAnimations(matrix, new SubProgressMonitor(monitor, 2));
			}
			
			else if (maxlength!=0) {
				
				// Expand recursively.
				AnimationMatrix nextMatrix = expand(nextAnimation, cache, reachabilityMap,
						maxlength-1, maxcount, new SubProgressMonitor(monitor, 1));
				
				// No further animation steps.
				if (nextMatrix.isEmpty()) {
					AnimationList list = new AnimationList();
					list.add(animation);
					matrix.add(list);
					monitor.worked(1);
				}
				
				// More steps...
				else {
					
					SubProgressMonitor submonitor = new SubProgressMonitor(monitor, 1);
					submonitor.beginTask("Expanding animations", nextMatrix.size());
					
					for (int j=0; j<nextMatrix.size(); j++) {
						AnimationList list = new AnimationList();
						list.add(animation);
						list.addAll(nextMatrix.get(j));
						matrix.add(list);
						filterAnimations(matrix, new SubProgressMonitor(submonitor, 1));
						// System.out.println("filtered " + j + " of " + nextMatrix.size());
						if (matrix.size()>maxcount) break;
						if (monitor.isCanceled()) break;
					}
					
					submonitor.done();			
				}	
			}
			
			if (monitor.isCanceled()) break;
		}
		
		monitor.done();
		return matrix;
		
	}
	
	
	/**
	 * Private helper method for filtering the animations.
	 */
	private void filterAnimations(AnimationMatrix matrix, IProgressMonitor monitor) {
		
		monitor.beginTask("Filtering animations", 3);
		
		if (removeEmpty) matrix.removeEmptyAnimations();
		monitor.worked(1);
		
		if (removeNoFlow) matrix.removeNoFlowAnimations();
		monitor.worked(1);
		
		if (removePartialFlow) matrix.removePartialFlowAnimations(); else
		if (removeDuplicateFlow) matrix.removeDuplicateFlowAnimations(); else
		if (removeDuplicates) matrix.removeDuplicateAnimations();
		monitor.worked(1);
		
		monitor.done();
		
	}
	

	public void setRemoveDuplicate(boolean removeDuplicate) {
		if (this.removeDuplicates!=removeDuplicate) {
			this.removeDuplicates = removeDuplicate;
			tableCache.clear();
		}
	}
	
	public void setRemoveDuplicateFlow(boolean removeDuplicateFlow) {
		if (this.removeDuplicateFlow!=removeDuplicateFlow) {
			this.removeDuplicateFlow = removeDuplicateFlow;
			tableCache.clear();
		}
	}
	
	public void setRemoveEmpty(boolean removeEmpty) {
		if (this.removeEmpty!=removeEmpty) {
			this.removeEmpty = removeEmpty;
			tableCache.clear();
		}
	}
	
	public void setRemoveNoFlow(boolean removeNoFlow) {
		if (this.removeNoFlow!=removeNoFlow) {
			this.removeNoFlow = removeNoFlow;
			tableCache.clear();
		}
	}
	
	public void setRemovePartial(boolean removePartial) {
		if (this.removePartialFlow!=removePartial) {
			this.removePartialFlow = removePartial;
			tableCache.clear();
		}
	}
	
	public void setMaxLength(int maxlength) {
		if (this.maxlength!=maxlength) {
			this.maxlength = maxlength;
			tableCache.clear();
		}
	}
	
	public void setMaxCount(int maxcount) {
		if (this.maxcount!=maxcount) {
			this.maxcount = maxcount;
			tableCache.clear();
		}
	}

}
