package org.ect.ea.extensions.constraints.providers;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IMenuManager;
import org.ect.ea.IEditPartProvider;
import org.ect.ea.automata.Automaton;
import org.ect.ea.extensions.constraints.refinement.ComputeRefinementAction;


public class RefinementMenuProvider implements IEditPartProvider{

	public void contributeToContextMenu(IMenuManager menu,
			IGraphicalEditPart[] selection) {
		// Check whether the selection is empty.
		if (selection.length==0) return;
		
		// Check whether there is at least one state selected.
		boolean automatonSelected = false;
		for (IGraphicalEditPart editpart : selection) {
			if (editpart.getNotationView().getElement() instanceof Automaton) {
				automatonSelected = true;
				break;
			}
		}
		
		// Add the action to the context menu.
		if (automatonSelected) {
			menu.add(new ComputeRefinementAction(selection));
		}
		
	}
		
	

	public IGraphicalEditPart createEditPart(View view) {
		return null;
	}

}
