package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMUtil {

	//
	// CONSTRUCTORS
	//

	/**
	 * Hides the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private DOMUtil() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Gets the attribute named <code>attributeName</code> of the node
	 * <code>node</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @param attributeName
	 *            The name. Not <code>null</code>.
	 * @return A node. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasAttributeNamed(node,attributeName)</code>.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or
	 *             <code>attributeName==null</code>.
	 * 
	 * @see #hasAttributeNamed(Node, String)
	 */
	public static Node getAttributeNamed(final Node node,
			final String attributeName) {

		if (node == null || attributeName == null)
			throw new NullPointerException();
		if (!hasAttributeNamed(node, attributeName))
			throw new IllegalArgumentException();

		return node.getAttributes().getNamedItem(attributeName);
	}

	/**
	 * Gets the child named <code>childName</code> of the node <code>node</code>
	 * .
	 * 
	 * @param node
	 *            The node. Not <code>null</code>
	 * @param childName
	 *            The name. Not <code>null</code>.
	 * @return A node. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasNamedChildNamed(node,childName)</code>.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or <code>childName==null</code>.
	 * 
	 * @see #hasChildNamed(Node, String)
	 */
	public static Node getChildNamed(final Node node, final String childName) {

		if (node == null || childName == null)
			throw new NullPointerException();
		if (!hasChildNamed(node, childName))
			throw new IllegalArgumentException();

		/* Get the child named $childName. */
		final NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			final Node child = children.item(i);
			if (child != null && childName.equals(child.getLocalName()))
				return child;
		}

		throw new IllegalArgumentException();
	}

	/**
	 * Checks if the node <code>node</code> has an attribute named
	 * <code>attributeName</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @param attributeName
	 *            The name. Not <code>null</code>.
	 * @return <code>true</code> if <code>node</code> has an attribute named
	 *         <code>attributeName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or
	 *             <code>attributeName==null</code>.
	 */
	public static boolean hasAttributeNamed(final Node node,
			final String attributeName) {

		if (node == null || attributeName == null)
			throw new NullPointerException();

		/* Get attributes. */
		final NamedNodeMap attributes = node.getAttributes();
		if (attributes == null)
			return false;

		/* Get the attribute named $attributeName. */
		final Node item = attributes.getNamedItem(attributeName);
		if (item == null)
			return false;

		return item.getNodeValue() != null;
	}

	/**
	 * Checks if the node <code>node</code> has a child named
	 * <code>childName</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>
	 * @param childName
	 *            The name. Not <code>null</code>.
	 * @return <code>true</code> if the node <code>node</code> has a child named
	 *         <code>childName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>node==null</code> or <code>childName==null</code>.
	 */
	public static boolean hasChildNamed(final Node node, final String childName) {

		if (node == null || childName == null)
			throw new NullPointerException();

		/* Get children. */
		final NodeList children = node.getChildNodes();
		if (children == null)
			return false;

		/* Get the child named $childName. */
		for (int i = 0; i < children.getLength(); i++) {
			final Node child = children.item(i);
			if (child != null && childName.equals(child.getLocalName()))
				return true;
		}

		return false;
	}
}
