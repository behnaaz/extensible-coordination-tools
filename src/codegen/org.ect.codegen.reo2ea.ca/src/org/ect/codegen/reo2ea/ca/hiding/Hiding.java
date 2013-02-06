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
package org.ect.codegen.reo2ea.ca.hiding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.ea.automata.*;
import org.ect.ea.extensions.*;
import org.ect.ea.extensions.constraints.*;
import org.ect.ea.extensions.constraints.operations.Normaliser;
import org.ect.ea.extensions.constraints.operations.Simplifier;
import org.ect.ea.extensions.constraints.operations.Substitutor;
import org.ect.ea.extensions.portnames.*;
import org.ect.ea.extensions.startstates.*;
import org.ect.ea.extensions.statememory.*;
import org.ect.ea.util.ca.CaHelper;

public class Hiding {
	private Set<String> names;
	private CaHelper helper;
	private Map<State,State> old2new = new HashMap<State,State>();
	
	public static Automaton getResult(Automaton ca, Collection<String> names) {
		if (names.isEmpty())
			return ca;
		
		return new Hiding(ca, names).helper.getAutomaton();
	}

	Hiding(Automaton ca, Collection<String> names) {
		this.names = new HashSet<String>(names);
		
		AutomatonPortNames ap = (AutomatonPortNames) ca.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		HashSet<String> ports = new HashSet<String>(ap.getValues());
		ports.removeAll(names);
		helper = new CaHelper(ca.getName(), ports);
		
		for (final State s : ca.getStates()) {
			ArrayList<Transition> trans = new ArrayList<Transition>();
			trans.add(new Transition() {//special dummy to avoid altering input CA
				@Override public State getTarget() { return s; }
			});
			depthFirst(trans, new HashSet<State>());
		}
	}
	
	private void depthFirst(List<Transition> path, Collection<State> visited) {
		Transition last = path.get(path.size()-1);
		State current = last.getTarget();

		TransitionPortNames tp = (TransitionPortNames) last.findExtension(TransitionPortNamesProvider.EXTENSION_ID);
		//Have we completed the path to be hidden?
		if (tp!=null && !names.containsAll(tp.getValues())) {	
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
		Conjunction con = new Conjunction();
		Set<String> substitutions = new HashSet<String>(names);
		Transition fst = path.get(0), lst = path.get(path.size()-1);
//		System.err.println();
		for (Transition t :path) {
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
		
		Disjunction dnf = new Normaliser().doSwitch(con);
		Constraint simplified = new Simplifier().doSwitch(dnf);
		Constraint substituted = new Substitutor(substitutions, true).doSwitch(simplified);
//		System.err.println(simplified +"\t"+substitutions+"\t"+substituted);

		
		StringListExtension tp = (StringListExtension) lst.findExtension(TransitionPortNamesProvider.EXTENSION_ID);
		Set<String> ports = new HashSet<String>(tp .getValues());
		ports.removeAll(names);
		
		return helper.createTransition(
				lookup(fst.getSource()), lookup(lst.getTarget()), ports, substituted);		
	}

	private Map<Parameter, Parameter> renameMemory(Constraint con, State state, ElementType et) {
		StateMemory sm = (StateMemory) state.findExtension(StateMemoryExtensionProvider.EXTENSION_ID);
		if (sm==null)
			return Collections.emptyMap();
		
		Map<Parameter, Parameter> replacements = new HashMap<Parameter, Parameter>();
		for (String s: sm.getValues()) {
			Parameter temp = new Element(s+"@"+state.getName(), ElementType.IDENTIFIER);
			replacements.put(new Element(s, et), temp);
		}

		return replacements;
	}
	
	private State lookup(State s) {
		if (old2new.containsKey(s))
			return old2new.get(s);
		//else
		BooleanExtension ss = (BooleanExtension) s.findExtension(StartStateExtensionProvider.EXTENSION_ID);
		State newS = helper.createState(	//CaHelper.createTransition() takes care of memory for us
				s.getName(), ss.getValue(), Collections.<String,String>emptyMap()); 
		old2new.put(s, newS);
		return newS;
	}
}
