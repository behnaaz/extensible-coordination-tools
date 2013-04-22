package org.ect.codegen.v2.proxy.rt.java.idl;

import java.util.List;
import java.util.concurrent.Semaphore;

import org.ect.codegen.v2.proxy.rt.java.AbstractInvocation;
import org.ect.codegen.v2.proxy.rt.java.Constants;
import org.ect.codegen.v2.proxy.rt.java.idl.CORBAInterface.CORBAObject;
import org.omg.CORBA.ARG_IN;
import org.omg.CORBA.Any;
import org.omg.CORBA.NVList;
import org.omg.CORBA.NamedValue;
import org.omg.CORBA.Request;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA_2_3.ORB;


public class CORBAInvocation extends AbstractInvocation<CORBAParcel> {

	//
	// FIELDS
	//

	/**
	 * The request to invoke.
	 */
	private Request request;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an invocation for the CORBA parcel <code>parcel</code>.
	 * 
	 * @param parcel
	 *            The parcel. Not <code>null</code>.
	 * @param broker
	 *            The object request broker providing access to the object
	 *            referred to by <code>objectReference</code>. Not
	 *            <code>null</code>.
	 * @param objectReference
	 *            A reference to the object to perform an operation on. Not
	 *            <code>null</code>.
	 * @param object
	 *            The object to perform an operation on.
	 * @throws NullPointerException
	 *             If <code>parcel==null</code> or <code>broker==null</code> or
	 *             <code>objectReference==null</code> or
	 *             <code>object==null</code>.
	 * @throws CORBAInvocationException
	 *             If something goes wrong while constructing.
	 */
	protected CORBAInvocation(final CORBAParcel parcel, final ORB broker,
			final org.omg.CORBA.Object objectReference, final CORBAObject object)
			throws CORBAInvocationException {

		super(parcel);
		if (broker == null || objectReference == null || object == null)
			throw new NullPointerException();

		try {
			/* Extract information from $parcel. */
			final String operationName = parcel.getOperationName();
			final Object[] argumentValues = parcel.getArgumentValues();

			/* Prepare arguments. */
			if (!object.hasOperation(operationName))
				throw new CORBAInvocationException(
						"I failed to construct an invocation for the parcel \""
								+ parcel + "\".", "The object named \""
								+ object.getName()
								+ "\" has no operation named \""
								+ operationName + "\".");

			final List<CORBAArgument> arguments = object
					.getArguments(operationName);

			final NVList argumentList = broker.create_list(arguments.size());
			for (int i = 0; i < arguments.size(); i++) {
				final CORBAArgument argument = arguments.get(i);
				final String argumentName = argument.getName();
				final TypeCode argumentType = argument.getType();

				final Any argumentBox = broker.create_any();
				final Object argumentValue = i < argumentValues.length ? argumentValues[i]
						: null;

				CORBAType.insertInto(argumentBox, argumentType, argumentValue);
				argumentList.add_value(argumentName, argumentBox, ARG_IN.value);
			}

			/* Prepare a result value. */
			final Any resultBox = broker.create_any();
			final TypeCode resultType = object.getReturnTypeOf(operationName);
			resultBox.type(resultType);
			final NamedValue result = broker.create_named_value("result",
					resultBox, org.omg.CORBA.ARG_OUT.value);

			/* Prepare the request. */
			this.request = objectReference._create_request(null, operationName,
					argumentList, result);

		} catch (final Exception e) {
			if (e instanceof CORBAInvocationException)
				throw (CORBAInvocationException) e;
			else
				throw new CORBAInvocationException(
						"I failed to construct an invocation for the parcel \""
								+ parcel + "\".", e);
		}
	}

	@Override
	protected CORBAParcel perform() throws InvocationException {
		try {
			/* Invoke. */
			request.invoke();
			final Exception exception = request.env().exception();
			if (exception != null)
				throw exception;

			/* Get the result. */
			final NamedValue result = request.result();
			final Any resultBox = result.value();
			final TypeCode resultType = resultBox.type();

			/* Append the result to $queue. */
			final String resultPortName = super.getParcel().getPortName()
					+ Constants.PROXY_VERTEX_NAME_RESULT_SUFFIX;
			final Object item = CORBAType.extractFrom(resultBox, resultType);

			/* Return. */
			return new CORBAParcel(resultPortName, item, new Semaphore(0));
		} catch (final Exception e) {
			throw new CORBAInvocationException(
					"I failed to invoke the operation named \""
							+ request.operation() + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class CORBAInvocationException extends InvocationException {
		private static final long serialVersionUID = 1L;

		private CORBAInvocationException(final String message) {
			super(message);
		}

		private CORBAInvocationException(final String message,
				final String cause) {
			super(message + " " + cause);
		}

		private CORBAInvocationException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
