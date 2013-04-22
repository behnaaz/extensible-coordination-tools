package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLVersion.WSDLVersionException;
import org.w3c.dom.Node;


public class WSDLBindingOperation extends
		AbstractWSDLNestedComponent<WSDLBinding> {

	//
	// FIELDS
	//

	private String soapAction;

	private boolean soapIncludeNamespace;

	private final Node bindingOperationNode;

	//
	// CONSTRUCTORS
	//

	public WSDLBindingOperation(final WSDLBinding binding,
			final Node bindingOperationNode)
			throws WSDLBindingOperationException {

		super(binding, bindingOperationNode);

		this.bindingOperationNode = bindingOperationNode;

		try {
			if (!isBindingOperationNode(bindingOperationNode,
					super.getVersion()))
				throw new IllegalArgumentException();
		} catch (final WSDLVersionException e) {
			throw new WSDLBindingOperationException(e);
		}

		if (binding.getType().isSOAP())
			try {
				extractSOAPAction();
			} catch (final WSDLBindingOperationException e) {
			} catch (final WSDLVersionException e) {
			}

	}

	//
	// METHODS - PUBLIC
	//

	public String getSOAPAction() {
		if (!hasSOAPAction())
			throw new IllegalStateException();

		return soapAction;
	}

	public boolean getSOAPIncludeNamespace() {
		if (!hasSOAPAction())
			throw new IllegalStateException();

		return soapIncludeNamespace;
	}

	public boolean hasSOAPAction() {
		return soapAction != null;
	}

	//
	// METHODS - PRIVATE
	//

	private void extractSOAPAction() throws WSDLBindingOperationException,
			WSDLVersionException {

		if (this.bindingOperationNode == null)
			throw new IllegalStateException();

		final Node attribute;

		/* Instantiate $attribute. */
		final WSDLVersion version = super.getParent().getVersion();
		switch (version) {
		case WSDL11:
			if (!DOMUtil.hasChildNamed(this.bindingOperationNode, "operation"))
				throw new WSDLBindingOperationException(
						"I cannot extract a SOAP action.");

			final Node child = DOMUtil.getChildNamed(this.bindingOperationNode,
					"operation");

			if (!DOMUtil.hasAttributeNamed(child, "soapAction"))
				throw new WSDLBindingOperationException(
						"I cannot extract a SOAP action.");

			attribute = DOMUtil.getAttributeNamed(child, "soapAction");
			break;
		case WSDL20:
			if (!DOMUtil.hasAttributeNamed(this.bindingOperationNode, "action"))
				throw new WSDLBindingOperationException(
						"I cannot extract a SOAP action.");

			attribute = DOMUtil.getAttributeNamed(this.bindingOperationNode,
					"action");
			break;
		default:
			throw new WSDLVersionException(version);
		}

		/* Set. */
		this.soapAction = attribute.getTextContent();
		this.soapIncludeNamespace = this.bindingOperationNode.getPrefix() != null
				&& !this.bindingOperationNode.getPrefix().isEmpty();
	}

	//
	// STATIC
	//

	/**
	 * Checks if the node <code>node</code> describes a
	 * <code>binding operation</code> component under the WSDL version
	 * <code>version</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @param version
	 *            The WSDL version. Not <code>null</code>.
	 * @return <code>true</code> if <code>node</code> describes a
	 *         <code>binding operation</code> component; <code>false</code>
	 *         otherwise.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or <code>version==null</code>..
	 * @throws WSDLVersionException
	 *             If <code>version</code> is not supported.
	 */
	public static boolean isBindingOperationNode(final Node node,
			final WSDLVersion version) throws WSDLVersionException {

		switch (version) {
		case WSDL11:
		case WSDL20:
			return "operation".equals(node.getLocalName());
		default:
			throw new WSDLVersionException(version);
		}
	}

	//
	// EXCEPTIONS
	//

	public class WSDLBindingOperationException extends Exception {
		private static final long serialVersionUID = 1L;

		private WSDLBindingOperationException() {
			super(
					"I cannot extract the \"binding operation\" component named \""
							+ WSDLBindingOperation.super.getName() + "\".");
		}

		private WSDLBindingOperationException(final String cause) {
			super(
					"I cannot extract the \"binding operation\" component named \""
							+ WSDLBindingOperation.super.getName() + "\": "
							+ cause);
		}

		private WSDLBindingOperationException(final Throwable cause) {
			super(
					"I cannot extract the \"binding operation\" component named \""
							+ WSDLBindingOperation.super.getName() + "\".",
					cause);
		}
	}
}
