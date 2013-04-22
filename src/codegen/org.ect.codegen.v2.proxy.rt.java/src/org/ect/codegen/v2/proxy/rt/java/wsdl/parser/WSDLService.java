package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLEndpoint.WSDLEndpointException;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLVersion.WSDLVersionException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class WSDLService extends AbstractWSDLTopLevelComponent {

	//
	// FIELDS
	//

	/**
	 * The <code>endpoint</code> components of this <code>service</code>
	 * component.
	 */
	private final WSDLComponents<WSDLEndpoint> endpoints;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a <code>service</code> component belonging to the
	 * <code>description</code> component <code>description</code> based on the
	 * node <code>node</code>.
	 * 
	 * @param description
	 *            The <code>description</code> component. Not <code>null</code>.
	 * @param serviceNode
	 *            The node.
	 * @throws IllegalArgumentException
	 *             If <code>!hasAttributeNamed(serviceNode,"name")</code> or
	 *             <code>!isServiceNode(serviceNode,super.getVersion())</code>.
	 * @throws NullPointerException
	 *             If <code>description==null</code> or <code>node==null</code>.
	 * @throws WSDLServiceException
	 *             If something goes wrong while constructing.
	 * 
	 * @see #isServiceNode(Node, WSDLVersion)
	 * @see AbstractWSDLTopLevelComponent#hasAttributeNamed(Node, String)
	 * @see AbstractWSDLTopLevelComponent#getVersion()
	 */
	WSDLService(final WSDLDescription description, final Node serviceNode)
			throws WSDLServiceException {

		super(description, serviceNode);

		try {
			if (!isServiceNode(serviceNode, super.getVersion()))
				throw new IllegalArgumentException();
		} catch (final WSDLVersionException e) {
			throw new WSDLServiceException(e);
		}

		this.endpoints = new WSDLComponents<WSDLEndpoint>(description);

		/* Get children. */
		final NodeList children = serviceNode.getChildNodes();
		if (children == null || children.getLength() == 0)
			throw new WSDLServiceException();

		/* Iterate children. */
		for (int i = 0; i < children.getLength(); i++) {
			final Node child = children.item(i);
			if (child == null)
				continue;

			/* Extract an endpoint. */
			try {
				if (WSDLEndpoint.isEndpointNode(child, super.getVersion()))
					endpoints.add(new WSDLEndpoint(this, child));

			} catch (WSDLEndpointException e) {
				continue;
			} catch (WSDLVersionException e) {
				continue;
			}
		}
	}

	//
	// METHODS
	//

	/**
	 * Gets the <code>endpoint</code> components of this <code>service</code>
	 * component.
	 * 
	 * @return A collection. Never <code>null</code>.
	 */
	public WSDLComponents<WSDLEndpoint> getEndpoints() {
		return endpoints;
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Checks if the node <code>node</code> describes a <code>service</code>
	 * component under the WSDL version <code>version</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @param version
	 *            The WSDL version. Not <code>null</code>.
	 * @return <code>true</code> if <code>node</code> describes a
	 *         <code>service</code> component; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or <code>version==null</code>..
	 * @throws WSDLVersionException
	 *             If <code>version</code> is not supported.
	 */
	public static boolean isServiceNode(final Node node,
			final WSDLVersion version) throws WSDLVersionException {

		if (node == null || version == null)
			throw new NullPointerException();

		switch (version) {
		case WSDL11:
		case WSDL20:
			return "service".equals(node.getLocalName());
		default:
			throw new WSDLVersionException(version);
		}
	}

	//
	// EXCEPTIONS
	//

	public class WSDLServiceException extends Exception {
		private static final long serialVersionUID = 1L;

		public WSDLServiceException() {
			super("I cannot extract the \"service\" component named \""
					+ WSDLService.this.getName() + "\".");
		}

		public WSDLServiceException(final String cause) {
			super("I cannot extract the \"service\" component named \""
					+ WSDLService.this.getName() + "\": " + cause);
		}

		public WSDLServiceException(final Throwable cause) {
			super("I cannot extract the \"service\" component named \""
					+ WSDLService.this.getName() + "\".", cause);
		}
	}
}
