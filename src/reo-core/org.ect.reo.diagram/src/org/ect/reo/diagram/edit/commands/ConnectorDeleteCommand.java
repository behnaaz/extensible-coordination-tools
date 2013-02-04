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
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.ect.reo.Connector;
import org.ect.reo.Node;
import org.ect.reo.Primitive;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class ConnectorDeleteCommand extends DestroyElementCommand {

	public ConnectorDeleteCommand(DestroyElementRequest request) {
		super(request);
	}

	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		// Prepare for delete:
		Connector connector = (Connector) getElementToDestroy();
		for (Connector subConnector : connector.getAllSubConnectors()) {
			prepareForDelete(subConnector);
		}
		prepareForDelete(connector);
		
		// Delete:
		return super.doExecuteWithResult(monitor, info);
		
	}
	
	private void prepareForDelete(Connector connector) {
		// Unhook all primitives.
		for (Primitive primitive : connector.getPrimitives()) {
			primitive.disconnectEnds();
		}
		// And nodes.
		for (Node node : connector.getNodes()) {
			node.getSourceEnds().clear();
			node.getSinkEnds().clear();
		}
		// Delete primitives and nodes.
		connector.getPrimitives().clear();
		connector.getNodes().clear();
	}
	
	@Override
	public boolean canExecute() {
		return (super.canExecute() && (getElementToDestroy() instanceof Connector));
	}
	
}
