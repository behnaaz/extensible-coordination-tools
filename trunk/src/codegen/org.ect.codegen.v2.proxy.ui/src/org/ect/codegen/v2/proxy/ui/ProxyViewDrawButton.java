package org.ect.codegen.v2.proxy.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.ect.codegen.v2.core.descr.VertexFactory.Vertex;
import org.ect.codegen.v2.core.gen.Printer;
import org.ect.codegen.v2.core.ui.AbstractWidgetWrapper;
import org.ect.codegen.v2.core.ui.ActiveReoDiagramEditor;
import org.ect.codegen.v2.core.ui.IListenerWidget;
import org.ect.codegen.v2.proxy.SimAutResource;
import org.ect.codegen.v2.proxy.descr.java.AbstractParty;
import org.ect.codegen.v2.proxy.descr.java.IDLParty;
import org.ect.codegen.v2.proxy.descr.java.IDLResource;
import org.ect.codegen.v2.proxy.descr.java.WSDLParty;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgramArguments;
import org.ect.codegen.v2.proxy.rt.java.wsdl.parser.WSDLResource;
import org.ect.reo.Component;
import org.ect.reo.Module;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Property;
import org.ect.reo.ReoFactory;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.animation.AnimationSemanticsProvider;
import org.ect.reo.animation.AnimationText;

public class ProxyViewDrawButton extends AbstractWidgetWrapper<Button>
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
	private ProxyViewDrawButton(final ProxyViewWidgets widgets,
			final ProxyViewControlsCanvas parent) {

		super(new Button(parent.get(), SWT.LEFT_TO_RIGHT));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setText("Draw");
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
				if (event.widget == ProxyViewDrawButton.this.get())
					try {
						/* Get the arguments to base the drawing on. */
						final ProxyJavaGeneratorProgramArguments arguments = widgets.controlsCanvas
								.extractProxygenArguments();

						/* Draw a component. */
						new ICommandProxy(new DrawCommand(arguments)).execute();

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
	public static ProxyViewDrawButton newInstance(final ProxyViewWidgets widgets) {

		if (widgets == null || widgets.mainCanvas == null)
			throw new NullPointerException();

		return new ProxyViewDrawButton(widgets, widgets.controlsCanvas);
	}

	//
	// CLASSES
	//

	private class DrawCommand extends AbstractTransactionalCommand {

		//
		// FIELDS
		//

		/**
		 * The arguments on which to base the drawing.
		 */
		private final ProxyJavaGeneratorProgramArguments arguments;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a draw command.
		 * 
		 * @param editor
		 *            The editor to draw in. Not <code>null</code>.
		 * @param arguments
		 *            The arguments on which to base the drawing. Not
		 *            <code>null</code>.
		 * @throws Exception
		 *             TODO: Throw proper exception.
		 * @throws NullPointerException
		 *             If <code>editor==null</code> or
		 *             <code>arguments==null</code>.
		 */
		private DrawCommand(final ProxyJavaGeneratorProgramArguments arguments)
				throws Exception {

			super(ActiveReoDiagramEditor.get().getEditingDomain(), null, null);
			if (arguments == null)
				throw new NullPointerException();

			this.arguments = arguments;
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
		protected CommandResult doExecuteWithResult(
				final IProgressMonitor arg0, final IAdaptable arg1)
				throws ExecutionException {

			/* Construct a component. */
			final ReoFactory factory = ReoFactory.eINSTANCE;
			final Component component = factory.createComponent();
			component.setName(arguments.INTERFACE_NAME);

			/* Get the module. */
			Module module = null;
			try {
				module = ActiveReoDiagramEditor.extractModule();
			} catch (final Exception e) {
				// TODO Auto-generated catch block
			}

			/* Process $arguments. */
			final String partyName = arguments.INTERFACE_NAME;
			final SimAutResource simAutResource = SimAutResource.DEFAULT_SIMAUT_RESOURCE;
			final AbstractParty<?> party;
			try {
				if (arguments.CORBA)
					party = new IDLParty(partyName, new IDLResource(
							arguments.INTERFACES_PATH), simAutResource);
				else if (arguments.WSDL)
					party = new WSDLParty(partyName, new WSDLResource(
							arguments.INTERFACES_PATH), simAutResource);
				else
					throw new Exception("No technology specified.");

			} catch (final Exception e) {
				return CommandResult.newErrorCommandResult(e);
			}

			/* Get the source and sink ends to draw. */
			final String sourceQualifier = "(source)";
			final String sinkQualifier = "(sink)";

			final List<String> list = new ArrayList<String>();
			for (final Vertex v : party.getObservableInputVertices())
				list.add(v.getName() + " " + sourceQualifier);
			for (final Vertex v : party.getObservableOutputVertices())
				list.add(v.getName() + " " + sinkQualifier);

			Collections.sort(list);
			final ListSelectionDialog dialog = new ListSelectionDialog(
					ProxyViewDrawButton.this.get().getShell(), list,
					new ArrayContentProvider(), new LabelProvider(), "");

			dialog.setTitle("Source and sink ends.");
			dialog.open();

			if (dialog.getResult() == null)
				return CommandResult.newOKCommandResult();

			/* Draw source ends and sink ends. */
			for (final Object o : dialog.getResult()) {
				if (!(o instanceof String))
					continue;

				final String[] tokens = ((String) o).split(" ");
				if (!(tokens.length == 2))
					continue;

				final String endName = tokens[0];
				final String endQualifier = tokens[1];

				if (endQualifier.equals(sourceQualifier)) {
					final SourceEnd sourceEnd = factory.createSourceEnd();
					sourceEnd.setName(endName);
					component.getSourceEnds().add(sourceEnd);
				} else if (endQualifier.equals(sinkQualifier)) {
					final SinkEnd sinkEnd = factory.createSinkEnd();
					sinkEnd.setName(endName);
					component.getSinkEnds().add(sinkEnd);
				} else
					continue;
			}

			/* Get the coloring table text. */
			String coloringTableText = "table {";
			for (final PrimitiveEnd e : component.getAllEnds()) {
				String coloringText = "\n ";
				for (final PrimitiveEnd ee : component.getAllEnds()) {
					final String endName = ee.getName().replaceAll("\\.", "");
					if (e == ee) {
						final String parenthesizedEndName = AnimationText.PAREN_L
								+ endName + AnimationText.PAREN_R;

						coloringText += " "
								+ endName
								+ ":"
								+ AnimationText.FLOW
								+ (ee instanceof SourceEnd ? " "
										+ AnimationText.RECEIVE
										+ parenthesizedEndName + " "
										+ AnimationText.DESTROY
										+ parenthesizedEndName : "")
								+ (ee instanceof SinkEnd ? " "
										+ AnimationText.CREATE
										+ parenthesizedEndName + " "
										+ AnimationText.SEND
										+ parenthesizedEndName : "");
					} else {
						coloringText += " " + endName + ":"
								+ AnimationText.NO_FLOW_G;
					}
				}
				coloringText += " next=table;";
				coloringTableText += coloringText;
			}
			coloringTableText += "\n}";

			/* Set the coloring table text. */
			final Property animationProperty = new Property(
					AnimationSemanticsProvider.KEY, coloringTableText);
			animationProperty.setHidden(true);
			component.getProperties().add(animationProperty);

			/* Serialize $arguments and store the result as a hidden property. */
			try {
				/* Serialize. */
				final String string = ProxyJavaGeneratorProgramArguments
						.serialize(arguments);

				/* Store. */
				final Property property = new Property("proxygen.arguments",
						string);
				property.setHidden(true);
				component.getProperties().add(property);

			} catch (final Exception e) {
				return CommandResult.newErrorCommandResult(e);
			}

			/* Return. */
			module.getComponents().add(component);
			return CommandResult.newOKCommandResult();
		}
	}
}
