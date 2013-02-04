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

import java.util.ArrayList;
import java.util.List;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class AnimationCache {
	
	private List<Animation> animations = new ArrayList<Animation>();
	private List<AnimationMatrix> matrizes = new ArrayList<AnimationMatrix>();
	
	public void put(Animation animation, AnimationMatrix matrix) {
		int index = indexOf(animation);
		if (index>=0) {
			animations.set(index,animation);
			matrizes.set(index,matrix);
		} else {
			animations.add(animation);
			matrizes.add(matrix);
		}
	}
	
	public AnimationMatrix get(Animation animation) {
		int index = indexOf(animation);
		return (index>=0) ? matrizes.get(index) : null;
	}
	
	private int indexOf(Animation animation) {
		for (int i=0; i<animations.size(); i++) {
			if (animation==animations.get(i)) return i;
		}
		return -1;
	}
	
	public boolean containsKey(Animation animation) {
		return indexOf(animation) >= 0;
	}

	public boolean containsEquivalent(Animation animation) {
		for (int i=0; i<animations.size(); i++) {
			if (animation.getNextAnimationTable()==animations.get(i).getNextAnimationTable() &&
				animation.hasEqualFlow(animations.get(i))) return true;
		}
		return false;
	}
	
	public void clear() {
		animations.clear();
		matrizes.clear();
	}
	
}