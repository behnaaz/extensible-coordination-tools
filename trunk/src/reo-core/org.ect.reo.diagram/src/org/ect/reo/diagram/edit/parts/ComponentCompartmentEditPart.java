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
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.PropertyHolder;
import org.ect.reo.diagram.edit.policies.ComponentCompartmentCanonicalEditPolicy;
import org.ect.reo.diagram.edit.policies.ComponentCompartmentItemSemanticEditPolicy;
import org.ect.reo.diagram.part.Messages;
import org.ect.reo.diagram.view.util.PropertyViewUpdater;


/**
 * @generated
 */
public class ComponentCompartmentEditPart extends ListCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 5004;

	/**
	 * @generated
	 */
	public ComponentCompartmentEditPart(View view) {
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
	 * @generated NOT
	 */
	@Override
	public void activate() {
		super.activate();
		PropertyHolder holder = (PropertyHolder) getNotationView().getElement();
		if (holder != null) {
			holder.eAdapters().add(PropertyViewUpdater.INSTANCE);
		}
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void deactivate() {
		PropertyHolder holder = (PropertyHolder) getNotationView().getElement();
		if (holder != null) {
			holder.eAdapters().remove(PropertyViewUpdater.INSTANCE);
		}
		super.deactivate();
	}

	/**
	 * @generated
	 */
	protected boolean hasModelChildrenChanged(Notification evt) {
		return false;
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return Messages.ComponentCompartmentEditPart_title;
	}

	/**
	 * @generated
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new ResizableCompartmentEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ComponentCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ComponentCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

}
