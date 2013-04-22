package org.ect.codegen.v2.core.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;

public class Project {

	//
	// CONSTRUCTORS
	//

	@Deprecated
	private Project() {
		throw new UnsupportedOperationException();
	}

	//
	// METHODS - STATIC
	//

	public static boolean createIfNonexistent(final String projectName)
			throws ProjectException {

		if (projectName == null)
			throw new NullPointerException();
		if (projectName.isEmpty())
			throw new IllegalArgumentException();

		try {

			/* Create a project. */
			final IProject project = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(projectName);

			if (project.exists())
				return false;

			project.create(null);
			project.open(null);

			/* Set the natures of $project. */
			final IProjectDescription description = project.getDescription();
			final List<String> natures = new ArrayList<String>();
			natures.add(JavaCore.NATURE_ID);
			natures.addAll(Arrays.asList(description.getNatureIds()));

			final String[] array = new String[natures.size()];
			natures.toArray(array);

			description.setNatureIds(array);
			project.setDescription(description, null);

			/* Make $project a Java project. */
			final IJavaProject javaProject = JavaCore.create(project);
			final IFolder binFolder = project.getFolder("bin");
			binFolder.create(false, true, null);
			javaProject.setOutputLocation(binFolder.getFullPath(), null);

			/* Set classpath. */
			final List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
			IClasspathEntry entry = JavaCore.newSourceEntry(new Path(
					File.separator + projectName + File.separator + "src"));
			entries.add(entry);
			entries.add(JavaRuntime.getDefaultJREContainerEntry());

			final IFolder depFolder = project.getFolder("lib");
			for (final String s : depFolder.getRawLocation().makeAbsolute()
					.toFile().list()) {

				entry = JavaCore.newLibraryEntry(new Path(File.separator
						+ projectName + File.separator + "lib" + File.separator
						+ s), null, null);
				entries.add(entry);
			}

			javaProject.setRawClasspath(
					entries.toArray(new IClasspathEntry[entries.size()]), null);

			return true;
		} catch (final Exception e) {
			throw new ProjectException("I cannot create a project named \""
					+ projectName
					+ "\" in the workspace at the location \""
					+ ResourcesPlugin.getWorkspace().getRoot().getLocation()
							.toOSString() + "\".", e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class ProjectException extends Exception {
		private static final long serialVersionUID = 1L;

		public ProjectException(final String message, final Throwable cause) {
			super(message, cause);
		}
	}
}
