package org.ect.codegen.v2.proxy.rt.java;

import java.util.concurrent.atomic.AtomicInteger;

import org.ect.codegen.v2.core.rt.java.InputPort;
import org.ect.codegen.v2.core.rt.java.OutputPort;
import org.ect.codegen.v2.core.rt.java.Port;


public class RequestFactory {

	//
	// FIELDS
	//

	/**
	 * A counter to increment once this request resolves.
	 */
	private final AtomicInteger counter;

	/**
	 * Flag indicating whether this request has resolved.
	 */
	private boolean hasResolved = false;

	/**
	 * The item involved in this request.
	 */
	private Object item;

	/**
	 * The name of the port involved in this request.
	 */
	private String portName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a void request on the port named <code>portName</code>.
	 * 
	 * @param portName
	 *            The name of the port. Not <code>null</code> or empty.
	 * @param counter
	 *            A counter to increment once this request resolves. Not
	 *            <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>counter==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	private RequestFactory(final String portName, final AtomicInteger counter) {
		if (portName == null || counter == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();

		this.counter = counter;
		this.portName = portName;
	}

	//
	// CLASSES
	//

	abstract class Request implements Runnable {

		/**
		 * Gets the item involved in this request.
		 * 
		 * @return The item; <code>null</code> in the case of an unresolved take
		 *         request.
		 */
		public Object getItem() {
			return item;
		}

		/**
		 * Gets the port involved in this request.
		 * 
		 * @return The port. Never <code>null</code>.
		 */
		public abstract Port getPort();

		/**
		 * Gets the name of the port involved in this request.
		 * 
		 * @return The port name. Never <code>null</code> or empty.
		 */
		public String getPortName() {
			return portName;
		}

		/**
		 * Checks if this request has resolved.
		 * 
		 * <p>
		 * This method synchronizes on this request.
		 * 
		 * @return <code>true</code> if this request has resolved;
		 *         <code>false</code> otherwise.
		 */
		public synchronized boolean hasResolved() {
			return hasResolved;
		}
	}

	final class Take extends Request {

		//
		// FIELDS
		//

		/**
		 * The output port involved in this take request.
		 */
		final OutputPort port;

		//
		// CONSTRUCTOR
		//

		/**
		 * Constructs a take request involving the port <code>port</code>.
		 * 
		 * @param port
		 *            The port. Not <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>port==null</code>.
		 */
		private Take(final OutputPort port) {
			if (port == null)
				throw new NullPointerException();

			this.port = port;
		}

		//
		// METHODS
		//

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 */
		@Override
		public OutputPort getPort() {
			return port;
		}

		/**
		 * Runs this take request.
		 */
		@Override
		public void run() {

			/* Perform the requested operation. */
			item = port.take();

			/* Set $hasResolved. */
			synchronized (this) {
				hasResolved = true;
			}

			/* Notify all threads waiting on $resolvedCounter. */
			synchronized (counter) {
				counter.incrementAndGet();
				counter.notifyAll();
			}
		}

		@Override
		public String toString() {
			return "(take " + portName + ")";
		}
	}

	final class Write extends Request {

		//
		// FIELDS
		//

		/**
		 * The input port involved in this write request.
		 */
		final InputPort port;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a write request involving the port <code>port</code> and
		 * the item <code>item</code>.
		 * 
		 * @param port
		 *            The port. Not <code>null</code>.
		 * @param item
		 *            The item. Not <code>null</code>.
		 * 
		 * @throws NullPointerException
		 *             If <code>port==null</code> or <code>item==null</code>.
		 */
		private Write(final InputPort port, final Object item) {
			if (port == null || item == null)
				throw new NullPointerException();

			this.port = port;
			RequestFactory.this.item = item;
		}

		//
		// METHODS
		//

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 */
		@Override
		public InputPort getPort() {
			return port;
		}

		/**
		 * Runs this write operation.
		 */
		@Override
		public void run() {

			/* Perform the requested operation. */
			port.write(item);

			/* Set $hasResolved. */
			synchronized (this) {
				hasResolved = true;
			}

			/* Notify all threads waiting on $resolvedCounter. */
			synchronized (counter) {
				counter.incrementAndGet();
				counter.notifyAll();
			}
		}

		@Override
		public String toString() {
			return "(write " + portName + " " + item + ")";
		}
	}

	//
	// STATIC
	//

	/**
	 * Creates a request to take any item from the port <code>port</code> named
	 * <code>portName</code>.
	 * 
	 * @param portName
	 *            The name. Not <code>null</code>.
	 * @param port
	 *            The port. Not <code>null</code>.
	 * @param resolvedCounter
	 *            A counter to increment once the request to create resolves.
	 *            Not <code>null</code>.
	 * @return A request. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>port==null</code> or
	 *             <code>resolvedCounter==null</code>.
	 */
	public static Request take(final String portName, final OutputPort port,
			final AtomicInteger resolvedCounter) {

		if (portName == null || port == null || resolvedCounter == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();

		final RequestFactory factory = new RequestFactory(portName,
				resolvedCounter);

		final Take take = factory.new Take(port);
		new Thread(take).start();
		return take;
	}

	/**
	 * Creates a request to write <code>item</code> to the port
	 * <code>port</code> named <code>portName</code>.
	 * 
	 * @param portName
	 *            The name. Not <code>null</code>.
	 * @param port
	 *            The port. Not <code>null</code>.
	 * @param item
	 *            The item. Not <code>null</code>.
	 * @param resolvedCounter
	 *            A counter to increment once the request to create resolves.
	 *            Not <code>null</code>.
	 * @return A request. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>port==null</code> or
	 *             <code>item==null</code> or <code>resolvedCounter==null</code>
	 *             .
	 */
	public static Request write(String portName, InputPort port, Object item,
			AtomicInteger resolvedCounter) {

		if (portName == null || port == null || item == null
				|| resolvedCounter == null)
			throw new NullPointerException();

		final RequestFactory factory = new RequestFactory(portName,
				resolvedCounter);

		final Write write = factory.new Write(port, item);
		new Thread(write).start();
		return write;
	}
}