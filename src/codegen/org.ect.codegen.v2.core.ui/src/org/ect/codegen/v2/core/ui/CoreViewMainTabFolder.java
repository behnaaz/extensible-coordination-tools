package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TabFolder;

public class CoreViewMainTabFolder extends AbstractWidgetWrapper<TabFolder> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this main-tab-folder
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a main-tab-folder for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the main-tab-folder to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private CoreViewMainTabFolder(final CoreViewWidgets widgets,
			final CoreViewMainCanvas parent) {

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
	 * Constructs a main-tab-folder for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return A main-tab-folder. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainCanvas==null</code>.
	 */
	public static CoreViewMainTabFolder newInstance(
			final CoreViewWidgets widgets) {

		if (widgets == null || widgets.mainCanvas == null)
			throw new NullPointerException();

		return new CoreViewMainTabFolder(widgets, widgets.mainCanvas);
	}
}
