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

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.CostsObject;
import org.ect.ea.costs.UnsupportedCostsTypeException;
import org.ect.ea.costs.types.IntegerCosts;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bandwidth Algebra</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getBandwidthAlgebra()
 * @model kind="class"
 * @generated
 */
public class BandwidthAlgebra extends CostsAlgebra {
	/**
	 * @generated NOT
	 */
	public String getName() 
	{
		return "Bandwidth";
	}
	
	/**
	 * @generated NOT
	 */
	public String getUnits()
	{
		return "Kbps";
	}
	
	public CostsObject getNull()
	{
		return new IntegerCosts(0);
	}
	
	
	/**
	 * @generated NOT
	 */
	public BandwidthAlgebra() {
		super();
	}

	/**
	 * The 'choose' operation for the bandwidth algebra.
 	 * @generated NOT
	 */
	public CostsObject choose(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		int result = Math.max(getValue(c1), getValue(c2));
		return new IntegerCosts(result);
	}

	
	/**
	 * The 'combineSequentially' operation for the bandwidth algebra.
 	 * @generated NOT
	 */
	public CostsObject combineSequentially(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		int result = Math.max(getValue(c1), getValue(c2));
		return new IntegerCosts(result);
	}

	
	/**
	 * The 'combineParallel' operation for the bandwidth algebra.
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
			MessageFormat format = new MessageFormat("{0,number,integer}Kbps",Locale.ENGLISH);
			Object[] parsed = format.parse(costs);
			result = (Long) parsed[0];
		}
		catch (ParseException e)
		{
			try{
				MessageFormat format2 = new MessageFormat("{0,number,integer} Kbps",Locale.ENGLISH);
				Object[] parsed2 = format2.parse(costs);
				result = (Long) parsed2[0];
			}
			catch (ParseException e2)
			{		
				MessageFormat format3 = new MessageFormat("{0,number,integer}",Locale.ENGLISH);
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
		return AlgebrasPackage.Literals.BANDWIDTH_ALGEBRA;
	}

} // BandwidthAlgebra