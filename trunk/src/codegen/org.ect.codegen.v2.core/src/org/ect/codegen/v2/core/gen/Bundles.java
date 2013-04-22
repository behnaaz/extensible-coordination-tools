package org.ect.codegen.v2.core.gen;

import java.io.File;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public final class Bundles {

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
	private Bundles() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS - PUBLIC
	//

	/**
	 * Checks if this class can find a bundle named <code>bundleName</code>.
	 * 
	 * @param bundleName
	 *            The name of the bundle. Not <code>null</code>.
	 * @return <code>true</code> if this class can find a bundle named
	 *         <code>bundleName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>bundleName==null</code>.
	 */
	public static boolean canFindBundle(final String bundleName) {

		/* Validate arguments. */
		if (bundleName == null)
			throw new NullPointerException("bundleName");

		/* Check. */
		try {
			tryFindBundle(bundleName);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Checks if this class can find the file referenced by <code>path</code> in
	 * the bundle named <code>bundleName</code>.
	 * 
	 * @param bundleName
	 *            The name of the bundle. Not <code>null</code>.
	 * @param path
	 *            The path of the file. Not <code>null</code>.
	 * @return <code>true</code> if this class can find the file;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>bundleName==null</code> or <code>path==null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!canFindBundle(bundleName)</code>.
	 * 
	 * @see #canFindBundle(String)
	 */
	public static boolean canFindFileInBundle(final String bundleName,
			final String path) {

		/* Validate arguments. */
		if (bundleName == null)
			throw new NullPointerException("bundleName");
		if (path == null)
			throw new NullPointerException("path");
		if (!canFindBundle(bundleName))
			throw new IllegalArgumentException(bundleName);

		/* Check. */
		try {
			tryFindFileInBundle(bundleName, path);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Finds the bundle named <code>bundleName</code>.
	 * 
	 * @param bundleName
	 *            The name of the bundle. Not <code>null</code>.
	 * @return A bundle. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!canFindBundle(bundleName)</code>.
	 * @throws NullPointerException
	 *             If <code>bundleName==null</code>.
	 * 
	 * @see #canFindBundle(String)
	 */
	public static Bundle findBundle(final String bundleName) {

		/* Validate arguments. */
		if (bundleName == null)
			throw new NullPointerException("bundleName");
		if (!canFindBundle(bundleName))
			throw new IllegalArgumentException(bundleName);

		/* Try to find. */
		try {
			return tryFindBundle(bundleName);
		}

		/* Catch. */
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Finds the file referenced by <code>path</code> in the bundle named
	 * <code>bundleName</code>.
	 * 
	 * @param bundleName
	 *            The name of the bundle. Not <code>null</code>.
	 * @param path
	 *            The path referencing the file. Not <code>null</code>.
	 * @return A file. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>bundleName==null</code> or <code>path==null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!canFindBundle(bundleName)</code> or
	 *             <code>!canFindFileInBundle(bundleName)</code>.
	 * 
	 * @see #canFindBundle(String)
	 * @see #canFindFileInBundle(String, String)
	 */
	public static File findFileInBundle(final String bundleName,
			final String path) {

		/* Validate arguments. */
		if (bundleName == null)
			throw new NullPointerException("bundleName");
		if (path == null)
			throw new NullPointerException("path");
		if (!canFindBundle(bundleName))
			throw new IllegalArgumentException(bundleName);
		if (!canFindFileInBundle(bundleName, path))
			throw new IllegalArgumentException(path);

		/* Try to find. */
		try {
			return tryFindFileInBundle(bundleName, path);
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
	 * Tries to find the bundle named <code>bundleName</code>.
	 * 
	 * @param bundleName
	 *            The name of the bundle.
	 * @return A bundle. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private static Bundle tryFindBundle(final String bundleName)
			throws Exception {

		final Bundle bundle = Platform.getBundle(bundleName);
		if (bundle == null)
			throw new NullPointerException();

		return bundle;
	}

	/**
	 * Tries to find the file referenced by <code>path</code> in the bundle
	 * named <code>bundleName</code>.
	 * 
	 * @param bundleName
	 *            The name of the bundle.
	 * @param path
	 *            The path referencing the file.
	 * @return A file. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private static File tryFindFileInBundle(final String bundleName,
			final String path) throws Exception {

		return new File(FileLocator.toFileURL(
				FileLocator.find(findBundle(bundleName), new Path(path), null))
				.getPath());
	}
}
