package org.ect.codegen.v2.core.ui;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.ect.codegen.v2.core.gen.DirTreeFactory;
import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.ect.codegen.v2.core.gen.Files;
import org.ect.codegen.v2.core.gen.Printer;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgram;
import org.ect.codegen.v2.core.gen.java.CoreJavaGeneratorProgramArguments;

public class CoreViewGenerateButton extends AbstractWidgetWrapper<Button>
		implements IListenerWidget {

	//
	// FIELDS
	//

	/**
	 * The widgets of the code-generator-view to which this generate-button
	 * belongs.
	 */
	private final CoreViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a generate-button for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @param parent
	 *            The parent of the generate-button to construct. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or <code>parent==null</code>.
	 */
	private CoreViewGenerateButton(final CoreViewWidgets widgets,
			final CoreViewMainCanvas parent) {

		super(new Button(parent.get(), SWT.LEFT_TO_RIGHT));
		if (widgets == null)
			throw new NullPointerException();

		this.widgets = widgets;

		super.get().setText("Generate");
		super.get().setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, true));
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
				if (event.widget == CoreViewGenerateButton.this.get())
					try {

						/* Prepare arguments. */
						final String connectorPath;
						switch (widgets.connectorCombo.getSelectedItem()) {
						case EDITOR:
							try {
								connectorPath = ActiveReoDiagramEditor
										.extractCanonicalPath();

							} catch (final Exception e) {
								throw new Exception(
										"I failed to get the location of the file in the editor.");
							}
							break;
						case FILE_SYSTEM:
							connectorPath = widgets.connectorFileCanvas.get()
									.getText();
							break;
						default:
							throw new Exception(
									"Please provide a connector file.");
						}

						final String destinationPath;
						switch (widgets.destinationCombo.getSelectedItem()) {
						case WORKSPACE:
							try {
								destinationPath = Workspace.getCanonicalPath();
							} catch (final Exception e) {
								throw new Exception(
										"I failed to get the location of the workspace.");
							}
							break;
						case FILE_SYSTEM:
							destinationPath = widgets.destinationFileCanvas
									.get().getText();
							break;
						default:
							throw new Exception(
									"Please provide a destination directory.");
						}

						/* Run job. */
						final CoreJavaGeneratorProgramArguments arguments = new CoreJavaGeneratorProgramArguments(
								connectorPath, destinationPath, null, null,
								null);

						final Display display = CoreViewGenerateButton.this
								.get().getDisplay();

						new GenerateJob(arguments, display).schedule();
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
	 * Constructs a generate-button for the code-generator-view.
	 * 
	 * @param widgets
	 *            The widgets of the code-generator-view. Not <code>null</code>.
	 * @return A generate-button. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>widgets==null</code> or
	 *             <code>widgets.mainCanvas==null</code>.
	 */
	public static CoreViewGenerateButton newInstance(
			final CoreViewWidgets widgets) {

		if (widgets == null || widgets.mainCanvas == null)
			throw new NullPointerException();

		return new CoreViewGenerateButton(widgets, widgets.mainCanvas);
	}

	//
	// CLASSES
	//

	private class GenerateJob extends Job {

		//
		// FIELDS
		//

		/**
		 * The arguments on which to run this job.
		 */
		private final CoreJavaGeneratorProgramArguments arguments;

		/**
		 * The display to report errors to.
		 */
		private final Display display;

		//
		// CONSTRUCTORS
		//

		/**
		 * Constructs a job for generating code.
		 * 
		 * @param arguments
		 *            The arguments on which to run the job to construct. Not
		 *            <code>null</code>.
		 * @param display
		 *            The display to report errors to. Not <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>arguments==null</code> or
		 *             <code>display==null</code>.
		 */
		public GenerateJob(final CoreJavaGeneratorProgramArguments arguments,
				final Display display) {

			super("");
			if (arguments == null || display == null)
				throw new NullPointerException();

			this.arguments = arguments;
			this.display = display;
			this.setUser(true);
		}

		//
		// METHODS - PUBLIC
		//

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 */
		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			if (monitor == null)
				throw new NullPointerException();

			/* Try. */
			try {
				monitor.beginTask(
						"Generating code for the module file at the location \""
								+ arguments.getInLocation() + "\".",
						IProgressMonitor.UNKNOWN);

				/* Construct a directory tree. */
				final DirTree tree = new DirTreeFactory()
						.construct("connector");

				/* Generate. */
				new CoreJavaGeneratorProgram(arguments, tree).call();

				/* Write. */
				final String outLocation = tree.tryWrite(arguments
						.getOutLocation());

				/* Create a project. */
				if (Files.tryCanonize(arguments.getOutLocation()).equals(
						Workspace.getCanonicalPath())) {

					monitor.subTask("Creating a project.");
					final String projectName = new File(outLocation).getName();
					Project.createIfNonexistent(projectName);
				}

				return Status.OK_STATUS;

			}

			/* Catch. */
			catch (final Exception e) {
				display.syncExec(new Runnable() {
					@Override
					public void run() {
						try {
							widgets.reportError(e, true);
						} catch (final Exception ee) {
							Printer.println(e, true);
						}
					}
				});

				return Status.CANCEL_STATUS;

			} finally {
				monitor.done();
			}
		}
	}
}
