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

import org.eclipse.equinox.app.IApplicationContext;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.FIFO;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2;
import org.ect.reo.mcrl2.conversion.Reo2MCRL2Preferences;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.util.ReoTraversal.TraversalType;


public class FIFOBenchmark extends AbstractBenchmark {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		
		// Register the mCRL2 semantics provider:
		ReoTextualSemantics.REGISTRY.add(new Reo2MCRL2());
		
		Reo2MCRL2Preferences.setWithData(false);
		Reo2MCRL2Preferences.setWithColour(false);
		Reo2MCRL2Preferences.setIntensionalEncoding(false);
		Reo2MCRL2Preferences.setNetworkScope(false);
		Reo2MCRL2Preferences.setblockingEncoding(false);

		// ------ sequential -------
		
		int start = 4;
		List<Double> d1 = sequentialFIFOs(TraversalType.DEPTH_FIRST, start, 15, 4);		// 15
		List<Double> b1 = sequentialFIFOs(TraversalType.BREADTH_FIRST, start, 0, 4);	// not required
		List<Double> n1 = sequentialFIFOs(null, start, 5, 2);							// 5
		
		printResults("benchmarks/seqFIFOs.dat", start, d1, b1, n1);

		// ---------- parallel ------
		
		/*
		List<Double> d2 = parallelFIFOs(TraversalType.DEPTH_FIRST, start, 30, 4);		// 30
		List<Double> b2 = parallelFIFOs(TraversalType.BREADTH_FIRST, start, 9, 2);		// 9
		List<Double> n2 = parallelFIFOs(null, start, 6, 1);								// 6
		
		//printResults("benchmarks/parFIFOs.dat", start, d2, b2, n2);
		*/
		
		return null;
		
	}
	
	/*
	private List<Double> parallelFIFOs(TraversalType type, int start, int end, int tests) throws Exception {
		
		System.out.println("\nPERFORMING PARALLEL BENCHMARKS WITH TRAVERSAL: " + type + "\n");
		Reo2MCRL2Preferences.setTraversalType(type);
		List<Double> times = new ArrayList<Double>();
		
		Connector connector = new Connector();
		Node n1 = new Node("A");
		n1.setType(NodeType.ROUTE);
		Node n2 = new Node("B");
		connector.getNodes().add(n1);
		connector.getNodes().add(n2);
		new Writer(n1);
		new Reader(n2);
		
		for (int i=1; i<=end; i++) {
			connector.getPrimitives().add(new FIFO(n1,n2));
			if (i>=start) {
				System.out.print("Testing parallel FIFOs for n=" + i);
				times.add(benchmarkConnector(connector, tests));
			}
		}
		return times;
		
	}
*/
	
	private List<Double> sequentialFIFOs(TraversalType type, int start, int end, int tests) throws Exception {
		
		System.out.println("\nPERFORMING SEQUENTIAL BENCHMARKS WITH TRAVERSAL: " + type + "\n");
		Reo2MCRL2Preferences.setTraversalType(type);
		List<Double> times = new ArrayList<Double>();
		
		Connector connector = new Connector();
		Node n1 = new Node("A");
		Node n2 = new Node("B");
		connector.getNodes().add(n1);
		connector.getNodes().add(n2);
		new Writer(n1);
		new Reader(n2);
		
		FIFO last = new FIFO(n1,n2);
		connector.getPrimitives().add(last);
		
		for (int i=1; i<=end; i++) {
			if (i>=start) {
				System.out.print("Testing sequential FIFOs for n=" + i);
				times.add(benchmarkConnector(connector, tests));
			}
			Node node = new Node();
			connector.getNodes().add(node);
			last.setNodeTwo(node);
			last = new FIFO(node, n2);
			connector.getPrimitives().add(last);
		}		
		return times;
		
	}

}
