package org.ect.codegen.v2.core.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.ViewPart;

public class CoreView extends ViewPart {

	//
	// FIELDS
	//

	/**
	 * The control to get focus initially.
	 */
	private Control controlToGetFocus;

	//
	// METHODS
	//

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(final Composite parent) {
		try {
			parent.setLayout(new FillLayout());
			final ScrolledComposite scrolledComposite = new ScrolledComposite(
					parent, SWT.H_SCROLL | SWT.V_SCROLL);

			/* Instantiate a set of widgets. */
			final CoreViewWidgets widgets = new CoreViewWidgets();

			/* Add a main canvas to $scrolledComposite. */
			widgets.mainCanvas = new CoreViewMainCanvas(widgets,
					scrolledComposite);

			scrolledComposite.setContent(widgets.mainCanvas.get());
			scrolledComposite.setMinWidth(320);
			scrolledComposite.setExpandHorizontal(true);

			/* Add a main tab folder to $mainCanvas. */
			widgets.mainTabFolder = CoreViewMainTabFolder.newInstance(widgets);

			/* Add a simple tab item to $mainTabFolder. */
			widgets.simpleSettingsCanvas = CoreViewSimpleSettingsCanvas
					.newInstance(widgets);
			widgets.simpleSettingsTabItem = CoreViewSimpleSettingsTabItem
					.newInstance(widgets);

			widgets.connectorLabel = CoreViewConnectorLabel
					.newInstance(widgets);
			widgets.connectorCombo = CoreViewConnectorCombo
					.newInstance(widgets);
			widgets.simpleSettingsCanvas.addEmptyCell();
			widgets.connectorFileCanvas = CoreViewConnectorFileCanvas
					.newInstance(widgets);

			widgets.simpleSettingsCanvas.addSeparator();

			widgets.destinationLabel = CoreViewDestinationLabel
					.newInstance(widgets);
			widgets.destinationCombo = CoreViewDestinationCombo
					.newInstance(widgets);
			widgets.simpleSettingsCanvas.addEmptyCell();
			widgets.destinationFileCanvas = CoreViewDestinationFileCanvas
					.newInstance(widgets);

			/* Add an error messages tab item to $mainTabFolder. */
			widgets.errorsCanvas = CoreViewErrorsCanvas.newInstance(widgets);
			widgets.errorsTabItem = CoreViewErrorsTabItem.newInstance(widgets);

			/* Add a generate button to $mainCanvas. */
			widgets.generateButton = CoreViewGenerateButton
					.newInstance(widgets);

			/* Validate widgets, and add listeners. */
			if (!widgets.isInitialized())
				throw new Exception("Initialization failed.");

			widgets.initializeListeners();

			/* Resize. */
			widgets.mainCanvas.resize();

			/* Set $controlWithInitialFocus. */
			controlToGetFocus = widgets.generateButton.get();

		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sets the focus.
	 */
	public void setFocus() {
		controlToGetFocus.setFocus();
	}

	//
	// STATIC - FIELDS
	//

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.ect.codegen.v2.core.ui.CoreView";
}