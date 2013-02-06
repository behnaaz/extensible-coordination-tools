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
package org.ect.codegen.ea2mc.providers;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.SilentTransitions;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInfoProvider;
import org.ect.ea.extensions.portnames.DelayInformation;
import org.ect.ea.util.ProductCache;

//Convert a delay-sequence to an automaton
public class Seq2Automaton {
	public static Automaton addNewParts(Automaton automaton, Transition transition, NewLinkedList list){
		
		// Create automaton and specify extensions to be used.
		Automaton costA = new Automaton();
		List<String> extensions = automaton.getUsedExtensionIds();
		costA.getUsedExtensionIds().addAll(extensions);
		
		// Automatically update the extensions.
		EA.monitorExtensions(costA, true);
		
		// Name the first state of an automaton 
		int sequentialState = -1;
		
		DElmNode<DelayElement> Current = new DElmNode<DelayElement>();
		if(list.getHead().getData()==null)	Current = list.getHead().next;
		else Current = list.getHead();
		
		//Sign visited nodes  
		DElmNode<DelayElement> tick = list.getHead();
		while(tick!=Current){
			tick.setVisited(true);
			DElmNode<DelayElement> tickHorizontal = tick.right;
			while(tickHorizontal!=null){
				tickHorizontal.setVisited(true);
				tickHorizontal = tickHorizontal.right;
			}
			tick = tick.next;
		}
		
		State preTarget = new State();
		Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
		boolean parallelList = false;
		boolean parallelNode = false;
		
		while(Current!=null){ 
			if(Current.getDummyTail()){
				break;
			}
			else{
				if(Current.right!=null){
					DElmNode<DelayElement> horizontal = Current;
					parallelList = false;
					parallelNode = false;
					while(horizontal!=null){
						if(horizontal.isHead()){//horizontal.getData()==null){
							horizontal.setVisited(true);
							store.push(horizontal.next);
							parallelList = true;
						}
						else{
							store.push(horizontal);
							parallelNode = true;
						}
						horizontal = horizontal.right;
					}
					while(!store.isEmpty()){
						if(parallelList){ //When a bunch of delay-sequences (their length >= 1) are combined in parallel,
							DElmNode<DelayElement> tempNode = store.pop();
							tempNode.setVisited(true);
							DelayElement temp = tempNode.getData();

							//Create automaton and specify extensions to be used.
							Automaton tempAutomaton1 = new Automaton();
							tempAutomaton1.getUsedExtensionIds().addAll(extensions);

							EA.monitorExtensions(tempAutomaton1, true);
							NewLinkedList sub1 = list.subSeq4(temp);
							costA = product(costA, addNewParts(tempAutomaton1, transition, sub1));
						}
						else if(parallelNode){ // When a bunch of single delays are combined in parallel, 
							while(!store.isEmpty()){
								DElmNode<DelayElement> tempElement2 = store.pop();
								tempElement2.setVisited(true);
								// Create automaton and specify extensions to be used.
								Automaton tempAutomaton2 = new Automaton();
								tempAutomaton2.getUsedExtensionIds().addAll(extensions);
								
								// Automatically update the extensions.
								EA.monitorExtensions(tempAutomaton2, true);
								
								// Make an automaton for each parallel delay
								Transition tempTransition2 = new Transition();
								State tempSource2 = new State();
								State tempTarget2 = new State();
								tempAutomaton2.getStates().add(tempSource2);
								tempAutomaton2.getStates().add(tempTarget2);
								tempAutomaton2.getTransitions().add(tempTransition2);
								tempTransition2.setAutomaton(tempAutomaton2);
								tempSource2.setAutomaton(tempAutomaton2);
								tempTarget2.setAutomaton(tempAutomaton2);
								tempTransition2.setSource(tempSource2);
								tempTransition2.setTarget(tempTarget2);
								DelayInformation tempDI = (DelayInformation)tempTransition2.findExtension(DelayInfoProvider.EXTENSION_ID);
								tempDI.getElements().add(tempElement2.getData());

								// Get an automaton of whole parallel delays  
								costA = product(costA, tempAutomaton2);
							}
						}
					}
					automaton = insert(automaton, costA);
					costA.getStates().clear();
					costA.getTransitions().clear();
					while(Current!=null && Current.getVisited())	Current = Current.next;
					if(parallelList){
						//parallelList = false;
						if(Current!=null && Current.getDummyTail()){
							DElmNode<DelayElement> settingDummy = Current;
							while(settingDummy!=null){
								if(settingDummy.getData()!=null)	settingDummy.setDummyTail(false);
								settingDummy = settingDummy.right;
							}
						}
					}
					//if(parallelNode)	parallelNode = false;
				}
				else if(Current.getData()!=null){
					boolean exists = false;
					if(!automaton.getStates().isEmpty()){
						for(State a : automaton.getStates()){
							if(a.getOutgoing().isEmpty()){
								preTarget =  a;
								exists = true;
							}
						}
					}
					else{
						Integer start = new Integer(sequentialState--);
						preTarget.setName(start.toString());
					}
					
					State source = preTarget;
					State target = new State();
					Transition t = new Transition();
					
					if(!exists)	source.setAutomaton(automaton);
					target.setAutomaton(automaton);
					// Set a name of target state
					Integer next = new Integer(sequentialState--);
					target.setName(next.toString());
					
					t.setAutomaton(automaton);
					t.setSource(source);
					t.setTarget(target);
					DelayInformation DI = (DelayInformation)t.findExtension(DelayInfoProvider.EXTENSION_ID);
					DI.getElements().add(Current.getData());
					automaton.getTransitions().add(t);
					Current.setVisited(true);
					Current = Current.next;
				}
			}
		}
		
		return automaton;
	}
	
	public static Automaton insert(Automaton origin, Automaton temp){
		State originStart = new State();
		State tempStart = new State();
		State tempEnd = new State();
		List<State> cache = new Vector<State>();
		List<State> visited = new Vector<State>();
		int j = 0;
		if(origin.getTransitions().isEmpty()){
			originStart.setAutomaton(origin);
		}
		else{
			for(State b : origin.getStates()){
				if(b.getOutgoing().isEmpty())	originStart = b;
				Integer originName = new Integer(j++);
				b.setName(originName.toString());
			}
		}
		
		for(State a : temp.getStates()){
			if(a.getIncoming().isEmpty())	tempStart = a;
			if(a.getOutgoing().isEmpty())	tempEnd = a;
			Integer tempName = new Integer(j++);
			a.setName(tempName.toString());
		}
		
		originStart.setName(tempStart.getName());
		cache.add(originStart);
		Stack<State> store = new Stack<State>();
		State position = new State();
		store.push(tempStart);
		boolean alreadyVisited = false;
		while(!store.isEmpty()){
			position = store.pop();
			if(!position.equals(tempEnd)){
				alreadyVisited = false;
				for(int i=0;i<position.getOutgoing().size() && !alreadyVisited;i++){
					Transition t1 = position.getOutgoing().get(i);
					State preTarget = new State();
					State target = new State();
					store.add(t1.getTarget());
					boolean exists = false;

					for(State search : cache){
						if(t1.getSource().getName().equals(search.getName()))	preTarget = search;
						if(t1.getTarget().getName().equals(search.getName())){
							target = search;
							exists = true;
						}
					}
					if(!visited.contains(preTarget)){
						if(!exists){
							origin.getStates().add(target);
							target.setName(t1.getTarget().getName());
							cache.add(target);
						}
						Transition t2 = new Transition();
						origin.getTransitions().add(t2);
						t2.setSource(preTarget);
						t2.setTarget(target);
						DelayInformation originDI = (DelayInformation)t2.findExtension(DelayInfoProvider.EXTENSION_ID);
						DelayInformation tempDI = (DelayInformation)t1.findExtension(DelayInfoProvider.EXTENSION_ID);
						originDI.getElements().addAll(EcoreUtil.copyAll(tempDI.getElements()));

						if(i==(position.getOutgoing().size()-1))	visited.add(preTarget);
					}
				}
			}
		}
		
		return origin;
	}
	
	public static Automaton product(Automaton store, Automaton temp){
		// Create automaton and specify extensions to be used.
		Automaton  newAutomaton= new Automaton();
		List<String> extensions = store.getUsedExtensionIds();
		newAutomaton.getUsedExtensionIds().addAll(extensions);
		
		// Automatically update the extensions.
		EA.monitorExtensions(newAutomaton, true);
		
		SilentTransitions.add(store);
		SilentTransitions.add(temp);
		ProductCache<State> cache = new ProductCache<State>();
		
		if(store.getTransitions().isEmpty()) newAutomaton = temp;
		
		else{
			int i = 0;
			for(State f : store.getStates()){
				Integer name1 = new Integer(i++);
				String stateName1 = name1.toString();
				f.setName(stateName1);
			}
			
			for(State g : temp.getStates()){
					Integer name2 = new Integer(i++);
					String stateName2 = name2.toString();
					g.setName(stateName2);
					
			}
			
			//Add possible product-states to registry
			for(State h : store.getStates()){
				for(State k : temp.getStates()){
					String productS1 = h.getName();
					String productS2 = k.getName();
					State productState = new State();
					productState.setName(productS1 + " x " + productS2);
					cache.addProduct(h, k, productState);
				}
			}
			
			for(Transition a : store.getTransitions()){
				DelayInformation DI1 = (DelayInformation)a.findExtension(DelayInfoProvider.EXTENSION_ID);
				List<DelayElement> DE1 = DI1.getElements();
				State source1 = a.getSource();
				State target1 = a.getTarget();
				for(Transition b : temp.getTransitions()){
					DelayInformation DI2 = (DelayInformation)b.findExtension(DelayInfoProvider.EXTENSION_ID);
					List<DelayElement> DE2 = DI2.getElements();
					State source2 = b.getSource();
					State target2 = b.getTarget();
					
					if(combindable(DE1,DE2)){
						State newSource = new State();
						State newTarget = new State();
						Transition newTransition = new Transition();
						
						// Name of the product source state.
						newSource = cache.getProduct(source1, source2);
						String Sname1 = source1.getName();  
						String Sname2 = source2.getName();
						newSource.setName(Sname1 + " x " + Sname2);
						newSource.setAutomaton(newAutomaton);

						// Name of the product target state.						
						newTarget = cache.getProduct(target1, target2);
						String Tname1 = target1.getName();  
						String Tname2 = target2.getName();
						newTarget.setName(Tname1 + " x " + Tname2);
						newTarget.setAutomaton(newAutomaton);
							
						//Set source and target of new transition
						newTransition.setAutomaton(newAutomaton);
						newTransition.setSource(newSource);
						newTransition.setTarget(newTarget);
						DelayInformation newDI = (DelayInformation)newTransition.findExtension(DelayInfoProvider.EXTENSION_ID);
						
						//Set DI of new Transition
						List<DelayElement> Dlist = new Vector<DelayElement>();
						for(DelayElement c : DE1){
							if(!newDI.getElements().contains(c) && !Dlist.contains(c)){
								Dlist.add(c);	
							}
							
						}
						for(DelayElement d : DE2){
							if(!newDI.getElements().contains(d) && !Dlist.contains(d))	Dlist.add(d);
	
						}
						
						newDI.getElements().addAll(EcoreUtil.copyAll(Dlist));
					}
				}
			}
		}
		SilentTransitions.remove(newAutomaton);
		
		return newAutomaton;
		
	}

	public static boolean combindable(List<DelayElement> DE1, List<DelayElement> DE2){
		boolean result = false;
		
		if((DE1.isEmpty() && !DE2.isEmpty())|| (!DE1.isEmpty() && DE2.isEmpty()))	result = true;
		else if(!DE1.isEmpty() && !DE2.isEmpty() && DE1.containsAll(DE2) && DE2.containsAll(DE1))	result = true;
		
		return result;
	}
}
