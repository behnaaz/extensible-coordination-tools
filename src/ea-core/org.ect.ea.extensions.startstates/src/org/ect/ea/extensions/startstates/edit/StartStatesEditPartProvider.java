package org.ect.ea.extensions.startstates.edit;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IMenuManager;
import org.ect.ea.IEditPartProvider;
import org.ect.ea.automata.State;
import org.ect.ea.extensions.startstates.StartStateAction;



public class StartStatesEditPartProvider implements IEditPartProvider {

	public void contributeToContextMenu(IMenuManager menu, IGraphicalEditPart[] selection) {
		
		// Check whether the selection is empty.
		if (selection.length==0) return;
		
		// Check whether there is at least one state selected.
		boolean stateSelected = false;
		for (IGraphicalEditPart editpart : selection) {
			if (editpart.getNotationView().getElement() instanceof State) {
				stateSelected = true;
				break;
			}
		}
		
		// Check whether there is at least one automaton selected.
		/*boolean automatonSelected = false;
		for (IGraphicalEditPart editpart : selection) {
			if (editpart.getNotationView().getElement() instanceof Automaton) {
				automatonSelected = true;
				break;
			}
		}
		*/
		
		// Add the action to the context menu.
		if (stateSelected) {
			menu.add(new StartStateAction(selection));
		}
		
		/*// Add the action to the context menu.
		if (automatonSelected) {
			menu.add(new RemoveUnreachableStatesAction(selection));
		}*/


	}

	public IGraphicalEditPart createEditPart(View view) {
		
		// Editpart for invisible nodes.
		if (InvisibleNodeEditPart.VISUAL_ID.equals(view.getType())) {
			return new InvisibleNodeEditPart(view);
		}
		
		// Editpart for fake transitions.
		if (FakeTransitionEditPart.VISUAL_ID.equals(view.getType())) {
			return new FakeTransitionEditPart(view);
		}
		return null;
	}

}
