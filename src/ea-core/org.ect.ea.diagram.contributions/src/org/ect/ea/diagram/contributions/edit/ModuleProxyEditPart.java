package org.ect.ea.diagram.contributions.edit;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.ect.ea.automata.AutomataPackage;
import org.ect.ea.diagram.contributions.commands.AutomatonCreateCommand;
import org.ect.ea.diagram.edit.parts.ModuleEditPart;
import org.ect.ea.diagram.edit.policies.ModuleItemSemanticEditPolicy;
import org.ect.ea.diagram.providers.AutomataElementTypes;



public class ModuleProxyEditPart extends ModuleEditPart {

	public ModuleProxyEditPart(View view) {
		super(view);
	}
	
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		
		// Remove the old semantic edit policy.
		removeEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
		
		// Use our custom version.
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ModuleItemSemanticEditPolicy() {
			
			@Override
			protected Command getCreateCommand(CreateElementRequest req) {
				
				// We use a custom command for creating automata.
				if (AutomataElementTypes.Automaton_1001 == req.getElementType()) {
					
					// This might be needed by a view factory.
					if (req.getContainmentFeature()==null) {
						req.setContainmentFeature(AutomataPackage.eINSTANCE.getModule_Automata());
					}
					
					// Create our own command.
					return getGEFWrapper(new AutomatonCreateCommand((IGraphicalEditPart) getHost(),req));
				}
				return super.getCreateCommand(req);
			}

		});
	}
	
}
