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
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.ect.reo.Component;
import org.ect.reo.Connector;
import org.ect.reo.Module;
import org.ect.reo.diagram.view.util.ReoViewCreator;
import org.ect.reo.prefs.ReoPreferences;
import org.ect.reo.util.ComponentCreator;



/**
 * @author Christian Krause
 * @generated NOT
 */
public class ConvertToComponentCommand extends AbstractTransactionalCommand {
	
	private Connector connector;
	private Component result;
	private Bounds bounds;
	private Diagram diagram;
	
	
	public ConvertToComponentCommand(Connector connector, TransactionalEditingDomain domain, Diagram diagram) {
		super(domain, "Convert to Component", null);
		this.connector = connector;
		this.diagram = diagram;
	}
	
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable arg1) throws ExecutionException {
		
		monitor.beginTask("Convert to Component", 10);
		
		// Use the right engine.
		connector.setColouringEngine(ReoPreferences.getColouringEngine());
		
		// Create the new component.
		result = ComponentCreator.fromConnector(connector, new SubProgressMonitor(monitor,9));
		
		Module module = connector.getModule();
		if (module!=null) module.getComponents().add(result);
		
		// Create the views.
		Node node = ReoViewCreator.createComponentView(result, diagram);
		if (bounds==null) {
			bounds = NotationFactory.eINSTANCE.createBounds();
		}
		node.setLayoutConstraint(bounds);		
		monitor.worked(1);
		
		monitor.done();
		return CommandResult.newOKCommandResult();
		
	}
	
	public Component getResult() {
		return result;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = (Bounds) EcoreUtil.copy(bounds);
	}

}
