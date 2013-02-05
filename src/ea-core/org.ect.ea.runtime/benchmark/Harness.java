package cwi.ea.runtime.bench;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cwi.ea.automata.Automaton;
import cwi.ea.extensions.portNames.AutomatonPortNames;
import cwi.ea.extensions.portNames.providers.AutomatonPortNamesProvider;
import cwi.ea.runtime.Sink;
import cwi.ea.runtime.Source;
import cwi.ea.runtime.TransactionalIO;
import cwi.ea.runtime.components.BasicReader;
import cwi.ea.runtime.components.BasicWriter;
import cwi.ea.runtime.interpreter.ExecutableCA;
import cwi.ea.runtime.interpreter.XCADriver;
import cwi.ea.runtime.primitives.TimeoutPort;
import cwi.ea.util.text.serialize.CatPrinter;
import cwi.reo.Connector;
import cwi.reo.Primitive;
import cwi.reo.Property;
import cwi.reo.components.Reader;
import cwi.reo.components.Writer;
import cwi.reo.util.PropertyHelper;
import cwi.reo2ea.ca.Reo2CA;

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
