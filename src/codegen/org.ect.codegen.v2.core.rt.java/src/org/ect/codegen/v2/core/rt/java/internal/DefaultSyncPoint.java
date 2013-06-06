package org.ect.codegen.v2.core.rt.java.internal;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeoutException;

public class DefaultSyncPoint extends AbstractSyncPoint {

	//
	// FIELDS
	//

	/**
	 * The take operations of this synchronization point.
	 */
	private final LinkedBlockingQueue<DefaultSyncPoint.Take> takes = new LinkedBlockingQueue<DefaultSyncPoint.Take>();

	/**
	 * The write operations of this synchronization point.
	 */
	private final LinkedBlockingQueue<DefaultSyncPoint.Write> writes = new LinkedBlockingQueue<DefaultSyncPoint.Write>();

	//
	// METHODS - PUBLIC
	//

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
	public int countTakes() {
		return takes.size();
	}

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
	public int countWrites() {
		return writes.size();
	}

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
	 * 
	 * @see #hasTake()
	 */
	public Take getTake() {
		while (!hasTake())
			;

		return takes.peek();
	}

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
	public Take[] getTakes() {
		final Take[] array = new Take[takes.size()];
		takes.toArray(array);
		return array;
	}

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
	 * @throws IllegalStateException
	 *             If <code>!hasWrite()</code>.
	 * 
	 * @see #hasWrite()
	 */
	public Write getWrite() {
		while (!hasWrite())
			;
		
		return writes.peek();
	}

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
	public Write[] getWrites() {
		final Write[] array = new Write[writes.size()];
		writes.toArray(array);
		return array;
	}

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
	public boolean hasTake() {
		return !takes.isEmpty();
	}

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
	public boolean hasWrite() {
		return !writes.isEmpty();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Take newTake() {
		return new Take();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Write newWrite(final Object item) {
		return new Write(item);
	}

	//
	// CLASSES
	//

	public abstract class Operation<O extends Operation<O>> extends
			AbstractOperation {

		//
		// FIELDS - DEFAULT
		//

		/**
		 * The item involved in this operation.
		 */
		volatile Object item;

		//
		// FIELDS - PRIVATE
		//

		/**
		 * The collection in which to publish this operation.
		 */
		private final Collection<O> collection;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs an operation.
		 * 
		 * @param collection
		 *            The collection in which to publish the operation to
		 *            construct. Not <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>collection==null</code>.
		 */
		public Operation(final Collection<O> collection) {
			if (collection == null)
				throw new NullPointerException();

			this.collection = collection;
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
		 * <p>
		 * This method synchronizes on this operation.
		 * </p>
		 */
		@Override
		public final synchronized Object getItem() {
			return item;
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 * 
		 * <p>
		 * This method synchronizes on this operation.
		 * </p>
		 */
		@Override
		@SuppressWarnings("unchecked")
		public final synchronized void doPublish(final long timeout)
				throws TimeoutException {

			if (timeout <= 0)
				throw new IllegalArgumentException();
			if (isPublished())
				throw new IllegalStateException();

			/* [LOCK] */
			DefaultSyncPoint.this.lock();
			try {

				/* Resolve immediately, or add to $collection. */
				if (resolveNow() || !collection.add((O) this))
					return;

				super.isPublished = true;

				/* Raise alarms. */
				DefaultSyncPoint.this.raiseAlarms();
			} finally {

				/* [UNLOCK] */
				DefaultSyncPoint.this.unlock();
			}

			/* Wait. */
			final long deadline = Math.max(
					System.currentTimeMillis() + timeout, Long.MAX_VALUE);

			while (isPublished() && deadline > System.currentTimeMillis())
				try {
					super.wait(deadline - System.currentTimeMillis());
				} catch (final Exception e) {
				}

			if (timeout == Long.MAX_VALUE)
				return;

			/* [LOCK] */
			DefaultSyncPoint.this.lock();
			try {
				if (!isResolved()) {
					doResolve();
					throw new TimeoutException();
				}
			} finally {

				/* [UNLOCK] */
				DefaultSyncPoint.this.unlock();
			}
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 * 
		 * <p>
		 * This method synchronizes on this operation.
		 * </p>
		 */
		@Override
		public final synchronized void doResolve() {
			if (isResolved())
				throw new IllegalStateException();

			super.isResolved = true;

			if (isPublished()) {
				collection.remove(this);
				super.isPublished = false;

				super.notifyAll();
			}
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 * 
		 * <p>
		 * This method synchronizes on this operation.
		 * </p>
		 */
		@Override
		public final synchronized void setItem(final Object newItem) {
			item = newItem;
		}

		//
		// METHODS - PROTECTED
		//

		/**
		 * Resolves this operation immediately (e.g., by pairing it with another
		 * pending operation), or returns <code>false</code> if this cannot
		 * happen.
		 * 
		 * <p>
		 * This method <em>neither</em> locks <em>nor</em> unlocks the
		 * synchronization point of this operation; the calling method is
		 * responsible for synchronizing access.
		 * </p>
		 * 
		 * @return <code>true</code> if this operation resolved immediately;
		 *         <code>false</code> otherwise.
		 */
		protected abstract boolean resolveNow();
	}

	public class Take extends Operation<Take> implements
			org.ect.codegen.v2.core.rt.java.internal.Take {

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a take operation.
		 * 
		 * @see Operation#Operation(LockableCollection)
		 */
		public Take() {
			super(DefaultSyncPoint.this.takes);
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
		 */
		@Override
		public final boolean resolveNow() {
			if (!DefaultSyncPoint.this.writes.isEmpty()) {
				final DefaultSyncPoint.Write write = DefaultSyncPoint.this.writes
						.iterator().next();

				this.item = write.item;
				this.doResolve();
				write.doResolve();
				// write.unpublishThenNotifyAll();
				return true;
			}

			return false;
		}
	}

	public class Write extends Operation<Write> implements
			org.ect.codegen.v2.core.rt.java.internal.Write {

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a write operation offering the item <code>item</code>.
		 * 
		 * @param item
		 *            The item. Possibly <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>item==null</code>.
		 */
		public Write(final Object item) {
			super(DefaultSyncPoint.this.writes);
			super.item = item;
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
		 */
		@Override
		public final boolean resolveNow() {
			if (!DefaultSyncPoint.this.takes.isEmpty()) {
				final DefaultSyncPoint.Take take = DefaultSyncPoint.this.takes
						.iterator().next();

				take.item = this.item;
				this.doResolve();
				take.doResolve();
				// take.unpublishThenNotifyAll();
				return true;
			}
			return false;
		}
	}
}