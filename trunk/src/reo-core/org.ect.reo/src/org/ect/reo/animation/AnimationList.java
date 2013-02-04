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
public class AnimationList extends Vector<Animation> {
	
	/**
	 * Checks whether there is flow in this AnimationList. 
	 * @return <code>True</code> if there is an animation with flow.
	 */
	public boolean hasFlow() {
		for (Animation animation : this) {
			if (!animation.getSteps().isEmpty()) return true;
		}
		return false;
	}
	
	/**
	 * Checks if the list has the same flow as the argument.
	 * @return <code>True</code> if both lists have the same flow.
	 */
	public boolean hasEqualFlow(AnimationList list) {
		return this.hasPartialFlow(list) && list.hasPartialFlow(this);
	}
	
	/**
	 * Checks if the argument list is part of this animation list, w.r.t. the data flow.
	 * @return <code>True</code> if the argument is a part of this animation list;
	 */
	public boolean hasPartialFlow(AnimationList list) {
		int i=0, j=0;
		while (i<size() && j<list.size()) {
			// First check the argument, then this list!
			if (!list.get(j).hasFlow()) { j++; continue; }
			if (!get(i).hasFlow()) { i++; continue; }
			// Both have flow.
			if (!get(i).hasEqualFlow(list.get(j))) return false;
			i++; j++;
		}
		if (j!=list.size()) return false;
		return true;
	}
	
	/**
	 * Remove all empty animation from the list.
	 */
	public void removeEmptyAnimations() {
		for (int i=0; i<size(); i++) {
			if (get(i).getColours().isEmpty()) remove(i--);
		}
	}
	
	/**
	 * Remove no-flow animations.
	 */
	public void removeNoFlowAnimations() {
		for (int i=0; i<size(); i++) {
			if (!get(i).hasFlow()) remove(i--);
		}		
	}
}