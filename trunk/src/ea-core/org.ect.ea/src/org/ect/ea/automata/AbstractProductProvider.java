package org.ect.ea.automata;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.ect.ea.IProductProvider;



/**
 * Abstract class for product providers.
 * @author Christian Krause
 */
public abstract class AbstractProductProvider implements IProductProvider {
	
	/**
	 * Compute the product of a list of automata using {@link #computeProduct(Automaton, Automaton, IProgressMonitor)}
	 * and {@link #doPostProcessing(Automaton, IProgressMonitor)}. Subclasses may override this method to implement 
	 * a certain heuristic which can be the computation faster.
	 * 
	 * @param automata The automata to be used.
	 * @param monitor Progress monitor.
	 * @return The product automaton.
	 */
	public Automaton computeProduct(List<Automaton> automata, IProgressMonitor monitor) {
		
		monitor.beginTask("Compute product", automata.size());
		Automaton product;
		
		if (automata.isEmpty()) {
			product = AutomataFactory.eINSTANCE.createAutomaton();
		} 
		else if (automata.size()==1){
			product = automata.get(0);
		}
		else {
			product = automata.get(0);
			for (int i=1; i<automata.size(); i++) {
				product = computeProduct(product, automata.get(i), new SubProgressMonitor(monitor,1));
			}
		}
		
		// Do post-processing.
		doPostProcessing(product, new SubProgressMonitor(monitor,1));
		
		return product;
		
	}
	
	
	/**
	 * A non-abstract wrapper class for product providers. This is useful for
	 * product providers that do not extend {@link AbstractProductProvider}.
	 * @author Christian Krause
	 */
	public static final class ProductWrapper extends AbstractProductProvider {
		
		private IProductProvider provider;
		
		public ProductWrapper(IProductProvider provider) {
			this.provider = provider;
		}
		
		public Automaton computeProduct(Automaton a1, Automaton a2, IProgressMonitor monitor) {
			return provider.computeProduct(a1, a2, monitor);
		}
		
		public void doPostProcessing(Automaton automaton, IProgressMonitor monitor) {
			provider.doPostProcessing(automaton, monitor);
		}
		
	}
	
}
