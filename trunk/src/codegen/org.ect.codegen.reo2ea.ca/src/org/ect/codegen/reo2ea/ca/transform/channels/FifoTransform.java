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

import org.eclipse.emf.common.util.EMap;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.util.ReoUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.CARefactoring;
import org.ect.ea.extensions.statememory.StateMemory;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;

public class FifoTransform extends ChannelTransform  {
	
	//private static final IPreferenceStore prefs = Reo2EAPlugin.getDefault().getPreferenceStore();

	private FIFO fifo;
	
	public Automaton transform(Channel channel) throws TransformationException {
		if (!(channel instanceof FIFO))
			throw new IllegalArgumentException(channel+" is not a FIFO");

		fifo = (FIFO) channel;
		Automaton automaton = super.transform(channel);
		for (State s: automaton.getStates())
			if(s.getName().equals("EMPTY"))
				CA.setStartState(s, !fifo.isFull());
			else if(s.getName().equals("FULL")) {
				CA.setStartState(s, fifo.isFull());
			} else
				throw new TransformationException("Invalid template for "+ channel);
		

//			System.err.println(getClass()+": "+automaton);
		return automaton;
	}
	
	 
	// This is a debugging feature to map FIFO names to memory locs 
	@Override
	protected void  mangleMemory(State s) {
		/*if (!prefs.getBoolean(PreferenceConstants.DEBUG)) { 
			 super.mangleMemory(s);
			 return;
		}*/
		
		StringListExtension mem = CA.getMemoryCells(s);
		if (mem.getValues().isEmpty())
			return;
		
		String  newName = (ReoUtil.node2PortName(fifo.getSourceEnd().getNode())
				+ ReoUtil.node2PortName(fifo.getSinkEnd().getNode())
				).toLowerCase();
		CARefactoring.renameStateMemory(mem.getValues().get(0), 
				newName, s);
		
		
		if (!(mem instanceof StateMemory && fifo.isFull()))
			return;
		
		EMap<String,String> init= ((StateMemory) mem).getInitializations();
		init.put(newName, "FULL");
	}
	
}
