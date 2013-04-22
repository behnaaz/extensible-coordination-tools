package org.ect.codegen.v2.core.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public abstract class AbstractGenerator<O> {

	//
	// FIELDS
	//

	// /**
	// * The directories containing the libraries used by the code to generate.
	// */
	// private final Collection<File> libDirs;
	//
	// /**
	// * The directories containing the sources of the runtime framework used by
	// * the code to generate.
	// */
	// private final Collection<File> srcDirs;

	/**
	 * The subject to generate code for.
	 */
	private final O subject;

	/**
	 * The templates files used to generate code with.
	 */
	private final Collection<File> stgFiles;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a generator for the subject <code>subject</code>.
	 * 
	 * @param subject
	 *            The subject to generate code for. Not <code>null</code>.
	 * @param stgFiles
	 *            The template files used to generate code with. Not
	 *            <code>null</code>.
	 * @param srcDirs
	 *            The directories containing the sources of the runtime
	 *            framework used by the code to generate. Not <code>null</code>.
	 * @param libDirs
	 *            The directories containing the libraries used by the code to
	 *            generate. Not <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>subject==null</code> or <code>stgFiles==null</code>
	 *             or <code>srcDirs==null</code> or <code>libDirs==null</code>
	 *             or <code>stgFiles.contains(null)</code> or
	 *             <code>srcDirs.contains(null)</code> or
	 *             <code>libDirs.contains(null)</code>.
	 */
	public AbstractGenerator(final O subject, final Collection<File> stgFiles) {
		//, final Collection<File> srcDirs, final Collection<File> libDirs) {

		/* Validate arguments. */
		if (subject == null)
			throw new NullPointerException("subject");
		if (stgFiles == null || stgFiles.contains(null))
			throw new NullPointerException("stgFiles");
//		if (srcDirs == null || srcDirs.contains(null))
//			throw new NullPointerException("srcDirs");
//		if (libDirs == null || libDirs.contains(null))
//			throw new NullPointerException("libDirs");

		/* Initialize. */
		// this.srcDirs = srcDirs;
		// this.libDirs = libDirs;
		this.subject = subject;
		this.stgFiles = stgFiles;
	}

	//
	// METHODS
	//

//	/**
//	 * Adds the directories containing the libraries used by the code to
//	 * generate to the subtree named "lib" of the tree <code>tree</code>.
//	 * 
//	 * <p>
//	 * Returns <code>tree</code>.
//	 * </p>
//	 * 
//	 * @param tree
//	 *            The tree. Not <code>null</code>.
//	 * @return A tree. Never <code>null</code>.
//	 * @throws NullPointerException
//	 *             If <code>tree==null</code>.
//	 */
//	public final DirTree addLibDirsTo(final DirTree tree) {
//
//		/* Validate arguments. */
//		if (tree == null)
//			throw new NullPointerException("tree");
//
//		/* Add. */
//		for (final File f : libDirs)
//			if (f.isDirectory())
//				tree.importFile(f, "lib", ConflictResolutionPolicy.IGNORE);
//
//		return tree;
//	}
//
//	/**
//	 * Adds the directories containing the sources of the runtime framework used
//	 * by the code to generate to the subtree named "src" of the tree
//	 * <code>tree</code>.
//	 * 
//	 * @param tree
//	 *            The tree. Not <code>null</code>.
//	 * @return A tree. Never <code>null</code>.
//	 * @throws NullPointerException
//	 *             If <code>tree==null</code>.
//	 */
//	public final DirTree addSrcDirsTo(final DirTree tree) {
//
//		/* Validate arguments. */
//		if (tree == null)
//			throw new NullPointerException("tree");
//
//		/* Add. */
//		for (final File f : srcDirs)
//			if (f.isDirectory())
//				tree.importFile(f, "src", ConflictResolutionPolicy.IGNORE);
//
//		return tree;
//	}

	/**
	 * Checks if this generator can generate code for its subject.
	 * 
	 * @return A boolean.
	 */
	public abstract boolean canGenerate();

	/**
	 * Generates code for the subject of this generator.
	 * 
	 * <p>
	 * Returns a map from names of generated files to their content.
	 * </p>
	 * 
	 * @return A map from strings to strings. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!canGenerate()</code>.
	 * 
	 * @see #canGenerate()
	 */
	public abstract Map<String, String> generate();

	/**
	 * Generates code for the subject of this generator, then adds the generated
	 * code to the tree <code>tree</code>.
	 * 
	 * <p>
	 * Returns <code>tree</code>.
	 * </p>
	 * 
	 * @param tree
	 *            The tree. Not <code>null</code>.
	 * @return A tree. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>tree==null</code>.
	 * @throws IllegalStateException
	 *             If <code>!canGenerate()</code>.
	 * 
	 * @see #canGenerate()
	 */
	public final DirTree generateThenAddTo(final DirTree tree) {

		/* Validate arguments. */
		if (tree == null)
			throw new NullPointerException();
		if (!canGenerate())
			throw new IllegalStateException("!canGenerate()");

		/* Generate. */
		final DirTree srcTree = new DirTreeFactory().construct("src");
		for (final Entry<String, String> e : generate().entrySet()) {
			final String name = e.getKey();
			final String content = e.getValue();
			srcTree.addLeaf(name, content);
		}

		/* Add. */
		tree.addOrMergeSubtree(srcTree, ConflictResolutionPolicy.IGNORE);
		return tree;
	}

	/**
	 * Gets the subject of this generator.
	 * 
	 * @return An object. Never <code>null</code>.
	 */
	public final O getSubject() {
		return subject;
	}

	/**
	 * Gets the templates files used to generate code with.
	 * 
	 * @return A collection of files. Never <code>null</code>.
	 */
	public final Collection<File> getStgFiles() {
		return stgFiles;
	}

	/**
	 * Checks if this generator can load the templates from the template files
	 * used to generate code with.
	 * 
	 * @return A boolean.
	 */
	public final boolean canLoadTemplates() {
		try {
			tryLoadTemplates();
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Loads the templates from the template files used to generate code with.
	 * 
	 * @return A collection of template groups. Never <code>null</code>.
	 * @throws IllegalStateException
	 *             If <code>!canLoadTemplates()</code>.
	 * 
	 * @see #canLoadTemplates()
	 */
	public final Collection<STGroup> loadTemplates() {

		/* Validate state. */
		if (!canLoadTemplates())
			throw new IllegalStateException("!canLoadTemplates()");

		/* Try to load. */
		try {
			return tryLoadTemplates();
		}

		/* Catch. */
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	//
	// METHODS - PRIVATE
	//

	/**
	 * Tries to load the templates from the template files used to generate code
	 * with.
	 * 
	 * @return A collection of templates. Never <code>null</code>.
	 * @throws Exception
	 *             If something goes wrong.
	 */
	private final Collection<STGroup> tryLoadTemplates() throws Exception {
		final Collection<STGroup> templates = new ArrayList<STGroup>();
		for (final File f : stgFiles)
			templates.add(new STGroupFile(f.getCanonicalPath()));

		return templates;
	}
}
