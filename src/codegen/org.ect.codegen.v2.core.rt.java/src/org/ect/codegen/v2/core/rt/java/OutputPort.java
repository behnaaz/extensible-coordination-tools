package org.ect.codegen.v2.core.rt.java;

import java.util.concurrent.TimeoutException;

import org.ect.codegen.v2.core.rt.java.internal.DefaultSyncPoint;
import org.ect.codegen.v2.core.rt.java.internal.SyncPoint;


public interface OutputPort extends Port {

	public Object take();

	public Object take(final long timeout) throws TimeoutException;
}

class OutputPortImpl extends PortImpl implements OutputPort {

	//
	// CONSTRUCTORS - PUBLIC
	//

	/**
	 * Constructs an output port.
	 */
	public OutputPortImpl() {
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
	OutputPortImpl(final SyncPoint point) {
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
	public Object take() {
		// if ((super.isOwnedByAnyThread() && !super.isOwnedByCurrentThread())
		// || !super.isOwnedByAnyThread() && !super.tryAcquire())
		// throw new IllegalStateException();

		return Ports.take(this);
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
	public Object take(final long timeout) throws TimeoutException {
		// if ((super.isOwnedByAnyThread() && !super.isOwnedByCurrentThread())
		// || !super.isOwnedByAnyThread() && !super.tryAcquire())
		// throw new IllegalStateException();

		return Ports.take(this, timeout);
	}
}