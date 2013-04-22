package org.ect.codegen.v2.proxy.gen.java;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.ect.codegen.v2.core.gen.AbstractGenerator;
import org.ect.codegen.v2.core.gen.Files;
import org.ect.codegen.v2.core.gen.java.AbstractJavaGenerator;
import org.ect.codegen.v2.core.gen.java.CoreJavaGenerator;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorDefaults;
import org.ect.codegen.v2.proxy.descr.java.AbstractParty;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class ProxyJavaGenerator<P extends AbstractParty<?>> extends
		AbstractJavaGenerator<P> {

	//
	// FIELDS
	//

	/**
	 * The cached result of <code>tryGenerate()</code>.
	 * 
	 * @see #tryGenerate()
	 */
	private Map<String, String> tryGenerateResult;

	/**
	 * The cached result of <code>tryGenerateSimAut()</code>.
	 * 
	 * @see #tryGenerateSimAut()
	 */
	private Map<String, String> tryGenerateSimAutResult;

	//
	// CONSTRUCTORS
	//

	/**
	 * Invokes: <code>super(party,stgFiles,srcDirs,libDirs)</code>.
	 * 
	 * @see AbstractGenerator#AbstractGenerator(Object, Collection, Collection,
	 *      Collection)
	 */
	public ProxyJavaGenerator(final P party, final Collection<File> stgFiles,
			final Collection<File> srcDirs, final Collection<File> libDirs) {

		super(party, stgFiles, srcDirs, libDirs);
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
				if ((template = g.getInstanceOf("proxyClass")) != null)
					break;

			/* Prepare the subject. */
			final P party = super.getSubject();

			/* Prepare the template. */
			template.add("party", party);

			/* Generate. */
			tryGenerateResult = new HashMap<String, String>();
			tryGenerateResult.putAll(tryGenerateSimAut());
			tryGenerateResult.put(party.getClassName().toString() + ".java",
					template.render());
		}

		return tryGenerateResult;
	}

	/**
	 * Tries to generate code for the simulation automaton of the subject of
	 * this generator.
	 * 
	 * <p>
	 * Returns a map from names of generated files to their content.
	 * </p>
	 * 
	 * @return A map from strings to strings. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private final Map<String, String> tryGenerateSimAut() throws Exception {
		if (tryGenerateSimAutResult == null) {

			/* Get defaults. */
			final Collection<File> stgFiles = Files
					.tryFindReadableFilesAt(CoreJavaGeneratorDefaults
							.findStgLocations());
			final Collection<File> srcDirs = Files
					.tryFindReadableDirsAt(CoreJavaGeneratorDefaults
							.findSrcLocations());
			final Collection<File> libDirs = Files
					.tryFindReadableDirsAt(CoreJavaGeneratorDefaults
							.findLibLocations());

			/* Generate. */
			tryGenerateSimAutResult = new CoreJavaGenerator(super.getSubject()
					.getSimAut(), stgFiles, srcDirs, libDirs).generate();
		}

		return tryGenerateSimAutResult;
	}
}
