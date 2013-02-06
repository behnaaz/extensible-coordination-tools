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
package org.ect.convert.umlsd2reo.reocircuits;

import java.util.Iterator;
import java.util.LinkedList;


import org.ect.convert.umlsd2reo.sdmodel.*;
import org.ect.reo.*;
import org.ect.reo.channels.*;


/**
 * UMLSD to REO 
 * Project for Coordination and Component Composition Course
 * 2008 Leiden University
 * 
 * This is the model for a General circuit. A general Circuit can contain
 * several other circuit but should not be contained in any bigger circuit.
 * 
 * @author Eccher Alessandro
 *
 */
public class GeneralCircuit extends SuperConnector {
	
	Node start=new Node();
	Node end=new Node();
	
	public GeneralCircuit(SeqDiagram diagram){
		super(diagram.getName());
		
		start.setName("Starting node");
		end.setName("Ending node");
		
		addNode(start);		
		addNode(end);
		
		
		//if it is a simple diagram, just create a basic diagram
		if (!diagram.includeOperators()){
			BaseCircuit base=new BaseCircuit(diagram);
			includeSuperConnector(base);

		}
		else {
			
			LinkedList<SDAction> actions=diagram.getActions();
			Iterator<SDAction> actIter=actions.iterator();
			
			SDAction action;
			
			Node anchor=start;
			
			while(actIter.hasNext()){
				action=actIter.next();
				
				if (action instanceof SDReference){
					SDReference item=(SDReference) action;
					BaseCircuit circuit=new BaseCircuit(item.getReferenced());
					includeSuperConnector(circuit);
					
					Sync sync=new Sync(anchor,circuit.getActivationInput());
					addPrimitive(sync);
					
					anchor=circuit.getActivationOutput();
					
				} else if (action instanceof SDInterOperatorAlt){
					SDInterOperatorAlt item=(SDInterOperatorAlt) action;
					
					AlternativeCircuit circuit=new AlternativeCircuit(
							item.getFirst(),
							item.getSecond(),
							item.getGuard1(),
							item.getGuard2());
					includeSuperConnector(circuit);
					
					Sync sync=new Sync(anchor,circuit.getActivationInput());
					addPrimitive(sync);
					
					anchor=circuit.getActivationOutput();
				} else if (action instanceof SDInterOperatorPar){
					SDInterOperatorPar item=(SDInterOperatorPar) action;
					
					ParallelCircuit circuit=new ParallelCircuit(
							item.getFirst(),
							item.getSecond());
					includeSuperConnector(circuit);
					
					Sync sync=new Sync(anchor,circuit.getActivationInput());
					addPrimitive(sync);
					
					anchor=circuit.getActivationOutput();
				} else if (action instanceof SDInterOperatorLoop){
					SDInterOperatorLoop item=(SDInterOperatorLoop) action;
					
					LoopCircuit circuit=new LoopCircuit(
							item.getArgument(),item.getGuard()
							);
					includeSuperConnector(circuit);
					
					Sync sync=new Sync(anchor,circuit.getActivationInput());
					addPrimitive(sync);
					
					anchor=circuit.getActivationOutput();
				} else if (action instanceof SDInterOperatorOpt){
					SDInterOperatorOpt item=(SDInterOperatorOpt) action;
					
					OptionCircuit circuit=new OptionCircuit(
							item.getArgument());
					includeSuperConnector(circuit);
					
					Sync sync=new Sync(anchor,circuit.getActivationInput());
					addPrimitive(sync);
					
					anchor=circuit.getActivationOutput();
				} else if (action instanceof SDInterOperatorStrict){
					SDInterOperatorStrict item=(SDInterOperatorStrict) action;
					
					StrictSequencingCircuit circuit=new StrictSequencingCircuit(
							item.getFirst(),
							item.getSecond());
					includeSuperConnector(circuit);
					
					Sync sync=new Sync(anchor,circuit.getActivationInput());
					addPrimitive(sync);
					
					anchor=circuit.getActivationOutput();				
				} else if (action instanceof SDInterOperatorWeak){
					SDInterOperatorWeak item=(SDInterOperatorWeak) action;
					
					WeakSequencingCircuit circuit=new WeakSequencingCircuit(
							item.getFirst(),
							item.getSecond());
					includeSuperConnector(circuit);
					
					Sync sync=new Sync(anchor,circuit.getActivationInput());
					addPrimitive(sync);
					
					anchor=circuit.getActivationOutput();
				}			
			}
		
			Sync sync=new Sync(anchor,end);
			addPrimitive(sync);
		}
	}
}
