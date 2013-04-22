package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;

public class ProxyViewMainTabFolder extends AbstractWidgetWrapper<TabFolder> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this main-tab-folder
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a main-tab-folder for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the main-tab-folder to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private ProxyViewMainTabFolder(final ProxyViewWidgets widgets,
			final ProxyViewMainCanvas parent) {

		super(new TabFolder(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a main-tab-folder for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A main-tab-folder. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainCanvas==null</code>.
	 */
	public static ProxyViewMainTabFolder newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.mainCanvas == null)
			throw new NullPointerException();

		return new ProxyViewMainTabFolder(widgets, widgets.mainCanvas);
	}
}
