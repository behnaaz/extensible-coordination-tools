package org.ect.codegen.v2.core.gen;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Files {

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
	private Files() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Tries to canonize the location <code>location</code>.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * </p>
	 * <p>
	 * <center><code>new File(path).getCanonicalPath()</code></center>
	 * </p>
	 * 
	 * @param location
	 *            The path. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws FilesException
	 *             If something goes wrong.
	 * @throws NullPointerException
	 *             If <code>location==null</code>.
	 * 
	 * @see File#File(String)
	 * @see File#getCanonicalPath()
	 */
	public static String tryCanonize(final String location)
			throws FilesException {

		/* Validate arguments. */
		if (location == null)
			throw new NullPointerException("location");

		/* Try to canonize. */
		try {
			return new File(location).getCanonicalPath();
		}

		/* Catch. */
		catch (final Exception e) {
			throw new FilesException("I failed to canonize the location \""
					+ location + "\".", e);
		}
	}

	/**
	 * Tries to find readable directories at the locations
	 * <code>locations</code>, separated by {@link File#pathSeparator}.
	 * 
	 * @param locations
	 *            The locations. Not <code>null</code>.
	 * @return A collection of files. Never <code>null</code>.
	 * @throws FilesException
	 *             If something goes wrong.
	 * @throws NullPointerException
	 *             If <code>locations==null</code>.
	 */
	public static Collection<File> tryFindReadableDirsAt(final String locations)
			throws FilesException {

		/* Validate arguments. */
		if (locations == null)
			throw new NullPointerException("locations");

		/* Try to find. */
		try {
			final Set<File> dirs = new HashSet<File>();
			for (final String s : locations.split(File.pathSeparator)) {
				final File dir = new File(s);

				if (!dir.isDirectory())
					throw new FilesException(
							"I cannot find a directory at the location \"" + s
									+ "\".");

				if (!dir.canRead())
					throw new FilesException(
							"I cannot read the directory at the location \""
									+ s + "\".");

				dirs.add(dir);
			}

			return dirs;
		}

		/* Catch. */
		catch (final Exception e) {
			throw new FilesException(
					"I failed to find readable directories at the location(s) \""
							+ locations + "\".", e);
		}
	}

	/**
	 * Tries to find readable files at the locations <code>locations</code>,
	 * separated by {@link File#pathSeparator}.
	 * 
	 * @param locations
	 *            The locations. Not <code>null</code>.
	 * @return A collection of files. Never <code>null</code>.
	 * @throws FilesException
	 *             If something goes wrong.
	 * @throws NullPointerException
	 *             If <code>locations==null</code>.
	 */
	public static Collection<File> tryFindReadableFilesAt(final String locations)
			throws FilesException {

		/* Validate arguments. */
		if (locations == null)
			throw new NullPointerException("locations");

		/* Try to find. */
		try {
			final Set<File> files = new HashSet<File>();
			for (final String s : locations.split(File.pathSeparator)) {
				final File file = new File(s);

				if (!file.isFile())
					throw new FilesException(
							"I cannot find a file at the location \"" + s
									+ "\".");

				if (!file.canRead())
					throw new FilesException(
							"I cannot read the file at the location \"" + s
									+ "\".");

				files.add(file);
			}

			return files;
		}

		/* Catch. */
		catch (final Exception e) {
			throw new FilesException(
					"I failed to find readable files at the location(s) \""
							+ locations + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class FilesException extends Exception {
		private static final long serialVersionUID = 1L;

		protected FilesException(final String message) {
			super(message);
		}

		protected FilesException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}
