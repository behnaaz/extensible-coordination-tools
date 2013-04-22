package org.ect.codegen.v2.core.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.ect.codegen.v2.core.NamedObjectFactory;
import org.ect.codegen.v2.core.NamedObjectSet;
import org.ect.codegen.v2.core.gen.FileLeafFactory.FileLeaf;

public class DirTreeFactory extends NamedObjectFactory<DirTreeFactory.DirTree> {

	//
	// METHODS
	//
	
	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	public boolean canConstruct(final String objectName) {
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty())
			throw new IllegalArgumentException(objectName);

		return true;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 */
	@Override
	protected DirTree newNamedObject(final String objectName) {
		if (objectName == null)
			throw new NullPointerException("objectName");
		if (objectName.isEmpty())
			throw new IllegalArgumentException(objectName);

		return new DirTree(objectName);
	}

	//
	// CLASSES
	//

	public class DirTree extends NamedObjectFactory<DirTree>.NamedObject {

		//
		// FIELDS
		//

		/**
		 * A factory for leaves.
		 */
		private final FileLeafFactory leafFactory = new FileLeafFactory();

		/**
		 * The leaves of this tree.
		 */
		private final NamedObjectSet<FileLeaf> leaves = new NamedObjectSet<FileLeaf>();

		/**
		 * A factory for subtrees.
		 */
		private final DirTreeFactory subtreeFactory = new DirTreeFactory();

		/**
		 * The subtrees of this tree.
		 */
		private final NamedObjectSet<DirTree> subtrees = new NamedObjectSet<DirTree>();

		//
		// CONSTRUCTORS
		//

		/**
		 * Invokes: <code>super(name)</code>.
		 * 
		 * @see NamedObject#NamedObject(String)
		 */
		private DirTree(final String name) {
			super(name);
		}

		//
		// METHODS
		//

		/**
		 * Adds the leaf <code>leaf</code> to this tree.
		 * 
		 * <p>
		 * Returns <code>true</code> if this tree contained no leaf named as
		 * <code>leaf</code> before; <code>false</code> otherwise.
		 * </p>
		 * 
		 * @param leaf
		 *            The leaf. Not <code>null</code>.
		 * @return A boolean.
		 * @throws NullPointerException
		 *             If <code>leaf==null</code>.
		 */
		public final boolean addLeaf(final FileLeaf leaf) {
			if (leaf == null)
				throw new NullPointerException("leaf");

			return leaves.add(leaf);
		}

		/**
		 * Adds a leaf named <code>leafName</code> with the content
		 * <code>content</code> to this tree.
		 * 
		 * <p>
		 * Returns <code>true</code> if this tree contained no leaf named
		 * <code>leafName</code> before; <code>false</code> otherwise.
		 * </p>
		 * 
		 * @param leafName
		 *            The name of the leaf. Not <code>null</code> or empty.
		 * @param content
		 *            The content of the leaf. Not <code>null</code>.
		 * @return A boolean.
		 * @throws IllegalArgumentException
		 *             If <code>name.isEmpty()</code>.
		 * @throws NullPointerException
		 *             If <code>leafName==null</code> or
		 *             <code>content==null</code>.
		 * 
		 * @see String#isEmpty()
		 */
		public final boolean addLeaf(final String leafName, final Object content) {

			/* Validate arguments. */
			if (leafName == null)
				throw new NullPointerException("leafName");
			if (content == null)
				throw new NullPointerException("content");
			if (leafName.isEmpty())
				throw new IllegalArgumentException(leafName);

			/* Add. */
			final FileLeaf leaf = leafFactory.construct(leafName);
			leaf.setContent(content);
			return addLeaf(leaf);
		}

		/**
		 * Adds the tree <code>tree</code> to this tree as a subtree. If this
		 * tree already has a subtree named as <code>tree</code>, this method
		 * merges the existing subtree and <code>tree</code>.
		 * 
		 * @param tree
		 *            The tree. Not <code>null</code>.
		 * @param policy
		 *            The policy for resolving leaf name conflicts. Not
		 *            <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>tree==null</code> or <code>policy==null</code>.
		 */
		public final void addOrMergeSubtree(final DirTree tree,
				final ConflictResolutionPolicy policy) {

			/* Validate arguments. */
			if (tree == null)
				throw new NullPointerException("tree");
			if (policy == null)
				throw new NullPointerException("policy");

			/* Import. */
			final String treeName = tree.getName();
			if (hasSubtree(treeName)) {

				/* Get the existing tree with the same name as $tree. */
				final DirTree existingTree = getSubtree(treeName);

				/* Add the leaves of $tree to $existingTree. */
				for (final FileLeaf l : tree.leaves)
					switch (policy) {
					case IGNORE:
						if (!existingTree.hasLeaf(l.getName()))
							existingTree.addLeaf(l);
					}

				/* Import the subtrees of $tree to $existingTree. */
				for (final DirTree t : tree.subtrees)
					existingTree.addOrMergeSubtree(t, policy);

			} else
				subtrees.add(tree);
		}

		/**
		 * Adds the tree <code>tree</code> to this tree as a subtree.
		 * 
		 * <p>
		 * Returns <code>true</code> if this tree contained no subtree named as
		 * <code>tree</code> before; <code>false</code> otherwise.
		 * </p>
		 * 
		 * @param tree
		 *            The tree. Not <code>null</code>.
		 * @return A boolean.
		 * @throws NullPointerException
		 *             If <code>tree==null</code>.
		 */
		public final boolean addSubtree(final DirTree tree) {
			if (tree == null)
				throw new NullPointerException("tree");

			return subtrees.add(tree);
		}

		/**
		 * Adds a subtree named <code>subtreeName</code> to this tree.
		 * 
		 * <p>
		 * Returns <code>true</code> if this tree contained no subtree named
		 * <code>subtreeName</code> before; <code>false</code> otherwise.
		 * </p>
		 * 
		 * @param subtreeName
		 *            The name of the subtree. Not <code>null</code> or empty.
		 * @return A boolean.
		 * @throws IllegalArgumentException
		 *             If <code>subtreeName.isEmpty()</code>.
		 * @throws NullPointerException
		 *             If <code>subtreeName==null</code>.
		 * 
		 * @see String#isEmpty()
		 */
		public final boolean addSubtree(final String subtreeName) {

			/* Validate arguments. */
			if (subtreeName == null)
				throw new NullPointerException("subtreeName");
			if (subtreeName.isEmpty())
				throw new IllegalArgumentException(subtreeName);

			/* Add. */
			return addSubtree(subtreeFactory.construct(subtreeName));
		}

		/**
		 * Gets the leaf named <code>leafName</code>.
		 * 
		 * @param leafName
		 *            The name of the leaf. Not <code>null</code>.
		 * @return A leaf. Never <code>null</code>.
		 * @throws IllegalStateException
		 *             If <code>!hasLeaf(name)</code>
		 * @throws NullPointerException
		 *             If <code>name==null</code>.
		 * 
		 * @see #hasLeaf(String)
		 */
		public final FileLeaf getLeaf(final String leafName) {

			/* Validate arguments. */
			if (leafName == null)
				throw new NullPointerException("leafName");
			if (!hasLeaf(leafName))
				throw new IllegalStateException("!hasLeaf(" + leafName + ")");

			/* Get. */
			return leaves.get(leafName);
		}

		/**
		 * Gets the names of the leaves of this tree.
		 * 
		 * @return A collection of strings. Never <code>null</code>.
		 */
		public final Collection<String> getLeafNames() {
			final List<String> list = new ArrayList<String>();
			for (final FileLeaf l : leaves)
				list.add(l.getName());

			Collections.sort(list);
			return list;
		}

		/**
		 * Gets the subtree named <code>subtreeName</code>.
		 * 
		 * @param subtreeName
		 *            The name of the subtree. Not <code>null</code>.
		 * @return A tree. Never <code>null</code>.
		 * @throws IllegalStateException
		 *             If <code>!hasSubtree(subtreeName)</code>
		 * @throws NullPointerException
		 *             If <code>subtreeName==null</code>.
		 * 
		 * @see #hasSubtree(String)
		 */
		public final DirTree getSubtree(final String subtreeName) {

			/* Validate arguments. */
			if (subtreeName == null)
				throw new NullPointerException("subtreeName");
			if (!hasSubtree(subtreeName))
				throw new IllegalStateException("!hasSubtree(" + subtreeName
						+ ")");

			/* Get. */
			return subtrees.get(subtreeName);
		}

		/**
		 * Gets the names of the subtrees of this tree.
		 * 
		 * @return A collection of strings. Never <code>null</code>.
		 */
		public final Collection<String> getSubtreeNames() {
			final List<String> list = new ArrayList<String>();
			for (final DirTree t : subtrees)
				list.add(t.getName());

			Collections.sort(list);
			return list;
		}

		/**
		 * Checks if this tree has a leaf named <code>leafName</code>.
		 * 
		 * <p>
		 * Returns <code>true</code> if this tree has a leaf named
		 * <code>leafName</code>; <code>false</code> otherwise.
		 * </p>
		 * 
		 * @param leafName
		 *            The name. Not <code>null</code>.
		 * @return A boolean.
		 * @throws NullPointerException
		 *             If <code>leafName==null</code>.
		 */
		public final boolean hasLeaf(final String leafName) {
			if (leafName == null)
				throw new NullPointerException("leafName");

			return leaves.contains(leafName);
		}

		/**
		 * Checks if this tree has a subtree named <code>subtreeName</code>.
		 * 
		 * <p>
		 * Returns <code>true</code> if this tree has a subtree named
		 * <code>subtreeName</code>; <code>false</code> otherwise.
		 * </p>
		 * 
		 * @param subtreeName
		 *            The name. Not <code>null</code> or empty.
		 * @return A boolean.
		 * @throws NullPointerException
		 *             If <code>subtreeName==null</code>.
		 * 
		 * @see String#isEmpty()
		 */
		public final boolean hasSubtree(final String subtreeName) {
			if (subtreeName == null)
				throw new NullPointerException("subtreeName");

			return subtrees.contains(subtreeName);
		}

		/**
		 * Imports the file or the directory <code>fileOrDir</code> into this
		 * tree as a leaf or as a subtree named <code>newFileOrDirName</code>.
		 * If this tree already has a leaf or a subtree named
		 * <code>newFileOrDirName</code>, this method merges the existing
		 * subtree and <code>tree</code>.
		 * 
		 * @param fileOrDir
		 *            The file or directory. Not <code>null</code>.
		 * @param newFileOrDirName
		 *            The new name of the file or directory to import;
		 *            <code>null</code> for keeping the old name. Not empty.
		 * @param policy
		 *            The policy for resolving leaf name conflicts. Not
		 *            <code>null</code>.
		 * @throws IllegalArgumentException
		 *             If
		 *             <code>newFileOrDirName!=null&&newFileOrDirName.isEmpty()</code>
		 *             .
		 * @throws NullPointerException
		 *             If <code>fileOrDir==null</code> or
		 *             <code>policy==null</code>.
		 * 
		 * @see String#isEmpty()
		 */
		public final void importFile(final File fileOrDir,
				final String newFileOrDirName,
				final ConflictResolutionPolicy policy) {

			/* Validate arguments. */
			if (fileOrDir == null)
				throw new NullPointerException("fileOrDir");
			if (policy == null)
				throw new NullPointerException("policy");
			if (newFileOrDirName != null && newFileOrDirName.isEmpty())
				throw new IllegalArgumentException(newFileOrDirName);

			/* Get the name. */
			final String name = newFileOrDirName == null ? fileOrDir.getName()
					: newFileOrDirName;

			/*
			 * Add files and directories recursively if $fileOrDir is a
			 * directory.
			 */
			if (fileOrDir.isDirectory()) {
				final DirTree tree = new DirTree(name);
				for (final File f : fileOrDir.listFiles())
					tree.importFile(f, null, policy);

				addOrMergeSubtree(tree, policy);
			}

			/* Add $file if $fileOrDir is a file. */
			else
				addLeaf(name, fileOrDir);
		}

		/**
		 * <em>Inherited documentation:</em>
		 * 
		 * <p>
		 * {@inheritDoc}
		 * </p>
		 */
		@Override
		public final String toString() {
			return toString(0);
		}

		/**
		 * Returns a string representation of this tree.
		 * 
		 * @param offset
		 *            The nonnegative offset.
		 * @return A string. Never <code>null</code>.
		 * @throws IllegalArgumentException
		 *             If <code>offset&lt;0</code>.
		 */
		public final String toString(int offset) {
			if (offset < 0)
				throw new IllegalArgumentException(Integer.toString(offset));

			final StringBuilder builder = new StringBuilder();

			/* Append the name of this tree. */
			final StringBuilder whitespaceBuilder = new StringBuilder();
			whitespaceBuilder.append("\n");
			for (int i = 0; i < offset; i++)
				whitespaceBuilder.append("  ");

			final String whitespace = whitespaceBuilder.toString();

			builder.append(whitespace);
			builder.append(super.getName());
			builder.append("/");

			/* Append the subtrees of this tree, sorted by their names. */
			final List<DirTree> sortedTrees = new ArrayList<DirTree>(subtrees);
			Collections.sort(sortedTrees);
			for (final DirTree t : sortedTrees)
				builder.append(t.toString(offset + 1));

			/* Append the leaves of this tree, sorted by their names. */
			final List<FileLeaf> sortedLeafs = new ArrayList<FileLeaf>(leaves);
			Collections.sort(sortedLeafs);
			for (final FileLeaf l : sortedLeafs) {
				builder.append(whitespace);
				builder.append("  ");
				builder.append(l.getName());
			}

			return builder.toString();
		}

		/**
		 * Tries to write the content of this tree to a directory named as this
		 * tree at the location <code>parentLocation</code>.
		 * 
		 * <p>
		 * Returns the location of the written directory.
		 * </p>
		 * 
		 * @param parentLocation
		 *            The location. Not <code>null</code>.
		 * @return A string. Never <code>null</code>.
		 * @throws NullPointerException
		 *             If <code>parentLocation==null</code>.
		 * @throws WriterException
		 *             If something goes wrong.
		 */
		public final String tryWrite(final String parentLocation)
				throws DirTreeException {

			/* Validate arguments. */
			if (parentLocation == null)
				throw new NullPointerException("parentLocation");

			/* Try. */
			try {

				/* Compute a location for the directory to write. */
				String tentativeLocation = parentLocation + File.separator
						+ super.getName();

				final int index = tentativeLocation.lastIndexOf(File.separator);
				final String prefix, postfix;
				if (index == -1) {
					prefix = "";
					postfix = tentativeLocation;
				} else {
					prefix = tentativeLocation.substring(0, index)
							+ File.separator;
					postfix = tentativeLocation.substring(index + 1);
				}

				tentativeLocation = prefix
						+ postfix.replaceAll("[^A-Za-z0-9_\\-\\.]", "");

				File dir = new File(tentativeLocation);

				int i = 1;
				final String base = tentativeLocation;
				while (dir.exists())
					dir = new File(tentativeLocation = base + "-" + i++);

				/* Create the directory to write. */
				if (!dir.mkdirs())
					throw new DirTreeException(
							"I failed to create a directory at the location \""
									+ tentativeLocation + "\".");

				/* Write the content of the directory to write. */
				for (final DirTree t : subtrees)
					t.tryWrite(tentativeLocation);

				for (final FileLeaf f : leaves)
					f.tryWrite(tentativeLocation);

				/* Return. */
				return dir.getCanonicalPath();
			}

			/* Catch. */
			catch (final Exception e) {
				throw new DirTreeException(
						"I failed to write the directory named \""
								+ super.getName()
								+ "\" and its content at the location \""
								+ parentLocation + "\".", e);
			}
		}

		//
		// EXCEPTIONS
		//

		public class DirTreeException extends Exception {
			private static final long serialVersionUID = 1L;

			public DirTreeException(final String message) {
				super(message);
			}

			public DirTreeException(final String message, final Throwable cause) {
				super(message, cause);
			}
		}
	}
}
