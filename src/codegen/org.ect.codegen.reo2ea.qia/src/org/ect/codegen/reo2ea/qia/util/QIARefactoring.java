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

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.ea.automata.*;
import org.ect.ea.extensions.constraints.*;
import org.ect.ea.extensions.portnames.*;

public class QIARefactoring {
	
	/**
	 * Rename a port name in an automaton. This updates
	 * all references of this port name in the
	 * automaton, including the port names fields.
	 */
	public static void renamePortName(String oldName, String newName, Automaton automaton) {
		rename(oldName, newName, QIA.getPortNames(automaton).getValues());
		for (Transition transition : automaton.getTransitions()) {
			//Rename request port names
			rename(oldName, newName, QIA.getPortNames(transition).getRequests());
			//Rename firing port names
			rename(oldName, newName, QIA.getPortNames(transition).getFirings());
			//Rename port names of delay information
			DelayInformation DI = (DelayInformation) transition.findExtension(DelayInfoProvider.EXTENSION_ID);
			List<DelayElement> list =  DI.getElements();
			for(int i=0;i<list.size();i++){
				//Rename input port names of delay information
				rename(oldName, newName, QIA.getDelayPortNames(transition,i).getInput());
				//Rename output port names of delay information
				rename(oldName, newName, QIA.getDelayPortNames(transition,i).getOutput());
			}
			
/*			//Rename input port names of delay information
			rename(oldName, newName, QIA.getDelayPortNames(transition).getInput());
			//Rename output port names of delay information
			rename(oldName, newName, QIA.getDelayPortNames(transition).getOutput());
			//renameElement(oldName, newName, QIA.getConstraint(transition));
*/		}
	}
	/**
	 * Set an arrival rate on transitions in an automaton. 
	 */
	public static void setArrivalRate(Double value, String portName, Automaton automaton){
		for(Transition transition : automaton.getTransitions()){
			IntensionalPortNames Iports = (IntensionalPortNames) transition.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			if(Iports.getFirings() == null || Iports.getFirings().isEmpty()){
				DelayInformation DI = (DelayInformation) transition.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayElement DE = DI.getElements().get(0);
				if(DE.getInput() != null && !DE.getInput().isEmpty() && DE.getInput().get(0).equals(portName))	DE.setDelay(value);
				else if(DE.getOutput() != null && !DE.getOutput().isEmpty() && DE.getOutput().get(0).equals(portName))	DE.setDelay(value);
				
			}
		}
	}
	
	/**
	 * Set a processing delay on transitions in an automaton.
	 */
	// Simple channels such as Sync, SyncDrain, SyncSpout
	public static void setProcessingDelay(Double value, Automaton automaton){
		for(Transition transition : automaton.getTransitions()){
			IntensionalPortNames Iports = (IntensionalPortNames) transition.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			if((Iports.getRequests() == null || Iports.getRequests().isEmpty()) && !Iports.getFirings().isEmpty()){
				DelayInformation DI = (DelayInformation) transition.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayElement DE = DI.getElements().get(0);
				DE.setDelay(value);
			}
		}
	}
	// LossySync and FIFO
	public static void setProcessingDelay(String name, String input, List<Double> delays, Automaton automaton){
		for(Transition transition : automaton.getTransitions()){
			IntensionalPortNames Iports = (IntensionalPortNames) transition.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			if((Iports.getRequests() == null || Iports.getRequests().isEmpty()) && !Iports.getFirings().isEmpty()){
				DelayInformation DI = (DelayInformation) transition.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayElement DE = DI.getElements().get(0);
				if(Iports.getFirings().size()==1 && Iports.getFirings().get(0).equals(input)){
					if(name.equals("LossySync")) {
/*						if(!delays.isEmpty() && delays.size()>=2)	DE.setDelay(delays.get(1));
						else	DE.setDelay(0.0);
*/						if(!delays.isEmpty() && delays.size()>=2)	DE.setDelay(delays.get(1));
						else	DE.setDelay(0.0);

					}
					else if(name.equals("FIFO")) {
						if(!delays.isEmpty())	DE.setDelay(delays.get(0));
						else	DE.setDelay(0.0);
					}
				}
				else{
					if(name.equals("LossySync")){
						/*if(!delays.isEmpty())	DE.setDelay(delays.get(0));
						else	DE.setDelay(0.0);*/
						if(!delays.isEmpty())	DE.setDelay(delays.get(0));
						else	DE.setDelay(0.0);
					}
					else if(name.equals("FIFO")) {
						if(!delays.isEmpty() && delays.size()>=2)	DE.setDelay(delays.get(1));
						//if(!delays.isEmpty())	DE.setDelay(delays.get(1));
						else	DE.setDelay(0.0);
					}
				}
			}
		}
	}
	/**
	 * Set rewards for data-arrivals, it any is given in its properties.  
	 */
	public static void setArrivalRewards(String value, String portName, Automaton automaton){
		for(Transition a : automaton.getTransitions()){
			IntensionalPortNames Iports = (IntensionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			if(Iports.getFirings()==null || Iports.getFirings().isEmpty()){
				DelayInformation DI = (DelayInformation) a.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayElement DE = DI.getElements().get(0);
				if(DE.getInput() != null && !DE.getInput().isEmpty() && DE.getInput().get(0).equals(portName)){
					StringTokenizer token = new StringTokenizer(value, ",");
					while(token.hasMoreTokens()){
						String r = token.nextToken();
						DE.getRewards().add(r);
					}
				}
				else if(DE.getOutput()!=null && !DE.getOutput().isEmpty() && DE.getOutput().get(0).equals(portName)){
					StringTokenizer token = new StringTokenizer(value, ",");
					while(token.hasMoreTokens()){
						String r = token.nextToken();
						DE.getRewards().add(r);
					}
				}
			}
		}
	}
	
	/**
	 * Set rewards for processing, it any is given in its properties.
	 * @Type (integer)
	 *  0 : simple channels such as Sync, SyncDrain, SyncSpout
	 *  1 : LossySync or FIFO1
	 */
	public static void setProcessingRewards(int Type, String value, Automaton automaton){
		for(Transition a : automaton.getTransitions()){
			IntensionalPortNames Iports = (IntensionalPortNames) a.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
			if((Iports.getRequests()==null || Iports.getRequests().isEmpty()) && !Iports.getFirings().isEmpty()){
				DelayInformation DI = (DelayInformation) a.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayElement DE = DI.getElements().get(0);
				if(Type == 0){
					List<String> temp = new Vector<String>();
					temp.add(value);
					DE.getRewards().addAll(temp);
				}
				else if(Type == 1){
					StringTokenizer token = new StringTokenizer(value, "|");
					List<String> temp = new Vector<String>();
					while(token.hasMoreTokens()){
						String b = token.nextToken();
						temp.add(b);						
					}
					
					if(!DE.getInput().isEmpty() && (DE.getOutput().isEmpty() || DE.getOutput().get(0).equals(""))){
						DE.getRewards().add(temp.get(0));
					}
					else if(!DE.getOutput().isEmpty() && !DE.getOutput().get(0).equals("")){
//						List<String> d = new Vector<String>();
//						d.add(temp.get(1));
						DE.getRewards().add(temp.get(1));
					}
					System.out.println(DE.getRewards().toString());
				}
			}
		}
	}
	
	/**
	 * Renames state memory in an automaton. This updates
	 * all references to the buffer name in the 
	 * automaton, including the port names fields.
	 */
	/*public static void renameStateMemory(String oldName, String newName, State state) {
		rename(oldName, newName, CA.getAllMemoryCells(state.getAutomaton()));
		rename(oldName, newName, CA.getMemoryCells(state).getValues());
		
		for (Transition transition : state.getIncoming()) 
			renameElement(oldName, newName, CA.getConstraint(transition));
		
		for (Transition transition : state.getOutgoing()) 
			renameElement(oldName, newName, CA.getConstraint(transition));
	}*/
	
	
	protected static void rename(String oldName, String newName, List<String> names) {
		assert names!=null;
		
		ListIterator<String> it = (ListIterator<String>) names.listIterator();	
		while (it.hasNext()) 
			if (it.next().equals(oldName))
				it.set(newName);
	}
	
	
	public static void renameElement(String oldName, String newName, Constraint constraint) {
		assert constraint!=null; 
		Iterator<EObject> elements = constraint.eAllContents();
		while (elements.hasNext()) {
			EObject e = elements.next();
			if (e instanceof Parameter && 
					((Parameter) e).getValue().equals(oldName) ) 
				((Parameter) e).setValue(newName);							
		}		
	}	

	public static void renameElement(Constraint constraint, Map<Parameter, Parameter> replacements) {
		assert constraint!=null; 
		Iterator<EObject> elements = constraint.eAllContents();
		while (elements.hasNext()) {
			EObject e = elements.next();
			if (e instanceof Parameter && replacements.containsKey(e)) 
				EcoreUtil.replace(e, 
						EcoreUtil.copy(replacements.get(e)));							
		}		
	}	
}

