package org.ect.codegen.v2.core.gen;

import java.util.concurrent.Callable;

import org.ect.codegen.v2.core.gen.DirTreeFactory.DirTree;

public abstract class AbstractGeneratorProgram<A extends AbstractGeneratorProgramArguments, V>
		implements Callable<V> {

	//
	// FIELDS
	//

	/**
	 * The arguments of this program.
	 */
	private final A arguments;

	/**
	 * The directory tree to put the generated code in.
	 */
	private final DirTree tree;

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a program.
	 * 
	 * @param arguments
	 *            The arguments of the program to construct. Not
	 *            <code>null</code>.
	 * @param tree
	 *            The directory tree to put the generated code in. Not
	 *            <code>null</code>.
	 * @throws NullPointerException
	 *             If <code>arguments==null</code> or <code>tree==null</code>.
	 */
	public AbstractGeneratorProgram(final A arguments, final DirTree tree) {

		/* Validate arguments. */
		if (arguments == null)
			throw new NullPointerException("arguments");
		if (tree == null)
			throw new NullPointerException("tree");

		/* Initialize. */
		this.arguments = arguments;
		this.tree = tree;
	}

	//
	// METHODS
	//

	/**
	 * Gets the arguments of this program.
	 * 
	 * @return Arguments. Never <code>null</code>.
	 */
	public A getArguments() {
		return arguments;
	}

	/**
	 * Gets the directory tree to put the generated code in.
	 * 
	 * @return A tree. Never <code>null</code>.
	 */
	public DirTree getTree() {
		return tree;
	}
}
