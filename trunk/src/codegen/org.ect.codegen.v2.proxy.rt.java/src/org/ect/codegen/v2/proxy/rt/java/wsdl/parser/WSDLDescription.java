package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLBinding.WSDLBindingException;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLResource.WSDLResourceException;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLVersion.WSDLVersionException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class WSDLDescription {

	//
	// FIELDS
	//

	/**
	 * The node from which this <code>description</code> component is extracted.
	 */
	private Node descriptionNode;

	/**
	 * A map from the namespace URIs defined in this <code>description</code>
	 * component to their prefixes.
	 */
	private final Map<String, String> namespaceURIsToPrefixes = new HashMap<String, String>();

	/**
	 * A map from their prefixes to the namespace URIs defined in this
	 * <code>description</code> component.
	 */
	private final Map<String, String> prefixesToNamespaceURIs = new HashMap<String, String>();

	/**
	 * A map from their qualified names to the <code>binding</code> components
	 * of this <code>description</code> component.
	 */
	private final Map<QName, WSDLBinding> qBindingNamesToBindings = new HashMap<QName, WSDLBinding>();

	/**
	 * A map from their qualified names to the <code>service</code> components
	 * of this <code>description</code> component.
	 */
	private final Map<QName, WSDLService> qServiceNamesToServices = new HashMap<QName, WSDLService>();

	/**
	 * The WSDL resource describing this <code>description</code> component.
	 */
	private final WSDLResource resource;

	/**
	 * The target namespace of this <code>description</code> component.
	 */
	private String targetNamespace;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a <code>description</code> component for the WSDL resource
	 * <code>resource</code>.
	 * 
	 * @param resource
	 *            The resource. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>resource==null</code>.
	 * @throws WSDLDescriptionException
	 *             If something goes wrong while constructing.
	 */
	public WSDLDescription(final WSDLResource resource)
			throws WSDLDescriptionException {

		if (resource == null)
			throw new NullPointerException();

		this.resource = resource;

		/* Get children. */
		final NodeList children = resource.getDocument().getChildNodes();
		if (children == null || children.getLength() == 0)
			throw new WSDLDescriptionException();

		try {

			/* Extract the description component. */
			for (int i = 0; i < children.getLength(); i++) {
				final Node child = children.item(i);
				if (child == null || !isDescriptionNode(child, getVersion()))
					continue;

				/* Store $child. */
				this.descriptionNode = child;

				/* Extract namespaces. */
				extractNamespaces();

				/* Extract bindings. */
				extractBindings();

				/* Extract services. */
				extractServices();

				/* Return. */
				return;
			}
		} catch (WSDLVersionException e) {
			throw new WSDLDescriptionException(e);
		}
	}

	//
	// METHODS
	//

	/**
	 * Converts the name or the prefixed name
	 * <code>unprefixedOrPrefixedName</code> to a qualified name.
	 * 
	 * <p>
	 * If <code>unprefixedOrPrefixedName</code> contains no ':' characters, it
	 * references an unprefixed name. In that case, this method returns a
	 * qualified name with:
	 * <ul>
	 * <li>the target namespace of this description (if
	 * <code>hasTargetNamespaceURI()</code>) or
	 * <code>XMLConstants.NULL_NS_URI</code> (otherwise) as the namespace URI;
	 * <li><code>nameOrPrefixedName</code> as the local name;
	 * <li>the prefix of the target namespace of this description (if
	 * <code>hasTargetNamespacePrefix()</code>) or
	 * <code>XMLConstants.DEFAULT_NS_PREFIX</code> (otherwise) as the prefix.
	 * </ul>
	 * 
	 * <p>
	 * If <code>unprefixedOrPrefixedName</code> contains a ':' character, it
	 * references a prefixed name. In that case, this method returns a qualified
	 * name with:
	 * <ul>
	 * <li>the namespace URI of the prefix (if existent) or
	 * <code>XMLConstants.NULL_NS_URI</code> (otherwise) as the namespace URI.
	 * <li>the substring after the first occurrence of ':' as the local name.
	 * <li>the substring before the first occurrence of ':' as the prefix.
	 * </ul>
	 * 
	 * @param unprefixedOrPrefixedName
	 *            The unprefixed name or the prefixed name. Not
	 *            <code>null</code> or empty.
	 * @return A qualified name. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>nameOrPrefixedName==null</code>.
	 * 
	 * @see #hasTargetNamespace()
	 * @see #hasTargetNamespacePrefix()
	 * @see XMLConstants#DEFAULT_NS_PREFIX
	 * @see XMLConstants#NULL_NS_URI;
	 */
	public QName convertToQName(final String unprefixedOrPrefixedName) {
		if (unprefixedOrPrefixedName == null)
			throw new NullPointerException();

		final String prefix, localName, namespaceURI;

		/* Get the qualified name. */
		final int colonIndex = unprefixedOrPrefixedName.indexOf(':');
		if (colonIndex >= 0) {
			prefix = unprefixedOrPrefixedName.substring(0, colonIndex);
			namespaceURI = hasPrefix(prefix) ? getNamespaceURIOf(prefix)
					: XMLConstants.NULL_NS_URI;
			localName = unprefixedOrPrefixedName.substring(colonIndex + 1);
		} else {
			namespaceURI = hasTargetNamespace() ? getTargetNamespace()
					: XMLConstants.NULL_NS_URI;
			localName = unprefixedOrPrefixedName;
			prefix = hasTargetNamespacePrefix() ? getTargetNamespacePrefix()
					: XMLConstants.DEFAULT_NS_PREFIX;
		}

		/* Return. */
		return new QName(namespaceURI, localName, prefix);
	}

	/**
	 * Gets the <code>binding</code> component with the (non-colonized) name
	 * <code>bindingName</code> of this <code>description</code> component.
	 * Shorthand for <code>getBinding(qBindingName)</code> where
	 * <code>qBindingName.equals(convertToQName(bindingName))</code>.
	 * 
	 * @param bindingName
	 *            The (non-colonized) name. Not <code>null</code>.
	 * @return A <code>binding</code> component. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasBinding(qBindingName)</code>.
	 * @throws NullPointerException
	 *             If <code>bindingName==null</code>.
	 * 
	 * @see #convertToQName(String)
	 * @see #getBinding(QName)
	 * @see #hasBinding(QName)
	 */
	public WSDLBinding getBinding(final String bindingName) {
		if (bindingName == null)
			throw new NullPointerException();

		final QName qBindingName = convertToQName(bindingName);
		return getBinding(qBindingName);
	}

	/**
	 * Gets the <code>binding</code> component with the qualified name
	 * <code>qBindingName</code> of this <code>description</code> component.
	 * 
	 * @param qBindingName
	 *            The qualified name. Never <code>null</code>.
	 * @return A <code>binding</code> component. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasBinding(qBindingName)</code>.
	 * @throws NullPointerException
	 *             If <code>qBindingName==null</code>.
	 * 
	 * @see #hasBinding(QName)
	 */
	public WSDLBinding getBinding(final QName qBindingName) {
		if (qBindingName == null)
			throw new NullPointerException();
		if (!hasBinding(qBindingName))
			throw new IllegalArgumentException();

		return qBindingNamesToBindings.get(qBindingName);
	}

	/**
	 * Gets the <code>binding</code> components of this <code>description</code>
	 * component.
	 * 
	 * @return A collection. Never <code>null</code>.
	 */
	public Collection<WSDLBinding> getBindings() {
		return qBindingNamesToBindings.values();
	}

	/**
	 * Gets the namespace URI associated with the prefix <code>prefix</code> as
	 * defined in this <code>description</code> component.
	 * 
	 * @param prefix
	 *            The prefix. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasPrefix(prefix)</code>.
	 * @throws NullPointerException
	 *             If <code>prefix==null</code>.
	 * 
	 * @see #hasPrefix(String)
	 */
	public String getNamespaceURIOf(final String prefix) {
		if (prefix == null)
			throw new NullPointerException();
		if (!hasPrefix(prefix))
			throw new IllegalArgumentException();

		return prefixesToNamespaceURIs.get(prefix);
	}

	/**
	 * Gets the prefix associated with the namespace URI
	 * <code>namespaceURI</code> as defined in this <code>description</code>
	 * component.
	 * 
	 * @param namespaceURI
	 *            The namespace URI. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasNamespaceURI(namespaceURI)</code>.
	 * @throws NullPointerException
	 *             If <code>namespaceURI==null</code>.
	 * 
	 * @see #hasNamespaceURI(String)
	 */
	public String getPrefixOf(final String namespaceURI) {
		if (namespaceURI == null)
			throw new NullPointerException();
		if (!hasNamespaceURI(namespaceURI))
			throw new IllegalArgumentException();

		return namespaceURIsToPrefixes.get(namespaceURI);
	}

	/**
	 * Gets the <code>service</code> component with the (non-colonized) name
	 * <code>serviceName</code> of this <code>description</code> component.
	 * Shorthand for <code>getService(qServiceName)</code> where
	 * <code>qServiceName.equals(convertToQName(serviceName))</code>.
	 * 
	 * @param serviceName
	 *            The (non-colonized) name. Not <code>null</code>.
	 * @return A <code>service</code> component. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasService(qServiceName)</code>.
	 * @throws NullPointerException
	 *             If <code>serviceName==null</code>.
	 * 
	 * @see #convertToQName(String)
	 * @see #getService(QName)
	 * @see #hasService(QName)
	 */
	public WSDLService getService(final String serviceName) {
		if (serviceName == null)
			throw new NullPointerException();

		final QName qServiceName = convertToQName(serviceName);
		return getService(qServiceName);
	}

	/**
	 * Gets the <code>service</code> component with the qualified name
	 * <code>qServiceName</code> of this <code>description</code> component.
	 * 
	 * @param qServiceName
	 *            The qualified name. Never <code>null</code>.
	 * @return A <code>service</code> component. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!hasService(qServiceName)</code>.
	 * @throws NullPointerException
	 *             If <code>qServiceName==null</code>.
	 * 
	 * @see #hasService(QName)
	 */
	public WSDLService getService(final QName qServiceName) {
		if (qServiceName == null)
			throw new NullPointerException();
		if (!hasService(qServiceName))
			throw new IllegalArgumentException();

		return qServiceNamesToServices.get(qServiceName);
	}

	/**
	 * Gets the <code>service</code> components of this <code>description</code>
	 * component.
	 * 
	 * @return A collection. Never <code>null</code>.
	 */
	public Collection<WSDLService> getServices() {
		return qServiceNamesToServices.values();
	}

	/**
	 * Gets the target namespace of this <code>description</code> component.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasTargetNamespace()</code>.
	 * 
	 * @see #hasTargetNamespace();
	 */
	public String getTargetNamespace() {
		if (!hasTargetNamespace())
			throw new IllegalStateException();

		return targetNamespace;
	}

	/**
	 * Gets the prefix associated with the target namespace of this
	 * <code>description</code> component.
	 * 
	 * @return A string. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasTargetNamespacePrefix()</code>.
	 * 
	 * @see #hasTargetNamespacePrefix();
	 */
	public String getTargetNamespacePrefix() {
		if (!hasTargetNamespacePrefix())
			throw new IllegalStateException();

		return getPrefixOf(targetNamespace);
	}

	/**
	 * Gets the version of this <code>description</code> component.
	 * 
	 * @return A version. Never <code>null</code>.
	 */
	public WSDLVersion getVersion() {
		return resource.getVersion();
	}

	/**
	 * Checks if this <code>description</code> component has a binding with the
	 * qualified name <code>qBindingName</code>.
	 * 
	 * @return <code>true</code> if this <code>description</code> component has
	 *         a binding with the qualified name <code>qBindingName</code>;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>qBindingName==null</code>.
	 */
	public boolean hasBinding(final QName qBindingName) {
		if (qBindingName == null)
			throw new NullPointerException();

		return qBindingNamesToBindings.containsKey(qBindingName);
	}

	/**
	 * Checks if this <code>description</code> component has a service with the
	 * qualified name <code>qServiceName</code>.
	 * 
	 * @return <code>true</code> if this <code>description</code> component has
	 *         a binding with the qualified name <code>qServiceName</code>;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>qServiceName==null</code>.
	 */
	public boolean hasService(final QName qServiceName) {
		if (qServiceName == null)
			throw new NullPointerException();

		return qServiceNamesToServices.containsKey(qServiceName);
	}

	/**
	 * Checks if this <code>description</code> component has a namespace URI
	 * <code>namespaceURI</code>.
	 * 
	 * @param namespaceURI
	 *            The namespace URI. Not <code>null</code>.
	 * @return <code>true</code> if this <code>description</code> component has
	 *         a namespace URI <code>namespaceURI</code>; <code>false</code>
	 *         otherwise.
	 * @throws NullPointerException
	 *             If <code>namespaceURI==null</code>.
	 */
	public boolean hasNamespaceURI(final String namespaceURI) {
		if (namespaceURI == null)
			throw new NullPointerException();

		return namespaceURIsToPrefixes.containsKey(namespaceURI);
	}

	/**
	 * Checks if this <code>description</code> component has a prefix
	 * <code>prefix</code>.
	 * 
	 * @param prefix
	 *            The prefix. Not <code>null</code>.
	 * @return <code>true</code> if this <code>description</code> component has
	 *         a prefix <code>prefix</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>prefix==null</code>.
	 */
	public boolean hasPrefix(final String prefix) {
		if (prefix == null)
			throw new NullPointerException();

		return prefixesToNamespaceURIs.containsKey(prefix);
	}

	/**
	 * Checks if this <code>description</code> component has a target namespace.
	 * 
	 * @return <code>true</code> if this <code>description</code> component has
	 *         a target namespace; <code>false</code> otherwise.
	 */
	public boolean hasTargetNamespace() {
		return targetNamespace != null;
	}

	/**
	 * Checks if this <code>description</code> component has a target namespace
	 * with a prefix.
	 * 
	 * @return <code>true</code> if this <code>description</code> component has
	 *         a target namespace with a prefix; <code>false</code> otherwise.
	 */
	public boolean hasTargetNamespacePrefix() {
		return hasTargetNamespace()
				&& namespaceURIsToPrefixes.containsKey(targetNamespace);
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Extracts the <code>binding</code> components from this
	 * <code>description</code> component.
	 * 
	 * @throws IllegalStateException
	 *             If <code>this.descriptionNode==null</code>.
	 * @throws WSDLDescriptionException
	 *             If something goes wrong while extracting.
	 * 
	 * @see #descriptionNode
	 */
	private void extractBindings() throws WSDLDescriptionException {
		if (descriptionNode == null)
			throw new IllegalStateException();

		try {

			/* Get children. */
			final NodeList children = descriptionNode.getChildNodes();
			if (children == null)
				return;

			/* Extract bindings. */
			for (int i = 0; i < children.getLength(); i++) {
				final Node child = children.item(i);
				if (child == null
						|| !WSDLBinding.isBindingNode(child, getVersion()))
					continue;

				/* Extract a binding. */
				try {
					final WSDLBinding binding = new WSDLBinding(this, child);
					qBindingNamesToBindings.put(binding.getQName(), binding);
				} catch (final WSDLBindingException e) {
					continue;
				}
			}
		} catch (final Exception e) {
			throw new WSDLDescriptionException(e);
		}
	}

	/**
	 * Extracts the namespaces from this <code>description</code> component.
	 * 
	 * @throws IllegalStateException
	 *             If <code>this.descriptionNode==null</code>.
	 * @throws WSDLDescriptionException
	 *             If something goes wrong while extracting.
	 * 
	 * @see #descriptionNode
	 */
	private void extractNamespaces() throws WSDLDescriptionException {
		if (descriptionNode == null)
			throw new IllegalStateException();

		try {

			/* Get children. */
			final NamedNodeMap attributes = descriptionNode.getAttributes();
			if (attributes == null)
				return;

			/* Extract namespaces. */
			for (int i = 0; i < attributes.getLength(); i++) {
				final Node attribute = attributes.item(i);
				if (attribute == null)
					continue;

				final String attributePrefix = attribute.getPrefix();
				final String attributeLocalName = attribute.getLocalName();

				/* Check the kind of $attribute. */
				boolean isXmlnsPrefix = false;
				boolean isTargetNamespace = false;
				if ((isXmlnsPrefix = "xmlns".equals(attributePrefix))
						|| (isTargetNamespace = "targetNamespace"
								.equals(attributeLocalName))) {

					final String namespaceURI = attribute.getTextContent();
					if (namespaceURI == null || namespaceURI.isEmpty())
						continue;

					/* Extract an XML namespace. */
					if (isXmlnsPrefix) {
						final String prefix = attributeLocalName;
						if (prefix == null)
							continue;

						this.prefixesToNamespaceURIs.put(prefix, namespaceURI);
						this.namespaceURIsToPrefixes.put(namespaceURI, prefix);
					}

					/* Extract the WSDL target namespace. */
					if (isTargetNamespace)
						this.targetNamespace = namespaceURI;
				}
			}
		} catch (final Exception e) {
			throw new WSDLDescriptionException(e);
		}
	}

	/**
	 * Extracts the <code>service</code> components from this
	 * <code>description</code> component.
	 * 
	 * @throws IllegalStateException
	 *             If <code>this.descriptionNode==null</code>.
	 * @throws WSDLDescriptionException
	 *             If something goes wrong while extracting.
	 * 
	 * @see #descriptionNode
	 */
	private void extractServices() throws WSDLDescriptionException {
		if (descriptionNode == null)
			throw new IllegalStateException();

		try {

			/* Get children. */
			final NodeList children = descriptionNode.getChildNodes();
			if (children == null)
				return;

			/* Extract services. */
			for (int i = 0; i < children.getLength(); i++) {
				final Node child = children.item(i);
				if (child == null
						|| !WSDLService.isServiceNode(child, getVersion()))
					continue;

				/* Extract a service. */
				final WSDLService service = new WSDLService(this, child);
				qServiceNamesToServices.put(service.getQName(), service);
			}
		} catch (final Exception e) {
			throw new WSDLDescriptionException(e);
		}
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Checks if the node <code>node</code> describes a <code>description</code>
	 * component under the WSDL version <code>version</code>.
	 * 
	 * @param node
	 *            The node. Not <code>null</code>.
	 * @return <code>true</code> if <code>node</code> describes a
	 *         <code>description</code> component; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>node==null</code>.
	 * @throws WSDLVersionException
	 *             If <code>version</code> is not supported.
	 */
	public static boolean isDescriptionNode(final Node node,
			final WSDLVersion version) throws WSDLVersionException {

		if (node == null)
			throw new NullPointerException();

		if (node.getLocalName() == null)
			return false;

		switch (version) {
		case WSDL11:
			return node.getLocalName().equals("definitions");
		case WSDL20:
			return node.getLocalName().equals("description");
		default:
			throw new WSDLVersionException(version);
		}
	}

	/**
	 * Constructs a <code>description</code> component for the WSDL resource
	 * represented by its textual representation <code>wsdlResourceText</code>:
	 * a local location, a remote location, or raw data.
	 * 
	 * @param wsdlResourceText
	 *            The textual representation. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>wsdlResourceText==null</code>.
	 * @throws WSDLDescriptionException
	 *             If something goes wrong while constructing.
	 * @throws WSDLResourceException
	 *             If something goes wrong before or while parsing or validating
	 *             the WSDL resource represented by
	 *             <code>wsdlResourceText</code>.
	 */
	public static WSDLDescription newInstance(final String wsdlResourceText)
			throws WSDLResourceException, WSDLDescriptionException {

		if (wsdlResourceText == null)
			throw new NullPointerException();

		return new WSDLDescription(new WSDLResource(wsdlResourceText));
	}

	//
	// EXCEPTIONS
	//

	public class WSDLDescriptionException extends Exception {
		private static final long serialVersionUID = 1L;

		private WSDLDescriptionException() {
			super("I cannot extract a \"description\" component.");
		}

		private WSDLDescriptionException(final String cause) {
			super("I cannot extract a \"description\" component: " + cause);
		}

		private WSDLDescriptionException(final Throwable cause) {
			super("I cannot extract a \"description\" component.", cause);
		}

	}
}
