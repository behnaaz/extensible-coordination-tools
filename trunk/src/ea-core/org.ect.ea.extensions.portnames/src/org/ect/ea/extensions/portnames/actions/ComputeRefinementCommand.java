package org.ect.ea.extensions.portnames.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.ect.ea.automata.Automaton;
import org.ect.ea.diagram.contributions.commands.ChangeExtensionsCommand;


public class ComputeRefinementCommand extends ChangeExtensionsCommand {

	private Automaton automaton;
	
	
	public ComputeRefinementCommand(Automaton automaton, IGraphicalEditPart editpart) {
		super("Compute Refinement", editpart);
		this.automaton = automaton;
	}

	
	@Override
	protected void performExtensionsChange(IProgressMonitor monitor) {
		QIARefinement refinement = new QIARefinement();
		refinement.compute(automaton, monitor);
		updateNormalViews();
	}
	
	
}
