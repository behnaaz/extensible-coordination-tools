package org.ect.codegen.v2.proxy.gen.java;

import java.io.File;

import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorDefaults;

public class ProxyJavaGeneratorDefaults {

	//
	// METHODS
	//

	/**
	 * Gets the default destination path, namely the canonical path to the
	 * parent of the path <code>interfacesPath</code>.
	 * 
	 * @param interfacesPath
	 *            The path. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws ProxygenDefaultsResolverException
	 *             If something goes wrong while getting.
	 * @throws NullPointerException
	 *             If <code>interfacesPath==null</code>.
	 */
	public String DESTINATION_PATH(final String interfacesPath)
			throws ProxygenDefaultsResolverException {

		if (interfacesPath == null)
			throw new NullPointerException();

		try {
			return new File(interfacesPath).getParentFile().getCanonicalPath();

		} catch (final Exception e) {
			throw new ProxygenDefaultsResolverException(
					ProxygenDefaultsResolverException.DESTINATION_PATH(this,
							interfacesPath), e);
		}
	}

	/**
	 * Gets the name the default interface.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws ProxygenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String INTERFACE_NAME() throws ProxygenDefaultsResolverException {
		throw new ProxygenDefaultsResolverException(
				ProxygenDefaultsResolverException.INTERFACE_NAME(this),
				"No default specified.");
	}

	/**
	 * Gets the canonical path to the default interfaces file.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws ProxygenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String INTERFACES_PATH() throws ProxygenDefaultsResolverException {
		throw new ProxygenDefaultsResolverException(
				ProxygenDefaultsResolverException.INTERFACES_PATH(this),
				"No default specified.");
	}

	/**
	 * Gets the canonical paths to the default non-Java proxy resources.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws ProxygenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String PROXY_RESOURCES_PATHS()
			throws ProxygenDefaultsResolverException {

		try {
			return CoreJavaGeneratorDefaults.findLibLocations();
		} catch (final Exception e) {
			throw new ProxygenDefaultsResolverException(
					ProxygenDefaultsResolverException
							.PROXY_RESOURCES_PATHS(this),
					e);
		}
	}

	/**
	 * Gets the canonical paths to the default Java proxy sources.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws ProxygenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String PROXY_SOURCES_PATHS()
			throws ProxygenDefaultsResolverException {

		try {
			return CoreJavaGeneratorDefaults.findSrcLocations();
		} catch (final Exception e) {
			throw new ProxygenDefaultsResolverException(
					ProxygenDefaultsResolverException.PROXY_SOURCES_PATHS(this),
					e);
		}
	}

	/**
	 * Gets the canonical path to the default proxy templates.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws ProxygenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String PROXY_TEMPLATES_PATHS()
			throws ProxygenDefaultsResolverException {

		throw new ProxygenDefaultsResolverException(
				ProxygenDefaultsResolverException.PROXY_TEMPLATES_PATHS(this),
				"No default specified.");
	}

	/**
	 * Gets the canonical path to the default <code>.simaut</code> file.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws ProxygenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String SIM_AUT_PATH() throws ProxygenDefaultsResolverException {
		throw new ProxygenDefaultsResolverException(
				ProxygenDefaultsResolverException.SIM_AUT_PATH(this),
				"No default specified.");
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
		return this.getClass().getSimpleName() + "()";
	}

	//
	// EXCEPTIONS
	//

	public static class ProxygenDefaultsResolverException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected ProxygenDefaultsResolverException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		protected ProxygenDefaultsResolverException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String DESTINATION_PATH(
				final ProxyJavaGeneratorDefaults thiz, final String interfacesPath) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument DESTINATION_PATH.";
		}

		protected static String INTERFACE_NAME(
				final ProxyJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument INTERFACE_NAME.";
		}

		protected static String INTERFACES_PATH(
				final ProxyJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument INTERFACES_PATH.";
		}

		protected static String PROXY_RESOURCES_PATHS(
				final ProxyJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument PROXY_RESOURCES_PATHS.";
		}

		protected static String PROXY_SOURCES_PATHS(
				final ProxyJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument PROXY_SOURCES_PATHS.";
		}

		protected static String PROXY_TEMPLATES_PATHS(
				final ProxyJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument PROXY_TEMPLATES_PATHS.";
		}

		protected static String SIM_AUT_PATH(final ProxyJavaGeneratorDefaults thiz) {
			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument SIM_AUT_PATH.";
		}
	}
}
