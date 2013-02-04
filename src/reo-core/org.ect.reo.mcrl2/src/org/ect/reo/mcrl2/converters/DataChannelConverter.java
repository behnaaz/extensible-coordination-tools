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

import org.ect.reo.DataAware;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.Sync;
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
import org.ect.reo.mcrl2.Summation;
import org.ect.reo.mcrl2.Synchronization;
import org.ect.reo.mcrl2.datatypes.MCRL2SortUtil;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class DataChannelConverter extends BasicChannelConverter {
	
	// Data variables:
	public static final String VAR = "d";
	public static final String VAR0 = "d0";
	public static final String VAR1 = "d1";
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.AbstractChannelConverter#caseSync(org.ect.reo.channels.Sync)
	 */
	@Override
	public Process caseSync(Sync sync) {
		
		Process process = createProcess(sync);
		Action action = new Sequence(
				new Summation(newAtom(VAR)),
				new Synchronization(atomOne(sync,0,VAR), atomTwo(sync,0,VAR)), 
				new Instance(process) );

		process.setAction(action);
		return process;			
			
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.AbstractChannelConverter#caseSyncDrainOrSpout(org.ect.reo.channels.Channel)
	 */
	@Override
	public Process caseSyncDrainOrSpout(Channel channel) {
		
		Process process = createProcess(channel);
		
		Action firing = new Synchronization(atomOne(channel,0,VAR0), atomTwo(channel,0,VAR1));
		if (channel instanceof DataAware) {
			firing = new Implication(MCRL2SortUtil.getDataExpression((DataAware) channel), firing);
		}
		
		Action action = new Sequence(
				new Summation(newAtom(VAR0), newAtom(VAR1)),
				firing, 
				new Instance(process) );
		
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.AbstractChannelConverter#caseAsyncDrainOrSpout(org.ect.reo.channels.Channel)
	 */
	@Override
	public Process caseAsyncDrainOrSpout(Channel channel) {
		
		Process process = createProcess(channel);
		Action action = new Sequence(
				new Summation(newAtom(VAR0), newAtom(VAR1)),
				new Choice(atomOne(channel,0,VAR0), atomTwo(channel,0,VAR1)), 
				new Instance(process) );
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicChannelConverter#caseLossySync(org.ect.reo.channels.LossySync)
	 */
	@Override
	public Process caseLossySync(LossySync lossy) {

		Process process = createProcess(lossy);
		Action action = new Sequence(
				new Summation(newAtom(VAR)),
				new Choice(
						atomOne(lossy,0,VAR), 
						new Synchronization(atomOne(lossy,0,VAR),atomTwo(lossy,0,VAR))), 
						new Instance(process));
		process.setAction(action);
		return process;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicChannelConverter#caseFilter(org.ect.reo.channels.Filter)
	 */
	@Override
	public Process caseFilter(Filter filter) {

		Process process = createProcess(filter);
		Action action = new Sequence(
				new Summation(newAtom(VAR)),
				new Implication(
						MCRL2SortUtil.getDataExpression(filter),
						new Synchronization(atomOne(filter,0,VAR),atomTwo(filter,0,VAR)),
						atomOne(filter,0,VAR)
				), 
				new Instance(process));

		// Set the action.
		process.setAction(action);
		return process;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicChannelConverter#caseTransform(org.ect.reo.channels.Transform)
	 */
	@Override
	public Process caseTransform(Transform transform) {

		String expression = transform.getExpression();
		if (expression==null || expression.trim().equals("")) expression = VAR;
		
		Process process = createProcess(transform);
		Action action = new Sequence(
				new Summation(newAtom(VAR)),
				new Synchronization(atomOne(transform,0,VAR),atomTwo(transform,0,expression)),
				new Instance(process));
		
		// Set the action.
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicChannelConverter#caseFIFO(org.ect.reo.channels.FIFO)
	 */
	@Override
	public Process caseFIFO(FIFO fifo) {

		// Create the process, including parameters.
		Process process = createProcess(fifo);
		process.getParameters().add(getDataTypeManager().getFIFOParamAtom(fifo));
		
		// Empty part.
		Sequence seq1 = new Sequence(atomOne(fifo, 0, VAR), new Instance(process, getDataTypeManager().getFIFOFull(VAR)));

		// Full part.
		Sequence seq2 = new Sequence(atomTwo(fifo, 0, getDataTypeManager().getFIFOData()), new Instance(process, getDataTypeManager().getFIFOEmpty()));

		// Composite action.
		Action action = new Sequence(new Summation(newAtom(VAR)), new Implication(getDataTypeManager().getFIFOEmptyCondition(), seq1, seq2));

		// Set the action.
		process.setAction(action);
		return process;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicChannelConverter#caseTimer(org.ect.reo.channels.Timer)
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
			new Sequence(
				new Summation(newAtom(VAR)),
				new Implication("!started",
					new Sequence(atomOne(timer,"d"), new Instance(process, "true", "0", "maxTime")),
					new Implication("(curTime < maxTime)", 
						new Sequence(tau, new Instance(process, "true", "curTime+1", "maxTime")),
						new Sequence(atomTwo(timer,"d"), new Instance(process, "false", "0", "maxTime")))));
		
		process.setAction(action);
		return process;

	}
	
	protected Atom newAtom(String name) {
		return new Atom(name, getDataTypeManager().getGlobalDataType());
	}
	
}
