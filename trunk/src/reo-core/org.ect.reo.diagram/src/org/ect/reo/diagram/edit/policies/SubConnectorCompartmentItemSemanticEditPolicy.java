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

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.ect.reo.ReoPackage;
import org.ect.reo.diagram.edit.commands.InternalComponentCreateCommand;
import org.ect.reo.diagram.edit.commands.InternalReaderCreateCommand;
import org.ect.reo.diagram.edit.commands.InternalWriterCreateCommand;
import org.ect.reo.diagram.edit.commands.NodeCreateCommand;
import org.ect.reo.diagram.edit.commands.SubConnectorCreateCommand;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class SubConnectorCompartmentItemSemanticEditPolicy extends
		ReoBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public SubConnectorCompartmentItemSemanticEditPolicy() {
		super(ReoElementTypes.Connector_2005);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ReoElementTypes.Node_2001 == req.getElementType()) {
			return getGEFWrapper(new NodeCreateCommand(req));
		}
		if (ReoElementTypes.Connector_2005 == req.getElementType()) {
			return getGEFWrapper(new SubConnectorCreateCommand(req));
		}
		if (ReoElementTypes.Reader_2008 == req.getElementType()) {
			return getGEFWrapper(new InternalReaderCreateCommand(req));
		}
		if (ReoElementTypes.Writer_2009 == req.getElementType()) {
			return getGEFWrapper(new InternalWriterCreateCommand(req));
		}
		if (ReoElementTypes.Component_2010 == req.getElementType()) {
			return getGEFWrapper(new InternalComponentCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
