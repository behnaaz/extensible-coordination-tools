package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.ect.codegen.v2.core.ui.ErrorsCanvas.ErrorsCanvasException;

public class CoreViewErrorsCanvas extends
		AbstractWidgetWrapper<ErrorsCanvas> implements IThrowableHandlerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this errors-canvas
	 * belongs.
	 */
	@SuppressWarnings("unused")
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an errors-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of this errors-canvas. Not <code>null</code>.
	 * 
	 * @throws ErrorsCanvasException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private CoreViewErrorsCanvas(final CoreViewWidgets widgets,
			final CoreViewMainTabFolder parent) throws ErrorsCanvasException {

		super(new ErrorsCanvas(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;
		this.get()
				.setDialogText(
						"See the \"Error Messages\" tab item on the \"Code Generator\" view for more information.");
	}

	//
	// METHODS
	//

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public void addThrowable(final Throwable throwable, final boolean showDialog)
			throws ThrowableHandlerWidgetException {

		try {
			super.get().addThrowable(throwable, showDialog);
		} catch (final Exception e) {
			/* Ignore $e. */
		}
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs an errors-canvas for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return An errors-canvas. Never <code>null</code>.
	 * @throws ErrorsCanvasException
	 *             If something goes wrong while constructing.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainTabFolder==null</code>.
	 */
	public static CoreViewErrorsCanvas newInstance(
			final CoreViewWidgets widgets) throws ErrorsCanvasException {

		if (widgets == null || widgets.mainTabFolder == null)
			throw new NullPointerException();

		return new CoreViewErrorsCanvas(widgets, widgets.mainTabFolder);
	}
}
