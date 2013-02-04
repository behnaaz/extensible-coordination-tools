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

import org.ect.reo.Node;
import org.ect.reo.NodeType;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.colouring.Colouring;
import org.ect.reo.colouring.ColouringTable;
import org.ect.reo.colouring.FlowColour;
import org.ect.reo.mcrl2.Action;
import org.ect.reo.mcrl2.AtomicAction;
import org.ect.reo.mcrl2.Choice;
import org.ect.reo.mcrl2.Implication;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Summation;
import org.ect.reo.mcrl2.Synchronization;
import org.ect.reo.mcrl2.datatypes.DataTypeManager;
import org.ect.reo.mcrl2.datatypes.GlobalDataType;
import org.ect.reo.semantics.ReoScope;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ColouredNodeConverter extends DataNodeConverter {

	// We need a helper:
	private ColouredConverterHelper helper = new ColouredConverterHelper();
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicNodeConverter#wrapAction(org.ect.reo.Node, org.ect.reo.mcrl2.Action)
	 */
	@Override
	protected Process wrapAction(Node node, Action action) {
		// OMMIT THE SUMMATION HERE:
		Process process = createProcess(node);			
		Sequence result = new Sequence(action, new Instance(process));
		process.setAction(result);
		return process;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicNodeConverter#noflow(org.ect.reo.PrimitiveEnd)
	 */
	@Override
	protected Action noflow(PrimitiveEnd end) {
		return atom(end, helper.noflowG());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataNodeConverter#flow(org.ect.reo.PrimitiveEnd)
	 */
	@Override
	protected Action flow(PrimitiveEnd end) {		
		
		// Get the real datatype:
		Sort realDataType = helper.getRealDataType();	
		AtomicAction atom;
		
		// Special case: join-nodes.
		if (end.getNode().getType()==NodeType.JOIN && realDataType instanceof GlobalDataType) {
			atom = joinAtom(end, (GlobalDataType) realDataType);
		} else {
			atom = super.atom(end,var());
		}
		
		// We definitly have flow here:
		if (atom.getArguments().isEmpty()) {
			atom.getArguments().add(var());
		}
		
		return atom;
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicNodeConverter#noflow(org.ect.reo.Node)
	 */
	@Override
	protected Action noflow(Node node) {
		
		Choice choice = new Choice();
		
		// Compute the colouring table:
		ColouringTable table = node.getColouringTable();
		for (Colouring colouring : table.getColourings()) {
			
			// We are interested in the noflow colourings:
			if (!colouring.hasFlow()) {

				// New synchronization (multi-action):
				Synchronization sync = new Synchronization();
				
				for (PrimitiveEnd end : node.getAllEnds()) {
					FlowColour color = colouring.getColour(end);
					
					// If we are in connector scope and we have a boundary node we must switch the color at the boundary.
					if (scope==ReoScope.CONNECTOR) {
						if ((node.isSourceNode() && (end instanceof SinkEnd)) || 
							(node.isSinkNode() && (end instanceof SourceEnd))) {
							color = switchNoFlow(color);
						}
					}
					
					String value = (color==FlowColour.NO_FLOW_GIVE_REASON_LITERAL) ? helper.noflowR() : helper.noflowG();
					sync.getActions().add(atom(end,value));
				}
				choice.getActions().add(sync);
				
			}	
		}
		return choice;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.BasicNodeConverter#wrapFlowAction(org.ect.reo.mcrl2.Action)
	 */
	@Override
	protected Action wrapFlowAction(Node node, Action flow) {
		Summation summation = createSummation(node, helper.getColoured());
		String expression = helper.isFlow(summation.getParameters().get(0).getName());
		for (int i=0; i<summation.getParameters().size(); i++) {
			expression = expression + " && " + helper.isFlow(summation.getParameters().get(i).getName());
		}
		return new Sequence(summation,new Implication(helper.isFlow(var()), flow));
	}
	
	/*
	 * Switch a no-flow colour.
	 */
	private FlowColour switchNoFlow(FlowColour colour) {
		if (colour==FlowColour.NO_FLOW_GIVE_REASON_LITERAL) return FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL;
		if (colour==FlowColour.NO_FLOW_REQUIRE_REASON_LITERAL) return FlowColour.NO_FLOW_GIVE_REASON_LITERAL;
		return colour;
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
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.reo.mcrl2.converters.DataNodeConverter#var()
	 */
	@Override
	protected String var() {
		return "c";
	}
	
}
