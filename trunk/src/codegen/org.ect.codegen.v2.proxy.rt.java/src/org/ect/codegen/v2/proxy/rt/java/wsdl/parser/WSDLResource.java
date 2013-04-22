package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.input.BOMInputStream;
import org.ect.codegen.v2.proxy.rt.java.InterfacesResource;
import org.ect.codegen.v2.proxy.rt.java.Resource;
import org.w3c.dom.Document;

public class WSDLResource extends InterfacesResource {

	//
	// FIELDS
	//

	/**
	 * The WSDL document represented by this resource.
	 */
	private final Document document;

	/**
	 * The version under which the WSDL document represented by this resource
	 * was successfully validated.
	 */
	private final WSDLVersion version;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a WSDL resource, parsed and validated, for its textual
	 * representation <code>wsdlResourceText</code>: a local location, a remote
	 * location, or raw data.
	 * 
	 * @param wsdlResourceText
	 *            The textual representation. Never <code>null</code>.
	 * @throws WSDLResourceException
	 *             If something goes wrong before or while parsing or
	 *             validating.
	 */
	public WSDLResource(final String wsdlResourceText)
			throws WSDLResourceException {

		super(wsdlResourceText);
		this.document = parse();
		this.version = validate();
	}

	//
	// METHODS
	//

	/**
	 * Gets the WSDL document, validated as version <code>getVersion()</code>,
	 * represented by this resource.
	 * 
	 * @return A document. Never <code>null</code>.
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Gets the version under which the WSDL document represented by this
	 * resource was successfully validated.
	 * 
	 * @return A version. Never <code>null</code>.
	 */
	public WSDLVersion getVersion() {
		return version;
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Parses this resource to an XML document.
	 * 
	 * @return A document. Never <code>null</code>.
	 * @throws WSDLResourceException
	 *             If something goes wrong before or while parsing.
	 */
	private Document parse() throws WSDLResourceException {

		/* Get a stream. */
		final InputStream stream = super.newInputStream();

		/* Get a parser. */
		final DocumentBuilder parser;
		try {
			final DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			builderFactory.setNamespaceAware(true);
			parser = builderFactory.newDocumentBuilder();
		} catch (final Exception e) {
			throw new WSDLResourceException("I cannot get an XML parser.");
		}

		/* Parse $stream. */
		try {
			return parser.parse(stream);
		} catch (Exception e) {
			throw new WSDLResourceException("the XML parser failed.");
		}
	}

	/**
	 * Validates the XML document <code>document</code> as a WSDL 1.1 or a WSDL
	 * 2.0 document.
	 * 
	 * @param document
	 *            The XML document. Not <code>null</code>.
	 * @return A version. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>this.document==null</code>.
	 * @throws WSDLResourceException
	 *             If something goes wrong before or while validating.
	 * 
	 * @see #document
	 */
	private WSDLVersion validate() throws WSDLResourceException {
		if (this.document == null)
			throw new IllegalStateException();

		/* Get a schema factory. */
		final SchemaFactory schemaFactory;
		try {
			schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		} catch (Exception e) {
			throw new WSDLResourceException(
					"I cannot get an XML Schema validator.");
		}

		/* Validate $document as WSDL 1.1 or as WSDL 2.0. */
		WSDLVersion version = null;
		for (final WSDLVersion v : WSDLVersion.values())
			try {

				/* Get the schema for version $v. */
				final Source source = new StreamSource(new BOMInputStream(
						new Resource(v.getSchemaLocation()).newInputStream()));

				final Schema schema = schemaFactory.newSchema(source);

				/* Validate. */
				schema.newValidator().validate(new DOMSource(this.document));

				/* Break after successful validation. */
				version = v;
				break;
			} catch (final Exception e) {
				continue;
			}

		/* Reflect on validation. */
		if (version == null)

			/* Guess version 2.0. */
			version = WSDLVersion.WSDL20;

		// throw new WSDLResourceException(
		// "both the WSDL 1.1 validator and the WSDL 2.0 validator failed.");

		return version;
	}

	//
	// EXCEPTIONS
	//

	public class WSDLResourceException extends Exception {
		private static final long serialVersionUID = 1L;

		private WSDLResourceException(final String cause) {
			super("I cannot extract a WSDL description from the resource \""
					+ WSDLResource.super.getResourceText() + "\": " + cause);
		}

		private WSDLResourceException(final Throwable cause) {
			super("I cannot extract a WSDL description from the resource \""
					+ WSDLResource.super.getResourceText() + "\".", cause);
		}
	}
}
