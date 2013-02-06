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
package org.ect.codegen.ea2mc.providers;

import java.util.List;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;

public class Preprocessings {

	public static void compute(Automaton automaton,int index){
		Renaming(automaton,index);
	}
	
	private static void Renaming(Automaton automaton,int initialIndex){
		List<State> states = automaton.getStates();
		int i = initialIndex+1;
		for(State a : states){
			Integer name = new Integer(i);
			if(isInitial(a)){
				Integer Iname = new Integer(initialIndex);
				a.setName(Iname.toString());
			}
			else{
				a.setName(name.toString());
				i++;
			} 
		}
	}
	
	private static boolean isInitial(State state){
		boolean result = false;
		if(((BooleanExtension) state.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue()){
			result = true;
		}
		
		return result;
	}
}
