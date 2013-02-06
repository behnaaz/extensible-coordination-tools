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

import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.SilentTransitions;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.portnames.*;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.ea.util.ProductCache;

public class Product {
	public static Automaton compute(Automaton store, Automaton temp){
		// Create automaton and specify extensions to be used.
		Automaton  newAutomaton= new Automaton();
		newAutomaton.getUsedExtensionIds().add(StartStateExtensionProvider.EXTENSION_ID);
		newAutomaton.getUsedExtensionIds().add(AutomatonPortNamesProvider.EXTENSION_ID);
		newAutomaton.getUsedExtensionIds().add(IntensionalPortNamesProvider.EXTENSION_ID);
		newAutomaton.getUsedExtensionIds().add(DelayInfoProvider.EXTENSION_ID);
		EA.monitorExtensions(newAutomaton, true);
		
		// Automatically update the extensions.
		//EA.monitorExtensions(newAutomaton, true);
		
		SilentTransitions.add(store);
		SilentTransitions.add(temp);
		ProductCache<State> cache = new ProductCache<State>();
		
		if(store.getTransitions().isEmpty()) newAutomaton = temp;
		
		else{
			//Name raw states in each automaton
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
			
			//Add possible product-states in registry
			for(State h : store.getStates()){
				for(State k : temp.getStates()){
					String productS1 = h.getName();
					String productS2 = k.getName();
					State productState = new State();
					productState.setName(productS1 + " x " + productS2);
					cache.addProduct(h, k, productState);
					
				}
			}
			
			
			AutomatonPortNames Aports1 = (AutomatonPortNames) store.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
			AutomatonPortNames Aports2 = (AutomatonPortNames) temp.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
			List<String> AportsList1 = Aports1.getValues();
			List<String> AportsList2 = Aports2.getValues();
			List<String> newAports = new Vector<String>();
			for(String a : Aports1.getValues()){
				if(!newAports.contains(a))	newAports.add(a);
			}
			for(String b : Aports2.getValues()){
				if(!newAports.contains(b))	newAports.add(b);
			}
			
			((AutomatonPortNames) newAutomaton.findExtension(AutomatonPortNamesProvider.EXTENSION_ID)).getValues().addAll(newAports);
			for(Transition a : store.getTransitions()){
				IntensionalPortNames Iports1 = (IntensionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				DelayInformation DI1 = (DelayInformation) a.findExtension(DelayInfoProvider.EXTENSION_ID);
				List<DelayElement> DE1 = DI1.getElements();
				List<String> M1 = Iports1.getRequests();
				List<String> N1 = Iports1.getFirings();
				
				State source1 = a.getSource();
				State target1 = a.getTarget();
				for(Transition b : temp.getTransitions()){
					IntensionalPortNames Iports2 = (IntensionalPortNames) b.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					DelayInformation DI2 = (DelayInformation) b.findExtension(DelayInfoProvider.EXTENSION_ID);
					List<DelayElement> DE2 = DI2.getElements();
					List<String> M2 = Iports2.getRequests();
					List<String> N2 = Iports2.getFirings();
					
					State source2 = b.getSource();
					State target2 = b.getTarget();
					
					if(combinable(M1,N1,M2,N2,AportsList1,AportsList2)){
						State newSource = new State();
						State newTarget = new State();
						Transition newTransition = new Transition();
						
						// Name of the product source state.
						newSource = cache.getProduct(source1, source2);
						String Sname1 = source1.getName();  
						String Sname2 = source2.getName();
						newSource.setName(Sname1 + " x " + Sname2);
						newSource.setAutomaton(newAutomaton);
						if(((BooleanExtension) source1.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue() == true &&
								((BooleanExtension) source2.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue() == true)
							((BooleanExtension) newSource.findExtension(StartStateExtensionProvider.EXTENSION_ID)).setValue(true);

						// Name of the product target state.						
						newTarget = cache.getProduct(target1, target2);
						String Tname1 = target1.getName();  
						String Tname2 = target2.getName();
						newTarget.setName(Tname1 + " x " + Tname2);
						newTarget.setAutomaton(newAutomaton);
						if(((BooleanExtension) target1.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue() == true &&
								((BooleanExtension) target2.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue() == true)
							((BooleanExtension) newTarget.findExtension(StartStateExtensionProvider.EXTENSION_ID)).setValue(true);
							
						//Set source and target of new transition
						newTransition.setAutomaton(newAutomaton);
						newTransition.setSource(newSource);
						newTransition.setTarget(newTarget);
						DelayInformation newDI = (DelayInformation)newTransition.findExtension(DelayInfoProvider.EXTENSION_ID);
						
						//Set Intensional port names of new transition
						IntensionalPortNames newIports = (IntensionalPortNames) newTransition.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
						List<String> RList = new Vector<String>();
						List<String> FList = new Vector<String>();
						for(String e : Iports1.getRequests()){
							if(!RList.contains(e))	RList.add(e);
						}
						for(String f : Iports2.getRequests()){
							if(!RList.contains(f))	RList.add(f);
						}
						
						for(String g : Iports1.getFirings()){
							if(!FList.contains(g))	FList.add(g);
						}
						for(String h : Iports2.getFirings()){
							if(!FList.contains(h))	FList.add(h);
						}
						newIports.getRequests().addAll(RList);
						newIports.getFirings().addAll(FList);
						
						//Set DI of new transition
						List<DelayElement> Dlist = new Vector<DelayElement>();
						Dlist.addAll(DE1);
						Dlist.addAll(DE2);
						List<DelayElement> remove = new Vector<DelayElement>();
						
						if(!DE1.isEmpty() && !DE2.isEmpty()) {
							for(int j=0;j<Dlist.size();j++){
								DelayElement l = Dlist.get(j);
								for(int k=0;k<Dlist.size();k++){
									if(j!=k){
										DelayElement m = Dlist.get(k);
										if(l.isEqual(m))	remove.add(l);
									}
								}
							}
						}
						List<DelayElement> attach = new Vector<DelayElement>();
						boolean exists = false;
						for(DelayElement o : remove){
							for(DelayElement d: attach){
								if(o.isEqual(d))	exists = true;
							}
							if(!exists)	attach.add(o);
							exists = false;
						}
						
						Dlist.removeAll(remove);
						Dlist.addAll(attach);
						
						
						
						/*for(DelayElement c : DE1){
							if(!Dlist.contains(c)){
								Dlist.add(c);	
							}
							
						}
						for(DelayElement d : DE2){
							if(!Dlist.contains(d))	Dlist.add(d);
	
						}*/
						
						newDI.getElements().addAll(EcoreUtil.copyAll(Dlist));
					}
				}
			}
		}
		SilentTransitions.remove(newAutomaton);
		removeUnreachableStates(newAutomaton);
		
		return newAutomaton;
	}
	public static void removeUnreachableStates(Automaton automaton){
		List<State> removeStates = new Vector<State>();
		List<Transition> removeTransitions = new Vector<Transition>();
		
		for(State a : automaton.getStates()){
			boolean initial = ((BooleanExtension) a.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue();
			if(initial!=true  && a.getIncoming().isEmpty()){
				removeStates.add(a);
				removeTransitions.addAll(a.getOutgoing());
			}
		}
		for(Transition b : removeTransitions){
			b.setSource(null);
			b.setTarget(null);
		}
		automaton.getStates().removeAll(removeStates);
		automaton.getTransitions().removeAll(removeTransitions);
		if(!removeStates.isEmpty())	removeUnreachableStates(automaton);
		else return; 
	}
	
	public static boolean combinable(List<String> M1, List<String> N1, List<String> M2, List<String> N2, List<String> portList1, List<String> portList2){
		boolean result = false;
		HashSet<String> M1temp = new HashSet<String>(M1);
		HashSet<String> N1temp = new HashSet<String>(N1);
		HashSet<String> p1Temp = new HashSet<String>(portList2);
		HashSet<String> M2temp = new HashSet<String>(M2);
		HashSet<String> p2Temp = new HashSet<String>(portList1);
		M1temp.retainAll(p1Temp);
		N1temp.retainAll(p1Temp);
		M2temp.retainAll(p2Temp);
		
		if(!M1.isEmpty() && !M2.isEmpty()){
			if(M1.equals(M2))	result = true;
		}
		else if(!N1.isEmpty() && !N2.isEmpty()){
			if(N1.equals(N2))	result = true;
		}
		else if(!N1.isEmpty() && N1temp.isEmpty() && M2.isEmpty())	result = true;
		else{
			if(M1temp.equals(M2temp) && N1.equals(N2))	result = true;
		}
		return result;
	}

}
