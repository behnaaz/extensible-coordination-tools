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
package org.ect.codegen.reo2ea.qia.properties;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.StringTokenizer;

import org.ect.reo.PropertyHolder;
import org.ect.reo.util.PropertyHelper;

/**
 * Static methods for accessing reward properties.
 */
public class RewardProperties{
	
	public static final String REWARDS_KEY = "rewards";
	
	public static String parseRewards(String value){
		String result = "0.0";
		
		StringTokenizer token = new StringTokenizer(value, "|");
		while(token.hasMoreTokens()){
			String r = token.nextToken();
			r=r.trim();
			double values[];
			values = parseRewardformat(r);
			if(result.equals("0.0")){
				Double a = values[0];
				result = a.toString();
						
				for(int i=1;i<values.length;i++){
					result = result.concat(",");
					Double b = values[i];
					result = result.concat(b.toString());
				}
			}
			else{
				for(int j=0;j<values.length;j++){
					Double b = values[j];
					result = result.concat(b.toString()).concat(",");
				}
				result = result.substring(0, result.length()-1);
			}
				//result = result.concat(value.toString());
			result = result.concat("|");
		}
		result = result.substring(0, result.length()-1);
		
		return result;
	}
	public static double[] parseRewardformat(String value){
		double[] rewards;
		try{
			rewards = rewardsParsing(value);
			return rewards;
		}catch(ParseException e){
			System.err.println("Wrong format for rewards");
			return new double[0];
		}
	}
	
	public static String printRewards(double[] rewards) {
		String result=" ";
		
		for(Double value:rewards){
			result=result.concat(value.toString()).concat(",");
		}
				
		result = result.substring(0, result.length()-1);
		result=result.trim();
		
		return result;
	}
		
	public static String getRewards(PropertyHolder element){
		String value = PropertyHelper.getFirstValue(element, REWARDS_KEY); 
		return (value!=null) ? parseRewards(value) : "0.0";
	}
	
	public static void setReward(PropertyHolder element, String value) {
		PropertyHelper.setOrRemove(element, REWARDS_KEY, value);
	}
	/*public static double[] getRewards(PropertyHolder element){
		String value = PropertyHelper.getFirstValue(element, REWARDS_KEY); // this might be null!
		return (value!=null) ? parseRewards(value) : new double[0];
	}
	
	public static void setReward(PropertyHolder element, double[] rewards) {
		String value = printRewards(rewards);
		PropertyHelper.setOrRemove(element, REWARDS_KEY, value);
	}*/
	
	public static double[] rewardsParsing(String rewards) throws ParseException{
		if(!rewards.contains(",") && (rewards.length()==0 || (rewards.length()==1 && rewards.equals(" "))))	return new double[0];
		else if(rewards.indexOf(",")==0){
			double reward = valueParsing(rewards);
			double fakeResult[] = new double[1];
			fakeResult[0] = reward;
			
			return fakeResult;
		}
		else{
			StringTokenizer value = new StringTokenizer(rewards, ",");
			int j = value.countTokens();
			int i = 0;
			double[] result = new double[j];
			
			while(value.hasMoreTokens()){
				double reward = valueParsing(value.nextToken());
				result[i++] = reward; 
			}
			return result;
		}
	}
	
	public static Double valueParsing(String value) throws ParseException{
		Double result = new Double(0);
		String temp = null; 
	
		MessageFormat format = new MessageFormat("{0,number,#.#}",Locale.ENGLISH);
		Object[] parsed = format.parse(value);
		temp=parsed[0].toString();
		if(temp.indexOf(".")<=0)
		{
			result=new Double(temp.concat(".0"));
		}
		else	result = (Double) parsed[0];
		return new Double(result);
	}
	
}
