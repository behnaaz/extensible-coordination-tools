package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.FileCanvas;
import org.ect.codegen.v2.core.ui.FileCanvas.Type;

public class ProxyViewSimAutFileCanvas extends
		AbstractWidgetWrapper<FileCanvas> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this
	 * sim-aut-file-canvas.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a sim-aut-file-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the sim-aut-file-canvas to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	public ProxyViewSimAutFileCanvas(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new FileCanvas(parent.get(), SWT.NULL, Type.FILE));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setFilterExtensions(new String[] { "*.simaut", "*.*" });
		disable();
	}

	//
	// METHODS
	//

	/**
	 * Disables this combo.
	 */
	public void disable() {
		super.get().setEnabled(false);
	}

	/**
	 * Enables this combo.
	 */
	public void enable() {
		super.get().setEnabled(true);
	}

	/**
	 * Gets the text of this canvas.
	 * 
	 * @return A string. Never <code>null</code>.
	 */
	public String getText() {
		return super.get().getText();
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a sim-aut-file-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return A sim-aut-file-canvas. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewSimAutFileCanvas newInstance(
			final ProxyViewWidgets widgets) {
		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewSimAutFileCanvas(widgets,
				widgets.simpleSettingsCanvas);
	}
}
