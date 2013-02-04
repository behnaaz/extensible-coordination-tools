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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Module;
import org.ect.reo.Reconfigurable;
import org.ect.reo.reconf.Permutations.PermutationValidator;


/**
 * 
 * @author Christian Krause
 * @generated NOT
 */
public class ReconfMatchFinder {
	
	// Source and target modules.
	private Module source, target;
	
	// Source and target elements.
	private Reconfigurable[] sources, targets;
	
	// A base match.
	private ReconfMatch baseMatch;
	
	/**
	 * Default constructor.
	 */
	public ReconfMatchFinder(Module source, Module target, Reconfigurable[] sources, Reconfigurable[] targets) {
		this.source = source;
		this.target = target;
		this.sources = sources;
		this.targets = targets;
	}
	
	public void setBaseMatch(ReconfMatch baseMatch) {
		this.baseMatch = baseMatch;
	}
	
	/**
	 * Generate matches.
	 * @param max Maximum number of matches.
	 * @param monitor Progress monitor.
	 * @return Matches.
	 */
	public List<ReconfMatch> generate(int max, IProgressMonitor monitor) {
		
		List<ReconfMatch> result = new ArrayList<ReconfMatch>();
		
		// Check if the domain is empty.
		if (sources.length==0) {
			if (max!=0) result.add(new ReconfMatch(source, target));
			monitor.done();
			return result;
		}
		
		// Compute the matches as integer arrays.
		int[][] matches = Permutations.generate(sources.length, targets.length, new MatchValidator(), monitor);
		
		// Derive the actual reconfiguration matches.
		int size = (max>=0) ? Math.max(max, matches.length) : matches.length;		
		for (int i=0; i<size; i++) {
			result.add(computeMatch(matches[i]));
		}
		return result;
	}
	
	
	/*
	 * Compute the match that corresponds to the argument integer array.
	 */
	private ReconfMatch computeMatch(int[] array) {
		ReconfMatch match = (baseMatch!=null) ? new ReconfMatch(baseMatch) : new ReconfMatch(source, target);
		for (int i=0; i<array.length; i++) {
			match.put(sources[i], targets[array[i]]);
		}
		return match;
	}
	
	/**
	 * Get the computed source elements.
	 * @return Source elements.
	 */
	public Reconfigurable[] getSources() {
		return sources;
	}

	/**
	 * Get the computed target elements.
	 * @return Target elements.
	 */
	public Reconfigurable[] getTargets() {
		return targets;
	}


	/**
	 * Match validation class.
	 * @generated NOT
	 * @author Christian Krause
	 */
	class MatchValidator implements PermutationValidator {
		
		/*
		 * (non-Javadoc)
		 * @see org.ect.reo.reconf.Permutations.PermutationValidator#isValid(int[])
		 */
		public boolean isValid(int[] partial) {
			
			// Check the type of the last element first.
			int last = partial.length - 1;
			if (!ReconfMatch.canMatch(sources[last], targets[partial[last]])) return false;
			
			// If that is ok, check the complete partial match.
			try {
				ReconfMatch match = computeMatch(partial);
				match.validate();
			} catch (ReconfException e) {
				return false;
			}
			return true;
		}
	}
}
