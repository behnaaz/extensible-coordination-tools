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
package org.ect.ea.extensions.portnames.providers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.automata.AutomataProduct;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.SilentTransitions;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInformation;
import org.ect.ea.extensions.portnames.IntentionalPortNames;
import org.ect.ea.extensions.portnames.actions.QIARefinement;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.ea.util.ProductCache;

public class IEAProduct extends AutomataProduct{
	@Override
	public Automaton computeProduct(Automaton a1, Automaton a2, IProgressMonitor monitor) {
		monitor.beginTask("IEA product", 1);
		
		// Make copies and add silent transitions.
		a1 = (Automaton) EcoreUtil.copy(a1);
		a2 = (Automaton) EcoreUtil.copy(a2);
		
		SilentTransitions.add(a1);
		SilentTransitions.add(a2);
		
		// Remember the original states.
		ProductCache<State> productStates = new ProductCache<State>();
		
		// Merge automata extensions.
		Automaton product = mergeAutomata(a1, a2);
		if (product==null) return null;
		
		// Name of the product automaton.
		String name1 = a1.getName()!=null ? a1.getName() : a1.getId(); 
		String name2 = a2.getName()!=null ? a2.getName() : a2.getId();
		product.setName(name1 + " x " + name2);
		
		// Extension ids.
		product.getUsedExtensionIds().addAll( a1.getUsedExtensionIds() );
		product.getUsedExtensionIds().addAll( a2.getUsedExtensionIds() );
		
		// Find initial states
		List<State> initials1 = new Vector<State>();
		List<State> initials2 = new Vector<State>();
		Stack<State> reachable1 = new Stack<State>();
		Stack<State> reachable2 = new Stack<State>();
		boolean initialExists = false;
		for(State s1 : a1.getStates())
		{
			BooleanExtension start1 = new BooleanExtension();
			start1 = (BooleanExtension) s1.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			if(start1!=null && start1.getValue()==true){
				reachable1.push(s1); 
				initials1.add(s1);
			}
		}
		for(State s2 : a2.getStates()){
			BooleanExtension start2 = new BooleanExtension();
			start2 = (BooleanExtension) s2.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			if(start2!=null && start2.getValue()==true){
				reachable2.push(s2);
				initials2.add(s2);
			}
		}
		if(!initials1.isEmpty() && !initials2.isEmpty()) initialExists = true;
		List<Transition> transitions1 = new ArrayList<Transition>();
		List<Transition> transitions2 = new ArrayList<Transition>();
		State source = new State();
		State state1 = new State();
		State state2 = new State();
		if(!initialExists){
			EList<Transition> temp1 = a1.getTransitions();
			EList<Transition> temp2 = a2.getTransitions();
			transitions1.addAll(temp1);
			transitions2.addAll(temp2);
			
			state1 = a1.getStates().get(0);
			state2 = a2.getStates().get(0);
			source = productStates.getProduct(state1, state2);
			if(source==null)	source = mergeStates(state1, state2);
			if(source==null){
				System.err.println("Error on merging states");
			}
			product.getStates().add(source);
			productStates.addProduct(state1, state2, source);
			
		}
		else{
			EList<Transition> temp1 = reachable1.pop().getOutgoing();
			
			transitions1.addAll(temp1);
			while(!reachable1.isEmpty()){
				EList<Transition> Ioutgoing1 = reachable1.pop().getOutgoing();  
				transitions1.addAll(Ioutgoing1);
			}
			EList<Transition> temp2 = reachable2.pop().getOutgoing();
			transitions2.addAll(temp2);
			while(!reachable2.isEmpty()){
				EList<Transition> Ioutgoing2 = reachable2.pop().getOutgoing();
				transitions2.addAll(Ioutgoing2);
			}
			for(State a: initials1){
				state1 = a;
				for(State b: initials2){
					state2 = b;
					source = productStates.getProduct(state1, state2);
					if(source==null)	source = mergeStates(state1, state2);
					if(source==null){
						System.err.println("Error on merging states");
					}
										
					product.getStates().add(source);
					productStates.addProduct(state1, state2, source);
				}
			}
		}
		
		// Product transitions.
		boolean done = false;
		Set<String> Aports1 = PortNamesProductConditions.getPortNames(a1);
		Set<String> Aports2 = PortNamesProductConditions.getPortNames(a2);
		Set<String> newMixed = new HashSet<String>(Aports1);
		newMixed.retainAll(Aports2);
		
		List<State> visited1 = new Vector<State>();
		List<State> visited2 = new Vector<State>();
				
		while(!done){
			for(Transition t1 : transitions1){
				for(Transition t2 : transitions2){
					//Merge the extensions
					List<State> sources = new Vector<State>();
					List<State> targets = new Vector<State>();
					boolean interleaving = true;
					List<Transition> Temptransitions = mergeQIATransitions(t1, t2, newMixed, sources, targets, interleaving);
					List<Transition> transitions = removeRedundant(Temptransitions);
					int indexS = 0;
					int indexT = 0;
					//Set source and target states. Add the transitions to the automaton.
					for(IExtendible current : transitions){
						Transition transition = (Transition) current;
						
						//Add reachable states
						State productSource = new State();
						//Check the number of source states
						productSource=productStates.getProduct(sources.get(indexS++),sources.get(indexS++));
						IntentionalPortNames ports = (IntentionalPortNames) transition.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
												
						Transition newTransition2 = new Transition();
						newTransition2.setSource(productSource);

						// starting point!!!
						State target1 = targets.get(indexT++);
						State target2 = targets.get(indexT++);
						State target = productStates.getProduct(target1, target2);
						if(target==null){
							target = mergeStates(target1, target2);
							if(target==null)	continue;
							product.getStates().add(target);				
							productStates.addProduct(target1, target2, target);
						}
						newTransition2.setTarget(target);
						// Add reachable States
						//State productTarget = target;
						// Name of the product state.
						/*String stateName5 = target1.getName()!=null ? target1.getName() : target1.getId(); 
						String stateName6 = target2.getName()!=null ? target2.getName() : target2.getId();
						productTarget.setName(stateName5 + " x " + stateName6);*/
													
						IntentionalPortNames newPorts2 = new IntentionalPortNames();
						newPorts2.setOwner(newTransition2);
						newPorts2.getRequests().addAll(ports.getRequests());
						newPorts2.getFirings().addAll(ports.getFirings());
							
						DelayInformation newDI2 = new DelayInformation();
						newDI2.setOwner(newTransition2);
						List<DelayElement> newDIelements = ((DelayInformation) transition.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements();
						newDI2.getElements().addAll(newDIelements);
							
						if(!alreadyExists(newTransition2)){
							if(!alreadyVisited(target1, target2, initials1, initials2, visited1, visited2)){
								reachable1.push(target1);
								reachable2.push(target2);
								visited1.add(target1);
								visited2.add(target2);
							}
							product.getTransitions().add(newTransition2);
							//System.out.println("source / target / R / F : "+productSource+" / "+target + " / "+ports.getRequests() +"/ "+ports.getFirings());
							//System.out.println("delays: "+newDI2.getElements().toString());
						}
						else{
							newTransition2.setSource(null);
							newTransition2.setTarget(null);
						}
					}
				}
			}
			if(!reachable1.isEmpty() && !reachable2.isEmpty()){
				transitions1.clear();
				transitions2.clear();
				
				EList<Transition> temp1 = reachable1.pop().getOutgoing(); 
				for(Transition element1 : temp1){
					if(!((IntentionalPortNames)element1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID)).getMcollections() && element1.getTarget()!=null){
						transitions1.add(element1); 
					}
				}	
				
				EList<Transition> temp2 = reachable2.pop().getOutgoing();
				for(Transition element3 : temp2){
					if(!((IntentionalPortNames)element3.findExtension(IntensionalPortNamesProvider.EXTENSION_ID)).getMcollections() && element3.getTarget()!=null){
						transitions2.add(element3);
					}
				}
			}
			else if(reachable1.isEmpty() || reachable2.isEmpty())	done = true;
			else	done = true;
		}
		// Remove silent transitions again.
		SilentTransitions.remove(product);
		monitor.worked(1);
		monitor.done();
		
		//rename(product);
		System.out.println("State:" + product.getStates().size()+"; Transitions: "+product.getTransitions().size());
		return product;
	
	}
	
	public boolean alreadyExists(Transition t){
		boolean exists = false;
		List<Transition> outgoings = t.getSource().getOutgoing();
		for(Transition a: outgoings){
			if(!a.equals(t)){
				if(a.getTarget().equals(t.getTarget())){
					IntentionalPortNames tIp = (IntentionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					IntentionalPortNames aIp = (IntentionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					
					/*if(tIp.getRequests().equals(aIp.getRequests()) 
							&& tIp.getFirings().equals(aIp.getFirings())){*/
						if(aIp.getRequests().containsAll(tIp.getRequests()) && tIp.getRequests().containsAll(aIp.getRequests())){
							if(aIp.getFirings().containsAll(tIp.getFirings()) && tIp.getFirings().containsAll(aIp.getFirings())){
								exists = true;
								break;
								
							}
						
					}
				}
			}
		}
		
		return exists;
	}
	
	public List<Transition> mergeQIATransitions(Transition t1, Transition t2, Set<String> mixed, List<State> sources, List<State> targets, boolean interleaving){
		List<Transition> result = new Vector<Transition>();
		if(Isinterleaving(t1,t2,mixed)){
			if (PortNamesProductConditions.canIJoin(t1, t2)) {
				interleaving = true;
				Transition t = new Transition();
				IntentionalPortNames p1 = (IntentionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				IntentionalPortNames p2 = (IntentionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				IntentionalPortNames joined = IntentionalPortNames.join(p1, p2);
				joined.setOwner(t);
				
				sources.add(t1.getSource());
				sources.add(t2.getSource());
				targets.add(t1.getTarget());
				targets.add(t2.getTarget());
				
				DelayInformation di1 = (DelayInformation) t1.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayInformation di2 = (DelayInformation) t2.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayInformation DI = DelayInformation.join(di1, di2);
				DI.setOwner(t);
								
				result.add(t);
			}
			PortNamesProductConditions.getSources().clear();
			PortNamesProductConditions.getTargets().clear();
		}
		else{
			List<IntentionalPortNames> iports = PortNamesProductConditions.canMJoin(t1, t2);
			if(!iports.isEmpty()){
				sources.addAll(PortNamesProductConditions.getSources());
				targets.addAll(PortNamesProductConditions.getTargets());
				PortNamesProductConditions.getSources().clear();
				PortNamesProductConditions.getTargets().clear();
				interleaving = false;
				for(int i=0;i<iports.size()-1;i++){
					IntentionalPortNames n1 = iports.get(i++);
					IntentionalPortNames n2 = iports.get(i);
					
					IntentionalPortNames joined = IntentionalPortNames.join(n1, n2);
					Transition t = new Transition();
					joined.setOwner(t);
					
					
					DelayInformation di1 = (DelayInformation) n1.getOwner().findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayInformation di2 = (DelayInformation) n2.getOwner().findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayInformation DI = DelayInformation.join(di1, di2);
					DI.setOwner(t);
					
					result.add(t);
				}
			}
		}
		
		return result;
	}
	
	public boolean Isinterleaving(Transition t1, Transition t2, Set<String> mixed){
		boolean interleaving = false;
		
		if(t1.getSource().equals(t1.getTarget()) || t2.getSource().equals(t2.getTarget())){
			interleaving = true;
		}
		else{
			IntentionalPortNames iports1 = (IntentionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			IntentionalPortNames iports2 = (IntentionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			Set<String> requests1 = new HashSet<String>(iports1.getRequests());
			Set<String> firings1 = new HashSet<String>(iports1.getFirings());
			Set<String> requests2 = new HashSet<String>(iports2.getRequests());
			Set<String> firings2 = new HashSet<String>(iports2.getFirings());
			requests1.retainAll(mixed);
			firings1.retainAll(mixed);
			requests2.retainAll(mixed);
			firings2.retainAll(mixed);
			if(requests1.isEmpty() && requests2.isEmpty() && firings1.isEmpty() && firings2.isEmpty())	interleaving = true;
		}
		
		return interleaving;
	}
	
	public static boolean alreadyVisited(State a, State b, List<State> Init1, List<State> Init2, List<State> visited1, List<State> visited2){
    	boolean isInit = false;
    	boolean visited = false;
    	
    	if(Init1.contains(a) && Init2.contains(b))	isInit = true; 
    	
    	for(int i=0; i<visited1.size();i++){
    		if(visited1.get(i)==a && visited2.get(i)==b){
    			visited = true;
    			break;
    		}
    	}
    	
    	return (isInit || visited);
    }
	
	
	public static List<Transition> removeRedundant(List<Transition> merged){
		List<Transition> remove = new Vector<Transition>();
		
		for(int i=0;i<merged.size();i++){
			Transition check1 = merged.get(i);
			IntentionalPortNames ports1 = (IntentionalPortNames) check1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			for(int j=i+1;j<merged.size();j++){
				Transition check2 = merged.get(j);
				IntentionalPortNames ports2 = (IntentionalPortNames) check2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				if(ports1.getRequests().containsAll(ports2.getRequests()) && ports1.getFirings().containsAll(ports2.getFirings()) && 
				   ports2.getRequests().containsAll(ports1.getRequests()) && ports2.getFirings().containsAll(ports1.getFirings())){
					remove.add(check2);
				}
			}
		}
		merged.removeAll(remove);
		
		return merged;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductProvider#doPostProcessing(org.ect.ea.automata.Automaton, org.eclipse.core.runtime.IProgressMonitor)
	 */
	
	public void doPostProcessing(Automaton automaton, IProgressMonitor monitor) {
		// Refinement.
		QIARefinement refinement = new QIARefinement();
		refinement.compute(automaton, monitor);
		//Renaming states
		rename(automaton);
	}
	
	public void rename(Automaton automaton){
		int i = 0;
		List<State> states = automaton.getStates();
		for(State a : states){
			Integer name = new Integer(i);
			a.setName(name.toString());
			i++;
		}
	}
}


/*package org.ect.ea.extensions.portNames.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.ea.automata.AutomataProduct;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.SilentTransitions;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.portNames.DelayElement;
import org.ect.ea.extensions.portNames.DelayInformation;
import org.ect.ea.extensions.portNames.IntensionalPortNames;
import org.ect.ea.extensions.portNames.actions.QIARefinement;
import org.ect.ea.extensions.startStates.StartStateExtensionProvider;
import org.ect.ea.util.ProductCache;


public class IEAProduct extends AutomataProduct {


	@Override
	public Automaton computeProduct(Automaton a1, Automaton a2, IProgressMonitor monitor) {
		
		monitor.beginTask("IEA product", 1);
			
		//long start = System.currentTimeMillis();
		// Make copies and add silent transitions.
		a1 = (Automaton) EcoreUtil.copy(a1);
		a2 = (Automaton) EcoreUtil.copy(a2);
		
		SilentTransitions.add(a1);
		SilentTransitions.add(a2);
		
		// Remember the original states.
		ProductCache<State> productStates = new ProductCache<State>();
		
		// Merge automata extensions.
		Automaton product = mergeAutomata(a1, a2);
		if (product==null) return null;
		
		// Name of the product automaton.
		String name1 = a1.getName()!=null ? a1.getName() : a1.getId(); 
		String name2 = a2.getName()!=null ? a2.getName() : a2.getId();
		product.setName(name1 + " x " + name2);
		
		// Extension ids.
		product.getUsedExtensionIds().addAll( a1.getUsedExtensionIds() );
		product.getUsedExtensionIds().addAll( a2.getUsedExtensionIds() );
		
		// Product states.
		for (State state1 : a1.getStates()) {
			for (State state2 : a2.getStates()) {
				State productState = mergeStates(state1, state2);
				if (productState==null) continue;
				productStates.addProduct(state1, state2, productState);
			}
		}
		
		// Find initial states
		BooleanExtension start1 = new BooleanExtension();
		BooleanExtension start2 = new BooleanExtension();
		List<Transition> transitions1 = new ArrayList<Transition>();
		List<Transition> transitions2 = new ArrayList<Transition>();
		List<State> Initail1 = new Vector<State>();
		List<State> Initail2 = new Vector<State>();
		Stack<State> reachable1 = new Stack<State>();
		Stack<State> reachable2 = new Stack<State>();
		boolean InitailExists = false;
		boolean Init1 = false;
		boolean Init2 = false;
		for(State s1 : a1.getStates())
		{
			start1 = (BooleanExtension) s1.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			if(start1!=null && start1.getValue()==true){
				reachable1.push(s1); 
				Initail1.add(s1);
				Init1 = true; 
			}
		}
		for(State s2 : a2.getStates()){
			start2 = (BooleanExtension) s2.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			if(start2!=null && start2.getValue()==true){
				reachable2.push(s2);
				Initail2.add(s2);
				Init2 = true; 
			}
		}
		InitailExists = Init1 && Init2;
		State source = new State();
		if(!InitailExists){
			EList<Transition> temp1 = a1.getTransitions();
			EList<Transition> temp2 = a2.getTransitions();
			transitions1.addAll(temp1);
			transitions2.addAll(temp2);
		}
		else{
			EList<Transition> temp1 = reachable1.pop().getOutgoing();
			
			transitions1.addAll(temp1);
			while(!reachable1.isEmpty()){
				EList<Transition> Ioutgoing1 = reachable1.pop().getOutgoing();  
				transitions1.addAll(Ioutgoing1);
			}
			EList<Transition> temp2 = reachable2.pop().getOutgoing();
			transitions2.addAll(temp2);
			while(!reachable2.isEmpty()){
				EList<Transition> Ioutgoing2 = reachable2.pop().getOutgoing();
				transitions2.addAll(Ioutgoing2);
			}
		}
		
		// Product transitions.
		boolean done = false;
		List<String> newMixed = new Vector<String>();
		List<State> visited1 = new Vector<State>();
		List<State> visited2 = new Vector<State>();
		
		State state1 = new State();
		State state2 = new State();
		if(!InitailExists){
			state1 = a1.getStates().get(0);
			state2 = a2.getStates().get(0);
			source = productStates.getProduct(state1, state2);
			if(source==null)	source = mergeStates(state1, state2);
			if(source==null){
				System.err.println("Error on merging states");
			}
			
			product.getStates().add(source);
			productStates.addProduct(state1, state2, source);

		}
		else{
			for(State a: Initail1){
				state1 = a;
				for(State b: Initail2){
					state2 = b;
					source = productStates.getProduct(state1, state2);
					if(source==null)	source = mergeStates(state1, state2);
					if(source==null){
						System.err.println("Error on merging states");
					}
										
					product.getStates().add(source);
					productStates.addProduct(state1, state2, source);
				}
			}
		}
			
		while(!done){
			for(Transition t1 : transitions1){
				for(Transition t2 : transitions2){
					//Merge the extensions
					List<Transition> Temptransitions = mergeTransitions(t1, t2);
					List<Transition> transitions = removeRedundant(Temptransitions);					
															
					//Set source and target states. Add the transitions to the automaton.
					for(IExtendible current : transitions){
						Transition transition = (Transition) current;
					
						//Add reachable states
						State productSource = new State();
						productSource=productStates.getProduct(t1.getSource(), t2.getSource());
						IntensionalPortNames ports = (IntensionalPortNames) transition.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					
						//Distinguish new target states according to the existence of new mixed nodes
						boolean hasMixed1 = false;
						boolean hasMixed2 = false;
						boolean isCollectable = true;
						
						newMixed = PortNamesProductConditions.getNewMixed(t1, t2);
						
						if(!newMixed.isEmpty()){
							CollectingMixed c1 = new CollectingMixed(t1, newMixed);
							CollectingMixed c2 = new CollectingMixed(t2, newMixed);
							isCollectable = c1.getCollectable() && c2.getCollectable();
								
							if(isCollectable){
								List<Transition> Clist1 = c1.getCollected();
								List<Transition> Clist2 = c2.getCollected();
								// To check whether ports = ports1 U ports2
								List<String> Runion = new Vector<String>();
								List<String> Funion = new Vector<String>();
									
								for(Transition temp1 : Clist1){
									IntensionalPortNames ports1 = (IntensionalPortNames) temp1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
									List<String> Runion1 = new Vector<String>();
									List<String> Funion1 = new Vector<String>();
										
									for(String Rport1 : ports1.getRequests()){
										if(!Runion1.contains(Rport1)){
											Runion1.add(Rport1);
											Runion.add(Rport1);
										}
									}
									for(String Fport1 : ports1.getFirings()){
										if(!Funion1.contains(Fport1)){
											Funion1.add(Fport1);
											Funion.add(Fport1);
										}
									}
										
									if(ports.getRequests().containsAll(ports1.getRequests()) && ports.getFirings().containsAll(ports1.getFirings())){ 
										DelayInformation DI1 = (DelayInformation) temp1.findExtension(DelayInfoProvider.EXTENSION_ID);
										hasMixed1 = false;
										for(String a : ports1.getRequests()){
											if(newMixed.contains(a))	hasMixed1 = true;
										}
										for(Transition temp2 : Clist2){
											IntensionalPortNames ports2 = (IntensionalPortNames) temp2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
											List<String> Runion2 = new Vector<String>();
											List<String> Funion2 = new Vector<String>();
											
											for(String Rport2 : ports2.getRequests()){
												if(!Runion2.contains(Rport2)){
													Runion2.add(Rport2);
													Runion.add(Rport2);
												}
											}
											for(String Fport2 : ports2.getFirings()){
												if(!Funion2.contains(Fport2)){
													Funion2.add(Fport2);
													Funion.add(Fport2);
												}
											}
												
											hasMixed2 = false;					
											if(ports.getRequests().containsAll(ports2.getRequests()) && ports.getFirings().containsAll(ports2.getFirings()) &&
													Runion.containsAll(ports.getRequests()) && Funion.containsAll(ports.getFirings()) && 
													ports.getRequests().containsAll(Runion) && ports.getFirings().containsAll(Funion) &&
													PortNamesProductConditions.includesMixedNodes(temp1, temp2,t1,t2)){													
												DelayInformation DI2 = (DelayInformation) temp2.findExtension(DelayInfoProvider.EXTENSION_ID);
												List<DelayElement> DEfromA2 = (List<DelayElement>) EcoreUtil.copyAll(DI2.getElements());
												List<DelayElement> DEfromA1 = (List<DelayElement>) EcoreUtil.copyAll(DI1.getElements());
												for(String b : ports2.getRequests()){
													if(newMixed.contains(b))	hasMixed2 = true;
												}
											
												if(hasMixed1 && hasMixed2){
													//Make new transition to be added
													Transition newTransition1 = new Transition();
													IntensionalPortNames newPorts1 = new IntensionalPortNames();
													List<String> newRequests1 = ports.getRequests();
													List<String> newFirings1 = ports.getFirings();
													
													newTransition1.setSource(productSource);
													//==========================states product===================================================
													State target = productStates.getProduct(temp1.getTarget(), temp2.getTarget());
													if(target==null){
														// Merge the target states.
														target = mergeStates(temp1.getTarget(),temp2.getTarget());
														
														// An error occurred.
														if (target==null) continue;
														
														product.getStates().add(target);				
														productStates.addProduct(temp1.getTarget(), temp2.getTarget(), target);
													}
													newTransition1.setTarget(target);
													//newTransition1.setTarget(productStates.getProduct(temp1.getTarget(), temp2.getTarget()));
													//===========================================================================================
																										
													newPorts1.setOwner(newTransition1);
													newPorts1.setId(IntensionalPortNamesProvider.EXTENSION_ID);
													newPorts1.getRequests().addAll(newRequests1);
													newPorts1.getFirings().addAll(newFirings1);														
											
													if(!alreadyAdded(product, newTransition1)){
														// Add reachable States
														State productTarget = new State(); 
														productTarget =	newTransition1.getTarget();
														// Name of the product state.
														String stateName3 = temp1.getTarget().getName()!=null ? temp1.getTarget().getName() : temp1.getTarget().getId(); 
														String stateName4 = temp2.getTarget().getName()!=null ? temp2.getTarget().getName() : temp2.getTarget().getId();
														productTarget.setName(stateName3 + " x " + stateName4);
													
														//To check the correctness of each transition
														System.out.println("source / target / R / F : "+productSource+" / "+productTarget + " / "+ports.getRequests() +"/ "+ports.getFirings());
																												
														product.getStates().add(productSource);		
														product.getStates().add(productTarget);				
															
														if(InitailExists && !alreadyVisited(temp1.getTarget(), temp2.getTarget(), Initail1, Initail2, visited1, visited2)){
															reachable1.push(temp1.getTarget()); 
															reachable2.push(temp2.getTarget());
															visited1.add(temp1.getTarget());
															visited2.add(temp2.getTarget());
														}
															
														DelayInformation newDI1 = new DelayInformation();
														newDI1.setOwner(newTransition1);
														newDI1.setId(DelayInfoProvider.EXTENSION_ID);
														
														for(DelayElement tempDE1: DEfromA1){
															if(tempDE1.getDelay()>=0.0)	newDI1.getElements().add(tempDE1);
														}
														for(DelayElement tempDE2: DEfromA2){
															if(tempDE2.getDelay()>=0.0)	newDI1.getElements().add(tempDE2);
														}
														
														newDI1.getElements().addAll(DEfromA1);
														newDI1.getElements().addAll(DEfromA2);
																											
														product.getTransitions().add(newTransition1);
													}
													else{
														newTransition1.setSource(null);
														newTransition1.setTarget(null);
													}
												}
											}
											Runion.removeAll(Runion2);
											Funion.removeAll(Funion2);
											Runion2.clear();
											Funion2.clear();
										}
									}
									Runion1.clear();
									Funion1.clear();
									Runion.clear();
									Funion.clear();
								}	
							}
						}
						
						if(hasMixed1 && hasMixed2 && !isCollectable){ 
							transition.setSource(null);
							transition.setTarget(null);
						}
						
						if(!hasMixed1 && !hasMixed2){
							//Make a new transition to be added
							Transition newTransition2 = new Transition();
							IntensionalPortNames newPorts2 = new IntensionalPortNames();
							DelayInformation newDI2 = new DelayInformation(); 
							List<String> newRequests2 = ports.getRequests();
							List<String> newFirings2 = ports.getFirings();
							List<DelayElement> newDIelements = ((DelayInformation) transition.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements();
														
							newTransition2.setSource(productSource);
							//====================================states product========================================
							State target = productStates.getProduct(t1.getTarget(), t2.getTarget());
							if(target==null){
								target = mergeStates(t1.getTarget(), t2.getTarget());
								if(target==null)	continue;
								product.getStates().add(target);				
								productStates.addProduct(t1.getTarget(), t2.getTarget(), target);
							}
							newTransition2.setTarget(target);
							//newTransition2.setTarget(productStates.getProduct(t1.getTarget(), t2.getTarget()));
							//==========================================================================================
							newPorts2.setOwner(newTransition2);
							newPorts2.setId(IntensionalPortNamesProvider.EXTENSION_ID);
							newPorts2.getRequests().addAll(newRequests2);
							newPorts2.getFirings().addAll(newFirings2);
							newDI2.setOwner(newTransition2);
							newDI2.setId(DelayInfoProvider.EXTENSION_ID);
							newDI2.getElements().addAll(newDIelements);
							
							if(InitailExists && !alreadyVisited(t1.getTarget(), t2.getTarget(), Initail1, Initail2, visited1, visited2)){
								reachable1.push(t1.getTarget());
								reachable2.push(t2.getTarget());
								visited1.add(t1.getTarget());
								visited2.add(t2.getTarget());
							}
							
							// Add reachable States
							State productTarget = newTransition2.getTarget();
							// Name of the product state.
							String stateName5 = t1.getTarget().getName()!=null ? t1.getTarget().getName() : t1.getTarget().getId(); 
							String stateName6 = t2.getTarget().getName()!=null ? t2.getTarget().getName() : t2.getTarget().getId();
							productTarget.setName(stateName5 + " x " + stateName6);
							
							//To check the correctness of each transition
							System.out.println("source / target / R / F : "+productSource+" / "+productTarget + " / "+ports.getRequests() +"/ "+ports.getFirings());
							product.getStates().add(productSource);				
							product.getStates().add(productTarget);				
							product.getTransitions().add(newTransition2);							
						}					
					}
				}
			}
			if(InitailExists && !reachable1.isEmpty() && !reachable2.isEmpty()){
				transitions1.clear();
				transitions2.clear();
				
				EList<Transition> temp1 = reachable1.pop().getOutgoing(); 
				for(Transition element1 : temp1){
					if(!((IntensionalPortNames)element1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID)).getMcollections() && element1.getTarget()!=null){
						transitions1.add(element1); 
					}
				}	
				
				EList<Transition> temp2 = reachable2.pop().getOutgoing();
				for(Transition element3 : temp2){
					if(!((IntensionalPortNames)element3.findExtension(IntensionalPortNamesProvider.EXTENSION_ID)).getMcollections() && element3.getTarget()!=null){
						transitions2.add(element3);
					}
				}
			}
			else if(InitailExists && (reachable1.isEmpty() || reachable2.isEmpty()))	done = true;
			else	done = true;
		}
						
		// Remove silent transitions again.
		SilentTransitions.remove(product);
		//long end = System.currentTimeMillis();
		//System.out.println("\n"+"computing time | "+(end-start));
		
		monitor.worked(1);
		monitor.done();
		
		System.out.println("State:" + product.getStates().size()+"; Transitions: "+product.getTransitions().size());
		return product;
		
	}

	public static boolean alreadyAdded(Automaton automaton, Transition temp){
		boolean result = false;
		IntensionalPortNames pivot = (IntensionalPortNames) temp.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		for(Transition check : automaton.getTransitions()){
			IntensionalPortNames ports = (IntensionalPortNames) check.findExtension(IntensionalPortNamesProvider.EXTENSION_ID); 
			if(temp.getSource()==check.getSource() && temp.getTarget()==check.getTarget() && 
			   pivot.getRequests().containsAll(ports.getRequests()) && pivot.getFirings().containsAll(ports.getFirings()) &&
			   ports.getRequests().containsAll(pivot.getRequests()) && ports.getFirings().containsAll(pivot.getFirings())){
				result = true;
			}
		}
		
		return result;
	}

	public static List<Transition> removeRedundant(List<Transition> merged){
		List<Transition> remove = new Vector<Transition>();
		
		for(int i=0;i<merged.size();i++){
			Transition check1 = merged.get(i);
			IntensionalPortNames ports1 = (IntensionalPortNames) check1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			for(int j=i+1;j<merged.size();j++){
				Transition check2 = merged.get(j);
				IntensionalPortNames ports2 = (IntensionalPortNames) check2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				if(ports1.getRequests().containsAll(ports2.getRequests()) && ports1.getFirings().containsAll(ports2.getFirings()) && 
				   ports2.getRequests().containsAll(ports1.getRequests()) && ports2.getFirings().containsAll(ports1.getFirings())){
					remove.add(check2);
				}
			}
		}
		merged.removeAll(remove);
		
		return merged;
	}
		
	public static boolean hasCommon(List<String> list1, List<String> list2){
		boolean result = false;
		for(String a : list1){
			if(list2.contains(a))	result = true;
		}
		
		return result;
	}

    public static boolean alreadyVisited(State a, State b, List<State> Init1, List<State> Init2, List<State> visited1, List<State> visited2){
    	boolean isInit = false;
    	boolean visited = false;
    	
    	if(Init1.contains(a) && Init2.contains(b))	isInit = true; 
    	
    	for(int i=0; i<visited1.size();i++){
    		if(visited1.get(i)==a && visited2.get(i)==b){
    			visited = true;
    			break;
    		}
    	}
    	
    	return (isInit || visited);
    }
	
    
    
    
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductProvider#doPostProcessing(org.ect.ea.automata.Automaton, org.eclipse.core.runtime.IProgressMonitor)
	 
	public void doPostProcessing(Automaton automaton, IProgressMonitor monitor) {
		// Refinement.
		QIARefinement refinement = new QIARefinement();
		refinement.compute(automaton, monitor);
		//Renaming states
		rename(automaton);
	}
	
	public void rename(Automaton automaton){
		int i = 0;
		List<State> states = automaton.getStates();
		for(State a : states){
			Integer name = new Integer(i);
			a.setName(name.toString());
			i++;
		}
	}

}
*/