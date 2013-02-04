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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;


import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.ect.reo.ReoPackage;
import org.ect.reo.diagram.edit.commands.ComponentCreateCommand;
import org.ect.reo.diagram.edit.commands.ConnectorCreateCommand;
import org.ect.reo.diagram.edit.commands.ReaderCreateCommand;
import org.ect.reo.diagram.edit.commands.WriterCreateCommand;
import org.ect.reo.diagram.providers.ReoElementTypes;

/**
 * @generated
 */
public class ModuleItemSemanticEditPolicy extends ReoBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ModuleItemSemanticEditPolicy() {
		super(ReoElementTypes.Module_1000);
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getCreateCommand(CreateElementRequest req) {
		if (ReoElementTypes.Connector_1001 == req.getElementType()) {
			return getGEFWrapper(new ConnectorCreateCommand(req));
		}
		if (ReoElementTypes.Reader_1002 == req.getElementType()) {
			return getGEFWrapper(new ReaderCreateCommand(req));
		}
		if (ReoElementTypes.Writer_1003 == req.getElementType()) {
			return getGEFWrapper(new WriterCreateCommand(req));
		}
		if (ReoElementTypes.Component_1004 == req.getElementType()) {
			return getGEFWrapper(new ComponentCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}
	}
}
