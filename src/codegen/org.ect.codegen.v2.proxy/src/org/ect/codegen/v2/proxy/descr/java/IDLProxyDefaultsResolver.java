package org.ect.codegen.v2.proxy.descr.java;

import java.io.File;

import org.ect.codegen.v2.core.gen.Bundles;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorDefaults;

public class IDLProxyDefaultsResolver extends ProxyJavaGeneratorDefaults {

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String INTERFACES_PATH()
			throws ProxygenCORBADefaultsResolverException {

		try {
			return Bundles.findFileInBundle("org.ect.codegen.v2.proxy",
					"idl/calculator.idl").getCanonicalPath();

		} catch (final Exception e) {
			throw new ProxygenCORBADefaultsResolverException(
					ProxygenCORBADefaultsResolverException
							.INTERFACES_PATH(this),
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
	public String INTERFACE_NAME() {
		return "example::Calculator";
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String PROXY_RESOURCES_PATHS()
			throws ProxygenCORBADefaultsResolverException {

		try {
			return super.PROXY_RESOURCES_PATHS()
					+ File.pathSeparator
					+ Bundles.findFileInBundle(
							"org.ect.codegen.v2.proxy.rt.java", "lib")
							.getCanonicalPath();

		} catch (final Exception e) {
			throw new ProxygenCORBADefaultsResolverException(
					ProxygenCORBADefaultsResolverException
							.PROXY_RESOURCES_PATHS(this),
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
	public String PROXY_SOURCES_PATHS()
			throws ProxygenCORBADefaultsResolverException {

		try {
			return super.PROXY_SOURCES_PATHS()
					+ File.pathSeparator
					+ Bundles.findFileInBundle(
							"org.ect.codegen.v2.proxy.rt.java", "src")
							.getCanonicalPath();

		} catch (final Exception e) {
			throw new ProxygenCORBADefaultsResolverException(
					ProxygenCORBADefaultsResolverException
							.PROXY_SOURCES_PATHS(this),
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
	public String PROXY_TEMPLATES_PATHS()
			throws ProxygenCORBADefaultsResolverException {

		try {
			return Bundles.findFileInBundle("org.ect.codegen.v2.proxy",
					"stg/corba.stg").getCanonicalPath();
		} catch (final Exception e) {
			throw new ProxygenCORBADefaultsResolverException(
					ProxygenCORBADefaultsResolverException
							.PROXY_TEMPLATES_PATHS(this),
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
	public String SIM_AUT_PATH() throws ProxygenCORBADefaultsResolverException {
		try {
			return Bundles.findFileInBundle("org.ect.codegen.v2.proxy",
					"idl/calculator.simaut").getCanonicalPath();
		} catch (final Exception e) {
			throw new ProxygenCORBADefaultsResolverException(
					ProxygenCORBADefaultsResolverException.SIM_AUT_PATH(this),
					e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class ProxygenCORBADefaultsResolverException extends
			ProxygenDefaultsResolverException {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected ProxygenCORBADefaultsResolverException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		protected ProxygenCORBADefaultsResolverException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String INTERFACE_NAME(final ProxyJavaGeneratorDefaults thiz) {

			return ProxygenDefaultsResolverException.INTERFACE_NAME(thiz);
		}

		protected static String INTERFACES_PATH(final ProxyJavaGeneratorDefaults thiz) {

			return ProxygenDefaultsResolverException.INTERFACES_PATH(thiz);
		}

		protected static String PROXY_RESOURCES_PATHS(
				final ProxyJavaGeneratorDefaults thiz) {

			return ProxygenDefaultsResolverException
					.PROXY_RESOURCES_PATHS(thiz);
		}

		protected static String PROXY_SOURCES_PATHS(
				final ProxyJavaGeneratorDefaults thiz) {

			return ProxygenDefaultsResolverException.PROXY_SOURCES_PATHS(thiz);
		}

		protected static String PROXY_TEMPLATES_PATHS(
				final ProxyJavaGeneratorDefaults thiz) {

			return ProxygenDefaultsResolverException
					.PROXY_TEMPLATES_PATHS(thiz);
		}

		protected static String SIM_AUT_PATH(final ProxyJavaGeneratorDefaults thiz) {
			return ProxygenDefaultsResolverException.SIM_AUT_PATH(thiz);
		}
	}
}
