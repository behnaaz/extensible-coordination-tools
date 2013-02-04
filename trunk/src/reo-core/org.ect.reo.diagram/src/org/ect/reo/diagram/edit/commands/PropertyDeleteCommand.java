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
import org.ect.reo.Component;
import org.ect.reo.Property;



/**
 * Command for deleting properties.
 * @generated NOT
 */
public class PropertyDeleteCommand extends DestroyElementCommand {
	
	public PropertyDeleteCommand(DestroyElementRequest request) {
		super(request);
	}

	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		Property property = (Property) getElementToDestroy();
		Component component = (Component) property.eContainer();
		component.getProperties().remove(property.getKey());
		
		return CommandResult.newOKCommandResult();
		
	}
	
	
	@Override
	public boolean canExecute() {
		return (super.canExecute() && (getElementToDestroy() instanceof Property) &&
				(getElementToDestroy().eContainer() instanceof Component));
	}
	
}
