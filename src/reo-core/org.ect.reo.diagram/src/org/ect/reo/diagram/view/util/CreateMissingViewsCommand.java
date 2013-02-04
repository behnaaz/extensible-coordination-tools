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
package org.ect.reo.diagram.view.util;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;


/**
 * @author Christian Krause
 * @generated NOT
 */
public class CreateMissingViewsCommand extends AbstractTransactionalCommand {

	private Module module;
	
	private boolean diagramOnly = false;
	
	/**
	 * Constructor.
	 */
	@SuppressWarnings("unchecked")
	public CreateMissingViewsCommand(TransactionalEditingDomain domain, Module module, List affectedFiles) {
		super(domain, "Create Missing Views", affectedFiles);
		this.module = module;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		if (diagramOnly) {
			
			Diagram diagram = GenericViewUtil.findDiagram(module);
			if (diagram==null) ReoViewCreator.createModuleView(module);
			
		} else {
			
			// Create all missing views.
			ReoViewUtil.updateViews(module);
			
			// Persist the changes.		
			Diagram diagram = GenericViewUtil.findDiagram(module);
			diagram.persist();
			diagram.persistChildren();
			diagram.persistEdges();
			
		}
		return CommandResult.newOKCommandResult();
		
	}

	public boolean shouldExecute() {
		
		Diagram diagram = GenericViewUtil.findDiagram(module);
		if (diagram==null) return true;
		
		if (!diagramOnly) {
			for (Connector connector : module.getConnectors()) {
				if (ReoViewFinder.findConnectorView(connector, diagram)==null) return true;
			}
			for (Component component : module.getComponents()) {
				if (ReoViewFinder.findComponentView(component, diagram)==null) return true;
			}
		}
		return false;
	}
	
	public void setDiagramOnly(boolean diagramOnly) {
		this.diagramOnly = diagramOnly;
	}
}
