package org.ect.codegen.v2.core.rt.java;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

import org.ect.codegen.v2.core.rt.java.Connector.State;
import org.ect.codegen.v2.core.rt.java.internal.DefaultSyncPoint;
import org.ect.codegen.v2.core.rt.java.internal.SyncPoint;
import org.ect.codegen.v2.core.rt.java.internal.Take;
import org.ect.codegen.v2.core.rt.java.internal.Write;

public interface Port extends Comparable<Port> {

	/**
	 * Acquires this port, blocking until it becomes available.
	 */
	public abstract void acquire();

	/**
	 * Counts the number of take operations pending on this port.
	 * 
	 * @return A nonnegative integer.
	 */
	public abstract int countTakes();

	/**
	 * Counts the number of write operations pending on this port.
	 * 
	 * @return A nonnegative integer.
	 */
	public abstract int countWrites();

	/**
	 * Gets the take operations pending on this port.
	 * 
	 * @return An array of take operations. Never <code>null</code>.
	 */
	public abstract Take[] getTakes();

	/**
	 * Gets the write operations pending on this port.
	 * 
	 * @return An array of write operations. Never <code>null</code>.
	 */
	public abstract Write[] getWrites();

	/**
	 * Checks if the current thread owns this port.
	 * 
	 * @return <code>true</code> if the current thread owns this port;
	 *         <code>false</code> otherwise.
	 */
	public abstract boolean isOwnedByCurrentThread();

	/**
	 * Checks if any thread owns this port.
	 * 
	 * @return <code>true</code> if any thread owns this port;
	 *         <code>false</code> otherwise.
	 */
	public abstract boolean isOwnedByAnyThread();

	/**
	 * Releases this port.
	 */
	public abstract void release();

	/**
	 * Tries to acquire this port now, returning immediately if another thread
	 * owns this port.
	 * 
	 * @return <code>true</code> if either acquiring succeeded or the current
	 *         thread already owned this port; <code>false</code> otherwise.
	 */
	public abstract boolean tryAcquire();
}

class PortImpl implements Port {

	//
	// FIELDS
	//

	/**
	 * The lock that threads must acquire before performing I/O operations on
	 * this port.
	 */
	final ReentrantLock lock = new ReentrantLock();

	/**
	 * The synchronization point to which this port provides access.
	 */
	final SyncPoint point;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a port.
	 */
	public PortImpl() {
		this.point = new DefaultSyncPoint();
	}

	/**
	 * Constructs a port providing access to the synchronization point
	 * <code>point</code>.
	 * 
	 * @param point
	 *            The synchronization point to which the port to construct
	 *            provides access. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>point==null</code>.
	 */
	protected PortImpl(final SyncPoint point) {
		if (point == null)
			throw new NullPointerException();

		this.point = point;
	}

	//
	// METHODS - PUBLIC
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public void acquire() {
		if (!isOwnedByCurrentThread())
			lock.lock();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int compareTo(final Port port) {
		if (port == null)
			throw new NullPointerException();

		return this.point.compareTo(Ports.castToPortImpl(port).point);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int countTakes() {
		return point.countTakes();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int countWrites() {
		return point.countWrites();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean equals(final Object object) {
		if (object == null)
			throw new NullPointerException();

		return object instanceof PortImpl && equals((PortImpl) object);
	}

	/**
	 * Checks if this port equals the port <code>port</code>. In that case, they
	 * provide access to the same synchronization point.
	 * 
	 * @param port
	 *            A port. Not <code>null</code>.
	 * @return <code>true</code> if <code>this</code> equals <code>port</code>;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>port==null</code>.
	 */
	public boolean equals(final PortImpl port) {
		if (port == null)
			throw new NullPointerException();

		return point.equals(port.point);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Take[] getTakes() {
		point.lock();
		try {
			return point.getTakes();
		} finally {
			point.unlock();
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Write[] getWrites() {
		point.lock();
		try {
			return point.getWrites();
		} finally {
			point.unlock();
		}
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int hashCode() {
		return point.hashCode();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean isOwnedByCurrentThread() {
		return lock.isHeldByCurrentThread();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean isOwnedByAnyThread() {
		return lock.isLocked();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public void release() {
		if (isOwnedByCurrentThread())
			lock.unlock();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + point.toString() + ")";
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean tryAcquire() {
		if (isOwnedByAnyThread())
			return isOwnedByCurrentThread();
		else
			return lock.tryLock();
	}

	//
	// DEFAULT
	//

	private volatile State writer;

	private volatile State taker;

	private final Semaphore newWriterFlag = new Semaphore(0);

	private final Semaphore newTakerFlag = new Semaphore(0);

	State getWriter() {
		if (!hasWriter())
			throw new IllegalStateException();
		
		return writer;
	}
	
	State getTaker() {
		if (!hasTaker())
			throw new IllegalStateException();
		
		return taker;
	}
	
	// State getLockedWriter() {
	// if (!hasWriter())
	// throw new IllegalStateException();
	//
	// writer.lock();
	// while (!writer.isActive()) {
	// writer.unlock();
	// while (true)
	// try {
	// newWriterFlag.acquire();
	// break;
	// } catch (InterruptedException e) {
	// }
	//
	// writer.lock();
	// }
	//
	// return writer;
	// }
	
	// State getLockedTaker() {
	// if (!hasTaker())
	// throw new IllegalStateException();
	//
	// taker.lock();
	// while (!taker.isActive()) {
	// taker.unlock();
	// while (true)
	// try {
	// newTakerFlag.acquire();
	// break;
	// } catch (InterruptedException e) {
	// }
	//
	// taker.lock();
	// }
	//
	// return taker;
	// }

	boolean hasTaker() {
		return taker != null;
	}

	boolean hasWriter() {
		return writer != null;
	}

	void setTaker(final State taker) {
		this.taker = taker;
		this.newTakerFlag.release();
		this.point.softRaiseAlarms();
	}

	void setWriter(final State writer) {
		this.writer = writer;
		this.newWriterFlag.release();
		this.point.softRaiseAlarms();
	}
}