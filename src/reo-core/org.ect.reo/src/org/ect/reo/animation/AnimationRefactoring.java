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

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.colouring.ColouringRefactoring;

/**
 * @generated NOT
 * @author Christian Krause
 */
public class AnimationRefactoring {

	/**
	 * Replace all occurrences of 'oldEnd' by 'newEnd' in all
	 * animation tables reachable from 'start'.
	 * @param start Animation table.
	 * @param oldEnd Old end.
	 * @param newEnd New end.
	 */
	public static void replaceEnd(AnimationTable start, PrimitiveEnd oldEnd, PrimitiveEnd newEnd) {
		
		// Replace the end in the colourings.
		ColouringRefactoring.replaceEnd(start, oldEnd, newEnd);
		
		// Replace it in the steps.
		for (AnimationTable table : start.getAllTables()) {	
			for (int i=0; i<table.size(); i++) {
				Animation animation = table.get(i);
				for (AnimationStep step : animation.getSteps()) {
					if (step.getActiveEnds().contains(oldEnd)) {
						step.getActiveEnds().add(newEnd);
						step.getActiveEnds().remove(oldEnd);
					}
				}
			}
		}
	}
	
}
