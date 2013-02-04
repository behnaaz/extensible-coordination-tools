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

/**
 * Triangular distribution
 */
public class TriangularDistribution extends ProbabilityDistribution {

	private double min, max, mode;
	
	public TriangularDistribution(double min, double max, double mode) {
		super();
		this.min = min;
		this.max = max;
		this.mode = mode;
	}
	
	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}

	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}

	/**
	 * @return the average
	 */
	public double getMode() {
		return mode;
	}

	@Override
	public double cumulative(double X) {
		if (X <= min) {
			return 0;
		} else if (X >= max) {
			return 1;
		} else if (X <= mode) {
			return (Math.pow((X - min), 2) / ((max - min) * (mode - min)));
		} else {
			return (1 - (Math.pow((max - X), 2) / ((max - min) * (max - mode))));
		}
	}

	@Override
	public double inverse(double probability) {
		checkRange(probability);
		if (probability < ((mode - min) / (max - min))) {
			return Math.sqrt((max - min) * (mode - min) * probability + min);
		} else {
			return (max - Math.sqrt((1 - probability) * (max - min) * (max - mode)));
		}
	}

	@Override
	public double probability(double X) {
		return 0;
	}

}
