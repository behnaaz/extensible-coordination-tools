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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.diagram.contributions.ExtensionViewUpdater;
import org.ect.ea.diagram.edit.parts.StateEditPart;
import org.ect.ea.diagram.edit.parts.StateNameEditPart;
import org.ect.ea.diagram.part.AutomataVisualIDRegistry;
import org.ect.ea.extensions.IExtendible;



public class StateProxyViewFactory extends AbstractShapeViewFactory {

	
	protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
		
		if (semanticHint == null) {
			semanticHint = AutomataVisualIDRegistry.getType(StateEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		
		super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
		
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		
		// Create the name label node.
		getViewService().createNode(eObjectAdapter, view,
				AutomataVisualIDRegistry.getType(StateNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());
		
		// Do not create an extension label node.
		//getViewService().createNode(eObjectAdapter, view,
		//		AutomataVisualIDRegistry.getType(StateExtensionEditPart.VISUAL_ID),
		//		ViewUtil.APPEND, true, getPreferencesHint());
		
		
		// Create views for extensions, if they exist already.
		if (eObject!=null) {
			ExtensionViewUpdater updater = new ExtensionViewUpdater();
			updater.updateExtensionViews((IExtendible) eObject, view);
		}
	}

}
