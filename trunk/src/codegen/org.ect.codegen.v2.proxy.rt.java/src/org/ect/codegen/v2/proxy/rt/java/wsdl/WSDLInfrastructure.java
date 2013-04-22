package org.ect.codegen.v2.proxy.rt.java.wsdl;

import java.util.concurrent.Semaphore;

import org.ect.codegen.v2.proxy.rt.java.AbstractQueueInfrastructure;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLClient;


public class WSDLInfrastructure extends
		AbstractQueueInfrastructure<WSDLParcel, WSDLInvocation> {

	/**
	 * The client facilitating actual communication.
	 */
	private final WSDLClient client;

	/**
	 * The service this infrastructure goes to.
	 */
	private final WSDLService service;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an infrastructure to the service <code>service</code>.
	 * 
	 * @param service
	 *            The service. Not <code>null</code>.
	 * @param client
	 *            The client facilitating actual communication. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>service==null</code> or <code>client==null</code>.
	 */
	public WSDLInfrastructure(final String partyName,
			final WSDLService service, final WSDLClient client) {

		super(partyName);

		if (service == null || client == null)
			throw new NullPointerException();

		this.service = service;
		this.client = client;

		// try {
		// this.client = new ServiceClient();
		// } catch (Exception e) {
		// throw new WSDLInfrastructureException(
		// "I cannot create an infrastructure for sending parcels.",
		// e);
		// }
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public WSDLParcel newParcel(final String portName, final Object item,
			final Semaphore deliveryTally) throws InfrastructureException {

		if (portName == null || item == null || deliveryTally == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();

		try {
			return new WSDLParcel(portName, item, deliveryTally, service);
		} catch (final Exception e) {
			final InfrastructureException exception = new WSDLInfrastructureException(
					"WSDL Parcel construction failed.", e);
			exception.setRecoverable();
			throw exception;
		}
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	protected WSDLInvocation newInvocation(final WSDLParcel parcel)
			throws WSDLInfrastructureException {

		if (parcel == null)
			throw new NullPointerException();

		try {
			return new WSDLInvocation(parcel, client, service);
		} catch (final Exception e) {
			final WSDLInfrastructureException exception = new WSDLInfrastructureException(
					"I failed to construct an invocation for the parcel \""
							+ parcel.toString() + "\".", e);

			exception.setRecoverable();
			throw exception;
		}
	}

	// /**
	// * <em>Inherited documentation:</em>
	// *
	// * <p>
	// * {@inheritDoc}
	// */
	// @Override
	// public void send(final WSDLParcel parcel)
	// throws WSDLInfrastructureException {
	//
	// /* Get the payload to send. */
	// final OMElement payload = extractPayloadFrom(parcel);
	//
	// /* Prepare a callback. */
	// final WSDLCallback callback = new WSDLCallback(parcel, queue,
	// service);
	//
	// /* Set options. */
	// final Options options = new Options();
	// final String endpointURL = service.getEndpointURL();
	// options.setTo(new EndpointReference(endpointURL));
	// options.setAction(parcel.getOperationName());
	// client.setOptions(options);
	//
	// /* Send. */
	// if (service.getOperation(parcel.getOperationName())
	// .getMessageExchangePattern()
	// .equals("http://www.w3.org/ns/wsdl/in-only"))
	// client.fireAndForget(payload);
	// else
	// client.sendReceiveNonBlocking(payload, callback);
	// }

	//
	// METHODS - PRIVATE
	//

	// /**
	// * Extracts a payload from the parcel <code>parcel</code>.
	// *
	// * @param parcel
	// * The parcel. Not <code>null</code>.
	// * @return The payload. Never <code>null</code>.
	// * @throws NullPointerException
	// * If <code>parcel==null</code>.
	// * @throws WSDLServiceException
	// * If something goes wrong while converting.
	// */
	// private OMElement extractPayloadFrom(final WSDLParcel parcel)
	// throws WSDLServiceException {
	//
	// if (parcel == null)
	// throw new NullPointerException();
	//
	// /* Get a factory and a namespace. */
	// final OMFactory factory = OMAbstractFactory.getOMFactory();
	// final OMNamespace namespace = factory.createOMNamespace(
	// service.getTargetNamespace(),
	// service.getTargetNamespacePrefix());
	//
	// /* Get argument names. */
	// final AxisMessage message = service.getMessage(
	// parcel.getOperationName(), parcel.getMessageName());
	// final LinkedHashMap<String, WSDLType> argumentNamesToTypeNames =
	// WSDLService
	// .getArgumentNamesToTypes(message);
	//
	// /* Create payload. */
	// final OMElement method = factory.createOMElement(
	// parcel.getOperationName(), namespace);
	//
	// final Iterator<Object> arguments = parcel.getArguments().iterator();
	// for (final Entry<String, WSDLType> e : argumentNamesToTypeNames
	// .entrySet()) {
	//
	// final String name = e.getKey();
	// final WSDLType type = e.getValue();
	//
	// final OMElement argument = factory.createOMElement(name, namespace);
	// final Object object = arguments.hasNext() ? arguments.next() : null;
	// type.setTextOf(argument, object);
	// method.addChild(argument);
	// }
	//
	// return method;
	// }

	//
	// EXCEPTIONS
	//

	public static class WSDLInfrastructureException extends
			InfrastructureException {
		private static final long serialVersionUID = 1L;

		public WSDLInfrastructureException(final String message) {
			super(message);
		}

		public WSDLInfrastructureException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}