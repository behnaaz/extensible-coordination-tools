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
package org.ect.ea.diagram.contributions.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Smoothness;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.diagram.contributions.ExtensionViewUpdater;
import org.ect.ea.diagram.edit.parts.TransitionEditPart;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;
import org.ect.ea.extensions.IExtendible;

public class TransitionProxyViewFactory extends ConnectionViewFactory {


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected List createStyles(View view) {

		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createFontStyle());

		// Set smoothness value.
		RoutingStyle routingStyle = NotationFactory.eINSTANCE.createRoutingStyle();
		routingStyle.setSmoothness(Smoothness.NORMAL_LITERAL);
		styles.add(routingStyle);

		return styles;
	}
	
	
	@Override
	protected void decorateView(View containerView, View view, 
								IAdaptable semanticAdapter, String semanticHint, 
								int index, boolean persisted) {
		
		if (semanticHint == null) {
			semanticHint = AutomataVisualIDRegistry.getType(TransitionEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		
		super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
		
		// We do not create any extension label nodes.		
		// getViewService().createNode(eObjectAdapter, view,
		//		AutomataVisualIDRegistry.getType(TransitionExtensionEditPart.VISUAL_ID),
		//		ViewUtil.APPEND, true, getPreferencesHint());
		
		// Create views for extensions, if they exist already.
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject!=null) {
			ExtensionViewUpdater updater = new ExtensionViewUpdater();
			updater.updateExtensionViews((IExtendible) eObject, view);
		}
	}

}