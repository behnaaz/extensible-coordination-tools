package org.ect.ea.diagram.contributions.commands;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.ect.ea.EA;
import org.ect.ea.IExtensionDefinition;
import org.ect.ea.IExtensionRegistry;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.edit.AutomatonProxyEditPart;



public class ActivateExtensionCommand extends ChangeExtensionsCommand {
	
	// The currently selected automata.
	private List<AutomatonProxyEditPart> editparts;
	
	// Flag indicating whether the extension should be activated or deactivated.
	private boolean activate;

	// Extension definition to be activated / deactivated.
	private IExtensionDefinition definition;

	// Extension registry to be used.
	private IExtensionRegistry registry;
	
	
	/**
	 * Default constructor.
	 */
	public ActivateExtensionCommand(boolean activate, IGraphicalEditPart container) {
		super("(de)activate extension", container);
		this.activate = activate;
		this.registry = EA.getExtensionRegistry();
	}
	
	/**
	 * Perform the activation command.
	 */
	protected void performExtensionsChange(IProgressMonitor monitor) {
		
		monitor.beginTask("(de)activate extension", 2);
		
		if (!canExecute()) return;
		IExtensionDefinition[] dependencies;
		
		if (activate) dependencies = registry.getResolvedDependencies(definition);
		else dependencies = registry.getReverseDependencies(definition);
		
		// (De)activate all dependencies.
		for (IExtensionDefinition dependency : dependencies) {
			for (AutomatonProxyEditPart editpart : editparts) {
				Automaton automaton = editpart.getAutomaton();
				doActivation(dependency.getId(), automaton);
			}
		}
		
		monitor.worked(1);
		
		// (De)activate the extension definition.
		for (AutomatonProxyEditPart editpart : editparts) {
			Automaton automaton = editpart.getAutomaton();
			doActivation(definition.getId(), automaton);
		}
		
		monitor.worked(1);
		monitor.done();

	}
	
	
	protected void doActivation(String id, Automaton automaton) {
		// Activation.
		if (activate && !automaton.getUsedExtensionIds().contains(id)) {
			automaton.getUsedExtensionIds().add(id);
		} 
		// Deactivation.
		else if (!activate && automaton.getUsedExtensionIds().contains(id)) {
			automaton.getUsedExtensionIds().remove(id);
		}
	}
	
	
	@Override
	public boolean canExecute() {
		// Check if there are any unresolved dependencies.
		if (activate && registry.getUnresolvedDependencies(definition).length!=0) {
			return false;
		}
		return true;
	}
	

	public void setExtensionDefinition(IExtensionDefinition definition) {
		this.definition = definition;
	}
	
	
	public void setEditparts(List<AutomatonProxyEditPart> editparts) {
		this.editparts = editparts;
	}	

}
