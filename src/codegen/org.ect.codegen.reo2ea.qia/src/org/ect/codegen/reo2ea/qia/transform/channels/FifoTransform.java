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

import org.ect.codegen.reo2ea.qia.util.QIA;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Channel;

public class FifoTransform extends ChannelTransform {
	@Override
	public Automaton transform(Channel channel) {
		if (!(channel instanceof FIFO))
			throw new IllegalArgumentException(channel+" is not a FIFO");

		Automaton automaton = super.transform(channel);
		FIFO fifo = (FIFO) channel;
		for (State s: automaton.getStates())
			if(s.getName().equals("EMPTY0"))
				QIA.setStartState(s, !fifo.isFull());
			else if(s.getName().equals("FULL0")) {
				QIA.setStartState(s, fifo.isFull());
			}
			else if(s.getName().equals("EMPTY1") || s.getName().equals("EMPTY2") || s.getName().equals("EMPTY3")) continue;
			else if(s.getName().equals("FULL1") || s.getName().equals("FULL2") || s.getName().equals("FULL3")) continue;
			else	throw new IllegalStateException();
		

			System.err.println(getClass()+": "+automaton); 
		return automaton;
	}
	/* This is a debugging feature to map FIFO names to memory locs
	@Override
	protected void  mangleMemory(State s) {
		StringListExtension mem = CA.getMemoryCells(s);
		if (mem.getValues().isEmpty())
			return;
		
		FIFO fifo = (FIFO) channel;
		String  newName = (fifo.getSourceEnd().getNode().getName()
				+fifo.getSinkEnd().getNode().getName()
				).toLowerCase();
		CARefactoring.renameStateMemory(mem.getValues().get(0), 
				newName, s);
		
		
		if (!(mem instanceof StateMemory && fifo.isFull()))
			return;
		
		EMap<String,String> init= ((StateMemory) mem).getInitializations();
		init.put(newName, "FULL");
	}
	*/
}
