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
package org.ect.codegen.reo2ea.ca;

import static org.ect.codegen.reo2ea.util.ReoUtil.node2PortName;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.ect.codegen.reo2ea.ca.hiding.Hiding;
import org.ect.codegen.reo2ea.ca.transform.CANamingPolicyFactory;
import org.ect.codegen.reo2ea.ca.transform.channels.ChannelTransform;
import org.ect.codegen.reo2ea.ca.transform.channels.DataTransform;
import org.ect.codegen.reo2ea.ca.transform.channels.FifoTransform;
import org.ect.codegen.reo2ea.ca.transform.channels.FilterTransform;
import org.ect.codegen.reo2ea.ca.transform.components.ComponentTransform;
import org.ect.codegen.reo2ea.ca.transform.components.ReaderTransform;
import org.ect.codegen.reo2ea.ca.transform.components.WriterTransform;
import org.ect.codegen.reo2ea.ca.transform.nodes.Replicate;
import org.ect.codegen.reo2ea.ca.transform.nodes.Route;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.codegen.reo2ea.transform.Composition;
import org.ect.codegen.reo2ea.transform.EndNamingPolicy;
import org.ect.codegen.reo2ea.transform.Transformation;
import org.ect.codegen.reo2ea.transform.Composition.TransformResult;
import org.ect.codegen.reo2ea.util.CAUtil;
import org.ect.codegen.reo2ea.util.ReoUtil;

import org.ect.ea.IProductProvider;
import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.startstates.ReachableStatesProduct;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Node;

/**
 * Reo2CA needs the extension registry, which is not always available.
 * This class works without the extension registry.
 * @author christian
 *
 */
public class Reo2CAStandalone {

	public static boolean DEBUG = true;	
	private static final String DEF_PREFIX = "s";
	public static final Composition composition; 
	
	static {
		// Initialize the composition object:
		Transformation<Connectable> trafo = new Transformation<Connectable>();
		trafo.addDelegate("org.ect.reo.channels.Channel", new ChannelTransform());
		trafo.addDelegate("org.ect.reo.channels.FIFO", new FifoTransform());
		trafo.addDelegate("org.ect.reo.channels.Transform", new DataTransform());
		trafo.addDelegate("org.ect.reo.channels.SyncDrain", new DataTransform());
		trafo.addDelegate("org.ect.reo.channels.Filter", new FilterTransform());
		trafo.addDelegate("REPLICATE", new Replicate());
		trafo.addDelegate("ROUTE", new Route());
		trafo.addDelegate("org.ect.reo.Component", new ComponentTransform());
		trafo.addDelegate("org.ect.reo.components.Reader", new ReaderTransform());
		trafo.addDelegate("org.ect.reo.components.Writer", new WriterTransform());
		
		IProductProvider product = new ReachableStatesProduct();
		CANamingPolicyFactory naming = new CANamingPolicyFactory();
		
		composition = new Composition(trafo, product, naming);
	}
	
	protected Connector connector;
	
	public Reo2CAStandalone(Connector c) throws Exception {			
		connector = c;
	}

	public Automaton getResult() throws TransformationException {
		TransformResult transformed = composition.transform(connector);
		Automaton ca= composition.compose(transformed.automata, new NullProgressMonitor());
//		beautify(ca, "S");
		Automaton hidden = hideInternals(ca , transformed.namingPolicy);
		setPortTypes(hidden);		
		CAUtil.trim(hidden);
		beautify(hidden, null);
		CAUtil.mergeTrans(hidden);		
		return hidden;
	}	
	
	public Automaton hideInternals(Automaton a, EndNamingPolicy enc)	{		
		CAUtil.trim(a);
		Set<String> boundary = new HashSet<String>();
		for (Node n: connector.getNodes()) 
			if (n.isSinkNode() || n.isSourceNode())
				boundary.add(ReoUtil.node2PortName(n));
/*		
		HideOperation hider = new HideOperation(a);
		for (String name: enc.getAllNames().values())
			if (!boundary.contains(name)) {
				System.err.println("Hiding "+ name);
				a = hider.apply(name);
				boundary.add(name);
			}
			
		return a;
*/			
		HashSet<String> internal = new HashSet<String>(enc.getAllNames().values());
		internal.removeAll(boundary);
		beautify(a,"s");
		return Hiding.getResult(a, internal);
	}
	
	public Automaton beautify(Automaton a, String statePrefix){				
		a.setName(connector.getName());
		
//		if (null==statePrefix && null==connector.getName())
//			statePrefix = DEF_PREFIX;
//		else
//			statePrefix = connector.getName().trim().toLowerCase().replaceAll("\\W", "");
		CAUtil.beautify(a, DEF_PREFIX);
		
		return a;
	}

	public Automaton setPortTypes(Automaton a) {
		//Set port types used by CASP
		for (Node node : connector.getNodes()) 
			if (node.isSinkNode())
				CA.setPortType(node2PortName(node), CA.PortType.OUT_PORT, a);
			else if (node.isSourceNode())
				CA.setPortType(node2PortName(node), CA.PortType.IN_PORT, a);
		
		return a;
	}		

}
