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

import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.ect.reo.channels.Channel;
import org.ect.reo.diagram.edit.commands.ChannelReorientCommand;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class ChannelItemSemanticEditPolicy extends
		ReoBaseItemSemanticEditPolicy {

	/**TODO???????????
	 * @generated
	 */
	public ChannelItemSemanticEditPolicy() {
		super(ReoElementTypes.BlockingSync_3020);
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated NOT
	 */
	@Override
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {

		// Any channels to reconnect?
		List<Channel> channels = new Vector<Channel>();
		for (Object element : req.getElementsToEdit()) {
			if (element instanceof Channel)
				channels.add((Channel) element);
		}

		return !channels.isEmpty() ? getGEFWrapper(new ChannelReorientCommand(
				req)) : super.getReorientRelationshipCommand(req);
	}

}
