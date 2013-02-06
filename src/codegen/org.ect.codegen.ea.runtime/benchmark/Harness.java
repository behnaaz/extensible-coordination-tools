package org.ect.ea.runtime.bench;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.portNames.AutomatonPortNames;
import org.ect.ea.extensions.portNames.providers.AutomatonPortNamesProvider;
import org.ect.ea.runtime.Sink;
import org.ect.ea.runtime.Source;
import org.ect.ea.runtime.TransactionalIO;
import org.ect.ea.runtime.components.BasicReader;
import org.ect.ea.runtime.components.BasicWriter;
import org.ect.ea.runtime.interpreter.ExecutableCA;
import org.ect.ea.runtime.interpreter.XCADriver;
import org.ect.ea.runtime.primitives.TimeoutPort;
import org.ect.ea.util.text.serialize.CatPrinter;
import org.ect.reo.Connector;
import org.ect.reo.Primitive;
import org.ect.reo.Property;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;
import org.ect.reo.util.PropertyHelper;
import org.ect.reo2ea.ca.Reo2CA;

@SuppressWarnings("unchecked")
public abstract class Harness {
	protected PrintStream os;
	protected long begin, compile;
	
	abstract void postProcess(Automaton a);
	abstract Connector build(int n);
	
	public Harness() {
		os = System.out; 
	}
	public Harness(String logfile) throws FileNotFoundException {
		os = new PrintStream(logfile);
	}

	public void log(Object o) {
		os.println(o);
	}
	
	public void run (Connector c) throws Exception {
		begin = System.currentTimeMillis();
		Automaton a = new Reo2CA(c).getResult();
		postProcess(a);
		
		AutomatonPortNames aports = (AutomatonPortNames) 
			a.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		Map<String, TransactionalIO> ports = new HashMap<String, TransactionalIO>();		
		for (String p : aports.getValues()) 			
			ports.put(p, new TimeoutPort(p));

		List<Runnable> components = createComps(c.getForeignPrimitives(), ports);
		
		String cat = new CatPrinter().visit(a);
//		System.err.println(cat);
		ExecutableCA<TransactionalIO> xca = XCADriver.loadString(cat, ports);		
		compile = System.currentTimeMillis();
		
		xca.launch(components.toArray());
		long end = System.currentTimeMillis();		
		os.println((compile-begin) +"\t"+ (end-compile));
	}

	private List<Runnable> createComps(Collection<Primitive> comps,	Map<String, TransactionalIO> ports) {
		List<Runnable> components = new ArrayList<Runnable>();
		for (Primitive p : comps) {
			Property prop = PropertyHelper.getFirst(p, "requests");
			int reqs = prop==null ? 10 : Integer.parseInt(prop.getValue());

			if (p instanceof Reader) {
				String port = ((Reader) p).getEnd().getNode().getName();				
				components.add(
						new BasicReader()
						.withSourcePorts((Source)ports.get(port))
						.withCount(reqs)
				);
			} else  if (p instanceof Writer) {
				String port = ((Writer) p).getEnd().getNode().getName();
				components.add(
						new BasicWriter()
						.withSinkPorts((Sink)ports.get(port))
						.withCount(reqs)
				);
			}
		}
		return components;
	}

}
