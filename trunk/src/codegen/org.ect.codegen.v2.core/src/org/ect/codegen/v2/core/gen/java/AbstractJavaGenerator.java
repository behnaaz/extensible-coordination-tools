package org.ect.codegen.v2.core.gen.java;

import java.io.File;
import java.util.Collection;

import org.ect.codegen.v2.core.gen.AbstractGenerator;
import org.ect.codegen.v2.core.gen.ConflictResolutionPolicy;
import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;

public abstract class AbstractJavaGenerator<O> extends AbstractGenerator<O> {

	//
	// FIELDS
	//

	/**
	 * The directories containing the libraries used by the code to generate.
	 */
	private final Collection<File> libDirs;

	/**
	 * The directories containing the sources of the runtime framework used by
	 * the code to generate.
	 */
	private final Collection<File> srcDirs;

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
	public AbstractJavaGenerator(final O subject,
			final Collection<File> stgFiles, final Collection<File> srcDirs,
			final Collection<File> libDirs) {

		super(subject, stgFiles);

		/* Validate arguments. */
		if (srcDirs == null || srcDirs.contains(null))
			throw new NullPointerException("srcDirs");
		if (libDirs == null || libDirs.contains(null))
			throw new NullPointerException("libDirs");

		/* Initialize. */
		this.srcDirs = srcDirs;
		this.libDirs = libDirs;
	}

	//
	// METHODS
	//

	/**
	 * Adds the directories containing the libraries used by the code to
	 * generate to the subtree named "lib" of the tree <code>tree</code>.
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
	 */
	public final DirTree addLibDirsTo(final DirTree tree) {

		/* Validate arguments. */
		if (tree == null)
			throw new NullPointerException("tree");

		/* Add. */
		for (final File f : libDirs)
			if (f.isDirectory())
				tree.importFile(f, "lib", ConflictResolutionPolicy.IGNORE);

		return tree;
	}

	/**
	 * Adds the directories containing the sources of the runtime framework used
	 * by the code to generate to the subtree named "src" of the tree
	 * <code>tree</code>.
	 * 
	 * @param tree
	 *            The tree. Not <code>null</code>.
	 * @return A tree. Never <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>tree==null</code>.
	 */
	public final DirTree addSrcDirsTo(final DirTree tree) {

		/* Validate arguments. */
		if (tree == null)
			throw new NullPointerException("tree");

		/* Add. */
		for (final File f : srcDirs)
			if (f.isDirectory())
				tree.importFile(f, "src", ConflictResolutionPolicy.IGNORE);

		return tree;
	}
}
