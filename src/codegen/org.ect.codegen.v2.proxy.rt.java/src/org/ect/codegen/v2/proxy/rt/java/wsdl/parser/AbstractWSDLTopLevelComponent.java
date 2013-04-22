package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import javax.xml.namespace.QName;

import org.w3c.dom.Node;

public class AbstractWSDLTopLevelComponent {

	//
	// FIELDS
	//

	/**
	 * The qualified name of this top-level component.
	 */
	private final QName qName;

	/**
	 * The <code>description</code> component to which this top-level component
	 * belongs.
	 */
	private final WSDLDescription description;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a top-level component belonging to the
	 * <code>description</code> component <code>description</code> based on the
	 * node <code>node</code>.
	 * 
	 * @param description
	 *            The <code>description</code> component. Not <code>null</code>.
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasAttributeNamed(node,"name")</code> and
	 *             <code>!hasAttributeNamed(node,"ref")</code>.
	 * @throws NullPointerException
	 *             If <code>description==null</code> or <code>node==null</code>.
	 * 
	 * @see #hasAttributeNamed(Node, String)
	 */
	AbstractWSDLTopLevelComponent(final WSDLDescription description,
			final Node node) {

		if (description == null || node == null)
			throw new NullPointerException();
		if (!DOMUtil.hasAttributeNamed(node, "name")
				&& !DOMUtil.hasAttributeNamed(node, "ref"))
			throw new IllegalArgumentException();

		this.description = description;

		/* Extract a qualified name. */
		Node nameAttribute;
		try {
			nameAttribute = DOMUtil.getAttributeNamed(node, "name");
		} catch (final Exception e) {
			nameAttribute = DOMUtil.getAttributeNamed(node, "ref");
		}
		final String unprefixedName = nameAttribute.getNodeValue();
		this.qName = description.convertToQName(unprefixedName);
	}

	//
	// METHODS
	//

	/**
	 * Gets the <code>description</code> component to which this top-level
	 * component belongs.
	 * 
	 * @return A <code>description</code> component. Never <code>null</code>.
	 */
	public WSDLDescription getDescription() {
		return description;
	}

	/**
	 * Gets the (non-colonized) name of this top-level component. A shorthand
	 * for <code>getQName().getLocalPart()</code>.
	 * 
	 * @return A string. Never <code>null</code>.
	 * 
	 * @see #getQName()
	 * @see QName#getLocalPart()
	 */
	public String getName() {
		return getQName().getLocalPart();
	}

	/**
	 * Gets the qualified name of this top-level component.
	 * 
	 * @return A qualified name. Never <code>null</code>.
	 */
	public QName getQName() {
		return qName;
	}

	/**
	 * Gets the version of the <code>description</code> component to which this
	 * top-level component belongs. Shorthand for
	 * <code>getDescription().getVersion()</code>.
	 * 
	 * @return A version. Never <code>null</code>.
	 * 
	 * @see #getDescription()
	 * @see WSDLDescription#getVersion()
	 */
	public WSDLVersion getVersion() {
		return getDescription().getVersion();
	}
}
