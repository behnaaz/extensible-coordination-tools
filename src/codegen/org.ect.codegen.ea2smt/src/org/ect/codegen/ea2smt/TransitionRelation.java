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

import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;


/** Encapsulation of the transition relation of a mathsat file. Every transition 
 * relation belongs to a certain automaton, an object of this class can
 * hold transition relations for more than one automaton. An automaton name is 
 * given in the constructor as the default name, but transition relations can be
 * added with other automata names as well. */

class TransitionRelation {

	/** The contents of this object. The pair consists of (automatonName, formulaName), 
	 * the EList contains the unfolded entries. */
	private EMap<StringPair, EList<String>> contents = new BasicEMap<StringPair, EList<String>>();
	
	/** Length of lists in contents. */
	private int length = 0;
	
	/** The default automaton name. */
	private String automatonName = "";
	
	
	private EList<String> formulaDefinitions = new BasicEList<String>();
	
	public TransitionRelation(String name) {
		this.automatonName = name;
	}
	
	/** Add a new set of constraints with a given name that belong to the automaton
	 * given in the constructor. Only added if the size of the list is not 0. 
	 * A set of constraints consists of a list of constraints of the same type (for
	 * example consistency constraints), which are already unfolded. They all 
	 * need to be unfolded to the same depth, i.e., all lists need to have the 
	 * same length. 
	 * @param name The 'name' of the constraints, for example TRANS or LOC. 
	 * @param list The list of constraints. */
	public void addConstraints(String name, EList<String> list) {
		StringPair pair = new StringPair(this.automatonName, name);
		this.addConstraints(pair, list);
	}
	
	/** Add a new set of constraints with a given name that belong to an automaton
	 * with a given name. Only added if the size of the list is not 0. 
	 * A set of constraints consists of a list of constraints of the same type (for
	 * example consistency constraints), which are already unfolded. They all 
	 * need to be unfolded to the same depth, i.e., all lists need to have the 
	 * same length. 
	 * @param names A pair of (automatonName, formulaName), where automatonName is
	 * the name of the automaton the constraints belong to, and formulaName is the
	 * 'name' of the constraints, for example TRANS or LOC. 
	 * @param list The list of constraints. */
	public void addConstraints(StringPair names, EList<String> list) {
		
		if (list.size() != 0) {
			this.contents.put(names, list);

			if (this.length == 0) { //set initial size
				this.length = list.size();
			} else if (this.length != list.size()) { //compare with size of previously added
				throw new UnsupportedOperationException("Transition lists must have the same length (or zero)");
			}
		}
	}
	
	
	public String toString() {
		String result = CodegenUtils.MathSAT_LINE_COMMENT + CodegenUtils.MathSAT_LINE_COMMENT + " Transition relation:\n";
	
		boolean addDefs = this.formulaDefinitions.isEmpty();
		
		for (int i = 0; i < this.length; i++) { 
			for (Map.Entry<StringPair, EList<String>> me: this.contents) {
				String def = me.getKey().getFirst() + "." + me.getKey().getSecond() + (i+1);
				if (addDefs) this.formulaDefinitions.add(def);
				result = result + "DEFINE " + def + " : BOOLEAN := " + me.getValue().get(i) + "\n";
			}
		}
		
//		for (int i = 0; i < this.length; i++) {
//			for (int j = 0; j < this.contents.size(); j++) {
//				String pref = (!this.automatonName.equals("")) ? this.automatonName + "." : "";
//				String def = pref + this.contents.get(j).getKey() + (i+1);
//				result = result + "DEFINE " + def + " : BOOLEAN := " + this.contents.get(j).getValue().get(i) + "\n";
//				formulaDefinitions.add(def);
//			}
//		}
		result = result + "\n\n";
		return result;
	}
	
	public EList<String> getFormulaDefinitions() {
		//initialise if this has not been done before (in toString())
		for (int i = 0; i < this.length; i++) { 
			for (Map.Entry<StringPair, EList<String>> me: this.contents) {
				String def = me.getKey().getFirst() + "." + me.getKey().getSecond() + (i+1);
				this.formulaDefinitions.add(def);
			}
		}	
		return this.formulaDefinitions;
	}
	
	public EMap<StringPair, EList<String>> getContents() {
		return this.contents;
	}
	
	public String getAutomatonName() {
		return this.automatonName;
	}
	
	public static TransitionRelation merge (EList<TransitionRelation> rels) {
		TransitionRelation result = new TransitionRelation("");
		//check that no double names exist
		EList<String> checkList = new BasicEList<String>();
		for (TransitionRelation t: rels) {
			for (Map.Entry<StringPair, EList<String>> me: t.getContents()) {
				checkList.add(me.getKey().getFirst() + me.getKey().getSecond());
			}
		}
		checkList = new StringListExtension(checkList).getDuplicateEntries();
		if (!checkList.isEmpty()) {
			throw new UnsupportedOperationException("Cannot merge transition relations for same automaton/constraint name.");
		}
		
		//compose result
		for (TransitionRelation t: rels) {			
			for (Map.Entry<StringPair, EList<String>> me: t.getContents()) {
				result.addConstraints(me.getKey(), me.getValue());
			}
		}
		
		return result;
	}
	
}
