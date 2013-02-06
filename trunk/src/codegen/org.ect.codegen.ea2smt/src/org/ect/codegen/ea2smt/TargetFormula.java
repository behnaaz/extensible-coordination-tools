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
package org.ect.codegen.ea2smt;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.clocks.ClockUtils;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;


/** Encapsulation of a target formula of a mathsat file. Target formulas are 
 * prefixed with 'FORMULA' (instead of 'DEFINE'). */

class TargetFormula {

	private String formula = "";
	private String automatonName = "";
	private String formulaName = "";
	
	public TargetFormula() {
		
	}
	
	public void setFormula(String form) {
		this.formula = form;
	}
	public String getFormula() {
		return this.formula;
	}
	
	public void setFormulaName(String fName) {
		this.formulaName = fName;
	}
	public String getFormulaName() {
		return this.formulaName;
	}
	
	public void setAutomatonName(String aName) {
		this.automatonName = aName;
	}
	public String getAutomatonName() {
		return this.automatonName;
	}	
	
	/** Merge a number of Target Formulas. */
	public static Pair<TargetFormula, GenericFormulas> merge(EList<TargetFormula> list) {
		
		//check that there are no duplicate names
		EList<String> checkList = new BasicEList<String>();
		for (TargetFormula t: list) {
			checkList.add(t.getAutomatonName() + "." + t.getFormulaName());
		}
		checkList = new StringListExtension(checkList).getDuplicateEntries();
		if (!checkList.isEmpty()) {
			throw new UnsupportedOperationException("Cannot merge target formulas for same automaton and same name.");
		}
		
		//compose the result
		TargetFormula form = new TargetFormula();
		form.setAutomatonName("MERGE");
		form.setFormulaName("TARGET");
		GenericFormulas rest = new GenericFormulas();
		checkList.clear();
		for (TargetFormula tf: list) {
			rest.addFormula(tf.getAutomatonName(), tf.getFormulaName(), tf.getFormula());
			checkList.add(tf.getAutomatonName() + "." + tf.getFormulaName());
		}
		String s = "";
		for (String def: checkList) {
			s = s + def + " " + ClockUtils.LAND + " ";  
		}
		s = s.substring(0, s.lastIndexOf(ClockUtils.LAND));
		form.setFormula(s);
		
		return new Pair<TargetFormula, GenericFormulas>(form, rest);
	}
	
	public String toString() {
		return CodegenUtils.MathSAT_LINE_COMMENT + CodegenUtils.MathSAT_LINE_COMMENT + " Target formula:\nFORMULA " + this.formula;
	}
}
