package org.ect.codegen.v2.proxy.gen.java;

import java.io.File;
import java.util.Collection;

import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.ect.codegen.v2.core.gen.AbstractGeneratorProgram;
import org.ect.codegen.v2.core.gen.Files;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgramArguments;
import org.ect.codegen.v2.proxy.SimAutResource;
import org.ect.codegen.v2.proxy.descr.java.AbstractParty;
import org.ect.codegen.v2.proxy.descr.java.IDLParty;
import org.ect.codegen.v2.proxy.descr.java.IDLResource;
import org.ect.codegen.v2.proxy.descr.java.WSDLParty;
import org.ect.codegen.v2.proxy.rt.java.Log4j;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLResource;

public class ProxyJavaGeneratorProgram extends
		AbstractGeneratorProgram<ProxyJavaGeneratorProgramArguments, AbstractParty<?>> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes: <code>super(arguments,tree)</code>.
	 * 
	 * @see AbstractGeneratorProgram#AbstractProgram(CoreJavaGeneratorProgramArguments, DirTree)
	 */
	public ProxyJavaGeneratorProgram(final ProxyJavaGeneratorProgramArguments arguments, final DirTree tree) {
		super(arguments, tree);
	}

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	public AbstractParty<?> call() throws ProxygenProgramException {

		/* Try to call. */
		try {
			final ProxyJavaGeneratorProgramArguments arguments = super.getArguments();
			final DirTree tree = super.getTree();

			/* Initialize. */
			final Collection<File> libDirs = Files
					.tryFindReadableDirsAt(arguments.PROXY_RESOURCES_PATHS);

			final Collection<File> srcDirs = Files
					.tryFindReadableDirsAt(arguments.PROXY_SOURCES_PATHS);

			final Collection<File> stgFiles = Files
					.tryFindReadableFilesAt(arguments.PROXY_TEMPLATES_PATHS);

			final SimAutResource simAutResource = arguments.DEFAULT_SIM_AUT ? SimAutResource.DEFAULT_SIMAUT_RESOURCE
					: new SimAutResource(arguments.SIM_AUT_PATH);

			final AbstractParty<?> party;
			if (arguments.CORBA && arguments.WSDL)
				throw new ProxygenProgramException(
						"I cannot run in both IDL and WSDL mode at the same time.");
			else if (arguments.CORBA)
				party = new IDLParty(arguments.INTERFACE_NAME, new IDLResource(
						arguments.INTERFACES_PATH), simAutResource);
			else if (arguments.WSDL)
				party = new WSDLParty(arguments.INTERFACE_NAME,
						new WSDLResource(arguments.INTERFACES_PATH),
						simAutResource);
			else
				throw new ProxygenProgramException(
						"I must run in either IDL or WSDL mode.");

			/* Generate. */
			final ProxyJavaGenerator<AbstractParty<?>> generator = new ProxyJavaGenerator<AbstractParty<?>>(
					party, stgFiles, srcDirs, libDirs);

			generator.generateThenAddTo(tree);
			generator.addLibDirsTo(tree);
			generator.addSrcDirsTo(tree);

			/* Return. */
			return party;
		}

		/* Catch. */
		catch (final Exception e) {
			throw new ProxygenProgramException(
					"I failed to generate code for the interface file at the location \""
							+ super.getArguments().INTERFACES_PATH + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class ProxygenProgramException extends Exception {
		private static final long serialVersionUID = 1L;

		protected ProxygenProgramException(final String message) {
			super(message);
		}

		protected ProxygenProgramException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}

	//
	// STATIC
	//

	static {
		Log4j.disable();
	}
}