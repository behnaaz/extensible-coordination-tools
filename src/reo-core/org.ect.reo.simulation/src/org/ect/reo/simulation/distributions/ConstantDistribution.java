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
 * Will return the same value all the time.
 */
public class ConstantDistribution extends ProbabilityDistribution {
	
	private double constant;
	
	public ConstantDistribution() {
		this(0);	
	}
	
	public ConstantDistribution(double number) {
		super();
		constant = number;
	}
	
	@Override
	public double cumulative(double X) {
		return (X < constant ? 0 : 1);
	}

	@Override
	public double inverse(double probability) {
		return constant;
	}

	@Override
	public double probability(double X) {
		return (X == constant ? 1 : 0);
	}

}
