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
package org.ect.reo.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.CustomDirectedChannel;
import org.ect.reo.channels.CustomDrainChannel;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.channels.DirectedChannel;
import org.ect.reo.channels.DrainChannel;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.channels.SpoutChannel;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.channels.Timer;
import org.ect.reo.channels.Transform;
import org.ect.reo.diagram.edit.policies.ReoBaseItemSemanticEditPolicy;


/**
 * @generated
 */
public class ChannelReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public ChannelReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof Channel) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated NOT
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof Node && newEnd instanceof Node)) {
			return false;
		}
		Node target = getLink().getNodeTwo();
		if (!(getLink().eContainer() instanceof Connector)) {
			return false;
		}
		Connector container = (Connector) getLink().eContainer();

		// Check channel constraints.
		if (getLink() instanceof DirectedChannel)
			return ReoBaseItemSemanticEditPolicy.LinkConstraints
					.canExistDirectedChannel(container, getNewSource(), target);

		if (getLink() instanceof DrainChannel)
			return ReoBaseItemSemanticEditPolicy.LinkConstraints
					.canExistDrainChannel(container, getNewSource(), target);

		if (getLink() instanceof SpoutChannel)
			return ReoBaseItemSemanticEditPolicy.LinkConstraints
					.canExistSpoutChannel(container, getNewSource(), target);

		return false;
	}

	/**
	 * @generated NOT
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof Node && newEnd instanceof Node)) {
			return false;
		}
		Node source = getLink().getNodeOne();
		if (!(getLink().eContainer() instanceof Connector)) {
			return false;
		}
		Connector container = (Connector) getLink().eContainer();

		// Check channel constraints.
		if (getLink() instanceof DirectedChannel)
			return ReoBaseItemSemanticEditPolicy.LinkConstraints
					.canExistDirectedChannel(container, source, getNewTarget());

		if (getLink() instanceof DrainChannel)
			return ReoBaseItemSemanticEditPolicy.LinkConstraints
					.canExistDrainChannel(container, source, getNewTarget());

		if (getLink() instanceof SpoutChannel)
			return ReoBaseItemSemanticEditPolicy.LinkConstraints
					.canExistSpoutChannel(container, source, getNewTarget());

		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setNodeOne(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setNodeTwo(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated NOT
	 */
	protected Channel getLink() {
		return (Channel) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected Node getOldSource() {
		return (Node) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Node getNewSource() {
		return (Node) newEnd;
	}

	/**
	 * @generated
	 */
	protected Node getOldTarget() {
		return (Node) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Node getNewTarget() {
		return (Node) newEnd;
	}
}
