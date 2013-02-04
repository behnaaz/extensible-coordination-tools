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
package org.ect.ea.diagram.contributions.commands;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.ect.ea.IProductDefinition;
import org.ect.ea.automata.AbstractProductProvider;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Module;
import org.ect.ea.automata.AbstractProductProvider.ProductWrapper;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;

public class ComputeProductCommand extends ChangeExtensionsCommand {

	private List<Automaton> automata;
	private Automaton product;
	private Module module;
	private IProductDefinition definition;
	
	
	public ComputeProductCommand(ModuleEditPart editpart, List<Automaton> automata, IProductDefinition definition) {
		super("Compute Product", editpart);
		this.automata = automata;
		this.module = (Module) editpart.getNotationView().getElement();
		this.definition = definition;
	}


	@Override
	protected void performExtensionsChange(IProgressMonitor monitor) {
		
		AbstractProductProvider provider;
		if (definition.getProvider() instanceof AbstractProductProvider) {
			provider = (AbstractProductProvider) definition.getProvider();
		} else {
			provider = new ProductWrapper(definition.getProvider());
		}
		
		product = provider.computeProduct(automata, monitor);
		module.getAutomata().add(product);
		monitor.done();
		
	}
	
}

