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
package org.ect.ea.diagram.contributions.edit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;
import org.ect.ea.diagram.contributions.ExtensionLabelHelper;
import org.ect.ea.diagram.edit.parts.StateExtensionEditPart;

public class StateExtensionProxyEditPart extends StateExtensionEditPart {

	private ExtensionLabelHelper helper;
	
	private Label tooltip;
	
	/**
	 * Default constructor.
	 * @param view State extension label node.
	 */
	public StateExtensionProxyEditPart(View view) {
		super(view);
		helper = new ExtensionLabelHelper(this);
	}
	
	
	@Override
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		if (event.getNotifier()==getNotationView().getElement()) refreshVisuals();
	}

	
	@Override
	protected IFigure createFigurePrim() {
		WrappingLabel label = new WrappingLabel("");
		tooltip = new Label("");
		label.setToolTip(tooltip);
		return label;
	}

	
	@Override
	protected void refreshLabel() {
		super.refreshLabel();
		tooltip.setText(helper.getToolTip());
	}

    
	@Override
	protected void refreshFontColor() {
		setFontColor(helper.getFontColor());
	}

	
	@Override
	protected Image getLabelIcon() {
		return helper.getLabelIcon();
	}
	
	@Override
	protected boolean isEditable() {
		return super.isEditable() && helper.isEditable();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		// We have to make sure that the parent is not null.
		return (getParent()!=null) ? super.getAdapter(key) : null;
	}
	
}
