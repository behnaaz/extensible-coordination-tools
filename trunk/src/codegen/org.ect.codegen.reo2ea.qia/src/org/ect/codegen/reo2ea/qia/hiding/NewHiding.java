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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.ect.codegen.reo2ea.qia.util.QIAHelper;
import org.ect.ea.EA;
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
import org.ect.ea.extensions.provider.BooleanExtensionItemProvider;
import org.ect.ea.extensions.startstates.StartStateAction;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;

public class NewHiding {
	private Set<String> names;
	private QIAHelper helper;
	private Map<State,State> old2new = new HashMap<State,State>();
	private Automaton copy = new Automaton();
	private List<State> simulating = new Vector<State>();
	private List<State> simulated = new Vector<State>();
	private List<State> removeStates = new Vector<State>();
	private List<Transition> removeTransitions = new Vector<Transition>(); 
	
	public static Automaton getResult(Automaton qia, Collection<String> names) {
		if (names.isEmpty())
			return qia;
		
		return new NewHiding(qia, names).helper.getAutomaton();
	}
	
	NewHiding(Automaton qia, Collection<String> names) {
		//copy = EA.copyAutomaton(qia);
		/*List<String> extensions = qia.getUsedExtensionIds();
		copy.getUsedExtensionIds().addAll(extensions);
		*/
		this.names = new HashSet<String>(names);
		
		AutomatonPortNames ap = (AutomatonPortNames) qia.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		HashSet<String> Aports = new HashSet<String>(ap.getValues());
		Aports.removeAll(names);
		helper = new QIAHelper(qia.getName(), Aports);
		
		for(final State s : qia.getStates()){
			List<Transition> outgoings = s.getOutgoing();
			for(Transition t:outgoings){
				IntensionalPortNames Iports = (IntensionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				Set<String> ports = new HashSet<String>();
				if(Iports.getRequests()!=null)	ports.addAll(Iports.getRequests());
				if(Iports.getFirings()!=null)	ports.addAll(Iports.getFirings());
				
				if(names.containsAll(ports))	unifyingStates(qia,s,t,true); // for reducible transitions
				else {//for modified transitions by deleting hidden ports
					ports.retainAll(names);
					if(!ports.isEmpty()){
						modifyingTransition(t);
					}
					else{
						IntensionalPortNames NIports = new IntensionalPortNames();
						NIports.getRequests().addAll(Iports.getRequests());
						NIports.getFirings().addAll(Iports.getFirings());
						DelayInformation NDI = new DelayInformation();
						DelayInformation DI = (DelayInformation) t.findExtension(DelayInfoProvider.EXTENSION_ID);
						for(DelayElement de: DI.getElements()){
							DelayElement nde = new DelayElement();
							nde.getInput().addAll(de.getInput());
							nde.getOutput().addAll(de.getOutput());
							nde.setDelay(de.getDelay());
							nde.getRewards().addAll(de.getRewards());
							NDI.getElements().add(nde);
						}
						helper.createTransition(lookup(t.getSource()), lookup(t.getTarget()), NIports, NDI);						
					}
				}
			}
		}
		for(Transition a: removeTransitions){
			helper.deleteTransition(a);
		}
		for(State b: removeStates){
			helper.deleteState(b.getName());
		}
/*		
		for (final State s : qia.getStates()) {
			ArrayList<Transition> trans = new ArrayList<Transition>();
			trans.add(new Transition() {//special dummy to avoid altering input QIA
				@Override public State getTarget() { return s; }
			});
			depthFirst(trans, new HashSet<State>());
		}
*/	}
	
	private void unifyingStates(Automaton qia, State s1, Transition t, boolean checkingSimulation){
		State s2 = t.getTarget();
		//result[0] simulates result[1].
		List<State> result = new Vector<State>();
		if(checkingSimulation){
			result = getSimulatingState(s1,s2,t);
		}
		else{
			if(simulating.contains(s1)){
				result = getSimulatingState(s1, simulating.get(simulated.indexOf(t.getTarget())), t);
				removeStates.add(t.getTarget());
				removeTransitions.add(t);
			}
			else if(simulated.contains(s1) && t.getTarget().equals(simulating.get(simulated.indexOf(s1)))){
				return;
			}
		}
					
		List<State> revisitStates = new Vector<State>();
		List<Transition> revisitTransitions = new Vector<Transition>();
		if(!removeTransitions.contains(t))	removeTransitions.add(t);
		
		List<Transition> incomings = result.get(1).getIncoming();
		for(Transition a: incomings){
			if(!a.equals(t)){
				IntensionalPortNames Iports = (IntensionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				IntensionalPortNames NIports = new IntensionalPortNames();
				Set<String> requests = new HashSet<String>(Iports.getRequests());
				Set<String> firings = new HashSet<String>(Iports.getFirings());
				requests.removeAll(names);
				firings.removeAll(names);
				if(!requests.isEmpty() || !firings.isEmpty()){
					NIports.getRequests().addAll(requests);
					NIports.getFirings().addAll(firings);
					
					DelayInformation DI = (DelayInformation) a.findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayInformation NDI = new DelayInformation();
					for(DelayElement b: DI.getElements()){
						DelayElement c = new DelayElement();
						Set<String> inputs = new HashSet<String>(b.getInput());
						Set<String> outputs = new HashSet<String>(b.getOutput());
						inputs.removeAll(names);
						outputs.removeAll(names);
						if(!inputs.isEmpty() || !outputs.isEmpty()){
							c.getInput().addAll(inputs);
							c.getOutput().addAll(outputs);
							c.setDelay(b.getDelay());
							c.getRewards().addAll(b.getRewards());
							NDI.getElements().add(c);
						}
					}
					State source = a.getSource();
					for(int i=0;i<simulated.size();i++){
						if(simulated.get(i).equals(a.getSource())){
							source = simulating.get(i);
							break;
						}
					}
					
					helper.createTransition(lookup(source), lookup(result.get(0)), NIports, NDI);
					if(!removeTransitions.contains(a))	removeTransitions.add(a);
				}
				else{
					revisitStates.add(a.getSource());
					revisitTransitions.add(a);
				}
				
			}
		}
		List<Transition> outgoings = result.get(1).getOutgoing();
		for(Transition b: outgoings){
			if(!b.equals(t)){
				IntensionalPortNames Iports = (IntensionalPortNames) b.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				IntensionalPortNames NIports = new IntensionalPortNames();
				Set<String> requests = new HashSet<String>(Iports.getRequests());
				Set<String> firings = new HashSet<String>(Iports.getFirings());
				requests.removeAll(names);
				firings.removeAll(names);
				if(!requests.isEmpty() || !firings.isEmpty()){
					NIports.getRequests().addAll(requests);
					NIports.getFirings().addAll(firings);

					DelayInformation DI = (DelayInformation) b.findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayInformation NDI = new DelayInformation();
					for(DelayElement d: DI.getElements()){
						DelayElement c = new DelayElement();
						Set<String> inputs = new HashSet<String>(d.getInput());
						Set<String> outputs = new HashSet<String>(d.getOutput());
						inputs.removeAll(names);
						outputs.removeAll(names);
						if(!inputs.isEmpty() && !outputs.isEmpty()){
							c.getInput().addAll(inputs);
							c.getOutput().addAll(outputs);
							c.setDelay(d.getDelay());
							c.getRewards().addAll(d.getRewards());
							NDI.getElements().add(c);
						}
					}
					State target = b.getTarget();
					for(int i=0;i<simulated.size();i++){
						if(simulated.get(i).equals(b.getTarget())){
							target = simulating.get(i);
							break;
						}
					}
					helper.createTransition(lookup(result.get(0)), lookup(target), NIports, NDI);
					if(!removeTransitions.contains(b))	removeTransitions.add(b);
				}
				else{
					revisitStates.add(s1);
					revisitTransitions.add(b);
				}
				
			}
		}
		removeStates.add(result.get(1));
		//traversing revisitStates and revisitTransitions
		if(!revisitStates.isEmpty()){
			for(int i=0;i<revisitStates.size();i++){
				unifyingStates(qia, revisitStates.get(i), revisitTransitions.get(i), false);
			}
		}
	}
		
	private List<State> getSimulatingState(State source, State target, Transition t){
		List<State> states = new Vector<State>();
		List<Transition> list1 = source.getOutgoing();
		List<Transition> considered1 = new Vector<Transition>();
		considered1.add(t);
		List<Transition> list2 = target.getOutgoing();
		List<Transition> considered2 = new Vector<Transition>();
		
		for(Transition a:list1){
			IntensionalPortNames Iports1 = (IntensionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			for(Transition b: list2){
				IntensionalPortNames Iports2 = (IntensionalPortNames) b.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				if(Iports1.getRequests().equals(Iports2.getRequests())){
					if(Iports1.getFirings().equals(Iports2.getFirings())){
						considered1.add(a);
						considered2.add(b);
						//break;
					}
				}
			}
		}
		
		if(considered1.containsAll(list1) && considered2.containsAll(list2)){
			List<Transition> list3 = source.getIncoming();
			List<Transition> considered3 = new Vector<Transition>();
			List<Transition> list4 = target.getIncoming();
			List<Transition> considered4 = new Vector<Transition>();
			considered4.add(t);
			//Check!!!
			for(Transition c: list3){
				IntensionalPortNames Iports3 = (IntensionalPortNames) c.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				for(Transition d: list4){
					IntensionalPortNames Iports4 = (IntensionalPortNames) d.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					if(Iports3.getRequests().equals(Iports4.getRequests())){
						if(Iports3.getFirings().equals(Iports4.getFirings())){
							considered3.add(c);
							considered4.add(d);
						}
					}
				}
			}
			if(!considered3.containsAll(list3) && considered4.containsAll(list4)){
				states.add(0, source);
				states.add(1, target);
			}
			else if(considered3.containsAll(list3) && !considered4.containsAll(list4)){
				states.add(0, target);
				states.add(1, source);
			}
			else{
				states.add(0, source);
				states.add(1, target);
			}
			
		}
		else if(considered1.containsAll(list1) && !considered2.containsAll(list2)){
			states.add(0, target);
			states.add(1, source);
		}
		else if (!considered1.containsAll(list1) && considered2.containsAll(list2)){
			states.add(0, source);
			states.add(1, target);
		}
		
		if(states.isEmpty()){
			IntensionalPortNames iports = (IntensionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			System.err.println("Impossible hiding with"+iports.toString());
		}
		simulating.add(states.get(0));
		simulated.add(states.get(1));
		return states;
	}
	
	//TODO reducing consecutive transitions according to hiding operation
	private void modifyingTransition(Transition t){
		IntensionalPortNames Iports = (IntensionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		List<String> Rports = new Vector<String>();
		List<String> Fports = new Vector<String>();
		if(Iports.getRequests()!=null)	Rports.addAll(Iports.getRequests()); 
		if(Iports.getFirings()!=null)	Fports.addAll(Iports.getFirings());
		Rports.removeAll(names);
		Fports.removeAll(names);
		IntensionalPortNames NIports = new IntensionalPortNames();
		NIports.getRequests().addAll(Rports);
		NIports.getFirings().addAll(Fports);
		
		DelayInformation NDI = new DelayInformation();
		DelayInformation DI = (DelayInformation) t.findExtension(DelayInfoProvider.EXTENSION_ID);
		//Hiding (mixed) nodes makes certain actions involved in the nodes immediate, i.e., these actions don't consume any time to conduct.
		//However, there are special nodes of replicators and mergers, actions of which are not simple suchlike pumping data items. 
		//For those nodes, hiding operation will preserve the rates of these nodes, if any. 
		
		
		for(DelayElement de: DI.getElements()){
			List<String> inputs = de.getInput();
			List<String> outputs = de.getOutput();
			
			inputs.removeAll(names);
			outputs.removeAll(names);
			if(!IsEmpty(inputs) || !IsEmpty(outputs)){
				DelayElement nde = new DelayElement();
				nde.getInput().addAll(inputs);
				nde.getOutput().addAll(outputs);
				nde.setDelay(de.getDelay());
				nde.getRewards().addAll(de.getRewards());
				
				NDI.getElements().add(nde);
			}
		}
		
		helper.createTransition(lookup(t.getSource()), lookup(t.getTarget()), NIports, NDI);
	}
	
	private boolean IsEmpty(List<String> set){
		boolean result = set.isEmpty();
		if(!set.isEmpty()){
			for(String a: set){
				if(a.equals(""))	result = true;	
			}
		}
		return result;
	}
	
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
