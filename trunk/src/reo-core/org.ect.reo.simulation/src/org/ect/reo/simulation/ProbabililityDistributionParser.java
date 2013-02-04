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

import org.ect.reo.simulation.distributions.*;

import JSci.maths.statistics.*;

/**
 * Class for parsing a string to a ProbabilityDistribution
 *
 */
public class ProbabililityDistributionParser {
	
	/**
	 * Parse the string into a ProbabilityDistribution. If the parsing fails for some reason a default distribution is returned: Uniform(0, 100)
	 * @param s the string which represents the distribution
	 * @param channelDistribution boolean to indicate if the distribution is for the delay of a channel
	 * @return the ProbabilityDistribution
	 */
	public static ReoDistribution parseDistribution(String s, boolean channelDistribution) {
		
		try {
			int startArg = s.indexOf("(");
			int endArg = s.indexOf(")");
			
			int params = 0;
			ProbabilityDistribution distribution = null;
			
			if ((startArg != 0) && (endArg != 0)){
				s = s.trim();
				String[] args = s.substring(startArg+1, endArg).split(",");
				String dist = s.substring(0, startArg);

				if (dist.equalsIgnoreCase("unif") || dist.equalsIgnoreCase("uniform") || dist.equalsIgnoreCase("uni")) { 
					distribution = new UniformDistribution(Integer.parseInt(args[0].trim()), Double.parseDouble(args[1].trim()));
					params = 2;
				} else if (dist.equalsIgnoreCase("triangular") || dist.equalsIgnoreCase("tri")) { 
					distribution = new TriangularDistribution(Double.parseDouble(args[0].trim()), Double.parseDouble(args[2].trim()), Double.parseDouble(args[1].trim()));
					params = 2;
				} else if (dist.equalsIgnoreCase("beta")) { 
					distribution = new BetaDistribution(Double.parseDouble(args[0].trim()), Double.parseDouble(args[1].trim()));
					params = 2;
				} else if (dist.equalsIgnoreCase("chi2")) { 
					distribution = new ChiSqrDistribution(Double.parseDouble(args[0].trim()));
					params = 1;
				} else if (dist.equalsIgnoreCase("exponential") || dist.equalsIgnoreCase("exp") || dist.equalsIgnoreCase("expo")) { 
					distribution = new ExponentialDistribution(Double.parseDouble(args[0].trim()));
					params = 1;
				} else if (dist.equalsIgnoreCase("f")) { 
					distribution = new FDistribution(Double.parseDouble(args[0].trim()), Double.parseDouble(args[1].trim()));
					params = 2;
				} else if (dist.equalsIgnoreCase("gamma") || dist.equalsIgnoreCase("gam")) { 
					distribution = new GammaDistribution(Double.parseDouble(args[0].trim()));
					params = 1;
				} else if (dist.equalsIgnoreCase("lognormal") || dist.equalsIgnoreCase("logn")) { 
					distribution = new LognormalDistribution(Double.parseDouble(args[0].trim()), Double.parseDouble(args[1].trim()));
					params = 2;
				} else if (dist.equalsIgnoreCase("weibull") || dist.equalsIgnoreCase("wbl")) { 
					distribution = new WeibullDistribution(Double.parseDouble(args[0].trim()));
					params = 1;
				} else if (dist.equalsIgnoreCase("binomial") || dist.equalsIgnoreCase("bino") || dist.equalsIgnoreCase("bin")) { 
					distribution = new BinomialDistribution(Integer.parseInt(args[0].trim()), Double.parseDouble(args[1].trim()));
					params = 2;
				} else if (dist.equalsIgnoreCase("poisson") || dist.equalsIgnoreCase("poiss") || dist.equalsIgnoreCase("pois")) { 
					distribution = new PoissonDistribution(Double.parseDouble(args[0].trim()));
					params = 1;
				} else if (dist.equalsIgnoreCase("constant") || dist.equalsIgnoreCase("con") || dist.equalsIgnoreCase("cons") || dist.equalsIgnoreCase("const")) { 
					distribution = new ConstantDistribution(Double.parseDouble(args[0].trim()));
					params = 1;
				} else if (dist.equalsIgnoreCase("ifneeded")) { 
					return new ReoDistribution(new IfNeededDistribution(), false, false);
				} else if (dist.equalsIgnoreCase("always")) { 
					return new ReoDistribution(new AlwaysAvailableDistribution(), true, false);
				} else if (dist.equalsIgnoreCase("file")) { 
					if ((channelDistribution) || ((args.length > 1) && (args[1].equalsIgnoreCase("true")))) {
						distribution =  new FileDistribution(args[0].trim(), true);
						params = 2;
					} else {
						distribution =  new FileDistribution(args[0].trim(), false);
						params = 1;
					}
				}
				
				if ((args.length > params) && (args[params].equalsIgnoreCase("true"))) {
					if ((args.length > params + 1) && (args[params + 1].equalsIgnoreCase("true"))) {
						return new ReoDistribution(distribution, true, true);
					} else {
						return new ReoDistribution(distribution, true, false);
				    }
				} else {
					if ((args.length > params + 1) && (args[params + 1].equalsIgnoreCase("true"))) {
						return new ReoDistribution(distribution, false, true);
					} else {
						return new ReoDistribution(distribution, false, false);
				    }
				}
			}			
		} catch (Exception e) {
			System.out.println(s + ": not in the right format");
		}
		return new ReoDistribution(new ConstantDistribution(0), false, false);
	}
}
