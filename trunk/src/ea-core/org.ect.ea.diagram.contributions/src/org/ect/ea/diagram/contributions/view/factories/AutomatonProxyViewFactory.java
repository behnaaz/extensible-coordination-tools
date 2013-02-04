package org.ect.ea.diagram.contributions.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.diagram.contributions.ExtensionViewUpdater;
import org.ect.ea.diagram.edit.parts.AutomatonCompartmentEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.diagram.edit.parts.AutomatonNameEditPart;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;
import org.ect.ea.extensions.IExtendible;



public class AutomatonProxyViewFactory extends AbstractShapeViewFactory {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createShapeStyle());
		return styles;
	}
	
	
	protected void decorateView(View containerView, View view,
								IAdaptable semanticAdapter, String semanticHint, 
								int index, boolean persisted) {

		if (semanticHint == null) {
			semanticHint = AutomataVisualIDRegistry.getType(AutomatonEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		
		super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
		
		if (!ModuleEditPart.MODEL_ID.equals(AutomataVisualIDRegistry.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put("modelID", ModuleEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}
		
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		
		// Create a node for the name label.
		getViewService().createNode(eObjectAdapter, view,
				AutomataVisualIDRegistry.getType(AutomatonNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		
		// Do NOT create a node for the extensions.
		//getViewService().createNode(eObjectAdapter, view,
		//		AutomataVisualIDRegistry.getType(AutomatonExtensionEditPart.VISUAL_ID),
		//		ViewUtil.APPEND, true, getPreferencesHint());
		
		// The compartment node.
		getViewService().createNode(eObjectAdapter, view,
				AutomataVisualIDRegistry.getType(AutomatonCompartmentEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		
		
		// Create views for extensions, if they exist already.
		if (eObject!=null) {
			ExtensionViewUpdater updater = new ExtensionViewUpdater();
			updater.updateExtensionViews((IExtendible) eObject, view);
		}
		
	}

}
