package org.ect.codegen.v2.proxy.cli;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.ect.codegen.v2.proxy.descr.java.IDLProxyDefaultsResolver;
import org.ect.codegen.v2.proxy.descr.java.WSDLProxyDefaultsResolver;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorDefaults;


public class ProxyCLIParser {

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
	public ProxyCLIParser() {
		this.options = new Options();

		final ProxyJavaGeneratorDefaults corbaResolver = new IDLProxyDefaultsResolver();
		final ProxyJavaGeneratorDefaults wsdlResolver = new WSDLProxyDefaultsResolver();
		String corbaDefault = null;
		String wsdlDefault = null;

		/* Add "comp" option. */
		this.options.addOption("c", "comp", false,
				"Compile the code after generating.");

		/* Add "corba" option. */
		this.options.addOption("corba", "corba", false, "Run in CORBA mode.");

		/* Add "dest" option. */
		this.options
				.addOption(
						"d",
						"dest",
						true,
						"Path to the location where to store the code to generate. Default: the directory containing the subject of code generation.");

		/* Add "def-sim-aut" option. */
		this.options
				.addOption("dsa", "def-sim-aut", false,
						"Use a default simulation automaton for the party to generate code for.");

		/* Add "help" option. */
		this.options.addOption("h", "help", false, "Print this message.");

		/* Add "ifaces" option. */
		try {
			corbaDefault = "\"" + corbaResolver.INTERFACES_PATH() + "\"";
		} catch (final Exception e) {
			corbaDefault = "unknown";
		}

		try {
			wsdlDefault = "\"" + wsdlResolver.INTERFACES_PATH() + "\"";
		} catch (final Exception e) {
			wsdlDefault = "unknown";
		}

		this.options
				.addOption(
						"i",
						"ifaces",
						true,
						"A path referencing the interfaces file (*.idl or *.wsdl) of the party to generate code for. Default: "
								+ corbaDefault
								+ " (CORBA) or "
								+ wsdlDefault
								+ " (WSDL).");

		/* Add "name" option. */
		try {
			corbaDefault = "\"" + corbaResolver.INTERFACE_NAME() + "\"";
		} catch (final Exception e) {
			corbaDefault = "unknown";
		}

		try {
			wsdlDefault = "\"" + wsdlResolver.INTERFACE_NAME() + "\"";
		} catch (final Exception e) {
			wsdlDefault = "unknown";
		}

		this.options
				.addOption(
						"n",
						"name",
						true,
						"The name of the interface implemented by the party to generate code for. Default: "
								+ corbaDefault
								+ " (CORBA) or "
								+ wsdlDefault
								+ " (WSDL).");

		/* Add "print" option. */
		this.options.addOption("p", "print", false,
				"Print the code to the console after generating.");

		/* Add "proxy-res" option. */
		try {
			corbaDefault = "\"" + corbaResolver.PROXY_RESOURCES_PATHS() + "\"";
		} catch (final Exception e) {
			corbaDefault = "unknown";
		}

		try {
			wsdlDefault = "\"" + wsdlResolver.PROXY_RESOURCES_PATHS() + "\"";
		} catch (final Exception e) {
			wsdlDefault = "unknown";
		}

		this.options
				.addOption(
						"pr",
						"proxy-res",
						true,
						"Paths, separated by \""
								+ File.pathSeparator
								+ "\", to directories containing the non-Java resources on which compilation and execution of the code to generate depends. Default: "
								+ corbaDefault + " (CORBA) or " + wsdlDefault
								+ " (WSDL).");

		/* Add "proxy-src" option. */
		try {
			corbaDefault = "\"" + corbaResolver.PROXY_SOURCES_PATHS() + "\"";
		} catch (final Exception e) {
			corbaDefault = "unknown";
		}

		try {
			wsdlDefault = "\"" + wsdlResolver.PROXY_SOURCES_PATHS() + "\"";
		} catch (final Exception e) {
			wsdlDefault = "unknown";
		}

		this.options
				.addOption(
						"ps",
						"proxy-src",
						true,
						"Paths, separated by \""
								+ File.pathSeparator
								+ "\", to directories containing the Java sources on which compilation and execution of the code to generate depends. Default: "
								+ corbaDefault + " (CORBA) or " + wsdlDefault
								+ " (WSDL).");

		/* Add "proxy-templ" option. */
		try {
			corbaDefault = "\"" + corbaResolver.PROXY_TEMPLATES_PATHS() + "\"";
		} catch (final Exception e) {
			corbaDefault = "unknown";
		}

		try {
			wsdlDefault = "\"" + wsdlResolver.PROXY_TEMPLATES_PATHS() + "\"";
		} catch (final Exception e) {
			wsdlDefault = "unknown";
		}

		this.options
				.addOption(
						"pt",
						"proxy-templ",
						true,
						"Paths, separated by \""
								+ File.pathSeparator
								+ "\", to the template files (*.stg) to generate code with. Default: "
								+ corbaDefault + " (CORBA) or " + wsdlDefault
								+ " (WSDL).");

		/* Add "simaut" option. */
		try {
			corbaDefault = "\"" + corbaResolver.SIM_AUT_PATH() + "\"";
		} catch (final Exception e) {
			corbaDefault = "unknown";
		}

		try {
			wsdlDefault = "\"" + wsdlResolver.SIM_AUT_PATH() + "\"";
		} catch (final Exception e) {
			wsdlDefault = "unknown";
		}

		this.options
				.addOption(
						"sa",
						"sim-aut",
						true,
						"A path referencing the simulation automaton file (*.simaut) for the party to generate code for. Ignored in combination with the flag \"--def-sim-aut\". Default: "
								+ corbaDefault
								+ " (CORBA) or "
								+ wsdlDefault
								+ " (WSDL).");

		/* Add "wsdl" option. */
		this.options.addOption("wsdl", "wsdl", false, "Run in WSDL mode.");
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
	 * @throws CliappProxygenArgumentsParserException
	 *             If something goes wrong while parsing.
	 * @throws NullPointerException
	 *             If <code>args==null</code>.
	 */
	public ProxyCLIArguments parse(final String[] args)
			throws CliappProxygenArgumentsParserException {

		if (args == null)
			throw new NullPointerException();

		try {
			/* Parse the arguments. */
			final CommandLineParser parser = new GnuParser();
			final CommandLine line = parser.parse(getOptions(), args);

			/* Create a record for the parsed arguments. */
			if (line.hasOption("help"))
				return new ProxyCLIArguments();

			final Boolean compile = line.hasOption("comp");
			final Boolean corba = line.hasOption("corba");
			final Boolean defaultSimAut = line.hasOption("def-sim-aut");
			final String destinationPath = line.getOptionValue("dest");
			final String interfaceName = line.getOptionValue("name");
			final String interfacesPaths = line.getOptionValue("ifaces");
			final Boolean print = line.hasOption("print");
			final String proxyResourcesPaths = line.getOptionValue("proxy-res");
			final String proxySourcesPaths = line.getOptionValue("proxy-src");
			final String proxyTemplatesPaths = line
					.getOptionValue("proxy-templ");
			final String simAutPath = line.getOptionValue("sim-aut");
			final Boolean wsdl = line.hasOption("wsdl");

			return new ProxyCLIArguments(compile, corba, defaultSimAut,
					destinationPath, interfaceName, interfacesPaths, print,
					proxyResourcesPaths, proxySourcesPaths,
					proxyTemplatesPaths, simAutPath, wsdl);

		} catch (final Exception e) {
			throw new CliappProxygenArgumentsParserException(
					CliappProxygenArgumentsParserException.PARSE(this, args), e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class CliappProxygenArgumentsParserException extends
			Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected CliappProxygenArgumentsParserException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		protected CliappProxygenArgumentsParserException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String PARSE(final ProxyCLIParser thiz,
				final String[] args) {

			return "The parser \"" + thiz
					+ "\" failed to parse the arguments \""
					+ Arrays.toString(args) + "\".";
		}
	}
}
