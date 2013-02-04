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
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.reo.diagram.edit.commands.ChannelCreateCommand;
import org.ect.reo.diagram.edit.commands.ChannelReorientCommand;
import org.ect.reo.diagram.edit.commands.NodeDeleteCommand;
import org.ect.reo.diagram.edit.commands.NodeSourceEndsCreateCommand;
import org.ect.reo.diagram.edit.commands.NodeSourceEndsReorientCommand;
import org.ect.reo.diagram.edit.commands.SinkEndNodeCreateCommand;
import org.ect.reo.diagram.edit.commands.SinkEndNodeReorientCommand;
import org.ect.reo.diagram.edit.parts.AsyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.AsyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSinkSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSourceSyncEditPart;
import org.ect.reo.diagram.edit.parts.BlockingSyncEditPart;
import org.ect.reo.diagram.edit.parts.CustomDirectedChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomDrainChannelEditPart;
import org.ect.reo.diagram.edit.parts.CustomSpoutChannelEditPart;
import org.ect.reo.diagram.edit.parts.FIFOEditPart;
import org.ect.reo.diagram.edit.parts.FilterEditPart;
import org.ect.reo.diagram.edit.parts.FullFIFOEditPart;
import org.ect.reo.diagram.edit.parts.LossySyncEditPart;
import org.ect.reo.diagram.edit.parts.NodeSourceEndsEditPart;
import org.ect.reo.diagram.edit.parts.PrioritySyncEditPart;
import org.ect.reo.diagram.edit.parts.SinkEndNodeEditPart;
import org.ect.reo.diagram.edit.parts.SyncDrainEditPart;
import org.ect.reo.diagram.edit.parts.SyncEditPart;
import org.ect.reo.diagram.edit.parts.SyncSpoutEditPart;
import org.ect.reo.diagram.edit.parts.TimerEditPart;
import org.ect.reo.diagram.edit.parts.TransformEditPart;
import org.ect.reo.diagram.part.ReoVisualIDRegistry;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class NodeItemSemanticEditPolicy extends ReoBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NodeItemSemanticEditPolicy() {
		super(ReoElementTypes.Node_2001);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new NodeDeleteCommand(req));
	}

	/**
	 * @generated
	 */
	@Override
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
		if (ReoElementTypes.Sync_3001 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.LossySync_3002 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.FIFO_3003 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.SyncDrain_3005 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.SyncSpout_3006 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.AsyncDrain_3007 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.AsyncSpout_3008 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.Filter_3011 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.Transform_3012 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.Timer_3015 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.NodeSourceEnds_3013 == req.getElementType()) {
			return getGEFWrapper(new NodeSourceEndsCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (ReoElementTypes.SinkEndNode_3014 == req.getElementType()) {
			return null;
		}
		if (ReoElementTypes.CustomDirectedChannel_3016 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.CustomDrainChannel_3017 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.CustomSpoutChannel_3018 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.PrioritySync_3019 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.BlockingSync_3020 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.BlockingSinkSync_3021 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.BlockingSourceSync_3022 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (ReoElementTypes.Sync_3001 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.LossySync_3002 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.FIFO_3003 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.SyncDrain_3005 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.SyncSpout_3006 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.AsyncDrain_3007 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.AsyncSpout_3008 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.Filter_3011 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.Transform_3012 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.Timer_3015 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.NodeSourceEnds_3013 == req.getElementType()) {
			return null;
		}
		if (ReoElementTypes.SinkEndNode_3014 == req.getElementType()) {
			return getGEFWrapper(new SinkEndNodeCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (ReoElementTypes.CustomDirectedChannel_3016 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.CustomDrainChannel_3017 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.CustomSpoutChannel_3018 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.PrioritySync_3019 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.BlockingSync_3020 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.BlockingSinkSync_3021 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		if (ReoElementTypes.BlockingSourceSync_3022 == req.getElementType()) {
			return getGEFWrapper(new ChannelCreateCommand(req, req.getSource(),
					req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	@Override
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case SyncEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case LossySyncEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case FIFOEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case SyncDrainEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case SyncSpoutEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case AsyncDrainEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case AsyncSpoutEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case FilterEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case TransformEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case TimerEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case CustomDirectedChannelEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case CustomDrainChannelEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case CustomSpoutChannelEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case PrioritySyncEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case BlockingSyncEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case BlockingSinkSyncEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		case BlockingSourceSyncEditPart.VISUAL_ID:
			return getGEFWrapper(new ChannelReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
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
		case NodeSourceEndsEditPart.VISUAL_ID:
			return getGEFWrapper(new NodeSourceEndsReorientCommand(req));
		case SinkEndNodeEditPart.VISUAL_ID:
			return getGEFWrapper(new SinkEndNodeReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
