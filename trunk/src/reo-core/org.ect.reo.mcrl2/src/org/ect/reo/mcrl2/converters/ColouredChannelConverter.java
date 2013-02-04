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
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.datatypes.MCRL2SortUtil;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ColouredChannelConverter extends DataChannelConverter {
	
	// We need a helper:
	private ColouredConverterHelper helper = new ColouredConverterHelper();
	
	// Data variables:
	public static final String CVAR = "c";
	public static final String CVAR0 = "c0";
	public static final String CVAR1 = "c1";
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataChannelConverter#caseSync(org.ect.reo.channels.Sync)
	 */
	@Override
	public Process caseSync(Sync sync) {
		return caseSyncOrTransform(sync,null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataChannelConverter#caseTransform(org.ect.reo.channels.Transform)
	 */
	@Override
	public Process caseTransform(Transform transform) {
		return caseSyncOrTransform(transform, transform.getExpression());
	}
	
	/*
	 * Sync or Transform channels.
	 */
	private Process caseSyncOrTransform(Channel channel, String expression) {
		
		if (expression==null) expression = CVAR;
		Process process = createProcess(channel);
		Action action = new Sequence(
				new Choice(
						new Sequence(
								new Summation(new Atom(CVAR,helper.getColoured())),
								new Implication(helper.isFlow(CVAR),new Synchronization(atomOne(channel,CVAR), atomTwo(channel,expression)))
						),
						new Synchronization(atomOne(channel,helper.noflowR()), atomTwo(channel,helper.noflowG())),
						new Synchronization(atomOne(channel,helper.noflowG()), atomTwo(channel,helper.noflowR())),
						new Synchronization(atomOne(channel,helper.noflowG()), atomTwo(channel,helper.noflowG()))
				),
				new Instance(process) );
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataChannelConverter#caseFilter(org.ect.reo.channels.Filter)
	 */
	@Override
	public Process caseFilter(Filter filter) {
		
		String where = (helper.getRealDataType()!=null) ? "whr " + VAR + "=" + helper.dataOfFlow(CVAR) + " end" : "";
		String expression = "(" + helper.isFlow(CVAR) + " && " + getExpression(filter) + " " + where + ")";
		Action dontLose = new Synchronization(atomOne(filter,CVAR), atomTwo(filter,CVAR));
		Action lose 	= new Choice(new Synchronization(atomOne(filter,CVAR), atomTwo(filter,helper.noflowR())),
									 new Synchronization(atomOne(filter,CVAR), atomTwo(filter,helper.noflowG())));
		
		Process process = createProcess(filter);
		Action action = new Sequence(
				new Choice(
						new Sequence(
							new Summation(new Atom(CVAR,helper.getColoured())),
							new Implication(expression, dontLose, lose)
						),
						new Synchronization(atomOne(filter,helper.noflowR()), atomTwo(filter,helper.noflowG())),
						new Synchronization(atomOne(filter,helper.noflowG()), atomTwo(filter,helper.noflowR())),
						new Synchronization(atomOne(filter,helper.noflowG()), atomTwo(filter,helper.noflowG()))
				),
				new Instance(process) );
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataChannelConverter#caseSyncDrainOrSpout(org.ect.reo.channels.Channel)
	 */
	@Override
	public Process caseSyncDrainOrSpout(Channel channel) {
		
		Process process = createProcess(channel);
		String where = (helper.getRealDataType()!=null) ? "whr " + VAR0 + "=" + helper.dataOfFlow(CVAR0) + ", " + VAR1 + "=" + helper.dataOfFlow(CVAR1) + " end" : "";
		String expression = helper.isFlow(CVAR0) + " && " + helper.isFlow(CVAR1);
		if (!getExpression(channel).equals("true")) {
			expression = expression + " && " + getExpression(channel) + " " + where;
		}
		
		Action flow = new Synchronization(atomOne(channel,CVAR0), atomTwo(channel,CVAR1));
		Action action = new Sequence(
				new Choice(
						new Sequence(
							new Summation(new Atom(CVAR0,helper.getColoured()), new Atom(CVAR1,helper.getColoured())),
							new Implication("(" + expression + ")", flow)
						),
						new Synchronization(atomOne(channel,helper.noflowR()), atomTwo(channel,helper.noflowG())),
						new Synchronization(atomOne(channel,helper.noflowG()), atomTwo(channel,helper.noflowR())),
						new Synchronization(atomOne(channel,helper.noflowG()), atomTwo(channel,helper.noflowG()))
				),
				new Instance(process));
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataChannelConverter#caseAsyncDrainOrSpout(org.ect.reo.channels.Channel)
	 */
	@Override
	public Process caseAsyncDrainOrSpout(Channel channel) {
		
		Process process = createProcess(channel);
		Action flow = new Choice(
				new Synchronization(atomOne(channel,CVAR), atomTwo(channel,helper.noflowG())),
				new Synchronization(atomOne(channel,CVAR), atomTwo(channel,helper.noflowG())),
				new Synchronization(atomOne(channel,helper.noflowG()), atomTwo(channel,CVAR)),
				new Synchronization(atomOne(channel,helper.noflowR()), atomTwo(channel,CVAR))
				);
		
		Action action = new Sequence(
				new Choice(
						new Sequence(
							new Summation(new Atom(CVAR,helper.getColoured())),
							new Implication(helper.isFlow(CVAR), flow)
						),
						new Synchronization(atomOne(channel,helper.noflowG()), atomTwo(channel,helper.noflowG()))
				),
				new Instance(process));
				
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataChannelConverter#caseLossySync(org.ect.reo.channels.LossySync)
	 */
	@Override
	public Process caseLossySync(LossySync lossy) {
					
		Process process = createProcess(lossy);
		Action action = new Sequence(
				new Choice(
						new Sequence(
								new Summation(new Atom(CVAR,helper.getColoured())),
								new Implication(helper.isFlow(CVAR), new Choice(
										new Synchronization(atomOne(lossy,CVAR), atomTwo(lossy,CVAR)),
										new Synchronization(atomOne(lossy,CVAR), atomTwo(lossy,helper.noflowG())
										)))
						),
						new Synchronization(atomOne(lossy,helper.noflowG()), atomTwo(lossy,helper.noflowR())),
						new Synchronization(atomOne(lossy,helper.noflowG()), atomTwo(lossy,helper.noflowG()))
				),
				new Instance(process) );
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
		Action emptyToFull = 
			new Sequence(
					new Summation(new Atom(CVAR,helper.getColoured())),
					new Implication(helper.isFlow(CVAR),
							new Choice(
									new Synchronization(atomOne(fifo,CVAR), atomTwo(fifo,helper.noflowR())),
									new Synchronization(atomOne(fifo,CVAR), atomTwo(fifo,helper.noflowG())))
				), 
				new Instance(process, getDataTypeManager().getFIFOFull(CVAR)));
		
		Action emptyToEmpty = new Sequence(
				new Choice(
						new Synchronization(atomOne(fifo,helper.noflowG()), atomTwo(fifo,helper.noflowR())),
						new Synchronization(atomOne(fifo,helper.noflowG()), atomTwo(fifo,helper.noflowG()))
				), new Instance(process, getDataTypeManager().getFIFOEmpty()));
		
		Choice empty = new Choice(emptyToFull, emptyToEmpty);
		
		// Full part.
		String dataItem = getDataTypeManager().getFIFOData();
		String flow = helper.flow(dataItem);
		Action fullToEmpty = new Sequence(
				new Choice(
						new Synchronization(atomOne(fifo,helper.noflowR()), atomTwo(fifo,flow)),
						new Synchronization(atomOne(fifo,helper.noflowG()), atomTwo(fifo,flow))
				), new Instance(process, getDataTypeManager().getFIFOEmpty()));
		
		Action fullToFull = new Sequence(
				new Choice(
						new Synchronization(atomOne(fifo,helper.noflowR()), atomTwo(fifo,helper.noflowG())),
						new Synchronization(atomOne(fifo,helper.noflowG()), atomTwo(fifo,helper.noflowG()))
				), new Instance(process, DataTypeManager.FIFO_BUFFER_VAR));
		
		Choice full = new Choice(fullToEmpty, fullToFull);
		
		
		// The main action:
		Action implication = new Implication(getDataTypeManager().getFIFOEmptyCondition(), empty, full);
		Action action = new Sequence(implication);
		process.setAction(action);
		return process;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataChannelConverter#caseTimer(org.ect.reo.channels.Timer)
	 */
	@Override
	public Process caseTimer(Timer timer) {

		// Create the process, including parameters.
		// Create the process.
		Process process = createProcess(timer);
		process.getParameters().add(new Atom("started", PrimitiveSort.BOOL, "false"));
		process.getParameters().add(new Atom("curTime", PrimitiveSort.NAT, "0"));
		process.getParameters().add(new Atom("maxTime", PrimitiveSort.NAT, ""+timer.getTimeout()));
		
		// Tau action:
		AtomicAction tau = new AtomicAction(new Atom("tau"));
		tau.setTime("curTime");
		
		// Not started:
		Action notStarted = 
			new Implication(helper.isFlow(CVAR),
				
				// Not started and flow:
				new Sequence(
					new Choice(
							new Synchronization(atomOne(timer,CVAR), atomTwo(timer,helper.noflowR())), 
							new Synchronization(atomOne(timer,CVAR), atomTwo(timer,helper.noflowG()))),
					new Instance(process, "true", "0", "maxTime")),
				
				// Not started and noflow:
				new Sequence(
					new Choice(
						new Synchronization(atomOne(timer,helper.noflowG()), atomTwo(timer,helper.noflowR())),
						new Synchronization(atomOne(timer,helper.noflowG()), atomOne(timer,helper.noflowG()))),
					new Instance(process, "false", "0", "maxTime")));
		
		// Already started:
		Action started = 
			new Implication(helper.isFlow(CVAR),
				
				// Started and flow:
				new Implication("(curTime < maxTime)",
					new Sequence(
						new Choice(
							new Synchronization(atomOne(timer,helper.noflowR()), atomTwo(timer,CVAR), tau), 
							new Synchronization(atomOne(timer,helper.noflowG()), atomTwo(timer,CVAR), tau)),
						new Instance(process, "true", "curTime+1", "maxTime")),
					new Sequence(
						new Choice(
							new Synchronization(atomOne(timer,helper.noflowR()), atomTwo(timer,CVAR)),
							new Synchronization(atomOne(timer,helper.noflowG()), atomTwo(timer,CVAR))), 
						new Instance(process, "false", "0", "maxTime"))
				),
				
				// Started and noflow:
				new Sequence(
					new Implication("(curTime < maxTime)",
						new Choice(
							new Synchronization(atomOne(timer,helper.noflowG()), atomTwo(timer,helper.noflowG())),
							new Synchronization(atomOne(timer,helper.noflowG()), atomTwo(timer,helper.noflowR())),
							new Synchronization(atomOne(timer,helper.noflowR()), atomTwo(timer,helper.noflowG())),
							new Synchronization(atomOne(timer,helper.noflowR()), atomOne(timer,helper.noflowR()))),
						new Choice(
							new Synchronization(atomOne(timer,helper.noflowG()), atomTwo(timer,helper.noflowG())),
							new Synchronization(atomOne(timer,helper.noflowR()), atomTwo(timer,helper.noflowG())))
					),
					new Instance(process, "false", "0", "maxTime")));

		// The action:
		Action action = 
			new Sequence(
				new Summation(newAtom(CVAR)),
				new Implication("!started", notStarted, started));
		
		process.setAction(action);
		return process;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.AbstractChannelConverter#setDataTypeManager(org.ect.reo.mcrl2.datatypes.DataTypeManager)
	 */
	@Override
	public void setDataTypeManager(DataTypeManager manager) {
		super.setDataTypeManager(manager);
		helper.setDataTypeManager(manager);
	}
	
	private String getExpression(Channel channel) {
		if (channel instanceof DataAware && helper.getRealDataType()!=null) {
			return MCRL2SortUtil.getDataExpression((DataAware) channel);
		} else {
			return "true";
		}
	}
	
}
