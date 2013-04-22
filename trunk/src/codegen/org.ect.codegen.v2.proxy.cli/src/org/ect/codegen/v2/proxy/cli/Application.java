package org.ect.codegen.v2.proxy.cli;

import org.apache.commons.cli.HelpFormatter;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.ect.codegen.v2.core.cli.JavaCompiler;
import org.ect.codegen.v2.core.gen.DirTreeFactory;
import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.ect.codegen.v2.core.gen.Printer;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgram;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	public Object start(IApplicationContext context) {

		/* Try. */
		try {
			/* Parse the arguments. */
			final Object args = context.getArguments().get("application.args");
			final ProxyCLIParser parser = new ProxyCLIParser();
			final ProxyCLIArguments arguments = parser.parse((String[]) args);

			/* Help. */
			if (arguments.HELP) {
				final HelpFormatter formatter = new HelpFormatter();
				formatter.setSyntaxPrefix("Usage: ");
				formatter.printHelp("reo-proxygen [options]",
						parser.getOptions());
			}

			/* Run. */
			else {

				/* Begin. */
				System.out.println("---Begin reo-proxygen---");

				/* Construct a directory tree. */
				final DirTree tree = new DirTreeFactory().construct("proxy");

				/* Generate. */
				new ProxyJavaGeneratorProgram(arguments, tree).call();

				/* Write. */
				final String outLocation = tree
						.tryWrite(arguments.DESTINATION_PATH);

				/* Compile. */
				if (arguments.COMPILE)
					JavaCompiler.compile(tree, outLocation);

				/* End. */
				System.out.println("---End reo-proxygen---");
			}
		} catch (final Exception e) {
			Printer.println(e, true);
		}

		return IApplication.EXIT_OK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do
	}
}
