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
package org.ect.reo.mcrl2.conversion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Primitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.mcrl2.converters.ElementConverter;
import org.ect.reo.mcrl2.util.PrimitiveEndAtoms;
import org.ect.reo.util.PropertyHelper;
import org.ect.reo.util.ReoTraversal;
import org.ect.reo.util.ReoTraversal.TraversalType;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class PrimitiveEndCommunications {
	
	public static void addCommunications(Network network, ElementConverter converter, IProgressMonitor monitor) {
		
		InitProcesses init = new InitProcesses(network, converter);
		if (init.getStart()==null) return;
		
		// Add synchronizations by traversing the connector.
		TraversalType type = Reo2MCRL2Preferences.traversalType();
		if (type!=null) {
			
			ReoTraversal traversal = ReoTraversal.create(type);
			traversal.addWorker(init);
			traversal.start(network.getAllPrimitives().size(), network.getAllNodes().size(), monitor);
			
		} else {
			List<Connectable> elements = new ArrayList<Connectable>();
			PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
			List<PrimitiveEnd> hidden = new ArrayList<PrimitiveEnd>();
			
			for (Primitive primitive : network.getAllPrimitives()) {
				atoms.putAll(init.getSynchronizations(primitive.getAllEnds()));
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
			converter.synchronize(initName(), null, atoms, elements, hidden);
			
		}
		
	}
	
	
	public static void addCommunications(Connector connector, ElementConverter converter, 
										IProgressMonitor monitor) {
		
		InitProcesses init = new InitProcesses(connector, converter);
		if (init.getStart()==null) return;

		// Add synchronizations by traversing the connector.
		TraversalType type = Reo2MCRL2Preferences.traversalType();
		if (type!=null) {
			
			ReoTraversal traversal = ReoTraversal.create(type);
			traversal.addWorker(init);
			traversal.getBorderElements().addAll(connector.getForeignPrimitives());
			traversal.getBorderElements().addAll(connector.getForeignNodes());
			traversal.start(connector.getPrimitives().size(), connector.getNodes().size(), monitor);
			
		} else {
			
			List<Connectable> elements = new ArrayList<Connectable>();
			PrimitiveEndAtoms atoms = new PrimitiveEndAtoms();
			List<PrimitiveEnd> hidden = new ArrayList<PrimitiveEnd>();
			
			for (Primitive primitive : connector.getPrimitives()) {
				atoms.putAll(init.getSynchronizations(primitive.getAllEnds()));
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
			converter.synchronize(initName(), null, atoms, elements, hidden);
		}

	}
	
	private static String initName() {
		return InitProcesses.INIT_NAME + "0"; // to avoid name clashes with actions 
	}
}
