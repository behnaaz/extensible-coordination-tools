package org.ect.codegen.v2.core.cli;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorDefaults;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgramArguments;

public class CoreCLIParser {

	//
	// FIELDS
	//

	/**
	 * The options passed as arguments to the application running this parser.
	 */
	protected final Options options;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a parser.
	 */
	public CoreCLIParser() {
		this.options = new Options();

		/* Add "comp" option. */
		this.options.addOption("c", "comp", false,
				"Compile the code after generating.");

		/* Add "dest" option. */
		this.options
				.addOption(
						"d",
						"dest",
						true,
						"Path to the location where to store the code to generate. Default: the directory containing the subject of code generation.");

		/* Add "help" option. */
		this.options.addOption("h", "help", false, "Print this message.");

		/* Add "print" option. */
		this.options.addOption("p", "print", false,
				"Print the code to the console after generating.");

		/* Add "res" option. */
		this.options
				.addOption(
						"r",
						"res",
						true,
						"Paths, separated by \""
								+ File.pathSeparator
								+ "\", to directories containing the non-Java resources on which compilation and execution of the code to generate depends. Default: "
								+ (CoreJavaGeneratorDefaults.canFindLibLocations() ? "\""
										+ CoreJavaGeneratorDefaults.findLibLocations()
										+ "\"" : "unknown") + ".");

		/* Add "src" option. */
		this.options
				.addOption(
						"s",
						"src",
						true,
						"Paths, separated by \""
								+ File.pathSeparator
								+ "\", to directories containing the Java sources on which compilation and execution of the code to generate depends. Default: "
								+ (CoreJavaGeneratorDefaults.canFindSrcLocations() ? "\""
										+ CoreJavaGeneratorDefaults.findSrcLocations()
										+ "\"" : "unknown") + ".");

		/* Add "templ" option. */
		this.options
				.addOption(
						"t",
						"templ",
						true,
						"Paths, separated by \""
								+ File.pathSeparator
								+ "\", to the template files (*.stg) to generate code with. Default: "
								+ (CoreJavaGeneratorDefaults.canFindStgLocations() ? "\""
										+ CoreJavaGeneratorDefaults.findStgLocations()
										+ "\"" : "unknown") + ".");
	}

	//
	// METHODS
	//

	/**
	 * Gets the options of this parser.
	 * 
	 * @return The options. Never <code>null</code>.
	 */
	public Options getOptions() {
		return options;
	}

	/**
	 * Parses the array of arguments <code>args</code>.
	 * 
	 * @param args
	 *            The arguments. Not <code>null</code>.
	 * @return The parsed arguments. Never <code>null</code>.
	 * @throws CliappCodegenArgumentsParserException
	 *             If something goes wrong while parsing.
	 * @throws NullPointerException
	 *             If <code>args==null</code>.
	 */
	public Parse parse(final String[] args)
			throws CliappCodegenArgumentsParserException {

		if (args == null)
			throw new NullPointerException("args");

		try {
			/* Parse the arguments. */
			final CommandLineParser parser = new GnuParser();
			final CommandLine line = parser.parse(getOptions(), args);
			if (line.hasOption("help"))
				return new Help();

			final Boolean compile = line.hasOption("comp");
			final String destinationPath = line.getOptionValue("dest");
			final String resourcesPaths = line.getOptionValue("res");
			final String sourcesPaths = line.getOptionValue("src");
			final String templatesPaths = line.getOptionValue("templ");

			final String connectorPath = args.length > 0
					&& args[args.length - 1].endsWith(".reo") ? args[args.length - 1]
					: null;

			return new Run(new CoreJavaGeneratorProgramArguments(connectorPath,
					destinationPath, resourcesPaths, sourcesPaths,
					templatesPaths), compile);

		} catch (final Exception e) {
			throw new CliappCodegenArgumentsParserException(
					CliappCodegenArgumentsParserException.PARSE(this, args), e);
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		return this.getClass().getCanonicalName();
	}

	//
	// CLASSES
	//

	public interface Parse {
	}

	public class Help implements Parse {
		private Help() {
		}
	}

	public class Run implements Parse {
		final private CoreJavaGeneratorProgramArguments arguments;
		final private boolean compile;

		private Run(final CoreJavaGeneratorProgramArguments arguments, final boolean compile) {

			this.arguments = arguments;
			this.compile = compile;
		}

		public CoreJavaGeneratorProgramArguments getArguments() {
			return arguments;
		}

		public boolean compile() {
			return compile;
		}
	}

	//
	// EXCEPTIONS
	//

	public static class CliappCodegenArgumentsParserException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected CliappCodegenArgumentsParserException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		protected CliappCodegenArgumentsParserException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String PARSE(final CoreCLIParser thiz,
				final String[] args) {

			return "The parser \"" + thiz
					+ "\" failed to parse the arguments \""
					+ Arrays.toString(args) + "\".";
		}
	}
}