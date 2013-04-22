package org.ect.codegen.v2.proxy.rt.java;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractParcel {

	//
	// FIELDS
	//

	/**
	 * A tally to mark---release a permit---when this parcel delivered.
	 */
	private final Semaphore deliveryTally;

	/**
	 * The item contained in this parcel. Possibly <code>null</code>.
	 */
	private final Object item;

	/**
	 * Flag indicating whether this parcel is delivered.
	 */
	private final AtomicBoolean isDelivered = new AtomicBoolean(false);

	/**
	 * The name of the port <em>associated</em> with this parcel. Here,
	 * "associated" means that one of the following holds:
	 * <ul>
	 * <li>{@link #item} has been taken from this port somewhere in the past; or
	 * <li>{@link #item} will be written on this port somewhere in the future.
	 * </ul>
	 */
	private final String portName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a parcel containing the item <code>item</code>
	 * <em>associated</em> with the port named <code>portName</code>. Here,
	 * "associated" means that one of the following holds:
	 * <ul>
	 * <li>{@link #item} has been taken from this port somewhere in the past; or
	 * <li>{@link #item} will be written on this port somewhere in the future.
	 * </ul>
	 * 
	 * @param portName
	 *            The name of the port. Not <code>null</code> or empty.
	 * @param item
	 *            The item. Not <code>null</code>.
	 * @param deliveryTally
	 *            A tally to mark---release a permit---when the parcel to
	 *            construct either is delivered or cannot be delivered.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>item==null</code> or
	 *             <code>deliveryTally==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	protected AbstractParcel(final String portName, final Object item,
			final Semaphore deliveryTally) {

		if (portName == null || item == null || deliveryTally == null)
			throw new NullPointerException();
		if (portName.isEmpty())
			throw new IllegalArgumentException();

		this.portName = portName;
		this.item = item;
		this.deliveryTally = deliveryTally;
	}

	//
	// METHODS
	//

	/**
	 * Gets the item contained in this parcel.
	 * 
	 * @return An object. Possibly <code>null</code>.
	 */
	public Object getItem() {
		return item;
	}

	/**
	 * Gets the name of the port associated with this parcel. Here, "associated"
	 * means that one of the following holds:
	 * <ul>
	 * <li>{@link #item} has been taken from this port somewhere in the past; or
	 * <li>{@link #item} will be written on this port somewhere in the future.
	 * </ul>
	 * 
	 * @return A string. Never <code>null</code> or empty.
	 */
	public String getPortName() {
		return portName;
	}

	/**
	 * Checks if this parcel is delivered.
	 * 
	 * @return <code>true</code> if this parcel is delivered; <code>false</code>
	 *         otherwise.
	 */
	public boolean isDelivered() {
		return isDelivered.get();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "<" + portName + ", " + item.toString() + ">";
	}

	//
	// METHODS - DEFAULT
	//

	/**
	 * Marks the parcel delivered by marking its ticket.
	 * 
	 * @throws IllegalStateException
	 *             If <code>isDelivered()</code>.
	 * 
	 * @see #isDelivered()
	 */
	void markDelivered() {
		if (isDelivered())
			throw new IllegalStateException();

		deliveryTally.release();
		isDelivered.set(true);
	}
}