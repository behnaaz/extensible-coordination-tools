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
 * Uniform distribution
 */
public class UniformDistribution extends ProbabilityDistribution {
	private double min, max;
	
	public UniformDistribution() {
		this(0, 1);
	}
	
	public UniformDistribution(double min, double max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	@Override
	public double cumulative(double X) {
		if (X < min) {
			return 0;
		} else if (X > max) {
			return 1;
		} else {
			return (X - min) / (max - min);
		}
	}

	@Override
	public double inverse(double probability) {
		checkRange(probability);
		return min + (probability * (max - min));
	}

	@Override
	public double probability(double X) {
		return 0;
	}
}
