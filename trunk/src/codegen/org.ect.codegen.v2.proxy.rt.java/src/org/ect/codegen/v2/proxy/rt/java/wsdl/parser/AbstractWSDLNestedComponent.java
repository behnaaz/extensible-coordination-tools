package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import org.w3c.dom.Node;

public abstract class AbstractWSDLNestedComponent<T extends AbstractWSDLTopLevelComponent>
		extends AbstractWSDLTopLevelComponent {

	//
	// FIELDS
	//

	/**
	 * The parent component of this nested component.
	 */
	private final T parent;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a nested component belonging to the parent component
	 * <code>parent</code> based on the node <code>node</code>.
	 * 
	 * @param parent
	 *            The parent component. Not <code>null</code>.
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasAttributeNamed(node, "name")</code>.
	 * @throws NullPointerException
	 *             If <code>parent==null</code> or <code>node==null</code>.
	 * 
	 * @see AbstractWSDLTopLevelComponent#hasAttributeNamed(Node, String)
	 */
	public AbstractWSDLNestedComponent(final T parent, final Node node) {

		super(parent.getDescription(), node);
		this.parent = parent;
	}

	//
	// METHODS
	//

	/**
	 * Gets the parent component of this nested component.
	 * 
	 * @return A component. Never <code>null</code>.
	 */
	public T getParent() {
		return parent;
	}
}
