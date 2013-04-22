package org.ect.codegen.v2.proxy.rt.java;


public abstract class AbstractInvocation<P extends AbstractParcel> {

	//
	// FIELDS
	//

	/**
	 * The parcel associated with this invocation.
	 */
	private P parcel;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an invocation for the parcel <code>parcel</code>.
	 * 
	 * @param parcel
	 *            The parcel. Not <code>null</code>.
	 */
	protected AbstractInvocation(final P parcel) {
		if (parcel == null)
			throw new NullPointerException();

		this.parcel = parcel;
	}

	//
	// METHODS - PROTECTED
	//

	/**
	 * Gets the parcel associated with this invocation.
	 * 
	 * @return A parcel. Never <code>null</code>.
	 */
	protected P getParcel() {
		return parcel;
	}

	/**
	 * Performs this invocation and returns a result parcel.
	 * 
	 * @return A parcel or <code>null</code>.
	 * @throws InvocationException
	 *             If something goes wrong while performing.
	 */
	protected abstract P perform() throws InvocationException;

	//
	// EXCEPTIONS
	//

	public static class InvocationException extends Exception {
		private static final long serialVersionUID = 1L;

		protected InvocationException(final String message) {
			super(message);
		}

		protected InvocationException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
