package org.ect.ea.diagram.edit.policies;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.part.AutomataDiagramUpdater;
import org.ect.ea.diagram.part.AutomataNodeDescriptor;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;


/**
 * @generated
 */
public class AutomatonCompartmentCanonicalEditPolicy extends
		CanonicalEditPolicy {

	/**
	 * @generated
	 */
	Set myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = AutomataDiagramUpdater
				.getAutomatonAutomatonCompartment_5001SemanticChildren(
						viewObject).iterator(); it.hasNext();) {
			result.add(((AutomataNodeDescriptor) it.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection semanticChildren, final View view) {
		int visualID = AutomataVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case StateEditPart.VISUAL_ID:
			if (!semanticChildren.contains(view.getElement())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize.add(AutomataPackage.eINSTANCE
					.getAutomaton_States());
		}
		return myFeaturesToSynchronize;
	}

}
