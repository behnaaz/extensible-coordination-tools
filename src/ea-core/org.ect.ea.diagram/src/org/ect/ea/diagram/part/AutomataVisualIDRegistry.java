package org.ect.ea.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.automata.Module;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonExtensionEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonNameEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.StateExtensionEditPart;
import org.ect.ea.diagram.edit.parts.StateNameEditPart;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionExtensionEditPart;


/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class AutomataVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.ect.ea.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (ModuleEditPart.MODEL_ID.equals(view.getType())) {
				return ModuleEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.ect.ea.diagram.part.AutomataVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				AutomataDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (AutomataPackage.eINSTANCE.getModule().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Module) domainElement)) {
			return ModuleEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.ect.ea.diagram.part.AutomataVisualIDRegistry
				.getModelID(containerView);
		if (!ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.ect.ea.diagram.part.AutomataVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModuleEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case AutomatonCompartmentEditPart.VISUAL_ID:
			if (AutomataPackage.eINSTANCE.getState().isSuperTypeOf(
					domainElement.eClass())) {
				return StateEditPart.VISUAL_ID;
			}
			break;
		case ModuleEditPart.VISUAL_ID:
			if (AutomataPackage.eINSTANCE.getAutomaton().isSuperTypeOf(
					domainElement.eClass())) {
				return AutomatonEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.ect.ea.diagram.part.AutomataVisualIDRegistry
				.getModelID(containerView);
		if (!ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (ModuleEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.ect.ea.diagram.part.AutomataVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModuleEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case AutomatonEditPart.VISUAL_ID:
			if (AutomatonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (AutomatonExtensionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (AutomatonCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StateEditPart.VISUAL_ID:
			if (StateNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StateExtensionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case AutomatonCompartmentEditPart.VISUAL_ID:
			if (StateEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ModuleEditPart.VISUAL_ID:
			if (AutomatonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TransitionEditPart.VISUAL_ID:
			if (TransitionExtensionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (AutomataPackage.eINSTANCE.getTransition().isSuperTypeOf(
				domainElement.eClass())) {
			return TransitionEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Module element) {
		return true;
	}

}
