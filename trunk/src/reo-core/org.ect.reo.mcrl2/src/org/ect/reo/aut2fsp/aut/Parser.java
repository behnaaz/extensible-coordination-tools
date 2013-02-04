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
package org.ect.reo.aut2fsp.aut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @generated NOT
 * @author Behnaz Changizi
 */
public class Parser {

	@SuppressWarnings("unused")
	public static final boolean DEBUG_MODE = true || !System.getProperty("java.vm.info", "").contains("sharing");

	int transno, statno;
	String start;
	
	public void parse(String line, AUT aut) {
		if (line.startsWith("des ")){
			getGeneralInfo(line);
		}
		else{
			getTransition(line, aut);
		}
	}

	private void getTransition(String input, AUT aut) {
		Transition trans=new Transition();	
		trans.setSource(aut.AddState(new State(Integer.parseInt(input.substring(input.indexOf('(')+1, input.indexOf(','))))));
		trans.setTarget(aut.AddState(new State(Integer.parseInt(input.substring(input.lastIndexOf(',')+1, input.indexOf(')'))))));
		trans.setName(input.substring(input.indexOf('\"')+1, input.lastIndexOf('\"')));
		aut.AddTransition(trans);
		if (DEBUG_MODE)
			System.out.println("++trans "+trans.getSource().getName()+trans.getName()+trans.getTarget().getName());
	}

	public  void getGeneralInfo(String input)
	{
		int cnt=1;
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			String someNumberStr = matcher.group();

			int someNumberInt = Integer.parseInt(someNumberStr);
			if (cnt==1){
				start = someNumberStr;	    	
			}
			else if (cnt==2){
				transno = someNumberInt;
			}
			else if (cnt==3){
				statno = someNumberInt;
			}
			cnt++;
			//  System.out.println("++m"+someNumberInt);
		}
		if (DEBUG_MODE)
			System.out.println("....start"+start+" tran#"+transno+" stat#"+statno);
	}


	public  void makeTransition(String input)
	{
		int cnt=1;
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			String someNumberStr = matcher.group();

			int someNumberInt = Integer.parseInt(someNumberStr);
			if (cnt==1){
				start = someNumberStr;	    	
			}
			else if (cnt==2){
				transno = someNumberInt;
			}
			else if (cnt==3){
				statno = someNumberInt;
			}
			if (DEBUG_MODE)
				System.out.println("++m"+someNumberInt);
		}
	}

}
