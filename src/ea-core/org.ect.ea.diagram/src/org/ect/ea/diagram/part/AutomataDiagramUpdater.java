package org.ect.ea.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.providers.AutomataElementTypes;


/**
 * @generated
 */
public class AutomataDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (AutomataVisualIDRegistry.getVisualID(view)) {
		case AutomatonCompartmentEditPart.VISUAL_ID:
			return getAutomatonAutomatonCompartment_5001SemanticChildren(view);
		case ModuleEditPart.VISUAL_ID:
			return getModule_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAutomatonAutomatonCompartment_5001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Automaton modelElement = (Automaton) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getStates().iterator(); it.hasNext();) {
			State childElement = (State) it.next();
			int visualID = AutomataVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == StateEditPart.VISUAL_ID) {
				result.add(new AutomataNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getModule_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Module modelElement = (Module) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getAutomata().iterator(); it.hasNext();) {
			Automaton childElement = (Automaton) it.next();
			int visualID = AutomataVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == AutomatonEditPart.VISUAL_ID) {
				result.add(new AutomataNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (AutomataVisualIDRegistry.getVisualID(view)) {
		case ModuleEditPart.VISUAL_ID:
			return getModule_1000ContainedLinks(view);
		case AutomatonEditPart.VISUAL_ID:
			return getAutomaton_1001ContainedLinks(view);
		case StateEditPart.VISUAL_ID:
			return getState_2001ContainedLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_3001ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (AutomataVisualIDRegistry.getVisualID(view)) {
		case AutomatonEditPart.VISUAL_ID:
			return getAutomaton_1001IncomingLinks(view);
		case StateEditPart.VISUAL_ID:
			return getState_2001IncomingLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_3001IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (AutomataVisualIDRegistry.getVisualID(view)) {
		case AutomatonEditPart.VISUAL_ID:
			return getAutomaton_1001OutgoingLinks(view);
		case StateEditPart.VISUAL_ID:
			return getState_2001OutgoingLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_3001OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModule_1000ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAutomaton_1001ContainedLinks(View view) {
		Automaton modelElement = (Automaton) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_Transition_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getState_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTransition_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAutomaton_1001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_2001IncomingLinks(View view) {
		State modelElement = (State) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Transition_3001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTransition_3001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAutomaton_1001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getState_2001OutgoingLinks(View view) {
		State modelElement = (State) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_Transition_3001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTransition_3001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Transition_3001(
			Automaton container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getTransitions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transition) {
				continue;
			}
			Transition link = (Transition) linkObject;
			if (TransitionEditPart.VISUAL_ID != AutomataVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			State dst = link.getTarget();
			State src = link.getSource();
			result.add(new AutomataLinkDescriptor(src, dst, link,
					AutomataElementTypes.Transition_3001,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Transition_3001(
			State target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != AutomataPackage.eINSTANCE
					.getTransition_Target()
					|| false == setting.getEObject() instanceof Transition) {
				continue;
			}
			Transition link = (Transition) setting.getEObject();
			if (TransitionEditPart.VISUAL_ID != AutomataVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			State src = link.getSource();
			result.add(new AutomataLinkDescriptor(src, target, link,
					AutomataElementTypes.Transition_3001,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Transition_3001(
			State source) {
		Automaton container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Automaton) {
				container = (Automaton) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getTransitions().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Transition) {
				continue;
			}
			Transition link = (Transition) linkObject;
			if (TransitionEditPart.VISUAL_ID != AutomataVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			State dst = link.getTarget();
			State src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new AutomataLinkDescriptor(src, dst, link,
					AutomataElementTypes.Transition_3001,
					TransitionEditPart.VISUAL_ID));
		}
		return result;
	}

}
