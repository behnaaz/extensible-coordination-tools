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

import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Choice;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Synchronization;


/**
 * @author Behnaz Changizi
 * @generated NOT
 */

public class BlockingNodeConverter extends BasicNodeConverter {
	
	// Prefix for block atoms.
	public static final String BLOCK_PREFIX = "b";
	
	// Prefix for start atoms.
	public static final String START_PREFIX = "s";
	
	// Prefix for finish atoms.
	public static final String FINISH_PREFIX = "f";
	
	// Prefix for unblock atoms.
	public static final String UNBLOCK_PREFIX = "u";

		@Override
		protected Process caseReplicateNode(Node node) {
			// Clean name
			cleanName(node);
			
			// Create a Choice
			Choice choice = new Choice();

			for (SinkEnd sink : node.getSinkEnds()) {
				
				// Create a Sequence
				Sequence sequence = new Sequence();
			
				// Create a synchronization for block action 
				Synchronization syncb = new Synchronization(flow(sink,0));
				// Create a synchronization for start action 
				Synchronization syncs = new Synchronization(flow(sink,1));
				// Create a synchronization for finish action 
				Synchronization syncf = new Synchronization(flow(sink,2));
				// Create a synchronization for unblock action 
				Synchronization syncu = new Synchronization(flow(sink,3));
			
				for (SourceEnd source : node.getSourceEnds()) {
					syncb.getActions().add(flow(source, 0));
					syncs.getActions().add(flow(source, 1));
					syncf.getActions().add(flow(source, 2));
					syncu.getActions().add(flow(source, 3));
				}
				
				// Add active ends to the synchronization.
				sequence.getActions().add(syncb);
				sequence.getActions().add(new Synchronization(syncs, syncf));
				sequence.getActions().add(syncu);
				
				// Add the synchronization to the choice action.
				choice.getActions().add(sequence);
			}
			
			// Wrap the flow actions.
			choice = new Choice(wrapFlowAction(node,choice));
	

			// Try to simplify the action a bit.
			Action action = (choice.getActions().size()==1) ? choice.getActions().get(0) : choice; 
			
			// Wrap and return the action.
			return wrapAction(node, action);
		}
		
		private void cleanName(Node node) {
			try{
				if (node.getName().matches("\\W"))
					node.setName(node.getName().replaceAll("\\W", ""));
			}
			catch(Exception ex)
			{
				System.out.println("Error: Node "+node.getName()+" does not have a mCRL2 compatible name!");
			}
		}

		@Override
		protected Process caseJoinNode(Node node) {
			// Clean name
			cleanName(node);
			// Create a choice.
			Choice choice = new Choice();

			for (SinkEnd sink : node.getSinkEnds()) {
				
				// Create a Sequence
				Sequence sequence = new Sequence();
				
				for (SourceEnd source : node.getSourceEnds()) {
					
					// Add active ends to the synchronization.
					sequence.getActions().add(new Synchronization(flow(source, 0), flow(sink,0)));
					sequence.getActions().add(new Synchronization(
												new Synchronization(flow(source, 1), flow(sink,1)), 
												new Synchronization(flow(source, 2), flow(sink,2))));
					sequence.getActions().add(new Synchronization(flow(source, 3), flow(sink,3)));
				}
				
				// Add the synchronization to the choice action.
				choice.getActions().add(sequence);
			}
			
			// Wrap the flow actions.
			choice = new Choice(wrapFlowAction(node,choice));
	

			// Try to simplify the action a bit.
			Action action = (choice.getActions().size()==1) ? choice.getActions().get(0) : choice; 
			
			// Wrap and return the action.
			return wrapAction(node, action);
		}
		
	
		protected Action flow(PrimitiveEnd end, int i) {
			return atom(end, i);
		}
		
		@Override
		protected Process caseRouteNode(Node node) {
			// Clean name
			cleanName(node);
			
			// Create a Choice
			Choice choice = new Choice();

			for (SinkEnd sink : node.getSinkEnds()) {
				
				// Create a Choice
				Choice choice2 = new Choice();
			
				for (SourceEnd source : node.getSourceEnds()) {
					choice2.getActions().add(new Sequence(
							new Synchronization(flow(source, 0), flow(sink,0)),
							new Synchronization(
									new Synchronization(flow(source, 1), flow(sink,1)),
									new Synchronization(flow(source, 2), flow(sink,2))
							),
							new Synchronization(flow(source, 3), flow(sink,3))
							));
				}
				
				
				// Add the synchronization to the choice action.
				choice.getActions().add(choice2);
			}
			
			// Wrap the flow actions.
			choice = new Choice(wrapFlowAction(node,choice));
	

			// Try to simplify the action a bit.
			Action action = (choice.getActions().size()==1) ? choice.getActions().get(0) : choice; 
			
			// Wrap and return the action.
			return wrapAction(node, action);
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.ect.reo.mcrl2.conversion.Converter#addAtoms(org.ect.reo.PrimitiveEnd, java.lang.String)
		 */
		public List<Atom> addAtoms(PrimitiveEnd end, String name) {
			List<Atom> atoms = new ArrayList<Atom>();
			Sort dataType = getDataTypeManager().getGlobalDataType();
			atoms.add(new Atom(BLOCK_PREFIX + capitalize(name) + BasicNodeConverter.NODE_SUFFIX, dataType));
			atoms.add(new Atom(START_PREFIX + capitalize(name) + BasicNodeConverter.NODE_SUFFIX, dataType));
			atoms.add(new Atom(FINISH_PREFIX + capitalize(name) + BasicNodeConverter.NODE_SUFFIX, dataType));
			atoms.add(new Atom(UNBLOCK_PREFIX + capitalize(name) + BasicNodeConverter.NODE_SUFFIX, dataType));
			this.atoms.put(end, atoms);
			return atoms;
		}
		
		
}
