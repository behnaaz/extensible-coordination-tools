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
import org.ect.ea.extensions.clocks.codegen.CodegenUtils;

/** Encapsulation of code generation result. Code generation returns an instance
 * of this class, which allows to access the different parts seperately. */

public class SMTMathSATFormulaGenResult {
	
	private AbstractionInfo abstractionInfo = new AbstractionInfo();
	//TODO private String info = "";  
	private VariableSection variableSection;
	private InitialConstraints initialConstraints;
	private TransitionRelation transitionRelation;
	private GenericFormulas property; //holds every formula which is not part of initial constraints or transition relation
	private TargetFormula target;
	private String com = CodegenUtils.MathSAT_LINE_COMMENT; //for quick typing
	private EList<String> formulaDefinitions = new BasicEList<String>();
	
	/* To generate general information */
	private String automatonName = "";
	private String upperBound = "";
	private String lowerBound = "";
	private String unfoldingDepth = "";
	
	public SMTMathSATFormulaGenResult() {}
	
	/** Get a string representation of <tt>this</tt>, i.e., a proper MathSAT file. */
	public String toString() {
		String result = "";
		result = result + this.abstractionInfo.toString();  //class AbstractionInfo takes care of adding information
		result = result + this.variableSection.toString();  //class VariableSection takes care of adding information
		result = result + this.initialConstraints.toString(); //class InitialConstraints takes care of adding information
		result = result + this.transitionRelation.toString(); //class TransitionRelation takes care of adding information
		result = result + com + com + " Property to be checked: \n" + this.property.toString() + "\n\n";
		result = result + this.target.toString();
		return result;
	}
	
	/** Merge a number of results. */
	public static SMTMathSATFormulaGenResult merge(EList<SMTMathSATFormulaGenResult> results) {

		AbstractionInfo abs = new AbstractionInfo(); //field of result
		VariableSection var = new VariableSection(""); //field of result
		InitialConstraints inits = new InitialConstraints(""); //field of result
		TransitionRelation trans = new TransitionRelation(""); //field of result
		GenericFormulas property = new GenericFormulas(); //field of result;
		TargetFormula trgt = new TargetFormula(); //field of result
		
		SMTMathSATFormulaGenResult result = new SMTMathSATFormulaGenResult(); //the result

		if (results.isEmpty()) {
			throw new UnsupportedOperationException("Set of files to be merged cannot be empty.");
		}
		if (results.size() == 1) {
			return results.get(0);
		}

		//compose the result
		
		//abstraction information
		EList<AbstractionInfo> a = new BasicEList<AbstractionInfo>();
		for (SMTMathSATFormulaGenResult res: results) {
			a.add(res.getAbstractionInfo());
		}
		abs = AbstractionInfo.merge(a);
		result.setAbstractionInfo(abs);
			
//		for (SMTMathSATFormulaGenResult res: results) {
//			for (String s: res.getAbstractionInfo().getStates()) {
//				abs.addState(s);
//			}
//			for (String p: res.getAbstractionInfo().getPorts()) {
//				abs.addPort(p);
//			}
//			for (String c: res.getAbstractionInfo().getClocks()) {
//				abs.addClock(c);
//			}
//			for (String m: res.getAbstractionInfo().getMemcells()) {
//				abs.addMemcell(m);
//			}
//		}
//		result.setAbstractionInfo(abs);
		
		//variable section
		EList<VariableSection> v = new BasicEList<VariableSection>();
		for (SMTMathSATFormulaGenResult res: results) {
			v.add(res.getVariableSection());
		}
		var = VariableSection.merge(v);
		result.setVariableSection(var);
		
		//initial constraints
		EList<InitialConstraints> i = new BasicEList<InitialConstraints>();
		for (SMTMathSATFormulaGenResult res: results) {
			i.add(res.getInitialConstraints());
		}
		inits = InitialConstraints.merge(i);
		result.setInitialConstraints(inits);
		
		//transition relation
		EList<TransitionRelation> tr = new BasicEList<TransitionRelation>();
		for (SMTMathSATFormulaGenResult res: results) {
			tr.add(res.getTransitionRelation());
		}
		trans = TransitionRelation.merge(tr);
		result.setTransitionRelation(trans);
		
		//property
		EList<GenericFormulas> p = new BasicEList<GenericFormulas>();
		for (SMTMathSATFormulaGenResult res: results) {
			p.add(res.getProperty());
		}
		property = GenericFormulas.merge(p);
		
		//target
		EList<TargetFormula> t = new BasicEList<TargetFormula>();
		for (SMTMathSATFormulaGenResult res: results) {
			t.add(res.getTarget());
		}
		Pair<TargetFormula,GenericFormulas> intermed = TargetFormula.merge(t);		
		p.clear(); //add 'new intermediate' formulas to property
		p.add(property);
		p.add(intermed.getSecond());
		property = GenericFormulas.merge(p);
		
		result.setProperty(property);
		
		trgt = intermed.getFirst();
		result.setTarget(trgt);
		
		return result;
	}
	
	/* Getter and Setter methods. */	
	/** Set the abstraction information of this. */
	public void setAbstractionInfo(AbstractionInfo absInfo) {
		this.setAbstractionInfo(absInfo, true);
	}
	/** Set the abstraction information of this if override is true. */
	public void setAbstractionInfo(AbstractionInfo absInfo, boolean override) {
		if (override) {
			abstractionInfo = absInfo;
		} 
	}
	public AbstractionInfo getAbstractionInfo() {
		return abstractionInfo;
	}
	
	public void setInitialConstraints(InitialConstraints init) {
		this.setInitialConstraints(init, true);
	}
	public void setInitialConstraints(InitialConstraints init, boolean override) {
		if (override) {
			this.initialConstraints = init;
		}
	}
	public InitialConstraints getInitialConstraints() {
		return this.initialConstraints;
	}
	
	public void setVariableSection(VariableSection varSec) {
		this.setVariableSection(varSec, true);
	}
	public void setVariableSection(VariableSection varSec, boolean override) {
		if (override) {
			this.variableSection = varSec;
		}
	}	
	public VariableSection getVariableSection() {
		return this.variableSection;
	}
	
	public void setAutomatonName(String name) {
		this.automatonName = name;
	}
	public String getAutomatonName() {
		return this.automatonName;
	}
	
	public void setUpperBound(String b) {
		this.setUpperBound(Integer.parseInt(b));
	}
	public void setUpperBound(int n) {
		this.upperBound = String.valueOf(n); 
	}
	public String getUpperBound() {
		return this.upperBound;
	}
	
	public void setLowerBound(String b) {
		this.setLowerBound(Integer.parseInt(b));
	}
	public void setLowerBound(int n) {
		this.lowerBound = String.valueOf(n); 
	}
	public String getLowerBound() {
		return this.lowerBound;
	}
	
	public void setUnfoldingDepth(String b) {
		this.setUnfoldingDepth(Integer.parseInt(b));
	}
	public void setUnfoldingDepth(int n) {
		this.unfoldingDepth = String.valueOf(n); 
	}
	public String getUnfoldingDepth() {
		return this.unfoldingDepth;
	}
	
	
	/** Transition relation. The transition relation is assumed to be unfolded
	 * already (this is a result container class). 
	 * @throws UnsupportedOperationException In case the list contains an 
	 * abstract transition, i.e., a transition which is not unfolded. */
	public void setTransitionRelation(TransitionRelation trans) {
		this.transitionRelation = trans;
	}
	public TransitionRelation getTransitionRelation() {
		return this.transitionRelation;
	}	
	
	public EList<String> getFormulaDefinitions() {
		if (this.formulaDefinitions.isEmpty()) { //initialise on first call
			this.formulaDefinitions.add(this.automatonName + "." + "INIT");
			this.formulaDefinitions.addAll(this.transitionRelation.getFormulaDefinitions());
		}
		return this.formulaDefinitions;
	}
	
	public void setTarget(TargetFormula t) {
		this.setTarget(t, true);
	}
	public void setTarget(TargetFormula t, boolean override) {
		if (override) {
			this.target = t;
		}
	}
	public TargetFormula getTarget() {
		return this.target;
	}
	
	public void setProperty(GenericFormulas p) {
		this.setProperty(p, true);
	}
	public void setProperty(GenericFormulas p, boolean override) {
		if (override) {
			this.property = p;
		} else {
			EList<StringTriple> contents = p.getContents();
			for (StringTriple triple: contents) {
				this.property.addFormula(triple);
			}
		}
	}
	public GenericFormulas getProperty() {
		return this.property;
	}
}
