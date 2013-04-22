package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.ect.codegen.v2.core.gen.Printer;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.IListenerWidget;

public class ProxyViewGenerateProxyButton extends AbstractWidgetWrapper<Button>
		implements IListenerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the proxy-generator-view to which this generate-button
	 * belongs.
	 */
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a generate-button for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @param parent
	 *            The parent of the generate-button to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private ProxyViewGenerateProxyButton(final ProxyViewWidgets widgets,
			final ProxyViewControlsCanvas parent) {

		super(new Button(parent.get(), SWT.LEFT_TO_RIGHT));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setText("Generate Proxy");
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
	public void initializeListeners() {
		super.get().addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				if (event.widget == ProxyViewGenerateProxyButton.this.get())
					try {
						new ProxyProgramJob(widgets.controlsCanvas
								.extractProxygenArguments(),
								ProxyViewGenerateProxyButton.this.get()
										.getDisplay(), widgets).schedule();

						// /* Prepare arguments. */
						// final ProxygenArguments arguments =
						// widgets.controlsCanvas
						// .extractArguments();
						//
						// final boolean storeInWorkspace =
						// widgets.destinationCombo
						// .getSelectedItem() ==
						// ProxygenViewDestinationCombo.Item.WORKSPACE;
						//
						// final boolean useDefaultSimAut = widgets.simAutCombo
						// .getSelectedItem() ==
						// ProxygenViewSimAutCombo.Item.DEFAULT;
						//
						// /* Run job. */
						// final Display display =
						// ProxygenViewGenerateButton.this
						// .get().getDisplay();
						//
						// new GenerateJob(arguments, display, storeInWorkspace,
						// useDefaultSimAut).schedule();

					} catch (final Exception e) {
						try {
							widgets.reportError(e, true);
						} catch (final Exception ee) {
							Printer.println(e, true);
						}
						return;
					}
			}
		});
	}

	//
	// STATIC - METHODS
	//

	/**
	 * Constructs a generate-button for the proxy-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the proxy-generator-view. Not <code>null</code>
	 *            .
	 * @return A generate-button. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainCanvas==null</code>.
	 */
	public static ProxyViewGenerateProxyButton newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.mainCanvas == null)
			throw new NullPointerException();

		return new ProxyViewGenerateProxyButton(widgets, widgets.controlsCanvas);
	}

	//
	// CLASSES
	//

	// private class GenerateJob extends Job {
	//
	// //
	// // FIELDS
	// //
	//
	// /**
	// * The arguments on which to run this job.
	// */
	// private final ProxygenArguments arguments;
	//
	// /**
	// * The display to report errors to.
	// */
	// private final Display display;
	//
	// /**
	// * Flag indicating whether the proxy to generate is stored as a new
	// * project in the workspace.
	// */
	// private final boolean storeInWorkspace;
	//
	// /**
	// * Flag indicating whether the proxy to generate is generated with a
	// * default simulation automaton.
	// */
	// private final boolean useDefaultSimAut;
	//
	// //
	// // CONSTRUCTORS
	// //
	//
	// /**
	// * Constructs a job for generating a proxy.
	// *
	// * @param arguments
	// * The arguments on which to run the job to construct. Not
	// * <code>null</code>.
	// * @param display
	// * The display to report errors to. Not <code>null</code>.
	// * @param storeInWorkspace
	// * Flag indicating whether the proxy to generate is stored as
	// * a new project in the workspace.
	// * @param useDefaultSimAut
	// * Flag indicating whether the proxy to generate is generated
	// * with a default simulation automaton.
	// * @throws NullPointerException
	// * If <code>arguments==null</code> or
	// * <code>display==null</code>.
	// */
	// public GenerateJob(final ProxygenArguments arguments,
	// final Display display, final boolean storeInWorkspace,
	// final boolean useDefaultSimAut) {
	//
	// super("");
	// if (arguments == null || display == null)
	// throw new NullPointerException();
	//
	// this.arguments = arguments;
	// this.display = display;
	// this.storeInWorkspace = storeInWorkspace;
	// this.useDefaultSimAut = useDefaultSimAut;
	// this.setUser(true);
	// }
	//
	// //
	// // METHODS - PUBLIC
	// //
	//
	// /**
	// * <em>Inherited documentation:</em>
	// *
	// * <p>
	// * {@inheritDoc}
	// */
	// @Override
	// protected IStatus run(final IProgressMonitor monitor) {
	// if (monitor == null)
	// throw new NullPointerException();
	//
	// monitor.beginTask("Generating a proxy for the party named \""
	// + arguments.INTERFACE_NAME + "\"", IProgressMonitor.UNKNOWN);
	//
	// try {
	//
	// /* Initialize. */
	// monitor.subTask("Initializing.");
	// AbstractParty<?> party = null;
	//
	// final String partyName = arguments.INTERFACE_NAME;
	// final String simAutPath = arguments.SIM_AUT_PATH;
	// final String interfacePath = arguments.INTERFACES_PATH;
	//
	// final Resource interfaceResource = new Resource(interfacePath);
	// final Resource simAutResource = useDefaultSimAut ?
	// SimAutResource.DEFAULT_SIMAUT_RESOURCE
	// : new Resource(simAutPath);
	//
	// if (arguments.CORBA)
	// party = new IDLParty(partyName, simAutResource,
	// interfaceResource);
	// else if (arguments.WSDL)
	// party = new WSDLParty(partyName, simAutResource,
	// interfaceResource);
	//
	// /* Generate. */
	// monitor.subTask("Generating.");
	// final DirTree tree = party.generate(
	// arguments.PROXY_TEMPLATES_PATHS,
	// arguments.PROXY_SOURCES_PATHS,
	// arguments.PROXY_DEPENDENCIES_PATH);
	//
	// /* Write. */
	// monitor.subTask("Writing.");
	// final String path = tree.write(arguments.DESTINATION_PATH);
	//
	// /* Create a project. */
	// if (storeInWorkspace) {
	// monitor.subTask("Creating a project.");
	// final String projectName = new File(path).getName();
	// Project.createIfNonexistent(projectName);
	// } else {
	// monitor.subTask("Compiling.");
	// JavaCompiler.compile(tree, path);
	// }
	//
	// return Status.OK_STATUS;
	//
	// } catch (final Exception e) {
	// display.syncExec(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// widgets.reportError(e, true);
	// } catch (final Exception ee) {
	// Printer.println(e, true);
	// }
	// }
	// });
	//
	// return Status.CANCEL_STATUS;
	// } finally {
	// monitor.done();
	// }
	// }
	// }
}
