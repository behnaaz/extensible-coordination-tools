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
package org.ect.codegen.reo2ea.qia.transform.nodes;

import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import java.util.List;
import java.util.Vector;

import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.qia.util.Product;
import org.ect.codegen.reo2ea.qia.util.QIA;
import org.ect.codegen.reo2ea.qia.util.QIARefactoring;
import org.ect.codegen.reo2ea.qia.util.QIA.PortType;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;

import org.ect.ea.EA;
import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInfoProvider;
import org.ect.ea.extensions.portnames.DelayInformation;
import org.ect.ea.extensions.portnames.IntensionalPortNames;
import org.ect.ea.extensions.portnames.IntensionalPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.reo.Component;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;

public class Replicate implements ITransformation<Node> {
	private AutomataFactory caFactory = 	AutomataFactory.eINSTANCE;
	private EndNamingPolicy endNames;

	public Automaton transform(Node node) {
		if (!(node.getSinkEnds().size()>1) && !(node.getSourceEnds().size()>1))
				return IDENITIY;
		
		Automaton automaton = caFactory.createAutomaton();		
		if(node.getSinkEnds().size()<2 && node.getSourceEnds().size() > 1 && 
				(!node.getProcessingDelay().isEmpty() && node.getProcessingDelay().size()!=1))
			throw new IllegalArgumentException("Not a proper processing delay to "+ node.getName());
		else if(node.getSinkEnds().size()>1 && !node.getProcessingDelay().isEmpty() && 
				node.getProcessingDelay().size() > node.getSinkEnds().size())
			throw new IllegalArgumentException("Not proper processing delays to " + node.getName());
		else{
			automaton.setName("REPL "+ node2PortName(node));
			automaton.getUsedExtensionIds().add(StartStateExtensionProvider.EXTENSION_ID);
			automaton.getUsedExtensionIds().add(IntensionalPortNamesProvider.EXTENSION_ID);
			automaton.getUsedExtensionIds().add(DelayInfoProvider.EXTENSION_ID); 
			automaton.getUsedExtensionIds().add(AutomatonPortNamesProvider.EXTENSION_ID);
			EA.monitorExtensions(automaton, true);
			List<String> extensions = automaton.getUsedExtensionIds();

			if(!node.getSinkEnds().isEmpty() && !node.getSourceEnds().isEmpty()){
				/**
				 * Note to self: sink and source is w.r.t _channels_!
				 * 					C
				 * (sink) A - O <	  (sources)
				 * 					D
				 * one replicator
				 * 
				 *       A
				 * (sink) > O - C (source)
				 *       B
				 * one merger
				 * 
				 * 	   	  A	    C 
				 * (sinks) > O <  (sources)
				 *  	  B	    D
				 *  
				 *  one merger + one replicator
				 */
				boolean boundaryIn = false;
				boolean boundaryOut = false;
				List<Automaton> outgoings = new Vector<Automaton>();
				List<String> sources = new Vector<String>();
				for(PrimitiveEnd source : node.getSourceEnds())		sources.add(endNames.getName(source));
				for(int m=0;m<node.getSinkEnds().size();m++){
					PrimitiveEnd sinkEnd = node.getSinkEnds().get(m);
					List<String> sinks1 = new Vector<String>();
					Automaton temp1 = new Automaton();
					temp1.getUsedExtensionIds().addAll(extensions);
					EA.monitorExtensions(temp1, true);
					State a1 = new State();
					State b1 = new State();
					Transition t1 = new Transition();
					temp1.getStates().add(a1);
					temp1.getStates().add(b1);
					temp1.getTransitions().add(t1);
					

					IntensionalPortNames ip1 = (IntensionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					DelayInformation DI1 = (DelayInformation) t1.findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayElement DE1 = new DelayElement();
					
					DI1.getElements().add(DE1);
					QIA.setStartState(a1, true);
					t1.setSource(a1);
					t1.setTarget(b1);
					String s1 = endNames.getName(sinkEnd);
					ip1.getRequests().add(s1);
					sinks1.add(s1);
					QIA.addPortName(t1, s1, PortType.IN_PORT);
					DE1.getInput().add(s1);
					if(!boundaryOut && sinkEnd.getPrimitive() instanceof Component){
						boundaryIn = true;
						if(!node.getArrivalRate().isEmpty() && node.getArrivalRate().get(m)!=null)
							DE1.setDelay(node.getArrivalRate().get(m));
						else DE1.setDelay(0.0);
						
						DE1.getRewards().add("0.0");
					}
					else{
						DE1.setDelay(0.0);
						DE1.getRewards().add("0.0");
					}
					
					
					List<Automaton> outgoings1 = new Vector<Automaton>();
					for(int i=0;i<node.getSourceEnds().size();i++){
						PrimitiveEnd sourceEnd = node.getSourceEnds().get(i);
						Automaton temp2 = new Automaton();
						outgoings1.add(temp2);
						temp2.getUsedExtensionIds().addAll(extensions);
						EA.monitorExtensions(temp2, true);
						
						//Make transition of (s1,sourceEnd,empty,s2)
						State a2 = new State();
						State b2 = new State();
						Transition t2 = new Transition();
						temp2.getStates().add(a2);
						temp2.getStates().add(b2);
						temp2.getTransitions().add(t2);

						
						IntensionalPortNames ip2 = (IntensionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
						DelayInformation DI2 = (DelayInformation) t2.findExtension(DelayInfoProvider.EXTENSION_ID);
						DelayElement DE2 = new DelayElement();
						
						QIA.setStartState(a2, true);
						t2.setSource(a2);
						t2.setTarget(b2);
						String s2 = endNames.getName(sourceEnd);
						ip2.getRequests().add(s2);
						QIA.addPortName(t2, s2, PortType.OUT_PORT);
						DI2.getElements().add(DE2);
						DE2.getOutput().add(s2);
						if(!boundaryIn && sourceEnd.getPrimitive() instanceof Component){
							boundaryOut = true;
							if(!node.getArrivalRate().isEmpty() && node.getArrivalRate().get(i)!=null)
								DE2.setDelay(node.getArrivalRate().get(i));
							else	DE2.setDelay(0.0);
							
							DE2.getRewards().add("0.0");
						}
						else	DE2.setDelay(0.0);
						
						//Make transitions of (s1,empty,{\forall sourceEnd}U{node.getSinkEnd().get(l)| l!=m},s2)
						for(int l=0;l<node.getSinkEnds().size();l++){
							if(l!=m){
								PrimitiveEnd sink = node.getSinkEnds().get(l); 
								Transition t3 = new Transition();
								temp2.getTransitions().add(t3);
								t3.setSource(b2);
								t3.setTarget(a2);
								IntensionalPortNames ip3 = (IntensionalPortNames) t3.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
								DelayInformation DI3 = (DelayInformation) t3.findExtension(DelayInfoProvider.EXTENSION_ID);
								DelayElement DE3 = new DelayElement();
								String s3 = endNames.getName(sink);
								ip3.getFirings().add(s3);
								ip3.getFirings().addAll(sources);
								DI3.getElements().add(DE3);
								DE3.getInput().add(s3);
								DE3.getOutput().addAll(sources);
								if(!node.getProcessingDelay().isEmpty() && node.getProcessingDelay().get(l)!=null)	
									DE3.setDelay(node.getProcessingDelay().get(l));
								else DE3.setDelay(0.0);
								DE3.getRewards().add("0.0");
							}
						}
					}
					Automaton p1 = outgoings1.remove(0);
					for(int k=0;k<outgoings1.size();k++){
						Automaton p2 = outgoings1.get(k);
						p1 = Product.compute(p1, p2);
					}
						
					p1 = Product.compute(p1, temp1);
					
					State initial = getInitialState(p1);
					State destination = getFurthestState(initial);
					Transition last = new Transition();
					last.setAutomaton(p1);

					IntensionalPortNames ip3 = (IntensionalPortNames) last.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					DelayInformation DI3 = (DelayInformation) last.findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayElement DE3 = new DelayElement();
					
					last.setSource(destination);
					last.setTarget(initial);
					ip3.getFirings().addAll(sinks1);
					ip3.getFirings().addAll(sources);
					DI3.getElements().add(DE3);
					DE3.getInput().addAll(sinks1);
					DE3.getOutput().addAll(sources);
					if(!node.getProcessingDelay().isEmpty() && node.getProcessingDelay().get(m)!=null)	
						DE3.setDelay(node.getProcessingDelay().get(m));
					else	DE3.setDelay(0.0);
					DE3.getRewards().add("0.0");
					outgoings.add(p1);
				}
				
				automaton = outgoings.remove(0);
				for(int index=0;index<outgoings.size();index++){
					Automaton temp = outgoings.get(index);
					automaton = Product.compute(automaton, temp);
				}
				
				System.err.println(getClass()+":Translated node " + node2PortName(node) + " to " + QIA.prettyPrint(automaton));
			}
		}
		return automaton;
	}
	
	public State getInitialState(Automaton automaton){
		State result = new State();
		
		for(State init : automaton.getStates()){
			if(((BooleanExtension)init.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue()==true){
				result = init;
				break;
			}
		}
		return result;	
	}
	
	public State getFurthestState(State initial){
		boolean keepGoing = false;
		State next = new State();
		
		for(Transition t1 : initial.getOutgoing()){
			IntensionalPortNames ip1 = (IntensionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			if((ip1.getFirings()==null || ip1.getFirings().isEmpty()) && !ip1.getRequests().isEmpty()){
				next = t1.getTarget();
				for(Transition t2 : next.getOutgoing()){
					IntensionalPortNames ip2 = (IntensionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					if((ip2.getFirings()==null || ip2.getFirings().isEmpty()) && !ip2.getRequests().isEmpty()){
						keepGoing = true;
						break;
					}
				}
				break;
			}
		}
		
		if(keepGoing) return getFurthestState(next);
		else 	return next;
	}

	public void setEndNamingPolicy(EndNamingPolicy endNames) {
		this.endNames = endNames;
	}
}
