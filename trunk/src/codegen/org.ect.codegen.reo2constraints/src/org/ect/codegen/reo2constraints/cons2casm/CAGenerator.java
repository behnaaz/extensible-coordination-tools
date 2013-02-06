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
package org.ect.codegen.reo2constraints.cons2casm;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ect.codegen.reo2constraints.externaltools.Sat4JInterface.Solution;
import org.ect.codegen.reo2constraints.generator.Keyword;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.reo.Reo;

public class CAGenerator implements IOutputGenerator{

	private int solutionCounter = 0;
    private Automaton automaton = null;
	public CAGenerator() {
		createACA("default");
	}
	//TODO vase chie in?
	String content = "";
	private Set<String> allPorts = new HashSet<String>();
	
	private void createACA(String name) {
		// First we create an automaton.
		automaton = new Automaton(name);

		// We need to specify that it is a constraint automaton with memory.
		CA.convertToConstraintAutomatonWithMemory(automaton);
	}/*	//	System.out.println("Created automaton " + CA.prettyPrint(automaton));

		Automaton product = (new AutomataProduct()).computeProduct(automaton, automaton);
		System.out.println("Computed product " + CA.prettyPrint(product));	
		
	}
*/
	@Override
	public String reportSolutions(List<Solution> satSolutions, String[] dcsolutions, List<org.ect.codegen.reo2constraints.generator.State> states) {
		for (Solution sol : satSolutions){
			Transition t = makeATransition(dcsolutions, sol, states);
			if (t!=null){
				System.out.println(t+" "+t.getSource()+" "+t.getTarget()+" "+t.getExtensions().toString());
				solutionCounter++;
				automaton.getTransitions().add(t);
			}
		}
		String ports = refine(allPorts.toString().replaceAll("[\\{|\\}|\\[|\\]]", ""));
	    try{
	    	CA.setPortNames(automaton, ports);
	    }
	    catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("Created automaton " + CA.prettyPrint(automaton));
		System.out.println("# " + solutionCounter);
		return CA.prettyPrint(automaton);
	}

	private Transition makeATransition(String[] dcsolutions, Solution sol, List<org.ect.codegen.reo2constraints.generator.State> states) {
		Transition t = null;
		String src = Integer.toString(sol.getSourceIdx());
		String trg = Integer.toString(sol.getTargetIdx());
		State q1 = findState(automaton, src);
		if (q1==null){
			q1 = new State(src);
			automaton.getStates().add(q1);
		}
		State q2;
		if (src.compareTo(trg) != 0){
			q2 = findState(automaton, trg);
			if (q2==null){
				q2 = new State(trg);
				automaton.getStates().add(q2);
			}
		}
		else
		    q2 = q1;
				
		String ports = sol.getFlow();
		// Setting the portnames, memory cells and constraints.
		try {
				if (ports.trim().length()>0){
					for (String s:ports.split(","))					
						allPorts.add(s.trim().toLowerCase());
				}
				
				org.ect.codegen.reo2constraints.generator.State start = states.get(sol.getSourceIdx());
				String mem = start.getName();
				if (mem.length()>0)
					CA.setMemoryCells(q1, mem);
				org.ect.codegen.reo2constraints.generator.State end = states.get(sol.getSourceIdx());
				if (q1 != q2){
					mem = end.getName();
					if (mem.length()>0)
						CA.setMemoryCells(q1, mem);
				}
		        //CA.setMemoryCells(q1, "x,y");//states
		        String cons = extractConstraints(sol, dcsolutions);
		        if (cons.contains(Keyword.FALSE))
		        	return null;
		        if (false)
		        	CA.setStartState(q1, true);

		        if (findTransition(q1, q2, allPorts, cons)==null){
		        	t = new Transition(q1,q2);
		        	CA.setConstraint(t, cons);//????TODO
		        	String refports = refine(ports.toLowerCase());
		        	CA.setPortNames(t, refports);
		        }
		} 
		catch (ParseException e) {
			   System.err.println(e);
		}
		String newtrans = "";
		if (sol.getDataConstraintIdx()>0 && dcsolutions[sol.getDataConstraintIdx()].trim().compareTo(Keyword.FALSE)!=0){
			newtrans=src+" {"+sol.getFlow()+ "}, " + dcsolutions[sol.getDataConstraintIdx()]+" "+trg+"\n";
		}
		else{
			newtrans=src+" {"+sol.getFlow()+ "} "+trg + "\n";
		}
		if (!content.contains(newtrans)){
			content += newtrans;
			Reo.logInfo(content);
		}
		return t;
	}

	private String refine(String ports) {
		String res = "";
		for (String s : ports.split(",")){
			s = s.trim();
			if (!(s.startsWith(Keyword.NEXT) || s.endsWith(Keyword.GIVRSN))){
				if (res.length()>0)
					res += ",";
				res += s;
			}
		}	
		return res;
	}
	private Transition findTransition(State q1, State q2, Set<String> ports, String cons) {
		for (Transition t : automaton.getTransitions()){
			Constraint conds=null;
			if (t.getSource()==q1 && t.getTarget()==q2){
				conds = CA.getConstraint(t);
				TransitionPortNames auports = CA.getPortNames(t);
				if (conds.toString().compareTo(cons)==0 && equal(auports, ports)){
					return t;//TODO
				}
			}
		}
		return null;
	}
	private boolean equal(TransitionPortNames auports, Set<String> ports) {
		if (ports.size()==0 && auports.getValues().size()==0)
			return true;
		if (ports.size() != auports.getValues().size())
			return false;
		for (String p : ports){
			if (!(auports.getValues().contains(p.toLowerCase().trim()) && auports.getValues().contains(p.toUpperCase().trim())))
				return false;
		}
		return true;
	}

	private State findState(Automaton automaton, String src) {
		for (State st :automaton.getStates()){
			if (st.getName().compareTo(src)==0)
				return st;
		}
		return null;
	}
	private String extractConstraints(Solution sol, String[] dcsolutions) {
		String res = "";
		if (sol.getDataConstraintIdx()>-1){
			res += dcsolutions[sol.getDataConstraintIdx()]; 
		}
		if (res.length()>0)
			res=" & "+res;
		String equals = "";
		for (String s : sol.getFlow().split(",")){
			 String pre = "";
			 if (s.trim().length()>0){
				 if (pre.length()!=0){
					 equals+=((equals.length()>0)?"&":"")+pre+"="+s.trim();
				 }
				 pre = s.trim();
			 }
		}	
		if (equals.length()==0)
			equals="true";
		res = equals + res;
		//"A=B | (A=1 & B=$s.x)"
		if (res.length()==0)
			res = "true";
		return res;
	}
	@Override
	public String appendResult(Map<String, String> filterIdConsditions,
			List<Solution> satsolutions, String[] dcsolutions, List<org.ect.codegen.reo2constraints.generator.State> states) {
		String finres = "";
		if (filterIdConsditions.size() > 0) {
			//if (showResultsGrouped) {
			//}
		}
		finres += reportSolutions(satsolutions, dcsolutions, states);
		return finres;
	}

	@Override
	public void saveToFile(File outputFile) {
		System.out.println("Saving automata to '"+outputFile.getAbsolutePath()+"'...");
		try {
				Module module = CA.create(outputFile.getAbsolutePath());
		        module.getAutomata().add(automaton);
		//        module.getAutomata().add(product);		        
		        // Save the module.
		        CA.save(module);                
		}
		catch (IOException e) {
		        System.err.println(e);
		} 	
	}	
	
	@Override
	public Object getOutput() {
		return automaton;
	}
}
