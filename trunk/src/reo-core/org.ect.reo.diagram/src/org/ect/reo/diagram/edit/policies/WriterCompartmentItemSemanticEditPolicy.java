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
import org.ect.reo.diagram.edit.commands.PropertyCreateCommand;
import org.ect.reo.diagram.providers.ReoElementTypes;


/**
 * @generated
 */
public class WriterCompartmentItemSemanticEditPolicy extends
		ReoBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public WriterCompartmentItemSemanticEditPolicy() {
		super(ReoElementTypes.Writer_1003);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ReoElementTypes.Property_2004 == req.getElementType()) {
			return getGEFWrapper(new PropertyCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
