package org.ect.codegen.v2.core.rt.java;

import java.util.concurrent.TimeoutException;

import org.ect.codegen.v2.core.rt.java.internal.Continuation;
import org.ect.codegen.v2.core.rt.java.internal.Take;
import org.ect.codegen.v2.core.rt.java.internal.Write;


public class Ports {

	//
	// CONSTRUCTORS
	//

	/**
	 * @deprecated Use this class only in a static way.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private Ports() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC - METHODS - PUBLIC
	//

	/**
	 * Constructs a duplex port.
	 * 
	 * @return A duplex port.
	 */
	public static DuplexPort newDuplexPort() {
		return new DuplexPortImpl();
	}

	/**
	 * Constructs an input port.
	 * 
	 * @return An input port.
	 */
	public static InputPort newInputPort() {
		return new InputPortImpl();
	}

	/**
	 * Constructs an output port.
	 * 
	 * @return An output port.
	 */
	public static OutputPort newOutputPort() {
		return new OutputPortImpl();
	}

	//
	// STATIC - METHODS - DEFAULT
	//

	/**
	 * Takes an item from this port, then runs the continuation
	 * <code>continuation</code>.
	 * 
	 * @param continuation
	 *            A continuation. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>continuation==null</code>.
	 */
	static void asyncTake(final Port port, final Continuation continuation) {
		if (continuation == null)
			throw new NullPointerException();

		new Thread() {
			@Override
			public void run() {
				final Object item = Ports.forceTake(port);
				continuation.run(item);
			}
		}.start();
	}

	/**
	 * Writes the item <code>item</code> on this port, then runs the
	 * continuation <code>continuation</code>.
	 * 
	 * @param item
	 *            An object. Not <code>null</code>.
	 * @param continuation
	 *            A continuation. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>item==null</code> or <code>continuation==null</code>
	 *             .
	 */
	static void asyncWrite(final Port port, final Object item,
			final Continuation continuation) {

		if (item == null || continuation == null)
			throw new NullPointerException();

		new Thread() {
			@Override
			public void run() {
				Ports.forceWrite(port, item);
				continuation.run(item);
			}
		}.start();
	}

	/**
	 * Casts the object <code>object</code> to a port.
	 * 
	 * @param object
	 * @return A port. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>!(object instanceof PortImpl)</code>.
	 */
	static PortImpl castToPortImpl(final Object object) {
		if (!(object instanceof PortImpl))
			throw new IllegalArgumentException();

		return (PortImpl) object;
	}

	/**
	 * Forces a take on this port (even if the current thread does not own this
	 * port).
	 * 
	 * @return An object. Never <code>null</code>.
	 */
	static Object forceTake(final Port port) {
		final Take take = castToPortImpl(port).point.newTake();
		while (true)
			try {
				take.doPublish(Long.MAX_VALUE);
				return take.getItem();
			} catch (final TimeoutException e) {
			}
	}

	/**
	 * Forces a write of the item <code>item</code> on this port (even if the
	 * current thread does not own this port).
	 * 
	 * @param item
	 *            An object. Possibly <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>item==null</code>.
	 */
	static void forceWrite(final Port port, final Object item) {
		if (item == null)
			throw new NullPointerException();

		final Write write = castToPortImpl(port).point.newWrite(item);
		while (true)
			try {
				write.doPublish(Long.MAX_VALUE);
				return;
			} catch (final TimeoutException e) {
			}
	}

	/**
	 * Takes an item from this port.
	 * 
	 * @return An object. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!isOwnedByCurrentThread()</code>.
	 * 
	 * @see #isOwnedByCurrentThread()
	 */
	static Object take(final Port port) {
		// if (!port.isOwnedByCurrentThread())
		// throw new IllegalStateException();

		while (true) {
			try {
				return take(port, Long.MAX_VALUE);
			} catch (final TimeoutException e) {
			}
		}
	}

	/**
	 * Takes an item from this port, or times out if <code>timeout</code>
	 * milliseconds have passed.
	 * 
	 * <p>
	 * The deadline imposed by <code>timeout</code> is indicative rather than
	 * precise: if passed, this method throws an exception <em>only as soon as
	 * possible</em>. This method cannot give any guarantees on when this will
	 * happen.
	 * </p>
	 * 
	 * @param timeout
	 *            The positive timeout.
	 * @return An object. Never <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>timeout&lt;=0</code>.
	 * @throws IllegalStateException
	 *             If <code>!isOwnedByCurrentThread()</code>.
	 * @throws TimeoutException
	 *             If <code>timeout</code> milliseconds have passed.
	 * 
	 * @see #isOwnedByCurrentThread()
	 */
	static Object take(final Port port, final long timeout)
			throws TimeoutException {
		// if (!port.isOwnedByCurrentThread())
		// throw new IllegalStateException();
		if (timeout <= 0)
			throw new IllegalArgumentException();

		final Take take = castToPortImpl(port).point.newTake();
		take.doPublish(timeout);
		return take.getItem();
	}

	/**
	 * Writes the item <code>item</code> on this port.
	 * 
	 * @param item
	 *            An object. Not <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!isOwnedByCurrentThread()</code>.
	 * @throws NullPointerException
	 *             If <code>item==null</code>.
	 * 
	 * @see #isOwnedByCurrentThread()
	 */
	static void write(final Port port, final Object item) {
		while (true) {
			try {
				write(port, item, Long.MAX_VALUE);
				return;
			} catch (final TimeoutException e) {
			}
		}
	}

	/**
	 * Writes the item <code>item</code> on this port, or times out if
	 * <code>timeout</code> milliseconds have passed.
	 * 
	 * <p>
	 * The deadline imposed by <code>timeout</code> is indicative rather than
	 * precise: if passed, this method throws an exception <em>only as soon as
	 * possible</em>. This method cannot give any guarantees on when this will
	 * happen.
	 * </p>
	 * 
	 * @param item
	 *            An object. Not <code>null</code>.
	 * @param timeout
	 *            The positive timeout.
	 * @throws IllegalArgumentException
	 *             If <code>timeout&lt;=0</code>.
	 * @throws IllegalStateException
	 *             If <code>!isOwnedByCurrentThread()</code>.
	 * @throws NullPointerException
	 *             If <code>item==null</code>.
	 * @throws TimeoutException
	 *             If <code>timeout</code> milliseconds have passed.
	 * 
	 * @see #isOwnedByCurrentThread()
	 */
	static void write(final Port port, final Object item, final long timeout)
			throws TimeoutException {
		if (item == null)
			throw new NullPointerException();
		if (timeout <= 0)
			throw new IllegalArgumentException();
		// if (!port.isOwnedByCurrentThread())
		// throw new IllegalStateException();

		final Write write = castToPortImpl(port).point.newWrite(item);
		write.doPublish(timeout);
	}
}
