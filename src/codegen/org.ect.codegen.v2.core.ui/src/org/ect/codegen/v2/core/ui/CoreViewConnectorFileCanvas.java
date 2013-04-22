package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.ect.codegen.v2.core.ui.FileCanvas.Type;

public class CoreViewConnectorFileCanvas extends
		AbstractWidgetWrapper<FileCanvas> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this
	 * connector-file-canvas belongs.
	 */
	@SuppressWarnings("unused")
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a connector-file-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the connector-file-canvas to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private CoreViewConnectorFileCanvas(final CoreViewWidgets widgets,
			final CoreViewSimpleSettingsCanvas parent) {

		super(new FileCanvas(parent.get(), SWT.NULL, Type.FILE));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setFilterExtensions(new String[] { "*.reo", "*.*" });
		super.get().setEnabled(false);
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a connector-file-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return A connector-file-canvas. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static CoreViewConnectorFileCanvas newInstance(
			final CoreViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new CoreViewConnectorFileCanvas(widgets,
				widgets.simpleSettingsCanvas);
	}
}
