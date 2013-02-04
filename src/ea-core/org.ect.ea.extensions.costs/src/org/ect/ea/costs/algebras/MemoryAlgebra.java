/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs.algebras;

import java.text.MessageFormat;
import java.text.ParseException;


import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.CostsObject;
import org.ect.ea.costs.UnsupportedCostsTypeException;
import org.ect.ea.costs.types.IntegerCosts;


/**
 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getMemoryAlgebra()
 * @model kind="class"
 * @generated
 */
public class MemoryAlgebra extends CostsAlgebra {

	/**
	 * @generated NOT
	 */
	public String getName() 
	{
		return "Memory";
	}
	
	/**
	 * @generated NOT
	 */
	public String getUnits()
	{
		return "k";
	}
	
	/**
	 * @generated NOT
	 */
	public MemoryAlgebra() {
		super();
	}

	
	/**
	 * @generated NOT
	 */
	public CostsObject getNull()
	{
		return new IntegerCosts(0);
	}
	
	/**
	 * The 'choose' operation for the memory algebra.
 	 * @generated NOT
	 */
	public CostsObject choose(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		int result = Math.max(getValue(c1), getValue(c2));
		return new IntegerCosts(result);
	}

	
	/**
	 * The 'combineSequentially' operation for the memory algebra.
 	 * @generated NOT
	 */
	public CostsObject combineSequentially(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		int result = getValue(c1) + getValue(c2);
		return new IntegerCosts(result);
	}

	
	/**
	 * The 'combineParallel' operation for the memory algebra.
 	 * @generated NOT
	 */
	public CostsObject combineParallel(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		int result = getValue(c1) + getValue(c2);
		return new IntegerCosts(result);
	}

	

	/**
	 * @throws ParseException 
	 * @see org.ect.ea.costs.CostsAlgebra#parse(java.lang.String)
	 * @generated NOT
	 */
	public CostsObject parse(String costs) throws ParseException {
			
		Long result = new Long(0);
		
		try{
			MessageFormat format = new MessageFormat("{0,number,integer}k");
			Object[] parsed = format.parse(costs);
			result = (Long) parsed[0];
		}
		catch (ParseException e)
		{
			try{
				MessageFormat format2 = new MessageFormat("{0,number,integer} k");
				Object[] parsed2 = format2.parse(costs);
				result = (Long) parsed2[0];
			}
			catch (ParseException e2)
			{		
				MessageFormat format3 = new MessageFormat("{0,number,integer}");
				Object[] parsed3 = format3.parse(costs);
			    result = (Long) parsed3[0];
			}
		}

		return new IntegerCosts( result.intValue() );
		
	}
	
	/**
	 * @generated NOT
	 */
	public String prettyValue(CostsObject c) throws UnsupportedCostsTypeException {
		return (Integer.toString(getValue(c)));
	}
	

	
	
	/**
	 * Helper method for extracting the integer value from a CostsObject.
	 * @param costs CostsObject.
	 * @return The integer value.
	 * @throws UnsupportedCostsTypeException If the argument is not an instance of IntegerCosts.
	 */
	private int getValue(CostsObject costs) throws UnsupportedCostsTypeException {
		
		// Check whether the type is correct.
		if (!(costs instanceof IntegerCosts)) {
			throw new UnsupportedCostsTypeException(this, costs.getType());
		}
		
		// Cast the object and extract the value.
		return ((IntegerCosts) costs).getIntegerValue();
	
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
	protected EClass eStaticClass() {
		return AlgebrasPackage.Literals.MEMORY_ALGEBRA;
	}

}