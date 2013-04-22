package org.ect.codegen.v2.core.gen;

import java.io.File;
import java.io.Serializable;

public abstract class AbstractGeneratorProgramArguments implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	// FIELDS
	//

	/**
	 * The location of the input module to generate code for.
	 */
	private final String inLocation;

	/**
	 * The location of the output directory to write the code to generate to.
	 */
	private final String outLocation;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs arguments for the codegen program.
	 * 
	 * @param inLocation
	 *            The location of the input module to generate code for. Not
	 *            <code>null</code>.
	 * @param outLocation
	 *            The location of the output directory to write the code to
	 *            generate to; <code>null</code> for default.
	 * @param stgLocations
	 *            The locations to the templates, separated by
	 *            {@link File#pathSeparator}, used to generate code with;
	 *            <code>null</code> for default.
	 * @throws AbstractGeneratorProgramArgumentsException
	 *             If something goes wrong.
	 * @throws NullPointerException
	 *             If <code>inLocation==null</code>.
	 */
	public AbstractGeneratorProgramArguments(final String inLocation,
			final String outLocation)
			throws AbstractGeneratorProgramArgumentsException {

		/* Validate arguments. */
		if (inLocation == null)
			throw new NullPointerException(inLocation);

		/* Initialize $this.inLocation. */
		try {
			this.inLocation = Files.tryCanonize(inLocation);
		} catch (final Exception e) {
			throw new AbstractGeneratorProgramArgumentsException(
					"I failed to canonize the location \"" + inLocation
							+ "\" of the input module.", e);
		}

		/* Initialize $this.outLocation. */
		if (outLocation == null) {
			final String parentLocation = new File(inLocation).getParent();
			if (parentLocation == null)
				throw new AbstractGeneratorProgramArgumentsException(
						"I failed to compute the directory containing the input module.");

			try {
				this.outLocation = Files.tryCanonize(parentLocation);
			} catch (final Exception e) {
				throw new AbstractGeneratorProgramArgumentsException(
						"I failed to canonize the location \"" + parentLocation
								+ "\" of the default output directory.", e);
			}
		} else
			this.outLocation = outLocation;
	}

	//
	// METHODS
	//

	/**
	 * Gets the location of the input module to generate code for.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getInLocation() {
		return inLocation;
	}

	/**
	 * Gets the location of the output directory to write the code to generate
	 * to.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getOutLocation() {
		return outLocation;
	}

	//
	// EXCEPTIONS
	//

	public static class AbstractGeneratorProgramArgumentsException extends
			Exception {

		private static final long serialVersionUID = 1L;

		protected AbstractGeneratorProgramArgumentsException(
				final String message) {
			super(message);
		}

		protected AbstractGeneratorProgramArgumentsException(
				final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}
