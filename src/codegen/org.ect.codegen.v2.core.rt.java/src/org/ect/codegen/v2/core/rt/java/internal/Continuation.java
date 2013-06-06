package org.ect.codegen.v2.core.rt.java.internal;

public abstract class Continuation {

	/**
	 * Runs this continuation.
	 * 
	 * @param item
	 *            The item involved in the I/O operation that preceded this
	 *            continuation. Not <code>null</code>.
	 */
	public abstract void run(final Object item);
	
	public static Continuation EMPTY_CONTINUATION = new Continuation() {
		public void run(final Object item) {
		}
	};
}