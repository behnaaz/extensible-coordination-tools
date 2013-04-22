package org.ect.codegen.v2.core.gen.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.ect.codegen.v2.core.descr.Composition;
import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.java.JavaComposition;
import org.ect.codegen.v2.core.descr.java.JavaConnector;
import org.ect.codegen.v2.core.descr.java.JavaIdentifierFactory;
import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.ect.codegen.v2.core.gen.AbstractGeneratorProgram;
import org.ect.codegen.v2.core.gen.Files;
import org.ect.codegen.v2.core.gen.ModuleResource;

public class CoreJavaGeneratorProgram
		extends
		AbstractGeneratorProgram<CoreJavaGeneratorProgramArguments, Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>>> {

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes: <code>super(arguments,tree)</code>.
	 * 
	 * @see AbstractGeneratorProgram#AbstractProgram(CoreJavaGeneratorProgramArguments,
	 *      DirTree)
	 */
	public CoreJavaGeneratorProgram(
			final CoreJavaGeneratorProgramArguments arguments,
			final DirTree tree) {
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
	@Override
	public Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> call()
			throws CoreProgramException {

		/* Try to call. */
		try {
			final CoreJavaGeneratorProgramArguments arguments = super
					.getArguments();
			final DirTree tree = super.getTree();

			/* Initialize. */
			final Collection<File> libDirs = Files
					.tryFindReadableDirsAt(arguments.getLibLocations());

			final Collection<File> srcDirs = Files
					.tryFindReadableDirsAt(arguments.getSrcLocations());

			final Collection<File> stgFiles = Files
					.tryFindReadableFilesAt(arguments.getStgLocations());

			final ModuleResource resource = new ModuleResource(
					arguments.getInLocation());

			/* Generate. */
			final Collection<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> compositions = new ArrayList<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>>();
			for (final Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> c : resource
					.extractCompositions()) {

				/* Construct a composition with Java identifiers. */
				final JavaIdentifierFactory identifierFactory = new JavaIdentifierFactory();
				final JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> composition = JavaComposition
						.newInstance(c, identifierFactory);

				/* Add $composition to $compositions. */
				compositions.add(composition);

				/* Construct a generator. */
				final CoreJavaGenerator generator = new CoreJavaGenerator(
						composition, stgFiles, srcDirs, libDirs);

				/* Generate. */
				generator.generateThenAddTo(tree);
				generator.addLibDirsTo(tree);
				generator.addSrcDirsTo(tree);
			}

			return compositions;
		}

		/* Catch. */
		catch (final Exception e) {
			throw new CoreProgramException(
					"I failed to generate code for the module file at the location \""
							+ super.getArguments().getInLocation() + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public class CoreProgramException extends Exception {
		private static final long serialVersionUID = 1L;

		protected CoreProgramException(final String message) {
			super(message);
		}

		protected CoreProgramException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
