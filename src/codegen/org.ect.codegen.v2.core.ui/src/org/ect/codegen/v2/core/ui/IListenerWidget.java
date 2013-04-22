package org.ect.codegen.v2.core.ui;

public interface IListenerWidget {

	/**
	 * Initializes the listeners of this widget.
	 * 
	 * @throws ListenerWidgetException
	 *             If something goes wrong while initializing.
	 */
	public void initializeListeners() throws ListenerWidgetException;

	//
	// EXCEPTIONS
	//

	public static class ListenerWidgetException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
