package org.ect.codegen.v2.proxy.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.FileCanvas;
import org.ect.codegen.v2.core.ui.FileCanvas.Type;
import org.ect.codegen.v2.core.ui.IListenerWidget;
import org.ect.codegen.v2.proxy.rt.java.Resource;
import org.ect.codegen.v2.proxy.rt.java.idl.CORBAComponent;
import org.ect.codegen.v2.proxy.rt.java.wsdl.WSDLServices;

public class ProxyViewInterfaceFileCanvas extends
		AbstractWidgetWrapper<FileCanvas> implements IListenerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this
	 * interface-file-canvas belongs.
	 */
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs an interfacce-file-canvas for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the interface-file-canvas to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	public ProxyViewInterfaceFileCanvas(final ProxyViewWidgets widgets,
			final ProxyViewSimpleSettingsCanvas parent) {

		super(new FileCanvas(parent.get(), SWT.NULL, Type.FILE));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;
	}

	//
	// METHODS
	//

	/**
	 * Clears this combo.
	 */
	public void clear() {
		super.get().setText("");
	}

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

	private AtomicInteger counter = new AtomicInteger(0);

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public void initializeListeners() {
		super.get().addModifyListener(new Runnable() {
			@Override
			public void run() {

				final Resource resource = new Resource(
						widgets.interfaceFileCanvas.get().getText());

				final Display display = ProxyViewInterfaceFileCanvas.this
						.get().getDisplay();

				final org.ect.codegen.v2.proxy.ui.ProxyViewTechnologyCombo.Item selectedItem = widgets.technologyCombo
						.getSelectedItem();

				final int sequenceNumber = counter.incrementAndGet();

				new Thread() {
					@Override
					public void run() {
						try {
							/* Compute a list of party names. */
							final List<String> partyNames = new ArrayList<String>();
							switch (selectedItem) {
							case CORBA:
								partyNames.addAll(new CORBAComponent(resource)
										.getInterfaceNames());
								break;
							case WSDL:
								partyNames.addAll(new WSDLServices(resource)
										.getInterfaceNames());
								break;
							}

							if (partyNames.isEmpty())
								throw new Exception("The interface resource \""
										+ resource.getCroppedResourceText()
										+ "\" contains no interfaces.");

							/* Update widgets. */
							if (ProxyViewInterfaceFileCanvas.this.counter
									.get() == sequenceNumber)

								display.asyncExec(new Runnable() {
									@Override
									public void run() {
										widgets.partyCombo.clear();
										widgets.partyCombo.addAll(partyNames
												.toArray());
										widgets.partyCombo.enable();
										widgets.simAutCombo.enable();
									}
								});

						} catch (final Exception e) {
							if (ProxyViewInterfaceFileCanvas.this.counter
									.get() == sequenceNumber)

								display.asyncExec(new Runnable() {
									@Override
									public void run() {
										try {
											widgets.reportError(e, false);
										} catch (final Exception ee) {
											/* Ignore $ee. */
										}

										/* Update widgets. */
										widgets.partyCombo.clear();
										widgets.partyCombo.disable();
										widgets.simAutCombo.disable();
										widgets.simAutFileCanvas.disable();
									}
								});
						}
					}
				}.start();

			}
		});
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a interface-file-canvas for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A interface-file-canvas. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.simpleSettingsCanvas==null</code>.
	 */
	public static ProxyViewInterfaceFileCanvas newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.simpleSettingsCanvas == null)
			throw new NullPointerException();

		return new ProxyViewInterfaceFileCanvas(widgets,
				widgets.simpleSettingsCanvas);
	}
}
