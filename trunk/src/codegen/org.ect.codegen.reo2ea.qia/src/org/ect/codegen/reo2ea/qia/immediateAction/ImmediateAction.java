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
package org.ect.codegen.reo2ea.qia.immediateAction;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.ect.ea.EA;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.portnames.DelayElement;
import org.ect.ea.extensions.portnames.DelayInfoProvider;
import org.ect.ea.extensions.portnames.DelayInformation;
import org.ect.ea.extensions.portnames.IntensionalPortNames;
import org.ect.ea.extensions.portnames.IntensionalPortNamesProvider;

public class ImmediateAction {
	private Set<String> names;
	private Automaton automaton;
	
	public static Automaton getResult(Automaton qia, Collection<String> names) {
		if (names.isEmpty())
			return qia;
		
		return new ImmediateAction(qia, names).getAutomaton();
	}
	
	public ImmediateAction(Automaton qia, Collection<String> ports) {
		this.names = new HashSet<String>(ports);
		this.automaton = EA.copyAutomaton(qia);
		
		for(final State s : automaton.getStates()){
			List<Transition> outgoings = s.getOutgoing();
			for(Transition t: outgoings){
				IntensionalPortNames Iports = (IntensionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
				if(!Iports.getFirings().isEmpty()){
					Set<String> list = new HashSet<String>(Iports.getFirings());
					list.retainAll(names);
					if(!list.isEmpty()){
						DelayInformation DI = (DelayInformation) t.findExtension(DelayInfoProvider.EXTENSION_ID);
						List<DelayElement> DEs = DI.getElements();
						List<DelayElement> remove = new Vector<DelayElement>();
						for(DelayElement de: DEs){
							Set<String> inputs = new HashSet<String>(de.getInput());
							Set<String> outputs = new HashSet<String>(de.getOutput());
							Set<String> total = new HashSet<String>(inputs);
							total.addAll(outputs);
							total.removeAll(names);
							if(total.isEmpty())	remove.add(de);
							else {
								if(names.containsAll(inputs)){
									union(de.getInput(), names);
								}
								else if(names.containsAll(outputs)){
									union(de.getOutput(), names);
								}
							}
						}
						DI.getElements().removeAll(remove);
					}
				}
			}
		}
	}
	
	public void union(List<String> target, Collection<String> list){
		for(String a:list){
			if(!target.contains(a))	target.add(a);
		}
	}
	
	public Automaton getAutomaton(){
		return automaton;
	}
}
