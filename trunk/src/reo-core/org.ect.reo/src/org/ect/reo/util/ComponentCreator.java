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
package org.ect.reo.util;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.Reo;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.semantics.ReoTextualSemantics;
import org.ect.reo.semantics.TextualSemanticsProvider;


/**
 * Component creation helper class.
 * @generated NOT
 * @author Christian Krause
 *
 */
public class ComponentCreator {
	
	/**
	 * Create a component from a connector.
	 * @generated NOT
	 */
	public static Component fromConnector(Connector connector, IProgressMonitor monitor) {
		Component component = new Component();
		convertConnector(connector, component, monitor);
		return component;
	}
	
	/**
	 * Convert a connector to a component.
	 * @generated NOT
	 */
	public static void convertConnector(Connector connector, Component target, IProgressMonitor monitor) {
		
		monitor.beginTask("Create Component",100);
		target.setName(connector.getName());
		
		// Source and sink nodes of the connector:
		EList<Node> sourceNodes = connector.getSourceNodes();
		EList<Node> sinkNodes = connector.getSinkNodes();
		
		// Remove extra ends:
		while (target.getSourceEnds().size() > sourceNodes.size()) {
			target.getSourceEnds().remove(target.getSourceEnds().size()-1);
		}
		while (target.getSinkEnds().size() > sinkNodes.size()) {
			target.getSinkEnds().remove(target.getSinkEnds().size()-1);
		}

		// Add missing ends:
		while (target.getSourceEnds().size() < sourceNodes.size()) {
			target.getSourceEnds().add(new SourceEnd());
		}
		while (target.getSinkEnds().size() < sinkNodes.size()) {
			target.getSinkEnds().add(new SinkEnd());
		}

		// Initialize names for the component ends:
		for (int i=0; i<sourceNodes.size(); i++) {
			target.getSourceEnd(i).setName(sourceNodes.get(i).getName());
		}
		for (int i=0; i<sinkNodes.size(); i++) {
			target.getSinkEnd(i).setName(sinkNodes.get(i).getName());
		}
		
		// Generate proper names now:
		PrimitiveEndNames names = new PrimitiveEndNames();
		for (int i=0; i<sourceNodes.size(); i++) {
			String name = names.generate(target.getSourceEnd(i));
			target.getSourceEnd(i).setName(name);
			for (SinkEnd end : sourceNodes.get(i).getSinkEnds()) {
				names.put(end,name);
			}
		}
		for (int i=0; i<sinkNodes.size(); i++) {
			String name = names.generate(target.getSinkEnd(i));
			target.getSinkEnd(i).setName(name);
			for (SourceEnd end : sinkNodes.get(i).getSourceEnds()) {
				names.put(end,name);
			}
		}
		
		// Generate the names for the rest of the connector as well.
		names.generate(connector);
		monitor.worked(10); // 10%
		
		
		// Now compute the semantics.
		int progress = 90;
		int count = ReoTextualSemantics.REGISTRY.size();
		if (count==0) {
			monitor.worked(progress);
		} else {
			for (TextualSemanticsProvider provider : ReoTextualSemantics.REGISTRY) {
				String semantics = provider.computeConnectorSemantics(connector, names, new SubProgressMonitor(monitor,progress / count));
				if (semantics==null) {
					Reo.logError("Textual semantics provider " + provider.getClass() + " return null semantics.");
				} else {
					String key = provider.getSemanticsKey();
					if (key==null || key.trim().equals("")) {
						Reo.logError("Textual semantics provider " + provider.getClass() + " return empty key.");						
					} else {
						PropertyHelper.setOrAddHidden(target, key, semantics);
					}
				}
			}
		}
		
		monitor.done();
		
	}
		
}
