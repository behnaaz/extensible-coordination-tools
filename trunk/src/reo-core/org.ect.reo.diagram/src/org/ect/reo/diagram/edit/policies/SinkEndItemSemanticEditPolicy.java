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
package org.ect.reo.diagram.edit.policies;

import java.util.Iterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.diagram.edit.commands.PrimitiveEndDeleteCommand;
import org.ect.reo.diagram.edit.commands.SinkEndNodeCreateCommand;
import org.ect.reo.diagram.edit.commands.SinkEndNodeReorientCommand;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class SinkEndItemSemanticEditPolicy extends
		ReoBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public SinkEndItemSemanticEditPolicy() {
		super(ReoElementTypes.SinkEnd_2007);
	}

	/**
	 * @generated NOT
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new PrimitiveEndDeleteCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (ReoElementTypes.SinkEndNode_3014 == req.getElementType()) {
			return getGEFWrapper(new SinkEndNodeCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (ReoElementTypes.SinkEndNode_3014 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case SinkEndNodeEditPart.VISUAL_ID:
			return getGEFWrapper(new SinkEndNodeReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
