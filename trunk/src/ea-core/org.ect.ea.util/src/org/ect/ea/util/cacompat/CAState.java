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
package org.ect.ea.util.cacompat;

import static org.ect.ea.util.cacompat.CA.START_STATES_ID;
import static org.ect.ea.util.cacompat.CA.STATE_MEMORY_ID;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.IExtensionProvider;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.extensions.StringListExtension;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.ea.extensions.statememory.StateMemory;
import org.ect.ea.extensions.statememory.StateMemoryExtensionProvider;

public class CAState extends org.ect.ea.automata.State {
	private static IExtensionProvider ssProvider = new StartStateExtensionProvider();
	private static IExtensionProvider memProvider = new StateMemoryExtensionProvider();

	public CAState() {
		super();
		setExtension(ssProvider, StartStateExtensionProvider.EXTENSION_ID);
		setExtension(memProvider, StateMemoryExtensionProvider.EXTENSION_ID);
	}
	
	
	public CAState(org.ect.ea.automata.State state) {
		setName(state.getName());

		for (IExtension ext: state.getExtensions()) 
			updateExtension(
					(IExtension)EcoreUtil.copy(ext));
	
		if (findExtension(START_STATES_ID)==null)
			setExtension(ssProvider, StartStateExtensionProvider.EXTENSION_ID);
		if (findExtension(STATE_MEMORY_ID)==null)
			setExtension(memProvider, StateMemoryExtensionProvider.EXTENSION_ID);		
	}
	
	/**
	 * Stupid fucking Java has no multiple inheritance or mixins so this is in 2 places
	 * @see CATransition#setExtension(IExtensionProvider)
	 * @param provider
	 */
	void setExtension(IExtensionProvider provider, String id) {
		IExtension extension = provider.createDefaultExtension(this);
		extension.setId(id);
		updateExtension(extension);		
	}

	/**
	 * Check whether a state is a start state.
	 */
	public boolean isStartState() {
		BooleanExtension start = (BooleanExtension) findExtension(START_STATES_ID);
		if (start==null) return false;
		return start.getValue();
	}
	
	/**
	 * Set the start state flag of a state.
	 */
	public void setStartState(boolean value) {
		IExtension extension = new BooleanExtension(value);
		extension.setId(START_STATES_ID);
		updateExtension(extension);
	}

	/**
	 * Get the memory cells from a state.
	 */
	public StateMemory getMemoryCells() {
		return (StateMemory) findExtension(STATE_MEMORY_ID);
	}

	
	/**
	 * Set the memory cells definition of a state.
	 */
	public void setMemoryCells(StringListExtension memory) {
		memory.setId(STATE_MEMORY_ID);
		updateExtension(memory);
	}

	/**
	 * Get the (local) index of a memory cell in a state.
	 */
	public int getLocalMemoryCellIndex(String cell) {
		for (String value : getMemoryCells().getValues()) {
			if (cell.equals(value)) {
				return getMemoryCells().getValues().indexOf(value); 
			}
		}
		return -1;
	}
	
	/**
	 * Returns a global index of a memory cell in an automaton.
	 */
	public int getGlobalMemoryCellIndex(String cell) {
		int index = 0;
		for (org.ect.ea.automata.State current : getAutomaton().getStates()) {
			if (current==this) {
				index = index + getLocalMemoryCellIndex(cell);
				break;
			}
			StringListExtension memory = (StringListExtension)current.findExtension(STATE_MEMORY_ID);
			index = index + memory.getValues().size();
		}
		return index;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append(getName())
		.append( isStartState() ?"*":"");
		
		if (getMemoryCells().getValues().isEmpty())
			return buf.toString();
//		else
//			return buf.append(getMemoryCells().getValues()).toString();
		EMap<String, String> init = getMemoryCells().getInitializations();
		buf.append('[');
		for (String m: getMemoryCells().getValues()) {
			buf.append(m);
			if (init.containsKey(m))
				buf.append(CATransition.SPACER)
				.append('"').append(init.get(m)).append('"');
			buf.append(',');
		}
		buf.replace(buf.length()-1, buf.length(), "]");
		
		return buf.toString();
	}
}
