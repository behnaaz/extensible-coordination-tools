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
package org.ect.reo.mcrl2.converters;

import java.util.ArrayList;
import java.util.List;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.Timer;
import org.ect.reo.channels.Transform;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Choice;
import org.ect.reo.mcrl2.Implication;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.PrimitiveSort;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Synchronization;


/**
 * @generated NOT
 * @author Christian Krause
 */
public class BasicChannelConverter extends AbstractChannelConverter {
	
	// Suffix for atoms of channel ends.
	public static final String CHANNEL_SUFFIX = "''";
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.AbstractChannelConverter#caseSynchronous(org.ect.reo.channels.Channel)
	 */
	@Override
	public Process caseSynchronous(Channel channel) {
		
		// Create the process.
		Process process = createProcess(channel);
		
		// Create the action.
		Action action = new Sequence(
							new Synchronization(atomOne(channel,0), atomTwo(channel,0)), 
							new Instance(process) );
		
		// Set the action.
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.AbstractChannelConverter#caseAsynchronous(org.ect.reo.channels.Channel)
	 */
	@Override
	public Process caseAsynchronous(Channel channel) {
		
		// Create the process.
		Process process = createProcess(channel);
				
		// Create the action.
		Action action = new Sequence(
							new Choice(atomOne(channel,0), atomTwo(channel,0)), 
							new Instance(process) );
		// Set the action.
		process.setAction(action);
		return process;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.channels.ChannelsSwitch#caseLossySync(org.ect.reo.channels.LossySync)
	 */
	@Override
	public Process caseLossySync(LossySync lossy) {

		// Create the process.
		Process process = createProcess(lossy);
		
		// The action:
		Action action = new Sequence(
						new Choice(
								atomOne(lossy,0), 
								new Synchronization(atomOne(lossy,0),atomTwo(lossy,0))), 
						new Instance(process));
		
		process.setAction(action);
		return process;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.channels.ChannelsSwitch#caseFilter(org.ect.reo.channels.Filter)
	 */
	@Override
	public Process caseFilter(Filter filter) {

		String expression = filter.getExpression();
		if (expression==null || expression.trim().equals("")) expression = "true";
		else if (!expression.trim().startsWith("(")) expression = "(" + expression + ")";
		
		// Create the process.
		Process process = createProcess(filter);
		
		// Create the action.
		Action action = new Sequence(
						new Choice(
							atomOne(filter,0), 
							new Synchronization(atomOne(filter,0),atomTwo(filter,0))), 
						new Instance(process));
		
		// Set the action.
		process.setAction(action);
		return process;

	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.channels.ChannelsSwitch#caseTransform(org.ect.reo.channels.Transform)
	 */
	@Override
	public Process caseTransform(Transform transform) {
		return caseSynchronous(transform);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.channels.ChannelsSwitch#caseFIFO(org.ect.reo.channels.FIFO)
	 */
	@Override
	public Process caseFIFO(FIFO fifo) {
		
		// Create the process, including parameters.
		Process process = createProcess(fifo);
		Action action = null;
		
		if (fifo.isFull()) {
			action = new Sequence(atomTwo(fifo,0), atomOne(fifo,0), new Instance(process));				
		} else {
			action = new Sequence(atomOne(fifo,0), atomTwo(fifo,0), new Instance(process));
		}
			
		// Set the action.
		process.setAction(action);
		return process;
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.channels.ChannelsSwitch#caseTimer(org.ect.reo.channels.Timer)
	 */
	@Override
	public Process caseTimer(Timer timer) {

		// Create the process.
		Process process = createProcess(timer);
		process.getParameters().add(new Atom("started", PrimitiveSort.BOOL, "false"));
		process.getParameters().add(new Atom("curTime", PrimitiveSort.NAT, "0"));
		process.getParameters().add(new Atom("maxTime", PrimitiveSort.NAT, ""+timer.getTimeout()));
		
		// Tau action:
		AtomicAction tau = new AtomicAction(new Atom("tau"));
		tau.setTime("curTime");
		
		// The action:
		Action action = 
			new Implication("!started", 
				new Sequence(atomOne(timer,0), new Instance(process, "true", "0", "maxTime")),
				new Implication("(curTime < maxTime)", 
					new Sequence(tau, new Instance(process, "true", "curTime+1", "maxTime")),
					new Sequence(atomTwo(timer,0), new Instance(process, "false", "0", "maxTime"))));
		
		process.setAction(action);
		return process;

	}

	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#addAtoms(org.ect.reo.PrimitiveEnd, java.lang.String)
	 */
	public List<Atom> addAtoms(PrimitiveEnd end, String name) {
		List<Atom> atoms = new ArrayList<Atom>();
		atoms.add(new Atom(capitalize(name) + CHANNEL_SUFFIX, getDataTypeManager().getGlobalDataType()));
		this.atoms.put(end, atoms);
		return atoms;
	}

}
