package org.ect.ea.diagram.edit.helpers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.ect.ea.automata.State;


/**
 * @generated
 */
public class AutomatonEditHelper extends AutomataBaseEditHelper {

	/**
	 * Destroy incoming/outgoing transitions when a state is deleted. 
	 * @generated NOT
	 */
	protected ICommand getDestroyElementCommand(DestroyElementRequest request) {

		CompositeCommand command = new CompositeCommand("Delete Transitions");

		if (request.getElementToDestroy() instanceof State) {
			State state = (State) request.getElementToDestroy();
			// Incoming edges.
			for (int i = 0; i < state.getIncoming().size(); i++) {
				request = new DestroyElementRequest(request.getEditingDomain(),
						(EObject) state.getIncoming().get(i), false);
				command.add(new DestroyElementCommand(request));
			}
			// Outgoing edges.
			for (int i = 0; i < state.getOutgoing().size(); i++) {
				request = new DestroyElementRequest(request.getEditingDomain(),
						(EObject) state.getOutgoing().get(i), false);
				command.add(new DestroyElementCommand(request));
			}

		}

		return command.isEmpty() ? null : command;

	}

}