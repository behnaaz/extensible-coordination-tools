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
package org.ect.reo.simulation.distributions;

import JSci.maths.statistics.ProbabilityDistribution;

public class ReoDistribution {
	ProbabilityDistribution distribution;
	boolean drawWhenEmpty;
	boolean alternate;
	boolean drawDistribution;

	public ReoDistribution(ProbabilityDistribution distribution, boolean drawWhenEmpty, boolean alternate) {
		this.distribution = distribution;
		this.drawWhenEmpty = drawWhenEmpty;
		this.alternate = alternate;
		drawDistribution = false;
	}
	
	
	public double inverse(double probability) {
		if (!alternate || drawDistribution) {
			drawDistribution = false;
			return distribution.inverse(probability);
		} else {
			drawDistribution = true;
			return 0;
		}
	}
	
	
	public void resetAlternation() {
		drawDistribution = false;
	}
	
	/**
	 * @return the distribution
	 */
	public ProbabilityDistribution getDistribution() {
		return distribution;
	}

	/**
	 * @return the drawWhenEmpty
	 */
	public boolean isDrawWhenEmpty() {
		return drawWhenEmpty;
	}
}
