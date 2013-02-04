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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.ect.reo.Connectable;
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ColouringTest implements IApplication {

	DefaultColouringEngine eng = new DefaultColouringEngine(); // try also default
	int times = 2;
	int from;
	int to;
	int by;
	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {

		// setting the engine
		ColouringTable table = null;
		IProgressMonitor monitor = new NullProgressMonitor();
		eng.setMaxSteps(1);
		eng.setIgnoreNoFlow(true);
		eng.setVerbose(false);
		
		System.out.println("starting");

		int times = 11;
		Boolean threecol = true;

		
		if (threecol) eng.setColours(3);
		else		  eng.setColours(2);

		/* Sync chain */
		if (threecol) setBenchmark("SyncChain 3-col", 5,100,10);
		else 		  setBenchmark("SyncChain 2-col", 5,100,10);
    	for (int run = 0; run < times; run++) {
    		for (int size = from; size < to; size += by) {
    			buildSyncChain(size,false);
    			table = eng.computeColouringTable(monitor);
//    			System.out.println(table.toString());
    		}
    		System.out.println("");
    	}
		
		/* Parallel Syncs */
		if (threecol) setBenchmark("ParallelSync 3-col", 1,7,1);
		else 		  setBenchmark("ParallelSync 2-col", 1,9,1);
    	for (int run = 0; run < times; run++) {
    		for (int size = from; size < to; size += by) {
    			buildSyncPar(size,false);
    			table = eng.computeColouringTable(monitor);
//    			System.out.println(table.toString());
    		}
    		System.out.println("");
    	}

		/* Ex Router */
		if (threecol) setBenchmark("ExRouter CS", 1,14,2);
		else 		  setBenchmark("ExRouter CI", 1,27,3);
    	for (int run = 0; run < times; run++) {
    		for (int size = from; size < to; size += by) {
    			buildExRouter(size,false);
    			table = eng.computeColouringTable(monitor);
//    			System.out.println(table.toString());
    		}
    		System.out.println("");
    	}

		/* In Router */
		if (threecol) setBenchmark("InRouter CS", 1,14,2);
		else 		  setBenchmark("InRouter CI", 1,9,1);
    	for (int run = 0; run < times; run++) {
    		for (int size = from; size < to; size += by) {
    			buildInRouter(size,false);
    			table = eng.computeColouringTable(monitor);
//    			System.out.println(table.toString());
    		}
    		System.out.println("");
    	}
    	
    	
    	// finish
		System.out.println("done");
		return table;
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
	}

	public void setBenchmark(String name,int a, int b, int c) {
		from = a; to = b; by = c;
		System.out.println(""+name);
    	for (int size = from; size < to; size += by)
    		System.out.print(""+size+"\t");
    	System.out.println("");
	}
	
	/* Builds a simple connector with a single channel, for debugging. */
	public void buildChannel() {
		Node x = new Node("x");
		Node y = new Node("y");
		Primitive w = new Writer(x);
		Primitive r = new Reader(y);
		Primitive s = new LossySync(x,y);
		
		List<Colourable> els = new ArrayList<Colourable>();
		els.add(x); 
		els.add(y);
		els.add(s);
		els.add(w);
		els.add(r);
		
		eng.setElements(els);
	}
	
	/* Builds a sequence of synchronous channels */
	public void buildSyncChain(int size, boolean open) {
		List<Colourable> els = new ArrayList<Colourable>();
		List<Connectable> border = new ArrayList<Connectable>();

		Node ni;
		Sync s;
		Node n = new Node("n0");
		Writer w = new Writer(n);
		Reader r;
		
		els.add(n);
		if (open) border.add(w);
		else      els.add(w);
		
		for (int i = 1; i<size+1; i++) {
			ni = new Node("n"+i);
			s = new Sync(n,ni);
			els.add(s);
			els.add(ni);
			n = ni;
		}
		r = new Reader(n);
		els.add(n);
		if (open) border.add(r);
		else      els.add(r);

		eng.setBorderElements(border);
		eng.setElements(els);
	}

	/* Builds a set of synchronous channels running in parallel*/
	public void buildSyncPar(int size, boolean open) {
		List<Colourable> els = new ArrayList<Colourable>();
		List<Connectable> border = new ArrayList<Connectable>();

		Node x;
		Node y;
		Sync s;
		Writer w;
		Reader r;
		
		for (int i = 0; i<size; i++) {
			x = new Node("x"+i);
			y = new Node("y"+i);
			s = new Sync(x,y);
			w = new Writer(x);
			w.getEnd().setName("w"+i);
			r = new Reader(y);
			r.getEnd().setName("r"+i);
			els.add(s); els.add(x); els.add(y);
			if (open) { border.add(w); border.add(r); }
			else      { els.add(w); els.add(r); }
		}
		eng.setBorderElements(border);
		eng.setElements(els);
	}

	/* Builds n-ary exclusive router*/
	public void buildExRouter(int size, boolean open) {
		List<Colourable> els = new ArrayList<Colourable>();
		List<Connectable> border = new ArrayList<Connectable>();
		
		Node a = new Node("a");
		Node b = new Node("b");
		SyncDrain s = new SyncDrain(a,b);
		Writer w = new Writer(a);
		w.getEnd().setName("w");

		els.add(a); els.add(b); els.add(s);
		if (open) border.add(w);
		else      els.add(w);

		for (int i = 0; i < size; i++) {
			Node x = new Node ("x"+i);
			Node y = new Node ("y"+i);
			LossySync l = new LossySync(a,x);
			Sync s1 = new Sync(x,b);
			Sync s2 = new Sync(x,y);
			Reader r = new Reader(y);
			r.getEnd().setName("r"+i);
			
			els.add(l); els.add(s1); els.add(s2); els.add(x); els.add(y);
			if (open) border.add(r);
			else      els.add(r);
		}
		
		eng.setBorderElements(border);
		eng.setElements(els);
	}
	
	/* Builds n-ary inclusive router*/
	public void buildInRouter(int size, boolean open) {
		List<Colourable> els = new ArrayList<Colourable>();
		List<Connectable> border = new ArrayList<Connectable>();
		
		Node a = new Node("a");
		Node b = new Node("b");
		SyncDrain s = new SyncDrain(a,b);
		Writer w = new Writer(a);
		w.getEnd().setName("w");

		els.add(a); els.add(b); els.add(s);
		if (open) border.add(w);
		else      els.add(w);

		for (int i = 0; i < size; i++) {
			Node x = new Node ("x"+i);
			Node y = new Node ("y"+i);
			LossySync l = new LossySync(a,x);
			LossySync s1 = new LossySync(x,b); // now it is an inrouter
			Sync s2 = new Sync(x,y);
			Reader r = new Reader(y);
			r.getEnd().setName("r"+i);
			
			els.add(l); els.add(s1); els.add(s2); els.add(x); els.add(y);
			if (open) border.add(r);
			else      els.add(r);
		}
		
		eng.setBorderElements(border);
		eng.setElements(els);
	}
}
