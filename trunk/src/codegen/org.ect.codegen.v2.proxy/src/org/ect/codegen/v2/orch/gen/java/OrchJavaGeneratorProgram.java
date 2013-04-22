package org.ect.codegen.v2.orch.gen.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.XMLExtractor;
import org.ect.codegen.v2.core.descr.XMLModule;
import org.ect.codegen.v2.core.descr.java.JavaComposition;
import org.ect.codegen.v2.core.descr.java.JavaConnector;
import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.ect.codegen.v2.core.gen.AbstractGeneratorProgram;
import org.ect.codegen.v2.core.gen.Files;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgram;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgramArguments;
import org.ect.codegen.v2.orch.descr.java.Orchestration;
import org.ect.codegen.v2.proxy.descr.java.AbstractParty;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgramArguments;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgram;
import org.ect.codegen.v2.proxy.rt.java.Log4j;

public class OrchJavaGeneratorProgram extends
		AbstractGeneratorProgram<OrchJavaGeneratorProgramArguments, Orchestration> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes: <code>super(arguments,tree)</code>.
	 * 
	 * @see AbstractGeneratorProgram#AbstractProgram(CoreJavaGeneratorProgramArguments, DirTree)
	 */
	public OrchJavaGeneratorProgram(final OrchJavaGeneratorProgramArguments arguments, final DirTree tree) {
		super(arguments, tree);
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Orchestration call() throws OrcgenProgramException {

		/* Try to call. */
		try {
			final OrchJavaGeneratorProgramArguments arguments = super.getArguments();
			final DirTree tree = super.getTree();

			/* Initialize. */
			final ModuleWithArgumentsPropertiesResource resource = new ModuleWithArgumentsPropertiesResource(
					arguments.MODULE_PATH);

			final CoreJavaGeneratorProgram coreProgram = new CoreJavaGeneratorProgram(
					new CoreJavaGeneratorProgramArguments(arguments.MODULE_PATH,
							arguments.DESTINATION_PATH, null, null, null), tree);

			final Collection<ProxyJavaGeneratorProgram> proxyPrograms = new ArrayList<ProxyJavaGeneratorProgram>();
			for (final ProxyJavaGeneratorProgramArguments a : resource.extractArgumentsProperties())
				proxyPrograms.add(new ProxyJavaGeneratorProgram(a, tree));

			final Collection<File> resourcesDirs = Files
					.tryFindReadableDirsAt(arguments.ORC_RESOURCES_PATHS);
			final Collection<File> sourcesDirs = Files
					.tryFindReadableDirsAt(arguments.ORC_SOURCES_PATHS);
			final Collection<File> templatesFiles = Files
					.tryFindReadableFilesAt(arguments.ORC_TEMPLATES_PATHS);

			/* Generate. */
			final Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> connectors = coreProgram
					.call();

			final Collection<AbstractParty<?>> parties = new ArrayList<AbstractParty<?>>();
			for (final ProxyJavaGeneratorProgram p : proxyPrograms)
				parties.add(p.call());

			final Orchestration orchestration = new Orchestration(connectors,
					parties, XMLExtractor.extractLinksFrom(new XMLModule(
							resource.getModule())));

			final OrchJavaGenerator orcgenGenerator = new OrchJavaGenerator(
					orchestration, templatesFiles, sourcesDirs, resourcesDirs);

			orcgenGenerator.generateThenAddTo(tree);
			orcgenGenerator.addLibDirsTo(tree);
			orcgenGenerator.addSrcDirsTo(tree);

			return orchestration;
		}

		/* Catch. */
		catch (final Exception e) {
			throw new OrcgenProgramException(
					"I failed to generate code for the module file at the location \""
							+ super.getArguments().MODULE_PATH + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class OrcgenProgramException extends Exception {
		private static final long serialVersionUID = 1L;

		protected OrcgenProgramException(final String message) {
			super(message);
		}

		protected OrcgenProgramException(final String message,
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
