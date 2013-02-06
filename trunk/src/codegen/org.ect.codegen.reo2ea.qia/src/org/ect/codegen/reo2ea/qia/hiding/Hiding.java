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
package org.ect.codegen.reo2ea.qia.hiding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ect.codegen.reo2ea.qia.util.QIAHelper;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInfoProvider;
import org.ect.ea.extensions.portnames.DelayInformation;
import org.ect.ea.extensions.portnames.IntensionalPortNames;
import org.ect.ea.extensions.portnames.IntensionalPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;

public class Hiding {
	private Set<String> names;
	private QIAHelper helper;
	private Map<State,State> old2new = new HashMap<State,State>();
	
	public static Automaton getResult(Automaton qia, Collection<String> names) {
		if (names.isEmpty())
			return qia;
		
		return new Hiding(qia, names).helper.getAutomaton();
	}
	
	Hiding(Automaton qia, Collection<String> names) {
		this.names = new HashSet<String>(names);
		
		AutomatonPortNames ap = (AutomatonPortNames) qia.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		HashSet<String> ports = new HashSet<String>(ap.getValues());
		ports.removeAll(names);
		helper = new QIAHelper(qia.getName(), ports);
		
		for (final State s : qia.getStates()) {
			ArrayList<Transition> trans = new ArrayList<Transition>();
			trans.add(new Transition() {//special dummy to avoid altering input QIA
				@Override public State getTarget() { return s; }
			});
			depthFirst(trans, new HashSet<State>());
		}
	}

	private void depthFirst(List<Transition> path, Collection<State> visited) {
		Transition last = path.get(path.size()-1);
		State current = last.getTarget();

		IntensionalPortNames Iports = (IntensionalPortNames) last.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		Set<String> ports = new HashSet<String>();
		if(Iports!=null){
			if(Iports.getRequests()!=null && !Iports.getRequests().isEmpty())	ports.addAll(Iports.getRequests());
			if(Iports.getFirings()!=null && !Iports.getFirings().isEmpty())	ports.addAll(Iports.getFirings());
		}
		//Have we completed the path to be hidden?
		if (Iports!=null && !names.containsAll(ports)) {	
			//Yes, remove dummy and add a new transition
			path.remove(0);
			catenate(path);
//			System.err.println(t.getSource().getName()+" "+t.getTarget().getName()+" ");
			return;
		} 
		if (visited.contains(current)) 
			// We're trying to hide a cycle, Abort!
			throw new IllegalStateException("Attempting to hide a cyclic path");		
		
		visited.add(current);
		for (Transition t : current.getOutgoing()) {
			List<Transition> past = new ArrayList<Transition>(path);
			past.add(t);
			depthFirst(past, new HashSet<State>(visited));
		}
	}
	
	private Transition catenate(List<Transition> path) {
		/*Conjunction con = new Conjunction();
		Set<String> substitutions = new HashSet<String>(names);*/
		Transition fst = path.get(0), lst = path.get(path.size()-1);
//		System.err.println();
		/*for (Transition t :path) {
			Constraint c = (Constraint) EcoreUtil.copy(
					t.findExtension(ConstraintExtensionProvider.EXTENSION_ID));
			
			Map<Parameter, Parameter> replacements = new HashMap<Parameter, Parameter>();
			if (t!=fst)  //not the first so replace all source mem refs
				replacements.putAll(
					renameMemory(c, t.getSource(), ElementType.SOURCE_MEMORY));			
			if (t!=lst)  //not the last so replace all target mem refs
				replacements.putAll(
						renameMemory(c, t.getTarget(), ElementType.TARGET_MEMORY));
			
			if (!replacements.isEmpty()) {
				CARefactoring.renameElement(c, replacements);
				
				for (Parameter p: replacements.values())
					substitutions.add(p.getValue());				
			}
			
//			System.err.println(t.getSource().getName()+" "+t.getTarget().getName()+" "+c+" \\ "+replacements);
//			System.err.println(replacements);
			con.getMembers().add(c);
		}
		
		Disjunction dnf = new Normalisation().perform(con);
		Constraint simplified = new Simplification().perform(dnf);
		Constraint substituted = new Substitution(substitutions, true).perform(simplified);*/
//		System.err.println(simplified +"\t"+substitutions+"\t"+substituted);

		
		IntensionalPortNames Iports = (IntensionalPortNames) lst.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		Set<String> Rports = new HashSet<String>(Iports.getRequests());
		Set<String> Fports = new HashSet<String>(Iports.getFirings());
		Rports.removeAll(names);
		Fports.removeAll(names);
		IntensionalPortNames newIports = new IntensionalPortNames();
		newIports.getRequests().addAll(Rports);
		newIports.getFirings().addAll(Fports);
		
		DelayInformation DI = (DelayInformation) lst.findExtension(DelayInfoProvider.EXTENSION_ID);
		DelayInformation newDI = new DelayInformation();
		for(DelayElement DE: DI.getElements()){
			Set<String> inputs = new HashSet<String>(DE.getInput());
			Set<String> outputs = new HashSet<String>(DE.getOutput());
			inputs.removeAll(names);
			outputs.removeAll(names);
			if(!IsEmpty(inputs) || !IsEmpty(outputs)){
				DelayElement newDE = new DelayElement();
				newDE.getInput().addAll(inputs);
				newDE.getOutput().addAll(outputs);
				newDE.setDelay(DE.getDelay());
				if(!DE.getRewards().isEmpty())	newDE.getRewards().addAll(DE.getRewards());
				else	newDE.getRewards().add("0.0");
				newDI.getElements().add(newDE);
			}
		}
		
		return helper.createTransition(
				lookup(fst.getSource()), lookup(lst.getTarget()), newIports, newDI);//, substituted);		
	}
	
	private boolean IsEmpty(Set<String> set){
		boolean result = set.isEmpty();
		if(!set.isEmpty()){
			for(String a: set){
				if(a.equals(""))	result = true;	
			}
		}
		return result;
	}

	/*private Map<Parameter, Parameter> renameMemory(Constraint con, State state, ElementType et) {
		StateMemory sm = (StateMemory) state.findExtension(StateMemoryExtensionProvider.EXTENSION_ID);
		if (sm==null)
			return Collections.emptyMap();
		
		Map<Parameter, Parameter> replacements = new HashMap<Parameter, Parameter>();
		for (String s: sm.getValues()) {
			Parameter temp = new Element(s+"@"+state.getName(), ElementType.IDENTIFIER);
			replacements.put(new Element(s, et), temp);
		}

		return replacements;
	}*/

	/*public Transition createTransition(State from, State to, IntensionalPortNames Iports, DelayInformation DI){//, Constraint con ){
		Transition t = new Transition();
		t.setSource(from);
		t.setTarget(to);
		Iports.setOwner(t);
		DI.setOwner(t);
		//con.setOwner(t);
		
		return t;
	}
*/
	private State lookup(State s) {
		if (old2new.containsKey(s))
			return old2new.get(s);
		//else
		BooleanExtension ss = (BooleanExtension) s.findExtension(StartStateExtensionProvider.EXTENSION_ID);
		State newS = helper.createState(	//CaHelper.createTransition() takes care of memory for us
				s.getName(), ss.getValue());//, Collections.<String,String>emptyMap()); 
		old2new.put(s, newS);
		return newS;
	}
}
