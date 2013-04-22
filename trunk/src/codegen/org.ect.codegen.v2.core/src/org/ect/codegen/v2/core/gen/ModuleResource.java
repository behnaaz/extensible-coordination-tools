package org.ect.codegen.v2.core.gen;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.ect.codegen.v2.core.descr.Composition;
import org.ect.codegen.v2.core.descr.Connector;
import org.ect.codegen.v2.core.descr.DefaultConstraintAutomaton;
import org.ect.codegen.v2.core.descr.XMLExtractor;
import org.ect.codegen.v2.core.descr.XMLModule;
import org.ect.reo.Module;
import org.ect.reo.Reo;

public class ModuleResource extends Resource {

	//
	// FIELDS
	//

	/**
	 * The compositions in the module of this resource.
	 */
	private Collection<Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> compositions;

	/**
	 * The module of this resource.
	 */
	private final Module module;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a resource for the module <code>module</code>.
	 * 
	 * @param module
	 *            The module. Not <code>null</code>.
	 * @throws ModuleResourceException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>module==null</code>.
	 */
	public ModuleResource(final Module module) throws ModuleResourceException {
		super("module");
		if (module == null)
			throw new NullPointerException();

		this.module = module;
	}

	/**
	 * Constructs a resource for the module in the file at the location
	 * <code>location</code>.
	 * 
	 * @param moduleLocation
	 *            The location. Not <code>null</code>.
	 * @throws ModuleResourceException
	 *             If something goes wrong.
	 * @throws NullPointerException
	 *             If <code>moduleLocation==null</code>.
	 */
	public ModuleResource(final String moduleLocation)
			throws ModuleResourceException {

		super(moduleLocation);

		/* Try to initialize. */
		try {
			this.module = Reo.loadModule(URI.createFileURI(moduleLocation));
			if (module == null)
				throw new ModuleResourceException(
						"I failed to load a module from the file at the location \""
								+ moduleLocation + "\".");
		}

		/* Catch. */
		catch (final Exception e) {
			throw new ModuleResourceException(
					"I failed to initialize a resource for the file at the location ",
					e);
		}
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Checks if this resource can extract compositions from its module.
	 * 
	 * @return A boolean.
	 */
	public final boolean canExtractCompositions() {
		try {
			tryExtractCompositions();
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Extracts the compositions from the module of this resource.
	 * 
	 * @return A collection of compositions. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!canExtractCompositions()</code>.
	 */
	public final Collection<Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> extractCompositions() {

		/* Validate state. */
		if (!canExtractCompositions())
			throw new IllegalStateException("!canExtractCompositions()");

		/* Try to extract. */
		try {
			return tryExtractCompositions();
		}

		/* Catch. */
		catch (final Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * Gets the module of this resource.
	 * 
	 * @return A module. Not <code>null</code>.
	 */
	public final Module getModule() {
		return module;
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
		return super.getCroppedResourceText();
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Tries to extract compositions from the module of this resource.
	 * 
	 * @return A collection of compositions. Never <code>null</code>.
	 * @throws Exception
	 *             If somethign goes wrong.
	 */
	private Collection<Composition<Connector<DefaultConstraintAutomaton>, DefaultConstraintAutomaton>> tryExtractCompositions()
			throws Exception {

		return compositions == null ? compositions = XMLExtractor
				.tryExtractFrom(new XMLModule(module)) : compositions;
	}

	//
	// EXCEPTIONS
	//

	public static class ModuleResourceException extends Exception {
		private static final long serialVersionUID = 1L;

		protected ModuleResourceException(final String message) {
			super(message);
		}

		protected ModuleResourceException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
