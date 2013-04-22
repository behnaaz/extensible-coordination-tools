package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

public enum WSDLVersion {
	WSDL11, WSDL20;

	//
	// METHODS
	//

	/**
	 * Gets the namespace URI defined for this WSDL version.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws WSDLVersionException
	 *             If this WSDL version is not supported.
	 * 
	 * @see #WSDL11_NAMESPACE_URI
	 * @see #WSDL20_NAMESPACE_URI
	 */
	public String getNamespaceURI() throws WSDLVersionException {
		switch (this) {
		case WSDL11:
			return WSDL11_NAMESPACE_URI;
		case WSDL20:
			return WSDL20_NAMESPACE_URI;
		default:
			throw new WSDLVersionException(this);
		}
	}

	/**
	 * Gets the remote location of the XML schema for this WSDL version.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws WSDLVersionException
	 *             If this WSDL version is not supported.
	 * 
	 * @see #WSDL11_SCHEMA_LOCATION
	 * @see #WSDL20_SCHEMA_LOCATION
	 */
	public String getSchemaLocation() throws WSDLVersionException {
		switch (this) {
		case WSDL11:
			return WSDL11_SCHEMA_LOCATION;
		case WSDL20:
			return WSDL20_SCHEMA_LOCATION;
		default:
			throw new WSDLVersionException(this);
		}
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The namespace URI <a
	 * href="http://www.w3.org/TR/wsdl#_notational">defined</a> for WSDL 1.1.
	 */
	public static final String WSDL11_NAMESPACE_URI = "http://schemas.xmlsoap.org/wsdl/";

	/**
	 * The location of the XML schema for WSDL 1.1.
	 */
	public static final String WSDL11_SCHEMA_LOCATION = "http://schemas.xmlsoap.org/wsdl/";

	/**
	 * The namespace URI <a href=http://www.w3.org/TR/2007/REC-wsdl20-20070626
	 * /#nsprefixes>defined</a> for WSDL 2.0.
	 */
	public static final String WSDL20_NAMESPACE_URI = "http://www.w3.org/ns/wsdl";

	/**
	 * The location of the XML schema for WSDL 2.0.
	 */
	public static final String WSDL20_SCHEMA_LOCATION = "http://www.w3.org/2007/06/wsdl/wsdl20.xsd";

	//
	// EXCEPTIONS
	//

	public static class WSDLVersionException extends Exception {
		private static final long serialVersionUID = 1L;

		WSDLVersionException(final WSDLVersion version) {
			super("I do not support the WSDL version \"" + version + "\".");
		}
	}
}