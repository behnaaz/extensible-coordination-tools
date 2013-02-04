package org.ect.ea.extensions.constraints;

import org.eclipse.emf.common.util.EList;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IValidationResult;


/**
 * @generated
 */
public interface Constraint extends IExtension {

	/**
	 * Validate this constraint.
	 * @generated NOT
	 */
	public IValidationResult validate();
	
	/**
	 * Get all elements of this constraint.
	 * @generated NOT
	 */
	public EList<Element> getAllElements();
	
	/**
	 * Convert the constraint into disjunctive
	 * normal form (DNF).
	 * @deprecated use org.ect.ea.extensions.constraints.algorithms.ConstraintNormalise 
	 * @generated NOT
	 */
	public Disjunction toDNF();
	
}
