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


/** Encapsulation of the initial constraints of a mathsat file. Every initial 
 * constraint formula belongs to a certain automaton, an object of this class can
 * hold initial constraints for more than one automaton. An automaton name is 
 * given in the constructor as the default name, but initial constraints can be
 * added with other automata names as well. */

class InitialConstraints {

	private String automatonName;
	
	/** The contents of these initial constraints. First parameter is the name of
	 * the automaton, second parameter is the formula representing the initial 
	 * constraints. */
	private EMap<String, String> contents = new BasicEMap<String, String>();
	
	/** Initialise new initial constraints, with a default automaton name. */
	public InitialConstraints(String name) {
		this.automatonName = name;
	}
	
	/** Set the initial constraints for the automaton given to the constructor. */
	public void set(String init) {
		this.contents.put(this.automatonName, init);
	}
	/** Set the initial constraints for an automaton with a given name. */
	public void set(String name, String init) {
		this.contents.put(name, init);
	}
	
	/** Get the initial constraints for the automaton given to the constructor.
	 * @return null If no such initial constraints exist. */
	public String get() {
		return this.contents.get(this.automatonName);
	}
	/** Get the initial constraints for an automaton with a given name. 
	 * @return null If no such automaton exists. */
	public String get(String name) {
		return this.contents.get(name);
	}
	
	/** Get the contents of this.*/
	public EMap<String,String> getContents() {
		return this.contents;
	}
	
	public String toString() {
		
		String result = CodegenUtils.MathSAT_LINE_COMMENT + CodegenUtils.MathSAT_LINE_COMMENT + " Initial constraints: \n";
		for (Map.Entry<String, String> me: this.contents) {
			result = result + "DEFINE " + me.getKey() + ".INIT : BOOLEAN := " + me.getValue() + "\n";
		}
		result = result + "\n\n";
		return result;
	}
	
	/** Merge a list of initial constraints. Throws an exception if two elements
	 * contain an entry with the same name. */
	public static InitialConstraints merge(EList<InitialConstraints> inits) {
		InitialConstraints result = new InitialConstraints("");
		
		//check that no double automaton names occur
		EList<String> checkL = new BasicEList<String>();
		for (InitialConstraints i: inits) {
			checkL.addAll(i.getContents().keySet());
		}
		checkL = new StringListExtension(checkL).getDuplicateEntries();
		if (!checkL.isEmpty()) {
			throw new UnsupportedOperationException("Cannot merge initial constraints for same automaton.");
		}
		
		//compose the result
		for (InitialConstraints i: inits) {
			for (Map.Entry<String, String> me: i.getContents()) { 
				result.set(me.getKey(), me.getValue());
			}
		}
		
		return result;
	}
}
