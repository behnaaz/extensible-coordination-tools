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
package org.ect.reo.diagram.edit.parts;

import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.CustomPrimitive;
import org.ect.reo.Property;
import org.ect.reo.PropertyHolder;
import org.ect.reo.diagram.edit.policies.ChannelItemSemanticEditPolicy;
import org.ect.reo.diagram.figures.ChannelLine;
import org.ect.reo.diagram.figures.util.ReoFigureColors;
import org.ect.reo.util.PropertyChangeListener;


/**
 * Abstract base class for custom channel edit parts.
 * Handles the foreground colors of custom channels.
 * 
 * @author Christian Krause
 * @generated NOT
 */
public abstract class CustomChannelEditPart extends ConnectionNodeEditPart implements ITreeBranchEditPart {
	
	/**
	 * Property listener.
	 * @generated NOT
	 */
	private PropertyChangeListener propertyListener = new PropertyChangeListener() {
		@Override
		protected void propertyChanged(Property property, PropertyHolder owner) {
			refreshForegroundColor();
		}
	};
	
	/**
	 * Constructor.
	 */
	public CustomChannelEditPart(View view) {
		super(view);
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public void activate() {
		super.activate();
		CustomPrimitive primitive = (CustomPrimitive) getNotationView().getElement();
		if (primitive!=null) {
			propertyListener.monitor(primitive, true);
		}
		refreshForegroundColor();
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public void deactivate() {
		CustomPrimitive primitive = (CustomPrimitive) getNotationView().getElement();
		if (primitive!=null) {
			propertyListener.monitor(primitive, false);
		}
		super.deactivate();
	}
	
	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ChannelItemSemanticEditPolicy());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart#refreshForegroundColor()
	 */
	@Override
    protected void refreshForegroundColor() {
		CustomPrimitive custom = (CustomPrimitive) getNotationView().getElement();
		if (custom!=null && getPrimaryShape()!=null) {
			getPrimaryShape().setCustomColor(ReoFigureColors.getCustomPrimitiveColor(custom));
		}
    }
	
	public ChannelLine getPrimaryShape() {
		return (ChannelLine) getFigure();
	}
	
}
