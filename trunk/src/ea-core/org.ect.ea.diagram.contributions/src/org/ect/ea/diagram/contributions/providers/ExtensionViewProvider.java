package org.ect.ea.diagram.contributions.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.diagram.contributions.view.factories.AutomatonProxyViewFactory;
import org.ect.ea.diagram.contributions.view.factories.StateProxyViewFactory;
import org.ect.ea.diagram.contributions.view.factories.TransitionProxyViewFactory;
import org.ect.ea.diagram.providers.AutomataViewProvider;
import org.ect.ea.diagram.view.factories.AutomatonViewFactory;
import org.ect.ea.diagram.view.factories.StateViewFactory;
import org.ect.ea.diagram.view.factories.TransitionViewFactory;



public class ExtensionViewProvider extends AutomataViewProvider {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getNodeViewClass(IAdaptable semanticAdapter, View container, String hint) {
		
		Class original = super.getNodeViewClass(semanticAdapter, container, hint);

		if (original==AutomatonViewFactory.class) {
			return AutomatonProxyViewFactory.class;
		}
		if (original==StateViewFactory.class) {
			return StateProxyViewFactory.class;
		}
		
		return original;
	}


	@Override
	@SuppressWarnings("rawtypes")
	protected Class getEdgeViewClass(IAdaptable semanticAdapter, View container, String hint) {
		
		Class original = super.getEdgeViewClass(semanticAdapter, container, hint);

		if (original==TransitionViewFactory.class) {
			return TransitionProxyViewFactory.class;
		}
		
		return original;
	}

	

}
