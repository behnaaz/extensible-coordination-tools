package org.ect.ea.extensions.startstates;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.ICustomExtensionProvider;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.configuration.ExtensionDefinition;
import org.ect.ea.extensions.BooleanExtension;
import org.ect.ea.extensions.IExtendible;
import org.ect.ea.extensions.IExtension;
import org.ect.ea.util.IValidationResult;
import org.ect.ea.util.ValidationResult;



public class StartStateExtensionProvider implements ICustomExtensionProvider {

	// Id of extensions provided by this class.
	public static final String EXTENSION_ID = "cwi.ea.extensions.startStates";
		
	// The name of the extensions.
	public static final String EXTENSION_NAME = "Start States";
	
	// An extension definition for this provider.
	public static final IExtensionDefinition EXTENSION_DEFINITION = 
		new ExtensionDefinition(EXTENSION_NAME, EXTENSION_ID, ExtensionDefinition.CUSTOM, 
			ExtensionDefinition.STATES, new StartStateExtensionProvider());


	/**
	 * Create a default start state extension.
	 */
	public IExtension createDefaultExtension(IExtendible owner) {
		State state = (State) owner;
		// If it is the first state added to an automaton, we mark it as a start state.
		if (state.getAutomaton()!=null && state.getAutomaton().getStates().size()==1) {
			return new BooleanExtension(true);
		}
		// Otherwise it is not a start state by default.
		return new BooleanExtension(false);
	}
	
	/**
	 * Identity extension. Not needed for a state extension.
	 */
	public IExtension createSilentExtension(Transition transition) {
		return null;
	}

	/**
	 * Not needed here.
	 */
	public boolean isSilentExtension(IExtension extension) {
		return false;
	}
	
	
	/**
	 * Compute the product of two extensions.
	 */
	public EList<IExtension> computeProductExtensions(IExtension x1, IExtension x2) {

		// Create a new list. 
		EList<IExtension> result = new BasicEList<IExtension>();
		
		// Compute the product value and init the extension.
		boolean isStartState1 = ((BooleanExtension) x1).getValue();
		boolean isStartState2 = ((BooleanExtension) x2).getValue();
		
		// Add the 'union' to the list and return it.
		result.add( new BooleanExtension(isStartState1 && isStartState2) );
		return result;
	
	}
	
	
	public void prepareExtensionRemoval(IExtension extension, View view) {
		
		// Remove the fake transition by setting the flag to false...
		((BooleanExtension) extension).setValue(false);
		
		// ...and updating the view.
		updateExtensionView(extension, view);
		
	}
	
	
	
	public void updateExtensionView(IExtension extension, View view) {

		// Extract the views etc.
		boolean isStartState = ((BooleanExtension) extension).getValue();
		Node stateNode = (Node) view;
		View compartment = (View) view.eContainer();

		// Look for a fake transition.
		Edge fakeTransition = FakeTransitionViewFactory.findFakeTransition(stateNode);
		
		// Check if it should be there but isn't actually there.
		if (isStartState && fakeTransition==null) {
			
			// Create a node and an edge.
			Node invisibleNode = FakeTransitionViewFactory.createInvisibleNode(stateNode);
			invisibleNode.setElement(extension);

			fakeTransition = FakeTransitionViewFactory.createFakeTransition(invisibleNode, stateNode);
			fakeTransition.setElement(extension);
			
			// Add both.
			compartment.insertChild(invisibleNode);
			compartment.getDiagram().insertEdge(fakeTransition);
			
		}
		
		// It is there but it shouldn't be there.
		else if (!isStartState && fakeTransition!=null) {
			
			// Disconnect the edge.
			Node invisibleNode = (Node) fakeTransition.getSource();
			fakeTransition.setSource(null);
			fakeTransition.setTarget(null);
			
			// Remove both.
			compartment.getDiagram().removeEdge(fakeTransition);
			compartment.removeChild(invisibleNode);
			
		}
			
	}
	
	public IValidationResult validateExtension(IExtension x) {
		return ValidationResult.newOKResult();
	}

}
