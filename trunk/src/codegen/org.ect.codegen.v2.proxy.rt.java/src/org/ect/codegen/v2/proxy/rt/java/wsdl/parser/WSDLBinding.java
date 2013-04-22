package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLBindingOperation.WSDLBindingOperationException;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLBindingType.WSDLBindingTypeException;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLVersion.WSDLVersionException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class WSDLBinding extends AbstractWSDLTopLevelComponent {

	//
	// FIELDS
	//

	/**
	 * Gets the type of this <code>binding</code> component.
	 */
	private WSDLBindingType type;

	/**
	 * The <code>binding operation</code> components of this
	 * <code>binding</code> component.
	 */
	private final WSDLComponents<WSDLBindingOperation> bindingOperations;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a <code>binding</code> component belonging to the
	 * <code>description</code> component <code>description</code> based on the
	 * node <code>node</code>.
	 * 
	 * @param description
	 *            The <code>description</code> component. Not <code>null</code>.
	 * @param bindingNode
	 *            The node. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasAttributeNamed(node, "name")</code> or
	 *             <code>!isBindingNode(bindingNode, super.getVersion())</code>.
	 * @throws NullPointerException
	 *             If <code>description==null</code> or <code>node==null</code>.
	 * @throws WSDLBindingException
	 *             If something goes wrong while constructing.
	 * 
	 * @see #isBindingNode(Node, WSDLVersion)
	 * @see AbstractWSDLTopLevelComponent#hasAttributeNamed(Node, String)
	 * @see AbstractWSDLTopLevelComponent#getVersion()
	 */
	WSDLBinding(final WSDLDescription description, final Node bindingNode)
			throws WSDLBindingException {

		super(description, bindingNode);

		try {
			if (!isBindingNode(bindingNode, super.getVersion()))
				throw new IllegalArgumentException();
		} catch (final WSDLVersionException e) {
			throw new WSDLBindingException(e);
		}

		this.bindingOperations = new WSDLComponents<WSDLBindingOperation>(
				description);

		/* Extract the type of this binding. */
		try {
			type = WSDLBindingType.extractBindingTypeFrom(bindingNode,
					description);
		} catch (final WSDLBindingTypeException e) {
			throw new WSDLBindingException(e);
		} catch (final WSDLVersionException e) {
			throw new WSDLBindingException(e);
		}

		/* Extract the operations of this binding. */
		final NodeList children = bindingNode.getChildNodes();
		if (children == null)
			return;

		for (int i = 0; i < children.getLength(); i++) {
			final Node child = children.item(i);
			if (child == null)
				continue;

			try {
				if (WSDLBindingOperation.isBindingOperationNode(child,
						super.getVersion()))
					bindingOperations
							.add(new WSDLBindingOperation(this, child));
			} catch (WSDLVersionException e) {
				continue;
			} catch (WSDLBindingOperationException e) {
				continue;
			}
		}
	}

	//
	// METHODS
	//

	/**
	 * Gets the <code>binding operation</code> components of this
	 * <code>binding</code> component.
	 * 
	 * @return A collection. Never <code>null</code>.
	 */
	public WSDLComponents<WSDLBindingOperation> getBindingOperations() {
		return bindingOperations;
	}

	/**
	 * Gets the type of this <code>binding</code> component.
	 * 
	 * @return A type. Never <code>null</code>.
	 */
	public WSDLBindingType getType() {
		return type;
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Checks if the node <code>node</code> describes a <code>binding</code>
	 * component under the WSDL version <code>version</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @param version
	 *            The WSDL version. Not <code>null</code>.
	 * @return <code>true</code> if <code>node</code> describes a
	 *         <code>binding</code> component; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or <code>version==null</code>..
	 * @throws WSDLVersionException
	 *             If <code>version</code> is not supported.
	 */
	public static boolean isBindingNode(final Node node,
			final WSDLVersion version) throws WSDLVersionException {

		if (node == null || version == null)
			throw new NullPointerException();

		switch (version) {
		case WSDL11:
		case WSDL20:
			return "binding".equals(node.getLocalName());
		default:
			throw new WSDLVersionException(version);
		}
	}

	//
	// EXCEPTIONS
	//

	public class WSDLBindingException extends Exception {
		private static final long serialVersionUID = 1L;

		private WSDLBindingException() {
			super("I cannot extract the \"binding\" component named \""
					+ WSDLBinding.super.getName() + "\".");
		}

		private WSDLBindingException(final String cause) {
			super("I cannot extract the \"binding\" component named \""
					+ WSDLBinding.super.getName() + "\": " + cause);
		}

		private WSDLBindingException(final Throwable cause) {
			super("I cannot extract the \"binding\" component named \""
					+ WSDLBinding.super.getName() + "\".", cause);
		}
	}
}
