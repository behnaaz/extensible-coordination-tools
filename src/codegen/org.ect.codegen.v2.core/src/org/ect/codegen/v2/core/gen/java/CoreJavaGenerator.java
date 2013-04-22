package org.ect.codegen.v2.core.gen.java;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.java.JavaComposition;
import org.ect.codegen.v2.core.descr.java.JavaConnector;
import org.ect.codegen.v2.core.gen.AbstractGenerator;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class CoreJavaGenerator
		extends
		AbstractJavaGenerator<JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> {

	//
	// FIELDS
	//

	/**
	 * The cached result of <code>tryGenerate()</code>.
	 * 
	 * @see #tryGenerate()
	 */
	private Map<String, String> tryGenerateResult;

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes: <code>super(composition,stgFiles,srcDirs,libDirs)</code>.
	 * 
	 * @see AbstractGenerator#AbstractGenerator(Object, Collection, Collection,
	 *      Collection)
	 */
	public CoreJavaGenerator(
			final JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> composition,
			final Collection<File> stgFiles, final Collection<File> srcDirs,
			final Collection<File> libDirs) {

		super(composition, stgFiles, srcDirs, libDirs);
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
	public boolean canGenerate() {
		try {
			tryGenerate();
			return true;
		} catch (final Exception e) {
			return false;
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
	public Map<String, String> generate() {

		/* Validate state. */
		if (!canGenerate())
			throw new IllegalStateException("!canGenerate()");

		/* Try to generate. */
		try {
			return tryGenerate();
		}

		/* Catch. */
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Tries to generate code for the subject of this generator.
	 * 
	 * <p>
	 * Returns a map from names of generated files to their content.
	 * </p>
	 * 
	 * @return A map from strings to strings. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private Map<String, String> tryGenerate() throws Exception {
		if (tryGenerateResult == null) {

			/* Get the template. */
			ST template = null;
			for (final STGroup g : super.loadTemplates())
				if ((template = g.getInstanceOf("connectorClass")) != null)
					break;

			/* Prepare the subject. */
			final JavaComposition<JavaConnector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton> composition = super
					.getSubject();

			composition.tryJoinSingleStateConnectors();
			composition.renameNonFIFOs("Region");

			/* Prepare the template.. */
			tryGenerateResult = new HashMap<String, String>();
			template.add("composition", composition);
			// for (final Connector<DefaultConstraintAutomaton> c : composition)
			// {
			// template.add("connector", c);
			// break;
			// }

			/* Generate. */
			final String name = composition.getClassName().toString() + ".java";
			final String content = template.render();
			tryGenerateResult.put(name, content);
		}

		return tryGenerateResult;
	}
}
