package org.ect.codegen.v2.proxy.rt.java.idl;

import java.util.concurrent.Semaphore;

import org.ect.codegen.v2.proxy.rt.java.AbstractQueueInfrastructure;
import org.ect.codegen.v2.proxy.rt.java.idl.CORBAInterface.CORBAObject;
import org.omg.CORBA_2_3.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


public class CORBAInfrastructure extends
		AbstractQueueInfrastructure<CORBAParcel, CORBAInvocation> {

	//
	// FIELDS
	//

	/**
	 * The object request broker providing access to the object targeted by this
	 * infrastructure.
	 */
	private final ORB broker;

	/**
	 * The object targeted by this infrastructure.
	 */
	private final CORBAObject object;

	/**
	 * A reference to the object targeted by this infrastructure.
	 */
	private final org.omg.CORBA.Object objectReference;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an infrastructure to the object <code>object</code>.
	 * 
	 * @param object
	 *            The object. Not <code>null</code>.
	 * @throws CORBAInfrastructureException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	public CORBAInfrastructure(final String partyName, final CORBAObject object)
			throws CORBAInfrastructureException {

		super(partyName);
		if (object == null)
			throw new NullPointerException();

		this.object = object;
		final String objectName = object.getName();

		try {
			/* Initialize an object request broker. */
			final String[] args = { "-ORBInitialHost", object.getHost(),
					"-ORBInitialPort", Integer.toString(object.getPort()) };
			this.broker = (ORB) ORB.init(args, null);

			/* Get a reference to the name service and initialize a context. */
			final org.omg.CORBA.Object nameServiceReference = broker
					.resolve_initial_references("NameService");

			/* Get the naming context. */
			final NamingContextExt context = NamingContextExtHelper
					.narrow(nameServiceReference);

			/* Get a reference to the object targeted by this infrastructure. */
			this.objectReference = context.resolve_str(objectName);
		} catch (final Exception e) {
			throw new CORBAInfrastructureException(
					"I cannot construct an infrastructure to the object named \""
							+ objectName + "\".", e);
		}
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
	public CORBAParcel newParcel(final String portName, final Object item,
			final Semaphore deliveryTally) throws InfrastructureException {

		if (portName == null || item == null || deliveryTally == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();

		try {
			return new CORBAParcel(portName, item, deliveryTally);
		} catch (final Exception e) {
			throw new CORBAInfrastructureException(
					"CORBA parcel construction failed.", e);
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
	protected CORBAInvocation newInvocation(final CORBAParcel parcel)
			throws CORBAInfrastructureException {

		if (parcel == null)
			throw new NullPointerException();

		try {
			return new CORBAInvocation(parcel, broker, objectReference, object);
		} catch (final Exception e) {
			final CORBAInfrastructureException exception = new CORBAInfrastructureException(
					"I failed to construct an invocation for the parcel \""
							+ parcel + "\".", e);

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
	// public void send(final CORBAParcel parcel) throws InfrastructureException
	// {
	// if (parcel == null)
	// throw new NullPointerException();
	//
	// final BackInvocation invocation = new BackInvocation(broker,
	// objectReference, object, parcel, queue);
	//
	// /* Prepare. */
	// try {
	// invocation.prepare();
	// } catch (final InvocationPrepareException e) {
	// final CORBAInfrastructureException exception = new
	// CORBAInfrastructureException(
	// "I failed to send the parcel \"" + parcel.toString()
	// + "\".", e);
	//
	// exception.setRecoverable();
	// throw exception;
	// }
	//
	// /* Invoke. */
	// try {
	// invocation.invoke();
	// } catch (final InvocationInvokeException e) {
	// new CORBAInfrastructureException("I failed to send the parcel \""
	// + parcel.toString() + "\".", e);
	// }
	// }

	//
	// EXCEPTIONS
	//

	public static class CORBAInfrastructureException extends
			InfrastructureException {
		private static final long serialVersionUID = 1L;

		public CORBAInfrastructureException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
