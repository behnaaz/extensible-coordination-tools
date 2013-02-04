package org.ect.ea.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;
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
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;
import org.ect.ea.diagram.view.factories.AutomatonCompartmentViewFactory;
import org.ect.ea.diagram.view.factories.AutomatonExtensionViewFactory;
import org.ect.ea.diagram.view.factories.AutomatonNameViewFactory;
import org.ect.ea.diagram.view.factories.AutomatonViewFactory;
import org.ect.ea.diagram.view.factories.ModuleViewFactory;
import org.ect.ea.diagram.view.factories.StateExtensionViewFactory;
import org.ect.ea.diagram.view.factories.StateNameViewFactory;
import org.ect.ea.diagram.view.factories.StateViewFactory;
import org.ect.ea.diagram.view.factories.TransitionExtensionViewFactory;
import org.ect.ea.diagram.view.factories.TransitionViewFactory;


/**
 * @generated
 */
public class AutomataViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (ModuleEditPart.MODEL_ID.equals(diagramKind)
				&& AutomataVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return ModuleViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		EObject domainElement = getSemanticElement(semanticAdapter);
		int visualID;
		if (semanticHint == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return null;
			}
			visualID = AutomataVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = AutomataVisualIDRegistry.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!AutomataElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != AutomataVisualIDRegistry
								.getNodeVisualID(containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!ModuleEditPart.MODEL_ID.equals(AutomataVisualIDRegistry
						.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case AutomatonEditPart.VISUAL_ID:
				case StateEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != AutomataVisualIDRegistry
									.getNodeVisualID(containerView,
											domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case AutomatonNameEditPart.VISUAL_ID:
				case AutomatonExtensionEditPart.VISUAL_ID:
				case AutomatonCompartmentEditPart.VISUAL_ID:
					if (AutomatonEditPart.VISUAL_ID != AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case StateNameEditPart.VISUAL_ID:
				case StateExtensionEditPart.VISUAL_ID:
					if (StateEditPart.VISUAL_ID != AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case TransitionExtensionEditPart.VISUAL_ID:
					if (TransitionEditPart.VISUAL_ID != AutomataVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				default:
					return null;
				}
			}
		}
		return getNodeViewClass(containerView, visualID);
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !AutomataVisualIDRegistry.canCreateNode(containerView,
						visualID)) {
			return null;
		}
		switch (visualID) {
		case AutomatonEditPart.VISUAL_ID:
			return AutomatonViewFactory.class;
		case AutomatonNameEditPart.VISUAL_ID:
			return AutomatonNameViewFactory.class;
		case AutomatonExtensionEditPart.VISUAL_ID:
			return AutomatonExtensionViewFactory.class;
		case StateEditPart.VISUAL_ID:
			return StateViewFactory.class;
		case StateNameEditPart.VISUAL_ID:
			return StateNameViewFactory.class;
		case StateExtensionEditPart.VISUAL_ID:
			return StateExtensionViewFactory.class;
		case AutomatonCompartmentEditPart.VISUAL_ID:
			return AutomatonCompartmentViewFactory.class;
		case TransitionExtensionEditPart.VISUAL_ID:
			return TransitionExtensionViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!AutomataElementTypes.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return null; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null) {
			return null; // our hint is visual id and must be specified
		}
		if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
			return null; // if semantic hint is specified it should be the same as in element type
		}
		int visualID = AutomataVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != AutomataVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return null; // visual id for link EClass should match visual id from element type
		}
		return getEdgeViewClass(visualID);
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(int visualID) {
		switch (visualID) {
		case TransitionEditPart.VISUAL_ID:
			return TransitionViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}

}
