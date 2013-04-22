package org.ect.codegen.v2.proxy.rt.java.wsdl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.description.AxisEndpoint;
import org.apache.axis2.description.AxisMessage;
import org.apache.axis2.description.AxisOperation;
import org.apache.axis2.description.AxisService;
import org.apache.ws.commons.schema.XmlSchemaComplexType;
import org.apache.ws.commons.schema.XmlSchemaElement;
import org.apache.ws.commons.schema.XmlSchemaGroupBase;
import org.apache.ws.commons.schema.XmlSchemaObject;
import org.apache.ws.commons.schema.XmlSchemaObjectCollection;
import org.apache.ws.commons.schema.XmlSchemaParticle;
import org.apache.ws.commons.schema.XmlSchemaSimpleType;
import org.ect.codegen.v2.proxy.rt.java.NamedObject;
import org.ect.codegen.v2.proxy.rt.java.wsdl.WSDLDirection.DirectionException;

public class WSDLService extends NamedObject {

	//
	// FIELDS
	//

	/**
	 * The URL of the endpoint of this service.
	 */
	private final String endpointURL;

	/**
	 * The name of this service.
	 */
	private final String name;

	/**
	 * The prefix of the target namespace of the schema.
	 */
	private final String schemaTargetNamespacePrefix;

	/**
	 * The Axis2 representation of this service.
	 */
	private final AxisService service;

	/**
	 * The target namespace.
	 */
	private final String targetNamespace;

	/**
	 * The prefix of the target namespace.
	 */
	private final String targetNamespacePrefix;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a service representation based on the Axis2 service
	 * representation <code>service</code>.
	 * 
	 * @param service
	 *            The Axis2 service representation. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>service==null</code>.
	 */
	public WSDLService(final AxisService service) {
		super(getName(service));

		if (service == null)
			throw new NullPointerException();
		if (!hasEndpointURL(service) || !hasName(service)
				|| !hasTargetNamespace(service)
				|| !hasTargetNamespacePrefix(service))
			throw new IllegalArgumentException();

		this.endpointURL = getEndpointURL(service);
		this.name = getName(service);
		this.schemaTargetNamespacePrefix = getSchemaTargetNamespacePrefix(service);
		this.service = service;
		this.targetNamespace = getTargetNamespace(service);
		this.targetNamespacePrefix = getTargetNamespacePrefix(service);
	}

	//
	// METHODS
	//

	/**
	 * Creates a client for this service.
	 * 
	 * @return A client. Never <code>null</code>.
	 * @throws WSDLServiceException
	 *             If something goes wrong while creating a client.
	 */
	public ServiceClient createClient() throws WSDLServiceException {
		try {
			return new ServiceClient(null, service);
		} catch (AxisFault e) {
			throw new WSDLServiceException(
					"I cannot create a client for the service named \"" + name
							+ "\".", e);
		}
	}

	/**
	 * Gets the direction of the message named <code>messageName</code> involved
	 * in the operation named <code>operationName</code> of this service.
	 * 
	 * @param operationName
	 *            The name of the operation. Not <code>null</code> or empty.
	 * @param messageName
	 *            The name of the message. Not <code>null</code> or empty.
	 * @return A direction. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>operationName.isEmpty()</code> or
	 *             <code>!hasOperation(operationName)</code> or
	 *             <code>messageName.isEmpty()</code> or
	 *             <code>!hasMessage(operationName,messageName)</code> or
	 *             <code>!hasDirection(operationName,messageName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code> or
	 *             <code>messageName==null</code>.
	 * 
	 * @see #hasDirection(String, String)
	 * @see #hasMessage(String, String)
	 * @see #hasOperation(String)
	 * @see String#isEmpty()
	 */
	public WSDLDirection getDirection(final String operationName,
			final String messageName) throws DirectionException {

		if (operationName == null || messageName == null)
			throw new NullPointerException();
		if (!hasOperation(operationName)
				|| !hasMessage(operationName, messageName)
				|| !hasDirection(operationName, messageName))
			throw new IllegalArgumentException();

		final AxisMessage message = getMessage(operationName, messageName);
		return WSDLDirection.convertToDirection(message.getDirection());
	}

	/**
	 * Gets the endpoint URL of this service.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getEndpointURL() {
		return endpointURL;
	}

	/**
	 * Gets the message named <code>messageName</code> involved in the operation
	 * named <code>operationName</code> of this service
	 * 
	 * @param operationName
	 *            The name of the operation. Not <code>null</code> or empty.
	 * @param messageName
	 *            The name of the message. Not <code>null</code> or empty.
	 * @return A message. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>operationName.isEmpty()</code> or
	 *             <code>!hasOperation(operationName)</code> or
	 *             <code>messageName.isEmpty()</code> or
	 *             <code>!hasMessage(operationName,messageName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code> or
	 *             <code>messageName==null</code>.
	 * 
	 * @see #hasMessage(String, String)
	 * @see #hasOperation(String)
	 * @see String#isEmpty()
	 */
	public AxisMessage getMessage(final String operationName,
			final String messageName) {

		if (operationName == null || messageName == null)
			throw new NullPointerException();
		if (!hasMessage(operationName, messageName))
			throw new IllegalArgumentException();

		for (final AxisMessage m : getMessages(operationName))
			if (m.getName().equals(messageName))
				return m;

		throw new IllegalArgumentException();
	}

	/**
	 * Gets the messages involved in the operation named
	 * <code>operationName</code>.
	 * 
	 * @return A nonempty list of messages. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>operationName.isEmpty()</code> or
	 *             <code>!hasOperation(operationName)</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasMessages(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see #hasMessages(String)
	 * @see #hasOperation(String)
	 * @see String#isEmpty()
	 * 
	 */
	public List<AxisMessage> getMessages(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (!hasMessages(operationName))
			throw new IllegalStateException();

		final AxisOperation operation = getOperation(operationName);

		final List<AxisMessage> messages = new ArrayList<AxisMessage>();
		final Iterator<AxisMessage> iterator = operation.getMessages();
		while (iterator.hasNext())
			messages.add(iterator.next());

		return messages;
	}

	/**
	 * Gets the name of this service.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the operation named <code>operationName</code> of this service.
	 * 
	 * @param operationName
	 *            The name. Not <code>null</code> or empty.
	 * @return An operation. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>operationName.isEmpty()</code> or
	 *             <code>!hasOperation(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see #hasOperation(String)
	 * @see String#isEmpty()
	 */
	public AxisOperation getOperation(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (!hasOperation(operationName))
			throw new IllegalArgumentException();

		final QName qOperationName = new QName(getTargetNamespace(),
				operationName, getTargetNamespacePrefix());
		return service.getOperation(qOperationName);
	}

	/**
	 * Gets the operations provided by this service.
	 * 
	 * @return A nonempty list of operations. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!hasOperations()</code>.
	 * 
	 * @see #hasOperations()
	 */
	public List<AxisOperation> getOperations() {
		if (!hasOperations())
			throw new IllegalStateException();

		final List<AxisOperation> operations = new ArrayList<AxisOperation>();
		final Iterator<AxisOperation> iterator = service.getOperations();
		while (iterator.hasNext())
			operations.add(iterator.next());

		return operations;
	}

	/**
	 * Gets the prefix of the target namespace of the schema.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getSchemaTargetNamespacePrefix() {
		return schemaTargetNamespacePrefix;
	}

	/**
	 * Gets the target namespace.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getTargetNamespace() {
		return targetNamespace;
	}

	/**
	 * Gets the prefix of the target namespace.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getTargetNamespacePrefix() {
		return targetNamespacePrefix;
	}

	/**
	 * Checks if the message named <code>messageName</code> involved in the
	 * operation named <code>operationName</code> of this service has a
	 * direction.
	 * 
	 * @param operationName
	 *            The name of the operation. Not <code>null</code> or empty.
	 * @param messageName
	 *            The name of the message. Not <code>null</code> or empty.
	 * @return A direction. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>operationName.isEmpty()</code> or
	 *             <code>!hasOperation(operationName)</code> or
	 *             <code>messageName.isEmpty()</code> or
	 *             <code>!hasMessage(operationName,messageName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code> or
	 *             <code>messageName==null</code>.
	 * 
	 * @see #hasMessage(String, String)
	 * @see #hasOperation(String)
	 * @see String#isEmpty()
	 */
	public boolean hasDirection(final String operationName,
			final String messageName) {

		if (operationName == null || messageName == null)
			throw new NullPointerException();
		if (!hasOperation(operationName)
				|| !hasMessage(operationName, messageName))
			throw new IllegalArgumentException();

		final AxisMessage message = getMessage(operationName, messageName);
		try {
			WSDLDirection.convertToDirection(message.getDirection());
			return true;
		} catch (DirectionException e) {
			return false;
		}
	}

	/**
	 * Checks if the operation named <code>operationName</code> of this service
	 * involves a message named <code>messageName</code> by searching that
	 * operation for a message with the <em>non</em>qualified name
	 * <code>messageName</code>.
	 * 
	 * <p>
	 * 
	 * @param operationName
	 *            The name of the operation. Not <code>null</code> or empty.
	 * @param messageName
	 *            The name of the message. Not <code>null</code> or empty.
	 * @return <code>true</code> if this service has an operation named
	 *         <code>operationName</code> involving a message named
	 *         <code>messageName</code>; <code>false</code> otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>!hasOperation(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code> or
	 *             <code>messageName==null</code>.
	 * 
	 * @see #hasOperation(String)
	 * @see String#isEmpty()
	 */
	public boolean hasMessage(final String operationName,
			final String messageName) {

		if (operationName == null || messageName == null)
			throw new NullPointerException();
		if (!hasOperation(operationName))
			throw new IllegalArgumentException();

		for (final AxisMessage m : getMessages(operationName))
			if (m.getName().equals(messageName))
				return true;

		return false;
	}

	/**
	 * Checks if the operation named <code>operationName</code> of this service
	 * has messages.
	 * 
	 * @param operationName
	 *            The name of the operation. Not <code>null</code> or empty.
	 * @return <code>true</code> if the operation named
	 *         <code>operationName</code> of this service has messages;
	 *         <code>false</code> otherwise.
	 * @throws IllegalArgumentException
	 *             If <code>operationName.isEmpty()</code> or
	 *             <code>!hasOperation(operationName)</code>.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	public boolean hasMessages(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();
		if (!hasOperation(operationName))
			throw new IllegalArgumentException();

		final AxisOperation operation = getOperation(operationName);

		final Iterator<AxisMessage> iterator = operation.getMessages();
		return iterator != null && iterator.hasNext();
	}

	/**
	 * Checks if this service has an operation named <code>operationName</code>
	 * by searching for an operation with the qualified name:
	 * <code>new QName(</code>
	 * 
	 * <ul>
	 * <li><code>getTargetNamespace(),</code>
	 * <li><code>operationName,</code>
	 * <li><code>getTargetNamespacePrefix()</code><code>)</code>.
	 * </ul>
	 * 
	 * @param operationName
	 *            The name of the operation. Not <code>null</code> or empty.
	 * @return <code>true</code> if this service has an operation named <code>
	 *         operationName</code>; <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>operationName==null</code>.
	 * 
	 * @see #getTargetNamespace()
	 * @see #getTargetNamespacePrefix()
	 * @see QName#QName(String, String, String)
	 * @see String#isEmpty()
	 */
	public boolean hasOperation(final String operationName) {
		if (operationName == null)
			throw new NullPointerException();

		final QName qOperationName = new QName(getTargetNamespace(),
				operationName, getTargetNamespacePrefix());

		return service.getOperation(qOperationName) != null;
	}

	/**
	 * Checks if this service has operations.
	 * 
	 * @return <code>true</code> if this service has operations;
	 *         <code>false</code> otherwise.
	 */
	public boolean hasOperations() {
		final Iterator<AxisOperation> iterator = service.getOperations();
		return iterator != null && iterator.hasNext();
	}

	//
	// PUBLIC - STATIC TODO: Doc.
	//

	/**
	 * Gets a map from the names of the arguments of the message
	 * <code>message</code> to their types.
	 * 
	 * @param message
	 *            The message. Not <code>null</code>.
	 * @return A map. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>message==null</code>.
	 * @throws WSDLServiceException
	 *             If something goes wrong while constructing the map.
	 */
	public static LinkedHashMap<String, WSDLType> getArgumentNamesToTypes(
			final AxisMessage message) throws WSDLServiceException {

		if (message == null)
			throw new NullPointerException();

		final LinkedHashMap<String, WSDLType> map = new LinkedHashMap<String, WSDLType>();

		/* Get the root element. */
		final XmlSchemaElement rootElement = message.getSchemaElement();

		/* Get a list of arguments if $rootElement has a complex inner type. */
		try {
			if (rootElement.getSchemaType() instanceof XmlSchemaComplexType) {
				final XmlSchemaComplexType rootElementType = (XmlSchemaComplexType) rootElement
						.getSchemaType();

				final XmlSchemaParticle particle = rootElementType
						.getParticle();
				if (particle instanceof XmlSchemaGroupBase) {
					final XmlSchemaGroupBase group = (XmlSchemaGroupBase) particle;
					final XmlSchemaObjectCollection items = group.getItems();

					for (int i = 0; i < items.getCount(); i++) {
						final XmlSchemaObject object = items.getItem(i);
						if (object instanceof XmlSchemaElement) {
							final XmlSchemaElement element = (XmlSchemaElement) object;
							if (WSDLType.hasSupportedSchemaTypeName(element))
								map.put(element.getName(),
										WSDLType.getTypeOf(element));
							else
								throw new Exception(
										"I cannot process the element named \""
												+ rootElement.getName()
												+ "\", because one of its (grand)children has the unsupported type.");
						} else
							throw new Exception(
									"I cannot process the element named \""
											+ rootElement.getName() + "\".");
					}
				} else
					throw new Exception("I cannot process the element named \""
							+ rootElement.getName() + "\".");
			}

			/* Throw if $rootElement has a simple inner type. */
			else if (rootElement.getSchemaType() instanceof XmlSchemaSimpleType)
				throw new Exception(
						"I cannot process the element named \""
								+ rootElement.getName()
								+ "\", because it has an xs:simpleType child---please replace it with a xs:complexType child.");

			/* Put a single argument if $rootElement has no inner type. */
			else
				map.put(rootElement.getName(), WSDLType.getTypeOf(rootElement));

		} catch (Exception e) {
			throw new WSDLServiceException(
					"I cannot get the types of the arguments of the message named \""
							+ message.getName() + "\".", e);
		}

		/* Return. */
		return map;
	}

	public static String getEndpointURL(final AxisService service) {
		if (service == null)
			throw new NullPointerException();
		if (!hasEndpointURL(service))
			throw new IllegalArgumentException();

		return service.getEndpoint(service.getEndpointName()).getEndpointURL();
	}

	public static String getName(final AxisService service) {
		if (service == null)
			throw new NullPointerException();
		if (!hasName(service))
			throw new IllegalArgumentException();

		return service.getName();
	}

	public static String getSchemaTargetNamespace(final AxisService service) {
		if (service == null)
			throw new NullPointerException();
		if (!hasSchemaTargetNamespace(service))
			throw new IllegalArgumentException();

		return service.getSchemaTargetNamespace();
	}

	public static String getSchemaTargetNamespacePrefix(
			final AxisService service) {

		if (service == null)
			throw new NullPointerException();
		if (!hasSchemaTargetNamespacePrefix(service))
			throw new IllegalArgumentException();

		return service.getSchemaTargetNamespacePrefix();
	}

	public static String getTargetNamespace(final AxisService service) {
		if (service == null)
			throw new NullPointerException();
		if (!hasTargetNamespace(service))
			throw new IllegalArgumentException();

		return service.getTargetNamespace();
	}

	public static String getTargetNamespacePrefix(final AxisService service) {
		if (service == null)
			throw new NullPointerException();
		if (!hasTargetNamespacePrefix(service))
			throw new IllegalArgumentException();

		return service.getTargetNamespacePrefix();
	}

	public static boolean hasEndpointURL(final AxisService service) {
		if (service == null)
			throw new NullPointerException();

		/* Get the name of the endpoint. */
		final String endpointName = service.getEndpointName();
		if (endpointName == null || endpointName.isEmpty())
			return false;

		/* Get the endpoint. */
		final AxisEndpoint endpoint = service.getEndpoint(endpointName);
		if (endpoint == null)
			return false;

		/* Get the URL of the endpoint. */
		final String endpointURL = endpoint.getEndpointURL();
		return endpointURL != null && !endpointURL.isEmpty();
	}

	public static boolean hasName(final AxisService service) {
		if (service == null)
			throw new NullPointerException();

		final String name = service.getName();
		return name != null && !name.isEmpty();
	}

	public static boolean hasSchemaTargetNamespace(final AxisService service) {
		if (service == null)
			throw new NullPointerException();

		final String schemaTargetNamespace = service.getSchemaTargetNamespace();
		return schemaTargetNamespace != null
				&& !schemaTargetNamespace.isEmpty();
	}

	public static boolean hasSchemaTargetNamespacePrefix(
			final AxisService service) {
		if (service == null)
			throw new NullPointerException();

		final String schemaTargetNamespacePrefix = service
				.getSchemaTargetNamespacePrefix();
		return schemaTargetNamespacePrefix != null
				&& !schemaTargetNamespacePrefix.isEmpty();
	}

	public static boolean hasTargetNamespace(final AxisService service) {
		if (service == null)
			throw new NullPointerException();

		final String targetNamespace = service.getTargetNamespace();
		return targetNamespace != null && !targetNamespace.isEmpty();
	}

	public static boolean hasTargetNamespacePrefix(final AxisService service) {
		if (service == null)
			throw new NullPointerException();

		final String targetNamespacePrefix = service.getTargetNamespacePrefix();
		return targetNamespacePrefix != null
				&& !targetNamespacePrefix.isEmpty();
	}

	//
	// EXCEPTIONS
	//

	public static class WSDLServiceException extends Exception {
		private static final long serialVersionUID = 1L;

		public WSDLServiceException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
