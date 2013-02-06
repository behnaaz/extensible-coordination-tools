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
package org.ect.codegen.ea2mc.genPRISMfile;

import java.sql.Timestamp;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.codegen.ea2mc.EATranslation;
import org.ect.codegen.ea2mc.providers.Preprocessings;
import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.automata.State;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInfoProvider;
import org.ect.ea.extensions.portnames.DelayInformation;

public class GenerationPRISMinput {
	public Automaton automaton;
	
	public GenerationPRISMinput(Automaton MC){
		automaton = MC;
	}
	public String compute(){
		Automaton copy = EA.copyAutomaton(automaton);
		Preprocessings.compute(copy,0);

		StringTemplateGroup output = EATranslation.INSTANCE.templates.getGroup("Prism");
		StringTemplate template = output.getInstanceOf("MC");
		String modelName = "Connector";
				
		template.setAttribute("name", modelName);
		template.setAttribute("states", copy.getStates());
		template.setAttribute("transitions", copy.getTransitions());
		
		List<String> delayLabel = new Vector<String>();
		List<String> listingDelayNames = new Vector<String>();
		List<DelayElement> listingDelays = new Vector<DelayElement>();
		List<String> delayFunc = new Vector<String>();
		//Temporary state label. Later it needs to be interactive with users.
		/*List<String> running1 = new Vector<String>();
		List<String> running2 = new Vector<String>();
		List<String> idle1 = new Vector<String>();
		List<String> idle2 = new Vector<String>();*/
		
		
		List<State> visitedState = new Vector<State>();
		Integer num = 0;
		for(Transition a : copy.getTransitions()){
			
			DelayInformation DI = (DelayInformation) a.findExtension(DelayInfoProvider.EXTENSION_ID);
			DelayElement DE = DI.getElements().get(0);
			DelayElement temp = (DelayElement)EcoreUtil.copy(DE);
			 			
			String name = "d";
			for(String b : DE.getInput()){
				StringTokenizer Itoken = new StringTokenizer(b,",");
				while(Itoken.hasMoreTokens())	name = name.concat(Itoken.nextToken());
			}
			for(String c : DE.getOutput()){
				StringTokenizer Otoken = new StringTokenizer(c,",");
				while(Otoken.hasMoreTokens())	name = name.concat(Otoken.nextToken());
			}
			delayLabel.add(name);
			/*if(name.equals("dC5Tran")){
				delayFunc.add("start1");
				if(!running1.contains(a.getTarget().getName())) running1.add(a.getTarget().getName());
			}
			else if(name.equals("dC7Tran")){
				delayFunc.add("start2");
				if(!running2.contains(a.getTarget().getName())) running2.add(a.getTarget().getName());
			}
			else if(name.equals("dM12C2") || name.equals("dM10C3")){
				delayFunc.add("finish1");
				if(!idle1.contains(a.getTarget().getName())) idle1.add(a.getTarget().getName());
			}
			else if(name.equals("dM22C11") || name.equals("dM20C10")){
				delayFunc.add("finish2");
				if(!idle2.contains(a.getTarget().getName())) idle2.add(a.getTarget().getName());
			}
			else{
				delayFunc.add(num.toString());
				if(running1.contains(a.getSource().getName()) &&  !idle1.contains(a.getTarget().getName()) && !running1.contains(a.getTarget().getName())){	
					running1.add(a.getTarget().getName());
				}
				if(running2.contains(a.getSource().getName()) &&  !idle2.contains(a.getTarget().getName()) && !running2.contains(a.getTarget().getName()))	
					running2.add(a.getTarget().getName());
			}*/
			DE.setName(name);
			
			boolean exists = false;
			for(DelayElement d : listingDelays){
				if(d.isEqual(DE))	exists = true;
			}
			if(!exists){
				listingDelays.add(temp);
				listingDelayNames.add(name);
			}
			num++;
		}
		
		//Get the number of rewards
		int rewardSize = getRewardNum(copy);
		List<List<String>> rewardLabels = new Vector<List<String>>();
		int Rnum = 0;
		for(Transition c : copy.getTransitions()){
			DelayInformation DI = (DelayInformation) c.findExtension(DelayInfoProvider.EXTENSION_ID);
			DelayElement DE = DI.getElements().get(0);
			DelayElement temp = (DelayElement)EcoreUtil.copy(DE);
			
			//getting Rewards
			State source = c.getSource();
			if(!visitedState.contains(source)){
				visitedState.add(source);
				List<String> tempRewards = new Vector<String>(rewardSize);
				if(source.getOutgoing().size()==1){ 
					int index = 0;
					if(temp.getRewards().size()!=rewardSize){
						for(String Rtemp : temp.getRewards()){
							StringTokenizer token = new StringTokenizer(Rtemp, ",");
							while(token.hasMoreTokens()){
								String Value = token.nextToken();
								tempRewards.add(index++,Value);
							}
						}
					}
					else{
						for(String Rtemp : temp.getRewards()){
							tempRewards.add(index++,Rtemp);
						}
					}
					for(int i=0;i<rewardSize;i++){
						List<String> orderedLabel = new Vector<String>();
						if(tempRewards.isEmpty())	orderedLabel.add("0.0"); 
						else orderedLabel.add(tempRewards.get(i));
						if(Rnum==0)	rewardLabels.add(i,orderedLabel);
						else{
							if(tempRewards.isEmpty())	rewardLabels.get(i).add("0.0");
							else rewardLabels.get(i).add(tempRewards.get(i));
						}
						
					}
					tempRewards.clear();
				}
				else{
					String DelaySum="#";
					List<String> ProbSum = new Vector<String>();
					
					int outgoings = source.getOutgoing().size();
					int count = 0;
					for(Transition b : source.getOutgoing()){
						DelayInformation DItemp = (DelayInformation) b.findExtension(DelayInfoProvider.EXTENSION_ID);
						DelayElement DEtemp = DItemp.getElements().get(0);
						if(DelaySum.equals("#"))	DelaySum = DEtemp.getName().concat("+");	
						else	DelaySum = DelaySum.concat(DEtemp.getName()+"+");
						int index = 0;
						if(DEtemp.getRewards().size() != rewardSize){
							if(DEtemp.getRewards().isEmpty()){
								for(int k=0;k<rewardSize;k++)	tempRewards.add(k,"0.0");
							}
							else{
								for(String Rtemp : DEtemp.getRewards()){
									StringTokenizer token = new StringTokenizer(Rtemp, ",");
									while(token.hasMoreTokens()){
										String Value = token.nextToken();
										tempRewards.add(index++,Value);
									}
								}
							}
						}
						else{
							for(String Rtemp : DEtemp.getRewards()){
								tempRewards.add(index++,Rtemp);
							}
						}
						for(int j=0;j<tempRewards.size();j++){
							String element="#";
							if(!tempRewards.get(j).equals("0.0")){
								element = tempRewards.get(j).concat("*"+ DEtemp.getName());
							}
							if(count<outgoings-1 && 
									(ProbSum.size()==0 || 
											(ProbSum.size()!=0 && ProbSum.get(j).equals("#")))){
								if(element.equals("#"))	ProbSum.add(j, "#");
								else	ProbSum.add(j, "(".concat(element));
							}
							else if(count==outgoings-1){
								if(!element.equals("#")){
									if(ProbSum.get(j).equals("#"))	ProbSum.set(j,element);
									else{
										String end = ProbSum.get(j).concat("+"+element+")");
										ProbSum.set(j, end);
									}
								}
								else{
									if(!ProbSum.get(j).equals("#")){
										String end = ProbSum.get(j).concat(")");
										ProbSum.set(j, end);
									}
								}
							}
							else{
								if(!element.equals("#")){
									if(ProbSum.get(j).equals("#"))	ProbSum.set(j,element);
									else{
										String added = ProbSum.get(j).concat("+"+element);
										ProbSum.set(j, added);
									}
								}
							}
						}
						tempRewards.clear();
						count++;
					}
					
					DelaySum = DelaySum.substring(0, DelaySum.length()-1);
					List<String> rewards = new Vector<String>(rewardSize);
					for(int k=0;k<rewardSize;k++){
						/*if(ProbSum.isEmpty()){
							System.out.println("Rnum: "+Rnum);
						}*/
						if(ProbSum.get(k)=="#")	rewards.add(k, "-1");
						else	rewards.add(k, ProbSum.get(k).concat("/").concat("("+DelaySum+")"));
						
						List<String> orderedLabel = new Vector<String>();
						orderedLabel.add(rewards.get(k));
						if(Rnum==0)	rewardLabels.add(k, orderedLabel);
						else	rewardLabels.get(k).add(rewards.get(k));
						
					}
				}
				Rnum++;
			}
		}
		
		List<Boolean> calculatable = new Vector<Boolean>();
		calculatable = isReasonable(rewardLabels);
		boolean exists = exists(calculatable);
		List<String> rewardNames = new Vector<String>();
		char name = 'a';
		for(int l=0;l<rewardSize;l++){
			rewardNames.add(Character.toString(name));
			name++;
		} 
		/*System.out.println("run1: "+running1.size()+"\t run2: "+running2.size());
		running2.removeAll(running1);
		for(String n: running1)	System.out.print("s="+ n +" | ");
		for(String m: running2) System.out.print("s="+ m +" | ");*/
		
		template.setAttribute("delays", listingDelays);
		template.setAttribute("listingDelayNames", listingDelayNames);
		template.setAttribute("delayNames", delayLabel);
		template.setAttribute("func", delayFunc);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		template.setAttribute("timestamps", time.toString());
		template.setAttribute("existingRewards", exists);
		template.setAttribute("showingReward", calculatable);
		template.setAttribute("rewardNames",rewardNames);
		template.setAttribute("Rstate", visitedState);
		template.setAttribute("rewards",rewardLabels);
		//Temporary setting
		//template.setAttribute("RunningA", running1);
		//template.setAttribute("RunningB", running2);
		String line = template.toString();

		return line;
	}
	
	public boolean exists(List<Boolean> list){
		boolean result = false;
		for(boolean a : list){
			if(a){
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	public List<Boolean> isReasonable(List<List<String>> valueList){
		List<Boolean> result = new Vector<Boolean>();
		if(valueList!=null){
			List<String> list = valueList.get(0);
			if(list!=null){
				for(int i=0;i<list.size();i++){
					if(!list.get(i).equals("-1") && !list.get(i).equals("0.0"))	result.add(true);
					else result.add(false);
				}
			}
		}
		
		return result;
	}
	
	public int getRewardNum(Automaton automaton){
		int result=0;
		List<Transition> list = automaton.getTransitions();
		Transition t = list.get(0);
		DelayInformation DI = (DelayInformation) t.findExtension(DelayInfoProvider.EXTENSION_ID);
		DelayElement DE = DI.getElements().get(0); 
		for(String value: DE.getRewards()){
			StringTokenizer token = new StringTokenizer(value, ",");
			result = result + token.countTokens();
		}
		
		return result;
	}
	
	public double getValue(String value){
		double result=0.0;
		
		result = Double.parseDouble(value);
		System.out.println(result);
		return result;
	}
}
