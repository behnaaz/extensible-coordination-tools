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

import java.util.List;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.colouring.ColouringHiding;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class AnimationHiding {
	
	
	public static void hideEnds(AnimationTable table, List<PrimitiveEnd> ends) {

		// Do a hiding for the colourings.
		ColouringHiding.hideEnds(table, ends);
		
		// Hide ends also in the animation steps.
		for (AnimationTable current : table.getAllTables()) {
			for (int i=0; i<current.size(); i++) {
				
				Animation animation = current.get(i);
				for (int j=0; j<animation.getSteps().size(); j++) {
					
					// Remove the ends.
					AnimationStep step = animation.getSteps().get(j);
					step.getActiveEnds().removeAll(ends);
					
					// Remove the step if it is empty.
					if (step.getActiveEnds().isEmpty()) {
						animation.getSteps().remove(j--);
					}
				}
			}
		}
	}
	
}
