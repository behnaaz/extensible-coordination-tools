package org.ect.codegen.v2.core.ui;

import java.lang.reflect.Field;

public abstract class AbstractWidgetWrapperRecord {

	//
	// METHODS
	//

	/**
	 * Checks if this record is initialized. In that case, all the widgets in
	 * this record are constructed.
	 * 
	 * @return <code>true</code> if all the widgets in this record are
	 *         initialized; <code>false</code> otherwise.
	 */
	public boolean isInitialized() {
		try {
			final Field[] fields = this.getClass().getFields();
			for (final Field f : fields) {
				final Object field = f.get(this);
				if (field == null) {
					return false;
				}
			}

			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Initializes the listeners of those widgets in this record that implement
	 * the <code>IListenerWidget</code> interface.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!isInitialized</code>.
	 * @throws WidgetRecordException
	 *             If something goes wrong while initializing.
	 * 
	 * @see #isInitialized()
	 */
	public void initializeListeners() throws WidgetRecordException {
		if (!isInitialized())
			throw new IllegalStateException();

		try {
			final Field[] fields = this.getClass().getFields();
			for (final Field f : fields) {
				final Object field = f.get(this);
				if (field instanceof IListenerWidget)
					((IListenerWidget) field).initializeListeners();
			}
		} catch (final Exception e) {
			throw new WidgetRecordException(
					"I failed to initialize the listener(s) of at least one of the widgets.",
					e);
		}
	}

	/**
	 * Reports the message <code>message</code> to all those widgets in this
	 * record that implement the <code>IThrowableHandlerWidget</code> interface.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * 
	 * <p>
	 * <center><code>reportError(new Exception(message),showDialog)</code>
	 * </center>
	 * 
	 * <p>
	 * 
	 * @param message
	 *            The message. Not <code>null</code>.
	 * @param showDialog
	 *            Flag indicating whether this method shows dialogs about
	 *            reporting <code>throwable</code>.
	 * @throws IllegalStateException
	 *             If <code>!isInitialized</code>.
	 * @throws NullPointerException
	 *             If <code>message==null</code>.
	 * @throws WidgetRecordException
	 *             If something goes wrong while reporting.
	 * 
	 * @see #isInitialized()
	 */
	public void reportError(final String message, boolean showDialog)
			throws WidgetRecordException {

		if (message == null)
			throw new NullPointerException();
		if (!isInitialized())
			throw new IllegalStateException();

		reportError(new Exception(message), showDialog);
	}

	/**
	 * Reports the throwable <code>throwable</code> to all those widgets in this
	 * record that implement the <code>IThrowableHandlerWidget</code> interface.
	 * 
	 * @param throwable
	 *            The throwable. Not <code>null</code>.
	 * @param showDialog
	 *            Flag indicating whether this method shows dialogs about
	 *            reporting <code>throwable</code>.
	 * @throws IllegalStateException
	 *             If <code>!isInitialized</code>.
	 * @throws NullPointerException
	 *             If <code>throwable==null</code>.
	 * @throws WidgetRecordException
	 *             If something goes wrong while reporting.
	 * 
	 * @see #isInitialized()
	 */
	public void reportError(final Throwable throwable, boolean showDialog)
			throws WidgetRecordException {

		if (throwable == null)
			throw new NullPointerException();
		if (!isInitialized())
			throw new IllegalStateException();

		try {
			final Field[] fields = this.getClass().getFields();
			for (final Field f : fields) {
				final Object field = f.get(this);
				if (field instanceof IThrowableHandlerWidget)
					((IThrowableHandlerWidget) field).addThrowable(throwable,
							showDialog);
			}
		} catch (final Exception e) {
			throw new WidgetRecordException("I failed to report the error \""
					+ throwable + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class WidgetRecordException extends Exception {
		private static final long serialVersionUID = 1L;

		public WidgetRecordException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}