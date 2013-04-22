package org.ect.codegen.v2.proxy.rt.java.idl;

import java.util.concurrent.Semaphore;

import org.ect.codegen.v2.proxy.rt.java.AbstractParcel;
import org.ect.codegen.v2.proxy.rt.java.Constants;


public class CORBAParcel extends AbstractParcel {

	//
	// FIELDS
	//

	/**
	 * The values of the arguments of the operation to invoke.
	 */
	private final Object[] argumentValues;

	/**
	 * The name of the operation to invoke.
	 */
	private final String operationName;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a CORBA parcel containing the item <code>item</code>
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
	 *            construct is delivered.
	 * @throws CORBAParcelException
	 *             If something goes wrong while constructing.
	 * @throws IllegalArgumentException
	 *             If <code>portName.isEmpty()</code>.
	 * @throws NullPointerException
	 *             If <code>portName==null</code> or <code>item==null</code> or
	 *             <code>deliveryTally==null</code>.
	 * 
	 * @see String#isEmpty()
	 */
	CORBAParcel(final String portName, final Object item,
			final Semaphore deliveryTally) throws CORBAParcelException {

		super(portName, item, deliveryTally);

		try {
			this.operationName = portName.substring(
					Constants.PROXY_VERTEX_NAME_PREFIX.length()).toLowerCase();
		} catch (final IndexOutOfBoundsException e) {
			throw new CORBAParcelException("The port named \"" + portName
					+ "\" encodes no operation name.");
		}

		if (item instanceof Object[]) {
			final Object[] argumentValues = (Object[]) item;
			this.argumentValues = new Object[argumentValues.length];
			System.arraycopy(argumentValues, 0, this.argumentValues, 0,
					argumentValues.length);
		} else
			this.argumentValues = new Object[] { item };
	}

	//
	// METHODS
	//

	/**
	 * Gets the values of the arguments of the operation to invoke.
	 * 
	 * @return An array. Not <code>null</code>.
	 */
	public Object[] getArgumentValues() {
		return argumentValues;
	}

	/**
	 * Gets the name of the operation to invoke.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getOperationName() {
		return operationName;
	}

	//
	// EXCEPTIONS
	//

	public static class CORBAParcelException extends Exception {
		private static final long serialVersionUID = 1L;

		private CORBAParcelException(final String message) {
			super(message);
		}

	}
}
