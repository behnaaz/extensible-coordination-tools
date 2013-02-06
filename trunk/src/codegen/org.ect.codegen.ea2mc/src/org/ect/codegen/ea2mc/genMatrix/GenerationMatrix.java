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
package org.ect.codegen.ea2mc.genMatrix;

import java.util.List;
import java.util.StringTokenizer;

import org.ect.codegen.ea2mc.providers.Preprocessings;
import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInfoProvider;
import org.ect.ea.extensions.portnames.DelayInformation;

public class GenerationMatrix {
	
	public Automaton MC;
	public static String newline = System.getProperty("line.separator");
	
	public GenerationMatrix(Automaton automaton){
		MC = automaton;
	}
	
	public String compute(){
		Automaton copy = EA.copyAutomaton(MC);
		Preprocessings.compute(copy,0);
		
		
		String [][] delays;
		int size = copy.getStates().size();
		delays = new String[size][size];
		List<State> states = copy.getStates();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				Double defaultValue = new Double(0.0); 
				delays[i][j]=defaultValue.toString();
			}
		}
		for(State a : states){
			String source = a.getName();
			int i = decideIndex(size, source);
			List<Transition> transitions = a.getOutgoing();
			for(Transition b : transitions){
				String target = b.getTarget().getName();
				int j = decideIndex(size, target);
				double delay = 0.0;
				String name;
				DelayInformation DI = (DelayInformation) b.findExtension(DelayInfoProvider.EXTENSION_ID);
				DelayElement DE = DI.getElements().get(0);
				delay = DE.getDelay();
				if(delay==0.0){
					name = "d";
					for(String c : DE.getInput()){
						StringTokenizer Itoken = new StringTokenizer(c,",");
						while(Itoken.hasMoreTokens())	name = name.concat(Itoken.nextToken());
					}
					for(String d : DE.getOutput()){
						StringTokenizer Otoken = new StringTokenizer(d,",");
						while(Otoken.hasMoreTokens())	name = name.concat(Otoken.nextToken());
					}
					delays[i][j] = name;
				}
				else{
					Double value = new Double(delay);
					delays[i][j] =  value.toString();
				}
			}
		}
		
		return makingBufferedLine(delays,size);
	}
	
	public String makingBufferedLine(String [][] delays, int range){
		String line="";
		for(int i=0;i<range;i++){
			for(int j=0;j<range;j++){
				line = line.concat(delays[i][j]);
				line = line.concat(",");
			}
			int lastIndex = line.lastIndexOf(",");
			line = line.substring(0, lastIndex);
			line = line.concat(newline);
		}
		
		return line;
	}
	
	
	public int decideIndex(int range, String name){
		int index = 0;
		for(int i=0;i<range;i++){
			Integer temp = new Integer(i);
			String tempIndex = temp.toString();
			if(tempIndex.equals(name))	
				index = i; 
		}
		
		return index;
	}
}
