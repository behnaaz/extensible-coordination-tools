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
package org.ect.reo.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;



/**
 * Traverse a Reo connector or network. Supports multi-threaded traversals. 
 * The actual computations are performed in instances of {@link ReoTraversalWorker}.
 * The actual traversal is done in subclasses.
 * 
 * @generated NOT
 * @author Christian Krause
 */
public abstract class ReoTraversal {
	
	/**
	 * Enum for traversal types.
	 */
	public static enum TraversalType {
		
		DEPTH_FIRST, BREADTH_FIRST;
		
		public String getName() {
			 switch(this) {
	            case DEPTH_FIRST:   return "depth-first";
	            case BREADTH_FIRST:   return "breadth-first";
	        }
	        throw new AssertionError("Unknown traversal type: " + this);
		}
	}
	
	// Priority update delay in milliseconds.
	public static final int PRIORITY_UPDATE_DELAY = 1000;
	
	// Visited nodes and primitives.
	protected List<Set<Connectable>> visited;
	
	// Border nodes and primitives.
	protected Set<Connectable> border;
	
	// Workers.
	protected List<ReoTraversalWorker> workers;
	
	// Allow overlapping traversals?
	protected boolean allowOverlap = false;
	
	// Stop when all nodes / primitives have been visited at least once?
	protected boolean stopWhenVisitedOnce = false;

	// Total number of primitives and node to visit.
	int numPrimitives, numNodes;
	
	// Currently used progress monitor.
	private IProgressMonitor monitor;
	
	
	/**
	 * Default constructor.
	 */
	public ReoTraversal() {
		this.visited = new ArrayList<Set<Connectable>>();
		this.workers = new ArrayList<ReoTraversalWorker>();
		this.border = new HashSet<Connectable>();
	}
	
	public static ReoTraversal create(TraversalType type) {
		if (type==TraversalType.BREADTH_FIRST) {
			return new ReoBreadthFirstTraversal();
		} else {
			return new ReoDepthFirstTraversal();
		}
	}
	
	/**
	 * Start the traversal. This has to be implemented by the subclasses.
	 * @param element Element where the traversal should start.
	 * @param worker Worker to be used.
	 */
	protected abstract void startTraversal(Connectable element, ReoTraversalWorker worker);
	
	
	/**
	 * Add a traversal worker.
	 * @param worker Traversal worker.
	 */
	public synchronized void addWorker(ReoTraversalWorker worker) {
		workers.add(worker);
		visited.add(new HashSet<Connectable>());
	}
	
	/**
	 * Remove a traversal worker.
	 * @param worker Traversal worker.
	 */
	public synchronized void removeWorker(ReoTraversalWorker worker) {
		if (workers.contains(worker)) {
			int index = getWorkerIndex(worker);
			workers.remove(index);
			visited.remove(index);
		}
	}
	
	/**
	 * Run a depth-first traversal using the registered workers.
	 * @param numPrimitives Number of primitives that will be visited.
	 * @param numNodes Number of nodes that will be visited.
	 * @param monitor Progress monitor.
	 */
	public synchronized void start(int numPrimitives, int numNodes, IProgressMonitor monitor) {
		
		this.numPrimitives = numPrimitives;
		this.numNodes = numNodes;
		this.monitor = monitor;
		int steps = numPrimitives + numNodes;
		monitor.beginTask("Traversal", steps);
		
		// Nothing to do?
		if (workers.isEmpty()) {
			monitor.worked(steps);
			monitor.done();
			return;
		}
		
		// Reset visited primitives and nodes.
		for (int i=0; i<workers.size(); i++) {
			visited.get(i).clear();
		}
		
		if (workers.size()==1) {
			// Single threaded.
			ReoTraversalWorker worker = workers.get(0);
			startTraversal(worker.getStart(), worker);
		
		} else {
			// Thread pool.
			PriorityThreadFactory factory = new PriorityThreadFactory();
			final CountDownLatch doneSignal = new CountDownLatch(workers.size());
			ExecutorService pool = Executors.newFixedThreadPool(workers.size(), factory);
			List<WorkerRunnable> runnables = new Vector<WorkerRunnable>();
			
			// Create runnables.
			for (ReoTraversalWorker worker : workers) {
				runnables.add(new WorkerRunnable(worker, doneSignal));
			}
			
			// Execute them in separate threads.
			for (WorkerRunnable runnable : runnables) {
				pool.execute(runnable);
			}
			
			// Start a control thread that updates priorities.
			UpdatePriorityRunnable update = new UpdatePriorityRunnable(factory, runnables);
			factory.newThread(update).start();
			
			// Wait until all threads are done.
			try {
				doneSignal.await();
			} catch(InterruptedException e) {
				Reo.logError("Traversal interrupted.", e);
			}
			
			// Stop update thread.
			update.stop();
			
			// Shutdown pool.
			pool.shutdown();

		}
		
		// Finish the monitor.
		monitor.done();
		
	}
	
	
	/**
	 * Check if all nodes and primitives where visited at least once.
	 */
	protected boolean allVisited() {
		return numNodes+numPrimitives==getVisitedElements().size();
	}
	
	/**
	 * Checks whether the traversal should be stopped.
	 * @return True if it should be stopped.
	 */
	protected boolean shouldStop(Connectable element, ReoTraversalWorker worker) {
		
		// All visited already?
		if (stopWhenVisitedOnce && allVisited()) return true;
		
		// This element visited already by this worker?
		Set<Connectable> elems = visited.get(getWorkerIndex(worker));
		synchronized (elems) {
			if (elems.contains(element)) return true;
		}
		
		// Is it a border element?
		synchronized (border) {
			if (border.contains(element)) return true;
		}
		
		return false;
	}
	
	
	/**
	 * Marks an element as visited.
	 * @param element Primitive or node.
	 * @param worker Worker that visits the primitive.
	 * @return True if it was not marked as visited yet.
	 */
	protected boolean markAsVisited(Connectable element, ReoTraversalWorker worker) {
		Set<Connectable> prim = visited.get(getWorkerIndex(worker));
		// Check if it is already visited.
		synchronized (prim) {
			if (prim.contains(element)) return false;
			prim.add(element);
		}
		return true;
	}
	
	/**
	 * Get the index of a worker.
	 */
	protected int getWorkerIndex(ReoTraversalWorker worker) {
		return allowOverlap ? workers.indexOf(worker) : 0;
	}
	
	protected boolean isCanceled() {
		return (monitor!=null && monitor.isCanceled());
	}
	
	/**
	 * Visit a node or a primitive.
	 * @param element Element to be visited.
	 * @param worker Worker that should visited the element.
	 * @param monitor Progress monitor.
	 * @return True if the visit was successful.
	 */
	protected boolean visit(Connectable element, ReoTraversalWorker worker) {
		
		// Must be either a node or a primitive.
		if (!(element instanceof Node) && !(element instanceof Primitive)) return false;
		
		// Should we stop now already?
		if (shouldStop(element, worker)) return false;
		
		// Mark it as visited.
		if (!markAsVisited(element, worker)) return false;
		
		// Visit the primitive.
		if (element instanceof Node) {
			return worker.visit((Node) element, new SubProgressMonitor(monitor,1));
		} else {
			return worker.visit((Primitive) element, new SubProgressMonitor(monitor,1));
		}
		
	}

	
	
	/**
	 * Get the set of already visited elements.
	 * @param worker Reo traversal worker.
	 */
	public Set<Connectable> getVisitedElements(ReoTraversalWorker worker) {
		synchronized (visited) {
			return visited.get(getWorkerIndex(worker));
		}
	}
		
	/**
	 * Get the set of already visited elements.
	 */
	public Set<Connectable> getVisitedElements() {
		Set<Connectable> all = new HashSet<Connectable>();
		synchronized (visited) {
			for (Set<Connectable> current : visited) {
				all.addAll(current);
			}
		}
		return all;
	}
	
	
	/**
	 * Get the set of allowed primitives and nodes.
	 * @return
	 */
	public Set<Connectable> getBorderElements() {
		return border;
	}
	
	/**
	 * Set allow-overlap flag.
	 */
	public void setAllowOverlap(boolean allowOverlap) {
		this.allowOverlap = allowOverlap;
	}
	
	/**
	 * Set whether to stop when all primitives and nodes have been visited
	 * at least once.
	 */
	public void stopWhenVisitedOnce(boolean stopWhenVisitedOnce) {
		this.stopWhenVisitedOnce = stopWhenVisitedOnce;
	}
	
	
	/**
	 * Get a sorted list of primitive ends. It is sorted according
	 * to the compare methods in the worker.
	 * @param worker Worker.
	 * @param element Element.
	 * @return List of ends.
	 */
	protected List<PrimitiveEnd> getSortedEnds(ReoTraversalWorker worker, Connectable element) {
		
		// Get a copy of the ends list.
		List<PrimitiveEnd> ends = new ArrayList<PrimitiveEnd>(element.getAllEnds());
		
		// Sort the list.
		if (element instanceof Node) {
			Collections.sort(ends, new PrimitiveComparator(worker, (Node) element));
		} else
		if (element instanceof Primitive) {
			Collections.sort(ends, new NodeComparator(worker, (Primitive) element));			
		}
		
		// Apply the shift (negative rotation of the list).
		Collections.rotate(ends, -worker.getShift());
		
		// Done.
		return ends;
	}

	
	/*
	 * Helper classes.
	 */
	
	class WorkerRunnable implements Runnable {
		
		private ReoTraversalWorker worker;
		private CountDownLatch doneSignal;
		
		public WorkerRunnable(ReoTraversalWorker worker, CountDownLatch doneSignal) {
			this.worker = worker;
			this.doneSignal = doneSignal;
		}
		
		public void run() {
			startTraversal(worker.getStart(), worker);
			doneSignal.countDown();
		}
		
		public ReoTraversalWorker getWorker() {
			return worker;
		}
	}
	
	
	class UpdatePriorityRunnable implements Runnable {
		
		private List<WorkerRunnable> runnables;
		private PriorityThreadFactory factory;
		private boolean stop = false;
		
		public UpdatePriorityRunnable(PriorityThreadFactory factory, List<WorkerRunnable> runnables) {
			this.runnables = runnables;
			this.factory = factory;
		}
		
		public void run() {
			while (!stop) {
				for (WorkerRunnable runnable : runnables) {
					int priority = runnable.getWorker().getPriority();
					if (priority>Thread.MAX_PRIORITY) priority = Thread.MAX_PRIORITY;
					if (priority<Thread.MIN_PRIORITY) priority = Thread.MIN_PRIORITY;
					factory.setPriority(runnable, priority);
				}
				try {
					Thread.sleep(PRIORITY_UPDATE_DELAY);
				} catch (InterruptedException e) {}
				
			}
		}
		
		public void stop() {
			stop = true;
		}
	}
	
	
	
	static class PriorityThreadFactory implements ThreadFactory {
		
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;
		private final Map<Runnable,Thread> threads;
		
        public PriorityThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null)? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
            threads = new HashMap<Runnable,Thread>();
        }
        
        public Thread newThread(Runnable r) {
        	Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
		
        public int getPriority(Runnable runnable) {
        	return threads.containsKey(runnable) ? threads.get(runnable).getPriority() : -1;
        }
        
        public void setPriority(Runnable runnable, int priority) {
        	if (threads.containsKey(runnable)) {
        		threads.get(runnable).setPriority(priority);
        	}
        }
	}
			
	
	class PrimitiveComparator implements Comparator<PrimitiveEnd> {
		
		private ReoTraversalWorker worker;
		private Node current;
		
		public PrimitiveComparator(ReoTraversalWorker worker, Node current) {
			this.worker = worker;
			this.current = current;
		}
		
		public int compare(PrimitiveEnd end1, PrimitiveEnd end2) {
			Primitive p1 = end1.getPrimitive();
			Primitive p2 = end2.getPrimitive();
			if (p1==null || p2==null) return 0;
			return worker.compare(p1, p2, current);
		}
		
	};
	
	
	class NodeComparator implements Comparator<PrimitiveEnd> {
		
		private ReoTraversalWorker worker;
		private Primitive current;
		
		public NodeComparator(ReoTraversalWorker worker, Primitive current) {
			this.worker = worker;
			this.current = current;
		}
		
		public int compare(PrimitiveEnd end1, PrimitiveEnd end2) {
			Node n1 = end1.getNode();
			Node n2 = end2.getNode();
			if (n1==null || n2==null) return 0;
			return worker.compare(n1, n2, current);
		}
		
	}

}
