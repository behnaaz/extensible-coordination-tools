package org.ect.codegen.v2.core.cli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.tools.ToolProvider;

import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;

public class JavaCompiler {

	//
	// CONSTRUCTORS
	//

	/**
	 * Deprecates the constructor.
	 * 
	 * @throws UnsupportedOperationException
	 *             Always.
	 */
	@Deprecated
	private JavaCompiler() {
		throw new UnsupportedOperationException();
	}

	//
	// STATIC
	//

	/**
	 * Compiles the Java files in the tree <code>tree</code>.
	 * 
	 * @param tree
	 *            The tree. Not <code>null</code>.
	 * @param treePath
	 *            Path to <code>tree</code> on the local file system. Not
	 *            <code>null</code>.
	 * @throws JavaCompilerException
	 *             If something goes wrong while compiling.
	 */
	public static void compile(final DirTree tree, final String treePath)
			throws JavaCompilerException {

		if (tree == null || treePath == null)
			throw new NullPointerException();

		try {
			/* Get the files on the classpath. */
			final List<File> filesOnClasspath = new ArrayList<File>();
			for (final String s : tree.getSubtree("dep").getLeafNames())
				filesOnClasspath.add(new File(treePath + File.separator + "dep"
						+ File.separator + s));

			/* Get the files to compile. */
			final Collection<File> filesToCompile = new HashSet<File>();
			for (final String s : tree.getSubtree("src").getLeafNames())
				if (s.endsWith(".java"))
					filesToCompile.add(new File(treePath + File.separator
							+ "src" + File.separator + s));

			/* Compile. */
			for (final File f : filesToCompile)
				org.ect.codegen.v2.core.cli.JavaCompiler.compile(f,
						filesOnClasspath, true, true);

		} catch (final Exception e) {
			throw new JavaCompilerException(JavaCompilerException.COMPILE(tree,
					treePath), e);
		}
	}

	/**
	 * Compiles the file <code>fileToCompile</code> under a classpath
	 * constituted by the files <code>filesOnClasspath</code>.
	 * 
	 * @param file
	 *            The file to compile. Not <code>null</code>.
	 * @param classpathFiles
	 *            The files on the classpath. Not <code>null</code>.
	 * @param includeCompileScript
	 *            Flag indicating if this method should write also a compile
	 *            script.
	 * @param includeRunScript
	 *            Flag indicating if this method should write also a run script.
	 * @throws IllegalArgumentException
	 *             If <code>!fileToCompile.isFile()</code>.
	 * @throws JavaCompilerException
	 *             If something goes wrong before, while, or after compiling.
	 * @throws NullPointerException
	 *             If <code>fileToCompile==null</code> or
	 *             <code>filesOnClasspath==null</code>.
	 * 
	 * @see File#isFile()
	 */
	public static void compile(final File file,
			final Collection<File> classpathFiles,
			boolean includeCompileScript, boolean includeRunScript)
			throws JavaCompilerException {

		if (file == null || classpathFiles == null)
			throw new NullPointerException();
		if (!file.isFile())
			throw new IllegalArgumentException();

		try {
			/* Get a compiler. */
			javax.tools.JavaCompiler compiler = ToolProvider
					.getSystemJavaCompiler();
			if (compiler == null)
				throw new JavaCompilerException(JavaCompilerException.COMPILE(
						file, classpathFiles), "No Java compiler is available.");

			/* Compute classpaths. */
			String canonicalClasspath = "." + File.pathSeparator
					+ file.getParentFile().getCanonicalPath();
			// String unixClasspath = ".:"
			// + file.getParentFile().getCanonicalPath();
			// String winClasspath = ".;"
			// + file.getParentFile().getCanonicalPath();

			for (final File f : classpathFiles) {
				canonicalClasspath += File.pathSeparator + f.getCanonicalPath();
				// unixClasspath += ":" + f.getCanonicalPath();
				// winClasspath += ";" + f.getCanonicalPath();
			}

			/* Compile. */
			if (0 != compiler.run(null, null, null, "-classpath",
					canonicalClasspath, file.getCanonicalPath()))

				throw new JavaCompilerException(JavaCompilerException.COMPILE(
						file, classpathFiles), "Compilation failed.");

			/* Include scripts. */
			// if (includeCompileScript || includeRunScript) {
			// File scriptFile;
			//
			// /* Get parent path and name. */
			// final String parentPath = file.getParentFile()
			// .getCanonicalPath();
			//
			// final String name = file.getName()
			// .substring(0, file.getName().length() - 5)
			// .toLowerCase();
			//
			// /* Write compile scripts. */
			// if (includeCompileScript) {
			//
			// /* Unix. */
			// scriptFile = new File(parentPath + File.separator
			// + "compile-" + name);
			//
			// new Writer(scriptFile).write("javac -classpath "
			// + unixClasspath + " " + file.getName());
			// scriptFile.setExecutable(true);
			//
			// /* Windows. */
			// scriptFile = new File(parentPath + File.separator
			// + "compile-" + name + ".bat");
			// new Writer(scriptFile).write("javac -classpath "
			// + winClasspath + " " + file.getName());
			// scriptFile.setExecutable(true);
			// }
			//
			// /* Write run scripts. */
			// if (includeRunScript) {
			//
			// /* Unix. */
			// scriptFile = new File(parentPath + File.separator + "run-"
			// + name);
			// new Writer(scriptFile).write("java -classpath "
			// + unixClasspath
			// + " "
			// + file.getName().substring(0,
			// file.getName().length() - 5)
			// + " \"$@\"");
			// scriptFile.setExecutable(true);
			//
			// /* Windows. */
			// scriptFile = new File(parentPath + File.separator + "run-"
			// + name + ".bat");
			// new Writer(scriptFile).write("java -classpath "
			// + winClasspath
			// + " "
			// + file.getName().substring(0,
			// file.getName().length() - 5)
			// + " %*");
			// scriptFile.setExecutable(true);
			// }
			// }

		} catch (final Exception e) {
			if (e instanceof JavaCompilerException)
				throw (JavaCompilerException) e;

			throw new JavaCompilerException(JavaCompilerException.COMPILE(file,
					classpathFiles), e);
		}
	}

	//
	// EXCEPTIONS
	//

	public static class JavaCompilerException extends Exception {
		private static final long serialVersionUID = 1L;

		//
		// CONSTRUCTORS
		//

		protected JavaCompilerException(final String message, final String cause) {
			super(message, new Throwable(cause));
		}

		protected JavaCompilerException(final String message,
				final Throwable cause) {
			super(message, cause);
		}

		//
		// METHODS
		//

		protected static String COMPILE(final DirTree tree,
				final String treePath) {

			return "The class \""
					+ JavaCompiler.class.getCanonicalName()
					+ "\" failed to compile the Java files in the tree named \""
					+ tree.getName() + "\".";
		}

		protected static String COMPILE(final File fileToCompile,
				final Collection<File> filesOnClasspath) {

			return "The class \"" + JavaCompiler.class.getCanonicalName()
					+ "\" failed to compile the file \"" + fileToCompile
					+ "\" under the classpath \"" + filesOnClasspath + "\".";
		}

	}
}
