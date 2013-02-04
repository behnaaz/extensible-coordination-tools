package org.ect.ea.diagram.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.diagram.providers.AutomataElementTypes;


/**
 * @generated
 */
public class StateCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public StateCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return AutomataPackage.eINSTANCE.getAutomaton();
	}

	/**
	 * @generated
	 */
	protected EObject doDefaultElementCreation() {
		State newElement = AutomataFactory.eINSTANCE.createState();

		Automaton owner = (Automaton) getElementToEdit();
		owner.getStates().add(newElement);

		AutomataElementTypes.init_State_2001(newElement);
		return newElement;
	}
}
