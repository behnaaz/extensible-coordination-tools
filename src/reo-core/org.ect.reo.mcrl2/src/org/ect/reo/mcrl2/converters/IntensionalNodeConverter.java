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

import org.eclipse.emf.common.util.EList;
import org.ect.reo.Node;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.mcrl2.Atom;
import org.ect.reo.mcrl2.Choice;
import org.ect.reo.mcrl2.Implication;
import org.ect.reo.mcrl2.Instance;
import org.ect.reo.mcrl2.Process;
import org.ect.reo.mcrl2.Sequence;
import org.ect.reo.mcrl2.Sort;
import org.ect.reo.mcrl2.Synchronization;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class IntensionalNodeConverter extends AbstractNodeConverter {

	@Override
	protected Process caseReplicateNode(Node node) {
		return caseOneToN(node, SinkEnd.class, SourceEnd.class);
	}
	
	@Override
	protected Process caseJoinNode(Node node) {
		return caseOneToN(node, SourceEnd.class, SinkEnd.class);
	}
	
	
	protected Process caseOneToN(Node node, Class<?> type1, Class<?> typeN) {
		
		// Create process.
		Process process = createProcess(node);
		
		// Add request parameters.
		EList<PrimitiveEnd> ends = node.getAllEnds();
		Atom[] params = IntensionalChannelConverter.addRequestParams(
								process, ends.size(), (char) ('z'-ends.size()+1));
		
		// Create a choice and add request arrivals.
		Choice choice = new Choice();
		for (int i=0; i<ends.size(); i++) {
			choice.getActions().add(IntensionalChannelConverter.requestArrival(process, params[i], atom(ends.get(i), 0)));
		}
		
		for (int i=0; i<ends.size(); i++) {
			
			// Now we want only the sink ends.
			if (!type1.isInstance(ends.get(i))) continue;
			
			// Merge inputs.
			Implication merge = new Implication();
			merge.setCondition(params[i].getName());
			
			Implication replicate = new Implication();
			merge.setAction(replicate);
			
			Synchronization fire = new Synchronization();
			Instance restart = new Instance(process);
			
			fire.getActions().add(atom(ends.get(i), 1));
			
			for (int j=0; j<ends.size(); j++) {
				
				// Now we want only the source ends.
				if (!typeN.isInstance(ends.get(j))) {
					restart.getArguments().add((i==j) ? "!" + params[j].getName() : params[j].getName());
					continue;
				}
				
				fire.getActions().add(atom(ends.get(j), 1));
				restart.getArguments().add("!" + params[j].getName());
				
				replicate.setCondition(params[j].getName());
				replicate.setAction(replicate = new Implication());
			}
			if (replicate.eContainer() instanceof Implication) {
				replicate = (Implication) replicate.eContainer();
			}
						
			replicate.setAction(new Sequence(fire, restart));
			choice.getActions().add(merge);
		}
		
		process.setAction(choice);
		return process;
		
	}
	
	
	@Override
	protected Process caseRouteNode(Node node) {
		
		// Create process.
		Process process = createProcess(node);
		
		// Add request parameters.
		EList<PrimitiveEnd> ends = node.getAllEnds();
		Atom[] params = IntensionalChannelConverter.addRequestParams(
								process, ends.size(), (char) ('z'-ends.size()+1));
		
		// Create a choice and add request arrivals.
		Choice choice = new Choice();
		for (int i=0; i<ends.size(); i++) {
			choice.getActions().add(IntensionalChannelConverter.requestArrival(process, params[i], atom(ends.get(i), 0)));
		}
		
		for (int i=0; i<ends.size(); i++) {
			
			// Now we want only the sink ends.
			if (!(ends.get(i) instanceof SinkEnd)) continue;
						
			for (int j=0; j<ends.size(); j++) {
				
				// Now we want only the source ends.
				if (!(ends.get(j) instanceof SourceEnd)) continue;
				
				// Merge inputs.
				Implication in = new Implication();
				Implication out = new Implication();
				
				in.setCondition(params[i].getName());
				out.setCondition(params[j].getName());
				
				in.setAction(out);
				
				Synchronization fire = new Synchronization();
				Instance restart = new Instance(process);
				
				fire.getActions().add(atom(ends.get(i), 1));
				fire.getActions().add(atom(ends.get(j), 1));
				
				for (int k=0;k<ends.size(); k++) {
					String arg = (k==i || k==j) ? "!" + params[k].getName() : params[k].getName();
					restart.getArguments().add(arg);
				}
				
				out.setAction(new Sequence(fire, restart));
				choice.getActions().add(in);
			}
		}
		
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
		atoms.add(new Atom(REQUEST_PREFIX + capitalize(name) + BasicNodeConverter.NODE_SUFFIX, dataType));
		atoms.add(new Atom(FIRE_PREFIX + capitalize(name) + BasicNodeConverter.NODE_SUFFIX, dataType));
		this.atoms.put(end, atoms);
		return atoms;
	}	
	
}
