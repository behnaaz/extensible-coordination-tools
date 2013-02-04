/*******************************************************************************
 * <copyright>
 * This file is part of the Extensible Coordination Tools (ECT).
 * Copyright (c) 2013 ECT developers. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 *******************************************************************************/
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
import org.ect.ea.costs.types.FloatCosts;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dealy Algebra</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.ect.ea.costs.algebras.AlgebrasPackage#getDelayAlgebra()
 * @model kind="class"
 * @generated
 */
public class DelayAlgebra extends CostsAlgebra {
	/**
	 * @generated Not
	 */
	public DelayAlgebra() {
		super();
	}
	
	/**
	 * @generated NOT
	 */
	public String getName() 
	{
		return "Delay";
	}
	
	/**
	 * @generated NOT
	 */
	public String getUnits()
	{
		return "s";
	}
	
	public CostsObject getNull()
	{
		return new FloatCosts(0);
	}
	
	/**
	 * The 'choose' operation for the delay algebra.
 	 * @generated NOT
	 */
	public CostsObject choose(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		float result = Math.max(getValue(c1), getValue(c2));
		return new FloatCosts(result);
	}

	
	/**
	 * The 'combineSequentially' operation for the delay algebra.
 	 * @generated NOT
	 */
	public CostsObject combineSequentially(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		float result = getValue(c1) + getValue(c2);
		return new FloatCosts(result);
	}

	
	/**
	 * The 'combineParallel' operation for the delay algebra.
 	 * @generated NOT
	 */
	public CostsObject combineParallel(CostsObject c1, CostsObject c2) throws UnsupportedCostsTypeException {
		float result = Math.max(getValue(c1),getValue(c2));
		return new FloatCosts(result);
	}

	/**
	 * @throws ParseException 
	 * @throws  
	 * @see org.ect.ea.costs.CostsAlgebra#parse(java.lang.String)
	 * @generated NOT
	 */
	public CostsObject parse(String costs) throws ParseException {
			
		Double result = new Double(0);
		String temp = null;
				
		try{
			MessageFormat format = new MessageFormat("{0,number,#.#}s",Locale.ENGLISH);
			Object[] parsed = format.parse(costs);
			temp =parsed[0].toString();
			if(temp.indexOf(".")<=0)
			{
				result=new Double(temp.concat(".0"));
			}
			else result = (Double) parsed[0];
		}
		catch (ParseException e)
		{
			try{
				MessageFormat format2 = new MessageFormat("{0,number,#.#} s",Locale.ENGLISH);
				Object[] parsed2 = format2.parse(costs);
				temp =parsed2[0].toString();
				if(temp.indexOf(".")<=0)
				{
					result=new Double(temp.concat(".0"));
				}
				else result = (Double) parsed2[0];
			}
			catch (ParseException e2)
			{
				MessageFormat format3 = new MessageFormat("{0,number,#.#}",Locale.ENGLISH);
				Object[] parsed3 = format3.parse(costs);
				temp =parsed3[0].toString();
				if(temp.indexOf(".")<=0)
				{
					result=new Double(temp.concat(".0"));
				}
				else result = (Double) parsed3[0];
			}
		}
						
		return new FloatCosts( result.floatValue());
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
		return AlgebrasPackage.Literals.DELAY_ALGEBRA;
	}

} // DelayAlgebra