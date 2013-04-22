package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.widgets.Widget;

public abstract class AbstractWidgetWrapper<W extends Widget> {

	//
	// FIELDS
	//

	/**
	 * The widget wrapped.
	 */
	private final W widget;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a wrapper around the widget <code>widget</code>.
	 * 
	 * @param widget
	 *            The widget. Not <code>null</code>.
	 */
	public AbstractWidgetWrapper(final W widget) {
		if (widget == null)
			throw new NullPointerException();

		this.widget = widget;
	}

	//
	// METHODS
	//

	/**
	 * Gets the widget wrapped by this wrapper.
	 * 
	 * @return A widget. Never <code>null</code>.
	 */
	public final W get() {
		return widget;
	}
}
