package org.ect.codegen.v2.core.gen.java;

import java.io.File;

import org.ect.codegen.v2.core.gen.AbstractGeneratorProgramArguments;

public class CoreJavaGeneratorProgramArguments extends
		AbstractGeneratorProgramArguments {

	private static final long serialVersionUID = 1L;

	//
	// FIELDS
	//

	/**
	 * The locations of the directories, separated by {@link File#pathSeparator}
	 * , containing the libraries used by the code to generate.
	 */
	private final String libLocations;

	/**
	 * The locations of the directories, separated by {@link File#pathSeparator}
	 * , containing the sources of the runtime framework used by the code to
	 * generate.
	 */
	private final String srcLocations;

	/**
	 * The locations of the templates, separated by {@link File#pathSeparator},
	 * used to generate code with.
	 */
	private final String stgLocations;

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
	 * @param libLocations
	 *            The locations to the directories, separated by
	 *            {@link File#pathSeparator}, containing the libraries used by
	 *            the code to generate; <code>null</code> for default.
	 * @param srcLocations
	 *            The locations to the directories, separated by
	 *            {@link File#pathSeparator}, containing the sources of the
	 *            runtime framework used by the code to generate;
	 *            <code>null</code> for default.
	 * @param stgLocations
	 *            The locations to the templates, separated by
	 *            {@link File#pathSeparator}, used to generate code with;
	 *            <code>null</code> for default.
	 * @throws CodegenArgumentsException
	 *             If something goes wrong.
	 * @throws AbstractGeneratorProgramArgumentsException
	 * @throws NullPointerException
	 *             If <code>inLocation==null</code>.
	 */
	public CoreJavaGeneratorProgramArguments(final String inLocation,
			final String outLocation, final String libLocations,
			final String srcLocations, final String stgLocations)
			throws AbstractGeneratorProgramArgumentsException {

		super(inLocation, outLocation);

		/* Initialize. */
		this.libLocations = libLocations == null ? CoreJavaGeneratorDefaults
				.findLibLocations() : libLocations;
		this.srcLocations = srcLocations == null ? CoreJavaGeneratorDefaults
				.findSrcLocations() : srcLocations;
		this.stgLocations = stgLocations == null ? CoreJavaGeneratorDefaults
				.findStgLocations() : stgLocations;
	}

	//
	// METHODS
	//

	/**
	 * Gets the locations of the directories, separated by
	 * {@link File#pathSeparator}, containing the libraries used by the code to
	 * generate.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getLibLocations() {
		return libLocations;
	}

	/**
	 * Gets the locations of the directories, separated by
	 * {@link File#pathSeparator}, containing the sources of the runtime
	 * framework used by the code to generate.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getSrcLocations() {
		return srcLocations;
	}

	/**
	 * Gets the locations of the templates, separated by
	 * {@link File#pathSeparator}, used to generate code with.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getStgLocations() {
		return stgLocations;
	}
}
