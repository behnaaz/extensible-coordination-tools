package org.ect.ea.runtime.bench;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import org.ect.ea.automata.*;
import org.ect.reo.*;
import org.ect.reo.channels.*;
import org.ect.reo.components.*;


public class Disc extends Harness {
	public Disc() {
		super();
	}
	
	Disc(String logfile) throws FileNotFoundException {
		super(logfile);
	}
	
	@Test public void testDisc() throws Exception{
		run(build(2));
	}
	
	Connector build0(int n) {
		Connector c = new Connector("DISC"+n);
		Node in, mid, com = new Node("com");
		c.getNodes().add(com);
		Collection<Node> mids = new ArrayList<Node>();
		
		for (int i=0; i<n; i++) {
			in = new Node("in"+i);
			c.getNodes().add(in);
			mid = new Node("mid"+i);
			c.getNodes().add(mid);
			mids.add(mid);
			
			new Writer(in);
			c.getPrimitives().add(new FIFO(in, mid));
			c.getPrimitives().add(new LossyFIFO(in, com));			
		}
		
		Node prev = com;
		for (Node nxt : mids) {
			c.getPrimitives().add(new SyncDrain(prev, nxt));
			prev = nxt;
		}
		
		Node out = new Node("uit");
		c.getNodes().add(out);
		new Reader(out);
		c.getPrimitives().add(new Sync(com, out));
		
		return c;
	}
	
	public Connector build(int n) {
		Connector c = new Connector("DISC"+n);
		Node in, mid, com = new Node("com");
		c.getNodes().add(com);
		Collection<Node> mids = new ArrayList<Node>();
		
		for (int i=0; i<n; i++) {
			in = new Node("in"+i);
			c.getNodes().add(in);
			mid = new Node("mid"+i);
			c.getNodes().add(mid);
			mids.add(mid);
			
			new Writer(in);
			c.getPrimitives().add(new FIFO(in, mid));
			c.getPrimitives().add(new LossySync(in, com));			
		}
		
		Node prev = new Node();
		c.getNodes().add(prev);
		c.getPrimitives().add(new FIFO(com, prev));
		for (Node nxt : mids) {
			c.getPrimitives().add(new SyncDrain(prev, nxt));
			prev = nxt;
		}
		
		Node out = new Node("uit");
		c.getNodes().add(out);
		new Reader(out);
		c.getPrimitives().add(new Sync(com, out));
		
		return c;
	}

	@Override
	void postProcess(Automaton a) {
		List<State> deadlocks = new ArrayList<State>();
		for (State s : a.getStates()) {
			if (s.getOutgoing().isEmpty())
				deadlocks.add(s);
		}
		System.err.println("removing deadlock states: "+deadlocks);
		a.getStates().removeAll(deadlocks);	
	}

}
