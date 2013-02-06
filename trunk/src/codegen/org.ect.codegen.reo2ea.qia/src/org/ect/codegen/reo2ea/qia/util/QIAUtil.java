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
package org.ect.codegen.reo2ea.qia.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.codegen.reo2ea.util.XMIWrangler;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.portnames.IntensionalPortNames;
import org.ect.ea.extensions.portnames.IntensionalPortNamesProvider;

public class QIAUtil {
	public static void trim(Automaton automaton) {
		Set<State> reachable = new HashSet<State>();
		for (State s: automaton.getStates())
			if(QIA.isStartState(s))
				reachable.addAll(
						traverse(s));
		
		Set<Transition> dangling = new HashSet<Transition>();
		for (State s: automaton.getStates()) 
			if (!reachable.contains(s)) {
				dangling.addAll(s.getOutgoing());				
			}
		
		remove(dangling);
		automaton.getStates().retainAll(reachable);
	}

	private static void remove(Collection<Transition> redundant) {
		for (Transition t: redundant) {
//			assert !reachable.contains(t.getSource());
			t.setTarget(null);
			t.setSource(null);
			EcoreUtil.remove(t);
		}
	}

	private static Set<State> traverse(State s) {
		Set<State> visited = new HashSet<State>();
		visited.add(s);
		List<Transition> neighbours = new ArrayList<Transition>(s.getOutgoing());
		
		while(!neighbours.isEmpty()) {
			State current = neighbours.remove(0).getTarget();
			if (visited.add(current))
				neighbours.addAll(current.getOutgoing());
		}
		return visited;
	}
	
	/**Clean up result of product computation
	 * @param automaton
	 */
	public static void beautify(Automaton automaton, String prefix) {
		Collections.sort(QIA.getPortNames(automaton).getValues());
		
		int i=0;
		for (State s: automaton.getStates())
			s.setName(prefix + ++i);
	}

	public static void mergeTrans(Automaton a) {
		Set<Transition> redundant = new HashSet<Transition>();
		
		for (Transition t0 : a.getTransitions()) {
			if (redundant.contains(t0))
				continue;
			
			Set<Transition> src = new HashSet<Transition>(t0.getSource().getOutgoing()),
							tgt = new HashSet<Transition>((t0.getTarget().getIncoming()));
			src.retainAll(tgt);
			src.removeAll(redundant);
			src.remove(t0);
			
			for (Transition t1 : src) 
				if (tryMerge(t0, t1)) 
					redundant.add(t1);				
		}
		
		remove(redundant);	
	}
	
	private static boolean tryMerge(Transition t0, Transition t1) {
//		if (t0==t1 || t0.getSource()!=t1.getSource() || t0.getTarget()!=t1.getTarget())
//			return false;
		assert t0!=t1 && t0.getSource()==t1.getSource() && t0.getTarget()==t1.getTarget();
		
		IntensionalPortNames ip0 = (IntensionalPortNames) t0.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		IntensionalPortNames ip1 = (IntensionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
		Collection<String> ip0Requests = ip0.getRequests(),
				ip0Firings = ip0.getFirings(),
				ip1Requests = ip1.getRequests(),
				ip1Firings = ip1.getFirings();

		if (ip0Requests.size()==ip1Requests.size() && new HashSet<String>(ip0Requests).containsAll(ip1Requests) &&
				ip0Firings.size()==ip1Firings.size() && new HashSet<String>(ip0Firings).containsAll(ip1Firings)){
			/*Constraint con0 = (Constraint) t0.findExtension(ConstraintExtensionProvider.EXTENSION_ID),
			con1 = (Constraint) t1.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
			//						 System.err.println(src+"->"+con1+"|"+con2+"->"+tgt);

			Disjunction dis = new Disjunction();
			dis.getMembers().add(con0);
			dis.getMembers().add(con1);

			t0.updateExtension(new Simplification().perform(dis));*/
			return true;
		} 
//		else
		return false;
	}

	public static void main(String[] args) throws IOException {
//		XMIWrangler.standaloneInit();
		Automaton a = XMIWrangler.loadAutomata("/var/tmp/workspace/baz/fifox2.ea").getAutomata().get(1);
		System.out.println(QIA.prettyPrint(a));
		QIAUtil.trim(a);
		
		System.out.println(QIA.prettyPrint(a));
	}
}
