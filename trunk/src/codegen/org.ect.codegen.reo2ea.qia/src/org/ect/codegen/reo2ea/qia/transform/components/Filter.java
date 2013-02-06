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
package org.ect.codegen.reo2ea.qia.transform.components;

import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import java.util.List;
import java.util.Vector;

import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.qia.util.Product;
import org.ect.codegen.reo2ea.qia.util.QIA;
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
import org.ect.reo.Node;
import org.ect.reo.Primitive;
import org.ect.reo.Component;
import org.ect.reo.PrimitiveEnd;

public class Filter implements ITransformation<Component>{
	private AutomataFactory caFactory = 	AutomataFactory.eINSTANCE;
	private EndNamingPolicy endNames;
	
	public Automaton transform(Component component){
		Automaton automaton = caFactory.createAutomaton();
		automaton.setName("SEL "+ component.getName());
		automaton.getUsedExtensionIds().add(StartStateExtensionProvider.EXTENSION_ID);
		automaton.getUsedExtensionIds().add(IntensionalPortNamesProvider.EXTENSION_ID);
		automaton.getUsedExtensionIds().add(DelayInfoProvider.EXTENSION_ID); 
		automaton.getUsedExtensionIds().add(AutomatonPortNamesProvider.EXTENSION_ID);
		EA.monitorExtensions(automaton, true);
		List<String> extensions = automaton.getUsedExtensionIds();
		
		List<Automaton> outgoings = new Vector<Automaton>();
		for(int i=0;i<component.getSinkEnds().size();i++){	
			PrimitiveEnd sinkEnd = component.getSinkEnds().get(i);
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
			
			String s1;
			if(sinkEnd.getNode()==null)	s1 = sinkEnd.getName();
			else	s1 = sinkEnd.getNode().getName();
			
			ip1.getRequests().add(s1);
			QIA.addPortName(t1, s1, PortType.OUT_PORT);
			DE1.getOutput().add(s1);
			DE1.setDelay(0.0);
			
			PrimitiveEnd sourceEnd = component.getSourceEnds().get(0);
			Automaton temp2 = new Automaton();
			temp2.getUsedExtensionIds().addAll(extensions);
			EA.monitorExtensions(temp2, true);
			
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
			
			String s2; 
			if(sourceEnd.getNode()==null)	s2 = sourceEnd.getName();
			else	s2 = sourceEnd.getNode().getName();
			
			ip2.getRequests().add(s2);
			QIA.addPortName(t2, s2, PortType.IN_PORT);
			DI2.getElements().add(DE2);
			DE2.getInput().add(s2);
			DE2.setDelay(0.0);
			
			
			for(int j=0;j<component.getSinkEnds().size();j++){
				if(j!=i){
					PrimitiveEnd sink = component.getSinkEnds().get(j);
					Transition t3 = new Transition();
					temp2.getTransitions().add(t3);
					t3.setSource(b2);
					t3.setTarget(a2);
					IntensionalPortNames ip3 = (IntensionalPortNames) t3.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					DelayInformation DI3 = (DelayInformation) t3.findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayElement DE3 = new DelayElement();
					
					String s3; 
					if(sink.getNode()==null)	s3 = sink.getName();
					else s3 = sink.getNode().getName();
					
					ip3.getFirings().add(s3);
					ip3.getFirings().add(s2);
					DI3.getElements().add(DE3);
					DE3.getInput().add(s2);
					DE3.getOutput().add(s3);
					if(!component.getProcessingDelay().isEmpty() && component.getProcessingDelay().size() > j)	DE3.setDelay(component.getProcessingDelay().get(j));
					else DE3.setDelay(0.0);
				}
			}
						
			Automaton p1 = Product.compute(temp2, temp1);
			
			State initial = getInitialState(p1);
			State destination = getFurthestState(initial);
			Transition last = new Transition();
			last.setAutomaton(p1);

			IntensionalPortNames ip3 = (IntensionalPortNames) last.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			DelayInformation DI3 = (DelayInformation) last.findExtension(DelayInfoProvider.EXTENSION_ID);
			DelayElement DE3 = new DelayElement();
			
			last.setSource(destination);
			last.setTarget(initial);
			ip3.getFirings().add(s1);
			ip3.getFirings().add(s2);
			DI3.getElements().add(DE3);
			DE3.getInput().add(s2);
			DE3.getOutput().add(s1);
			if(!component.getProcessingDelay().isEmpty() && component.getProcessingDelay().size() > 0)	DE3.setDelay(component.getProcessingDelay().get(0));
			else	DE3.setDelay(0.0);
			
			
			outgoings.add(p1);
		}
	/*	for(int i=0;i<node.getSourceEnds().size();i++){	
			PrimitiveEnd sourceEnd = node.getSourceEnds().get(i);
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
			
			String s1 = sourceEnd.getName();
			ip1.getRequests().add(s1);
			QIA.addPortName(t1, s1, PortType.OUT_PORT);
			DE1.getOutput().add(s1);
			DE1.setDelay(0.0);
			
			PrimitiveEnd sinkEnd = node.getSinkEnds().get(0);
			Automaton temp2 = new Automaton();
			temp2.getUsedExtensionIds().addAll(extensions);
			EA.monitorExtensions(temp2, true);
			
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
			String s2 = sinkEnd.getName();
			ip2.getRequests().add(s2);
			QIA.addPortName(t2, s2, PortType.IN_PORT);
			DI2.getElements().add(DE2);
			DE2.getInput().add(s2);
			DE2.setDelay(0.0);
			
			
			for(int j=0;j<node.getSourceEnds().size();j++){
				if(j!=i){
					PrimitiveEnd source = node.getSourceEnds().get(j);
					Transition t3 = new Transition();
					temp2.getTransitions().add(t3);
					t3.setSource(b2);
					t3.setTarget(a2);
					IntensionalPortNames ip3 = (IntensionalPortNames) t3.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					DelayInformation DI3 = (DelayInformation) t3.findExtension(DelayInfoProvider.EXTENSION_ID);
					DelayElement DE3 = new DelayElement();
					String s3 = source.getName();
					ip3.getFirings().add(s3);
					ip3.getFirings().add(s2);
					DI3.getElements().add(DE3);
					DE3.getInput().add(s2);
					DE3.getOutput().add(s3);
					if(!node.getProcessingDelay().isEmpty() && node.getProcessingDelay().size() > j)	DE3.setDelay(node.getProcessingDelay().get(j));
					else DE3.setDelay(0.0);
				}
			}
						
			Automaton p1 = Product.compute(temp2, temp1);
			
			State initial = getInitialState(p1);
			State destination = getFurthestState(initial);
			Transition last = new Transition();
			last.setAutomaton(p1);

			IntensionalPortNames ip3 = (IntensionalPortNames) last.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			DelayInformation DI3 = (DelayInformation) last.findExtension(DelayInfoProvider.EXTENSION_ID);
			DelayElement DE3 = new DelayElement();
			
			last.setSource(destination);
			last.setTarget(initial);
			ip3.getFirings().add(s1);
			ip3.getFirings().add(s2);
			DI3.getElements().add(DE3);
			DE3.getInput().add(s2);
			DE3.getOutput().add(s1);
			if(!node.getProcessingDelay().isEmpty() && node.getProcessingDelay().size() > 0)	DE3.setDelay(node.getProcessingDelay().get(0));
			else	DE3.setDelay(0.0);
			
			
			outgoings.add(p1);
		}*/
		
		automaton = outgoings.remove(0);
		for(int index=0;index<outgoings.size();index++){
			Automaton temp = outgoings.get(index);
			automaton = Product.compute(automaton, temp);
		}
		
		System.err.println(getClass()+":Translated node " + component.getName() + " to " + QIA.prettyPrint(automaton));
		
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
	
	public void setEndNamingPolicy(EndNamingPolicy endName) {
		this.endNames = endNames;
	}
}
