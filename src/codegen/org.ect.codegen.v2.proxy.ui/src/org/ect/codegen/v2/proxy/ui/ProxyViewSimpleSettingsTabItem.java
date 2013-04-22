package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;

public class ProxyViewSimpleSettingsTabItem extends
		AbstractWidgetWrapper<TabItem> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this
	 * simple-settings-tab-item belongs.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a simple-settings-tab-item for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the simple-settings-tab-item to construct. Not
	 *            <code>null</code>.
	 * @param control
	 *            The control of the simple-settings-tab-item to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code> or
	 *             <code>control==null</code>.
	 */
	public ProxyViewSimpleSettingsTabItem(final ProxyViewWidgets widgets,
			final ProxyViewMainTabFolder parent,
			final ProxyViewSimpleSettingsCanvas control) {

		super(new TabItem(parent.get(), SWT.NULL));
		if (widgets == null || control == null)
			throw new NullPointerException();

		this.widgets = widgets;
		super.get().setText("Settings");
		super.get().setControl(control.get());
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a simple-settings-tab-item for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A simple-settings-tab-item. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainTabFolder==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewSimpleSettingsTabItem newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.mainTabFolder == null
				|| widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewSimpleSettingsTabItem(widgets,
				widgets.mainTabFolder, widgets.simpleSettingsCanvas);
	}
}
