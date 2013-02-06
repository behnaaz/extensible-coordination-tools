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
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;

/** Encapsulation of the variable section of a mathsat file. Every variable that
 * is added belongs to a specific automaton. */

class VariableSection {
	
	/* First parameter is the name of the automaton, second the list of variables. */
	private EMap<String, EList<String>> integerVars = new BasicEMap<String, EList<String>>();
	/* First parameter is the name of the automaton, second the list of variables. */
	private EMap<String, EList<String>> booleanVars = new BasicEMap<String, EList<String>>();
	/* First parameter is the name of the automaton, second the list of variables. */
	private EMap<String, EList<String>> realVars = new BasicEMap<String, EList<String>>();
	
	String automatonName = ""; 
	
	/** Create a new variable section. Variables are stored together with the 
	 * automaton name. */
	public VariableSection(String autoName) {
		this.automatonName = autoName;
	}
	
	/** Set the list of integer variables belonging to the automaton given to the constructor. */
	public void setIntegerVars(EList<String> ints) {
		this.integerVars.put(this.automatonName, ints);
	}
	/** Set the list of integer variables belonging to an automaton with a given name. */
	public void setIntegerVars(EList<String> ints, String name) {
		this.integerVars.put(name, ints);
	}
	/** Add an integer variable to the list of variables belonging to the automaton 
	 * given to the constructor. */
	public void addIntegerVar(String i) {
		EList<String> temp = this.integerVars.get(this.automatonName);
		if (temp == null) { //if entry for this.automatonName does not exist yet			
			BasicEList<String> list = new BasicEList<String>();
			list.add(i);
			this.setIntegerVars(list);
		} else { //entry exists already
			temp.add(i);
			this.setIntegerVars(temp);
		}
	}
	/** Add an integer variable to the list of variables belonging to an automaton 
	 * with a given name. */
	public void addIntegerVar(String i, String name) {
		EList<String> temp = this.integerVars.get(name);
		if (temp == null) { //if entry for this.automatonName does not exist yet			
			BasicEList<String> list = new BasicEList<String>();
			list.add(i);
			this.setIntegerVars(list, name);
		} else { //entry exists already
			temp.add(i);
			this.setIntegerVars(temp, name);
		}
	}
	/** Get the integer variables belonging to the automaton given to the constructor. */
	public EList<String> getIntegerVars() {
		return this.integerVars.get(this.automatonName);
	}
	/** Get the integer variables belonging to an automaton with a given name. */
	public EList<String> getIntegerVars(String name) {
		return this.integerVars.get(name);
	}
	/** Get all integer variables. . */
	public EMap<String, EList<String>> getAllIntegerVars() {
		return this.integerVars;
	}
	
	/** Set the list of boolean variables belonging to the automaton given to the constructor. */
	public void setBooleanVars(EList<String> ints) {
		this.booleanVars.put(this.automatonName, ints);
	}
	/** Set the list of boolean variables belonging to an automaton with a given name. */
	public void setBooleanVars(EList<String> ints, String name) {
		this.booleanVars.put(name, ints);
	}
	/** Add an boolean variable to the list of variables belonging to the automaton 
	 * given to the constructor. */
	public void addBooleanVar(String i) {
		EList<String> temp = this.booleanVars.get(this.automatonName);
		if (temp == null) { //if entry for this.automatonName does not exist yet			
			BasicEList<String> list = new BasicEList<String>();
			list.add(i);
			this.setBooleanVars(list);
		} else { //entry exists already
			temp.add(i);
			this.setBooleanVars(temp);
		}
	}
	/** Add an boolean variable to the list of variables belonging to an automaton 
	 * with a given name. */
	public void addBooleanVar(String i, String name) {
		EList<String> temp = this.booleanVars.get(name);
		if (temp == null) { //if entry for this.automatonName does not exist yet			
			BasicEList<String> list = new BasicEList<String>();
			list.add(i);
			this.setBooleanVars(list, name);
		} else { //entry exists already
			temp.add(i);
			this.setBooleanVars(temp, name);
		}
	}
	/** Get the boolean variables belonging to the automaton given to the constructor. */
	public EList<String> getBooleanVars() {
		return this.booleanVars.get(this.automatonName);
	}
	/** Get the boolean variables belonging to an automaton with a given name. */
	public EList<String> getBooleanVars(String name) {
		return this.booleanVars.get(name);
	}
	/** Get all boolean variables. */
	public EMap<String, EList<String>> getAllBooleanVars() {
		return this.booleanVars;
	}
	
	/** Set the list of real variables belonging to the automaton given to the constructor. */
	public void setRealVars(EList<String> ints) {
		this.realVars.put(this.automatonName, ints);
	}
	/** Set the list of real variables belonging to an automaton with a given name. */
	public void setRealVars(EList<String> ints, String name) {
		this.realVars.put(name, ints);
	}
	/** Add an real variable to the list of variables belonging to the automaton 
	 * given to the constructor. */
	public void addRealVar(String i) {
		EList<String> temp = this.realVars.get(this.automatonName);
		if (temp == null) { //if entry for this.automatonName does not exist yet			
			BasicEList<String> list = new BasicEList<String>();
			list.add(i);
			this.setRealVars(list);
		} else { //entry exists already
			temp.add(i);
			this.setRealVars(temp);
		}
	}
	/** Add an real variable to the list of variables belonging to an automaton 
	 * with a given name. */
	public void addRealVar(String i, String name) {
		EList<String> temp = this.realVars.get(name);
		if (temp == null) { //if entry for this.automatonName does not exist yet			
			BasicEList<String> list = new BasicEList<String>();
			list.add(i);
			this.setRealVars(list, name);
		} else { //entry exists already
			temp.add(i);
			this.setRealVars(temp, name);
		}
	}
	/** Get the real variables belonging to the automaton given to the constructor. */
	public EList<String> getRealVars() {
		return this.realVars.get(this.automatonName);
	}
	/** Get the real variables belonging to an automaton with a given name. */
	public EList<String> getRealVars(String name) {
		return this.realVars.get(name);
	}
	/** Get all real variables. */
	public EMap<String, EList<String>> getAllRealVars() {
		return this.realVars;
	}


	public String toString() {
		
		String result = CodegenUtils.MathSAT_LINE_COMMENT + CodegenUtils.MathSAT_LINE_COMMENT + "Variables:\n";
		String name;
		
		if (!this.integerVars.isEmpty()) {
			for (Map.Entry<String, EList<String>> me: this.integerVars) {
				name = me.getKey();
				if (!me.getValue().isEmpty()) {
					result = result + "VAR ";
					for (String var: me.getValue()) {
						result = result + var + ", "; 
					}
					result = result.substring(0, result.length()-2) + ": INTEGER "; //remove last comma
					result = result + CodegenUtils.MathSAT_LINE_COMMENT + "(" + name + ")\n";
				}
			}
		}

		if (!this.booleanVars.isEmpty()) {
			for (Map.Entry<String, EList<String>> me: this.booleanVars) {
				name = me.getKey();
				if (!me.getValue().isEmpty()) {
					result = result + "VAR ";
					for (String var: me.getValue()) {
						result = result + var + ", "; 
					}
					result = result.substring(0, result.length()-2) + ": BOOLEAN "; //remove last comma
					result = result + CodegenUtils.MathSAT_LINE_COMMENT + "(" + name + ")\n";
				}
			}
		}
		
		if (!this.realVars.isEmpty()) {
			for (Map.Entry<String, EList<String>> me: this.realVars) {
				name = me.getKey();
				if (!me.getValue().isEmpty()) {
					result = result + "VAR ";
					for (String var: me.getValue()) {
						result = result + var + ", "; 
					}
					result = result.substring(0, result.length()-2) + ": REAL "; //remove last comma
					result = result + CodegenUtils.MathSAT_LINE_COMMENT + "(" + name + ")\n";
				}
			}
		}	
		result = result + "\n\n";
		return result;
	}
	
	/** Merge a number of variable sections. */
	public static VariableSection merge(EList<VariableSection> vars) {
		
		VariableSection result = new VariableSection("");
		EMap<String, EList<String>> tempM;
		for (VariableSection v: vars) {
			tempM = v.getAllIntegerVars();
			for (Map.Entry<String, EList<String>> me: tempM) {
				result.setIntegerVars(me.getValue(), me.getKey());
			}
			tempM = v.getAllBooleanVars();
			for (Map.Entry<String, EList<String>> me: tempM) {
				result.setBooleanVars(me.getValue(), me.getKey());
			}
			tempM = v.getAllRealVars();
			for (Map.Entry<String, EList<String>> me: tempM) {
				result.setRealVars(me.getValue(), me.getKey());
			}
		}
		
		return result;
	}
}