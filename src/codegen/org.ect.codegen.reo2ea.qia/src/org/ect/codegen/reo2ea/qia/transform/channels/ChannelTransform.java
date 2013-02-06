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
package org.ect.codegen.reo2ea.qia.transform.channels;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.qia.properties.RewardProperties;
import org.ect.codegen.reo2ea.qia.util.QIA;
import org.ect.codegen.reo2ea.qia.util.QIARefactoring;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;

import org.ect.ea.automata.Automaton;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.channels.Channel;

public class ChannelTransform implements ITransformation<Channel>  {
	private static final String SOURCE="SOURCE", SINK="SINK";
	protected final static Map<String, Automaton> primitives = new HashMap<String, Automaton>();

	protected Channel channel;
//	private char stateSuffix='z';
	private EndNamingPolicy endNames;
	
	public Automaton transform(Channel channel) {
		this.channel = channel;
		
		Automaton copy = (Automaton) EcoreUtil.copy(
				primitives.get(channel.getClass().getName()));
		
		List<String> input = new Vector<String>();
		int i=0;
		for (PrimitiveEnd sourceEnd: channel.getSourceEnds()){ 
			String sourceName = endNames.getName(sourceEnd);
			QIARefactoring.renamePortName(SOURCE+ i++, sourceName, copy);

			//Set arrival rate for source nodes
			if(sourceEnd.getNode().getArrivalRate().isEmpty())	QIARefactoring.setArrivalRate(0.0, sourceName, copy);
			else
				QIARefactoring.setArrivalRate(sourceEnd.getNode().getArrivalRate().get(0),sourceName,copy);
			//Set rewards if any exists
			if(!sourceEnd.getNode().getProperties().isEmpty() && sourceEnd.getNode().getProperties().get(0).getKey().equals("rewards")){
				QIARefactoring.setArrivalRewards(sourceEnd.getNode().getProperties().get(0).getValue(),sourceName,copy);
			}
			else	QIARefactoring.setArrivalRewards("0.0", sourceName, copy);
			
			if(!input.contains(sourceName))	input.add(sourceName);
		}
		
		i=0;
		for (PrimitiveEnd sinkEnd: channel.getSinkEnds()){ 
			String sinkName = endNames.getName(sinkEnd);
			QIARefactoring.renamePortName(SINK+ i++, sinkName, copy);
			
			// Set arrival rate for sink nodes
			if(sinkEnd.getNode().getArrivalRate().isEmpty())	QIARefactoring.setArrivalRate(0.0, sinkName, copy);
			else
				QIARefactoring.setArrivalRate(sinkEnd.getNode().getArrivalRate().get(0),sinkName,copy);
			
			//Set rewards if any exists
			if(!sinkEnd.getNode().getProperties().isEmpty() && sinkEnd.getNode().getProperties().get(0).getKey().equals("rewards")){
				QIARefactoring.setArrivalRewards(sinkEnd.getNode().getProperties().get(0).getValue(),sinkName,copy);
			}
			else	QIARefactoring.setArrivalRewards("0.0", sinkName, copy);
		}

		//Set processing delay on the transition and rewards
		if(channel.getName().equals("Sync") || channel.getName().equals("SyncDrain") || channel.getName().equals("SyncSpout")){
			if(!channel.getProcessingDelay().isEmpty())	
				QIARefactoring.setProcessingDelay(channel.getProcessingDelay().get(0), copy);
			else	QIARefactoring.setProcessingDelay(0.0, copy);
			
			if(!channel.getProperties().isEmpty() && channel.getProperties().get(0).getKey().equals("rewards"))
				QIARefactoring.setProcessingRewards(0, channel.getProperties().get(0).getValue(), copy);
			else	QIARefactoring.setProcessingRewards(0, "0.0", copy);
		}
		else {
			if(!channel.getProcessingDelay().isEmpty())	
				QIARefactoring.setProcessingDelay(channel.getName(),input.get(0), channel.getProcessingDelay(), copy);
			else{	
				List<Double> values = new Vector<Double>();
				values.add(0.0); values.add(0.0);
				QIARefactoring.setProcessingDelay(channel.getName(),input.get(0),values, copy);
			}
			
			if(!channel.getProperties().isEmpty() && channel.getProperties().get(0).getKey().equals("rewards")){
				String tempValue = channel.getProperties().get(0).getValue();
				StringTokenizer token = new StringTokenizer(tempValue, "|");
				if(token.countTokens()==2)	QIARefactoring.setProcessingRewards(1, channel.getProperties().get(0).getValue(), copy);
				else	QIARefactoring.setProcessingRewards(1, "0.0|0.0", copy);
				//QIARefactoring.setProcessingRewards(1, channel.getProperties().get(0).getValue(), copy);
			}
			else{
				System.out.println(channel.getProperties());
				QIARefactoring.setProcessingRewards(1, "0.0|0.0", copy);
			}
		}
		/*for (State s : copy.getStates()) 
			mangleMemory(s);*/
		
		System.err.println("translated channel " + channel + " to "+QIA.prettyPrint(copy));
		return copy;
	}

	public void setEndNamingPolicy(EndNamingPolicy endNames) {
		this.endNames = endNames;
	}
	
	public static void setTemplates(Collection<Automaton> templates) {		
		for (Automaton a : templates) {
				String classNames = a.getName();
				primitives.put(classNames, a);				
				
				System.err.println("Loaded primitive " + a.getName());
		} 
	}
}
