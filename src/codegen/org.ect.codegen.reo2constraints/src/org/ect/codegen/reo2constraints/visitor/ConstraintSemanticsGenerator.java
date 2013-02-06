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
package org.ect.codegen.reo2constraints.visitor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import org.ect.codegen.reo2constraints.generator.Constraint;
import org.ect.codegen.reo2constraints.generator.Preprocessing;
import org.ect.codegen.reo2constraints.generator.StateManager;
import org.ect.codegen.reo2constraints.generator.Util;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.Reo;
import org.ect.reo.util.PropertyHelper;
import org.ect.reo.util.ReoTraversal;
import org.ect.reo.util.ReoTraversal.TraversalType;

/**
 * @author Behnaz Changizi
 * 
 */
public class ConstraintSemanticsGenerator {
	protected Module mod;
	protected Preprocessing preprocssinfo;
	private StateManager statemgr;
	public StateManager getStatemgr() {
		return statemgr;
	}

	public Preprocessing getPreprocssinfo() {
		return preprocssinfo;
	}

	public ConstraintSemanticsGenerator(Module mod) {
		this.mod = mod;
		this.preprocssinfo = new Preprocessing(mod, Util.ReduceVarPrefix);							
	    this.statemgr = new StateManager(null, preprocssinfo.getFIFOIds(), preprocssinfo.getTimerIds(), preprocssinfo.getInitialState());
	}

	
/*
public static void addCommunications(Network network, IProgressMonitor monitor) {
		
	//	ConstraintsTraversalWorker init = new ConstraintsTraversalWorker(new InfoCenter(mod, Util.ReduceVarPrefix));
		if (init.getStart()==null) return;
		
		// Add synchronizations by traversing the connector.
		TraversalType type = traversalType();
		if (type!=null) {
			
			ReoTraversal traversal = ReoTraversal.create(type);
			traversal.addWorker(init);
			traversal.start(network.getAllPrimitives().size(), network.getAllNodes().size(), monitor);
			
		} else {
			List<Connectable> elements = new ArrayList<Connectable>();
		//	PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
			List<PrimitiveEnd> hidden = new ArrayList<PrimitiveEnd>();
			
			for (Primitive primitive : network.getAllPrimitives()) {
			//	atoms.putAll(init.getSynchronizations(primitive.getAllEnds()));
				for (PrimitiveEnd end : primitive.getAllEnds()) {
					if (!end.isComponentEnd()) {
						Boolean hide = true;
						String hideValue = PropertyHelper.getFirstValue(end.getNode(), "hide"); 
						if (hideValue!=null) {
			                hide = Boolean.parseBoolean(hideValue);
			            } 
						if (hide)
							hidden.add(end);
					}
				}
			}
			
			elements.addAll(network.getAllPrimitives());
			elements.addAll(network.getAllNodes());
			
		}
		
	}
	*/
	
	private static TraversalType traversalType() {
		return ReoTraversal.TraversalType.DEPTH_FIRST;
	}

/*how to caal // Add the communications.
		PrimitiveEndCommunications.addCommunications(connector, converter, monitor);
		
		// Set the initial process.
		Specification spec = converter.getSpecification();
		setInitialProcess(spec);
		spec.format();		
		return spec.toString();
		yeki dige ino farakhani kone in void bargardoone amma un javabo
		*/
	public Constraint computeConnectorSemantics(Connector connector, IProgressMonitor monitor) {
		//???????????????Connector connector = refine(cnn);
		ConstraintsTraversalWorker init = new ConstraintsTraversalWorker(connector, this.preprocssinfo, this.statemgr);
		if (init.getStart()==null) return null;

		// Add synchronizations by traversing the connector.
		TraversalType type = traversalType();
		if (type!=null) {
			
			ReoTraversal traversal = ReoTraversal.create(type);
			traversal.addWorker(init);
			traversal.getBorderElements().addAll(connector.getForeignPrimitives());
			traversal.getBorderElements().addAll(connector.getForeignNodes());
			traversal.start(connector.getPrimitives().size(), connector.getNodes().size(), monitor);
			
		} else {
			
			List<Connectable> elements = new ArrayList<Connectable>();
		//	PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
			List<PrimitiveEnd> hidden = new ArrayList<PrimitiveEnd>();
			
			for (Primitive primitive : connector.getPrimitives()) {
		//		atoms.putAll(init.getSynchronizations(primitive.getAllEnds()));
				for (PrimitiveEnd end : primitive.getAllEnds()) {
					if (connector.getNodes().contains(end.getNode())) {
								Boolean hide = true;
								String hideValue = PropertyHelper.getFirstValue(end.getNode(), "hide"); 
								if (hideValue!=null) {
					                hide = Boolean.parseBoolean(hideValue);
					            } 
								if (hide)
									hidden.add(end);
					}
				}
			}
			
			elements.addAll(connector.getPrimitives());
			elements.addAll(connector.getNodes());
	//		converter.synchronize(initName(), null, atoms, elements, hidden);
		}
		Reo.logInfo(init.getFinalEncoding().toString());
		return init.getFinalEncoding();

	}	

	
	

	/* TODO 
		public static String replaceReduceFriendlyNamesByReoOriginalOnes(
			String redFriendlyFormula, Map<String, String> newOldNames) {
		String res = redFriendlyFormula;
		for (String newName : newOldNames.keySet()) {
			res = res.replaceAll(newName, newOldNames.get(newName));
		}
		return res;
	}
	

	public String getProblems(){
		return preprocssinfo.getProblems();
	}
		*/
}
//TODO: check if the name of a node is not ok