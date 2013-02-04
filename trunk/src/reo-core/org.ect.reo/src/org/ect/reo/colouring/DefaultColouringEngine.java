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
package org.ect.reo.colouring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.Reo;
import org.ect.reo.colouring.ColouringTraversalWorker.NextTableFinder;
import org.ect.reo.util.ReoTraversal;
import org.ect.reo.util.ReoTraversal.TraversalType;



/**
 * Default colouring engine.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public class DefaultColouringEngine extends AbstractColouringEngine implements NextTableFinder {
	
	// Multi-threaded flag.
	private boolean multiThreaded = false;	
	
	// Verbose flag.
	protected boolean verbose = true;
	
	
	/**
	 * Compute the coloring table of the network. 
	 * @param monitor Progress monitor to be used.
	 * @return The coloring table of the network.
	 */
	public ColouringTable computeColouringTable(IProgressMonitor monitor) {
		
		// Make sure the traversal type is valid.
		if (traversalType==null) traversalType = TraversalType.values()[0];
		
		// Check the cache.
		if (cache==null) cache = new ColouringCache();
		
		// Let's go.
		if (verbose) {
			Reo.debug("\nComputing colouring table using " + traversalType.getName() + " traversal...");
		}
		monitor.beginTask("Computing colouring table", 100);
		
		// We expect at least one element.
		if (elements==null || elements.isEmpty()) {
			return new ColouringTable();
		}
		
		// Extract lists of nodes and primitives.
		List<Primitive> primitives = new ArrayList<Primitive>();
		List<Node> nodes = new ArrayList<Node>();
		
		for (Colourable element : elements) {
			if (element instanceof Node) nodes.add((Node) element);
			else if (element instanceof Primitive) primitives.add((Primitive) element);
		}
		
		// Sort the primitives by length of their coloring table. 
		Collections.sort(primitives, new Comparator<Primitive>() {	
			public int compare(Primitive p1, Primitive p2) {
				int s1 = p1.getColouringTable().size();
				int s2 = p2.getColouringTable().size();
				return s1 - s2;
			}
		});
				
		// Debugging output.
		if (verbose) {
			Reo.debug("\n Size\t| Total\t| Ends \t| Time\t| CPU\t| Priority\t| Added table of...");
			Reo.debug(  "-----------------------------------------------------------");
		}
		
		// Create a traversal object.
		ReoTraversal traversal = ReoTraversal.create(traversalType);
		traversal.setAllowOverlap(false);
		traversal.stopWhenVisitedOnce(true);
		
		// Set the border of the traversal.
		if (border!=null) {
			traversal.getBorderElements().addAll(border);
		}
		
		// Determine the number of threads.
		int processors = multiThreaded ? Runtime.getRuntime().availableProcessors() : 1;
		if (primitives.size()<processors) processors = primitives.size();
		if (processors<1) processors = 1;
		
		// Create workers.
		List<ColouringTraversalWorker> workers = new ArrayList<ColouringTraversalWorker>();
		for (int i=0; i<processors; i++) {
			
			// Create a new worker.
			Connectable start = primitives.size()>i ? primitives.get(i) : nodes.get(0);
			ColouringTraversalWorker worker = new ColouringTraversalWorker(start, this, cache, i+1);
			worker.setColours(colours);
			worker.setMaxSteps(maxSteps);
			worker.setVerbose(verbose);
			
			// Register the worker.
			workers.add(worker);
			traversal.addWorker(worker);
		}
		
		// Create another thread that updates the priorities.
		ColouringPriorityUpdater updater = new ColouringPriorityUpdater(workers);
		if (multiThreaded) {
			Thread thread = new Thread(updater);
			thread.setPriority(Thread.NORM_PRIORITY-1);
			thread.start();
		}
		
		monitor.worked(5); // 5%
		
		// Take the time.
		long time = System.currentTimeMillis();
		
		// Do the traversal.
		traversal.start(primitives.size(), nodes.size(), new SubProgressMonitor(monitor, 70)); // 75%
		
		// Stop the priority updater.
		updater.stop();
		
		// Get the tables of the workers.
		List<ColouringTable> tables = new ArrayList<ColouringTable>();
		for (ColouringTraversalWorker worker : workers) {
			tables.add(worker.getColouringTable());
		}
		
		// Sort them by length.
		Collections.sort(tables, new Comparator<ColouringTable>() {
			public int compare(ColouringTable t1, ColouringTable t2) {
				return t1.size() - t2.size();
			}
		});

		monitor.worked(5); // 80%
		
		// Now join them.
		ColouringTable table = null;
		IProgressMonitor joinWorkers = new SubProgressMonitor(monitor, 15); // 95%
		joinWorkers.beginTask("Joining worker's tables", processors);
		for (ColouringTable current : tables) {
			table = (table==null) ? current.getCopy() : table.join(current, maxSteps, new SubProgressMonitor(joinWorkers, 1));
		}
		joinWorkers.done();
		
		
		// Add the tables of those elements that have not been visited yet.
		Set<Colourable> missing = new HashSet<Colourable>(elements);
		missing.removeAll( traversal.getVisitedElements() );
		monitor.worked(1); // 96%
		
		IProgressMonitor addMissing = new SubProgressMonitor(monitor, 3); // 99%
		joinWorkers.beginTask("Add missing tables", missing.size());
		
		ColouringTraversalWorker worker = workers.get(0);
		worker.setColouringTable(table);
		
		for (Colourable elem : missing) {
			worker.joinTable(elem, elem.toString(), new SubProgressMonitor(addMissing, 1));
		}
		table = worker.getColouringTable();
		addMissing.done();
		
		// Remove causality-loops.
		ColouringCausalityLoops.removeLoops(table, new SubProgressMonitor(monitor, 1));
		
		// Set names of the resulting tables.
		ColouringRefactoring.resetTableNames(table);
		
		// Print some stats.
		if (verbose) {
			time = System.currentTimeMillis() - time;
			int seconds = (int) (time / 1000);
			int fract = (int) (time - seconds*1000) / 10;
			Reo.debug("\nComputing colouring tables took " + seconds + "." + fract + " seconds.");
		}
		
		// Done.
		monitor.done();	
		return table;
		
	}
		
		
	@Override
	public ColouringTable findNextColouringTable(ColouringTable table) {
		return super.findNextColouringTable(table);
	}
	
	public void setMultiThreaded(boolean multiThreaded) {
		this.multiThreaded  = multiThreaded;
	}
	
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

		
	class ColouringPriorityUpdater implements Runnable {
		
		private List<ColouringTraversalWorker> workers;
		private boolean stop = false;
		
		public ColouringPriorityUpdater(List<ColouringTraversalWorker> workers) {
			this.workers = workers;
		}
		
		public void stop() {
			this.stop  = true;
		}

		public void run() {
			
			while (!stop) {
				
				// Get sizes of colouring tables.
				int[] sizes = new int[workers.size()];
				int max = 0;
				for (int i=0; i<workers.size(); i++) {
					sizes[i] = workers.get(i).getColouringTable().getColourings().size();
					if (sizes[i]>max) max = sizes[i];
				}
				if (max==0) max = 1;
				
				// Set priorities.
				for (int i=0; i<workers.size(); i++) {
					double p = 1.0 - ((double) sizes[i] / (double) max);
					int priority = (int) ((double) (Thread.MAX_PRIORITY - Thread.MIN_PRIORITY) * p) + Thread.MIN_PRIORITY;
					workers.get(i).setPriority(priority);
				}
				
				// Sleep.
				try {
					Thread.sleep(ReoTraversal.PRIORITY_UPDATE_DELAY);
				} catch (InterruptedException e) {}
			}
		}
	}

}
