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

import static org.ect.reo.mcrl2.converters.IntensionalChannelConverter.FIRE_PREFIX;
import static org.ect.reo.mcrl2.converters.IntensionalChannelConverter.REQUEST_PREFIX;

import java.util.ArrayList;
import java.util.List;

import org.ect.reo.PrimitiveEnd;
import org.ect.reo.components.SingleEndComponent;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;



/**
 * @author Christian Krause
 * @generated NOT
 */
public class IntensionalComponentConverter extends AbstractComponentConverter {
	
	// Suffix for atoms of component ends.
	public static final String COMPONENT_SUFFIX = BasicChannelConverter.CHANNEL_SUFFIX;
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.components.ComponentsSwitch#caseSingleEndComponent(org.ect.reo.components.SingleEndComponent)
	 */
	@Override
	public Process caseSingleEndComponent(SingleEndComponent component) {
		
		// Create the process.
		Process process = createProcess(component);
		PrimitiveEnd end = component.getEnd();
		int requests = component.getRequests();
		
		// Create the action.
		Action action = null;
		
		if (requests<0) {
			action = new Sequence(atom(end,0), atom(end,1), new Instance(process));
		}
		else if (requests==0) {
			action = new Instance(process);
		}
		else {
			Sequence seq = new Sequence();
			for (int i=0; i<requests; i++){
				seq.getActions().add(atom(end,0));
				seq.getActions().add(atom(end,1));
			}
			action = seq;
		}
			
		// Set the action.
		process.setAction(action);
		return process;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#addAtoms(org.ect.reo.PrimitiveEnd, java.lang.String)
	 */
	public List<Atom> addAtoms(PrimitiveEnd end, String name) {
		List<Atom> atoms = new ArrayList<Atom>();
		Sort dataType = getDataTypeManager().getGlobalDataType();
		atoms.add(new Atom(REQUEST_PREFIX + capitalize(name) + COMPONENT_SUFFIX, dataType));
		atoms.add(new Atom(FIRE_PREFIX + capitalize(name) + COMPONENT_SUFFIX, dataType));
		this.atoms.put(end, atoms);
		return atoms;
	}
	
}
