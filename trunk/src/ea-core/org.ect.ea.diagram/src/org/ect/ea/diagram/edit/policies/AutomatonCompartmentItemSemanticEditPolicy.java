package org.ect.ea.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.diagram.edit.commands.StateCreateCommand;
import org.ect.ea.diagram.providers.AutomataElementTypes;


/**
 * @generated
 */
public class AutomatonCompartmentItemSemanticEditPolicy extends
		AutomataBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (AutomataElementTypes.State_2001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(AutomataPackage.eINSTANCE
						.getAutomaton_States());
			}
			return getGEFWrapper(new StateCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
