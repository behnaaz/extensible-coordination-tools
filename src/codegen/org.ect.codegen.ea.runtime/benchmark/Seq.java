package org.ect.ea.runtime.bench;

import java.io.FileNotFoundException;

import org.junit.Test;

import org.ect.ea.automata.Automaton;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.components.Writer;


public class Seq extends Harness {
	Seq(String logfile) throws FileNotFoundException {
		super(logfile);
	}

	@Test public void testSeq() throws Exception{
		for (int i = 1; i < 3; i++) {
			run(build(i));
		}
	}
	
	public Connector build(int n) {
		Connector c = new Connector("SEQ"+n);
		Node r=null,fst, l, in;

		c.getNodes().add(l=fst= new Node("N0"));
		c.getNodes().add(in= new Node("X0"));
		c.getPrimitives().add(new SyncDrain(in,l));
		new Writer(in);
		
		for (int i=1; i<n; i++) {			
			c.getNodes().add(r= new Node("N"+i));		
			c.getPrimitives().add(new FIFO(l,r));
			
			c.getNodes().add(in= new Node("X"+i));
			c.getPrimitives().add(new SyncDrain(in,r));
			new Writer(in);

			l=r;
		}
		
		FIFO tok = new FIFO(r,fst);
		tok.setFull(true);
		c.getPrimitives().add(tok);
		
		return c;
	}

	@Override
	void postProcess(Automaton a) {
	}
}
