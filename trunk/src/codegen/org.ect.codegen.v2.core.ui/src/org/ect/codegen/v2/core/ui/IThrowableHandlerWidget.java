package org.ect.codegen.v2.core.ui;

public interface IThrowableHandlerWidget {

	/**
	 * Adds the throwable <code>throwable</code> to this widget.
	 * 
	 * <p>
	 * This method synchronizes on this widget.
	 * 
	 * @param throwable
	 *            The throwable. Not <code>null</code>.
	 * @param showDialog
	 *            Flag indicating whether this method shows a dialog about
	 *            adding <code>throwable</code>.
	 * @throws NullPointerException
	 *             If <code>throwable==null</code>.
	 * @throws ThrowableHandlerWidgetException
	 *             If something goes wrong while adding.
	 */
	public void addThrowable(final Throwable throwable, final boolean showDialog)
			throws ThrowableHandlerWidgetException;

	//
	// EXCEPTIONS
	//

	public static class ThrowableHandlerWidgetException extends Exception {
		private static final long serialVersionUID = 1L;

		public ThrowableHandlerWidgetException(final String message,
				final Throwable cause) {
			super(message, cause);
		}
	}
}
