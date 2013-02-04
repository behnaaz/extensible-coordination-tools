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
package org.ect.reo.simulation;

import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.simulation.distributions.ReoDistribution;
import org.ect.reo.util.PropertyHelper;


/**
 * Static methods for accessing stochastic properties, such as delays
 * and arrival rates for nodes and primitives.
 */
public class StochasticReoProperties {
	
	/**
	 * Key for the 'processing delay 1' property of primitives.
	 */
	public static final String PROCESSING_DELAY1_KEY = "processingDelay1";
		
	public static ReoDistribution getProcessingDelay1(Primitive primitive) {
		String value = PropertyHelper.getFirstValue(primitive, PROCESSING_DELAY1_KEY);
		// This might be null!
		
		return ProbabililityDistributionParser.parseDistribution(value, true);
	}
	
	public static String getProcessingDelay1String(Primitive primitive) {
		String value = PropertyHelper.getFirstValue(primitive, PROCESSING_DELAY1_KEY);
		// This might be null!
		
		return (value == null ? "" : value);
	}

	public static void setProcessingDelay1(Primitive primitive, String delays) {
		PropertyHelper.setOrRemove(primitive, PROCESSING_DELAY1_KEY, delays);
	}
	
	/**
	 * Key for the 'processing delay 2' property of primitives.
	 */
	public static final String PROCESSING_DELAY2_KEY = "processingDelay2";
		
	public static ReoDistribution getProcessingDelay2(Primitive primitive) {
		String value = PropertyHelper.getFirstValue(primitive, PROCESSING_DELAY2_KEY);
		// This might be null!
		
		return ProbabililityDistributionParser.parseDistribution(value, true);
	}
	
	public static String getProcessingDelay2String(Primitive primitive) {
		String value = PropertyHelper.getFirstValue(primitive, PROCESSING_DELAY2_KEY);
		// This might be null!

		return (value == null ? "" : value);
	}

	public static void setProcessingDelay2(Primitive primitive, String delays) {
		PropertyHelper.setOrRemove(primitive, PROCESSING_DELAY2_KEY, delays);
	}
	
	/**
	 * Key for the 'Arrival rate' property of nodes.
	 */
	public static final String ARRIVAL_RATE_KEY = "arrivalRate";
		
	public static ReoDistribution getArrivalRate(Node node) {
		String value = PropertyHelper.getFirstValue(node, ARRIVAL_RATE_KEY);
		// This might be null!
		
		return ProbabililityDistributionParser.parseDistribution(value, false);
	}
	
	public static String getArrivalRateString(Node node) {
		String value = PropertyHelper.getFirstValue(node, ARRIVAL_RATE_KEY);
		// This might be null!
		
		return (value == null ? "" : value);
	}

	public static void setArrivalRate(Node node, String arrivalRate) {
		PropertyHelper.setOrRemove(node, ARRIVAL_RATE_KEY, arrivalRate);
	}
	
	
	/**
	 * Key for the 'Start with request' property of nodes.
	 */
	public static final String START_REQUEST_KEY = "startRequest";
		
	public static boolean getRequestStart(Node node) {
		String value = PropertyHelper.getFirstValue(node, START_REQUEST_KEY);
		// This might be null!
		
		return Boolean.valueOf(value);
	}

	public static void setRequestStart(Node node, String requestStart) {
		PropertyHelper.setOrRemove(node, START_REQUEST_KEY, requestStart);
	}
}
