package org.ect.codegen.v2.core.rt.java.internal;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class BufferedSyncPoint extends AbstractSyncPoint {

	//
	// FIELDS
	//

	/**
	 * The buffer of this FIFO synchronization point. (An
	 * <code>AtomicReferenceArray&lt;Object&gt;</code> instead of an
	 * <code>Object[]</code> because writes must become visible immediately to
	 * all threads; atomicity (i.e., compare-and-set) does not play a role in
	 * this decision.)
	 */
	private final AtomicReferenceArray<Object> array;

	/**
	 * A take operation providing access to this synchronization point.
	 */
	private final Take take = new Take();

	/**
	 * A reference to a slot in the array {@link #array} from which to take
	 * next.
	 */
	private int takeIndex = 0;

	/**
	 * A write operation providing access to this synchronization point.
	 */
	private final Write write = new Write();

	/**
	 * A reference to a slot in the array {@link #array} to which to write next.
	 */
	private int writeIndex = 0;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a FIFO synchronization point with a buffer of capacity
	 * <code>capacity</code>.
	 * 
	 * @param capacity
	 *            The positive capacity.
	 * @throws IllegalArgumentException
	 *             If <code>capacity&lt;=0</code>.
	 */
	public BufferedSyncPoint(final int capacity) {
		if (capacity <= 0)
			throw new IllegalArgumentException();

		this.array = new AtomicReferenceArray<Object>(capacity);
	}

	//
	// METHODS
	//

	/**
	 * @deprecated This FIFO synchronization point does not support counting
	 *             take operations.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Override
	public int countTakes() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated This FIFO synchronization point does not support counting
	 *             write operations.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Override
	public int countWrites() {
		throw new UnsupportedOperationException();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Take getTake() {
		return take;
	}

	/**
	 * @deprecated This FIFO synchronization point does not support getting take
	 *             operations.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Override
	public Take[] getTakes() {
		throw new UnsupportedOperationException();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public Write getWrite() {
		return write;
	}

	/**
	 * @deprecated This FIFO synchronization point does not support getting
	 *             write operations.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Override
	public Write[] getWrites() {
		throw new UnsupportedOperationException();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean hasTake() {
		return array.get(takeIndex) == null;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean hasWrite() {
		return array.get(writeIndex) != null;
	}

	/**
	 * @deprecated This FIFO synchronization point does not support constructing
	 *             take operations.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Override
	public Take newTake() {
		return take;
//		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated This FIFO synchronization point does not support constructing
	 *             write operations.
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Override
	public Write newWrite(final Object item) {
		write.setItem(item);
		return write;
//		throw new UnsupportedOperationException();
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Blocks until the array slot referenced by {@link #takeIndex} contains an
	 * item.
	 * 
	 * <p>
	 * TODO: Make the implementation of this method less stupid.
	 * </p>
	 */
	void blockUntilItem() {
		while (array.get(takeIndex) == null)
			;
	}

	/**
	 * Blocks until the array slot referenced by {@link #writeIndex} is empty.
	 * 
	 * <p>
	 * TODO: Make the implementation of this method less stupid.
	 * </p>
	 */
	void blockUntilEmpty() {
		while (array.get(writeIndex) != null)
			;
	}

	//
	// CLASSES
	//

	public abstract class Operation extends AbstractOperation {

		/**
		 * @deprecated This operation does not support publishing.
		 * @throws UnsupportedOperationException
		 *             Always.
		 */
		@Override
		public void doPublish(long timeout) throws TimeoutException {
			throw new UnsupportedOperationException();
		}

		/**
		 * @deprecated This operation does not support publishing.
		 * @throws UnsupportedOperationException
		 *             Always.
		 */
		@Override
		public boolean isPublished() {
			throw new UnsupportedOperationException();
		}
	}

	public class Take extends Operation implements
			org.ect.codegen.v2.core.rt.java.internal.Take {

		/**
		 * @deprecated This take operation does not support getting its item.
		 * @throws UnsupportedOperationException
		 *             Always.
		 */
		@Override
		public Object getItem() {
//			throw new UnsupportedOperationException();
			return item;
		}
		
		volatile Object item = null;
		
		@Override
		public void doPublish(long timeout) throws TimeoutException {
			item = BufferedSyncPoint.this.write.getItem();
			BufferedSyncPoint.this.write.doResolve();
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public void doResolve() {
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public void setItem(final Object newItem) {

			/* Block until a slot becomes available. */
			BufferedSyncPoint.this.blockUntilEmpty();

			/* Set. */
			BufferedSyncPoint.this.array.set(BufferedSyncPoint.this.writeIndex,
					newItem);

			/* Advance $writeIndex. */
			BufferedSyncPoint.this.writeIndex = (BufferedSyncPoint.this.writeIndex + 1)
					% BufferedSyncPoint.this.array.length();

			/* Raise alarms. */
			BufferedSyncPoint.this.raiseAlarms();
		}
	}

	public class Write extends Operation implements
			org.ect.codegen.v2.core.rt.java.internal.Write {

		@Override
		public void doPublish(long timeout) throws TimeoutException {
					
			BufferedSyncPoint.this.take.setItem(object);
		}
		
		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public Object getItem() {

			/* Block until an item becomes available. */
			BufferedSyncPoint.this.blockUntilItem();

			/* Get. */
			return array.get(takeIndex);
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public void doResolve() {

			/* Clear array slot. */
			BufferedSyncPoint.this.array.set(BufferedSyncPoint.this.takeIndex,
					null);

			/* Advance $takeIndex. */
			BufferedSyncPoint.this.takeIndex = (BufferedSyncPoint.this.takeIndex + 1)
					% BufferedSyncPoint.this.array.length();

			/* Raise alarms. */
			BufferedSyncPoint.this.raiseAlarms();
		}

		/**
		 * @deprecated This write operation does not support setting its item.
		 * @throws UnsupportedOperationException
		 *             Always.
		 */
		@Override
		public void setItem(final Object newItem) {
//			throw new UnsupportedOperationException();
			object = newItem;
		}
		
		volatile Object object = null;
	}
}
