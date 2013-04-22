package org.ect.codegen.v2.core.cli;

import org.apache.commons.cli.HelpFormatter;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.ect.codegen.v2.core.cli.CoreCLIParser.Help;
import org.ect.codegen.v2.core.cli.CoreCLIParser.Parse;
import org.ect.codegen.v2.core.cli.CoreCLIParser.Run;
import org.ect.codegen.v2.core.gen.DirTreeFactory;
import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.ect.codegen.v2.core.gen.Printer;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgram;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgramArguments;

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
	public Object start(IApplicationContext context) throws Exception {

		/* Try. */
		try {

			/* Parse the arguments. */
			final Object args = context.getArguments().get("application.args");
			final CoreCLIParser parser = new CoreCLIParser();
			final Parse parse = parser.parse((String[]) args);

			/* Help. */
			if (parse instanceof Help) {
				final HelpFormatter formatter = new HelpFormatter();
				formatter.setSyntaxPrefix("Usage: ");
				formatter.printHelp("reo-coregen [options] <*>.reo",
						parser.getOptions());
			}

			/* Run. */
			if (parse instanceof Run) {
				final Run runParse = (Run) parse;
				final CoreJavaGeneratorProgramArguments arguments = runParse.getArguments();

				/* Begin. */
				System.out.println("---Begin reo-coregen.");

				/* Construct a directory tree. */
				final DirTree tree = new DirTreeFactory()
						.construct("connector");

				/* Generate. */
				new CoreJavaGeneratorProgram(arguments, tree).call();

				/* Write. */
				final String outLocation = tree.tryWrite(arguments
						.getOutLocation());

				/* Compile. */
				if (runParse.compile())
					JavaCompiler.compile(tree, outLocation);

				/* End. */
				System.out.println("---End reo-coregen.");
			}
		}

		/* Catch. */
		catch (final Exception e) {
			Printer.println(e, false);
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
