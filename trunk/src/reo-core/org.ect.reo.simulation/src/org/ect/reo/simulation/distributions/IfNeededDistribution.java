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
 * Special distribution which indicates that a Node will provide it's data whenever it's needed. So the node will always be in an empty state,
 * and whenever the data is needed it will go to busy.
 */
public class IfNeededDistribution extends ProbabilityDistribution {
	
	public IfNeededDistribution() {
		super();
	}
	
	@Override
	public double cumulative(double X) {
		return 0;
	}

	@Override
	public double inverse(double probability) {
		return 0;
	}

	@Override
	public double probability(double X) {
		return 0;
	}

}
