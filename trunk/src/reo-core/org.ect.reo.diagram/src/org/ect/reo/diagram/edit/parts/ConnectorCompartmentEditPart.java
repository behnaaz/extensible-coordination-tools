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

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.ReoPackage;
import org.ect.reo.diagram.edit.policies.ConnectorCompartmentCanonicalEditPolicy;
import org.ect.reo.diagram.edit.policies.ConnectorCompartmentItemSemanticEditPolicy;
import org.ect.reo.diagram.part.Messages;


/**
 * @generated
 */
public class ConnectorCompartmentEditPart extends ShapeCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 5001;

	/**
	 * @generated
	 */
	public ConnectorCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean isSelectable() {
		// We don't want compartments to be selectable.
		return false;
	}

	/**
	 * Update the color and the text of nodes if the primitives change.
	 * The event we are looking for is when a component is dragged into
	 * or out of the connector, thereby changing the source/sink node property.
	 * @generated NOT
	 */
	@Override
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		int type = event.getEventType();
		if (event.getFeature() == ReoPackage.eINSTANCE
				.getConnector_Primitives()
				&& (type == Notification.ADD || type == Notification.ADD_MANY
						|| type == Notification.REMOVE || type == Notification.REMOVE_MANY)) {
			for (Object child : getChildren()) {
				if (child instanceof NodeEditPart) {
					NodeEditPart node = (NodeEditPart) child;
					node.updatePrimaryShape();
				}
			}

		}
	}

	/**
	 * @generated
	 */
	@Override
	public String getCompartmentName() {
		return Messages.ConnectorCompartmentEditPart_title;
	}

	/**
	 * @generated
	 */
	@Override
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new ResizableCompartmentEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ConnectorCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ConnectorCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	@Override
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

}
