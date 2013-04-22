package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class CoreViewDestinationLabel extends AbstractWidgetWrapper<Label> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this destination-label
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a destination-label for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the destination-label to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private CoreViewDestinationLabel(final CoreViewWidgets widgets,
			final CoreViewSimpleSettingsCanvas parent) {

		super(new Label(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;
		super.get().setText("Destination:");
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a destination-label for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return A destination-label. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static CoreViewDestinationLabel newInstance(
			final CoreViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new CoreViewDestinationLabel(widgets,
				widgets.simpleSettingsCanvas);
	}
}
