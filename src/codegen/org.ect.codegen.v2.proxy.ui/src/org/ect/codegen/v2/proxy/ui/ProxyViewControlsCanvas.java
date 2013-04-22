package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.ActiveReoDiagramEditor;
import org.ect.codegen.v2.core.ui.Workspace;
import org.ect.codegen.v2.orch.gen.java.OrchJavaGeneratorProgramArguments;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgramArguments;
import org.ect.codegen.v2.proxy.ui.ProxyViewTechnologyCombo.Item;

public class ProxyViewControlsCanvas extends AbstractWidgetWrapper<Canvas> {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this
	 * simple-settings-canvas belongs.
	 */
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a simple-settings-canvas for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the simple-settings-canvas to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	public ProxyViewControlsCanvas(final ProxyViewWidgets widgets,
			final ProxyViewMainCanvas parent) {

		super(new Canvas(parent.get(), SWT.NULL));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		/* Set layout. */
		final GridLayout layout = new GridLayout(3, false);
		layout.marginTop = 0;
		layout.marginLeft = 5;
		layout.marginRight = 5;
		layout.marginBottom = 5;
		layout.verticalSpacing = 10;
		layout.horizontalSpacing = 10;
		super.get().setLayout(layout);
		super.get().setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, true));
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

	/**
	 * Extracts arguments for the "orcgen"-program based on the current content
	 * of the widgets.
	 * 
	 * @return Arguments. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong while extracting.
	 */
	public OrchJavaGeneratorProgramArguments extractOrcgenArguments() throws Exception {

		/* Set "compile" and "destinationPath" flags. */
		String destinationPath = null;
		boolean compile = false;
		switch (widgets.destinationCombo.getSelectedItem()) {
		case WORKSPACE:
			destinationPath = Workspace.getCanonicalPath();
			break;
		case FILE_SYSTEM:
			destinationPath = widgets.destinationFileCanvas.get().getText();
			compile = true;
			break;
		}

		/* Set "modulePath" flag. */
		final String modulePath = ActiveReoDiagramEditor
				.extractCanonicalPath();

		/* Set "orcResourcesPaths" flag. */
		final String orcResourcesPaths = null;

		/* Set "orcSourcesPaths" flag. */
		final String orcSourcesPaths = null;

		/* Set "orcTemplatesPaths" flag. */
		final String orcTemplatesPaths = null;

		/* Set "print" flag. */
		final boolean print = false;

		/* Return. */
		return new OrchJavaGeneratorProgramArguments(compile, destinationPath, modulePath,
				orcResourcesPaths, orcSourcesPaths, orcTemplatesPaths, print);
	}

	/**
	 * Extracts arguments for the "proxygen"-program based on the current
	 * content of the widgets.
	 * 
	 * @return Arguments. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong while extracting.
	 */
	public ProxyJavaGeneratorProgramArguments extractProxygenArguments() throws Exception {

		/* Set "corba" flag. */
		final boolean corba = widgets.technologyCombo.getSelectedItem() == Item.CORBA;

		/* Set "defaultSimAut" flag. */
		final boolean defaultSimAut = widgets.simAutCombo.getSelectedItem() == org.ect.codegen.v2.proxy.ui.ProxyViewSimAutCombo.Item.DEFAULT;

		/* Set "compile" and "destinationPath" flags. */
		boolean compile = false;
		String destinationPath = null;
		switch (widgets.destinationCombo.getSelectedItem()) {
		case WORKSPACE:
			destinationPath = Workspace.getCanonicalPath();
			break;
		case FILE_SYSTEM:
			destinationPath = widgets.destinationFileCanvas.get().getText();
			compile = true;
			break;
		}

		/* Set "interfaceName" flag. */
		final String interfaceName = widgets.partyCombo.getText();

		/* Set "interfacesPath" flag. */
		final String interfacesPath = widgets.interfaceFileCanvas.get()
				.getText();

		/* Set "print" flag. */
		final boolean print = false;

		/* Set "proxyResourcesPaths" flag. */
		final String proxyResourcesPaths = null;

		/* Set "proxySourcesPaths" flag. */
		final String proxySourcesPaths = null;

		/* Set "proxyTemplatePath" flag. */
		final String proxyTemplatesPaths = null;

		/* Set "simAutPath" flag. */
		final String simAutPath = widgets.simAutFileCanvas.getText();

		/* Set "wsdl" flag. */
		final boolean wsdl = widgets.technologyCombo.getSelectedItem() == Item.WSDL;

		/* Return. */
		return new ProxyJavaGeneratorProgramArguments(compile, corba, defaultSimAut,
				destinationPath, interfaceName, interfacesPath, print,
				proxyResourcesPaths, proxySourcesPaths, proxyTemplatesPaths,
				simAutPath, wsdl);
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a simple-settings-canvas for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A simple-settings-canvas. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainTabFolder==null</code>.
	 */
	public static ProxyViewControlsCanvas newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.mainTabFolder == null)
			throw new NullPointerException();

		return new ProxyViewControlsCanvas(widgets, widgets.mainCanvas);
	}
}
