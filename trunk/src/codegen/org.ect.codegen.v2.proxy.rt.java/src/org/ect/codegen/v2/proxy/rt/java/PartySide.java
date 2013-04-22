package org.ect.codegen.v2.proxy.rt.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.ect.codegen.v2.proxy.rt.java.AbstractInfrastructure.InfrastructureException;


public class PartySide<P extends AbstractParcel, I extends AbstractInfrastructure<P>>
		implements Runnable {

	/**
	 * The infrastructure to the actual party.
	 */
	private final I infrastructure;

	/**
	 * The proxy to which this party side belongs.
	 */
	private final AbstractProxy<P, I> proxy;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a party side that has access to the infrastructure
	 * <code>infrastructure</code>.
	 * 
	 * @param proxy
	 *            The proxy to which the party side to construct belongs. Not
	 *            <code>null</code>.
	 * @param infrastructure
	 *            The infrastructure to the actual party. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>proxy==null</code> or
	 *             <code>infrastructure==null</code>.
	 */
	PartySide(final AbstractProxy<P, I> proxy, final I infrastructure) {
		if (proxy == null || infrastructure == null)
			throw new NullPointerException();

		this.infrastructure = infrastructure;
		this.proxy = proxy;
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Runs this party side.
	 */
	@Override
	public void run() {
		while (true) {

			/* Receive a parcel. */
			final P parcel;
			try {
				parcel = infrastructure.receive();
			} catch (final InfrastructureException e) {
				final Exception exception = new PartySideException(
						"Something went wrong while processing data received on the party side.",
						e);
				proxy.throwException(exception);
				continue;
			}

			/* Unpack $parcel and dispatch it on the connector. */
			final String portName = parcel.getPortName();
			final Object item = parcel.getItem();

			if (proxy.getConnectorSide().hasInputPortNamed(portName))
				proxy.getConnectorSide().dispatch(portName, item);
			else {

				/* Ignore. */
				continue;

				// final Exception exception = new PartySideException(
				// "Something went wrong while processing data received on the party side.",
				// "The data received is addressed to the unknown port named \""
				// + portName + "\".");
				// proxy.throwException(exception);
			}
		}
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Dispatches the items <code>portNamesToItems.values()</code>, received on
	 * the ports named <code>portNamesToItems.keySet()</code> on this party
	 * side.
	 * 
	 * @param portNamesToItems
	 *            The port names and items. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>namesToItems==null</code>.
	 * @throws PartySideException
	 *             If something goes wrong while dispatching.
	 */
	void dispatch(Map<String, Object> portNamesToItems)
			throws PartySideException {

		if (portNamesToItems == null)
			throw new NullPointerException();

		/* Pack the items as specified in $portNamesToItems as parcels. */
		final int nParcels = portNamesToItems.size();
		final List<P> parcels = new ArrayList<P>(nParcels);

		final Semaphore deliveryTally = new Semaphore(0);
		for (final String portName : portNamesToItems.keySet())
			try {
				final Object item = portNamesToItems.get(portName);
				parcels.add(infrastructure.newParcel(portName, item,
						deliveryTally));

			} catch (final InfrastructureException e) {
				throw new PartySideException(
						"Something went wrong while dispatching, on the party side, data received on the connector side.",
						e);
			}

		/* Send parcels. */
		final InfrastructureException[] exception = new InfrastructureException[1];
		for (final P parcel : parcels)
			new Thread() {
				@Override
				public void run() {
					try {
						infrastructure.send(parcel);
					} catch (final InfrastructureException e) {
						if (exception[0] == null
								|| exception[0].isRecoverable())
							exception[0] = e;
					} finally {
						parcel.markDelivered();
					}
				};
			}.start();

		/* Wait until all parcels have been delivered. */
		deliveryTally.acquireUninterruptibly(nParcels);

		/* Process $exception. */
		if (exception[0] != null)
			throw new PartySideException(
					"Something went wrong while dispatching, on the party side, data received on the connector side.",
					exception[0]);
	}

	/**
	 * Gets the name of the party proxied by the proxy to which this party side
	 * belongs.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	String getPartyName() {
		return infrastructure.getPartyName();
	}

	//
	// EXCEPTIONS
	//

	public static class PartySideException extends Exception {
		private static final long serialVersionUID = 1L;

		private PartySideException(final String message, final String cause) {
			super(message + " " + cause);
		}

		private PartySideException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}