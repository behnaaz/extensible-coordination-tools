package org.ect.codegen.v2.core.rt.java.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractSyncPoint implements SyncPoint {

	//
	// FIELDS
	//

	/**
	 * The per-thread alarm of this synchronization point.
	 */
	private final ThreadLocal<Alarm> alarm = new ThreadLocal<Alarm>() {
		@Override
		protected Alarm initialValue() {
			final Alarm alarm = new Alarm();
			synchronized (AbstractSyncPoint.this.alarms) {
				AbstractSyncPoint.this.alarms.add(alarm);
			}

			return alarm;
		}
	};

	/**
	 * The alarms associated with this synchronization point.
	 */
	private final List<Alarm> alarms = new ArrayList<Alarm>();

	/**
	 * The lock of this synchronization point.
	 */
	private final ReentrantLock lock = new ReentrantLock();

	/**
	 * The rank of this synchronization point (orders synchronization points).
	 */
	private final Rank rank = Rank.newInstance();

	//
	// METHODS - PUBLIC
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!(point instanceof AbstractSyncPoint)</code>.
	 */
	@Override
	public final int compareTo(final SyncPoint point) {
		if (point == null)
			throw new NullPointerException();
		if (!(point instanceof AbstractSyncPoint))
			throw new IllegalArgumentException();

		return rank.compareTo(((AbstractSyncPoint) point).rank);
	}

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
	public final boolean equals(final Object object) {
		if (object == null)
			throw new NullPointerException();

		return object instanceof AbstractSyncPoint
				&& equals((AbstractSyncPoint) object);
	}

	/**
	 * Checks if this synchronization point equals the synchronization point
	 * <code>point</code>. In that case, they have equal ranks.
	 * 
	 * @param point
	 *            A synchronization point. Not <code>null</code>.
	 * @return <code>true</code> if <code>this</code> equals <code>point</code>;
	 *         <code>false</code> otherwise.
	 * @throws NullPointerException
	 *             If <code>point==null</code>.
	 */
	public final boolean equals(final AbstractSyncPoint point) {
		if (point == null)
			throw new NullPointerException();

		return rank.equals(point.rank);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public final int hashCode() {
		return rank.hashCode();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public final void lock() {
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
	public final void unlock() {
		while (lock.isHeldByCurrentThread())
			lock.unlock();
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public final void raiseAlarms() {
		synchronized (alarms) {
			for (final Alarm a : alarms) {
				if (a.isSet())
					a.raise();
			}
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
	public final void setAlarm(final Semaphore beepBeepBeep,
			final boolean[] flags, final int flagsIndex) {

		this.alarm.get().set(beepBeepBeep, flags, flagsIndex);
	}

	//
	// STATIC
	//

	/**
	 * Unlocks the synchronization points in the collection
	 * <code>collection</code>.
	 * 
	 * @param collection
	 *            The collection. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>collection==null</code> or
	 *             <code>collection.contains(null)</code>
	 * 
	 * @see Collection#contains(Object).
	 */
	public static void unlockAll(
			final Collection<? extends SyncPoint> collection) {

		if (collection == null || collection.contains(null))
			throw new NullPointerException();

		for (final SyncPoint p : collection)
			p.unlock();
	}
}

class Alarm {

	//
	// FIELDS
	//

	/**
	 * Flag indicating that this alarm has gone off (in which case this
	 * semaphore holds more than 0 permits) or not (0 permits).
	 */
	private volatile Semaphore beepBeepBeep;

	/**
	 * Flags that this alarm can manipulate when it goes off.
	 */
	private volatile boolean[] flags;

	/**
	 * The index of one particular flag in {@link #flags} that this alarm sets
	 * if it goes off.
	 */
	private volatile int flagsIndex;

	//
	// METHODS
	//

	/**
	 * Sets this alarm.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this alarm; the
	 * calling method is responsible for synchronizing access.
	 * </p>
	 * 
	 * @param beepBeepBeep
	 *            Flag indicating that this alarm has gone off (in which case
	 *            this semaphore holds more than 0 permits) or not (0 permits).
	 * @param flags
	 *            Flags that this alarm can manipulate when it goes off.
	 * @param flagsIndex
	 *            The index of one particular flag in {@link #flags} that this
	 *            alarm raises if it goes off.
	 */
	public void set(final Semaphore beepBeepBeep, final boolean[] flags,
			final int flagsIndex) {

		this.beepBeepBeep = beepBeepBeep;
		this.flagsIndex = flagsIndex;
		this.flags = flags;
	}

	public void set(final Semaphore beepBeepBeep,
			final AtomicReferenceArray<Boolean> flags, final int flagsIndex) {

		this.beepBeepBeep = beepBeepBeep;
		this.flagsIndex = flagsIndex;
	}

	/**
	 * Checks if this alarm is set.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this alarm; the
	 * calling method is responsible for synchronizing access.
	 * </p>
	 * 
	 * @return <code>true</code> if this alarm is set; <code>false</code>
	 *         otherwise.
	 */
	public boolean isSet() {
		return flags != null;
	}

	/**
	 * Raises this alarm.
	 * 
	 * <p>
	 * This method <em>neither</em> locks <em>nor</em> unlocks this alarm; the
	 * calling method is responsible for synchronizing access.
	 * </p>
	 * 
	 * @throws IllegalStateException
	 *             If <code>!isSet()</code>.
	 */
	public void raise() {
		if (!isSet())
			throw new IllegalStateException();

		if (!flags[flagsIndex]) {
			flags[flagsIndex] = true;
			beepBeepBeep.release();
		}
	}
}

class Rank implements Comparable<Rank> {

	//
	// FIELDS
	//

	/**
	 * The major component of this rank.
	 */
	final long x;

	/**
	 * The minor component of this rank.
	 */
	final int y;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a two dimensional rank based on <code>x</code> and
	 * <code>y</code>.
	 * 
	 * @param x
	 *            The major component.
	 * @param y
	 *            The minor component.
	 */
	private Rank(final long x, final int y) {
		this.x = x;
		this.y = y;
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
	 * @throws NullPointerException
	 *             If <code>rank==null</code>.
	 */
	@Override
	public int compareTo(final Rank rank) {
		if (rank == null)
			throw new NullPointerException();

		if (x == rank.x)
			if (y == rank.y)
				return 0;
			else
				return y < rank.y ? -1 : 1;
		else
			return x < rank.x ? -1 : 1;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             If <code>object==null</code>.
	 */
	@Override
	public boolean equals(final Object object) {
		if (object == null)
			throw new NullPointerException();

		return object instanceof Rank && equals((Rank) object);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * 
	 * @throws NullPointerException
	 *             If <code>rank==null</code>.
	 */
	public boolean equals(final Rank rank) {
		if (rank == null)
			throw new NullPointerException();

		return x == rank.x && y == rank.y;
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
		return (int) x;
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
		return x + "." + y;
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The previous time at which a rank was generated.
	 */
	private static long previousTime;

	/**
	 * The number of ranks that have been generated already at time
	 * {@link #previousTime}.
	 */
	private static int nRanksAtPreviousTime;

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a rank.
	 * 
	 * <p>
	 * This method synchronizes on the class {@link Rank}.
	 * 
	 * @return A rank. Never <code>null</code>.
	 */
	static synchronized Rank newInstance() {

		/* Create a rank. */
		long time = System.currentTimeMillis();
		int nRanks = previousTime == time ? nRanksAtPreviousTime + 1 : 1;
		Rank rank = new Rank(time, nRanks);

		/* Update $previousTime and $nRanksAtPreviousTime. */
		previousTime = time;
		nRanksAtPreviousTime = nRanks;

		/* Return. */
		return rank;
	}
}