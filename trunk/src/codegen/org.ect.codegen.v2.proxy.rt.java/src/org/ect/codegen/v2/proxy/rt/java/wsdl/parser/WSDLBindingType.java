package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLVersion.WSDLVersionException;
import org.w3c.dom.Node;


public enum WSDLBindingType {
	SOAP11, SOAP12;

	//
	// METHODS
	//

	/**
	 * Checks if this type is <code>SOAP11</code> or <code>SOAP12</code>.
	 * 
	 * @return <code>true</code> if this type is <code>SOAP11</code> or
	 *         <code>SOAP12</code>; <code>false</code> otherwise.
	 */
	public boolean isSOAP() {
		switch (this) {
		case SOAP11:
		case SOAP12:
			return true;
		default:
			return false;
		}
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The namespace URI <a
	 * href="http://www.w3.org/TR/wsdl#_notational">defined</a> for the WSDL 1.1
	 * binding extension for SOAP 1.1.
	 */
	public static final String WSDL11_SOAP11_NAMESPACE_URI = "http://schemas.xmlsoap.org/wsdl/soap/";

	/**
	 * The namespace URI <a
	 * href="http://www.w3.org/Submission/wsdl11soap12/#namespaces">defined</a>
	 * for the WSDL 1.1 binding extension for SOAP 1.2.
	 */
	public static final String WSDL11_SOAP12_NAMESPACE_URI = "http://schemas.xmlsoap.org/wsdl/soap12/";

	/**
	 * The namespace URI <a href=
	 * "http://www.w3.org/TR/2007/REC-wsdl20-adjuncts-20070626/#soap-version-decl-xml"
	 * >defined</a> for the WSDL 2.0 binding extension for SOAP.
	 */
	public static final String WSDL20_SOAP_NAMESPACE_URI = "http://www.w3.org/ns/wsdl/soap";

	//
	// STATIC - METHODS
	//

	/**
	 * Extracts the type of the <code>binding</code> component described by the
	 * node <code>bindingNode</code>.
	 * 
	 * @param bindingNode
	 *            The node. Not <code>null</code>.
	 * @param description
	 *            The <code>description</code> component to which the
	 *            <code>binding</code> component belongs.
	 * @return A type. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>!WSDLBinding.isBindingNode(bindingNode,description.getVersion())</code>
	 *             .
	 * @throws NullPointerException
	 *             If <code>bindingNode==null</code> or
	 *             <code>description==null</code>.
	 * @throws WSDLBindingTypeException
	 *             If something goes wrong before or while extracting.
	 * @throws WSDLVersionException
	 *             If the WSDL version <code>description.getVersion()</code> is
	 *             not supported.
	 * 
	 * @see WSDLBinding#isBindingNode(Node, WSDLVersion)
	 * @see WSDLDescription#getVersion()
	 */
	public static WSDLBindingType extractBindingTypeFrom(
			final Node bindingNode, final WSDLDescription description)
			throws WSDLBindingTypeException, WSDLVersionException {

		if (bindingNode == null || description == null)
			throw new NullPointerException();

		try {
			if (!WSDLBinding.isBindingNode(bindingNode,
					description.getVersion()))
				throw new IllegalArgumentException();
		} catch (WSDLVersionException e) {
			throw new WSDLBindingTypeException("I cannot find a binding type.",
					e);
		}

		switch (description.getVersion()) {

		/* Extract from a WSDL 1.1 description. */
		case WSDL11: {

			if (!DOMUtil.hasChildNamed(bindingNode, "binding"))
				throw new WSDLBindingTypeException(
						"I cannot find a binding type.");

			final Node child = DOMUtil.getChildNamed(bindingNode, "binding");

			/* Extract attributes. */
			final String prefix = child.getPrefix();
			if (prefix != null && !description.hasPrefix(prefix))
				throw new WSDLBindingTypeException(
						"I cannot resolve the prefix \"" + prefix
								+ "\" to a binding type.");
			final String type = prefix == null ? null : description
					.getNamespaceURIOf(prefix);

			/* Return the type. */
			if (prefix == null || WSDL11_SOAP11_NAMESPACE_URI.equals(type))
				return SOAP11;
			else if (WSDL11_SOAP12_NAMESPACE_URI.equals(type))
				return SOAP12;
			else
				throw new WSDLBindingTypeException(
						"I do not support binding type \"" + type + "\".");
		}

		/* Extract from a WSDL 2.0 description. */
		case WSDL20: {

			/* Extract attributes. */
			String type = null;
			if (DOMUtil.hasAttributeNamed(bindingNode, "type"))
				type = DOMUtil.getAttributeNamed(bindingNode, "type")
						.getTextContent();

			String version = null;
			if (DOMUtil.hasAttributeNamed(bindingNode, "type"))
				version = DOMUtil.getAttributeNamed(bindingNode, "type")
						.getTextContent();

			/* Return the type. */
			if (WSDL20_SOAP_NAMESPACE_URI.equals(type) && "1.1".equals(version))
				return SOAP11;
			else if (WSDL20_SOAP_NAMESPACE_URI.equals(type)
					&& (version == null || "1.2".equals(version)))
				return SOAP12;
			else
				throw new WSDLBindingTypeException(
						"I do not support binding type \""
								+ type
								+ "\""
								+ (version == null ? "." : ", version \""
										+ version + "\"."));
		}

		/* Throw. */
		default:
			throw new WSDLVersionException(description.getVersion());
		}
	}

	/**
	 * Gets the default type of the WSDL version <code>version</code>.
	 * 
	 * @param version
	 *            The version. Not <code>null</code>.
	 * @return A type. Never <code>null</code>.
	 * @throws WSDLVersionException
	 *             If <code>version</code> is not supported.
	 */
	public static WSDLBindingType getDefaultOf(final WSDLVersion version)
			throws WSDLVersionException {

		if (version == null)
			throw new NullPointerException();

		switch (version) {
		case WSDL11:
			return SOAP11;
		case WSDL20:
			return SOAP12;
		default:
			throw new WSDLVersionException(version);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class WSDLBindingTypeException extends Exception {
		private static final long serialVersionUID = 1L;

		WSDLBindingTypeException(final String message) {
			super(message);
		}

		WSDLBindingTypeException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}

	public static class WSDLBindingTypeSwitchException extends
			WSDLBindingTypeException {
		private static final long serialVersionUID = 1L;

		WSDLBindingTypeSwitchException(final WSDLBindingType type) {
			super("I do not support binding type \" + type + \".");
		}
	}
}
