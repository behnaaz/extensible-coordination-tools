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
package org.ect.reo.mcrl2.benchmarks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.equinox.app.IApplicationContext;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.ReconfRule;
import org.ect.reo.Reo;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2Preferences;
import org.ect.reo.reconf.ApplyRuleCommand;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.util.ReoTraversal.TraversalType;


/**
 * Benchmark application for testing different connector traversal.
 * @author Christian Krause
 * @generated NOT
 */
public class CounterBenchmark extends AbstractBenchmark {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		
		Reo2MCRL2Preferences.setWithData(false);
		Reo2MCRL2Preferences.setWithColour(false);
		Reo2MCRL2Preferences.setIntensionalEncoding(false);
		Reo2MCRL2Preferences.setblockingEncoding(false);
		Reo2MCRL2Preferences.setNetworkScope(false);

		List<Double> depth = benchmark(TraversalType.DEPTH_FIRST, 30, 4);
		List<Double> breadth = benchmark(TraversalType.BREADTH_FIRST, 14, 4);
		List<Double> none = benchmark(null, 3, 2);
		
		printResults("benchmarks/benchmarks.dat", 1, depth, breadth, none);
		return null;
		
	}
	
	
	private List<Double> benchmark(TraversalType type, int count, int tests) throws Exception {
		
		System.out.println("\nPERFORMING BENCHMARKS WITH TRAVERSAL: " + type + "\n");
		List<Double> times = new ArrayList<Double>();
		
		// Load the reo file:
		URI path = URI.createFileURI("benchmarks/counter.reo");
		Module module = Reo.loadModule(path);
		TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(
											module.eResource().getResourceSet());
		
		// Connector and reconfiguration rule to be used:
		Connector ncounter = module.getConnectors().get(0);
		Connector onecounter = module.getConnectors().get(1);
		
		// Register the mCRL2 semantics provider:
		ReoTextualSemantics.REGISTRY.add(new Reo2MCRL2());		
		Reo2MCRL2Preferences.setTraversalType(type);
		
		// Testing the 1-Counter:
		System.out.println("Testing connector '" + ncounter.getName() + "'");
		times.add(benchmarkConnector(onecounter, tests));
		
		// Testing the N-Counter:
		ReconfRule rule = module.getReconfRules().get(0);

		for (int i=2; i<=count; i++) {			
			System.out.println("Testing n=" + i);
			times.add(benchmarkConnector(ncounter, tests));
			
			// New apply rule command:
			ApplyRuleCommand command = new ApplyRuleCommand(domain, rule, module, module);
			command.execute(new NullProgressMonitor(), null);
		}
		
		return times;
	}
		
}
