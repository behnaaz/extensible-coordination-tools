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
package org.ect.ea.automata;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ect.ea.EA;
import org.ect.ea.extensions.ExtensionsProduct;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.ProductCache;

/**
 * Default automata product implementation.
 * @author Christian Krause
 * 
 * @generated NOT
 */
public class AutomataProduct extends AbstractProductProvider {
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductProvider#computeProduct(org.ect.ea.automata.Automaton, org.ect.ea.automata.Automaton, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Automaton computeProduct(Automaton a1, Automaton a2, IProgressMonitor monitor) {
		
		monitor.beginTask("Compute product", 5);
		
		// Make copies and add silent transitions.
		a1 = EA.copyAutomaton(a1);
		a2 = EA.copyAutomaton(a2);
		
		// Add silent transitions.
		SilentTransitions.add(a1);
		SilentTransitions.add(a2);
		
		monitor.worked(1);
		
		// Remember the original states.
		ProductCache<State> productStates = new ProductCache<State>();
		
		// Merge automata extensions.
		Automaton product = mergeAutomata(a1, a2);		
		monitor.worked(1);

		// Compute the product states.
		for (State state1 : a1.getStates()) {
			for (State state2 : a2.getStates()) {
				State state = mergeStates(state1,state2);
				if (state==null) continue;
				product.getStates().add(state);				
				productStates.addProduct(state1, state2, state);
			}
		}
		
		monitor.worked(1);
		
		// Compute the product transitions.
		for (Transition t1 : a1.getTransitions()) {
			for (Transition t2 : a2.getTransitions()) {

				// Merge the extensions.
				List<Transition> transitions = mergeTransitions(t1, t2);
				
				// Set source and target. Add the transitions to the automaton.
				for (IExtendible current : transitions) {
					Transition transition = (Transition) current;
					transition.setSource( productStates.getProduct(t1.getSource(), t2.getSource()));
					transition.setTarget( productStates.getProduct(t1.getTarget(), t2.getTarget()));
					product.getTransitions().add(transition);
				}
			}
			
		}
		
		monitor.worked(1);
		
		// Remove silent transitions again.
		SilentTransitions.remove(product);
		
		monitor.worked(1);
		monitor.done();

		return product;
		
	}
	
	/**
	 * Convenience method for computing the product.
	 */
	public Automaton computeProduct(Automaton a1, Automaton a2) {
		return computeProduct(a1, a2, new NullProgressMonitor());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.ect.ea.IProductProvider#doPostProcessing(org.ect.ea.automata.Automaton, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doPostProcessing(Automaton automaton, IProgressMonitor monitor) {
		// No default post processing.
	}

	
	/*
	 * Merge two states including their extensions.
	 */
	protected State mergeStates(State state1, State state2) {
		// Merge state extensions.
		List<? extends IExtendible> states = mergeExtensions(state1, state2, State.class);
		if (states.size()!=1) {
			EA.logError("Product error: states extension returned zero or more than one product extensions.");
			return null;
		}
		// The product state.
		State product = (State) states.get(0);
		product.setName(getProductName(state1,state2));
		return product;
	}
	
	
	/*
	 * Merge two transitions including their extensions.
	 */
	@SuppressWarnings("unchecked")
	protected List<Transition> mergeTransitions(Transition t1, Transition t2) {
		return (List<Transition>) mergeExtensions(t1, t2, Transition.class);
	}
	
	
	/*
	 * Merge two automata including their extensions.
	 */
	protected Automaton mergeAutomata(Automaton a1, Automaton a2) {
		// Merge automata extensions.
		List<? extends IExtendible> automata = mergeExtensions(a1, a2, Automaton.class);
		if (automata.size()!=1) {
			EA.logError("Product error: automata extension returned zero or more than one product extensions.");
			return null;
		}
		Automaton product = (Automaton) automata.get(0);
		product.setName(getProductName(a1,a2));
		
		// Merge used extension ids.
		product.getUsedExtensionIds().addAll( a1.getUsedExtensionIds() );
		product.getUsedExtensionIds().addAll( a2.getUsedExtensionIds() );
		return product;
	}
	
	/**
	 * Helper method for merging extensions. This can be also used
	 * by other product implementations. Please take a look at 
	 * {@link #computeProduct(Automaton, Automaton, IProgressMonitor)}
	 * for the usage of this method.
	 * @param s1 First extendible.
	 * @param s2 Second extendible.
	 * @param resultType A class that implements {@link IExtendible}.
	 * @return List of generate {@link IExtendible}s.
	 */
	protected List<? extends IExtendible> mergeExtensions(IExtendible s1, IExtendible s2, Class<?> resultType) {
	
		List<IExtendible> result = new Vector<IExtendible>();
		List<IExtension> extensions;
		
		// Get all extension ids.
		Set<String> productIds = getCommonExtensionIds(s1,s2);		
		
		// Initialize result.
		IExtendible initial = newExtendible(resultType);
		
		// Copy all non-product extensions.
		extensions = new Vector<IExtension>();
		extensions.addAll(s1.getExtensions());
		extensions.addAll(s2.getExtensions());
		
		for (IExtension extension : extensions) {
			
			// Check whether it is a common extension type.
			if (productIds.contains(extension.getId())) continue;
			
			// Check whether the extension type is compatible with the new owner type.
			if (AutomataCorrector.extensionTypeMatches(extension.getId(), initial))
			
			// Ok, copy the original extension.
			initial.getExtensions().add( (IExtension) EcoreUtil.copy(extension) );
		}

		result.add(initial);
		
		// Iterate all extension ids.
		for (String id : productIds) {

			// Get the extensions and compute the product.
			IExtension e1 = s1.findExtension(id);
			IExtension e2 = s2.findExtension(id);
			extensions = ExtensionsProduct.compute(e1, e2); 
			
			// Create a copy of the result and clear the original list.
			Vector<IExtendible> oldResult = new Vector<IExtendible>(result);
			result.clear();
			
			for (IExtendible ownerProduct : oldResult) {
				for (IExtension extensionProduct : extensions) {
					
					// Clone the owner with all its extensions.
					IExtendible ownerClone = (IExtendible) EcoreUtil.copy(ownerProduct);
					
					// Clone the product extension.
					IExtension extensionClone = (IExtension) EcoreUtil.copy(extensionProduct);
					ownerClone.updateExtension(extensionClone);
					
					// Add the cloned owner to the result.
					result.add(ownerClone);
					
				}
			}
		}
				
		return result;
		
	}
	
	/*
	 * Get the set of common extension ids of two extendibles.
	 */
	protected Set<String> getCommonExtensionIds(IExtendible s1, IExtendible s2) {
		HashSet<String> common = new HashSet<String>();
		for (IExtension e1 : s1.getExtensions()) {
			if (s2.findExtension(e1.getId())!=null) common.add(e1.getId());
		}
		return common;
	}
	
	
	/*
	 * Instantiate a new IExtendible.
	 */
	protected IExtendible newExtendible(Class<?> clazz) {
		if (clazz==Automaton.class) return new Automaton();
		if (clazz==State.class) return new State();
		if (clazz==Transition.class) return new Transition();
		return null;
	}
	
	/*
	 * Compute a name for a product state. 
	 */
	protected String getProductName(State state1, State state2) {
		String name1 = state1.getName()!=null ? state1.getName() : state1.getId(); 
		String name2 = state2.getName()!=null ? state2.getName() : state2.getId();
		return name1 + " x " + name2;
	}	
	
	/*
	 * Compute a name for a product automaton. 
	 */
	protected String getProductName(Automaton a1, Automaton a2) {
		String name1 = a1.getName()!=null ? a1.getName() : a1.getId(); 
		String name2 = a2.getName()!=null ? a2.getName() : a2.getId();
		return name1 + " x " + name2;
	}	

}

