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

import java.util.Vector;


/**
 * @generated NOT
 * @author Christian Krause
 */
@SuppressWarnings("serial")
public class AnimationMatrix extends Vector<AnimationList> {

	public AnimationMatrix() {
	}

	public AnimationMatrix(AnimationMatrix matrix) {
		if (matrix==null) return;
		for (int i=0; i<matrix.size(); i++) {
			add(matrix.get(i));
		}
	}

	/**
	 * Remove all animations that have no flow at all.
	 */
	public void removeNoFlowAnimations() {
		for (int i=0; i<size(); i++) {
			get(i).removeNoFlowAnimations();
			if (!get(i).hasFlow()) remove(i--);
		}
	}

	/**
	 * Remove all animations that are empty.
	 */
	public void removeEmptyAnimations() {
		for (int i=0; i<size(); i++) {
			get(i).removeEmptyAnimations();
			if (get(i).isEmpty()) remove(i--);
		}
	}


	/**
	 * Remove all partial animations.
	 */
	public void removePartialFlowAnimations() {
		for (int i=0; i<size(); i++) {
			for (int j=0; j<size(); j++) {
				if (i==j) continue;
				if (get(i).hasPartialFlow(get(j))) {
					remove(j);
					i = j = 0;
				}
			}
		}
	}

	
	/**
	 * Remove duplicate animations.
	 */
	public void removeDuplicateAnimations() {
		for (int i=0; i<size(); i++) {
			for (int j=i+1; j<size(); j++) {
				if (get(i).equals(get(j))) remove(j--);
			}
		}
	}

	
	/**
	 * Remove duplicate animations only w.r.t. the flow.
	 */
	public void removeDuplicateFlowAnimations() {
		for (int i=0; i<size(); i++) {
			for (int j=i+1; j<size(); j++) {
				if (get(i).hasEqualFlow(get(j))) remove(j--);
			}
		}
	}
	
}