package org.ect.codegen.v2.orch.gen.java;

import java.io.File;

import org.ect.codegen.v2.core.gen.Bundles;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorDefaults;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorDefaults;


public class OrchJavaGeneratorDefaults {

	//
	// METHODS
	//

	/**
	 * Gets the default destination path, namely the canonical path to the
	 * parent of the path <code>MODULE_PATH</code>.
	 * 
	 * @param modulePath
	 *            The path. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws OrcgenDefaultsResolverException
	 *             If something goes wrong while getting.
	 * @throws NullPointerException
	 *             If <code>interfacesPath==null</code>.
	 */
	public String DESTINATION_PATH(final String modulePath)
			throws OrcgenDefaultsResolverException {

		if (modulePath == null)
			throw new NullPointerException();

		try {
			return new File(modulePath).getParentFile().getCanonicalPath();

		} catch (final Exception e) {
			throw new OrcgenDefaultsResolverException(
					OrcgenDefaultsResolverException.DESTINATION_PATH(this,
							modulePath), e);
		}
	}

	/**
	 * Gets the canonical path to the default <code>.reo</code> file.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws OrcgenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String MODULE_PATH() throws OrcgenDefaultsResolverException {
		try {
			return Bundles.findFileInBundle("cwi.reo.codegen",
					"res/conn/Sync.reo").getCanonicalPath();

		} catch (final Exception e) {
			throw new OrcgenDefaultsResolverException(
					OrcgenDefaultsResolverException.MODULE_PATH(this), e);
		}
	}

	/**
	 * Gets the canonical paths to the default non-Java orchestrator resources.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws OrcgenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String ORC_RESOURCES_PATHS() throws OrcgenDefaultsResolverException {

		try {
			return CoreJavaGeneratorDefaults.findLibLocations() + File.pathSeparator
					+ new ProxyJavaGeneratorDefaults().PROXY_RESOURCES_PATHS();

		} catch (final Exception e) {
			throw new OrcgenDefaultsResolverException(
					OrcgenDefaultsResolverException.ORC_RESOURCES_PATHS(this),
					e);
		}
	}

	/**
	 * Gets the canonical paths to the default Java orchestrator sources.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws OrcgenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String ORC_SOURCES_PATHS() throws OrcgenDefaultsResolverException {

		try {
			return CoreJavaGeneratorDefaults.findSrcLocations() + File.pathSeparator
					+ new ProxyJavaGeneratorDefaults().PROXY_SOURCES_PATHS();

		} catch (final Exception e) {
			throw new OrcgenDefaultsResolverException(
					OrcgenDefaultsResolverException.ORC_SOURCES_PATHS(this), e);
		}
	}

	/**
	 * Gets the canonical path to the default orchestrator templates.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws OrcgenDefaultsResolverException
	 *             If something goes wrong while getting.
	 */
	public String ORC_TEMPLATES_PATHS() throws OrcgenDefaultsResolverException {

		try {
			return Bundles.findFileInBundle("org.ect.codegen.v2.proxy",
					"stg/orch.stg").getCanonicalPath();

		} catch (final Exception e) {
			throw new OrcgenDefaultsResolverException(
					OrcgenDefaultsResolverException.ORC_TEMPLATES_PATHS(this),
					e);
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
	public String toString() {
		return this.getClass().getSimpleName() + "()";
	}

	//
	// EXCEPTIONS
	//

	public static class OrcgenDefaultsResolverException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected OrcgenDefaultsResolverException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		protected OrcgenDefaultsResolverException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String DESTINATION_PATH(
				final OrchJavaGeneratorDefaults thiz, final String interfacesPath) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument DESTINATION_PATH.";
		}

		protected static String MODULE_PATH(final OrchJavaGeneratorDefaults thiz) {
			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument MODULE_PATH.";
		}

		protected static String ORC_RESOURCES_PATHS(
				final OrchJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument ORC_RESOURCES_PATHS.";
		}

		protected static String ORC_SOURCES_PATHS(
				final OrchJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument ORC_SOURCES_PATHS.";
		}

		protected static String ORC_TEMPLATES_PATHS(
				final OrchJavaGeneratorDefaults thiz) {

			return "The resolver \""
					+ thiz
					+ "\" failed to get a default value for the argument ORC_TEMPLATES_PATHS.";
		}
	}
}
