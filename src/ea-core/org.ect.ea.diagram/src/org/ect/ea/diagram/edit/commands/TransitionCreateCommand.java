package org.ect.ea.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.ect.ea.automata.AutomataFactory;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.automata.Automaton;
import org.ect.ea.automata.State;
import org.ect.ea.automata.Transition;
import org.ect.ea.diagram.edit.policies.AutomataBaseItemSemanticEditPolicy;


/**
 * @generated
 */
public class TransitionCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	private final EObject source;

	/**
	 * @generated
	 */
	private final EObject target;

	/**
	 * @generated
	 */
	private Automaton container;

	/**
	 * @generated
	 */
	public TransitionCreateCommand(CreateRelationshipRequest request,
			EObject source, EObject target) {
		super(request);
		this.source = source;
		this.target = target;
		if (request.getContainmentFeature() == null) {
			setContainmentFeature(AutomataPackage.eINSTANCE
					.getAutomaton_Transitions());
		}

		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element
				.eContainer()) {
			if (element instanceof Automaton) {
				container = (Automaton) element;
				super.setElementToEdit(container);
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (source == null && target == null) {
			return false;
		}
		if (source != null && false == source instanceof State) {
			return false;
		}
		if (target != null && false == target instanceof State) {
			return false;
		}
		if (getSource() == null) {
			return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) {
			return false;
		}
		return AutomataBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateTransition_3001(getContainer(), getSource(),
						getTarget());
	}

	/**
	 * @generated
	 */
	protected EObject doDefaultElementCreation() {
		Transition newElement = AutomataFactory.eINSTANCE.createTransition();
		getContainer().getTransitions().add(newElement);
		newElement.setSource(getSource());
		newElement.setTarget(getTarget());
		return newElement;
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
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in create link command"); //$NON-NLS-1$
		}
		return super.doExecuteWithResult(monitor, info);
	}

	/**
	 * @generated
	 */
	protected ConfigureRequest createConfigureRequest() {
		ConfigureRequest request = super.createConfigureRequest();
		request.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		request.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		return request;
	}

	/**
	 * @generated
	 */
	protected void setElementToEdit(EObject element) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected State getSource() {
		return (State) source;
	}

	/**
	 * @generated
	 */
	protected State getTarget() {
		return (State) target;
	}

	/**
	 * @generated
	 */
	public Automaton getContainer() {
		return container;
	}
}
