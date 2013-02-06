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
package org.ect.codegen.ea2mc.actions;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.codegen.ea2mc.providers.DElmNode;
import org.ect.codegen.ea2mc.providers.NewLinkedList;
import org.ect.codegen.ea2mc.providers.Seq2Automaton;
import org.ect.codegen.ea2mc.providers.newSeq2Automaton;
import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.portnames.*;
import org.ect.ea.extensions.portnames.providers.*;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;

public class QIAintoMC {
	public Automaton automaton = new Automaton();
	public List<DelayElement> external = new Vector<DelayElement>();
	public List<String> edgePorts = new Vector<String>();
			
	public QIAintoMC(Automaton simpleAutomaton){
		super();
		automaton = simpleAutomaton;
	}
	
	public Automaton compute(){
		Automaton MC = EA.copyAutomaton(automaton);
		List<Transition> beSpread = new Vector<Transition>();
		List<Transition> firings = new Vector<Transition>();
		List<Transition> singleFirings = new Vector<Transition>();
		for(Transition t1 : MC.getTransitions()){
			DelayInformation delay = (DelayInformation) t1.findExtension(DelayInfoProvider.EXTENSION_ID);
			if(delay.getElements().size()>1){
				// Filter synchronized multi-firings
				beSpread.add(t1);
				firings.add(t1);
			}
			else if(delay.getElements().size()==1){
				IntensionalPortNames Iports = (IntensionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				// Get boundary nodes 
				if(Iports.getFirings()==null || Iports.getFirings().isEmpty()){
					DelayElement edge = delay.getElements().get(0);
					boolean add = true;
					for(DelayElement tempDE : external){
						if(tempDE.isEqual(edge))	add = false;	
					}
					if(add){
						List<String> inputs = edge.getInput();
						List<String> outputs = edge.getOutput();
						for(String a : inputs){
							StringTokenizer tempI = new StringTokenizer(a,",");
							while(tempI.hasMoreTokens()){
								String port1 = tempI.nextToken();
								if(!edgePorts.contains(port1))	edgePorts.add(port1);
							}
						}
						for(String b : outputs){
							StringTokenizer tempO = new StringTokenizer(b,",");
							while(tempO.hasMoreTokens()){
								String port2 = tempO.nextToken();
								if(!edgePorts.contains(port2))	edgePorts.add(port2);
							}
						}
						
						external.add(edge);
					}
				}
				else{
					if(Iports.getRequests()==null || Iports.getRequests().isEmpty()){
						if(delay.getElements()!=null && !delay.getElements().isEmpty()){
							DelayElement singleFiring = delay.getElements().get(0);
							if(singleFiring.getInput()!=null && !singleFiring.getInput().isEmpty()){
								List<String> SingleIports = new Vector<String>();
								for(String d : singleFiring.getInput()){
									StringTokenizer Itoken = new StringTokenizer(d,",");
									while(Itoken.hasMoreTokens()){
										String singlePort1 = Itoken.nextToken();
										if(!SingleIports.contains(singlePort1))	SingleIports.add(singlePort1);
									}
								}
								singleFiring.getInput().clear();
								singleFiring.getInput().addAll(SingleIports);
							}
							if(singleFiring.getOutput()!=null && !singleFiring.getOutput().isEmpty()){
								List<String> SingleOports = new Vector<String>();
								for(String e : singleFiring.getOutput()){
									StringTokenizer Otoken = new StringTokenizer(e,",");
									while(Otoken.hasMoreTokens()){
										String singlePort2 = Otoken.nextToken();
										if(!SingleOports.contains(singlePort2))	SingleOports.add(singlePort2);
									}
								}
								singleFiring.getOutput().clear();
								singleFiring.getOutput().addAll(SingleOports);
							}
							
							firings.add(t1);
						}
					}
					singleFirings.add(t1);
				}
			}
		}
			
		MC.getUsedExtensionIds().remove(IntensionalPortNamesProvider.EXTENSION_ID);
		System.out.println("MC extentions : "+ MC.getUsedExtensionIds());
		
		// Automatically update the extensions.
		EA.monitorExtensions(MC, true);
	
		MC.getTransitions().removeAll(beSpread);
		
		List<Transition> arrivals = new Vector<Transition>();
		for(Transition d : MC.getTransitions()){
			arrivals.add(d);
		}
				
		//Check a transition from the current state is already added to the origin automaton.
		// 1. Obtain the firings having the same source state
		List<Transition> checkingList = new Vector<Transition>();
		int[] size = new int[firings.size()/2];
		//Initialize 'size' with -1
		for(int i=0;i<size.length;i++)	size[i] = -1; 
		int index = 0;
		for(Transition t2 : firings){
			List<Transition> temp = new Vector<Transition>();
			int num = 0;
			for(Transition t3 : firings){
				if(!t2.equals(t3) && t2.getSource().equals(t3.getSource()) && !temp.contains(t3) && !checkingList.contains(t3)){
					temp.add(t3);
					num++;
				}
			}
			if(num>=2){
				temp.add(t2);
				num++;
				if(!checkingList.containsAll(temp)){
					size[index++] = num;
					checkingList.addAll(temp);
				}
			}
			else	temp.clear();
		}
		//2. Check if some firings can be included by others
		List<Transition> retainable = new Vector<Transition>();
		for(int i=0;i<index;i++){
			List<Transition> tempTlist = new Vector<Transition>();
			int startPoint = 0;
			if(i!=0){
				for(int k=0;k<i;k++)	startPoint = startPoint + size[k];
			}
			for(int j=0;j<size[i];j++){
				tempTlist.add(checkingList.get(startPoint+j));
			}
			retainable.addAll(include(tempTlist, true));
		}
		//3. Decide a set of transitions which should be deleted without spreading 
		List<Transition> removeWithoutSpreading = new Vector<Transition>();
		checkingList.removeAll(retainable);
		for(Transition t5 : checkingList){
			if(beSpread.contains(t5))	removeWithoutSpreading.add(t5);
		}
		beSpread.removeAll(removeWithoutSpreading);
		
		//Split synchronized multi-event firings
		for(Transition t2 : beSpread){
			
			if (!EA.hasPlatform()) {
				EA.initStandalone();
				// Register extension definitions.
				EA.getExtensionRegistry().registerExtensionDefinition(StartStateExtensionProvider.EXTENSION_DEFINITION);
				EA.getExtensionRegistry().registerExtensionDefinition(DelayInfoProvider.EXTENSION_DEFINITION);
			}
			
			Automaton extended = new Automaton();
			extended.getUsedExtensionIds().add(StartStateExtensionProvider.EXTENSION_ID);
			extended.getUsedExtensionIds().add(DelayInfoProvider.EXTENSION_ID);
			
			// Automatically update the extensions.
			EA.monitorExtensions(extended, true);
			
			/*// Remove delay for arrival rate at mixed nodes
			DelayInformation DI = (DelayInformation) t2.findExtension(DelayInfoProvider.EXTENSION_ID);
			List<DelayElement> mixed = new Vector<DelayElement>();
			for(DelayElement DE : DI.getElements()){
				if(DE.getDelay()<0)		mixed.add(DE);	
			}
			DI.getElements().removeAll(mixed);*/
			
			extended = extractDelaySequence(extended,t2);
			addingTransactionLabel(extended);
			MC = insert(MC,extended,t2);
		}
		for(int i=0;i<singleFirings.size();i++){
			addingTransactionLabel(singleFirings.get(i));
		}
		
		// Check it there are missed transitions of data arrivals
		List<Transition> candidates = new Vector<Transition>();
		for(Transition c : MC.getTransitions()){
			if(!arrivals.contains(c))	candidates.add(c);	
		}
		MC = addMissingArrivals(MC,candidates,external,edgePorts,beSpread);
		
		
		for(Transition t3 : beSpread){
			t3.setSource(null);
			t3.setTarget(null);
		}
		for(Transition t4 : removeWithoutSpreading){
			t4.setSource(null);
			t4.setTarget(null);
		}
		return MC;
	}
	
	void addingTransactionLabel(Transition t){
		DelayInformation delay = (DelayInformation) t.findExtension(DelayInfoProvider.EXTENSION_ID);
		DelayElement element = delay.getElements().get(0);
		
		if(element.getInput().isEmpty())	element.getInput().add("Tran");
		else if(element.getOutput().isEmpty()) element.getOutput().add("Tran");
	}
	
	List<Transition> include(List<Transition> list, boolean test){
		if(!test){
			return list;
		}
		else{
			Transition remove = new Transition();
			boolean done = false;
			for(int i=0;i<list.size();i++){
				Transition t1 = list.get(i);
				DelayInformation DI1 = (DelayInformation) t1.findExtension(DelayInfoProvider.EXTENSION_ID);
				List<DelayElement> DE1 = DI1.getElements();
				boolean include = false;
				for(int j=0;j<list.size();j++){
					if(i!=j){
						Transition t2 = list.get(j);
						DelayInformation DI2 = (DelayInformation) t2.findExtension(DelayInfoProvider.EXTENSION_ID);
						List<DelayElement> DE2 = DI2.getElements();
						include = containsAll(DE1, DE2);
					
						if(!include)	break;
					/*	else{
							if(j==list.size()-1 && include){
								remove = list.get(i);
								done = true;
							}
						}*/
					}
					
						if(j==list.size()-1 && include){
							remove = list.get(i);
							done = true;
						}
					
				}
				if(done)	break;
			}
			if(done){
				list.remove(remove);
				return include(list, true);
			}
			else return include(list, false);
		}
	}
	
	boolean containsAll(List<DelayElement> list1, List<DelayElement> list2){
		boolean result = true;
		List<String> inputs1 = new Vector<String>();
		List<String> outputs1 = new Vector<String>();
		List<String> inputs2 = new Vector<String>();
		List<String> outputs2 = new Vector<String>();
		for(DelayElement d1 : list1){
			if(d1.getInput()==null)	d1.getInput().addAll(new Vector<String>());
			if(d1.getOutput()==null)	d1.getOutput().addAll(new Vector<String>());
			for(String a : d1.getInput()){
				StringTokenizer token1 = new StringTokenizer(a,",");
				while(token1.hasMoreTokens()){
					String input1 = token1.nextToken();
					if(!inputs1.contains(input1))	inputs1.add(input1);	
				}
			}
			for(String b : d1.getOutput()){
				StringTokenizer token2 = new StringTokenizer(b,",");
				while(token2.hasMoreTokens()){
					String output1 = token2.nextToken();
					if(!outputs1.contains(output1))	outputs1.add(output1);	
				}
			}
		}
		for(DelayElement d2 : list2){
			if(d2.getInput()==null)	d2.getInput().addAll(new Vector<String>());
			if(d2.getOutput()==null) d2.getOutput().addAll(new Vector<String>());
			for(String c : d2.getInput()){
				StringTokenizer token3 = new StringTokenizer(c,",");
				while(token3.hasMoreTokens()){
					String input2 = token3.nextToken();
					if(!inputs2.contains(input2))	inputs2.add(input2);	
				}
			}
			for(String d : d2.getOutput()){
				StringTokenizer token4 = new StringTokenizer(d,",");
				while(token4.hasMoreTokens()){
					String output2 = token4.nextToken();
					if(!outputs2.contains(output2))	outputs2.add(output2);	
				}
			}
		}
		if(!inputs1.containsAll(inputs2) || !outputs1.containsAll(outputs2))	result = false;
		return result;
	}
	
	void addingTransactionLabel(Automaton copy){
		for(Transition a : copy.getTransitions()){
			DelayInformation DI = (DelayInformation) a.findExtension(DelayInfoProvider.EXTENSION_ID);
			DelayElement DE = DI.getElements().get(0);
			List<String> inputs = new Vector<String>();
			List<String> outputs = new Vector<String>();
			for(String input : DE.getInput()){
				StringTokenizer Itoken = new StringTokenizer(input,",");
				while(Itoken.hasMoreTokens()){
					inputs.add(Itoken.nextToken());
				}
			}
			for(String output : DE.getOutput()){
				StringTokenizer Otoken = new StringTokenizer(output,",");
				while(Otoken.hasMoreTokens()){
					outputs.add(Otoken.nextToken());
				}
			}
			if(inputs.size()+outputs.size()==1){
				if(inputs.isEmpty()){
					DE.getInput().clear();
					DE.getInput().add("Tran");
				}
				else{
					DE.getOutput().clear();
					DE.getOutput().add("Tran");
				}
			}
		}
	}
	
	public Automaton addMissingArrivals(Automaton origin, List<Transition> Tlist, List<DelayElement> External, List<String> ports, List<Transition> removed){
		List<Transition> candidates = new Vector<Transition>();
		for(Transition a : Tlist){
			State source = a.getSource();
			List<Transition> sourceOutgoings = source.getOutgoing();
			boolean decide = false;
			for(Transition b : sourceOutgoings){
				if(!Tlist.contains(b) && !decide){
					DelayInformation tempDI = (DelayInformation)b.findExtension(DelayInfoProvider.EXTENSION_ID);
					if(!tempDI.getElements().isEmpty()){
						DelayElement tempDE = tempDI.getElements().get(0);
						if(!tempDE.getInput().isEmpty() && !decide){
							StringTokenizer tempI = new StringTokenizer(tempDE.getInput().get(0),",");
							while(tempI.hasMoreTokens() && !decide){
								if(ports.contains(tempI.nextToken())){
									candidates.add(a);
									decide = true;
								}
							}
						}
						if(!tempDE.getOutput().isEmpty() && !decide){
							StringTokenizer tempO = new StringTokenizer(tempDE.getOutput().get(0),",");
							while(tempO.hasMoreTokens() && !decide){
								if(ports.contains(tempO.nextToken())){
									candidates.add(a);
									decide = true;
								}
							}
						}

					}
				}
			}
		}
		
		List<Transition> present = new Vector<Transition>();
		List<Transition> post = new Vector<Transition>();
		present.addAll(candidates);
		candidates.clear();
		//for(Transition test : present)	if(test.getTarget()==null)	System.out.println("target of source "+ test.getSource()+" is null");
		while(present!=null && !present.isEmpty()){
			// Get transitions to be drawn
			for(Transition c : present){
				DelayElement pivot = ((DelayInformation)c.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().get(0);
				if(pivot.getInput()==null)	pivot.getInput().addAll(new Vector<String>());
				if(pivot.getOutput()==null)	pivot.getOutput().addAll(new Vector<String>());
				
				List<Transition> draws = new Vector<Transition>();
				for(Transition d : c.getSource().getOutgoing()){
					if(!c.equals(d) && !((DelayInformation)d.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().isEmpty()){
						List<DelayElement> checkingEdgePorts = ((DelayInformation)d.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements();
						for(DelayElement m : checkingEdgePorts){
							if(m.getInput()==null) m.getInput().addAll(new Vector<String>());
							if(m.getOutput()==null) m.getOutput().addAll(new Vector<String>());
							List<String> mPorts = new Vector<String>();
							if(!m.getInput().isEmpty()){
								StringTokenizer Itoken = new StringTokenizer(m.getInput().get(0),",");
								while(Itoken.hasMoreTokens()){
									mPorts.add(Itoken.nextToken());
								}
							}
							if(!m.getOutput().isEmpty()){
								StringTokenizer Otoken = new StringTokenizer(m.getOutput().get(0),",");
								while(Otoken.hasMoreTokens()){
									mPorts.add(Otoken.nextToken());
								}
							}
							if(ports.containsAll(mPorts))	draws.add(d);
						}
					}
				}
				
				// Check transitions in "draws" have already drawn or not.
				List<Transition> alreadyExists = c.getTarget().getOutgoing();
				List<Transition> notAdded2 = new Vector<Transition>();
				for(Transition k : alreadyExists){
					if(!((DelayInformation)k.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().isEmpty()){
						DelayElement existsDE = ((DelayInformation)k.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().get(0);
						if(existsDE.getInput()==null)	existsDE.getInput().addAll(new Vector<String>());
						if(existsDE.getOutput()==null)	existsDE.getOutput().addAll(new Vector<String>());
						
						for(Transition l : draws){
							if(!((DelayInformation)l.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().isEmpty()){
								DelayElement tempDE = ((DelayInformation)l.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().get(0);
								if(tempDE.getInput()==null)	tempDE.getInput().addAll(new Vector<String>());
								if(tempDE.getOutput()==null)	tempDE.getOutput().addAll(new Vector<String>());
								
								if(tempDE.getInput().equals(existsDE.getInput()) && tempDE.getOutput().equals(existsDE.getOutput())){
									notAdded2.add(l);
								}
							}
						}
					}
				}
				draws.removeAll(notAdded2);
				
				
				List<Transition> newAdded = new Vector<Transition>();
				for(Transition e : draws){
					DelayElement element = ((DelayInformation) e.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().get(0);
					State tempNext = e.getTarget();
					List<Transition> possibleOutgoings = tempNext.getOutgoing();
					for(Transition f : possibleOutgoings){
						if(!((DelayInformation)f.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().isEmpty()){
							DelayElement outDE = ((DelayInformation) f.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().get(0);
							if(outDE.getInput().equals(pivot.getInput()) && outDE.getOutput().equals(pivot.getOutput())){
								Transition added = new Transition();
								DelayInformation addDI = new DelayInformation();
								addDI.setId(DelayInfoProvider.EXTENSION_ID);
								addDI.setOwner(added);
								DelayElement addedDE = new DelayElement();
								if(element.getInput()!=null)	addedDE.getInput().addAll(element.getInput());
								else	addedDE.getInput().addAll(new Vector<String>());
								if(element.getOutput()!=null)	addedDE.getOutput().addAll(element.getOutput());
								else	addedDE.getOutput().addAll(new Vector<String>());
								addedDE.setDelay(element.getDelay());
								//adding Rewards					
								addedDE.getRewards().addAll(element.getRewards());								
								addDI.getElements().add(addedDE);
								
								added.setSource(c.getTarget());
								added.setTarget(f.getTarget());
								if(!candidates.contains(added) && !newAdded.contains(added)){
									candidates.add(added);
									newAdded.add(added);
								}
								else{
									added.setSource(null);
									added.setTarget(null);
								}
							}
						}
					}
				}
				//Check if the next state also misses arrival transitions or not
				List<Transition> next = new Vector<Transition>();
				if(!draws.isEmpty())	next = c.getTarget().getOutgoing();
				for(Transition g : next){
					if(!newAdded.contains(g)){
						List<Transition> Dnext = new Vector<Transition>();
						Dnext = g.getTarget().getOutgoing();
						boolean goAhead = false;
						for(Transition h : Dnext){
							if(!removed.contains(h)){
								DelayElement nextDE = ((DelayInformation)h.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements().get(0);
								List<String> nextPorts = new Vector<String>();
								String nextInput = new String();
								String nextOutput = new String();
								if(!nextDE.getInput().isEmpty())	nextInput = nextDE.getInput().get(0);
								if(!nextDE.getOutput().isEmpty())	nextOutput = nextDE.getOutput().get(0); 	
								StringTokenizer tempNextI = new StringTokenizer(nextInput,",");
								StringTokenizer tempNextO = new StringTokenizer(nextOutput,",");
								while(tempNextI.hasMoreTokens()){
									nextPorts.add(tempNextI.nextToken());
								}
								while(tempNextO.hasMoreTokens()){
									nextPorts.add(tempNextO.nextToken());
								}
								if(!ports.containsAll(nextPorts))	goAhead = true;
							}
						}
						if(goAhead)	post.add(g);
					}
				}
				origin.getTransitions().addAll(newAdded);
			}
			present.clear();
			present.addAll(post);
			post.clear();
		}
		
		return origin;
	}
	
	public Automaton insert(Automaton origin, Automaton temp, Transition t){
		State originStart = t.getSource();
		State originEnd = t.getTarget();
		State tempStart = new State();
		State tempEnd = new State();
		List<State> cache = new Vector<State>();
		List<State> visited = new Vector<State>();
		int j = 0;
		//Rename states in an origin automaton
		for(State b : origin.getStates()){
			Integer originName = new Integer(j++);
			b.setName(originName.toString());
		}
		//Obtain an initial and a final states from the automaton to be inserted
		for(State a : temp.getStates()){
			if(a.getIncoming().isEmpty())	tempStart = a;
			if(a.getOutgoing().isEmpty())	tempEnd = a;
			Integer tempName = new Integer(j++);
			a.setName(tempName.toString());
		}
		
		originStart.setName(tempStart.getName());
		cache.add(originStart);
		originEnd.setName(tempEnd.getName());
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
							if(!t1.getTarget().getName().equals(tempEnd.getName())){
								target.setName(t1.getTarget().getName());
								origin.getStates().add(target);
							}
							else	target = originEnd;	
								
							cache.add(target);
						}
						Transition t2 = new Transition();
						t2.setSource(preTarget);
						t2.setTarget(target);
						origin.getTransitions().add(t2);
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
	
	//Split synchronized multi-event firings
	public Automaton extractDelaySequence(Automaton automaton, Transition t){
	
		List<DelayElement> DI = ((DelayInformation) t.findExtension(DelayInfoProvider.EXTENSION_ID)).getElements();
		NewLinkedList result = new NewLinkedList();
		List<DelayElement> Init = getEdgeInput(DI);

		
		List<NewLinkedList> dlist = new Vector<NewLinkedList>();
		List<NewLinkedList> pureList = new Vector<NewLinkedList>();
		for(DelayElement a : Init){
			NewLinkedList temp = new NewLinkedList();
			NewLinkedList copy = new NewLinkedList();
			temp.sequentialInsertion(a);
			copy.sequentialInsertion(a);
			dlist.add(temp);
			pureList.add(copy);
			List<DelayElement> Pre = new Vector<DelayElement>();
			List<DelayElement> Post = new Vector<DelayElement>();
			
			Pre.add(a);
			Post = getNext(DI,Pre);
			while(!Post.isEmpty()){
				for(DelayElement b : Post){
					temp.contains_removes(b);
					copy.contains_removes(b);
				}
								
				temp.sequentialInsertion(Post.get(0));
				copy.sequentialInsertion(Post.get(0));
				for(int i=1;i<Post.size();i++){
					temp.parallelInsertion(Post.get(i));
					copy.parallelInsertion(Post.get(i));
				}
				Pre.clear();
				Pre.addAll(Post);
				Post.clear();
				Post = getNext(DI,Pre);				
			}
		}
		
		List<DelayElement> comTemp = new Vector<DelayElement>();
		List<DelayElement> com = new Vector<DelayElement>();
		NewLinkedList Common = new NewLinkedList();		
				
		for(int j=0;j<Init.size();j++){
			for(int k=j+1;k<Init.size();k++){
				comTemp.addAll(commonDI(dlist.get(j),dlist.get(k),Common));
				for(DelayElement a : comTemp){
					if(!com.contains(a))	com.add(a);
				}
				comTemp.clear();
			}
		}
		
		if(Common.isEmpty()){
			for(int m=0;m<Init.size();m++){
				result = combineParallel(result,dlist.get(m),false);
			}
		}
		
		else{
			result = arrangeDelaySequence(Common,dlist,false);
			
			/*List<DelayElement> to = new Vector<DelayElement>();
			List<DelayElement> from = new Vector<DelayElement>();
			NewNode<DelayElement> position = Common.getHead().next;
			NewNode<DelayElement> Rposition = position;
			while(Rposition!=null){
				if(!to.contains(Rposition.getData()))	to.add(Rposition.getData());
				Rposition = Rposition.right;
			}
			
			for(int n=0;n<Init.size();n++){
				if(dlist.get(n).contains(to)){
					result = combineParallel(result,dlist.get(n).subSeq1(to));
				}
			}
			
			result.getCurrent().next.setData(to.get(0));
			result.setCurrent(result.getCurrent().next);
			for(int i=1;i<to.size();i++)	result.addDatatoRight(to.get(i));
			//Set other dummy-tail nodes 
			NewNode<DelayElement> settingDummy1 = result.getCurrent();
			while(settingDummy1!=null){
				settingDummy1.dummyTail = true;
				settingDummy1 = settingDummy1.right;
			}
			
			position = position.next;
			Rposition = position;
			from.addAll(to);
			to.clear();
			while(position!=null){
				while(Rposition!=null){
					if(!to.contains(Rposition.getData()))	to.add(Rposition.getData());
					Rposition = Rposition.right;
				}
				
				NewLinkedList Construction = new NewLinkedList();
				for(int i=0;i<Init.size();i++){
					if(dlist.get(i).contains(to) && dlist.get(i).contains(from)){
						Construction = combineParallel(Construction,dlist.get(i).subSeq2(from, to));
					}
				}
				Construction.getCurrent().next.setData(to.get(0));
				Construction.setCurrent(Construction.getCurrent().next);
				for(int k=1;k<to.size();k++)	Construction.addDatatoRight(to.get(k));
				//Set other dummy-tail nodes 
				NewNode<DelayElement> settingDummy2 = Construction.getCurrent();
				while(settingDummy2!=null){
					settingDummy2.dummyTail = true;
					settingDummy2 = settingDummy2.right;
				}
				
				 
				NewNode<DelayElement> Tail = result.getCurrent();
				Tail.next = Construction.getHead().next;
				Construction.getHead().next.prev = Tail;
				//Set "next" of dummy-tail nodes
				Tail = Tail.right;
				while(Tail!=null){
					Tail.next = Construction.getHead().next;
					Tail = Tail.right;
				}
				result.setCurrent(Construction.getCurrent());
				
				position = position.next;
				Rposition = position;
				from.clear();
				from.addAll(to);
				to.clear();
			}
			
			NewLinkedList lastPart = new NewLinkedList();
			for(int p=0;p<Init.size();p++){
				if(dlist.get(p).contains(from)){
					lastPart = combineParallel(lastPart, dlist.get(p).subSeq3(from));
				}
			}
			NewNode<DelayElement> lastTail = new NewNode<DelayElement>();
			if(!lastPart.isEmpty()){
				lastTail = result.getCurrent(); 
				lastTail.next = lastPart.getHead().next;
				lastPart.getHead().next.prev = lastTail;
			}*/
		}
		
		// Rearrange the delay-sequence
		// Check here!!!
		NewLinkedList done = result;
		for(int i=0;i<pureList.size();i++){
			NewLinkedList newComList = new NewLinkedList();
			commonDI(pureList.get(i),done, newComList);
			if(!newComList.isEmpty()){
				List<NewLinkedList> list = new Vector<NewLinkedList>();
				list.add(pureList.get(i));
				list.add(done);
				done = arrangeDelaySequence(newComList, list, true);
			}
		}
		if(!com.isEmpty()){
			for(int j=0;j<pureList.size();j++){
				if(!pureList.get(j).contains(com)){
					done = combineParallel(done, pureList.get(j),false);
				}
			}
		}
		
		SetDummy(done,com);
//		automaton = Seq2Automaton.addNewParts(automaton,t, done);
		automaton = newSeq2Automaton.addNewParts(automaton, t, done, false);

		return automaton;
	}
	
	public void SetDummy(NewLinkedList list, List<DelayElement> dummy){
		DElmNode<DelayElement> start = list.getHead();
		Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
		store.push(start);
		while(!store.isEmpty()){
			DElmNode<DelayElement> temp = store.pop();
			if(temp!=null){
				if(temp.getData()==null && temp.right!=null){
					temp.setDummyTail(false);
					store.push(temp.next);
					store.push(temp.right);
				}
				else if(temp.getData()==null && temp.right==null){
					temp.setDummyTail(false);
					store.push(temp.next);
				}
				else if(temp.getData()!=null){
					boolean found = false;
					for(int i=0;i<dummy.size();i++){
						if(list.contains(temp, dummy.get(i))){
							found = true;
							temp.setDummyTail(true);
						}
					}
					if(!found){
						temp.setDummyTail(false);
					}
					DElmNode<DelayElement> horizontal = temp.right;
					while(horizontal!=null){
						horizontal.setDummyTail(found);
						horizontal = horizontal.right;
					}
					store.push(temp.next);
				}
			}
		}
	}
	
	public void addIrrelevant(NewLinkedList origin,NewLinkedList compare){
		DElmNode<DelayElement> start1 = origin.getHead();
		DElmNode<DelayElement> start2 = compare.getHead();
		List<DelayElement> added = getAllElements(start1);
		List<DelayElement> missing = getAllElements(start2);
		missing.removeAll(added);
		
		Stack<DElmNode<DelayElement>> stack1 = new Stack<DElmNode<DelayElement>>();
		Stack<DElmNode<DelayElement>> stack2 = new Stack<DElmNode<DelayElement>>();
		stack1.push(start1);
		stack2.push(start2);
		
		while(!stack1.isEmpty() && !stack2.isEmpty()){
			DElmNode<DelayElement> temp1 = stack1.pop();
			DElmNode<DelayElement> temp2 = stack2.pop();
									
			if((temp2.getData()==null && temp2.right!=null) && 
					(temp1.getData()==null && temp1.right!=null)){
				if(temp2.next!=null && temp1.next!=null){
					stack2.push(temp2.next);
					stack2.push(temp2.right);
					stack1.push(temp1.next);
					stack1.push(temp1.right);
				}
				else if(temp2.next!=null && temp1.next==null){
					stack2.push(temp2.right);
					stack1.push(temp1.right);
					if(temp2.next.getData()!=null && 
							missing.contains(temp2.next.getData())){
						DElmNode<DelayElement> node = new DElmNode<DelayElement>();
						node.setData(temp2.next.getData());
						node.prev = temp1;
						temp1.next = node;
						missing.remove(node.getData());
						stack2.push(temp2.next);
						stack1.push(temp1.next);
					}
				}
			}
			else if((temp2.getData()==null && temp2.right==null) && 
					(temp1.getData()==null && temp1.right==null)){
				if(temp2.next.getData()==null && temp1.next.getData()==null){
					stack2.push(temp2.next);
					stack1.push(temp1.next);
				}
				else if(temp2.next!=null && temp1.next==null){
					if(missing.contains(temp2.next.getData())){
						DElmNode<DelayElement> node = new DElmNode<DelayElement>();
						node.setData(temp2.next.getData());
						node.prev = temp1;
						temp1.next = node;
						missing.remove(node.getData());
						stack2.push(temp2.next);
						stack1.push(temp1.next);
					}
					else{
						origin = connection(origin,temp1,temp2.next.getData());
					}
				}
				else if(temp2.next!=null && temp1.next!=null && isEqual(temp2.next,temp1.next)){
					DElmNode<DelayElement> horizontal2 = temp2.next;
					DElmNode<DelayElement> horizontal1 = temp1.next;
					while(horizontal2!=null){
						if(horizontal2.right!=null && horizontal1.right==null){
							DElmNode<DelayElement> node = new DElmNode<DelayElement>();
							node.setData(horizontal2.getData());
							node.prev = horizontal1.prev;
							node.next = horizontal1.next;
							horizontal1.right = node;
							node.left = horizontal1;
							missing.remove(node.getData());
						}
						horizontal2 = horizontal2.right;
						horizontal1 = horizontal1.right;
					}
					stack2.push(temp2.next);
					stack1.push(temp1.next);
				}
			}
			else if(temp2.getData()!=null && temp1.getData()!=null 
					&& isEqual(temp2, temp1)){
				if(temp2.right!=null && temp1.right==null){
					DElmNode<DelayElement> horizontal = temp2.right;
					DElmNode<DelayElement> Left = temp1;
					while(horizontal!=null){
						DElmNode<DelayElement> node = new DElmNode<DelayElement>();
						node.setData(horizontal.getData());
						node.left = Left;
						Left.right = node;
						node.next = Left.next;
						node.prev = node.prev;
						Left = node;
						missing.remove(node.getData());
						horizontal = horizontal.right;
					}
				}
				if(temp2.next!=null && temp1.next==null){
					if(missing.contains(temp2.next.getData())){
						DElmNode<DelayElement> node = new DElmNode<DelayElement>();
						node.setData(temp2.next.getData());
						node.prev = temp1;
						temp1.next = node;
						missing.remove(node.getData());
						stack2.push(temp2.next);
						stack1.push(temp1.next);
					}
					else{
						origin = connection(origin,temp1,temp2.next.getData());
					}
				}
				else if(temp2.next!=null && temp1.next!=null &&
						isEqual(temp2.next, temp1.next)){
					DElmNode<DelayElement> horizontal2 = temp2.next;
					DElmNode<DelayElement> horizontal1 = temp1.next;
					while(horizontal2!=null){
						if(horizontal2.right!=null && horizontal1.right==null){
							DElmNode<DelayElement> node = new DElmNode<DelayElement>();
							node.setData(horizontal2.getData());
							node.prev = horizontal1.prev;
							node.next = horizontal1.next;
							node.left = horizontal1;
							horizontal1.right = node;
							missing.remove(node.getData());
						}
						horizontal2 = horizontal2.right;
						horizontal1 = horizontal1.right;
					}
					stack2.push(temp2.next);
					stack1.push(temp1.next);
				}
			}
			else if(temp2.getData()!=null && temp1.getData()==null){
				if(missing.contains(temp2.getData())){
					temp1.setData(temp2.getData());
					missing.remove(temp2.getData());
					stack2.push(temp2);
					stack1.push(temp1);
				}
			}
		}
	}
	
	public NewLinkedList connection(NewLinkedList origin, DElmNode<DelayElement> toBeConnected, DelayElement pivot){
		DElmNode<DelayElement> Next = find(origin,pivot);
		
		toBeConnected.next = Next;
		if(Next.prev==null)	Next.prev = toBeConnected;
		
		return origin;
	}
	
	public DElmNode<DelayElement> find(NewLinkedList list, DelayElement pivot){
		DElmNode<DelayElement> result = new DElmNode<DelayElement>();
		DElmNode<DelayElement> start = list.getHead();
		Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
		
		store.push(start);
		while(!store.isEmpty()){
			DElmNode<DelayElement> temp = store.pop();
			if(temp!=null && temp.getData()==null){
				store.push(temp.next);
				DElmNode<DelayElement> Rtemp = temp.right;
				while(Rtemp!=null){
					store.push(Rtemp);
					Rtemp = Rtemp.right;
				}
			}
			else if(temp!=null && temp.getData()!=null){
				if(	list.contains(temp, pivot)){
					result = temp;
					break;
				}
				else{
					store.push(temp.next);
				}
			}
		}
		return result;
	}
	
	public boolean isEqual(DElmNode<DelayElement> node1, DElmNode<DelayElement> node2){
		boolean result = false;
		if(node1.getData()==null && node2.getData()==null)	result = true;
		else if(node1.getData().isEqual(node2.getData()))	result = true;
		
		return result;
	}
	
	//Check!!!
	public void desideCurrent(NewLinkedList target, NewLinkedList origin, DelayElement pivot){
		DElmNode<DelayElement> start1 = target.getHead();
		DElmNode<DelayElement> start2 = origin.getHead();
		Stack<DElmNode<DelayElement>> stack1 = new Stack<DElmNode<DelayElement>>();
		Stack<DElmNode<DelayElement>> stack2 = new Stack<DElmNode<DelayElement>>();
		stack1.push(start1);
		stack2.push(start2);
		boolean done = false;
		while(!stack1.isEmpty() && !stack2.isEmpty() && !done){
			DElmNode<DelayElement> temp1 = stack1.pop();
			DElmNode<DelayElement> temp2 = stack2.pop();
			if(temp2!=null && temp1!=null){
				if((temp2.getData()==null && temp2.right!=null) && 
						(temp1.getData()==null && temp1.right!=null)){
					if(temp2.next!=null && temp1.next!=null){
						stack2.push(temp2.next);
						stack2.push(temp2.right);
						stack1.push(temp1.next);
						stack1.push(temp1.right);
					}
					else if(temp2.next!=null && temp1.next==null){
						if(temp2.next.getData()!=null && temp2.next.getData().isEqual(pivot)){
							target.setCurrent(temp1);
							DElmNode<DelayElement> node = new DElmNode<DelayElement>();
							temp1.next = node;
							node.prev = temp1;
							done = true;
							break;
						}
						else{
							stack2.push(temp2.right);
							stack1.push(temp1.right);
						}
					}
				}
				else if((temp2.getData()==null && temp2.right==null) && 
						(temp1.getData()==null && temp1.right==null)){
					if(temp2.next!=null && temp1.next==null &&
							temp2.next.getData()!=null){
						if(temp2.next.getData().isEqual(pivot)){
							target.setCurrent(temp1);
							DElmNode<DelayElement> node = new DElmNode<DelayElement>();
							temp1.next = node;
							node.prev = temp1;
							done = true;
							break;
						}
						else{
							stack2.push(temp2.next);
							stack1.push(temp1.next);
						}
					}
					else if(temp2.next!=null && temp1.next!=null &&
							temp2.next.getData()!=null && temp1.next.getData()!=null){
						if(temp2.next.getData().isEqual(temp1.next.getData())){
							stack2.push(temp2.next);
							stack1.push(temp1.next);
						}
					}
					else{
						stack2.push(temp2.next);
						stack1.push(temp1.next);
					}
				}
				else if(temp2.getData()!=null && temp1.getData()!=null &&
						isEqual(temp2, temp1)){
					if(temp2.next!=null && temp1.next==null){
						if(origin.contains(temp2.next, pivot)){
							target.setCurrent(temp1);
							DElmNode<DelayElement> node = new DElmNode<DelayElement>();
							temp1.next = node;
							node.prev = temp1;
							done = true;
						}
					}
					else if(temp2.next!=null && temp1.next!=null &&
							isEqual(temp2.next, temp1.next)){
						stack2.push(temp2.next);
						stack1.push(temp1.next);
					}
				}
				else if(temp2.getData()!=null && temp1.getData()==null &&
						temp2.getData().isEqual(pivot)){
					target.setCurrent(temp1.prev);
					done = true;
				}
			}
		}
	}
	
	//Combine primitive delay-sequences
	public NewLinkedList arrangeDelaySequence(NewLinkedList Common, List<NewLinkedList> dlist, boolean last){
		NewLinkedList result = new NewLinkedList();
		List<DelayElement> to = new Vector<DelayElement>();
		List<DelayElement> from = new Vector<DelayElement>();
		DElmNode<DelayElement> position = Common.getHead().next;
		DElmNode<DelayElement> Rposition = position;
		while(Rposition!=null){
			if(!to.contains(Rposition.getData()))	to.add(Rposition.getData());
			Rposition = Rposition.right;
		}
		
		for(int n=0;n<dlist.size();n++){
			if(dlist.get(n).contains(to)){
				//NewLinkedList temp = dlist.get(n).subSeq1(to);
				result = combineParallel(result,dlist.get(n).subSeq1(to),true);
				//addIrrelevant(result,temp);
			}
		}
		
		if(result.isEmpty()){
			result.sequentialInsertion(to.get(0));
		}
		else{
			if(last){
				desideCurrent(result,dlist.get(1),to.get(0));
			}
			result.getCurrent().next.setData(to.get(0));
			result.setCurrent(result.getCurrent().next);
		}
		for(int i=1;i<to.size();i++)	result.parallelInsertion(to.get(i));
		//Set other dummy-tail nodes 
		DElmNode<DelayElement> settingDummy1 = result.getCurrent();
		while(settingDummy1!=null){
			settingDummy1.dummyTail = true;
			settingDummy1 = settingDummy1.right;
		}
		
		position = position.next;
		Rposition = position;
		from.addAll(to);
		to.clear();
		while(position!=null){
			while(Rposition!=null){
				if(!to.contains(Rposition.getData()))	to.add(Rposition.getData());
				Rposition = Rposition.right;
			}
			
			NewLinkedList Construction = new NewLinkedList();
			for(int i=0;i<dlist.size();i++){
				if(dlist.get(i).contains(to) && dlist.get(i).contains(from)){
					//NewLinkedList temp = dlist.get(i).subSeq2(from, to);
					Construction = combineParallel(Construction,dlist.get(i).subSeq2(from, to),true);
					//addIrrelevant(Construction,temp);
				}
			}
			//Construction.getCurrent() could be null
			if(Construction.isEmpty()){
				Construction.sequentialInsertion(to.get(0));
			}
			else{
				Construction.getCurrent().next.setData(to.get(0));
				Construction.setCurrent(Construction.getCurrent().next);
			}
			for(int k=1;k<to.size();k++)	Construction.parallelInsertion(to.get(k));
			//Set other dummy-tail nodes 
			DElmNode<DelayElement> settingDummy2 = Construction.getCurrent();
			while(settingDummy2!=null){
				settingDummy2.dummyTail = true;
				settingDummy2 = settingDummy2.right;
			}
			
			 
			DElmNode<DelayElement> Tail = result.getCurrent();
			Tail.next = Construction.getHead().next;
			Construction.getHead().next.prev = Tail;
			//Set "next" of dummy-tail nodes
			Tail = Tail.right;
			while(Tail!=null){
				Tail.next = Construction.getHead().next;
				Tail = Tail.right;
			}
			result.setCurrent(Construction.getCurrent());
			
			position = position.next;
			Rposition = position;
			from.clear();
			from.addAll(to);
			to.clear();
		}
		
		NewLinkedList lastPart = new NewLinkedList();
		for(int p=0;p<dlist.size();p++){
			if(dlist.get(p).contains(from)){
				//NewLinkedList temp = dlist.get(p).subSeq3(from);
				lastPart = combineParallel(lastPart, dlist.get(p).subSeq3(from),false);
				//addIrrelevant(lastPart,temp);
			}
		}
		DElmNode<DelayElement> lastTail = new DElmNode<DelayElement>();
		if(!lastPart.isEmpty()){
			lastTail = result.getCurrent(); 
			lastTail.next = lastPart.getHead().next;
			lastPart.getHead().next.prev = lastTail;
		}
		
		if(last)	addIrrelevant(result, dlist.get(1));
		return result;
	}
	
	
	public NewLinkedList combineParallel(NewLinkedList origin, NewLinkedList list1, boolean addingTail){
		if(list1.isEmpty())	;
		else if(origin.isEmpty()){
			origin=NewLinkedList.copy(list1);
			//origin=list1;
			if(addingTail){
				DElmNode<DelayElement> dummyT = new DElmNode<DelayElement>();
				dummyT.dummyTail = true;
				origin.getCurrent().next = dummyT;
				dummyT.prev = origin.getCurrent();
			}
		}
		else if(origin.getHead().next.getData()!=null){
			DElmNode<DelayElement> newHead = new DElmNode<DelayElement>();
			newHead.prev = origin.getHead();
			newHead.next = origin.getHead().next;
			newHead.next.prev = newHead;
			newHead.prev.next = newHead;
			newHead.right = list1.getHead();
			list1.getHead().left = newHead;
			list1.getHead().prev = origin.getHead();
			list1.getCurrent().next = origin.getCurrent().next;
		}
		else{
			list1.getHead().prev = origin.getHead();
			list1.getHead().right = origin.getHead().next;
			origin.getHead().next.left = list1.getHead();
			origin.getHead().next = list1.getHead();
			list1.getCurrent().next = origin.getCurrent().next;
			/*list1.getHead().left = origin.getHead().next;
			list1.getHead().right = origin.getHead().next.right;
			list1.getHead().prev = origin.getHead();
			list1.getHead().left.right = list1.getHead();
			list1.getHead().right.left = list1.getHead();
			list1.getCurrent().next = origin.getCurrent().next;*/
		}
		
		return origin;
	}
	
	
	public List<DelayElement> getAllElements(DElmNode<DelayElement> current){
		List<DelayElement> elements = new Vector<DelayElement>();
		if(current==null) ;
		else{
			Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
			store.push(current);
			while(!store.isEmpty()){
				DElmNode<DelayElement> temp = store.pop();
				if(temp!=null){
					if(temp.getData()==null && temp.right!=null){
						store.push(temp.next);
						List<DelayElement> tempList = getAllElements(temp.right);
						for(DelayElement e : tempList)
							if(!elements.contains(e)) elements.add(e);
					}
					else if(temp.getData()==null && temp.right==null){
						List<DelayElement> tempList = getAllElements(temp.next);
						for(DelayElement e : tempList)
							if(!elements.contains(e))	elements.add(e);
					}
					else if(temp.getData()!=null){
						while(temp!=null){
							if(temp.getData()==null){
								store.push(temp);
								break;
							}
							if(!elements.contains(temp.getData()))	elements.add(temp.getData());
							DElmNode<DelayElement> horizontal = temp.right;
							while(horizontal!=null){
								if(!elements.contains(horizontal.getData()))	elements.add(horizontal.getData());
								horizontal = horizontal.right;
							}
							temp = temp.next;
						}
					}
				}
			}
		}
		return elements;
	}
	
	public boolean setCommon(DElmNode<DelayElement> current, DelayElement item){
		Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
		boolean parallel = false;
		store.push(current);
		while(!store.isEmpty()){
			DElmNode<DelayElement> temp = store.pop();
			if(temp!=null){
				if(temp.getData()==null && temp.right!=null){
					store.push(temp.next);
					parallel = setCommon(temp.right,item);
				}
				else if(temp.getData()==null && temp.right==null){
					parallel = setCommon(temp.next, item);
				}
				else if(temp.getData()!=null){
					boolean found = false;
					while(temp!=null && !found){
						if(temp.getData()==null){
							store.push(temp);
							break;
						}
						if(temp.getData().isEqual(item)){
							temp.setCommon(true);
							found = true; 
							break;
						}
						else{
							DElmNode<DelayElement> horizontal = temp;
							while(horizontal!=null){
								boolean tempParallel = false;
								if(horizontal.getCommon())	tempParallel = true;
								if(horizontal.getData().isEqual(item)){
									horizontal.setCommon(true);
									found = true;
									if(tempParallel) parallel = true;
									break;
								}
								horizontal = horizontal.right;
							}
						}
						temp = temp.next;
					}
				}
			}
		}
		return parallel;
	}
	
	public DElmNode<DelayElement> getPosition(NewLinkedList list, DelayElement item){
		DElmNode<DelayElement> position = list.getHead().next;
		Stack<DElmNode<DelayElement>> store = new Stack<DElmNode<DelayElement>>();
		boolean done = false;
		store.push(position);
		while(!store.isEmpty() && !done){
			DElmNode<DelayElement> temp = store.pop();
			if(temp!=null){
				if(temp.getData()==null && temp.right!=null){
					store.push(temp.next);
					store.push(temp.right);
				}
				else if(temp.getData()==null && temp.right==null){
					store.push(temp.next);
				}
				else if(temp.getData()!=null){
					while(temp!=null && !done){
						if(temp.getData()!=null && list.contains(temp,item)){
							position = temp;
							done = true;
							break;
						}
						temp = temp.next;
					}
				}
			}
		}
				
		return position;
	}
	
	public List<DelayElement> commonDI(NewLinkedList list1, NewLinkedList list2, NewLinkedList comStore){
		List<DelayElement> com1 = new Vector<DelayElement>();
		List<DelayElement> com2 = new Vector<DelayElement>();
		
		DElmNode<DelayElement> Current1 = list1.getHead();
		DElmNode<DelayElement> Current2 = list2.getHead();		
		
		com1.addAll(getAllElements(Current1));
		com2.addAll(getAllElements(Current2));
		
		//Get common DI
		List<DelayElement> com = new Vector<DelayElement>();
		for(DelayElement a : com1){
			if(com2.contains(a))	com.add(a);
		}
		
		//Mark common DI in list1 and list2 
		Current1 = list1.getHead().next;
		Current2 = list2.getHead().next;
		for(DelayElement b : com){
			boolean parallel = false;
			parallel = setCommon(Current1,b);
			
			//Add common DI to comStore
			DElmNode<DelayElement> settingPosition = comStore.getHead();
			boolean alreadyExists = false;
			while(settingPosition!=null){
				if(comStore.contains(settingPosition,b)){
					alreadyExists = true;
					break;
				}
				settingPosition = settingPosition.next;
			}
			if(!alreadyExists){
				if(parallel) comStore.parallelInsertion(b); 
				else{
					//TODO So far, I considered the comStore with only one head (most of time, it's enough), 
					// but there is possibility for the comStore to have more than one head. Consider this case. 				
					comStore.sequentialInsertion(b);
				}
			}
			
			//Add DIs (in list1) having the same order as common DI's to comStore
			DElmNode<DelayElement> position = new DElmNode<DelayElement>();
			DElmNode<DelayElement> findingLeftmost = comStore.getCurrent();
			while(findingLeftmost!=null){
				if(findingLeftmost.left==null){
					position = findingLeftmost;
					comStore.setCurrent(position);
				}
				findingLeftmost = findingLeftmost.left;
			}
						
			DElmNode<DelayElement> RCurrent1 = new DElmNode<DelayElement>();
			RCurrent1 = getPosition(list1,b);
			
			while(RCurrent1!=null){
				if(!comStore.contains(position,RCurrent1.getData())){
					if(!alreadyExists)	comStore.parallelInsertion(RCurrent1.getData());
					else{
						DElmNode<DelayElement> newCommon1 = new DElmNode<DelayElement>();
						newCommon1.setData(RCurrent1.getData());
						newCommon1.right = settingPosition;
						settingPosition.left = newCommon1;
						newCommon1.prev = settingPosition.prev;
						newCommon1.next = settingPosition.next;
						settingPosition.prev.next = newCommon1;
						if(settingPosition.next!=null)	settingPosition.next.prev = newCommon1;
					}
				}
				RCurrent1 = RCurrent1.right;
			}
			
			setCommon(Current2, b);
			
			//Add DIs (in list2) having the same order as common DI's
			DElmNode<DelayElement> RCurrent2 = new DElmNode<DelayElement>();
			RCurrent2 = getPosition(list2, b);
			while(RCurrent2!=null){
				if(!comStore.contains(position, RCurrent2.getData())){
					if(!alreadyExists)	comStore.parallelInsertion(RCurrent2.getData());
					else{
						DElmNode<DelayElement> newCommon2 = new DElmNode<DelayElement>();
						newCommon2.setData(RCurrent2.getData());
						newCommon2.right = settingPosition;
						settingPosition.left = newCommon2;
						newCommon2.prev = settingPosition.prev;
						newCommon2.next = settingPosition.next;
						settingPosition.prev.next = newCommon2;
						if(settingPosition.next!=null)	settingPosition.next.prev = newCommon2;
					}
				}
				RCurrent2 = RCurrent2.right;
			}
		}
		
		return com;
	}
	
	public List<DelayElement> getNext(List<DelayElement> DI, List<DelayElement> pre){
		List<DelayElement> result = new Vector<DelayElement>();
		List<String> output = new Vector<String>();
		List<String> input = new Vector<String>();
		
		for(DelayElement a : pre){
			for(String b : a.getOutput()){
				StringTokenizer temp1 = new StringTokenizer(b,",");
				while(temp1.hasMoreTokens()){
					output.add(temp1.nextToken());
				}
			}
		}
		
		for(DelayElement c : DI){
			for(String d : c.getInput()){
				StringTokenizer temp2 = new StringTokenizer(d,",");
				while(temp2.hasMoreTokens()){
					input.add(temp2.nextToken());
				}
			}
			if(hasCommon(input,output))	result.add(c);
			input.clear();
		}
		
		return result;
	}
	
	public List<DelayElement> getEdgeInput(List<DelayElement> list){
		List<DelayElement> result = new Vector<DelayElement>();
		List<String> output = new Vector<String>();
		for(int j=0;j<list.size();j++){
			for(String a : list.get(j).getOutput()){
				StringTokenizer tempString = new StringTokenizer(a,",");
				
				while(tempString.hasMoreTokens()){
					String element = tempString.nextToken().trim();
					output.add(element);
				}
			}
		}
		
		for(int i=0;i<list.size();i++){
			List<String> input = new Vector<String>();
			for(String b : list.get(i).getInput()){
				StringTokenizer temp = new StringTokenizer(b,",");
				
				while(temp.hasMoreTokens()){
					String element = temp.nextToken().trim();
					input.add(element);
				}
			}
			if(!hasCommon(list.get(i).getInput(), output)) result.add(list.get(i));
			//if(!input.isEmpty() && !hasCommon(list.get(i).getInput(),output))	result.add(list.get(i));
		}
		
		return result;
	}
		
	public boolean hasCommon(List<String> list1, List<String> list2){
		boolean result = true;
		List<String> input = new Vector<String>();
		for(String temp : list1){
			StringTokenizer inputTokens = new StringTokenizer(temp,",");
			while(inputTokens.hasMoreTokens()){
				input.add(inputTokens.nextToken());
			}
		}
		
		HashSet<String> Input = new HashSet<String>(input);
		HashSet<String> Output = new HashSet<String>(list2);
		
		Input.retainAll(Output);
		
		if(Input.isEmpty())	result = false;

		return result;
	}
}