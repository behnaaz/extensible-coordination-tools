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
package org.ect.codegen.reo2ea.qia.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.ect.ea.automata.*;
import org.ect.ea.extensions.*;
import org.ect.ea.extensions.portnames.*;
import org.ect.ea.extensions.startstates.*;

public class QIAHelper {
	static final String 	// Extension IDs used for constraint automata (with memory)
		AUTOMATON_PORTNAMES = AutomatonPortNamesProvider.EXTENSION_ID,
		INTENSIONAL_PORTNAMES = IntensionalPortNamesProvider.EXTENSION_ID,
		START_STATES = StartStateExtensionProvider.EXTENSION_ID,
		DELAYINFORMATION = DelayInfoProvider.EXTENSION_ID;
		//CONSTRAINTS = ConstraintExtensionProvider.EXTENSION_ID;

	private Automaton automaton;
	private int sno;
	
	public QIAHelper(String name) {
		automaton = new Automaton(name);
		automaton.useExtensionType(START_STATES);
		//automaton.useExtensionType(CONSTRAINTS);
		automaton.useExtensionType(AUTOMATON_PORTNAMES);
		automaton.useExtensionType(INTENSIONAL_PORTNAMES);
		automaton.useExtensionType(DELAYINFORMATION);
		AutomatonPortNames ports = new AutomatonPortNames();
		automaton.updateExtension(ports);
	}

	public QIAHelper(String name, Collection<String> portNames) {
		this(name);
		AutomatonPortNames ports = (AutomatonPortNames) automaton.findExtension(AUTOMATON_PORTNAMES);
		if (portNames!=null)
			for (String p: portNames)
				ports.getValues().add(p);
	}
	
	public QIAHelper(String name, Collection<String> in, Collection<String>  out) {
		this(name);
		AutomatonPortNames ports = (AutomatonPortNames) automaton.findExtension(AUTOMATON_PORTNAMES);
		if (in!=null)
			for (String p: in)
				ports.getInPorts().add(p);
		
		if (out!=null)
			for (String p: out)
				ports.getOutPorts().add(p);
	}
	
	public State createState() {
		return createState("s"+sno++, false);
	}
	
	public State createState(String name, boolean isStart) {
		State s = new State(name==null? "s"+sno++ : name);
		automaton.getStates().add(s);

		BooleanExtension ss = new BooleanExtension(isStart);
		ss.setId(START_STATES);
		s.updateExtension(ss);
		
		return s;
	}

	public void deleteState(String name){
		State remove = null;
		List<Transition> removeT = new Vector<Transition>();
		for(State a :automaton.getStates()){
			if(a.getName().equals(name)){
				remove = a;
				removeT.addAll(a.getIncoming());
				removeT.addAll(a.getOutgoing());
				break;
			}
		}
		automaton.getStates().remove(remove);
		for(Transition b:removeT){
			automaton.getTransitions().remove(b);
			b.setSource(null);
			b.setTarget(null);
		}
	}
	
	public Transition createTransition(State from, State to, IntensionalPortNames Iports, DelayInformation DI){ //,Constraint con) {
		Transition t = new Transition(from, to);		
		t.updateExtension(Iports);
		t.updateExtension(DI);

		List<Transition> list = automaton.getTransitions();
		boolean exists = false;
		for(Transition a : list){
			if(	a.getSource().equals(from) && a.getTarget().equals(to)){
				IntensionalPortNames ports = (IntensionalPortNames) a.findExtension(INTENSIONAL_PORTNAMES);
				if(ports.getRequests().equals(Iports.getRequests())){
					exists = true;
					break;
				}
			}
					
		}
		
		if(!exists)	getAutomaton().getTransitions().add(t);
	/*	if (con!=null)
			addCon(t, con);
	*/	
		return t;
	}
	
	public Transition createTransition(State from, State to){//, Constraint con) {
		IntensionalPortNames Iports = new IntensionalPortNames();
		return createTransition(from, to, Iports);//, con);
	}
	
	public Transition createTransition(State from, State to, IntensionalPortNames Iports) {
		DelayInformation DI = new DelayInformation();
		return createTransition(from, to, Iports, DI);
	}
	
	public void deleteTransition(Transition t){
		Transition remove = null;
		String source = t.getSource().getName();
		String target = t.getTarget().getName();
		for(Transition a: automaton.getTransitions()){
			if(source.equals(a.getSource().getName())){
				if(target.equals(a.getTarget().getName())){
					remove = a;
				}
			}
		}
		
		automaton.getTransitions().remove(remove);
	}
	
	/*private Transition addCon(Transition t, Constraint con) {
		t.updateExtension(con);
		
		StringListExtension ports = (TransitionPortNames) t.findExtension(TRANSITION_PORTNAMES),
		fMem  = (StateMemory)t.getSource().findExtension(STATE_MEMORY),
		tMem  = (StateMemory)t.getTarget().findExtension(STATE_MEMORY);
		
		for (Element e: con.getAllElements()) { 
			String name = e.getValue();
			switch (e.getType()) {
			case IDENTIFIER:
				addUnique(name, ports);
				break;
			case SOURCE_MEMORY:
				addUnique(name, fMem);
				break;				
			case TARGET_MEMORY:
				addUnique(name, tMem);
				break;
			}		
		}
		return t;
	}*/
	
	/*private Transition addIPorts(Transition t, IntensionalPortNames ports) {
		IntensionalPortNames Iports = new IntensionalPortNames();
		t.updateExtension(Iports);
		if(Iports.getFirings()!=null && !Iports.getFirings().isEmpty()){
			for(String a:Iports.getFirings()){
				if(!a.equals("")){
					addUnique(a, Iports.getFirings());
				}
			}
		}
		
		return t;		
	}*/
	
	private boolean addUnique(String s, Collection<String> c) {
		if (!c.contains(s))
			return c.add(s);
		
		return false;
	}
	
	public void validatePorts() {
		AutomatonPortNames ap = (AutomatonPortNames) automaton.findExtension(AUTOMATON_PORTNAMES);
		for (Transition t : automaton.getTransitions()) {
			IntensionalPortNames ports = (IntensionalPortNames) t.findExtension(INTENSIONAL_PORTNAMES);
			
			for (String p : ports.getRequests()) {
				if (!(ap.getInPorts().contains(p) || ap.getOutPorts().contains(p)))
					addUnique(p, ap.getValues());
			}
		}
	}
	
	public Automaton getAutomaton() {
		return automaton;
	}
	
	
  	public static void main(String[] args) throws Exception {
		QIAHelper helper = new QIAHelper("foo");
		Map<String,String> m = new HashMap<String,String>();
		m.put("y", "0");
		State s0 = helper.createState("s0", true);
		State s1 = helper.createState();
		//Constraint parse = ConstraintParserHelper.parse("$t.x=A");
		helper.createTransition(s0, s1);//, parse);
		helper.createTransition(s1, s0);//, ConstraintParserHelper.parse("B=$s.x"));
		
		//System.out.println(new CatPrinter().visit(helper.getAutomaton()));
		
//		helper.serialize(System.err);
	}
}
