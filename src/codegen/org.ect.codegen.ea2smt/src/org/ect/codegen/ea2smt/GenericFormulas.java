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


/** Generic Formula encapsulation. An object of this class can hold any number
 * of formulas, each formula is associated with an automaton name and a formula 
 * name. */

class GenericFormulas {

	private EList<StringTriple> contents = new BasicEList<StringTriple>();
	
	public GenericFormulas() {}
	
	/***/
	public void addFormula(String automatonName, String formulaName, String formula) {
		this.contents.add(new StringTriple(automatonName, formulaName, formula));
	}
	public void addFormula(StringTriple triple) {
		this.contents.add(triple);
	}
	
	public EList<StringTriple> getContents() {
		return this.contents;
	}
	
	public String toString() {
		String result = "";
		for (StringTriple trip: this.contents) {
			result = result + "DEFINE " + trip.getFirst() + "." + trip.getSecond() + " : BOOLEAN := " + trip.getThird() + "\n"; 
		}
		result = result + "\n\n";
		return result;
	}
	
	public static GenericFormulas merge(EList<GenericFormulas> list) {
		
		//check for duplicates
		EList<String> checkList = new BasicEList<String>();
		for (GenericFormulas gf: list) {
			for (StringTriple cont: gf.getContents()) {
				checkList.add(cont.getFirst() + "." + cont.getSecond());
			}
		}
		checkList = new StringListExtension(checkList).getDuplicateEntries();
		if (!checkList.isEmpty()) {
			throw new UnsupportedOperationException("Cannot merge target formulas for same automaton and same name.");
		}
		
		//compose the result
		GenericFormulas result = new GenericFormulas();
		for (GenericFormulas gf: list) {
			for (StringTriple st: gf.getContents()) {
				result.addFormula(st);
			}
		}
		
		return result;
	}
}
