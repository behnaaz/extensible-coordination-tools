package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

public class CoreViewSimpleSettingsCanvas extends AbstractWidgetWrapper<Canvas> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this
	 * simple-settings-canvas belongs.
	 */
	@SuppressWarnings("unused")
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a simple-settings-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the simple-settings-canvas to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private CoreViewSimpleSettingsCanvas(
			final CoreViewWidgets widgets,
			final CoreViewMainTabFolder parent) {

		super(new Canvas(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		/* Set layout. */
		final GridLayout layout = new GridLayout(2, false);
		layout.marginTop = 10;
		layout.marginLeft = 5;
		layout.marginRight = 5;
		layout.marginBottom = 5;
		layout.verticalSpacing = 10;
		layout.horizontalSpacing = 10;
		super.get().setLayout(layout);
	}

	//
	// METHODS
	//

	/**
	 * Adds a horizontal separator to this canvas.
	 */
	public void addSeparator() {
		final Layout layout = super.get().getLayout();
		if (layout instanceof GridLayout)
			new Label(this.get(), SWT.SEPARATOR | SWT.HORIZONTAL)
					.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
							true, ((GridLayout) layout).numColumns, 1));
	}

	/**
	 * Adds an empty cell to this canvas.
	 */
	public void addEmptyCell() {
		new Label(this.get(), SWT.NULL).setVisible(false);
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a simple-settings-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return A simple-settings-canvas. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainTabFolder==null</code>.
	 */
	public static CoreViewSimpleSettingsCanvas newInstance(
			final CoreViewWidgets widgets) {

		if (widgets == null || widgets.mainTabFolder == null)
			throw new NullPointerException();

		return new CoreViewSimpleSettingsCanvas(widgets,
				widgets.mainTabFolder);
	}
}
