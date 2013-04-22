package org.ect.codegen.v2.core.rt.java.solver.constraints;

import org.ect.codegen.v2.core.rt.java.solver.Constraint;

public class True extends Constraint {

	//
	// CONSTRUCTORS
	//

	/**
	 * Constructs a <em>true</em> constraint. A true constraint is always
	 * satisfied.
	 */
	public True() {
		super();
	}

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
	public Constraint copy() {
		return new True();
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUnsatisfiable() {
		if (!super.hasProblem())
			throw new IllegalStateException();

		return false;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public boolean initialize() {
		if (!super.hasProblem())
			throw new IllegalStateException();

		return true;
	}

	/**
	 * <em>Inherited documentation:</em>
	 * 
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "true";
	}
}
