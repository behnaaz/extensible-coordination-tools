package org.ect.ea.extensions.portnames.actions;

import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.portnames.IntentionalPortNames;
import org.ect.ea.extensions.portnames.providers.IntensionalPortNamesProvider;
import org.ect.ea.extensions.startstates.StartStateExtensionProvider;


public class QIARefinement {

	
	public void compute(Automaton automaton, IProgressMonitor monitor) {
		
		monitor.beginTask("Compute refinement", 3);
		System.out.println("compute refinement...");
		
		EList<Transition> transitions = automaton.getTransitions();
		List<State> states = automaton.getStates();
		List<Transition> unnecessary = new Vector<Transition>();
		int size = transitions.size();
		
		for(State a : states){
			if(a.getOutgoing().size()>1){
				List<Transition> tempTransitions = a.getOutgoing();
				for(int i=0;i<tempTransitions.size();i++){
					Transition t = tempTransitions.get(i);
					IntentionalPortNames ip = (IntentionalPortNames) t.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
					List<String> M = tokenize(ip.getRequests());
					List<String> N = tokenize(ip.getFirings());
					for(int j=0;j<tempTransitions.size();j++){
						if(i!=j){
							Transition t1 = tempTransitions.get(j);
							IntentionalPortNames ip1 = (IntentionalPortNames) t1.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
							List<String> M1 = tokenize(ip1.getRequests());
							List<String> N1 = tokenize(ip1.getFirings());
							if(M1.containsAll(M) && !N.isEmpty() && N1.containsAll(N)){
								//To retain some transitions emanating from the same source with the same Iports but getting to different targets
								// In general, hiding causes this case that will be handled by bisimulation(needs to be implemented).  
								//if(!M.equals(M1) || !N.equals(N1)){								
								HashSet<String> pureN = new HashSet<String>(N);
								HashSet<String> pureN1 = new HashSet<String>(N1);
								pureN.removeAll(M);
								pureN1.removeAll(M1);
								if(pureN1.containsAll(pureN)){
									boolean independent = false;
									for(int k=0;k<tempTransitions.size();k++){
										if(k!=i && k!=j){
											Transition t2 = tempTransitions.get(k);
											IntentionalPortNames ip2 = (IntentionalPortNames) t2.findExtension(IntensionalPortNamesProvider.EXTENSION_ID);
											List<String> N2 = tokenize(ip2.getFirings());
											
											if(!N2.isEmpty()){
												HashSet<String> independentN1 = new HashSet<String>(N1);
												HashSet<String> commonN2 = new HashSet<String>(N2);
												independentN1.removeAll(N);
												commonN2.retainAll(N);
												if(N2.containsAll(independentN1) && commonN2.isEmpty()){
													independent = true;
													break;
												}
											}
										}
									}
								
									if(!independent && !unnecessary.contains(t))		
										unnecessary.add(t);
								}
							}
						}
					}
				}
			}
		}
		
		for(Transition temp : unnecessary)	
			System.out.println("removed Transition : "+temp.getSource()+" , "+temp.getTarget()+" , "
					+temp.findExtension(IntensionalPortNamesProvider.EXTENSION_ID).toString());
		
		for(Transition c : unnecessary){
			c.setSource(null);
			c.setTarget(null);
		}
		
		automaton.getTransitions().removeAll(unnecessary);
		
		System.out.println("After refinement : "+(size-transitions.size())+" removed");
		System.out.println("States: "+states.size()+" Transitions: "+transitions.size());
		monitor.worked(1);
		
		removeUnreachableStates(automaton);
		monitor.worked(1);
		
		monitor.worked(1);
		monitor.done();
	}
	
	public List<String> tokenize(List<String> ports){
		List<String> result = new Vector<String>();
		for(String a : ports){
			StringTokenizer token = new StringTokenizer(a,",");
			while(token.hasMoreTokens()){
				String next = token.nextToken();
				if(!result.contains(next))	result.add(next);
			}
		}
		
		return result;
	}
		
	protected void removeUnreachableStates(Automaton automaton){
		List<State> removeStates = new Vector<State>();
		List<Transition> removeTransitions = new Vector<Transition>();
		
		for(State a : automaton.getStates()){
			if((BooleanExtension) a.findExtension(StartStateExtensionProvider.EXTENSION_ID)!=null){
				boolean initial = ((BooleanExtension) a.findExtension(StartStateExtensionProvider.EXTENSION_ID)).getValue();
				if(initial!=true && a.getIncoming().isEmpty()){
					removeStates.add(a);
					removeTransitions.addAll(a.getOutgoing());	
				}
			}
		}
		
		for(Transition b : removeTransitions){
			b.setSource(null);
			b.setTarget(null);
		}
		automaton.getStates().removeAll(removeStates);
		automaton.getTransitions().removeAll(removeTransitions);
		if(!removeStates.isEmpty())	removeUnreachableStates(automaton);
		else return; 
	}
	
}
