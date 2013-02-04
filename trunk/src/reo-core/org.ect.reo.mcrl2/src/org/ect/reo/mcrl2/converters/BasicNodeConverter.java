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
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Synchronization;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class BasicNodeConverter extends AbstractNodeConverter {
	
	// Suffix for atoms of nodes ends.
	public static final String NODE_SUFFIX = "'";
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.NodeSwitch#caseReplicateNode(org.ect.reo.Node)
	 */
	@Override
	protected Process caseReplicateNode(Node node) {
		
		// Create a choice.
		Choice choice = new Choice();
		for (SinkEnd sink : node.getSinkEnds()) {
			
			// Create a synchronization.
			Synchronization sync = new Synchronization();
			
			// Add active ends to the synchronization.
			sync.getActions().add(flow(sink));
			for (SourceEnd source : node.getSourceEnds()) {
				sync.getActions().add(flow(source));
			}
			
			// No-flow actions:
			for (SinkEnd inactive : node.getSinkEnds()) {
				if (sink==inactive) continue;
				Action noflow = noflow(inactive);
				if (noflow!=null) sync.getActions().add(noflow);
			}
			
			// Add the synchronization to the choice action.
			choice.getActions().add(sync);
		}
		
		// Wrap the flow actions.
		choice = new Choice(wrapFlowAction(node,choice));
		
		// Additional noflow:
		addNoflow(node, choice);

		// Try to simplify the action a bit.
		Action action = (choice.getActions().size()==1) ? choice.getActions().get(0) : choice; 
		
		// Wrap and return the action.
		return wrapAction(node, action);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.NodeSwitch#caseRouteNode(org.ect.reo.Node)
	 */
	@Override
	protected Process caseRouteNode(Node node) {
		
		// Create a choice.
		Choice choice = new Choice();
		
		for (SinkEnd sink : node.getSinkEnds()) {
			for (SourceEnd source : node.getSourceEnds()) {
				
				// Create a synchronization.
				Synchronization sync = new Synchronization();
			
				// Add active ends to the synchronization.
				sync.getActions().add(flow(sink));
				sync.getActions().add(flow(source));
				
				// No-flow actions:
				for (PrimitiveEnd inactive : node.getAllEnds()) {
					if (inactive==sink || inactive==source) continue;
					Action noflow = noflow(inactive);
					if (noflow!=null) sync.getActions().add(noflow);
				}
			
				// Add the synchronization to the choice action.
				choice.getActions().add(sync);
			}
		}
		
		// Wrap the flow actions.
		choice = new Choice(wrapFlowAction(node,choice));
		
		// Additional noflow:
		addNoflow(node, choice);

		// Try to simplify the action a bit.
		Action action = (choice.getActions().size()==1) ? choice.getActions().get(0) : choice; 
		
		// Wrap and return the action.
		return wrapAction(node, action);
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.NodeSwitch#caseJoinNode(org.ect.reo.Node)
	 */
	@Override
	protected Process caseJoinNode(Node node) {
		
		// Create a choice.
		Choice choice = new Choice();
		for (SourceEnd source : node.getSourceEnds()) {
			
			// Create a synchronization.
			Synchronization sync = new Synchronization();
			
			// Add active ends to the synchronization.
			for (SinkEnd sink : node.getSinkEnds()) {
				sync.getActions().add(flow(sink));
			}
			sync.getActions().add(flow(source));
			
			// No-flow actions:
			for (SourceEnd inactive : node.getSourceEnds()) {
				if (source==inactive) continue;
				Action noflow = noflow(inactive);
				if (noflow!=null) sync.getActions().add(noflow);
			}
			
			// Add the synchronization to the choice action.
			choice.getActions().add(sync);
		}
		
		// Wrap the flow actions.
		choice = new Choice(wrapFlowAction(node,choice));

		// Additional noflow:
		addNoflow(node, choice);
		
		// Try to simplify the action a bit.
		Action action = (choice.getActions().size()==1) ? choice.getActions().get(0) : choice; 
		
		// Wrap and return the action.
		return wrapAction(node, action);
		
	}
	
	/*
	 * Subclasses may override this for customizing flow actions.
	 */
	protected Action flow(PrimitiveEnd end) {
		return atom(end);
	}
	
	/*
	 * Subclasses may override this for adding noflow actions.
	 */
	protected Action noflow(PrimitiveEnd end) {
		return null;
	}
	

	/*
	 * Subclasses may override this for adding noflow actions.
	 */
	protected Action noflow(Node node) {
		return null;
	}
	
	/*
	 * Add 'global' noflow actions for a node.
	 */
	private void addNoflow(Node node, Choice choice) {
		Action noflow = noflow(node);
		if (noflow!=null) {
			if (noflow instanceof Choice) {
				choice.getActions().addAll(((Choice) noflow).getActions());
			} else {
				choice.getActions().add(noflow);				
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.conversion.Converter#addAtoms(org.ect.reo.PrimitiveEnd, java.lang.String)
	 */
	public List<Atom> addAtoms(PrimitiveEnd end, String name) {
		List<Atom> atoms = new ArrayList<Atom>();
		atoms.add(new Atom(capitalize(name) + NODE_SUFFIX, getDataTypeManager().getGlobalDataType()));
		this.atoms.put(end, atoms);
		return atoms;
	}
	
	/*
	 * Wrap the node action into a process.
	 */
	protected Process wrapAction(Node node, Action action) {
		Process process = createProcess(node);
		process.setAction(new Sequence(action, new Instance(process)));
		return process;
	}	
	
	/*
	 * Wrap a flow action. Subclasses may override this.
	 */
	protected Action wrapFlowAction(Node node, Action flow) {
		return flow;
	}
	
}
