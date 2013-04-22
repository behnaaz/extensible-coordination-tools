package org.ect.codegen.v2.core.rt.java.internal;

import java.util.concurrent.Semaphore;

public interface SyncPoint extends Comparable<SyncPoint> {

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	public int compareTo(final SyncPoint point);

	/**
	 * Counts the take operations of this synchronization point.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return A nonnegative integer.
	 */
	public int countTakes();

	/**
	 * Counts the write operations of this synchronization point.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return A nonnegative integer.
	 */
	public int countWrites();

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * <p>
	 * 
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	@Override
	public boolean equals(final Object object);

	/**
	 * Gets a take operation, blocking until one becomes available.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return A take operation. Never <code>null</code>.
	 */
	public Take getTake();

	/**
	 * Gets the take operations of this synchronization point.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return An array of take operations. Never <code>null</code>.
	 */
	public Take[] getTakes();

	/**
	 * Gets a write operation, blocking until one becomes available.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return A write operation. Never <code>null</code>.
	 */
	public Write getWrite();

	/**
	 * Gets the write operations of this synchronization point.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return An array of write operations. Never <code>null</code>.
	 */
	public Write[] getWrites();

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public int hashCode();

	/**
	 * Checks if this synchronization point has a take operation.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return <code>true</code> if this synchronization point has a take
	 *         operation; <code>false</code> otherwise.
	 */
	public boolean hasTake();

	/**
	 * Checks if this synchronization point has a write operation.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @return <code>true</code> if this synchronization point has a write
	 *         operation; <code>false</code> otherwise.
	 */
	public boolean hasWrite();

	/**
	 * Constructs a take operation on this synchronization point.
	 * 
	 * @return A take operation. Never <code>null</code>.
	 */
	public Take newTake();

	/**
	 * Constructs a write operation on this synchronization point.
	 * 
	 * @param item
	 *            The item of the write operation to construct. Not
	 *            <code>null</code>.
	 * @return A write operation. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>item==null</code>.
	 */
	public Write newWrite(final Object item);

	/**
	 * Locks this synchronization point.
	 */
	public void lock();

	/**
	 * Raises the alarms.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 */
	public void raiseAlarms();

	/**
	 * Sets the alarm of the current thread.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this
	 * synchronization point; the calling method is responsible for
	 * synchronizing access.
	 * </p>
	 * 
	 * @param beepBeepBeep
	 *            Flag indicating that this alarm has gone off (in which case
	 *            this semaphore holds more than 0 permits) or not (0 permits).
	 *            Not <code>null</code>.
	 * @param flags
	 *            Flags that the alarm to set can manipulate when it goes off.
	 *            Not <code>null</code>.
	 * @param flagsIndex
	 *            The index of one particular flag in <code>flags</code> that
	 *            the alarm to set raises when it goes off.
	 * @throws ArrayIndexOutOfBoundsException
	 *             If <code>flagsIndex&lt;0</code> or
	 *             <code>flagsIndex&gt;=flags.length</code>.
	 * @throws NullPointerException
	 *             If <code>beepBeepBeep==null</code> or
	 *             <code>flags==null</code> or <code>seqNumber==null</code>.
	 */
	public void setAlarm(final Semaphore beepBeepBeep, final boolean[] flags,
			final int flagsIndex);

	/**
	 * Unlocks this synchronization point.
	 */
	public void unlock();
}
