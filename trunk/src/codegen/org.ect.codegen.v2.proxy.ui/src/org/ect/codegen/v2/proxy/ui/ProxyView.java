package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.part.ViewPart;
import org.ect.codegen.v2.proxy.rt.java.Log4j;

public class ProxyView extends ViewPart {

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
			final ProxyViewWidgets widgets = new ProxyViewWidgets();

			/* Add a main canvas to $scrolledComposite. */
			widgets.mainCanvas = new ProxyViewMainCanvas(scrolledComposite,
					widgets);

			scrolledComposite.setContent(widgets.mainCanvas.get());
			scrolledComposite.setMinWidth(480);
			scrolledComposite.setExpandHorizontal(true);

			/* Add a main tab folder to $mainCanvas. */
			widgets.mainTabFolder = ProxyViewMainTabFolder
					.newInstance(widgets);

			/* Add a simple tab item to $mainTabFolder. */
			widgets.simpleSettingsCanvas = ProxyViewSimpleSettingsCanvas
					.newInstance(widgets);
			widgets.simpleTabItem = ProxyViewSimpleSettingsTabItem
					.newInstance(widgets);

			widgets.technologyLabel = ProxyViewTechnologyLabel
					.newInstance(widgets);
			widgets.technologyCombo = ProxyViewTechnologyCombo
					.newInstance(widgets);

			widgets.interfaceLabel = ProxyViewInterfaceLabel
					.newInstance(widgets);
			widgets.interfaceFileCanvas = ProxyViewInterfaceFileCanvas
					.newInstance(widgets);

			widgets.partyLabel = ProxyViewPartyLabel.newInstance(widgets);
			widgets.partyCombo = ProxyViewPartyCombo.newInstance(widgets);

			widgets.simpleSettingsCanvas.addSeparator();

			widgets.simAutLabel = ProxyViewSimAutLabel.newInstance(widgets);
			widgets.simAutCombo = ProxyViewSimAutCombo.newInstance(widgets);
			widgets.simpleSettingsCanvas.addEmptyCell();
			widgets.simAutFileCanvas = ProxyViewSimAutFileCanvas
					.newInstance(widgets);

			widgets.simpleSettingsCanvas.addSeparator();

			widgets.destinationLabel = ProxyViewDestinationLabel
					.newInstance(widgets);
			widgets.destinationCombo = ProxyViewDestinationCombo
					.newInstance(widgets);
			widgets.simpleSettingsCanvas.addEmptyCell();
			widgets.destinationFileCanvas = ProxyViewDestinationFileCanvas
					.newInstance(widgets);

			/* Add an error messages tab item to $mainTabFolder. */
			widgets.errorsCanvas = ProxyViewErrorsCanvas
					.newInstance(widgets);
			widgets.errorsTabItem = ProxyViewErrorsTabItem
					.newInstance(widgets);

			/* Add controls to $mainCanvas. */
			widgets.controlsCanvas = ProxyViewControlsCanvas
					.newInstance(widgets);

			widgets.drawButton = ProxyViewDrawButton.newInstance(widgets);
			widgets.generateProxyButton = ProxyViewGenerateProxyButton
					.newInstance(widgets);
			widgets.generateOrchestrationButton = ProxyViewGenerateOrchestrationButton
					.newInstance(widgets);

			/* Validate widgets, and add listeners. */
			if (!widgets.isInitialized())
				throw new Exception("Initialization failed.");

			widgets.initializeListeners();

			/* Initialize. */
			widgets.technologyCombo.get().notifyListeners(SWT.Selection,
					new Event());
			widgets.mainCanvas.resize();

			/* Set $controlWithInitialFocus. */
			controlToGetFocus = widgets.technologyCombo.get();

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
	public static final String ID = "org.ect.codegen.v2.proxy.ui.ProxyView";

	//
	// STATIC - Initializers
	//

	/**
	 * Disable Log4j.
	 */
	static {
		Log4j.disable();
	}
}