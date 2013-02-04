package org.ect.ea.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.edit.parts.TransitionExtensionEditPart;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;


/**
 * @generated
 */
public class TransitionViewFactory extends ConnectionViewFactory {

	/**
	 * This adjusts the smoothness of the transitions.
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	protected List createStyles(View view) {

		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createFontStyle());

		// set smoothness value
		RoutingStyle routingStyle = NotationFactory.eINSTANCE
				.createRoutingStyle();
		routingStyle.setSmoothness(Smoothness.NORMAL_LITERAL);

		styles.add(routingStyle);

		return styles;

	}

	/**
	 * @generated
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = AutomataVisualIDRegistry
					.getType(TransitionEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService().createNode(
				eObjectAdapter,
				view,
				AutomataVisualIDRegistry
						.getType(TransitionExtensionEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
	}

}
