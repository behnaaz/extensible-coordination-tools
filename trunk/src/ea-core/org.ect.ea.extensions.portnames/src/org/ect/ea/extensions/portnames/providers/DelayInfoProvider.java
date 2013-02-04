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
package org.ect.ea.extensions.portnames.providers;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.StringTokenizer;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.graphics.Color;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.ITextualExtensionProvider;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInformation;
import org.ect.ea.util.IValidationResult;

public class DelayInfoProvider implements ITextualExtensionProvider {

	//The ID of the extensions
	public static final String EXTENSION_ID = "cwi.ea.extensions.portNames.delayInformation";
	public static final String EXTENSION_NAME = "delayInformation";	
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.TEXTUAL, 
			ExtensionDefinition.TRANSITIONS, new DelayInfoProvider());

	
	public IExtension createDefaultExtension(IExtendible owner) {
		//to create an empty delay information
		return new DelayInformation();
	}

	public IExtension createSilentExtension(Transition owner) {
		return  createDefaultExtension(owner);
	}
	
	public boolean isSilentExtension(IExtension extension) {
		DelayInformation delay = (DelayInformation) extension;
		boolean result = true;
		for(DelayElement a : delay.getElements()){
			if(!a.getInput().isEmpty()  || !a.getOutput().isEmpty())	result = false;
		}
		return result;
	}
		
	public String editExtension(IExtension extension) {
		DelayInformation info = (DelayInformation) extension;
		EList<DelayElement> ListofElements = info.getElements();
		String elementsprinting = " ";
		String input = " ";
		String output = " ";
		Double delay = 0.0;
		String rewards = " ";
		
		if(!ListofElements.isEmpty()){
			int i=0;
			
			while(i<ListofElements.size()){
				String temp=null;
				String inputtemp=null;
				String outputtemp=null;
				String rewardstemp=null;
				DelayElement element=ListofElements.get(i++);
				
				// to make looking good
				inputtemp=element.getInput().toString();
				inputtemp=inputtemp.substring(1, inputtemp.length()-1);
				outputtemp=element.getOutput().toString();
				outputtemp=outputtemp.substring(1, outputtemp.length()-1);
				rewardstemp=element.getRewards().toString();
				rewardstemp=rewardstemp.substring(1, rewardstemp.length()-1);
				
				input = "{".concat(inputtemp).concat("}"); 
				output = "{".concat(outputtemp).concat("}");
				rewards = "[".concat(rewardstemp).concat("]");
				delay = element.getDelay();
				
				temp="(".concat(input).concat(",").concat(output).concat(",").concat(delay.toString()).concat(",").concat(rewards).concat(")");
				elementsprinting=elementsprinting.concat(temp).concat(",");
			}
			elementsprinting=elementsprinting.substring(0, elementsprinting.length()-1);
			return "{"+elementsprinting+"}";
		}
		else return "{({"+input+"},{"+output+"},"+ delay+",[0.0]"+")}";
	}

	public String printExtension(IExtension extension) {
		return editExtension(extension);
	}

	public IExtension parseExtension(String value, IExtendible owner) throws ParseException {
		DelayInformation DelayInfo = new DelayInformation();
		int lastEnd = value.lastIndexOf("}");
		value=value.substring(0, lastEnd-1);
		StringTokenizer token = new StringTokenizer(value,")");
		String element = null;
		
		// Parse DelayInformation for each delay element
		while(token.hasMoreTokens()){
			element=token.nextToken();
												
			// Check individual input, output, delay, and rewards
			StringTokenizer subToken1 = new StringTokenizer(element,"}");
			String input = subToken1.nextToken(); 
			String output = subToken1.nextToken();
			String tempdelay = subToken1.nextToken();
			String temprewards=" ";
			boolean rewardExists = false;
			StringTokenizer subToken2 = new StringTokenizer(tempdelay,"[");
			if(subToken2.countTokens()==2){
				rewardExists = true;
				tempdelay = subToken2.nextToken();
				tempdelay = tempdelay.substring(1);
				temprewards = subToken2.nextToken();
			}
						
			int startPoint1 = input.lastIndexOf("{"); 
			int startPoint2 = output.indexOf("{"); 
			int startPoint3 = 0;
			if(!rewardExists)	startPoint3=tempdelay.indexOf(",");
						
			StringListExtension Input = StringListExtension.parse(input.substring(startPoint1+1));
			StringListExtension Output = StringListExtension.parse(output.substring(startPoint2+1));
			if(rewardExists){
				tempdelay = tempdelay.substring(startPoint3, tempdelay.indexOf(","));
   			    temprewards = temprewards.substring(0, temprewards.indexOf("]"));
			}
			else{
				tempdelay=tempdelay.substring(startPoint3+1);
			}
			Double Delay = valueParsing(tempdelay); 
			String Rewards = rewardsParsing(temprewards);
			
			Input.trimAll(); 
			Output.trimAll();
			if(rewardExists)	Rewards.trim();
			
			//adding elements
			DelayElement Elements =  new DelayElement();
			Elements.getInput().addAll(Input.getValues());
			Elements.getOutput().addAll(Output.getValues());
			Elements.setDelay(Delay);
			if(rewardExists) Elements.getRewards().add(Rewards);
			DelayInfo.getElements().add(Elements);
		}
		//for(DelayElement a : DelayInfo.getElements())	System.out.println(a.toString());
		return DelayInfo;
	}

	public String rewardsParsing(String rewards) throws ParseException{
		if(!rewards.contains(",") && (rewards.length()==0 || (rewards.length()==1 && rewards.equals(" "))))	return rewards;
		else{
			StringTokenizer value = new StringTokenizer(rewards, ",");
			String result=" ";
			
			while(value.hasMoreTokens()){
				Double reward = valueParsing(value.nextToken());
				result=result.concat(reward.toString()).concat(",");
			}
			result = result.substring(0, result.length()-1);
			result=result.trim();
			return result;
		}
	}
	
	public Double valueParsing(String value) throws ParseException{
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
	
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {
		EList<IExtension> result = new BasicEList<IExtension>();
		DelayInformation delay1 = (DelayInformation) x1;
		DelayInformation delay2 = (DelayInformation) x2;
		DelayInformation joined = new DelayInformation();
				
		joined = DelayInformation.join(delay1, delay2);
		result.add(joined);
		
		/*boolean collectionNeeded = false; 
		List<String> newMixed = PortNamesProductConditions.getNewMixed((Transition)delay1.getOwner(), (Transition)delay2.getOwner());
		if(!newMixed.isEmpty()){
			CollectingMixed c1 = new CollectingMixed((Transition) delay1.getOwner(), newMixed);
			CollectingMixed c2 = new CollectingMixed((Transition) delay2.getOwner(), newMixed);
			
			List<Transition> Clist1 = c1.getCollected();
			List<Transition> Clist2 = c2.getCollected();
			for(Transition temp1 : Clist1){
				IntensionalPortNames ports1 = (IntensionalPortNames) temp1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				for(Transition temp2 : Clist2){
					IntensionalPortNames ports2 = (IntensionalPortNames) temp2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					
					boolean hasmixed1 = false;
					boolean hasmixed2 = false;
					for(String a : ports1.getRequests()){
						if(newMixed.contains(a))	hasmixed1 = true;
					}
					for(String b : ports2.getRequests()){
						if(newMixed.contains(b))	hasmixed2 = true;
					}
					
					collectionNeeded = hasmixed1 && hasmixed2;
					if(collectionNeeded) {
						DelayInformation DI1 = (DelayInformation) temp1.findExtension(DelayInfoProvider.EXTENSION_ID);
						DelayInformation DI2 = (DelayInformation) temp2.findExtension(DelayInfoProvider.EXTENSION_ID);
						joined = DelayInformation.join(DI1, DI2);	
						result.add(joined);
					}
					
				}
			}
		}
				
		if(!collectionNeeded){
			joined = DelayInformation.join(delay1, delay2);
			result.add(joined);
		}*/
		  
		return result;
	}

	// to validate input and output port names.
	public IValidationResult validateExtension(IExtension x) {
		return ((DelayInformation) x).validate();
	}

	public boolean isReadOnly(IExtension extension) {
		return false;
	}

	public Color getFontColor(IExtension extension) {
		return ColorConstants.black;
	}

}
