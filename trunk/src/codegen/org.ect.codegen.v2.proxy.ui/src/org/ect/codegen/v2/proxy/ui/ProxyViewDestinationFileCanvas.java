package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.FileCanvas;
import org.ect.codegen.v2.core.ui.FileCanvas.Type;

public class ProxyViewDestinationFileCanvas extends
		AbstractWidgetWrapper<FileCanvas> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this
	 * destination-file-canvas belongs.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a destination-file-canvas for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the destination-file-canvas to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private ProxyViewDestinationFileCanvas(
			final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new FileCanvas(parent.get(), SWT.NULL, Type.DIRECTORY));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setEnabled(false);
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a destination-file-canvas for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A destination-file-canvas. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewDestinationFileCanvas newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewDestinationFileCanvas(widgets,
				widgets.simpleSettingsCanvas);
	}
}
