package org.ect.codegen.v2.proxy.rt.java.wsdl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.axis2.description.AxisService;
import org.apache.axis2.description.WSDL11ToAllAxisServicesBuilder;
import org.apache.axis2.description.WSDL20ToAllAxisServicesBuilder;
import org.ect.codegen.v2.proxy.rt.java.Resource;

public final class WSDLParser {

	//
	// CONSTRUCTORS
	//

	/**
	 * Hide the default constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private WSDLParser() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC
	//

	/**
	 * Parses the resource <code>wsdlResource</code> as a WSDL resource.
	 * 
	 * @param wsdlResource
	 *            The resource. Not <code>null</code>.
	 * @return A nonempty map from names to WSDL services. Never
	 *         <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>wsdlResource==null</code>.
	 * @throws WSDLParserException
	 *             If something goes wrong before or while parsing.
	 */
	public static Map<String, AxisService> parseWSDL(final Resource wsdlResource)
			throws WSDLParserException {

		if (wsdlResource == null)
			throw new NullPointerException();

		/* Get a stream for $wsdlResource. */
		final ResettingBufferedInputStream stream;
		try {
			stream = new ResettingBufferedInputStream(
					wsdlResource.newInputStream());

		} catch (Exception e) {
			throw new WSDLParserException("I cannot open the resource \""
					+ wsdlResource.getCroppedResourceText() + "\".");
		}

		/* Attempt to parse $stream as WSDL 2.0. */
		final Collection<AxisService> services = new HashSet<AxisService>();
		try {
			services.addAll(new WSDL20ToAllAxisServicesBuilder(stream)
					.populateAllServices());
		}

		/* Attempt to parse $stream as WSDL 1.1. */
		catch (Exception e) {
			try {
				services.addAll(new WSDL11ToAllAxisServicesBuilder(stream)
						.populateAllServices());

			} catch (Exception ee) {
				throw new WSDLParserException(
						"I can parse the resource \""
								+ wsdlResource.getCroppedResourceText()
								+ "\" neither as a WSDL 1.1 resource nor as a WSDL 2.0 resource.",
						ee);
			}
		}

		/* Close $stream. */
		finally {
			try {
				stream.closeHard();
			} catch (IOException e) {
				throw new WSDLParserException(
						"I cannot close the WSDL resource \"" + wsdlResource
								+ "\".", e);
			}
		}

		/* Return. */
		final Map<String, AxisService> namesToInterfaces = new HashMap<String, AxisService>();
		for (final AxisService s : services)
			namesToInterfaces.put(s.getName(), s);

		if (namesToInterfaces.isEmpty())
			throw new WSDLParserException("The WSDL resource \"" + wsdlResource
					+ "\" contains no valid service definitions.");

		return namesToInterfaces;
	}

	//
	// CLASSES
	//

	private static class ResettingBufferedInputStream extends
			BufferedInputStream {

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a buffered input stream that <em>resets</em>, instead of
		 * closes, upon invocation of <code>close()</code> and that
		 * <em>closes</em> upon invocation of <code>closeHard()</code>.
		 * 
		 * @param in
		 *            The underlying input stream.
		 * 
		 * @see #close()
		 * @see #closeHard()
		 */
		public ResettingBufferedInputStream(final InputStream in) {
			super(in);
			super.mark(Integer.MAX_VALUE);
		}

		//
		// METHODS
		//

		/**
		 * Resets, instead of closes, this stream.
		 * 
		 * @throws IOException
		 *             If an I/O error occurs.
		 */
		@Override
		public void close() throws IOException {
			super.reset();
		}

		/**
		 * Invokes: <code>super.close()</code>.
		 * 
		 * @throws IOException
		 *             If an I/O error occurs.
		 * @see BufferedInputStream#close()
		 */
		public void closeHard() throws IOException {
			super.close();
		}
	}

	//
	// EXCEPTIONS
	//

	public static class WSDLParserException extends Exception {
		private static final long serialVersionUID = 1L;

		private WSDLParserException(String message) {
			super(message);
		}

		private WSDLParserException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}