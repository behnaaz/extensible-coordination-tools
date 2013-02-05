package cwi.ea.runtime.bench;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class Benchmark implements IApplication {
	private static final String LOGFILE = "/tmp/runtimes.log";
	@Override
	public Object start(IApplicationContext context) throws Exception {
/*
		String[] args = (String[]) context.getArguments()
						.get(IApplicationContext.APPLICATION_ARGS);
		if (args.length<2) 
			System.err.println("Usage: <connector.reo> <iterations>");
		
		Connector c = Reo.loadModule(URI.createFileURI(args[0])).getConnectors().get(0);
*/		
		Disc d = new Disc(LOGFILE);
		d.log("compile\trun");
		d.log("===================");
		
		for (int i = 1; i < 5; i++) {
			d.run(d.build(i));
		}

		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

}
