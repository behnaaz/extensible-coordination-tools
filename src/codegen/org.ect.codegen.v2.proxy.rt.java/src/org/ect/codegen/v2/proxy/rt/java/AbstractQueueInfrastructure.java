package org.ect.codegen.v2.proxy.rt.java;

import java.util.concurrent.LinkedBlockingQueue;

import org.ect.codegen.v2.proxy.rt.java.AbstractInvocation.InvocationException;


public abstract class AbstractQueueInfrastructure<P extends AbstractParcel, I extends AbstractInvocation<P>>
		extends AbstractInfrastructure<P> {

	//
	// FIELDS
	//

	/**
	 * The name of the party targeted by this infrastructure.
	 */
	private final String partyName;

	/**
	 * The queue used for caching return values.
	 */
	private final LinkedBlockingQueue<P> queue = new LinkedBlockingQueue<P>();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an infrastructure targeting the party named
	 * <code>partyName</code>.
	 * 
	 * @param partyName
	 *            The name of the party. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>partyName==null</code>.
	 */
	protected AbstractQueueInfrastructure(final String partyName) {
		if (partyName == null)
			throw new NullPointerException();

		this.partyName = partyName;
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
	public String getPartyName() {
		return partyName;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public P receive() throws InfrastructureException {
		while (true)
			try {
				return queue.take();
			} catch (final Exception e) {
				continue;
			}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public void send(final P parcel) throws InfrastructureException {
		if (parcel == null)
			throw new NullPointerException();

		/* Construct an invocation. */
		final I invocation = newInvocation(parcel);

		/* Perform the invocation. */
		final P resultParcel;
		try {
			resultParcel = invocation.perform();
		} catch (final InvocationException e) {
			throw new QueueInfrastructureException(
					"I failed to send the parcel \"" + parcel + "\".", e);
		}

		/* Add $resultParcel to $queue. */
		if (resultParcel != null)
			new Thread() {
				public void run() {
					while (true)
						try {
							queue.put(resultParcel);
							return;
						} catch (final Exception e) {
							continue;
						}
				};
			}.start();
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * Constructs an invocation.
	 * 
	 * @return An invocation. Never <code>null</code>.
	 */
	protected abstract I newInvocation(final P parcel)
			throws InfrastructureException;

	//
	// EXCEPTIONS
	//

	public static class QueueInfrastructureException extends
			InfrastructureException {
		private static final long serialVersionUID = 1L;

		protected QueueInfrastructureException(final String message) {
			super(message);
		}

		protected QueueInfrastructureException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
