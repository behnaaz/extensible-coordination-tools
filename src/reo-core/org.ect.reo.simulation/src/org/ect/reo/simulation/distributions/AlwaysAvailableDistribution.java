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
 * Special distribution which indicates that a Node is always available for sending data. So whenever the Node is finished processing
 * data, it will be ready to send new data immediately (so the state of the Node will never be empty). 
 *
 */
public class AlwaysAvailableDistribution extends ProbabilityDistribution {
	
	public AlwaysAvailableDistribution() {
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
