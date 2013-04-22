package org.ect.codegen.v2.core.rt.java;

import java.util.ArrayDeque;
import java.util.Deque;

import org.ect.codegen.v2.core.rt.java.Connector.State;


public class History {

	//
	// FIELDS
	//

	/**
	 * The deque storing the steps in this history.
	 */
	private final Deque<Step> deque = new ArrayDeque<Step>();

	/**
	 * The next sequence number for a step.
	 */
	private long nextSequenceNumber = 1;

	/**
	 * The configuration of this history.
	 */
	private final Configuration configuration = new Configuration();

	//
	// METHODS - PUBLIC
	//

	/**
	 * Gets the number of steps extending this history since its construction.
	 * 
	 * <p>
	 * This method synchronizes on this history.
	 * </p>
	 * 
	 * @return A nonnegative integer. Never <code>null</code>.
	 */
	public synchronized long countSteps() {
		return nextSequenceNumber - 1;
	}

	/**
	 * Gets the configuration of this history.
	 * 
	 * @return A configuration. Never <code>null</code>.
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Gets the last step of this history.
	 * 
	 * <p>
	 * This method synchronizes on this history.
	 * </p>
	 * 
	 * @return A step. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>isEmpty()</code>.
	 */
	public synchronized Step getLastStep() {
		if (isEmpty())
			throw new IllegalStateException();

		return deque.peekFirst();
	}

	/**
	 * Gets the next sequence number of this history.
	 * 
	 * <p>
	 * This method synchronizes on this history.
	 * </p>
	 * 
	 * @return A positive long.
	 */
	public synchronized long getNextSequenceNumber() {
		return nextSequenceNumber;
	}

	/**
	 * Checks if this history is empty.
	 * 
	 * <p>
	 * This method synchronizes on this history.
	 * </p>
	 * 
	 * @return <code>true</code> if this history is empty; <code>false</code>
	 *         otherwise.
	 */
	public synchronized boolean isEmpty() {
		return deque.isEmpty();
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Extends this history with a new step to the target state
	 * <code>target</code> involving the firing <code>firing</code>.
	 * 
	 * <p>
	 * Whether or not this history actually records the target state and the
	 * firing depends on its configuration.
	 * </p>
	 * 
	 * <p>
	 * This method synchronizes on this history.
	 * </p>
	 * 
	 * @param target
	 *            The target state of the new step. Not <code>null</code>.
	 * @param firing
	 *            The firing of the new step. Not <code>null</code>.
	 * @throws IllegalArgumentException
	 *             If <code>firing instanceof Failure</code>.
	 * @throws NullPointerException
	 *             If <code>target==null</code> or <code>firing==null</code>.
	 */
	synchronized void extend(final State target, final Firing firing) {
		if (target == null || firing == null)
			throw new NullPointerException();
		if (firing instanceof Failure)
			throw new IllegalArgumentException();

		final Step step = new Step(nextSequenceNumber++);
		synchronized (configuration) {
			if (configuration.recordFirings)
				step.setFiring(firing);
			if (configuration.recordTargets)
				step.setTarget(target);
			if (configuration.recordTimes)
				step.setTime(System.currentTimeMillis());

			if (configuration.capacity > 0) {
				while (configuration.capacity <= deque.size())
					deque.pollLast();

				if (configuration.capacity > 0)
					deque.offerFirst(step);
			}
		}
	}

	//
	// CLASSES
	//

	class Configuration {

		//
		// FIELDS
		//

		/**
		 * The capacity of the history configured by this configuration.
		 */
		private long capacity = Long.MAX_VALUE;

		/**
		 * Flag indicating if the history configured by this configuration
		 * should record firings.
		 */
		private boolean recordFirings = true;

		/**
		 * Flag indicating if the history configured by this configuration
		 * should record target states.
		 */
		private boolean recordTargets = true;

		/**
		 * Flag indicating if the history configured by this configuration
		 * should record recording times.
		 */
		private boolean recordTimes = true;

		//
		// METHODS
		//

		/**
		 * Gets the capacity of the history configured by this configuration.
		 * 
		 * <p>
		 * This method synchronizes on this configuration.
		 * </p>
		 * 
		 * @return A nonnegative long.
		 */
		public synchronized long getCapacity() {
			return capacity;
		}

		/**
		 * Sets the capacity of this history.
		 * 
		 * <p>
		 * This method synchronizes on this configuration.
		 * </p>
		 * 
		 * @param newCapacity
		 *            The nonnegative new capacity.
		 * @throws IllegalArgumentException
		 *             If <code>newCapacity&lt;0</code>.
		 */
		public synchronized void setCapacity(long newCapacity) {
			if (newCapacity < 0)
				throw new IllegalArgumentException();

			capacity = newCapacity;
		}

		/**
		 * Gets the flag indicating if the history configured by this
		 * configuration should record firings.
		 * 
		 * <p>
		 * This method synchronizes on this configuration.
		 * </p>
		 * 
		 * @return <code>true</code> if the flag is raised; <code>false</code>
		 *         otherwise.
		 */
		public synchronized boolean getRecordFirings() {
			return recordFirings;
		}

		/**
		 * Sets the flag indicating if the history configured by this
		 * configuration should record firings.
		 * 
		 * <p>
		 * This method synchronizes on this configuration.
		 * </p>
		 * 
		 * @param flag
		 *            The flag.
		 */
		public synchronized void setRecordFirings(final boolean flag) {
			recordFirings = flag;
		}

		/**
		 * Gets the flag indicating if the history configured by this
		 * configuration should record target states.
		 * 
		 * @return <code>true</code> if the flag is raised; <code>false</code>
		 *         otherwise.
		 */
		public synchronized boolean getRecordTargets() {
			return recordTargets;
		}

		/**
		 * Sets the flag indicating if the history configured by this
		 * configuration should record target states.
		 * 
		 * <p>
		 * This method synchronizes on this configuration.
		 * </p>
		 * 
		 * @param flag
		 *            The flag.
		 */
		public synchronized void setRecordTargets(final boolean flag) {
			recordTargets = flag;
		}

		/**
		 * Gets the flag indicating if the history configured by this
		 * configuration should record recording times.
		 * 
		 * <p>
		 * This method synchronizes on this configuration.
		 * </p>
		 * 
		 * @return <code>true</code> if the flag is raised; <code>false</code>
		 *         otherwise.
		 */
		public synchronized boolean getRecordTimes() {
			return recordTimes;
		}

		/**
		 * Sets the flag indicating if the history configured by this
		 * configuration should record recording times.
		 * 
		 * <p>
		 * This method synchronizes on this configuration.
		 * </p>
		 * 
		 * @param flag
		 *            The flag.
		 */
		public synchronized void setRecordTimes(final boolean flag) {
			recordTimes = flag;
		}
	}
}