package org.ect.codegen.v2.proxy.descr.java;

import java.io.File;

import org.ect.codegen.v2.core.gen.Bundles;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorDefaults;

public class WSDLProxyDefaultsResolver extends ProxyJavaGeneratorDefaults {

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String INTERFACES_PATH()
			throws ProxygenWSDLDefaultsResolverException {

		try {
			return Bundles.findFileInBundle("org.ect.codegen.v2.proxy",
					"wsdl/greath.wsdl").getCanonicalPath();

		} catch (final Exception e) {
			throw new ProxygenWSDLDefaultsResolverException(
					ProxygenWSDLDefaultsResolverException.INTERFACES_PATH(this),
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
		return "reservationService";
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
			throws ProxygenWSDLDefaultsResolverException {

		try {
			return super.PROXY_RESOURCES_PATHS()
					+ File.pathSeparator
					+ Bundles.findFileInBundle(
							"org.ect.codegen.v2.proxy.rt.java", "lib")
							.getCanonicalPath();

		} catch (final Exception e) {
			throw new ProxygenWSDLDefaultsResolverException(
					ProxygenWSDLDefaultsResolverException
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
			throws ProxygenWSDLDefaultsResolverException {

		try {
			return super.PROXY_SOURCES_PATHS()
					+ File.pathSeparator
					+ Bundles.findFileInBundle(
							"org.ect.codegen.v2.proxy.rt.java", "src")
							.getCanonicalPath();

		} catch (final Exception e) {
			throw new ProxygenWSDLDefaultsResolverException(
					ProxygenWSDLDefaultsResolverException
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
			throws ProxygenWSDLDefaultsResolverException {

		try {
			return Bundles.findFileInBundle("org.ect.codegen.v2.proxy",
					"stg/wsdl.stg").getCanonicalPath();
		} catch (final Exception e) {
			throw new ProxygenWSDLDefaultsResolverException(
					ProxygenWSDLDefaultsResolverException
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
	public String SIM_AUT_PATH() throws ProxygenWSDLDefaultsResolverException {
		try {
			return Bundles.findFileInBundle("org.ect.codegen.v2.proxy",
					"wsdl/greath.simaut").getCanonicalPath();
		} catch (final Exception e) {
			throw new ProxygenWSDLDefaultsResolverException(
					ProxygenWSDLDefaultsResolverException.SIM_AUT_PATH(this), e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class ProxygenWSDLDefaultsResolverException extends
			ProxygenDefaultsResolverException {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected ProxygenWSDLDefaultsResolverException(final String message,
				final String cause) {
			super(message, new Throwable(cause));
		}

		protected ProxygenWSDLDefaultsResolverException(final String message,
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
