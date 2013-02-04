/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs;

import java.text.ParseException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;


import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * @see org.ect.ea.costs.CostsPackage#getCostsAlgebra()
 * @model kind="class" abstract="true" interface="true"
 * @generated
 */
public abstract class CostsAlgebra extends EObjectImpl implements EObject {
		
	/**
	 * Do the 'choose' operation of this Q-algebra.
	 */
	public abstract CostsObject choose(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException;

	
	/**
	 * Do the 'combineSequentially' operation of this Q-algebra.
	 */
	public abstract CostsObject combineSequentially(CostsObject c1, CostsObject c2)	throws UnsupportedCostsTypeException;

	
	/**
	 * Do the 'combineParallel' operation of this Q-algebra.
	 */
	public abstract CostsObject combineParallel(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException;


	/**
	 * Parse a string to a CostsObject.
	 */
	public abstract CostsObject parse(String costs) throws ParseException;

	/**
	 * Pretty print a cost value (without units).
	 */
	public abstract String prettyValue(CostsObject c) throws UnsupportedCostsTypeException;

	/**
	 * Return the name of the cost type
	 */
    public abstract String getName();

    /**
	 * Return the units of the cost type
	 */
	public abstract String getUnits();

	/**
	 * Return the null value
	 */
	public abstract CostsObject getNull();
	
	
	public String prettyPrint(CostsObject c) throws UnsupportedCostsTypeException
	{
		return (prettyValue(c) + " " + getUnits());
	}
	
	
	
	/**
	 * @Override
	 */
	public String toString()
	{
		return getName();
	}
	
	
	/* inequalities are defined via the choose operator */
	
	public boolean Qleq (CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException
	{
		return (choose(c1,c2) == c2);
	}
	public boolean Qgeq (CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException
	{ 
		return (Qleq(c2,c1)); 
	}
	
	public boolean Qeq (CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException
	{
		return (Qleq(c1,c2) && Qgeq (c1,c2));
	}
	
	public boolean Qlth (CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException
	{
		return (Qleq(c1,c2) && !(Qeq(c1,c2)));
	}
	
	public boolean Qgth (CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException
	{
		return (Qgeq(c1,c2) && !(Qeq(c1,c2)));
	}
	
	
	/* ---------------------------------------------------------------- *
	 * GENERATED CODE.                                                  *
	 * Do not edit below this line. If you need to edit, move it above  *
	 * this line and change the '@generated'-tag to '@generated NOT'.   *
	 * ---------------------------------------------------------------- */

	
	/**
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	/**
	 * @generated
	 */
	protected CostsAlgebra() {
		super();
	}

	/**
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CostsPackage.Literals.COSTS_ALGEBRA;
	}

}
