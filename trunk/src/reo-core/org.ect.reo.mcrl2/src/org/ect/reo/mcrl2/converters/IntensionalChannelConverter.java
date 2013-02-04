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

import static org.ect.reo.mcrl2.converters.BasicChannelConverter.CHANNEL_SUFFIX;

import java.util.ArrayList;
import java.util.List;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.FIFO;
import org.ect.reo.channels.LossySync;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Choice;
import org.ect.reo.mcrl2.Implication;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.PrimitiveSort;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Synchronization;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;



/**
 * @author Christian Krause
 * @generated NOT
 */
public class IntensionalChannelConverter extends AbstractChannelConverter {
	
	// Prefix for request atoms.
	public static final String REQUEST_PREFIX = "r";
	
	// Prefix for fire atoms.
	public static final String FIRE_PREFIX = "f";
	
	
	@Override
	public Process caseSynchronous(Channel channel) {
		
		// Create the process including parameters.
		Process process = createProcess(channel);
		Atom[] params = addRequestParams(process, 2, 'a');
		
		// Firing.
		Implication impl = new Implication(params[0].getName(),
							new Implication(params[1].getName(),
							 new Sequence(
							  new Synchronization(atomOne(channel,1),atomTwo(channel,1)), 
							  new Instance(process, "!"+params[0].getName(), "!"+params[1].getName()))
							));
		
		// Create the action.
		Choice choice = new Choice(	requestArrival(process, params[0], atomOne(channel,0)),
									requestArrival(process, params[1], atomTwo(channel,0)),
									impl);
		
		// Set the action.
		process.setAction(choice);
		return process;
	}
	
	
	@Override
	public Process caseAsynchronous(Channel channel) {
		
		// Create the process including parameters.
		Process process = createProcess(channel);
		Atom[] params = addRequestParams(process, 2, 'a');
		
		// Firings.
		Implication impl1 = new Implication(params[0].getName(),
							 new Sequence(
							  atomOne(channel,1),
							  new Instance(process, "!"+params[0].getName(), params[1].getName())));

		Implication impl2 = new Implication(params[1].getName(),
				 new Sequence(
				  atomTwo(channel,1),
				  new Instance(process, params[0].getName(), "!"+params[1].getName())));

		// Create the action.
		Choice choice = new Choice(	requestArrival(process, params[0], atomOne(channel,0)),
									requestArrival(process, params[1], atomTwo(channel,0)),
									impl1, impl2);
		
		// Set the action.
		process.setAction(choice);
		return process;
	}
	
	
	@Override
	public Process caseLossySync(LossySync lossy) {

		// Create the process including parameters.
		Process process = createProcess(lossy);
		Atom[] params = addRequestParams(process, 2, 'a');
		
		// Firing.
		Implication impl1 = new Implication("(" + params[0].getName() + " && " + params[1].getName() + ")",
							     new Sequence(
							       new Synchronization(atomOne(lossy,1),atomTwo(lossy,1)), 
							       new Instance(process, "!"+params[0].getName(), "!"+params[1].getName())));
		
		// Losing.
		Implication impl2 = new Implication("(" + params[0].getName() + " && !" + params[1].getName() + ")",
			     				new Sequence(
			     				  atomOne(lossy,1), 
						          new Instance(process, "!"+params[0].getName(), params[1].getName())));
		
		// Create the action.
		Choice choice = new Choice(	requestArrival(process, params[0], atomOne(lossy,0)),
									requestArrival(process, params[1], atomTwo(lossy,0)),
									impl1, impl2);
		
		// Set the action.
		process.setAction(choice);
		return process;

	}

	
	@Override
	public Process caseFIFO(FIFO fifo) {
		
		// Create the process, including parameters.
		Process process = createProcess(fifo);
		Atom full = new Atom(DataTypeManager.FIFO_BUFFER_VAR, PrimitiveSort.BOOL, String.valueOf(fifo.isFull()));
		process.getParameters().add(full);
		Atom[] params = addRequestParams(process, 2, 'a');
		
		// Firing.
		Implication impl1 = new Implication("!"+full.getName(),
							   new Implication(params[0].getName(),
							     new Sequence(
							       atomOne(fifo,1), 
							       new Instance(process, "!"+full.getName(), "!"+params[0].getName(), params[1].getName()))));
		
		Implication impl2 = new Implication(full.getName(),
				   			   new Implication(params[1].getName(),
				   			     new Sequence(
				   			    	atomTwo(fifo,1), 
				   			    	new Instance(process, "!"+full.getName(), params[0].getName(), "!"+params[1].getName()))));
		
		// Create the action.
		Choice choice = new Choice(	requestArrival(process, params[0], atomOne(fifo,0)),
									requestArrival(process, params[1], atomTwo(fifo,0)),
									impl1, impl2);
		
		// Set the action.
		process.setAction(choice);
		return process;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#addAtoms(org.ect.reo.PrimitiveEnd, java.lang.String)
	 */
	public List<Atom> addAtoms(PrimitiveEnd end, String name) {
		List<Atom> atoms = new ArrayList<Atom>();
		Sort dataType = getDataTypeManager().getGlobalDataType();
		atoms.add(new Atom(REQUEST_PREFIX + capitalize(name) + CHANNEL_SUFFIX, dataType));
		atoms.add(new Atom(FIRE_PREFIX + capitalize(name) + CHANNEL_SUFFIX, dataType));
		this.atoms.put(end, atoms);
		return atoms;
	}	
	
	
	// ------- Helper methods ------ //
	
	public static Atom[] addRequestParams(Process process, int count, char c) {
		Atom[] result = new Atom[count];
		for (int i=0; i<count; i++) {
			Atom p = new Atom(String.valueOf(c++), PrimitiveSort.BOOL, "false");
			process.getParameters().add(p);
			result[i] = p;
		}
		return result;
	}
	
	public static Implication requestArrival(Process process, Atom param, AtomicAction atom) {
		Implication impl = new Implication();
		impl.setCondition("!" + param.getName());
		Instance instance = new Instance(process);
		for (Atom p : process.getParameters()) {
			String arg = (p==param) ? "!" + p.getName() : p.getName();
			instance.getArguments().add(arg);
		}
		impl.setAction( new Sequence(atom, instance) );
		return impl;
	}
	
}
