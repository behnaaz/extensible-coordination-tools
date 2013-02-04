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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.channels.AsyncDrain;
import org.ect.reo.channels.AsyncSpout;
import org.ect.reo.channels.BlockingSinkSync;
import org.ect.reo.channels.BlockingSourceSync;
import org.ect.reo.channels.BlockingSync;
import org.ect.reo.channels.Channel;
import org.ect.reo.channels.ChannelsFactory;
import org.ect.reo.channels.ChannelsPackage;
import org.ect.reo.channels.CustomDirectedChannel;
import org.ect.reo.channels.CustomDrainChannel;
import org.ect.reo.channels.CustomSpoutChannel;
import org.ect.reo.channels.Filter;
import org.ect.reo.channels.LossySync;
import org.ect.reo.channels.PrioritySync;
import org.ect.reo.channels.Sync;
import org.ect.reo.channels.SyncDrain;
import org.ect.reo.channels.SyncSpout;
import org.ect.reo.channels.Timer;
import org.ect.reo.channels.Transform;
import org.ect.reo.diagram.edit.policies.ReoBaseItemSemanticEditPolicy;
import org.ect.reo.diagram.edit.policies.ReoBaseItemSemanticEditPolicy.LinkConstraints;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class ChannelCreateCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final EObject source;

	/**
	 * @generated
	 */
	private final EObject target;

	/**
	 * @generated
	 */
	private final Connector container;

	/**
	 * @generated
	 */
	public ChannelCreateCommand(CreateRelationshipRequest request,
			EObject source, EObject target) {
		super(request.getLabel(), null, request);
		this.source = source;
		this.target = target;
		container = deduceContainer(source, target);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean canExecute() {

		if (source == null && target == null) {
			return false;
		}

		// Determine what kind of channel should be reated.
		EClass type = (EClass) ReoElementTypes.getElement(getCreateRequest()
				.getElementType());
		boolean directed = (ChannelsPackage.eINSTANCE.getDirectedChannel()
				.isSuperTypeOf(type));
		boolean spout = (ChannelsPackage.eINSTANCE.getSpoutChannel()
				.isSuperTypeOf(type));
		boolean drain = (ChannelsPackage.eINSTANCE.getDrainChannel()
				.isSuperTypeOf(type));

		if (source != null) {
			if (!(source instanceof Node))
				return false;
			if ((directed || drain)
					&& LinkConstraints.hasReader((Node) source, getContainer()))
				return false;
			if (spout
					&& LinkConstraints.hasWriter((Node) source, getContainer()))
				return false;
		}
		if (target != null) {
			if (!(target instanceof Node))
				return false;
			if ((directed || spout)
					&& LinkConstraints.hasWriter((Node) target, getContainer()))
				return false;
			if (drain
					&& LinkConstraints.hasReader((Node) source, getContainer()))
				return false;
		}

		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}

		return true;
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in create link command"); //$NON-NLS-1$
		}

		ENamedElement type = ReoElementTypes.getElement(getCreateRequest()
				.getElementType());
		Channel channel = (Channel) ChannelsFactory.eINSTANCE
				.create((EClass) type);

		getContainer().getPrimitives().add(channel);

		channel.setNodeOne(getSource());
		channel.setNodeTwo(getTarget());

		doConfigure(channel, monitor, info);
		((CreateElementRequest) getRequest()).setNewElement(channel);
		return CommandResult.newOKCommandResult(channel);

	}
	
	/**TODO
	 * @generated NOT
	 */
	 
	protected void doConfigure(BlockingSourceSync newElement,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE,
				getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET,
				getTarget());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**TODO
	 * @generated NOT
	 */
	 
	protected void doConfigure(BlockingSinkSync newElement,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE,
				getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET,
				getTarget());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}
	
	/**TODO
	 * @generated NOT
	 */
	 
	protected void doConfigure(BlockingSync newElement,
			IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE,
				getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET,
				getTarget());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	 * @generated NOT
	 */
	private CreateElementRequest getCreateRequest() {
		return (CreateElementRequest) getRequest();
	}

	/**
	 * @generated NOT
	 */
	protected void doConfigure(Channel newElement, IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {

		IElementType elementType = ((CreateElementRequest) getRequest())
				.getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(
				getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest())
				.getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE,
				getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET,
				getTarget());
		ICommand configureCommand = elementType
				.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected Node getSource() {
		return (Node) source;
	}

	/**
	 * @generated
	 */
	protected Node getTarget() {
		return (Node) target;
	}

	/**
	 * @generated
	 */
	public Connector getContainer() {
		return container;
	}

	/**
	 * Default approach is to traverse ancestors of the source to find instance of container.
	 * Modify with appropriate logic.
	 * @generated
	 */
	private static Connector deduceContainer(EObject source, EObject target) {
		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof Connector) {
				return (Connector) element;
			}
		}
		return null;
	}
}
