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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.diagram.edit.policies.ReaderCanonicalEditPolicy;
import org.ect.reo.diagram.edit.policies.ReaderItemSemanticEditPolicy;
import org.ect.reo.diagram.figures.util.ReoFigureSizes;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated NOT
 */
public class ReaderEditPart extends ComponentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1002;

	/**
	 * @generated
	 */
	public ReaderEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ReaderItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new ReaderCanonicalEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated NOT
	 */
	protected boolean addFixedChild(EditPart childEditPart) {

		if (childEditPart instanceof ReaderNameEditPart) {
			((ReaderNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureComponentNameLabel());
			return true;
		}

		return super.addFixedChild(childEditPart);

	}

	/**
	 * @generated NOT
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {

		if (childEditPart instanceof ReaderNameEditPart) {
			return true;
		}

		return super.removeFixedChild(childEditPart);

	}

	/**
	 * @generated NOT
	 */
	protected NodeFigure createNodePlate() {
		Size size = ReoFigureSizes.READER;
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(size.getWidth()), getMapMode().DPtoLP(size.getHeight()));
		return result;
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(ReoVisualIDRegistry
				.getType(ReaderNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request)
					.getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter
					.getAdapter(IElementType.class);
			if (type == ReoElementTypes.Property_2004) {
				return getChildBySemanticHint(ReoVisualIDRegistry
						.getType(ReaderCompartmentEditPart.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}

}
