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
package org.ect.codegen.reo2ea.libraryprovider;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.portnames.AutomatonPortNames;
import org.ect.ea.extensions.portnames.AutomatonPortNamesProvider;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.PrimitiveEnd;
import org.ect.reo.SinkEnd;
import org.ect.reo.SourceEnd;
import org.ect.reo.animation.Animation;
import org.ect.reo.animation.AnimationPrinter;
import org.ect.reo.animation.AnimationSemanticsProvider;
import org.ect.reo.animation.AnimationTable;
import org.ect.reo.libraries.LibraryProvider;
import org.ect.reo.libraries.ReoLibraryUtil;
import org.ect.reo.util.PropertyHelper;
import static org.ect.reo.colouring.FlowColour.*;

public class AutomataLibraryProvider implements LibraryProvider {
	private Map<URI, Automaton> cache = new WeakHashMap<URI, Automaton>();
	private Component component;
	private Animation tau;	
	
	/**
	 * check whether the component and automaton interfaces are compatible
	 */
	public boolean canCreatePrimitive(CustomPrimitive stub) {
		try {			 
			Automaton a = loadTypeURI(stub);			
			if (a!=null && checkPorts(a, stub)) {
				cache.put(ReoLibraryUtil.getTypeURI(stub), a);
				return true;
			}
		} catch (Exception t) {				
			return false;
		}
		return false;
	}

	public static Automaton loadTypeURI(CustomPrimitive stub)
			throws IOException, IllegalArgumentException {
		URI uri= ReoLibraryUtil.getAbsoluteTypeURI(stub);
		String name= uri.fragment();// fragment is the target automaton name.
		if (uri==null || name ==null)
			return null;
		
		Resource resource = new ResourceSetImpl().getResource(uri.trimFragment(), true);
		resource.load(Collections.EMPTY_MAP);
		for (Automaton a: ((Module)resource.getContents().get(0)).getAutomata())
			if (a.getName().equals(name)) 
				return a;
		
		throw new IllegalArgumentException("automaton not found");
	}

	private boolean checkPorts(Automaton a, CustomPrimitive stub) {
		AutomatonPortNames ports = (AutomatonPortNames) a.findExtension(AutomatonPortNamesProvider.EXTENSION_ID);
		Set<String> inp = new HashSet<String>(ports.getInPorts());
		Set<String> outp = new HashSet<String>(ports.getOutPorts());		
		
		for (SinkEnd sink : stub.getSinkEnds())
			if (!outp.contains(sink.getName()) )
				return false;
		for (SourceEnd source: stub.getSourceEnds())
			if (!inp.contains(source.getName()) )
				return false;
		
		return true;
	}
	
	public CustomPrimitive createPrimitive(CustomPrimitive stub,
			IProgressMonitor monitor) {
		URI uri = ReoLibraryUtil.getAbsoluteTypeURI(stub);
		if (!cache.containsKey(uri))
			return null;

		component = (Component) EcoreUtil.copy(stub);
		PropertyHelper.removeAll(component,ReoLibraryUtil.TYPE_URI_KEY);
		tau = new Animation();
		for (PrimitiveEnd end : component.getAllEnds()) 
			tau.setColour(end, NO_FLOW_GIVE_REASON_LITERAL);
					
		Automaton a = cache.get(uri);
		for (State s: a.getStates()) {
			BooleanExtension ss = (BooleanExtension) s.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			if (ss.getValue()) {
				AnimationTable table = traverse(s, new HashMap<State, AnimationTable>());
				AnimationPrinter printer = new AnimationPrinter(component);
				String definition = printer.printAllTables(table);
				AnimationSemanticsProvider.setAnimationDefinition(component, definition);
				break;
			}
		}

		return component;	
	}

	private AnimationTable traverse(State s, Map<State, AnimationTable> visited) {
		if (visited.containsKey(s)) 
			return visited.get(s);
		
		AnimationTable at = new AnimationTable(s.getName());
		Animation self = (Animation) EcoreUtil.copy(tau);
		self.setNextAnimationTable(at);
		at.add(self);
		visited.put(s, at);
		
		for (Transition t: s.getOutgoing()) {
			Animation animation = createAnimation(t);
			at.add(animation);
			AnimationTable next = traverse(t.getTarget(), visited);			
			animation.setNextAnimationTable(next);
		}
		
		AnimationSemanticsProvider.createAnimationSteps(at);
		return at;
	}

	private Animation createAnimation(Transition t) {
		Animation anim = new Animation();
		TransitionPortNames tp = (TransitionPortNames) t.findExtension(TransitionPortNamesProvider.EXTENSION_ID);
		Set<String> ports = new HashSet<String>(tp.getValues());
		
		for (PrimitiveEnd end : component.getAllEnds()) {
			if (ports.contains(end.getName()))
				anim.setColour(end, FLOW_LITERAL);
			else
				anim.setColour(end, NO_FLOW_GIVE_REASON_LITERAL);			
		}
		
		return anim;
	}

	public boolean canCreateConnector(CustomPrimitive stub) {
		return false;
	}

	public Connector createConnector(CustomPrimitive stub,
			IProgressMonitor monitor) {
		return null;
	}

}
