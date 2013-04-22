package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;

public class ProxyViewSimAutLabel extends AbstractWidgetWrapper<Label> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this sim-aut-label
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a sim-aut-label for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the sim-aut-label to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private ProxyViewSimAutLabel(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new Label(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;
		super.get().setText("Simulation automaton:");
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a sim-aut-label for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A sim-aut-label. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewSimAutLabel newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewSimAutLabel(widgets,
				widgets.simpleSettingsCanvas);
	}
}