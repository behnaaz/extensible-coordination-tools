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
package org.ect.reo.ui.actions;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Size;
import org.ect.reo.Module;
import org.ect.reo.Network;
import org.ect.reo.Reconfigurable;
import org.ect.reo.diagram.view.util.NetworkView;
import org.ect.reo.diagram.view.util.ReoViewCopier;


/**
 * Command for duplicating a network. This command automatically
 * copies the diagram information of the network as well and shifts
 * the nodes.
 * @author Christian Krause
 * @generated NOT
 */
public class DuplicateNetworkCommand extends AbstractTransactionalCommand {
	
	// Input and output network:
	private Network network, result;
	
	// Diagram to be used:
	private Diagram diagram;
	
	// Target module:
	private Module module;
	
	/**
	 * Default constructor.
	 * @param network Network to be copied.
	 * @param module Module to be copied into.
	 * @param diagram Diagram to be used.
	 * @param domain Editing domain.
	 */
	public DuplicateNetworkCommand(Network network, Module module, Diagram diagram, TransactionalEditingDomain domain) {
		super(domain, "Duplicate Network", null);
		this.network = network;
		this.module = module;
		this.diagram = diagram;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable arg1) throws ExecutionException {
		
		monitor.beginTask("Duplicate Network", 3);
		
		// Copy the network.
		Copier copier = Module.copyContent(network, module);
		monitor.worked(1);
		
		// Remove reconfiguration actions:
		for (EObject object : copier.values()) {
			if (object instanceof Reconfigurable) {
				((Reconfigurable) object).getReconfActions().clear();
			}
		}
		
		// Copy the views.
		ReoViewCopier.copyViews(copier, diagram);
		monitor.worked(1);
		
		// Create the result network and a corresponding network view.
		result = new Network(copier.values());
		NetworkView view = new NetworkView(result);
		
		// Move the view.
		int space = 20;
		Size size = view.getBounds();
		
		if (size.getWidth()>=size.getHeight()) {
			// Move it below the original one.
			view.move(0, size.getHeight() + space);
		} else {
			// Move it to the right.
			view.move(size.getWidth() + space, 0);
		}
		monitor.worked(1);
		
		monitor.done();
		return CommandResult.newOKCommandResult();
	}
	
	
	public Network getResult() {
		return result;
	}
}
