package org.ect.codegen.v2.core.rt.java;

import java.util.concurrent.TimeoutException;

import org.ect.codegen.v2.core.rt.java.internal.DefaultSyncPoint;
import org.ect.codegen.v2.core.rt.java.internal.SyncPoint;


public interface InputPort extends Port {

	public void write(final Object item);

	public void write(final Object item, final long timeout)
			throws TimeoutException;
}

class InputPortImpl extends PortImpl implements InputPort {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an input port.
	 */
	public InputPortImpl() {
		super(new DefaultSyncPoint());
	}

	//
	// CONSTRUCTORS - DEFAULT
	//

	/**
	 * Invokes <code>super(point)</code>.
	 * 
	 * @see PortImpl#Port(SyncPoint)
	 */
	InputPortImpl(final SyncPoint point) {
		super(point);
	}

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws IllegalStateException
	 *             If
	 *             <code>isOwnedByAnyThread()&&!isOwnedByCurrentThread()</code>
	 *             or <code>!isOwnedByAnyThread()&&!tryAcquire()</code>.
	 */
	@Override
	public void write(final Object item) {
		// if ((super.isOwnedByAnyThread() && !super.isOwnedByCurrentThread())
		// || !super.isOwnedByAnyThread() && !super.tryAcquire())
		// throw new IllegalStateException();

		Ports.write(this, item);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws IllegalStateException
	 *             If
	 *             <code>isOwnedByAnyThread()&&!isOwnedByCurrentThread()</code>
	 *             or <code>!isOwnedByAnyThread()&&!tryAcquire()</code>.
	 */
	@Override
	public void write(final Object item, final long timeout)
			throws TimeoutException {

		// if ((super.isOwnedByAnyThread() && !super.isOwnedByCurrentThread())
		// || !super.isOwnedByAnyThread() && !super.tryAcquire())
		// throw new IllegalStateException();

		Ports.write(this, item, timeout);
	}
}