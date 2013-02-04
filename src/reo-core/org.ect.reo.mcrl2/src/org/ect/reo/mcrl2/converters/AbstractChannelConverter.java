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

import org.ect.reo.CustomPrimitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.ChannelsSwitch;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;
import org.ect.reo.semantics.ReoScope;


/**
 * @author Christian Krause
 * @generated NOT
 */
public abstract class AbstractChannelConverter extends ChannelsSwitch<Process> implements Converter<Channel> {
	
	// Actions associated to ends.
	protected PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
	
	// Data type manager:
	private DataTypeManager dataTypeManager;
	
	// Scope for the conversion.
	protected ReoScope scope;
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#convert(java.lang.Object)
	 */
	public Process convert(Channel channel) {
		if (dataTypeManager!=null) {
			dataTypeManager.setLocalDataType(null);
		}
		if (channel instanceof CustomPrimitive) {
			return CustomPrimitiveConverter.convert((CustomPrimitive) channel, atoms, dataTypeManager);
		} else {
			return doSwitch(channel);			
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.Converter#setScope(org.ect.reo.semantics.ReoScope)
	 */
	public void setScope(ReoScope scope) {
		this.scope = scope;
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#getAtoms()
	 */
	public PrimitiveEndAtoms getAtoms() {
		return atoms;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#isVisible(org.ect.reo.PrimitiveEnd, int)
	 */
	public boolean isVisible(PrimitiveEnd end, int index) {
		// By default all actions (atoms) are visible.
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.Converter#setDataTypeManager(org.ect.reo.mcrl2.datatypes.DataTypeManager)
	 */
	public void setDataTypeManager(DataTypeManager manager) {
		this.dataTypeManager = manager;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.Converter#getDataTypeManager()
	 */
	public DataTypeManager getDataTypeManager() {
		return dataTypeManager;
	}
	
	/*
	 * Synchronous channels.
	 */
	public Process caseSynchronous(Channel channel) {
		return null;
	}
	
	@Override
	public Process caseSync(Sync sync) {
		return caseSynchronous(sync);
	}

	/*
	 * SyncDrain or SyncSpouts.
	 */
	public Process caseSyncDrainOrSpout(Channel channel) {
		return caseSynchronous(channel);
	}
		
	@Override
	public Process caseSyncDrain(SyncDrain syncdrain) {
		return caseSyncDrainOrSpout(syncdrain);
	}
	
	@Override
	public Process caseSyncSpout(SyncSpout syncspout) {
		return caseSyncDrainOrSpout(syncspout);
	}
	
	/*
	 * Asynchronous channels.
	 */
	public Process caseAsynchronous(Channel channel) {
		return null;
	}
	
	/*
	 * AsyncDrain or AsyncSpout.
	 */
	public Process caseAsyncDrainOrSpout(Channel channel) {
		return caseAsynchronous(channel);
	}

	@Override
	public Process caseAsyncDrain(AsyncDrain asyncdrain) {
		return caseAsyncDrainOrSpout(asyncdrain);
	}
	
	@Override
	public Process caseAsyncSpout(AsyncSpout asyncspout) {
		return caseAsyncDrainOrSpout(asyncspout);
	}

	// ------ Helper methods ----- //
	
	protected Process createProcess(Channel channel) {
		return new Process(channel.eClass().getName());
	}

	protected AtomicAction atom(PrimitiveEnd end, int index, String... arguments) {
		return new AtomicAction( atoms.get(end).get(index), arguments);		
	}
	
	protected AtomicAction atomOne(Channel channel, int index, String... arguments) {
		return atom(channel.getChannelEndOne(), index, arguments);
	}
	
	protected AtomicAction atomOne(Channel channel, String... arguments) {
		return atomOne(channel, 0, arguments);
	}
	
	protected AtomicAction atomTwo(Channel channel, int index, String... arguments) {
		return atom(channel.getChannelEndTwo(), index, arguments);
	}

	protected AtomicAction atomTwo(Channel channel, String... arguments) {
		return atomTwo(channel, 0, arguments);
	}

	protected static String capitalize(String s) {
		if (s==null || s.length()==0) return "Null";
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}
