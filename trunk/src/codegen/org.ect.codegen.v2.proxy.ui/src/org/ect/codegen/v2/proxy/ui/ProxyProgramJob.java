package org.ect.codegen.v2.proxy.ui;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.ect.codegen.v2.core.gen.DirTreeFactory;
import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.ect.codegen.v2.core.gen.Files;
import org.ect.codegen.v2.core.gen.Printer;
import org.ect.codegen.v2.core.ui.Project;
import org.ect.codegen.v2.core.ui.Workspace;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgramArguments;
import org.ect.codegen.v2.proxy.gen.java.ProxyJavaGeneratorProgram;

public class ProxyProgramJob extends Job {

	//
	// FIELDS
	//

	/**
	 * The arguments on which to run this job.
	 */
	private final ProxyJavaGeneratorProgramArguments arguments;

	/**
	 * The display to show error dialogs on.
	 */
	private final Display display;

	/**
	 * The widgets to report errors to.
	 */
	private final ProxyViewWidgets widgets;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a job for generating a proxy.
	 * 
	 * @param arguments
	 *            The arguments on which to run the job to construct. Not
	 *            <code>null</code>.
	 * @param display
	 *            The display to show error dialogs on. Not <code>null</code>.
	 * @param widgets
	 *            The widgets to report errors to. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>arguments==null</code> or <code>display==null</code>
	 *             or <code>widgets==null</code>.
	 */
	public ProxyProgramJob(final ProxyJavaGeneratorProgramArguments arguments,
			final Display display, final ProxyViewWidgets widgets) {

		super("Proxy Generator");
		if (arguments == null || display == null || widgets == null)
			throw new NullPointerException();

		this.arguments = arguments;
		this.display = display;
		this.widgets = widgets;
		this.setUser(true);
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected IStatus run(final IProgressMonitor monitor) {
		try {
			monitor.beginTask("Generating a proxy for the party named \""
					+ arguments.INTERFACE_NAME + "\".",
					IProgressMonitor.UNKNOWN);

			/* Construct a directory tree. */
			final DirTree tree = new DirTreeFactory().construct("proxy");

			/* Generate. */
			new ProxyJavaGeneratorProgram(arguments, tree).call();

			/* Write. */
			final String outLocation = tree
					.tryWrite(arguments.DESTINATION_PATH);

			/* Create a project. */
			if (Files.tryCanonize(arguments.DESTINATION_PATH).equals(
					Workspace.getCanonicalPath())) {

				monitor.subTask("Creating a project.");
				final String projectName = new File(outLocation).getName();
				Project.createIfNonexistent(projectName);
			}

			return Status.OK_STATUS;

		} catch (final Exception e) {
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
