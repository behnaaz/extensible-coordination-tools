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

import java.util.HashSet;
import java.util.Set;

import org.antlr.runtime.RecognitionException;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.clocks.ClockUtils;
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;
import org.ect.ea.extensions.clocks.parsers.TCAClocksParser;
import org.ect.ea.extensions.clocks.parsers.TCADataParser;
import org.ect.ea.extensions.constraints.CA;


//TODO Test:
// What happens if automaton has no clocks (does constructor give empty set or error?)

public class MathSATTemplate implements SMTTemplate {
	
	/** The automaton holding the contents of this template. */
	private final Automaton automaton;
	/** Location to check reachability for. */
	private final String reachState;
	/** The upper bound of data values. */
	private final int upperBound;
	/** The unfolding depth, i.e., the number of steps */
	private final int unfoldingDepth;
	/** Whether the automaton supports timed extensions or not. */
	private final boolean timed;
	/** Whether the data domain is finite or infinite. */
	private final boolean infinite;
	/** Port names */
	private final EList<String> portNames;
	/** Memory cell names */
	private final EList<String> memcellNames;
	/** Clock names */
	private final EList<String> clockNames;
	
	/* For naming in toString() */
	private final String initPre;
	private final String mutexPre;
	private final String transPre;
	private final String locPre;
	private final String reachPre;
	private final String execPre;
	
	/* For faster code generation/typing, and for spaces */
	private final String LAND = " " + ClockUtils.LAND + " ";
	private final String LOR = " " + ClockUtils.LOR + " ";
	private final String COM = CodegenUtils.MathSAT_LINE_COMMENT;
	
	public MathSATTemplate(Automaton auto, String state, int depth, int upB) {
		
		//initialise private fields of this template
		this.automaton = auto;
		this.reachState = state;
		this.upperBound = upB;
		this.unfoldingDepth = depth;
		if (upB == 0) {
			this.infinite = true;
		} else {
			this.infinite = false;
		}
		if (auto.getUsedExtensionIds().contains(org.ect.ea.extensions.clocks.AutomatonClocksProvider.EXTENSION_ID)) {
			this.timed = true;
		} else {
			this.timed = false;
		}
		this.portNames = CA.getPortNames(this.automaton).getValues();
		if (auto.getUsedExtensionIds().contains(org.ect.ea.extensions.statememory.StateMemoryExtensionProvider.EXTENSION_ID)) {
			this.memcellNames = removeDuplicateEntries(CA.getAllMemoryCells(this.automaton));
		} else {
			this.memcellNames = new BasicEList<String>();
		}
		if (this.timed) {
			this.clockNames = ClockUtils.getClocks(this.automaton);
		} else {
			this.clockNames = new BasicEList<String>();
		}
		
		/* For naming in toString() */
		this.initPre = this.automaton.getName() + ".INIT";
		this.mutexPre = this.automaton.getName() + ".MUTEX";
		this.transPre = this.automaton.getName() + ".TRANS";
		this.locPre = this.automaton.getName() + ".LOC";
		this.reachPre = this.automaton.getName() + ".REACH";
		this.execPre = this.automaton.getName() + ".EXEC";
	}

	/** Build the initial constraints. Implement abstract method from superclass
	 * to suit MathSAT input format. 
	 * @note Tested, OK. */
	public String buildInit() {
		
		//set up variables etc.		
		String result = "";
		State startState = null;
		for (State s: this.automaton.getStates()) {
			if (CA.isStartState(s)) {
				startState = s;
			}
		}
		if (startState.equals(null)) {throw new FormulaGenerationException("StartStateExtension not supported!");}

		
		//Formula generation
		
		//initial location
		result = startState.getName() + "_0";
		
		//other locations
		for (State s: this.automaton.getStates()) {
			if (s.getName() != startState.getName()) {
				result = result + LAND + ClockUtils.LNOT + s.getName() + "_0";
			}
		}
		
		//invariant
		if (this.timed) {
			String inv = ClockUtils.getInvariant(startState);
			if (!inv.equals("true") && !inv.equals("")) { //do not need to add 'true' or empty invariant
				try {
					inv = inv.replace("(", "").replace(")", "");
					TCAClocksParser parser = new TCAClocksParser(inv);
					inv = parser.shape_clock_constraint("0");
				} catch (RecognitionException re) {
					throw new FormulaGenerationException("Internal error " +
							"while handling clock constraint, check implementation!");
				}
				result = result + LAND + inv;
			}
		}
		
		
		//data constraints on ports:
		//(!p /\ (CodegenUtils.DATA_PREFIX p = CodegenUtils.NO_FLOW_NAME))
		for (String p: this.portNames) {
			result = result + LAND + ClockUtils.LNOT + p + 
				"_0" + LAND + "(" + CodegenUtils.DATA_PREFIX + p + 
				"_0 = " + CodegenUtils.NO_FLOW_NAME + ")"; 
		}
		
		//data constraints on memory cells, if supported:
		//(m = CodegenUtils.NO_FLOW_NAME)
		if (!this.memcellNames.isEmpty()) {
			for (String m: this.memcellNames) {
				result = result + LAND + "(" + m + "_0 = " + 
					CodegenUtils.NO_FLOW_NAME + ")"; 
			}
		}

		if (this.timed) {
			//global clock
			result = result + LAND + "(" + CodegenUtils.GLOBAL_CLOCK + "_0 = 0)";
			
			//other clocks
			for (String c: this.clockNames) {
				result = result + LAND + "(" + c + "_0 = 0)";
			}
		}
		
		//for finite data domains, add 
		//'(CodegenUtils.NO_FLOW_NAME = CodegenUtils.NO_FLOW_VALUE)'
		if (!this.infinite) {
			result = result + LAND + "(" + 
				CodegenUtils.NO_FLOW_NAME + " = " + 
				CodegenUtils.NO_FLOW_VALUE + ")";
		}
		
		return result;
	}

	/** 
	 * Generate the transition relation. That is, compose the transition relation
	 * formulas from the constituents of the automaton underlying this template.
	 * Implement abstract method from superclass to suit MathSAT input format.
	 * */
	public EList<String> buildTrans(boolean unfold, boolean product) {
			
		String form = "";
		EList<String> result = new BasicEList<String>();

		//generate transitions
		for (Transition t: this.automaton.getTransitions()) {
				form = form + LOR + generateTransition(t);
		}

		//for timed CA, generate delay transitions if product is true.
		if (this.timed && product) {
			for (State s: this.automaton.getStates()) {
				form = form + LOR + generateDelayTransition(s);
			}
		}
		
		form = form.substring(form.indexOf(LOR) + LOR.length());
		//form = form.replaceFirst(LOR, ""); for some reason, this does not work
		
		if (unfold) {
			return unfold(form, 0, this.unfoldingDepth-1);
		} else {
			result.add(form);
			return result;
		}
	}

	/** Build the location constraints. Implement abstract method from 
	 * superclass to suit MathSAT input format. 
	 * @note Tested, OK. */
	public EList<String> buildLocation(boolean unfold) {
		
		EList<String> result = new BasicEList<String>();
		EList<String> names = new BasicEList<String>();
		String temp = "", form = "";
		for (State s: this.automaton.getStates()) {
			names.add(s.getName());
		}
		
		//if only one state: nothing to do
		if (names.size() == 1) {
			result.add("true");
			return result;
		}
		
		//more than one state
		for (String s: names) {  //first loop, positive state
			
			temp = "(" + s + "_t+1";
			for (String ns : names) { //second loop, negated state
				if (!ns.equals(s)) {
					temp = temp + LAND + ClockUtils.LNOT + ns + "_t+1"; 
				}
			}
			temp = temp + ")";
			form = LOR + temp + form; 
		}
		//form = form.replaceFirst(" | ", " "); for some reason, this does not work
		form = form.substring(form.indexOf(LOR) + LOR.length());
		if (unfold) {
			return unfold(form, 0, this.unfoldingDepth-1);
		} 
		result.add(form);
		return result;
	}

	/** Build the mutex constraints. Implement abstract method from 
	 * superclass to suit MathSAT input format. 
	 * @note Tested, OK. */
	public EList<String> buildMutex(boolean unfold) {
		
		String form = "";
		EList<String> result = new BasicEList<String>();

		if (this.portNames.isEmpty()) {
			return result;
		}
		
		//consistency constraint
		for (String p: this.portNames) {
			form = form + LAND + 
				"(" + ClockUtils.LNOT + p + "_t+1" + 
				LOR + ClockUtils.LNOT + "(" + 
				CodegenUtils.DATA_PREFIX + p + "_t+1" + " = " + 
				CodegenUtils.NO_FLOW_NAME + "))" + //end of first conjunct 
				LAND + 
				"(" + p + "_t+1 " + 
				LOR + "(" + 
				CodegenUtils.DATA_PREFIX + p + "_t+1" + " = " + 
				CodegenUtils.NO_FLOW_NAME + ")) ";			
		}
		form = form.replaceFirst(LAND, "");

		//finite data domain constraint
		if (!this.infinite) {
			for (String p: this.portNames) {
				form = form + LAND + "(" + 
					CodegenUtils.DATA_PREFIX + p + "_t+1 " + ClockUtils.GEQ + 
					CodegenUtils.NO_FLOW_VALUE + ")" + LAND + "(" + 
					CodegenUtils.DATA_PREFIX + p + "_t+1" + " " + 
					ClockUtils.LEQ + " " + String.valueOf(this.upperBound) + ")";
			}
			for (String m: this.memcellNames) {
				form = form + LAND + "(" + m + "_t+1 " + ClockUtils.GEQ + 
					CodegenUtils.NO_FLOW_VALUE + ")" + LAND + "(" + m + "_t+1" + 
					" " + ClockUtils.LEQ + " " + String.valueOf(this.upperBound) + ")";
			}
		}

		if (unfold) {
			return unfold(form, 0, this.unfoldingDepth-1);
		} 
		result.add(form);
		return result;		
	}

	public String toString() {

		EList<String> locL = this.buildLocation(true);
		EList<String> mutexL = this.buildMutex(true);
		EList<String> transL = this.buildTrans(true, false);
		
		/* for intermediate results*/
		String vars = "", init = "", trans = "", reach = "", exec = "", form = "";
		
		String result = "";
		
		//generate variable sets
		vars = generateVariableSection();

		/* Compose the variable section of the result. */
		vars = COM + COM + " Variables:\n" + vars; 
		
		/* Compose the initial constraints. */
		init = init + "\n\n" + COM + COM + " Initial Constraints:\nDEFINE " + 
			initPre + " := " + buildInit();
		
		
		/* Compose the transition relation part. */
		trans = trans + "\n\n" + COM + COM + " Transition Relation:\n";
		if ((!(mutexL.size() == this.unfoldingDepth) && !(mutexL.size() == 0)) || 
				(!(locL.size() == this.unfoldingDepth) && !(locL.size() == 0)) || 
				(!(transL.size() == this.unfoldingDepth) && !(locL.size() == 0))) {
			throw new FormulaGenerationException("Unexpected internal error: lengths of lists do not match. Check implementation."); //should not happen
		}
		for (int i = 0; i < this.unfoldingDepth; i++) {
			trans = trans + "DEFINE " + transPre + (i+1) + " := " + transL.get(i) + "\n";
			trans = (locL.isEmpty()) ? trans : trans + "DEFINE " + locPre + (i+1) + " := " + locL.get(i) + "\n";
			trans = (mutexL.isEmpty()) ? trans : trans + "DEFINE " + mutexPre + (i+1) + " := " + mutexL.get(i) + "\n";
		}
		

		/* Compose the location reachability part, if it exists. */
		if (!(this.reachState.equals(""))) {
			reach = reach + "\n\n" + COM + COM + " Reachability check for location " + reachState + "\nDEFINE " + reachPre + " := ";
			for (int i = 0; i <= this.unfoldingDepth; i++) {
				reach = reach + reachState + "_" + i + LOR;
			}
			reach = reach.substring(0, reach.lastIndexOf(LOR));
		}
		
		/* Compose the 'executability' part, i.e. all formulas defined so far.*/
		exec = "\n\n" + COM + COM + "Executability; every solution to this " +
				"formula is one behaviour for the first " + this.unfoldingDepth + 
				" steps.\n" + "DEFINE " + execPre + " := " + initPre;
		for (int i = 1; i <= this.unfoldingDepth; i++) {
			exec = exec + LAND + transPre + i + LAND + locPre + i + LAND + mutexPre + i;
		}
		
		
		/* Compose the formula. */
		form = "\n\n" + COM + COM + "Formula to be checked. ";
		if (!(reach.equals(""))) {
			form = form + "Executability plus reachability\nFORMULA " + execPre + LAND + reachPre;
		} else {
			form = form + "Equals executability.\nFORMULA " + execPre;
		}
		
		/* Compose the result. */
		result = vars + init + trans + reach + exec + form;
		
		return result;
		
	}
	
	/** Generate the product representation for a list of templates. 
	 * Unfolding depth is taken from the Template on which the method is called. */
	public String toString(EList<SMTTemplate> templates) {

		String result = "";
		
		//cast to MathSATTemplate
		EList<MathSATTemplate> mtemps = new BasicEList<MathSATTemplate>();
		for (SMTTemplate temp: templates) {
			if (!(temp instanceof MathSATTemplate)) {
				throw new FormulaGenerationException("Cannot call MathSATTemplate.toString() on other than MathSATTemplate.");
			}
			mtemps.add((MathSATTemplate) temp);
		}
		
		if (!mtemps.contains(this)) {
			mtemps.add(this);
		}
		
		
		/* *** Compose the string. *** */
		
		/* variables*/
		result = COM + COM + " Variables:\n";
		for (MathSATTemplate temp: mtemps) {
			result = result + temp.generateVariableSection();
		}
		
		/* initial constraints */
		result = result + "\n\n" + COM + COM + " Initial Constraints:\n";
		for (MathSATTemplate temp: mtemps) {
			result = result + "DEFINE " + temp.initPre + " := " + temp.buildInit() + "\n";
		}
		
		/* transition relation */
		result = result + "\n\n" + COM + COM + " Transition Relation:\n";
		for (int i = 0; i < this.unfoldingDepth; i++) {
			for (MathSATTemplate temp: mtemps) {
				result = (temp.buildTrans(false, true).isEmpty()) ? result : result + "DEFINE " + temp.transPre + (i+1) + " := " + unfold(temp.buildTrans(false, true).get(0),i,i) + "\n";
				result = (temp.buildLocation(false).isEmpty()) ? result : result + "DEFINE " + temp.locPre + (i+1) + " := " + unfold(temp.buildLocation(false).get(0),i,i) + "\n";
				result = (temp.buildMutex(false).isEmpty()) ? result : result + "DEFINE " + temp.mutexPre + (i+1) + " := " + unfold(temp.buildMutex(false).get(0),i,i) + "\n";
			}
		}
		
		/* location reachability */
		String rState = "";
		for (MathSATTemplate temp: mtemps) { //collect the selected locations
			if (!temp.reachState.equals("")) {
				rState = rState + temp.reachState + "_t" + LAND;
			}
		}
		if (!rState.equals("")) {
			rState = rState.substring(0, rState.lastIndexOf(LAND));
			result = result + "\n\n" + COM + COM + " Reachability check for location(s) " + "[" + rState.replace("_t", "").replace(LAND, ",") +"]";
			rState = "(" + unfold(rState, 0, this.unfoldingDepth).toString().replace("[", "").replace("]", "").replace(",", ")" + LOR + "(") + ")";
			result = result + "\nDEFINE " + reachPre + " := " + rState;
		}
		
		/* Executability part */
		//TODO how to proceed exactly depends on abstraction refinement
		
		return result;
	}
	
	/** ************************************************** 
	 * Private Helper Methods
	 */
	
	/** Unfold a formula. This method replaces index <tt>t</tt> by increasing 
	 * integer numbers, starting at <tt>beginIndex</tt> and ending at <tt>endIndex</tt>,
	 * that means it iterates over the input formula <tt>(endIndex-startIndex)+1</tt> times.
	 * Note that if the formula contains index <tt>t+1</tt>, the maximal index 
	 * in the result is <tt>endIndex+1</tt>.
	 * For example, for input formula <tt>a_t /\ b_t+1</tt>, <tt>startIndex=0</tt> and <tt>endIndex=2</tt>, 
	 * the result is <tt>[a_0 /\ b_1, a_1 /\ b_2, a_2 /\ b_3]</tt>. If the 
	 * formula does not contain any symbol with index <tt>t</tt> or <tt>t+1</tt>, 
	 * the method returns a singleton list with the original formula.
	 * @param f Formula to be unfolded
	 * @param startIndex Start index for unfolding, that means the first (minimal)
	 * index by which <tt>t</tt> will be replaced. 
	 * @param endIndex End index for unfolding, that means the last (maximal)
	 * index by which <tt>t</tt> will be replaced 
	 * @return An EList containing the (stratified) unfolded version of the 
	 * formula <tt>f</tt>. 
	 * @precondition Variables in the formula may only contain indices 't' 
	 * and 't+1', otherwise, the method will not work correctly. */
    private static EList<String> unfold(final String f, int beginIndex, int endIndex) {
				
		EList<String> result = new BasicEList<String>();
		String temp = f;
		if (temp.trim().equals("")) {
			return result; 
		}
		if (!f.contains("_t") && !f.contains("_t+1")) {
			result.add(f);
			return result;
		}

		for (int i = 0; i <= endIndex; i++) {
			result.add(temp.replace("_t+1", "_" + String.valueOf(i+1)).replace("_t", "_" + String.valueOf(i)));
		}
		
		return result; 
	}

    /** Remove duplicate entries from a list. No guarantee that the sequential 
     * order remains the same. */
    private static EList<String> removeDuplicateEntries(EList<String> orig) {
    	Set<String> temp = new HashSet<String>(orig);
    	EList<String> result = new BasicEList<String>(temp);
    	return result;
    }
    
	/** Generate a delay transition for a particular state. */
	private String generateDelayTransition(State s) {
		String result = "", temp;
		TCAClocksParser cparser;
		
		//source location
		result = s.getName() + "_t";
		
		//ports
		for (String p: this.portNames) {
			result = result + LAND + ClockUtils.LNOT + p + "+t+1";
		}
		
		//memory cells
		for (String m: this.memcellNames) {
			result = result + LAND + "(" + m + "_t+1 = " + m + "_t) ";
		}
		
		//clocks
		for (String c: this.clockNames) {
			result = result + LAND + "(" + c + "_t+1 = " + c + "_t)";
		}
		result = result + LAND + "(" + CodegenUtils.GLOBAL_CLOCK + "_t <= " + 
			CodegenUtils.GLOBAL_CLOCK + "_t+1 )";
		
		//inter-step invariant
		if (this.timed) {
			try {
				temp = ClockUtils.getInvariant(s).replace("(", "").replace(")", "");
				cparser = new TCAClocksParser(temp);
				temp = cparser.shape_clock_constraint_interstep("t");
				if ((temp != "") & (!temp.equals("true"))) {
					result = result + LAND + "(" + temp + ")";
				}
			} catch (RecognitionException re) {
				throw new FormulaGenerationException("Internal error while handling " +
					"clock constraint, check implementation!");
			}
		}
		
		//target location
		result = result + LAND + s.getName() + "_t+1";
		
		//normal invariant
		if (this.timed) {
			try {
				temp = ClockUtils.getInvariant(s).replace("(", "").replace(")", "");
				cparser = new TCAClocksParser(temp);
				temp = cparser.shape_clock_constraint("t+1");
				if ((temp != "") & (!temp.equals("true"))) {
					result = result + LAND + "(" + temp + ")";
				}
			} catch (RecognitionException re) {
				throw new FormulaGenerationException("Internal error while handling " +
					"clock constraint, check implementation!");
			}
		}
		return "(" + result + ")";
	}
    
    /**
     * Generate a transition formula. 
     * @param t The transition to generate the formula for. */
	private String generateTransition(final Transition t) {
		
		boolean timedTrans = ClockUtils.isTimed(t);
		String result = "";
		String temp = "";
		EList<String> list = new BasicEList<String>();
		TCADataParser dparser;
		TCAClocksParser cparser;
		
		//source location
		result = t.getSource().getName() + "_t"; 
		
		//inter-step invariant of source location
		if (timedTrans) {
			try {
				temp = ClockUtils.getInvariant(t.getSource()).replace("(", "").replace(")", "");
				cparser = new TCAClocksParser(temp);
				temp = cparser.shape_clock_constraint_interstep("t");
				if ((temp != "") & (!temp.equals("true"))) {
					result = result + LAND + "(" + temp + ")";
				}
			} catch (RecognitionException re) {
				throw new FormulaGenerationException("Internal error while handling " +
					"clock constraint, check implementation!");
			}
		}
		
		//ports
		for (String p: this.portNames) {
			if (CA.getPortNames(t).getValues().contains(p)) { //active port
				result = result + LAND + p + "_t+1";
			} else { //inactive port
				result = result + LAND + ClockUtils.LNOT + p + "_t+1";
			}
		}
		
		//memory cells not used by target location
		if (!this.memcellNames.isEmpty()) {
			list.addAll(this.memcellNames);
			list.removeAll(CA.getMemoryCells(t.getTarget()).getValues());		
			for (String m: list) {
				result = result + LAND + "(" + m + "_t+1 = " + CodegenUtils.NO_FLOW_NAME + ")";
			}
		}		
		
		//data constraint
		try {
			//get the data constraint
			temp = t.findExtension(ClockUtils.DATA_CONSTRAINT_ID).toString();
			if (!temp.equals("true")) {
				dparser = new TCADataParser(temp, true);
				temp = dparser.addIndexRemoveMemcellPrefix("t", "t+1");
				if ((temp != "") & (!temp.equals("true"))) {
					result = result + LAND + "(" + temp + ")";
				}
			}
		} catch (RecognitionException re) {
			throw new FormulaGenerationException("Internal error while " +
					"handling data constraint, check implementation!");
		}
		
		if (timedTrans) {
		
			//inter-step clock guard
			try {
				temp = ClockUtils.getGuard(t).replace("(", "").replace(")", "");
				cparser = new TCAClocksParser(temp);
				temp = cparser.shape_clock_constraint_interstep("t");
				if ((temp != "") & (!temp.equals("true"))) {
					result = result + LAND + temp;
				}
			} catch (RecognitionException re) {
				throw new FormulaGenerationException("Internal error while " +
						"handling clock constraint, check implementation!");
			}
			
			//global clock
			if (CA.getPortNames(t).getValues().isEmpty()) { //invisible transition
				result = result + LAND + "(" + 
					CodegenUtils.GLOBAL_CLOCK + "_t < " + 
					CodegenUtils.GLOBAL_CLOCK + "_t+1 )";
			} else { //visible transition
				result = result + LAND + "(" + 
					CodegenUtils.GLOBAL_CLOCK + "_t <= " + 
					CodegenUtils.GLOBAL_CLOCK + "_t+1 )";
			}
			
			//clock update
			for (String s: this.clockNames) {
				String update = ClockUtils.getUpdate(s,t);
				if (update.equals("")) update = s;   //clock not handled by transition
				result = result + LAND + "(" + s + "_t+1 = ";
				try {
					new Integer(update);
					result = result + update + ") "; //getting here -> parseable integer
				} catch (NumberFormatException nfe) {
					result = result + update + "_t) "; //getting here -> not a parseable integer
				}
			}
		}
		
		//target location
		result = result + LAND + t.getTarget().getName() + "_t+1";
		
		//invariant of target location
		if (timedTrans) {
			try {
				temp = ClockUtils.getInvariant(t.getTarget()).replace("(", "").replace(")", "");
				cparser = new TCAClocksParser(temp);
				temp = cparser.shape_clock_constraint("t+1");
				if ((temp != "") & (!temp.equals("true"))) {
					result = result + LAND + temp;
				}
			} catch (RecognitionException re) {
				throw new FormulaGenerationException("Internal error while handling " +
						"clock constraint, check implementation!");
			}
		}
		
		return "(" + result + ")";		
	}
	
	/** Generate the variable section for this template. */
	private String generateVariableSection() {
		
		EList<String> bool = new BasicEList<String>();
		EList<String> integ = new BasicEList<String>();
		EList<String> ratio = new BasicEList<String>();
		
		String result = "";
		
		integ.add(CodegenUtils.NO_FLOW_NAME);
		for (int i = 0; i <= this.unfoldingDepth; i++) {
			ratio.add(CodegenUtils.GLOBAL_CLOCK + "_" + i);
			for (String c: this.clockNames) {
				ratio.add(c + "_" + i);
			}
			for (State s: this.automaton.getStates()) {
				bool.add(s.getName() + "_" + i);
			}
			for (String p: this.portNames) {
				bool.add(p + "_" + i);
				integ.add(CodegenUtils.DATA_PREFIX + p + "_" + i);
			}
			for (String m: this.memcellNames) {
				integ.add(m + "_" + i);
			}
		}
		 
		if (!bool.isEmpty()) {
			result = result + "VAR ";
			for (String b:bool) {
				result = result + b + ", ";
			}
			result = result.substring(0, result.length()-2) + ": BOOLEAN " + COM + "(" + this.automaton.getName() + ")\n";
		}
		if (!integ.isEmpty()) {
			result = result + "VAR ";
			for (String i: integ) {
				result = result + i + ", ";
			}
			result = result.substring(0, result.length()-2) + ": INTEGER " + COM + "(" + this.automaton.getName() + ")\n";
		}
		if (!ratio.isEmpty()) {
			result = result + "VAR ";
			for (String r: ratio) {
				result = result + r + ", ";
			}
			result = result.substring(0, result.length()-2) + ": REAL " + COM + "(" + this.automaton.getName() + ")\n";
		}
		
		return result;
	}
	
}
