package org.ect.codegen.v2.proxy.cli;

import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgramArguments;

public class ProxyCLIArguments extends ProxyJavaGeneratorProgramArguments {
	private static final long serialVersionUID = 1L;

	//
	// FIELDS
	//

	/**
	 * Flag indicating if the console application should show the help message.
	 */
	public final boolean HELP;

	//
	// CONSTRUCTORS
	//

	public ProxyCLIArguments()
			throws AbstractGeneratorProgramArgumentsException {
		this.HELP = true;
	}

	public ProxyCLIArguments(final Boolean compile, Boolean corba,
			final Boolean defaultSimAut, final String destinationPath,
			final String interfaceName, final String interfacesPaths,
			final Boolean print, final String proxyResourcesPaths,
			final String proxySourcesPaths, final String proxyTemplatesPaths,
			final String simAutPath, final Boolean wsdl)
			throws ProxygenArgumentsException,
			AbstractGeneratorProgramArgumentsException {

		super(compile, corba, defaultSimAut, destinationPath, interfaceName,
				interfacesPaths, print, proxyResourcesPaths, proxySourcesPaths,
				proxyTemplatesPaths, simAutPath, wsdl);

		this.HELP = false;
	}
}
