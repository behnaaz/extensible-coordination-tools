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
package org.ect.codegen.reo2ea.transform;

import static org.ect.codegen.reo2ea.core.ITransformation.IDENITIY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;

import org.ect.codegen.reo2ea.core.INamingPolicyFactory;
import org.ect.codegen.reo2ea.core.ITransformation;
import org.ect.codegen.reo2ea.core.TransformationException;
import org.ect.ea.IProductProvider;
import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.reo.Component;
import org.ect.reo.Connectable;
import org.ect.reo.Connector;
import org.ect.reo.Network;
import org.ect.reo.Node;
import org.ect.reo.Primitive;

public class Composition {
	public class TransformResult{
		public List<Automaton> automata;
		public EndNamingPolicy namingPolicy;
		
		public TransformResult(List<Automaton> automata,EndNamingPolicy namingPolicy) {
			this.automata=automata; this.namingPolicy=namingPolicy;
		}
	}
	
	private ITransformation<Connectable> trans;
	private IProductProvider pp;
	private INamingPolicyFactory npf;
	
	public Set<String> locals = new HashSet<String>();
	
	public Composition(ITransformation<Connectable> trans, IProductProvider pp, INamingPolicyFactory npf) {
		this.trans = trans;
		this.pp = pp;
		this.npf = npf;
	}
	
	public TransformResult transform(Network net) throws TransformationException {
		List<Automaton> automata = new ArrayList<Automaton>();
		EndNamingPolicy enp = npf.newInstance();
		TransformResult result = new TransformResult(automata, enp);
		
		for (Connector c: net.getConnectors()) 
			automata.addAll(transform(c, enp).automata);
		
		for (Component c: net.getComponents()) {
			Automaton a = trans.transform(c);
			if (a!=IDENITIY)
				automata.add(a);					
		}
								
		return result;
	}
	
	public TransformResult transform(Connector con) throws TransformationException {
		return transform(con, npf.newInstance());
	}
	
	/** 
	 * @return the List of CA's corresponding to the primitives 
	 * and mixed nodes in connector
	 * @throws TransformationException 
	 */
	private TransformResult transform(Connector con, EndNamingPolicy enp) throws TransformationException {
		List<Automaton> automata = new ArrayList<Automaton>();
		trans.setEndNamingPolicy(enp);
		TransformResult result = new TransformResult(automata, enp);
		
		for (Node n: con.getNodes()) {
			Automaton a = trans.transform(n);
			if (a!=IDENITIY) {
				automata.add(a);
			}
		}
		for (Primitive p: con.getPrimitives()) {
			Automaton a = trans.transform(p);
			if (a!=IDENITIY) { 
				automata.add(a);
			}
		}
		
		return result;
	}
	

	/**
	 * Compute product in using the following heuristic. Find two automata
	 * with at least one port name in common and compute their product.
	 * Repeat until list has just one automaton left.
	 */
	public Automaton compose(List<Automaton> automata, IProgressMonitor monitor) {
		List<Automaton>	prod = new ArrayList<Automaton>(automata);	
		Automaton a1, a2=null;
		Set<String> local = new HashSet<String>();
		
		while (prod.size()>1) {
			a1 = prod.remove(0);	
			Set<String> ports = new HashSet<String> (
					CA.getPortNames(a1).getValues());
			Set<String> common = new HashSet<String>(ports);
			
			Iterator<Automaton> it = prod.iterator();
			while (it.hasNext()) {
				a2 = it.next();
				//Find an automaton with at least one port name in common
				if (ports.removeAll(
						CA.getPortNames(a2).getValues())) 
					break;
			}
			assert a2!=null;
			prod.remove(a2);
			common.retainAll(CA.getPortNames(a2).getValues());
			local.addAll(common);
			
			/*System.err.println(prod.size()+":\t"+a1.getStates().size() +"; "+a1.getTransitions().size() +CA.getPortNames(a1).getValues()+
					"\t"+a2.getStates().size() +"; "+a2.getTransitions().size() +CA.getPortNames(a2).getValues());*/
			System.err.println(prod.size()+":\t"+a1.getStates().size() +"; "+a1.getTransitions().size() +
					"\t"+a2.getStates().size() +"; "+a2.getTransitions().size()+"\t"+System.currentTimeMillis());
			prod.add( pp.computeProduct(a1, a2, monitor) );
		}
		
//		Automaton product = a1;
		Automaton product = prod.remove(0);
		pp.doPostProcessing(product, monitor);
		AutomatonPortNames Aports = (AutomatonPortNames) product.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		Set<String> remain = new HashSet<String>(Aports.getValues());
		remain.removeAll(local);
		setLocalPorts(remain);
		
		/*//printing a product result
		for(State a : product.getStates()){
			for(Transition t:a.getOutgoing()){
				IntensionalPortNames iports = (IntensionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				System.out.println(t.getSource().getName()+ " ; "+iports.getRequests()+"|"+iports.getFirings()+" ; "+t.getTarget().getName());
			}
		}*/
		
		assert prod.isEmpty();
		return product;
	}	
	
	private void setLocalPorts(Set<String> set){
		locals = set;
	}
	
	public Set<String> getLocalPorts(){
		return locals;
	}
}
