package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;

public class ProxyViewMainCanvas extends AbstractWidgetWrapper<Canvas> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this main-canvas
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a main-canvas for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the main-canvas to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	public ProxyViewMainCanvas(final Composite parent,
			final ProxyViewWidgets widgets) {

		super(new Canvas(parent, SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		/* Set layout. */
		final GridLayout layout = new GridLayout();
		layout.marginBottom = 10;
		layout.marginLeft = 10;
		layout.marginRight = 10;
		layout.marginTop = 10;
		layout.horizontalSpacing = 10;
		layout.verticalSpacing = 10;
		layout.numColumns = 1;
		super.get().setLayout(layout);
	}

	//
	// METHODS
	//

	/**
	 * Resizes this main-canvas.
	 * 
	 * <p>
	 * <em>Shorthand for:</em>
	 * 
	 * <p>
	 * <center>
	 * <code>super.get().setSize(super.get().computeSize(SWT.DEFAULT,SWT.DEFAULT))</code>
	 * </center>
	 * 
	 * @see Control#computeSize(int, int)
	 * @see Control#setSize(Point)
	 * @see AbstractWidgetWrapper#get()
	 */
	public void resize() {
		super.get().setSize(super.get().computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
