package org.ect.codegen.v2.proxy.rt.java.wsdl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;

import org.apache.axis2.description.AxisMessage;
import org.apache.commons.lang3.StringUtils;
import org.ect.codegen.v2.proxy.rt.java.AbstractInvocation;
import org.ect.codegen.v2.proxy.rt.java.Constants;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLClient;


public class WSDLInvocation extends AbstractInvocation<WSDLParcel> {

	//
	// FIELDS
	//

	/**
	 * The client to perform the invocation with.
	 */
	private final WSDLClient client;

	/**
	 * The name of the port on which to dispatch the result.
	 */
	private String resultPortName;

	/**
	 * The type of the result value.
	 */
	private WSDLType resultType;

	/**
	 * The service to perform the invocation on.
	 */
	private final WSDLService service;

	/**
	 * The arguments of the operation to invoke.
	 */
	private final LinkedHashMap<String, String> arguments;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an invocation for the WSDL parcel <code>parcel</code>.
	 * 
	 * @param parcel
	 *            The parcel. Not <code>null</code>.
	 * @param client
	 *            The client to perform the invocation with. Not
	 *            <code>null</code>.
	 * @param service
	 *            The service to perform the invocation on. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>parcel==null</code> or <code>client==null</code> or
	 *             <code>service==null</code>.
	 * @throws WSDLInvocationException
	 *             If something goes wrong while constructing.
	 */
	WSDLInvocation(final WSDLParcel parcel, final WSDLClient client,
			final WSDLService service) throws WSDLInvocationException {

		super(parcel);
		if (client == null || service == null)
			throw new NullPointerException();

		this.client = client;
		this.service = service;

		/* Prepare the arguments. */
		this.arguments = new LinkedHashMap<String, String>();

		try {
			final AxisMessage message = service.getMessage(getOperationName(),
					getMessageName());
			final LinkedHashMap<String, WSDLType> argumentNamesToTypes = WSDLService
					.getArgumentNamesToTypes(message);

			final Iterator<Object> values = parcel.getArguments().iterator();
			for (final Entry<String, WSDLType> e : argumentNamesToTypes
					.entrySet()) {
				final Object object = values.hasNext() ? values.next() : null;
				arguments.put(e.getKey(), e.getValue().convertToString(object));
			}
		} catch (final Exception e) {
			throw new WSDLInvocationException(
					"I failed to construct an invocation for the parcel \""
							+ parcel.toString() + "\".", e);
		}

		/* Prepare the result. */
		boolean hasStarted = false;
		for (final AxisMessage m : service.getMessages(getOperationName())) {
			hasStarted = hasStarted || m.getName().equals(getMessageName());

			try {
				if (hasStarted
						&& WSDLDirection.convertToDirection(m.getDirection()) == WSDLDirection.OUT) {
					final LinkedHashMap<String, WSDLType> map = WSDLService
							.getArgumentNamesToTypes(m);

					this.resultPortName = Constants.PROXY_VERTEX_NAME_PREFIX
							+ StringUtils.capitalize(getOperationName()) + "."
							+ m.getName();
					this.resultType = map == null || map.isEmpty() ? WSDLType.STRING
							: map.values().iterator().next();
					break;
				}
			} catch (final Exception e) {
				throw new WSDLInvocationException(
						"I failed to construct an invocation for the parcel \""
								+ parcel.toString() + "\".", e);
			}
		}

		if (this.resultPortName == null || this.resultType == null)
			throw new WSDLInvocationException(
					"I failed to construct an invocation for the parcel \""
							+ parcel.toString() + "\".");
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
	protected WSDLParcel perform() throws InvocationException {
		try {
			final String resultText = client.invoke(getOperationName(),
					arguments);

			final Object resultObject = resultType.parseToObject(resultText);
			return new WSDLParcel(resultPortName, resultObject,
					new Semaphore(0), service);

		} catch (final Exception e) {
			throw new WSDLInvocationException(
					"I failed to perform the operation named \""
							+ getOperationName() + "\".", e);
		}
	}

	//
	// PRIVATE
	//

	/**
	 * Shorthand for: <code>super.getParcel().getMessageName()</code>.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	private String getMessageName() {
		return super.getParcel().getMessageName();
	}

	/**
	 * Shorthand for: <code>super.getParcel().getOperationName()</code>.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	private String getOperationName() {
		return super.getParcel().getOperationName();
	}

	//
	// EXCEPTIONS
	//

	public static class WSDLInvocationException extends InvocationException {
		private static final long serialVersionUID = 1L;

		private WSDLInvocationException(final String message) {
			super(message);
		}

		private WSDLInvocationException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
