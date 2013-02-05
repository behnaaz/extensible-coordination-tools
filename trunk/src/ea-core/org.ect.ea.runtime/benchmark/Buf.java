package cwi.ea.runtime.bench;

import cwi.ea.automata.Automaton;
import cwi.reo.Connector;
import cwi.reo.Node;
import cwi.reo.channels.FIFO;
import cwi.reo.components.Reader;
import cwi.reo.components.Writer;

public class Buf extends Harness {

	@Override
	Connector build(int n) {
		Connector c = new Connector("TEST");
		Node l = new Node("N"+0), r = null;
		c.getNodes().add(l);
		new Writer(l);
		
		for (int i=1; i<=n; i++) {
			r = new Node("N"+i);
			c.getNodes().add(r);		
			c.getPrimitives().add(new FIFO(l,r));
			l=r;
		}
		
		new Reader(r);
		return c;
	}

	@Override
	void postProcess(Automaton a) {
	}

}
