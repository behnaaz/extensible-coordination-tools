package org.ect.codegen.v2.core.gen.java;

import org.ect.codegen.v2.core.gen.Bundles;

public class CoreJavaGeneratorDefaults {

	//
	// CONSTRUCTORS
	//

	/**
	 * @deprecated Use this class in a static way.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private CoreJavaGeneratorDefaults() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS - PUBLIC
	//

	/**
	 * Checks if this class can find the locations of the default lib files.
	 * 
	 * @return <code>true</code> if this class can find the locations;
	 *         <code>false</code> otherwise.
	 */
	public static boolean canFindLibLocations() {
		try {
			tryFindLibLocations();
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Checks if this class can find the locations of the default src files.
	 * 
	 * @return <code>true</code> if this class can find the locations;
	 *         <code>false</code> otherwise.
	 */
	public static boolean canFindSrcLocations() {
		try {
			tryFindSrcLocations();
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Checks if this class can find the locations of the default stg files.
	 * 
	 * @return <code>true</code> if this class can find the locations;
	 *         <code>false</code> otherwise.
	 */
	public static boolean canFindStgLocations() {
		try {
			tryFindStgLocations();
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Finds the locations of the default lib files.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!canFindLibLocations()</code>.
	 * 
	 * @see #canFindLibLocations()
	 */
	public static String findLibLocations() {

		/* Validate state. */
		if (!canFindLibLocations())
			throw new IllegalStateException("!canFindLibLocations()");

		/* Try to find. */
		try {
			return tryFindLibLocations();
		}

		/* Catch. */
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Finds the locations of the default src files.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!canFindSrcLocations()</code>.
	 * 
	 * @see #canFindSrcLocations()
	 */
	public static String findSrcLocations() {

		/* Validate state. */
		if (!canFindSrcLocations())
			throw new IllegalStateException("!canFindSrcLocations()");

		/* Try to find. */
		try {
			return tryFindSrcLocations();
		}

		/* Catch. */
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Finds the locations of the default stg files.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!canFindStgLocations()</code>.
	 * 
	 * @see #canFindStgLocations()
	 */
	public static String findStgLocations() {

		/* Validate state. */
		if (!canFindStgLocations())
			throw new IllegalStateException("!canFindStgLocations()");

		/* Try to find. */
		try {
			return tryFindStgLocations();
		}

		/* Catch. */
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	//
	// STATIC - METHODS - PRIVATE
	//

	/**
	 * Tries to find the locations of the default lib files.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private static String tryFindLibLocations() throws Exception {
		final String string = Bundles.findFileInBundle(
				"org.ect.codegen.v2.core.rt.java", "lib").getCanonicalPath();

		if (string == null)
			throw new Exception();

		return string;
	}

	/**
	 * Tries to find the locations of the default stg files.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private static String tryFindStgLocations() throws Exception {
		final String string = Bundles.findFileInBundle(
				"org.ect.codegen.v2.core", "stg/Connector.stg")
				.getCanonicalPath();

		if (string == null)
			throw new Exception();

		return string;
	}

	/**
	 * Tries to find the locations of the default src files.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private static String tryFindSrcLocations() throws Exception {
		final String string = Bundles.findFileInBundle(
				"org.ect.codegen.v2.core.rt.java", "src").getCanonicalPath();

		if (string == null)
			throw new Exception();

		return string;
	}
}
