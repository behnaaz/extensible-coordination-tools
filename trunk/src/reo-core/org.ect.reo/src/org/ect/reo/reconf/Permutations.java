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
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * Helper class for generating permutations.
 * @generated NOT
 * @author Christian Krause
 *
 */
class Permutations {
	
	/*
	 * Interface for permutation checker.
	 */
	static interface PermutationValidator {
		boolean isValid(int[] partial);
	}
	
	/**
	 * Generate permutations: n out of m.
	 * @param size Size of the domain (n).
	 * @param range Size of the codomain (m).
	 * @param validator Permutation validator.
	 * @param monitor 
	 * @return All possible permutations.
	 */
	static int[][] generate(int size, int range, PermutationValidator validator, IProgressMonitor monitor) {
		
		// Verify parameters.
		if (size<=0 || range<size) return new int[0][];
				
		// Generate the permutations...
		List<int[]> perms = build(size, range, 0, null, validator, monitor);
		
		// Convert to array.
		int[][] result = new int[perms.size()][];
		result = perms.toArray(result);
		monitor.done();
		return result;
		
	}
	
	
	/*
	 * Internal method for building up all possible n-out-of-m permutations.
	 */
	private static List<int[]> build(int size, int range, int index, List<int[]> perms, PermutationValidator validator, IProgressMonitor monitor) {		
		
		monitor.beginTask("Computing permutations", size - index);
		
		// End of recursion?
		if (index>=size) {
			monitor.done();
			return perms;
		}
		
		// Recursion start?
		if (index<=0) {
			perms = new ArrayList<int[]>();
			int[] initial = new int[0];
			Arrays.fill(initial, -1);
			perms.add(initial);
		}
		
		// Make copies
		int copies = range - index;
		List<int[]> newPerms = new ArrayList<int[]>();
		IProgressMonitor subMonitor = new SubProgressMonitor(monitor,1);
		subMonitor.beginTask("Computing permutations", perms.size());
		
		for (int i=0; i<perms.size(); i++) {
			
			int[] current = perms.get(i);
			int[] allset = new int[index + copies];
			Arrays.fill(allset, -1);
			for (int k=0; k<index; k++) allset[k] = current[k];
			
			// Make copies and set index cell.
			for (int j=0; j<copies; j++) {
				int[] copy = copyOf(current, index+1);
				copy[index] = nextLargest(allset,range);
				allset[index + j] = copy[index];
				if (validator==null || validator.isValid(copy)) {
					newPerms.add(copy);
				}
			}
			subMonitor.worked(1);
		}
		subMonitor.done();
		
		// Recursion.
		subMonitor = new SubProgressMonitor(monitor, size - index - 1);
		List<int[]> result = build(size, range, index+1, newPerms, validator, subMonitor);
		monitor.done();
		
		return result;
	}
	
	
	/**
	 * Copy an array.
	 */
	public static int[] copyOf(int[] original, int newLength) {
        int[] copy = new int[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }
	
	
	// Test bits.
	private static int[] testBits = new int[0];
	
	/*
	 * Compute the next largest value in range.
	 */
	private static int nextLargest(int[] data, int range) {
		if (testBits.length < range) {
			testBits = new int[range*2];
		}
		Arrays.fill(testBits, 0);
		for (int i=0; i<data.length; i++) {
			if (data[i]>=0) testBits[data[i]] = 1;
		}
		for (int i=0; i<testBits.length; i++) {
			if (testBits[i]==0) return i;
		}
		return -1;
	}


	/*
	 * Main method for testing.
	 */
	public static void main(String[] args) {
		int[][] result = generate(3,5,null, new NullProgressMonitor());
		for (int[] c : result) {
			for (int i : c) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println("size: " + result.length);
	}
}
