package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;

public class ProxyViewTechnologyLabel extends AbstractWidgetWrapper<Label> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this technology-label
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a technology-label for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the technology-label to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private ProxyViewTechnologyLabel(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new Label(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;
		super.get().setText("Technology:");
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a technology-label for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A technology-label. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewTechnologyLabel newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewTechnologyLabel(widgets,
				widgets.simpleSettingsCanvas);
	}
}
