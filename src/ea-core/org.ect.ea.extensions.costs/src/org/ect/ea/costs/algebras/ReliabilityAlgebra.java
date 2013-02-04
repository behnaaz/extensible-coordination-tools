/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.ect.ea.costs.algebras;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;



import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.CostsObject;
import org.ect.ea.costs.UnsupportedCostsTypeException;
import org.ect.ea.costs.types.FloatCosts;
import org.ect.ea.costs.types.IntegerCosts;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reliability Algebra</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getReliabilityAlgebra()
 * @model kind="class"
 * @generated
 */
public class ReliabilityAlgebra extends CostsAlgebra {
	
	/**
	 * @generated Not
	 */
	public ReliabilityAlgebra() {
		super();
	}
	/**
	 * @generated NOT
	 */
	public String getName() 
	{
		return "Reliability";
	}
	
	/**
	 * @generated NOT
	 */
	public String getUnits()
	{
		return "R";
	}
	
	public CostsObject getNull()
	{
		return new FloatCosts(0);
	}
	
	/**
	 * The 'choose' operation for the reliability algebra.
 	 * @generated NOT
	 */
	public CostsObject choose(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		float result = Math.min(getValue(c1), getValue(c2));
		return new FloatCosts(result);
	}

	
	/**
	 * The 'combineSequentially' operation for the reliability algebra.
 	 * @generated NOT
	 */
	public CostsObject combineSequentially(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		float result = getValue(c1) * getValue(c2);
		return new FloatCosts(result);
	}

	
	/**
	 * The 'combineParallel' operation for the reliability algebra.
 	 * @generated NOT
	 */
	public CostsObject combineParallel(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		float result = getValue(c1) * getValue(c2);
		return new FloatCosts(result);
	}

	/**
	 * @throws ParseException 
	 * @see org.ect.ea.costs.CostsAlgebra#parse(java.lang.String)
	 * @generated NOT
	 */
	public CostsObject parse(String costs) throws ParseException {
			
		Double result = new Double(0);
		float maximum = 1;
		float DefaultValue=0;
				
		try{
			MessageFormat format = new MessageFormat("{0,number,#.#}R",Locale.ENGLISH);
			Object[] parsed = format.parse(costs);
			result = (Double) parsed[0];
		}
		catch (ParseException e)
		{
			try{
				MessageFormat format2 = new MessageFormat("{0,number,#.#} R",Locale.ENGLISH);
				Object[] parsed2 = format2.parse(costs);
				result = (Double) parsed2[0];
			}
			catch (ParseException e2)
			{		
				MessageFormat format3 = new MessageFormat("{0,number,#.#}",Locale.ENGLISH);
				Object[] parsed3 = format3.parse(costs);
			    result = (Double) parsed3[0];
			}
		}
		if(result.floatValue()>=maximum)	return new FloatCosts(DefaultValue);
		

		else return new FloatCosts( result.floatValue());
		
	}
	
	/**
	 * @generated NOT
	 */
	public String prettyValue(CostsObject c) throws UnsupportedCostsTypeException {
		return (Float.toString(getValue(c)));
	}
	

	
	
	/**
	 * Helper method for extracting the integer value from a CostsObject.
	 * @param costs CostsObject.
	 * @return The integer value.
	 * @throws UnsupportedCostsTypeException If the argument is not an instance of IntegerCosts.
	 */
	private float getValue(CostsObject costs) throws UnsupportedCostsTypeException {
		
		// Check whether the type is correct.
		if (!(costs instanceof FloatCosts)) {
			throw new UnsupportedCostsTypeException(this, costs.getType());
		}
		
		// Cast the object and extract the value.
		return ((FloatCosts) costs).getFloatValue();
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (C) SEN3 group at CWI, Amsterdam. Published under the terms of the GPL2.";

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AlgebrasPackage.Literals.RELIABILITY_ALGEBRA;
	}

} // ReliabilityAlgebra