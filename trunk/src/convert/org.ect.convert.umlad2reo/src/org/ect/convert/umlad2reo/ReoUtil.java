package org.ect.convert.umlad2reo;

import org.eclipse.emf.common.util.URI;

import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Node;
import org.ect.reo.Reo;
import org.ect.reo.components.Reader;
import org.ect.reo.components.Writer;

public class ReoUtil {
	/*private static cwi.reo.Module loadReo(URI fileURI) {
System.err.println("Loading connectors from " + fileURI);
Resource resource = new ResourceSetImpl().getResource(fileURI, true);
List<EObject> circuits = resource.getContents();
return (cwi.reo.Module) circuits.get(0);
}*/

	public static void refineReo(URI infileURI, URI outfileURI) throws Exception {
		System.out.println("Creating a new Reo connector...");
		Reo.initStandalone();
		System.out.println("Loading " + infileURI.toFileString());
		Module mod = Reo.loadModule(infileURI);
		refine(mod);
		System.out.println("Saving connector to " + outfileURI.toFileString());
		//Resource res = createReoFile(outfileURI, mod);//??????????key ijad shode uri dare?
		Reo.saveModule(mod);
	}

	public static  void refine(Module mod) {
		for (Connector con : mod.getConnectors())
			for (Node n : con.getNodes()){
				if (n.isSinkNode() && n.getSourceEnds().size() == 0) {
					Reader r = new Reader(n);
					mod.getComponents().add(r);
				} else if (n.isSourceNode() && n.getSinkEnds().size() == 0) {
					Writer w= new Writer(n);
					mod.getComponents().add(w);
				}
			}
	}
}

