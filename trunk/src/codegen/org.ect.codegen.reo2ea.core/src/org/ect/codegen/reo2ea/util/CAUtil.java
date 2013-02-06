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
package org.ect.codegen.reo2ea.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.constraints.CA;
import org.ect.ea.extensions.constraints.Constraint;
import org.ect.ea.extensions.constraints.ConstraintExtensionProvider;
import org.ect.ea.extensions.constraints.Disjunction;
import org.ect.ea.extensions.constraints.operations.Simplifier;
import org.ect.ea.extensions.portnames.TransitionPortNames;
import org.ect.ea.extensions.portnames.TransitionPortNamesProvider;

public class CAUtil {
	
	public static void trim(Automaton automaton) {
		Set<State> reachable = new HashSet<State>();
		for (State s: automaton.getStates())
			if(CA.isStartState(s))
				reachable.addAll(
						traverse(s));
		
		Set<Transition> dangling = new HashSet<Transition>();
		for (State s: automaton.getStates()) 
			if (!reachable.contains(s)) {
				dangling.addAll(s.getOutgoing());				
//				automaton.getTransitions().removeAll(s.getIncoming());
//				System.err.println("removed state " + s.getName());
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
		Collections.sort(CA.getPortNames(automaton).getValues());
		
		int i=0;
		for (State s: automaton.getStates())
			s.setName(prefix + ++i);
//		for (Transition t: automaton.getTransitions())
//			CA.setConstraint(t, 
//					CA.getConstraint(t).simplify());
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
/*	
	public static void mergeTrans0(Automaton a) {
		Collection<Transition> redundant = new HashSet<Transition>();
		
		ListIterator<Transition> lt1 = a.getTransitions().listIterator();
		while (lt1.hasNext()) {
			Transition tr1 = lt1.next();
			
			ListIterator<Transition> lt2 = a.getTransitions().listIterator(lt1.nextIndex());		
			while (lt2.hasNext()) {
				Transition tr2 = lt2.next();
				if (!redundant.contains(tr2) && tryMerge(tr1, tr2)) 
					redundant.add(tr2);
			}
		}
		
		remove(redundant);	
	}
*/	
	private static boolean tryMerge(Transition t0, Transition t1) {
//		if (t0==t1 || t0.getSource()!=t1.getSource() || t0.getTarget()!=t1.getTarget())
//			return false;
		assert t0!=t1 && t0.getSource()==t1.getSource() && t0.getTarget()==t1.getTarget();
		
		Collection<String> tp0 = ((TransitionPortNames) t0.findExtension(
				TransitionPortNamesProvider.EXTENSION_ID)).getValues(),
				tp1 = ((TransitionPortNames) t1.findExtension(
						TransitionPortNamesProvider.EXTENSION_ID)).getValues();
//		System.err.println(s+"->"+tp1+"|"+tp2+"->"+target);

		if (tp0.size()==tp1.size() && new HashSet<String>(tp0).containsAll(tp1)){
			Constraint con0 = (Constraint) t0.findExtension(ConstraintExtensionProvider.EXTENSION_ID),
			con1 = (Constraint) t1.findExtension(ConstraintExtensionProvider.EXTENSION_ID);
			//						 System.err.println(src+"->"+con1+"|"+con2+"->"+tgt);

			Disjunction dis = new Disjunction();
			dis.getMembers().add(con0);
			dis.getMembers().add(con1);

			t0.updateExtension(new Simplifier().doSwitch(dis));
			return true;
		} 
//		else
		return false;
	}

	public static void main(String[] args) throws IOException {
//		XMIWrangler.standaloneInit();
//		Automaton a = XMIWrangler.loadAutomata("/var/tmp/workspace/baz/fifox2.ea").getAutomata().get(1);
//		System.out.println(CA.prettyPrint(a));
//		CAUtil.trim(a);
		
//		System.out.println(CA.prettyPrint(a));
	}
}
