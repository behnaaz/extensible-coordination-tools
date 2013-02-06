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
package org.ect.codegen.reo2constraints.generator;

public class State {
	
	private String fifosState = "";
	//Timer Status: 0:OFF 1:ON 2:ON and TimeOUT
	private String timersState = "";
	
	public String getFifosState() {
		return fifosState;
	}

	public String getTimersState() {
		return timersState;
	}
	//TODO
	public State(String todo, String FIFOsState, String timersState){
		this.fifosState = FIFOsState;
		this.timersState = timersState;
	}
	public String toString(){
		return fifosState+timersState;
	}
	
	//TODO Name of memory cells
	public String getName() {
		if (fifosState.length()+timersState.length()==0)
			return "";
		else if (timersState.length()==0){
			String name = "";
			for (int i=0; i<fifosState.length(); i++){
				if (fifosState.charAt(i) == '1')
					name += getName(i);
			}
		}
		else if (fifosState.length()==0){
			return "";
		}
		return "";
	}
//TODO 
	private String getName(int i) {
		if (i==0)
			return "x";
		if (i==1)
			return "y";
		if (i==2)
			return "z";
		return null;
	}
}
