package org.ect.codegen.v2.proxy.rt.java.wsdl.parser;

import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;

import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLBindingType.WSDLBindingTypeSwitchException;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLDescription.WSDLDescriptionException;


public class WSDLClient {

	//
	// FIELDS
	//

	/**
	 * The actual client used to invoke operations with.
	 */
	private Service client;

	/**
	 * The <code>endpoint</code> component representing the endpoint of the
	 * service to invoke operations on through this client.
	 */
	private final WSDLEndpoint endpoint;

	/**
	 * The qualified name of the next operation to invoke.
	 */
	private QName qNextOperationName;

	/**
	 * The WSDL resource used to construct and initialize this client.
	 */
	private final WSDLResource resource;

	/**
	 * The <code>service</code> component representing the service to invoke
	 * operations on through this client.
	 */
	private final WSDLService service;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a dynamic client to invoke operations with for the service
	 * with the (non-colonized) name <code>serviceName</code> described in the
	 * WSDL resource <code>resource</code>.
	 * 
	 * @param resource
	 *            The WSDL resource. Not <code>null</code>.
	 * @param serviceName
	 *            The (non-colonized) name. Not <code>null</code>.
	 * @throws WSDLClientConstructorException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>resource==null</code> or
	 *             <code>serviceName==null</code>.
	 */
	public WSDLClient(final WSDLResource resource, final String serviceName)
			throws WSDLClientConstructorException {

		if (resource == null || serviceName == null)
			throw new NullPointerException();

		this.resource = resource;

		/* Extract a "description" component. */
		final WSDLDescription description;
		try {
			description = new WSDLDescription(resource);
		} catch (final WSDLDescriptionException e) {
			throw new WSDLClientConstructorException(e);
		}

		/* Extract a "service" component. */
		final QName qServiceName = description.convertToQName(serviceName);
		if (!description.hasService(qServiceName))
			throw new WSDLClientConstructorException(
					"I cannot find a service named \"" + serviceName + "\".");

		this.service = description.getService(qServiceName);

		/* Extract an "endpoint" component. */
		final WSDLComponents<WSDLEndpoint> endpoints = service.getEndpoints();
		if (endpoints.isEmpty())
			throw new WSDLClientConstructorException(
					"I cannot find any endpoints.");

		this.endpoint = endpoints.getFirst();

		/* Set system properties. */
		System.setProperty("javax.xml.soap.MessageFactory",
				"com.sun.xml.internal.messaging.saaj.soap.MessageFactoryImpl");
		System.setProperty("javax.xml.soap.MetaFactory",
				"com.sun.xml.internal.messaging.saaj.soap.SAAJMetaFactoryImpl");
		System.setProperty("javax.xml.soap.SOAPConnectionFactory",
				"com.sun.xml.internal.messaging.saaj.client.p2p.HttpSOAPConnectionFactory");
		System.setProperty("javax.xml.soap.SOAPFactory",
				"com.sun.xml.internal.messaging.saaj.soap.SOAPFactoryImpl");
		System.setProperty("javax.xml.ws.spi.Provider",
				"com.sun.xml.internal.ws.spi.ProviderImpl");

		/* Initialize the actual client. */
		initializeClient();
	}

	//
	// METHODS
	//

	/**
	 * Gets the <code>description</code> component representing the service
	 * whose operations this client invokes.
	 * 
	 * @return A <code>description</code> component.
	 */
	public WSDLDescription getDescription() {
		return service.getDescription();
	}

	/**
	 * Invokes the operation with the (non-colonized) name
	 * <code>operationName</code> on the string arguments <code>arguments</code>
	 * , and returns the string result. Shorthand for
	 * <code>invoke(qServiceName,arguments)</code> where
	 * <code>qServiceName.equals(getDescription().convertToQName(serviceName))</code>
	 * .
	 * 
	 * @param operationName
	 *            The (non-colonized) name. Not <code>null</code>.
	 * @param arguments
	 *            The arguments. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws WSDLClientInvokeException
	 *             If something goes wrong while invoking.
	 * @throws NullPointerException
	 *             If <code>qOperationName==null</code> or
	 *             <code>arguments==null</code>.
	 * 
	 * @see #getDescription()
	 * @see #invoke(QName, LinkedHashMap)
	 * @see WSDLDescription#convertToQName(String)
	 */
	public String invoke(final String operationName,
			final LinkedHashMap<String, String> arguments)
			throws WSDLClientInvokeException {

		if (operationName == null || arguments == null)
			throw new NullPointerException();

		final QName qOperationName = getDescription().convertToQName(
				operationName);
		return invoke(qOperationName, arguments);
	}

	/**
	 * Invokes the operation with the qualified name <code>qOperationName</code>
	 * on the string arguments <code>arguments</code>, and returns the string
	 * result.
	 * 
	 * @param qOperationName
	 *            The qualified name. Not <code>null</code>.
	 * @param arguments
	 *            The arguments. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws WSDLClientInvokeException
	 *             If something goes wrong while invoking.
	 * @throws NullPointerException
	 *             If <code>qOperationName==null</code> or
	 *             <code>arguments==null</code>.
	 */
	public String invoke(final QName qOperationName,
			final LinkedHashMap<String, String> arguments)
			throws WSDLClientInvokeException {

		if (qOperationName == null || arguments == null)
			throw new NullPointerException();

		qNextOperationName = qOperationName;

		/*
		 * Extract the "binding" component and its "binding operation"
		 * components.
		 */
		final WSDLBinding binding = endpoint.getBinding();
		final WSDLComponents<WSDLBindingOperation> bindingOperations = binding
				.getBindingOperations();

		/* Check if $binding supports $qOperationName. */
		if (!bindingOperations.contains(qOperationName))
			throw new WSDLClientInvokeException(
					"I cannot find an operation with that name.");

		final WSDLBindingOperation bindingOperation = bindingOperations
				.get(qOperationName);

		/* Construct a dispatch object. */
		final QName portName = endpoint.getQName();
		final Class<SOAPMessage> type = SOAPMessage.class;
		final Mode mode = Service.Mode.MESSAGE;
		final Dispatch<SOAPMessage> dispatch = client.createDispatch(portName,
				type, mode);

		/* Set the SOAP action if available. */
		if (bindingOperation.hasSOAPAction()) {
			final Map<String, Object> requestContext = dispatch
					.getRequestContext();
			requestContext.put(BindingProvider.SOAPACTION_USE_PROPERTY,
					Boolean.TRUE);
			requestContext.put(BindingProvider.SOAPACTION_URI_PROPERTY,
					bindingOperation.getSOAPAction());
		}

		/* Assume a SOAP binding. */
		if (!binding.getType().isSOAP())
			throw new WSDLClientInvokeException("I support only SOAP bindings.");

		/* Create and compose a SOAP message. */
		final SOAPMessage message;
		try {
			message = newSOAPMessage();
			composeSOAPMessage(message, qOperationName, bindingOperation,
					arguments);
		} catch (WSDLClientComposeSOAPMessageException e) {
			throw new WSDLClientInvokeException(e);
		} catch (WSDLClientNewSOAPMessageException e) {
			throw new WSDLClientInvokeException(e);
		} catch (WSDLBindingTypeSwitchException e) {
			throw new WSDLClientInvokeException(e);
		}

		/* Send $message and return. */
		try {
			final SOAPMessage response = dispatch.invoke(message);
			if (response == null)
				throw new WSDLClientInvokeException(
						"I received a null response.");

			final SOAPBody responseBody = response.getSOAPBody();
			if (responseBody == null)
				throw new WSDLClientInvokeException(
						"I received a null response.");

			return responseBody.getTextContent();

		} catch (final Exception e) {
			throw new WSDLClientInvokeException(e);
		}
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Converts the SOAP message <code>message</code> to an XML string.
	 * 
	 * @param message
	 *            The SOAP message. Not <code>null</code>.
	 * @return A string. Never <code>null</code>.
	 * @throws WSDLClientException
	 * @throws NullPointerException
	 *             If <code>message==null</code>.
	 */
	String convertToXMLString(final SOAPMessage message)
			throws WSDLClientException {

		if (message == null)
			throw new NullPointerException();

		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			message.writeTo(stream);
			return new String(stream.toByteArray());
		} catch (final Exception e) {
			throw new WSDLClientException(
					"I cannot convert the SOAP message \""
							+ message.getContentDescription()
							+ "\" to a string.", e);
		}
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Composes the SOAP message <code>message</code> such that it represents an
	 * invocation of the operation with the qualified name
	 * <code>qOperationName</code> on the arguments <code>arguments</code>.
	 * 
	 * @param message
	 *            The SOAP message. Not <code>null</code>.
	 * @param qOperationName
	 *            The qualified name of the operation. Not <code>null</code>.
	 * @param arguments
	 *            The arguments. Not <code>null</code>
	 * @throws WSDLClientComposeSOAPMessageException
	 *             If something goes wrong while composing.
	 */
	private void composeSOAPMessage(final SOAPMessage message,
			final QName qOperationName,
			final WSDLBindingOperation bindingOperation,
			final LinkedHashMap<String, String> arguments)
			throws WSDLClientComposeSOAPMessageException {

		if (message == null || qOperationName == null || arguments == null)
			throw new NullPointerException();

		try {
			/* Get the SOAP body. */
			final SOAPBody body = message.getSOAPBody();
			if (body == null)
				throw new WSDLClientComposeSOAPMessageException(
						"I cannot get a SOAP body.");

			/* Add an operation element to $body. */
			final SOAPElement operation = body.addChildElement(qOperationName);

			/* Add argument elements to $operation. */
			for (final Entry<String, String> e : arguments.entrySet()) {
				final String argumentName = e.getKey();
				final String argumentValue = e.getValue();

				/* Guess whether to include the namespace. */
				if (bindingOperation.getSOAPIncludeNamespace())
					operation.addChildElement(argumentName,
							operation.getPrefix(), operation.getNamespaceURI())
							.addTextNode(argumentValue);
				else
					operation.addChildElement(argumentName).addTextNode(
							argumentValue);

			}

			/* Save the changes made to $message. */
			message.saveChanges();

		} catch (final SOAPException e) {
			throw new WSDLClientComposeSOAPMessageException(e);
		}
	}

	/**
	 * Initializes the actual client through which to invoke operations.
	 * 
	 * @throws WSDLClientConstructorException
	 *             If something goes wrong while initializing.
	 * @throws IllegalStateException
	 *             If <code>endpoint==null</code> or <code>service==null</code>.
	 */
	private void initializeClient() throws WSDLClientConstructorException {
		if (this.endpoint == null || this.service == null)
			throw new IllegalStateException();

		/* Create the actual interface. */
		this.client = Service.create(service.getQName());

		/* Get a binding id. */
		final String bindingId;
		switch (this.endpoint.getBinding().getType()) {
		case SOAP11:
			bindingId = SOAPBinding.SOAP11HTTP_BINDING;
			break;
		case SOAP12:
			bindingId = SOAPBinding.SOAP12HTTP_BINDING;
			break;
		default:
			throw new WSDLClientConstructorException(
					"I do not support the binding type \""
							+ endpoint.getBinding().getType() + "\".");
		}

		/* Add an endpoint to $client. */
		final QName portName = this.endpoint.getQName();
		final String endpointAddress = this.endpoint.getAddress();
		this.client.addPort(portName, bindingId, endpointAddress);
	}

	/**
	 * Constructs an empty SOAP message.
	 * 
	 * @return A SOAP message. Never <code>null</code>.
	 * @throws WSDLClientNewSOAPMessageException
	 *             If something goes wrong while constructing.
	 * @throws IllegalStateException
	 *             If <code>this.endpoint==null</code>.
	 * @throws WSDLBindingTypeSwitchException
	 *             If the binding type is not supported.
	 * 
	 */
	private SOAPMessage newSOAPMessage()
			throws WSDLClientNewSOAPMessageException,
			WSDLBindingTypeSwitchException {

		if (this.endpoint == null)
			throw new IllegalStateException();

		/* Get the protocol. */
		final String protocol;
		final WSDLBindingType type = this.endpoint.getBinding().getType();
		switch (type) {
		case SOAP11:
			protocol = SOAPConstants.SOAP_1_1_PROTOCOL;
			break;
		case SOAP12:
			protocol = SOAPConstants.SOAP_1_2_PROTOCOL;
			break;
		default:
			throw new WSDLBindingTypeSwitchException(type);
		}

		/* Create a message. */
		try {
			final MessageFactory factory = MessageFactory.newInstance(protocol);
			return factory.createMessage();
		} catch (final SOAPException e) {
			throw new WSDLClientNewSOAPMessageException(e);
		}
	}

	//
	// EXCEPTIONS
	//

	public class WSDLClientException extends Exception {
		private static final long serialVersionUID = 1L;

		private WSDLClientException(final String message) {
			super(message);
		}

		private WSDLClientException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}

	public class WSDLClientComposeSOAPMessageException extends
			WSDLClientException {
		private static final long serialVersionUID = 1L;

		private WSDLClientComposeSOAPMessageException() {
			super("I cannot compose a SOAP message.");
		}

		private WSDLClientComposeSOAPMessageException(final String cause) {
			super("I cannot compose a SOAP message: " + cause);
		}

		private WSDLClientComposeSOAPMessageException(final Throwable cause) {
			super("I cannot compose a SOAP message.", cause);
		}
	}

	public class WSDLClientConstructorException extends WSDLClientException {
		private static final long serialVersionUID = 1L;

		private WSDLClientConstructorException() {
			super("I cannot construct a client from the WSDL resource \""
					+ resource.getCroppedResourceText() + "\".");
		}

		private WSDLClientConstructorException(final String cause) {
			super("I cannot construct a client from the WSDL resource \""
					+ resource.getCroppedResourceText() + "\": " + cause);
		}

		private WSDLClientConstructorException(final Throwable cause) {
			super("I cannot construct a client from the WSDL resource \""
					+ resource.getCroppedResourceText() + "\".", cause);
		}
	}

	public class WSDLClientInvokeException extends WSDLClientException {
		private static final long serialVersionUID = 1L;

		private WSDLClientInvokeException() {
			super("I cannot invoke the operation named \""
					+ qNextOperationName.getLocalPart() + "\".");
		}

		private WSDLClientInvokeException(final String cause) {
			super("I cannot invoke the operation named \""
					+ qNextOperationName.getLocalPart() + "\": " + cause);
		}

		private WSDLClientInvokeException(final Throwable cause) {
			super("I cannot invoke the operation named \""
					+ qNextOperationName.getLocalPart() + "\".", cause);
		}
	}

	public class WSDLClientNewSOAPMessageException extends WSDLClientException {
		private static final long serialVersionUID = 1L;

		private WSDLClientNewSOAPMessageException() {
			super("I cannot construct a SOAP message.");
		}

		private WSDLClientNewSOAPMessageException(final String cause) {
			super("I cannot construct a SOAP message: " + cause);
		}

		private WSDLClientNewSOAPMessageException(final Throwable cause) {
			super("I cannot construct a SOAP message.", cause);
		}
	}

	//
	// MAIN
	//

	public static void main(String[] args) throws Exception {
		final String wsdlResourceText = "http://www.webservicex.com/globalweather.asmx?WSDL";
		final String serviceName = "GlobalWeather";
		final WSDLResource resource = new WSDLResource(wsdlResourceText);
		final WSDLClient interfaze = new WSDLClient(resource, serviceName);

		final LinkedHashMap<String, String> arguments = new LinkedHashMap<String, String>();
		// arguments.put("x", "10001");
		// arguments.put("y", "2");
		arguments.put("CityName", "Pisa");
		arguments.put("CountryName", "Italy");
		final String result = interfaze.invoke("GetWeather", arguments);

		System.out.println(result);
	}
}