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
package org.ect.ea.extensions.costs.providers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.Transition;
import org.ect.ea.costs.CostsAlgebra;
import org.ect.ea.costs.algebras.AlgebrasFactory;
import org.ect.ea.diagram.contributions.actions.ChangeExtensionsAction;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;
import org.ect.ea.diagram.edit.parts.AutomatonEditPart;
import org.ect.ea.extensions.costs.CostsAlgebraExtension;
import org.ect.ea.extensions.costs.CostsObjectExtension;

public class CostsAction extends ChangeExtensionsAction {
	
	private AutomatonEditPart selected;
	private Automaton automaton;
	private EClass algebraClass;
	private CostsAlgebraExtension algebras;
	private boolean add;

	
	/**
	 * Default constructor.
	 * @param selected Selected automaton editpart.
	 * @param add Flag indicating whether the algebra should be added.
	 */
	public CostsAction(AutomatonEditPart selected, boolean add, EClass algebraClass) {
		this.selected = selected;
		this.automaton = (Automaton) selected.getNotationView().getElement();
		this.algebras = (CostsAlgebraExtension) automaton.findExtension(CostAlgebrasProvider.EXTENSION_ID);
		this.algebraClass = algebraClass;
		this.add = add;
		
		CostsAlgebra algebra = createAlgebra();
		setText(algebra==null ? "?" : algebra.getName());
	}
	
	
	
	@Override
	protected ChangeExtensionsCommand getCommand() {
		
		ChangeExtensionsCommand command = new ChangeExtensionsCommand("change costs", selected) {

			@Override
			protected void performExtensionsChange(IProgressMonitor monitor) {
				
				monitor.beginTask("Change costs", 1);
				
				// Adding costs and an algebra.
				if (add) {
					
					// Adding the algebra.
					CostsAlgebra algebra = createAlgebra();
					algebras.getCostsAlgebras().add(algebra);
					
					// Adding the costs.
					for (Transition transition : automaton.getTransitions()) {
						CostsObjectExtension costs = (CostsObjectExtension) transition.findExtension(CostValuesProvider.EXTENSION_ID);
						if (costs!=null) costs.getCostsObjects().add( algebra.getNull() );
					}

				} else {
					
					// Calculate the index of the algebra in the extension object.
					int index = algebras.getCostsAlgebras().size() - 1;
					CostsAlgebra algebra = null;
					
					while (index >= 0) {
						algebra = algebras.getCostsAlgebras().get(index);
						if (algebraClass.isInstance(algebra)) break;
						index--;
					}
				
					// Remove all costs objects.
					for (Transition transition : automaton.getTransitions()) {
						CostsObjectExtension costs = (CostsObjectExtension) transition.findExtension(CostValuesProvider.EXTENSION_ID);
						if (costs!=null) costs.getCostsObjects().remove( index );
					}
					
					// Remove the algebra.
					algebras.getCostsAlgebras().remove(index);
				}
				
				monitor.worked(1);
				monitor.done();
				
			}
			
		};
		
		return command;
	}

	
	/*
	 * Create a new instance of the algebra class.
	 */
	protected CostsAlgebra createAlgebra() {
		CostsAlgebra algebra = (CostsAlgebra) AlgebrasFactory.eINSTANCE.create(algebraClass);		
		return algebra;
	}
	
}
