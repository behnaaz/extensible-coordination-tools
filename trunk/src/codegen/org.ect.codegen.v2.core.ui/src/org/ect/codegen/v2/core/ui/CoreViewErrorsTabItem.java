package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;

public class CoreViewErrorsTabItem extends AbstractWidgetWrapper<TabItem> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this errors-tab-item
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a errors-tab-item for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the errors-tab-item to construct. Not
	 *            <code>null</code>.
	 * @param control
	 *            The control of the errors-tab-item to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code> or
	 *             <code>control==null</code>.
	 */
	private CoreViewErrorsTabItem(final CoreViewWidgets widgets,
			final CoreViewMainTabFolder parent,
			final CoreViewErrorsCanvas control) {

		super(new TabItem(parent.get(), SWT.NULL));
		if (widgets == null || control == null)
			throw new NullPointerException();

		this.widgets = widgets;
		super.get().setText("Error Messages");
		super.get().setControl(control.get());
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a errors-tab-item for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return An errors-tab-item. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainTabFolder==null</code> or
	 *             <code>widgets.errorsCanvas==null</code>.
	 */
	public static CoreViewErrorsTabItem newInstance(
			final CoreViewWidgets widgets) {

		if (widgets == null || widgets.mainTabFolder == null
				|| widgets.errorsCanvas == null)
			throw new NullPointerException();

		return new CoreViewErrorsTabItem(widgets, widgets.mainTabFolder,
				widgets.errorsCanvas);
	}
}
