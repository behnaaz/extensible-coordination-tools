package org.ect.ea.extensions.startstates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.ea.EA;
import org.ect.ea.automata.AutomataProduct;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.SilentTransitions;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.util.ProductCache;


/**
 * This product definition is equivalent to the default implementation
 * in {@link AutomataProduct} except for one thing: it computes only
 * reachable states in the product. For large automata, this implementation
 * should be faster.
 * 
 * If there is no start states extension in the automata, the default
 * product implementation is used instead.
 * 
 * @author Christian Krause
 */
public class ReachableStatesProduct extends AutomataProduct {
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductProvider#computeProduct(org.ect.ea.automata.Automaton, org.ect.ea.automata.Automaton, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Automaton computeProduct(Automaton a1, Automaton a2, IProgressMonitor monitor) {
		
		// Check if the start state extensions are present in both automata.
		if (!a1.getUsedExtensionIds().contains(StartStateExtensionProvider.EXTENSION_ID) ||
			!a2.getUsedExtensionIds().contains(StartStateExtensionProvider.EXTENSION_ID)) {
			// fall back to the default implementation.
			return super.computeProduct(a1, a2);
		}
		
		// Otherwise start computing the product.
		monitor.beginTask("Compute product", 5);
		
		// Make copies and add silent transitions.
		a1 = EA.copyAutomaton(a1);
		a2 = EA.copyAutomaton(a2);
		
		// Add silent transitions.
		SilentTransitions.add(a1);
		SilentTransitions.add(a2);
		monitor.worked(1);
		
		// Remember the product states and transitions.
		ProductCache<State> productStates = new ProductCache<State>();
		ProductCache<Transition> productTransitions = new ProductCache<Transition>();
		
		// Merge automata extensions.			
		Automaton product = mergeAutomata(a1,a2);		
		monitor.worked(1);
		
		// Initialize with the start states.
		Set<State> states1 = new HashSet<State>(getStartStates(a1));
		Set<State> states2 = new HashSet<State>(getStartStates(a2));
		
		while (!states1.isEmpty() && !states2.isEmpty()) {
			
			// Remember new targets.
			Set<State> targets1 = new HashSet<State>();
			Set<State> targets2 = new HashSet<State>();
			
			for (State state1 : states1) {
				for (State state2 : states2) {
					
					// Check if the product of these two states is defined already.
					State source = productStates.getProduct(state1, state2);
					
					// If not, create it.
					if (source==null) source = mergeStates(state1,state2);
					
					// An error occurred.
					if (source==null) continue;
					
					product.getStates().add(source);
					productStates.addProduct(state1, state2, source);
					
					// Outgoing transitions.
					for (Transition t1 : state1.getOutgoing()) {
						for (Transition t2 : state2.getOutgoing()) {
							
							// Check if these transitions were merged already before.
							if (productTransitions.isDefined(t1, t2)) continue;
							
							// If not, merge the extensions and create new transitions.
							List<Transition> transitions = mergeTransitions(t1, t2);
							productTransitions.addProducts(t1, t2, transitions);
							if (transitions.isEmpty()) continue;
							
							// Get the product of the targets of the transitions or create it if necessary.
							State target = productStates.getProduct(t1.getTarget(), t2.getTarget());
							if (target==null) {
								
								// Merge the target states.
								target = mergeStates(t1.getTarget(),t2.getTarget());
								
								// An error occurred.
								if (target==null) continue;
								
								product.getStates().add(target);				
								productStates.addProduct(t1.getTarget(), t2.getTarget(), target);
								targets1.add(t1.getTarget());
								targets2.add(t2.getTarget());
								
							}
							
							// Set source and target of the new transitions 
							// and add the transitions to the automaton.
							for (Transition transition : transitions) {
								transition.setSource(source);
								transition.setTarget(target);
								product.getTransitions().add(transition);
							}
						}
					}
					// End transitions.
				}
			}
			// End states.
			
			// Continue with the new targets.
			states1 = targets1;
			states2 = targets2;
			
		}
		
		monitor.worked(2);

		// Remove silent transitions again.
		SilentTransitions.remove(product);
		
		monitor.worked(1);
		monitor.done();

		return product;
		
	}
	
	
	protected List<State> getStartStates(Automaton automaton) {
		List<State> result = new ArrayList<State>();
		for (State state : automaton.getStates()) {
			BooleanExtension ext = (BooleanExtension) state.findExtension(StartStateExtensionProvider.EXTENSION_ID);
			if (ext.getValue()==true) result.add(state);
		}
		return result;
	}
	
}
