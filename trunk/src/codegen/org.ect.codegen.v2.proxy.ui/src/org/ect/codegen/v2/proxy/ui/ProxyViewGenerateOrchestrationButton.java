package org.ect.codegen.v2.proxy.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.ect.codegen.v2.core.gen.Printer;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.IListenerWidget;

public class ProxyViewGenerateOrchestrationButton extends
		AbstractWidgetWrapper<Button> implements IListenerWidget {

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
	private ProxyViewGenerateOrchestrationButton(
			final ProxyViewWidgets widgets,
			final ProxyViewControlsCanvas parent) {

		super(new Button(parent.get(), SWT.LEFT_TO_RIGHT));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setText("Generate Orchestration");
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
				if (event.widget == ProxyViewGenerateOrchestrationButton.this
						.get())

					try {
						new OrchProgramJob(widgets.controlsCanvas
								.extractOrcgenArguments(),
								ProxyViewGenerateOrchestrationButton.this.get()
										.getDisplay(), widgets).schedule();

						// /* Prepare arguments. */
						// final Module module = ActiveReoDiagramEditor
						// .extractModule();
						//
						// final Collection<ProxygenArguments> collection = new
						// ArrayList<ProxygenArguments>();
						// for (final Component c : module.getComponents())
						// for (final Property p : c.getProperties())
						// if (p.getKey().equals("arguments"))
						// collection.add(ProxygenArguments
						// .deserialize(p.getValue()));
						//
						// final Display display =
						// ProxygenViewGenerateDiagramButton.this
						// .get().getDisplay();
						//
						// final String workspaceLocation = Workspace
						// .getCanonicalPath();
						//
						// /* Schedule job. */
						// new GenerateJob(collection, new ConnectorCollection(
						// new ModuleResource(module).getConnectors()),
						// module, display, workspaceLocation).schedule();

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
	public static ProxyViewGenerateOrchestrationButton newInstance(
			final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.mainCanvas == null)
			throw new NullPointerException();

		return new ProxyViewGenerateOrchestrationButton(widgets,
				widgets.controlsCanvas);
	}

	// //
	// // CLASSES
	// //
	//
	// public class QVertex {
	// private final String entityName;
	// private final Vertex vertex;
	//
	// public QVertex(final String entityName, final Vertex vertex) {
	//
	// if (vertex == null || entityName == null)
	// throw new NullPointerException();
	//
	// this.vertex = vertex;
	// this.entityName = entityName;
	// }
	//
	// @Override
	// public boolean equals(final Object object) {
	// return object instanceof QVertex && equals((QVertex) object);
	// }
	//
	// public boolean equals(final QVertex qVertexName) {
	// return entityName.equals(qVertexName.entityName)
	// && vertex.equals(qVertexName.vertex);
	// }
	//
	// public String getEntityName() {
	// return entityName;
	// }
	//
	// public Vertex getVertex() {
	// return vertex;
	// }
	//
	// @Override
	// public int hashCode() {
	// return vertex.hashCode();
	// }
	//
	// @Override
	// public String toString() {
	// return "{" + entityName + "}" + vertex;
	// }
	// }
	//
	// private class GenerateJob extends Job {
	//
	// //
	// // FIELDS
	// //
	//
	// /**
	// * The arguments records based on which to generate proxies.
	// */
	// private final Collection<ProxygenArguments> arguments;
	//
	// /**
	// * The connectors to generate code for.
	// */
	// private final ConnectorCollection connectors;
	//
	// /**
	// * The display to report errors to.
	// */
	// private final Display display;
	//
	// /**
	// * The location of the workspace.
	// */
	// private final String workspaceLocation;
	//
	// private final Module module;
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
	// public GenerateJob(final Collection<ProxygenArguments> arguments,
	// final ConnectorCollection connectors, final Module module,
	// final Display display, final String workspaceLocation) {
	//
	// super("");
	// if (arguments == null || connectors == null || display == null
	// || workspaceLocation == null)
	// throw new NullPointerException();
	//
	// this.arguments = arguments;
	// this.connectors = connectors;
	// this.module = module;
	// this.display = display;
	// this.workspaceLocation = workspaceLocation;
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
	// monitor.beginTask("Generating all", IProgressMonitor.UNKNOWN);
	//
	// try {
	// /* Construct a tree everything to add. */
	// final DirTree tree = new DirTree("orchestration");
	//
	// /* Generate connectors. */
	// connectors.generateCodeAndAddTo(tree,
	// CodegenDefaults.TEMPLATE_FILES());
	// connectors.generateDependenciesAndAddTo(tree,
	// CodegenDefaults.RESOURCES_DIRS());
	// connectors.generateRuntimeAndAddTo(tree,
	// CodegenDefaults.RUNTIME_DIRS());
	//
	// /* Generate proxies. */
	// final Collection<AbstractParty<?>> parties = new
	// ArrayList<AbstractParty<?>>();
	// for (final ProxygenArguments a : arguments) {
	//
	// final String partyName = a.INTERFACE_NAME;
	//
	// /* Initialize. */
	// monitor.subTask("Initializing (" + partyName + ").");
	//
	// final Resource interfaceResource = new Resource(
	// a.INTERFACES_PATH);
	// final Resource simAutResource = new Resource(a.SIM_AUT_PATH);
	//
	// AbstractParty<?> party = null;
	// if (a.CORBA)
	// party = new IDLParty(partyName, simAutResource,
	// interfaceResource);
	// else if (a.WSDL)
	// party = new WSDLParty(partyName, simAutResource,
	// interfaceResource);
	// else
	// throw new Exception();
	//
	// parties.add(party);
	//
	// /* Generate. */
	// monitor.subTask("Generating  (" + partyName + ").");
	//
	// party.generateProxyAndAddTo(tree, a.PROXY_TEMPLATES_PATHS);
	// party.generateDependenciesAndAddTo(tree,
	// a.PROXY_DEPENDENCIES_PATH);
	// party.generateRuntimeAndAddTo(tree, a.PROXY_SOURCES_PATHS);
	// }
	//
	// /* Generate orchestration. */
	// final Map<QVertex, Integer> connectorInputQVerticesToIndices = new
	// HashMap<QVertex, Integer>();
	// final Map<QVertex, Integer> connectorOutputQVertexNamesToIndices = new
	// HashMap<QVertex, Integer>();
	// final Map<QVertex, Integer> connectorQVertexNamesToIndices = new
	// HashMap<QVertex, Integer>();
	// int i = 0;
	// for (final Connector c : module.getConnectors()) {
	// cwi.reo.descr.conn.Connector<?> connector = connectors
	// .getConnector(c.getName());
	//
	// for (final Node n : c.getSourceNodes()) {
	// final int index = i++;
	// final QVertex qVertexName = new QVertex(c.getName(),
	// connector.getBehavior().getVertexFactory()
	// .get(n.getName()));
	//
	// connectorQVertexNamesToIndices.put(qVertexName, index);
	// connectorInputQVerticesToIndices
	// .put(qVertexName, index);
	// }
	//
	// for (final Node n : c.getSinkNodes()) {
	// final int index = i++;
	// final QVertex qVertexName = new QVertex(c.getName(),
	// connector.getBehavior().getVertexFactory()
	// .get(n.getName()));
	//
	// connectorQVertexNamesToIndices.put(qVertexName, index);
	// connectorOutputQVertexNamesToIndices.put(qVertexName,
	// index);
	// }
	// }
	//
	// final Map<QVertex, Integer> connectorConnectedInputQVerticesToIndices =
	// new HashMap<QVertex, Integer>();
	// final Map<QVertex, Integer> connectorConnectedOutputQVerticesToIndices =
	// new HashMap<QVertex, Integer>();
	// final Map<QVertex, Integer> connectorUnconnectedInputQVerticesToIndices =
	// new HashMap<QVertex, Integer>(
	// connectorInputQVerticesToIndices);
	// final Map<QVertex, Integer> connectorUnconnectedOutputQVerticesToIndices
	// = new HashMap<QVertex, Integer>(
	// connectorOutputQVertexNamesToIndices);
	//
	// final Map<QVertex, Integer> partyConnectedQVerticesToIndices = new
	// HashMap<QVertex, Integer>();
	// for (final Component c : module.getComponents()) {
	//
	// AbstractParty<?> party = null;
	// for (final AbstractParty<?> p : parties)
	// if (p.getName().equals(c.getName())) {
	// party = p;
	// break;
	// }
	//
	// for (final PrimitiveEnd e : c.getAllEnds())
	// if (e.getNode() != null) {
	// cwi.reo.descr.conn.Connector<?> connector = connectors
	// .getConnector(e.getNode().getConnector()
	// .getName());
	//
	// final QVertex connectorQVertex = new QVertex(
	// connector.getName(), connector
	// .getBehavior().getVertexFactory()
	// .get(e.getNode().getName()));
	//
	// final QVertex partyQVertex = new QVertex(
	// party.getName(), party.getSimAut()
	// .getBehavior().getVertexFactory()
	// .get(e.getName()));
	//
	// final int index = connectorQVertexNamesToIndices
	// .get(connectorQVertex);
	//
	// partyConnectedQVerticesToIndices.put(partyQVertex,
	// index);
	//
	// if (e instanceof SinkEnd) {
	// connectorConnectedInputQVerticesToIndices.put(
	// connectorQVertex, index);
	// connectorUnconnectedInputQVerticesToIndices
	// .remove(connectorQVertex);
	// }
	//
	// if (e instanceof SourceEnd) {
	// connectorConnectedOutputQVerticesToIndices.put(
	// connectorQVertex, index);
	// connectorUnconnectedOutputQVerticesToIndices
	// .remove(connectorQVertex);
	// }
	// }
	// }
	//
	// final List<QVertex> partyUnconnectedQVertexNames = new
	// ArrayList<QVertex>();
	// for (final AbstractParty<?> p : parties) {
	// final String partyName = p.getName();
	// for (final Vertex v : p.getObservableVertices()) {
	// final QVertex qVertexName = new QVertex(partyName, v);
	// if (!partyConnectedQVerticesToIndices
	// .containsKey(qVertexName))
	// partyUnconnectedQVertexNames.add(qVertexName);
	// }
	// }
	//
	// // final String path =
	// //
	// "C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.proxygen\\res\\stg\\orchestration.stg";
	// final ST template = STUtils
	// .loadTemplate(
	// "C:\\Users\\sung\\Desktop\\workspace\\cwi.reo.proxygen\\res\\stg\\orchestration.stg",
	// "orchestrationClass");
	//
	// template.add("parties", parties);
	// template.add("connectors", connectors.getConnectors());
	//
	// template.add("connectorInputQVerticesToIndices",
	// connectorInputQVerticesToIndices.entrySet());
	// template.add("connectorOutputQVerticesToIndices",
	// connectorOutputQVertexNamesToIndices.entrySet());
	//
	// template.add("partyConnectedQVerticesToIndices",
	// partyConnectedQVerticesToIndices.entrySet());
	// template.add("partyUnconnectedQVertices",
	// partyUnconnectedQVertexNames);
	//
	// template.add("connectorConnectedInputQVerticesToIndices",
	// connectorConnectedInputQVerticesToIndices.entrySet());
	// template.add("connectorConnectedOutputQVerticesToIndices",
	// connectorConnectedOutputQVerticesToIndices.entrySet());
	//
	// template.add("connectorUnconnectedInputQVerticesToIndices",
	// connectorUnconnectedInputQVerticesToIndices.entrySet());
	// template.add("connectorUnconnectedOutputQVerticesToIndices",
	// connectorUnconnectedOutputQVerticesToIndices.entrySet());
	//
	// final DirTree srcTree = new DirTree("src");
	// final String code = template.render();
	//
	// if (!srcTree.addLeaf("Orchestration.java", code)
	// || !tree.importTree(srcTree))
	// throw new Exception();
	//
	// /* Write. */
	// monitor.subTask("Writing.");
	// final String path = tree.write(workspaceLocation);
	//
	// /* Create a project. */
	// monitor.subTask("Creating a project.");
	// final String projectName = new File(path).getName();
	// Project.createIfNonexistent(projectName);
	//
	// return Status.OK_STATUS;
	// } catch (final Exception e) {
	// e.printStackTrace();
	// display.syncExec(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// widgets.reportError(e, true);
	// } catch (final Exception ee) {
	// ThrowablePrinter.print(e, null, true);
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
