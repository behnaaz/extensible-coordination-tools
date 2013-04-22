package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import javax.xml.namespace.QName;

import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLVersion.WSDLVersionException;
import org.w3c.dom.Node;


public class WSDLEndpoint extends AbstractWSDLNestedComponent<WSDLService> {

	//
	// FIELDS
	//

	/**
	 * The address of this <code>endpoint</code> component.
	 */
	private String address;

	/**
	 * The <code>binding</code> component of this <code>endpoint</code>
	 * component.
	 */
	private WSDLBinding binding;

	/**
	 * The node from which this <code>endpoint</code> component is extracted.
	 */
	private final Node endpointNode;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an <code>endpoint</code> component belonging to the
	 * <code>service</code> component <code>service</code> based on the node
	 * <code>node</code>.
	 * 
	 * @param service
	 *            The <code>service</code> component. Not <code>null</code>.
	 * @param endpointNode
	 *            The node. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasAttributeNamed(serviceNode,"name")</code> or
	 *             <code>!isEndpointNode(serviceNode,super.getVersion())</code>.
	 * @throws NullPointerException
	 *             If <code>description==null</code> or <code>node==null</code>.
	 * @throws WSDLEndpointException
	 *             If something goes wrong while constructing an endpoint.
	 * 
	 * @see #isEndpointNode(Node, WSDLVersion)
	 * @see AbstractWSDLTopLevelComponent#hasAttributeNamed(Node, String)
	 * @see AbstractWSDLTopLevelComponent#getVersion()
	 */
	WSDLEndpoint(final WSDLService service, final Node endpointNode)
			throws WSDLEndpointException {

		super(service, endpointNode);

		try {
			if (!isEndpointNode(endpointNode, super.getVersion()))
				throw new IllegalArgumentException();
		} catch (WSDLVersionException e) {
			throw new WSDLEndpointException(e);
		}

		this.endpointNode = endpointNode;

		/* Extract the binding. */
		extractBinding();

		/* Extract the address. */
		try {
			extractAddress();
		} catch (final WSDLVersionException e) {
			throw new WSDLEndpointException(e);
		}
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the <code>binding</code> component of this <code>endpoint</code>
	 * component.
	 * 
	 * @return A <code>binding</code> component. Never <code>null</code>.
	 */
	public WSDLBinding getBinding() {
		return binding;
	}

	/**
	 * Gets the address of this <code>endpoint</code> component.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getAddress() {
		return address;
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Extracts the address of this <code>endpoint</code> component from the
	 * node <code>this.endpointNode</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>this.endpointNode==null</code>.
	 * @throws WSDLEndpointException
	 *             If something goes wrong while extracting.
	 * @throws WSDLVersionException
	 *             If something goes wrong while extracting.
	 * 
	 * @see #endpointNode
	 */
	private void extractAddress() throws WSDLEndpointException,
			WSDLVersionException {

		if (this.endpointNode == null)
			throw new IllegalStateException();

		switch (super.getVersion()) {
		case WSDL11:

			/* Get the child named "address". */
			if (!DOMUtil.hasChildNamed(this.endpointNode, "address"))
				throw new WSDLEndpointException("I cannot get an address.");

			final Node addressChild = DOMUtil.getChildNamed(this.endpointNode,
					"address");

			/* Get the attribute named "location". */
			if (!DOMUtil.hasAttributeNamed(addressChild, "location"))
				throw new WSDLEndpointException("I cannot get an address.");

			final Node locationAttribute = DOMUtil.getAttributeNamed(
					addressChild, "location");

			/* Extract the address. */
			this.address = locationAttribute.getTextContent();
			return;
		case WSDL20:

			/* Get the attribute named "address". */
			if (!DOMUtil.hasAttributeNamed(this.endpointNode, "address"))
				throw new WSDLEndpointException("I cannot get an address.");

			final Node addressAttribute = DOMUtil.getAttributeNamed(
					this.endpointNode, "address");

			/* Extract the address. */
			this.address = addressAttribute.getTextContent();
			return;
		default:
			throw new WSDLVersionException(super.getVersion());
		}
	}

	/**
	 * Extracts the <code>binding</code> component of this <code>endpoint</code>
	 * component from the node <code>this.endpointNode</code>.
	 * 
	 * @throws IllegalStateException
	 *             If <code>this.endpointNode==null</code>.
	 * @throws WSDLEndpointException
	 *             If something goes wrong while extracting.
	 * 
	 * @see #endpointNode
	 */
	private void extractBinding() throws WSDLEndpointException {
		if (this.endpointNode == null)
			throw new IllegalStateException();

		/* Get the attribute named "binding". */
		if (!DOMUtil.hasAttributeNamed(this.endpointNode, "binding"))
			throw new WSDLEndpointException(
					"I cannot get a reference to a binding.");

		final Node bindingAttribute = DOMUtil.getAttributeNamed(
				this.endpointNode, "binding");

		/* Extract the unprefixed name of the binding. */
		final String unprefixedName = bindingAttribute.getTextContent();
		if (unprefixedName == null || unprefixedName.isEmpty())
			throw new WSDLEndpointException(
					"I cannot get a reference to a binding.");

		/* Get the "binding" component. */
		final WSDLDescription description = super.getDescription();
		final QName qBindingName = description.convertToQName(unprefixedName);
		if (!description.hasBinding(qBindingName))
			throw new WSDLEndpointException("I cannot resolve the reference \""
					+ unprefixedName + "\" to a binding.");

		this.binding = description.getBinding(qBindingName);
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Checks if the node <code>node</code> describes an <code>endpoint</code>
	 * component under the WSDL version <code>version</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @param version
	 *            The WSDL version. Not <code>null</code>.
	 * @return <code>true</code> if <code>node</code> describes a
	 *         <code>endpoint</code> component; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or <code>version==null</code>..
	 * @throws WSDLVersionException
	 *             If <code>version</code> is not supported.
	 */
	public static boolean isEndpointNode(final Node node,
			final WSDLVersion version) throws WSDLVersionException {

		if (node == null)
			throw new NullPointerException();

		switch (version) {
		case WSDL11:
			return "port".equals(node.getLocalName());
		case WSDL20:
			return "endpoint".equals(node.getLocalName());
		default:
			throw new WSDLVersionException(version);
		}
	}

	//
	// EXCEPTIONS
	//

	public class WSDLEndpointException extends Exception {
		private static final long serialVersionUID = 1L;

		private WSDLEndpointException() {
			super("I cannot extract the endpoint named \""
					+ WSDLEndpoint.this.getName() + "\".");
		}

		private WSDLEndpointException(final String cause) {
			super("I cannot extract the endpoint named \""
					+ WSDLEndpoint.this.getName() + "\": " + cause);
		}

		private WSDLEndpointException(final Throwable cause) {
			super("I cannot extract the endpoint named \""
					+ WSDLEndpoint.this.getName() + "\".", cause);
		}
	}
}
