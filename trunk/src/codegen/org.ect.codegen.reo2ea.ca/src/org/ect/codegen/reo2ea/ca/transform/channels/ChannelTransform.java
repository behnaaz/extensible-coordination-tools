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
package org.ect.codegen.reo2ea.ca.transform.channels;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.constraints.CARefactoring;
import org.ect.ea.extensions.statememory.StateMemoryExtensionProvider;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.channels.Channel;

public class ChannelTransform implements ITransformation<Channel>  {
	private static final String SOURCE="SOURCE", SINK="SINK";
	protected final static Map<String, Automaton> primitives = new HashMap<String, Automaton>();

	private char stateSuffix='z';
	protected EndNamingPolicy endNames;

	public static void setTemplates(Collection<Automaton> templates) {		
		for (Automaton a : templates) {
				String classNames = a.getName();
				primitives.put(classNames, a);				
				
				System.err.println("Loaded primitive " + classNames);
		} 
	}
	
	public void setEndNamingPolicy(EndNamingPolicy endNames) {
		this.endNames = endNames;		
	}

	public Automaton transform(Channel channel) throws TransformationException {
		String name = channel.getClass().getName();
		if (!primitives.containsKey(name))
			throw new TransformationException("Invalid template for "+ channel);
		
		Automaton copy = (Automaton) EcoreUtil.copy(primitives.get(name));
		
		int i=0;
		for (PrimitiveEnd sourceEnd: channel.getSourceEnds()) 
			CARefactoring.renamePortName(SOURCE+ i++, endNames.getName(sourceEnd), copy);
		
		i=0;
		for (PrimitiveEnd sinkEnd: channel.getSinkEnds()) 
			CARefactoring.renamePortName(SINK+ i++, endNames.getName(sinkEnd), copy);

		for (State s : copy.getStates()) 
			mangleMemory(s);
		
		return copy;
	}

	protected void mangleMemory(State state) {
		StringListExtension mem = (StringListExtension) state.findExtension(StateMemoryExtensionProvider.EXTENSION_ID);
//		StringListExtension mem = StateMemoryExtensionProvider.get(state);
		
		if (mem==null)
			return;

		for (String old : mem.getValues()) 
			CARefactoring.renameStateMemory(old, 
					Character.toString(stateSuffix--), state);
	}		
	
}
