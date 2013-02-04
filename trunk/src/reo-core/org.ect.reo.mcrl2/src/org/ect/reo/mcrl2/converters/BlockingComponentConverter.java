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

import org.ect.reo.Component;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.components.SingleEndComponent;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Choice;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;


/**
 * @author Behnaz Changizi
 * @generated NOT
 */

public class BlockingComponentConverter extends BasicComponentConverter {

	// Prefix for block atoms.
	public static final String BLOCK_PREFIX = "b";
	
	// Prefix for start atoms.
	public static final String START_PREFIX = "s";
	
	// Prefix for finish atoms.
	public static final String FINISH_PREFIX = "f";
	
	// Prefix for unblock atoms.
	public static final String UNBLOCK_PREFIX = "u";
	
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
				action = new Sequence(atom(end,0), new Instance(process));
			}
			else if (requests==0) {
				action = new Instance(process);
			}
			else {
				Sequence seq = new Sequence();
				for (int i=0; i<requests; i++){
					seq.getActions().add(atom(end,0));
					seq.getActions().add(atom(end,1));
					seq.getActions().add(atom(end,2));
					seq.getActions().add(atom(end,3));
				}
				action = seq;
			}
			
			process.setAction(action);
			return process;

		}
		
		/*
		 * Variables.
		 */
		public Process caseVariable(Component component) {
			//??????????????
			// Source and sink:
			SourceEnd src = component.getSourceEnd(0);
			SinkEnd snk = component.getSinkEnd(0);
			
			// Create the process and the actions.
			Process process = createProcess(component);
			Action action = new Sequence(atom(src,0), new Choice(atom(src,0), atom(snk,0)), new Instance(process));
			process.setAction(action);
			return process;
			
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.ect.reo.mcrl2.converters.AbstractComponentConverter#convert(org.ect.reo.Component)
		 */
		@Override
		public Process convert(Component component) {
			getDataTypeManager().setLocalDataType(null);
			if ("Variable".equalsIgnoreCase(component.getName()) && 
					component.getSourceEnds().size()==1 && 
					component.getSinkEnds().size()==1) {
				return caseVariable(component);
			}
			return super.convert(component);
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.ect.reo.mcrl2.conversion.Converter#addAtoms(org.ect.reo.PrimitiveEnd, java.lang.String)
		 */
		public List<Atom> addAtoms(PrimitiveEnd end, String name) {
			List<Atom> atoms = new ArrayList<Atom>();
			atoms.add(new Atom(BLOCK_PREFIX + capitalize(name) + COMPONENT_SUFFIX, getDataTypeManager().getGlobalDataType()));
			atoms.add(new Atom(START_PREFIX + capitalize(name) + COMPONENT_SUFFIX, getDataTypeManager().getGlobalDataType()));
			atoms.add(new Atom(FINISH_PREFIX + capitalize(name) + COMPONENT_SUFFIX, getDataTypeManager().getGlobalDataType()));
			atoms.add(new Atom(UNBLOCK_PREFIX + capitalize(name) + COMPONENT_SUFFIX, getDataTypeManager().getGlobalDataType()));
			this.atoms.put(end, atoms);
			return atoms;
		}	
		
}
