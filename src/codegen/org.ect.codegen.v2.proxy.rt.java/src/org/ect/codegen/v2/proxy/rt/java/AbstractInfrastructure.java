package org.ect.codegen.v2.proxy.rt.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public abstract class AbstractInfrastructure<P extends AbstractParcel> {

	//
	// FIELDS
	//

	/**
	 * A queue buffering exceptions thrown by this infrastructure.
	 */
	private ConcurrentLinkedQueue<InfrastructureException> exceptions = new ConcurrentLinkedQueue<AbstractInfrastructure.InfrastructureException>();

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a shallow infrastructure.
	 */
	protected AbstractInfrastructure() {
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the name of the party targeted by this infrastructure.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public abstract String getPartyName();

	/**
	 * Constructs parcel containing the item <code>item</code>
	 * <em>associated</em> with the port named <code>portName</code>. Here,
	 * "associated" means that one of the following holds:
	 * <ul>
	 * <li>{@link #item} has been taken from this port somewhere in the past; or
	 * <li>{@link #item} will be written on this port somewhere in the future.
	 * </ul>
	 * 
	 * @param portName
	 *            The name of the port. Not <code>null</code>.
	 * @param item
	 *            The item. Not <code>null</code>.
	 * @param deliveryTally
	 *            A tally to mark---release a permit---when the parcel to
	 *            construct is delivered.
	 * @return A parcel. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws InfrastructureException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>item==null</code> or
	 *             <code>deliveryTally==null</code>.
	 */
	public abstract P newParcel(final String portName, final Object item,
			final Semaphore deliveryTally) throws InfrastructureException;

	/**
	 * Receives a parcel, blocking until one is available.
	 * 
	 * @return A parcel. Never <code>null</code>.
	 * @throws InfrastructureException
	 *             If something goes wrong before, while, or after receiving.
	 */
	public abstract P receive() throws InfrastructureException;

	/**
	 * Sends the parcel <code>parcel</code>, blocking until delivery.
	 * 
	 * @param parcel
	 *            The parcel. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>parcel==null</code>.
	 * @throws InfrastructureException
	 *             If something goes wrong before, while, or after sending.
	 */
	public abstract void send(final P parcel) throws InfrastructureException;

	/**
	 * Gets the exceptions thrown by this infrastructure since the previous
	 * invocation of this method.
	 * 
	 * @return A list. Never <code>null</code>.
	 */
	public List<InfrastructureException> getExceptions() {
		final List<InfrastructureException> list = new ArrayList<InfrastructureException>();

		InfrastructureException exception;
		while ((exception = exceptions.poll()) != null)
			list.add(exception);

		return list;
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * Buffers the exception <code>exception</code> in a queue of exceptions
	 * thrown by this infrastructure.
	 * 
	 * @param exception
	 */
	protected void bufferException(final InfrastructureException exception) {
		exceptions.offer(exception);
	}

	//
	// EXCEPTIONS
	//

	public static class InfrastructureException extends Exception {
		private static final long serialVersionUID = 1L;

		private boolean isRecoverable = false;

		protected InfrastructureException(final String message) {
			super(message);
		}

		protected InfrastructureException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		public boolean isRecoverable() {
			return isRecoverable;
		}

		public void setRecoverable() {
			isRecoverable = true;
		}
	}
}